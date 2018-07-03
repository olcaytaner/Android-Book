package com.mobile.filmarsivi;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class TurListesi extends ListFragment {
	private CursorAdapter turAdapter;
	private Context context;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View turListesiView = inflater.inflate(R.layout.tur_listesi, container, false);
    	context = this.getActivity();
        String[] alanListesi = new String[] {"ad"};
        int[] gosterimListesi = new int[] {R.id.turAdi};
        turAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(turAdapter);
        new TurleriGetirGorev(context, turAdapter).execute((Object[]) null);
        return turListesiView;
    }
    
}
