package net.azzerial.cgc.commands.fun;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class SlapCommand extends Command {

	private final List<String> gifs = Arrays.asList(
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/1.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/2.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/3.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/4.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/5.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/6.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/7.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/8.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/9.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/10.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/11.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/12.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/13.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/14.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/15.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/16.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/17.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/18.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/19.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/slap/20.gif"
	);

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 2) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		List<User> mentionedUsers = event.getMessage().getMentionedUsers();
		if (mentionedUsers.isEmpty()) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "No user mentioned.", getGithubPage(), author,
				"You need to mention to user you want to slap.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		String gif = gifs.get(MiscUtil.getRandomNumber(0, gifs.size()));

		MessageUtil.sendActionMessage(channel,
			null, null, author,
			EmoteUtil.RAISED_HAND + " " + author.getAsMention() + " slaps you " + user.getAsMention() + "!",
			null, gif, false, null);
		return ("Successfully slapped the user.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"slap",
			"lilone"
		));
	}

	@Override
	public String getName() {
		return ("Slap");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Anime-Gifs");
	}

	@Override
	public String getHelpDescription() {
		return ("Sends a slapping anime gif to the chat.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/slap @user\n```");
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
