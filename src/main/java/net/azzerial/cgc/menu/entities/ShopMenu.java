package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.items.core.ItemType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.requests.RestAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ShopMenu extends Menu {

	private static final String BACK_ARROW = "\uD83D\uDD19";
	private static final String DIGIT_ONE = "\u0031\u20E3";
	private static final String DIGIT_TWO = "\u0032\u20E3";
	private static final String DIGIT_THREE = "\u0033\u20E3";
	private static final String HEAVY_MULTIPLICATION_X = "\u2716";
	private static final String LABEL = "\uD83C\uDFF7";
	private static final String SHOPPING_BAGS = "\uD83D\uDECD";
	private static final String WRAPPED_GIFT = "\uD83C\uDF81";

	public static final int IDOL_BOX_PRICE = 400;
	public static final int SPECIAL_IDOL_BOX_PRICE = 900;
	public static final int DIVINE_IDOL_BOX_PRICE = 2000;
	public static final int SKIN_BOX_PRICE = 200;
	public static final int SPECIAL_SKIN_BOX_PRICE = 450;
	public static final int DIVINE_SKIN_BOX_PRICE = 1000;
	public static final int PASSIVE_BOX_PRICE = 500;
	public static final int SPECIAL_PASSIVE_BOX_PRICE = 1125;
	public static final int DIVINE_PASSIVE_BOX_PRICE = 2500;

	private final DatabaseUser databaseUser;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;

	private static boolean isDisplayingShopPage;
	private static int selectedShopPage;

	public ShopMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit, DatabaseUser databaseUser, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.databaseUser = databaseUser;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
	}

	@Override
	public void display(MessageChannel channel) {
		isDisplayingShopPage = false;
		selectedShopPage = 0;

		initialize(channel.sendMessage(getShopMenuMessage()));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

		if (isDisplayingShopPage) {
			emotes.add(BACK_ARROW);
			switch (selectedShopPage) {
				case 0:
					if (databaseUser.getBalance() >= IDOL_BOX_PRICE) {
						emotes.add(DIGIT_ONE);
					}
					if (databaseUser.getBalance() >= SPECIAL_IDOL_BOX_PRICE) {
						emotes.add(DIGIT_TWO);
					}
					if (databaseUser.getBalance() >= DIVINE_IDOL_BOX_PRICE) {
						emotes.add(DIGIT_THREE);
					}
					break;
				case 1:
					if (databaseUser.getBalance() >= SKIN_BOX_PRICE) {
						emotes.add(DIGIT_ONE);
					}
					if (databaseUser.getBalance() >= SPECIAL_SKIN_BOX_PRICE) {
						emotes.add(DIGIT_TWO);
					}
					if (databaseUser.getBalance() >= DIVINE_SKIN_BOX_PRICE) {
						emotes.add(DIGIT_THREE);
					}
					break;
				case 2:
					if (databaseUser.getBalance() >= PASSIVE_BOX_PRICE) {
						emotes.add(DIGIT_ONE);
					}
					if (databaseUser.getBalance() >= SPECIAL_PASSIVE_BOX_PRICE) {
						emotes.add(DIGIT_TWO);
					}
					if (databaseUser.getBalance() >= DIVINE_PASSIVE_BOX_PRICE) {
						emotes.add(DIGIT_THREE);
					}
					break;
			}
		} else {
			emotes.add(WRAPPED_GIFT);
			emotes.add(SHOPPING_BAGS);
			//emotes.add(LABEL);
		}
		if (closable) {
			emotes.add(HEAVY_MULTIPLICATION_X);
		}

		restAction.queue(m -> {
			for (int i = 0; i < emotes.size(); i += 1) {
				if (i + 1 < emotes.size()) {
					m.addReaction(emotes.get(i)).queue();
				} else {
					m.addReaction(emotes.get(i)).queue(a -> createEvent(m, emotes));
				}
			}
		});
	}

	private void createEvent(Message message, List<String> emotes) {
		scheduler.addNewMessage(MessageReactionAddEvent.class,
			(event) -> {
				if (!event.getMessageId().equals(message.getId())) {
					return (false);
				}
				if (event.getReaction().getReactionEmote().isEmote()) {
					event.getReaction().removeReaction().queue();
					return (false);
				}
				if (!emotes.contains(event.getReaction().getReactionEmote().getName())) {
					event.getReaction().removeReaction().queue();
					return (false);
				}
				return (canUserInteract(event.getUser(), event.getGuild()));
			},
			(MessageReactionAddEvent event) -> {
				boolean clearAllReactions = false;

				switch (event.getReaction().getReactionEmote().getName()) {
					case WRAPPED_GIFT:
						isDisplayingShopPage = true;
						selectedShopPage = 0;
						clearAllReactions = true;
						break;
					case SHOPPING_BAGS:
						isDisplayingShopPage = true;
						selectedShopPage = 1;
						clearAllReactions = true;
						break;
					/*case LABEL:
						isDisplayingShopPage = true;
						selectedShopPage = 2;
						clearAllReactions = true;
						break;*/
					case BACK_ARROW:
						isDisplayingShopPage = false;
						clearAllReactions = true;
						break;
					case DIGIT_ONE:
						if (selectedShopPage == 0) {
							databaseUser.updateBalance(databaseUser.getBalance() - IDOL_BOX_PRICE);
							databaseUser.updateInventory(ItemType.IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.IDOL_BOX) + 1));
						} else if (selectedShopPage == 1) {
							databaseUser.updateBalance(databaseUser.getBalance() - SKIN_BOX_PRICE);
							databaseUser.updateInventory(ItemType.SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.SKIN_BOX) + 1));
						} else if (selectedShopPage == 2) {
							databaseUser.updateBalance(databaseUser.getBalance() - PASSIVE_BOX_PRICE);
							databaseUser.updateInventory(ItemType.PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.PASSIVE_BOX) + 1));
						}
						break;
					case DIGIT_TWO:
						if (selectedShopPage == 0) {
							databaseUser.updateBalance(databaseUser.getBalance() - SPECIAL_IDOL_BOX_PRICE);
							databaseUser.updateInventory(ItemType.SPECIAL_IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_IDOL_BOX) + 1));
						} else if (selectedShopPage == 1) {
							databaseUser.updateBalance(databaseUser.getBalance() - SPECIAL_SKIN_BOX_PRICE);
							databaseUser.updateInventory(ItemType.SPECIAL_SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_SKIN_BOX) + 1));
						} else if (selectedShopPage == 2) {
							databaseUser.updateBalance(databaseUser.getBalance() - SPECIAL_PASSIVE_BOX_PRICE);
							databaseUser.updateInventory(ItemType.SPECIAL_PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_PASSIVE_BOX) + 1));
						}
						break;
					case DIGIT_THREE:
						if (selectedShopPage == 0) {
							databaseUser.updateBalance(databaseUser.getBalance() - DIVINE_IDOL_BOX_PRICE);
							databaseUser.updateInventory(ItemType.DIVINE_IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_IDOL_BOX) + 1));
						} else if (selectedShopPage == 1) {
							databaseUser.updateBalance(databaseUser.getBalance() - DIVINE_SKIN_BOX_PRICE);
							databaseUser.updateInventory(ItemType.DIVINE_SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_SKIN_BOX) + 1));
						} else if (selectedShopPage == 2) {
							databaseUser.updateBalance(databaseUser.getBalance() - DIVINE_PASSIVE_BOX_PRICE);
							databaseUser.updateInventory(ItemType.DIVINE_PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_PASSIVE_BOX) + 1));
						}
						break;
					case HEAVY_MULTIPLICATION_X:
						message.delete().queue();
						return;
				}
				if (!clearAllReactions) {
					event.getReaction().removeReaction(event.getUser()).queue();
				}

				if (clearAllReactions) {
					if (isDisplayingShopPage) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getShopPageMessage())));
					} else {
						message.clearReactions().queue(m -> initialize(message.editMessage(getShopMenuMessage())));
					}
				} else {
					if (isDisplayingShopPage) {
						initialize(message.editMessage(getShopPageMessage()));
					} else {
						initialize(message.editMessage(getShopMenuMessage()));
					}
				}
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	private Message getShopMenuMessage() {
		return (MessageUtil.getShopMenuMessage(getAuthor(), databaseUser));
	}

	private Message getShopPageMessage() {
		return (MessageUtil.getShopPageMessage(getAuthor(), databaseUser, selectedShopPage));
	}

	public static class Builder extends Menu.Builder<Builder, ShopMenu> {

		private DatabaseUser databaseUser;
		private boolean closable = false;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};

		@Override
		public ShopMenu build() {
			if (databaseUser == null) {
				return (null);
			}
			return (new ShopMenu(scheduler, users, roles, timeout, unit, databaseUser, closable, timeoutAction));
		}

		public ShopMenu.Builder setDatabaseUser(DatabaseUser databaseUser) {
			this.databaseUser = databaseUser;
			return (this);
		}

		public ShopMenu.Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}
	}

}
