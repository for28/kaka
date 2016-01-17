package com.add.kaka;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.kaka.R;
import com.sqlite.kaka.DataBaseHelper;
import com.sqlite.kaka.SQLiteUtils;
import com.tabs.kaka.MyTabActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditHomeworkActivity extends BaseEditActivity {

	//private EventsInfo mEventInfo=new EventsInfo();
	//���ڽ���ȡ����ʱ����Ϊ�������id
	private Calendar currentCal;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.layout_base_event);
	//	addContentView(view, params);
		LinearLayout linearLayout = (LinearLayout)findViewById(com.example.kaka.R.id.base_event_test);
		Button button=new Button(this);
		button.setText("haha");
		linearLayout.addView(button);
		currentCal=Calendar.getInstance();
		
	}

	//actionBar�ĵ���¼�
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.comfirm:
				SQLiteUtils sqlite=new SQLiteUtils();
				DataBaseHelper db=SQLiteUtils.createDBHelper(EditHomeworkActivity.this);
				if(!editText.getText().toString().trim().equals("")){
					int y=currentCal.get(Calendar.YEAR);
					int m=currentCal.get(Calendar.MONTH)+1;
					int d=currentCal.get(Calendar.DAY_OF_MONTH);
					int h=currentCal.get(Calendar.HOUR_OF_DAY);
					int min=currentCal.get(Calendar.MINUTE);
					int s=currentCal.get(Calendar.SECOND);
					//������ת��Ϊ�ַ���
					String currentTime=Integer.toString(y)+Integer.toString(m)+
							Integer.toString(d)+Integer.toString(h)+
							Integer.toString(min)+Integer.toString(s);
					mEventsInfo.setBuildTime(currentTime);
					mEventsInfo.setHomework(editText.getText().toString());
					sqlite.inster(db, mEventsInfo, SQLiteUtils.HOMEWORK_TAG);
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			         String dateString = formatter.format(mEventsInfo.getDate());
					System.out.println("datetime:"+dateString);
					System.out.println("datetime_long:"+mEventsInfo.getDate());
					Toast.makeText(EditHomeworkActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(EditHomeworkActivity.this, "���ݲ���Ϊ��", Toast.LENGTH_LONG).show();
				}
				//Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
				//���沢������Activity
				Intent intenttTest = new Intent(this,MyTabActivity.class);
				
				/**
				 * ����ʹ�ñ��   ��   FLAG_ACTIVITY_CLEAR_TOP
				 * �������ǣ������Ҫ������Activity�ڵ�ǰ�������Ѿ����ڣ�
				 * ��ô����ջ�����Activity֮�ϵ����е�Activity���б����٣�
				 * ���Ұ����Activity��ʾ���û�����������ʶ��������Ҫ�ģ�
				 * ��Ϊ������Activity�൱��һ�����˵Ķ��������ͨ����Ӧ���ٴ���һ���µ���Activity��ʵ����
				 * �������տ��ܻ��ڵ�ǰ�����в���һ���ܳ���ӵ�ж����Activity�Ķ�ջ��
				 */
				intenttTest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intenttTest);
				return true;
			case android.R.id.home:
			//	Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
				//������Activity,
				Intent intent = new Intent(this,MyTabActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			default:
				break;
			}
			return super.onMenuItemSelected(featureId, item);
		}
	
}
