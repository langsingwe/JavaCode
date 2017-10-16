package com.entity;

import javax.persistence.Embeddable;

/*
 * 地址类
 */
@Embeddable  //表示是一个嵌入类，这个类的对象在另一个实体类中充当属性
public class Address {
	private String postCode;// 邮编
	private String address;// 地址
	private String phone;// 电话

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address() {
		super();
	}

	public Address(String postCode, String address, String phone) {
		super();
		this.postCode = postCode;
		this.address = address;
		this.phone = phone;
	}

}
