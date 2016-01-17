package com.add.kaka;

import java.util.Calendar;

import com.example.kaka.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
//import cn.edu.hstc.dialog.CustomDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class EditEventsActivity extends Activity {
	 private int year, month, day;
     private int hour, minute;
     private Calendar cal;
     private EventsInfo mEventInfo=new EventsInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_events_activity);
        //��ȡ�ؼ�
        final TextView selectDate=(TextView)findViewById(R.id.selectDate);
        final TextView selectTime=(TextView)findViewById(R.id.selectTime);
        final TextView selectType=(TextView)findViewById(R.id.selectType);
        final EditText content=(EditText)findViewById(R.id.eventDetail);
        final Button sure=(Button)findViewById(R.id.testBasebtn);
       // final Button cancle=(Button)findViewById(R.id.cancle);
        
        //��ȡactionBar������
        ActionBar actionaBar =getActionBar();
        ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
        actionaBar.setBackgroundDrawable(actionBar_Color);
        getActionBar().setIcon(R.drawable.programs);
        setTitle("�ҵ���ҵ");
        
        cal = Calendar.getInstance();
        //��ȡ������ʱ�������Ϣ
        year = cal.get(Calendar.YEAR);
        //month��0��ʼ����(һ��month = 0)
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        //��ʼ�����ڵ���ʾ
        selectDate.setText(year+"��"+month+"��"+day+"��");
    	selectTime.setText(hour+"ʱ"+minute+"��");
    	mEventInfo.setYear(year);
    	mEventInfo.setMonth(month);
    	mEventInfo.setDay(day);
    	mEventInfo.setHour(hour);
    	mEventInfo.setMinute(minute);
    	//���ü����¼�		
        selectDate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
	        //����textView
		        //selectDate.setText(year+"��"+month+"��"+day+"��");
		        //����ѡ�����ĶԻ�����ʽ
		        new DatePickerDialog(EditEventsActivity.this ,new OnDateSetListener(){
					public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
						mEventInfo.setYear(year);
						mEventInfo.setMonth(monthOfYear+1);
						mEventInfo.setDay(dayOfMonth);
						selectDate.setText(year+"��"+(monthOfYear+1)+"��"+dayOfMonth+"��");
					}
					} , mEventInfo.getYear(), mEventInfo.getMonth()-1, mEventInfo.getDay()).show();
				}
        });
        //���ü����¼�	
        selectTime.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	        	new TimePickerDialog(EditEventsActivity.this, new OnTimeSetListener() {
		            @Override
		            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		            	mEventInfo.setHour(hourOfDay);
		            	mEventInfo.setMinute(minute);
		            	selectTime.setText(hourOfDay+"ʱ"+minute+"��");
		            } }, mEventInfo.getHour(), mEventInfo.getMinute(), true).show();				
				}
        });
        
        selectType.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 AlertDialog.Builder builder = new AlertDialog.Builder(EditEventsActivity.this);
	                builder.setIcon(R.drawable.ok);
	                builder.setTitle("ѡ��һ������");
	                //    ָ�������б����ʾ����
	                final String[] types = {"��ҵ", "����", "����", "����", "����"};
	                //    ����һ���������б�ѡ����
	                builder.setItems(types, new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        Toast.makeText(EditEventsActivity.this, "ѡ�������Ϊ��" + types[which], Toast.LENGTH_SHORT).show();
	                    }
	                });
	                builder.show();			
				}
        });
        
        sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				Toast.makeText(EditEventsActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
			}
		});
        
        content.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mEventInfo.setHomework(content.getText().toString());
			}
		});
     /*   
        cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(EditEventsActivity.this, "ȡ��", Toast.LENGTH_LONG).show();
			}
		});*/
       // CustomDialog dialog1 = new CustomDialog(this, R.layout.layout_dialog, R.style.Theme_dialog);//Dialogʹ��Ĭ�ϴ�С(160) 
       // CustomDialog dialog2 = new CustomDialog(this, 240, 240, R.layout.layout_dialog, R.style.Theme_dialog);
      //  dialog2.show();//��ʾDialog //���Ҫ�޸�Dialog�е�ĳ��View,�����"����ɾ��..."��Ϊ"������..."
       // dialog1.show();
}
        
}


