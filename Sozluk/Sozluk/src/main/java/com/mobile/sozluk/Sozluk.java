package com.mobile.sozluk;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sozluk extends DefaultHandler{
	private ArrayList<SozlukKelime> kelimeler;
	private SozlukKelime kelime;
	private String deger = null;
	private String anlamSinif = null;
	
	public Sozluk(){
		kelimeler = new ArrayList<SozlukKelime>();
	}
	
	public ArrayList<SozlukKelime> kelimeAra(String arananKelime){
		ArrayList<SozlukKelime> sonucListe;
		sonucListe = new ArrayList<SozlukKelime>();
		for (SozlukKelime kelime:kelimeler){
			if (kelime.getAd().equals(arananKelime)){
				sonucListe.add(kelime);
			}
		}
		return sonucListe;
	}
	
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (localName.equals("word")){
    		String ad = attributes.getValue("name");
    		kelime = new SozlukKelime(ad);
    		String sinif = attributes.getValue("lex_class");
    		if (sinif != null){
    			kelime.setSinif(sinif);
    		}
    		String koken = attributes.getValue("origin");
    		if (koken != null){
    			kelime.setKoken(koken);
    		}
    		deger = null;
    	} else {
    		if (localName.equals("meaning")){
        		anlamSinif = attributes.getValue("class");
        		deger = null;
    		}
    	}
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Anlam anlam;
    	if (localName.equals("lexicon")){
    		return;
    	} else {
    		if (localName.equals("word")){
    			kelimeler.add(kelime);
    		} else {
    			if (localName.equals("meaning")){
    				if (anlamSinif != null){
    					anlam = new Anlam(anlamSinif, deger);
    				} else {
    					anlam = new Anlam(deger);
    				}
    				kelime.anlamEkle(anlam);
    			}
    		}
    	}
    }
 
    public void characters(char[] ch, int start, int length) throws SAXException {
    	if (deger != null){
    		deger = deger + new String(ch, start, length);
    	} else {
    		deger = new String(ch, start, length);
    	}
    }

}
