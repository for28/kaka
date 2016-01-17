package com.tabs.kaka;

import java.util.ArrayList;
import java.util.HashMap;

import com.add.kaka.AddActivity;
import com.add.kaka.EditHomeworkActivity;
import com.add.kaka.ImportExamActivity;
import com.example.kaka.R;
import com.sqlite.kaka.DataBaseHelper;
import com.sqlite.kaka.SQLiteUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class EventsActivity extends Activity{
	
	SimpleAdapter adapter;
	DataBaseHelper dbHelper=new DataBaseHelper(EventsActivity.this,"kaka_db");
	ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
	int index=0;//用于标识哪个item被选择
	
	//删除弹框
	PopupWindow deleteWindow=null;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_item_event);
		

		final Button addExam=(Button)findViewById(R.id.add_exam2);
		final Button addHomework=(Button)findViewById(R.id.add_homework2);
		final Button addMeeting=(Button)findViewById(R.id.add_meetting2);
		//final Button addNotice=(Button)findViewById(R.id.add_notice);
		final Button addHabbit=(Button)findViewById(R.id.add_habbit2);
		
		loadFromSQLite(list);
		showHomework();
		
		//SQLiteUtils sqlite=new SQLiteUtils();
		//sqlite.delete(dbHelper, "hh", SQLiteUtils.HOMEWORK_TAG);
		
		listview.setOnItemLongClickListener(new ItemLongClickListener());
		listview.setOnItemClickListener(new ListItemClickedListener());
	}
	
	public void addEvents(View v){
		
		int key=v.getId();
		switch(key){
		case R.id.add_exam2:{
			Toast.makeText(EventsActivity.this, "添加考试", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(EventsActivity.this,ImportExamActivity.class);
			 startActivity(intent);
//			 finish();
			 break;
		}
		case R.id.add_habbit2:{
			Toast.makeText(EventsActivity.this, "添加习惯", Toast.LENGTH_SHORT).show();
			 break;
		}
		case R.id.add_homework2:{
			
			Toast.makeText(EventsActivity.this, "添加作业", Toast.LENGTH_SHORT).show();
			
			Intent intent=new Intent(EventsActivity.this, EditHomeworkActivity.class);
			Bundle bundle=new Bundle();
			bundle.putString("content","");
			bundle.putString("datetime", "0");
			bundle.putString("alerttime", "0");
			intent.putExtra("android.intent.extra.INTENT", bundle);
			 startActivity(intent);
//			 finish();
			 break;
		}
		case R.id.add_meetting2:{
			Toast.makeText(EventsActivity.this, "添加会议", Toast.LENGTH_SHORT).show();
			 break;
		}
		case R.id.add_notice:{
			Toast.makeText(EventsActivity.this, "添加公告", Toast.LENGTH_SHORT).show();
			 break;
			
		}
		}
	}
	
	/**
	 * 数据项长按回调接口
	 * @author ZAZA
	 *
	 */
	class ItemLongClickListener implements OnItemLongClickListener{
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			index=position;
			View deleteView=getLayoutInflater().inflate(R.layout.delete_popupwindow, null);
			deleteWindow=new PopupWindow(deleteView,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);
			//deleteWindow.setAnimationStyle(R.style.popupAnimation);
			deleteWindow.setFocusable(true);
			deleteWindow.setBackgroundDrawable(new BitmapDrawable());//这里必须设置这句，使得touch弹窗以外的地方或者按返回键才会消失而且Drawable不能用null代替
			deleteWindow.showAtLocation(deleteView, Gravity.CENTER, 0, 0);
			
			Button deleteButton=(Button)deleteView.findViewById(R.id.deleteButton);
			Button cancelButton=(Button)deleteView.findViewById(R.id.cancel);
			
			deleteButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					deleteItem(index);
					deleteWindow.dismiss();
				}
			});
			cancelButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					deleteWindow.dismiss();
				}
			});
			return true;
		}
		
	}
	
	class ListItemClickedListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent itemIntent=new Intent(EventsActivity.this, EditHomeworkActivity.class);
			Bundle bundle=new Bundle();
			bundle.putString("content", list.get(position).get("content"));
			bundle.putString("datetime", list.get(position).get("datetime"));
			if(list.get(position).get("alerttime")==null){
				bundle.putString("alerttime", "0");
			}
			else{
			bundle.putString("alerttime", list.get(position).get("alerttime"));
			}
			itemIntent.putExtra("android.intent.extra.INTENT", bundle);
			startActivity(itemIntent);
		}
	}
	
	public void deleteItem(int position){
		String id=((HashMap<String, String>)listview.getItemAtPosition(position)).get("id").toString();
		list.remove(position);
		SQLiteUtils sqlite=new SQLiteUtils();
		sqlite.delete(dbHelper, id,SQLiteUtils.HOMEWORK_TAG);
		
		adapter.notifyDataSetChanged();
	}
	
	public void showHomework(){
		if(list!=null){
			FrameLayout.LayoutParams params=new LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
			View homeworkView=getLayoutInflater().inflate(R.layout.show_homework, null);
			listview=(ListView)findViewById(R.id.homework_list);
			adapter=new SimpleAdapter(EventsActivity.this, list, R.layout.show_homework,new String[]{"content","datetime"} , new int[]{R.id.my_homework,R.id.homework_time});
			listview.setAdapter(adapter);
			addContentView(homeworkView, params);
		}
	}
	
	private void loadFromSQLite(ArrayList<HashMap<String, String>> list){
		
		SQLiteDatabase db=dbHelper.getReadableDatabase();
		Cursor cur=db.query("homework", null, null, null, null, null, null);
		while(cur.moveToNext()){
			for (int i = 0; i < cur.getCount(); i++) {
				cur.moveToPosition(i);
				String content = cur.getString(0);
				String datetime = cur.getString(1);
				String alerttime = cur.getString(2);
				String id=cur.getString(3);
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("datetime", datetime);
				map.put("content", content);
				map.put("alerttime", alerttime);
				map.put("id", id);
				list.add(map);
				System.out.println("alerttime:"+alerttime);
			}
		}
	}

}
