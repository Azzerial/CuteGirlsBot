package net.azzerial.cgc.commands.Currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.*;

public class SlotsCommand extends Command {
								// 30		100.00		 1/1
	private final String CHERRY = "\uD83C\uDF52";		// 8		 26.66		4/15
	private final String LEMON = "\uD83C\uDF4B";		// 6		 20.00		 1/5
	private final String GRAPES = "\uD83C\uDF47";		// 4		 13.33		2/15
	private final String WATERMELON = "\uD83C\uDF49";	// 4		 13.33		2/15
	private final String CLOVER = "\uD83C\uDF40";		// 3		 10.00		1/10
	private final String MONEY = "\uD83D\uDCB0";		// 3		 10.00		1/10
	private final String GEM = "\uD83D\uDC8E";		// 2		  6.66		1/15

	private String[] objects = {
		CHERRY,	CHERRY, CHERRY,	CHERRY, CHERRY,
		LEMON, LEMON, LEMON, LEMON,
		GRAPES, GRAPES,
		WATERMELON, WATERMELON,
		CLOVER, CLOVER,
		MONEY, MONEY,
		GEM, GEM,
		MONEY,
		CLOVER,
		WATERMELON, WATERMELON,
		GRAPES, GRAPES,
		LEMON, LEMON, LEMON,
		CHERRY,	CHERRY, CHERRY, CHERRY
	};

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 2) {
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		long currentBalance = databaseUser.getBalance(); // User's current balance.
		boolean allIn; // Did the user bet all of his balance.
		boolean noBet = false;
		long bet; // Amount the user has bet.
		long money = 0; // Amount the user has won.
		if (args[1].equalsIgnoreCase("all")) {
			allIn = true;
			bet = currentBalance;
		} else {
			allIn = false;
			if (args[1].matches("[0-9]+")) {
				if (args[1].length() > 6) {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.ERROR, "Betting value is too big.", getGithubPage(), author,
						"You can't bet that much money.",
						null, null, null);
					return ("!Betting value is too big.");
				}
				bet = Long.parseLong(args[1]);
			} else {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Incorrect betting value.", getGithubPage(), author,
					"You can either bet a number or \"all\". `" + args[1] + "` isn't a valid betting value.",
					null, null, null);
				return ("!Incorrect betting value.");
			}
		}

		// Manage the bet value.
		if (currentBalance < bet) {
			// Check if the user has the money he wasn't to bet.
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Balance doesn't contain enough money.", getGithubPage(), author,
				"Your balance doesn't contain enough money in order to execute this bet.",
				null, null, null);
			return ("!Balance doesn't contain enough money.");
		} else if (bet == 0) {
			noBet = true;
			bet = 100;
		}

		// Get the fully random slots values.
		String[] slots = {
			objects[MiscUtil.getRandomNumber(0, objects.length)],
			objects[MiscUtil.getRandomNumber(0, objects.length)],
			objects[MiscUtil.getRandomNumber(0, objects.length)]
		};
		// In case of an all in, randomize more with the goal of increasing the winning rate.
		if (allIn) {
			List<Integer> s = Arrays.asList(0, 1, 2);
			Collections.shuffle(s);
			if (!slots[s.get(0)].equals(slots[s.get(1)]) && !slots[s.get(0)].equals(slots[s.get(2)])) {
				slots[0] = objects[MiscUtil.getRandomNumber(0, objects.length)];
			}
		}

		Map<String, Integer> score = new HashMap<String, Integer>();
		for (String str : slots) {
			if (score.containsKey(str)) {
				int value = score.get(str).intValue() + 1;
				score.remove(str);
				score.put(str, value);
				continue;
			}
			score.put(str, 1);
		}
		if (score.containsKey(CHERRY)) {
			if (score.get(CHERRY).intValue() == 2) {
				money = (int) Math.ceil(bet * 0.5);
			} else if (score.get(CHERRY).intValue() == 3) {
				money = (int) Math.ceil(bet * 1);
			}
		}
		if (score.containsKey(LEMON)) {
			if (score.get(LEMON).intValue() == 2) {
				money = (int) Math.ceil(bet * 0.75);
			} else if (score.get(LEMON).intValue() == 3) {
				money = (int) Math.ceil(bet * 1.5);
			}
		}
		if (score.containsKey(GRAPES)) {
			if (score.get(GRAPES).intValue() == 2) {
				money = (int) Math.ceil(bet * 1);
			} else if (score.get(GRAPES).intValue() == 3) {
				money = (int) Math.ceil(bet * 2);
			}
		}
		if (score.containsKey(WATERMELON)) {
			if (score.get(WATERMELON).intValue() == 2) {
				money = (int) Math.ceil(bet * 1);
			} else if (score.get(WATERMELON).intValue() == 3) {
				money = (int) Math.ceil(bet * 4);
			}
		}
		if (score.containsKey(CLOVER)) {
			if (score.get(CLOVER).intValue() == 2) {
				money = (int) Math.ceil(bet * 1.2);
			} else if (score.get(CLOVER).intValue() == 3) {
				money = (int) Math.ceil(bet * 6);
			}
		}
		if (score.containsKey(MONEY)) {
			if (score.get(MONEY).intValue() == 2) {
				money = (int) Math.ceil(bet * 1.5);
			} else if (score.get(MONEY).intValue() == 3) {
				money = (int) Math.ceil(bet * 10);
			}
		}
		if (score.containsKey(GEM)) {
			if (score.get(GEM).intValue() == 2) {
				money = (int) Math.ceil(bet * 2);
			} else if (score.get(GEM).intValue() == 3) {
				money = (int) Math.ceil(bet * 12);
			}
		}

		String resultDescription;
		if (noBet) {
			// No bet.
			if (money == 0) {
				resultDescription = "\n\nIt was a loss.";
			} else {
				resultDescription = "\n\nIt was a win.";
			}
		} else {
			// Update in database the values.
			long balance = currentBalance + money;
			DatabaseUserManager.updateUserBalance(author.getIdLong(), balance);

			if (money == 0) {
				// Player lost his bet.
				money = -bet;
				resultDescription = "You lost!\n\n¥`" + money + "` were lost. Updated balance: ¥`" + balance + "`";
			} else {
				// Player won some money.
				resultDescription = "You won!\n\n¥`" + money + "` have been added to your wallet. Updated balance: ¥`" + balance + "`";
			}
		}

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.SLOT_MACHINE, "Slots result", author,
			"[ " + slots[0] + slots[1] + slots[2] + " ] " +
				resultDescription,
			null, null, null);
		return ("Done.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"slots"
		));
	}

	@Override
	public String getName() {
		return ("Slots");
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
