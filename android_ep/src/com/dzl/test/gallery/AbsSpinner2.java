/*
 * Copyright (C) 2006 The Android Open Source Project
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
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SpinnerAdapter;


/**
 * An abstract base class for spinner widgets. SDK users will probably not
 * need to use this class.
 * 
 * @attr ref android.R.styleable#AbsSpinner_entries
 */
@SuppressLint("NewApi")
public abstract class AbsSpinner2 extends AdapterView2<SpinnerAdapter> {
    SpinnerAdapter mAdapter;

    int mHeightMeasureSpec;
    int mWidthMeasureSpec;

    int mSelectionLeftPadding = 0;
    int mSelectionTopPadding = 0;
    int mSelectionRightPadding = 0;
    int mSelectionBottomPadding = 0;
    final Rect mSpinnerPadding = new Rect();

    final RecycleBin mRecycler = new RecycleBin();
    private DataSetObserver mDataSetObserver;

    /** Temporary frame to hold a child View's frame rectangle */
    private Rect mTouchFrame;

    public AbsSpinner2(Context context) {
        super(context);
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context) { start constructor ");
        initAbsSpinner();
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context) { end constructor ");
    }

    public AbsSpinner2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context, AttributeSet attrs) { end constructor ");
    }

    public AbsSpinner2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context, AttributeSet attrs, int defStyleAttr) { start constructor ");
//    }
//
//    public AbsSpinner2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
        initAbsSpinner();

//        final TypedArray a = context.obtainStyledAttributes(
//                attrs, R.styleable.AbsSpinner2, defStyleAttr, 0);
//
//        CharSequence[] entries = a.getTextArray(R.styleable.AbsSpinner_entries);
//        if (entries != null) {
//            ArrayAdapter<CharSequence> adapter =
//                    new ArrayAdapter<CharSequence>(context,
//                            android.R.layout.simple_spinner_item, entries);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            setAdapter(adapter);
//        }
//
//        a.recycle();
		System.out.println("resp1onse AbsSpinner2: public AbsSpinner2(Context context, AttributeSet attrs, int defStyleAttr) { end constructor ");
    }

    /**
     * Common code for different constructor flavors
     */
    private void initAbsSpinner() {
		System.out.println("resp1onse AbsSpinner2: private void initAbsSpinner() { start void ");
        setFocusable(true);
        setWillNotDraw(false);
		System.out.println("resp1onse AbsSpinner2: private void initAbsSpinner() { end void ");
    }

    /**
     * The Adapter is used to provide the data which backs this Spinner.
     * It also provides methods to transform spinner items based on their position
     * relative to the selected item.
     * @param adapter The SpinnerAdapter to use for this Spinner
     */
    @Override
    public void setAdapter(SpinnerAdapter adapter) {
		System.out.println("resp1onse AbsSpinner2: public void setAdapter(SpinnerAdapter adapter) { start void ");
        if (null != mAdapter) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            resetList();
        }
        
        mAdapter = adapter;
        
        mOldSelectedPosition = INVALID_POSITION;
        mOldSelectedRowId = INVALID_ROW_ID;
        
        if (mAdapter != null) {
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
            checkFocus();

            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);

            int position = mItemCount > 0 ? 0 : INVALID_POSITION;

            setSelectedPositionInt(position);
            setNextSelectedPositionInt(position);
            
            if (mItemCount == 0) {
                // Nothing selected
                checkSelectionChanged();
            }
            
        } else {
            checkFocus();
            resetList();
            // Nothing selected
            checkSelectionChanged();
        }

        requestLayout();
		System.out.println("resp1onse AbsSpinner2: public void setAdapter(SpinnerAdapter adapter) { end void ");
    }

    /**
     * Clear out all children from the list
     */
    void resetList() {
		System.out.println("resp1onse AbsSpinner2: void resetList() { start void ");
        mDataChanged = false;
        mNeedSync = false;
        
        removeAllViewsInLayout();
        mOldSelectedPosition = INVALID_POSITION;
        mOldSelectedRowId = INVALID_ROW_ID;
        
        setSelectedPositionInt(INVALID_POSITION);
        setNextSelectedPositionInt(INVALID_POSITION);
        invalidate();
		System.out.println("resp1onse AbsSpinner2: void resetList() { end void ");
    }

    /** 
     * @see android.view.View#measure(int, int)
     * 
     * Figure out the dimensions of this Spinner. The width comes from
     * the widthMeasureSpec as Spinnners can't have their width set to
     * UNSPECIFIED. The height is based on the height of the selected item
     * plus padding. 
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse AbsSpinner2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void ");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize;
        int heightSize;

        mSpinnerPadding.left = getPaddingLeft() > mSelectionLeftPadding ? getPaddingLeft()
                : mSelectionLeftPadding;
        mSpinnerPadding.top = getPaddingTop() > mSelectionTopPadding ? getPaddingTop()
                : mSelectionTopPadding;
        mSpinnerPadding.right = getPaddingRight() > mSelectionRightPadding ? getPaddingRight()
                : mSelectionRightPadding;
        mSpinnerPadding.bottom = getPaddingBottom() > mSelectionBottomPadding ? getPaddingBottom()
                : mSelectionBottomPadding;

        if (mDataChanged) {
            handleDataChanged();
        }
        
        int preferredHeight = 0;
        int preferredWidth = 0;
        boolean needsMeasuring = true;
        
        int selectedPosition = getSelectedItemPosition();
        System.out.println("resp1onse AbsSpinner2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void selectedPosition = " + selectedPosition);
        if (selectedPosition >= 0 && mAdapter != null && selectedPosition < mAdapter.getCount()) {
            // Try looking in the recycler. (Maybe we were measured once already)
            View view = mRecycler.get(selectedPosition);
            System.out.println("resp1onse AbsSpinner2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void mRecycler.get(" + selectedPosition + ") = " + (view == null)  );
            if (view == null) {
                // Make a new one
                view = mAdapter.getView(selectedPosition, null, this);

                if (view.getImportantForAccessibility() == IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
                    view.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
                }
            }

            if (view != null) {
                // Put in recycler for re-measuring and/or layout
                mRecycler.put(selectedPosition, view);

                if (view.getLayoutParams() == null) {
                    mBlockLayoutRequests = true;
                    view.setLayoutParams(generateDefaultLayoutParams());
                    mBlockLayoutRequests = false;
                }
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
                
                preferredHeight = getChildHeight(view) + mSpinnerPadding.top + mSpinnerPadding.bottom;
                preferredWidth = getChildWidth(view) + mSpinnerPadding.left + mSpinnerPadding.right;
                
                needsMeasuring = false;
            }
        }
        
        if (needsMeasuring) {
            // No views -- just use padding
            preferredHeight = mSpinnerPadding.top + mSpinnerPadding.bottom;
            if (widthMode == MeasureSpec.UNSPECIFIED) {
                preferredWidth = mSpinnerPadding.left + mSpinnerPadding.right;
            }
        }

        preferredHeight = Math.max(preferredHeight, getSuggestedMinimumHeight());
        preferredWidth = Math.max(preferredWidth, getSuggestedMinimumWidth());

        heightSize = resolveSizeAndState(preferredHeight, heightMeasureSpec, 0);
        widthSize = resolveSizeAndState(preferredWidth, widthMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        mHeightMeasureSpec = heightMeasureSpec;
        mWidthMeasureSpec = widthMeasureSpec;
		System.out.println("resp1onse AbsSpinner2: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { end void ");
    }

    int getChildHeight(View child) {
        return child.getMeasuredHeight();
    }
    
    int getChildWidth(View child) {
        return child.getMeasuredWidth();
    }
    
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
		System.out.println("resp1onse AbsSpinner2: protected ViewGroup.LayoutParams generateDefaultLayoutParams() { start return ");
		System.out.println("resp1onse AbsSpinner2: protected ViewGroup.LayoutParams generateDefaultLayoutParams() { end return ");
        return new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    
    void recycleAllViews() {
		System.out.println("resp1onse AbsSpinner2: void recycleAllViews() { start void ");
        final int childCount = getChildCount();
        final AbsSpinner2.RecycleBin recycleBin = mRecycler;
        final int position = mFirstPosition;

        // All views go in recycler
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            int index = position + i;
            recycleBin.put(index, v);
        }  
		System.out.println("resp1onse AbsSpinner2: void recycleAllViews() { end void ");
    }

    /**
     * Jump directly to a specific item in the adapter data.
     */
    public void setSelection(int position, boolean animate) {
		System.out.println("resp1onse AbsSpinner2: public void setSelection(int position, boolean animate) { start void ");
        // Animate only if requested position is already on screen somewhere
        boolean shouldAnimate = animate && mFirstPosition <= position &&
                position <= mFirstPosition + getChildCount() - 1;
        setSelectionInt(position, shouldAnimate);
		System.out.println("resp1onse AbsSpinner2: public void setSelection(int position, boolean animate) { end void ");
    }

    @Override
    public void setSelection(int position) {
		System.out.println("resp1onse AbsSpinner2: public void setSelection(int position) { start void ");
        setNextSelectedPositionInt(position);
        requestLayout();
        invalidate();
		System.out.println("resp1onse AbsSpinner2: public void setSelection(int position) { end void ");
    }
    

    /**
     * Makes the item at the supplied position selected.
     * 
     * @param position Position to select
     * @param animate Should the transition be animated
     * 
     */
    void setSelectionInt(int position, boolean animate) {
		System.out.println("resp1onse AbsSpinner2: void setSelectionInt(int position, boolean animate) { start void ");
        if (position != mOldSelectedPosition) {
            mBlockLayoutRequests = true;
            int delta  = position - mSelectedPosition;
            setNextSelectedPositionInt(position);
            layout(delta, animate);
            mBlockLayoutRequests = false;
        }
		System.out.println("resp1onse AbsSpinner2: void setSelectionInt(int position, boolean animate) { end void ");
    }

    abstract void layout(int delta, boolean animate);

    @Override
    public View getSelectedView() {
		System.out.println("resp1onse AbsSpinner2: public View getSelectedView() { start return ");
        if (mItemCount > 0 && mSelectedPosition >= 0) {
            return getChildAt(mSelectedPosition - mFirstPosition);
        } else {
            return null;
        }
    }
   
    /**
     * Override to prevent spamming ourselves with layout requests
     * as we place views
     * 
     * @see android.view.View#requestLayout()
     */
    @Override
    public void requestLayout() {
		System.out.println("resp1onse AbsSpinner2: public void requestLayout() { start void mBlockLayoutRequests = " + mBlockLayoutRequests);
        if (!mBlockLayoutRequests) {
            super.requestLayout();
        }
		System.out.println("resp1onse AbsSpinner2: public void requestLayout() { end void ");
    }

    @Override
    public SpinnerAdapter getAdapter() {
		System.out.println("resp1onse AbsSpinner2: public SpinnerAdapter getAdapter() { start return ");
		System.out.println("resp1onse AbsSpinner2: public SpinnerAdapter getAdapter() { end return ");
        return mAdapter;
    }

//    @Override
//    public int getCount() {
//		System.out.println("resp1onse AbsSpinner2: public int getCount() { start return ");
//		System.out.println("resp1onse AbsSpinner2: public int getCount() { end return ");
//        return mItemCount;
//    }

    /**
     * Maps a point to a position in the list.
     * 
     * @param x X in local coordinate
     * @param y Y in local coordinate
     * @return The position of the item which contains the specified point, or
     *         {@link #INVALID_POSITION} if the point does not intersect an item.
     */
    public int pointToPosition(int x, int y) {
		System.out.println("resp1onse AbsSpinner2: public int pointToPosition(int x, int y) { start return ");
        Rect frame = mTouchFrame;
        if (frame == null) {
            mTouchFrame = new Rect();
            frame = mTouchFrame;
        }

        final int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return mFirstPosition + i;
                }
            }
        } 
		System.out.println("resp1onse AbsSpinner2: public int pointToPosition(int x, int y) { end return  =1");
        return INVALID_POSITION;
    }
    
    static class SavedState extends BaseSavedState {
        long selectedId;
        int position;

        /**
         * Constructor called from {@link AbsSpinner2#onSaveInstanceState()}
         */
        SavedState(Parcelable superState) {
            super(superState);
        }
        
        /**
         * Constructor called from {@link #CREATOR}
         */
        SavedState(Parcel in) {
            super(in);
            selectedId = in.readLong();
            position = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
		System.out.println("resp1onse AbsSpinner2: public void writeToParcel(Parcel out, int flags) { start void ");
            super.writeToParcel(out, flags);
            out.writeLong(selectedId);
            out.writeInt(position);
		System.out.println("resp1onse AbsSpinner2: public void writeToParcel(Parcel out, int flags) { end void ");
        }

        @Override
        public String toString() {
		System.out.println("resp1onse AbsSpinner2: public String toString() { start return ");
		System.out.println("resp1onse AbsSpinner2: public String toString() { end return ");
            return "AbsSpinner.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " selectedId=" + selectedId
                    + " position=" + position + "}";
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
		System.out.println("resp1onse AbsSpinner2: public SavedState createFromParcel(Parcel in) { start return ");
		System.out.println("resp1onse AbsSpinner2: public SavedState createFromParcel(Parcel in) { end return ");
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
		System.out.println("resp1onse AbsSpinner2: public SavedState[] newArray(int size) { start return ");
		System.out.println("resp1onse AbsSpinner2: public SavedState[] newArray(int size) { end return ");
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
		System.out.println("resp1onse AbsSpinner2: public Parcelable onSaveInstanceState() { start return ");
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.selectedId = getSelectedItemId();
        if (ss.selectedId >= 0) {
            ss.position = getSelectedItemPosition();
        } else {
            ss.position = INVALID_POSITION;
        }
		System.out.println("resp1onse AbsSpinner2: public Parcelable onSaveInstanceState() { end return  =1");
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
		System.out.println("resp1onse AbsSpinner2: public void onRestoreInstanceState(Parcelable state) { start void ");
        SavedState ss = (SavedState) state;
  
        super.onRestoreInstanceState(ss.getSuperState());

        if (ss.selectedId >= 0) {
            mDataChanged = true;
            mNeedSync = true;
            mSyncRowId = ss.selectedId;
            mSyncPosition = ss.position;
            mSyncMode = SYNC_SELECTED_POSITION;
            requestLayout();
        }
		System.out.println("resp1onse AbsSpinner2: public void onRestoreInstanceState(Parcelable state) { end void ");
    }

    class RecycleBin {
        private final SparseArray<View> mScrapHeap = new SparseArray<View>();

        public void put(int position, View v) {
		System.out.println("resp1onse AbsSpinner2: public void put(int position, View v) { start void ");
            mScrapHeap.put(position, v);
            
            System.out.println("resp1onse AbsSpinner2: public void put(int position, View v) { end void mScrapHeap = " + mScrapHeap);
		System.out.println("resp1onse AbsSpinner2: public void put(int position, View v) { end void ");
        }
        
        View get(int position) {
        	System.out.println("resp1onse AbsSpinner2: View get(int " + position + ") { start mScrapHeap = " + mScrapHeap);
            // System.out.print("Looking for " + position);
            View result = mScrapHeap.get(position);
            if (result != null) {
                // System.out.println(" HIT");
                mScrapHeap.delete(position);
            } else {
                // System.out.println(" MISS");
            }
            System.out.println("resp1onse AbsSpinner2: View get(int " + position + ") { end mScrapHeap = " + mScrapHeap);
            return result;
        }

        void clear() {
		System.out.println("resp1onse AbsSpinner2: void clear() { start void ");
            final SparseArray<View> scrapHeap = mScrapHeap;
            final int count = scrapHeap.size();
            for (int i = 0; i < count; i++) {
                final View view = scrapHeap.valueAt(i);
                if (view != null) {
                    removeDetachedView(view, true);
                }
            }
            scrapHeap.clear();
		System.out.println("resp1onse AbsSpinner2: void clear() { end void ");
        }
    }

    @SuppressLint("NewApi")
	@Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("resp1onse AbsSpinner2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { start void ");
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(AbsSpinner2.class.getName());
		System.out.println("resp1onse AbsSpinner2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { end void ");
    }

    @SuppressLint("NewApi")
	@Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
		System.out.println("resp1onse AbsSpinner2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { start void ");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(AbsSpinner2.class.getName());
		System.out.println("resp1onse AbsSpinner2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { end void ");
    }
}
