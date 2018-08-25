package net.azzerial.cgc.database.entities;

import net.azzerial.imcg.entities.IdolCollection;

import java.util.GregorianCalendar;

public class DatabaseUser {

	private long id;
	private long balance;
	private int dailyStreak;
	private GregorianCalendar lastDailyTime;
	private IdolCollection idolCollection;

	public DatabaseUser(long id, long balance, int dailyStreak, GregorianCalendar lastDailyTime, IdolCollection idolCollection) {
		this.id = id;
		this.balance = balance;
		this.dailyStreak = dailyStreak;
		this.lastDailyTime = lastDailyTime;
		this.idolCollection = idolCollection;
	}

	public long getId() {
		return (id);
	}

	public long getBalance() {
		return (balance);
	}

	public int getDailyStreak() {
		return (dailyStreak);
	}

	public GregorianCalendar getLastDailyTime() {
		return (lastDailyTime);
	}

	public IdolCollection getIdolCollection() {
		return (idolCollection);
	}

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

}
