package com.shanghui.call.Aty;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.shanghui.call.R;
/**
 * 开始动画
 * @author lifeidroid
 *
 */
public class Aty_Welcome extends BaseActivity {
	private LinearLayout lay_welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_welcome);
		lay_welcome = (LinearLayout) findViewById(R.id.aty_welcome);
		AlphaAnimation Alpha = new AlphaAnimation(0.3f, 1.0f);
		Alpha.setDuration(2000);
		lay_welcome.startAnimation(Alpha);
		Alpha.setAnimationListener(new AnimationImpl());

	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			lay_welcome.setBackgroundResource(R.drawable.welcome_android);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			skip();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private void skip() {
		startActivity(new Intent(this, Aty_Judge.class));
		finish();
	}

/*	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}*/
}