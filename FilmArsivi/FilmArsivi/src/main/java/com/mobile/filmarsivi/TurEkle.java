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

public class TurEkle extends Fragment{
	private EditText ad;
	private Context context;
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) { 
        View turEkleView = inflater.inflate(R.layout.tur_ekle, container, false);
    	context = this.getActivity();
		ad = (EditText) turEkleView.findViewById(R.id.ekleTurAd);
		Button ekle = (Button) turEkleView.findViewById(R.id.turEkle);
		ekle.setOnClickListener(ekleTikla);
        return turEkleView;
    }
    
    AsyncTask<Object, Object, Object> turEkleGorev = new AsyncTask<Object, Object, Object>(){
    	@Override
        protected Object doInBackground(Object... params){
    		FilmVeritabani veriTabani = new FilmVeritabani(context);
    		veriTabani.turEkle(ad.getText().toString());
    		return null;
        } 

        @Override
        protected void onPostExecute(Object result){
        } 
    };
    
    public OnClickListener ekleTikla = new OnClickListener() {
		public void onClick(View v){
			turEkleGorev.execute((Object[]) null);
		}
	};

}
