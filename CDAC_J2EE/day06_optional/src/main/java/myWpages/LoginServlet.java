package myWpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDaoImpl;
import DAO.CustomerDaoImpl;
import POJO.Customer;
import Utils.DBUtils;
import static Utils.DBUtils.openConnection;


public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl customerDao;
	private BookDaoImpl bookDao;

	public void init() throws ServletException {
		try {
			ServletConfig config=getServletConfig();
			System.out.println("in init of " + getClass().getName()+" config "+config);
			openConnection(config.getInitParameter("drvr"),config.getInitParameter("db_url"),
					config.getInitParameter("user_name"),config.getInitParameter("pwd"));
			customerDao = new CustomerDaoImpl();
			bookDao = new BookDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass().getName(), e);
		}
	}

	public void destroy() {
		try {
			customerDao.cleanUp();
			bookDao.cleanUp();
			DBUtils.closeConnection();
		} catch (Exception e) {
			// System.out.println("err in destroy "+e);
			throw new RuntimeException("err in destory", e);// understanding purpose!!!!
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
	
			Customer customer = customerDao.authenticateCustomer(email, password);
			if (customer == null) {
				pw.print("<h5>Invalid Login </h5>");
				pw.print("<h5>Please <a href='login.html'>Retry</a></h5>");
			} else {			
				HttpSession session = request.getSession();
				System.out.println("Imple class name " + session.getClass().getName());// imple cls name
				System.out.println("from 1st page HS is new " + session.isNew());// true
				System.out.println("session id " + session.getId());		
				session.setAttribute("customer_details", customer);			
				session.setAttribute("customer_dao", customerDao);				
				session.setAttribute("book_dao", bookDao);
				session.setAttribute("shopping_cart",new ArrayList<Integer>());
				response.sendRedirect("category");// Client Pull II			
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass().getName(), e);
		}
	}

}
