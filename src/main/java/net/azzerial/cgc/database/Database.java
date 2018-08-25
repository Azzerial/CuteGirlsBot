package net.azzerial.cgc.database;

import net.azzerial.cgc.core.CGC;
import net.azzerial.cgc.utils.MiscUtil;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Database {

	private static Database instance;

	private Connection connection;
	private HashMap<String, PreparedStatement> preparedStatements;
	private List<String> idolsInDatabase;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return (instance);
	}

	private Database() {
		preparedStatements = new HashMap<String, PreparedStatement>();

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + (CGC.dataDirectory.equals("./") ? "database.db" : CGC.dataDirectory + "/database.db"));
			Statement statement = connection.createStatement();

			statement.setQueryTimeout(30);

			// Create the ops table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"ops(" +
					"id INTEGER NOT NULL UNIQUE, " +
					"PRIMARY KEY (id)" +
				")");

			// Create the servers table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"servers(" +
					"id INTEGER NOT NULL UNIQUE, " +
					"prefix TEXT NOT NULL, " +
					"admins TEXT NOT NULL, " +
					"PRIMARY KEY (id)" +
				")");

			// Create the users table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"users(" +
					"user_id INTEGER NOT NULL UNIQUE, " +
					"PRIMARY KEY (user_id)" +
				")");

			// Create the user_currency table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"user_currency(" +
					"user_id INTEGER NOT NULL UNIQUE, " +
					"balance INTEGER DEFAULT 0, " +
					"daily_streak INTEGER DEFAULT 0, " +
					"last_daily_time TEXT DEFAULT NULL, " +
					"PRIMARY KEY (user_id)" +
				")");

			// Create the user_idol_collection table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"user_idol_collection(" +
					"user_id INTEGER NOT NULL UNIQUE, " +
					"PRIMARY KEY (user_id)" +
				")");

			// ops statements
			preparedStatements.put(Permissions.ADD_OP, connection.prepareStatement("INSERT INTO ops (id) VALUES (?)"));
			preparedStatements.put(Permissions.GET_OPS, connection.prepareStatement("SELECT * FROM ops"));
			preparedStatements.put(Permissions.REMOVE_OP, connection.prepareStatement("DELETE FROM ops WHERE id = ?"));

			// users statements
			preparedStatements.put(DatabaseUserManager.ADD_USER, connection.prepareStatement("INSERT INTO users (user_id) VALUES (?)"));
			preparedStatements.put(DatabaseUserManager.GET_USERS, connection.prepareStatement("SELECT * FROM users"));

			// user_currency statements
			preparedStatements.put(DatabaseUserManager.ADD_USER_CURRENCY, connection.prepareStatement("INSERT INTO user_currency (user_id) VALUES (?)"));
			preparedStatements.put(DatabaseUserManager.GET_USER_CURRENCY, connection.prepareStatement("SELECT * FROM user_currency WHERE user_id = ?"));
			preparedStatements.put(DatabaseUserManager.UPDATE_USER_CURRENCY_BALANCE, connection.prepareStatement("UPDATE user_currency SET balance = ? WHERE user_id = ?"));
			preparedStatements.put(DatabaseUserManager.UPDATE_USER_CURRENCY_DAILY_STREAK, connection.prepareStatement("UPDATE user_currency SET daily_streak = ? WHERE user_id = ?"));
			preparedStatements.put(DatabaseUserManager.UPDATE_USER_CURRENCY_LAST_DAILY_TIME, connection.prepareStatement("UPDATE user_currency SET last_daily_time = ? WHERE user_id = ?"));

			// user_idol_collection statements
			preparedStatements.put(CuteGirl.GET_IDOLS_IN_DATABASE, connection.prepareStatement("SELECT * FROM user_idol_collection WHERE user_id = 0"));
			preparedStatements.put(CuteGirl.GET_USER_IDOL_COLLECTION, connection.prepareStatement("SELECT * FROM user_idol_collection WHERE user_id = ?"));

		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Get the names of the idols which are in database.
		idolsInDatabase = loadIdolsInDatabase();

		System.out.println("[Database] Idols in database:");
		for (String s : idolsInDatabase) {
			System.out.println("- " + s);
		}
	}

	private List<String> loadIdolsInDatabase() {
		List<String> columnNames = new ArrayList<String>();

		try {
			ResultSet resultSet = preparedStatements.get(CuteGirl.GET_IDOLS_IN_DATABASE).executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i += 1) {
				String column = resultSetMetaData.getColumnName(i);

				if (!column.equalsIgnoreCase("user_id")) {
					columnNames.add(column);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(columnNames, new MiscUtil.sortStringByIgnoreCase());
		return (columnNames);
	}

	public PreparedStatement getStatement(String statementName) {
		if (!preparedStatements.containsKey(statementName)) {
			return (null);
		}
		return (preparedStatements.get(statementName));
	}

	public List<String> getIdolsInDatabase() {
		return (idolsInDatabase);
	}

	public boolean addIdolToDatabase(String columnName, String defaultValue) {
		if (idolsInDatabase.contains(columnName)) {
			return (false);
		}

		try {
			if (connection.createStatement().executeUpdate("ALTER TABLE user_idol_collection ADD COLUMN " + columnName + " TEXT DEFAULT \"" + defaultValue + "\"") == 0) {
				return (true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

	public boolean updateIdolCollectionInDatabase(long userId, String columnName, String value) {
		if (!idolsInDatabase.contains(columnName)) {
			return (false);
		}

		try {
			if (connection.createStatement().executeUpdate("UPDATE user_idol_collection SET " + columnName + " = \"" + value + "\" WHERE user_id = " + Long.toString(userId)) == 0) {
				return (true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (false);
	}

}