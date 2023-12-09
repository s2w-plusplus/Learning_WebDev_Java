package myWpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestForm
 */
@WebServlet("/test_input")
public class TestForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside overloaded doGet() of TestForm which is invoked by "+Thread.currentThread());
		response.setContentType("text/html");
		
		String name=request.getParameter("f1");
		String[] favcolors=request.getParameterValues("clr");
		String browser=request.getParameter("browser");
		String city=request.getParameter("myselect");
		String info=request.getParameter("info");
		
		try(PrintWriter pw=response.getWriter())
		{
			pw.print("<p>Hello "+name+"</p><p>Favorite Color: "+Arrays.toString(favcolors)+"</p><p>Browser: "+browser+"</p><p>City: "+city+"</p><p>Bio: "+info+"</p>");
		}


	}
}