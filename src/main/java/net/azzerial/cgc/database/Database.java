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
			connection = DriverManager.getConnection("jdbc:sqlite:" + (CGC.dataDirectory.equals("./") ? "cgc.db" : CGC.dataDirectory + "/cgc.db"));
			Statement statement = connection.createStatement();

			statement.setQueryTimeout(30);
			statement.execute("PRAGMA foreign_keys = ON");

			// Create the Ops table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"Ops(" +
					"id TEXT NOT NULL UNIQUE, " +
					"PRIMARY KEY (id)" +
				")");

			// Create the Servers table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"Servers(" +
					"id TEXT NOT NULL UNIQUE, " +
					"prefix TEXT NOT NULL, " +
					"admins TEXT NOT NULL, " +
					"PRIMARY KEY (id)" +
				")");

			// Create the Players table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				"Players(" +
					"id TEXT NOT NULL UNIQUE, " +
					"money REAL DEFAULT 0, " +
					"gems REAL DEFAULT 0, " +
					"PRIMARY KEY (id)" +
				")");

			// Ops Statements
			preparedStatements.put(Permissions.ADD_OP, connection.prepareStatement("INSERT INTO Ops (id) VALUES (?)"));
			preparedStatements.put(Permissions.GET_OPS, connection.prepareStatement("SELECT id FROM Ops"));
			preparedStatements.put(Permissions.REMOVE_OP, connection.prepareStatement("DELETE FROM Ops WHERE id = ?"));

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