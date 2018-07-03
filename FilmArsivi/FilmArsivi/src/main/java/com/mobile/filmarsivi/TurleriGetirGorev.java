package com.mobile.filmarsivi;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class TurleriGetirGorev extends AsyncTask<Object, Object, Cursor>{
	
	private FilmVeritabani veriTabani;
	private CursorAdapter turAdapter;
	
	public TurleriGetirGorev(Context context, CursorAdapter turAdapter){
		veriTabani = new FilmVeritabani(context);
		this.turAdapter = turAdapter;
	}

    @Override
    protected Cursor doInBackground(Object... params){
 	   veriTabani.open();
 	   return veriTabani.turleriGetir(); 
    }

    @Override
    protected void onPostExecute(Cursor result){
 	   turAdapter.changeCursor(result);
 	   veriTabani.close();
    } 

}
