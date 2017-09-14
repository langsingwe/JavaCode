package cc.wei.hibernate.day1.service;

import java.util.List;

import cc.wei.hibernate.day1.domain.User;

public interface IUserDao {
	
	void save(User u);
	
	void delete(Long id);
	
	void update(User u);
	
	User get(Long id);
	
	List<User> getAllUsers();
	
	List<User> findUsersByNames(String name);
	
}
