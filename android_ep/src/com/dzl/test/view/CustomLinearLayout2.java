package com.dzl.test.view;

import java.util.ArrayList;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;

public class CustomLinearLayout2 extends LinearLayout {

	public CustomLinearLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
		System.out.println("resp1onse CustomLinearLayout2: public CustomLinearLayout2(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse CustomLinearLayout2: public CustomLinearLayout2(Context context, AttributeSet attrs) { end constructor ");
	}

	@Override
	public void addView(View child, android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, android.view.ViewGroup.LayoutParams params) { start void ");

		super.addView(child, params);
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) { start void ");

		super.addView(child, index, params);
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	public void updateViewLayout(View view, android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomLinearLayout2: public void updateViewLayout(View view, android.view.ViewGroup.LayoutParams params) { start void ");

		super.updateViewLayout(view, params);
		System.out.println("resp1onse CustomLinearLayout2: public void updateViewLayout(View view, android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params) { end return ");
		return super.addViewInLayout(child, index, params);
	}

	@Override
	protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params,			boolean preventRequestLayout) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params,			boolean preventRequestLayout) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean addViewInLayout(View child, int index, android.view.ViewGroup.LayoutParams params,			boolean preventRequestLayout) { end return ");
		return super .addViewInLayout(child, index, params, preventRequestLayout);
	}

	@Override
	protected void attachLayoutAnimationParameters(View child, android.view.ViewGroup.LayoutParams params, int index, int count) {
		System.out.println("resp1onse CustomLinearLayout2: protected void attachLayoutAnimationParameters(View child, android.view.ViewGroup.LayoutParams params, int index, int count) { start void ");

		super.attachLayoutAnimationParameters(child, params, index, count);
		System.out.println("resp1onse CustomLinearLayout2: protected void attachLayoutAnimationParameters(View child, android.view.ViewGroup.LayoutParams params, int index, int count) { end void ");
	}

	@Override
	protected void attachViewToParent(View child, int index, android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomLinearLayout2: protected void attachViewToParent(View child, int index, android.view.ViewGroup.LayoutParams params) { start void ");

		super.attachViewToParent(child, index, params);
		System.out.println("resp1onse CustomLinearLayout2: protected void attachViewToParent(View child, int index, android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	protected LayoutParams generateLayoutParams( android.view.ViewGroup.LayoutParams p) {

		System.out.println("resp1onse CustomLinearLayout2: protected LayoutParams generateLayoutParams( android.view.ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected LayoutParams generateLayoutParams( android.view.ViewGroup.LayoutParams p) { end return ");
		return super.generateLayoutParams(p);
	}

	@Override
	protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) { end return ");
		return super.checkLayoutParams(p);
	}

	@Override
	public String toString() {

		System.out.println("resp1onse CustomLinearLayout2: public String toString() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public String toString() { end return ");
		return super.toString();
	}

	@Override
	public void setShowDividers(int showDividers) {
		System.out.println("resp1onse CustomLinearLayout2: public void setShowDividers(int showDividers) { start void ");

		super.setShowDividers(showDividers);
		System.out.println("resp1onse CustomLinearLayout2: public void setShowDividers(int showDividers) { end void ");
	}

	@Override
	public int getShowDividers() {

		System.out.println("resp1onse CustomLinearLayout2: public int getShowDividers() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getShowDividers() { end return ");
		return super.getShowDividers();
	}

	@Override
	public void setDividerDrawable(Drawable divider) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDividerDrawable(Drawable divider) { start void ");

		super.setDividerDrawable(divider);
		System.out.println("resp1onse CustomLinearLayout2: public void setDividerDrawable(Drawable divider) { end void ");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onDraw(Canvas canvas) { start void ");

		super.onDraw(canvas);
		System.out.println("resp1onse CustomLinearLayout2: protected void onDraw(Canvas canvas) { end void ");
	}

	@Override
	public boolean isBaselineAligned() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isBaselineAligned() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isBaselineAligned() { end return ");
		return super.isBaselineAligned();
	}

	@Override
	public void setBaselineAligned(boolean baselineAligned) {
		System.out.println("resp1onse CustomLinearLayout2: public void setBaselineAligned(boolean baselineAligned) { start void ");

		super.setBaselineAligned(baselineAligned);
		System.out.println("resp1onse CustomLinearLayout2: public void setBaselineAligned(boolean baselineAligned) { end void ");
	}

	@Override
	public boolean isMeasureWithLargestChildEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isMeasureWithLargestChildEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isMeasureWithLargestChildEnabled() { end return ");
		return super.isMeasureWithLargestChildEnabled();
	}

	@Override
	public void setMeasureWithLargestChildEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setMeasureWithLargestChildEnabled(boolean enabled) { start void ");

		super.setMeasureWithLargestChildEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setMeasureWithLargestChildEnabled(boolean enabled) { end void ");
	}

	@Override
	public int getBaseline() {

		System.out.println("resp1onse CustomLinearLayout2: public int getBaseline() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getBaseline() { end return ");
		return super.getBaseline();
	}

	@Override
	public int getBaselineAlignedChildIndex() {

		System.out.println("resp1onse CustomLinearLayout2: public int getBaselineAlignedChildIndex() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getBaselineAlignedChildIndex() { end return ");
		return super.getBaselineAlignedChildIndex();
	}

	@Override
	public void setBaselineAlignedChildIndex(int i) {
		System.out.println("resp1onse CustomLinearLayout2: public void setBaselineAlignedChildIndex(int i) { start void ");

		super.setBaselineAlignedChildIndex(i);
		System.out.println("resp1onse CustomLinearLayout2: public void setBaselineAlignedChildIndex(int i) { end void ");
	}

	@Override
	public float getWeightSum() {

		System.out.println("resp1onse CustomLinearLayout2: public float getWeightSum() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getWeightSum() { end return ");
		return super.getWeightSum();
	}

	@Override
	public void setWeightSum(float weightSum) {
		System.out.println("resp1onse CustomLinearLayout2: public void setWeightSum(float weightSum) { start void ");

		super.setWeightSum(weightSum);
		System.out.println("resp1onse CustomLinearLayout2: public void setWeightSum(float weightSum) { end void ");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void ");

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("resp1onse CustomLinearLayout2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onLayout(boolean changed, int l, int t, int r, int b) { start void ");

		super.onLayout(changed, l, t, r, b);
		System.out.println("resp1onse CustomLinearLayout2: protected void onLayout(boolean changed, int l, int t, int r, int b) { end void ");
	}

	@Override
	public void setOrientation(int orientation) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOrientation(int orientation) { start void ");

		super.setOrientation(orientation);
		System.out.println("resp1onse CustomLinearLayout2: public void setOrientation(int orientation) { end void ");
	}

	@Override
	public int getOrientation() {

		System.out.println("resp1onse CustomLinearLayout2: public int getOrientation() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getOrientation() { end return ");
		return super.getOrientation();
	}

	@Override
	public void setGravity(int gravity) {
		System.out.println("resp1onse CustomLinearLayout2: public void setGravity(int gravity) { start void ");

		super.setGravity(gravity);
		System.out.println("resp1onse CustomLinearLayout2: public void setGravity(int gravity) { end void ");
	}

	@Override
	public void setHorizontalGravity(int horizontalGravity) {
		System.out.println("resp1onse CustomLinearLayout2: public void setHorizontalGravity(int horizontalGravity) { start void ");

		super.setHorizontalGravity(horizontalGravity);
		System.out.println("resp1onse CustomLinearLayout2: public void setHorizontalGravity(int horizontalGravity) { end void ");
	}

	@Override
	public void setVerticalGravity(int verticalGravity) {
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalGravity(int verticalGravity) { start void ");

		super.setVerticalGravity(verticalGravity);
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalGravity(int verticalGravity) { end void ");
	}

	@Override
	public android.widget.LinearLayout.LayoutParams generateLayoutParams( AttributeSet attrs) {

		System.out.println("resp1onse CustomLinearLayout2: public android.widget.LinearLayout.LayoutParams generateLayoutParams( AttributeSet attrs) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public android.widget.LinearLayout.LayoutParams generateLayoutParams( AttributeSet attrs) { end return ");
		return super.generateLayoutParams(attrs);
	}

	@Override
	protected android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() {

		System.out.println("resp1onse CustomLinearLayout2: protected android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() { end return ");
		return super.generateDefaultLayoutParams();
	}

	@Override
	public int getDescendantFocusability() {

		System.out.println("resp1onse CustomLinearLayout2: public int getDescendantFocusability() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getDescendantFocusability() { end return ");
		return super.getDescendantFocusability();
	}

	@Override
	public void focusableViewAvailable(View v) {
		System.out.println("resp1onse CustomLinearLayout2: public void focusableViewAvailable(View v) { start void ");

		super.focusableViewAvailable(v);
		System.out.println("resp1onse CustomLinearLayout2: public void focusableViewAvailable(View v) { end void ");
	}

	@Override
	public View focusSearch(View focused, int direction) {

		System.out.println("resp1onse CustomLinearLayout2: public View focusSearch(View focused, int direction) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View focusSearch(View focused, int direction) { end return ");
		return super.focusSearch(focused, direction);
	}

	@Override
	public boolean dispatchUnhandledMove(View focused, int direction) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchUnhandledMove(View focused, int direction) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchUnhandledMove(View focused, int direction) { end return ");
		return super.dispatchUnhandledMove(focused, direction);
	}

	@Override
	public void clearChildFocus(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void clearChildFocus(View child) { start void ");

		super.clearChildFocus(child);
		System.out.println("resp1onse CustomLinearLayout2: public void clearChildFocus(View child) { end void ");
	}

	@Override
	public void clearFocus() {
		System.out.println("resp1onse CustomLinearLayout2: public void clearFocus() { start void ");

		super.clearFocus();
		System.out.println("resp1onse CustomLinearLayout2: public void clearFocus() { end void ");
	}

	@Override
	public View getFocusedChild() {

		System.out.println("resp1onse CustomLinearLayout2: public View getFocusedChild() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View getFocusedChild() { end return ");
		return super.getFocusedChild();
	}

	@Override
	public View findFocus() {

		System.out.println("resp1onse CustomLinearLayout2: public View findFocus() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View findFocus() { end return ");
		return super.findFocus();
	}

	@Override
	public void addFocusables(ArrayList<View> views, int direction,
			int focusableMode) {

		super.addFocusables(views, direction, focusableMode);
	}

	@Override
	public void dispatchWindowFocusChanged(boolean hasFocus) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchWindowFocusChanged(boolean hasFocus) { start void ");

		super.dispatchWindowFocusChanged(hasFocus);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchWindowFocusChanged(boolean hasFocus) { end void ");
	}

	@Override
	public void addTouchables(ArrayList<View> views) {
		System.out.println("resp1onse CustomLinearLayout2: public void addTouchables(ArrayList<View> views) { start void ");

		super.addTouchables(views);
		System.out.println("resp1onse CustomLinearLayout2: public void addTouchables(ArrayList<View> views) { end void ");
	}

	@Override
	public void dispatchDisplayHint(int hint) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchDisplayHint(int hint) { start void ");

		super.dispatchDisplayHint(hint);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchDisplayHint(int hint) { end void ");
	}

	@Override
	protected void dispatchVisibilityChanged(View changedView, int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchVisibilityChanged(View changedView, int visibility) { start void ");

		super.dispatchVisibilityChanged(changedView, visibility);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchVisibilityChanged(View changedView, int visibility) { end void ");
	}

	@Override
	public void dispatchWindowVisibilityChanged(int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchWindowVisibilityChanged(int visibility) { start void ");

		super.dispatchWindowVisibilityChanged(visibility);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchWindowVisibilityChanged(int visibility) { end void ");
	}

	@Override
	public void dispatchConfigurationChanged(Configuration newConfig) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchConfigurationChanged(Configuration newConfig) { start void ");

		super.dispatchConfigurationChanged(newConfig);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchConfigurationChanged(Configuration newConfig) { end void ");
	}

	@Override
	public void bringChildToFront(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void bringChildToFront(View child) { start void ");

		super.bringChildToFront(child);
		System.out.println("resp1onse CustomLinearLayout2: public void bringChildToFront(View child) { end void ");
	}

	@Override
	public boolean dispatchDragEvent(DragEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchDragEvent(DragEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchDragEvent(DragEvent event) { end return ");
		return super.dispatchDragEvent(event);
	}

	@Override
	public void dispatchSystemUiVisibilityChanged(int visible) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSystemUiVisibilityChanged(int visible) { start void ");

		super.dispatchSystemUiVisibilityChanged(visible);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSystemUiVisibilityChanged(int visible) { end void ");
	}

	@Override
	public boolean dispatchKeyEventPreIme(KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyEventPreIme(KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyEventPreIme(KeyEvent event) { end return ");
		return super.dispatchKeyEventPreIme(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyEvent(KeyEvent event) { end return ");
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyShortcutEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchKeyShortcutEvent(KeyEvent event) { end return ");
		return super.dispatchKeyShortcutEvent(event);
	}

	@Override
	public boolean dispatchTrackballEvent(MotionEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchTrackballEvent(MotionEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchTrackballEvent(MotionEvent event) { end return ");
		return super.dispatchTrackballEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchTouchEvent(MotionEvent ev) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchTouchEvent(MotionEvent ev) { end return ");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) { start void ");

		super.dispatchSaveInstanceState(container);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) { end void ");
	}

	@Override
	protected void dispatchFreezeSelfOnly(SparseArray<Parcelable> container) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchFreezeSelfOnly(SparseArray<Parcelable> container) { start void ");

		super.dispatchFreezeSelfOnly(container);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchFreezeSelfOnly(SparseArray<Parcelable> container) { end void ");
	}

	@Override
	protected void dispatchRestoreInstanceState(
			SparseArray<Parcelable> container) {

		super.dispatchRestoreInstanceState(container);
	}

	@Override
	protected void dispatchThawSelfOnly(SparseArray<Parcelable> container) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchThawSelfOnly(SparseArray<Parcelable> container) { start void ");

		super.dispatchThawSelfOnly(container);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchThawSelfOnly(SparseArray<Parcelable> container) { end void ");
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchDraw(Canvas canvas) { start void ");

		super.dispatchDraw(canvas);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchDraw(Canvas canvas) { end void ");
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {

		System.out.println("resp1onse CustomLinearLayout2: protected int getChildDrawingOrder(int childCount, int i) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getChildDrawingOrder(int childCount, int i) { end return ");
		return super.getChildDrawingOrder(childCount, i);
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean drawChild(Canvas canvas, View child, long drawingTime) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean drawChild(Canvas canvas, View child, long drawingTime) { end return ");
		return super.drawChild(canvas, child, drawingTime);
	}

	@Override
	public void dispatchSetSelected(boolean selected) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSetSelected(boolean selected) { start void ");

		super.dispatchSetSelected(selected);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSetSelected(boolean selected) { end void ");
	}

	@Override
	public void dispatchSetActivated(boolean activated) {
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSetActivated(boolean activated) { start void ");

		super.dispatchSetActivated(activated);
		System.out.println("resp1onse CustomLinearLayout2: public void dispatchSetActivated(boolean activated) { end void ");
	}

	@Override
	protected void dispatchSetPressed(boolean pressed) {
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchSetPressed(boolean pressed) { start void ");

		super.dispatchSetPressed(pressed);
		System.out.println("resp1onse CustomLinearLayout2: protected void dispatchSetPressed(boolean pressed) { end void ");
	}

	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean getChildStaticTransformation(View child, Transformation t) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean getChildStaticTransformation(View child, Transformation t) { end return ");
		return super.getChildStaticTransformation(child, t);
	}

	@Override
	public void addView(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child) { start void ");

		super.addView(child);
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child) { end void ");
	}

	@Override
	public void addView(View child, int index) {
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int index) { start void ");

		super.addView(child, index);
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int index) { end void ");
	}

	@Override
	public void addView(View child, int width, int height) {
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int width, int height) { start void ");

		super.addView(child, width, height);
		System.out.println("resp1onse CustomLinearLayout2: public void addView(View child, int width, int height) { end void ");
	}

	@Override
	protected void cleanupLayoutState(View child) {
		System.out.println("resp1onse CustomLinearLayout2: protected void cleanupLayoutState(View child) { start void ");

		super.cleanupLayoutState(child);
		System.out.println("resp1onse CustomLinearLayout2: protected void cleanupLayoutState(View child) { end void ");
	}

	@Override
	protected void detachViewFromParent(View child) {
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewFromParent(View child) { start void ");

		super.detachViewFromParent(child);
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewFromParent(View child) { end void ");
	}

	@Override
	protected void detachViewFromParent(int index) {
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewFromParent(int index) { start void ");

		super.detachViewFromParent(index);
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewFromParent(int index) { end void ");
	}

	@Override
	protected void detachViewsFromParent(int start, int count) {
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewsFromParent(int start, int count) { start void ");

		super.detachViewsFromParent(start, count);
		System.out.println("resp1onse CustomLinearLayout2: protected void detachViewsFromParent(int start, int count) { end void ");
	}

	@Override
	protected void detachAllViewsFromParent() {
		System.out.println("resp1onse CustomLinearLayout2: protected void detachAllViewsFromParent() { start void ");

		super.detachAllViewsFromParent();
		System.out.println("resp1onse CustomLinearLayout2: protected void detachAllViewsFromParent() { end void ");
	}

	@Override
	public boolean getChildVisibleRect(View child, Rect r, Point offset) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean getChildVisibleRect(View child, Rect r, Point offset) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean getChildVisibleRect(View child, Rect r, Point offset) { end return ");
		return super.getChildVisibleRect(child, r, offset);
	}

	@Override
	protected boolean canAnimate() {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean canAnimate() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean canAnimate() { end return ");
		return super.canAnimate();
	}

	@Override
	protected void debug(int depth) {
		System.out.println("resp1onse CustomLinearLayout2: protected void debug(int depth) { start void ");

		super.debug(depth);
		System.out.println("resp1onse CustomLinearLayout2: protected void debug(int depth) { end void ");
	}

	@Override
	public int getChildCount() {

		System.out.println("resp1onse CustomLinearLayout2: public int getChildCount() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getChildCount() { end return ");
		return super.getChildCount();
	}

	@Override
	public View getChildAt(int index) {

		System.out.println("resp1onse CustomLinearLayout2: public View getChildAt(int index) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View getChildAt(int index) { end return ");
		return super.getChildAt(index);
	}

	@Override
	public void clearDisappearingChildren() {
		System.out.println("resp1onse CustomLinearLayout2: public void clearDisappearingChildren() { start void ");

		super.clearDisappearingChildren();
		System.out.println("resp1onse CustomLinearLayout2: public void clearDisappearingChildren() { end void ");
	}

	@Override
	public void endViewTransition(View view) {
		System.out.println("resp1onse CustomLinearLayout2: public void endViewTransition(View view) { start void ");

		super.endViewTransition(view);
		System.out.println("resp1onse CustomLinearLayout2: public void endViewTransition(View view) { end void ");
	}

	@Override
	public boolean gatherTransparentRegion(Region region) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean gatherTransparentRegion(Region region) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean gatherTransparentRegion(Region region) { end return ");
		return super.gatherTransparentRegion(region);
	}

	@Override
	protected void drawableStateChanged() {
		System.out.println("resp1onse CustomLinearLayout2: protected void drawableStateChanged() { start void ");

		super.drawableStateChanged();
		System.out.println("resp1onse CustomLinearLayout2: protected void drawableStateChanged() { end void ");
	}

	@Override
	public boolean addStatesFromChildren() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean addStatesFromChildren() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean addStatesFromChildren() { end return ");
		return super.addStatesFromChildren();
	}

	@Override
	public void childDrawableStateChanged(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void childDrawableStateChanged(View child) { start void ");

		super.childDrawableStateChanged(child);
		System.out.println("resp1onse CustomLinearLayout2: public void childDrawableStateChanged(View child) { end void ");
	}

	@Override
	protected boolean fitSystemWindows(Rect insets) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean fitSystemWindows(Rect insets) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean fitSystemWindows(Rect insets) { end return ");
		return super.fitSystemWindows(insets);
	}

	@Override
	public void setDescendantFocusability(int focusability) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDescendantFocusability(int focusability) { start void ");

		super.setDescendantFocusability(focusability);
		System.out.println("resp1onse CustomLinearLayout2: public void setDescendantFocusability(int focusability) { end void ");
	}

	@Override
	public void requestChildFocus(View child, View focused) {
		System.out.println("resp1onse CustomLinearLayout2: public void requestChildFocus(View child, View focused) { start void ");

		super.requestChildFocus(child, focused);
		System.out.println("resp1onse CustomLinearLayout2: public void requestChildFocus(View child, View focused) { end void ");
	}

	@Override
	public boolean showContextMenuForChild(View originalView) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean showContextMenuForChild(View originalView) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean showContextMenuForChild(View originalView) { end return ");
		return super.showContextMenuForChild(originalView);
	}

	@Override
	public ActionMode startActionModeForChild(View originalView,
			Callback callback) {

		return super.startActionModeForChild(originalView, callback);
	}

	@Override
	public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) { end return ");
		return super.requestChildRectangleOnScreen(child, rectangle, immediate);
	}

	@Override
	public boolean hasFocus() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean hasFocus() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean hasFocus() { end return ");
		return super.hasFocus();
	}

	@Override
	public boolean hasFocusable() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean hasFocusable() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean hasFocusable() { end return ");
		return super.hasFocusable();
	}

	@Override
	public void recomputeViewAttributes(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void recomputeViewAttributes(View child) { start void ");

		super.recomputeViewAttributes(child);
		System.out.println("resp1onse CustomLinearLayout2: public void recomputeViewAttributes(View child) { end void ");
	}

	@Override
	public void setMotionEventSplittingEnabled(boolean split) {
		System.out.println("resp1onse CustomLinearLayout2: public void setMotionEventSplittingEnabled(boolean split) { start void ");

		super.setMotionEventSplittingEnabled(split);
		System.out.println("resp1onse CustomLinearLayout2: public void setMotionEventSplittingEnabled(boolean split) { end void ");
	}

	@Override
	public boolean isMotionEventSplittingEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isMotionEventSplittingEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isMotionEventSplittingEnabled() { end return ");
		return super.isMotionEventSplittingEnabled();
	}

	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		System.out.println("resp1onse CustomLinearLayout2: public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) { start void ");

		super.requestDisallowInterceptTouchEvent(disallowIntercept);
		System.out.println("resp1onse CustomLinearLayout2: public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) { end void ");
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onInterceptTouchEvent(MotionEvent ev) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onInterceptTouchEvent(MotionEvent ev) { end return ");
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean requestFocus(int direction, Rect previouslyFocusedRect) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean requestFocus(int direction, Rect previouslyFocusedRect) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean requestFocus(int direction, Rect previouslyFocusedRect) { end return ");
		return super.requestFocus(direction, previouslyFocusedRect);
	}

	@Override
	protected boolean onRequestFocusInDescendants(int direction,
			Rect previouslyFocusedRect) {

		return super.onRequestFocusInDescendants(direction,
				previouslyFocusedRect);
	}

	@Override
	protected void setChildrenDrawingCacheEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawingCacheEnabled(boolean enabled) { start void ");

		super.setChildrenDrawingCacheEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawingCacheEnabled(boolean enabled) { end void ");
	}

	@Override
	protected void onAnimationStart() {
		System.out.println("resp1onse CustomLinearLayout2: protected void onAnimationStart() { start void ");

		super.onAnimationStart();
		System.out.println("resp1onse CustomLinearLayout2: protected void onAnimationStart() { end void ");
	}

	@Override
	protected void onAnimationEnd() {
		System.out.println("resp1onse CustomLinearLayout2: protected void onAnimationEnd() { start void ");

		super.onAnimationEnd();
		System.out.println("resp1onse CustomLinearLayout2: protected void onAnimationEnd() { end void ");
	}

	@Override
	public void setClipChildren(boolean clipChildren) {
		System.out.println("resp1onse CustomLinearLayout2: public void setClipChildren(boolean clipChildren) { start void ");

		super.setClipChildren(clipChildren);
		System.out.println("resp1onse CustomLinearLayout2: public void setClipChildren(boolean clipChildren) { end void ");
	}

	@Override
	public void setClipToPadding(boolean clipToPadding) {
		System.out.println("resp1onse CustomLinearLayout2: public void setClipToPadding(boolean clipToPadding) { start void ");

		super.setClipToPadding(clipToPadding);
		System.out.println("resp1onse CustomLinearLayout2: public void setClipToPadding(boolean clipToPadding) { end void ");
	}

	@Override
	protected void setStaticTransformationsEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: protected void setStaticTransformationsEnabled(boolean enabled) { start void ");

		super.setStaticTransformationsEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: protected void setStaticTransformationsEnabled(boolean enabled) { end void ");
	}

	@Override
	public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) { start void ");

		super.setOnHierarchyChangeListener(listener);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) { end void ");
	}

	@Override
	protected void onAttachedToWindow() {
		System.out.println("resp1onse CustomLinearLayout2: protected void onAttachedToWindow() { start void ");

		super.onAttachedToWindow();
		System.out.println("resp1onse CustomLinearLayout2: protected void onAttachedToWindow() { end void ");
	}

	@Override
	protected void onDetachedFromWindow() {
		System.out.println("resp1onse CustomLinearLayout2: protected void onDetachedFromWindow() { start void ");

		super.onDetachedFromWindow();
		System.out.println("resp1onse CustomLinearLayout2: protected void onDetachedFromWindow() { end void ");
	}

	@Override
	public void removeView(View view) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeView(View view) { start void ");

		super.removeView(view);
		System.out.println("resp1onse CustomLinearLayout2: public void removeView(View view) { end void ");
	}

	@Override
	public void removeViewInLayout(View view) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewInLayout(View view) { start void ");

		super.removeViewInLayout(view);
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewInLayout(View view) { end void ");
	}

	@Override
	public void removeViewsInLayout(int start, int count) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewsInLayout(int start, int count) { start void ");

		super.removeViewsInLayout(start, count);
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewsInLayout(int start, int count) { end void ");
	}

	@Override
	public void removeViewAt(int index) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewAt(int index) { start void ");

		super.removeViewAt(index);
		System.out.println("resp1onse CustomLinearLayout2: public void removeViewAt(int index) { end void ");
	}

	@Override
	public void removeViews(int start, int count) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeViews(int start, int count) { start void ");

		super.removeViews(start, count);
		System.out.println("resp1onse CustomLinearLayout2: public void removeViews(int start, int count) { end void ");
	}

	@Override
	public void setLayoutTransition(LayoutTransition transition) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutTransition(LayoutTransition transition) { start void ");

		super.setLayoutTransition(transition);
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutTransition(LayoutTransition transition) { end void ");
	}

	@Override
	public LayoutTransition getLayoutTransition() {

		System.out.println("resp1onse CustomLinearLayout2: public LayoutTransition getLayoutTransition() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public LayoutTransition getLayoutTransition() { end return ");
		return super.getLayoutTransition();
	}

	@Override
	public void removeAllViews() {
		System.out.println("resp1onse CustomLinearLayout2: public void removeAllViews() { start void ");

		super.removeAllViews();
		System.out.println("resp1onse CustomLinearLayout2: public void removeAllViews() { end void ");
	}

	@Override
	public void removeAllViewsInLayout() {
		System.out.println("resp1onse CustomLinearLayout2: public void removeAllViewsInLayout() { start void ");

		super.removeAllViewsInLayout();
		System.out.println("resp1onse CustomLinearLayout2: public void removeAllViewsInLayout() { end void ");
	}

	@Override
	protected void removeDetachedView(View child, boolean animate) {
		System.out.println("resp1onse CustomLinearLayout2: protected void removeDetachedView(View child, boolean animate) { start void ");

		super.removeDetachedView(child, animate);
		System.out.println("resp1onse CustomLinearLayout2: protected void removeDetachedView(View child, boolean animate) { end void ");
	}

	@Override
	public ViewParent invalidateChildInParent(int[] location, Rect dirty) {

		System.out.println("resp1onse CustomLinearLayout2: public ViewParent invalidateChildInParent(int[] location, Rect dirty) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public ViewParent invalidateChildInParent(int[] location, Rect dirty) { end return ");
		return super.invalidateChildInParent(location, dirty);
	}

	@Override
	public void startLayoutAnimation() {
		System.out.println("resp1onse CustomLinearLayout2: public void startLayoutAnimation() { start void ");

		super.startLayoutAnimation();
		System.out.println("resp1onse CustomLinearLayout2: public void startLayoutAnimation() { end void ");
	}

	@Override
	public void scheduleLayoutAnimation() {
		System.out.println("resp1onse CustomLinearLayout2: public void scheduleLayoutAnimation() { start void ");

		super.scheduleLayoutAnimation();
		System.out.println("resp1onse CustomLinearLayout2: public void scheduleLayoutAnimation() { end void ");
	}

	@Override
	public void setLayoutAnimation(LayoutAnimationController controller) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutAnimation(LayoutAnimationController controller) { start void ");

		super.setLayoutAnimation(controller);
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutAnimation(LayoutAnimationController controller) { end void ");
	}

	@Override
	public LayoutAnimationController getLayoutAnimation() {

		System.out.println("resp1onse CustomLinearLayout2: public LayoutAnimationController getLayoutAnimation() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public LayoutAnimationController getLayoutAnimation() { end return ");
		return super.getLayoutAnimation();
	}

	@Override
	public boolean isAnimationCacheEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isAnimationCacheEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isAnimationCacheEnabled() { end return ");
		return super.isAnimationCacheEnabled();
	}

	@Override
	public void setAnimationCacheEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setAnimationCacheEnabled(boolean enabled) { start void ");

		super.setAnimationCacheEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setAnimationCacheEnabled(boolean enabled) { end void ");
	}

	@Override
	public boolean isAlwaysDrawnWithCacheEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isAlwaysDrawnWithCacheEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isAlwaysDrawnWithCacheEnabled() { end return ");
		return super.isAlwaysDrawnWithCacheEnabled();
	}

	@Override
	public void setAlwaysDrawnWithCacheEnabled(boolean always) {
		System.out.println("resp1onse CustomLinearLayout2: public void setAlwaysDrawnWithCacheEnabled(boolean always) { start void ");

		super.setAlwaysDrawnWithCacheEnabled(always);
		System.out.println("resp1onse CustomLinearLayout2: public void setAlwaysDrawnWithCacheEnabled(boolean always) { end void ");
	}

	@Override
	protected boolean isChildrenDrawnWithCacheEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean isChildrenDrawnWithCacheEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean isChildrenDrawnWithCacheEnabled() { end return ");
		return super.isChildrenDrawnWithCacheEnabled();
	}

	@Override
	protected void setChildrenDrawnWithCacheEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawnWithCacheEnabled(boolean enabled) { start void ");

		super.setChildrenDrawnWithCacheEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawnWithCacheEnabled(boolean enabled) { end void ");
	}

	@Override
	protected boolean isChildrenDrawingOrderEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean isChildrenDrawingOrderEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean isChildrenDrawingOrderEnabled() { end return ");
		return super.isChildrenDrawingOrderEnabled();
	}

	@Override
	protected void setChildrenDrawingOrderEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawingOrderEnabled(boolean enabled) { start void ");

		super.setChildrenDrawingOrderEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: protected void setChildrenDrawingOrderEnabled(boolean enabled) { end void ");
	}

	@Override
	public int getPersistentDrawingCache() {

		System.out.println("resp1onse CustomLinearLayout2: public int getPersistentDrawingCache() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getPersistentDrawingCache() { end return ");
		return super.getPersistentDrawingCache();
	}

	@Override
	public void setPersistentDrawingCache(int drawingCacheToKeep) {
		System.out.println("resp1onse CustomLinearLayout2: public void setPersistentDrawingCache(int drawingCacheToKeep) { start void ");

		super.setPersistentDrawingCache(drawingCacheToKeep);
		System.out.println("resp1onse CustomLinearLayout2: public void setPersistentDrawingCache(int drawingCacheToKeep) { end void ");
	}

	@Override
	public int indexOfChild(View child) {

		System.out.println("resp1onse CustomLinearLayout2: public int indexOfChild(View child) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int indexOfChild(View child) { end return ");
		return super.indexOfChild(child);
	}

	@Override
	protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout2: protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) { start void ");

		super.measureChildren(widthMeasureSpec, heightMeasureSpec);
		System.out.println("resp1onse CustomLinearLayout2: protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	@Override
	protected void measureChild(View child, int parentWidthMeasureSpec,
			int parentHeightMeasureSpec) {

		super.measureChild(child, parentWidthMeasureSpec,
				parentHeightMeasureSpec);
	}

	@Override
	protected void measureChildWithMargins(View child,
			int parentWidthMeasureSpec, int widthUsed,
			int parentHeightMeasureSpec, int heightUsed) {

		super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed,
				parentHeightMeasureSpec, heightUsed);
	}

	@Override
	public void startViewTransition(View view) {
		System.out.println("resp1onse CustomLinearLayout2: public void startViewTransition(View view) { start void ");

		super.startViewTransition(view);
		System.out.println("resp1onse CustomLinearLayout2: public void startViewTransition(View view) { end void ");
	}

	@Override
	public void requestTransparentRegion(View child) {
		System.out.println("resp1onse CustomLinearLayout2: public void requestTransparentRegion(View child) { start void ");

		super.requestTransparentRegion(child);
		System.out.println("resp1onse CustomLinearLayout2: public void requestTransparentRegion(View child) { end void ");
	}

	@Override
	public AnimationListener getLayoutAnimationListener() {

		System.out.println("resp1onse CustomLinearLayout2: public AnimationListener getLayoutAnimationListener() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public AnimationListener getLayoutAnimationListener() { end return ");
		return super.getLayoutAnimationListener();
	}

	@Override
	public void jumpDrawablesToCurrentState() {
		System.out.println("resp1onse CustomLinearLayout2: public void jumpDrawablesToCurrentState() { start void ");

		super.jumpDrawablesToCurrentState();
		System.out.println("resp1onse CustomLinearLayout2: public void jumpDrawablesToCurrentState() { end void ");
	}

	@Override
	protected int[] onCreateDrawableState(int extraSpace) {

		System.out.println("resp1onse CustomLinearLayout2: protected int[] onCreateDrawableState(int extraSpace) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int[] onCreateDrawableState(int extraSpace) { end return ");
		return super.onCreateDrawableState(extraSpace);
	}

	@Override
	public void setAddStatesFromChildren(boolean addsStates) {
		System.out.println("resp1onse CustomLinearLayout2: public void setAddStatesFromChildren(boolean addsStates) { start void ");

		super.setAddStatesFromChildren(addsStates);
		System.out.println("resp1onse CustomLinearLayout2: public void setAddStatesFromChildren(boolean addsStates) { end void ");
	}

	@Override
	public void setLayoutAnimationListener(AnimationListener animationListener) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutAnimationListener(AnimationListener animationListener) { start void ");

		super.setLayoutAnimationListener(animationListener);
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutAnimationListener(AnimationListener animationListener) { end void ");
	}

	@Override
	protected void initializeFadingEdge(TypedArray a) {
		System.out.println("resp1onse CustomLinearLayout2: protected void initializeFadingEdge(TypedArray a) { start void ");

		super.initializeFadingEdge(a);
		System.out.println("resp1onse CustomLinearLayout2: protected void initializeFadingEdge(TypedArray a) { end void ");
	}

	@Override
	public int getVerticalFadingEdgeLength() {

		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalFadingEdgeLength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalFadingEdgeLength() { end return ");
		return super.getVerticalFadingEdgeLength();
	}

	@Override
	public void setFadingEdgeLength(int length) {
		System.out.println("resp1onse CustomLinearLayout2: public void setFadingEdgeLength(int length) { start void ");

		super.setFadingEdgeLength(length);
		System.out.println("resp1onse CustomLinearLayout2: public void setFadingEdgeLength(int length) { end void ");
	}

	@Override
	public int getHorizontalFadingEdgeLength() {

		System.out.println("resp1onse CustomLinearLayout2: public int getHorizontalFadingEdgeLength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getHorizontalFadingEdgeLength() { end return ");
		return super.getHorizontalFadingEdgeLength();
	}

	@Override
	public int getVerticalScrollbarWidth() {

		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalScrollbarWidth() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalScrollbarWidth() { end return ");
		return super.getVerticalScrollbarWidth();
	}

	@Override
	protected int getHorizontalScrollbarHeight() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getHorizontalScrollbarHeight() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getHorizontalScrollbarHeight() { end return ");
		return super.getHorizontalScrollbarHeight();
	}

	@Override
	protected void initializeScrollbars(TypedArray a) {
		System.out.println("resp1onse CustomLinearLayout2: protected void initializeScrollbars(TypedArray a) { start void ");

		super.initializeScrollbars(a);
		System.out.println("resp1onse CustomLinearLayout2: protected void initializeScrollbars(TypedArray a) { end void ");
	}

	@Override
	public void setVerticalScrollbarPosition(int position) {
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalScrollbarPosition(int position) { start void ");

		super.setVerticalScrollbarPosition(position);
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalScrollbarPosition(int position) { end void ");
	}

	@Override
	public int getVerticalScrollbarPosition() {

		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalScrollbarPosition() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getVerticalScrollbarPosition() { end return ");
		return super.getVerticalScrollbarPosition();
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnFocusChangeListener(OnFocusChangeListener l) { start void ");

		super.setOnFocusChangeListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnFocusChangeListener(OnFocusChangeListener l) { end void ");
	}

	@Override
	public void addOnLayoutChangeListener(OnLayoutChangeListener listener) {
		System.out.println("resp1onse CustomLinearLayout2: public void addOnLayoutChangeListener(OnLayoutChangeListener listener) { start void ");

		super.addOnLayoutChangeListener(listener);
		System.out.println("resp1onse CustomLinearLayout2: public void addOnLayoutChangeListener(OnLayoutChangeListener listener) { end void ");
	}

	@Override
	public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) {
		System.out.println("resp1onse CustomLinearLayout2: public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) { start void ");

		super.removeOnLayoutChangeListener(listener);
		System.out.println("resp1onse CustomLinearLayout2: public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) { end void ");
	}

	@Override
	public OnFocusChangeListener getOnFocusChangeListener() {

		System.out.println("resp1onse CustomLinearLayout2: public OnFocusChangeListener getOnFocusChangeListener() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public OnFocusChangeListener getOnFocusChangeListener() { end return ");
		return super.getOnFocusChangeListener();
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnClickListener(OnClickListener l) { start void ");

		super.setOnClickListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnClickListener(OnClickListener l) { end void ");
	}

	@Override
	public void setOnLongClickListener(OnLongClickListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnLongClickListener(OnLongClickListener l) { start void ");

		super.setOnLongClickListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnLongClickListener(OnLongClickListener l) { end void ");
	}

	@Override
	public void setOnCreateContextMenuListener(OnCreateContextMenuListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnCreateContextMenuListener(OnCreateContextMenuListener l) { start void ");

		super.setOnCreateContextMenuListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnCreateContextMenuListener(OnCreateContextMenuListener l) { end void ");
	}

	@Override
	public boolean performClick() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean performClick() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean performClick() { end return ");
		return super.performClick();
	}

	@Override
	public boolean performLongClick() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean performLongClick() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean performLongClick() { end return ");
		return super.performLongClick();
	}

	@Override
	public boolean showContextMenu() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean showContextMenu() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean showContextMenu() { end return ");
		return super.showContextMenu();
	}

	@Override
	public ActionMode startActionMode(Callback callback) {

		System.out.println("resp1onse CustomLinearLayout2: public ActionMode startActionMode(Callback callback) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public ActionMode startActionMode(Callback callback) { end return ");
		return super.startActionMode(callback);
	}

	@Override
	public void setOnKeyListener(OnKeyListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnKeyListener(OnKeyListener l) { start void ");

		super.setOnKeyListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnKeyListener(OnKeyListener l) { end void ");
	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnTouchListener(OnTouchListener l) { start void ");

		super.setOnTouchListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnTouchListener(OnTouchListener l) { end void ");
	}

	@Override
	public void setOnDragListener(OnDragListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnDragListener(OnDragListener l) { start void ");

		super.setOnDragListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnDragListener(OnDragListener l) { end void ");
	}

	@Override
	public boolean requestRectangleOnScreen(Rect rectangle) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean requestRectangleOnScreen(Rect rectangle) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean requestRectangleOnScreen(Rect rectangle) { end return ");
		return super.requestRectangleOnScreen(rectangle);
	}

	@Override
	public boolean requestRectangleOnScreen(Rect rectangle, boolean immediate) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean requestRectangleOnScreen(Rect rectangle, boolean immediate) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean requestRectangleOnScreen(Rect rectangle, boolean immediate) { end return ");
		return super.requestRectangleOnScreen(rectangle, immediate);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {

		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	@Override
	public void sendAccessibilityEvent(int eventType) {
		System.out.println("resp1onse CustomLinearLayout2: public void sendAccessibilityEvent(int eventType) { start void ");

		super.sendAccessibilityEvent(eventType);
		System.out.println("resp1onse CustomLinearLayout2: public void sendAccessibilityEvent(int eventType) { end void ");
	}

	@Override
	public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
		System.out.println("resp1onse CustomLinearLayout2: public void sendAccessibilityEventUnchecked(AccessibilityEvent event) { start void ");

		super.sendAccessibilityEventUnchecked(event);
		System.out.println("resp1onse CustomLinearLayout2: public void sendAccessibilityEventUnchecked(AccessibilityEvent event) { end void ");
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { end return ");
		return super.dispatchPopulateAccessibilityEvent(event);
	}

	@Override
	public CharSequence getContentDescription() {

		System.out.println("resp1onse CustomLinearLayout2: public CharSequence getContentDescription() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public CharSequence getContentDescription() { end return ");
		return super.getContentDescription();
	}

	@Override
	public void setContentDescription(CharSequence contentDescription) {
		System.out.println("resp1onse CustomLinearLayout2: public void setContentDescription(CharSequence contentDescription) { start void ");

		super.setContentDescription(contentDescription);
		System.out.println("resp1onse CustomLinearLayout2: public void setContentDescription(CharSequence contentDescription) { end void ");
	}

	@Override
	public boolean isFocused() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isFocused() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isFocused() { end return ");
		return super.isFocused();
	}

	@Override
	public void setScrollContainer(boolean isScrollContainer) {
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollContainer(boolean isScrollContainer) { start void ");

		super.setScrollContainer(isScrollContainer);
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollContainer(boolean isScrollContainer) { end void ");
	}

	@Override
	public int getDrawingCacheQuality() {

		System.out.println("resp1onse CustomLinearLayout2: public int getDrawingCacheQuality() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getDrawingCacheQuality() { end return ");
		return super.getDrawingCacheQuality();
	}

	@Override
	public void setDrawingCacheQuality(int quality) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheQuality(int quality) { start void ");

		super.setDrawingCacheQuality(quality);
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheQuality(int quality) { end void ");
	}

	@Override
	public boolean getKeepScreenOn() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean getKeepScreenOn() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean getKeepScreenOn() { end return ");
		return super.getKeepScreenOn();
	}

	@Override
	public void setKeepScreenOn(boolean keepScreenOn) {
		System.out.println("resp1onse CustomLinearLayout2: public void setKeepScreenOn(boolean keepScreenOn) { start void ");

		super.setKeepScreenOn(keepScreenOn);
		System.out.println("resp1onse CustomLinearLayout2: public void setKeepScreenOn(boolean keepScreenOn) { end void ");
	}

	@Override
	public int getNextFocusLeftId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusLeftId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusLeftId() { end return ");
		return super.getNextFocusLeftId();
	}

	@Override
	public void setNextFocusLeftId(int nextFocusLeftId) {
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusLeftId(int nextFocusLeftId) { start void ");

		super.setNextFocusLeftId(nextFocusLeftId);
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusLeftId(int nextFocusLeftId) { end void ");
	}

	@Override
	public int getNextFocusRightId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusRightId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusRightId() { end return ");
		return super.getNextFocusRightId();
	}

	@Override
	public void setNextFocusRightId(int nextFocusRightId) {
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusRightId(int nextFocusRightId) { start void ");

		super.setNextFocusRightId(nextFocusRightId);
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusRightId(int nextFocusRightId) { end void ");
	}

	@Override
	public int getNextFocusUpId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusUpId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusUpId() { end return ");
		return super.getNextFocusUpId();
	}

	@Override
	public void setNextFocusUpId(int nextFocusUpId) {
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusUpId(int nextFocusUpId) { start void ");

		super.setNextFocusUpId(nextFocusUpId);
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusUpId(int nextFocusUpId) { end void ");
	}

	@Override
	public int getNextFocusDownId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusDownId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusDownId() { end return ");
		return super.getNextFocusDownId();
	}

	@Override
	public void setNextFocusDownId(int nextFocusDownId) {
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusDownId(int nextFocusDownId) { start void ");

		super.setNextFocusDownId(nextFocusDownId);
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusDownId(int nextFocusDownId) { end void ");
	}

	@Override
	public int getNextFocusForwardId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusForwardId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getNextFocusForwardId() { end return ");
		return super.getNextFocusForwardId();
	}

	@Override
	public void setNextFocusForwardId(int nextFocusForwardId) {
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusForwardId(int nextFocusForwardId) { start void ");

		super.setNextFocusForwardId(nextFocusForwardId);
		System.out.println("resp1onse CustomLinearLayout2: public void setNextFocusForwardId(int nextFocusForwardId) { end void ");
	}

	@Override
	public boolean isShown() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isShown() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isShown() { end return ");
		return super.isShown();
	}

	@Override
	public int getVisibility() {

		System.out.println("resp1onse CustomLinearLayout2: public int getVisibility() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getVisibility() { end return ");
		return super.getVisibility();
	}

	@Override
	public void setVisibility(int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: public void setVisibility(int visibility) { start void ");

		super.setVisibility(visibility);
		System.out.println("resp1onse CustomLinearLayout2: public void setVisibility(int visibility) { end void ");
	}

	@Override
	public boolean isEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isEnabled() { end return ");
		return super.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setEnabled(boolean enabled) { start void ");

		super.setEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setEnabled(boolean enabled) { end void ");
	}

	@Override
	public void setFocusable(boolean focusable) {
		System.out.println("resp1onse CustomLinearLayout2: public void setFocusable(boolean focusable) { start void ");

		super.setFocusable(focusable);
		System.out.println("resp1onse CustomLinearLayout2: public void setFocusable(boolean focusable) { end void ");
	}

	@Override
	public void setFocusableInTouchMode(boolean focusableInTouchMode) {
		System.out.println("resp1onse CustomLinearLayout2: public void setFocusableInTouchMode(boolean focusableInTouchMode) { start void ");

		super.setFocusableInTouchMode(focusableInTouchMode);
		System.out.println("resp1onse CustomLinearLayout2: public void setFocusableInTouchMode(boolean focusableInTouchMode) { end void ");
	}

	@Override
	public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setSoundEffectsEnabled(boolean soundEffectsEnabled) { start void ");

		super.setSoundEffectsEnabled(soundEffectsEnabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setSoundEffectsEnabled(boolean soundEffectsEnabled) { end void ");
	}

	@Override
	public boolean isSoundEffectsEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isSoundEffectsEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isSoundEffectsEnabled() { end return ");
		return super.isSoundEffectsEnabled();
	}

	@Override
	public void setHapticFeedbackEnabled(boolean hapticFeedbackEnabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setHapticFeedbackEnabled(boolean hapticFeedbackEnabled) { start void ");

		super.setHapticFeedbackEnabled(hapticFeedbackEnabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setHapticFeedbackEnabled(boolean hapticFeedbackEnabled) { end void ");
	}

	@Override
	public boolean isHapticFeedbackEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isHapticFeedbackEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isHapticFeedbackEnabled() { end return ");
		return super.isHapticFeedbackEnabled();
	}

	@Override
	public void setWillNotDraw(boolean willNotDraw) {
		System.out.println("resp1onse CustomLinearLayout2: public void setWillNotDraw(boolean willNotDraw) { start void ");

		super.setWillNotDraw(willNotDraw);
		System.out.println("resp1onse CustomLinearLayout2: public void setWillNotDraw(boolean willNotDraw) { end void ");
	}

	@Override
	public boolean willNotDraw() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean willNotDraw() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean willNotDraw() { end return ");
		return super.willNotDraw();
	}

	@Override
	public void setWillNotCacheDrawing(boolean willNotCacheDrawing) {
		System.out.println("resp1onse CustomLinearLayout2: public void setWillNotCacheDrawing(boolean willNotCacheDrawing) { start void ");

		super.setWillNotCacheDrawing(willNotCacheDrawing);
		System.out.println("resp1onse CustomLinearLayout2: public void setWillNotCacheDrawing(boolean willNotCacheDrawing) { end void ");
	}

	@Override
	public boolean willNotCacheDrawing() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean willNotCacheDrawing() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean willNotCacheDrawing() { end return ");
		return super.willNotCacheDrawing();
	}

	@Override
	public boolean isClickable() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isClickable() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isClickable() { end return ");
		return super.isClickable();
	}

	@Override
	public void setClickable(boolean clickable) {
		System.out.println("resp1onse CustomLinearLayout2: public void setClickable(boolean clickable) { start void ");

		super.setClickable(clickable);
		System.out.println("resp1onse CustomLinearLayout2: public void setClickable(boolean clickable) { end void ");
	}

	@Override
	public boolean isLongClickable() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isLongClickable() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isLongClickable() { end return ");
		return super.isLongClickable();
	}

	@Override
	public void setLongClickable(boolean longClickable) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLongClickable(boolean longClickable) { start void ");

		super.setLongClickable(longClickable);
		System.out.println("resp1onse CustomLinearLayout2: public void setLongClickable(boolean longClickable) { end void ");
	}

	@Override
	public void setPressed(boolean pressed) {
		System.out.println("resp1onse CustomLinearLayout2: public void setPressed(boolean pressed) { start void ");

		super.setPressed(pressed);
		System.out.println("resp1onse CustomLinearLayout2: public void setPressed(boolean pressed) { end void ");
	}

	@Override
	public boolean isPressed() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isPressed() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isPressed() { end return ");
		return super.isPressed();
	}

	@Override
	public boolean isSaveEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isSaveEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isSaveEnabled() { end return ");
		return super.isSaveEnabled();
	}

	@Override
	public void setSaveEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setSaveEnabled(boolean enabled) { start void ");

		super.setSaveEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setSaveEnabled(boolean enabled) { end void ");
	}

	@Override
	public boolean getFilterTouchesWhenObscured() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean getFilterTouchesWhenObscured() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean getFilterTouchesWhenObscured() { end return ");
		return super.getFilterTouchesWhenObscured();
	}

	@Override
	public void setFilterTouchesWhenObscured(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setFilterTouchesWhenObscured(boolean enabled) { start void ");

		super.setFilterTouchesWhenObscured(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setFilterTouchesWhenObscured(boolean enabled) { end void ");
	}

	@Override
	public boolean isSaveFromParentEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isSaveFromParentEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isSaveFromParentEnabled() { end return ");
		return super.isSaveFromParentEnabled();
	}

	@Override
	public void setSaveFromParentEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setSaveFromParentEnabled(boolean enabled) { start void ");

		super.setSaveFromParentEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setSaveFromParentEnabled(boolean enabled) { end void ");
	}

	@Override
	public View focusSearch(int direction) {

		System.out.println("resp1onse CustomLinearLayout2: public View focusSearch(int direction) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View focusSearch(int direction) { end return ");
		return super.focusSearch(direction);
	}

	@Override
	public ArrayList<View> getFocusables(int direction) {

		System.out.println("resp1onse CustomLinearLayout2: public ArrayList<View> getFocusables(int direction) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public ArrayList<View> getFocusables(int direction) { end return ");
		return super.getFocusables(direction);
	}

	@Override
	public void addFocusables(ArrayList<View> views, int direction) {
		System.out.println("resp1onse CustomLinearLayout2: public void addFocusables(ArrayList<View> views, int direction) { start void ");

		super.addFocusables(views, direction);
		System.out.println("resp1onse CustomLinearLayout2: public void addFocusables(ArrayList<View> views, int direction) { end void ");
	}

	@Override
	public ArrayList<View> getTouchables() {

		System.out.println("resp1onse CustomLinearLayout2: public ArrayList<View> getTouchables() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public ArrayList<View> getTouchables() { end return ");
		return super.getTouchables();
	}

	@Override
	public void onStartTemporaryDetach() {
		System.out.println("resp1onse CustomLinearLayout2: public void onStartTemporaryDetach() { start void ");

		super.onStartTemporaryDetach();
		System.out.println("resp1onse CustomLinearLayout2: public void onStartTemporaryDetach() { end void ");
	}

	@Override
	public void onFinishTemporaryDetach() {
		System.out.println("resp1onse CustomLinearLayout2: public void onFinishTemporaryDetach() { start void ");

		super.onFinishTemporaryDetach();
		System.out.println("resp1onse CustomLinearLayout2: public void onFinishTemporaryDetach() { end void ");
	}

	@Override
	public DispatcherState getKeyDispatcherState() {

		System.out.println("resp1onse CustomLinearLayout2: public DispatcherState getKeyDispatcherState() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public DispatcherState getKeyDispatcherState() { end return ");
		return super.getKeyDispatcherState();
	}

	@Override
	public boolean onFilterTouchEventForSecurity(MotionEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onFilterTouchEventForSecurity(MotionEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onFilterTouchEventForSecurity(MotionEvent event) { end return ");
		return super.onFilterTouchEventForSecurity(event);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		System.out.println("resp1onse CustomLinearLayout2: public void onWindowFocusChanged(boolean hasWindowFocus) { start void ");

		super.onWindowFocusChanged(hasWindowFocus);
		System.out.println("resp1onse CustomLinearLayout2: public void onWindowFocusChanged(boolean hasWindowFocus) { end void ");
	}

	@Override
	public boolean hasWindowFocus() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean hasWindowFocus() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean hasWindowFocus() { end return ");
		return super.hasWindowFocus();
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onVisibilityChanged(View changedView, int visibility) { start void ");

		super.onVisibilityChanged(changedView, visibility);
		System.out.println("resp1onse CustomLinearLayout2: protected void onVisibilityChanged(View changedView, int visibility) { end void ");
	}

	@Override
	protected void onDisplayHint(int hint) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onDisplayHint(int hint) { start void ");

		super.onDisplayHint(hint);
		System.out.println("resp1onse CustomLinearLayout2: protected void onDisplayHint(int hint) { end void ");
	}

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onWindowVisibilityChanged(int visibility) { start void ");

		super.onWindowVisibilityChanged(visibility);
		System.out.println("resp1onse CustomLinearLayout2: protected void onWindowVisibilityChanged(int visibility) { end void ");
	}

	@Override
	public int getWindowVisibility() {

		System.out.println("resp1onse CustomLinearLayout2: public int getWindowVisibility() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getWindowVisibility() { end return ");
		return super.getWindowVisibility();
	}

	@Override
	public void getWindowVisibleDisplayFrame(Rect outRect) {
		System.out.println("resp1onse CustomLinearLayout2: public void getWindowVisibleDisplayFrame(Rect outRect) { start void ");

		super.getWindowVisibleDisplayFrame(outRect);
		System.out.println("resp1onse CustomLinearLayout2: public void getWindowVisibleDisplayFrame(Rect outRect) { end void ");
	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onConfigurationChanged(Configuration newConfig) { start void ");

		super.onConfigurationChanged(newConfig);
		System.out.println("resp1onse CustomLinearLayout2: protected void onConfigurationChanged(Configuration newConfig) { end void ");
	}

	@Override
	public boolean isInTouchMode() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isInTouchMode() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isInTouchMode() { end return ");
		return super.isInTouchMode();
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyPreIme(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyPreIme(int keyCode, KeyEvent event) { end return ");
		return super.onKeyPreIme(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyDown(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyDown(int keyCode, KeyEvent event) { end return ");
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyLongPress(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyLongPress(int keyCode, KeyEvent event) { end return ");
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyUp(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyUp(int keyCode, KeyEvent event) { end return ");
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { end return ");
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyShortcut(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onKeyShortcut(int keyCode, KeyEvent event) { end return ");
		return super.onKeyShortcut(keyCode, event);
	}

	@Override
	public boolean onCheckIsTextEditor() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onCheckIsTextEditor() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onCheckIsTextEditor() { end return ");
		return super.onCheckIsTextEditor();
	}

	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

		System.out.println("resp1onse CustomLinearLayout2: public InputConnection onCreateInputConnection(EditorInfo outAttrs) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public InputConnection onCreateInputConnection(EditorInfo outAttrs) { end return ");
		return super.onCreateInputConnection(outAttrs);
	}

	@Override
	public boolean checkInputConnectionProxy(View view) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean checkInputConnectionProxy(View view) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean checkInputConnectionProxy(View view) { end return ");
		return super.checkInputConnectionProxy(view);
	}

	@Override
	public void createContextMenu(ContextMenu menu) {
		System.out.println("resp1onse CustomLinearLayout2: public void createContextMenu(ContextMenu menu) { start void ");

		super.createContextMenu(menu);
		System.out.println("resp1onse CustomLinearLayout2: public void createContextMenu(ContextMenu menu) { end void ");
	}

	@Override
	protected ContextMenuInfo getContextMenuInfo() {

		System.out.println("resp1onse CustomLinearLayout2: protected ContextMenuInfo getContextMenuInfo() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected ContextMenuInfo getContextMenuInfo() { end return ");
		return super.getContextMenuInfo();
	}

	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onCreateContextMenu(ContextMenu menu) { start void ");

		super.onCreateContextMenu(menu);
		System.out.println("resp1onse CustomLinearLayout2: protected void onCreateContextMenu(ContextMenu menu) { end void ");
	}

	@Override
	public boolean onTrackballEvent(MotionEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onTrackballEvent(MotionEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onTrackballEvent(MotionEvent event) { end return ");
		return super.onTrackballEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onTouchEvent(MotionEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onTouchEvent(MotionEvent event) { end return ");
		return super.onTouchEvent(event);
	}

	@Override
	public void cancelLongPress() {
		System.out.println("resp1onse CustomLinearLayout2: public void cancelLongPress() { start void ");

		super.cancelLongPress();
		System.out.println("resp1onse CustomLinearLayout2: public void cancelLongPress() { end void ");
	}

	@Override
	public void setTouchDelegate(TouchDelegate delegate) {
		System.out.println("resp1onse CustomLinearLayout2: public void setTouchDelegate(TouchDelegate delegate) { start void ");

		super.setTouchDelegate(delegate);
		System.out.println("resp1onse CustomLinearLayout2: public void setTouchDelegate(TouchDelegate delegate) { end void ");
	}

	@Override
	public TouchDelegate getTouchDelegate() {

		System.out.println("resp1onse CustomLinearLayout2: public TouchDelegate getTouchDelegate() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public TouchDelegate getTouchDelegate() { end return ");
		return super.getTouchDelegate();
	}

	@Override
	public void bringToFront() {
		System.out.println("resp1onse CustomLinearLayout2: public void bringToFront() { start void ");

		super.bringToFront();
		System.out.println("resp1onse CustomLinearLayout2: public void bringToFront() { end void ");
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onScrollChanged(int l, int t, int oldl, int oldt) { start void ");

		super.onScrollChanged(l, t, oldl, oldt);
		System.out.println("resp1onse CustomLinearLayout2: protected void onScrollChanged(int l, int t, int oldl, int oldt) { end void ");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onSizeChanged(int w, int h, int oldw, int oldh) { start void ");

		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("resp1onse CustomLinearLayout2: protected void onSizeChanged(int w, int h, int oldw, int oldh) { end void ");
	}

	@Override
	public void getDrawingRect(Rect outRect) {
		System.out.println("resp1onse CustomLinearLayout2: public void getDrawingRect(Rect outRect) { start void ");

		super.getDrawingRect(outRect);
		System.out.println("resp1onse CustomLinearLayout2: public void getDrawingRect(Rect outRect) { end void ");
	}

	@Override
	public Matrix getMatrix() {

		System.out.println("resp1onse CustomLinearLayout2: public Matrix getMatrix() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Matrix getMatrix() { end return ");
		return super.getMatrix();
	}

	@Override
	public float getRotation() {

		System.out.println("resp1onse CustomLinearLayout2: public float getRotation() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getRotation() { end return ");
		return super.getRotation();
	}

	@Override
	public void setRotation(float rotation) {
		System.out.println("resp1onse CustomLinearLayout2: public void setRotation(float rotation) { start void ");

		super.setRotation(rotation);
		System.out.println("resp1onse CustomLinearLayout2: public void setRotation(float rotation) { end void ");
	}

	@Override
	public float getRotationY() {

		System.out.println("resp1onse CustomLinearLayout2: public float getRotationY() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getRotationY() { end return ");
		return super.getRotationY();
	}

	@Override
	public void setRotationY(float rotationY) {
		System.out.println("resp1onse CustomLinearLayout2: public void setRotationY(float rotationY) { start void ");

		super.setRotationY(rotationY);
		System.out.println("resp1onse CustomLinearLayout2: public void setRotationY(float rotationY) { end void ");
	}

	@Override
	public float getRotationX() {

		System.out.println("resp1onse CustomLinearLayout2: public float getRotationX() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getRotationX() { end return ");
		return super.getRotationX();
	}

	@Override
	public void setRotationX(float rotationX) {
		System.out.println("resp1onse CustomLinearLayout2: public void setRotationX(float rotationX) { start void ");

		super.setRotationX(rotationX);
		System.out.println("resp1onse CustomLinearLayout2: public void setRotationX(float rotationX) { end void ");
	}

	@Override
	public float getScaleX() {

		System.out.println("resp1onse CustomLinearLayout2: public float getScaleX() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getScaleX() { end return ");
		return super.getScaleX();
	}

	@Override
	public void setScaleX(float scaleX) {
		System.out.println("resp1onse CustomLinearLayout2: public void setScaleX(float scaleX) { start void ");

		super.setScaleX(scaleX);
		System.out.println("resp1onse CustomLinearLayout2: public void setScaleX(float scaleX) { end void ");
	}

	@Override
	public float getScaleY() {

		System.out.println("resp1onse CustomLinearLayout2: public float getScaleY() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getScaleY() { end return ");
		return super.getScaleY();
	}

	@Override
	public void setScaleY(float scaleY) {
		System.out.println("resp1onse CustomLinearLayout2: public void setScaleY(float scaleY) { start void ");

		super.setScaleY(scaleY);
		System.out.println("resp1onse CustomLinearLayout2: public void setScaleY(float scaleY) { end void ");
	}

	@Override
	public float getPivotX() {

		System.out.println("resp1onse CustomLinearLayout2: public float getPivotX() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getPivotX() { end return ");
		return super.getPivotX();
	}

	@Override
	public void setPivotX(float pivotX) {
		System.out.println("resp1onse CustomLinearLayout2: public void setPivotX(float pivotX) { start void ");

		super.setPivotX(pivotX);
		System.out.println("resp1onse CustomLinearLayout2: public void setPivotX(float pivotX) { end void ");
	}

	@Override
	public float getPivotY() {

		System.out.println("resp1onse CustomLinearLayout2: public float getPivotY() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getPivotY() { end return ");
		return super.getPivotY();
	}

	@Override
	public void setPivotY(float pivotY) {
		System.out.println("resp1onse CustomLinearLayout2: public void setPivotY(float pivotY) { start void ");

		super.setPivotY(pivotY);
		System.out.println("resp1onse CustomLinearLayout2: public void setPivotY(float pivotY) { end void ");
	}

	@Override
	public float getAlpha() {

		System.out.println("resp1onse CustomLinearLayout2: public float getAlpha() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getAlpha() { end return ");
		return super.getAlpha();
	}

	@Override
	public void setAlpha(float alpha) {
		System.out.println("resp1onse CustomLinearLayout2: public void setAlpha(float alpha) { start void ");

		super.setAlpha(alpha);
		System.out.println("resp1onse CustomLinearLayout2: public void setAlpha(float alpha) { end void ");
	}

	@Override
	public boolean isDirty() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isDirty() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isDirty() { end return ");
		return super.isDirty();
	}

	@Override
	public float getX() {

		System.out.println("resp1onse CustomLinearLayout2: public float getX() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getX() { end return ");
		return super.getX();
	}

	@Override
	public void setX(float x) {
		System.out.println("resp1onse CustomLinearLayout2: public void setX(float x) { start void ");

		super.setX(x);
		System.out.println("resp1onse CustomLinearLayout2: public void setX(float x) { end void ");
	}

	@Override
	public float getY() {

		System.out.println("resp1onse CustomLinearLayout2: public float getY() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getY() { end return ");
		return super.getY();
	}

	@Override
	public void setY(float y) {
		System.out.println("resp1onse CustomLinearLayout2: public void setY(float y) { start void ");

		super.setY(y);
		System.out.println("resp1onse CustomLinearLayout2: public void setY(float y) { end void ");
	}

	@Override
	public float getTranslationX() {

		System.out.println("resp1onse CustomLinearLayout2: public float getTranslationX() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getTranslationX() { end return ");
		return super.getTranslationX();
	}

	@Override
	public void setTranslationX(float translationX) {
		System.out.println("resp1onse CustomLinearLayout2: public void setTranslationX(float translationX) { start void ");

		super.setTranslationX(translationX);
		System.out.println("resp1onse CustomLinearLayout2: public void setTranslationX(float translationX) { end void ");
	}

	@Override
	public float getTranslationY() {

		System.out.println("resp1onse CustomLinearLayout2: public float getTranslationY() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public float getTranslationY() { end return ");
		return super.getTranslationY();
	}

	@Override
	public void setTranslationY(float translationY) {
		System.out.println("resp1onse CustomLinearLayout2: public void setTranslationY(float translationY) { start void ");

		super.setTranslationY(translationY);
		System.out.println("resp1onse CustomLinearLayout2: public void setTranslationY(float translationY) { end void ");
	}

	@Override
	public void getHitRect(Rect outRect) {
		System.out.println("resp1onse CustomLinearLayout2: public void getHitRect(Rect outRect) { start void ");

		super.getHitRect(outRect);
		System.out.println("resp1onse CustomLinearLayout2: public void getHitRect(Rect outRect) { end void ");
	}

	@Override
	public void getFocusedRect(Rect r) {
		System.out.println("resp1onse CustomLinearLayout2: public void getFocusedRect(Rect r) { start void ");

		super.getFocusedRect(r);
		System.out.println("resp1onse CustomLinearLayout2: public void getFocusedRect(Rect r) { end void ");
	}

	@Override
	public boolean getGlobalVisibleRect(Rect r, Point globalOffset) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean getGlobalVisibleRect(Rect r, Point globalOffset) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean getGlobalVisibleRect(Rect r, Point globalOffset) { end return ");
		return super.getGlobalVisibleRect(r, globalOffset);
	}

	@Override
	public void offsetTopAndBottom(int offset) {
		System.out.println("resp1onse CustomLinearLayout2: public void offsetTopAndBottom(int offset) { start void ");

		super.offsetTopAndBottom(offset);
		System.out.println("resp1onse CustomLinearLayout2: public void offsetTopAndBottom(int offset) { end void ");
	}

	@Override
	public void offsetLeftAndRight(int offset) {
		System.out.println("resp1onse CustomLinearLayout2: public void offsetLeftAndRight(int offset) { start void ");

		super.offsetLeftAndRight(offset);
		System.out.println("resp1onse CustomLinearLayout2: public void offsetLeftAndRight(int offset) { end void ");
	}

	@Override
	public android.view.ViewGroup.LayoutParams getLayoutParams() {

		System.out.println("resp1onse CustomLinearLayout2: public android.view.ViewGroup.LayoutParams getLayoutParams() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public android.view.ViewGroup.LayoutParams getLayoutParams() { end return ");
		return super.getLayoutParams();
	}

	@Override
	public void setLayoutParams(android.view.ViewGroup.LayoutParams params) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutParams(android.view.ViewGroup.LayoutParams params) { start void ");

		super.setLayoutParams(params);
		System.out.println("resp1onse CustomLinearLayout2: public void setLayoutParams(android.view.ViewGroup.LayoutParams params) { end void ");
	}

	@Override
	public void scrollTo(int x, int y) {
		System.out.println("resp1onse CustomLinearLayout2: public void scrollTo(int x, int y) { start void ");

		super.scrollTo(x, y);
		System.out.println("resp1onse CustomLinearLayout2: public void scrollTo(int x, int y) { end void ");
	}

	@Override
	public void scrollBy(int x, int y) {
		System.out.println("resp1onse CustomLinearLayout2: public void scrollBy(int x, int y) { start void ");

		super.scrollBy(x, y);
		System.out.println("resp1onse CustomLinearLayout2: public void scrollBy(int x, int y) { end void ");
	}

	@Override
	protected boolean awakenScrollBars() {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars() { end return ");
		return super.awakenScrollBars();
	}

	@Override
	protected boolean awakenScrollBars(int startDelay) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars(int startDelay) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars(int startDelay) { end return ");
		return super.awakenScrollBars(startDelay);
	}

	@Override
	protected boolean awakenScrollBars(int startDelay, boolean invalidate) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars(int startDelay, boolean invalidate) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean awakenScrollBars(int startDelay, boolean invalidate) { end return ");
		return super.awakenScrollBars(startDelay, invalidate);
	}

	@Override
	public void invalidate(Rect dirty) {
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate(Rect dirty) { start void ");

		super.invalidate(dirty);
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate(Rect dirty) { end void ");
	}

	@Override
	public void invalidate(int l, int t, int r, int b) {
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate(int l, int t, int r, int b) { start void ");

		super.invalidate(l, t, r, b);
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate(int l, int t, int r, int b) { end void ");
	}

	@Override
	public void invalidate() {
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate() { start void ");

		super.invalidate();
		System.out.println("resp1onse CustomLinearLayout2: public void invalidate() { end void ");
	}

	@Override
	public boolean isOpaque() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isOpaque() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isOpaque() { end return ");
		return super.isOpaque();
	}

	@Override
	public Handler getHandler() {

		System.out.println("resp1onse CustomLinearLayout2: public Handler getHandler() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Handler getHandler() { end return ");
		return super.getHandler();
	}

	@Override
	public boolean post(Runnable action) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean post(Runnable action) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean post(Runnable action) { end return ");
		return super.post(action);
	}

	@Override
	public boolean postDelayed(Runnable action, long delayMillis) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean postDelayed(Runnable action, long delayMillis) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean postDelayed(Runnable action, long delayMillis) { end return ");
		return super.postDelayed(action, delayMillis);
	}

	@Override
	public boolean removeCallbacks(Runnable action) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean removeCallbacks(Runnable action) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean removeCallbacks(Runnable action) { end return ");
		return super.removeCallbacks(action);
	}

	@Override
	public void postInvalidate() {
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidate() { start void ");

		super.postInvalidate();
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidate() { end void ");
	}

	@Override
	public void postInvalidate(int left, int top, int right, int bottom) {
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidate(int left, int top, int right, int bottom) { start void ");

		super.postInvalidate(left, top, right, bottom);
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidate(int left, int top, int right, int bottom) { end void ");
	}

	@Override
	public void postInvalidateDelayed(long delayMilliseconds) {
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidateDelayed(long delayMilliseconds) { start void ");

		super.postInvalidateDelayed(delayMilliseconds);
		System.out.println("resp1onse CustomLinearLayout2: public void postInvalidateDelayed(long delayMilliseconds) { end void ");
	}

	@Override
	public void postInvalidateDelayed(long delayMilliseconds, int left,
			int top, int right, int bottom) {

		super.postInvalidateDelayed(delayMilliseconds, left, top, right, bottom);
	}

	@Override
	public void computeScroll() {
		System.out.println("resp1onse CustomLinearLayout2: public void computeScroll() { start void ");

		super.computeScroll();
		System.out.println("resp1onse CustomLinearLayout2: public void computeScroll() { end void ");
	}

	@Override
	public boolean isHorizontalFadingEdgeEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isHorizontalFadingEdgeEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isHorizontalFadingEdgeEnabled() { end return ");
		return super.isHorizontalFadingEdgeEnabled();
	}

	@Override
	public void setHorizontalFadingEdgeEnabled(
			boolean horizontalFadingEdgeEnabled) {

		super.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled);
	}

	@Override
	public boolean isVerticalFadingEdgeEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isVerticalFadingEdgeEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isVerticalFadingEdgeEnabled() { end return ");
		return super.isVerticalFadingEdgeEnabled();
	}

	@Override
	public void setVerticalFadingEdgeEnabled(boolean verticalFadingEdgeEnabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalFadingEdgeEnabled(boolean verticalFadingEdgeEnabled) { start void ");

		super.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalFadingEdgeEnabled(boolean verticalFadingEdgeEnabled) { end void ");
	}

	@Override
	protected float getTopFadingEdgeStrength() {

		System.out.println("resp1onse CustomLinearLayout2: protected float getTopFadingEdgeStrength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected float getTopFadingEdgeStrength() { end return ");
		return super.getTopFadingEdgeStrength();
	}

	@Override
	protected float getBottomFadingEdgeStrength() {

		System.out.println("resp1onse CustomLinearLayout2: protected float getBottomFadingEdgeStrength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected float getBottomFadingEdgeStrength() { end return ");
		return super.getBottomFadingEdgeStrength();
	}

	@Override
	protected float getLeftFadingEdgeStrength() {

		System.out.println("resp1onse CustomLinearLayout2: protected float getLeftFadingEdgeStrength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected float getLeftFadingEdgeStrength() { end return ");
		return super.getLeftFadingEdgeStrength();
	}

	@Override
	protected float getRightFadingEdgeStrength() {

		System.out.println("resp1onse CustomLinearLayout2: protected float getRightFadingEdgeStrength() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected float getRightFadingEdgeStrength() { end return ");
		return super.getRightFadingEdgeStrength();
	}

	@Override
	public boolean isHorizontalScrollBarEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isHorizontalScrollBarEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isHorizontalScrollBarEnabled() { end return ");
		return super.isHorizontalScrollBarEnabled();
	}

	@Override
	public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) { start void ");

		super.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) { end void ");
	}

	@Override
	public boolean isVerticalScrollBarEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isVerticalScrollBarEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isVerticalScrollBarEnabled() { end return ");
		return super.isVerticalScrollBarEnabled();
	}

	@Override
	public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) { start void ");

		super.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) { end void ");
	}

	@Override
	public void setScrollbarFadingEnabled(boolean fadeScrollbars) {
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollbarFadingEnabled(boolean fadeScrollbars) { start void ");

		super.setScrollbarFadingEnabled(fadeScrollbars);
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollbarFadingEnabled(boolean fadeScrollbars) { end void ");
	}

	@Override
	public boolean isScrollbarFadingEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isScrollbarFadingEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isScrollbarFadingEnabled() { end return ");
		return super.isScrollbarFadingEnabled();
	}

	@Override
	public void setScrollBarStyle(int style) {
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollBarStyle(int style) { start void ");

		super.setScrollBarStyle(style);
		System.out.println("resp1onse CustomLinearLayout2: public void setScrollBarStyle(int style) { end void ");
	}

	@Override
	public int getScrollBarStyle() {

		System.out.println("resp1onse CustomLinearLayout2: public int getScrollBarStyle() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getScrollBarStyle() { end return ");
		return super.getScrollBarStyle();
	}

	@Override
	protected int computeHorizontalScrollRange() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollRange() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollRange() { end return ");
		return super.computeHorizontalScrollRange();
	}

	@Override
	protected int computeHorizontalScrollOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollOffset() { end return ");
		return super.computeHorizontalScrollOffset();
	}

	@Override
	protected int computeHorizontalScrollExtent() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollExtent() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeHorizontalScrollExtent() { end return ");
		return super.computeHorizontalScrollExtent();
	}

	@Override
	protected int computeVerticalScrollRange() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollRange() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollRange() { end return ");
		return super.computeVerticalScrollRange();
	}

	@Override
	protected int computeVerticalScrollOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollOffset() { end return ");
		return super.computeVerticalScrollOffset();
	}

	@Override
	protected int computeVerticalScrollExtent() {

		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollExtent() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int computeVerticalScrollExtent() { end return ");
		return super.computeVerticalScrollExtent();
	}

	@Override
	protected int getWindowAttachCount() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getWindowAttachCount() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getWindowAttachCount() { end return ");
		return super.getWindowAttachCount();
	}

	@Override
	public IBinder getWindowToken() {

		System.out.println("resp1onse CustomLinearLayout2: public IBinder getWindowToken() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public IBinder getWindowToken() { end return ");
		return super.getWindowToken();
	}

	@Override
	public IBinder getApplicationWindowToken() {

		System.out.println("resp1onse CustomLinearLayout2: public IBinder getApplicationWindowToken() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public IBinder getApplicationWindowToken() { end return ");
		return super.getApplicationWindowToken();
	}

	@Override
	public void saveHierarchyState(SparseArray<Parcelable> container) {
		System.out.println("resp1onse CustomLinearLayout2: public void saveHierarchyState(SparseArray<Parcelable> container) { start void ");

		super.saveHierarchyState(container);
		System.out.println("resp1onse CustomLinearLayout2: public void saveHierarchyState(SparseArray<Parcelable> container) { end void ");
	}

	@Override
	protected Parcelable onSaveInstanceState() {

		System.out.println("resp1onse CustomLinearLayout2: protected Parcelable onSaveInstanceState() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected Parcelable onSaveInstanceState() { end return ");
		return super.onSaveInstanceState();
	}

	@Override
	public void restoreHierarchyState(SparseArray<Parcelable> container) {
		System.out.println("resp1onse CustomLinearLayout2: public void restoreHierarchyState(SparseArray<Parcelable> container) { start void ");

		super.restoreHierarchyState(container);
		System.out.println("resp1onse CustomLinearLayout2: public void restoreHierarchyState(SparseArray<Parcelable> container) { end void ");
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onRestoreInstanceState(Parcelable state) { start void ");

		super.onRestoreInstanceState(state);
		System.out.println("resp1onse CustomLinearLayout2: protected void onRestoreInstanceState(Parcelable state) { end void ");
	}

	@Override
	public long getDrawingTime() {

		System.out.println("resp1onse CustomLinearLayout2: public long getDrawingTime() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public long getDrawingTime() { end return ");
		return super.getDrawingTime();
	}

	@Override
	public void setDuplicateParentStateEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDuplicateParentStateEnabled(boolean enabled) { start void ");

		super.setDuplicateParentStateEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setDuplicateParentStateEnabled(boolean enabled) { end void ");
	}

	@Override
	public boolean isDuplicateParentStateEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isDuplicateParentStateEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isDuplicateParentStateEnabled() { end return ");
		return super.isDuplicateParentStateEnabled();
	}

	@Override
	public void setLayerType(int layerType, Paint paint) {
		System.out.println("resp1onse CustomLinearLayout2: public void setLayerType(int layerType, Paint paint) { start void ");

		super.setLayerType(layerType, paint);
		System.out.println("resp1onse CustomLinearLayout2: public void setLayerType(int layerType, Paint paint) { end void ");
	}

	@Override
	public int getLayerType() {

		System.out.println("resp1onse CustomLinearLayout2: public int getLayerType() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getLayerType() { end return ");
		return super.getLayerType();
	}

	@Override
	public void setDrawingCacheEnabled(boolean enabled) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheEnabled(boolean enabled) { start void ");

		super.setDrawingCacheEnabled(enabled);
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheEnabled(boolean enabled) { end void ");
	}

	@Override
	public boolean isDrawingCacheEnabled() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isDrawingCacheEnabled() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isDrawingCacheEnabled() { end return ");
		return super.isDrawingCacheEnabled();
	}

	@Override
	public Bitmap getDrawingCache() {

		System.out.println("resp1onse CustomLinearLayout2: public Bitmap getDrawingCache() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Bitmap getDrawingCache() { end return ");
		return super.getDrawingCache();
	}

	@Override
	public Bitmap getDrawingCache(boolean autoScale) {

		System.out.println("resp1onse CustomLinearLayout2: public Bitmap getDrawingCache(boolean autoScale) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Bitmap getDrawingCache(boolean autoScale) { end return ");
		return super.getDrawingCache(autoScale);
	}

	@Override
	public void destroyDrawingCache() {
		System.out.println("resp1onse CustomLinearLayout2: public void destroyDrawingCache() { start void ");

		super.destroyDrawingCache();
		System.out.println("resp1onse CustomLinearLayout2: public void destroyDrawingCache() { end void ");
	}

	@Override
	public void setDrawingCacheBackgroundColor(int color) {
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheBackgroundColor(int color) { start void ");

		super.setDrawingCacheBackgroundColor(color);
		System.out.println("resp1onse CustomLinearLayout2: public void setDrawingCacheBackgroundColor(int color) { end void ");
	}

	@Override
	public int getDrawingCacheBackgroundColor() {

		System.out.println("resp1onse CustomLinearLayout2: public int getDrawingCacheBackgroundColor() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getDrawingCacheBackgroundColor() { end return ");
		return super.getDrawingCacheBackgroundColor();
	}

	@Override
	public void buildDrawingCache() {
		System.out.println("resp1onse CustomLinearLayout2: public void buildDrawingCache() { start void ");

		super.buildDrawingCache();
		System.out.println("resp1onse CustomLinearLayout2: public void buildDrawingCache() { end void ");
	}

	@Override
	public void buildDrawingCache(boolean autoScale) {
		System.out.println("resp1onse CustomLinearLayout2: public void buildDrawingCache(boolean autoScale) { start void ");

		super.buildDrawingCache(autoScale);
		System.out.println("resp1onse CustomLinearLayout2: public void buildDrawingCache(boolean autoScale) { end void ");
	}

	@Override
	public boolean isInEditMode() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isInEditMode() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isInEditMode() { end return ");
		return super.isInEditMode();
	}

	@Override
	protected boolean isPaddingOffsetRequired() {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean isPaddingOffsetRequired() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean isPaddingOffsetRequired() { end return ");
		return super.isPaddingOffsetRequired();
	}

	@Override
	protected int getLeftPaddingOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getLeftPaddingOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getLeftPaddingOffset() { end return ");
		return super.getLeftPaddingOffset();
	}

	@Override
	protected int getRightPaddingOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getRightPaddingOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getRightPaddingOffset() { end return ");
		return super.getRightPaddingOffset();
	}

	@Override
	protected int getTopPaddingOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getTopPaddingOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getTopPaddingOffset() { end return ");
		return super.getTopPaddingOffset();
	}

	@Override
	protected int getBottomPaddingOffset() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getBottomPaddingOffset() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getBottomPaddingOffset() { end return ");
		return super.getBottomPaddingOffset();
	}

	@Override
	public boolean isHardwareAccelerated() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isHardwareAccelerated() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isHardwareAccelerated() { end return ");
		return super.isHardwareAccelerated();
	}

	@Override
	public void draw(Canvas canvas) {
		System.out.println("resp1onse CustomLinearLayout2: public void draw(Canvas canvas) { start void ");

		super.draw(canvas);
		System.out.println("resp1onse CustomLinearLayout2: public void draw(Canvas canvas) { end void ");
	}

	@Override
	public int getSolidColor() {

		System.out.println("resp1onse CustomLinearLayout2: public int getSolidColor() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getSolidColor() { end return ");
		return super.getSolidColor();
	}

	@Override
	public boolean isLayoutRequested() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isLayoutRequested() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isLayoutRequested() { end return ");
		return super.isLayoutRequested();
	}

	@Override
	protected void onFinishInflate() {
		System.out.println("resp1onse CustomLinearLayout2: protected void onFinishInflate() { start void ");

		super.onFinishInflate();
		System.out.println("resp1onse CustomLinearLayout2: protected void onFinishInflate() { end void ");
	}

	@Override
	public Resources getResources() {

		System.out.println("resp1onse CustomLinearLayout2: public Resources getResources() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Resources getResources() { end return ");
		return super.getResources();
	}

	@Override
	public void invalidateDrawable(Drawable drawable) {
		System.out.println("resp1onse CustomLinearLayout2: public void invalidateDrawable(Drawable drawable) { start void ");

		super.invalidateDrawable(drawable);
		System.out.println("resp1onse CustomLinearLayout2: public void invalidateDrawable(Drawable drawable) { end void ");
	}

	@Override
	public void scheduleDrawable(Drawable who, Runnable what, long when) {
		System.out.println("resp1onse CustomLinearLayout2: public void scheduleDrawable(Drawable who, Runnable what, long when) { start void ");

		super.scheduleDrawable(who, what, when);
		System.out.println("resp1onse CustomLinearLayout2: public void scheduleDrawable(Drawable who, Runnable what, long when) { end void ");
	}

	@Override
	public void unscheduleDrawable(Drawable who, Runnable what) {
		System.out.println("resp1onse CustomLinearLayout2: public void unscheduleDrawable(Drawable who, Runnable what) { start void ");

		super.unscheduleDrawable(who, what);
		System.out.println("resp1onse CustomLinearLayout2: public void unscheduleDrawable(Drawable who, Runnable what) { end void ");
	}

	@Override
	public void unscheduleDrawable(Drawable who) {
		System.out.println("resp1onse CustomLinearLayout2: public void unscheduleDrawable(Drawable who) { start void ");

		super.unscheduleDrawable(who);
		System.out.println("resp1onse CustomLinearLayout2: public void unscheduleDrawable(Drawable who) { end void ");
	}

	@Override
	protected boolean verifyDrawable(Drawable who) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean verifyDrawable(Drawable who) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean verifyDrawable(Drawable who) { end return ");
		return super.verifyDrawable(who);
	}

	@Override
	public void refreshDrawableState() {
		System.out.println("resp1onse CustomLinearLayout2: public void refreshDrawableState() { start void ");

		super.refreshDrawableState();
		System.out.println("resp1onse CustomLinearLayout2: public void refreshDrawableState() { end void ");
	}

	@Override
	public void setBackgroundColor(int color) {
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundColor(int color) { start void ");

		super.setBackgroundColor(color);
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundColor(int color) { end void ");
	}

	@Override
	public void setBackgroundResource(int resid) {
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundResource(int resid) { start void ");

		super.setBackgroundResource(resid);
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundResource(int resid) { end void ");
	}

	@Override
	public void setBackgroundDrawable(Drawable background) {
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundDrawable(Drawable background) { start void ");

		super.setBackgroundDrawable(background);
		System.out.println("resp1onse CustomLinearLayout2: public void setBackgroundDrawable(Drawable background) { end void ");
	}

	@Override
	public Drawable getBackground() {

		System.out.println("resp1onse CustomLinearLayout2: public Drawable getBackground() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Drawable getBackground() { end return ");
		return super.getBackground();
	}

	@Override
	public void setPadding(int left, int top, int right, int bottom) {
		System.out.println("resp1onse CustomLinearLayout2: public void setPadding(int left, int top, int right, int bottom) { start void ");

		super.setPadding(left, top, right, bottom);
		System.out.println("resp1onse CustomLinearLayout2: public void setPadding(int left, int top, int right, int bottom) { end void ");
	}

	@Override
	public int getPaddingTop() {

		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingTop() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingTop() { end return ");
		return super.getPaddingTop();
	}

	@Override
	public int getPaddingBottom() {

		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingBottom() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingBottom() { end return ");
		return super.getPaddingBottom();
	}

	@Override
	public int getPaddingLeft() {

		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingLeft() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingLeft() { end return ");
		return super.getPaddingLeft();
	}

	@Override
	public int getPaddingRight() {

		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingRight() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getPaddingRight() { end return ");
		return super.getPaddingRight();
	}

	@Override
	public void setSelected(boolean selected) {
		System.out.println("resp1onse CustomLinearLayout2: public void setSelected(boolean selected) { start void ");

		super.setSelected(selected);
		System.out.println("resp1onse CustomLinearLayout2: public void setSelected(boolean selected) { end void ");
	}

	@Override
	public boolean isSelected() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isSelected() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isSelected() { end return ");
		return super.isSelected();
	}

	@Override
	public void setActivated(boolean activated) {
		System.out.println("resp1onse CustomLinearLayout2: public void setActivated(boolean activated) { start void ");

		super.setActivated(activated);
		System.out.println("resp1onse CustomLinearLayout2: public void setActivated(boolean activated) { end void ");
	}

	@Override
	public boolean isActivated() {

		System.out.println("resp1onse CustomLinearLayout2: public boolean isActivated() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean isActivated() { end return ");
		return super.isActivated();
	}

	@Override
	public ViewTreeObserver getViewTreeObserver() {

		System.out.println("resp1onse CustomLinearLayout2: public ViewTreeObserver getViewTreeObserver() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public ViewTreeObserver getViewTreeObserver() { end return ");
		return super.getViewTreeObserver();
	}

	@Override
	public View getRootView() {

		System.out.println("resp1onse CustomLinearLayout2: public View getRootView() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public View getRootView() { end return ");
		return super.getRootView();
	}

	@Override
	public void getLocationOnScreen(int[] location) {
		System.out.println("resp1onse CustomLinearLayout2: public void getLocationOnScreen(int[] location) { start void ");

		super.getLocationOnScreen(location);
		System.out.println("resp1onse CustomLinearLayout2: public void getLocationOnScreen(int[] location) { end void ");
	}

	@Override
	public void getLocationInWindow(int[] location) {
		System.out.println("resp1onse CustomLinearLayout2: public void getLocationInWindow(int[] location) { start void ");

		super.getLocationInWindow(location);
		System.out.println("resp1onse CustomLinearLayout2: public void getLocationInWindow(int[] location) { end void ");
	}

	@Override
	public void setId(int id) {
		System.out.println("resp1onse CustomLinearLayout2: public void setId(int id) { start void ");

		super.setId(id);
		System.out.println("resp1onse CustomLinearLayout2: public void setId(int id) { end void ");
	}

	@Override
	public int getId() {

		System.out.println("resp1onse CustomLinearLayout2: public int getId() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getId() { end return ");
		return super.getId();
	}

	@Override
	public Object getTag() {

		System.out.println("resp1onse CustomLinearLayout2: public Object getTag() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Object getTag() { end return ");
		return super.getTag();
	}

	@Override
	public void setTag(Object tag) {
		System.out.println("resp1onse CustomLinearLayout2: public void setTag(Object tag) { start void ");

		super.setTag(tag);
		System.out.println("resp1onse CustomLinearLayout2: public void setTag(Object tag) { end void ");
	}

	@Override
	public Object getTag(int key) {

		System.out.println("resp1onse CustomLinearLayout2: public Object getTag(int key) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Object getTag(int key) { end return ");
		return super.getTag(key);
	}

	@Override
	public void setTag(int key, Object tag) {
		System.out.println("resp1onse CustomLinearLayout2: public void setTag(int key, Object tag) { start void ");

		super.setTag(key, tag);
		System.out.println("resp1onse CustomLinearLayout2: public void setTag(int key, Object tag) { end void ");
	}

	@Override
	public void requestLayout() {
		System.out.println("resp1onse CustomLinearLayout2: public void requestLayout() { start void ");

		super.requestLayout();
		System.out.println("resp1onse CustomLinearLayout2: public void requestLayout() { end void ");
	}

	@Override
	public void forceLayout() {
		System.out.println("resp1onse CustomLinearLayout2: public void forceLayout() { start void ");

		super.forceLayout();
		System.out.println("resp1onse CustomLinearLayout2: public void forceLayout() { end void ");
	}

	@Override
	protected int getSuggestedMinimumHeight() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getSuggestedMinimumHeight() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getSuggestedMinimumHeight() { end return ");
		return super.getSuggestedMinimumHeight();
	}

	@Override
	protected int getSuggestedMinimumWidth() {

		System.out.println("resp1onse CustomLinearLayout2: protected int getSuggestedMinimumWidth() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected int getSuggestedMinimumWidth() { end return ");
		return super.getSuggestedMinimumWidth();
	}

	@Override
	public void setMinimumHeight(int minHeight) {
		System.out.println("resp1onse CustomLinearLayout2: public void setMinimumHeight(int minHeight) { start void ");

		super.setMinimumHeight(minHeight);
		System.out.println("resp1onse CustomLinearLayout2: public void setMinimumHeight(int minHeight) { end void ");
	}

	@Override
	public void setMinimumWidth(int minWidth) {
		System.out.println("resp1onse CustomLinearLayout2: public void setMinimumWidth(int minWidth) { start void ");

		super.setMinimumWidth(minWidth);
		System.out.println("resp1onse CustomLinearLayout2: public void setMinimumWidth(int minWidth) { end void ");
	}

	@Override
	public Animation getAnimation() {

		System.out.println("resp1onse CustomLinearLayout2: public Animation getAnimation() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public Animation getAnimation() { end return ");
		return super.getAnimation();
	}

	@Override
	public void startAnimation(Animation animation) {
		System.out.println("resp1onse CustomLinearLayout2: public void startAnimation(Animation animation) { start void ");

		super.startAnimation(animation);
		System.out.println("resp1onse CustomLinearLayout2: public void startAnimation(Animation animation) { end void ");
	}

	@Override
	public void clearAnimation() {
		System.out.println("resp1onse CustomLinearLayout2: public void clearAnimation() { start void ");

		super.clearAnimation();
		System.out.println("resp1onse CustomLinearLayout2: public void clearAnimation() { end void ");
	}

	@Override
	public void setAnimation(Animation animation) {
		System.out.println("resp1onse CustomLinearLayout2: public void setAnimation(Animation animation) { start void ");

		super.setAnimation(animation);
		System.out.println("resp1onse CustomLinearLayout2: public void setAnimation(Animation animation) { end void ");
	}

	@Override
	protected boolean onSetAlpha(int alpha) {

		System.out.println("resp1onse CustomLinearLayout2: protected boolean onSetAlpha(int alpha) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: protected boolean onSetAlpha(int alpha) { end return ");
		return super.onSetAlpha(alpha);
	}

	@Override
	public void playSoundEffect(int soundConstant) {
		System.out.println("resp1onse CustomLinearLayout2: public void playSoundEffect(int soundConstant) { start void ");

		super.playSoundEffect(soundConstant);
		System.out.println("resp1onse CustomLinearLayout2: public void playSoundEffect(int soundConstant) { end void ");
	}

	@Override
	public boolean performHapticFeedback(int feedbackConstant) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean performHapticFeedback(int feedbackConstant) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean performHapticFeedback(int feedbackConstant) { end return ");
		return super.performHapticFeedback(feedbackConstant);
	}

	@Override
	public boolean performHapticFeedback(int feedbackConstant, int flags) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean performHapticFeedback(int feedbackConstant, int flags) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean performHapticFeedback(int feedbackConstant, int flags) { end return ");
		return super.performHapticFeedback(feedbackConstant, flags);
	}

	@Override
	public void setSystemUiVisibility(int visibility) {
		System.out.println("resp1onse CustomLinearLayout2: public void setSystemUiVisibility(int visibility) { start void ");

		super.setSystemUiVisibility(visibility);
		System.out.println("resp1onse CustomLinearLayout2: public void setSystemUiVisibility(int visibility) { end void ");
	}

	@Override
	public int getSystemUiVisibility() {

		System.out.println("resp1onse CustomLinearLayout2: public int getSystemUiVisibility() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getSystemUiVisibility() { end return ");
		return super.getSystemUiVisibility();
	}

	@Override
	public void setOnSystemUiVisibilityChangeListener( OnSystemUiVisibilityChangeListener l) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOnSystemUiVisibilityChangeListener( OnSystemUiVisibilityChangeListener l) { start void ");

		super.setOnSystemUiVisibilityChangeListener(l);
		System.out.println("resp1onse CustomLinearLayout2: public void setOnSystemUiVisibilityChangeListener( OnSystemUiVisibilityChangeListener l) { end void ");
	}

	@Override
	public boolean onDragEvent(DragEvent event) {

		System.out.println("resp1onse CustomLinearLayout2: public boolean onDragEvent(DragEvent event) { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public boolean onDragEvent(DragEvent event) { end return ");
		return super.onDragEvent(event);
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
				scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
				isTouchEvent);
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
		System.out.println("resp1onse CustomLinearLayout2: protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) { start void ");

		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
		System.out.println("resp1onse CustomLinearLayout2: protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) { end void ");
	}

	@Override
	public int getOverScrollMode() {

		System.out.println("resp1onse CustomLinearLayout2: public int getOverScrollMode() { start return ");
		System.out.println("resp1onse CustomLinearLayout2: public int getOverScrollMode() { end return ");
		return super.getOverScrollMode();
	}

	@Override
	public void setOverScrollMode(int overScrollMode) {
		System.out.println("resp1onse CustomLinearLayout2: public void setOverScrollMode(int overScrollMode) { start void ");

		super.setOverScrollMode(overScrollMode);
		System.out.println("resp1onse CustomLinearLayout2: public void setOverScrollMode(int overScrollMode) { end void ");
	}

}
