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
    //�ӿ�ʵ��
    private OnDateTimeSetListener mOnDateTimeSetListener;
    @SuppressWarnings("deprecation")
	public DateTimePickerDialog(Context context, long date,EventsInfo mEventsInfo) {
        super(context);
        mDateTimePicker = new BaseEventTimePicker(context,mEventsInfo);
        setView(mDateTimePicker);
        
        /**
         *ʵ�ֽӿڣ�ʵ������ķ���
         */
        mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener(){
			@Override
			//����ʱ��,����ѡ��ʱ��ͻᴥ������¼�
			public void OnDateTimeChanged(BaseEventTimePicker view,
					int year, int month, int day, int hour, int minute) {
				mDate.set(Calendar.YEAR, year);
                mDate.set(Calendar.MONTH, month);
                mDate.set(Calendar.DAY_OF_MONTH, day);
                mDate.set(Calendar.HOUR_OF_DAY, hour);
                mDate.set(Calendar.MINUTE, minute);
               // mDate.set(Calendar.SECOND, 0);
               // ���¶Ի�������ϵ�����
                updateTitle(mDate.getTimeInMillis());
			}
        });
        
        setButton("����", this);
        setButton2("ȡ��", (OnClickListener) null);
        mDate.setTimeInMillis(date);
        updateTitle(mDate.getTimeInMillis());
    }
    /**
     *�ӿڻ��{  
     *�ؼ� ����
     */
    public interface OnDateTimeSetListener {
        void OnDateTimeSet(AlertDialog dialog, long date);
    }
    /**
     * ���¶Ի�������
     * @param date
     */
    private void updateTitle(long date) {
        int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
        setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
    }
    /**
     * ���⹫��������Activityʵ��
     */
    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }
    
    /**
    *������ð�ť�ͻᴥ��������������ӿڴ�ֵ����text��ʾ������
    */
    public void onClick(DialogInterface arg0, int arg1) {
        if (mOnDateTimeSetListener != null) {
            mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
        }
    }
}