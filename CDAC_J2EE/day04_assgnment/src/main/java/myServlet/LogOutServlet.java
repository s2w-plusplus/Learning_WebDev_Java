package myServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CandidateDAOImpl;
import DAO.VoterDAOImpl;
import POJO.Voter;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/status")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doGet() of " + getClass().getName());
		HttpSession persistentSession = request.getSession();
		System.out.println("SessionId : " + persistentSession.getId());
		Voter v=(Voter)persistentSession.getAttribute("voter_info");
		
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			if(v!=null) {
				if(v.hasVoted()) {
					pw.print("<h3>You have Already Voted</h3><br>");
					pw.print("<h3>Logging You Off.......</h3><br>");
					pw.print("<h5><a href='vLogin.html'>LogOut & Go back to  Login Page</a></h5>");
				}
				else {	
					VoterDAOImpl vDao=(VoterDAOImpl)persistentSession.getAttribute("vDao_Instance");
					CandidateDAOImpl cDao=(CandidateDAOImpl)persistentSession.getAttribute("cDao_Instance");
					boolean status1=cDao.updateVotes(Integer.parseInt(request.getParameter("vote")));	
					boolean status2=vDao.updateVotingStatus(v.getvID());
					if( status1 && status2 ) {
						pw.print("<h3>Your vote has been successfully recorded</h3><br>");
						pw.print("<h3>Logging You Off.......</h3><br>");
						pw.print("<h5><a href='vLogin.html'>LogOut & Go back to  Login Page</a></h5>");
					}
					else pw.print("<h3>Something went wrong! Unable to update vote or voting status</h3><br>");
				}
			   persistentSession.invalidate();
			}
			else pw.print("<h3>Unable to keep track of you</h3><br>");
		}catch (Exception e) {
			throw new ServletException("Error in doGet of " + getClass().getName(), e);
		}
		
	}
		
}

