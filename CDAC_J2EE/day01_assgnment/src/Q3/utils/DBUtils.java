package Q3.utils;

import java.sql.*;

public class DBUtils {
//singleton instance of DB connection
	private static Connection cn;

	public static Connection fetchConnection() throws ClassNotFoundException, SQLException {
		if (cn == null) {
			// class loading of JDBC driver :optional currently in Java SE
			Class.forName("com.mysql.cj.jdbc.Driver");
			// get fixed DB connection
			// test : DB name
			String url = "jdbc:mysql://localhost:3306/wbjava_schema?useSSL=false&allowPublicKeyRetrieval=true";
			cn = DriverManager.getConnection(url, "root", "MySQL@42500191");
		}
		return cn;

	}
}
