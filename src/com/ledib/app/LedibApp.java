package com.ledib.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;


import com.ledib.model.LedItem;

public class LedibApp extends Application {

	private static LedibApp INSTANCE;
	private List<LedItem> mLeds;

	@Override
	public void onCreate() {
		super.onCreate();
		//initApp();
		INSTANCE = this;

	}

	public static LedibApp getInstance() {
		return INSTANCE;
	}

	public List<LedItem> getData() {
		return mLeds;
	}

	public void initApp() {
		mLeds = new ArrayList<LedItem>();
		mLeds.add(new LedItem(2, "Led1"));
		mLeds.add(new LedItem(3, "Led2"));
		mLeds.add(new LedItem(4, "Led3"));
		mLeds.add(new LedItem(5, "Led4"));
		mLeds.add(new LedItem(6, "Led5"));
		mLeds.add(new LedItem(7, "Led6"));
	}
	
	public void saveState(List<LedItem> list){
		mLeds = list;
	}
	
	public void clear(){
		mLeds = null;
	}
	
	public boolean isStored(){
		if(mLeds != null){
			return true;
		}else{
			return false;
		}
	}
	
	public List<LedItem> restoreState(){
		if(!isStored()){
			initApp();
		}
		return mLeds;
	}
	
}
