package com.hsp.interfaces;

public class UpperLetter implements ChangeLetter {
	
	private String str;
	@Override
	public String change() {
		// TODO Auto-generated method stub
		//°ÑĞ¡Ğ´×ÖÄ¸->´óĞ´
		return str.toUpperCase();
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

}
