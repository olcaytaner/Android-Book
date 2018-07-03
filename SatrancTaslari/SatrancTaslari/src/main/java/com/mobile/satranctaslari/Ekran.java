package com.mobile.satranctaslari;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Ekran extends View{
	
	private Bitmap sah, kale, fil, at, vezir, rakamlar[];
	public int tablo[];
	public boolean tahtayaKonduMu[];
	public Bulmaca bulmaca = null;
	public boolean hareketEdiyor;
	public int hareketX, hareketY;
	public int hangiTas;
	public int hucreGenislik;
	
    Bitmap tasResim(int tas){
        switch (tas){
            case 0:return sah;
            case 1:return vezir;
            case 2:
            case 3:return kale;
            case 4:
            case 5:return fil;
            case 6:
            case 7:return at;
            default:return sah;
        }
    }
    
    public void drawDigit(Canvas canvas, int x, int y, int digit){
        int W, H, w = rakamlar[digit].getWidth(), h = rakamlar[digit].getHeight(), l = hucreGenislik / 2;
        if (h > w){
            W = w * l / h;
            canvas.drawBitmap(rakamlar[digit], null, new RectF(x + (hucreGenislik - l) / 2 + l / 2 - W / 2, y + (hucreGenislik - l) / 2, x + (hucreGenislik - l) / 2 + l / 2 + W / 2, y + (hucreGenislik + l) / 2), null);
        } else {
            H = h * l / w;
            canvas.drawBitmap(rakamlar[digit], null, new RectF(x + (hucreGenislik - l) / 2, y + (hucreGenislik - l) / 2 + l / 2 - H / 2, x + (hucreGenislik + l) / 2, y + (hucreGenislik - l) / 2 + l / 2 + H / 2), null);
        }
    }
	
    @Override
    public void onDraw(Canvas canvas) {
        Paint black = new Paint(), blue = new Paint();
        int i, posx, posy;
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.STROKE);
        black.setTextSize(hucreGenislik / 2);
        super.onDraw(canvas);
        for (i = 0; i < 9; i++){
            canvas.drawLine((i + 1) * hucreGenislik, hucreGenislik, (i + 1) * hucreGenislik, 9 * hucreGenislik, black);
            canvas.drawLine(hucreGenislik, (i + 1) * hucreGenislik, 9 * hucreGenislik, (i + 1) * hucreGenislik, black);
        }
        if (bulmaca != null){
            blue.setColor(Color.BLUE);
            blue.setStyle(Paint.Style.STROKE);
            blue.setStrokeWidth(3);
            for (i = 0; i < bulmaca.yerlestirilecekYerSayisi(); i++){
            	Kare kare = bulmaca.yerlestirilecekYer(i);
                posx = kare.getX();
                posy = kare.getY();
                if (tablo[i] == Bulmaca.BOS){
                    canvas.drawRect(new Rect((posy + 1) * hucreGenislik, (posx + 1) * hucreGenislik, (posy + 2) * hucreGenislik, (posx + 2) * hucreGenislik), blue);
                } else {
                    if (hareketEdiyor && hangiTas >= 8 && hangiTas - 8 == i){
                        canvas.drawBitmap(tasResim(tablo[i]), null, new RectF(hareketX, hareketY, hareketX + hucreGenislik - 2, hareketY + hucreGenislik - 2), null);
                    } else {
                        canvas.drawBitmap(tasResim(tablo[i]), null, new RectF((posy + 1) * hucreGenislik + 1, (posx + 1) * hucreGenislik + 1, (posy + 2) * hucreGenislik - 1, (posx + 2) * hucreGenislik - 1), null);
                    }
                }
            }
            for (i = 0; i < bulmaca.tehditSayisi(); i++){
            	Tehdit tehdit = bulmaca.tehdit(i);
                posx = tehdit.getX();
                posy = tehdit.getY();
                drawDigit(canvas, (posy + 1) * hucreGenislik, (posx + 1) * hucreGenislik, tehdit.getTehditSayisi());
            }        	
            for (i = 0; i < 8; i++){
                if (!tahtayaKonduMu[i]){
                    if (hareketEdiyor && hangiTas < 8 && hangiTas == i){
                        canvas.drawBitmap(tasResim(i), null, new RectF(hareketX, hareketY, hareketX + hucreGenislik - 2, hareketY + hucreGenislik - 2), null);
                    } else {
                        canvas.drawBitmap(tasResim(i), null, new RectF((i + 1) * hucreGenislik + 1, 9 * hucreGenislik + 1, (i + 2) * hucreGenislik - 1, 10 * hucreGenislik - 1), null);
                    }
                }
            }
        }
    }

    public Ekran(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources mRes;
        mRes = context.getResources();
        sah = BitmapFactory.decodeResource(mRes, R.drawable.sah);
        vezir = BitmapFactory.decodeResource(mRes, R.drawable.vezir);
        kale = BitmapFactory.decodeResource(mRes, R.drawable.kale);
        fil = BitmapFactory.decodeResource(mRes, R.drawable.fil);
        at = BitmapFactory.decodeResource(mRes, R.drawable.at);
        rakamlar = new Bitmap[9];
        rakamlar[0] = BitmapFactory.decodeResource(mRes, R.drawable.sayi0);
        rakamlar[1] = BitmapFactory.decodeResource(mRes, R.drawable.sayi1);
        rakamlar[2] = BitmapFactory.decodeResource(mRes, R.drawable.sayi2);
        rakamlar[3] = BitmapFactory.decodeResource(mRes, R.drawable.sayi3);
        rakamlar[4] = BitmapFactory.decodeResource(mRes, R.drawable.sayi4);
        rakamlar[5] = BitmapFactory.decodeResource(mRes, R.drawable.sayi5);
        rakamlar[6] = BitmapFactory.decodeResource(mRes, R.drawable.sayi6);
        rakamlar[7] = BitmapFactory.decodeResource(mRes, R.drawable.sayi7);
        rakamlar[8] = BitmapFactory.decodeResource(mRes, R.drawable.sayi8);
        hucreGenislik = 70;
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int posx, posy, pozisyon;
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            if (y < 9 * hucreGenislik){
                posx = x / hucreGenislik - 1;
                posy = y / hucreGenislik - 1;
                pozisyon = bulmaca.yerlestirilecekYerNo(posy, posx);
                if (pozisyon != -1 && tablo[pozisyon] != Bulmaca.BOS){
                    hangiTas = pozisyon + 8;
                    hareketEdiyor = true;
                }
            } else {
                posx = x / hucreGenislik - 1;
                if (posx >= 0 && posx < 8 && !tahtayaKonduMu[posx]){
                    hangiTas = posx;
                    hareketEdiyor = true;
                }
            }
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            hareketEdiyor = false;
            if (y < 9 * hucreGenislik){
                posx = x / hucreGenislik - 1;
                posy = y / hucreGenislik - 1;
                if (posx >= 0 && posx < 8 && posy >= 0 && posy < 8){
                    pozisyon = bulmaca.yerlestirilecekYerNo(posy, posx);
                    if (pozisyon != -1 && tablo[pozisyon] == Bulmaca.BOS && hangiTas >= 0 && hangiTas < 8 && !tahtayaKonduMu[hangiTas]){
                        tahtayaKonduMu[hangiTas] = true;
                        tablo[pozisyon] = hangiTas;
                        invalidate();
                    }
                }
            } else {
                posx = x / hucreGenislik - 1;
                if (posx >= 0 && posx < 8 && hangiTas >= 8 && tahtayaKonduMu[tablo[hangiTas - 8]]){
                    tahtayaKonduMu[tablo[hangiTas - 8]] = false;
                    tablo[hangiTas - 8] = Bulmaca.BOS;
                    invalidate();
                }
            }
            invalidate();
            return true;
        } else if (action == MotionEvent.ACTION_MOVE && hareketEdiyor){
            hareketX = (int) event.getX() - hucreGenislik / 2;
            hareketY = (int) event.getY() - hucreGenislik / 2;
            invalidate();
        }
        return false;
    }

}
