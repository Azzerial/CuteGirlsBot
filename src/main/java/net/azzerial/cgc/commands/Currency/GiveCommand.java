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

public class GiveCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 4) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, null);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		List<User> mentionedUsers = event.getMessage().getMentionedUsers();
		if (mentionedUsers.isEmpty()) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "No user mentioned.", getGithubPage(), author,
				"You need to mention to user you want to pay.",
				null, null, null);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		if (!DatabaseUserManager.databaseContainsUser(user.getIdLong())) {
			DatabaseUserManager.addUserToDatabase(user.getIdLong());
		}

		switch (args[1]) {
			case "money":
				return (giveMoney(channel, author, user, args[3]));
		}

		MessageUtil.sendErrorMessage(channel,
			MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
			"The provided type argument is invalid.",
			null, null, null);
		return ("!Wrong usage");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"give"
		));
	}

	@Override
	public String getName() {
		return ("Give");
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
		return (true);
	}

	// Money argument
	public String giveMoney(MessageChannel channel, User author, User user, String arg) {
		long mentionedUserBalance = DatabaseUserManager.getDatabaseUser(user.getIdLong()).getBalance();
		long amount;
		if (arg.matches("[0-9]+")) {
			if (arg.length() > 9) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Payment value is too big.", getGithubPage(), author,
					"You can't pay that much money in one transaction.",
					null, null, null);
				return ("!Payment value is too big.");
			}
			amount = Long.parseLong(arg);
		} else {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Incorrect payment value.", getGithubPage(), author,
				"You must enter a number. `" + arg + "` isn't a valid payment value.",
				null, null, null);
			return ("!Incorrect payment value.");
		}
		// Send payment.
		mentionedUserBalance += amount;
		DatabaseUserManager.updateUserBalance(user.getIdLong(), mentionedUserBalance);

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.CREDIT_CARD, "Transaction done", author,
			user.getName() + " received ¥`" + amount + "`. Updated his balance to: ¥`" + mentionedUserBalance + "`",
			null, null, null);

		return ("Gave money to " + user.getName() + "](" + user.getId() + ".");
	}

}
