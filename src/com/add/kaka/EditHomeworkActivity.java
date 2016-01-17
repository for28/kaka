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
	//用于将获取到的时间作为数据项的id
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

	//actionBar的点击事件
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
					//将日期转换为字符串
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
					Toast.makeText(EditHomeworkActivity.this, "保存成功", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(EditHomeworkActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
				}
				//Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
				//保存并返回主Activity
				Intent intenttTest = new Intent(this,MyTabActivity.class);
				
				/**
				 * 这里使用标记   ：   FLAG_ACTIVITY_CLEAR_TOP
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
