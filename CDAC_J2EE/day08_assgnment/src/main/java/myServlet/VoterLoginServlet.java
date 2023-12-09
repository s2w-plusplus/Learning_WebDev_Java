package myServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CandidateDAOImpl;
import DAO.VoterDAOImpl;
import POJO.Voter;
import utils.DButils;

@WebServlet(urlPatterns="/authVoter",loadOnStartup =1)
public class VoterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VoterDAOImpl vDao;
    private CandidateDAOImpl cDao;

	public void init() throws ServletException {
		System.out.println("inside overloaded init() of "+ getClass().getName());
		try {
				vDao=new VoterDAOImpl();
				cDao=new CandidateDAOImpl();
		}catch (Exception e) {
			//Overloaded methods can't throw broader or new checked exceptions
			//so  throw the exception to WC , by wrapping it in ServletException instance
			throw new ServletException("Error in init() of"+ getClass().getName(), e);
		}
	}

	public void destroy() {
		System.out.println("in destroy() of " + getClass().getName());
		try {
			vDao.cleanUp();
			cDao.cleanUp();
		} catch (Exception e) {
			// we can't throw broader and new 'checked' exception, but nobody said anything about throwing 'unchecked' exceptions!!!!
			//hence wrapping in runtime exception so the WC will at least know the problem
			throw new RuntimeException("Error in destroy()", e);// understanding purpose!!!!  its pointless because we are destroying anyway
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("in doPost() of " + getClass().getName());
		HttpSession Session = request.getSession();
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			Voter v=vDao.authenticateVoter(request.getParameter("email"), request.getParameter("passwd"));
			if(v!=null) {
				
				//putting attributes into newly gotten session object 
				Session.setAttribute("voter_info", v); //goes into inner map
				Session.setAttribute("vDao_Instance", vDao);
				Session.setAttribute("cDao_Instance", cDao);
				
				if(v.getRole().equals("voter")) {
					if(!v.hasVoted()) {
						System.out.println("The Voter is valid and hasn't voted yet");
						System.out.println("Implementing class for HttpSession i/f : " + Session.getClass().getName());
						System.out.println("Is this HS new? -> " + Session.isNew());
						System.out.println("Unique SessionId created by PRNG: " + Session.getId()); 
						//this ID is used as outer-map key whose Value is {HTTPSession Object}
						
						/*if we were using cookies instead of HTTP Session
						 *Cookie ck=new Cookie("voter_info", v.toString());
						 *response.addCookie(ck);
						 */
						
						// redirect the voter to the next page (CandidateList) in the NEXT request
						response.sendRedirect("cList");// Client Pull 
						// URL : http://host:port/day04_assgnment/cList
					}
					else {
						System.out.println("SessionId created by PRNG: " + Session.getId()); 
						System.out.println("Is this HS new? -> " + Session.isNew());
						// redirect the voter to the next page (LogOutServlet) in the NEXT request
						response.sendRedirect("status");// Client Pull 	
					}
				}
				else {
					System.out.println("Welcome Admin!");
					// redirect the voter to the next page (AdminServlet) in the NEXT request
					response.sendRedirect("admin_page.jsp");// Client Pull 	
				}
			}
			else {
					pw.print("<h4>Invalid Login </h4>");
					pw.print("<h5><a href='vLogin.html'>Go back to  Login Page</a></h5>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
