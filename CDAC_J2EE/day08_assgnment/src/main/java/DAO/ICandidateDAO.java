package DAO;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;

import POJO.Candidate;

public interface ICandidateDAO {
	String registerCandidate(String name,String party) throws SQLException;
	boolean updateVotes(int candidateId) throws SQLException;
	
	List<Candidate> top2Analysis()  throws SQLException;
	LinkedHashMap<String,Integer> partywiseAnalysis() throws SQLException;
		
	
	
}
