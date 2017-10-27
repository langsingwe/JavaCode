package com.test.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.test.mybatis.models.User;

public interface IUser {
	@Select("select * from user where id=#{id}")
	public User getUserByID(int id);
}
