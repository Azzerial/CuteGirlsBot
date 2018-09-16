package net.azzerial.cgc.database;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.azzerial.cgc.database.entities.utils.Column;
import net.azzerial.cgc.database.entities.utils.Rank;
import net.azzerial.cgc.database.entities.utils.RankingType;
import net.azzerial.cgc.utils.MiscUtil;
import net.azzerial.imcg.idols.core.IdolsList;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolCollection;
import net.azzerial.imcg.entities.utils.SkinData;
import net.azzerial.imcg.items.core.Inventory;
import net.azzerial.imcg.items.core.ItemType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseUserManager {

	public static final String ADD_USER = "ADD_USER";
	public static final String GET_USERS = "GET_USERS";

	public static final String ADD_USER_CURRENCY = "ADD_USER_CURRENCY";
	public static final String GET_USER_CURRENCY = "GET_USER_CURRENCY";
	public static final String UPDATE_USER_CURRENCY_BALANCE = "UPDATE_USER_CURRENCY_BALANCE";
	public static final String UPDATE_USER_CURRENCY_DAILY_STREAK = "UPDATE_USER_CURRENCY_DAILY_STREAK";
	public static final String UPDATE_USER_CURRENCY_LAST_DAILY_TIME = "UPDATE_USER_CURRENCY_LAST_DAILY_TIME";

	public static final String ADD_USER_IDOL_COLLECTION = "ADD_USER_IDOL_COLLECTION";
	public static final String GET_IDOLS_IN_DATABASE = "GET_IDOLS_IN_DATABASE";
	public static final String GET_USER_IDOL_COLLECTION = "GET_USER_IDOL_COLLECTION";

	public static final String ADD_USER_INVENTORY = "ADD_USER_INVENTORY";
	public static final String GET_USER_INVENTORY = "GET_USER_INVENTORY";

	private static List<DatabaseUser> usersCache;
	private static DatabaseUserManager instance;

	public DatabaseUserManager() {
		List<DatabaseUser> users = new ArrayList<DatabaseUser>();
		List<Idol> idols = IdolsList.getIdols();

		// Load users data from database
		try {
			ResultSet usersIds = Database.getInstance().getStatement(GET_USERS).executeQuery();
			while (usersIds.next()) {
				long id = usersIds.getLong(Column.ID.asString());

				// Query user_currency for the current user id.
				PreparedStatement statementUserCurrency = Database.getInstance().getStatement(GET_USER_CURRENCY);
				statementUserCurrency.setLong(1, id);
				ResultSet userCurrencyResultSet = statementUserCurrency.executeQuery();

				// Get user_currency values.
				long balance = userCurrencyResultSet.getLong(Column.BALANCE.asString());
				int daily_streak = userCurrencyResultSet.getInt(Column.DAILY_STREAK.asString());
				GregorianCalendar lastDailyTime = MiscUtil.stringToGregorianCalendar(userCurrencyResultSet.getString(Column.LAST_DAILY_TIME.asString()));

				// Query user_idol_collection for the current user id.
				PreparedStatement statementUserIdolCollection = Database.getInstance().getStatement(GET_USER_IDOL_COLLECTION);
				statementUserIdolCollection.setLong(1, id);
				ResultSet userIdolCollectionResultSet = statementUserIdolCollection.executeQuery();

				// Get user_idol_collection values.
				IdolCollection.Builder idolCollectionBuilder = new IdolCollection.Builder();
				for (int i = 0; i < idols.size(); i += 1) {
					Idol idol = idols.get(i);
					IdolCollection.Collection.Builder collectionBuilder = new IdolCollection.Collection.Builder();
					String[] str = userIdolCollectionResultSet.getString(idol.getDatabaseName()).split(";");
					String[] skins = str[1].split(",");

					collectionBuilder.setUserId(id);
					collectionBuilder.setIdolId(idol.getId());
					if (str[0].equalsIgnoreCase("T")) {
						collectionBuilder.isIdolOwned(true);
					} else if (str[0].equalsIgnoreCase("F"))  {
						collectionBuilder.isIdolOwned(false);
					}
					for (int n = 0; n < skins.length; n += 1) {
						String[] s1 = skins[n].split(":");
						int skinId = Integer.parseInt(s1[0]);
						String[] s2 = s1[1].split("-");
						int basicSkinCount = Integer.parseInt(s2[0]);
						int evolvedSkinCount = Integer.parseInt(s2[1]);

						collectionBuilder.addSkin(skinId, new SkinData(basicSkinCount, evolvedSkinCount));
					}
					idolCollectionBuilder.addIdol(idol.getId(), collectionBuilder.build());
				}

				// Query user_inventory for the current user id.
				PreparedStatement statementUserInventory = Database.getInstance().getStatement(GET_USER_INVENTORY);
				statementUserInventory.setLong(1, id);
				ResultSet userInventoryResultSet = statementUserInventory.executeQuery();

				// Get user_inventory values.
				Inventory inventory = new Inventory(
					userInventoryResultSet.getInt(ItemType.IDOL_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.SPECIAL_IDOL_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.DIVINE_IDOL_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.SKIN_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.SPECIAL_SKIN_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.DIVINE_SKIN_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.PASSIVE_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.SPECIAL_PASSIVE_BOX.asString()),
					userInventoryResultSet.getInt(ItemType.DIVINE_PASSIVE_BOX.asString())
				);

				DatabaseUser user = new DatabaseUser(id, balance, daily_streak, lastDailyTime, idolCollectionBuilder.build(), inventory);
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

	public static DatabaseUser addUserToDatabase(long id) {
		try {
			// Set user insert statement.
			PreparedStatement statementUser = Database.getInstance().getStatement(ADD_USER);
			statementUser.setLong(1, id);
			// Set user_currency insert statement.
			PreparedStatement statementUserCurrency = Database.getInstance().getStatement(ADD_USER_CURRENCY);
			statementUserCurrency.setLong(1, id);
			// Set user_idol_collection insert statement.
			PreparedStatement statementUserIdolCollection = Database.getInstance().getStatement(ADD_USER_IDOL_COLLECTION);
			statementUserIdolCollection.setLong(1, id);
			// Set user_inventory insert statement.
			PreparedStatement statementUserInventory = Database.getInstance().getStatement(ADD_USER_INVENTORY);
			statementUserInventory.setLong(1, id);

			if (statementUser.executeUpdate() == 1
				&& statementUserCurrency.executeUpdate() == 1
				&& statementUserIdolCollection.executeUpdate() == 1
				&& statementUserInventory.executeUpdate() == 1) {
				DatabaseUser user = createDefaultUser(id);

				usersCache.add(user);
				return (user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (null);
	}

	public static boolean updateUserBalance(long id, long newBalance) {
		if (!isUserInDatabase(id)) {
			return (false);
		}

		try {
			PreparedStatement statement = Database.getInstance().getStatement(DatabaseUserManager.UPDATE_USER_CURRENCY_BALANCE);
			statement.setLong(1, newBalance);
			statement.setLong(2, id);
			if (statement.executeUpdate() != -1) {
				return (true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserDailyStreak(long id, int newDailyStreak) {
		if (!isUserInDatabase(id)) {
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
						user.getLastDailyTime(),
						user.getIdolCollection(),
						user.getInventory()
					)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserLastDailyTime(long id, GregorianCalendar newLastDailyTime) {
		if (!isUserInDatabase(id)) {
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
						newLastDailyTime,
						user.getIdolCollection(),
						user.getInventory()
					)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public static boolean updateUserIdolCollection(long userId, String columnName, String value) {
		return (Database.getInstance().updateIdolCollectionInDatabase(userId, columnName, value));
	}

	public static boolean updateUserInventory(long userId, ItemType itemType, int value) {
		return (Database.getInstance().updateInventoryInDatabase(userId, itemType, value));
	}

	// --- Class ---

	public static boolean isUserInDatabase(long id) {
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
		return (addUserToDatabase(id));
	}

	public static Rank getUserRank(DatabaseUser user, RankingType rankingType) {
		List<DatabaseUser> orderedUsers = new ArrayList<DatabaseUser>(usersCache);

		// Order the users based on their IdolCollection's progress value.
		switch (rankingType) {
			case IDOL_COLLECTION:
				Collections.sort(orderedUsers, new MiscUtil.sortDatabaseUserByIdolCollectionProgress());
				break;
			case BALANCE:
				Collections.sort(orderedUsers, new MiscUtil.sortDatabaseUserByBalance());
				break;
		}
		// Note: They are ordered from the HIGHEST to the LOWEST value.

		// Getting the correct rank value following the standard competition ranking:
		// 	https://en.wikipedia.org/wiki/Ranking#Standard_competition_ranking_(%221224%22_ranking)
		int lastProgressValue = 0;
		int rank = 0;
		for (int i = 0; i < orderedUsers.size(); i += 1) {
			DatabaseUser u = orderedUsers.get(i);

			switch (rankingType) {
				case IDOL_COLLECTION:
					if (lastProgressValue != u.getIdolCollection().getCollectionsCardsProgress().getProgress()) {
						rank = i;
					}
					break;
				case BALANCE:
					if (lastProgressValue != Math.toIntExact(u.getBalance())) {
						rank = i;
					}
					break;
			}
			if (user.equals(u)) {
				break;
			}
		}
		return (new Rank(rank + 1, orderedUsers.size()));
	}

	// --- Utility ---

	private static DatabaseUser createDefaultUser(long id) {
		return (new DatabaseUser(id, 0, 0, null, IdolCollection.createNewEmptyCollection(id), Inventory.createNewEmptyInventory()));
	}

	private static boolean overrideCachedDatabaseUser(DatabaseUser newDatabaseUser) {
		DatabaseUser databaseUser = getDatabaseUser(newDatabaseUser.getId());
		if (databaseUser == null) {
			return (false);
		}
		return (usersCache.remove(databaseUser) && usersCache.add(newDatabaseUser));
	}

}
