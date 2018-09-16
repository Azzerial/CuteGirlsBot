package net.azzerial.imcg.items.core;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.menu.entities.ShopMenu;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.IdolsList;
import net.dv8tion.jda.core.entities.Message;

import java.util.List;

public class ItemUtils {

	// --- Idol items ---

	public static Idol getRandomIdolFromSpecificTier(IdolTier idolTier) {
		List<Idol> idols = IdolsList.getIdolsByTier(idolTier);

		if (idols.isEmpty()) {
			switch (idolTier) {
				case TIER_1:
					return (getRandomIdolFromSpecificTier(IdolTier.TIER_2));
				case TIER_2:
					return (getRandomIdolFromSpecificTier(IdolTier.TIER_3));
				case TIER_3:
					return (null);
			}
		}
		return (idols.get(MiscUtil.getRandomNumber(0, idols.size())));
	}

	public static final Message addIdolToCollection(DatabaseUser user, Idol idol) {
		// the user either doesn't have the idol or
		if (!user.getIdolCollection().getCollection(idol).isIdolOwned()) {
			// give the idol to the user.
			user.getIdolCollection().getCollection(idol).updateIdolOwn(true);
			// the user received the idol.
			return (MessageUtil.getActionMessage(
				EmoteUtil.NEW_BUTTON, "Idol added to collection!",
				CGC.getAPI().getUserById(user.getId()),
				"`Name` " + idol.getName() + "\n" +
					"`Type` " + idol.getIdolType().asString() + "\n" +
					"`Tier` " + idol.getIdolTier().asString() + "\n\n" +
					"Press " + EmoteUtil.BALLOT_BOX_CHECK + " to continue.",
				idol.getProfilePicture(), null
			));
		}
		// give a small money compensation as the idol is already owned.
		long newBalance = user.getBalance();
		int value = 0;
		
		switch (idol.getIdolTier()) {
			case TIER_1:
				value = ShopMenu.DIVINE_IDOL_BOX_PRICE / 2;
				break;
			case TIER_2:
				value = ShopMenu.SPECIAL_IDOL_BOX_PRICE / 2;
				break;
			case TIER_3:
				value = ShopMenu.IDOL_BOX_PRICE / 2;
				break;
		}
		newBalance += value;
		user.updateBalance(newBalance);
		// the user received a compensation.
		return (MessageUtil.getActionMessage(
			EmoteUtil.YEN_BANKNOTE, "Idol already owned!",
			CGC.getAPI().getUserById(user.getId()),
			"You already own " + idol.getLatinName() + "...\n" +
				"You received ¥`" + value + "` as a compensation.\n" +
				"Updated balance: ¥`" + newBalance + "`\n\n" +
				"Press " + EmoteUtil.BALLOT_BOX_CHECK + " to continue.",
			idol.getProfilePicture(), null
		));
	}

	// --- Skin items ---

	public static SingleSkin getRandomSkinItemFromSpecificRank(DatabaseUser user, Rarity rarity, boolean evolved) {
		List<Idol> idols = user.getIdolCollection().getOwnedIdols();

		if (idols.isEmpty()) {
			return (null);
		}

		// check if the idol has a normal skin if case NORMAL.
		Idol idol = idols.get(MiscUtil.getRandomNumber(0, idols.size()));;
		while (!idol.hasSkinOfRarity(rarity)) {
			idol = idols.get(MiscUtil.getRandomNumber(0, idols.size()));
		}

		// get a random skin id.
		List<IdolSkin> idolSkins = idol.getSkinIdOfRarity(rarity);

		// get the skin count.
		int mid = idols.size() / 2;
		if (mid == 0) {
			mid = 1;
		}
		double count = MiscUtil.getRandomNumber(mid, idols.size()) / rarity.getValue();

		return (new SingleSkin(
			idol,
			idolSkins.get(MiscUtil.getRandomNumber(0, idolSkins.size())),
			evolved,
			(count < 1 ? 1 : (int)count)
		));
	}

	public static final Message addSkinToCollection(DatabaseUser user, SingleSkin skin) {
		// the user doesn't own any idol.
		if (skin == null) {
			return (MessageUtil.getActionMessage(
				EmoteUtil.WARNING, "You don't own a single idol!",
				CGC.getAPI().getUserById(user.getId()),
				"Buy an `Idol Box` in order to get one...\n\n" +
					"Press " + EmoteUtil.BALLOT_BOX_CHECK + " to continue.",
				null, null
			));
		}

		SkinData skinData = user.getIdolCollection().getCollection(skin.getIdol()).getSkinData(skin.getSkin());
		int count = 0;

		// get the correct count of skins.
		if (skin.isEvolved()) {
			count = skinData.getEvolvedSkinCount();
		} else {
			count = skinData.getBasicSkinCount();
		}
		count += skin.getCount();
		// give the skins to the user.
		user.getIdolCollection().getCollection(skin.getIdol()).updateSkinData(skinData, skin.isEvolved(), count);

		// the user received the idol.
		return (MessageUtil.getActionMessage(
			EmoteUtil.NEW_BUTTON, "Card" + (skin.getCount() == 1 ? "" :  "s") + " added to collection!",
			CGC.getAPI().getUserById(user.getId()),
			"`Amount Received` " + skin.getCount() + "\n" +
				"`Idol Name` " + skin.getIdol().getName() + "\n" +
				"`Card Name` " + skin.getSkin().getThemeName() + "\n" +
				"`Rarity` " + skin.getSkin().getRarity().asString() + (skin.isEvolved() ? "+" : "") + "\n" +
				(skin.isEvolved() ? "`Attack` " + skin.getSkin().getStats().getAttack(Stats.EVOLVED_BONUS) + "\n" +
					"`Defense` " + skin.getSkin().getStats().getDefense(Stats.EVOLVED_BONUS)
					: "`Attack` " + skin.getSkin().getStats().getBaseAttack() + "\n" +
					"`Defense` " + skin.getSkin().getStats().getBaseDefense()) + "\n\n" +
				"Press " + EmoteUtil.BALLOT_BOX_CHECK + " to continue.",
			null, (skin.isEvolved() ? skin.getSkin().getEvolvedCard().getCard() : skin.getSkin().getBasicCard().getCard())
		));
	}

}