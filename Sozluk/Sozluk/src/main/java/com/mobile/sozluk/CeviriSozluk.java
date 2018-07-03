package com.mobile.sozluk;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CeviriSozluk extends DefaultHandler{
	private ArrayList<KaynakKelime> kelimeler;
	private KaynakKelime kelime;
	private String anlamSinif;
	private String ceviriSinif;
	private String deger;
	
	public CeviriSozluk(){
		kelimeler = new ArrayList<KaynakKelime>();
	}
	
	public ArrayList<KaynakKelime> kelimeAra(String arananKelime){
		ArrayList<KaynakKelime> sonucListe;
		sonucListe = new ArrayList<KaynakKelime>();
		for (KaynakKelime kelime:kelimeler){
			if (kelime.getAd().equals(arananKelime)){
				sonucListe.add(kelime);
			}
		}
		return sonucListe;
	}

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (localName.equals("word")){
    		String ad = attributes.getValue("name");
    		kelime = new KaynakKelime(ad);
    		ceviriSinif = null;
    		deger = null;
    	} else {
    		if (localName.equals("lexical")){
        		ceviriSinif = attributes.getValue("class");
        		deger = null;    			
    		} else {
        		if (localName.equals("meaning")){
            		anlamSinif = attributes.getValue("class");
            		deger = null;
        		}    			
    		}
    	}
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Ceviri ceviri;
    	Anlam anlam;
    	if (localName.equals("lexicon")){
    		return;
    	} else {
    		if (localName.equals("word")){
    			kelimeler.add(kelime);
    		} else {
    			if (localName.equals("lexical")){
    				ceviriSinif = null;
    			} else {
    				if (localName.equals("meaning")){
    					if (anlamSinif != null){
    						anlam = new Anlam(anlamSinif, deger);
    					} else {
    						anlam = new Anlam(deger);
    					}
    					if (ceviriSinif != null){
    						ceviri = new Ceviri(ceviriSinif, anlam);
    					} else {
    						ceviri = new Ceviri(anlam);
    					}
    					kelime.ceviriEkle(ceviri);
    				}
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
