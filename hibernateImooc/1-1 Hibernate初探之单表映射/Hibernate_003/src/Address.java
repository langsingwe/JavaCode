/*
 * 地址类
 */
public class Address {
	
	//邮编
	private String postcode;
	//电话
	private String phone;
	//地址
	private String address;
	
	public Address() {}

	public Address(String postcode, String phone, String address) {
		super();
		this.postcode = postcode;
		this.phone = phone;
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
