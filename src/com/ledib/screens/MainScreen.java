package com.ledib.screens;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ledib.R;
import com.ledib.app.LedibApp;
import com.ledib.model.LedItem;

public class MainScreen extends Activity {

	GridView mGrid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init() {
		mGrid = (GridView) findViewById(R.id.tbl);
		mGrid.setAdapter(new AppsAdapter(this, LedibApp.getInstance()
				.restoreState()));
		//animation initialization
		
	}

	public static class AppsAdapter extends BaseAdapter implements
			OnClickListener {

		private List<LedItem> mLeds;
		private Context mContext;

		public AppsAdapter(Context c, List<LedItem> list) {
			mLeds = list;
			mContext = c;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater ll = (LayoutInflater) mContext
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				convertView = ll.inflate(R.layout.tile, null);
				ViewHolder holder = new ViewHolder(getItem(position).getId(),
						(TextView) convertView.findViewById(R.id.name),
						(TextView) convertView.findViewById(R.id.state),
						(TextView) convertView.findViewById(R.id.intence));
				convertView.setTag(holder);
				convertView.setClickable(true);
				convertView.setOnClickListener(this);
			}

			LedItem item = getItem(position);

			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.setId(item.getId());
			holder.setName(item.getName());
			holder.setIntence(item.getProgress());
			holder.setState(item.isOn());

			return convertView;
		}

		public final int getCount() {
			return mLeds.size();
		}

		public final LedItem getItem(int position) {
			return mLeds.get(position);
		}

		public final long getItemId(int position) {
			return position;
		}

		@SuppressWarnings("unused")
		private static class ViewHolder {

			private int mId;
			private TextView mName;
			private TextView mState;
			private TextView mIntence;

			public ViewHolder(int id, TextView name, TextView state,
					TextView intence) {
				mId = id;
				mName = name;
				mState = state;
				mIntence = intence;
			}

			public int getId() {
				return mId;
			}

			public void setId(int id) {
				this.mId = id;
			}

			public TextView getName() {
				return mName;
			}

			public void setName(String mName) {
				this.mName.setText(mName);
			}

			public TextView getState() {
				return mState;
			}

			public void setState(boolean mState) {
				this.mState.setText(mState ? "On" : "Off");
				this.mState.setTextColor(mState ? Color.GREEN : Color.RED);
			}

			public TextView getIntence() {
				return mIntence;
			}

			public void setIntence(int mIntence) {
				this.mIntence.setText(mIntence + "%");
			}

		}

		

		//@Override
		public void onClick(View v) {
			if (v.getTag() instanceof ViewHolder) {
				((MainScreen) mContext).startActivityForResult(new Intent(
						mContext, LedDetail.class).putExtra(LedDetail.DATA_KEY,
						((ViewHolder) v.getTag()).getId()), DETAIL_CODE);
			}

		}

	}

	private static final int DETAIL_CODE = 0x13;
	
	@Override
	protected void onActivityResult(int req, int res, Intent data) {
		if(req == DETAIL_CODE){
			mGrid.setAdapter(new AppsAdapter(this, LedibApp.getInstance()
					.restoreState()));
		}
	}

}
