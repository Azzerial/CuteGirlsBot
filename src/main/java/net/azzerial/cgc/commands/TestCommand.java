package net.azzerial.cgc.commands;

import net.azzerial.imcg.core.IdolsList;
import net.azzerial.imcg.entities.Idol;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class TestCommand extends Command {

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		characterInfo(event);

		return ("Tested.");
	}

	@Override
	public List<String> getAliases() {
		return (Arrays.asList(
			"test"
		));
	}

	@Override
	public String getName() {
		return ("Test");
	}

	@Override
	public String getGithubPage() {
		return ("https://github.com/Azzerial/CuteGirlsCollection/wiki");
	}

	@Override
	public String getHelpDescription() {
		return ("Testing command. It may unleash unicorns.");
	}

	@Override
	public String getHelpUsage() {
		return ("```md\n/test\n```");
	}

	@Override
	public boolean isAdminRequired() {
		return (false);
	}

	@Override
	public boolean isOpRequired() {
		return (true);
	}

	private void characterInfo(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();

		Idol idol = IdolsList.getIdol(0);

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(idol.getLatinName() + "  ❲" + idol.getJapaneseName() + "❳", getGithubPage(), idol.getIdolType().getTypeIcon());
		builder.setDescription("`Type` " + idol.getIdolType().asString() + "\n" +
			"`Age` " + idol.getAge() + "\n" +
			"`Birthday` " + idol.getBirthday().asString() + "\n" +
			"`Height` " + idol.getHeight() + "cm\n" +
			"`Weight` " + idol.getWeight() + "kg\n" +
			"`BWH` " + idol.getMeasurements().asString() + "\n" +
			"`Blood Plan` " + idol.getBloodType().asString() + "\n" +
			"`Handedness` " + idol.getHandedness().asString());
		builder.setThumbnail(idol.getProfilePicture());
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		channel.sendMessage(msg).queue();
	}

}
