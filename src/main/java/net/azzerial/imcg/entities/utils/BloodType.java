package net.azzerial.imcg.entities.utils;

public enum BloodType {
	A("A"),
	AB("AB"),
	B("B"),
	O("O");

	private String type;

	BloodType(String type) {
		this.type = type;
	}

	public String asString() {
		return (type);
	}

}
