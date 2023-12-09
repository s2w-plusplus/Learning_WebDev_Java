package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CandidateDAOImpl;
import DAO.VoterDAOImpl;
import POJO.Candidate;
import POJO.Voter;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doGet() of " + getClass().getName());
		HttpSession persistentSession = request.getSession();
		System.out.println("SessionId : " + persistentSession.getId()); 
		System.out.println("Is this HS new? -> " + persistentSession.isNew());
		
		VoterDAOImpl vDao=(VoterDAOImpl)persistentSession.getAttribute("vDao_Instance");
		CandidateDAOImpl cDao=(CandidateDAOImpl)persistentSession.getAttribute("cDao_Instance");
		Voter v=(Voter)persistentSession.getAttribute("voter_info");
		
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			LinkedHashMap<String,Integer> cmap=cDao.partywiseAnalysis();
			List<Candidate> clist=cDao.top2Analysis();
			
			pw.print("<h3>Hello Admin, "+v.getName()+"</h3>");
			pw.print("<br><hr><h2>Top 2 Candidates in current poll</h2><hr>");
			pw.print("<center><table>");
			clist.forEach((c)->{
				pw.print("<tr><td>"+c.getName().toUpperCase()+
						"</td><td>"+c.getParty().toUpperCase()+"</td><td>"+c.getVotes()+"</td></tr>");
			});
			pw.print("</table></center><hr>");
			
			
			pw.print("<br><br><br><hr><h2>Partywise Analysis</h2><hr>");
			pw.print("<center><table>");
			for( Map.Entry<String,Integer> entry: cmap.entrySet() ) 
				pw.print("<tr><td>"+entry.getKey().toUpperCase()+"</td><td>"+entry.getValue()+"</td></tr>");
			pw.print("<table></center><hr>");
			
		}catch(Exception e) {
			throw new ServletException("Error in doGet of "+ getClass().getName()+" caused by ", e);
		}
		persistentSession.invalidate();
	}

}
