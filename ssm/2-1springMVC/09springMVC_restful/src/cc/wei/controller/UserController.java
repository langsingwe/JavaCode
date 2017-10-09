package cc.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.wei.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	//请求add方法是 url = /user/add
	@RequestMapping("/add")
	public String add(User user) {
		System.out.println("-- add --");
		return "add";
	}
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id")int id) {
		System.out.println("id====="+id);
		return "delete";
	}
}
