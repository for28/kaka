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
        	//��ȡϵͳʱ��
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
        
         //���ز���
        inflate(context, R.layout.base_edit_dialog, this);
        
         //��ʼ���ؼ�
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
      * Ϊ�ؼ����ü�����
      */
    private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
             
            // ��������
            updateDateControl();
            //���ӿڴ�ֵ
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
     *��������
     */
    private void updateDateControl() {
        //���ڵ��㷨
        Calendar cal = Calendar.getInstance();
        //���������������Ϊ��λ��ʱ��
        cal.setTimeInMillis(mDate.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, -7 / 2 - 1);
        mDateSpinner.setDisplayedValues(null);
        for (int i = 0; i < 7; ++i) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            mDateDisplayValues[i] = (String) DateFormat.format("MM.dd EEEE", cal);
        }
        //����Ҫ��ʾ��ֵ
        mDateSpinner.setDisplayedValues(mDateDisplayValues);
        
        mDateSpinner.setValue(7 / 2);
        mDateSpinner.invalidate();
    }
     
    /**
     *�ӿڻص� �����ǵ�ǰ��View ������Сʱ����
     */
    public interface OnDateTimeChangedListener {
		void OnDateTimeChanged(BaseEventTimePicker view, int year, int month,
				int day, int hour, int minute);
    }
    /**
     *����Ĺ������� 
     */
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }
    /**
     * ���ӿڴ�ֵ
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
//		//��ȡʱ��
//		mDate = Calendar.getInstance();
//		mHour = mDate.get(Calendar.HOUR_OF_DAY);
//		mMinute = mDate.get(Calendar.MINUTE);
//		
//		//���ز���
//		inflate(context, R.layout.layout_base_event_dialog, this);
//		
//		//��ʼ���ؼ�
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
//	//���ü����� 
//	// OnValueChangeListener ��һ��������ǰ��ֵ�ı�ļ����ӿ�
//	private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
//		@Override
//		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//			//������ԭ������������ʱ��֮�����������óɿؼ��ϵ� ����
//			mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
//			//��������
//			updateDateControl();
//			//���ӿڴ�ֵ
//			OnDateTimeChanged();
//		}
//
//	
//	};
//	private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
//		@Override
//		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//			//ֱ�ӻ�ȡ�ؼ��ϵ�ֵ
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
//	//�ӿڻص�
//	
//	public interface OnDateTimeChangedListener{
//		void OnDateTimeChanged(BaseEventTimePicker view,int year,
//				int month,int day,int hour,int minute);
//	}
//	//���⹫���ķ���
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
//		//��������
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
