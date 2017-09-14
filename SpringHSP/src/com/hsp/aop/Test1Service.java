package com.hsp.aop;

public class Test1Service implements TestServiceInter,TestServiceInter2 {

	private String name;
	
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Hi " + name);
		int i = 9 / 0;
	}
	public void sayBye() {
		System.out.println("bye " + name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
