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
        //获取控件
        final TextView selectDate=(TextView)findViewById(R.id.selectDate);
        final TextView selectTime=(TextView)findViewById(R.id.selectTime);
        final TextView selectType=(TextView)findViewById(R.id.selectType);
        final EditText content=(EditText)findViewById(R.id.eventDetail);
        final Button sure=(Button)findViewById(R.id.testBasebtn);
       // final Button cancle=(Button)findViewById(R.id.cancle);
        
        //获取actionBar并设置
        ActionBar actionaBar =getActionBar();
        ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
        actionaBar.setBackgroundDrawable(actionBar_Color);
        getActionBar().setIcon(R.drawable.programs);
        setTitle("我的作业");
        
        cal = Calendar.getInstance();
        //获取年月日时分秒的信息
        year = cal.get(Calendar.YEAR);
        //month从0开始计算(一月month = 0)
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        //初始化日期的显示
        selectDate.setText(year+"年"+month+"月"+day+"日");
    	selectTime.setText(hour+"时"+minute+"分");
    	mEventInfo.setYear(year);
    	mEventInfo.setMonth(month);
    	mEventInfo.setDay(day);
    	mEventInfo.setHour(hour);
    	mEventInfo.setMinute(minute);
    	//设置监听事件		
        selectDate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
	        //设置textView
		        //selectDate.setText(year+"年"+month+"月"+day+"日");
		        //日历选择器的对话框形式
		        new DatePickerDialog(EditEventsActivity.this ,new OnDateSetListener(){
					public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
						mEventInfo.setYear(year);
						mEventInfo.setMonth(monthOfYear+1);
						mEventInfo.setDay(dayOfMonth);
						selectDate.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
					}
					} , mEventInfo.getYear(), mEventInfo.getMonth()-1, mEventInfo.getDay()).show();
				}
        });
        //设置监听事件	
        selectTime.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	        	new TimePickerDialog(EditEventsActivity.this, new OnTimeSetListener() {
		            @Override
		            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		            	mEventInfo.setHour(hourOfDay);
		            	mEventInfo.setMinute(minute);
		            	selectTime.setText(hourOfDay+"时"+minute+"分");
		            } }, mEventInfo.getHour(), mEventInfo.getMinute(), true).show();				
				}
        });
        
        selectType.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 AlertDialog.Builder builder = new AlertDialog.Builder(EditEventsActivity.this);
	                builder.setIcon(R.drawable.ok);
	                builder.setTitle("选择一个类型");
	                //    指定下拉列表的显示数据
	                final String[] types = {"作业", "会议", "生日", "考试", "其他"};
	                //    设置一个下拉的列表选择项
	                builder.setItems(types, new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        Toast.makeText(EditEventsActivity.this, "选择的类型为：" + types[which], Toast.LENGTH_SHORT).show();
	                    }
	                });
	                builder.show();			
				}
        });
        
        sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				Toast.makeText(EditEventsActivity.this, "保存成功", Toast.LENGTH_LONG).show();
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
				Toast.makeText(EditEventsActivity.this, "取消", Toast.LENGTH_LONG).show();
			}
		});*/
       // CustomDialog dialog1 = new CustomDialog(this, R.layout.layout_dialog, R.style.Theme_dialog);//Dialog使用默认大小(160) 
       // CustomDialog dialog2 = new CustomDialog(this, 240, 240, R.layout.layout_dialog, R.style.Theme_dialog);
      //  dialog2.show();//显示Dialog //如果要修改Dialog中的某个View,比如把"正在删除..."改为"加载中..."
       // dialog1.show();
}
        
}


