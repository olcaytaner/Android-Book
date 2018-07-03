package com.mobile.satranctaslari;

import java.util.ArrayList;

public class Bulmaca {
	
	public static final int BOS = -1;	
	private ArrayList<Kare> yerlestirilecekYerler;
	private ArrayList<Tehdit> tehditler;
	
	public Bulmaca(){
		yerlestirilecekYerler = new ArrayList<Kare>();
		tehditler = new ArrayList<Tehdit>();
	}
	
	void yerlestirilecekYerEkle(Kare yer){
	    yerlestirilecekYerler.add(yer);
	}

	int yerlestirilecekYerNo(int x, int y){
	    int i;
	    for (i = 0; i < yerlestirilecekYerler.size(); i++){
	        Kare kare = yerlestirilecekYerler.get(i);
	        if (kare.getX() == x && kare.getY() == y){
	            return i;
	        }
	    }
	    return -1;
	}

	int yerlestirilecekYerSayisi(){
	    return yerlestirilecekYerler.size();
	}

	Kare yerlestirilecekYer(int pozisyon){
	    return yerlestirilecekYerler.get(pozisyon);
	}

	int tehditSayisi(){
	    return tehditler.size();
	}

	void tehditEkle(Tehdit tehdit){
	    tehditler.add(tehdit);
	}

	Tehdit tehdit(int pozisyon){
	    return tehditler.get(pozisyon);
	}

	int sahKontrol(Cozum cozum, int x, int y){
	    int i, j, kim;
	    for (i = -1; i < 2; i++)
	        for (j = -1; j < 2; j++){
	            kim = yerlestirilecekYerNo(x + i,y + j);
	            if (kim != -1 && cozum.tasAd(kim).equalsIgnoreCase("sah"))
	                return 1;
	        }
	    return 0;
	}

	int filKontrol(Cozum cozum, int x, int y){
	    int xArtis[] = {1, 1, -1, -1};
	    int yArtis[] = {1, -1, 1, -1};
	    int i, j, kim, count = 0, a, b;
	    for (j = 0; j < 4; j++){
	        for (i = 1; i < 8; i++){
	            a = x + i * xArtis[j];
	            b = y + i * yArtis[j];
	            kim = yerlestirilecekYerNo(a, b);
	            if (kim != BOS){
	                if (cozum.tasAd(kim).startsWith("fil"))
	                    count++;
	                else
	                    break;
	            }
	        }
	    }
	    return count;
	}

	int kaleKontrol(Cozum cozum, int x, int y){
	    int xArtis[] = {1, -1, 0, 0};
	    int yArtis[] = {0, 0, 1, -1};
	    int i, j, kim, count = 0, a, b;
	    for (j = 0; j < 4; j++){
	        for (i = 1; i < 8; i++){
	            a = x + i * xArtis[j];
	            b = y + i * yArtis[j];
	            kim = yerlestirilecekYerNo(a, b);
	            if (kim != BOS){
	                if (cozum.tasAd(kim).startsWith("kale"))
	                    count++;
	                else
	                    break;
	            }
	        }
	    }
	    return count;
	}

	int vezirKontrol(Cozum cozum, int x, int y){
	    int xArtis[] = {1, 1, -1, -1, 1, -1, 0, 0};
	    int yArtis[] = {1, -1, 1, -1, 0, 0, 1, -1};
	    int i, j, kim, a, b;
	    for (j = 0; j < 8; j++){
	        for (i = 1; i < 8; i++){
	            a = x + i * xArtis[j];
	            b = y + i * yArtis[j];
	            kim = yerlestirilecekYerNo(a, b);
	            if (kim != BOS){
	                if (cozum.tasAd(kim).equalsIgnoreCase("vezir"))
	                    return 1;
	                else
	                    break;
	            }
	        }
	    }
	    return 0;
	}

	int atKontrol(Cozum cozum, int x, int y){
	    int xArtis[] = {1, 2, 1, -1, -1, 2, -2, -2};
	    int yArtis[] = {-2, -1, 2, 2, -2, 1, 1, -1};
	    int j, kim, a, b, count = 0;
	    for (j = 0; j < 8; j++){
	        a = x + xArtis[j];
	        b = y + yArtis[j];
	        kim = yerlestirilecekYerNo(a, b);
	        if (kim != BOS){
	            if (cozum.tasAd(kim).startsWith("at"))
	                count++;
	            else
	                break;
	        }
	    }
	    return count;
	}

	boolean sartlariSaglar(Cozum cozum){
	    int i, x, y, count;
	    for (i = 0; i < tehditler.size(); i++){
	        Tehdit tehdit = tehditler.get(i);
	        x = tehdit.getX();
	        y = tehdit.getY();
	        count = sahKontrol(cozum, x, y) + filKontrol(cozum, x, y) + kaleKontrol(cozum, x, y) + vezirKontrol(cozum, x, y) + atKontrol(cozum, x, y);
	        if (count != tehdit.getTehditSayisi())
	            return false;
	    }
	    return true;
	}

	boolean sartlariSimdilikSaglar(Cozum cozum){
	    int i, x, y, count;
	    for (i = 0; i < tehditler.size(); i++){
	        Tehdit tehdit = tehditler.get(i);
	        x = tehdit.getX();
	        y = tehdit.getY();
	        count = sahKontrol(cozum, x, y) + filKontrol(cozum, x, y) + kaleKontrol(cozum, x, y) + vezirKontrol(cozum, x, y) + atKontrol(cozum, x, y);
	        if (count > tehdit.getTehditSayisi())
	            return false;
	    }
	    return true;
	}

}
