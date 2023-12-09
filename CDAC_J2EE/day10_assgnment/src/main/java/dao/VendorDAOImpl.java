package dao;


import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.NoResultException;

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
	
	
//===============================================================================
	
	@Override
	public String authenticateVendor(String email, String passwd) {
		Vendor vendor=null;
		String status=null;
		String jpql="Select x from Vendor v where v.email=:em and v.password=:pwd";
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
				vendor=session.createQuery(jpql, Vendor.class)
					.setParameter("em", email).setParameter("pwd", passwd).getSingleResult();
				status="The Vendor Is Authorised";
				tx.commit();
		}catch (RuntimeException re) {	
			if(re instanceof NoResultException) {
				status="The Vendor Is Unauthorised";
				if(tx != null)
					tx.rollback();	
			}else {
				if(tx != null)
					tx.rollback();	
				throw re;
			}	
		} 
		return status;
	}

	@Override
	public String updateRegAmount(String email, double offset) {
		Vendor vendor=null;
		String status=null;
		String jpql="Select x from Vendor x where x.email=:em";
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			vendor=session.createQuery(jpql, Vendor.class)
					.setParameter("em", email).getSingleResult();
			vendor.setRegAmount(vendor.getRegAmount()+offset);
			tx.commit();
			status="The Registration amount updated successfully";
		}catch (RuntimeException re) {	
			if(re instanceof NoResultException) {
				status="No such Vendor Found, Updation failed!";
				if(tx != null)
					tx.rollback();	
			}else{
				if(tx != null)
					tx.rollback();	
				throw re;
			}	
		} 
		
		return status;
	}

	@Override
	public String deleteByCondition(double minAmount, LocalDate thresholdDate) {
		Vendor vendors=null;
		String status="No deletion has taken place";
		String jpql="delete  from Vendor v where v.regAmount<:min and v.regDate > :specDate";
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
				int updateCount=session.createQuery(jpql)
					.setParameter("min", minAmount).setParameter("specDate", thresholdDate)
					.executeUpdate();
			tx.commit();
			if(updateCount>0)
				status=updateCount+" Deletion(s) performed";
		}catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); 
			throw e;
		}
		return status;
	}

}
