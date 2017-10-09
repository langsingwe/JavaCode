package cc.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.wei.vo.User;

@Controller
public class LoginController {
	@RequestMapping("toLogin")
	public ModelAndView toLogin() {
		return new ModelAndView("login");
	}
	@RequestMapping("/login3")
	public ModelAndView login3(User user) {
		System.out.println("roleName="+user.getRole().getName());
		ModelAndView mv = new ModelAndView("success");
		if("wei".equals(user.getName())&&"123456".equals(user.getPwd())) {
			mv.setViewName("success");
			mv.addObject("msg","登陆成功！");
		}else {
			mv.setViewName("login");
		}
			return mv;
	}
}
