package net.azzerial.cgc.commands;

import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.entities.Rank;
import net.azzerial.cgc.entities.RankingType;
import net.azzerial.imcg.core.IdolsList;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.entities.Leader;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.Progress;
import net.azzerial.imcg.entities.utils.Stats;
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
		Idol idol = IdolsList.getIdol("Shiki Ichinose");

		int number = databaseUser.getIdolCollection().getCollection(idol).getSkinData(0).getBasicSkinCount();
		databaseUser.getIdolCollection().getCollection(idol).getSkinData(0).updateBasicSkinCount(number + 1);
		DatabaseUserManager.updateUserIdolCollection(
			databaseUser.getId(),
			idol.getDatabaseName(),
			databaseUser.getIdolCollection().getCollection(idol).convertToString()
		);

		//channel.sendMessage(idolInfoMessage(idol, author)).queue();
		//channel.sendMessage(idolSkinMessage(idol, idol.getSkin(4), false, author)).queue();
		//channel.sendMessage(userIdolCollectionInfoMessage(author, author)).queue();
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

	private Message idolInfoMessage(Idol idol, User author) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(idol.getName(), null, idol.getIdolType().getIcon());
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
		builder.setColor(idol.getIdolType().getColor());
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	private Message idolSkinMessage(Idol idol, IdolSkin skin, boolean evolved, User author) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(skin.getThemeName(), null, idol.getIdolType().getIcon());
		builder.setTitle("Rarity: " + skin.getRarity().asString() + (evolved ? "+" : ""));
		if (evolved) {
			builder.addField("Attack", "" + skin.getStats().getAttack(Stats.EVOLVED_BONUS), true);
			builder.addField("Defense", "" + skin.getStats().getDefense(Stats.EVOLVED_BONUS), true);
		} else {
			builder.addField("Attack", "" + skin.getStats().getBaseAttack(), true);
			builder.addField("Defense", "" + skin.getStats().getBaseDefense(), true);
		}
		builder.setImage(evolved ? skin.getEvolvedCard().getCard() : skin.getBasicCard().getCard());
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(idol.getIdolType().getColor());
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	private Message userIdolCollectionInfoMessage(User user, User author) {
		DatabaseUser dbUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());
		IdolCollection idolCollection = dbUser.getIdolCollection();
		Progress globalProgress = idolCollection.getCollectionsProgress();
		Progress basicCardProgress = idolCollection.getCollectionsBasicCardProgress();
		Progress evolvedCardProgress = idolCollection.getCollectionsEvolvedCardProgress();
		Rank idolCollectionRank = DatabaseUserManager.getUserRank(dbUser, RankingType.IDOL_COLLECTION);

		//
		// REMOVE THE HARD CODED LEADER.
		//
		Leader leader = new Leader(0, 4, true);
		//
		// REMOVE THE HARD CODED LEADER.
		//

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(user.getName() + "'s idols collection", null, user.getAvatarUrl());
		builder.setThumbnail(leader.getCard().getCard());
		builder.setDescription("Rank #`" + idolCollectionRank.getRank() + "` out of `" + idolCollectionRank.getSize() + "` users.");
		builder.addField("Global Progress", "" +
			"All cards: `" + globalProgress.getProgress() + "/" + globalProgress.getSize() + "` (" + globalProgress.getPercentageAsString() + ")\n" +
			"Basic: `" + basicCardProgress.getProgress() + "/" + basicCardProgress.getSize() + "` (" + basicCardProgress.getPercentageAsString() + ")\n" +
			"Evolved: `" + evolvedCardProgress.getProgress() + "/" + evolvedCardProgress.getSize() + "` (" + evolvedCardProgress.getPercentageAsString() + ")", true);
		builder.addField("Leader", "" +
			"Name: `" + leader.getIdol().getName() + "`\n" +
			"Card: `" + leader.getIdolSkin().getThemeName() + "`\n" +
			"Rarity: `" +  leader.getRarity() + "`", true);
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 175, 225));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

}
