package net.azzerial.imcg.entities;

public class IdolSkin {

	/*
	 * For each Passive (only on evolved skins):
	 *
	 * Rarity | Passive type    | Max stat %
	 * -------------------------|------------
	 * Normal | level 1 passive | %3
	 * Rare   | level 2 passive | %7
	 * S Rare | level 3 passive | %15
	 *
	 *** Combat Passive:
	 *
	 * Types:
	 *  - Cute: more AD
	 *  - Cool: more Def
	 *  - Passion: balanced AD/Def
	 *
	 * Ideas:
	 *  	bonus % AD/Def/Both depending against opponent from type
	 *  	reduces % AD/Def/Both of the opponent if from type
	 *
	 *** Leader Passive:
	 *
	 * Types:
	 *  - Cute: faster stamina regen (reduction in %)
	 *  - Cool: increases daily revenue
	 *  - Passion: increases gambling rewards and luck
	 *
	 *  Ideas:
	 *   	stamina regen -% time
	 *	daily reward multiplier (flat or %)
	 *	increased % gambling rewards prices
	 */

	private final int id;
	private final String latinThemeName;
	private final String japaneseThemeName;
	private final Rarity rarity;
	private final Stats stats;
	private final Card basicCard;
	private final Card evolvedCard;

	public IdolSkin(int id, String latinThemeName, String japaneseThemeName, Rarity rarity, Stats stats, Card basicCard, Card evolvedCard) {
		this.id = id;
		this.latinThemeName = latinThemeName;
		this.japaneseThemeName = japaneseThemeName;
		this.rarity = rarity;
		this.stats = stats;
		this.basicCard = basicCard;
		this.evolvedCard = evolvedCard;
	}

	public int getId() {
		return (id);
	}

	public String getLatinThemeName() {
		return (latinThemeName);
	}

	public String getJapaneseThemeName() {
		return (japaneseThemeName);
	}

	public Rarity getRarity() {
		return (rarity);
	}

	public Stats getStats() {
		return (stats);
	}

	public Card getBasicCard() {
		return (basicCard);
	}

	public Card getEvolvedCard() {
		return (evolvedCard);
	}

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
				case 3:
					return ("Divine");
			}
			return (null);
		}
	}

	public static class Stats {

		private final int baseAttack;
		private final int baseDefense;

		public Stats(int baseAttack, int baseDefense) {
			this.baseAttack = baseAttack;
			this.baseDefense = baseDefense;
		}

		public int getBaseAttack() {
			return (baseAttack);
		}

		public int getBaseDefense() {
			return (baseDefense);
		}

		public int getAttack(int bonusPercent) {
			int n = (100 + bonusPercent) / 100;
			if (n < 0) {
				n = 0;
			}
			return (baseAttack * n);
		}

		public int getDefense(int bonusPercent) {
			int n = (100 + bonusPercent) / 100;
			if (n < 0) {
				n = 0;
			}
			return (baseDefense * n);
		}
	}

	public static class Card {

		private final String card;
		private final String framelessCard;

		public Card(String card, String framelessCard) {
			this.card = card;
			this.framelessCard = framelessCard;
		}

		public String getCard() {
			return (card);
		}

		public String getFramelessCard() {
			return (framelessCard);
		}

		public boolean hasFramelessCard() {
			return (framelessCard != null && !framelessCard.isEmpty());
		}
	}
}
