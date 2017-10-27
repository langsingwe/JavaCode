package com.test.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.test.mybatis.models.User;

public interface IUser {
	/**
	 * 获取用户列表数据
	 * 
	 * @return
	 */
//	@Select("select * from user where id=#{id}")

	public List<User> getUserList();

	/**
	 * 插入用户
	 * 
	 * @param user
	 */
	public void insertUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void deleteUser(int userId);

	/**
	 * 查询用户
	 * 
	 * @param i
	 * @return 
	 */
	public User getUser(int id);
}
