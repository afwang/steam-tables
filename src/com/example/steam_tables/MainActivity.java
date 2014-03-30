package com.example.steam_tables;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
//import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String PRESSURE_KEY = "pressure";
	private static final String TEMP_KEY = "temperature";
	
	private EditText pressure;
	private EditText temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pressure = (EditText)findViewById(R.id.pressure_input);
		temp = (EditText)findViewById(R.id.temperature_input);
		
		SharedPreferences inputs = getPreferences(MODE_PRIVATE);
		pressure.setText(Float.toString(inputs.getFloat(PRESSURE_KEY, 0)));
		temp.setText(Float.toString(inputs.getFloat(TEMP_KEY, 0)));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences inputs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = inputs.edit();
		
		//Use 2 try-catch blocks so separate attempts are made for
		//pressure and temperature
		try {
			editor.putFloat(PRESSURE_KEY,
				Float.parseFloat(pressure.getText().toString()));
		}
		catch(NumberFormatException e) {
		}
		
		try {
			editor.putFloat(TEMP_KEY,
				Float.parseFloat(temp.getText().toString()));
		}
		catch(NumberFormatException e) {
		}
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
