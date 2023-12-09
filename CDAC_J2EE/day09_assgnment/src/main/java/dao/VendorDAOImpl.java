package dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import pojos.Vendor;
import static utils.HibernateUtils.getFactory;


public class VendorDAOImpl implements IVendorDAO {
	
	@Override
	public String addVendorDetails(Vendor vendor) {
		String mesg="Adding Vendor details failed....";
		Session session=getFactory().openSession();
		Transaction tx=session.beginTransaction();
		try {
			Serializable id=session.save(vendor);
			tx.commit();
			mesg="new Vendor details added with id="+id;
		}catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		} finally {
			if(session != null)
				session.close();
		}
		return mesg;
	}

	@Override
	public Vendor findByVendorId(long vendorId) {
		Vendor vendor=null;
		Session session=getFactory().openSession();
		Transaction tx=session.beginTransaction();
		try {
			vendor=session.get(Vendor.class,vendorId);
			tx.commit();
		}catch (RuntimeException e) {	
			if(tx != null)
				tx.rollback();
			throw e;
		} finally {
			if(session != null)
				session.close();
		}
		return vendor;
	}
	
	@Override
	public List<Vendor> getAllVendors() {
		List<Vendor> vendors= null;
		String jpql = "select v from Vendor v";
		// get session from SF
		Session session = getFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			vendors = session.createQuery(jpql, Vendor.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}finally {
			if(session != null)
				session.close();
		}
		return vendors;
	}
	
}
