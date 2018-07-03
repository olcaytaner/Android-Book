package com.mobile.satranctaslari;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SatrancTaslari extends Activity {
	
    private long baslangicZamani;
    private TextView zamanGosterge;
    private Ekran ekran;
    private boolean durdu = false, bitti = false;
    private Cozum cozum;
    private Bulmaca bulmaca;
    private Bulmaca[] bulmacalar;
    private int soruSayisi;    
    
    final Handler h = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            long milisaniye = System.currentTimeMillis() - baslangicZamani;
            if (!durdu){
                zamanGosterge.setText(String.format("%02d:%02d", ((int) (milisaniye / 1000)) / 60, ((int) (milisaniye / 1000)) % 60));
            }
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
		setContentView(R.layout.ana_ekran);
        zamanGosterge = (TextView) findViewById(R.id.zamanGosterge);
        dosyaOku();
        ekran = (Ekran) findViewById(R.id.ekran);
        yeniOyun();
        Timer timer = new Timer();
        baslangicZamani = System.currentTimeMillis();
        timer.schedule(new ZamanDuzenleyici(), 0, 500);
        ekran.invalidate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.opsiyon, menu);
		return true;
	}
	   
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.coz:
                coz();
                ekran.invalidate();
                durdu = true;
				break;
			case R.id.kontrol:
				cozumKontrol();
				break;
			case R.id.yenioyun:
                yeniOyun();
                ekran.invalidate();
                baslangicZamani = System.currentTimeMillis();
                durdu = false;
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	void dosyaOku(){
		InputStream stream = getResources().openRawResource(R.raw.bulmacalar);
		Scanner ayirici = new Scanner(stream);
	    int i, j, x, y, kareSayisi, tehditSayisi;
	    soruSayisi = ayirici.nextInt();
	    bulmacalar = new Bulmaca[soruSayisi];
	    for (i = 0; i < soruSayisi; i++){
	        Bulmaca bulmaca = new Bulmaca();
	        for (j = 0; j < 8; j++){
	            x = ayirici.nextInt();
	            y = ayirici.nextInt();
	            Kare kare = new Kare(x - 1, y - 1);
	            bulmaca.yerlestirilecekYerEkle(kare);
	        }
	        kareSayisi = ayirici.nextInt();
	        for (j = 0; j < kareSayisi; j++){
	            x = ayirici.nextInt();
	            y = ayirici.nextInt();
	            tehditSayisi = ayirici.nextInt();
	            Tehdit tehdit = new Tehdit(x - 1, y - 1, tehditSayisi);
	            bulmaca.tehditEkle(tehdit);
	        }
	        bulmacalar[i] = bulmaca;
	    }
	}

	void yeniOyun(){
		Random random;
		random = new Random();
	    int i;
	    bitti = false;
	    bulmaca = bulmacalar[random.nextInt(bulmacalar.length)];
	    ekran.tablo = new int[8];
	    ekran.tahtayaKonduMu = new boolean[8];
	    for (i = 0; i < 8; i++){
	        ekran.tablo[i] = Bulmaca.BOS;
	        ekran.tahtayaKonduMu[i] = false;
	    }
	    ekran.hareketEdiyor = false;
	    ekran.bulmaca = bulmaca;
	}

	void cozumKontrol(){
	    String mesaj;
	    cozum = new Cozum();
	    for (int i = 0; i < 8; i++)
	        cozum.tasNoIleEkle(ekran.tablo[i]);
	    if (!bulmaca.sartlariSaglar(cozum)){
	        mesaj = "Cozumunuz Yanlis";
	    } else {
	        mesaj = "Cozumunuz Dogru";
	    }
	    Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
	}

	void cozumArama(){
	    ArrayList<Tas> adaylar = new ArrayList<Tas>();
	    if (!bulmaca.sartlariSimdilikSaglar(cozum)){
	        return;
	    } else {
	        if (cozum.tasSayisi() == 8){
	            if (bulmaca.sartlariSaglar(cozum)){
	                bitti = true;
	            }
	        }
	        else{
	            adaylar = cozum.adaylariOlustur();
	            for (Tas tas:adaylar){
	                cozum.tasEkle(tas);
	                cozumArama();
	                if (bitti){
	                    return;
	                }
	                cozum.tasCikar();
	            }
	        }
	    }
	}

	void coz(){
	    int i;
	    bitti = false;
	    cozum = new Cozum();
	    cozumArama();
	    for (i = 0; i < 8; i++){
	        ekran.tablo[i] = cozum.tasNo(i);
	        ekran.tahtayaKonduMu[i] = true;
	    }
	}

}
