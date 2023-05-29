package com.jdbcDemo.connection;

import java.sql.*;

public class DbConnection {
	
	public static Connection getDatabaseConnection() {
		try {
			Class.forName(DbDetails.DBDRIVER);
			Connection con = DriverManager.getConnection(
			DbDetails.URL, DbDetails.USERNAME, DbDetails.PASSWORD);
			return con;
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
