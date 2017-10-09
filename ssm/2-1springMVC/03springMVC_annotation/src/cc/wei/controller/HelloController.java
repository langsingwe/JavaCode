package cc.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * 使用注解开发controller
 * @controller表示该类是一个处理器，容器扫描该类时会产生相应的对象
 */
@Controller
public class HelloController {
	/*
	 * 处理方法
	 * @RequestMapping 注解访问该类的url
	 */
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "spring mvc annotation");
		return mv;
	}
}
