package com.mybatis.mapper;

import java.util.List;

import com.mybatis.pojo.Order;
import com.mybatis.pojo.User;

public interface UserMapper {
	public List<Order> getUserOrders(int id);

	public User getUserById(int id);
}
