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

public class KissCommand extends Command {

	private final List<String> gifs = Arrays.asList(
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/1.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/2.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/3.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/4.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/5.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/6.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/7.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/8.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/9.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/10.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/11.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/12.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/13.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/14.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/15.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/16.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/17.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/18.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/19.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/kiss/20.gif"
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
				"You need to mention to user you want to kiss.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		String gif = gifs.get(MiscUtil.getRandomNumber(0, gifs.size()));

		MessageUtil.sendActionMessage(channel,
			null, null, author,
			EmoteUtil.KISS_MARK + " " + author.getAsMention() + " kisses you " + user.getAsMention() + "!",
			null, gif, false, null);
		return ("Successfully kissed the user.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"kiss",
			"klaws"
		));
	}

	@Override
	public String getName() {
		return ("Kiss");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Anime-Gifs");
	}

	@Override
	public String getHelpDescription() {
		return ("Sends a kissing anime gif to the chat.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/kiss @user\n```");
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
