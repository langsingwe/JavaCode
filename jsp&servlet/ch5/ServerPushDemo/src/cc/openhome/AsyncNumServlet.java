package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class AsyncNumServlet
 */
@WebServlet(name="AsyncNumServlet",urlPatterns= {"/asyncNum.do"},asyncSupported = true)
public class AsyncNumServlet extends HttpServlet {
	private List<AsyncContext> asyncs;
	
	@Override
	public void init() throws ServletException{
		asyncs = (List<AsyncContext> getServletContext().getAttribute("asyncs"));
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		AsyncContext  ctx = request.startAsync();
		synchronized(asyncs) {
			asyncs.add(ctx);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
