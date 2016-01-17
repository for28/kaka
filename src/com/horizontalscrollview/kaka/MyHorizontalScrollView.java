package com.horizontalscrollview.kaka;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyHorizontalScrollView extends HorizontalScrollView{

	//用于获取布局
	private LinearLayout mLayout;
	//侧边菜单栏
	private ViewGroup mMenuView;
	//主视图
	private ViewGroup mMainView;
	//屏幕宽
	private int mScreenWidth;
	//侧边栏与右边框的距离
	private int mMenuRightPadding=50;
	//侧边栏的宽
	private int mMenuWidth;
	//用于判断是否已经获取过view的各项属性
	private boolean flag;
	
	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//获得设备窗口的分辨率信息
		WindowManager wm=(WindowManager)context.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		//将设备的分辨率信息放到outMetrics中
		wm.getDefaultDisplay().getMetrics(outMetrics);
		
		/**
	    dip: device independent pixels(设备独立像素). 不同设备有不同的显示效果,这个和设备硬件
	          有关，一般我们为了支持WVGA、HVGA和QVGA 推荐使用这个，不依赖像素。，先说说px,px就是像素，
	          如果用px,就会用实际像素画，比个如吧，用画一条长度为240px的横线，在480宽的模拟器上看就是
	          一半的屏宽，而在320宽的模拟器上看就是2／3的屏宽了。而dip，就是把屏幕的高分成480分，宽分成320分。
		 * */
		//获取屏幕宽
		mScreenWidth=outMetrics.widthPixels;
		//这个函数是转换为标准尺寸，COMPLEX_UNIT_DIP是单位，100是数值，这里是100dp的意思，将mMenuRightPadding设置为100dp
		mMenuRightPadding=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100 , context.getResources().getDisplayMetrics());
		
	}
	//view的大小有多大，由onMeasure决定
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if(!flag){
			
			mLayout=(LinearLayout) getChildAt(0);
			mMenuView=(ViewGroup)mLayout.getChildAt(0);
			mMainView=(ViewGroup)mLayout.getChildAt(1);
			//将侧边栏的宽设为屏幕宽-侧边与有边框的距离
			
			mMenuWidth=mMenuView.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			mMainView.getLayoutParams().width=mScreenWidth;
			flag=true;
		}	
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	//onLayout这个方法是在layout pass中被调用的，用于确定View的摆放位置和大小
	//其中的上下左右参数都是相对于parent的。如果View含有child，那么onLayout中需要对每一个child进行布局。
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
			//整个view的坐标中，x坐标等于mMenuWidth的位置是主界面的坐标，所以将(mMenuWidth,0)这个坐标放到屏幕左上角，
			//即显示主界面
			this.scrollTo(mMenuWidth, 0);
		}
	}
	//处理触摸事件
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		//离开屏幕
		case MotionEvent.ACTION_UP:
			//getScrollX()获得手指移动的距离
			int scrollX=getScrollX();
			if(scrollX>mMenuWidth/2){
				//移动主界面
				this.smoothScrollTo(mMenuWidth, 0);
				
			}else{
				//不移动
				this.smoothScrollTo(0, 0);
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		//scale的值0~1 这里获得的l是主界面显示的宽度，所以往右滑scale变小
				float scale = l * 1.0f / mMenuWidth;
				String s=String.valueOf(scale);
				Log.v("scale",s);
				//让右边的缩放比例从0.7变化到1，向右滑动时，rightScale应该要变小，就是scale也要变小
				float rightScale = 0.7f + 0.3f * scale;
				//让左边的缩放比例从1变化到0.7，
				float leftScale = 1.0f - scale * 0.3f;
				
				float leftAlpha = 0.6f + 0.4f * (1 - scale);
				
				// 关于 ViewHelper.setTranslationY（view,float)函数的解释。这里的view 是
				//您要移动哪个View 就是哪个东西你要将他在界面上进行活动呢？ float是指你移动的距离
				ViewHelper.setTranslationX(mMenuView, mMenuWidth * scale * 0.8f);

				ViewHelper.setPivotX(mMainView, 0);
				ViewHelper.setPivotY(mMainView, mMainView.getHeight() / 2);
				
				ViewHelper.setScaleX(mMenuView, leftScale);
				ViewHelper.setScaleY(mMenuView, leftScale);
				
				ViewHelper.setAlpha(mMenuView, leftAlpha);
				
				ViewHelper.setScaleX(mMainView, rightScale);
				ViewHelper.setScaleY(mMainView, rightScale);
	}

}
