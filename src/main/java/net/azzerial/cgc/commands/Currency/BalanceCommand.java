package net.azzerial.cgc.commands.Currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class BalanceCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length > 2) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, null);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		if (args.length == 1) {
			MessageUtil.sendActionMessage(channel,
				EmoteUtil.PURSE, "Current balance", author,
				"You have ¥`" + databaseUser.getBalance() + "` in your wallet.",
				null, null, null);
			return ("Displayed user's balance.");
		}

		List<User> mentionedUsers = event.getMessage().getMentionedUsers();
		if (mentionedUsers.isEmpty()) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "No user mentioned.", getGithubPage(), author,
				"You need to mention to user you want to see the balance from.",
				null, null, null);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		long balance = DatabaseUserManager.getDatabaseUser(user.getIdLong()).getBalance();

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.PURSE, "Current balance", author,
			user.getName() + " has ¥`" + balance + "` in his wallet.",
			null, null, null);
		return ("Displayed an other user's balance.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"balance",
			"bal",
			"wallet"
		));
	}

	@Override
	public String getName() {
		return ("Balance");
	}

	@Override
	public String getGithubPage() {
		return (null);
	}

	@Override
	public String getHelpDescription() {
		return ("");
	}

	@Override
	public String getHelpUsage() {
		return ("");
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
