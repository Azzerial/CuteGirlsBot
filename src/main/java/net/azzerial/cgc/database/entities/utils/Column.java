package net.azzerial.cgc.database.entities.utils;

public enum Column {
	ID("user_id"),
	BALANCE("balance"),
	DAILY_STREAK("daily_streak"),
	LAST_DAILY_TIME("last_daily_time");

	private String column;

	Column(String column) {
		this.column = column;
	}

	public String asString() {
		return (column);
	}
}
