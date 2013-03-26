package com.taogongbao.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
	
	public static Connection getConnection(String className,String url,String username, String password){
		try {
			Class.forName(className);
			return DriverManager.getConnection(url, username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
