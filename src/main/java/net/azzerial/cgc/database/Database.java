package net.azzerial.cgc.database;

import net.azzerial.cgc.core.CGC;

import java.sql.*;

import java.util.HashMap;

public class Database {

	private static Database instance;

	private Connection connection;
	private HashMap<String, PreparedStatement> preparedStatements;

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

		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getStatement(String statementName) {
		if (!preparedStatements.containsKey(statementName)) {
			return (null);
		}
		return (preparedStatements.get(statementName));
	}

}