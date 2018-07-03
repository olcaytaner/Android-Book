package com.mobile.barkirmaca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;

public class Oyun {

	private ArrayList<Seviye> seviyeler;
	private int seviyeSayisi;

	public Oyun(Context context, int genislik){
		InputStream stream = context.getResources().openRawResource(R.raw.seviyeler);
		Scanner ayirici = new Scanner(stream);
	    int i, satir, sutun;
	    String satirBilgi;
	    seviyeSayisi = Integer.parseInt(ayirici.next());
	    seviyeler = new ArrayList<Seviye>();
	    for (i = 0; i < seviyeSayisi; i++){
		    satir = Integer.parseInt(ayirici.next());
		    sutun = Integer.parseInt(ayirici.next());
	        Seviye seviye = new Seviye(satir, sutun, genislik, i);
	        for (int j = 0; j < satir; j++){
	           satirBilgi = ayirici.next();
	           seviye.satirAyarla(j, satirBilgi);
	        }
	        seviyeler.add(seviye);
	    }
	}

	public Seviye seviye(int pozisyon){
	    return seviyeler.get(pozisyon);
	}

}
