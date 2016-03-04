package com.android.demo.android.widget.viewpager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */


public class AutoScrollViewPager extends ViewPager {

	private Timer timer;
	private TimerTask task;
	private final AtomicBoolean pausedTouch = new AtomicBoolean(false);
	private int curIndex = 0;

	public AutoScrollViewPager(Context context) {
		super(context);

	}

	public AutoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// 当拦截触摸事件到达此位置的时候，返回true，
		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
		return true;
	}

	public void startAd(long delay, long period) {
		assert getAdapter() != null;
		if (getAdapter().getCount() > 1) {
			if (timer == null) {
				timer = new Timer();
			}
			if (task != null) {
				task.cancel();
			}
			task = new TimerTask() {

				@Override
				public boolean cancel() {
					resume();
					return super.cancel();
				}

				@Override
				public void run() {
					waitIfTouch();
					if (curIndex == getAdapter().getCount() - 1) {
						curIndex = 0;
					} else {
						curIndex++;
					}
					AutoScrollViewPager.this.post(new Runnable() {
						@Override
						public void run() {
							setCurrentItem(curIndex, true);
						}
					});
				}
			};
			timer.schedule(task, delay, period);
		}
	}

	public void stop() {
		if (task != null) {
			task.cancel();
			task = null;
		}
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	private boolean waitIfTouch() {
		if (pausedTouch.get()) {
			synchronized (pausedTouch) {
				try {
					pausedTouch.wait();
				} catch (InterruptedException e) {
					return true;
				}
			}
		}
		return false;
	}

	private void pause() {
		pausedTouch.set(true);
	}

	private void resume() {
		synchronized (pausedTouch) {
			pausedTouch.set(false);
			pausedTouch.notifyAll();
		}
	}

	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();

	public boolean onTouchEvent(MotionEvent arg0) {
		// 每次进行onTouch事件都记录当前的按下的坐标
		curP.x = arg0.getX();
		curP.y = arg0.getY();

		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
			downP.x = arg0.getX();
			downP.y = arg0.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			pause();
		} else if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			float dx = Math.abs(arg0.getX() - downP.x);
			float dy = Math.abs(arg0.getY() - downP.y);
			if (dy < dx) {
				// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
				getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
		} else if (arg0.getAction() == MotionEvent.ACTION_UP) {
			resume();
		}
		return super.onTouchEvent(arg0);
	}

}
