package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.entities.Idol;
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

public class IdolMenu extends Menu {

	private static final String DRESS = "\uD83D\uDC57";
	private static final String BACK_ARROW = "\uD83D\uDD19";
	private static final String PLAY = "\u25B6";
	private static final String REVERSE = "\u25C0";
	private static final String HEAVY_MULTIPLICATION_X = "\u2716";

	private final Idol idol;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;

	private static boolean isDisplayingIdolSkin = false;
	private static int idolSkinId = 0;
	private static boolean showEvolvedSkin = false;

	public IdolMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit, Idol idol, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.idol = idol;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
	}

	@Override
	public void display(MessageChannel channel) {
		isDisplayingIdolSkin = false;
		idolSkinId = 0;
		showEvolvedSkin = false;

		initialize(channel.sendMessage(getIdolMessage()));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

		if (isDisplayingIdolSkin) {
			emotes.add(BACK_ARROW);
			emotes.add(REVERSE);
			emotes.add(PLAY);
		} else {
			emotes.add(DRESS);
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
					case (DRESS):
						isDisplayingIdolSkin = true;
						idolSkinId = 0;
						showEvolvedSkin = false;
						clearAllReactions = true;
						break;
					case (BACK_ARROW):
						isDisplayingIdolSkin = false;
						clearAllReactions = true;
						break;
					case (REVERSE):
						changeIdolSkinPage(false);
						break;
					case (PLAY):
						changeIdolSkinPage(true);
						break;
					case (HEAVY_MULTIPLICATION_X):
						message.delete().queue();
						return;
				}
				if (!clearAllReactions) {
					event.getReaction().removeReaction(event.getUser()).queue();
				}

				if (clearAllReactions) {
					if (isDisplayingIdolSkin) {
						message.clearReactions().queue(m -> initialize(message.editMessage(getIdolSkinMessage())));
					} else {
						message.clearReactions().queue(m -> initialize(message.editMessage(getIdolMessage())));
					}
				} else {
					if (isDisplayingIdolSkin) {
						initialize(message.editMessage(getIdolSkinMessage()));
					} else {
						initialize(message.editMessage(getIdolMessage()));
					}
				}

			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	private void changeIdolSkinPage(boolean goToNextPage) {
		if (showEvolvedSkin) {
			showEvolvedSkin = false;
			if (goToNextPage) {
				idolSkinId += 1;
			}
		} else {
			showEvolvedSkin = true;
			if (!goToNextPage) {
				idolSkinId -= 1;
			}
		}


		if (idolSkinId < 0) {
			idolSkinId = idol.getSkins().size() - 1;
		} else if (idolSkinId >= idol.getSkins().size()) {
			idolSkinId = 0;
		}
	}

	private Message getIdolMessage() {
		return (MessageUtil.getIdolInfoMessage(idol, getAuthor()));
	}

	private Message getIdolSkinMessage() {
		return (MessageUtil.getIdolSkinInfoMessage(idol, idolSkinId, showEvolvedSkin, getAuthor()));
	}

	public static class Builder extends Menu.Builder<Builder,IdolMenu> {

		private Idol idol;
		private boolean closable;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};

		@Override
		public IdolMenu build() {
			if (idol == null) {
				return (null);
			}
			return (new IdolMenu(scheduler, users, roles, timeout, unit, idol, closable, timeoutAction));
		}

		public IdolMenu.Builder setIdol(Idol idol) {
			this.idol = idol;
			return (this);
		}

		public IdolMenu.Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}
	}
}
