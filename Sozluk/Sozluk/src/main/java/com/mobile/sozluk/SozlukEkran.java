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

public class SozlukEkran extends ListFragment {
	private Sozluk sozluk = null;
	private EditText kelimeKutu;
	private Activity ekran;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ekran = this.getActivity();
        View sozlukEkran = inflater.inflate(R.layout.sozluk_ekran, container, false);
        kelimeKutu = (EditText) sozlukEkran.findViewById(R.id.kelimeAra);
        kelimeKutu.addTextChangedListener(kelimeIzleyici);
        return sozlukEkran;
    }
    
    public void setSozluk(Sozluk sozluk){
    	this.sozluk = sozluk;
    }
        
	private TextWatcher kelimeIzleyici = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (sozluk != null){
				ArrayList<SozlukKelime> kelimeler = sozluk.kelimeAra(s.toString());
				ArrayList<HashMap<String, String>> kelimeListesi = new ArrayList<HashMap<String, String>>();
				for (SozlukKelime kelime:kelimeler){
					String anlamBilgi;
					if (kelime.getSinif() != null){
						anlamBilgi = kelime.getSinif() + ". ";
					} else {
						anlamBilgi = "";
					}
					if (kelime.getKoken() != null){
						anlamBilgi = anlamBilgi + kelime.getKoken() + ". ";
					}
					for (int i = 0; i < kelime.anlamSayisi(); i++){
						HashMap<String, String> hashMap = new HashMap<String, String>();
						hashMap.put("anlam", anlamBilgi + kelime.anlam(i).getAnlam());
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
