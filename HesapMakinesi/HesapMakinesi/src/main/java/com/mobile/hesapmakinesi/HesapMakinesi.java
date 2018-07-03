package com.mobile.hesapmakinesi;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class HesapMakinesi extends Activity {
	private EditText sonuc;
	private char oncekiIslem;
	private int oncekiSayi;
	private int sayi;
	private int hafiza;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
		sonuc = (EditText) findViewById(R.id.Sonuc);
		Button dugme0 = (Button) findViewById(R.id.Dugme0);
		Button dugme1 = (Button) findViewById(R.id.Dugme1);
		Button dugme2 = (Button) findViewById(R.id.Dugme2);
		Button dugme3 = (Button) findViewById(R.id.Dugme3);
		Button dugme4 = (Button) findViewById(R.id.Dugme4);
		Button dugme5 = (Button) findViewById(R.id.Dugme5);
		Button dugme6 = (Button) findViewById(R.id.Dugme6);
		Button dugme7 = (Button) findViewById(R.id.Dugme7);
		Button dugme8 = (Button) findViewById(R.id.Dugme8);
		Button dugme9 = (Button) findViewById(R.id.Dugme9);
		Button dugmeArti = (Button) findViewById(R.id.DugmeArti);
		Button dugmeEksi = (Button) findViewById(R.id.DugmeEksi);
		Button dugmeCarpi = (Button) findViewById(R.id.DugmeCarpi);
		Button dugmeBolu = (Button) findViewById(R.id.DugmeBolu);
		Button dugmeEsit = (Button) findViewById(R.id.DugmeEsit);
		Button dugmeSil = (Button) findViewById(R.id.DugmeSil);
		Button dugmeHafizayaEkle = (Button) findViewById(R.id.DugmeHafizayaEkle);
		Button dugmeHafizadanCikar = (Button) findViewById(R.id.DugmeHafizadanCikar);
		Button dugmeHafizayiGoster = (Button) findViewById(R.id.DugmeHafizayiGoster);
		Button dugmeHafizayiSil = (Button) findViewById(R.id.DugmeHafizayiSil);
		dugme0.setOnClickListener(dugme0Tikla);
		dugme1.setOnClickListener(dugme1Tikla);
		dugme2.setOnClickListener(dugme2Tikla);
		dugme3.setOnClickListener(dugme3Tikla);
		dugme4.setOnClickListener(dugme4Tikla);
		dugme5.setOnClickListener(dugme5Tikla);
		dugme6.setOnClickListener(dugme6Tikla);
		dugme7.setOnClickListener(dugme7Tikla);
		dugme8.setOnClickListener(dugme8Tikla);
		dugme9.setOnClickListener(dugme9Tikla);
		dugmeArti.setOnClickListener(dugmeArtiTikla);
		dugmeEksi.setOnClickListener(dugmeEksiTikla);
		dugmeCarpi.setOnClickListener(dugmeCarpiTikla);
		dugmeBolu.setOnClickListener(dugmeBoluTikla);
		dugmeEsit.setOnClickListener(dugmeEsitTikla);
		dugmeSil.setOnClickListener(dugmeSilTikla);
		dugmeHafizayaEkle.setOnClickListener(dugmeHafizayaEkleTikla);
		dugmeHafizadanCikar.setOnClickListener(dugmeHafizadanCikarTikla);
		dugmeHafizayiGoster.setOnClickListener(dugmeHafizayiGosterTikla);
		dugmeHafizayiSil.setOnClickListener(dugmeHafizayiSilTikla);
		oncekiIslem = '=';
		oncekiSayi = 0;
		sayi = 0;
		hafiza = 0;
	}
	
	private void ekrandaGoster(int sayi){
		sonuc.setText(String.format("%d", sayi));
	}
	
	private void sayiEkle(int rakam){
		sayi = sayi * 10 + rakam;
		ekrandaGoster(sayi);
	}
	
	private void islemYap(char islem){
		switch (oncekiIslem) {
		case '+':
			oncekiSayi = oncekiSayi + sayi;
			break;
		case '-':
			oncekiSayi = oncekiSayi - sayi;
			break;
		case 'x':
			oncekiSayi = oncekiSayi * sayi;
			break;
		case '/':
			if (sayi != 0)
				oncekiSayi = oncekiSayi / sayi;
			break;
		case '=':
			oncekiSayi = sayi;
			break;
		}
		oncekiIslem = islem;
		sayi = 0;
		ekrandaGoster(oncekiSayi);
	}
	
	public OnClickListener dugme0Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(0);
		}
	};

	public OnClickListener dugme1Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(1);
		}
	};

	public OnClickListener dugme2Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(2);
		}
	};

	public OnClickListener dugme3Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(3);
		}
	};

	public OnClickListener dugme4Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(4);
		}
	};

	public OnClickListener dugme5Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(5);
		}
	};

	public OnClickListener dugme6Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(6);
		}
	};

	public OnClickListener dugme7Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(7);
		}
	};

	public OnClickListener dugme8Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(8);
		}
	};

	public OnClickListener dugme9Tikla = new OnClickListener() {
		public void onClick(View v){
			sayiEkle(9);
		}
	};

	public OnClickListener dugmeArtiTikla = new OnClickListener() {
		public void onClick(View v){
			islemYap('+');
		}
	};

	public OnClickListener dugmeEksiTikla = new OnClickListener() {
		public void onClick(View v){
			islemYap('-');
		}
	};

	public OnClickListener dugmeCarpiTikla = new OnClickListener() {
		public void onClick(View v){
			islemYap('x');
		}
	};

	public OnClickListener dugmeBoluTikla = new OnClickListener() {
		public void onClick(View v){
			islemYap('/');
		}
	};

	public OnClickListener dugmeEsitTikla = new OnClickListener() {
		public void onClick(View v){
			islemYap('=');
			sayi = oncekiSayi;
			oncekiSayi = 0;
		}
	};

	public OnClickListener dugmeSilTikla = new OnClickListener() {
		public void onClick(View v){
			sayi = 0;
			ekrandaGoster(sayi);
		}
	};

	public OnClickListener dugmeHafizayaEkleTikla = new OnClickListener() {
		public void onClick(View v){
			 hafiza += sayi;
			 sayi = 0;
		}
	};

	public OnClickListener dugmeHafizadanCikarTikla = new OnClickListener() {
		public void onClick(View v){
			 hafiza -= sayi;
			 sayi = 0;
		}
	};

	public OnClickListener dugmeHafizayiGosterTikla = new OnClickListener() {
		public void onClick(View v){
			 sayi = hafiza;
			 ekrandaGoster(sayi);
		}
	};

	public OnClickListener dugmeHafizayiSilTikla = new OnClickListener() {
		public void onClick(View v){
			 hafiza = 0;
		}
	};
}
