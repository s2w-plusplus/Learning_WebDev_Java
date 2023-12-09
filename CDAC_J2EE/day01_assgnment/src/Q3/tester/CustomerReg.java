package Q3.tester;

import static Q3.utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.Scanner;

public class CustomerReg {

	public static void main(String[] args) {
		
		String sql="INSERT INTO customers VALUES (default,?, ?, ?, ?, ?, 'customer')";
		try (Scanner scan=new java.util.Scanner(System.in);
				Connection conn = fetchConnection();
				PreparedStatement pState=conn.prepareStatement(sql);) {
			
				System.out.println("Enter your Name");
				pState.setString(1, scan.next());
				System.out.println("Enter your Email");
				pState.setString(2, scan.next());
				System.out.println("Set your New-Password");
				pState.setString(3, scan.next());
				System.out.println("Enter Registration Amount");
				pState.setDouble(4, scan.nextDouble());
				System.out.println("Enter Registration Date in jdbc format(<yyyy>-<[m]m>-<[d]d>)");
				pState.setDate(5, Date.valueOf(scan.next()));
				
				System.out.println("The Operation was a "+((pState.executeUpdate()>0) ? "Success":" Failure"));
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
