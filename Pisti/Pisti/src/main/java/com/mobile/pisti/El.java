package com.mobile.pisti;

public class El {
	private Kart[] kartlar = new Kart[4];
	private boolean[] oynandiMi = new boolean[4];
	
	public El(Kart kart1, Kart kart2, Kart kart3, Kart kart4){
		kartlar[0] = kart1;
		kartlar[1] = kart2;
		kartlar[2] = kart3;
		kartlar[3] = kart4;
	}
	
	public Kart oyna(int pozisyon){
		oynandiMi[pozisyon] = true;
		return kartlar[pozisyon];
	}
	
	public Kart kartGetir(int pozisyon){
		return kartlar[pozisyon];
	}
	
	public boolean oynandiMi(int pozisyon){
		return oynandiMi[pozisyon];
	}

}
