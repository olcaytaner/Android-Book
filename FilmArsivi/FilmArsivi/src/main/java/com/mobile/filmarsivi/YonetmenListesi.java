package com.mobile.filmarsivi;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class YonetmenListesi extends ListFragment{
	private CursorAdapter yonetmenAdapter;
	private Context context;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View yonetmenListesiView = inflater.inflate(R.layout.yonetmen_listesi, container, false);
    	context = this.getActivity();
        String[] alanListesi = new String[] {"ad", "soyad"};
        int[] gosterimListesi = new int[] {R.id.yonetmenAdi, R.id.yonetmenSoyadi};
        yonetmenAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.yonetmen_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(yonetmenAdapter);
        new YonetmenleriGetirGorev(context, yonetmenAdapter).execute((Object[]) null);
        return yonetmenListesiView;
    }
    
}
