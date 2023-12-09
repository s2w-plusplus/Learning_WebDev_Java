package listener;

import static utils.DButils.*;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Listen2connectDB implements ServletContextListener {

    public Listen2connectDB() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("in contextDestroyed()....");
		try {
			closeConnection();
		} catch (SQLException e) {
			System.out.println("Error in ctx destroyed "+e);
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("inside contextInitialized()");
         ServletContext svctx=sce.getServletContext();
         try{
        	 openConnection(svctx.getInitParameter("driver"),svctx.getInitParameter("DB_url"),
        			 svctx.getInitParameter("Username"),svctx.getInitParameter("Password"));
         } catch (Exception e) {
 			System.out.println("err in ctx-init " + e);
 		 }    
    }
	
}
