package cc.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.wei.vo.Cart;
import cc.wei.vo.Goods;
import cc.wei.vo.Student;

@Controller
public class CollectionController {
	@RequestMapping("/arr")
	public ModelAndView arr(String[] hobbies) {
		for(String str:hobbies) {
			System.out.println(str);
		}
		return null;
	}
	
	@RequestMapping("/arr1")
	public ModelAndView arr(Student student) {
		for(String str:student.getHobbies()) {
			System.out.println(str);
		}
		return null;
	}
	
	@RequestMapping("/arr2")
	public ModelAndView arr2(Cart cart) {
		for(Goods goods:cart.getList()) {
			System.out.println(goods.getName()+"\t"+goods.getPrice()+"\t"+goods.getNum());
		}
		return null;
	}
}
