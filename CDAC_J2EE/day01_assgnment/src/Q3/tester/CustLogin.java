package Q3.tester;

import static Q3.utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CustLogin {

	public static void main(String[] args){
		
		String sql="SELECT * FROM customers where email=? and password=?";
		try (Scanner scan=new java.util.Scanner(System.in);
				Connection conn = fetchConnection();
				PreparedStatement pState=conn.prepareStatement(sql);) {
			
				System.out.println("Enter the Email & the Password");
				pState.setString(1, scan.next());
				pState.setString(2, scan.next());
						
				try(ResultSet rSet=pState.executeQuery())
				{
					if(rSet.next()) { 
					  System.out.println("Login Successful!");
					  System.out.printf("Id:%d, Name:%s, Email:%s, Reg.Amount:%.1f, Reg.Date:%s ."
								,rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getDouble(5),rSet.getDate(6));
					}
					else System.out.println("Invalid Username or Password");	 	
				
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
