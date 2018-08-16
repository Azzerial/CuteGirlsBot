package net.azzerial.cgc.commands;

import net.azzerial.cgc.database.Permissions;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.util.ArrayList;
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
		Object[] keys = commands.keySet().toArray();

		// No arguments.
		if (args.length == 1) {
			// Send help menu. (The one listing the commands)
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < keys.length; i += 1) {
				Command cmd = commands.get(keys[i]);
				list.add("`" + cmd.getName() + "`");
			}

			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(EmoteUtil.OPEN_BOOK + " Cute Girls Collection commands.", getGithubPage(), author.getAvatarUrl());
			builder.setDescription(MiscUtil.listToString(list, ", "));
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), null);
			builder.setColor(new Color(255, 226, 148));
			MessageEmbed embed = builder.build();
			MessageBuilder mBuilder = new MessageBuilder(embed);
			Message msg = mBuilder.build();
			channel.sendMessage(msg).queue();
			return ("Sent the help page.");
		}

		String command = args[1].toLowerCase();

		// With arguments.
		for (int i = 0; i < keys.length; i += 1) {
			Command cmd = commands.get(keys[i]);

			if (cmd.getAliases().contains(command) || cmd.getName().equalsIgnoreCase(command)) {
				// Send help for that page.
				MessageUtil.sendHelpMessage(channel, cmd.getName() + " command.", cmd.getGithubPage(), author,
					cmd.getHelpDescription(),
					cmd.getHelpUsage(),
					null, null, null);
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
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/help");
	}

	@Override
	public String getHelpDescription() {
		return ("Displays Cute Girls Collection's commands, or if specified the help page for a command.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/help [search]\n```\n" +
			"Where `[search]` represents an optional parameter, which can either be:\n" +
			"\t• A command's name.\n" +
			"\t• A command's keyword.\n");
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
