package net.azzerial.cgc.utils;

import java.awt.Color;
import java.util.function.Consumer;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;

public class MessageUtil {

// --- Message Senders ---

	public static void sendActionMessage(MessageChannel channel,
					     String icon, String actionResult, String githubPage,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(actionResult, githubPage, icon);
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
					    String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl,
					    Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(errorName, githubPage, EmoteUtil.WARNING_IMAGE);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(errorDetails);
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
		builder.setAuthor(helpName, githubPage, EmoteUtil.OPEN_BOOK_IMAGE);
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
		builder.setAuthor(commandName, githubPage, EmoteUtil.MAGNIFYING_GLASS_IMAGE);
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
					     String icon, String actionResult, String githubPage,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(actionResult, githubPage, icon);
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
					    String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl,
					    Consumer<? super Message> restAction) {
		if (author == null) {
			throw new NullPointerException("The message's author is null");
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(errorName, githubPage, EmoteUtil.WARNING_IMAGE);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(errorDetails);
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
		builder.setAuthor(helpName, githubPage, EmoteUtil.OPEN_BOOK_IMAGE);
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
		builder.setAuthor(commandName, githubPage, EmoteUtil.MAGNIFYING_GLASS_IMAGE);
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

}
