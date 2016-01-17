package com.dialog.kaka;

import java.util.Calendar;

import com.add.kaka.EventsInfo;
import com.dialog.kaka.BaseEventTimePicker.OnDateTimeChangedListener;
 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.format.DateUtils;
 
public class DateTimePickerDialog extends AlertDialog implements
        OnClickListener {
    private BaseEventTimePicker mDateTimePicker;
    private Calendar mDate = Calendar.getInstance();
    //接口实例
    private OnDateTimeSetListener mOnDateTimeSetListener;
    @SuppressWarnings("deprecation")
	public DateTimePickerDialog(Context context, long date,EventsInfo mEventsInfo) {
        super(context);
        mDateTimePicker = new BaseEventTimePicker(context,mEventsInfo);
        setView(mDateTimePicker);
        
        /**
         *实现接口，实现里面的方法
         */
        mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener(){
			@Override
			//设置时间,滚动选择时间就会触发这个事件
			public void OnDateTimeChanged(BaseEventTimePicker view,
					int year, int month, int day, int hour, int minute) {
				mDate.set(Calendar.YEAR, year);
                mDate.set(Calendar.MONTH, month);
                mDate.set(Calendar.DAY_OF_MONTH, day);
                mDate.set(Calendar.HOUR_OF_DAY, hour);
                mDate.set(Calendar.MINUTE, minute);
               // mDate.set(Calendar.SECOND, 0);
               // 更新对话框标题上的日期
                updateTitle(mDate.getTimeInMillis());
			}
        });
        
        setButton("设置", this);
        setButton2("取消", (OnClickListener) null);
        mDate.setTimeInMillis(date);
        updateTitle(mDate.getTimeInMillis());
    }
    /**
     *接口回調  
     *控件 秒数
     */
    public interface OnDateTimeSetListener {
        void OnDateTimeSet(AlertDialog dialog, long date);
    }
    /**
     * 更新对话框日期
     * @param date
     */
    private void updateTitle(long date) {
        int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
        setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
    }
    /**
     * 对外公开方法让Activity实现
     */
    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }
    
    /**
    *点击设置按钮就会触发这个函数，给接口传值更新text显示的内容
    */
    public void onClick(DialogInterface arg0, int arg1) {
        if (mOnDateTimeSetListener != null) {
            mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
        }
    }
}