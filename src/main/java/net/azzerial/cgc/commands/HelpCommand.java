package net.azzerial.cgc.commands;

import net.azzerial.cgc.database.Permissions;
import net.azzerial.cgc.utils.MessageUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HelpCommand extends Command {

	public static TreeMap<String, Command> commands;
	
	public HelpCommand() {
		commands = new TreeMap<String, Command>();
	}
	
	public Command registerCommand(Command command) {
		commands.put(command.getAliases().get(0), command);
		return (command);
	}
	
	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length > 2) {
			MessageUtil.sendErrorMessage(channel,
				"Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.\n" +
					"You can get some help for this command [here](" + getGithubPage() + ").",
				null, null, null);
			return (INVALID_AMOUNT_OF_AGRUMENTS);
		}

		// No arguments.
		if (args.length == 1) {
			// Send help menu. (The one listing the commands)
			return ("Sent the help page.");
		}

		String command = args[1];
		Object[] keys = commands.keySet().toArray();

		// With arguments.
		for (int i = 0; i < keys.length; i += 1) {
			Command cmd = commands.get(keys[i]);
			
			if (cmd.isOpRequired() && !Permissions.isOp(author)) {
				continue;
			}
			if (cmd.getAliases().contains(command) || cmd.getName().equalsIgnoreCase(command)) {
				// Send help for that page.
				return ("Sent the help page for the " + cmd.getName() + " command.");
			}
		}
		MessageUtil.sendErrorMessage(channel,
			"Command not found.", getGithubPage(), author,
			"There are no command named `" + command + "`.",
			null, null, null);
		return ("!Command not found");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"help"
		));
	}

	@Override
	public String getName() {
		return ("Help");
	}

	@Override
	public String getGithubPage() {
		return (null);
	}

	@Override
	public boolean isAdminRequired() {
		return (false);
	}

	@Override
	public boolean isOpRequired() {
		return (false);
	}

}
