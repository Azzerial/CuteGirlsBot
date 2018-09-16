package net.azzerial.imcg.items;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.imcg.items.core.Draw;
import net.azzerial.imcg.items.core.Item;
import net.azzerial.imcg.items.core.ItemType;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.List;

public class DivinePassiveBox extends Item {

	@Override
	public String getName() {
		return ("Divine Passive Box");
	}

	@Override
	public String getDescription() {
		return ("A divine box which gives you a random passive you can use on your Leader.");
	}

	@Override
	public ItemType getItemType() {
		return (ItemType.DIVINE_PASSIVE_BOX);
	}

	@Override
	public List<Integer> getPrizesPercentages() {
		return (Arrays.asList(
			3,  // Tier 5
			7,  // Tier 4
			20, // Tier 3
			45, // Tier 2
			25  // Tier 1
		));
	}

	@Override
	public Message useItem(DatabaseUser user) {
		Draw draw = new Draw(getPrizesPercentages());
		draw.execute();

		switch (draw.getValue()) {
			case 0:
				// give a random Tier 5 passive.
				break;
			case 1:
				// give a random Tier 4 passive.
				break;
			case 2:
				// give a random Tier 3 passive.
				break;
			case 3:
				// give a random Tier 2 passive.
				break;
			case 4:
				// give a random Tier 1 passive.
				break;
		}
		return (null);
	}

}