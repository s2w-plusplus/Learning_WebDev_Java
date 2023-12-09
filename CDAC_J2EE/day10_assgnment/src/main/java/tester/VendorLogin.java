package tester;

import org.hibernate.SessionFactory;

import dao.VendorDAOImpl;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;
public class VendorLogin {

	public static void main(String[] args) {


		try(SessionFactory sf=getFactory();
				Scanner scan=new Scanner(System.in)){
			
			VendorDAOImpl vdao= new VendorDAOImpl();
 			
			System.out.println("Enter the Email & Password of the Vendor");
			System.out.println(vdao.authenticateVendor(scan.next(), scan.next()));
			
		}

	}

}
