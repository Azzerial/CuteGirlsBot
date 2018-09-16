package net.azzerial.cgc.entities.roulettes;

public enum RouletteType {
	ROULETTE(0),
	/*
	 * 15% -> Gem [10 (40%), 25 (30%), 50 (20%), 100 (10%)]
	 * 25% -> ¥en [100 (40%), 250 (30%), 500 (20%), 1000 (10%)]
	 * 25% -> IdolBox [normal (72%), special (25%), divine (3%)]
	 * 25% -> SkinBox [normal (72%), special (25%), divine (3%)]
	 * 10% -> PassiveBox [normal (65%), special (30%), divine (5%)]
	 */
	CURRENCY_ROULETTE(1),
	/*
	 * 35% -> Gem [20 (40%), 50 (30%), 100 (20%), 200 (10%)]
	 * 65% -> ¥en [100 (40%), 250 (30%), 500 (20%), 1000 (10%)]
	 *  0% -> IdolBox []
	 *  0% -> SkinBox []
	 *  0% -> PassiveBox []
	 */
	IDOL_ROULETTE(2),
	/*
	 * 15% -> Gem [10 (40%), 25 (30%), 50 (20%), 100 (10%)]
	 *  0% -> ¥en [100 (40%), 250 (30%), 500 (20%), 1000 (10%)]
	 * 55% -> IdolBox [normal (50%), special (35%), divine (15%)]
	 * 25% -> SkinBox [normal (65%), special (30%), divine (5%)]
	 *  5% -> PassiveBox [normal (65%), special (30%), divine (5%)]
	 */
	CARD_ROULETTE(3);
	/*
	 * 15% -> Gem [10 (40%), 25 (30%), 50 (20%), 100 (10%)]
	 * 10% -> ¥en [100 (40%), 250 (30%), 500 (20%), 1000 (10%)]
	 * 15% -> IdolBox [normal (65%), special (30%), divine (5%)]
	 * 60% -> SkinBox [normal (50%), special (35%), divine (15%)]
	 *  0% -> PassiveBox []
	 */

	private int type;

	RouletteType(int type) {
		this.type = type;
	}

	public int getValue() {
		return (type);
	}

}
