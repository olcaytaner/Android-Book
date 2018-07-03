package com.mobile.barkirmaca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Ekran extends View{
	
	public Parametre parametre = null;
	private int fark;
	private boolean hareketBasladi = false;
	
	Paint tuglaRenk(TuglaTip tip){
		Paint boya = new Paint();
	    switch (tip){
	        case NORMAL:
	        	boya.setColor(Color.BLACK);
	            break;
	        case ZOR:
	        	boya.setColor(Color.rgb(165, 42, 42));
	            break;
	        case HIZLI:
	        	boya.setColor(Color.BLUE);
	            break;
	        case YAVAS:
	        	boya.setColor(Color.CYAN);
	            break;
	        case BUYUK:
	        	boya.setColor(Color.rgb(255, 165, 0));
	            break;
	        case KUCUK:
	        	boya.setColor(Color.GREEN);
	            break;
	        case YASAM:
	        	boya.setColor(Color.MAGENTA);
	            break;
	        case OLUM:
	        	boya.setColor(Color.RED);
	            break;
	        case COKTOP:
	        	boya.setColor(Color.YELLOW);
	            break;
	    }
	    boya.setStyle(Style.FILL);
	    return boya;
	}

    @Override
    public void onDraw(Canvas canvas) {
        Seviye seviye;
        Tugla cizilenTugla;
        Cubuk cubuk;
        Top top;
        Paint tuglaRenk, kenar, cubukRenk, topRenk, yazi;
        DusenTugla dusenTugla;
        int fontBuyukluk;
        Rect yaziBuyukluk;
        if (parametre == null){
        	return;
        }
        kenar = new Paint();
        kenar.setStyle(Style.STROKE);
        kenar.setColor(Color.GRAY);
        seviye = parametre.seviye();
        for (int i = 0; i < seviye.satir(); i++){
            for (int j = 0; j < seviye.sutun(); j++){
                cizilenTugla = seviye.tugla(i, j);
                if (!cizilenTugla.kirildiMi()){
                	tuglaRenk = tuglaRenk(cizilenTugla.tip());
                    canvas.drawRect(cizilenTugla.yer(), tuglaRenk);
                    canvas.drawRect(cizilenTugla.yer(), kenar);
                }
            }
        }
        cubuk = parametre.cubuk();
        cubukRenk = new Paint();
        cubukRenk.setStyle(Style.FILL);
        cubukRenk.setColor(Color.DKGRAY);
        canvas.drawRect(cubuk.yer(), cubukRenk);
        canvas.drawRect(cubuk.yer(), kenar);
        topRenk = new Paint();
        topRenk.setStyle(Style.FILL);
        topRenk.setColor(Color.rgb(128, 0, 128));
        for (int i = 0; i < parametre.topSayisi(); i++){
            top = parametre.top(i);
            RectF alan = new RectF(top.merkez().x - top.yariCap(), top.merkez().y - top.yariCap(), top.merkez().x + top.yariCap(), top.merkez().y + top.yariCap());
            canvas.drawOval(alan, topRenk);
        }
        for (int i = 0; i < parametre.dusenTuglaSayisi(); i++){
            dusenTugla = parametre.dusenTugla(i);
            tuglaRenk = tuglaRenk(dusenTugla.tip());
            canvas.drawOval(new RectF(dusenTugla.yer()), tuglaRenk);
        }
        top = parametre.top(0);
        for (int i = 0; i < parametre.yasamSayisi(); i++){
            RectF alan = new RectF((int)(5 + i * 2 * top.yariCap()), (int) (parametre.cubuk().yer().bottom + 1.4 * parametre.cubuk().yer().height()), (int) (5 + i * 2 * top.yariCap() + 1.6 * top.yariCap()), (int) (parametre.cubuk().yer().bottom + 1.4 * parametre.cubuk().yer().height() + 1.6 * top.yariCap()));
            canvas.drawOval(alan, topRenk);
        }
        String puan = "" + parametre.puan();
        fontBuyukluk = parametre.ekranGenislik() * 12 / 300;
        yazi = new Paint();
        yazi.setTextSize(fontBuyukluk);
        yazi.setColor(Color.rgb(128, 0, 128));
        yaziBuyukluk = new Rect();
        yazi.getTextBounds(puan, 0, puan.length(), yaziBuyukluk);
        canvas.drawText(puan, parametre.ekranGenislik() - yaziBuyukluk.width(), parametre.ekranYukseklik() - yaziBuyukluk.height(), yazi);
        String seviyeYazi = "Seviye " + (parametre.seviye().seviyeNo() + 1);
        fontBuyukluk = parametre.ekranGenislik() * 12 / 150;
        yazi.setTextSize(fontBuyukluk);
        yaziBuyukluk = new Rect();
        yazi.getTextBounds(seviyeYazi, 0, seviyeYazi.length(), yaziBuyukluk);
        canvas.drawText(seviyeYazi, parametre.ekranGenislik() / 2 - yaziBuyukluk.width() / 2, parametre.ekranYukseklik() / 2 - yaziBuyukluk.height() / 2, yazi);
    }

    public Ekran(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            if (parametre.elleCubukTemas(x, y)){
                fark = x - parametre.cubuk().yer().left;
                hareketBasladi = true;
            } else {
                hareketBasladi = false;
            }
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            if (hareketBasladi){
                parametre.cubukYeniX(x - fark);
                invalidate();
            }
            hareketBasladi = false;
            return true;
        } else if (action == MotionEvent.ACTION_MOVE){
            if (hareketBasladi){
                parametre.cubukYeniX(x - fark);
                invalidate();
            }
        }
        return false;
    }

}
