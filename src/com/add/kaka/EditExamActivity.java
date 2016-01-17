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

//头顶的menu
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
		//点击弹出一个选择日期的对话框
		DateTimePickerDialog dialog=
				new DateTimePickerDialog(EditExamActivity.this, mExamEventsInfo.getDate(),mExamEventsInfo);
		//设置完成的回调函数
		 dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
	            public void OnDateTimeSet(AlertDialog dialog, long date){
//	            	Toast.makeText(BaseEventActivity.this,
//	                        "你输入的日期是：" + getStringDate(date), Toast.LENGTH_LONG)
//	                        .show();
	            	CharSequence newDate=getStringDate(date);
	            	examTime.setText(newDate);
	            	
	            	mExamEventsInfo.setDate(date);
//	            	保存日期到EventsInfo对象中
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
					
					 // 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm
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
         builder.setTitle("选择一个类型");
         //    指定下拉列表的显示数据
         final String[] types = {"开始时间", "5分钟前", "15分钟前", "30分钟前", "1小时前"
         						,"1天前","2天前","1个星期前" };
         //    设置一个下拉的列表选择项
         builder.setItems(types, new DialogInterface.OnClickListener(){
             @Override
             public void onClick(DialogInterface dialog, int which){
            	 examTimeRemind.setText(types[which]);
                 Toast.makeText(EditExamActivity.this, "选择的提醒时间为：" + types[which], Toast.LENGTH_SHORT).show();
             }
         });
         builder.show();			
	}
	
	//actionBar的点击事件
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.comfirm:
				Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
				//保存并返回主Activity
				Intent intenttTest = new Intent(this,MyTabActivity.class);
				
				/*这里使用标记   ：   FLAG_ACTIVITY_CLEAR_TOP
				 * 用这个标记，如果你要启动的Activity在当前任务中已经存在，
				 * 那么，堆栈中这个Activity之上的所有的Activity都有被销毁，
				 * 并且把这个Activity显示给用户。添加这个标识往往是重要的，
				 * 因为返回主Activity相当与一个回退的动作，因此通常不应该再创建一个新的主Activity的实例，
				 * 否则，最终可能会在当前任务中产生一个很长的拥有多个主Activity的堆栈。
				 */
				intenttTest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intenttTest);
				return true;
			case android.R.id.home:
			//	Toast.makeText(this, "哈哈", Toast.LENGTH_SHORT).show();
				//返回主Activity,
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
