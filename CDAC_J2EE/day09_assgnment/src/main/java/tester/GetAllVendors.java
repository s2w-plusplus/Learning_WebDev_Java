package tester;

import static utils.HibernateUtils.getFactory;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDAOImpl;
import pojos.Vendor;

public class GetAllVendors {

	public static void main(String[] args) {
		
		try(SessionFactory sf=getFactory())
		{
			VendorDAOImpl dao=new VendorDAOImpl();
			System.out.println("Getting all Vendor details");
			List<Vendor> allVendors = dao.getAllVendors();
			System.out.println(allVendors);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
