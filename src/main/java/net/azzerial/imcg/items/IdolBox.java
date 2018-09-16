package net.azzerial.imcg.items;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.utils.IdolTier;
import net.azzerial.imcg.items.core.Draw;
import net.azzerial.imcg.items.core.Item;
import net.azzerial.imcg.items.core.ItemType;
import net.azzerial.imcg.items.core.ItemUtils;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;
import java.util.List;

public class IdolBox extends Item {

	/*
	 * 50% -> tier 3
	 * 35% -> tier 2
	 * 15% -> tier 1
	 */

	@Override
	public String getName() {
		return ("Idol Box");
	}

	@Override
	public String getDescription() {
		return ("A box which gives you a random Idol for your collection.");
	}

	@Override
	public ItemType getItemType() {
		return (ItemType.IDOL_BOX);
	}

	@Override
	public List<Integer> getPrizesPercentages() {
		return (Arrays.asList(
			50, // Tier 3
			35, // Tier 2
			15  // Tier 1
		));
	}

	@Override
	public Message useItem(DatabaseUser user) {
		Draw draw = new Draw(getPrizesPercentages());
		draw.execute();

		Idol idol = null;
		switch (draw.getValue()) {
			case 0:
				// give a random tier 3 idol.
				idol = ItemUtils.getRandomIdolFromSpecificTier(IdolTier.TIER_3);
				break;
			case 1:
				// give a random tier 2 idol.
				idol = ItemUtils.getRandomIdolFromSpecificTier(IdolTier.TIER_2);
				break;
			case 2:
				// give a random tier 1 idol.
				idol = ItemUtils.getRandomIdolFromSpecificTier(IdolTier.TIER_1);
				break;
		}
		return (ItemUtils.addIdolToCollection(user, idol));
	}

}
