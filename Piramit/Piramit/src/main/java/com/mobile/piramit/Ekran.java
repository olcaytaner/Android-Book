package com.mobile.piramit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Ekran extends View{
	public Bulmaca bulmaca;
	public int hucreGenislik;
	
    public Ekran(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
    }
    
    @Override
    public void onDraw(Canvas canvas) {
        int x1, y1, x2, y2;
        Paint siyah = new Paint(), mavi = new Paint(), yazi = new Paint();
        siyah.setColor(Color.BLACK);
        siyah.setStyle(Paint.Style.STROKE);
        siyah.setStrokeWidth(1);
        mavi.setColor(Color.BLUE);
        mavi.setStyle(Paint.Style.STROKE);
        mavi.setStrokeWidth(3);
        if (bulmaca != null){
            for (int i = 0; i < bulmaca.getBuyukluk(); i++){
                for (int j = 0; j <= i; j++){
                    x1 = (int) ((bulmaca.getBuyukluk() - i + 1 + 2 * j) / 2.0) * hucreGenislik;
                    y1 = (i + 1) * hucreGenislik;
                    x2 = x1 + hucreGenislik;
                    y2 = y1 + hucreGenislik;
                    Rect alan = new Rect(x1, y1, x2, y2);
                    if (bulmaca.oynananDeger(i) != j){
                        canvas.drawRect(alan, siyah);
                    } else {
                        canvas.drawRect(alan, mavi);
                    }
                    String sayi = "" + bulmaca.sayi(i, j);
                    int fontBuyukluk = (int) (hucreGenislik / 1.5);
                    yazi.setTextSize(fontBuyukluk);
                    Rect yaziBuyukluk = new Rect();
                    yazi.getTextBounds(sayi, 0, 1, yaziBuyukluk);
                    canvas.drawText(sayi, alan.left + (hucreGenislik - yaziBuyukluk.width()) / 2, (float) (alan.top + hucreGenislik - yaziBuyukluk.height() / 2), yazi);
                }
            }
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x1, y1, x2, y2;
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 0; i < bulmaca.getBuyukluk(); i++){
            for (int j = 0; j <= i; j++){
                x1 = (int) ((bulmaca.getBuyukluk() - i + 1 + 2 * j) / 2.0) * hucreGenislik;
                y1 = (i + 1) * hucreGenislik;
                x2 = x1 + hucreGenislik;
                y2 = y1 + hucreGenislik;
                Rect alan = new Rect(x1, y1, x2, y2);
                if (alan.contains(x,  y)){
                	bulmaca.oyna(i, j);
                	invalidate();
                	return true;
                }
            }
        }
    	return true;
    }
    
}
