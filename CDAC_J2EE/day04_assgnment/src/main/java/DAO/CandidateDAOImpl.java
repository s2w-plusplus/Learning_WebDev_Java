package DAO;
//DATA ACCESS OBJECT LAYER

import POJO.Candidate;
import static utils.DButils.fetchConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CandidateDAOImpl implements ICandidateDAO {
	private Connection conn;
	private PreparedStatement pState1,pState2,pState3,pState4,pState5,pStateRevert1, pStateRevert2;
	
	public CandidateDAOImpl() throws SQLException, ClassNotFoundException {
		Connection conn = fetchConnection();
		pState1=conn.prepareStatement("INSERT INTO candidates VALUES(default,?,?,0)");
	    pState2=conn.prepareStatement("SELECT * FROM candidates");
	    pState3=conn.prepareStatement("UPDATE candidates SET votes=votes+1 where id=?");
	    pState4=conn.prepareStatement("SELECT * FROM candidates ORDER BY votes DESC LIMIT 2");
	    pState5=conn.prepareStatement("SELECT party,SUM(votes) FROM candidates GROUP BY party ORDER BY votes DESC");
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
	
	public boolean updateVotes(int candidateId) throws SQLException{
		pState3.setInt(1, candidateId);
		int status=pState3.executeUpdate();
		return (status==1)?true:false;
	}
	
	public List<Candidate> top2Analysis()  throws SQLException{
		ArrayList<Candidate> tempList=new ArrayList<>();
		try(ResultSet rSet=pState4.executeQuery()){
			while(rSet.next()) {
				tempList.add(new Candidate(rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getInt(4)));
			}
		return tempList;	
		}
	}
	
	public LinkedHashMap<String,Integer> partywiseAnalysis() throws SQLException{
		LinkedHashMap<String,Integer> tempMap=new LinkedHashMap<>();
		try(ResultSet rSet=pState5.executeQuery()){
			while(rSet.next()) {
				tempMap.put (rSet.getString(1),rSet.getInt(2) );
			}
		}
		return tempMap;	
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
		if (pState4 != null)
			pState4.close();
		if (pState5 != null)
			pState5.close();
		if (pStateRevert1 != null)
			pStateRevert1.close();
		if (pStateRevert2 != null)
			pStateRevert2.close();
		System.out.println("candidate dao cleaned up...");
	}
	
}
