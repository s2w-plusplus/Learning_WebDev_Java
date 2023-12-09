package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import POJO.*;


@WebServlet("/cList")
public class CandidateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doGet() of " + getClass().getName());
		HttpSession persistentSession = request.getSession();
		System.out.println("SessionId : " + persistentSession.getId()); 
		
		VoterDAOImpl vDao=(VoterDAOImpl)persistentSession.getAttribute("vDao_Instance");
		CandidateDAOImpl cDao=(CandidateDAOImpl)persistentSession.getAttribute("cDao_Instance");
		Voter v=(Voter)persistentSession.getAttribute("voter_info");
		
		/*if we were using cookies
		 * Cookie[] cookies = request.getCookies();
		 * then you iterate through it to get desired things
		 * lots of hassle, can't put objects 
		 */
		
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			if(v!=null) {
				ArrayList<Candidate> clist=cDao.dispCandidateList();
				pw.print("<body><h4>Login Successful</h4><br>");
				pw.print("<h3>Welcome "+v.getName()+"</h3><br>");
				pw.print("<hr><form action='status'>");
				pw.print("<table><tr><th>Cast Your Vote</th></tr>");
				clist.forEach((c)->{
					pw.print("<tr><td><input type='radio' name='vote' value="+c.getcID()+"></td><td>"+c.getName().toUpperCase()+
							"</td><td>"+c.getParty().toUpperCase()+"</td></tr>");
				});
				pw.print("</table><hr><br/>");
				pw.print("<button type='submit'>Vote</button>");
				pw.print("</form></body>");
			}
			else pw.print("<h3>Unable to keep track of you</h3><br>");
		}catch(Exception e) {
			throw new ServletException("Error in doGet of "+ getClass().getName()+" caused by ", e);
		}
	
	}

}
