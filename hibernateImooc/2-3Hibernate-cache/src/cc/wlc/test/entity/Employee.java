package cc.wlc.test.entity;

public class Employee {
	private Integer empid;
	private String name;
	private Integer age;

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Employee() {
		super();
	}

	public Employee(Integer empid, String name, Integer age) {
		super();
		this.empid = empid;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", age=" + age + "]";
	}

}
