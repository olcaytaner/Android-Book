package com.mobile.pisti;

import java.util.ArrayList;
import java.util.Collections;

public class Deste {
	private ArrayList<Kart> kartlar;
	
	public Deste(){
		kartlariDagit();
		Collections.shuffle(kartlar);
	}
	
	private void kartlariDagit(){
		String tip;
		Kart kart;
		kartlar = new ArrayList<Kart>();
		for (int i = 0; i < 4; i++){
			switch (i){
				case 0:
					tip = "karo";
					break;
				case 1:
					tip = "kupa";
					break;
				case 2:
					tip = "sinek";
					break;
				case 3:
					tip = "maca";
					break;
				default:
					tip = "";
					break;
			}
			for (int j = 1; j <= 13; j++){
				kart = new Kart(tip, j);
				kartlar.add(kart);
			}
		}
	}
	
	public El elDagit(){
		El el;
		el = new El(kartlar.get(kartlar.size() - 1), kartlar.get(kartlar.size() - 2), kartlar.get(kartlar.size() - 3), kartlar.get(kartlar.size() - 4));
		for (int i = 0; i < 4; i++){
			kartlar.remove(kartlar.size() - 1);
		}
		return el;
	}
	
	public void ortaDagit(Orta orta){
		for (int i = 0; i < 4; i++){
			orta.kartEkle(kartlar.get(kartlar.size() - 1));
			kartlar.remove(kartlar.size() - 1);
		}
	}

}
