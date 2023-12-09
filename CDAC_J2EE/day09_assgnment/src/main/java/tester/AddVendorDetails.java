package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;
import pojos.Vendor;
import dao.*;
import org.hibernate.SessionFactory;

public class AddVendorDetails {

	public static void main(String[] args) {
	
		try (SessionFactory sf = getFactory(); Scanner scan = new Scanner(System.in)) {
			
			VendorDAOImpl VendorDao = new VendorDAOImpl();

			System.out.println("Enter Vendor Details in following format: "
					+ "VendorID , Name, Email, Password, Registration_Amount, Registration_Date");
			
			Vendor vendor= new Vendor(scan.nextLong(), scan.next(), scan.next(), 
					scan.next(), scan.nextDouble(), LocalDate.parse(scan.next()));
			
			System.out.println("status " + VendorDao.addVendorDetails(vendor));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
