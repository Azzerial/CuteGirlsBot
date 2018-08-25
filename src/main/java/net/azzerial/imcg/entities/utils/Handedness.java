package net.azzerial.imcg.entities.utils;

public enum Handedness {
	LEFT("Left"),
	RIGHT("Right");

	private String hand;

	Handedness(String hand) {
		this.hand = hand;
	}

	public String asString() {
		return (hand);
	}

}
