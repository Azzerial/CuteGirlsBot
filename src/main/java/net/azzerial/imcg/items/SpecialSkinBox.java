package net.azzerial.imcg.items;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.imcg.entities.utils.Rarity;
import net.azzerial.imcg.entities.utils.SingleSkin;
import net.azzerial.imcg.items.core.Draw;
import net.azzerial.imcg.items.core.Item;
import net.azzerial.imcg.items.core.ItemType;
import net.azzerial.imcg.items.core.ItemUtils;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.List;

public class SpecialSkinBox extends Item {

	@Override
	public String getName() {
		return ("Special Card Box");
	}

	@Override
	public String getDescription() {
		return ("A special box which gives you a random card of an owned Idol for your collection.");
	}

	@Override
	public ItemType getItemType() {
		return (ItemType.SPECIAL_SKIN_BOX);
	}

	@Override
	public List<Integer> getPrizesPercentages() {
		return (Arrays.asList(
			25, // Normal
			13, // Normal+
			40, // Rare
			15, // Rare+
			7,  // S Rare
			0   // S Rare+
		));
	}

	@Override
	public Message useItem(DatabaseUser user) {
		Draw draw = new Draw(getPrizesPercentages());
		draw.execute();

		SingleSkin skin = null;
		switch (draw.getValue()) {
			case 0:
				// give a random Normal skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.NORMAL, false);
				break;
			case 1:
				// give a random Normal+ skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.NORMAL, true);
				break;
			case 2:
				// give a random Rare skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.RARE, false);
				break;
			case 3:
				// give a random Rare+ skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.RARE, true);
				break;
			case 4:
				// give a random S Rare skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.S_RARE, false);
				break;
			case 5:
				// give a random S Rare+ skin.
				skin = ItemUtils.getRandomSkinItemFromSpecificRank(user, Rarity.S_RARE, true);
				break;
		}
		return (ItemUtils.addSkinToCollection(user, skin));
	}

}