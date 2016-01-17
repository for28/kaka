package com.example.kaka;

import com.tabs.kaka.MyTabActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		
		SharedPreferences preferences;
		SharedPreferences.Editor editor;
		preferences=this.getSharedPreferences("check", MODE_PRIVATE);
		editor=preferences.edit();
		
		boolean fristload=preferences.getBoolean("fristload", true);
		/*editor.putBoolean("fristload", true);
		editor.commit();*/
		if(fristload){
            new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent=new Intent(MainActivity.this,PagerActivity.class);
					startActivity(intent);
					finish();
				}
			}, 2300);	
		}
		else{
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,MyTabActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2300);
		}
		
		editor.putBoolean("fristload", false);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
