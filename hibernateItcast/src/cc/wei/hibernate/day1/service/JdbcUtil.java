package cc.wei.hibernate.day1.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	private static String user = "root";
	private static String password = "123456";
	private static String url = "jdbc:mysql:///hibernate";
	private static String driverName = "com.mysql.jdbc.Driver";

	private JdbcUtil() {
		
	}
	
	static {
		try {
			Class.forName(driverName);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
