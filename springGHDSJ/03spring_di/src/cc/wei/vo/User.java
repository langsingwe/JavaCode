package cc.wei.vo;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class User {
	
	private int age;
	private String name;
	private Role role;
	private String[] hobbies;
	private Map<String,String> banks;
	private Properties body;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public Map<String, String> getBanks() {
		return banks;
	}
	public void setBanks(Map<String, String> banks) {
		this.banks = banks;
	}
	public Properties getBody() {
		return body;
	}
	public void setBody(Properties body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", role=" + role + ", hobbies=" + Arrays.toString(hobbies)
				+ ", banks=" + banks + ", body=" + body + "]";
	}
	
}
