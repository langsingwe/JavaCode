package com.java.string.stringBuffer;

public class StringBufferDemo {
	public static void main(String[] args) {
//		method_delete();
		method_update();
	}
	
	//修改方法
	public static void method_update() {
		StringBuffer sb = new StringBuffer("abced");
		
//		sb.replace(1, 4, "java");
		sb.setCharAt(2, 'k');
		sop(sb.toString());
	}
	
	//删除方法
	public static void method_delete() {
		StringBuffer sb = new StringBuffer("abcde");
//		sb.delete(1, 3);
		//清空缓冲区
//		sb.delete(0, sb.length());
		//删除指定字符
		sb.deleteCharAt(2);
		sop(sb.toString());
	}
	//添加方法
	public static void method_add() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("abc").append(true).append(34);
//		sop(sb.toString());
//		sop(sb1.toString());
		sop(sb.toString());
	}
	public static void sop(String str) {
		System.out.println(str);
	}
}	
