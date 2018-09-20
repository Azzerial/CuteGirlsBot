package net.azzerial.cgc.commands.imcg;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.database.entities.utils.Rank;
import net.azzerial.cgc.database.entities.utils.RankingType;
import net.azzerial.cgc.menu.entities.ListingMenu;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeaderboardCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 1) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		List<String> users = new ArrayList<String>();
		List<DatabaseUser> orderedUsers = new ArrayList<DatabaseUser>(DatabaseUserManager.getUsersCache());
		Collections.sort(orderedUsers, new MiscUtil.sortDatabaseUserByIdolCollection());
		int lastScoreValue = 0;
		int rank = 0;
		for (int i = 0; i < orderedUsers.size(); i += 1) {
			DatabaseUser u = orderedUsers.get(i);
			int score = u.getIdolCollection().getScore();

			if (lastScoreValue != score) {
				rank = i;
			}
			User user = CGC.getAPI().getUserById(u.getId());
			String name = (event.getGuild().isMember(user) ?
				event.getGuild().getMember(user).getEffectiveName() :
				user.getName());
			users.add((rank + 1) + ":\t\t" + name + "\n\t\t# score: " + score);
			lastScoreValue = score;
		}
		Rank idolCollectionRank = DatabaseUserManager.getUserRank(databaseUser, RankingType.IDOL_COLLECTION);

		ListingMenu menu = new ListingMenu.Builder()
			.setMessageScheduler(CGC.getMessageScheduler())
			.setUsers(author)
			.setTitleEmote(EmoteUtil.RIBBON)
			.setTitle("Leaderboard")
			.setDescription("Your are ranked #`" + idolCollectionRank.getRank() + "` out of `" + idolCollectionRank.getSize() + "` users (cross-server ranking).")
			.setEmptyItemsListDescription("The leaderboard is empty.")
			.setCodeblockLanguage("makefile")
			.enablePageSkip(true)
			.setPageSkipAmount(3)
			.showPageNumber(true)
			.setItemsPerPage(6)
			.showItemNumber(false)
			.orderItems(false)
			.setItems(users)
			.setClosable(true)
			.setTimeoutAction(MiscUtil.deleteOnTimeout)
			.build();
		if (menu != null) {
			menu.display(channel);
		}
		return ("Displayed the leaderboard.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"leaderboard",
			"top"
		));
	}

	@Override
	public String getName() {
		return ("Leaderboard");
	}

	@Override
	public String getGithubPage() {
		return (null);
	}

	@Override
	public String getHelpDescription() {
		return ("Displays the leaderboard.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/leaderboard\n```");
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
