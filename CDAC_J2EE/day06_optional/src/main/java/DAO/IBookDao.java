package DAO;

import java.sql.SQLException;
import java.util.List;

import POJO.Book;

public interface IBookDao {
	
	List<String> fetchAllCategories() throws SQLException;

	List<Book> getBooksByCategory(String category) throws SQLException;

	List<Book> getCartDetails(List<Integer> cart) throws SQLException;
}
