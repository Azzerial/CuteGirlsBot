package net.azzerial.imcg.items;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.utils.IdolTier;
import net.azzerial.imcg.items.core.Draw;
import net.azzerial.imcg.items.core.Item;
import net.azzerial.imcg.items.core.ItemType;
import net.azzerial.imcg.items.core.ItemUtils;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.List;

public class SpecialIdolBox extends Item {

	@Override
	public String getName() {
		return ("Special Idol Box");
	}

	@Override
	public String getDescription() {
		return ("A special box which gives you a random Idol for your collection.");
	}

	@Override
	public ItemType getItemType() {
		return (ItemType.SPECIAL_IDOL_BOX);
	}

	@Override
	public List<Integer> getPrizesPercentages() {
		return (Arrays.asList(
			20, // Tier 3
			45, // Tier 2
			35  // Tier 1
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