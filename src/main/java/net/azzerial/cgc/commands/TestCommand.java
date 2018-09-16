package net.azzerial.cgc.commands;

import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.database.entities.utils.Rank;
import net.azzerial.cgc.database.entities.utils.RankingType;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.entities.unused.Leader;
import net.azzerial.imcg.entities.utils.IdolType;
import net.azzerial.imcg.entities.utils.Progress;
import net.azzerial.imcg.idols.core.IdolsList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCommand extends Command {

	private static final String PLAY = "\u25B6";
	private static final String REVERSE = "\u25C0";

	@Override
	public String onCommand(MessageReceivedEvent event, String[] args, MessageChannel channel, User author, User self) {
		Idol idol = IdolsList.getIdol("Shiki Ichinose");

		channel.sendMessage(userIdolCollectionOverviewMessage(author, author)).queue(m -> {
			List<String> emotes = new ArrayList<String>();

			emotes.add(EmoteUtil.JAPANESE_DOLLS);

			for (int i = 0; i < emotes.size(); i += 1) {
				m.addReaction(emotes.get(i)).queue();
			}
		});
		channel.sendMessage(userIdolCollectionIdolsListMessage(idol, author, author)).queue(m -> {
			List<String> emotes = new ArrayList<String>();

			emotes.add(EmoteUtil.BACK_ARROW);
			emotes.add(REVERSE);
			emotes.add(PLAY);

			for (int i = 0; i < emotes.size(); i += 1) {
				m.addReaction(emotes.get(i)).queue();
			}
		});
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

	private Message userIdolCollectionOverviewMessage(User user, User author) {
		DatabaseUser dbUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());
		IdolCollection idolCollection = dbUser.getIdolCollection();
		Progress idolsProgress = idolCollection.getCollectionsIdolsProgress();
		Progress cuteProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.CUTE);
		Progress coolProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.COOL);
		Progress passionProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.PASSION);
		Progress cardsProgress = idolCollection.getCollectionsCardsProgress();
		Progress basicCardProgress = idolCollection.getCollectionsBasicCardProgress();
		Progress evolvedCardProgress = idolCollection.getCollectionsEvolvedCardProgress();
		Rank idolCollectionRank = DatabaseUserManager.getUserRank(dbUser, RankingType.IDOL_COLLECTION);
	//	Leader leader = new Leader(0, 4, true);

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(user.getName() + "'s idols collection", null, user.getAvatarUrl());
		builder.setDescription("Ranked #`" + idolCollectionRank.getRank() + "` out of `" + idolCollectionRank.getSize() + "` users.");
		builder.addField("Idols progress", "" +
			"All idols: `" + idolsProgress.getProgress() + "/" + idolsProgress.getSize() + "` (" + idolsProgress.getPercentageAsString() + ")\n" +
			"Cute: `" + cuteProgress.getProgress() + "/" + cuteProgress.getSize() + "` (" + cuteProgress.getPercentageAsString() + ")\n" +
			"Cool: `" + coolProgress.getProgress() + "/" + coolProgress.getSize() + "` (" + coolProgress.getPercentageAsString() + ")\n" +
			"Passion: `" + passionProgress.getProgress() + "/" + passionProgress.getSize() + "` (" + passionProgress.getPercentageAsString() + ")"
		, true);
		builder.addField("Cards progress", "" +
			"All cards: `" + cardsProgress.getProgress() + "/" + cardsProgress.getSize() + "` (" + cardsProgress.getPercentageAsString() + ")\n" +
			"Basic: `" + basicCardProgress.getProgress() + "/" + basicCardProgress.getSize() + "` (" + basicCardProgress.getPercentageAsString() + ")\n" +
			"Evolved: `" + evolvedCardProgress.getProgress() + "/" + evolvedCardProgress.getSize() + "` (" + evolvedCardProgress.getPercentageAsString() + ")"
		, true);
	/*	builder.addField("Leader card", "" +
			"Name: `" + leader.getIdol().getName() + "`\n" +
			"Card: `" + leader.getIdolSkin().getThemeName() + "`\n" +
			"Rarity: `" +  leader.getRarity() + "`"
		, false);
		builder.setImage(leader.getCard().getCard());
	*/	builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 175, 225));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	private Message userIdolCollectionIdolsListMessage(Idol idol, User user, User author) {
		DatabaseUser dbUser = DatabaseUserManager.getDatabaseUser(user.getIdLong());
		IdolCollection.Collection collection = dbUser.getIdolCollection().getCollection(idol);
		Progress cardsProgress = collection.getCollectionProgress();
		Progress basicCardProgress = collection.getCollectionBasicCardProgress();
		Progress evolvedCardProgress = collection.getCollectionEvolvedCardProgress();

		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(idol.getName(), null, idol.getIdolType().getIcon());
		builder.setDescription("`Owned` " + (collection.isIdolOwned() ? "True" : "False"));
		builder.addField("Idol's cards progress", "" +
			"All cards: `" + cardsProgress.getProgress() + "/" + cardsProgress.getSize() + "` (" + cardsProgress.getPercentageAsString() + ")\n" +
			"Basic: `" + basicCardProgress.getProgress() + "/" + basicCardProgress.getSize() + "` (" + basicCardProgress.getPercentageAsString() + ")\n" +
			"Evolved: `" + evolvedCardProgress.getProgress() + "/" + evolvedCardProgress.getSize() + "` (" + evolvedCardProgress.getPercentageAsString() + ")"
		, true);
		builder.setThumbnail(idol.getProfilePicture());
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 175, 225));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

}
