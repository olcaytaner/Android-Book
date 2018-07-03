package com.mobile.barkirmaca;

import android.graphics.Point;

public class Top {
	
	private Point merkez;
	private Point hiz;
	private int yariCap;

	public Top(int cubukX, int cubukY, int ekranGenislik){
	    hiz = new Point((int)(-ekranGenislik / 300.0), (int)(-ekranGenislik / 300.0));
	    yariCap = ekranGenislik / 50;
	    merkez = new Point(cubukX + yariCap, cubukY - yariCap);
	}

	public void hareketEttir(){
	    merkez.offset(hiz.x, hiz.y);
	}
	
	public Point merkez(){
		return merkez;
	}
	
	public int yariCap(){
		return yariCap;
	}

	public boolean sinirlarlaTemas(int ekranGenislik, int ekranYukseklik){
	    if (merkez.x < 0 || merkez.x > ekranGenislik){
	        hiz.x *= -1;
	    }
	    if (merkez.y < 0){
	        hiz.y *= -1;
	    }
	    if (merkez.y > ekranYukseklik){
	        return true;
	    } else {
	        return false;
	    }
	}

	public boolean tuglaylaTemas(Tugla tugla){
	    if (merkez.x + yariCap > tugla.yer().left && merkez.x - yariCap < tugla.yer().right
	        && merkez.y + yariCap > tugla.yer().top && merkez.y - yariCap < tugla.yer().bottom){
	        hiz.y *= -1;
	        return true;
	    } else {
	        return false;
	    }
	}

	public boolean cubuklaTemas(Cubuk cubuk){
	    if (merkez.x + yariCap > cubuk.yer().left && merkez.x - yariCap < cubuk.yer().right
		        && merkez.y + yariCap > cubuk.yer().top && merkez.y - yariCap < cubuk.yer().bottom){
		        hiz.y *= -1;
	        return true;
	    } else {
	        return false;
	    }
	}

	public void hizlandir(){
	    hiz.x *= 1.25;
	    hiz.y *= 1.25;
	}

	public void yavaslat(){
	    hiz.x *= 0.8;
	    hiz.y *= 0.8;
	}

}
