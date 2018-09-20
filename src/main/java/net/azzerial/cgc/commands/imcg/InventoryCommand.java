package net.azzerial.cgc.commands.imcg;

import net.azzerial.cgc.commands.Command;
import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.menu.entities.InventoryMenu;
import net.azzerial.cgc.menu.entities.ShopMenu;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class InventoryCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		if (args.length != 1) {
			MessageUtil.sendErrorMessage(channel,
				MessageUtil.ErrorType.ERROR, "Wrong usage.", getGithubPage(), author,
				"The provided amount of arguments is invalid.",
				null, null, true, MiscUtil.deleteOnTimeout);
			return (INVALID_AMOUNT_OF_ARGUMENTS);
		}

		InventoryMenu menu = new InventoryMenu.Builder()
			.setMessageScheduler(CGC.getMessageScheduler())
			.setUsers(author)
			.setDatabaseUser(databaseUser)
			.setClosable(true)
			.build();
		if (menu != null) {
			menu.display(channel);
		}
		return ("Opened the inventory.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"inventory",
			"inv"
		));
	}

	@Override
	public String getName() {
		return ("Inventory");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/User-Inventory");
	}

	@Override
	public String getHelpDescription() {
		return ("Opens your inventory menu.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/inventory\n```");
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
