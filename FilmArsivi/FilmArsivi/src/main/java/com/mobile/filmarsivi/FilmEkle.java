package com.mobile.filmarsivi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class FilmEkle extends Fragment {
	private EditText ad;
	private EditText sure;
	private EditText yil;
	private Spinner tur;
	private Spinner yonetmen;
	private Context context;
	private CursorAdapter turAdapter;
	private CursorAdapter yonetmenAdapter;
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	String[] alanListesi;
    	int[] gosterimListesi;
        View filmEkleView = inflater.inflate(R.layout.film_ekle, container, false);
    	context = this.getActivity();
		ad = (EditText) filmEkleView.findViewById(R.id.ekleAd);
		sure = (EditText) filmEkleView.findViewById(R.id.ekleSure);
		yil = (EditText) filmEkleView.findViewById(R.id.ekleYil);
		tur = (Spinner) filmEkleView.findViewById(R.id.ekleTur);
		yonetmen = (Spinner) filmEkleView.findViewById(R.id.ekleYonetmen);
		Button ekle = (Button) filmEkleView.findViewById(R.id.ekle);
		ekle.setOnClickListener(ekleTikla);
        alanListesi = new String[] {"ad"};
        gosterimListesi = new int[] {R.id.turAdi};
        turAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        tur.setAdapter(turAdapter);
        new TurleriGetirGorev(context, turAdapter).execute((Object[]) null);
        alanListesi = new String[] {"ad", "soyad"};
        gosterimListesi = new int[] {R.id.yonetmenAdi, R.id.yonetmenSoyadi};
        yonetmenAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.yonetmen_hucre, null, alanListesi, gosterimListesi, 0);
        yonetmen.setAdapter(yonetmenAdapter);
        new YonetmenleriGetirGorev(context, yonetmenAdapter).execute((Object[]) null);
        return filmEkleView;
    }
    
    AsyncTask<Object, Object, Object> filmEkleGorev = new AsyncTask<Object, Object, Object>(){
    	@Override
        protected Object doInBackground(Object... params){
    		FilmVeritabani veriTabani = new FilmVeritabani(context);
	        Cursor turCursor = (Cursor) turAdapter.getItem(tur.getSelectedItemPosition());
	        String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
	        Cursor yonetmenCursor = (Cursor) yonetmenAdapter.getItem(yonetmen.getSelectedItemPosition());
	        String yonetmenAdi = yonetmenCursor.getString(yonetmenCursor.getColumnIndex("ad")) + " " + yonetmenCursor.getString(yonetmenCursor.getColumnIndex("soyad"));
    		veriTabani.filmEkle(ad.getText().toString(), Integer.parseInt(sure.getText().toString()), 
    				Integer.parseInt(yil.getText().toString()), turAdi, yonetmenAdi);
    		return null;
        } 

        @Override
        protected void onPostExecute(Object result){
        } 
    };
    
    public OnClickListener ekleTikla = new OnClickListener() {
		public void onClick(View v){
			filmEkleGorev.execute((Object[]) null);
		}
	};
	
}
