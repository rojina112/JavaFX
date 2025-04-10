package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Mydatabase {
	
	final private String HOST = "localhost";
	final private String USER = "root";
	final private String PASS = "Irish2062@";
	final private int PORT = 3306;
	final private String DBNAME = "employeeenrollmentsystem";
	final private String DRIVER = "com.mysql.cj.jdbc.Driver";
	final private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeenrollmentsystem", "root", "Irish2062@");
			if (conn != null) {
				System.out.println("Database connection established successfully");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error: MySQL JDBC Driver not found - " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error: Database connection failed - " + e.getMessage());
			if (e.getMessage().contains("Access denied")) {
				System.err.println("Please check your username and password");
			} else if (e.getMessage().contains("Communications link failure")) {
				System.err.println("Please make sure MySQL server is running");
			} else if (e.getMessage().contains("Unknown database")) {
				System.err.println("Database 'employeeenrollmentsystem' does not exist");
			}
		} catch (Exception e) {
			System.err.println("Unexpected error during database connection: " + e.getMessage());
		}
		return conn;
	}

	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			if (conn != null) {
				System.out.println("Database connection established successfully");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error: MySQL JDBC Driver not found - " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error: Database connection failed - " + e.getMessage());
			if (e.getMessage().contains("Access denied")) {
				System.err.println("Please check your username and password");
			} else if (e.getMessage().contains("Communications link failure")) {
				System.err.println("Please make sure MySQL server is running");
			} else if (e.getMessage().contains("Unknown database")) {
				System.err.println("Database '" + DBNAME + "' does not exist");
			}
		} catch (Exception e) {
			System.err.println("Unexpected error during database connection: " + e.getMessage());
		}
		return conn;
	}

	
	public static boolean testConnection() {
		Connection conn = null;
		try {
			conn = getConnection();
			if (conn != null) {
				// Try to execute a simple query to fully test the connection
				Statement stmt = conn.createStatement();
				stmt.execute("SELECT 1");
				stmt.close();
				conn.close();
				return true;
			}
		} catch (Exception e) {
			System.err.println("Connection test failed: " + e.getMessage());
		} finally {
			close(conn);
		}
		return false;
	}

	
	public static boolean close(Connection conn) {
		boolean result = false;
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				result = true;
			}
		} catch (SQLException e) {
			System.err.println("Error closing connection: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error during connection close: " + e.getMessage());
		}
		return result;
	}
}



