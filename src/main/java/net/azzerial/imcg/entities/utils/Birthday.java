package net.azzerial.imcg.entities.utils;

public class Birthday {

	private int day;
	private Month month;

	public Birthday(int day, Month month) {
		this.day = day;
		this.month = month;
	}

	public String getDay() {
		StringBuilder builder = new StringBuilder();

		builder.append(day);
		if (day == 1 || day == 21 || day == 31) {
			builder.append("st");
		} else if (day == 2 || day == 22) {
			builder.append("nd");
		} else if (day == 3 || day == 23) {
			builder.append("rd");
		} else {
			builder.append("th");
		}
		return (builder.toString());
	}

	public int getDayValue() {
		return (day);
	}

	public String getMonth() {
		return (month.asString());
	}

	public int getMonthValue() {
		return (month.getValue());
	}

	public String asString() {
		return (new StringBuilder().append(getDay()).append(" of ").append(getMonth()).toString());
	}

}
