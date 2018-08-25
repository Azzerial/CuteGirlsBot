package net.azzerial.imcg.entities.utils;

public class Stats {

	private final int baseAttack;
	private final int baseDefense;

	public static final int EVOLVED_BONUS = 15;

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
