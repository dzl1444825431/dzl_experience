//package com.dzl.test.anim;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.DisplayMetrics;
//import android.util.TypedValue;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AccelerateInterpolator;
//import android.view.animation.Animation;
//import android.view.animation.AnimationSet;
//import android.view.animation.LinearInterpolator;
//import android.view.animation.ScaleAnimation;
//import android.view.animation.TranslateAnimation;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
//
//import com.dzl.zyyidianyun.Constants;
//import com.dzl.zyyidianyun.R;
//import com.dzl.zyyidianyun.R.color;
//import com.squareup.picasso.Picasso;
//
//@SuppressLint("NewApi")
//public class AnimatorUtil extends Activity {
//
//	private View tv;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
////		setContentView(R.layout.animator);
////		tv = findViewById(R.id.textView1);
////
////		tv.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				paowuxian("http://qiniu.ilovegame.net/gallery/BDZ6p9l7nV6.png",
////						39, 391);
////			}
////		});
//
//		activity = this;
//		
//		paowuxian("http://qiniu.ilovegame.net/gallery/BDZ6p9l7nV6.png",
//				39, 391);
//
////		final AnimatorSet animatorSet = new AnimatorSet();
////		animatorSet.play(
////				ObjectAnimator.ofFloat(tv, View.SCALE_X, new float[] { 0.0F,
////						1.1F })).with(
////				ObjectAnimator.ofFloat(tv, View.SCALE_Y, new float[] { 0.0F,
////						1.1F }));
////		animatorSet.setDuration(5000L);
////		animatorSet.setInterpolator(new OvershootInterpolator(5.2F));
////		animatorSet.start();
////		animatorSet.addListener(new AnimatorListenerAdapter() {
////			public void onAnimationEnd(Animator animator) {
////				System.out.println("respone : " + "end");
////				// animatorSet.start();
////			}
////		});
////		animatorSet.start();
//		System.out.println("respone : getDip 55 = " + getDip(this, 55));
//		System.out.println("respone : getDip 55 = " + getDip(this, 10));
//	}
//
//	public static int getDip(Context paramContext, int paramInt) {
//		return (int) TypedValue.applyDimension(1, paramInt, paramContext
//				.getResources().getDisplayMetrics());
//	}
//
//	public void paowuxian(String url, int x_location, int y_location) {
//		initScreenDisplayMetrics(activity);
//		ImageView imageView = new ImageView(activity);
//		int i = getDip(activity, 55);// 82
//		imageView.setLayoutParams(new LinearLayout.LayoutParams(i, i));
//		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//		// imageView.setCornerRadius(R.dimen.rec_game_codius);
//		imageView.setBackgroundColor(getResources().getColor(
//				R.color.transparent));
//		if (!TextUtils.isEmpty(url)) {
//			ImageLoadUitl.loadPicasso(activity, "http://qiniu.ilovegame.net/gallery/BDZ6p9l7nV6.png", imageView);
////			Picasso.with(this.activity)
////					.load("http://qiniu.ilovegame.net/gallery/BDZ6p9l7nV6.png")
////					.into(imageView);
//		}
//		setAnim(imageView, x_location, y_location);
//	}
//
//	public void initScreenDisplayMetrics(Activity activity) {
//		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay()
//				.getMetrics(localDisplayMetrics);
//		Constants.screenHeight = localDisplayMetrics.heightPixels;
//		Constants.screenWidth = localDisplayMetrics.widthPixels;
//	}
//
////	private ViewGroup anim_mask_layout;
//
//	private void setAnim(final View imageView, int x_location, int y_location) {
//		ViewGroup anim_mask_layout = createAnimLayout();
//		anim_mask_layout.addView(imageView);
//
//		addViewToAnimLayout(imageView, x_location, y_location);
//
//		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//				-2, -2);
//		layoutParams.leftMargin = x_location;
//		layoutParams.topMargin = y_location;
//		imageView.setLayoutParams(layoutParams);
//
//		int i = Constants.screenWidth / 2;
//		int j = Constants.screenHeight;
//		AnimationSet animationSet = createAnimation2(x_location, y_location, i,
//				j);
//		imageView.startAnimation(animationSet);
//
//		animationSet.setAnimationListener(new Animation.AnimationListener() {
//			public void onAnimationEnd(Animation paramAnonymousAnimation) {
//				imageView.setVisibility(8);
//				// if (AppIconAnimUtil.this.mAdapter != null) {
//				// AppIconAnimUtil.this.mAdapter
//				// .notifyDataSetChanged();
//				// }
//			}
//
//			public void onAnimationRepeat(Animation paramAnonymousAnimation) {
//			}
//
//			public void onAnimationStart(Animation paramAnonymousAnimation) {
//				imageView.setVisibility(0);
//			}
//		});
//	}
//
//	private AnimationSet createAnimation(int x_location, int y_location, int i,
//			int j) {
//		TranslateAnimation translateAnimation1 = new TranslateAnimation(0.0F, i
//				- x_location + 40, 0.0F, 0.0F);
//		translateAnimation1.setInterpolator(new LinearInterpolator());
//		translateAnimation1.setRepeatCount(0);
//		translateAnimation1.setFillAfter(true);
//		TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0F,
//				0.0F, 0.0F, j - y_location);
//		translateAnimation2.setInterpolator(new AccelerateInterpolator());
//		translateAnimation2.setRepeatCount(0);
//		translateAnimation1.setFillAfter(true);
//		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0F, 0.4F, 1.0F,
//				0.4F, 1, 0.5F, 1, 0.5F);
//		scaleAnimation.setInterpolator(new LinearInterpolator());
//		AnimationSet animationSet = new AnimationSet(false);
//		animationSet.setFillAfter(false);
//		animationSet.addAnimation(scaleAnimation);
//		animationSet.addAnimation(translateAnimation2);
//		animationSet.addAnimation(translateAnimation1);
//		animationSet.setDuration(600L);
//		return animationSet;
//	}
//
//	private ViewGroup createAnimLayout() {
//		ViewGroup localViewGroup = (ViewGroup) this.activity.getWindow()
//				.getDecorView();
//		LinearLayout localLinearLayout = new LinearLayout(this.activity);
//		localLinearLayout
//				.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
//		localLinearLayout.setId(2147483647);
//		// localLinearLayout.setBackgroundResource(17170445);
//		localViewGroup.addView(localLinearLayout);
//		return localLinearLayout;
//	}
//
//	private View addViewToAnimLayout(View view, int leftMargin, int topMargin) {
//		LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
//		layoutParams.leftMargin = leftMargin;
//		layoutParams.topMargin = topMargin;
//		view.setLayoutParams(layoutParams);
//		return view;
//	}
//	
//	AnimationSet createAnimation2(int x_location, int y_location, int i,
//			int j) {
//		TranslateAnimation translateAnimation1 = 
//				new TranslateAnimation(0, -x_location, 0, -y_location);
//		translateAnimation1.setInterpolator(new LinearInterpolator());
//		translateAnimation1.setRepeatCount(0);
//		translateAnimation1.setFillAfter(true);
////		TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0F,
////				0.0F, 0.0F, j - y_location);
////		translateAnimation2.setInterpolator(new AccelerateInterpolator());
////		translateAnimation2.setRepeatCount(0);
////		translateAnimation1.setFillAfter(true);
//		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0F, 0.4F, 1.0F,
//				0.4F, 1, 0.5F, 1, 0.5F);
//		scaleAnimation.setInterpolator(new LinearInterpolator());
//		AnimationSet animationSet = new AnimationSet(false);
//		animationSet.setFillAfter(false);
//		animationSet.addAnimation(scaleAnimation);
////		animationSet.addAnimation(translateAnimation2);
//		animationSet.addAnimation(translateAnimation1);
//		animationSet.setDuration(600L);
//		return animationSet;
//	}
//	private Activity activity;
//	private int screenHeight;
//	private int screenWidth;
//
//}
