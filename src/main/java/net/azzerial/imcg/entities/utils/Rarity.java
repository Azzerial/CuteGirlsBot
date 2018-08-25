package net.azzerial.imcg.entities.utils;

public enum Rarity {
	NORMAL(0),
	RARE(1),
	S_RARE(2);

	private int rarity;

	Rarity(int rarity) {
		this.rarity = rarity;
	}

	public int getValue() {
		return (rarity);
	}

	public String asString() {
		switch (rarity) {
			case 0:
				return ("Normal");
			case 1:
				return ("Rare");
			case 2:
				return ("S Rare");
			default:
				return ("");
		}
	}
}
