package com.mobile.filmarsivi;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;

public class FilmArsivi extends Activity implements
		ActionBar.TabListener {
	private Fragment filmListesi;
	private Fragment filmEkle;
	private Fragment turListesi;
	private Fragment turEkle;
	private Fragment yonetmenListesi;
	private Fragment yonetmenEkle;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ana_ekran);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText(R.string.filmListesi)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.filmEkle)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.turListesi)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.turEkle)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.yonetmenListesi)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.yonetmenEkle)
				.setTabListener(this));
	}
	
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		switch (tab.getPosition()){
			case 0:
				filmListesi = Fragment.instantiate(this, FilmListesi.class.getName());
				fragmentTransaction.add(android.R.id.content, filmListesi, "FilmListesi");
				break;
			case 1:
				filmEkle = Fragment.instantiate(this, FilmEkle.class.getName());					
				fragmentTransaction.add(android.R.id.content, filmEkle, "FilmEkle");
				break;
			case 2:
				turListesi = Fragment.instantiate(this, TurListesi.class.getName());
				fragmentTransaction.add(android.R.id.content, turListesi, "TurListesi");
				break;
			case 3:
				turEkle = Fragment.instantiate(this, TurEkle.class.getName());					
				fragmentTransaction.add(android.R.id.content, turEkle, "TurEkle");
				break;
			case 4:
				yonetmenListesi = Fragment.instantiate(this, YonetmenListesi.class.getName());
				fragmentTransaction.add(android.R.id.content, yonetmenListesi, "YonetmenListesi");
				break;
			case 5:
				yonetmenEkle = Fragment.instantiate(this, YonetmenEkle.class.getName());					
				fragmentTransaction.add(android.R.id.content, yonetmenEkle, "YonetmenEkle");
				break;
		}
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		switch (tab.getPosition()){
			case 0:
				fragmentTransaction.detach(filmListesi);
				break;
			case 1:
				fragmentTransaction.detach(filmEkle);
				break;
			case 2:
				fragmentTransaction.detach(turListesi);
				break;
			case 3:
				fragmentTransaction.detach(turEkle);
				break;
			case 4:
				fragmentTransaction.detach(yonetmenListesi);
				break;
			case 5:
				fragmentTransaction.detach(yonetmenEkle);
				break;
		}
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

}
