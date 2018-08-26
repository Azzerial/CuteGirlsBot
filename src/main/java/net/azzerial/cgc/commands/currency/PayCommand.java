package net.azzerial.cgc.commands.currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class PayCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length > 3) {
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
		long mentionedUserBalance = DatabaseUserManager.getDatabaseUser(user.getIdLong()).getBalance();

		long amount;
		long currentBalance = databaseUser.getBalance();
		if (args[2].equalsIgnoreCase("all")) {
			amount = currentBalance;
		} else {
			if (args[2].matches("[0-9]+")) {
				if (args[2].length() > 9) {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.ERROR, "Payment value is too big.", getGithubPage(), author,
						"You can't pay that much money in one transaction.",
						null, null, null);
					return ("!Payment value is too big.");
				}
				amount = Long.parseLong(args[2]);
			} else {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Incorrect payment value.", getGithubPage(), author,
					"You must enter a number. `" + args[2] + "` isn't a valid payment value.",
					null, null, null);
				return ("!Incorrect payment value.");
			}
		}

		// Take money.
		if (currentBalance < amount) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Balance doesn't contain enough money.", getGithubPage(), author,
				"Your balance doesn't contain enough money in order to execute this payment.",
				null, null, null);
			return ("!Balance doesn't contain enough money.");
		}
		long balance = currentBalance - amount;
		DatabaseUserManager.updateUserBalance(author.getIdLong(), balance);

		// Send payment.
		mentionedUserBalance += amount;
		DatabaseUserManager.updateUserBalance(user.getIdLong(), mentionedUserBalance);

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.MONEY_WITH_WINGS, "Payment done", author,
			"You payed " + user.getName() + " ¥`" + amount + "`. Updated balance: ¥`" + balance + "`",
			null, null, null);
		return ("Payed [" + user.getName() + "](" + user.getId() + ").");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"pay"
		));
	}

	@Override
	public String getName() {
		return ("Pay");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Pay-User");
	}

	@Override
	public String getHelpDescription() {
		return ("Gives a user a set amount of your money.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/pay @user <amount>\n```\n" +
			"Where `<amount>` represents the amount of money you will give, which can either be:\n" +
			"\t• A number, smaller than 1,000,000,000.\n" +
			"\t• *all*, if you want to give all of your money.");
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
