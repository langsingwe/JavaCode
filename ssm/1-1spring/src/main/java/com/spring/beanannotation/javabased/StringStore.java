package com.spring.beanannotation.javabased;

public class StringStore implements Store {
	public void init() {
		System.out.println("this is init");
	}
	public void destroy() {
		System.out.println("this is destroy");
	}
}
