package com.example.kaka;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity{

	TextView message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		message=(TextView)findViewById(R.id.message);
		message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MenuActivity.this, "修改个人信息", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
