package com.sqlite.kaka;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.add.kaka.AddActivity;
import com.add.kaka.EditHomeworkActivity;
import com.add.kaka.EventsInfo;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


//对数据库进行操作
public class SQLiteUtils {

	public static final String DATABAES_NAME="kaka_db";
	public static final int HOMEWORK_TAG=1;
	//创建数据库操作对象
	public static DataBaseHelper createDBHelper(Context context){
		DataBaseHelper dbHelper=new DataBaseHelper(context, DATABAES_NAME);
		return dbHelper;
	}
	//向数据库插入数据
	public void inster(DataBaseHelper dbHelper,EventsInfo eventInfo,int tag){
		switch(tag){
		case HOMEWORK_TAG:{
			
			ContentValues value=new ContentValues();
			
			value.put("content", eventInfo.getHomework());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
	         String dateString = formatter.format(eventInfo.getDate());
			value.put("datetime", dateString);
			value.put("alerttime", eventInfo.getAlertTime());
			value.put("id", eventInfo.getBuildTime());
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			db.insert("homework", null, value);
			
			db.close();
			
			break;
		}
		default:break;
		}
	}
	
	public void update(DataBaseHelper dbHelper,int tag){
		
		switch(tag){
		case HOMEWORK_TAG:{
			
			break;
		}
		default:break;
		}
	}
	public void delete(DataBaseHelper dbHelper,String id,int tag){
		switch(tag){
		case HOMEWORK_TAG:{
			SQLiteDatabase db=dbHelper.getReadableDatabase();
			String sql="DELETE FROM homework WHERE id="+"'"+id+"'";
			db.execSQL(sql);
			db.close();
			break;
		}
		default:break;
		}
	}
	
}
