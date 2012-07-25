package com.ledib.model;

public class LedItem {

	private int mId;
	private int mProgress;
	private boolean mOn;
	private String mName;

	public LedItem(int id, String name) {
		this(id, name, false, 100);
	}

	public LedItem(int id, String name, boolean on, int progress) {
		setId(id);
		setName(name);
		setOn(on);
		setProgress(progress);
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public int getProgress() {
		return mProgress;
	}

	public void setProgress(int mProgress) {
		this.mProgress = mProgress;
	}

	public boolean isOn() {
		return mOn;
	}

	public void setOn(boolean mOn) {
		this.mOn = mOn;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}
	
	

}
