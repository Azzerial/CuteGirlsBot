package net.azzerial.cgc.utils;

import java.awt.Color;
import java.util.function.Consumer;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.Stats;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;

public class MessageUtil {

// --- Message Senders ---

	public static void sendActionMessage(MessageChannel channel,
					     String emote, String actionResult,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(emote + " " + actionResult);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			channel.sendMessage(msg).queue(restAction);
		} else {
			channel.sendMessage(msg).queue();
		}
	}

	public static void sendErrorMessage(MessageChannel channel,
					    ErrorType errorType, String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl,
					    Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		String emote = (errorType.getValue() == 0 ? EmoteUtil.WARNING : EmoteUtil.PROHIBITED);
		builder.setTitle(emote + " " + errorName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(errorDetails + "\n\nIf needed, you can get some help [here](" + githubPage + ").");
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 67, 101));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			channel.sendMessage(msg).queue(restAction);
		} else {
			channel.sendMessage(msg).queue();
		}
	}

	public static void sendHelpMessage(MessageChannel channel,
					   String helpName, String githubPage,
					   User author,
					   String helpDescription, String helpUsage,
					   String thumbnailUrl, String imageUrl,
					   Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(EmoteUtil.OPEN_BOOK + " " + helpName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.addField("`" + EmoteUtil.LIGHT_BULB + "` Action:", helpDescription + "\n\u2063", false);
		builder.addField("`" + EmoteUtil.MEMO + "` Usage:", helpUsage + "\n\nMore information and usage examples are available [here](" + githubPage + ").", false);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			channel.sendMessage(msg).queue(restAction);
		} else {
			channel.sendMessage(msg).queue();
		}
	}

	public static void sendSearchMessage(MessageChannel channel,
					     String commandName, String githubPage,
					     User author,
					     String searchedItem, String itemLink,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(commandName, githubPage);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setTitle(searchedItem, itemLink);
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(150, 186, 255));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			channel.sendMessage(msg).queue(restAction);
		} else {
			channel.sendMessage(msg).queue();
		}
	}

// --- Message Editors ---

	public static void editActionMessage(Message message,
					     String emote, String actionResult,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(emote + " " + actionResult);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			message.editMessage(msg).queue(restAction);
		} else {
			message.editMessage(msg).queue();
		}
	}

	public static void editErrorMessage(Message message,
					    ErrorType errorType, String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl,
					    Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		String emote = (errorType.getValue() == 0 ? EmoteUtil.WARNING : EmoteUtil.PROHIBITED);
		builder.setTitle(emote + " " + errorName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(errorDetails + "\n\nIf needed, you can get some help [here](" + githubPage + ").");
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 67, 101));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			message.editMessage(msg).queue(restAction);
		} else {
			message.editMessage(msg).queue();
		}
	}

	public static void editHelpMessage(Message message,
					   String helpName, String githubPage,
					   User author,
					   String helpDescription, String helpUsage,
					   String thumbnailUrl, String imageUrl,
					   Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(EmoteUtil.OPEN_BOOK + " " + helpName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.addField("`" + EmoteUtil.LIGHT_BULB + "` Action:", helpDescription + "\n\u2063", false);
		builder.addField("`" + EmoteUtil.MEMO + "` Usage:", helpUsage + "\n\nMore information and usage examples are available [here](" + githubPage + ").", false);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			message.editMessage(msg).queue(restAction);
		} else {
			message.editMessage(msg).queue();
		}
	}

	public static void editSearchMessage(Message message,
					     String commandName, String githubPage,
					     User author,
					     String searchedItem, String itemLink,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(commandName, githubPage);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setTitle(searchedItem, itemLink);
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(150, 186, 255));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (restAction != null) {
			message.editMessage(msg).queue(restAction);
		} else {
			message.editMessage(msg).queue();
		}
	}

// --- Message Getters ---

	public static Message getActionMessage(String emote, String actionResult,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(emote + " " + actionResult);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getErrorMessage(ErrorType errorType, String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		String emote = (errorType.getValue() == 0 ? EmoteUtil.WARNING : EmoteUtil.PROHIBITED);
		builder.setTitle(emote + " " + errorName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(errorDetails + "\n\nIf needed, you can get some help [here](" + githubPage + ").");
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 67, 101));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getHelpMessage(String helpName, String githubPage,
					   User author,
					   String helpDescription, String helpUsage,
					   String thumbnailUrl, String imageUrl) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(EmoteUtil.OPEN_BOOK + " " + helpName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.addField("`" + EmoteUtil.LIGHT_BULB + "` Action:", helpDescription + "\n\u2063", false);
		builder.addField("`" + EmoteUtil.MEMO + "` Usage:", helpUsage + "\n\nMore information and usage examples are available [here](" + githubPage + ").", false);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		return (mBuilder.build());
	}

	public static Message getSearchMessage(String commandName, String githubPage,
					     User author,
					     String searchedItem, String itemLink,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(commandName, githubPage);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setTitle(searchedItem, itemLink);
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(150, 186, 255));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

// --- IMCG ---

	public static Message getIdolInfoMessage(Idol idol, User author) {
		EmbedBuilder builder = new EmbedBuilder();

		builder.setAuthor(idol.getName(), null, idol.getIdolType().getIcon());
		builder.setDescription("`Id` " + idol.getId() + "\n" +
			"`Type` " + idol.getIdolType().asString() + "\n" +
			"`Age` " + idol.getAge() + "\n" +
			"`Birthday` " + idol.getBirthday().asString() + "\n" +
			"`Height` " + idol.getHeight() + "cm\n" +
			"`Weight` " + idol.getWeight() + "kg\n" +
			"`BWH` " + idol.getMeasurements().asString() + "\n" +
			"`Blood Plan` " + idol.getBloodType().asString() + "\n" +
			"`Handedness` " + idol.getHandedness().asString() + "\n\n" +
			"Press " + EmoteUtil.DRESS + " to see " + idol.getLatinName() + "'s cards.");
		builder.setThumbnail(idol.getProfilePicture());
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(idol.getIdolType().getColor());
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getIdolSkinInfoMessage(Idol idol, int skinId, boolean evolved, User author) {
		EmbedBuilder builder = new EmbedBuilder();
		IdolSkin skin = idol.getSkin(skinId);

		builder.setAuthor(skin.getThemeName(), null, idol.getIdolType().getIcon());
		builder.setTitle("Rarity: " + skin.getRarity().asString() + (evolved ? "+" : ""));
		builder.setDescription((evolved ? "Evolved" : "Basic") + " card `" + (skinId + 1) + "/" + idol.getSkins().size() + "`");
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

// --- Utilities ---

	public enum ErrorType {
		ERROR(0),
		PROHIBITED(1);

		private int errorType;

		ErrorType(int errorType) {
			this.errorType = errorType;
		}

		public int getValue() {
			return (errorType);
		}
	}

}
