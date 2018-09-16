package net.azzerial.cgc.utils;

import java.awt.Color;
import java.util.function.Consumer;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.database.entities.utils.Rank;
import net.azzerial.cgc.database.entities.utils.RankingType;
import net.azzerial.cgc.menu.entities.ClosableMessage;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.IdolType;
import net.azzerial.imcg.entities.utils.Progress;
import net.azzerial.imcg.entities.utils.Stats;
import net.azzerial.imcg.items.core.ItemType;
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
					     boolean closable, Consumer<Message> restAction) {
		EmbedBuilder builder = new EmbedBuilder();
		if (emote != null && actionResult != null) {
			builder.setTitle(emote + " " + actionResult);
		}
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (!closable) {
			if (restAction != null) {
				channel.sendMessage(msg).queue(restAction);
			} else {
				channel.sendMessage(msg).queue();
			}
			return;
		}
		if (restAction != null) {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.setTimeoutAction(restAction)
				.build();
			menu.display(channel);
		} else {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.build();
			menu.display(channel);
		}
	}

	public static void sendErrorMessage(MessageChannel channel,
					    ErrorType errorType, String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl,
					    boolean closable, Consumer<Message> restAction) {
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(255, 67, 101));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (!closable) {
			if (restAction != null) {
				channel.sendMessage(msg).queue(restAction);
			} else {
				channel.sendMessage(msg).queue();
			}
			return;
		}
		if (restAction != null) {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.setTimeoutAction(restAction)
				.build();
			menu.display(channel);
		} else {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.build();
			menu.display(channel);
		}
	}

	public static void sendHelpMessage(MessageChannel channel,
					   String helpName, String githubPage,
					   User author,
					   String helpDescription, String helpUsage,
					   String thumbnailUrl, String imageUrl,
					   boolean closable, Consumer<Message> restAction) {
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (!closable) {
			if (restAction != null) {
				channel.sendMessage(msg).queue(restAction);
			} else {
				channel.sendMessage(msg).queue();
			}
			return;
		}
		if (restAction != null) {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.setTimeoutAction(restAction)
				.build();
			menu.display(channel);
		} else {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.build();
			menu.display(channel);
		}
	}

	public static void sendSearchMessage(MessageChannel channel,
					     String commandName, String githubPage,
					     User author,
					     String searchedItem, String itemLink,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     boolean closable, Consumer<Message> restAction) {
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(150, 186, 255));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		Message msg = mBuilder.build();
		if (!closable) {
			if (restAction != null) {
				channel.sendMessage(msg).queue(restAction);
			} else {
				channel.sendMessage(msg).queue();
			}
			return;
		}
		if (restAction != null) {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.setTimeoutAction(restAction)
				.build();
			menu.display(channel);
		} else {
			ClosableMessage menu = new ClosableMessage.Builder()
				.setMessageScheduler(CGC.getMessageScheduler())
				.addUsers(author)
				.setClosable(closable)
				.setMessage(msg)
				.build();
			menu.display(channel);
		}
	}

// --- Message Editors ---

	public static void editActionMessage(Message message,
					     String emote, String actionResult,
					     User author,
					     String actionDetails,
					     String thumbnailUrl, String imageUrl,
					     Consumer<? super Message> restAction) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(emote + " " + actionResult);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(emote + " " + actionResult);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.setDescription(actionDetails);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(255, 186, 212));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getErrorMessage(ErrorType errorType, String errorName, String githubPage,
					    User author,
					    String errorDetails,
					    String thumbnailUrl, String imageUrl) {
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
		builder.setColor(new Color(255, 67, 101));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getHelpMessage(String helpName, String githubPage,
					   User author,
					   String helpDescription, String helpUsage,
					   String thumbnailUrl, String imageUrl) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(EmoteUtil.OPEN_BOOK + " " + helpName);
		if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
			builder.setThumbnail(thumbnailUrl);
		}
		builder.addField("`" + EmoteUtil.LIGHT_BULB + "` Action:", helpDescription + "\n" + MiscUtil.INVISIBLE_SEPARATOR, false);
		builder.addField("`" + EmoteUtil.MEMO + "` Usage:", helpUsage + "\n\nMore information and usage examples are available [here](" + githubPage + ").", false);
		if (imageUrl != null && !imageUrl.isEmpty()) {
			builder.setImage(imageUrl);
		}
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
		if (author != null) {
			builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		}
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
			"`Tier` " + idol.getIdolTier().asString() + "\n" +
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
	/*	if (evolved) {
			builder.addField("Attack", "" + skin.getStats().getAttack(Stats.EVOLVED_BONUS), true);
			builder.addField("Defense", "" + skin.getStats().getDefense(Stats.EVOLVED_BONUS), true);
		} else {
			builder.addField("Attack", "" + skin.getStats().getBaseAttack(), true);
			builder.addField("Defense", "" + skin.getStats().getBaseDefense(), true);
		}
	*/	builder.setImage(evolved ? skin.getEvolvedCard().getCard() : skin.getBasicCard().getCard());
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(idol.getIdolType().getColor());
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getShopMenuMessage(User author, DatabaseUser user) {
		EmbedBuilder builder = new EmbedBuilder();

		builder.setTitle(EmoteUtil.SHOPPING_CART + " Shop Menu");
		builder.setDescription("Welcome to the shop, the place where you can buy idols boxes " + EmoteUtil.WRAPPED_GIFT + " and cards boxes " + EmoteUtil.SHOPPING_BAGS /*+ " and passives boxes " + EmoteUtil.LABEL*/ + ".\n\n" +
			"You currently have ¥`" + user.getBalance() + "` in your wallet.\n" +
			"Press the bellow reactions to browse the shop menu.");
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getShopPageMessage(User author, DatabaseUser user, int selectedShopPage) {
		EmbedBuilder builder = new EmbedBuilder();

		switch (selectedShopPage) {
			case 0:
				builder.setTitle(EmoteUtil.WRAPPED_GIFT + " Shop: Idol Boxes");
				builder.setDescription("Crossed items can't be afforded.\n" +
					EmoteUtil.DIGIT_ONE + " - ¥`400` - " + (user.getBalance() < 400 ? "~~" : "") + "Regular Idol box" + (user.getBalance() < 400 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.IDOL_BOX) + ")\n" +
					EmoteUtil.DIGIT_TWO + " - ¥`900` - " + (user.getBalance() < 900 ? "~~" : "") + "Special Idol box" + (user.getBalance() < 900 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.SPECIAL_IDOL_BOX) + ")\n" +
					EmoteUtil.DIGIT_THREE + " - ¥`2000` - " + (user.getBalance() < 2000 ? "~~" : "") + "Divine Idol box" + (user.getBalance() < 2000 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.DIVINE_IDOL_BOX) + ")\n\n" +
					"You currently have ¥`" + user.getBalance() + "` in your wallet.\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the shop menu.");
				break;
			case 1:
				builder.setTitle(EmoteUtil.SHOPPING_BAGS + " Shop: Card Boxes");
				builder.setDescription("Crossed items can't be afforded.\n" +
					EmoteUtil.DIGIT_ONE + " - ¥`200` - " + (user.getBalance() < 200 ? "~~" : "") + "Regular Card box" + (user.getBalance() < 200 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.SKIN_BOX) + ")\n" +
					EmoteUtil.DIGIT_TWO + " - ¥`450` - " + (user.getBalance() < 450 ? "~~" : "") + "Special Card box" + (user.getBalance() < 450 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.SPECIAL_SKIN_BOX) + ")\n" +
					EmoteUtil.DIGIT_THREE + " - ¥`1000` - " + (user.getBalance() < 1000 ? "~~" : "") + "Divine Card box" + (user.getBalance() < 1000 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.DIVINE_SKIN_BOX) + ")\n\n" +
					"You currently have ¥`" + user.getBalance() + "` in your wallet.\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the shop menu.");
				break;
			case 2:
				builder.setTitle(EmoteUtil.LABEL + " Shop: Passive Boxes");
				builder.setDescription("Crossed items can't be afforded.\n" +
					EmoteUtil.DIGIT_ONE + " - ¥`500` - " + (user.getBalance() < 500 ? "~~" : "") + "Regular Passive box" + (user.getBalance() < 500 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.PASSIVE_BOX) + ")\n" +
					EmoteUtil.DIGIT_TWO + " - ¥`1125` - " + (user.getBalance() < 1125 ? "~~" : "") + "Special Passive box" + (user.getBalance() < 1125 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.SPECIAL_PASSIVE_BOX) + ")\n" +
					EmoteUtil.DIGIT_THREE + " - ¥`2500` - " + (user.getBalance() < 2500 ? "~~" : "") + "Divine Passive box" + (user.getBalance() < 2500 ? "~~" : "") + ". (Owned: " + user.getInventory().getItemCount(ItemType.DIVINE_PASSIVE_BOX) + ")\n\n" +
					"You currently have ¥`" + user.getBalance() + "` in your wallet.\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the shop menu.");
				break;
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getInventoryMenuMessage(User author, DatabaseUser user) {
		EmbedBuilder builder = new EmbedBuilder();

		builder.setTitle(EmoteUtil.HANDBAG + " Inventory Menu");
		builder.setDescription("This is your own inventory, you can use here the idols boxes " + EmoteUtil.WRAPPED_GIFT + " and cards boxes " + EmoteUtil.SHOPPING_BAGS /*+ " and passives boxes " + EmoteUtil.LABEL*/ + " you earned.\n\n" +
			"You currently have ¥`" + user.getBalance() + "` in your wallet.\n" +
			"Press the bellow reactions to browse the inventory menu.");
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getInventoryPageMessage(User author, DatabaseUser user, int selectedInventoryPage) {
		EmbedBuilder builder = new EmbedBuilder();
		int regular;
		int special;
		int divine;

		switch (selectedInventoryPage) {
			case 0:
				regular = user.getInventory().getItemCount(ItemType.IDOL_BOX);
				special = user.getInventory().getItemCount(ItemType.SPECIAL_IDOL_BOX);
				divine = user.getInventory().getItemCount(ItemType.DIVINE_IDOL_BOX);

				builder.setTitle(EmoteUtil.WRAPPED_GIFT + " Inventory: Idol Boxes");
				builder.setDescription("Crossed items can't be used.\n" +
					EmoteUtil.DIGIT_ONE + " - `" + regular + "` - " + (regular == 0 ? "~~" : "") + "Regular Idol box" + (regular == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_TWO + " - `" + special + "` - " + (special == 0 ? "~~" : "") + "Special Idol box" + (special == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_THREE + " - `" + divine + "` - " + (divine == 0 ? "~~" : "") + "Divine Idol box" + (divine == 0 ? "~~" : "") + ".\n\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the inventory menu.");
				break;
			case 1:
				regular = user.getInventory().getItemCount(ItemType.SKIN_BOX);
				special = user.getInventory().getItemCount(ItemType.SPECIAL_SKIN_BOX);
				divine = user.getInventory().getItemCount(ItemType.DIVINE_SKIN_BOX);

				builder.setTitle(EmoteUtil.SHOPPING_BAGS + " Inventory: Skin Boxes");
				builder.setDescription("Crossed items can't be used.\n" +
					EmoteUtil.DIGIT_ONE + " - `" + regular + "` - " + (regular == 0 ? "~~" : "") + "Regular Skin box" + (regular == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_TWO + " - `" + special + "` - " + (special == 0 ? "~~" : "") + "Special Skin box" + (special == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_THREE + " - `" + divine + "` - " + (divine == 0 ? "~~" : "") + "Divine Skin box" + (divine == 0 ? "~~" : "") + ".\n\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the inventory menu.");
				break;
			case 2:
				regular = user.getInventory().getItemCount(ItemType.PASSIVE_BOX);
				special = user.getInventory().getItemCount(ItemType.SPECIAL_PASSIVE_BOX);
				divine = user.getInventory().getItemCount(ItemType.DIVINE_PASSIVE_BOX);

				builder.setTitle(EmoteUtil.LABEL + " Inventory: Passive Boxes");
				builder.setDescription("Crossed items can't be used.\n" +
					EmoteUtil.DIGIT_ONE + " - `" + regular + "` - " + (regular == 0 ? "~~" : "") + "Regular Passive box" + (regular == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_TWO + " - `" + special + "` - " + (special == 0 ? "~~" : "") + "Special Passive box" + (special == 0 ? "~~" : "") + ".\n" +
					EmoteUtil.DIGIT_THREE + " - `" + divine + "` - " + (divine == 0 ? "~~" : "") + "Divine Passive box" + (divine == 0 ? "~~" : "") + ".\n\n" +
					"Press " + EmoteUtil.BACK_ARROW + " to go back the inventory menu.");
				break;
		}
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 226, 148));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getIdolCollectionOverviewMessage(User user, DatabaseUser dbUser, User author) {
		IdolCollection idolCollection = dbUser.getIdolCollection();
		Progress idolsProgress = idolCollection.getCollectionsIdolsProgress();
		Progress cuteProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.CUTE);
		Progress coolProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.COOL);
		Progress passionProgress = idolCollection.getCollectionsIdolsByTypeProgress(IdolType.PASSION);
		Progress cardsProgress = idolCollection.getCollectionsCardsProgress();
		Progress basicCardProgress = idolCollection.getCollectionsBasicCardProgress();
		Progress evolvedCardProgress = idolCollection.getCollectionsEvolvedCardProgress();
		Rank idolCollectionRank = DatabaseUserManager.getUserRank(dbUser, RankingType.IDOL_COLLECTION);

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
		builder.setFooter(author.getName() + "#" + author.getDiscriminator(), author.getAvatarUrl());
		builder.setColor(new Color(255, 175, 225));
		MessageEmbed embed = builder.build();
		MessageBuilder mBuilder = new MessageBuilder(embed);
		return (mBuilder.build());
	}

	public static Message getIdolCollectionIdolMessage(Idol idol, User user, DatabaseUser dbUser, User author) {
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
