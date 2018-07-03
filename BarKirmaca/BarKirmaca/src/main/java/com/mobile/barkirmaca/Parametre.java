package com.mobile.barkirmaca;

import java.util.ArrayList;

public class Parametre {

	private int yasamSayisi;
	private Cubuk cubuk;
	private ArrayList<DusenTugla> dusenTuglalar;
	private ArrayList<Top> toplar;
	private Seviye seviye;
	private int puan;
	private int ekranGenislik;
	private int ekranYukseklik;

	public void yeniSeviye(Seviye seviye){
	    this.seviye = seviye;
	    cubuk = new Cubuk(ekranGenislik, ekranYukseklik);
	    toplar = new ArrayList<Top>();
	    toplar.add(new Top(cubuk.yer().left, cubuk.yer().top, ekranGenislik));
	    dusenTuglalar = new ArrayList<DusenTugla>();
	}

	public Parametre(int genislik, int yukseklik, Seviye seviye){
	    ekranGenislik = genislik;
	    ekranYukseklik = yukseklik;
	    yasamSayisi = 3;
	    puan = 0;
	    yeniSeviye(seviye);
	}
	
	public Seviye seviye(){
		return seviye;
	}
	
	public Cubuk cubuk(){
		return cubuk;
	}
	
	public int puan(){
		return puan;
	}
	
	public int ekranGenislik(){
		return ekranGenislik;
	}
	
	public int ekranYukseklik(){
		return ekranYukseklik;
	}
	
	public int yasamSayisi(){
		return yasamSayisi;
	}

	public int topSayisi(){
	    return toplar.size();
	}

	public Top top(int pozisyon){
	    return toplar.get(pozisyon);
	}

	public int dusenTuglaSayisi(){
	    return dusenTuglalar.size();
	}

	DusenTugla dusenTugla(int pozisyon){
	    return dusenTuglalar.get(pozisyon);
	}

	public boolean topSinirlarlaTemasKontrol(Top top){
	    Top yeniTop;
	    if (top.sinirlarlaTemas(ekranGenislik, ekranYukseklik)){
	        toplar.remove(top);
	        if (toplar.size() == 0){
	            yasamSayisi--;
	            if (yasamSayisi > 0){
	                yeniTop = new Top(cubuk.yer().left, cubuk.yer().top, ekranGenislik);
	                toplar.add(yeniTop);
	            } else {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	public boolean topTuglaylaTemasKontrol(Top top){
	    Tugla tugla;
	    DusenTugla dusenTugla;
	    for (int i = 0; i < seviye.satir(); i++){
	        for (int j = 0; j < seviye.sutun(); j++){
	            tugla = seviye.tugla(i, j);
	            if (!tugla.kirildiMi() && top.tuglaylaTemas(tugla)){
	                tugla.vuruldu();
	                if (tugla.kirildiMi()){
	                    if (tugla.tip() != TuglaTip.ZOR)
	                        puan += 10;
	                    else
	                        puan += 20;
	                    if (tugla.tip() != TuglaTip.NORMAL && tugla.tip() != TuglaTip.ZOR){
	                        dusenTugla = new DusenTugla(tugla.tip(), tugla.yer(), ekranYukseklik);
	                        dusenTuglalar.add(dusenTugla);                        
	                    }
	                    if (seviye.bittiMi())
	                        return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	public boolean topCubuklaTemasKontrol(Top top){
	    return top.cubuklaTemas(cubuk);
	}

	public boolean elleCubukTemas(int x, int y){
	    if (cubuk.yer().contains(x, y)){
	        return true;
	    } else {
	        return false;
	    }
	}

	public void cubukYeniX(int x){
	    if (x > 0 && x < ekranGenislik - cubuk.yer().width()){
	        cubuk.yeniPozisyon(x);        
	    }
	}

	public boolean dusenTuglaCubuklaTemasKontrol(){
	    Top yeniTop;
	    for (DusenTugla dusenTugla:dusenTuglalar){
	        if (dusenTugla.cubuklaTemas(cubuk)){
	            switch (dusenTugla.tip()){
	                case HIZLI:
	                    for (Top top:toplar){
	                        top.hizlandir();
	                    }
	                    break;
	                case YAVAS:
	                    for (Top top:toplar){
	                        top.yavaslat();
	                    }
	                    break;
	                case BUYUK:
	                    cubuk.buyult();
	                    break;
	                case KUCUK:
	                    cubuk.kucult();
	                    break;
	                case YASAM:
	                    yasamSayisi++;
	                    break;
	                case OLUM:
	                    yasamSayisi--;
	                    if (yasamSayisi == 0)
	                        return false;
	                    break;
	                case COKTOP:
	                    yeniTop = new Top(cubuk.yer().left, cubuk.yer().bottom, ekranGenislik);
	                    toplar.add(yeniTop);
	                    break;
	                default:
	                    break;
	            }
	            dusenTuglalar.remove(dusenTugla);
	            break;
	        }
	    }
	    return true;
	}

}
