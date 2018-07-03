package com.mobile.barkirmaca;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.app.Activity;

public class TuglaKirmaca extends Activity {
	
	private Oyun oyun;
	private int seviyeNo;
	private int ekranGenislik;
	private int ekranYukseklik;
	private Ekran ekran;
	private Timer timer;
	
	private void yeniSeviye(){
	    seviyeNo++;
	    ekran.parametre.yeniSeviye(oyun.seviye(seviyeNo));
	}
	
    final Handler h = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Top top;
            DusenTugla dusenTugla;
            for (int i = 0; i < ekran.parametre.topSayisi(); i++){
                top = ekran.parametre.top(i);
                top.hareketEttir();
                ekran.parametre.topCubuklaTemasKontrol(top);
                if (ekran.parametre.topTuglaylaTemasKontrol(top)){
                    yeniSeviye();
                    return true;
                }
                if (!ekran.parametre.topSinirlarlaTemasKontrol(top)){
                    timer.cancel();
                }
            }
            for (int i = 0; i < ekran.parametre.dusenTuglaSayisi(); i++){
                dusenTugla = ekran.parametre.dusenTugla(i);
                dusenTugla.hareketEttir();
                if (!ekran.parametre.dusenTuglaCubuklaTemasKontrol())
                    timer.cancel();
            }
            ekran.invalidate();
            return false;
        }
    });

    class ZamanDuzenleyici extends TimerTask {
        @Override
        public void run() {
            h.sendEmptyMessage(0);
        }
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Parametre oyunParametre;
		setContentView(R.layout.ana_ekran);
        ekran = (Ekran) findViewById(R.id.ekran);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        ekranGenislik = displayMetrics.widthPixels;
        ekranYukseklik = displayMetrics.heightPixels - 130;
        seviyeNo = 0;
        oyun = new Oyun(this.getApplicationContext(), ekranGenislik);        
        oyunParametre = new Parametre(ekranGenislik, ekranYukseklik, oyun.seviye(0));
        ekran.parametre = oyunParametre;
        timer = new Timer();
        timer.schedule(new ZamanDuzenleyici(), 0, 20);
	}

}
