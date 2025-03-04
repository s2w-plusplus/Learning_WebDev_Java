package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import utils.HibernateUtils;

/**
 * Application Lifecycle Listener implementation class SessionFactoryMgr
 *
 */
public class SessionFactoryMgr implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SessionFactoryMgr() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("in ctx destroyed");
        HibernateUtils.getSf().close();//cn pool cleanup !
   
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("ctx initiated");
        //create singleton , thrd safe SF instance
   	HibernateUtils.getSf();
    }
	
}
