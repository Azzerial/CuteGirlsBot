package net.azzerial.cgc.commands.imcg;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.menu.entities.IdolMenu;
import net.azzerial.cgc.menu.entities.ListingMenu;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.core.IdolsList;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.utils.IdolType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdolCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length == 1) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, null);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		// /idol list
		if ((args.length == 2 || args.length == 3) && args[1].equalsIgnoreCase("list")) {
			List<String> names = new ArrayList<String>();

			// /idol list
			if (args.length == 2) {
				// list all idols
				IdolsList.getIdols().forEach(idol -> {
					names.add(idol.getId() + ". " + idol.getName());
				});
				sendIdolListMessage(channel, author, names, "Idols List");
				return ("Listed all the idols.");
			}

			// /idol list [type]
			switch (args[2].toLowerCase()) {
				case "cute":
					// list cute idols
					IdolsList.getIdols().forEach(idol -> {
						if (idol.getIdolType().equals(IdolType.CUTE)) {
							names.add(idol.getId() + ". " + idol.getName());
						}
					});
					sendIdolListMessage(channel, author, names, "Cute Idols List");
					return ("Listed the cute idols.");
				case "cool":
					// list  cool idols
					IdolsList.getIdols().forEach(idol -> {
						if (idol.getIdolType().equals(IdolType.COOL)) {
							names.add(idol.getId() + ". " + idol.getName());
						}
					});
					sendIdolListMessage(channel, author, names, "Cool Idols List");
					return ("Listed the cool idols.");
				case "passion":
					// list passion idols
					IdolsList.getIdols().forEach(idol -> {
						if (idol.getIdolType().equals(IdolType.PASSION)) {
							names.add(idol.getId() + ". " + idol.getName());
						}
					});
					sendIdolListMessage(channel, author, names, "Passion Idols List");
					return ("Listed the passion idols.");
			}

			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Unhandled type argument.", getGithubPage(), author,
				"The provided type argument(s) for the */idol list [type]* are unhandled.",
				null, null, null);
			return ("!Unknown argument.");
		}

		// /idol info
		if (args.length >= 3 && args[1].equalsIgnoreCase("info")) {

			// /idol info <id>
			if (args.length == 3 && args[2].matches("[0-9]+")) {
				int id = Integer.parseInt(args[2]);
				Idol idol = IdolsList.getIdol(id);

				if (idol == null) {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.ERROR, "Invalid idol id.", getGithubPage(), author,
						"We didn't find any idol with that id.",
						null, null, null);
					return ("!Invalid idol id.");
				}

				// send info of the idol with that id
				sendIdolInfoMessage(channel, idol, author);
				return ("Displayed the idol's info.");
			}

			// /idol info <name>
			StringBuilder builder = new StringBuilder();
			for (int i = 2; i < args.length; i++) {
				builder.append(args[i]);
				if (i + 1 < args.length) {
					builder.append(" ");
				}
			}
			Idol idol = IdolsList.getIdol(builder.toString());

			if (idol == null) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Invalid idol name.", getGithubPage(), author,
					"We didn't find any idol with that name.",
					null, null, null);
				return ("!Invalid idol name.");
			}

			// send info of the idol with that name
			sendIdolInfoMessage(channel, idol, author);
			return ("Displayed the idol's info.");
		}
		return (UNKNOWN_CASE);
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"idol",
			"idols"
		));
	}

	@Override
	public String getName() {
		return ("Idol");
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

	private void sendIdolListMessage(MessageChannel channel, User author, List<String> names, String title) {
		ListingMenu menu = new ListingMenu.Builder()
			.setMessageScheduler(CGC.getMessageScheduler())
			.setUsers(author)
			.setTitle(title)
			.setTitleEmote(EmoteUtil.RIBBON)
			.setDescription("Use the message's reaction emotes to browse the list.\n" +
				"If you want to know more about an idol, type: `/idol info <id>`.")
			.setItems(names)
			.setItemsPerPage(5)
			.setCodeblockLanguage("md")
			.enablePageSkip(true)
			.setPageSkipAmount(5)
			.orderItems(true)
			.showItemNumber(false)
			.showPageNumber(true)
			.setClosable(true)
			.build();
		if (menu != null) {
			menu.display(channel);
		}
	}

	private void sendIdolInfoMessage(MessageChannel channel, Idol idol, User author) {
		IdolMenu menu = new IdolMenu.Builder()
			.setMessageScheduler(CGC.getMessageScheduler())
			.setUsers(author)
			.setIdol(idol)
			.setClosable(true)
			.build();
		if (menu != null) {
			menu.display(channel);
		}
	}

}
