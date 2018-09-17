package net.azzerial.cgc.commands.currency;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DailyCommand extends Command {

	private final int dailyAmount = 200; // Daily pay base amount.
	private final int dailyStreakAmount = 50; // Daily pay bonus base amount.
	private final int dailyStreakMax = 46; // Max bonus streak.
	private final int hours = 20; // Command cooldown.

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 1) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		GregorianCalendar lastDailyTime = databaseUser.getLastDailyTime(); // User's last daily pay date.
		GregorianCalendar now = new GregorianCalendar();

		if (lastDailyTime != null) {
			GregorianCalendar nextTime = (GregorianCalendar) lastDailyTime.clone();
			nextTime.add(Calendar.HOUR, hours);

			// Check the last claim date.
			if (nextTime.compareTo(now) > 0) {
				// Duration between last daily time date and now.
				long diff = Duration.between(now.toZonedDateTime(), lastDailyTime.toZonedDateTime()).getSeconds();
				diff += TimeUnit.HOURS.toSeconds(hours);

				long hours = TimeUnit.HOURS.convert(diff, TimeUnit.SECONDS);
				diff -= TimeUnit.HOURS.toSeconds(hours);
				long minutes = TimeUnit.MINUTES.convert(diff, TimeUnit.SECONDS);
				diff -= TimeUnit.MINUTES.toSeconds(minutes);
				long seconds = TimeUnit.SECONDS.convert(diff, TimeUnit.SECONDS);

				String time = new StringBuilder()
					.append(hours).append(" hour" + (hours > 1 ? "s" : "") + ", ")
					.append(minutes).append(" minute" + (minutes > 1 ? "s" : "") + " and ")
					.append(seconds).append(" second" + (seconds > 1 ? "s" : "")).toString();

				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR,"Daily reward already claimed.", getGithubPage(), author,
					"You already claimed your daily reward today! Please wait another `" + time + "` before using it again.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!Daily pay already claimed.");
			}
		}

		int currentStreak = databaseUser.getDailyStreak(); // User's current bonus streak.
		long currentBalance = databaseUser.getBalance(); // User's current balance.
		// View if the streak has been broken or not.
		if (lastDailyTime != null) {
			GregorianCalendar streakResetTime = (GregorianCalendar) lastDailyTime.clone();
			streakResetTime.add(Calendar.HOUR, 48);
			if (streakResetTime.compareTo(now) < 0) {
				currentStreak = 0;
			}
		}

		// Calculate new values.
		int dailyPay = dailyAmount + (dailyStreakAmount * Math.min(dailyStreakMax, currentStreak));
		long balance = currentBalance + dailyPay;

		// Update in database the values.
		databaseUser.updateBalance(balance);
		if (currentStreak < dailyStreakMax) {
			currentStreak += 1;
			databaseUser.updateDailyStreak(currentStreak);
		}
		databaseUser.updateLastDailyTime(now);

		MessageUtil.sendActionMessage(channel,
			EmoteUtil.PURSE, "Daily reward", author,
			"¥`" + dailyPay + "` have been added to your wallet. Updated balance: ¥`" + balance + "`",
			null, null, false, null);
		return ("Earned daily reward.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"daily"
		));
	}

	@Override
	public String getName() {
		return ("Daily");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/daily-reward");
	}

	@Override
	public String getHelpDescription() {
		return ("Gives you a daily reward of ¥`100` with a bonus of ¥`50` per consecutive day.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/daily\n```");
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
