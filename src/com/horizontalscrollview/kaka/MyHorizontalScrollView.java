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

	//���ڻ�ȡ����
	private LinearLayout mLayout;
	//��߲˵���
	private ViewGroup mMenuView;
	//����ͼ
	private ViewGroup mMainView;
	//��Ļ��
	private int mScreenWidth;
	//��������ұ߿�ľ���
	private int mMenuRightPadding=50;
	//������Ŀ�
	private int mMenuWidth;
	//�����ж��Ƿ��Ѿ���ȡ��view�ĸ�������
	private boolean flag;
	
	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//����豸���ڵķֱ�����Ϣ
		WindowManager wm=(WindowManager)context.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		//���豸�ķֱ�����Ϣ�ŵ�outMetrics��
		wm.getDefaultDisplay().getMetrics(outMetrics);
		
		/**
	    dip: device independent pixels(�豸��������). ��ͬ�豸�в�ͬ����ʾЧ��,������豸Ӳ��
	          �йأ�һ������Ϊ��֧��WVGA��HVGA��QVGA �Ƽ�ʹ����������������ء�����˵˵px,px�������أ�
	          �����px,�ͻ���ʵ�����ػ����ȸ���ɣ��û�һ������Ϊ240px�ĺ��ߣ���480���ģ�����Ͽ�����
	          һ�����������320���ģ�����Ͽ�����2��3�������ˡ���dip�����ǰ���Ļ�ĸ߷ֳ�480�֣���ֳ�320�֡�
		 * */
		//��ȡ��Ļ��
		mScreenWidth=outMetrics.widthPixels;
		//���������ת��Ϊ��׼�ߴ磬COMPLEX_UNIT_DIP�ǵ�λ��100����ֵ��������100dp����˼����mMenuRightPadding����Ϊ100dp
		mMenuRightPadding=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100 , context.getResources().getDisplayMetrics());
		
	}
	//view�Ĵ�С�ж����onMeasure����
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if(!flag){
			
			mLayout=(LinearLayout) getChildAt(0);
			mMenuView=(ViewGroup)mLayout.getChildAt(0);
			mMainView=(ViewGroup)mLayout.getChildAt(1);
			//��������Ŀ���Ϊ��Ļ��-������б߿�ľ���
			
			mMenuWidth=mMenuView.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			mMainView.getLayoutParams().width=mScreenWidth;
			flag=true;
		}	
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	//onLayout�����������layout pass�б����õģ�����ȷ��View�İڷ�λ�úʹ�С
	//���е��������Ҳ������������parent�ġ����View����child����ôonLayout����Ҫ��ÿһ��child���в��֡�
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
			//����view�������У�x�������mMenuWidth��λ��������������꣬���Խ�(mMenuWidth,0)�������ŵ���Ļ���Ͻǣ�
			//����ʾ������
			this.scrollTo(mMenuWidth, 0);
		}
	}
	//�������¼�
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		//�뿪��Ļ
		case MotionEvent.ACTION_UP:
			//getScrollX()�����ָ�ƶ��ľ���
			int scrollX=getScrollX();
			if(scrollX>mMenuWidth/2){
				//�ƶ�������
				this.smoothScrollTo(mMenuWidth, 0);
				
			}else{
				//���ƶ�
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
		//scale��ֵ0~1 �����õ�l����������ʾ�Ŀ�ȣ��������һ�scale��С
				float scale = l * 1.0f / mMenuWidth;
				String s=String.valueOf(scale);
				Log.v("scale",s);
				//���ұߵ����ű�����0.7�仯��1�����һ���ʱ��rightScaleӦ��Ҫ��С������scaleҲҪ��С
				float rightScale = 0.7f + 0.3f * scale;
				//����ߵ����ű�����1�仯��0.7��
				float leftScale = 1.0f - scale * 0.3f;
				
				float leftAlpha = 0.6f + 0.4f * (1 - scale);
				
				// ���� ViewHelper.setTranslationY��view,float)�����Ľ��͡������view ��
				//��Ҫ�ƶ��ĸ�View �����ĸ�������Ҫ�����ڽ����Ͻ��л�أ� float��ָ���ƶ��ľ���
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
