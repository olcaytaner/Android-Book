package com.mobile.piramit;

import java.util.Random;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.app.Activity;

public class Piramit extends Activity {
	private Bulmaca[] bulmacalar;
    private Ekran ekran;

	protected void onCreate(Bundle savedInstanceState) {
		Random random = new Random();
	    String[] bulmacaBilgileri = {"443252145336141522663", "234524435626143614625", 
	    		"161524246313452326215", "355424315665631243245", "653634542621351325265", 
	    		"543236612135654465432", "445385279314268683141725276595138379834941283", 
	    		"345342468929768161215485464767167583529398619", 
	    		"233154981265474421359918793858356165737438971", 
	    		"134925548326192374564412375353491475182356918"};
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		bulmacalar = new Bulmaca[bulmacaBilgileri.length];
		for (int i = 0; i < bulmacaBilgileri.length; i++){
			bulmacalar[i] = new Bulmaca(bulmacaBilgileri[i]);
		}
        ekran = (Ekran) findViewById(R.id.ekran);
		ekran.bulmaca = bulmacalar[random.nextInt(bulmacaBilgileri.length)];
        ekran.hucreGenislik = displayMetrics.widthPixels / (ekran.bulmaca.getBuyukluk() + 2);
	}

}
