package com.mobile.filmarsivi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class FilmVeritabani{
	
   private SQLiteDatabase veriTabani;
   private DatabaseOpenHelper veritabaniYardimci; 

   public FilmVeritabani(Context context){
	   veritabaniYardimci = new DatabaseOpenHelper(context, "Film", null, 1);
   } 

   public void open() throws SQLException{
	   veriTabani = veritabaniYardimci.getWritableDatabase();
   }

   public void close(){
      if (veriTabani != null)
         veriTabani.close(); 
   }

   public void filmEkle(String ad, int sure, int yil, String tur, String yonetmen){
      ContentValues yeniFilm = new ContentValues();
      yeniFilm.put("ad", ad);
      yeniFilm.put("sure", sure);
      yeniFilm.put("yil", yil);
      yeniFilm.put("tur", tur);
      yeniFilm.put("yonetmen", yonetmen);
      open();
      veriTabani.insert("film", null, yeniFilm);
      close();
   }

   public void turEkle(String ad){
	   ContentValues yeniTur = new ContentValues();
	   yeniTur.put("ad", ad);
	   open();
	   veriTabani.insert("tur", null, yeniTur);
	   close();
   }

   public void yonetmenEkle(String ad, String soyad){
	   ContentValues yeniYonetmen = new ContentValues();
	   yeniYonetmen.put("ad", ad);
	   yeniYonetmen.put("soyad", soyad);
	   open();
	   veriTabani.insert("yonetmen", null, yeniYonetmen);
	   close();
   }

   public void filmDegistir(long id, String ad, int sure, int yil, String tur, String yonetmen){
      ContentValues yeniFilm = new ContentValues();
      yeniFilm.put("ad", ad);
      yeniFilm.put("sure", sure);
      yeniFilm.put("yil", yil);
      yeniFilm.put("tur", tur);
      yeniFilm.put("yonetmen", yonetmen);
      open();
      veriTabani.update("film", yeniFilm, "_id=" + id, null);
      close();
   }
   
   public void filmSil(long id){
	      open();
	      veriTabani.delete("film", "_id=" + id, null);
	      close();
   }

   public Cursor filmleriGetir(){
      return veriTabani.query("film", new String[] {"_id", "ad", "yil"}, null, null, null, null, "ad");
   }

   public Cursor turleriGetir(){
	   return veriTabani.query("tur", new String[] {"_id", "ad"}, null, null, null, null, "ad");
   }

   public Cursor yonetmenleriGetir(){
	   return veriTabani.query("yonetmen", new String[] {"_id", "ad", "soyad"}, null, null, null, null, "ad");
   }
   
   public Cursor filmGetir(long id){
      return veriTabani.query("film", null, "_id=" + id, null, null, null, null);
   }

   public Cursor turGetir(long id){
	  return veriTabani.query("tur", null, "_id=" + id, null, null, null, null);
   }

   public Cursor yonetmenGetir(long id){
	  return veriTabani.query("yonetmen", null, "_id=" + id, null, null, null, null);
   }
   
   private class DatabaseOpenHelper extends SQLiteOpenHelper{

	  public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version){
		  super(context, name, factory, version);
      }

      public void onCreate(SQLiteDatabase db){
          String sqlFilm = "CREATE TABLE film" +
                  "(_id integer primary key autoincrement," +
                  "ad TEXT, sure integer, yil integer, tur TEXT," +
                  "yonetmen TEXT);";
          db.execSQL(sqlFilm);
          String sqlTur = "CREATE TABLE tur" +
                  "(_id integer primary key autoincrement," +
                  "ad TEXT);";
          db.execSQL(sqlTur);
          String sqlYonetmen = "CREATE TABLE yonetmen" +
                  "(_id integer primary key autoincrement," +
                  "ad TEXT, soyad TEXT);";
          db.execSQL(sqlYonetmen);
      }

      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      }
   }
}
