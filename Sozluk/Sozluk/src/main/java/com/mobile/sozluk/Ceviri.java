package com.mobile.sozluk;

public class Ceviri {
	private String sinif = null;
	private Anlam anlam;
	
	public Ceviri(String sinif, Anlam anlam){
		this.sinif = sinif;
		this.anlam = anlam;
	}
	
	public Ceviri(Anlam anlam){
		this.anlam = anlam;
	}
	
	public String getSinif(){
		return sinif;
	}
	
	public Anlam getAnlam(){
		return anlam;
	}

}
