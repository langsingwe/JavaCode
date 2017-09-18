package cc.wei.vo;

public class User {
	
	private int age;
	private String name;
	private Role role;
	
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
	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", role=" + role + "]";
	}
	
	
}
