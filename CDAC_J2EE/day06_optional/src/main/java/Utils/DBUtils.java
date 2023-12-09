package Utils;

import java.sql.*;

public class DBUtils {
//singleton instance of DB connection
	private static Connection cn;

	public static void openConnection(String drvrClass, String dbURL, String user, String password)
			throws ClassNotFoundException, SQLException {
		
		if (cn == null) {
			Class.forName(drvrClass);
			cn = DriverManager.getConnection(dbURL, user, password);
		}
	}
	
	public static Connection fetchConnection() {
		return cn;
	}

	public static void closeConnection() throws SQLException
	{
		if(cn != null)
			cn.close();
	}
	
}
