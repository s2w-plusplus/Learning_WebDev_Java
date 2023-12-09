package dao;

import pojos.Address;
import pojos.Course;
import pojos.Student;
import org.hibernate.*;
import static utils.HibernateUtils.getSf;

public class StudentDaoImpl implements IStudentDao {

	@Override
	public String admitNewStudent(Student student, String courseTitle) {
		String mesg = "student admisssion failed";
		String jpql = "select c from Course c where c.title =:title";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// validate course : JPQL
			Course course = session.createQuery(jpql, Course.class).setParameter("title", courseTitle)
					.getSingleResult();
			// course : persistent : currently course n student are un related
			// add student ref to the course
			// course.getStudents().add(student); //linked course ---> student

			// student.setSelectedCourse(course);//link student --> course
			course.addStudent(student);
			tx.commit();// auto dirty chking : insert
			mesg = "student admission done!";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String cancelAdmission(String email, String courseTitle) {
		String mesg = "cancelling admisssion failed";
		String courseJpql = "select c from Course c where c.title =:title";
		String studentJpql = "select s from Student s where s.email=:email";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// validate course
			Course course = session.createQuery(courseJpql, Course.class).setParameter("title", courseTitle)
					.getSingleResult();
			// validate student
			Student student = session.createQuery(studentJpql, Student.class).setParameter("email", email)
					.getSingleResult();
			// course , student : PERSISTENT
			// helper method : removeStudent
			course.removeStudent(student);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return null;
	}

	@Override
	public String assignStudentAddress(int studentId, Address address) {
		String mesg = "address assignment failed";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Student student = session.get(Student.class, studentId);
			if (student != null) {
				//address: TRANSIENT, student:PERSISTENT
				address.setOwner(student);
				session.persist(address);
				mesg = "address assignment done";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String updateCity(int studentId, String newCity) {
		String mesg = "updating city failed";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Address address=session.get(Address.class, studentId);
			if (address != null) {
				//address :persistent
				address.setCity(newCity);
				mesg = "updating city done";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
