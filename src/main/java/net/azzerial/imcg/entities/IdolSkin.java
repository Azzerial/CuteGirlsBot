package net.azzerial.imcg.entities;

import net.azzerial.imcg.entities.utils.Card;
import net.azzerial.imcg.entities.utils.Rarity;
import net.azzerial.imcg.entities.utils.Stats;

public class IdolSkin {

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

	public String getThemeName() {
		StringBuilder builder = new StringBuilder();

		builder.append(latinThemeName).append("  ❲").append(japaneseThemeName).append("❳");
		return (builder.toString());
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

}
