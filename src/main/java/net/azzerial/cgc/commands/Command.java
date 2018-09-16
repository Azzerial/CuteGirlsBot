package net.azzerial.cgc.commands;

import java.util.List;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.core.CGCInfo;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.Permissions;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public abstract class Command extends ListenerAdapter {

	protected String INVALID_AMOUNT_OF_ARGUMENTS = "!Invalid amount of arguments.";
	protected String DATABASE_ERROR = "!Something unexpected happened when modifying the database.";
	protected String UNKNOWN_CASE = "!Unknown return case.";

	public abstract String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self);
	public abstract List<String> getAliases();
	public abstract String getName();
	public abstract String getGithubPage();
	public abstract String getHelpDescription();
	public abstract String getHelpUsage();
	public abstract boolean isAdminRequired();
	public abstract boolean isOpRequired();

	protected DatabaseUser databaseUser;

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// Check if all the set up has ended before enabling commands usage.
		if (!CGC.ready) {
			return;
		}
		// Check if the message's author isn't a bot and if the message was sent in a guild.
		if (event.getAuthor().isBot() || !event.isFromType(ChannelType.TEXT)) {
			return;
		}
		// Check if the command requires Op permissions to be ran. If it's the case, check if the author has the right permissions.
		if (containsCommand(event.getMessage()) && isOpRequired()) {
			if (!Permissions.isOp(event.getAuthor())) {
				System.out.println("[" + getName() + "]: [" + event.getAuthor().getName() + "](" + event.getAuthor().getId() + ") tried to run the command but wasn't Op.");
				sendMissingPermissionMessage(event.getChannel(), event.getAuthor(), true);
				return;
			}
		}
		// Check if the command exists and run it.
		if (containsCommand(event.getMessage())) {
			databaseUser = DatabaseUserManager.getDatabaseUser(event.getAuthor().getIdLong());

			System.out.println("[" + getName() + "]: [" + event.getAuthor().getName() + "](" + event.getAuthor().getId() + ") executed the command.");
			String output = onCommand(event, commandArgs(event.getMessage()), event.getTextChannel(), event.getAuthor(), event.getJDA().getSelfUser());
			System.out.println("[" + getName() + "]" + (output.startsWith("!") ? "(FAILURE): " + output.substring(1) : "(SUCCESS): " + output));
		}
	}
	
	private boolean containsCommand(Message message) {
		String string = commandArgs(message)[0];

		if (string.startsWith(CGCInfo.PREFIX)) {
			string = string.substring(CGCInfo.PREFIX.length());
		} else {
			return (false);
		}
		return (getAliases().contains(string.toLowerCase()));
	}

	private String[] commandArgs(Message message) {
		return (message.getContentRaw().split(" "));
	}
	
	private void sendMissingPermissionMessage(MessageChannel channel, User author, boolean opCase) {
		MessageUtil.sendErrorMessage(channel,
			MessageUtil.ErrorType.PROHIBITED, "Missing permission.", getGithubPage(), author,
			(opCase ? Permissions.OP_REQUIRED_MESSAGE : Permissions.ADMIN_REQUIRED_MESSAGE),
			null, null, true, MiscUtil.deleteOnTimeout);
	}

}