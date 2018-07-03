package com.mobile.pisti;

public class Kart {
	private String tip;
	private int deger;
	
	public Kart(String tip, int deger){
		this.tip = tip;
		this.deger = deger;
	}
	
	public String toString(){
		switch (deger){
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return tip + "_" + deger;
			case 1:
				return tip + "_as";
			case 11:
				return tip + "_vale";
			case 12:
				return tip + "_kiz";
			case 13:
				return tip + "_papaz";
		}
		return null;		
	}
	
	public int getDeger(){
		return deger;
	}
	
	public String getTip(){
		return tip;
	}
	
}
