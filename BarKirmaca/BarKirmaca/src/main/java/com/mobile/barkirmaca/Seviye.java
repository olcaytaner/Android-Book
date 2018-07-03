package com.mobile.barkirmaca;

import android.graphics.Rect;

public class Seviye {

	private Tugla[][] satirlar;
	private int satir;
	private int sutun;
	private int seviyeNo;
	private int tuglaGenislik;
	private int tuglaYukseklik;
	
	public Seviye(int satir, int sutun, int genislik, int seviyeNo){
        this.satir = satir;
        this.sutun = sutun;
        this.seviyeNo = seviyeNo;
        satirlar = new Tugla[satir][sutun];
        tuglaGenislik = genislik / sutun;
        tuglaYukseklik = (int) (0.7 * tuglaGenislik);
	}
	
	public int satir(){
		return satir;
	}
	
	public int sutun(){
		return sutun;
	}
	
	public int seviyeNo(){
		return seviyeNo;
	}

	public boolean bittiMi(){
	    Tugla tugla;
	    for (int i = 0; i < satir; i++){
	        for (int j = 0; j < sutun; j++){
	            tugla = satirlar[i][j];
	            if (!tugla.kirildiMi()){
	                return false;
	            }
	        }
	    }
	    return true;
	}

	public void satirAyarla(int satirNo, String satirBilgi){
	    for (int i = 0; i < satirBilgi.length(); i++){
	        Rect yer;
	        yer = new Rect(i * tuglaGenislik, satirNo * tuglaYukseklik, (i + 1) * tuglaGenislik, (satirNo + 1) * tuglaYukseklik);
	        switch (satirBilgi.charAt(i)){
	            case '1':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.NORMAL, yer);
	                break;
	            case '2':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.ZOR, yer);
	                break;
	            case '3':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.HIZLI, yer);
	                break;
	            case '4':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.YAVAS, yer);
	                break;
	            case '5':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.BUYUK, yer);
	                break;
	            case '6':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.KUCUK, yer);
	                break;
	            case '7':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.YASAM, yer);
	                break;
	            case '8':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.OLUM, yer);
	                break;
	            case '9':
	                satirlar[satirNo][i] = new Tugla(TuglaTip.COKTOP, yer);
	                break;
	        }
	    }
	}

	public Tugla tugla(int satir, int sutun){
	    return satirlar[satir][sutun];
	}


}
