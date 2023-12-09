package DAO;

import java.sql.*;

public interface ICandidateDAO {
	String registerCandidate(String name,String party) throws SQLException;
	String updateVotes(int candidateId) throws SQLException;
}
