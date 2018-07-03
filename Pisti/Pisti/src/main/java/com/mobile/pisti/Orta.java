package com.mobile.pisti;

import java.util.ArrayList;

public class Orta {
	private ArrayList<Kart> kartlar;
	
	public Orta(){
		kartlar = new ArrayList<Kart>();
	}
	
	public Kart ustKart(){
		if (kartlar.isEmpty()){
			return null;
		}
		return kartlar.get(kartlar.size() - 1);
	}
	
	public void kartEkle(Kart kart){
		kartlar.add(kart);
	}
	
	public int kartSayisi(){
		return kartlar.size();
	}
	
	public boolean kazancVarMi(){
		Kart ustKart;
		Kart altKart;
		if (kartlar.size() >= 2){
			ustKart = kartlar.get(kartlar.size() - 1);
			altKart = kartlar.get(kartlar.size() - 2);
			if (ustKart.getDeger() == altKart.getDeger() || ustKart.getDeger() == 11){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void kazancaGonder(Kazanc kazanc){
        Kart ustKart;
        Kart altKart;
        ustKart = kartlar.get(kartlar.size() - 1);
        altKart = kartlar.get(kartlar.size() - 2);
		if (kartlar.size() == 2 && ustKart.getDeger() == altKart.getDeger()){
			kazanc.pistiEkle(ustKart());
		} else {
			for (Kart kart:kartlar){
				kazanc.normalEkle(kart);
			}
		}
		kartlar.clear();
	}

}
