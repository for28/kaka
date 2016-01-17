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
 *���뿼�Ե�ҳ�� 
 */
 public class ImportExamActivity extends Activity {
	TextView text1,text2,text3;
	//ʵ����Ļ�л� 
	ViewPager mPager;
	//
	List<View> listViews;
	//����ͼƬ
	ImageView cursor;
	int bmpW=0;
	//����ͼƬƫ����
	int offset=10;
	//��ǰҳ�����
	int currIndex=0;
	
//	ListView  final_exam_view,identify_exam_view,other_exam_view;
	//ͷ����menu
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
		//����actionBar
		actionaBar.hide();
		InitTextView();
	    InitViewPager();
		InitImageView();
//		InitListView();
		
		
	}
	
	/**
	 * ��ʼ��ͷ��  ���� TextView
	 */
	private void InitTextView(){
		//��ȡTextView�ؼ�
	    text1=(TextView)findViewById(R.id.viewpage_text1);
	    text2=(TextView)findViewById(R.id.viewpage_text2);
	    text3=(TextView)findViewById(R.id.viewpage_text3);
		//����TextView�����¼�
	    text1.setOnClickListener(new TextViewOnClickListener(0) );
	    text2.setOnClickListener(new TextViewOnClickListener(1) );
	    text3.setOnClickListener(new TextViewOnClickListener(2) );
	}
	
	/**
	 * ��ʼ��ҳ������
	 */
	private void InitViewPager() {
		mPager =(ViewPager)findViewById(R.id.viewpager);
		listViews = new ArrayList<View>();
		//���ز��֣�����activity.getLayoutInflater
		LayoutInflater mInflater = getLayoutInflater();
		
		View page1 =(View)mInflater.inflate(R.layout.exam_viewpager_page1, null);
		ListView final_exam_view =(ListView)(page1.findViewById(R.id.final_exam));
		String[] strings={"javaEE","�ƶ�������","���������","�ֳ��豸����"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.exam_page_item,R.id.exam_item_text, strings);
		for(int i =0;i<strings.length;i++)
		Log.i("��ʼ���б���",strings[i]);
		final_exam_view.setAdapter(adapter);
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page1, null));
	//	listViews.add(final_exam_view);
		
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page2, null));
		listViews.add(mInflater.inflate(R.layout.exam_viewpager_page3, null));
		
		//����������
		mPager.setAdapter(new mPagerAdapter(listViews));
		//���õ�ǰҳ��
		mPager.setCurrentItem(0);
		//���ü�����
		mPager.setOnPageChangeListener(new mOnPageChangeListener());
		
	}
	
	/**
	 * ��ʼ������cursor
	 */
	public void InitImageView() {
		cursor = (ImageView)findViewById(R.id.viewpage_image);
		//��ȡͼƬ���
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//��ȡ�ֱ��ʿ��
		int screenW = dm.widthPixels;
		
		//����ƫ����
		offset = (screenW / 3 - bmpW)/2 ;
		Log.i("InitImageView��bmpW",""+bmpW);
		Log.i("InitImageView��screenW", screenW+"!!!!");
		Log.i("InitImageView",">>>>>>"+offset);
		//Matrix==����
		Matrix matrix = new Matrix();
		//ͼƬ�ƶ���x��y��
		matrix.postTranslate(offset, 0);
		//���ö�����ʼλ��
		cursor.setImageMatrix(matrix);
	}
	
	
	
	/**
	 * �Զ���ļ���TextView�����¼������ݴ�ֵȷ��ҳ��
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
	 * ҳ����������
	 */
	public class mPagerAdapter extends PagerAdapter{
		public List<View> mListViews;
		public mPagerAdapter(List<View> list){
			mListViews = list;
		
			
		}
		@Override
		//�õ����ݵ�����
		public int getCount() {
			return mListViews.size();
		}

		@Override
		//�жϽ����Ƿ��ɶ�������
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		//�Ƴ���ǰ��Item
		public void destroyItem(View container, int position, Object object) {
			//��mListViews�л�ȡ��position��λ�õ����ݲ��Ƴ�
			 ((ViewPager) container).removeView(mListViews.get(position));
		}

		@Override
		//ѡ���ĸ�������ڵ�ǰ��ViewPager��
		public Object instantiateItem(View container, int position) {
			//��mListViews�л�ȡ��position��λ�õ����ݲ����
			((ViewGroup) container).addView(mListViews.get(position));
//			View page1 =(View)getLayoutInflater().inflate(R.layout.exam_viewpager_page1, null);
			ListView final_exam_view =(ListView)(container.findViewById(R.id.final_exam));
			final String[] strings={"javaEE","�ƶ�������","���������","�ֳ��豸����"};
			ArrayAdapter<String> finalExamAdapter = new ArrayAdapter<String>(ImportExamActivity.this, R.layout.exam_page_item,R.id.exam_item_text, strings);
			for(int i =0;i<strings.length;i++)
			Log.i("��ʼ���б���",strings[i]);
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
			String[] strings1={"Ӣ���ļ�","Ӣ������","��","������ȼ�"
					,"a","b","c","d","e","f","g","h","i","j","k","l","m","o","p"};
			ArrayAdapter<String> identifyAdapter = new ArrayAdapter<String>(ImportExamActivity.this
					, R.layout.exam_page_item,R.id.exam_item_text, strings1);
			identify_exam_view.setAdapter(identifyAdapter);
			}
			
			if(position==2){
				ListView other_exam_view =(ListView)(container.findViewById(R.id.other_exam));
				String[] strings1={"����","ϰ��","��ҵ","��ʱ","֪ͨ","����"};
				ArrayAdapter<String> otherExamAdapter = new ArrayAdapter<String>(ImportExamActivity.this
						, R.layout.exam_page_item,R.id.exam_item_text, strings1);
				other_exam_view.setAdapter(otherExamAdapter);
				}
			return mListViews.get(position);
		}
	}
	/**
	 * ʵ��ҳ���л�����
	 */
	public class  mOnPageChangeListener implements OnPageChangeListener{
          @Override
          //ѡ��ʱ
         public void onPageSelected(int arg0) {
        	  //ҳ��1 ->ҳ��2 ƫ����
        	  int one = offset*2+bmpW;
        	  //ҳ��1 ->ҳ��3 ƫ����
        	  int two = one * 2;
//        	  System.out.println("bmpW��"+bmpW+"offset��"+offset);
//        	  System.out.println("one��"+one+"two��"+two);
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
         //ͼƬͣ�ڶ�������λ��
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

