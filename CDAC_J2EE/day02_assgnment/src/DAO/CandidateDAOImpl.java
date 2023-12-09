package DAO;
//DATA ACCESS OBJECT LAYER

import POJO.Candidate;
import static utils.DButils.fetchConnection;

import java.sql.*;
import java.util.ArrayList;

public class CandidateDAOImpl implements ICandidateDAO {
	private Connection conn;
	private PreparedStatement pState1,pState2,pState3,pStateRevert1, pStateRevert2;
	
	public CandidateDAOImpl() throws SQLException, ClassNotFoundException {
		Connection conn = fetchConnection();
		pState1=conn.prepareStatement("INSERT INTO candidates VALUES(default,?,?,0)");
	    pState2=conn.prepareStatement("SELECT * FROM candidates");
	    pState3=conn.prepareStatement("UPDATE candidates SET votes=votes+1 where id=?");
	    pStateRevert1=conn.prepareStatement("UPDATE candidates SET votes=0");
	    pStateRevert2=conn.prepareStatement("DELETE from candidates where id>5");
	} 
	
	public String registerCandidate(String name , String party) throws SQLException{
		
		pState1.setString(1, name);
		pState1.setString(2, party);
		int status=pState1.executeUpdate();
		return "Candidate Registration was "+((status==1)?"Successful":"Unsuccessful");
		}
	
	public ArrayList<Candidate> dispCandidateList() throws SQLException{
		ArrayList<Candidate> tempList=new ArrayList<>();
		try(ResultSet rSet=pState2.executeQuery()){
			while(rSet.next()) {
				tempList.add(new Candidate(rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getInt(4)));
			}
		return tempList;	
		}
	}
	
	public String updateVotes(int candidateId) throws SQLException{
		pState3.setInt(1, candidateId);
		int status=pState3.executeUpdate();
		return "Vote Updation was "+((status==1)?"Successful":"Unsuccessful");
		
	}
	
	public void cleanUp() throws SQLException {
		 pStateRevert1.executeUpdate();
		 pStateRevert2.executeUpdate();
		if (pState1 != null)
			pState1.close();
		if (pState2 != null)
			pState2.close();
		if (pState3 != null)
			pState3.close();
		if (pStateRevert1 != null)
			pStateRevert1.close();
		if (pStateRevert2 != null)
			pStateRevert2.close();
		System.out.println("candidate dao cleaned up...");
	}
	
}
