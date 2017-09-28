package com.spring.ioc.interfaces;

public class App {
	public static void main(String[] args) {
		OneInterface oif = new OneInterfaceImpl();
		System.out.println(oif.hello("world"));
	}
}
