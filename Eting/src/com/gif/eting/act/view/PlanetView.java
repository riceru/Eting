package com.gif.eting.act.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import com.gif.eting.R;
import com.gif.eting.util.AnimateDrawable;

/**
 * 움직이는 구름
 * 
 * @author lifenjoy51
 * 
 */
@SuppressLint("ViewConstructor")
public class PlanetView extends View {
	private AnimateDrawable mDrawable;

	public PlanetView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);

		setAnimationEvent(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);

		invalidate();
	}

	/**
	 * 구름애니메이션
	 * 
	 * @param context
	 * @param an
	 */
	private void setAnimationEvent(Context context, Animation an) {

		System.out.println("onAnimationEnd");
		Drawable dr = context.getResources().getDrawable(R.drawable.main_planet);
		dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;

		int objWidth = dr.getIntrinsicWidth();
		int objHeight = dr.getIntrinsicHeight();
		
		int ptX = width/2 - objWidth/2; // 수평 위치
		int ptY = height/100*40 - objHeight/2 ; // 수평 위치 끝
		
		FrameLayout.LayoutParams settingParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		settingParams.leftMargin = ptX; //Your X coordinate
		settingParams.topMargin = ptY; //Your Y coordinate
		settingParams.gravity = Gravity.LEFT | Gravity.TOP;
		this.setLayoutParams(settingParams);

		// 위아래로 왔다갔다하게하기
		an = new RotateAnimation (0, 360, objWidth/2, objHeight/2);

		an.setAnimationListener(new AnimationControl(context));

		long duration; // 이동하는 시간
		duration = 35000;

		an.setDuration(duration);
		an.setRepeatCount(-1);
		an.initialize(objWidth, objHeight, width, height);

		mDrawable = new AnimateDrawable(dr, an);
		an.startNow();

	}

	/**
	 * 구름애니메이션 상태를 확인하는 클래스
	 * 
	 * @author lifenjoy51
	 * 
	 */
	private class AnimationControl implements AnimationListener {
		Context context;

		public AnimationControl(Context context) {
			this.context = context;
		}

		@Override
		public void onAnimationEnd(Animation an) {
		}

		/**
		 * 애니메이션이 반복될때마다 실행
		 */
		@Override
		public void onAnimationRepeat(Animation an) {
		}

		@Override
		public void onAnimationStart(Animation arg0) {

		}
	}
}