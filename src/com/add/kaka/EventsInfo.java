package com.add.kaka;


//用于保存各类事件设置的内容
public class EventsInfo {

	private String homework;
	private String exam;
	private String habbit;
	private String meetting;
	private String otherEvent;
	private int year=0;
	private int month=0;
	private int day=0;
	private int hour=0;
	private int minute=0;
	private String alertTime;
	private long date=0;
	
	private String buildTime;
	
	public void setDate(long date){
		this.date=date;
	}
	public long getDate(){
		return this.date;
	}
	public void setHomework(String homework){
		this.homework=homework;
	}
	public void setExam(String exam){
		this.exam=exam;
	}
	public void setHabbit(String habbit){
		this.habbit=habbit;
	}
	public void setMeetting(String meetting){
		this.meetting=meetting;
	}
	public void setOtherEvent(String otherEvent){
		this.otherEvent=otherEvent;
	}
	public void setYear(int year){
		this.year=year;
	}
	public void setMonth(int month){
		this.month=month;
	}
	public void setDay(int day){
		this.day=day;
	}
	public void setHour(int hour){
		this.hour=hour;
	}
	public void setMinute(int minute){
		this.minute=minute;
	}
	public void setAlertTime(String alertTime){
		this.alertTime=alertTime;
	}
	public String getHomework(){
		return this.homework;
	}
	public String getExam(){
		return this.exam;
	}
	public String getHabbit(){
		return this.habbit;
	}
	public String getMeeting(){
		return this.meetting;
	}
	public String getOtherEvent(){
		return this.otherEvent;
	}
	public int getYear(){
		return this.year;
	}
	public int getMonth(){
		return this.month;
	}
	public int getDay(){
		return this.day;
	}
	public int getHour(){
		return this.hour;	
	}
	public int getMinute(){
		return this.minute;
	}
	public String getAlertTime(){
		return this.alertTime;
	}
	public void setBuildTime(String buildTime){
		this.buildTime=buildTime;
	}
	public String getBuildTime(){
		return this.buildTime;
	}
	
}
