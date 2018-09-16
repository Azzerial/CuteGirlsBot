package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.entities.Idol;
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
	private static int idolId;
	private final List<Idol> idols = IdolsList.getIdols();

	public CollectionMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit, User user, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.user = user;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
		this.databaseUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());
	}

	@Override
	public void display(MessageChannel channel) {
		isDisplayingIdolProgress = false;
		idolId = 0;

		initialize(channel.sendMessage(getCollectionProgressMessage()));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

		if (isDisplayingIdolProgress) {
			emotes.add(BACK_ARROW);
			emotes.add(REVERSE);
			emotes.add(PLAY);
		} else {
			emotes.add(JAPANESE_DOLLS);
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
						idolId = 0;
						clearAllReactions = true;
						break;
					case (BACK_ARROW):
						isDisplayingIdolProgress = false;
						clearAllReactions = true;
						break;
					case (REVERSE):
						changeIdolPage(false);
						break;
					case (PLAY):
						changeIdolPage(true);
						break;
					case (HEAVY_MULTIPLICATION_X):
						message.delete().queue();
						return;
				}
				if (!clearAllReactions) {
					event.getReaction().removeReaction(event.getUser()).queue();
				}

				if (clearAllReactions) {
					if (isDisplayingIdolProgress) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getIdolProgressMessage())));
					} else {
						message.clearReactions().queue(m -> initialize(message.editMessage(getCollectionProgressMessage())));
					}
				} else {
					if (isDisplayingIdolProgress) {
						initialize(message.editMessage(getIdolProgressMessage()));
					} else {
						initialize(message.editMessage(getCollectionProgressMessage()));
					}
				}
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	private void changeIdolPage(boolean goToNextPage) {
		if (goToNextPage) {
			idolId += 1;
		} else {
			idolId -= 1;
		}

		if (idolId < 0) {
			idolId = idols.size() - 1;
		} else if (idolId >= idols.size()) {
			idolId = 0;
		}
	}

	private Message getCollectionProgressMessage() {
		return (MessageUtil.getIdolCollectionOverviewMessage(user, databaseUser, getAuthor()));
	}

	private Message getIdolProgressMessage() {
		return (MessageUtil.getIdolCollectionIdolMessage(idols.get(idolId), user, databaseUser, getAuthor()));
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
