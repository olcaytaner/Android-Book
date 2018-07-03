package com.mobile.barkirmaca;

import android.graphics.Rect;

public class Cubuk {
	private Rect yer;
	
	public Cubuk(int ekranGenislik, int ekranYukseklik){
		yer = new Rect((int) (0.425 * ekranGenislik), (int) (ekranYukseklik - 0.175 * ekranGenislik), (int) (0.575 * ekranGenislik), (int) (ekranYukseklik - 0.125 * ekranGenislik));
	}

	public void buyult(){
	    yer.inset(-(int)(yer.width() * 0.625), 0);
	}

	public void kucult(){
	    yer.inset((int)(yer.width() * 0.1), 0);
	}

	public void yeniPozisyon(int x){
	    yer.offsetTo(x, yer.top);
	}
	
	public Rect yer(){
		return yer;
	}

}
