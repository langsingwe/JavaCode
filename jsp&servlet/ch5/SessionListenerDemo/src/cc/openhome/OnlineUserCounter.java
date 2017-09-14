package cc.openhome;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineUserCounter
 *
 */
@WebListener
public class OnlineUserCounter implements HttpSessionListener {
	private static int counter;
	
	public static int getCounter() {
		return counter;
	}
    /**
     * Default constructor. 
     */
    public OnlineUserCounter() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	OnlineUserCounter.counter++;
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	OnlineUserCounter.counter--;
    }
	
}
