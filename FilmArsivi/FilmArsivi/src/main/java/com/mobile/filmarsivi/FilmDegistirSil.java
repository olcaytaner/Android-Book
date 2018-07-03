package com.mobile.filmarsivi;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class FilmDegistirSil extends Activity {
	private long rowID;
	private EditText ad;
	private EditText sure;
	private EditText yil;
	private Spinner tur;
	private Spinner yonetmen;
	private Context context;
	private CursorAdapter turAdapter;
	private CursorAdapter yonetmenAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	String[] alanListesi;
    	int[] gosterimListesi;
		super.onCreate(savedInstanceState);
		context = this.getApplicationContext();
		setContentView(R.layout.film_degistir_sil);
		Button degisiklikleriKaydet = (Button) findViewById(R.id.degisiklikleriKaydet);
		Button sil = (Button) findViewById(R.id.sil);
		Button geriDon = (Button) findViewById(R.id.geriDon);
		ad = (EditText) findViewById(R.id.ad);
		sure = (EditText) findViewById(R.id.sure);
		yil = (EditText) findViewById(R.id.yil);
		tur = (Spinner) findViewById(R.id.tur);
		yonetmen = (Spinner) findViewById(R.id.yonetmen);
        alanListesi = new String[] {"ad"};
        gosterimListesi = new int[] {R.id.turAdi};
        turAdapter = new SimpleCursorAdapter(this, R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        tur.setAdapter(turAdapter);
        new TurleriGetirGorev(context, turAdapter).execute((Object[]) null);
        alanListesi = new String[] {"ad", "soyad"};
        gosterimListesi = new int[] {R.id.yonetmenAdi, R.id.yonetmenSoyadi};
        yonetmenAdapter = new SimpleCursorAdapter(this, R.layout.yonetmen_hucre, null, alanListesi, gosterimListesi, 0);
        yonetmen.setAdapter(yonetmenAdapter);
        new YonetmenleriGetirGorev(context, yonetmenAdapter).execute((Object[]) null);
		degisiklikleriKaydet.setOnClickListener(degisiklikleriKaydetTikla);
		sil.setOnClickListener(silTikla);
		geriDon.setOnClickListener(geriDonTikla);
		Bundle extras = getIntent().getExtras();
        rowID = extras.getLong("row_id");
        new FilmGetirGorev().execute(rowID);        
	}
	
	private class FilmGetirGorev extends AsyncTask<Long, Object, Cursor>{
		
		FilmVeritabani veriTabani = new FilmVeritabani(context);

		@Override
	     protected Cursor doInBackground(Long... params){
	        veriTabani.open();
	        return veriTabani.filmGetir(params[0]);
	     }
		
	      @Override
	      protected void onPostExecute(Cursor result){
	         super.onPostExecute(result);
	         result.moveToFirst(); 
	         int adPos = result.getColumnIndex("ad");
	         int surePos = result.getColumnIndex("sure");
	         int yilPos = result.getColumnIndex("yil");
	         int turPos = result.getColumnIndex("tur");
	         int yonetmenPos = result.getColumnIndex("yonetmen");
	         ad.setText(result.getString(adPos));
	         sure.setText("" + result.getInt(surePos));
	         yil.setText("" + result.getInt(yilPos));
	         for (int i = 0; i < turAdapter.getCount(); i++){
	        	 Cursor cursor = (Cursor) turAdapter.getItem(i);
	        	 String turAdi = cursor.getString(cursor.getColumnIndex("ad"));
	        	 String sonucTurAdi = result.getString(turPos);
	             if (turAdi.equals(sonucTurAdi)){
	                 tur.setSelection(i);
	                 break;
	             }
	         }
	         for (int i = 0; i < yonetmenAdapter.getCount(); i++){
	        	 Cursor cursor = (Cursor) yonetmenAdapter.getItem(i);
	        	 String yonetmenAdi = cursor.getString(cursor.getColumnIndex("ad")) + " " + cursor.getString(cursor.getColumnIndex("soyad"));
	        	 String sonucYonetmenAdi = result.getString(yonetmenPos);
	             if (yonetmenAdi.equals(sonucYonetmenAdi)){
	                 yonetmen.setSelection(i);
	                 break;
	             }
	         }
	         result.close(); 
	         veriTabani.close();
	      }
	}
	
    AsyncTask<Object, Object, Object> filmDegistirGorev = new AsyncTask<Object, Object, Object>(){
    	@Override
        protected Object doInBackground(Object... params){
    		FilmVeritabani veriTabani = new FilmVeritabani(context);
	        Cursor turCursor = (Cursor) turAdapter.getItem(tur.getSelectedItemPosition());
	        String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
	        Cursor yonetmenCursor = (Cursor) yonetmenAdapter.getItem(yonetmen.getSelectedItemPosition());
	        String yonetmenAdi = yonetmenCursor.getString(yonetmenCursor.getColumnIndex("ad")) + " " + yonetmenCursor.getString(yonetmenCursor.getColumnIndex("soyad"));
    		veriTabani.filmDegistir(rowID, ad.getText().toString(), Integer.parseInt(sure.getText().toString()), 
    				Integer.parseInt(yil.getText().toString()), turAdi, yonetmenAdi);
    		return null;
        } 

        @Override
        protected void onPostExecute(Object result){
           finish();
        } 
    };
    
    AsyncTask<Long, Object, Object> filmSilGorev = new AsyncTask<Long, Object, Object>(){
        @Override
        protected Object doInBackground(Long... params){
     	   FilmVeritabani veriTabani = new FilmVeritabani(context);
     	   veriTabani.filmSil(params[0]);
            return null;
        } 

        @Override
        protected void onPostExecute(Object result){
           finish(); 
        } 
   }; 
	
	public OnClickListener degisiklikleriKaydetTikla = new OnClickListener() {
		public void onClick(View v){
			filmDegistirGorev.execute((Object[]) null); 
		}
	};
	
	public OnClickListener silTikla = new OnClickListener() {
		public void onClick(View v){
			filmSilGorev.execute(new Long[] { rowID });	
		}
	};

	public OnClickListener geriDonTikla = new OnClickListener() {
		public void onClick(View v){
			finish();
		}
	};
	
}

