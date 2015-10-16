/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dzl.test.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
//import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

import com.dzl.test.R;
import com.dzl.test.util.ReflectUtil;
import com.dzl.test.util.ReflectUtilView;

/**
 * A view that shows items in a center-locked, horizontally scrolling list.
 * <p>
 * The default values for the Gallery assume you will be using
 * {@link android.R.styleable#Theme_galleryItemBackground} as the background for
 * each View given to the Gallery from the Adapter. If you are not doing this,
 * you may need to adjust some Gallery properties, such as the spacing.
 * <p>
 * Views given to the Gallery should use {@link Gallery2.LayoutParams} as their
 * layout parameters type.
 * 
 * @attr ref android.R.styleable#Gallery_animationDuration
 * @attr ref android.R.styleable#Gallery_spacing
 * @attr ref android.R.styleable#Gallery_gravity
 * 
 *  This widget is no longer supported. Other horizontally scrolling
 * widgets include {@link HorizontalScrollView} and {@link android.support.v4.view.ViewPager}
 * from the support library.
 */

//@Widget
public class Gallery2 extends AbsSpinner2 implements GestureDetector.OnGestureListener {

    private static final String TAG = "Gallery";

    private static final boolean localLOGV = false;

    /**
     * Duration in milliseconds from the start of a scroll during which we're
     * unsure whether the user is scrolling or flinging.
     */
    private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;

    /**
     * Horizontal spacing between items.
     */
    private int mSpacing = 0;

    /**
     * How long the transition animation should run when a child view changes
     * position, measured in milliseconds.
     */
    private int mAnimationDuration = 400;

    /**
     * The alpha of items that are not selected.
     */
    private float mUnselectedAlpha;
    
    /**
     * Left most edge of a child seen so far during layout.
     */
    private int mLeftMost;

    /**
     * Right most edge of a child seen so far during layout.
     */
    private int mRightMost;

    private int mGravity;

    /**
     * Helper for detecting touch gestures.
     */
    private GestureDetector mGestureDetector;

    /**
     * The position of the item that received the user's down touch.
     */
    private int mDownTouchPosition;

    /**
     * The view of the item that received the user's down touch.
     */
    private View mDownTouchView;
    
    /**
     * Executes the delta scrolls from a fling or scroll movement. 
     */
    private FlingRunnable mFlingRunnable = new FlingRunnable();

    /**
     * Sets mSuppressSelectionChanged = false. This is used to set it to false
     * in the future. It will also trigger a selection changed.
     */
    private Runnable mDisableSuppressSelectionChangedRunnable = new Runnable() {
        @Override
        public void run() {
		System.out.println("resp1onse Gallery2: public void run() { start void ");
            mSuppressSelectionChanged = false;
            selectionChanged();
		System.out.println("resp1onse Gallery2: public void run() { end void ");
        }
    };
    
    /**
     * When fling runnable runs, it resets this to false. Any method along the
     * path until the end of its run() can set this to true to abort any
     * remaining fling. For example, if we've reached either the leftmost or
     * rightmost item, we will set this to true.
     */
    private boolean mShouldStopFling;
    
    /**
     * The currently selected item's child.
     */
    private View mSelectedChild;
    
    /**
     * Whether to continuously callback on the item selected listener during a
     * fling.
     */
    private boolean mShouldCallbackDuringFling = true;

    /**
     * Whether to callback when an item that is not selected is clicked.
     */
    private boolean mShouldCallbackOnUnselectedItemClick = true;

    /**
     * If true, do not callback to item selected listener. 
     */
    private boolean mSuppressSelectionChanged;

    /**
     * If true, we have received the "invoke" (center or enter buttons) key
     * down. This is checked before we action on the "invoke" key up, and is
     * subsequently cleared.
     */
    private boolean mReceivedInvokeKeyDown;
    
    private AdapterContextMenuInfo mContextMenuInfo;

    /**
     * If true, this onScroll is the first for this user's drag (remember, a
     * drag sends many onScrolls).
     */
    private boolean mIsFirstScroll;

    /**
     * If true, mFirstPosition is the position of the rightmost child, and
     * the children are ordered right to left.
     */
    private boolean mIsRtl = true;
    
    /**
     * Offset between the center of the selected child view and the center of the Gallery.
     * Used to reset position correctly during layout.
     */
    private int mSelectedCenterOffset;

    public Gallery2(Context context) {
        this(context, null);
		System.out.println("resp1onse Gallery2: public Gallery2(Context context) { start constructor ");
		System.out.println("resp1onse Gallery2: public Gallery2(Context context) { end constructor ");
    }

    public Gallery2(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.galleryStyle);
		System.out.println("resp1onse Gallery2: public Gallery2(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse Gallery2: public Gallery2(Context context, AttributeSet attrs) { end constructor ");
    }

    public Gallery2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		System.out.println("resp1onse Gallery2: public Gallery2(Context context, AttributeSet attrs, int defStyleAttr) { start constructor ");
//    }
//
//    public Gallery2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        
        mGestureDetector = new GestureDetector(context, this);
        mGestureDetector.setIsLongpressEnabled(true);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.Gallery, defStyleAttr, 0);

        int index = a.getInt(R.styleable.Gallery_gravity, -1);
        if (index >= 0) {
            setGravity(index);
        }

        int animationDuration =
                a.getInt(R.styleable.Gallery_animationDuration, -1);
        if (animationDuration > 0) {
            setAnimationDuration(animationDuration);
        }

        int spacing =
                a.getDimensionPixelOffset(R.styleable.Gallery_spacing, 0);
        setSpacing(spacing);

        float unselectedAlpha = a.getFloat(
                R.styleable.Gallery_unselectedAlpha, 0.3f);
        setUnselectedAlpha(unselectedAlpha);
        
        a.recycle();

        // We draw the selected item last (because otherwise the item to the
        // right overlaps it)
//        mGroupFlags |= FLAG_USE_CHILD_DRAWING_ORDER;
//        
//        mGroupFlags |= FLAG_SUPPORT_STATIC_TRANSFORMATIONS;
        ReflectUtil.setField_ViewGroup_mGroupFlags(this,
        		ReflectUtil.getField_ViewGroup_mGroupFlags(this) | 1024);
        ReflectUtil.setField_ViewGroup_mGroupFlags(this, 
        		ReflectUtil.getField_ViewGroup_mGroupFlags(this) | 2048);
		System.out.println("resp1onse Gallery2: public Gallery2(Context context, AttributeSet attrs, int defStyleAttr) { end constructor ");
    }

    /**
     * Whether or not to callback on any {@link #getOnItemSelectedListener()}
     * while the items are being flinged. If false, only the final selected item
     * will cause the callback. If true, all items between the first and the
     * final will cause callbacks.
     * 
     * @param shouldCallback Whether or not to callback on the listener while
     *            the items are being flinged.
     */
    public void setCallbackDuringFling(boolean shouldCallback) {
		System.out.println("resp1onse Gallery2: public void setCallbackDuringFling(boolean shouldCallback) { start void ");
        mShouldCallbackDuringFling = shouldCallback;
		System.out.println("resp1onse Gallery2: public void setCallbackDuringFling(boolean shouldCallback) { end void ");
    }

    /**
     * Whether or not to callback when an item that is not selected is clicked.
     * If false, the item will become selected (and re-centered). If true, the
     * {@link #getOnItemClickListener()} will get the callback.
     * 
     * @param shouldCallback Whether or not to callback on the listener when a
     *            item that is not selected is clicked.
     * @hide
     */
    public void setCallbackOnUnselectedItemClick(boolean shouldCallback) {
		System.out.println("resp1onse Gallery2: public void setCallbackOnUnselectedItemClick(boolean shouldCallback) { start void ");
        mShouldCallbackOnUnselectedItemClick = shouldCallback;
		System.out.println("resp1onse Gallery2: public void setCallbackOnUnselectedItemClick(boolean shouldCallback) { end void ");
    }
    
    /**
     * Sets how long the transition animation should run when a child view
     * changes position. Only relevant if animation is turned on.
     * 
     * @param animationDurationMillis The duration of the transition, in
     *        milliseconds.
     * 
     * @attr ref android.R.styleable#Gallery_animationDuration
     */
    public void setAnimationDuration(int animationDurationMillis) {
		System.out.println("resp1onse Gallery2: public void setAnimationDuration(int animationDurationMillis) { start void ");
        mAnimationDuration = animationDurationMillis;
		System.out.println("resp1onse Gallery2: public void setAnimationDuration(int animationDurationMillis) { end void ");
    }

    /**
     * Sets the spacing between items in a Gallery
     * 
     * @param spacing The spacing in pixels between items in the Gallery
     * 
     * @attr ref android.R.styleable#Gallery_spacing
     */
    public void setSpacing(int spacing) {
		System.out.println("resp1onse Gallery2: public void setSpacing(int spacing) { start void ");
        mSpacing = spacing;
		System.out.println("resp1onse Gallery2: public void setSpacing(int spacing) { end void ");
    }

    /**
     * Sets the alpha of items that are not selected in the Gallery.
     * 
     * @param unselectedAlpha the alpha for the items that are not selected.
     * 
     * @attr ref android.R.styleable#Gallery_unselectedAlpha
     */
    public void setUnselectedAlpha(float unselectedAlpha) {
		System.out.println("resp1onse Gallery2: public void setUnselectedAlpha(float unselectedAlpha) { start void ");
        mUnselectedAlpha = unselectedAlpha;
		System.out.println("resp1onse Gallery2: public void setUnselectedAlpha(float unselectedAlpha) { end void ");
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        
		System.out.println("resp1onse Gallery2: protected boolean getChildStaticTransformation(View child, Transformation t) { start return ");
        t.clear();
        t.setAlpha(child == mSelectedChild ? 1.0f : mUnselectedAlpha);
        
		System.out.println("resp1onse Gallery2: protected boolean getChildStaticTransformation(View child, Transformation t) { end return  =1");
        return true;
    }

    @Override
    protected int computeHorizontalScrollExtent() {
        // Only 1 item is considered to be selected
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollExtent() { start return ");
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollExtent() { end return ");
        return 1;
    }

    @Override
    protected int computeHorizontalScrollOffset() {
        // Current scroll position is the same as the selected position
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollOffset() { start return ");
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollOffset() { end return ");
        return mSelectedPosition;
    }

    @Override
    protected int computeHorizontalScrollRange() {
        // Scroll range is the same as the item count
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollRange() { start return ");
		System.out.println("resp1onse Gallery2: protected int computeHorizontalScrollRange() { end return ");
        return mItemCount;
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		System.out.println("resp1onse Gallery2: protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse Gallery2: protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { end return ");
        return p instanceof LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		System.out.println("resp1onse Gallery2: protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse Gallery2: protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { end return ");
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		System.out.println("resp1onse Gallery2: public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) { start return ");
		System.out.println("resp1onse Gallery2: public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) { end return ");
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        /*
         * Gallery expects Gallery.LayoutParams.
         */
		System.out.println("resp1onse Gallery2: protected ViewGroup.LayoutParams generateDefaultLayoutParams() { start return ");
		System.out.println("resp1onse Gallery2: protected ViewGroup.LayoutParams generateDefaultLayoutParams() { end return ");
        return new Gallery2.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
		System.out.println("resp1onse Gallery2: protected void onLayout(boolean changed, int l, int t, int r, int b) { start void ");
        super.onLayout(changed, l, t, r, b);
        
        /*
         * Remember that we are in layout to prevent more layout request from
         * being generated.
         */
        mInLayout = true;
        layout(0, false);
        mInLayout = false;
		System.out.println("resp1onse Gallery2: protected void onLayout(boolean changed, int l, int t, int r, int b) { end void ");
    }

    @Override
    int getChildHeight(View child) {
        return child.getMeasuredHeight();
    }

    /**
     * Tracks a motion scroll. In reality, this is used to do just about any
     * movement to items (touch scroll, arrow-key scroll, set an item as selected).
     * 
     * @param deltaX Change in X from the previous event.
     */
    void trackMotionScroll(int deltaX) {
		System.out.println("resp1onse Gallery2: void trackMotionScroll(int deltaX) { start void ");

        if (getChildCount() == 0) {
            return;
        }
        
        boolean toLeft = deltaX < 0; 
        
        int limitedDeltaX = getLimitedMotionScrollAmount(toLeft, deltaX);
        if (limitedDeltaX != deltaX) {
            // The above call returned a limited amount, so stop any scrolls/flings
            mFlingRunnable.endFling(false);
            onFinishedMovement();
        }
        
        offsetChildrenLeftAndRight(limitedDeltaX);
        
        detachOffScreenChildren(toLeft);
        
        if (toLeft) {
            // If moved left, there will be empty space on the right
            fillToGalleryRight();
        } else {
            // Similarly, empty space on the left
            fillToGalleryLeft();
        }
        
        // Clear unused views
        mRecycler.clear();
        
        setSelectionToCenterChild();

        final View selChild = mSelectedChild;
        if (selChild != null) {
            final int childLeft = selChild.getLeft();
            final int childCenter = selChild.getWidth() / 2;
            final int galleryCenter = getWidth() / 2;
            mSelectedCenterOffset = childLeft + childCenter - galleryCenter;
        }

        onScrollChanged(0, 0, 0, 0); // dummy values, View's implementation does not use these.

        invalidate();
		System.out.println("resp1onse Gallery2: void trackMotionScroll(int deltaX) { end void ");
    }

    int getLimitedMotionScrollAmount(boolean motionToLeft, int deltaX) {
        int extremeItemPosition = motionToLeft != mIsRtl ? mItemCount - 1 : 0;
        View extremeChild = getChildAt(extremeItemPosition - mFirstPosition);
        
        if (extremeChild == null) {
            return deltaX;
        }
        
        int extremeChildCenter = getCenterOfView(extremeChild);
        int galleryCenter = getCenterOfGallery();
        
        if (motionToLeft) {
            if (extremeChildCenter <= galleryCenter) {
                
                // The extreme child is past his boundary point!
                return 0;
            }
        } else {
            if (extremeChildCenter >= galleryCenter) {

                // The extreme child is past his boundary point!
                return 0;
            }
        }
        
        int centerDifference = galleryCenter - extremeChildCenter;

        return motionToLeft
                ? Math.max(centerDifference, deltaX)
                : Math.min(centerDifference, deltaX); 
    }

    /**
     * Offset the horizontal location of all children of this view by the
     * specified number of pixels.
     * 
     * @param offset the number of pixels to offset
     */
    private void offsetChildrenLeftAndRight(int offset) {
		System.out.println("resp1onse Gallery2: private void offsetChildrenLeftAndRight(int offset) { start void ");
        for (int i = getChildCount() - 1; i >= 0; i--) {
            getChildAt(i).offsetLeftAndRight(offset);
        }
		System.out.println("resp1onse Gallery2: private void offsetChildrenLeftAndRight(int offset) { end void ");
    }
    
    /**
     * @return The center of this Gallery.
     */
    private int getCenterOfGallery() {
		System.out.println("resp1onse Gallery2: private int getCenterOfGallery() { start return ");
		System.out.println("resp1onse Gallery2: private int getCenterOfGallery() { end return ");
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }
    
    /**
     * @return The center of the given view.
     */
    private static int getCenterOfView(View view) {
		System.out.println("resp1onse Gallery2: private static int getCenterOfView(View view) { start return ");
		System.out.println("resp1onse Gallery2: private static int getCenterOfView(View view) { end return ");
        return view.getLeft() + view.getWidth() / 2;
    }
    
    /**
     * Detaches children that are off the screen (i.e.: Gallery bounds).
     * 
     * @param toLeft Whether to detach children to the left of the Gallery, or
     *            to the right.
     */
    private void detachOffScreenChildren(boolean toLeft) {
		System.out.println("resp1onse Gallery2: private void detachOffScreenChildren(boolean toLeft) { start void ");
        int numChildren = getChildCount();
        int firstPosition = mFirstPosition;
        int start = 0;
        int count = 0;

        if (toLeft) {
            final int galleryLeft = getPaddingLeft();
            for (int i = 0; i < numChildren; i++) {
                int n = mIsRtl ? (numChildren - 1 - i) : i;
                final View child = getChildAt(n);
                if (child.getRight() >= galleryLeft) {
                    break;
                } else {
                    start = n;
                    count++;
                    mRecycler.put(firstPosition + n, child);
                }
            }
            if (!mIsRtl) {
                start = 0;
            }
        } else {
            final int galleryRight = getWidth() - getPaddingRight();
            for (int i = numChildren - 1; i >= 0; i--) {
                int n = mIsRtl ? numChildren - 1 - i : i;
                final View child = getChildAt(n);
                if (child.getLeft() <= galleryRight) {
                    break;
                } else {
                    start = n;
                    count++;
                    mRecycler.put(firstPosition + n, child);
                }
            }
            if (mIsRtl) {
                start = 0;
            }
        }

        detachViewsFromParent(start, count);
        
        if (toLeft != mIsRtl) {
            mFirstPosition += count;
        }
		System.out.println("resp1onse Gallery2: private void detachOffScreenChildren(boolean toLeft) { end void ");
    }
    
    /**
     * Scrolls the items so that the selected item is in its 'slot' (its center
     * is the gallery's center).
     */
    private void scrollIntoSlots() {
		System.out.println("resp1onse Gallery2: private void scrollIntoSlots() { start void ");
        
        if (getChildCount() == 0 || mSelectedChild == null) return;
        
        int selectedCenter = getCenterOfView(mSelectedChild);
        int targetCenter = getCenterOfGallery();
        
        int scrollAmount = targetCenter - selectedCenter;
        if (scrollAmount != 0) {
            mFlingRunnable.startUsingDistance(scrollAmount);
        } else {
            onFinishedMovement();
        }
		System.out.println("resp1onse Gallery2: private void scrollIntoSlots() { end void ");
    }

    private void onFinishedMovement() {
		System.out.println("resp1onse Gallery2: private void onFinishedMovement() { start void ");
        if (mSuppressSelectionChanged) {
            mSuppressSelectionChanged = false;
            
            // We haven't been callbacking during the fling, so do it now
            super.selectionChanged();
        }
        mSelectedCenterOffset = 0;
        invalidate();
		System.out.println("resp1onse Gallery2: private void onFinishedMovement() { end void ");
    }
    
    @Override
    void selectionChanged() {
		System.out.println("resp1onse Gallery2: void selectionChanged() { start void ");
        if (!mSuppressSelectionChanged) {
            super.selectionChanged();
        }
		System.out.println("resp1onse Gallery2: void selectionChanged() { end void ");
    }

    /**
     * Looks for the child that is closest to the center and sets it as the
     * selected child.
     */
    private void setSelectionToCenterChild() {
		System.out.println("resp1onse Gallery2: private void setSelectionToCenterChild() { start void ");
        
        View selView = mSelectedChild;
        if (mSelectedChild == null) return;
        
        int galleryCenter = getCenterOfGallery();
        
        // Common case where the current selected position is correct
        if (selView.getLeft() <= galleryCenter && selView.getRight() >= galleryCenter) {
            return;
        }
        
        // TODO better search
        int closestEdgeDistance = Integer.MAX_VALUE;
        int newSelectedChildIndex = 0;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            
            View child = getChildAt(i);
            
            if (child.getLeft() <= galleryCenter && child.getRight() >=  galleryCenter) {
                // This child is in the center
                newSelectedChildIndex = i;
                break;
            }
            
            int childClosestEdgeDistance = Math.min(Math.abs(child.getLeft() - galleryCenter),
                    Math.abs(child.getRight() - galleryCenter));
            if (childClosestEdgeDistance < closestEdgeDistance) {
                closestEdgeDistance = childClosestEdgeDistance;
                newSelectedChildIndex = i;
            }
        }
        
        int newPos = mFirstPosition + newSelectedChildIndex;
        
        if (newPos != mSelectedPosition) {
            setSelectedPositionInt(newPos);
            setNextSelectedPositionInt(newPos);
            checkSelectionChanged();
        }
		System.out.println("resp1onse Gallery2: private void setSelectionToCenterChild() { end void ");
    }

    /**
     * Creates and positions all views for this Gallery.
     * <p>
     * We layout rarely, most of the time {@link #trackMotionScroll(int)} takes
     * care of repositioning, adding, and removing children.
     * 
     * @param delta Change in the selected position. +1 means the selection is
     *            moving to the right, so views are scrolling to the left. -1
     *            means the selection is moving to the left.
     */
    @Override
    void layout(int delta, boolean animate) {
		System.out.println("resp1onse Gallery2: void layout(int delta, boolean animate) { start void ");

//        mIsRtl = isLayoutRtl();
    	mIsRtl = false;

        int childrenLeft = mSpinnerPadding.left;
        int childrenWidth = getRight()- getLeft()  - mSpinnerPadding.left - mSpinnerPadding.right;

        if (mDataChanged) {
            handleDataChanged();
        }

        // Handle an empty gallery by removing all views.
        if (mItemCount == 0) {
            resetList();
		System.out.println("resp1onse Gallery2: void layout(int delta, boolean animate) { end return if ");
            return;
        }

        // Update to the new selected position.
        if (mNextSelectedPosition >= 0) {
            setSelectedPositionInt(mNextSelectedPosition);
        }

        // All views go in recycler while we are in layout
        recycleAllViews();

        // Clear out old views
        //removeAllViewsInLayout();
        detachAllViewsFromParent();

        /*
         * These will be used to give initial positions to views entering the
         * gallery as we scroll
         */
        mRightMost = 0;
        mLeftMost = 0;

        // Make selected view and center it
        
        /*
         * mFirstPosition will be decreased as we add views to the left later
         * on. The 0 for x will be offset in a couple lines down.
         */  
        mFirstPosition = mSelectedPosition;
        View sel = makeAndAddView(mSelectedPosition, 0, 0, true);//因在onMeasure中已缓存了一个子view,故都是取缓存区的子view
        
        // Put the selected child in the center
        int selectedOffset = childrenLeft + (childrenWidth / 2) - (sel.getWidth() / 2) +
                mSelectedCenterOffset;
        sel.offsetLeftAndRight(selectedOffset);

        fillToGalleryRight();
        fillToGalleryLeft();
        
        // Flush any cached views that did not get reused above
        mRecycler.clear();

        invalidate();
        checkSelectionChanged();

        mDataChanged = false;
        mNeedSync = false;
        setNextSelectedPositionInt(mSelectedPosition);
        
        updateSelectedItemMetadata();
		System.out.println("resp1onse Gallery2: void layout(int delta, boolean animate) { end void ");
    }

    private void fillToGalleryLeft() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeft() { start void ");
        if (mIsRtl) {
            fillToGalleryLeftRtl();
        } else {
            fillToGalleryLeftLtr();
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeft() { end void ");
    }

    private void fillToGalleryLeftRtl() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeftRtl() { start void ");
        int itemSpacing = mSpacing;
        int galleryLeft = getPaddingLeft();
        int numChildren = getChildCount();
        int numItems = mItemCount;

        // Set state for initial iteration
        View prevIterationView = getChildAt(numChildren - 1);
        int curPosition;
        int curRightEdge;

        if (prevIterationView != null) {
            curPosition = mFirstPosition + numChildren;
            curRightEdge = prevIterationView.getLeft() - itemSpacing;
        } else {
            // No children available!
            mFirstPosition = curPosition = mItemCount - 1;
            curRightEdge = getRight()- getLeft()  - getPaddingRight();
            mShouldStopFling = true;
        }

        while (curRightEdge > galleryLeft && curPosition < mItemCount) {
            prevIterationView = makeAndAddView(curPosition, curPosition - mSelectedPosition,
                    curRightEdge, false);

            // Set state for next iteration
            curRightEdge = prevIterationView.getLeft() - itemSpacing;
            curPosition++;
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeftRtl() { end void ");
    }

    private void fillToGalleryLeftLtr() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeftLtr() { start void ");
        int itemSpacing = mSpacing;
        int galleryLeft = getPaddingLeft();
        
        // Set state for initial iteration
        View prevIterationView = getChildAt(0);
        int curPosition;
        int curRightEdge;
        
        if (prevIterationView != null) {
            curPosition = mFirstPosition - 1;
            curRightEdge = prevIterationView.getLeft() - itemSpacing;
        } else {
            // No children available!
            curPosition = 0; 
            curRightEdge = getRight()- getLeft()  - getPaddingRight();
            mShouldStopFling = true;
        }
                
        while (curRightEdge > galleryLeft && curPosition >= 0) {
            prevIterationView = makeAndAddView(curPosition, curPosition - mSelectedPosition,
                    curRightEdge, false);

            // Remember some state
            mFirstPosition = curPosition;
            
            // Set state for next iteration
            curRightEdge = prevIterationView.getLeft() - itemSpacing;
            curPosition--;
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryLeftLtr() { end void ");
    }
    
    private void fillToGalleryRight() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryRight() { start void ");
        if (mIsRtl) {
            fillToGalleryRightRtl();
        } else {
            fillToGalleryRightLtr();
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryRight() { end void ");
    }

    private void fillToGalleryRightRtl() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryRightRtl() { start void ");
        int itemSpacing = mSpacing;
        int galleryRight = getRight()- getLeft()  - getPaddingRight();

        // Set state for initial iteration
        View prevIterationView = getChildAt(0);
        int curPosition;
        int curLeftEdge;

        if (prevIterationView != null) {
            curPosition = mFirstPosition -1;
            curLeftEdge = prevIterationView.getRight() + itemSpacing;
        } else {
            curPosition = 0;
            curLeftEdge = getPaddingLeft();
            mShouldStopFling = true;
        }

        while (curLeftEdge < galleryRight && curPosition >= 0) {
            prevIterationView = makeAndAddView(curPosition, curPosition - mSelectedPosition,
                    curLeftEdge, true);

            // Remember some state
            mFirstPosition = curPosition;

            // Set state for next iteration
            curLeftEdge = prevIterationView.getRight() + itemSpacing;
            curPosition--;
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryRightRtl() { end void ");
    }

    private void fillToGalleryRightLtr() {
		System.out.println("resp1onse Gallery2: private void fillToGalleryRightLtr() { start void ");
        int itemSpacing = mSpacing;
        int galleryRight = getRight()- getLeft()  - getPaddingRight();
        int numChildren = getChildCount();
        int numItems = mItemCount;
        
        // Set state for initial iteration
        View prevIterationView = getChildAt(numChildren - 1);
        int curPosition;
        int curLeftEdge;
        
        if (prevIterationView != null) {
            curPosition = mFirstPosition + numChildren;
            curLeftEdge = prevIterationView.getRight() + itemSpacing;
        } else {
            mFirstPosition = curPosition = mItemCount - 1;
            curLeftEdge = getPaddingLeft();
            mShouldStopFling = true;
        }
                
        while (curLeftEdge < galleryRight && curPosition < numItems) {
            prevIterationView = makeAndAddView(curPosition, curPosition - mSelectedPosition,
                    curLeftEdge, true);

            // Set state for next iteration
            curLeftEdge = prevIterationView.getRight() + itemSpacing;
            curPosition++;
        }
		System.out.println("resp1onse Gallery2: private void fillToGalleryRightLtr() { end void ");
    }

    /**
     * Obtain a view, either by pulling an existing view from the recycler or by
     * getting a new one from the adapter. If we are animating, make sure there
     * is enough information in the view's layout parameters to animate from the
     * old to new positions.
     * 
     * @param position Position in the gallery for the view to obtain
     * @param offset Offset from the selected position
     * @param x X-coordinate indicating where this view should be placed. This
     *        will either be the left or right edge of the view, depending on
     *        the fromLeft parameter
     * @param fromLeft Are we positioning views based on the left edge? (i.e.,
     *        building from left to right)?
     * @return A view that has been added to the gallery
     */
    private View makeAndAddView(int position, int offset, int x, boolean fromLeft) {

		System.out.println("resp1onse Gallery2: private View makeAndAddView(int position, int offset, int x, boolean fromLeft) { start return ");
        View child;
        if (!mDataChanged) {
            child = mRecycler.get(position);//循环makeAndAddView：在第一次onLayout中，无缓存子view，故child = null
            								//循环makeAndAddView：在第二次onLayout中，已缓存所有子view，故child != null 
            if (child != null) {
                // Can reuse an existing view
                int childLeft = child.getLeft();
                
                // Remember left and right edges of where views have been placed
                mRightMost = Math.max(mRightMost, childLeft 
                        + child.getMeasuredWidth());
                mLeftMost = Math.min(mLeftMost, childLeft);

                // Position the view
                setUpChild(child, offset, x, fromLeft);

		System.out.println("resp1onse Gallery2: private View makeAndAddView(int position, int offset, int x, boolean fromLeft) { end return if ");
                return child;
            }
        }

        // Nothing found in the recycler -- ask the adapter for a view
        child = mAdapter.getView(position, null, this);

        // Position the view
        setUpChild(child, offset, x, fromLeft);

		System.out.println("resp1onse Gallery2: private View makeAndAddView(int position, int offset, int x, boolean fromLeft) { end return  =1");
        return child;
    }

    /**
     * Helper for makeAndAddView to set the position of a view and fill out its
     * layout parameters.
     * 
     * @param child The view to position
     * @param offset Offset from the selected position
     * @param x X-coordinate indicating where this view should be placed. This
     *        will either be the left or right edge of the view, depending on
     *        the fromLeft parameter
     * @param fromLeft Are we positioning views based on the left edge? (i.e.,
     *        building from left to right)?
     */
    private void setUpChild(View child, int offset, int x, boolean fromLeft) {
		System.out.println("resp1onse Gallery2: private void setUpChild(View child, int offset, int x, boolean fromLeft) { start void ");

        // Respect layout params that are already in the view. Otherwise
        // make some up...
        Gallery2.LayoutParams lp = (Gallery2.LayoutParams) child.getLayoutParams();
        if (lp == null) {
            lp = (Gallery2.LayoutParams) generateDefaultLayoutParams();
        }

        addViewInLayout(child, fromLeft != mIsRtl ? -1 : 0, lp, true);

        child.setSelected(offset == 0);

        // Get measure specs
        int childHeightSpec = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec,
                mSpinnerPadding.top + mSpinnerPadding.bottom, lp.height);
        int childWidthSpec = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec,
                mSpinnerPadding.left + mSpinnerPadding.right, lp.width);

        // Measure child
        child.measure(childWidthSpec, childHeightSpec);

        int childLeft;
        int childRight;

        // Position vertically based on gravity setting
        int childTop = calculateTop(child, true);
        int childBottom = childTop + child.getMeasuredHeight();

        int width = child.getMeasuredWidth();
        if (fromLeft) {
            childLeft = x;
            childRight = childLeft + width;
        } else {
            childLeft = x - width;
            childRight = x;
        }

        child.layout(childLeft, childTop, childRight, childBottom);
		System.out.println("resp1onse Gallery2: private void setUpChild(View child, int offset, int x, boolean fromLeft) { end void ");
    }

    /**
     * Figure out vertical placement based on mGravity
     * 
     * @param child Child to place
     * @return Where the top of the child should be
     */
    private int calculateTop(View child, boolean duringLayout) {
		System.out.println("resp1onse Gallery2: private int calculateTop(View child, boolean duringLayout) { start return ");
        int myHeight = duringLayout ? getMeasuredHeight() : getHeight();
        int childHeight = duringLayout ? child.getMeasuredHeight() : child.getHeight(); 
        
        int childTop = 0;

        switch (mGravity) {
        case Gravity.TOP:
            childTop = mSpinnerPadding.top;
            break;
        case Gravity.CENTER_VERTICAL:
            int availableSpace = myHeight - mSpinnerPadding.bottom
                    - mSpinnerPadding.top - childHeight;
            childTop = mSpinnerPadding.top + (availableSpace / 2);
            break;
        case Gravity.BOTTOM:
            childTop = myHeight - mSpinnerPadding.bottom - childHeight;
            break;
        }
		System.out.println("resp1onse Gallery2: private int calculateTop(View child, boolean duringLayout) { end return  =1");
        return childTop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Give everything to the gesture detector
		System.out.println("resp1onse Gallery2: public boolean onTouchEvent(MotionEvent event) { start return ");
        boolean retValue = mGestureDetector.onTouchEvent(event);

        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            // Helper method for lifted finger
            onUp();
        } else if (action == MotionEvent.ACTION_CANCEL) {
            onCancel();
        }
        
		System.out.println("resp1onse Gallery2: public boolean onTouchEvent(MotionEvent event) { end return  =1");
        return retValue;
    }
    
    @Override
    public boolean onSingleTapUp(MotionEvent e) {

		System.out.println("resp1onse Gallery2 OnGestureListener: public boolean onSingleTapUp(MotionEvent e) { start return ");
        if (mDownTouchPosition >= 0) {
            
            // An item tap should make it selected, so scroll to this child.
            scrollToChild(mDownTouchPosition - mFirstPosition);

            // Also pass the click so the client knows, if it wants to.
            if (mShouldCallbackOnUnselectedItemClick || mDownTouchPosition == mSelectedPosition) {
                performItemClick(mDownTouchView, mDownTouchPosition, mAdapter
                        .getItemId(mDownTouchPosition));
            }
            
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onSingleTapUp(MotionEvent e) { end return if ");
            return true;
        }
        
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onSingleTapUp(MotionEvent e) { end return  =1");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { start return ");
        if (!mShouldCallbackDuringFling) {
            // We want to suppress selection changes
            
            // Remove any future code to set mSuppressSelectionChanged = false
            removeCallbacks(mDisableSuppressSelectionChangedRunnable);

            // This will get reset once we scroll into slots
            if (!mSuppressSelectionChanged) mSuppressSelectionChanged = true;
        }
        
        // Fling the gallery!
        mFlingRunnable.startUsingVelocity((int) -velocityX);
        
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { end return  =1");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { start return ");
        if (localLOGV) Log.v(TAG, String.valueOf(e2.getX() - e1.getX()));
        
        /*
         * Now's a good time to tell our parent to stop intercepting our events!
         * The user has moved more than the slop amount, since GestureDetector
         * ensures this before calling this method. Also, if a parent is more
         * interested in this touch's events than we are, it would have
         * intercepted them by now (for example, we can assume when a Gallery is
         * in the ListView, a vertical scroll would not end up in this method
         * since a ListView would have intercepted it by now).
         */
//        mParent.requestDisallowInterceptTouchEvent(true);
        ViewParent mParent = ReflectUtilView.getField_View_mParent(this);
        mParent.requestDisallowInterceptTouchEvent(true);
        
        // As the user scrolls, we want to callback selection changes so related-
        // info on the screen is up-to-date with the gallery's selection
        if (!mShouldCallbackDuringFling) {
            if (mIsFirstScroll) {
                /*
                 * We're not notifying the client of selection changes during
                 * the fling, and this scroll could possibly be a fling. Don't
                 * do selection changes until we're sure it is not a fling.
                 */
                if (!mSuppressSelectionChanged) mSuppressSelectionChanged = true;
                postDelayed(mDisableSuppressSelectionChangedRunnable, SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT);
            }
        } else {
            if (mSuppressSelectionChanged) mSuppressSelectionChanged = false;
        }
        
        // Track the motion
        trackMotionScroll(-1 * (int) distanceX);
       
        mIsFirstScroll = false;
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { end return  =1");
        return true;
    }
    
    @Override
    public boolean onDown(MotionEvent e) {

        // Kill any existing fling/scroll
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onDown(MotionEvent e) { start return ");
        mFlingRunnable.stop(false);

        // Get the item's view that was touched
        mDownTouchPosition = pointToPosition((int) e.getX(), (int) e.getY());
        
        if (mDownTouchPosition >= 0) {
            mDownTouchView = getChildAt(mDownTouchPosition - mFirstPosition);
            mDownTouchView.setPressed(true);
        }
        
        // Reset the multiple-scroll tracking state
        mIsFirstScroll = true;
        
        // Must return true to get matching events for this down event.
		System.out.println("resp1onse Gallery2 OnGestureListener : public boolean onDown(MotionEvent e) { end return  =1");
        return true;
    }

    /**
     * Called when a touch event's action is MotionEvent.ACTION_UP.
     */
    void onUp() {
		System.out.println("resp1onse Gallery2: void onUp() { start void ");
        
        if (mFlingRunnable.mScroller.isFinished()) {
            scrollIntoSlots();
        }
        
        dispatchUnpress();
		System.out.println("resp1onse Gallery2: void onUp() { end void ");
    }
    
    /**
     * Called when a touch event's action is MotionEvent.ACTION_CANCEL.
     */
    void onCancel() {
		System.out.println("resp1onse Gallery2: void onCancel() { start void ");
        onUp();
		System.out.println("resp1onse Gallery2: void onCancel() { end void ");
    }
    
    @Override
    public void onLongPress(MotionEvent e) {
		System.out.println("resp1onse Gallery2 OnGestureListener : public void onLongPress(MotionEvent e) { start void ");
        
        if (mDownTouchPosition < 0) {
            return;
        }
        
        performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        long id = getItemIdAtPosition(mDownTouchPosition);
        dispatchLongPress(mDownTouchView, mDownTouchPosition, id);
		System.out.println("resp1onse Gallery2 OnGestureListener : public void onLongPress(MotionEvent e) { end void ");
    }

    // Unused methods from GestureDetector.OnGestureListener below
    
    @Override
    public void onShowPress(MotionEvent e) {
		System.out.println("resp1onse Gallery2 OnGestureListener : public void onShowPress(MotionEvent e) { start void ");
		System.out.println("resp1onse Gallery2 OnGestureListener : public void onShowPress(MotionEvent e) { end void ");
    }

    // Unused methods from GestureDetector.OnGestureListener above
    
    private void dispatchPress(View child) {
		System.out.println("resp1onse Gallery2: private void dispatchPress(View child) { start void ");
        
        if (child != null) {
            child.setPressed(true);
        }
        
        setPressed(true);
		System.out.println("resp1onse Gallery2: private void dispatchPress(View child) { end void ");
    }
    
    private void dispatchUnpress() {
		System.out.println("resp1onse Gallery2: private void dispatchUnpress() { start void ");
        
        for (int i = getChildCount() - 1; i >= 0; i--) {
            getChildAt(i).setPressed(false);
        }
        
        setPressed(false);
		System.out.println("resp1onse Gallery2: private void dispatchUnpress() { end void ");
    }
    
    @Override
    public void dispatchSetSelected(boolean selected) {
		System.out.println("resp1onse Gallery2: public void dispatchSetSelected(boolean selected) { start void ");
        /*
         * We don't want to pass the selected state given from its parent to its
         * children since this widget itself has a selected state to give to its
         * children.
         */
		System.out.println("resp1onse Gallery2: public void dispatchSetSelected(boolean selected) { end void ");
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
		System.out.println("resp1onse Gallery2: protected void dispatchSetPressed(boolean pressed) { start void ");
        
        // Show the pressed state on the selected child
        if (mSelectedChild != null) {
            mSelectedChild.setPressed(pressed);
        }
		System.out.println("resp1onse Gallery2: protected void dispatchSetPressed(boolean pressed) { end void ");
    }

    @Override
    protected ContextMenuInfo getContextMenuInfo() {
		System.out.println("resp1onse Gallery2: protected ContextMenuInfo getContextMenuInfo() { start return ");
		System.out.println("resp1onse Gallery2: protected ContextMenuInfo getContextMenuInfo() { end return ");
        return mContextMenuInfo;
    }

    @Override
    public boolean showContextMenuForChild(View originalView) {

		System.out.println("resp1onse Gallery2: public boolean showContextMenuForChild(View originalView) { start return ");
        final int longPressPosition = getPositionForView(originalView);
        if (longPressPosition < 0) {
            return false;
        }
        
        final long longPressId = mAdapter.getItemId(longPressPosition);
		System.out.println("resp1onse Gallery2: public boolean showContextMenuForChild(View originalView) { end return  =1");
        return dispatchLongPress(originalView, longPressPosition, longPressId);
    }

    @Override
    public boolean showContextMenu() {
        
		System.out.println("resp1onse Gallery2: public boolean showContextMenu() { start return ");
        if (isPressed() && mSelectedPosition >= 0) {
            int index = mSelectedPosition - mFirstPosition;
            View v = getChildAt(index);
		System.out.println("resp1onse Gallery2: public boolean showContextMenu() { end return if ");
            return dispatchLongPress(v, mSelectedPosition, mSelectedRowId);
        }        
        
		System.out.println("resp1onse Gallery2: public boolean showContextMenu() { end return  =1");
        return false;
    }

    private boolean dispatchLongPress(View view, int position, long id) {
		System.out.println("resp1onse Gallery2: private boolean dispatchLongPress(View view, int position, long id) { start return ");
        boolean handled = false;
        
        if (mOnItemLongClickListener != null) {
            handled = mOnItemLongClickListener.onItemLongClick(this, mDownTouchView,
                    mDownTouchPosition, id);
        }

        if (!handled) {
            mContextMenuInfo = new AdapterContextMenuInfo(view, position, id);
            handled = super.showContextMenuForChild(this);
        }

        if (handled) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        
		System.out.println("resp1onse Gallery2: private boolean dispatchLongPress(View view, int position, long id) { end return  =1");
        return handled;
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Gallery steals all key events
		System.out.println("resp1onse Gallery2: public boolean dispatchKeyEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse Gallery2: public boolean dispatchKeyEvent(KeyEvent event) { end return ");
        return event.dispatch(this, null, null);
    }

    /**
     * Handles left, right, and clicking
     * @see android.view.View#onKeyDown
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("resp1onse Gallery2: public boolean onKeyDown(int keyCode, KeyEvent event) { start return ");
        switch (keyCode) {
            
        case KeyEvent.KEYCODE_DPAD_LEFT:
            if (movePrevious()) {
                playSoundEffect(SoundEffectConstants.NAVIGATION_LEFT);
		System.out.println("resp1onse Gallery2: public boolean onKeyDown(int keyCode, KeyEvent event) { end return if ");
                return true;
            }
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (moveNext()) {
                playSoundEffect(SoundEffectConstants.NAVIGATION_RIGHT);
		System.out.println("resp1onse Gallery2: public boolean onKeyDown(int keyCode, KeyEvent event) { end return if ");
                return true;
            }
            break;
        case KeyEvent.KEYCODE_DPAD_CENTER:
        case KeyEvent.KEYCODE_ENTER:
            mReceivedInvokeKeyDown = true;
            // fallthrough to default handling
        }
        
		System.out.println("resp1onse Gallery2: public boolean onKeyDown(int keyCode, KeyEvent event) { end return  =1");
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (KeyEvent.isConfirmKey(keyCode)) {
//            if (mReceivedInvokeKeyDown) {
//                if (mItemCount > 0) {
//                    dispatchPress(mSelectedChild);
//                    postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            dispatchUnpress();
//                        }
//                    }, ViewConfiguration.getPressedStateDuration());
//
//                    int selectedIndex = mSelectedPosition - mFirstPosition;
//                    performItemClick(getChildAt(selectedIndex), mSelectedPosition, mAdapter
//                            .getItemId(mSelectedPosition));
//                }
//            }
//
//            // Clear the flag
//            mReceivedInvokeKeyDown = false;
//            return true;
//        }
//        return super.onKeyUp(keyCode, event);
//    }
    
    boolean movePrevious() {
        if (mItemCount > 0 && mSelectedPosition > 0) {
            scrollToChild(mSelectedPosition - mFirstPosition - 1);
            return true;
        } else {
            return false;
        }
    }

    boolean moveNext() {
        if (mItemCount > 0 && mSelectedPosition < mItemCount - 1) {
            scrollToChild(mSelectedPosition - mFirstPosition + 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean scrollToChild(int childPosition) {
		System.out.println("resp1onse Gallery2: private boolean scrollToChild(int childPosition) { start return ");
        View child = getChildAt(childPosition);
        
        if (child != null) {
            int distance = getCenterOfGallery() - getCenterOfView(child);
            mFlingRunnable.startUsingDistance(distance);
		System.out.println("resp1onse Gallery2: private boolean scrollToChild(int childPosition) { end return if ");
            return true;
        }
        
		System.out.println("resp1onse Gallery2: private boolean scrollToChild(int childPosition) { end return  =1");
        return false;
    }
    
    @Override
    void setSelectedPositionInt(int position) {
		System.out.println("resp1onse Gallery2: void setSelectedPositionInt(int position) { start void ");
        super.setSelectedPositionInt(position);

        // Updates any metadata we keep about the selected item.
        updateSelectedItemMetadata();
		System.out.println("resp1onse Gallery2: void setSelectedPositionInt(int position) { end void ");
    }

    private void updateSelectedItemMetadata() {
		System.out.println("resp1onse Gallery2: private void updateSelectedItemMetadata() { start void ");
        
        View oldSelectedChild = mSelectedChild;

        View child = mSelectedChild = getChildAt(mSelectedPosition - mFirstPosition);
        if (child == null) {
            return;
        }

        child.setSelected(true);
        child.setFocusable(true);

        if (hasFocus()) {
            child.requestFocus();
        }

        // We unfocus the old child down here so the above hasFocus check
        // returns true
        if (oldSelectedChild != null && oldSelectedChild != child) {

            // Make sure its drawable state doesn't contain 'selected'
            oldSelectedChild.setSelected(false);
            
            // Make sure it is not focusable anymore, since otherwise arrow keys
            // can make this one be focused
            oldSelectedChild.setFocusable(false);
        }
        
		System.out.println("resp1onse Gallery2: private void updateSelectedItemMetadata() { end void ");
    }
    
    /**
     * Describes how the child views are aligned.
     * @param gravity
     * 
     * @attr ref android.R.styleable#Gallery_gravity
     */
    public void setGravity(int gravity)
    {
        if (mGravity != gravity) {
            mGravity = gravity;
            requestLayout();
        }
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
		System.out.println("resp1onse Gallery2: protected int getChildDrawingOrder(int " + childCount + " , int " + i + " ) { start return ");
        int selectedIndex = mSelectedPosition - mFirstPosition;
        
        System.out.println("resp1onse Gallery2: protected int getChildDrawingOrder(int " + childCount + " , int " + i + " ) { start return selectedIndex = " + selectedIndex);
        // Just to be safe
        if (selectedIndex < 0) return i;
        
        if (i == childCount - 1) {
            // Draw the selected child last
            return selectedIndex;
        } else if (i >= selectedIndex) {
            // Move the children after the selected child earlier one
            return i + 1;
        } else {
            // Keep the children before the selected child the same
            return i;
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		System.out.println("resp1onse Gallery2: protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) { start void ");
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        
        /*
         * The gallery shows focus by focusing the selected item. So, give
         * focus to our selected item instead. We steal keys from our
         * selected item elsewhere.
         */
        if (gainFocus && mSelectedChild != null) {
            mSelectedChild.requestFocus(direction);
            mSelectedChild.setSelected(true);
        }

		System.out.println("resp1onse Gallery2: protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) { end void ");
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("resp1onse Gallery2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { start void ");
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(Gallery2.class.getName());
		System.out.println("resp1onse Gallery2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { end void ");
    }

    @SuppressLint("NewApi")
	@Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
		System.out.println("resp1onse Gallery2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { start void ");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(Gallery2.class.getName());
        info.setScrollable(mItemCount > 1);
        if (isEnabled()) {
            if (mItemCount > 0 && mSelectedPosition < mItemCount - 1) {
                info.addAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            if (isEnabled() && mItemCount > 0 && mSelectedPosition > 0) {
                info.addAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
            }
        }
		System.out.println("resp1onse Gallery2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { end void ");
    }

    @Override
    public boolean performAccessibilityAction(int action, Bundle arguments) {
		System.out.println("resp1onse Gallery2: public boolean performAccessibilityAction(int action, Bundle arguments) { start return ");
        if (super.performAccessibilityAction(action, arguments)) {
            return true;
        }
        switch (action) {
            case AccessibilityNodeInfo.ACTION_SCROLL_FORWARD: {
                if (isEnabled() && mItemCount > 0 && mSelectedPosition < mItemCount - 1) {
                    final int currentChildIndex = mSelectedPosition - mFirstPosition;
		System.out.println("resp1onse Gallery2: public boolean performAccessibilityAction(int action, Bundle arguments) { end return if ");
                    return scrollToChild(currentChildIndex + 1);
                }
            } return false;
            case AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD: {
                if (isEnabled() && mItemCount > 0 && mSelectedPosition > 0) {
                    final int currentChildIndex = mSelectedPosition - mFirstPosition;
		System.out.println("resp1onse Gallery2: public boolean performAccessibilityAction(int action, Bundle arguments) { end return if ");
                    return scrollToChild(currentChildIndex - 1);
                }
            } return false;
        }
		System.out.println("resp1onse Gallery2: public boolean performAccessibilityAction(int action, Bundle arguments) { end return  =1");
        return false;
    }

    /**
     * Responsible for fling behavior. Use {@link #startUsingVelocity(int)} to
     * initiate a fling. Each frame of the fling is handled in {@link #run()}.
     * A FlingRunnable will keep re-posting itself until the fling is done.
     */
    private class FlingRunnable implements Runnable {
        /**
         * Tracks the decay of a fling scroll
         */
        private Scroller mScroller;

        /**
         * X value reported by mScroller on the previous fling
         */
        private int mLastFlingX;

        public FlingRunnable() {
		System.out.println("resp1onse Gallery2: public FlingRunnable() { start constructor ");
            mScroller = new Scroller(getContext());
		System.out.println("resp1onse Gallery2: public FlingRunnable() { end constructor ");
        }

        private void startCommon() {
		System.out.println("resp1onse Gallery2: private void startCommon() { start void ");
            // Remove any pending flings
            removeCallbacks(this);
		System.out.println("resp1onse Gallery2: private void startCommon() { end void ");
        }
        
        public void startUsingVelocity(int initialVelocity) {
		System.out.println("resp1onse Gallery2: public void startUsingVelocity(int initialVelocity) { start void ");
            if (initialVelocity == 0) return;
            
            startCommon();
            
            int initialX = initialVelocity < 0 ? Integer.MAX_VALUE : 0;
            mLastFlingX = initialX;
            mScroller.fling(initialX, 0, initialVelocity, 0,
                    0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
            post(this);
		System.out.println("resp1onse Gallery2: public void startUsingVelocity(int initialVelocity) { end void ");
        }

        public void startUsingDistance(int distance) {
		System.out.println("resp1onse Gallery2: public void startUsingDistance(int distance) { start void ");
            if (distance == 0) return;
            
            startCommon();
            
            mLastFlingX = 0;
            mScroller.startScroll(0, 0, -distance, 0, mAnimationDuration);
            post(this);
		System.out.println("resp1onse Gallery2: public void startUsingDistance(int distance) { end void ");
        }
        
        public void stop(boolean scrollIntoSlots) {
		System.out.println("resp1onse Gallery2: public void stop(boolean scrollIntoSlots) { start void ");
            removeCallbacks(this);
            endFling(scrollIntoSlots);
		System.out.println("resp1onse Gallery2: public void stop(boolean scrollIntoSlots) { end void ");
        }
        
        private void endFling(boolean scrollIntoSlots) {
		System.out.println("resp1onse Gallery2: private void endFling(boolean scrollIntoSlots) { start void ");
            /*
             * Force the scroller's status to finished (without setting its
             * position to the end)
             */
            mScroller.forceFinished(true);
            
            if (scrollIntoSlots) scrollIntoSlots();
		System.out.println("resp1onse Gallery2: private void endFling(boolean scrollIntoSlots) { end void ");
        }

        @Override
        public void run() {
		System.out.println("resp1onse Gallery2: public void run() { start void ");

            if (mItemCount == 0) {
                endFling(true);
		System.out.println("resp1onse Gallery2: public void run() { end return if ");
                return;
            }

            mShouldStopFling = false;
            
            final Scroller scroller = mScroller;
            boolean more = scroller.computeScrollOffset();
            final int x = scroller.getCurrX();

            // Flip sign to convert finger direction to list items direction
            // (e.g. finger moving down means list is moving towards the top)
            int delta = mLastFlingX - x;

            // Pretend that each frame of a fling scroll is a touch scroll
            if (delta > 0) {
                // Moving towards the left. Use leftmost view as mDownTouchPosition
                mDownTouchPosition = mIsRtl ? (mFirstPosition + getChildCount() - 1) :
                    mFirstPosition;

                // Don't fling more than 1 screen
                delta = Math.min(getWidth() - getPaddingLeft() - getPaddingRight() - 1, delta);
            } else {
                // Moving towards the right. Use rightmost view as mDownTouchPosition
                int offsetToLast = getChildCount() - 1;
                mDownTouchPosition = mIsRtl ? mFirstPosition :
                    (mFirstPosition + getChildCount() - 1);

                // Don't fling more than 1 screen
                delta = Math.max(-(getWidth() - getPaddingRight() - getPaddingLeft() - 1), delta);
            }

            trackMotionScroll(delta);

            if (more && !mShouldStopFling) {
                mLastFlingX = x;
                post(this);
            } else {
               endFling(true);
            }
		System.out.println("resp1onse Gallery2: public void run() { end void ");
        }
        
    }
    
    /**
     * Gallery extends LayoutParams to provide a place to hold current
     * Transformation information along with previous position/transformation
     * info.
     */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
		System.out.println("resp1onse Gallery2: public LayoutParams(Context c, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse Gallery2: public LayoutParams(Context c, AttributeSet attrs) { end constructor ");
        }

        public LayoutParams(int w, int h) {
            super(w, h);
		System.out.println("resp1onse Gallery2: public LayoutParams(int w, int h) { start constructor ");
		System.out.println("resp1onse Gallery2: public LayoutParams(int w, int h) { end constructor ");
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
		System.out.println("resp1onse Gallery2: public LayoutParams(ViewGroup.LayoutParams source) { start constructor ");
		System.out.println("resp1onse Gallery2: public LayoutParams(ViewGroup.LayoutParams source) { end constructor ");
        }
    }
}
