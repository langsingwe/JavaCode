package cc.wei.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public ModelAndView hello() {
		return null;
	}
	
	@RequestMapping("/hello1")
	public ModelAndView hello1() {
		return new ModelAndView();
	}
	

	@RequestMapping("/hello2")
	public ModelAndView hello2(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
		return new ModelAndView();
	}
}
