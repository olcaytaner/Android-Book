package com.mobile.ehliyetsinavi;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tercihler implements Serializable{
	private boolean trafikVarMi;
	private boolean motorVarMi;
	private boolean ilkYardimVarMi;
	private int trafikSoruSayisi;
	private int motorSoruSayisi;
	private int ilkYardimSoruSayisi;
	
	public Tercihler(boolean trafikVarMi, boolean motorVarMi, boolean ilkYardimVarMi, int trafikSoruSayisi, int motorSoruSayisi, int ilkYardimSoruSayisi){
		this.trafikVarMi = trafikVarMi;
		this.motorVarMi = motorVarMi;
		this.ilkYardimVarMi = ilkYardimVarMi;
		this.trafikSoruSayisi = trafikSoruSayisi;
		this.motorSoruSayisi = motorSoruSayisi;
		this.ilkYardimSoruSayisi = ilkYardimSoruSayisi;
	}
	
	public boolean trafikVarMi(){
		return trafikVarMi;
	}
	
	public boolean motorVarMi(){
		return motorVarMi;
	}
	
	public boolean ilkYardimVarMi(){
		return ilkYardimVarMi;
	}
	
	public int getTrafikSoruSayisi(){
		return trafikSoruSayisi;
	}
	
	public int getMotorSoruSayisi(){
		return motorSoruSayisi;
	}
	
	public int getIlkYardimSoruSayisi(){
		return ilkYardimSoruSayisi;
	}
	
}
