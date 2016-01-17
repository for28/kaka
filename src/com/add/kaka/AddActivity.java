package com.add.kaka;

import com.example.kaka.R;
import com.example.kaka.R.id;
import com.tabs.kaka.EventsActivity;
import com.tabs.kaka.MyTabActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);
		final Button addEvent=(Button)findViewById(R.id.add_event);
		
		final Button addExam=(Button)findViewById(R.id.add_exam);
		final Button addHomework=(Button)findViewById(R.id.add_homework);
		final Button addMeeting=(Button)findViewById(R.id.add_meeting);
		final Button addNotice=(Button)findViewById(R.id.add_notice);
		final Button addHabbit=(Button)findViewById(R.id.add_habbit);
		
		//获取actionBar并设置
        ActionBar actionaBar =getActionBar();
        ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
        actionaBar.setBackgroundDrawable(actionBar_Color);
        getActionBar().setIcon(R.drawable.programs);
        setTitle("添加事件");
	}
	
	public void addEvents(View v){
		int key=v.getId();
		switch(key){
		case R.id.add_event:{
			 Toast.makeText(AddActivity.this, "添加事件", Toast.LENGTH_SHORT).show();
			 
			 break;
		}
		case R.id.add_exam:{
			Toast.makeText(AddActivity.this, "添加考试", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(AddActivity.this,ImportExamActivity.class);
			 startActivity(intent);
//			 finish();
			 break;
		}
		case R.id.add_habbit:{
			Toast.makeText(AddActivity.this, "添加习惯", Toast.LENGTH_SHORT).show();
			 break;
		}
		case R.id.add_homework:{
			
			Toast.makeText(AddActivity.this, "添加作业", Toast.LENGTH_SHORT).show();
			
			Intent intent=new Intent(AddActivity.this, EditHomeworkActivity.class);
			Bundle bundle=new Bundle();
			bundle.putString("content","");
			bundle.putString("datetime", "0");
			bundle.putString("alerttime", "0");
			intent.putExtra("android.intent.extra.INTENT", bundle);
			 startActivity(intent);
//			 finish();
			 break;
		}
		case R.id.add_meeting:{
			Toast.makeText(AddActivity.this, "添加会议", Toast.LENGTH_SHORT).show();
			 break;
		}
		case R.id.add_notice:{
			Toast.makeText(AddActivity.this, "添加公告", Toast.LENGTH_SHORT).show();
			 break;
			
		}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			//Intent intent=new Intent(AddActivity.this,MyTabActivity.class);
			//startActivity(intent);
//			finish();
			return true;
		}
		else{
		return super.onKeyDown(keyCode, event);
		}
	}
	//返回键调用的方法
	/*
	public void back(View v){
		finish();
		
	}*/

}
