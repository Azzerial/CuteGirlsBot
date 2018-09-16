package net.azzerial.cgc.database.entities;

import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.items.core.Inventory;
import net.azzerial.imcg.items.core.ItemType;

import java.util.GregorianCalendar;

public class DatabaseUser {

	private long id;
	private long balance;
	private int dailyStreak;
	private GregorianCalendar lastDailyTime;
	private IdolCollection idolCollection;
	private Inventory inventory;

	public DatabaseUser(long id, long balance, int dailyStreak, GregorianCalendar lastDailyTime, IdolCollection idolCollection, Inventory inventory) {
		this.id = id;
		this.balance = balance;
		this.dailyStreak = dailyStreak;
		this.lastDailyTime = lastDailyTime;
		this.idolCollection = idolCollection;
		this.inventory = inventory;
	}

	public long getId() {
		return (id);
	}

	public long getBalance() {
		return (balance);
	}

	public boolean updateBalance(long value) {
		if (DatabaseUserManager.updateUserBalance(id, value)) {
			this.balance = value;
			return (true);
		}
		return (false);
	}

	public int getDailyStreak() {
		return (dailyStreak);
	}

	public boolean updateDailyStreak(int value) {
		if (DatabaseUserManager.updateUserDailyStreak(id, value)) {
			this.dailyStreak = value;
			return (true);
		}
		return (false);
	}

	public GregorianCalendar getLastDailyTime() {
		return (lastDailyTime);
	}

	public boolean updateLastDailyTime(GregorianCalendar calendar) {
		if (DatabaseUserManager.updateUserLastDailyTime(id, calendar)) {
			this.lastDailyTime = calendar;
			return (true);
		}
		return (false);
	}

	public IdolCollection getIdolCollection() {
		return (idolCollection);
	}

	public Inventory getInventory() {
		return (inventory);
	}

	public boolean updateInventory(ItemType itemType, int value) {
		if (DatabaseUserManager.updateUserInventory(id, itemType, value)) {
			this.inventory.updateItemCount(id, itemType, value);
			return (true);
		}
		return (false);
	}

}
