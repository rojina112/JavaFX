package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mydatabase {
	
		private static final String DriveManager = null;
		final private String HOST = "localhost";
		final private String USER = "root";
		final private String PASS = "Irish2062@";
		final private int PORT = 3306;
		final private String DBNAME = "EmployeeEnrollmentSystem";
		final private String DRIVER = "com.mysql.cj.jdbc.Driver";
		final private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

		public Connection connect() {
			Connection conn = null;
			try {
				Class.forName(DRIVER); // Loading driver class
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (Exception ex) {
				System.out.println("Error : " + ex.getMessage());
			}
			return conn;
		}

		public boolean close(Connection conn) {
			boolean result = false;
			try {
				if (!conn.isClosed()) {
					conn.close();
					result = true;
				}
			} catch (Exception ex) {
				System.out.println("Error : " + ex.getMessage());
			}
			return result;
		}
	}


