package com.mobile.pisti;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

public class Pisti extends Activity {

	private ImageView[] kartlar = new ImageView[4];
	private ImageView[] bilgisayarKartlar = new ImageView[4];
	private ImageView[] kapaliKartlar = new ImageView[13];
	private ImageView ortaKart;
	private Orta orta;
	private Deste deste;
	private El insan, bilgisayar;
	private Kazanc insanKazanc, bilgisayarKazanc;
	private int tur, hamle;
	private ImageView insanAnimasyonKart, bilgisayarAnimasyonKart;
	private int insanAnimasyonKartX, insanAnimasyonKartY;
	private int bilgisayarAnimasyonKartX, bilgisayarAnimasyonKartY;

	private void ortayiGoster(){
		for (int i = 0; i < 13; i++){
			String tag = (String) kapaliKartlar[i].getTag();
			if (Integer.parseInt(tag) > orta.kartSayisi() + 7){
				kapaliKartlar[i].setVisibility(View.INVISIBLE);
			} else {
				kapaliKartlar[i].setVisibility(View.VISIBLE);				
			}
		}
		ortaKart.bringToFront();
	}
		
	private void oyna(El el, Kazanc kazanc, int aKart){
		Kart kart = el.oyna(aKart);
		orta.kartEkle(kart);
		if (orta.kazancVarMi()){
			orta.kazancaGonder(kazanc);
			ortayiGoster();
			ortaKart.setVisibility(View.INVISIBLE);
		} else {
			ortayiGoster();
			kartGoster(orta.ustKart(), ortaKart);
		}
	}
	
	private void oyunBitti(){
		int bilgisayarPuan = bilgisayarKazanc.puan();
		int insanPuan = insanKazanc.puan();
		if (bilgisayarKazanc.kartSayisi() > insanKazanc.kartSayisi()){
			bilgisayarPuan += 3;
		} else {
			insanPuan += 3;
		}
		String mesaj = "Bilgisayar:" + bilgisayarPuan + " Insan:" + insanPuan;
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
	}
	
	private void oyunuDevamEttir(){
		hamle++;
		if (hamle == 4){
			hamle = 0;
			tur++;
			if (tur < 6){
				turaBasla();
				ortayiGoster();
			} else {
				oyunBitti();
			}
		}
	}

	private int ayniKagitVarMi(){
		for (int i = 0; i < 4; i++){
			if (!bilgisayar.oynandiMi(i) && bilgisayar.kartGetir(i).getDeger() == orta.ustKart().getDeger()){
				return i;
			}
		}
		return -1;
	}

	private int valeVarMi(){
		for (int i = 0; i < 4; i++){
			if (!bilgisayar.oynandiMi(i) && bilgisayar.kartGetir(i).getDeger() == 11){
				return i;
			}
		}
		return -1;
	}

	private int rastgeleOyna(){
		Random random = new Random();
		int kart = random.nextInt(4);
		while (bilgisayar.oynandiMi(kart)){
			kart = random.nextInt(4);
		}
		return kart;
	}

	private void bilgisayarDusun(){
		int secilenKart = -1;
		if (orta.ustKart() != null){
			secilenKart = ayniKagitVarMi();
			if (secilenKart == -1){
				secilenKart = valeVarMi();
			}
		}
		if (secilenKart == -1){
			secilenKart = rastgeleOyna();
		}
		bilgisayarAnimasyonKart = bilgisayarKartlar[secilenKart];
		kartGoster(bilgisayar.kartGetir(secilenKart), bilgisayarAnimasyonKart);
		bilgisayarAnimasyon();
	}

	private void kartGoster(Kart kart, ImageView kartResim){
		AssetManager assets = getAssets();
		try{
			InputStream resim = assets.open(kart.toString() + ".png");
			Drawable flag = Drawable.createFromStream(resim, kart.toString());
			kartResim.setImageDrawable(flag);
		}
		catch (IOException e) {
		}
		kartResim.setVisibility(View.VISIBLE);
	}

	private void turaBasla(){
		insan = deste.elDagit();
		bilgisayar = deste.elDagit();
		for (int i = 0; i < 4; i++){
			kartGoster(insan.kartGetir(i), kartlar[i]);
		}
		if (orta.ustKart() != null){
			kartGoster(orta.ustKart(), ortaKart);			
		} else {
			ortaKart.setVisibility(View.INVISIBLE);
		}
		for (int i = 0; i < 4; i++){
			bilgisayarKartlar[i].setVisibility(View.VISIBLE);
		}
		hamle = 0;
	}
	
	private void oyunaBasla(){
		tur = 0;
		deste = new Deste();
		orta = new Orta();
		insanKazanc = new Kazanc();
		bilgisayarKazanc = new Kazanc();
		deste.ortaDagit(orta);
		turaBasla();
		ortayiGoster();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
		kartlar[0] = (ImageView) findViewById(R.id.kart1);
		kartlar[1] = (ImageView) findViewById(R.id.kart2);
		kartlar[2] = (ImageView) findViewById(R.id.kart3);
		kartlar[3] = (ImageView) findViewById(R.id.kart4);
		bilgisayarKartlar[0] = (ImageView) findViewById(R.id.bilgisayarKart1);
		bilgisayarKartlar[1] = (ImageView) findViewById(R.id.bilgisayarKart2);
		bilgisayarKartlar[2] = (ImageView) findViewById(R.id.bilgisayarKart3);
		bilgisayarKartlar[3] = (ImageView) findViewById(R.id.bilgisayarKart4);
		kapaliKartlar[0] = (ImageView) findViewById(R.id.kapaliKart1);
		kapaliKartlar[1] = (ImageView) findViewById(R.id.kapaliKart2);
		kapaliKartlar[2] = (ImageView) findViewById(R.id.kapaliKart3);
		kapaliKartlar[3] = (ImageView) findViewById(R.id.kapaliKart4);
		kapaliKartlar[4] = (ImageView) findViewById(R.id.kapaliKart5);
		kapaliKartlar[5] = (ImageView) findViewById(R.id.kapaliKart6);
		kapaliKartlar[6] = (ImageView) findViewById(R.id.kapaliKart7);
		kapaliKartlar[7] = (ImageView) findViewById(R.id.kapaliKart8);
		kapaliKartlar[8] = (ImageView) findViewById(R.id.kapaliKart9);
		kapaliKartlar[9] = (ImageView) findViewById(R.id.kapaliKart10);
		kapaliKartlar[10] = (ImageView) findViewById(R.id.kapaliKart11);
		kapaliKartlar[11] = (ImageView) findViewById(R.id.kapaliKart12);
		kapaliKartlar[12] = (ImageView) findViewById(R.id.kapaliKart13);
		for (int i = 0; i < 13; i++){
			kapaliKartlar[i].setVisibility(View.INVISIBLE);
		}
		ortaKart = (ImageView) findViewById(R.id.ortaKart);
		for (int i = 0; i < 4; i++){
			kartlar[i].setOnClickListener(kartTikla);
		}
		oyunaBasla();
	}
	
	public OnClickListener kartTikla = new OnClickListener() {
		public void onClick(View v){
			String tag = (String) v.getTag();
			int secilenKart = Integer.parseInt(tag);
			if (insan.oynandiMi(secilenKart)){
				return;
			}
			insanAnimasyonKart = kartlar[secilenKart];
			insanAnimasyon();
		}
	};
	
	private void insanAnimasyon(){
		insanAnimasyonKartX = insanAnimasyonKart.getLeft();
		insanAnimasyonKartY = insanAnimasyonKart.getTop();
		insanAnimasyonKart.animate().x(ortaKart.getLeft()).y(ortaKart.getTop()).setDuration(1000).setListener(insanAnimasyonBitti);
	}

	private void bilgisayarAnimasyon(){
		bilgisayarAnimasyonKartX = bilgisayarAnimasyonKart.getLeft();
		bilgisayarAnimasyonKartY = bilgisayarAnimasyonKart.getTop();
		bilgisayarAnimasyonKart.animate().x(ortaKart.getLeft()).y(ortaKart.getTop()).setDuration(1000).setListener(bilgisayarAnimasyonBitti);
	}

	public AnimatorListenerAdapter insanAnimasyonBitti = new AnimatorListenerAdapter(){
		public void onAnimationStart(Animator animation){
		} 
		public void onAnimationEnd(Animator animation){
			String tag = (String) insanAnimasyonKart.getTag();
			int kartTag = Integer.parseInt(tag);
			insanAnimasyonKart.setVisibility(View.INVISIBLE);
			oyna(insan, insanKazanc, kartTag);
			bilgisayarDusun();
			insanAnimasyonKart.animate().x(insanAnimasyonKartX).y(insanAnimasyonKartY).setDuration(1).setListener(null);
		}		
	};
	
	public AnimatorListenerAdapter bilgisayarAnimasyonBitti = new AnimatorListenerAdapter(){
		public void onAnimationStart(Animator animation){
		} 
		public void onAnimationEnd(Animator animation){
			String tag = (String) bilgisayarAnimasyonKart.getTag();
			int kartTag = Integer.parseInt(tag);
			bilgisayarAnimasyonKart.setVisibility(View.INVISIBLE);
			bilgisayarAnimasyonKart.setImageResource(R.drawable.bos);
			oyna(bilgisayar, bilgisayarKazanc, kartTag - 4);
			oyunuDevamEttir();
			bilgisayarAnimasyonKart.animate().x(bilgisayarAnimasyonKartX).y(bilgisayarAnimasyonKartY).setDuration(1).setListener(null);
		}		
	};
	
}
