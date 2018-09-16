package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.items.*;
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

public class InventoryMenu extends Menu {

	private static final String WRAPPED_GIFT = "\uD83C\uDF81";
	private static final String SHOPPING_BAGS = "\uD83D\uDECD";
	private static final String LABEL = "\uD83C\uDFF7";
	private static final String BACK_ARROW = "\uD83D\uDD19";
	private static final String DIGIT_ONE = "\u0031\u20E3";
	private static final String DIGIT_TWO = "\u0032\u20E3";
	private static final String DIGIT_THREE = "\u0033\u20E3";
	private static final String BALLOT_BOX_CHECK = "\u2611";
	private static final String HEAVY_MULTIPLICATION_X = "\u2716";

	private final DatabaseUser databaseUser;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;

	private static boolean isDisplayingUsedItemPage;
	private static boolean isDisplayingInventoryPage;
	private static int selectedInventoryPage;

	public InventoryMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit, DatabaseUser databaseUser, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.databaseUser = databaseUser;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
	}

	@Override
	public void display(MessageChannel channel) {
		isDisplayingInventoryPage = false;
		isDisplayingUsedItemPage = false;
		selectedInventoryPage = 0;

		initialize(channel.sendMessage(getInventoryMenuMessage()));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

		if (isDisplayingUsedItemPage) {
			emotes.add(BALLOT_BOX_CHECK);
		} else {
			if (isDisplayingInventoryPage) {
				emotes.add(BACK_ARROW);
				switch (selectedInventoryPage) {
					case 0:
						if (databaseUser.getInventory().getItemCount(ItemType.IDOL_BOX) != 0) {
							emotes.add(DIGIT_ONE);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_IDOL_BOX) != 0) {
							emotes.add(DIGIT_TWO);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.DIVINE_IDOL_BOX) != 0) {
							emotes.add(DIGIT_THREE);
						}
						break;
					case 1:
						if (databaseUser.getInventory().getItemCount(ItemType.SKIN_BOX) != 0) {
							emotes.add(DIGIT_ONE);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_SKIN_BOX) != 0) {
							emotes.add(DIGIT_TWO);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.DIVINE_SKIN_BOX) != 0) {
							emotes.add(DIGIT_THREE);
						}
						break;
					case 2:
						if (databaseUser.getInventory().getItemCount(ItemType.PASSIVE_BOX) != 0) {
							emotes.add(DIGIT_ONE);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_PASSIVE_BOX) != 0) {
							emotes.add(DIGIT_TWO);
						}
						if (databaseUser.getInventory().getItemCount(ItemType.DIVINE_PASSIVE_BOX) != 0) {
							emotes.add(DIGIT_THREE);
						}
						break;
				}
			} else {
				emotes.add(WRAPPED_GIFT);
				emotes.add(SHOPPING_BAGS);
				//emotes.add(LABEL);
			}
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
						isDisplayingInventoryPage = true;
						selectedInventoryPage = 0;
						clearAllReactions = true;
						break;
					case SHOPPING_BAGS:
						isDisplayingInventoryPage = true;
						selectedInventoryPage = 1;
						clearAllReactions = true;
						break;
					/*case LABEL:
						isDisplayingInventoryPage = true;
						selectedInventoryPage = 2;
						clearAllReactions = true;
						break;*/
					case BACK_ARROW:
						isDisplayingInventoryPage = false;
						clearAllReactions = true;
						break;
					case DIGIT_ONE:
						if (selectedInventoryPage == 0) {
							isDisplayingUsedItemPage = true;
							databaseUser.updateInventory(ItemType.IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.IDOL_BOX) - 1));
							message.clearReactions().queue(m -> initialize(message.editMessage(new IdolBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 1) {
							isDisplayingUsedItemPage = true;
							if (!databaseUser.getIdolCollection().getOwnedIdols().isEmpty()) {
								databaseUser.updateInventory(ItemType.SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.SKIN_BOX) - 1));
							}
							message.clearReactions().queue(m -> initialize(message.editMessage(new SkinBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 2) {
							databaseUser.updateInventory(ItemType.PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.PASSIVE_BOX) - 1));
						}
						break;
					case DIGIT_TWO:
						if (selectedInventoryPage == 0) {
							isDisplayingUsedItemPage = true;
							databaseUser.updateInventory(ItemType.SPECIAL_IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_IDOL_BOX) - 1));
							message.clearReactions().queue(m -> initialize(message.editMessage(new SpecialIdolBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 1) {
							isDisplayingUsedItemPage = true;
							if (!databaseUser.getIdolCollection().getOwnedIdols().isEmpty()) {
								databaseUser.updateInventory(ItemType.SPECIAL_SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_SKIN_BOX) - 1));
							}
							message.clearReactions().queue(m -> initialize(message.editMessage(new SpecialSkinBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 2) {
							databaseUser.updateInventory(ItemType.SPECIAL_PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.SPECIAL_PASSIVE_BOX) - 1));
						}
						break;
					case DIGIT_THREE:
						if (selectedInventoryPage == 0) {
							isDisplayingUsedItemPage = true;
							databaseUser.updateInventory(ItemType.DIVINE_IDOL_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_IDOL_BOX) - 1));
							message.clearReactions().queue(m -> initialize(message.editMessage(new DivineIdolBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 1) {
							isDisplayingUsedItemPage = true;
							if (!databaseUser.getIdolCollection().getOwnedIdols().isEmpty()) {
								databaseUser.updateInventory(ItemType.DIVINE_SKIN_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_SKIN_BOX) - 1));
							}
							message.clearReactions().queue(m -> initialize(message.editMessage(new DivineSkinBox().useItem(databaseUser))));
							return;
						} else if (selectedInventoryPage == 2) {
							databaseUser.updateInventory(ItemType.DIVINE_PASSIVE_BOX, (databaseUser.getInventory().getItemCount(ItemType.DIVINE_PASSIVE_BOX) - 1));
						}
						break;
					case BALLOT_BOX_CHECK:
						isDisplayingUsedItemPage = false;
						clearAllReactions = true;
						break;
					case HEAVY_MULTIPLICATION_X:
						message.delete().queue();
						return;
				}
				if (!clearAllReactions) {
					event.getReaction().removeReaction(event.getUser()).queue();
				}

				if (clearAllReactions) {
					if (isDisplayingInventoryPage) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getInventoryPageMessage())));
					} else {
						message.clearReactions().queue(m -> initialize(message.editMessage(getInventoryMenuMessage())));
					}

				} else {
					if (isDisplayingInventoryPage) {
						initialize(message.editMessage(getInventoryPageMessage()));
					} else {
						initialize(message.editMessage(getInventoryMenuMessage()));
					}
				}
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	private Message getInventoryMenuMessage() {
		return (MessageUtil.getInventoryMenuMessage(getAuthor(), databaseUser));
	}

	private Message getInventoryPageMessage() {
		return (MessageUtil.getInventoryPageMessage(getAuthor(), databaseUser, selectedInventoryPage));
	}

	public static class Builder extends Menu.Builder<Builder, InventoryMenu> {

		private DatabaseUser databaseUser;
		private boolean closable = false;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};

		@Override
		public InventoryMenu build() {
			if (databaseUser == null) {
				return (null);
			}
			return (new InventoryMenu(scheduler, users, roles, timeout, unit, databaseUser, closable, timeoutAction));
		}

		public InventoryMenu.Builder setDatabaseUser(DatabaseUser databaseUser) {
			this.databaseUser = databaseUser;
			return (this);
		}

		public InventoryMenu.Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}
	}

}
