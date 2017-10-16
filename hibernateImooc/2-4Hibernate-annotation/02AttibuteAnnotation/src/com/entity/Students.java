package com.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity; /*JPA注解*/
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 学生实体类
 */
@Entity
@Table(name = "t_students", schema = "hibernate")
public class Students {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sid;// 学号
	private String sname;// 姓名
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String major;// 专业
	@Embedded
	private Address add;// 地址

	public Students() {
		super();
	}

	public Students(int sid, String sname, String gender, Date birthday, String major, Address add) {
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.add = add;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
