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
		
		//����һ��tabhost����
		TabHost tabhost1=getTabHost();
		
		Intent intent1=new Intent();
		intent1.setClass(this, EventsActivity.class);
		
		//����һҳѡ�
		TabHost.TabSpec spec1=tabhost1.newTabSpec("�¼�");
		Resources resources=getResources();
		
		//����ѡ��İ�ť
		spec1.setIndicator("�¼�");
		//����ѡ������ݲ���
		spec1.setContent(intent1);
		//����Ϣѡ����뵽tabhost��
		tabhost1.addTab(spec1);		
		
		//����һ������tabhost����
		TabHost tabhost2=getTabHost();		
		Intent intent2=new Intent();
	    intent2.setClass(this, NoticesActivity.class);
		TabHost.TabSpec spec2=tabhost2.newTabSpec("����");
		spec2.setIndicator("����");
		spec2.setContent(intent2);
		tabhost2.addTab(spec2);	
		
		
		//����һ����Ϣtabhost����
		TabHost tabhost3=getTabHost();		
		Intent intent3=new Intent();
	    intent3.setClass(this, MessagesActivity.class);
		TabHost.TabSpec spec3=tabhost3.newTabSpec("��Ϣ");
		spec3.setIndicator("��Ϣ");
		spec3.setContent(intent3);
		tabhost3.addTab(spec3);	
		
		//����һ����ϵ��tabhost����
		TabHost tabhost4=getTabHost();		
		Intent intent4=new Intent();
		intent4.setClass(this, ContactsActivity.class);
		TabHost.TabSpec spec4=tabhost3.newTabSpec("��ϵ��");
		//spec4.setIndicator("��ϵ��", resources.getDrawable(R.drawable.btn_operation_nor));
		spec4.setIndicator("��ϵ��");
		spec4.setContent(intent4);
		tabhost4.addTab(spec4);	
		
		message=(TextView)findViewById(R.id.message);
		message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MyTabActivity.this, "�޸ĸ�����Ϣ", Toast.LENGTH_SHORT).show();
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
