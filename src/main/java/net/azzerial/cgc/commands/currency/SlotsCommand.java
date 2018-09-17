package net.azzerial.cgc.commands.currency;

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

	private final String JAPANESE_CONGRATULATIONS = "\u3297\uFE0F";
	private final String WHITE_FLOWER = "\uD83D\uDCAE";
	private final String MONEY_MOUTH = "\uD83E\uDD11";
	private final String PARTY_POPPER = "\uD83C\uDF89";
	private final String CONFETTI_BALL = "\uD83C\uDF8A";

	private String[] winningEmotes = {
		JAPANESE_CONGRATULATIONS,
		WHITE_FLOWER,
		MONEY_MOUTH,
		PARTY_POPPER,
		CONFETTI_BALL
	};

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 2) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
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
			if (bet == 0) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Empty wallet.", getGithubPage(), author,
					"You can't bet all of your money as your wallet is empty.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!Empty wallet.");
			}
		} else {
			allIn = false;
			if (args[1].matches("[0-9]+")) {
				if (args[1].length() > 9) {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.ERROR, "Betting value is too big.", getGithubPage(), author,
						"You can't bet that much money.",
						null, null, true, MiscUtil.deleteOnTimeout);
					return ("!Betting value is too big.");
				}
				bet = Long.parseLong(args[1]);
				if (bet == currentBalance) {
					allIn = true;
				}
			} else {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Incorrect betting value.", getGithubPage(), author,
					"You can either bet a number or \"all\". `" + args[1] + "` isn't a valid betting value.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!Incorrect betting value.");
			}
		}

		// Manage the bet value.
		if (currentBalance < bet) {
			// Check if the user has the money he wasn't to bet.
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Balance doesn't contain enough money.", getGithubPage(), author,
				"Your balance doesn't contain enough money in order to execute this bet.",
				null, null, true, MiscUtil.deleteOnTimeout);
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
				money = (int) Math.ceil(bet * 3);
			}
		}
		if (score.containsKey(CLOVER)) {
			if (score.get(CLOVER).intValue() == 2) {
				money = (int) Math.ceil(bet * 1.2);
			} else if (score.get(CLOVER).intValue() == 3) {
				money = (int) Math.ceil(bet * 5);
			}
		}
		if (score.containsKey(MONEY)) {
			if (score.get(MONEY).intValue() == 2) {
				money = (int) Math.ceil(bet * 1.5);
			} else if (score.get(MONEY).intValue() == 3) {
				money = (int) Math.ceil(bet * 8);
			}
		}
		if (score.containsKey(GEM)) {
			if (score.get(GEM).intValue() == 2) {
				money = (int) Math.ceil(bet * 2);
			} else if (score.get(GEM).intValue() == 3) {
				money = (int) Math.ceil(bet * 10);
			}
		}

		long balance = 0;
		String resultDescription;
		if (noBet) {
			// No bet.
			if (money == 0) {
				resultDescription = "\n\nIt was a loss.";
			} else {
				resultDescription = "\n\nIt was a win.";
			}
		} else {
			if (money == 0) {
				// Player lost his bet.
				resultDescription = "You lost!\n\n¥`" + bet + "` were lost.";
				money = -bet;
			} else {
				// Player won some money.
				String emote = winningEmotes[MiscUtil.getRandomNumber(0, winningEmotes.length)];
				resultDescription = "You won! " + emote + "\n\n¥`" + money + "` have been added to your wallet.";
			}

			// Update in database the values.
			balance = currentBalance + money;
			databaseUser.updateBalance(balance);
		}

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.SLOT_MACHINE, "Slots result", author,
			"[ " + slots[0] + slots[1] + slots[2] + " ] " +
				resultDescription + (noBet ? "" : " Updated balance: ¥`" + balance + "`"),
			null, null, false, null);
		return ("Slots game displayed.");
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
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Slot-Machine");
	}

	@Override
	public String getHelpDescription() {
		return ("Gamble your money to get more money, or not. You have a bonus chance of winning by betting all of your money.\n" +
			"Note that you won't lose your bet if you win one of the prizes.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/slots <amount>\n```\n" +
			"Where `<amount>` represents the amount of money you bet, which can either be:\n" +
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
