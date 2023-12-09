package javaBeans;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import DAO.CandidateDAOImpl;


public class CandidateBean {

	private String name;
	private String party;
	private String dob;
	private CandidateDAOImpl cDao;
	private String statusMessage;
	
	public CandidateBean() throws Exception {
		System.out.println("in candidate bean constr");
		cDao=new CandidateDAOImpl();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public CandidateDAOImpl getcDao() {
		return cDao;
	}
	public void setcDao(CandidateDAOImpl cDao) {
		this.cDao = cDao;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	public String validateCandidate(){
		
		int age=Period.between(LocalDate.parse(dob), LocalDate.now()).getYears(); 
		try {
				if(age>35) 
					statusMessage=cDao.registerCandidate(name, party);
				else
					statusMessage="Registration failed : Violation Of Age Constraint!";	
				
			} catch (SQLException e) {
				  statusMessage="Registration failed : "+e.getMessage();
			}
		return "admin_page";
	}
	
	
	
}
