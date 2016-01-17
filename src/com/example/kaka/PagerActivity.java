package com.example.kaka;

import java.util.ArrayList;

import com.tabs.kaka.MyTabActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class PagerActivity extends Activity{

	private ViewPager mViewPager;
	private PagerTitleStrip mPagerTitleStrip;
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);
		mViewPager=(ViewPager)findViewById(R.id.viewpager);
		mPagerTitleStrip=(PagerTitleStrip)findViewById(R.id.pagertitle);
		//将布局文件转换成view对象
		LayoutInflater mLi=LayoutInflater.from(this);
		View view1=mLi.inflate(R.layout.viewpager_activity_item1, null);
		View view2=mLi.inflate(R.layout.viewpager_activity_item2, null);
		
		final ArrayList<View> views=new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		
		PagerAdapter mPagerAdapter=new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}
			
			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return null;
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		mViewPager.setAdapter(mPagerAdapter);
	}
	public void startbutton(View v){
		
		Intent intent=new Intent(PagerActivity.this,MyTabActivity.class);
		startActivity(intent);
		finish();
	}
}
