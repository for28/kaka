package com.add.kaka;

import java.text.SimpleDateFormat;

import com.dialog.kaka.DateTimePickerDialog;
import com.dialog.kaka.DateTimePickerDialog.OnDateTimeSetListener;
import com.example.kaka.R;
import com.tabs.kaka.MyTabActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//ͷ����menu
public class EditExamActivity extends Activity {
	TextView examTime,examTimeRemind;
	EditText examName;
	public EventsInfo mExamEventsInfo=new EventsInfo();
	
	public boolean onCreateOptionsMenu(Menu menu) {
		//return super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_exam);
		 ActionBar actionaBar =getActionBar();
	     ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
	     actionaBar.setBackgroundDrawable(actionBar_Color);
	     getActionBar().setIcon(R.drawable.programs);
	     actionaBar.setHomeButtonEnabled(true);
	     Intent intent = getIntent();
	     examName=(EditText)findViewById(R.id.edit_exam_name);
	     examName.setText(intent.getExtras().getString("examName"));	     
	}

	public void examTimeOnclickListener(View view){
		examTime=(TextView)findViewById(R.id.edit_exam_time);
		Toast.makeText(this, "fdsf", Toast.LENGTH_SHORT).show();
		//�������һ��ѡ�����ڵĶԻ���
		DateTimePickerDialog dialog=
				new DateTimePickerDialog(EditExamActivity.this, mExamEventsInfo.getDate(),mExamEventsInfo);
		//������ɵĻص�����
		 dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
	            public void OnDateTimeSet(AlertDialog dialog, long date){
//	            	Toast.makeText(BaseEventActivity.this,
//	                        "������������ǣ�" + getStringDate(date), Toast.LENGTH_LONG)
//	                        .show();
	            	CharSequence newDate=getStringDate(date);
	            	examTime.setText(newDate);
	            	
	            	mExamEventsInfo.setDate(date);
//	            	�������ڵ�EventsInfo������
	            	int y=Integer.parseInt(newDate.toString().substring(0, 4));
	            	int m=Integer.parseInt(newDate.toString().substring(5, 7));
	            	int d=Integer.parseInt(newDate.toString().substring(8, 10));
	            	int h=Integer.parseInt(newDate.toString().substring(11, 13));
	            	int min=Integer.parseInt(newDate.toString().substring(14, 16));
	            	mExamEventsInfo.setYear(y);
	            	mExamEventsInfo.setMonth(m);
	            	mExamEventsInfo.setDay(d);
	            	mExamEventsInfo.setHour(h);
	            	mExamEventsInfo.setMinute(min);
	            }
				private CharSequence getStringDate(long date) {
					
					 // ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm
		         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		         String dateString = formatter.format(date);
		         return dateString;
				     
				}
	        });  
		 dialog.show();
}
	
	public void examTimeRemindOnclickListener(View view){
		examTimeRemind=(TextView)findViewById(R.id.edit_exam_remind);
		 AlertDialog.Builder builder = new AlertDialog.Builder(EditExamActivity.this);
         builder.setIcon(R.drawable.ok);
         builder.setTitle("ѡ��һ������");
         //    ָ�������б����ʾ����
         final String[] types = {"��ʼʱ��", "5����ǰ", "15����ǰ", "30����ǰ", "1Сʱǰ"
         						,"1��ǰ","2��ǰ","1������ǰ" };
         //    ����һ���������б�ѡ����
         builder.setItems(types, new DialogInterface.OnClickListener(){
             @Override
             public void onClick(DialogInterface dialog, int which){
            	 examTimeRemind.setText(types[which]);
                 Toast.makeText(EditExamActivity.this, "ѡ�������ʱ��Ϊ��" + types[which], Toast.LENGTH_SHORT).show();
             }
         });
         builder.show();			
	}
	
	//actionBar�ĵ���¼�
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.comfirm:
				Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
				//���沢������Activity
				Intent intenttTest = new Intent(this,MyTabActivity.class);
				
				/*����ʹ�ñ��   ��   FLAG_ACTIVITY_CLEAR_TOP
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
