package com.tabs.kaka;

import java.util.zip.Inflater;

import com.add.kaka.AddActivity;
import com.example.kaka.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MyTabActivity extends TabActivity{

	TextView message;
	
	Button addButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		//创建一个tabhost对象
		TabHost tabhost1=getTabHost();
		
		Intent intent1=new Intent();
		intent1.setClass(this, EventsActivity.class);
		
		//创建一页选项卡
		TabHost.TabSpec spec1=tabhost1.newTabSpec("事件");
		Resources resources=getResources();
		
		//创建选项卡的按钮
		spec1.setIndicator("事件");
		//创建选项卡的内容布局
		spec1.setContent(intent1);
		//把消息选项卡加入到tabhost中
		tabhost1.addTab(spec1);		
		
		//创建一个公告tabhost对象
		TabHost tabhost2=getTabHost();		
		Intent intent2=new Intent();
	    intent2.setClass(this, NoticesActivity.class);
		TabHost.TabSpec spec2=tabhost2.newTabSpec("公告");
		spec2.setIndicator("公告");
		spec2.setContent(intent2);
		tabhost2.addTab(spec2);	
		
		
		//创建一个消息tabhost对象
		TabHost tabhost3=getTabHost();		
		Intent intent3=new Intent();
	    intent3.setClass(this, MessagesActivity.class);
		TabHost.TabSpec spec3=tabhost3.newTabSpec("消息");
		spec3.setIndicator("消息");
		spec3.setContent(intent3);
		tabhost3.addTab(spec3);	
		
		//创建一个联系人tabhost对象
		TabHost tabhost4=getTabHost();		
		Intent intent4=new Intent();
		intent4.setClass(this, ContactsActivity.class);
		TabHost.TabSpec spec4=tabhost3.newTabSpec("联系人");
		//spec4.setIndicator("联系人", resources.getDrawable(R.drawable.btn_operation_nor));
		spec4.setIndicator("联系人");
		spec4.setContent(intent4);
		tabhost4.addTab(spec4);	
		
		message=(TextView)findViewById(R.id.message);
		message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MyTabActivity.this, "修改个人信息", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
/*	
	public void addEvent(View v){
		
		Intent intent=new Intent(MyTabActivity.this,AddActivity.class);
		startActivityForResult( intent,0);
		//finish();
	}*/
}
