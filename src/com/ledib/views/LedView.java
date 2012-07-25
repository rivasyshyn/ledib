package com.ledib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LedView extends View {

	private Paint mPaint;
	private int mColorOff = 0x737300;
	private int mColorStep = 0x010102;
	private int mIntence;
	private boolean mOn;

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(getCurrentColor());

		setVisibility(View.VISIBLE);
	}

	public LedView(Context context) {
		super(context);
		init();
	}

	public LedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LedView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	// @Override
	// public void draw(Canvas c) {
	// super.draw(c);
	// c.drawCircle(c.getWidth() / 2, c.getHeight() / 2, c.getWidth() / 3,
	// mPaint);
	// }

	@Override
	public void onDraw(Canvas c) {
		// super.onDraw(c);
		if (mOn) {
			mPaint.setColor(getCurrentColor());
		} else {
			mPaint.setColor(mColorOff);
		}
		mPaint.setAlpha(255);
		mPaint.setStyle(Style.FILL);
		Log.i("color", "" + mPaint.getColor());
		c.drawCircle(this.getWidth() / 2, this.getHeight() / 2,
				this.getWidth() / 3, mPaint);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(4);
		mPaint.setColor(0x888888);
		mPaint.setAlpha(255);
		c.drawCircle(this.getWidth() / 2, this.getHeight() / 2,
				(float) (this.getWidth() / 2.5), mPaint);
		Log.i("led", "onDraw ");
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec,
			final int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
	}

	private int getCurrentColor() {
		int result = mColorOff + mColorStep * mIntence;
		return result;
	}

	public void setIntence(int intence) {
		mIntence = intence;
		invalidate();
		Log.i("led", "" + intence);
	}

	public void setOn(boolean on) {
		mOn = on;
		invalidate();
		Log.i("led", "" + on);
	}

}
