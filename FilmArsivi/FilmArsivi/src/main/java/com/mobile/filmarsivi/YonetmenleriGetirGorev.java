package com.mobile.filmarsivi;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class YonetmenleriGetirGorev extends AsyncTask<Object, Object, Cursor>{
	
	FilmVeritabani veriTabani;
	CursorAdapter yonetmenAdapter;
	
	public YonetmenleriGetirGorev(Context context, CursorAdapter yonetmenAdapter){
		veriTabani = new FilmVeritabani(context);
		this.yonetmenAdapter = yonetmenAdapter;
	}

    @Override
    protected Cursor doInBackground(Object... params){
 	   veriTabani.open();
 	   return veriTabani.yonetmenleriGetir(); 
    }

    @Override
    protected void onPostExecute(Cursor result){
 	   yonetmenAdapter.changeCursor(result);
 	   veriTabani.close();
    } 

}
