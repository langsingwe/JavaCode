package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Search() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>搜索结果</title>");
		out.println("</head>");
		out.println("<body>");
		
		String start = request.getParameter("start");
		if(start == null) {
			start = "1";
		}
		int count = Integer.parseInt(start);
		int begin = 10 * count - 9;
		int end = 10 * count;
		out.println("第" + begin + "到" + end + "	搜索结果<br>");
		out.println("<ul>");
		for(int i=0;i<=10;i++) {
			out.println("<li>搜索结果" + i + "</li>");
		}
		out.println("</ul>");
		for(int i=1;i<10;i++) {
			if(i == count) {
				out.println(i);
				continue;
			}
			out.println("<a href='search?start=" + i + "'>" + i + "</a>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
