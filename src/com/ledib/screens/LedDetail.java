package com.ledib.screens;

import java.util.List;

import com.ledib.R;
import com.ledib.app.LedibApp;
import com.ledib.model.LedItem;
import com.ledib.views.LedView;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LedDetail extends Activity implements OnCheckedChangeListener,
		OnClickListener, OnSeekBarChangeListener {

	public static final String DATA_KEY = "id_bundle_key";
	private LedItem mLed;
	private ToggleButton mSwitcher;
	private SeekBar mSeekBar;
	private LedView mLedView;
	private TextView mProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.led_line);

		if (getIntent().getExtras() == null) {
			// error code
		} else {
			init();
			prepare(getIntent().getExtras().getInt(DATA_KEY, -1));
			show();
		}

	}

	private void init() {
		mSwitcher = (ToggleButton) findViewById(R.id.toggleButton1);
		mSwitcher.setOnCheckedChangeListener(this);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		mLedView = (LedView) findViewById(R.id.led);
		mLedView.setIntence(mSeekBar.getProgress());
		mProgress = (TextView) findViewById(R.id.persentage);
		Button bb = (Button) findViewById(R.id.button1);
		bb.setOnClickListener(this);
		Button ff = (Button) findViewById(R.id.button2);
		ff.setOnClickListener(this);

	}

	private void show() {

	}

	private void prepare(int id) {
		if (id == -1) {
			// error
			return;
		}

		List<LedItem> items = LedibApp.getInstance().restoreState();
		for (LedItem item : items) {
			if (item.getId() == id) {
				mLed = item;
			}
		}

		mSeekBar.setProgress(mLed.getProgress());
		mSwitcher.setChecked(mLed.isOn());
		mProgress.setText(Integer.toString(mLed.getProgress()) + "%");
		((TextView) findViewById(R.id.textView1)).setText(mLed.getName());
	}


	@Override

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mLedView.setIntence(progress);
		mLed.setProgress(progress);
		mProgress.setText(Integer.toString(progress) + "%");
	}


	@Override

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}


	@Override

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}


	@Override

	public void onClick(View v) {
		// TODO Auto-generated method stub
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


	@Override

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		mLedView.setOn(buttonView.isChecked());
		mLed.setOn(buttonView.isChecked());
	}

}
