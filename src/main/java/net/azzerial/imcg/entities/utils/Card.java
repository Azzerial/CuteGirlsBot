package net.azzerial.imcg.entities.utils;

public class Card {

	private final String card;
	private final String thumbnail;

	public Card(String card, String thumbnail) {
		this.card = card;
		this.thumbnail = thumbnail;
	}

	public String getCard() {
		return (card);
	}

	public String getThumbnail() {
		return (thumbnail);
	}

}
