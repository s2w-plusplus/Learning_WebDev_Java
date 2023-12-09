package DAO;

import java.sql.SQLException;

import POJO.Customer;

public interface ICustomerDao {
	// method for customer login
	Customer authenticateCustomer(String email, String pwd) throws SQLException;

}
