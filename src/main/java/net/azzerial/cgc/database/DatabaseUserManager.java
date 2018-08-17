package net.azzerial.cgc.database;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.utils.MiscUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseUserManager {

	public static final String ADD_USER = "ADD_USER";
	public static final String GET_USERS = "GET_USERS";
	public static final String ADD_USER_CURRENCY = "ADD_USER_CURRENCY";
	public static final String GET_USER_CURRENCY = "GET_USER_CURRENCY";
	public static final String UPDATE_USER_CURRENCY_BALANCE = "UPDATE_USER_CURRENCY_BALANCE";
	public static final String UPDATE_USER_CURRENCY_DAILY_STREAK = "UPDATE_USER_CURRENCY_DAILY_STREAK";
	public static final String UPDATE_USER_CURRENCY_LAST_DAILY_TIME = "UPDATE_USER_CURRENCY_LAST_DAILY_TIME";

	private static List<DatabaseUser> usersCache;
	private static DatabaseUserManager instance;

	public DatabaseUserManager() {
		List<DatabaseUser> users = new ArrayList<DatabaseUser>();

		// Load users data from database
		try {
			ResultSet usersIds = Database.getInstance().getStatement(GET_USERS).executeQuery();
			while (usersIds.next()) {
				long id = usersIds.getLong(DatabaseUser.Column.ID.getColumn());

				// Query user_currency for the current user id.
				PreparedStatement statementUserCurrency = Database.getInstance().getStatement(GET_USER_CURRENCY);
				statementUserCurrency.setLong(1, id);
				ResultSet userCurrency = statementUserCurrency.executeQuery();

				// Get user_currency values.
				long balance = userCurrency.getLong(DatabaseUser.Column.BALANCE.getColumn());
				int daily_streak = userCurrency.getInt(DatabaseUser.Column.DAILY_STREAK.getColumn());
				GregorianCalendar lastDailyTime = MiscUtil.stringToGregorianCalendar(userCurrency.getString(DatabaseUser.Column.LAST_DAILY_TIME.getColumn()));

				DatabaseUser user = new DatabaseUser(id, balance, daily_streak, lastDailyTime);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.usersCache = users;
	}

	public static DatabaseUserManager loadUsers() {
		if (instance == null) {
			instance = new DatabaseUserManager();
			System.out.println("[Database/DatabaseUserManager]: Loaded Database Users.");
		}
		return (instance);
	}

	// --- Database ---

	public static DatabaseUser addUserToDatabaseAndReturn(long id) {
		if (addUserToDatabase(id)) {
			return (getDatabaseUser(id));
		}
		return (null);
	}

	public static boolean addUserToDatabase(long id) {
		if (databaseContainsUser(id)) {
			return (false);
		}

		try {
			// Set user insert statement.
			PreparedStatement statementUser = Database.getInstance().getStatement(ADD_USER);
			statementUser.setLong(1, id);
			// Set user_currency insert statement.
			PreparedStatement statementUserCurrency = Database.getInstance().getStatement(ADD_USER_CURRENCY);
			statementUserCurrency.setLong(1, id);

			if (statementUser.executeUpdate() == 1 && statementUserCurrency.executeUpdate() == 1) {
				return (usersCache.add(createDefaultUser(id)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserBalance(long id, long newBalance) {
		if (!databaseContainsUser(id)) {
			return (false);
		}
		DatabaseUser user = getDatabaseUser(id);

		try {
			PreparedStatement statement = Database.getInstance().getStatement(DatabaseUserManager.UPDATE_USER_CURRENCY_BALANCE);
			statement.setLong(1, newBalance);
			statement.setLong(2, id);
			if (statement.executeUpdate() == 1) {
				return (overrideCachedDatabaseUser(
					new DatabaseUser(
						user.getId(),
						newBalance,
						user.getDailyStreak(),
						user.getLastDailyTime()
					)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserDailyStreak(long id, int newDailyStreak) {
		if (!databaseContainsUser(id)) {
			return (false);
		}
		DatabaseUser user = getDatabaseUser(id);

		try {
			PreparedStatement statement = Database.getInstance().getStatement(DatabaseUserManager.UPDATE_USER_CURRENCY_DAILY_STREAK);
			statement.setInt(1, newDailyStreak);
			statement.setLong(2, id);
			if (statement.executeUpdate() == 1) {
				return (overrideCachedDatabaseUser(
					new DatabaseUser(
						user.getId(),
						user.getBalance(),
						newDailyStreak,
						user.getLastDailyTime()
					)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserLastDailyTime(long id, GregorianCalendar newLastDailyTime) {
		if (!databaseContainsUser(id)) {
			return (false);
		}
		DatabaseUser user = getDatabaseUser(id);

		try {
			PreparedStatement statement = Database.getInstance().getStatement(DatabaseUserManager.UPDATE_USER_CURRENCY_LAST_DAILY_TIME);
			statement.setString(1, MiscUtil.gregorianCalendarToString(newLastDailyTime));
			statement.setLong(2, id);
			if (statement.executeUpdate() == 1) {
				return (overrideCachedDatabaseUser(
					new DatabaseUser(
						user.getId(),
						user.getBalance(),
						user.getDailyStreak(),
						newLastDailyTime
					)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	// --- Class ---

	public static boolean databaseContainsUser(long id) {
		for (int i = 0; i < usersCache.size(); i += 1) {
			if (usersCache.get(i).getId() == id) {
				return (true);
			}
		}
		return (false);
	}

	public static DatabaseUser getDatabaseUser(long id) {
		for (int i = 0; i < usersCache.size(); i += 1) {
			if (usersCache.get(i).getId() == id) {
				return (usersCache.get(i));
			}
		}
		return (null);
	}

	// --- Utility ---

	private static DatabaseUser createDefaultUser(long id) {
		return (new DatabaseUser(id, 0, 0, null));
	}

	private static boolean overrideCachedDatabaseUser(DatabaseUser newDatabaseUser) {
		DatabaseUser databaseUser = getDatabaseUser(newDatabaseUser.getId());
		if (databaseUser == null) {
			return (false);
		}
		return (usersCache.remove(databaseUser) && usersCache.add(newDatabaseUser));
	}

}
