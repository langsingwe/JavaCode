package com.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.Order;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/orders")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orders = userMapper.getUserOrders(1);
		System.out.println("orders");
		ModelAndView mav = new ModelAndView("user_orders");
		mav.addObject("orders",orders);
		return mav;
	}
	
}
