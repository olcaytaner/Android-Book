package com.mobile.barkirmaca;

import android.graphics.Point;
import android.graphics.Rect;

public class DusenTugla {

	private TuglaTip tip;
	private Rect yer;
	private Point hiz;

	public DusenTugla(TuglaTip tip, Rect yer, int yukseklik){
        this.tip = tip;
        this.yer = yer;
        hiz = new Point(0, (int) (yukseklik / 500.0));
	}

	public void hareketEttir(){
	    yer.offset(hiz.x, hiz.y);
	}

	public boolean cubuklaTemas(Cubuk cubuk){
	    if (Rect.intersects(yer, cubuk.yer())){
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public TuglaTip tip(){
		return tip;
	}
	
	public Rect yer(){
		return yer;
	}

}
