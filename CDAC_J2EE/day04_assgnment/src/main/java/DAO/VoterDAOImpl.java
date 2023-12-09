package DAO;

import static utils.DButils.fetchConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Voter;

public class VoterDAOImpl implements IVoterDAO {
	private Connection conn;
	private PreparedStatement pState1,pState2,pStateRevert;
	
	public VoterDAOImpl() throws ClassNotFoundException, SQLException {
		Connection conn = fetchConnection();
		pState1=conn.prepareStatement("SELECT * FROM voters WHERE 	email=? AND password=?");
	    pState2=conn.prepareStatement("UPDATE voters SET status=1 where id=?");
	    pStateRevert=conn.prepareStatement("UPDATE voters SET status=0");
	}
	
	public Voter authenticateVoter(String email, String passwd) throws SQLException {
		
		pState1.setString(1, email);
		pState1.setString(2, passwd);
				
		try(ResultSet rSet=pState1.executeQuery())
		{
			if(rSet.next())  
				return (new Voter(rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getBoolean(5),rSet.getString(6)));		
			 return null;
		}
	}

	
	public boolean updateVotingStatus(int voterId) throws SQLException {
		
		pState2.setInt(1, voterId);
		int status=pState2.executeUpdate();
		return (status==1)?true:false;
	}
		
		
	public void cleanUp() throws SQLException {
			pStateRevert.executeUpdate();
			if (pState1 != null)
				pState1.close();
			if (pState2 != null)
				pState2.close();
			if (pStateRevert != null)
				pStateRevert.close();
			System.out.println("voter dao cleaned up...");
		}
		
	
}
