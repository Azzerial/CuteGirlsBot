package net.azzerial.cgc.commands;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class ShutdownCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		MessageUtil.sendActionMessage(channel, EmoteUtil.RIBBON_IMAGE, "Shutting down Cute Girls Collection", getGithubPage(), author,
			"じゃね〜",
			null,null,null);
		System.out.println("[Command/ShutdownCommand]: じゃね〜");
		CGC.getAPI().shutdown();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.exit(CGC.NORMAL_SHUTDOWN);
		return ("Shutdown");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"shutdown",
			"gn"
		));
	}

	@Override
	public String getName() {
		return ("Shutdown");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki/Shutdown");
	}

	@Override
	public String getHelpDescription() {
		return ("Shutdowns the bot.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/shutdown\n```");
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
