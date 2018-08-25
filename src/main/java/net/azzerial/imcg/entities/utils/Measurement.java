package net.azzerial.imcg.entities.utils;

public class Measurement {

	private int bust;
	private int waist;
	private int hip;

	public Measurement(int bust, int waist, int hip) {
		this.bust = bust;
		this.waist = waist;
		this.hip = hip;
	}

	public int getBustValue() {
		return (bust);
	}

	public int getWaistValue() {
		return (waist);
	}

	public int getHipValue() {
		return (hip);
	}

	public String asString() {
		return (new StringBuilder().append(bust).append("-").append(waist).append("-").append(hip).toString());
	}

}
