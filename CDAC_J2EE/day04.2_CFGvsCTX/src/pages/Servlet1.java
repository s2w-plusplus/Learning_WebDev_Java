package pages;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
/*@WebServlet(
		urlPatterns = { "/s1" }, 
		initParams = { 
				@WebInitParam(name = "test", value = "1234")
		})*/
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

/*Added by me
	//compare with Servlet2 for better clarity 
	
	//changes have been made here by me
	//to understand the difference between overriding init() vs init(ServletConfig config) methods of GenericServlet Interface
	//When overriding init(ServletConfig config), the first thing that must be done is to call: super.init(config);
	//If you do so then calling directly to getServletContext() in your method will no longer result in an NPE
*/
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	//	ServletConfig sc=getServletConfig(); //no need 
		System.out.println("in init of s1: init param "+config.getInitParameter("test1"));
		ServletContext ctx=getServletContext();
		System.out.println("ctx param "+ctx.getInitParameter("test2"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" s1");
	}

}
