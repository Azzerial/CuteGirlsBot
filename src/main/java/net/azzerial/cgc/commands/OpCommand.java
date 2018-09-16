package net.azzerial.cgc.commands;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.core.CGCInfo;
import net.azzerial.cgc.database.Permissions;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OpCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 2 && args.length != 3) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}
		JDA api = event.getJDA();

		if (args.length == 2 && args[1].equalsIgnoreCase("list")) {
			ArrayList<String> ops = Permissions.getOps();
			Collections.sort(ops);
			String ops_string = "";

			for (String op : ops) {
				ops_string += "`" + event.getGuild().getMember(api.getUserById(op)).getEffectiveName() + "`, ";
			}
			ops_string = ops_string.substring(0, ops_string.length() - 2);
			ops_string += ".";
			MessageUtil.sendSearchMessage(channel,
				"Ops List", getGithubPage(), author,
				null, null,"The following users are ops:\n" + ops_string,
				null, null, true, MiscUtil.clearReactionsOnTimeout);
			return ("<List> Displayed the current Ops list.");
		}

		if (args.length == 3 && args[1].equalsIgnoreCase("add")) {
			User user = null;

			if (!event.getMessage().getMentionedUsers().isEmpty()) {
				user = event.getMessage().getMentionedUsers().get(0);
			}
			if (user == null) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Invalid parameter", getGithubPage(), author,
					"`" + args[2] + "` isn't a valid parameter.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!<Add> User fed an invalid parameter. (" + args[2] + ")");
			}
			String username = event.getGuild().getMember(user).getEffectiveName();
			if (!Permissions.addOp(user.getId())) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "User already Op", getGithubPage(), author,
					"`" + username + "` is already op.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!<Add> " + user.getName() + " is already op.");
			}
			MessageUtil.sendActionMessage(channel,
				EmoteUtil.RIBBON, "User added to Ops", author,
				"`" + username + "` has been added to the Ops list.",
				null, null, false, null);
			return ("<Add> " + user.getName() + " has been added to the Ops list.");
		}

		if (args.length == 3 && args[1].equalsIgnoreCase("remove")) {
			User user = null;

			if (!event.getMessage().getMentionedUsers().isEmpty()) {
				user = event.getMessage().getMentionedUsers().get(0);
			}
			if (user == null) {
				MessageUtil.sendErrorMessage(channel,
					MessageUtil.ErrorType.ERROR, "Invalid parameter", getGithubPage(), author,
					"`" + args[2] + "` isn't a valid parameter.",
					null, null, true, MiscUtil.deleteOnTimeout);
				return ("!<Remove> User fed an invalid parameter. (" + args[2] + ")");
			}
			String username = event.getGuild().getMember(user).getEffectiveName();
			if (!Permissions.removeOp(user.getId())) {
				if (user.getId().equals(CGCInfo.BOT_AUTHOR_ID)) {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.PROHIBITED, "Bot author", getGithubPage(), author,
						"`" + username + "` is the bot's author.\nYou can't remove him from the op list.",
						null, null, true, MiscUtil.deleteOnTimeout);
					return ("!<Remove> User tried to remove the bot's author from the Ops list.");
				} else {
					MessageUtil.sendErrorMessage(channel,
						MessageUtil.ErrorType.ERROR, "User isn't Op", getGithubPage(), author,
						"`" + username + "` isn't op.",
						null, null, true, MiscUtil.deleteOnTimeout);
					return ("!<Remove> " + user.getName() + " isn't op.");
				}
			}
			MessageUtil.sendActionMessage(channel,
				EmoteUtil.RIBBON, "User removed from Ops", author,
				"`" + username + "` has been removed from the Ops list.",
				null, null, false, null);
			return ("<Remove> " + user.getName() + " has been removed from the Ops list.");
		}

		MessageUtil.sendErrorMessage(channel,
			MessageUtil.ErrorType.ERROR, "Wrong parameter.", getGithubPage(), author,
			"The provided argument is invalid.",
			null, null, true, MiscUtil.deleteOnTimeout);
		return ("!Unknown.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"op",
			"ops"
		));
	}

	@Override
	public String getName() {
		return ("Op");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Op-Command");
	}

	@Override
	public String getHelpDescription() {
		return ("Manages Cute Girls Collection's operators list.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/op list\n/op <action> @user\n```\n" +
			"Where `<action>` can either be:\n" +
			"\t• *add*, if you want to add the mentioned user to the op list.\n" +
			"\t• *remove*, if you want to remove the mentioned user from the op list.\n");
	}

	@Override
	public boolean isAdminRequired() {
		return (false);
	}

	@Override
	public boolean isOpRequired() {
		return (true);
	}
}
