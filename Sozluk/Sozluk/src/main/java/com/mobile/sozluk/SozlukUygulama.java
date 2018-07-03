package com.mobile.sozluk;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;

public class SozlukUygulama extends Activity implements
		ActionBar.TabListener {
	private SozlukEkran sozlukEkran;
	private CeviriEkran ceviriEkran;
	private Sozluk sozluk = null;
	private CeviriSozluk ceviriSozluk = null;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		actionBar.addTab(actionBar.newTab().setText(R.string.sozluk)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.ceviriSozluk)
				.setTabListener(this));
		new SozlukleriYukle().execute();
	}
	
    private class SozlukleriYukle extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected Boolean doInBackground(Void... params) {
    		try {
    			XMLReader sozlukCozumleyici = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
    	        sozluk = new Sozluk();
    	        sozlukCozumleyici.setContentHandler(sozluk);
    	        sozlukCozumleyici.parse(new InputSource(getResources().openRawResource(R.raw.turkish_dictionary)));
    			XMLReader ceviriSozlukCozumleyici = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
    	        ceviriSozluk = new CeviriSozluk();
    	        ceviriSozlukCozumleyici.setContentHandler(ceviriSozluk);
    	        ceviriSozlukCozumleyici.parse(new InputSource(getResources().openRawResource(R.raw.english_turkish)));
    		} catch (SAXException e) {
    			e.printStackTrace();
    		} catch (ParserConfigurationException e) {
    			e.printStackTrace();
    		} catch (NotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			return true;
		}
		
        protected void onPostExecute(Boolean complete){
	        Toast.makeText(getApplicationContext(), "Sozlukler Yuklendi!", Toast.LENGTH_LONG).show();
        } 
    }

	
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (tab.getPosition() == 0){
			sozlukEkran = (SozlukEkran) Fragment.instantiate(this, SozlukEkran.class.getName());
			sozlukEkran.setSozluk(sozluk);
			fragmentTransaction.add(android.R.id.content, sozlukEkran, "Turkce Sozluk");
		} else {
			if (tab.getPosition() == 1){
				ceviriEkran = (CeviriEkran) Fragment.instantiate(this, CeviriEkran.class.getName());
				ceviriEkran.setCeviriSozluk(ceviriSozluk);
				fragmentTransaction.add(android.R.id.content, ceviriEkran, "Ingilizce-Turkce Sozluk");
			}
		}
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (tab.getPosition() == 0){
			fragmentTransaction.detach(sozlukEkran);
		} else {
			if (tab.getPosition() == 1){
				fragmentTransaction.detach(ceviriEkran);
			}
		}
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

}

