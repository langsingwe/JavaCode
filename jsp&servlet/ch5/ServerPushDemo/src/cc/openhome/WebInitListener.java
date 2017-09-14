package cc.openhome;

import javax.servlet.*;
import java.util.*;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class WebInitListener
 *
 */
@WebListener()
public class WebInitListener implements ServletContextListener {
	private List<AsyncContext> asyncs = new ArrayList<AsyncContext>();
    /**
     * Default constructor. 
     */
    public WebInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	sce.getServletContext().setAttribute("asyncs", asyncs);
    	new Thread(new Runnable() {
    		public void run() {
    			while(true) {
    				try {
    					Thread.sleep((int)(Math.random() * 10000));
    					double num = Math.random() * 10;
    					synchronized(asyncs) {
    						for(AsyncContext ctx : asyncs) {
    							ctx.getResponse().getWriter().println(num);
    							ctx.complete();
    						}
    						asyncs.clear();
    					}
    				}catch(Exception e){
    					throw new RuntimeException(e);
    				}
    			}
    		}
    	}).start();
    }
	
}
