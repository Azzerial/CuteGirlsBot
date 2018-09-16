package net.azzerial.imcg.entities.utils;

public enum IdolTier {
	TIER_1(1),
	TIER_2(2),
	TIER_3(3);

	private int tier;

	IdolTier(int tier) {
		this.tier = tier;
	}

	public int getValue() {
		return (tier);
	}

	public String asString() {
		switch (this) {
			case TIER_1:
				return ("Tier 1");
			case TIER_2:
				return ("Tier 2");
			case TIER_3:
				return ("Tier 3");
		}
		return (null);
	}

}