package com.mobile.barkirmaca;

import android.graphics.Rect;

public class Tugla {

	private TuglaTip tip;
	private Rect yer;
	private boolean kirildi;
	private int vurmaSayisi;
	
	public Tugla(TuglaTip tip, Rect yer){
        this.tip = tip;
        kirildi = false;
        this.yer = yer;
        this.vurmaSayisi = 0;
	}

	public void vuruldu(){
	    vurmaSayisi++;
	    if (tip == TuglaTip.ZOR){
	        if (vurmaSayisi == 2){
	            kirildi = true;
	        }
	    } else {
	        kirildi = true;
	    }
	}
	
	public boolean kirildiMi(){
		return kirildi;
	}
	
	public Rect yer(){
		return yer;
	}
	
	public TuglaTip tip(){
		return tip;
	}

}
