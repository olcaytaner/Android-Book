package com.mobile.filmarsivi;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class YonetmenEkle extends Fragment{
	private EditText ad;
	private EditText soyad;
	private Context context;
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) { 
        View yonetmenEkleView = inflater.inflate(R.layout.yonetmen_ekle, container, false);
    	context = this.getActivity();
		ad = (EditText) yonetmenEkleView.findViewById(R.id.ekleYonetmenAd);
		soyad = (EditText) yonetmenEkleView.findViewById(R.id.ekleYonetmenSoyad);
		Button ekle = (Button) yonetmenEkleView.findViewById(R.id.yonetmenEkle);
		ekle.setOnClickListener(ekleTikla);
        return yonetmenEkleView;
    }
    
    AsyncTask<Object, Object, Object> yonetmenEkleGorev = new AsyncTask<Object, Object, Object>(){
    	@Override
        protected Object doInBackground(Object... params){
    		FilmVeritabani veriTabani = new FilmVeritabani(context);
    		veriTabani.yonetmenEkle(ad.getText().toString(), soyad.getText().toString());
    		return null;
        } 

        @Override
        protected void onPostExecute(Object result){
        } 
    };
    
    public OnClickListener ekleTikla = new OnClickListener() {
		public void onClick(View v){
			yonetmenEkleGorev.execute((Object[]) null);
		}
	};

}
