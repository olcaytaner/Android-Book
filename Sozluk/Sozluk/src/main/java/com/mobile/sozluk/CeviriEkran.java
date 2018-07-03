package com.mobile.sozluk;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;

public class CeviriEkran extends ListFragment {
	private CeviriSozluk ceviriSozluk = null;
	private EditText kelimeKutu;
	private Activity ekran;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ekran = this.getActivity();
        View ceviriEkran = inflater.inflate(R.layout.ceviri_ekran, container, false);
        kelimeKutu = (EditText) ceviriEkran.findViewById(R.id.ceviriKelimeAra);
        kelimeKutu.addTextChangedListener(kelimeIzleyici);
        return ceviriEkran;
    }
    
    public void setCeviriSozluk(CeviriSozluk ceviriSozluk){
    	this.ceviriSozluk = ceviriSozluk;
    }
    
	private TextWatcher kelimeIzleyici = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (ceviriSozluk != null){
				ArrayList<KaynakKelime> kelimeler = ceviriSozluk.kelimeAra(s.toString());
				ArrayList<HashMap<String, String>> kelimeListesi = new ArrayList<HashMap<String, String>>();
				for (KaynakKelime kelime:kelimeler){
					for (int i = 0; i < kelime.ceviriSayisi(); i++){
						String anlamBilgi;
						if (kelime.ceviri(i).getSinif() != null){
							anlamBilgi = kelime.ceviri(i).getSinif() + ". ";
						} else {
							anlamBilgi = "";
						}
						HashMap<String, String> hashMap = new HashMap<String, String>();
						hashMap.put("anlam", anlamBilgi + kelime.ceviri(i).getAnlam().getAnlam());
						kelimeListesi.add(hashMap);
					}
				}
		        String[] alanListesi = new String[] {"anlam"};
		        int[] gosterimListesi = new int[] {R.id.sozlukAnlam};
		        SimpleAdapter kelimeAdapter = new SimpleAdapter(ekran, kelimeListesi, R.layout.sozluk_hucre, alanListesi, gosterimListesi);
		        setListAdapter(kelimeAdapter);				
			}
		} 
		@Override
		public void afterTextChanged(Editable s){			
		} 
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {			
		} 
	}; 	

}
