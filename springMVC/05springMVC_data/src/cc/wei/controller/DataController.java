package cc.wei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DataController {
	@RequestMapping("/mv")
	public ModelAndView mv() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("data");
		//通过ModelAndView来添加回显数据 添加的是一条key-value数据
		mv.addObject("name", "张三");
		//添加一组key-value数据
		Map<String, Object> map = new HashMap<>();
		map.put("age",22);
		map.put("sex","女");
		mv.addAllObjects(map);
		return mv;
	}
	
	@RequestMapping("/mm")
	public ModelAndView mm(ModelMap modelMap) {
		//添加一条key-value数据
		modelMap.addAttribute("name", "王二");
		//添加一组key-value数据
		Map<String, Object> map = new HashMap<>();
		map.put("age",22);
		map.put("sex","女");
		//添加一组key-value数据
		modelMap.addAllAttributes(map);
		return new ModelAndView("data");
	}
	
	@RequestMapping("/api")
	public ModelAndView api(HttpServletRequest req,HttpServletResponse resp,HttpSession session) {
		req.setAttribute("name", "大黄");
		session.setAttribute("username", "小明");
		return new ModelAndView("data");
	}
}

