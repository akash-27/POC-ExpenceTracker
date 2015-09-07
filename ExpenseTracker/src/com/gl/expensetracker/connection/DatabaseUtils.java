package com.gl.expensetracker.connection;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

	Connection connection;

	public DatabaseUtils() {

		try {
			Class.forName(ApplicationUtilities.getCustomProperty("mysql.driverClass", "com.mysql.jdbc.Driver"));

		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();

		}
	}

	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(ApplicationUtilities.getCustomProperty("mysql.connectionUrl",null), ApplicationUtilities.getCustomProperty("mysql.userName", "root"), ApplicationUtilities.getCustomProperty("mysql.password", "India@123"));
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {

			connection.close();
		} catch (Exception e) {
			System.out.println(" error in closing database connection");
		}

	}


	//	public static Connection getConnection() {
	//		Properties props = new Properties();
	//		FileInputStream fis = null;
	//		Connection con = null;
	//		try {
	//			fis = new FileInputStream("/root/dym_java/expense-tracker/ExpenseTracker/src/expensetracker.properties");
	//			props.load(fis);
	//			Class.forName(props.getProperty("mysql.driverClass"));
	//			con = DriverManager.getConnection(props.getProperty("mysql.connectionUrl"),
	//					props.getProperty("mysql.userName"),
	//					props.getProperty("mysql.password"));
	//		} catch (IOException | SQLException | ClassNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return con;
	//	}
	//
	//
	//
	//	public void close() {
	//		try {
	//
	//			connection.close();
	//		} catch (Exception e) {
	//			System.out.println(" error in closing database connection");
	//		}
	//
	//	}
}
