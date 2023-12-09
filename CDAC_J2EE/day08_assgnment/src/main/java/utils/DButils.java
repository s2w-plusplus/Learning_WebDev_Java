package utils;

import java.sql.*;

public class DButils{
	
	private static Connection conn;
	
	public static void openConnection(String driver,String DB_url,String Username,String Password) 
			throws SQLException, ClassNotFoundException  {
		
		System.out.println("inside openConnection()");
		if(conn==null) {
			Class.forName(driver);
			conn=DriverManager.getConnection(DB_url, Username,Password);
		}
	}
	
	public  static Connection fetchConnection(){	
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException
	{
		System.out.println("inside closeConnection()");
		if(conn != null)
			conn.close();
	}
}
