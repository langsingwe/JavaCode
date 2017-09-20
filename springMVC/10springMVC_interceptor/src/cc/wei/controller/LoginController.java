package cc.wei.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/login")
	public String login(String username,String password,HttpSession session) {
		if("wei".equals(username)&&"123456".equals(password)) {
			session.setAttribute("username", username);
			return "success";
		}else {
			return "login";
		}
	}
}
