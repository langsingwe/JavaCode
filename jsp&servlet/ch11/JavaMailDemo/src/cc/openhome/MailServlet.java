package cc.openhome;

import java.io.*;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailServlet
 */
@WebServlet(
			urlPatterns= {"/mail.do"},
			initParams= {
					@WebInitParam(name = "mailHost", value="smtp.gmail.com"),
					@WebInitParam(name = "mailPort", value="465"),
					@WebInitParam(name = "username", value="your_username"),
					@WebInitParam(name = "password", value="your_password"),
			}
		)
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String mailHost;
	private String mailPort;
	private String username;
	private String password;
	private Properties props;
	
	@Override
	public void init() throws ServletException{
		mailHost = getServletConfig().getInitParameter("mailHost");
		mailPort = getServletConfig().getInitParameter("mailPort");
		username = getServletConfig().getInitParameter("username");
		password = getServletConfig().getInitParameter("password");
		
		props = new Properties();
		props.put("mail.smtp.host", mailHost);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", mailPort);
		props.setProperty("mail.smtp.socketFactory.port", mailPort);
		props.setProperty("mail.smtp.auth", "true");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		try {
			Message message = getMessage(from,to,subject,text);
			Transport.send(message);
			response.getWriter().println("邮件发送成功");
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	private Message getMessage(String from, String to, String subject, String text) throws MessagingException, AddressException {
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(text);
		return message;
	}

}











