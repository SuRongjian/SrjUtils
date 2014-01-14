package com.srj.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getMySQLDBConnection(String databaseName,String user,String password){
		Connection conn=null;
		String uri="jdbc:mysql://localhost:3306/"+databaseName+"?useUnicode=true&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(uri, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getOracleDBConnection(String databaseName,String user,String password){
		Connection conn=null;
		String uri=null;
		/** Oracle数据库连接URL*/
		if(databaseName==null){
			uri = "jdbc:oracle:thin:@192.168.5.128:1521:orcl";
		}else{
			uri = "jdbc:oracle:thin:@"+databaseName;
		}
	    
		/** Oracle数据库连接驱动*/
	    final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	    
		try {
			Class.forName(DB_DRIVER);
			conn=DriverManager.getConnection(uri, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
