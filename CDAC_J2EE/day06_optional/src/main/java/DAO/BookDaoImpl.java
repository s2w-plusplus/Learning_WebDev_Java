package DAO;

import java.util.ArrayList;
import java.util.List;
import POJO.Book;
import java.sql.*;
import static Utils.DBUtils.fetchConnection;


public class BookDaoImpl implements IBookDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;


	public BookDaoImpl() throws ClassNotFoundException, SQLException {
		
		cn = fetchConnection();
		pst1 = cn.prepareStatement("select distinct category from books");
		pst2 = cn.prepareStatement("select * from books where category=?");
		pst3 = cn.prepareStatement("select * from books where id=?");
		System.out.println("book dao created...");
	}

	@Override
	public List<String> fetchAllCategories() throws SQLException {
		
		List<String> categoryList = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				categoryList.add(rst.getString(1));
		}
		return categoryList;
	}

	@Override
	public List<Book> getBooksByCategory(String category) throws SQLException {
		List<Book> books = new ArrayList<>();
		pst2.setString(1, category);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next())
				books.add(new Book(rst.getInt(1), rst.getString(2), rst.getString(3), category, rst.getDouble(5)));
		}
		System.out.println("dao retd books " + books);
		return books;
	}

	@Override
	public List<Book> getCartDetails(List<Integer> cart) throws SQLException {
		List<Book> books = new ArrayList<>();
		for (int id : cart) {
			pst3.setInt(1, id);
			try (ResultSet rst = pst3.executeQuery()) {
				if (rst.next())
					books.add(new Book(id, rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5)));
			}
		}
		System.out.println("cart books "+books);
		return books;
	}


	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		System.out.println("book dao cleaned up....");
	}

}
