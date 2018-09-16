package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
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

public class ClosableMessage extends Menu {

	private static final String HEAVY_MULTIPLICATION_X = "\u2716";

	private final Message message;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;

	ClosableMessage(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit,
		    Message message, boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.message = message;
		this.closable = closable;
		this.timeoutAction = timeoutAction;
	}

	@Override
	public void display(MessageChannel channel) {
		initialize(channel.sendMessage(message));
	}

	private void initialize(RestAction<Message> restAction) {
		List<String> emotes = new ArrayList<String>();

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
				switch (event.getReaction().getReactionEmote().getName()) {
					case HEAVY_MULTIPLICATION_X:
						message.delete().queue();
						return;
				}
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}

	public static class Builder extends Menu.Builder<ClosableMessage.Builder, ClosableMessage> {

		private Message message;
		private boolean closable = true;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};

		@Override
		public ClosableMessage build() {
			return (new ClosableMessage(scheduler, users, roles, timeout, unit, message, closable, timeoutAction));
		}

		public ClosableMessage.Builder setMessage(Message message) {
			this.message = message;
			return (this);
		}

		public ClosableMessage.Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}

		public ClosableMessage.Builder setTimeoutAction(Consumer<Message> timeoutAction) {
			this.timeoutAction = timeoutAction;
			return (this);
		}

	}

}
