package myWpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet
 */

//@WebServlet(urlPatterns="/map2meToo",loadOnStartup=1) //done using xml tags
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		System.out.println("inside overloaded init() of 2ndservlet which is invoked by "+Thread.currentThread());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("inside overloaded destroy() of 2ndservlet which is invoked by "+Thread.currentThread());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside overloaded doGet() of 2ndservlet which is invoked by "+Thread.currentThread());
		
		response.setContentType("text/html");
		
		try(PrintWriter pw=response.getWriter())
		{
			pw.print("<h4> Saurabh, this is your second servlet used on "+LocalDateTime.now()+"</h4>");
		}
	}

}
