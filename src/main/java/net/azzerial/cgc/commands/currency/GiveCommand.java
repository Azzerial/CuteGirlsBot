package net.azzerial.cgc.commands.currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
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
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		List<User> mentionedUsers = event.getMessage().getMentionedUsers();
		if (mentionedUsers.isEmpty()) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "No user mentioned.", getGithubPage(), author,
				"You need to mention to user you want to pay.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return ("No user mentioned.");
		}

		User user = mentionedUsers.get(0);
		switch (args[1]) {
			case "money":
				return (giveMoney(channel, author, user, args[3]));
		}

		MessageUtil.sendErrorMessage(channel,
			MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
			"The provided type argument is invalid.",
			null, null, true, MiscUtil.deleteOnTimeout);
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
		DatabaseUser mentionedDatabaseUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());
		long mentionedUserBalance = mentionedDatabaseUser.getBalance();
		long amount;
		if (arg.matches("[0-9]+")) {
			if (arg.length() > 9) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Payment value is too big.", getGithubPage(), author,
					"You can't pay that much money in one transaction.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!Payment value is too big.");
			}
			amount = Long.parseLong(arg);
		} else {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Incorrect payment value.", getGithubPage(), author,
				"You must enter a number. `" + arg + "` isn't a valid payment value.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return ("!Incorrect payment value.");
		}
		// Send payment.
		mentionedUserBalance += amount;
		mentionedDatabaseUser.updateBalance(mentionedUserBalance);

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.CREDIT_CARD, "Transaction done", author,
			user.getName() + " received ¥`" + amount + "`. Updated his balance to: ¥`" + mentionedUserBalance + "`",
			null, null, false, null);

		return ("Gave money to " + user.getName() + "](" + user.getId() + ".");
	}

}
