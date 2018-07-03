package com.mobile.sozluk;

import java.util.ArrayList;

public class KaynakKelime extends Kelime{
	
	private ArrayList<Ceviri> ceviriler;
	
	public KaynakKelime(String ad){
		super(ad);
		ceviriler = new ArrayList<Ceviri>();		
	}
	
	public void ceviriEkle(Ceviri ceviri){
		ceviriler.add(ceviri);
	}
	
	public int ceviriSayisi(){
		return ceviriler.size();
	}
	
	public Ceviri ceviri(int pozisyon){
		return ceviriler.get(pozisyon);
	}


}
