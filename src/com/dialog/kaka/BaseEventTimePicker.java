package com.dialog.kaka;

import java.util.Calendar;

import com.add.kaka.EventsInfo;
import com.example.kaka.R;


import android.content.Context;
import android.text.format.DateFormat;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class BaseEventTimePicker extends FrameLayout {
    private final NumberPicker mDateSpinner;
    private final NumberPicker mHourSpinner;
    private final NumberPicker mMinuteSpinner;
    private Calendar mDate;
    private int mHour, mMinute;
    private String[] mDateDisplayValues = new String[7];
    private OnDateTimeChangedListener mOnDateTimeChangedListener;
 
    public BaseEventTimePicker(Context context,EventsInfo mEventsInfo) {
        super(context);
        
        if(mEventsInfo.getYear()==0){
        	//获取系统时间
        	mDate = Calendar.getInstance();
            mHour = mDate.get(Calendar.HOUR_OF_DAY);
            mMinute = mDate.get(Calendar.MINUTE);
        }
        else{
        	
        	mDate=Calendar.getInstance();
        	mDate.set(mEventsInfo.getYear(), mEventsInfo.getMonth()-1, mEventsInfo.getDay());
        	mHour=mEventsInfo.getHour();
        	mMinute=mEventsInfo.getMinute();
        }
        
         //加载布局
        inflate(context, R.layout.base_edit_dialog, this);
        
         //初始化控件
        mDateSpinner = (NumberPicker)findViewById(R.id.np_date);
        mDateSpinner.setMinValue(0);
        mDateSpinner.setMaxValue(6);
        updateDateControl();
        mDateSpinner.setOnValueChangedListener(mOnDateChangedListener);
 
        mHourSpinner = (NumberPicker)findViewById(R.id.np_hour);
        mHourSpinner.setMaxValue(23);
        mHourSpinner.setMinValue(0);
        mHourSpinner.setValue(mHour);
        mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);
 
        mMinuteSpinner = (NumberPicker)findViewById(R.id.np_minute);
        mMinuteSpinner.setMaxValue(59);
        mMinuteSpinner.setMinValue(0);
        mMinuteSpinner.setValue(mMinute);
        mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);
    }
    
     /**
      * 为控件设置监听器
      */
    private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
             
            // 更新日期
            updateDateControl();
            //给接口传值
            onDateTimeChanged();
        }
    };
 
    private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mHour = mHourSpinner.getValue();
            onDateTimeChanged();
        }
    };
 
    private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMinute = mMinuteSpinner.getValue();
            onDateTimeChanged();
        }
    };
    /**
     *更新日期
     */
    private void updateDateControl() {
        //星期的算法
        Calendar cal = Calendar.getInstance();
        //设置日历对象毫秒为单位的时间
        cal.setTimeInMillis(mDate.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, -7 / 2 - 1);
        mDateSpinner.setDisplayedValues(null);
        for (int i = 0; i < 7; ++i) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            mDateDisplayValues[i] = (String) DateFormat.format("MM.dd EEEE", cal);
        }
        //设置要显示的值
        mDateSpinner.setDisplayedValues(mDateDisplayValues);
        
        mDateSpinner.setValue(7 / 2);
        mDateSpinner.invalidate();
    }
     
    /**
     *接口回调 参数是当前的View 年月日小时分钟
     */
    public interface OnDateTimeChangedListener {
		void OnDateTimeChanged(BaseEventTimePicker view, int year, int month,
				int day, int hour, int minute);
    }
    /**
     *对外的公开方法 
     */
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }
    /**
     * 给接口传值
     */
    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            mOnDateTimeChangedListener.OnDateTimeChanged(this,mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH),
                mDate.get(Calendar.DAY_OF_MONTH), mHour, mMinute);
        }
    }
}

















//public class BaseEventTimePicker extends FrameLayout {
//
//	private final NumberPicker mDateNumberPicker;
//	private final NumberPicker mHourNumberPicker;
//	private final NumberPicker mMinuteNumberPicker;
//	private Calendar mDate;
//	private int mHour,mMinute;
//	private String[] mDateDisplayValues = new String[7];
//	private OnDateTimeChangedListener mOnDateTimeChangedListener;
//	
//
//	public BaseEventTimePicker(Context context) {
//		super(context);
//		//获取时间
//		mDate = Calendar.getInstance();
//		mHour = mDate.get(Calendar.HOUR_OF_DAY);
//		mMinute = mDate.get(Calendar.MINUTE);
//		
//		//加载布局
//		inflate(context, R.layout.layout_base_event_dialog, this);
//		
//		//初始化控件
//		mDateNumberPicker = (NumberPicker) this.findViewById(R.id.np_date);
//		mDateNumberPicker.setMinValue(0);
//		mDateNumberPicker.setMaxValue(6);
//        updateDateControl();
//        mDateNumberPicker.setOnValueChangedListener(mOnDateChangedListener);
// 
//        mHourNumberPicker = (NumberPicker) this.findViewById(R.id.np_hour);
//        mHourNumberPicker.setMaxValue(23);
//        mHourNumberPicker.setMinValue(0);
//        mHourNumberPicker.setValue(mHour);
//        mHourNumberPicker.setOnValueChangedListener(mOnHourChangedListener);
// 
//        mMinuteNumberPicker = (NumberPicker) this.findViewById(R.id.np_minute);
//        mMinuteNumberPicker.setMaxValue(59);
//        mMinuteNumberPicker.setMinValue(0);
//        mMinuteNumberPicker.setValue(mMinute);
//        mMinuteNumberPicker.setOnValueChangedListener(mOnMinuteChangedListener);
//	}
//	//设置监听器 
//	// OnValueChangeListener 是一个监听当前数值改变的监听接口
//	private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
//		@Override
//		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//			//日期再原基础加上与新时间之差，结果就是设置成控件上的 日期
//			mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
//			//更新日期
//			updateDateControl();
//			//给接口传值
//			OnDateTimeChanged();
//		}
//
//	
//	};
//	private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
//		@Override
//		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//			//直接获取控件上的值
//			mHour=mMinuteNumberPicker.getValue();
//			OnDateTimeChanged();
//		}
//	};
//	private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
//		@Override
//		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//			mMinute = mMinuteNumberPicker.getValue();
//			OnDateTimeChanged();
//		}
//	};
//	//接口回调
//	
//	public interface OnDateTimeChangedListener{
//		void OnDateTimeChanged(BaseEventTimePicker view,int year,
//				int month,int day,int hour,int minute);
//	}
//	//对外公开的方法
//	public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback){
//		mOnDateTimeChangedListener=callback;
//	}
//	
//	private void OnDateTimeChanged() {
//		if(mOnDateChangedListener!=null){
//			mOnDateTimeChangedListener.OnDateTimeChanged(this,mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH)
//					, mDate.get(Calendar.DAY_OF_MONTH), mHour, mMinute);
//		
//		}
//	}
//	protected void updateDateControl() {
//		//计算星期
//		Calendar calendar = Calendar.getInstance();
//		 /**
//	     * Sets the time of this {@code Calendar}.
//	     *
//	     * @param milliseconds
//	     *            the time as the number of milliseconds since Jan. 1, 1970.
//	     */
//		calendar.setTimeInMillis(mDate.getTimeInMillis());
//		calendar.add(Calendar.DAY_OF_YEAR, -7/2-1);
//		mDateNumberPicker.setDisplayedValues(null);
//		for(int i =0; i<7;++i ){
//			calendar.add(Calendar.DAY_OF_YEAR, 2);
//			mDateDisplayValues[i] =(String) DateFormat.format("MM.dd.EEEE", calendar);
//		}
//		mDateNumberPicker.setDisplayedValues(mDateDisplayValues);
//		mDateNumberPicker.setValue(7);
//		mDateNumberPicker.invalidate();
//		
//	}
//
//}
