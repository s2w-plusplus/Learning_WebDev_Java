package utils;

import java.sql.*;

public class DButils{
	
	private static Connection conn;
	
	public  static Connection fetchConnection() throws ClassNotFoundException, SQLException{	
		
		if(conn==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wbjava_schema?useSSL=false&allowPublicKeyRetrieval=true";
			conn=DriverManager.getConnection(url, "root", "MySQL@42500191");
		}
		return conn;
	}
	
	public static void closeConnection() throws SQLException
	{
		if(conn != null)
			conn.close();
	}
}
