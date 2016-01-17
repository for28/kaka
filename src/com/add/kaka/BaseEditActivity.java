package com.add.kaka;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sdp.RepeatTime;

import com.dialog.kaka.DateTimePickerDialog;
import com.dialog.kaka.DateTimePickerDialog.OnDateTimeSetListener;
import com.example.kaka.R;
import com.sqlite.kaka.DataBaseHelper;
import com.sqlite.kaka.SQLiteUtils;
import com.tabs.kaka.MyTabActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.text.StaticLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BaseEditActivity extends Activity {
	 public int year, month, day;
     public int hour, minute;
     public EventsInfo mEventsInfo=new EventsInfo();
     public  EditText editText;

     private Calendar cal;
     
     public String timeItem="时间";
     public String alertItem="提醒时间";
     public String repeatItem="重复";
	@Override
	//头顶的menu
	public boolean onCreateOptionsMenu(Menu menu) {
		//return super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.base_edit_layout);
    editText=(EditText)findViewById(R.id.base_edit);
	 ActionBar actionaBar =getActionBar();
     ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
     actionaBar.setBackgroundDrawable(actionBar_Color);
     getActionBar().setIcon(R.drawable.programs);
     
     actionaBar.setHomeButtonEnabled(true);
     
     cal = Calendar.getInstance();
     //获取年月日时分秒的信息
     year = cal.get(Calendar.YEAR);
     //month从0开始计算(一月month = 0)
     month = cal.get(Calendar.MONTH)+1;
     day = cal.get(Calendar.DAY_OF_MONTH);
     hour = cal.get(Calendar.HOUR_OF_DAY);
     minute = cal.get(Calendar.MINUTE);
     
   //判断是否编辑
     Intent intent=getIntent();
     Bundle bundle=intent.getBundleExtra("android.intent.extra.INTENT");
     String datetime=bundle.getString("datetime");
    // mEventsInfo.setDate(Long.parseLong(datetime));
     String content=bundle.getString("content");
     mEventsInfo.setHomework(content);
     String alerttime=bundle.getString("alerttime");
     mEventsInfo.setAlertTime(alerttime);
	
     if(!content.equals("")){
    	 editText.setText(content);
     }
     if(!datetime.equals("0")){
    	 timeItem=datetime;
     }
     if(!alerttime.equals("0")){
    	 alertItem=alerttime;
     }
     
     
     //列表项需要的数据项
     int[] imageIds =new int[]{R.drawable.event_calendar,R.drawable.event_time,R.drawable.event_refresh} ;
     String[] textStrings = new String[]{timeItem,alertItem,repeatItem};
     
     // 创建一个list集合，集合的元素是Map
    List<Map<String ,Object>> listItems = new ArrayList<Map<String, Object>>();
    
    //用for循环填充listItems
    for(int i =0; i<textStrings.length;i++ ){
    	Map<String, Object> listItem = new HashMap<String, Object>();
    	listItem.put("image", imageIds[i]);
    	listItem.put("text", textStrings[i]);
    	listItems.add(listItem);
    }
    /*
     * 创建一个SimpleAdapter
     * SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	*参数context：上下文，比如this。关联SimpleAdapter运行的视图上下文
	*参数data：Map列表，列表要显示的数据，这部分需要自己实现，如例子中的getData()，类型要与上面的一致，每条项目要与from中指定条目一致
    *参数resource：ListView单项布局文件的Id,这个布局就是你自定义的布局了，你想显示什么样子的布局都在这个布局中。这个布局中必须包括了to中定义的控件id
    *参数 from：一个被添加到Map上关联每一个项目列名称的列表，数组里面是列名称
    *参数 to：是一个int数组，数组里面的id是自定义布局中各个控件的id，需要与上面的from对应
     */
     SimpleAdapter simpleAdapter= new SimpleAdapter(this, listItems,R .layout.base_edit_item,
    		 new String[]{"image","text"}, new int[]{R.id.base_event_item_image,R.id.base_event_item_text});
     ListView base_event_list = (ListView)findViewById(R.id.base_event_list);
     //设置适配器
     base_event_list.setAdapter(simpleAdapter);
     
     // 给listView的列表项的单击事件设置监听器
     base_event_list.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			final TextView text=(TextView)view.findViewById(R.id.base_event_item_text);
			switch (position) {
			case 0:
	
				if(mEventsInfo.getDate()==0){
					mEventsInfo.setDate(System.currentTimeMillis());
				}
				//点击弹出一个选择日期的对话框
				
					DateTimePickerDialog dialog=
							new DateTimePickerDialog(BaseEditActivity.this, mEventsInfo.getDate(),mEventsInfo);
					//设置完成的回调函数
					 dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
				            public void OnDateTimeSet(AlertDialog dialog, long date){
//				            	Toast.makeText(BaseEventActivity.this,
//				                        "你输入的日期是：" + getStringDate(date), Toast.LENGTH_LONG)
//				                        .show();
				            	CharSequence newDate=getStringDate(date);
				            	text.setText(newDate);
				            	
				            	mEventsInfo.setDate(date);
				            	//保存日期到EventsInfo对象中
				            	int y=Integer.parseInt(newDate.toString().substring(0, 4));
				            	int m=Integer.parseInt(newDate.toString().substring(5, 7));
				            	int d=Integer.parseInt(newDate.toString().substring(8, 10));
				            	int h=Integer.parseInt(newDate.toString().substring(11, 13));
				            	int min=Integer.parseInt(newDate.toString().substring(14, 16));
				            	mEventsInfo.setYear(y);
				            	mEventsInfo.setMonth(m);
				            	mEventsInfo.setDay(d);
				            	mEventsInfo.setHour(h);
				            	mEventsInfo.setMinute(min);
				            }
							private CharSequence getStringDate(long date) {
								
								 // 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm
					         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					         String dateString = formatter.format(date);
					         return dateString;
							     
							}
				        });  
					 dialog.show();
					
			break;
			case 1:
				//点击弹出一个选择提醒时间的对话框
				 AlertDialog.Builder builder = new AlertDialog.Builder(BaseEditActivity.this);
	                builder.setIcon(R.drawable.ok);
	                builder.setTitle("选择一个类型");
	                //    指定下拉列表的显示数据
	                final String[] types = {"开始时间", "5分钟前", "15分钟前", "30分钟前", "1小时前"
	                						,"1天前","2天前","1个星期前" };
	                //    设置一个下拉的列表选择项
	                builder.setItems(types, new DialogInterface.OnClickListener(){
	                    @Override
	                    public void onClick(DialogInterface dialog, int which){
	                    	text.setText(types[which]);
	                    	mEventsInfo.setAlertTime(types[which]);
	                        Toast.makeText(BaseEditActivity.this, "选择的提醒时间为：" + types[which], Toast.LENGTH_SHORT).show();
	                    }
	                });
	                builder.show();			
				
				break;
			case 2:
				AlertDialog.Builder repeatBuilder = new AlertDialog.Builder(BaseEditActivity.this);
				repeatBuilder.setIcon(R.drawable.ok);
				repeatBuilder.setTitle("选择一个类型");
                //指定下拉列表的显示数据
                final String[] repeatTypes = {"不重复","每天", "每周", "每月", "每年", "2周一次"};
                //设置一个下拉的列表选择项
                repeatBuilder.setItems(repeatTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    	text.setText(repeatTypes[which]);
                        Toast.makeText(BaseEditActivity.this, "选择的提醒时间为：" + repeatTypes[which], Toast.LENGTH_SHORT).show();
                    }
                });
                repeatBuilder.show();			
				break;
			default:
				break;
			}
		}
	});
	}
	//@Override
	//actionBar的点击事件
	/*
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.comfirm:
			SQLiteUtils sqlite=new SQLiteUtils();
			DataBaseHelper db=SQLiteUtils.createDBHelper(BaseEditActivity.this);
			if(!editText.getText().toString().trim().equals("")){
				mEventsInfo.setHomework(editText.getText().toString());
				sqlite.inster(db, mEventsInfo, SQLiteUtils.HOMEWORK_TAG);
				Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
				Toast.makeText(BaseEditActivity.this, "保存成功", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(BaseEditActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
			}*/
			//Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
			//保存并返回主Activity
		//	Intent intenttTest = new Intent(this,MyTabActivity.class);
			
			/**
			 * 这里使用标记   ：   FLAG_ACTIVITY_CLEAR_TOP
			 * 用这个标记，如果你要启动的Activity在当前任务中已经存在，
			 * 那么，堆栈中这个Activity之上的所有的Activity都有被销毁，
			 * 并且把这个Activity显示给用户。添加这个标识往往是重要的，
			 * 因为返回主Activity相当与一个回退的动作，因此通常不应该再创建一个新的主Activity的实例，
			 * 否则，最终可能会在当前任务中产生一个很长的拥有多个主Activity的堆栈。
			 */
		/*	intenttTest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
	}*/
	
	public void comfrim_click() {
		
	}
	
}
