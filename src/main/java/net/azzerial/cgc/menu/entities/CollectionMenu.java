package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.SkinData;
import net.azzerial.imcg.idols.core.IdolsList;
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

public class CollectionMenu extends Menu {

	private static final String DRESS = "\uD83D\uDC57";
	private static final String JAPANESE_DOLLS = "\ud83c\udf8e";
	private static final String BACK_ARROW = "\uD83D\uDD19";
	private static final String PLAY = "\u25B6";
	private static final String REVERSE = "\u25C0";
	private static final String HEAVY_MULTIPLICATION_X = "\u2716";

	private final User user;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;
	private final DatabaseUser databaseUser;

	private static boolean isDisplayingIdolProgress;
	private static boolean isDisplayingIdolSkins;
	private static List<SkinData> skins = new ArrayList<SkinData>();
	private static int skinIndex;
	private static boolean evolvedSkin;
	private final List<Idol> idols;
	private static int idolIndex;

	public CollectionMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit, User user, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.user = user;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
		this.databaseUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());

		this.idols = databaseUser.getIdolCollection().getOwnedIdols();
	}

	@Override
	public void display(MessageChannel channel) {
		isDisplayingIdolProgress = false;
		idolIndex = 0;
		isDisplayingIdolSkins = false;
		skinIndex = 0;
		evolvedSkin = false;

		initialize(channel.sendMessage(getCollectionProgressMessage()));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

		if (isDisplayingIdolSkins) {
			emotes.add(BACK_ARROW);
			if (skins.size() != 1) {
				emotes.add(REVERSE);
				emotes.add(PLAY);
			}
		} else {
			if (isDisplayingIdolProgress) {
				emotes.add(BACK_ARROW);
				if (idols.size() != 1) {
					emotes.add(REVERSE);
				}
				skins = databaseUser.getIdolCollection().getCollection(idols.get(idolIndex)).getOwnedSkinData();
				emotes.add(DRESS);
				if (idols.size() != 1) {
					emotes.add(PLAY);
				}
			} else {
				if (!idols.isEmpty()) {
					emotes.add(JAPANESE_DOLLS);
				}
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
					case (JAPANESE_DOLLS):
						isDisplayingIdolProgress = true;
						idolIndex = 0;
						clearAllReactions = true;
						break;
					case DRESS:
						if (skins != null && !skins.isEmpty()) {
							isDisplayingIdolSkins = true;
							isDisplayingIdolProgress = false;
							skinIndex = 0;
							evolvedSkin = false;
							clearAllReactions = true;
						}
						break;
					case (BACK_ARROW):
						if (isDisplayingIdolProgress) {
							isDisplayingIdolProgress = false;
						} else if (isDisplayingIdolSkins) {
							isDisplayingIdolSkins = false;
							isDisplayingIdolProgress = true;
						}
						clearAllReactions = true;
						break;
					case (REVERSE):
						if (isDisplayingIdolProgress) {
							changeIdolPage(false);
						} else if (isDisplayingIdolSkins) {
							changeSkinPage(false);
						}
						break;
					case (PLAY):
						if (isDisplayingIdolProgress) {
							changeIdolPage(true);
						} else if (isDisplayingIdolSkins) {
							changeSkinPage(true);
						}
						break;
					case (HEAVY_MULTIPLICATION_X):
						message.delete().queue();
						return;
				}
				if (!clearAllReactions) {
					event.getReaction().removeReaction(event.getUser()).queue();
				}

				if (clearAllReactions) {
					if (isDisplayingIdolSkins) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getIdolSkinMessage())));
					} else if (isDisplayingIdolProgress) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getIdolProgressMessage())));
					} else {
						message.clearReactions().queue(m -> initialize(message.editMessage(getCollectionProgressMessage())));
					}
				} else {
					if (isDisplayingIdolSkins) {
						initialize(message.editMessage(getIdolSkinMessage()));
					} else if (isDisplayingIdolProgress) {
						initialize(message.editMessage(getIdolProgressMessage()));
					} else {
						initialize(message.editMessage(getCollectionProgressMessage()));
					}
				}
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	private void changeIdolPage(boolean goToNextPage) {
		if (idols.isEmpty()) {
			return;
		}
		if (goToNextPage) {
			idolIndex += 1;
		} else {
			idolIndex -= 1;
		}

		if (idolIndex < 0) {
			idolIndex = idols.size() - 1;
		} else if (idolIndex >= idols.size()) {
			idolIndex = 0;
		}
	}

	private void changeSkinPage(boolean goToNextPage) {
		if (skins.isEmpty()) {
			return;
		}
		if (goToNextPage) {
			if (!evolvedSkin) {
				if (skins.get(skinIndex).hasEvolvedSkin()) {
					evolvedSkin = true;
				} else {
					skinIndex += 1;
				}
			} else {
				evolvedSkin = false;
				skinIndex += 1;
			}
		} else {
			if (evolvedSkin) {
				if (skins.get(skinIndex).hasBasicSkin()) {
					evolvedSkin = false;
				} else {
					skinIndex -= 1;
				}
			} else {
				evolvedSkin = true;
				skinIndex -= 1;
			}
		}

		if (skinIndex < 0) {
			skinIndex = skins.size() - 1;
		} else if (skinIndex >= skins.size()) {
			skinIndex = 0;
		}

		if (!evolvedSkin && !skins.get(skinIndex).hasBasicSkin()) {
			evolvedSkin = true;
		} else if (evolvedSkin && !skins.get(skinIndex).hasEvolvedSkin()) {
			evolvedSkin = false;
		}
	}

	private Message getCollectionProgressMessage() {
		return (MessageUtil.getIdolCollectionOverviewMessage(user, databaseUser, getAuthor()));
	}

	private Message getIdolProgressMessage() {
		return (MessageUtil.getIdolCollectionIdolMessage(idols.get(idolIndex), user, databaseUser, getAuthor()));
	}

	private Message getIdolSkinMessage() {
		Idol idol = idols.get(idolIndex);
		return (MessageUtil.getIdolCollectionSkinMessage(idol, skins.get(skinIndex), evolvedSkin, getAuthor()));
	}

	public static class Builder extends Menu.Builder<Builder, CollectionMenu> {

		private User user;
		private boolean closable = false;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};

		@Override
		public CollectionMenu build() {
			if (user == null) {
				return (null);
			}
			return (new CollectionMenu(scheduler, users, roles, timeout, unit, user, closable, timeoutAction));
		}

		public CollectionMenu.Builder setCollectionUser(User user) {
			this.user = user;
			return (this);
		}

		public CollectionMenu.Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}
	}
}
