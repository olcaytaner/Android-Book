package com.mobile.piramit;

public class Bulmaca {
	private int buyukluk;
	private int[][] sayilar;
	private int[] oynama;
	
	public Bulmaca(String bulmacaBilgisi){
		int k = 0;
		buyukluk = (int) Math.sqrt(2 * bulmacaBilgisi.length());
		oynama = new int[buyukluk];
		sayilar = new int[buyukluk][];
		for (int i = 0; i < buyukluk; i++){
			oynama[i] = -1;
			sayilar[i] = new int[i + 1];
			for (int j = 0; j <= i; j++){
				sayilar[i][j] = bulmacaBilgisi.charAt(k) - 48;
				k++;
			}
		}		
	}
	
	public int sayi(int satir, int sutun){
		return sayilar[satir][sutun];
	}
	
	public int oynananDeger(int satir){
		return oynama[satir];
	}
	
	public void oyna(int satir, int deger){
		oynama[satir] = deger;
	}
	
	public int getBuyukluk(){
		return buyukluk;
	}
}
