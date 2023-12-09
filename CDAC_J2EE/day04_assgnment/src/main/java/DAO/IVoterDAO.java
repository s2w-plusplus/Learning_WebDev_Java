package DAO;

import java.sql.SQLException;

import POJO.Voter;

public interface IVoterDAO  {

	 Voter authenticateVoter(String email,String passwd) throws SQLException;
	boolean updateVotingStatus(int voterId) throws SQLException  ;
	

}
