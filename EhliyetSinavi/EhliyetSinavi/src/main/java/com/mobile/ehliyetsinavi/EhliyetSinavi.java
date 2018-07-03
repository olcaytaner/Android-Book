package com.mobile.ehliyetsinavi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class EhliyetSinavi extends Activity {
	static final int YENIDEN_BASLAT = 1;
	private TextView soru;
	private Button cevap1;
	private Button cevap2;
	private Button cevap3;
	private Button cevap4;
	private ArrayList<Soru> sorular;
	private Tercihler tercihler;
	private int trafikSoruSayisi;
	private int motorSoruSayisi;
	private int ilkYardimSoruSayisi;
	private int dogruCevapSayisi;
	private int soruNo;
	private int dogruCevap;

	private void tercihleriOku(){
		SharedPreferences temelTercihler = getSharedPreferences("EhliyetSinavi", MODE_PRIVATE);
		tercihler = new Tercihler(temelTercihler.getBoolean("trafikVarMi", true), temelTercihler.getBoolean("motorVarMi", true),
				temelTercihler.getBoolean("ilkYardimVarMi", true), temelTercihler.getInt("trafikSoruSayisi", 10), 
				temelTercihler.getInt("motorSoruSayisi", 10), temelTercihler.getInt("ilkYardimSoruSayisi", 10));
	}
	
	private void soruDosyasiOku(){
		InputStream stream = getResources().openRawResource(R.raw.ehliyet);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-16")));
		Soru yeniSoru;
		String soru = null;
		sorular = new ArrayList<Soru>();
	    try {
	        while ((soru = reader.readLine()) != null) {
	        	String[] soruIcerik = soru.split(";");
	        	if ((soruIcerik[0].equalsIgnoreCase("T") || soruIcerik[0].equalsIgnoreCase("M") || soruIcerik[0].equalsIgnoreCase("Y")) 
	        		&& (soruIcerik[6].equalsIgnoreCase("A") || soruIcerik[6].equalsIgnoreCase("B") || soruIcerik[6].equalsIgnoreCase("C") || soruIcerik[6].equalsIgnoreCase("D"))){
		        	yeniSoru = new Soru(soruIcerik[0], soruIcerik[1], soruIcerik[2], soruIcerik[3], soruIcerik[4], soruIcerik[5], soruIcerik[6]);
		        	sorular.add(yeniSoru);	        		
	        	}
	        }
	    } catch (IOException e) {
	    }
	}
	
	private Soru simdikiSoruyuBul(){
		int hangiSoru, i;
		String soruTipi;
		if (soruNo < trafikSoruSayisi){
			hangiSoru = soruNo;
			soruTipi = "T";
		} else {
			if (soruNo < trafikSoruSayisi + motorSoruSayisi){
				hangiSoru = soruNo - trafikSoruSayisi;
				soruTipi = "M";
			} else {
				hangiSoru = soruNo - trafikSoruSayisi - motorSoruSayisi;
				soruTipi = "Y";
			}
		}
		i = 0;
		for (Soru soru:sorular){
			if (soru.getSoruTipi().equalsIgnoreCase(soruTipi)){
				if (i == hangiSoru){
					return soru;
				} else {
					i++;
				}
			}
		}
		return null;
	}
	
	private void simdikiSoruyuGoster(){
		Soru simdikiSoru;
		simdikiSoru = simdikiSoruyuBul();
		if (simdikiSoru != null){
			soru.setText((soruNo + 1) + ") " + simdikiSoru.getSoruCumle());
			cevap1.setText("A) " + simdikiSoru.getCevap1());
			cevap2.setText("B) " + simdikiSoru.getCevap2());
			cevap3.setText("C) " + simdikiSoru.getCevap3());
			cevap4.setText("D) " + simdikiSoru.getCevap4());
			if (simdikiSoru.getDogruCevap().equalsIgnoreCase("A")){
				dogruCevap = 1;
			} else {
				if (simdikiSoru.getDogruCevap().equalsIgnoreCase("B")){
					dogruCevap = 2;
				} else {
					if (simdikiSoru.getDogruCevap().equalsIgnoreCase("C")){
						dogruCevap = 3;
					} else {
						dogruCevap = 4;
					}
				}
			}
		}
	}
	
	private void sinaviBaslat(){
		if (!tercihler.trafikVarMi())
			trafikSoruSayisi = 0;
		else
			trafikSoruSayisi = tercihler.getTrafikSoruSayisi();
		if (!tercihler.motorVarMi())
			motorSoruSayisi = 0;
		else
			motorSoruSayisi = tercihler.getMotorSoruSayisi();
		if (!tercihler.ilkYardimVarMi())
			ilkYardimSoruSayisi = 0;
		else
			ilkYardimSoruSayisi = tercihler.getIlkYardimSoruSayisi();
		soruNo = 0;
		dogruCevapSayisi = 0;
		Collections.shuffle(sorular);
		simdikiSoruyuGoster();
		cevap1.setEnabled(true);
		cevap2.setEnabled(true);
		cevap3.setEnabled(true);
		cevap4.setEnabled(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
		soru = (TextView) findViewById(R.id.soru);
		soru.setEnabled(false);
		cevap1 = (Button) findViewById(R.id.cevap1);
		cevap2 = (Button) findViewById(R.id.cevap2);
		cevap3 = (Button) findViewById(R.id.cevap3);
		cevap4 = (Button) findViewById(R.id.cevap4);
		Button konular = (Button) findViewById(R.id.konular);
		Button sinaviBaslat = (Button) findViewById(R.id.sinaviBaslat);
		cevap1.setOnClickListener(cevapTikla);
		cevap2.setOnClickListener(cevapTikla);
		cevap3.setOnClickListener(cevapTikla);
		cevap4.setOnClickListener(cevapTikla);
		konular.setOnClickListener(konularTikla);
		sinaviBaslat.setOnClickListener(sinaviBaslatTikla);
		soruDosyasiOku();
		tercihleriOku();
		sinaviBaslat();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == YENIDEN_BASLAT) {
	        if (resultCode == RESULT_OK) {
	    		tercihleriOku();
	        	sinaviBaslat();
	        }
	    }
	}	
	public OnClickListener cevapTikla = new OnClickListener() {
		public void onClick(View v){
			if (((String)v.getTag()).equalsIgnoreCase(Integer.toString(dogruCevap))){
				dogruCevapSayisi++;
			}
			soruNo++;
			if (soruNo < trafikSoruSayisi + motorSoruSayisi + ilkYardimSoruSayisi){
				simdikiSoruyuGoster();
			} else {
				String mesaj = dogruCevapSayisi + " soruyu dogru cevapladiniz!";
		        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
		        cevap1.setEnabled(false);
		        cevap2.setEnabled(false);
		        cevap3.setEnabled(false);
		        cevap4.setEnabled(false);
			}
		}
	};

	public OnClickListener konularTikla = new OnClickListener() {
		public void onClick(View v){
			Intent konularIntent = new Intent(EhliyetSinavi.this, Konular.class);
			konularIntent.putExtra("tercihler", tercihler);
			startActivityForResult(konularIntent, YENIDEN_BASLAT);
		}
	};

	public OnClickListener sinaviBaslatTikla = new OnClickListener() {
		public void onClick(View v){
			sinaviBaslat();
		}
	};

}
