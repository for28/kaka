package com.add.kaka;

import java.util.ArrayList;
import java.util.List;

import com.example.kaka.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 *导入考试的页面 
 */
 public class ImportExamActivity extends Activity {
	TextView text1,text2,text3;
	//实现屏幕切换 
	ViewPager mPager;
	//
	List<View> listViews;
	//动画图片
	ImageView cursor;
	int bmpW=0;
	//动画图片偏移量
	int offset=10;
	//当前页卡编号
	int currIndex=0;
	
//	ListView  final_exam_view,identify_exam_view,other_exam_view;
	//头顶的menu
	public boolean onCreateOptionsMenu(Menu menu) {
		//return super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_viewpager);
		ActionBar actionaBar =getActionBar();
//	    ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
//	    actionaBar.setBackgroundDrawable(actionBar_Color);
//	    getActionBar().setIcon(R.drawable.programs);
//	    actionaBar.setHomeButtonEnabled(true);
		//隐藏actionBar
		actionaBar.hide();
		InitTextView();
	    InitViewPager();
		InitImageView();
//		InitListView();
		
		
	}
	
	/**
	 * 初始化头标  三个 TextView
	 */
	private void InitTextView(){
		//获取TextView控件
	    text1=(TextView)findViewById(R.id.viewpage_text1);
	    text2=(TextView)findViewById(R.id.viewpage_text2);
	    text3=(TextView)findViewById(R.id.viewpage_text3);
		//设置TextView监听事件
	    text1.setOnClickListener(new TextViewOnClickListener(0) );
	    text2.setOnClickListener(new TextViewOnClickListener(1) );
	    text3.setOnClickListener(new TextViewOnClickListener(2) );
	}
	
	/**
	 * 初始化页卡内容
	 */
	private void InitViewPager() {
		mPager =(ViewPager)findViewById(R.id.viewpager);
		listViews = new ArrayList<View>();
		//加载布局，调用activity.getLayoutInflater
		LayoutInflater mInflater = getLayoutInflater();
		
		View page1 =(View)mInflater.inflate(R.layout.exam_viewpager_page1, null);
		ListView final_exam_view =(ListView)(page1.findViewById(R.id.final_exam));
		String[] strings={"javaEE","移动互联网","计算机网络","手持设备开发"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.exam_page_item,R.id.exam_item_text, strings);
		for(int i =0;i<strings.length;i++)
		Log.i("初始化列表项",strings[i]);
		final_exam_view.setAdapter(adapter);
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page1, null));
	//	listViews.add(final_exam_view);
		
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page2, null));
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page3, null));
		
		//设置适配器
		mPager.setAdapter(new mPagerAdapter(listViews));
		//设置当前页卡
		mPager.setCurrentItem(0);
		//设置监听器
		mPager.setOnPageChangeListener(new mOnPageChangeListener());
		
	}
	
	/**
	 * 初始化动画cursor
	 */
	public void InitImageView() {
		cursor = (ImageView)findViewById(R.id.viewpage_image);
		//获取图片宽度
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//获取分辨率宽度
		int screenW = dm.widthPixels;
		
		//计算偏移量
		offset = (screenW / 3 - bmpW)/2 ;
		Log.i("InitImageView：bmpW",""+bmpW);
		Log.i("InitImageView：screenW", screenW+"!!!!");
		Log.i("InitImageView",">>>>>>"+offset);
		//Matrix==矩阵
		Matrix matrix = new Matrix();
		//图片移动（x，y）
		matrix.postTranslate(offset, 0);
		//设置动画初始位置
		cursor.setImageMatrix(matrix);
	}
	
	
	
	/**
	 * 自定义的监听TextView监听事件，根据传值确定页卡
	 */
	public class TextViewOnClickListener implements OnClickListener{
		int index =0;
		public TextViewOnClickListener(int i){
			index = i;
		}
		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	}
	/**
	 * 页卡的适配器
	 */
	public class mPagerAdapter extends PagerAdapter{
		public List<View> mListViews;
		public mPagerAdapter(List<View> list){
			mListViews = list;
		
			
		}
		@Override
		//得到数据的行数
		public int getCount() {
			return mListViews.size();
		}

		@Override
		//判断界面是否由对象生成
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		//移除当前的Item
		public void destroyItem(View container, int position, Object object) {
			//从mListViews中获取到position你位置的内容并移除
			 ((ViewPager) container).removeView(mListViews.get(position));
		}

		@Override
		//选择哪个对象放在当前的ViewPager中
		public Object instantiateItem(View container, int position) {
			//从mListViews中获取到position你位置的内容并添加
			((ViewGroup) container).addView(mListViews.get(position));
//			View page1 =(View)getLayoutInflater().inflate(R.layout.exam_viewpager_page1, null);
			ListView final_exam_view =(ListView)(container.findViewById(R.id.final_exam));
			final String[] strings={"javaEE","移动互联网","计算机网络","手持设备开发"};
			ArrayAdapter<String> finalExamAdapter = new ArrayAdapter<String>(ImportExamActivity.this, R.layout.exam_page_item,R.id.exam_item_text, strings);
			for(int i =0;i<strings.length;i++)
			Log.i("初始化列表项",strings[i]);
			final_exam_view.setAdapter(finalExamAdapter);
			final_exam_view.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					Intent intent = new Intent(ImportExamActivity.this,EditExamActivity.class);
					intent.putExtra("examName", strings[position]);
					startActivity(intent);
				}
			});
			
			if(position==1){
			ListView identify_exam_view =(ListView)(container.findViewById(R.id.identify_exam));
			String[] strings1={"英语四级","英语六级","软考","计算机等级"
					,"a","b","c","d","e","f","g","h","i","j","k","l","m","o","p"};
			ArrayAdapter<String> identifyAdapter = new ArrayAdapter<String>(ImportExamActivity.this
					, R.layout.exam_page_item,R.id.exam_item_text, strings1);
			identify_exam_view.setAdapter(identifyAdapter);
			}
			
			if(position==2){
				ListView other_exam_view =(ListView)(container.findViewById(R.id.other_exam));
				String[] strings1={"考试","习惯","作业","临时","通知","会议"};
				ArrayAdapter<String> otherExamAdapter = new ArrayAdapter<String>(ImportExamActivity.this
						, R.layout.exam_page_item,R.id.exam_item_text, strings1);
				other_exam_view.setAdapter(otherExamAdapter);
				}
			return mListViews.get(position);
		}
	}
	/**
	 * 实现页卡切换监听
	 */
	public class  mOnPageChangeListener implements OnPageChangeListener{
          @Override
          //选中时
         public void onPageSelected(int arg0) {
        	  //页卡1 ->页卡2 偏移量
        	  int one = offset*2+bmpW;
        	  //页卡1 ->页卡3 偏移量
        	  int two = one * 2;
//        	  System.out.println("bmpW："+bmpW+"offset："+offset);
//        	  System.out.println("one："+one+"two："+two);
            Animation animation = null;
             switch (arg0) {
             case 0:
                 if (currIndex == 1) {
                     animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                animation = new TranslateAnimation(two, 0, 0, 0);
                 }
                break;
           case 1:
             if (currIndex == 0) {
                     animation = new TranslateAnimation(offset, one, 0, 0);
                 } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                 }
                break;
             case 2:
                 if (currIndex == 0) {
                     animation = new TranslateAnimation(offset, two, 0, 0);
                 } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                 }
                 break;
             }
            currIndex = arg0;
         //图片停在动画结束位置
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.startAnimation(animation);
         }

         @Override
         public void onPageScrolled(int arg0, float arg1, int arg2) {
         }
 
         @Override
         public void onPageScrollStateChanged(int arg0) {
         }

		
	}
	
	
	
}

