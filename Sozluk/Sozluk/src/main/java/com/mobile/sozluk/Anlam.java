package com.mobile.sozluk;

public class Anlam {
	private String sinif = null;
	private String anlam;
	
	public Anlam(String sinif, String anlam){
		this.sinif = sinif;
		this.anlam = anlam;
	}
	
	public Anlam(String anlam){
		this.anlam = anlam;
	}
	
	public String getSinif(){
		return sinif;
	}
	
	public String getAnlam(){
		return anlam;
	}

}
