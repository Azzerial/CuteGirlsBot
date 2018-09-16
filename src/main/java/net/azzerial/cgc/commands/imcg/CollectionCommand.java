package net.azzerial.cgc.commands.imcg;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.database.entities.utils.Rank;
import net.azzerial.cgc.database.entities.utils.RankingType;
import net.azzerial.cgc.menu.entities.CollectionMenu;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.entities.utils.IdolType;
import net.azzerial.imcg.entities.utils.Progress;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CollectionCommand extends Command {

	/*
	 * Pages:
	 *
	 * Main Collection page
	 * 	- cycle through the user's owned idols. (Japanese Dolls emoji)
	 * 		- back
	 * 		- next
	 * 		- prev
	 * 		- view cards (Dress emoji)
	 * 			- back
	 * 			- next
	 * 			- prev
	 * 			- set Leader (Ribbon emoji) -> only if it's the user's collection
	 * 	- quick view of the owned idols and their cards. ()
	 */

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length > 2) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		// /collection
		// view your own collection, in edit mode, you can change your leader or fusion idols.
		if (args.length == 1) {
			// the user is viewing his own collection

			CollectionMenu menu = new CollectionMenu.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.setUsers(author)
				.setCollectionUser(author)
				.setClosable(true)
				.build();
			if (menu != null) {
				menu.display(channel);
			}
			return ("Displayed the idol collection");
		}

		// /collection @user
		// view a user's collection
		if (args.length == 2) {
			// the user is viewing someone else's collection (or perhaps his own)
			List<User> mentionedUsers = event.getMessage().getMentionedUsers();
			if (mentionedUsers.isEmpty()) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "No user mentioned.", getGithubPage(), author,
					"You need to mention to user you want to see the collection from.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("No user mentioned.");
			}

			User user = mentionedUsers.get(0);
			CollectionMenu menu = new CollectionMenu.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.setUsers(author)
				.setCollectionUser(user)
				.setClosable(true)
				.build();
			if (menu != null) {
				menu.display(channel);
			}
			return ("Displayed the idol collection");
		}
		return (UNKNOWN_CASE);
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"collection"
		));
	}

	@Override
	public String getName() {
		return ("Collection");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/User-Idol-Collection");
	}

	@Override
	public String getHelpDescription() {
		return ("Display a browsable menu containing a user's idol collection progress, ranking and other stats.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/collection [@user]\n```\n" +
			"Where `[@user]` represents an optional parameter, which is an user mention.");
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
