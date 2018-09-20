package net.azzerial.cgc.utils;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.Permissions;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;

public class DebugUtil extends ListenerAdapter {

	private final String ZAP = "\u26a1";		// Delete message
	private final String OCEAN = "\ud83c\udf0a";	// Clear reactions
	private final String STAR = "\u2b50";		// Pin message
	private final String COMET = "\u2604 ";		// Unpin message

	private final boolean activated;

	public DebugUtil(boolean activated) {
		this.activated = activated;
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		// Check if all the set up has ended before enabling commands usage, and if the debug mode is activated or not.
		if (!CGC.ready || !activated) {
			return;
		}
		// Check if the reaction's author isn't a bot, is he is an Op, if the reaction was sent in a guild and is an emoji.
		if (event.getUser().isBot() || !Permissions.isOp(event.getUser())
			|| !event.isFromType(ChannelType.TEXT) || event.getReactionEmote().isEmote()) {
			return;
		}
		// Prints the unicode value of the emote.
		// EmoteUtil.printStringUnicode(event);
		// Check for known shortcut emotes.
		RestAction<Message> restAction = event.getChannel().getMessageById(event.getMessageIdLong());
		switch (event.getReactionEmote().getName()) {
			case ZAP:
				restAction.queue(m -> m.delete().queue());
				return;
			case OCEAN:
				restAction.queue(m -> m.clearReactions().queue());
				return;
			case STAR:
				restAction.queue(m -> m.pin().queue());
				return;
			case COMET:
				restAction.queue(m -> m.unpin().queue());
				return;
		}
	}

}
