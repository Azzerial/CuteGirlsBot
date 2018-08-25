package net.azzerial.imcg.entities.utils;

public enum Month {
	JANUARY(1),
	FEBRUARY(2),
	MARCH(3),
	APRIL(4),
	MAY(5),
	JUNE(6),
	JULY(7),
	AUGUST(8),
	SEPTEMBER(9),
	OCTOBER(10),
	NOVEMBER(11),
	DECEMBER(12);

	private int month;

	Month(int month) {
		this.month = month;
	}

	public int getValue() {
		return (month);
	}

	public String asString() {
		switch (month) {
			case 1:
				return("January");
			case 2:
				return("February");
			case 3:
				return("March");
			case 4:
				return("April");
			case 5:
				return("May");
			case 6:
				return("June");
			case 7:
				return("July");
			case 8:
				return("August");
			case 9:
				return("September");
			case 10:
				return("October");
			case 11:
				return("November");
			case 12:
				return("December");
			default:
				return ("");
		}
	}

}
