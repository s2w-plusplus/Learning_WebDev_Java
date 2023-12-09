package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDAOImpl;

public class ConditionalDelete {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner scan=new Scanner(System.in)){
			
			VendorDAOImpl vdao= new VendorDAOImpl();
 			
			System.out.println("Enter the lower threshold for registration_amount"
					+ " and the upper threshold for registration_date");
			System.out.println(vdao.deleteByCondition(scan.nextDouble(), LocalDate.parse(scan.next())));		
		}

	}

}
