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

public class HugCommand extends Command {

	private final List<String> gifs = Arrays.asList(
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/1.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/2.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/3.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/4.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/5.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/6.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/7.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/8.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/9.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/10.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/11.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/12.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/13.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/14.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/15.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/16.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/17.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/18.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/19.gif",
		"https://raw.githubusercontent.com/Azzerial/CuteGirlsCollection/master/rsc/hug/20.gif"
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
				"You need to mention to user you want to hug.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		String gif = gifs.get(MiscUtil.getRandomNumber(0, gifs.size()));

		MessageUtil.sendActionMessage(channel,
			null, null, author,
			EmoteUtil.REVOLVING_HEARTS + " " + author.getAsMention() + " hugs you " + user.getAsMention() + "!",
			null, gif, true, MiscUtil.deleteOnTimeout);
		return ("Successfully hugged the user.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"hug",
			"kyun"
		));
	}

	@Override
	public String getName() {
		return ("Hug");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Hug");
	}

	@Override
	public String getHelpDescription() {
		return ("Sends a hugging anime gif to the chat.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/hug @user\n```");
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
