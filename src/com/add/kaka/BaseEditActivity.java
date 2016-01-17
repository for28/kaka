package com.add.kaka;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sdp.RepeatTime;

import com.dialog.kaka.DateTimePickerDialog;
import com.dialog.kaka.DateTimePickerDialog.OnDateTimeSetListener;
import com.example.kaka.R;
import com.sqlite.kaka.DataBaseHelper;
import com.sqlite.kaka.SQLiteUtils;
import com.tabs.kaka.MyTabActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.text.StaticLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BaseEditActivity extends Activity {
	 public int year, month, day;
     public int hour, minute;
     public EventsInfo mEventsInfo=new EventsInfo();
     public  EditText editText;

     private Calendar cal;
     
     public String timeItem="ʱ��";
     public String alertItem="����ʱ��";
     public String repeatItem="�ظ�";
	@Override
	//ͷ����menu
	public boolean onCreateOptionsMenu(Menu menu) {
		//return super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.base_edit_layout);
    editText=(EditText)findViewById(R.id.base_edit);
	 ActionBar actionaBar =getActionBar();
     ColorDrawable actionBar_Color=new ColorDrawable(Color.parseColor("#46A3FF"));
     actionaBar.setBackgroundDrawable(actionBar_Color);
     getActionBar().setIcon(R.drawable.programs);
     
     actionaBar.setHomeButtonEnabled(true);
     
     cal = Calendar.getInstance();
     //��ȡ������ʱ�������Ϣ
     year = cal.get(Calendar.YEAR);
     //month��0��ʼ����(һ��month = 0)
     month = cal.get(Calendar.MONTH)+1;
     day = cal.get(Calendar.DAY_OF_MONTH);
     hour = cal.get(Calendar.HOUR_OF_DAY);
     minute = cal.get(Calendar.MINUTE);
     
   //�ж��Ƿ�༭
     Intent intent=getIntent();
     Bundle bundle=intent.getBundleExtra("android.intent.extra.INTENT");
     String datetime=bundle.getString("datetime");
    // mEventsInfo.setDate(Long.parseLong(datetime));
     String content=bundle.getString("content");
     mEventsInfo.setHomework(content);
     String alerttime=bundle.getString("alerttime");
     mEventsInfo.setAlertTime(alerttime);
	
     if(!content.equals("")){
    	 editText.setText(content);
     }
     if(!datetime.equals("0")){
    	 timeItem=datetime;
     }
     if(!alerttime.equals("0")){
    	 alertItem=alerttime;
     }
     
     
     //�б�����Ҫ��������
     int[] imageIds =new int[]{R.drawable.event_calendar,R.drawable.event_time,R.drawable.event_refresh} ;
     String[] textStrings = new String[]{timeItem,alertItem,repeatItem};
     
     // ����һ��list���ϣ����ϵ�Ԫ����Map
    List<Map<String ,Object>> listItems = new ArrayList<Map<String, Object>>();
    
    //��forѭ�����listItems
    for(int i =0; i<textStrings.length;i++ ){
    	Map<String, Object> listItem = new HashMap<String, Object>();
    	listItem.put("image", imageIds[i]);
    	listItem.put("text", textStrings[i]);
    	listItems.add(listItem);
    }
    /*
     * ����һ��SimpleAdapter
     * SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	*����context�������ģ�����this������SimpleAdapter���е���ͼ������
	*����data��Map�б��б�Ҫ��ʾ�����ݣ��ⲿ����Ҫ�Լ�ʵ�֣��������е�getData()������Ҫ�������һ�£�ÿ����ĿҪ��from��ָ����Ŀһ��
    *����resource��ListView������ļ���Id,������־������Զ���Ĳ����ˣ�������ʾʲô���ӵĲ��ֶ�����������С���������б��������to�ж���Ŀؼ�id
    *���� from��һ������ӵ�Map�Ϲ���ÿһ����Ŀ�����Ƶ��б�����������������
    *���� to����һ��int���飬���������id���Զ��岼���и����ؼ���id����Ҫ�������from��Ӧ
     */
     SimpleAdapter simpleAdapter= new SimpleAdapter(this, listItems,R .layout.base_edit_item,
    		 new String[]{"image","text"}, new int[]{R.id.base_event_item_image,R.id.base_event_item_text});
     ListView base_event_list = (ListView)findViewById(R.id.base_event_list);
     //����������
     base_event_list.setAdapter(simpleAdapter);
     
     // ��listView���б���ĵ����¼����ü�����
     base_event_list.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			final TextView text=(TextView)view.findViewById(R.id.base_event_item_text);
			switch (position) {
			case 0:
	
				if(mEventsInfo.getDate()==0){
					mEventsInfo.setDate(System.currentTimeMillis());
				}
				//�������һ��ѡ�����ڵĶԻ���
				
					DateTimePickerDialog dialog=
							new DateTimePickerDialog(BaseEditActivity.this, mEventsInfo.getDate(),mEventsInfo);
					//������ɵĻص�����
					 dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
				            public void OnDateTimeSet(AlertDialog dialog, long date){
//				            	Toast.makeText(BaseEventActivity.this,
//				                        "������������ǣ�" + getStringDate(date), Toast.LENGTH_LONG)
//				                        .show();
				            	CharSequence newDate=getStringDate(date);
				            	text.setText(newDate);
				            	
				            	mEventsInfo.setDate(date);
				            	//�������ڵ�EventsInfo������
				            	int y=Integer.parseInt(newDate.toString().substring(0, 4));
				            	int m=Integer.parseInt(newDate.toString().substring(5, 7));
				            	int d=Integer.parseInt(newDate.toString().substring(8, 10));
				            	int h=Integer.parseInt(newDate.toString().substring(11, 13));
				            	int min=Integer.parseInt(newDate.toString().substring(14, 16));
				            	mEventsInfo.setYear(y);
				            	mEventsInfo.setMonth(m);
				            	mEventsInfo.setDay(d);
				            	mEventsInfo.setHour(h);
				            	mEventsInfo.setMinute(min);
				            }
							private CharSequence getStringDate(long date) {
								
								 // ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm
					         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					         String dateString = formatter.format(date);
					         return dateString;
							     
							}
				        });  
					 dialog.show();
					
			break;
			case 1:
				//�������һ��ѡ������ʱ��ĶԻ���
				 AlertDialog.Builder builder = new AlertDialog.Builder(BaseEditActivity.this);
	                builder.setIcon(R.drawable.ok);
	                builder.setTitle("ѡ��һ������");
	                //    ָ�������б����ʾ����
	                final String[] types = {"��ʼʱ��", "5����ǰ", "15����ǰ", "30����ǰ", "1Сʱǰ"
	                						,"1��ǰ","2��ǰ","1������ǰ" };
	                //    ����һ���������б�ѡ����
	                builder.setItems(types, new DialogInterface.OnClickListener(){
	                    @Override
	                    public void onClick(DialogInterface dialog, int which){
	                    	text.setText(types[which]);
	                    	mEventsInfo.setAlertTime(types[which]);
	                        Toast.makeText(BaseEditActivity.this, "ѡ�������ʱ��Ϊ��" + types[which], Toast.LENGTH_SHORT).show();
	                    }
	                });
	                builder.show();			
				
				break;
			case 2:
				AlertDialog.Builder repeatBuilder = new AlertDialog.Builder(BaseEditActivity.this);
				repeatBuilder.setIcon(R.drawable.ok);
				repeatBuilder.setTitle("ѡ��һ������");
                //ָ�������б����ʾ����
                final String[] repeatTypes = {"���ظ�","ÿ��", "ÿ��", "ÿ��", "ÿ��", "2��һ��"};
                //����һ���������б�ѡ����
                repeatBuilder.setItems(repeatTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    	text.setText(repeatTypes[which]);
                        Toast.makeText(BaseEditActivity.this, "ѡ�������ʱ��Ϊ��" + repeatTypes[which], Toast.LENGTH_SHORT).show();
                    }
                });
                repeatBuilder.show();			
				break;
			default:
				break;
			}
		}
	});
	}
	//@Override
	//actionBar�ĵ���¼�
	/*
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.comfirm:
			SQLiteUtils sqlite=new SQLiteUtils();
			DataBaseHelper db=SQLiteUtils.createDBHelper(BaseEditActivity.this);
			if(!editText.getText().toString().trim().equals("")){
				mEventsInfo.setHomework(editText.getText().toString());
				sqlite.inster(db, mEventsInfo, SQLiteUtils.HOMEWORK_TAG);
				Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
				Toast.makeText(BaseEditActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(BaseEditActivity.this, "���ݲ���Ϊ��", Toast.LENGTH_LONG).show();
			}*/
			//Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			//���沢������Activity
		//	Intent intenttTest = new Intent(this,MyTabActivity.class);
			
			/**
			 * ����ʹ�ñ��   ��   FLAG_ACTIVITY_CLEAR_TOP
			 * �������ǣ������Ҫ������Activity�ڵ�ǰ�������Ѿ����ڣ�
			 * ��ô����ջ�����Activity֮�ϵ����е�Activity���б����٣�
			 * ���Ұ����Activity��ʾ���û�����������ʶ��������Ҫ�ģ�
			 * ��Ϊ������Activity�൱��һ�����˵Ķ��������ͨ����Ӧ���ٴ���һ���µ���Activity��ʵ����
			 * �������տ��ܻ��ڵ�ǰ�����в���һ���ܳ���ӵ�ж����Activity�Ķ�ջ��
			 */
		/*	intenttTest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intenttTest);
			return true;
		case android.R.id.home:
		//	Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			//������Activity,
			Intent intent = new Intent(this,MyTabActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}*/
	
	public void comfrim_click() {
		
	}
	
}
