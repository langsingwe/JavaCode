package com.hsp.beanlife;

public class PersonService {

		private String name;
		
		public PersonService() {
			System.out.println("PersonService函数");
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			System.out.println("setName(String name)函数");
			this.name = name;
		}
		
		public void sayHi() {
			System.out.println("hi " + name);
		}
		
		//该方法可以给arg0表示正在被实例化的bean id
		public void setBeanName(String arg0) {
			System.out.println("setBeanName被调用" + arg0);
		}
}
