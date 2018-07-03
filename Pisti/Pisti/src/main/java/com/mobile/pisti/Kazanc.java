package com.mobile.pisti;

import java.util.ArrayList;

public class Kazanc {
	private ArrayList<Kart> normalKartlar;
	private ArrayList<Kart> pistiKartlar;
	
	public Kazanc(){
		normalKartlar = new ArrayList<Kart>();
		pistiKartlar = new ArrayList<Kart>();
	}
	
	public void normalEkle(Kart kart){
		normalKartlar.add(kart);
	}
	
	public void pistiEkle(Kart kart){
		pistiKartlar.add(kart);
	}
	
	public int kartSayisi(){
		return normalKartlar.size() + pistiKartlar.size() * 2;
	}
	
	public int puan(){
		int puan = 0;
		for (Kart kart: pistiKartlar){
			if (kart.getDeger() == 11){
				puan += 20;
			} else {
				puan += 10;
			}
		}
		for (Kart kart: normalKartlar){
			if (kart.getDeger() == 1 || kart.getDeger() == 11){
				puan++;
			} else {
				if (kart.getDeger() == 2 && kart.getTip().equalsIgnoreCase("sinek")){
					puan += 2;
				} else {
					if (kart.getDeger() == 2 && kart.getTip().equalsIgnoreCase("karo")){
						puan += 3;
					}
				}
			}
		}
		return puan;
	}

}
