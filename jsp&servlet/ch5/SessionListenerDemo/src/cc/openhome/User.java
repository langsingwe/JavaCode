package cc.openhome;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class User
 *
 */
@WebListener
public class User implements HttpSessionBindingListener {
	
	private String name;
	private String data;
	public User(String name) {
		this.name = name;
	}

    /**
     * Default constructor. 
     */
    public User() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	this.data = name + "来自数据库的数据...";
    }

	/**
     * @return 
	 * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	
    }
    public String getData() {
		return data;
	}
	public String getName() {
		return name;
	}
	
}
