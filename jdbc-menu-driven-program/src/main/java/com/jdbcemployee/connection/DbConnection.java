package com.jdbcemployee.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection getDbConnection() {
		try {
			Class.forName(DbDetails.DBDRIVER);
			Connection con = DriverManager.getConnection(
					DbDetails.URL, DbDetails.USERNAME, DbDetails.PASSWORD);
			
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
