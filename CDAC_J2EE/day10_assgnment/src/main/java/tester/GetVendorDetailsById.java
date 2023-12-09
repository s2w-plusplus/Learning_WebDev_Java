package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import dao.VendorDAOImpl;
import pojos.Vendor;

public class GetVendorDetailsById {

	public static void main(String[] args) {
		
		try(SessionFactory sf=getFactory();Scanner scan=new Scanner(System.in))
		{
			VendorDAOImpl dao=new VendorDAOImpl();
			System.out.println("Enter Vendor id");
			Vendor ref=dao.findByVendorId(scan.nextLong());
			System.out.println(ref);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
