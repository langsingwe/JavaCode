package com.spring.ioc.interfaces;

public class OneInterfaceImpl implements OneInterface {
	public String hello(String str) {
		return "word from interface OneInterface:" + str;
	}
}
