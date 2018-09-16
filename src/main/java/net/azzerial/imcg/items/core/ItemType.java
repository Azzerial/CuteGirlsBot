package net.azzerial.imcg.items.core;

/*
 * Create:
 *
 *  - Idol Tiers:
 *  	3: Idols starting at normal.
 *  	2: Some Idols starting at rare.
 *  	1: My favorites Idols. (starting at rare)
 *
 *  - Passive Tiers:
 *  	5: 3% bonus
 *  	4: 5% bonus
 *  	3: 7% bonus
 *  	2: 10% bonus
 *  	1: 15% bonus
 */

public enum ItemType {
	UNKNOWN_ITEM(0),
	IDOL_BOX(1),
	/*
	 * 50% -> tier 3
	 * 35% -> tier 2
	 * 15% -> tier 1
	 */
	SPECIAL_IDOL_BOX(2),
	/*
	 * 20% -> tier 3
	 * 45% -> tier 2
	 * 35% -> tier 1
	 */
	DIVINE_IDOL_BOX(3),
	/*
	 *  7% -> tier 3
	 * 33% -> tier 2
	 * 60% -> tier 1
	 */
	SKIN_BOX(4),
	/*
	 * 35% -> Normal
	 * 20% -> Normal+
	 * 30% -> Rare
	 * 15% -> Rare+
	 *  0% -> S Rare
	 *  0% -> S Rare+
	 */
	SPECIAL_SKIN_BOX(5),
	/*
	 * 25% -> Normal
	 * 13% -> Normal+
	 * 40% -> Rare
	 * 15% -> Rare+
	 *  7% -> S Rare
	 *  0% -> S Rare+
	 */
	DIVINE_SKIN_BOX(6),
	/*
	 *  0% -> Normal
	 *  0% -> Normal+
	 * 40% -> Rare
	 * 20% -> Rare+
	 * 33% -> S Rare
	 *  7% -> S Rare+
	 */
	PASSIVE_BOX(7),
	/*
	 * 35% -> tier 5
	 * 30% -> tier 4
	 * 20% -> tier 3
	 * 13% -> tier 2
	 *  2% -> tier 1
	 */
	SPECIAL_PASSIVE_BOX(8),
	/*
	 * 10% -> tier 5
	 * 20% -> tier 4
	 * 33% -> tier 3
	 * 25% -> tier 2
	 * 12% -> tier 1
	 */
	DIVINE_PASSIVE_BOX(9);
	/*
	 * 3% -> tier 5
	 * 7% -> tier 4
	 * 20% -> tier 3
	 * 45% -> tier 2
	 * 25% -> tier 1
	 */

	private int itemId;

	ItemType(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return (itemId);
	}

	public static ItemType getItemType(int id) {
		for (ItemType itemType : values()) {
			if (itemType.itemId == id) {
				return (itemType);
			}
		}
		return (UNKNOWN_ITEM);
	}

	public String asString() {
		switch (this) {
			case IDOL_BOX:
				return ("idol_box");
			case SPECIAL_IDOL_BOX:
				return ("special_idol_box");
			case DIVINE_IDOL_BOX:
				return ("divine_idol_box");
			case SKIN_BOX:
				return ("skin_box");
			case SPECIAL_SKIN_BOX:
				return ("special_skin_box");
			case DIVINE_SKIN_BOX:
				return ("divine_skin_box");
			case PASSIVE_BOX:
				return ("passive_box");
			case SPECIAL_PASSIVE_BOX:
				return ("special_passive_box");
			case DIVINE_PASSIVE_BOX:
				return ("divine_passive_box");
		}
		return ("");
	}
}
