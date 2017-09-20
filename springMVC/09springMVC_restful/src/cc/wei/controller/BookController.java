package cc.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.wei.vo.Book;

@Controller
public class BookController {
	@RequestMapping("/add")
	public String add(Book book) {
		return "";
	}
}
