package com.mobile.ehliyetsinavi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Konular extends Activity {
	private CheckBox trafik;
	private CheckBox motor;
	private CheckBox ilkYardim;
	private TextView trafikSayac;
	private TextView motorSayac;
	private TextView ilkYardimSayac;
	private SeekBar trafikGosterge;
	private SeekBar motorGosterge;
	private SeekBar ilkYardimGosterge;
	private Tercihler tercihler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.konular);
		Button sinaviYenidenBaslat = (Button) findViewById(R.id.sinaviYenidenBaslat);
		Button sinavaGeriDon = (Button) findViewById(R.id.sinavaGeriDon);
		trafik = (CheckBox) findViewById(R.id.trafik);
		motor = (CheckBox) findViewById(R.id.motor);
		ilkYardim = (CheckBox) findViewById(R.id.ilkYardim);
		trafikSayac = (TextView) findViewById(R.id.trafikSayac);
		motorSayac = (TextView) findViewById(R.id.motorSayac);
		ilkYardimSayac = (TextView) findViewById(R.id.ilkYardimSayac);
		trafikGosterge = (SeekBar) findViewById(R.id.trafikGosterge);
		motorGosterge = (SeekBar) findViewById(R.id.motorGosterge);
		ilkYardimGosterge = (SeekBar) findViewById(R.id.ilkYardimGosterge);
		sinaviYenidenBaslat.setOnClickListener(sinaviYenidenBaslatTikla);
		sinavaGeriDon.setOnClickListener(sinavaGeriDonTikla);
		trafik.setOnCheckedChangeListener(konuTikla);
		motor.setOnCheckedChangeListener(konuTikla);
		ilkYardim.setOnCheckedChangeListener(konuTikla);
		trafikGosterge.setOnSeekBarChangeListener(konuDegerDegistir);
		motorGosterge.setOnSeekBarChangeListener(konuDegerDegistir);
		ilkYardimGosterge.setOnSeekBarChangeListener(konuDegerDegistir);
		Intent konularIntent = getIntent();
		tercihler = (Tercihler)konularIntent.getSerializableExtra("tercihler");
		trafik.setChecked(tercihler.trafikVarMi());
		motor.setChecked(tercihler.motorVarMi());
		ilkYardim.setChecked(tercihler.ilkYardimVarMi());
		trafikGosterge.setProgress(tercihler.getTrafikSoruSayisi());
		motorGosterge.setProgress(tercihler.getMotorSoruSayisi());
		ilkYardimGosterge.setProgress(tercihler.getIlkYardimSoruSayisi());
		trafikSayac.setText(String.format("%d", tercihler.getTrafikSoruSayisi()));
		motorSayac.setText(String.format("%d", tercihler.getMotorSoruSayisi()));
		ilkYardimSayac.setText(String.format("%d", tercihler.getIlkYardimSoruSayisi()));
	}
	
	public OnClickListener sinaviYenidenBaslatTikla = new OnClickListener() {
		public void onClick(View v){
			((Konular) v.getContext()).setResult(RESULT_OK);
			finish();
		}
	};
	
	public OnClickListener sinavaGeriDonTikla = new OnClickListener() {
		public void onClick(View v){
			((Konular) v.getContext()).setResult(RESULT_CANCELED);
			finish();
		}
	};

	public OnCheckedChangeListener konuTikla = new OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){	
			SharedPreferences temelTercihler = getSharedPreferences("EhliyetSinavi", MODE_PRIVATE);
			SharedPreferences.Editor temelTercihlerDegistir = temelTercihler.edit();
			if (((String)buttonView.getTag()).equalsIgnoreCase("1")){
				temelTercihlerDegistir.putBoolean("trafikVarMi", isChecked);
			} else {
				if (((String)buttonView.getTag()).equalsIgnoreCase("2")){
					temelTercihlerDegistir.putBoolean("motorVarMi", isChecked);
				} else {
					if (((String)buttonView.getTag()).equalsIgnoreCase("3")){
						temelTercihlerDegistir.putBoolean("ilkYardimVarMi", isChecked);
					}
				}
			}
			temelTercihlerDegistir.apply();
		}		
	};
	
	public OnSeekBarChangeListener konuDegerDegistir = new OnSeekBarChangeListener(){
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			SharedPreferences temelTercihler = getSharedPreferences("EhliyetSinavi", MODE_PRIVATE);
			SharedPreferences.Editor temelTercihlerDegistir = temelTercihler.edit();
			if (((String)arg0.getTag()).equalsIgnoreCase("1")){
				temelTercihlerDegistir.putInt("trafikSoruSayisi", arg1);
				trafikSayac.setText(String.format("%d", arg1));
			} else {
				if (((String)arg0.getTag()).equalsIgnoreCase("2")){
					temelTercihlerDegistir.putInt("motorSoruSayisi", arg1);
					motorSayac.setText(String.format("%d", arg1));
				} else {
					if (((String)arg0.getTag()).equalsIgnoreCase("3")){
						temelTercihlerDegistir.putInt("ilkYardimSoruSayisi", arg1);
						ilkYardimSayac.setText(String.format("%d", arg1));
					}
				}
			}
			temelTercihlerDegistir.apply();
		}
		public void onStartTrackingTouch(SeekBar arg0) {
		}
		public void onStopTrackingTouch(SeekBar arg0) {
		}		
	};

}
