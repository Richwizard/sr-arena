package com.sr.data;

import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBController {

	private static final DBController dbcontroller = new DBController();
	private static Connection connection;
	private static final String DB_PATH = "data/shxdb.db";
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Error: unable to load driver class!");
			e.printStackTrace();
		}
	}
	
	public DBController()	{
		
	}

	public static ArrayList<String> dbController(ArrayList<String> names, int namesAmount) {
		DBController dbc = DBController.getInstance();
		dbc.initDBConnection();
		return dbc.handleDB(names, namesAmount);
	}

	private ArrayList<String> handleDB(ArrayList<String> names, int namesAmount) {
		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			String sql = new String("SELECT name FROM virtual_entity_name ORDER BY RANDOM() LIMIT " + namesAmount + ";");
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				names.add(rs.getString("name"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
		return names;
	}

	public static DBController getInstance() {
		return dbcontroller;
	}

	private void initDBConnection() {
		try {
			if (connection != null)
				return;
//			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
//			if (!connection.isClosed())
//				System.out.println("...Connection established");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						if (connection.isClosed())
							System.out.println("Connection to Database closed");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

/*
 * stmt.executeUpdate("DROP TABLE IF EXISTS books;"); stmt.
 * executeUpdate("CREATE TABLE books (author, title, publication, pages, price);"
 * ); stmt.
 * execute("INSERT INTO books (author, title, publication, pages, price) VALUES ('Paulchen Paule', 'Paul der Penner', '2001-05-06', '1234', '5.67');"
 * ); PreparedStatement ps =
 * connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?);");
 * ps.setString(1, "Willi Winzig"); ps.setString(2, "Willi's Wille");
 * ps.setDate(3, Date.valueOf("2011-05-16")); ps.setInt(4, 432); ps.setDouble(5,
 * 32.95); ps.addBatch();
 * 
 * 
 * System.out.println("Creating statement..."); stmt = conn.createStatement();
 * String sql; sql = "SELECT id, first, last, age FROM Employees"; ResultSet rs
 * = stmt.executeQuery(sql);
 * 
 * //STEP 5: Extract data from result set while(rs.next()){ //Retrieve by column
 * name int id = rs.getInt("id"); int age = rs.getInt("age"); String first =
 * rs.getString("first"); String last = rs.getString("last");
 * 
 * //Display values System.out.print("ID: " + id); System.out.print(", Age: " +
 * age); System.out.print(", First: " + first); System.out.println(", Last: " +
 * last); }
 * 
 * https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
 * https://javabeginners.de/Datenbanken/SQLite-Datenbank.php
 */