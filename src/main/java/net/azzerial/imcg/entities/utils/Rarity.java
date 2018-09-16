package net.azzerial.imcg.entities.utils;

public enum Rarity {
	NORMAL(1),
	RARE(2),
	S_RARE(3);

	private int rarity;

	Rarity(int rarity) {
		this.rarity = rarity;
	}

	public int getValue() {
		return (rarity);
	}

	public String asString() {
		switch (this) {
			case NORMAL:
				return ("Normal");
			case RARE:
				return ("Rare");
			case S_RARE:
				return ("S Rare");
			default:
				return ("");
		}
	}
}
