package com.mobile.filmarsivi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
 
public class FilmListesi extends ListFragment {
	protected static final String ROW_ID = "row_id";
	private CursorAdapter filmAdapter;
	private Context context;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View filmListesiView = inflater.inflate(R.layout.film_listesi, container, false);
    	context = this.getActivity();
        String[] alanListesi = new String[] {"ad", "yil"};
        int[] gosterimListesi = new int[] {R.id.filmAdi, R.id.filmYili};
        filmAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.film_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(filmAdapter);
        new FilmleriGetirGorev().execute((Object[]) null);
        return filmListesiView;
    }
    
    private class FilmleriGetirGorev extends AsyncTask<Object, Object, Cursor>{
    	FilmVeritabani veriTabani = new FilmVeritabani(context);

        @Override
        protected Cursor doInBackground(Object... params){
     	   veriTabani.open();
     	   return veriTabani.filmleriGetir(); 
        }

        @Override
        protected void onPostExecute(Cursor result){
     	   filmAdapter.changeCursor(result);
     	   veriTabani.close();
        } 
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	Intent filmDegistirSil = new Intent(l.getContext(), FilmDegistirSil.class);
    	filmDegistirSil.putExtra(ROW_ID, id);
    	startActivity(filmDegistirSil); 
    }
 
}