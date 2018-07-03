package com.mobile.sozluk;

import java.util.ArrayList;

public class SozlukKelime extends Kelime{
	private String sinif;
	private String koken;
	private ArrayList<Anlam> anlamlar;
	
	public SozlukKelime(String ad){
		super(ad);
		anlamlar = new ArrayList<Anlam>();		
	}
	
	public void setSinif(String sinif){
		this.sinif = sinif;
	}
	
	public void setKoken(String koken){
		this.koken = koken;
	}
	
	public String getSinif(){
		return sinif;
	}
	
	public String getKoken(){
		return koken;
	}
		
	public void anlamEkle(Anlam anlam){
		anlamlar.add(anlam);
	}
	
	public int anlamSayisi(){
		return anlamlar.size();
	}
	
	public Anlam anlam(int pozisyon){
		return anlamlar.get(pozisyon);
	}

}
