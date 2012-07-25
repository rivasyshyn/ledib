package com.ledib.screens;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ledib.R;
import com.ledib.app.LedibApp;
import com.ledib.model.LedItem;
import com.ledib.views.LedView;

public class LEDibActivity extends Activity implements OnSeekBarChangeListener,
		OnCheckedChangeListener, OnClickListener {

	private ToggleButton mSwitcher;
	private SeekBar mSeekBar;
	private LedView mLed;

	private List<LedItem> mLeds;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leds_list);
		// init();
		requestLeds();
		initList();
	}

	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		LedibApp.getInstance().saveState(mLeds);
	}
	
	@Override
	public void onBackPressed(){
		LedibApp.getInstance().clear();
		super.onBackPressed();
	}
	
	@SuppressWarnings("unused")
	private void init() {
		mSwitcher = (ToggleButton) findViewById(R.id.toggleButton1);
		mSwitcher.setOnCheckedChangeListener(this);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		mLed = (LedView) findViewById(R.id.led);
		mLed.setIntence(mSeekBar.getProgress());
	}

	private void initList() {
		ListView v = (ListView) findViewById(R.id.list);
		v.setAdapter(new LedAdapter(getApplicationContext(), 0, mLeds));
	}

	public void requestLeds() {
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// buttonView.toggle();
		mLed.setOn(buttonView.isChecked());
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		mLed.setIntence(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			mSeekBar.setProgress(0);
			break;

		case R.id.button2:
			mSeekBar.setProgress(100);
			break;

		default:
			break;
		}

	}

	private class LedAdapter extends ArrayAdapter<LedItem> {

		List<LedItem> mLeds;

		public LedAdapter(Context context, int resource, List<LedItem> objects) {
			super(context, resource, objects);
			mLeds = objects;
		}

		@Override
		public LedItem getItem(int position) {
			return mLeds.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.led_line, null);
				ViewHolder holder = new ViewHolder(
						(TextView) convertView.findViewById(R.id.textView1),
						(SeekBar) convertView.findViewById(R.id.seekBar1),
						(LedView) convertView.findViewById(R.id.led),
						(ToggleButton) convertView
								.findViewById(R.id.toggleButton1));
				convertView.setTag(holder);
				convertView.findViewById(R.id.button1).setOnClickListener(
						holder);
				convertView.findViewById(R.id.button2).setOnClickListener(
						holder);
			}
			ViewHolder holder = (ViewHolder) convertView.getTag();
			LedItem item = getItem(position);
			Log.v("adapter", "" + (item == null));

			holder.setId(position);
			holder.setName(item.getName());
			holder.setOn(item.isOn());
			holder.setProgress(item.getProgress());

			return convertView;
		}

		@SuppressWarnings("unused")
		private class ViewHolder implements OnCheckedChangeListener,
				OnClickListener, OnSeekBarChangeListener {

			private int mId;
			private TextView mName;
			private SeekBar mSeekBar;
			private LedView mLedView;
			private ToggleButton mSwitcher;

			public ViewHolder(TextView name, SeekBar seekbar, LedView ledview,
					ToggleButton switcher) {
				setName(name);
				setSeekBar(seekbar);
				setLedView(ledview);
				setSwitcher(switcher);
			}

			public void setName(String name) {
				mName.setText(name);
			}

			public void setProgress(int progress) {
				mSeekBar.setProgress(progress);
				mLedView.setIntence(progress);
			}

			public void setOn(boolean on) {
				mLedView.setOn(on);
				mSwitcher.setChecked(on);
			}

			public TextView getName() {
				return mName;
			}

			public void setName(TextView mName) {
				this.mName = mName;
			}

			public SeekBar getSeekBar() {
				return mSeekBar;
			}

			public void setSeekBar(SeekBar mSeekBar) {
				this.mSeekBar = mSeekBar;
				this.mSeekBar.setOnSeekBarChangeListener(this);
			}

			public LedView getLedView() {
				return mLedView;
			}

			public void setLedView(LedView mLedView) {
				this.mLedView = mLedView;
			}

			public ToggleButton getSwitcher() {
				return mSwitcher;
			}

			public void setSwitcher(ToggleButton mSwitcher) {
				this.mSwitcher = mSwitcher; 
				this.mSwitcher.setOnCheckedChangeListener(this);
			}

			@Override
			public void onProgressChanged(SeekBar paramSeekBar, int paramInt,
					boolean paramBoolean) {
				this.mLedView.setIntence(paramInt);
				getItem(getId()).setProgress(paramInt);
			}

			@Override
			public void onStartTrackingTouch(SeekBar paramSeekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar paramSeekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onClick(View paramView) {
				switch (paramView.getId()) {
				case R.id.button1:
					this.mSeekBar.setProgress(0);
					getItem(getId()).setProgress(0);
					break;

				case R.id.button2:
					this.mSeekBar.setProgress(100);
					getItem(getId()).setProgress(100);
					break;

				default:
					break;
				}

			}

			@Override
			public void onCheckedChanged(CompoundButton paramCompoundButton,
					boolean paramBoolean) {
				this.mLedView.setOn(paramBoolean);
				getItem(getId()).setOn(paramBoolean);
			}

			public int getId() {
				return mId;
			}

			public void setId(int mId) {
				this.mId = mId;
			}

		}
	}
}