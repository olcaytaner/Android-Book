package com.mobile.satranctaslari;

import java.util.ArrayList;

public class Cozum {
	
	private ArrayList<Tas> taslar;
	private Tas tumTaslar[] = {new Tas("sah"), new Tas("vezir"), new Tas("kale1"), new Tas("kale2"), 
			new Tas("fil1"), new Tas("fil2"), new Tas("at1"), new Tas("at2")};
	
	public Cozum(){
		taslar = new ArrayList<Tas>();
	}
	
	String tasAd(int pozisyon){
	    if (pozisyon >= 0 && pozisyon < taslar.size())
	        return taslar.get(pozisyon).getAd();
	    else
	        return "bos";
	}

	int tasNo(int pozisyon){
	    for (int i = 0; i < tumTaslar.length; i++){
	        if (taslar.get(pozisyon).getAd().equalsIgnoreCase(tumTaslar[i].getAd())){
	            return i;
	        }
	    }
	    return -1;
	}

	int tasSayisi(){
	    return taslar.size();
	}

	void tasEkle(Tas tas){
	    taslar.add(tas);
	}

	void tasNoIleEkle(int tas){
	    if (tas >= 0 && tas < 8){
	        taslar.add(tumTaslar[tas]);
	    }
	}

	void tasCikar(){
	    taslar.remove(taslar.size() - 1);
	}

	ArrayList<Tas> adaylariOlustur(){
	    boolean bulundu;
	    ArrayList<Tas> aday = new ArrayList<Tas>();
	    for (Tas olasiTas:tumTaslar){
	        bulundu = false;
	        for (Tas tas:taslar){
	            if (tas.getAd().equalsIgnoreCase(olasiTas.getAd())){
	                bulundu = true;
	                break;
	            }
	        }
	        if (!bulundu){
	            aday.add(olasiTas);
	        }
	    }
	    return aday;
	}

}
