package cc.wei.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.wei.vo.User;

@Controller
public class AjaxController {
	@RequestMapping("/checkName")
	public void checkName(String username,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		if("wei".equals(username)) {
			resp.getWriter().print("1");
		}else {
			resp.getWriter().print("0");
		}
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list(){
		List<User> list = new ArrayList<>();
		list.add(new User(1,"张三",25));
		list.add(new User(2,"王五",22));
		list.add(new User(3,"李四",44));
		return list;
	}
}
