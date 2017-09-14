package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		
		)
public class Login extends HttpServlet {
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	
	public void init() throws ServletException{
		SUCCESS_VIEW = getInitParameter("SUCCESS");
		ERROR_VIEW = getInitParameter("ERROR");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		if("wei".equals(name) && "123456".equals(passwd)) {
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		}else {
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}

}
