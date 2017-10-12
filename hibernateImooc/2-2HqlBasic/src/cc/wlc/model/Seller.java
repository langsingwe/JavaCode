package cc.wlc.model;

import java.io.Serializable;

/*
 * 商家信息持久化类
 */
public class Seller implements Serializable {

	private Long id;// 主键
	private String name;// 名称
	private String tel;// 电话
	private String address;// 地址
	private String website;// 网站
	private Integer star;// 星级
	private String business;// 经营范围

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Seller() {
		super();
	}

	public Seller(Long id, String name, String tel, String address, String website, Integer star, String business) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.website = website;
		this.star = star;
		this.business = business;
	}

	@Override
	public String toString() {
		return "Seller [主键=" + id + ", 名称=" + name + ", 电话=" + tel + ", 地址=" + address + ", 网站=" + website
				+ ", 星级=" + star + ", 经营范围=" + business + "]";
	}

}
