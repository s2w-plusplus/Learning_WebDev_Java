package myWpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDaoImpl;
import POJO.Book;
import POJO.Customer;

@WebServlet("/check_out")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			
			HttpSession hs = request.getSession();
			System.out.println("from logout page HS is new " + hs.isNew());// false
			System.out.println("session id " + hs.getId());// SAME session id for the same clnt

			Customer c = (Customer) hs.getAttribute("customer_details");
			if (c != null) {
				pw.print("<h5> Hello ,  " + c.getName() + "</h5>");
		
				BookDaoImpl bookDao = (BookDaoImpl) hs.getAttribute("book_dao");
	
				List<Integer> cart = (List<Integer>) hs.getAttribute("shopping_cart");
			
				List<Book> cartDetails = bookDao.getCartDetails(cart);
				pw.print("<h4> Cart Contents</h4>");
				double totalCartValue = 0;
				for (Book b : cartDetails) {
					pw.print("<h5>" + b + "</h5>");
					totalCartValue += b.getPrice();
				}
				pw.print("<h5>Total Cart Value "+totalCartValue+"</h5>");
				hs.invalidate();
				pw.print("<h5>You have logged out....</h5>");

			} else // => no cookies rcvd from the clnt browser
				pw.print("<h5> No Cookies : Session Tracking Failed!!!!!!!!!!!</h5>");
			pw.print("<h5><a href='login.html'>Visit Again</a></h5>");

		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass().getName(), e);
		}
	}

}
