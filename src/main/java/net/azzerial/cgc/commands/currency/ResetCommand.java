package net.azzerial.cgc.commands.currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class ResetCommand extends Command {

	private final long resetValue = 100;

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 1) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		databaseUser.updateBalance(resetValue);
		MessageUtil.sendActionMessage(channel,
			EmoteUtil.PURSE, "Reset done", author,
			"Your balance has been reset. Updated balance: ¥`" + resetValue + "`",
			null, null, false, null);
		return ("Reset balance.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"reset"
		));
	}

	@Override
	public String getName() {
		return ("Reset");
	}

	@Override
	public String getGithubPage() {
		return (null);
	}

	@Override
	public String getHelpDescription() {
		return ("Resets your balance to ¥`50`.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/reset\n```");
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
