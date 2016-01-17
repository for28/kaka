package com.sqlite.kaka;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//创建数据库
public class DataBaseHelper extends SQLiteOpenHelper{

	private static final int VERSION = 2;
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DataBaseHelper(Context context,String name,int version){
		this(context,name,null,version);
	}
	
	public DataBaseHelper(Context context,String name){
		this(context,name,VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table homework(content varchar(100),datetime varchar(30),alerttime varchar(30),id varchar(30))");
		//db.execSQL("create table exam(content varchar(100),datetime varchar(30),alerttime varchar(30),id varchar(30)),addr varchar(30),is_done bit");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
