package cc.openhome;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloView
 */
@WebServlet("/hello.view")
public class HelloView extends HttpServlet {
	private String htmlTemplate = 
			"<html>"
		+	" <head>"
		+   "  <meta http-equiv='Content-Type'"
		+   "    content='text/html; charset=UTF-8'>"
		+   "<title>%s</title>"
		+   " </head>"
		+	"<body>"
		+	" <h1>%s</h1>"
		+	"</body>"
		+	"</html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String user = req.getParameter("user");
		String message = (String) req.getAttribute("message");
		String html = String.format(htmlTemplate, user, message);
		resp.getWriter().print(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
