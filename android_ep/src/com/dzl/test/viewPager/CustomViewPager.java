package com.dzl.test.viewPager;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class CustomViewPager extends ViewPager {

	public CustomViewPager(Context context) {
		super(context);
		System.out.println("resp1onse CustomViewPager: public CustomViewPager(Context context) { start constructor ");
		System.out.println("resp1onse CustomViewPager: public CustomViewPager(Context context) { end constructor ");
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		System.out.println("resp1onse CustomViewPager: public CustomViewPager(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse CustomViewPager: public CustomViewPager(Context context, AttributeSet attrs) { end constructor ");
	}

	@Override
	public void addFocusables(ArrayList<View> arg0, int arg1, int arg2) {
		System.out.println("resp1onse CustomViewPager: public void addFocusables(ArrayList<View> arg0, int arg1, int arg2) { start void ");

		super.addFocusables(arg0, arg1, arg2);
		System.out.println("resp1onse CustomViewPager: public void addFocusables(ArrayList<View> arg0, int arg1, int arg2) { end void ");
	}

	@Override
	public void addTouchables(ArrayList<View> arg0) {
		System.out.println("resp1onse CustomViewPager: public void addTouchables(ArrayList<View> arg0) { start void ");

		super.addTouchables(arg0);
		System.out.println("resp1onse CustomViewPager: public void addTouchables(ArrayList<View> arg0) { end void ");
	}

	@Override
	public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomViewPager: public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) { start void ");

		super.addView(child, index, params);
		System.out.println("resp1onse CustomViewPager: public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	public boolean arrowScroll(int arg0) {

		System.out.println("resp1onse CustomViewPager: public boolean arrowScroll(int arg0) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean arrowScroll(int arg0) { end return ");
		return super.arrowScroll(arg0);
	}

	@Override
	public boolean beginFakeDrag() {

		System.out.println("resp1onse CustomViewPager: public boolean beginFakeDrag() { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean beginFakeDrag() { end return ");
		return super.beginFakeDrag();
	}

	@Override
	protected boolean canScroll(View arg0, boolean arg1, int arg2, int arg3, int arg4) {

		System.out.println("resp1onse CustomViewPager: protected boolean canScroll(View arg0, boolean arg1, int arg2, int arg3, int arg4) { start return ");
		System.out.println("resp1onse CustomViewPager: protected boolean canScroll(View arg0, boolean arg1, int arg2, int arg3, int arg4) { end return ");
		return super.canScroll(arg0, arg1, arg2, arg3, arg4);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean canScrollHorizontally(int direction) {
		System.out.println("resp1onse CustomViewPager: public boolean canScrollHorizontally(int direction) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean canScrollHorizontally(int direction) { end return ");
		return super.canScrollHorizontally(direction);
	}

	@Override
	protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {

		System.out.println("resp1onse CustomViewPager: protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomViewPager: protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) { end return ");
		return super.checkLayoutParams(p);
	}

	@Override
	public void computeScroll() {
		System.out.println("resp1onse CustomViewPager: public void computeScroll() { start void ");

		super.computeScroll();
		System.out.println("resp1onse CustomViewPager: public void computeScroll() { end void ");
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		System.out.println("resp1onse CustomViewPager: public boolean dispatchKeyEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean dispatchKeyEvent(KeyEvent event) { end return ");
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0) {

		System.out.println("resp1onse CustomViewPager: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0) { end return ");
		return super.dispatchPopulateAccessibilityEvent(arg0);
	}

	@Override
	public void draw(Canvas arg0) {
		System.out.println("resp1onse CustomViewPager: public void draw(Canvas arg0) { start void ");

		super.draw(arg0);
		System.out.println("resp1onse CustomViewPager: public void draw(Canvas arg0) { end void ");
	}

	@Override
	protected void drawableStateChanged() {
		System.out.println("resp1onse CustomViewPager: protected void drawableStateChanged() { start void ");

		super.drawableStateChanged();
		System.out.println("resp1onse CustomViewPager: protected void drawableStateChanged() { end void ");
	}

	@Override
	public void endFakeDrag() {
		System.out.println("resp1onse CustomViewPager: public void endFakeDrag() { start void ");

		super.endFakeDrag();
		System.out.println("resp1onse CustomViewPager: public void endFakeDrag() { end void ");
	}

	@Override
	public boolean executeKeyEvent(KeyEvent event) {

		System.out.println("resp1onse CustomViewPager: public boolean executeKeyEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean executeKeyEvent(KeyEvent event) { end return ");
		return super.executeKeyEvent(event);
	}

	@Override
	public void fakeDragBy(float xOffset) {
		System.out.println("resp1onse CustomViewPager: public void fakeDragBy(float xOffset) { start void ");

		super.fakeDragBy(xOffset);
		System.out.println("resp1onse CustomViewPager: public void fakeDragBy(float xOffset) { end void ");
	}

	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {

		System.out.println("resp1onse CustomViewPager: protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() { start return ");
		System.out.println("resp1onse CustomViewPager: protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() { end return ");
		return super.generateDefaultLayoutParams();
	}

	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {

		System.out.println("resp1onse CustomViewPager: public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) { start return ");
		System.out.println("resp1onse CustomViewPager: public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) { end return ");
		return super.generateLayoutParams(attrs);
	}

	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {

		System.out.println("resp1onse CustomViewPager: protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomViewPager: protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) { end return ");
		return super.generateLayoutParams(p);
	}

	@Override
	public PagerAdapter getAdapter() {

		System.out.println("resp1onse CustomViewPager: public PagerAdapter getAdapter() { start return ");
		System.out.println("resp1onse CustomViewPager: public PagerAdapter getAdapter() { end return ");
		return super.getAdapter();
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {

		System.out.println("resp1onse CustomViewPager: protected int getChildDrawingOrder(int childCount, int i) { start return ");
		System.out.println("resp1onse CustomViewPager: protected int getChildDrawingOrder(int childCount, int i) { end return ");
		return super.getChildDrawingOrder(childCount, i);
	}

	@Override
	public int getCurrentItem() {

		System.out.println("resp1onse CustomViewPager: public int getCurrentItem() { start return ");
		System.out.println("resp1onse CustomViewPager: public int getCurrentItem() { end return ");
		return super.getCurrentItem();
	}

	@Override
	public int getOffscreenPageLimit() {

		System.out.println("resp1onse CustomViewPager: public int getOffscreenPageLimit() { start return ");
		System.out.println("resp1onse CustomViewPager: public int getOffscreenPageLimit() { end return ");
		return super.getOffscreenPageLimit();
	}

	@Override
	public int getPageMargin() {

		System.out.println("resp1onse CustomViewPager: public int getPageMargin() { start return ");
		System.out.println("resp1onse CustomViewPager: public int getPageMargin() { end return ");
		return super.getPageMargin();
	}

	@Override
	public boolean isFakeDragging() {

		System.out.println("resp1onse CustomViewPager: public boolean isFakeDragging() { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean isFakeDragging() { end return ");
		return super.isFakeDragging();
	}

	@Override
	protected void onAttachedToWindow() {
		System.out.println("resp1onse CustomViewPager: protected void onAttachedToWindow() { start void ");

		super.onAttachedToWindow();
		System.out.println("resp1onse CustomViewPager: protected void onAttachedToWindow() { end void ");
	}

	@Override
	protected void onDetachedFromWindow() {
		System.out.println("resp1onse CustomViewPager: protected void onDetachedFromWindow() { start void ");

		super.onDetachedFromWindow();
		System.out.println("resp1onse CustomViewPager: protected void onDetachedFromWindow() { end void ");
	}

	@Override
	protected void onDraw(Canvas arg0) {
		System.out.println("resp1onse CustomViewPager: protected void onDraw(Canvas arg0) { start void ");

		super.onDraw(arg0);
		System.out.println("resp1onse CustomViewPager: protected void onDraw(Canvas arg0) { end void ");
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {

		System.out.println("resp1onse CustomViewPager: public boolean onInterceptTouchEvent(MotionEvent arg0) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean onInterceptTouchEvent(MotionEvent arg0) { end return ");
		return super.onInterceptTouchEvent(arg0);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		System.out.println("resp1onse CustomViewPager: protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) { start void ");

		super.onLayout(arg0, arg1, arg2, arg3, arg4);
		System.out.println("resp1onse CustomViewPager: protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) { end void ");
	}

	@Override
	protected void onMeasure(int arg0, int arg1) {
		System.out.println("resp1onse CustomViewPager: protected void onMeasure(int arg0, int arg1) { start void ");

		super.onMeasure(arg0, arg1);
		System.out.println("resp1onse CustomViewPager: protected void onMeasure(int arg0, int arg1) { end void ");
	}

	@Override
	protected void onPageScrolled(int arg0, float arg1, int arg2) {
		System.out.println("resp1onse CustomViewPager: protected void onPageScrolled(int arg0, float arg1, int arg2) { start void ");

		super.onPageScrolled(arg0, arg1, arg2);
		System.out.println("resp1onse CustomViewPager: protected void onPageScrolled(int arg0, float arg1, int arg2) { end void ");
	}

	@Override
	protected boolean onRequestFocusInDescendants(int arg0, Rect arg1) {

		System.out.println("resp1onse CustomViewPager: protected boolean onRequestFocusInDescendants(int arg0, Rect arg1) { start return ");
		System.out.println("resp1onse CustomViewPager: protected boolean onRequestFocusInDescendants(int arg0, Rect arg1) { end return ");
		return super.onRequestFocusInDescendants(arg0, arg1);
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		System.out.println("resp1onse CustomViewPager: public void onRestoreInstanceState(Parcelable state) { start void ");

		super.onRestoreInstanceState(state);
		System.out.println("resp1onse CustomViewPager: public void onRestoreInstanceState(Parcelable state) { end void ");
	}

	@Override
	public Parcelable onSaveInstanceState() {

		System.out.println("resp1onse CustomViewPager: public Parcelable onSaveInstanceState() { start return ");
		System.out.println("resp1onse CustomViewPager: public Parcelable onSaveInstanceState() { end return ");
		return super.onSaveInstanceState();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		System.out.println("resp1onse CustomViewPager: protected void onSizeChanged(int w, int h, int oldw, int oldh) { start void ");

		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("resp1onse CustomViewPager: protected void onSizeChanged(int w, int h, int oldw, int oldh) { end void ");
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {

		System.out.println("resp1onse CustomViewPager: public boolean onTouchEvent(MotionEvent arg0) { start return ");
		System.out.println("resp1onse CustomViewPager: public boolean onTouchEvent(MotionEvent arg0) { end return ");
		return super.onTouchEvent(arg0);
	}

	@Override
	public void removeView(View view) {
		System.out.println("resp1onse CustomViewPager: public void removeView(View view) { start void ");

		super.removeView(view);
		System.out.println("resp1onse CustomViewPager: public void removeView(View view) { end void ");
	}

	@Override
	public void setAdapter(PagerAdapter arg0) {
		System.out.println("resp1onse CustomViewPager: public void setAdapter(PagerAdapter arg0) { start void ");

		super.setAdapter(arg0);
		System.out.println("resp1onse CustomViewPager: public void setAdapter(PagerAdapter arg0) { end void ");
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		System.out.println("resp1onse CustomViewPager: public void setCurrentItem(int item, boolean smoothScroll) { start void ");

		super.setCurrentItem(item, smoothScroll);
		System.out.println("resp1onse CustomViewPager: public void setCurrentItem(int item, boolean smoothScroll) { end void ");
	}

	@Override
	public void setCurrentItem(int item) {
		System.out.println("resp1onse CustomViewPager: public void setCurrentItem(int item) { start void ");

		super.setCurrentItem(item);
		System.out.println("resp1onse CustomViewPager: public void setCurrentItem(int item) { end void ");
	}

	@Override
	public void setOffscreenPageLimit(int limit) {
		System.out.println("resp1onse CustomViewPager: public void setOffscreenPageLimit(int limit) { start void ");

		super.setOffscreenPageLimit(limit);
		System.out.println("resp1onse CustomViewPager: public void setOffscreenPageLimit(int limit) { end void ");
	}

	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		System.out.println("resp1onse CustomViewPager: public void setOnPageChangeListener(OnPageChangeListener listener) { start void ");

		super.setOnPageChangeListener(listener);
		System.out.println("resp1onse CustomViewPager: public void setOnPageChangeListener(OnPageChangeListener listener) { end void ");
	}

	@Override
	public void setPageMargin(int marginPixels) {
		System.out.println("resp1onse CustomViewPager: public void setPageMargin(int marginPixels) { start void ");

		super.setPageMargin(marginPixels);
		System.out.println("resp1onse CustomViewPager: public void setPageMargin(int marginPixels) { end void ");
	}

	@Override
	public void setPageMarginDrawable(Drawable d) {
		System.out.println("resp1onse CustomViewPager: public void setPageMarginDrawable(Drawable d) { start void ");

		super.setPageMarginDrawable(d);
		System.out.println("resp1onse CustomViewPager: public void setPageMarginDrawable(Drawable d) { end void ");
	}

	@Override
	public void setPageMarginDrawable(int resId) {
		System.out.println("resp1onse CustomViewPager: public void setPageMarginDrawable(int resId) { start void ");

		super.setPageMarginDrawable(resId);
		System.out.println("resp1onse CustomViewPager: public void setPageMarginDrawable(int resId) { end void ");
	}

	@Override
	public void setPageTransformer(boolean arg0, PageTransformer arg1) {
		System.out.println("resp1onse CustomViewPager: public void setPageTransformer(boolean arg0, PageTransformer arg1) { start void ");

		super.setPageTransformer(arg0, arg1);
		System.out.println("resp1onse CustomViewPager: public void setPageTransformer(boolean arg0, PageTransformer arg1) { end void ");
	}

	@Override
	protected boolean verifyDrawable(Drawable who) {

		System.out.println("resp1onse CustomViewPager: protected boolean verifyDrawable(Drawable who) { start return ");
		System.out.println("resp1onse CustomViewPager: protected boolean verifyDrawable(Drawable who) { end return ");
		return super.verifyDrawable(who);
	}

}
