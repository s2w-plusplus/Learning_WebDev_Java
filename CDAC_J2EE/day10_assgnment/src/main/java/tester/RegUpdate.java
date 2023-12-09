package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDAOImpl;

public class RegUpdate {

	public static void main(String[] args) {

		try(SessionFactory sf=getFactory();
				Scanner scan=new Scanner(System.in)){
			
			VendorDAOImpl vdao= new VendorDAOImpl();
 			
			System.out.println("Enter the Email & Registration_amount Offset for the Vendor");
			System.out.println(vdao.updateRegAmount(scan.next(),scan.nextDouble()));
			
		}
	}

}
