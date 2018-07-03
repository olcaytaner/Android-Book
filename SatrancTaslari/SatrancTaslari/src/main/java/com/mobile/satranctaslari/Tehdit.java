package com.mobile.satranctaslari;

public class Tehdit extends Kare {
	
	private int tehditSayisi;
	
	public Tehdit(int x, int y, int tehditSayisi){
		super(x, y);
		this.tehditSayisi = tehditSayisi;
	}
	
	public int getTehditSayisi(){
		return tehditSayisi;
	}

}
