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

package com.dzl.test.listview;

//import com.google.android.collect.Lists;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView.FixedViewInfo;
import android.widget.WrapperListAdapter;

import com.dzl.test.R;

/*
 * Implementation Notes:
 *
 * Some terminology:
 *
 *     index    - index of the items that are currently visible
 *     position - index of the items in the cursor
 */


/**
 * A view that shows items in a vertically scrolling list. The items
 * come from the {@link ListAdapter} associated with this view.
 *
 * <p>See the <a href="{@docRoot}resources/tutorials/views/hello-listview.html">List View
 * tutorial</a>.</p>
 *
 * @attr ref android.R.styleable#ListView_entries
 * @attr ref android.R.styleable#ListView_divider
 * @attr ref android.R.styleable#ListView_dividerHeight
 * @attr ref android.R.styleable#ListView_choiceMode
 * @attr ref android.R.styleable#ListView_headerDividersEnabled
 * @attr ref android.R.styleable#ListView_footerDividersEnabled
 */
@SuppressLint("NewApi")
public class ListView extends AbsListView {
    /**
     * Used to indicate a no preference for a position type.
     */
    static final int NO_POSITION = -1;

    /**
     * Normal list that does not indicate choices
     */
    public static final int CHOICE_MODE_NONE = 0;

    /**
     * The list allows up to one choice
     */
    public static final int CHOICE_MODE_SINGLE = 1;

    /**
     * The list allows multiple choices
     */
    public static final int CHOICE_MODE_MULTIPLE = 2;

    /**
     * When arrow scrolling, ListView will never scroll more than this factor
     * times the height of the list.
     */
    private static final float MAX_SCROLL_FACTOR = 0.33f;

    /**
     * When arrow scrolling, need a certain amount of pixels to preview next
     * items.  This is usually the fading edge, but if that is small enough,
     * we want to make sure we preview at least this many pixels.
     */
    private static final int MIN_SCROLL_PREVIEW_PIXELS = 2;

    static final int TOUCH_MODE_REST = -1;

    /**
     * Indicates we just received the touch event and we are waiting to see if the it is a tap or a
     * scroll gesture.
     */
    static final int TOUCH_MODE_DOWN = 0;

    /**
     * Indicates the touch has been recognized as a tap and we are now waiting to see if the touch
     * is a longpress
     */
    static final int TOUCH_MODE_TAP = 1;

    /**
     * Indicates we have waited for everything we can wait for, but the user's finger is still down
     */
    static final int TOUCH_MODE_DONE_WAITING = 2;

    /**
     * Indicates the touch gesture is a scroll
     */
    static final int TOUCH_MODE_SCROLL = 3;

    /**
     * Indicates the view is in the process of being flung
     */
    static final int TOUCH_MODE_FLING = 4;

    /**
     * Indicates the touch gesture is an overscroll - a scroll beyond the beginning or end.
     */
    static final int TOUCH_MODE_OVERSCROLL = 5;

    /**
     * Indicates the view is being flung outside of normal content bounds
     * and will spring back.
     */
    static final int TOUCH_MODE_OVERFLING = 6;

    
    /**
     * Regular layout - usually an unsolicited layout from the view system
     */
    static final int LAYOUT_NORMAL = 0;

    /**
     * Show the first item
     */
    static final int LAYOUT_FORCE_TOP = 1;

    /**
     * Force the selected item to be on somewhere on the screen
     */
    static final int LAYOUT_SET_SELECTION = 2;

    /**
     * Show the last item
     */
    static final int LAYOUT_FORCE_BOTTOM = 3;

    /**
     * Make a mSelectedItem appear in a specific location and build the rest of
     * the views from there. The top is specified by mSpecificTop.
     */
    static final int LAYOUT_SPECIFIC = 4;

    /**
     * Layout to sync as a result of a data change. Restore mSyncPosition to have its top
     * at mSpecificTop
     */
    static final int LAYOUT_SYNC = 5;

    /**
     * Layout as a result of using the navigation keys
     */
    static final int LAYOUT_MOVE_SELECTION = 6;

    
    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
//    public class FixedViewInfo {
//        /** The view to add to the list */
//        public View view;
//        /** The data backing the view. This is returned from {@link ListAdapter#getItem(int)}. */
//        public Object data;
//        /** <code>true</code> if the fixed view should be selectable in the list */
//        public boolean isSelectable;
//    }

    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<FixedViewInfo>();
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<FixedViewInfo>();

    Drawable mDivider;
    int mDividerHeight;
    
    Drawable mOverScrollHeader;
    Drawable mOverScrollFooter;

    private boolean mIsCacheColorOpaque;
    private boolean mDividerIsOpaque;
    private boolean mClipDivider;

    private boolean mHeaderDividersEnabled;
    private boolean mFooterDividersEnabled;

    private boolean mAreAllItemsSelectable = true;

    private boolean mItemsCanFocus = false;

    private int mChoiceMode = CHOICE_MODE_NONE;

    private SparseBooleanArray mCheckStates;
    private LongSparseArray<Boolean> mCheckedIdStates;

    // used for temporary calculations.
    private final Rect mTempRect = new Rect();
    private Paint mDividerPaint;

    // the single allocated result per list view; kinda cheesey but avoids
    // allocating these thingies too often.
    private final ArrowScrollFocusResult mArrowScrollFocusResult = new ArrowScrollFocusResult();

    // Keeps focused children visible through resizes
    private FocusSelector mFocusSelector;

	public ListAdapter mAdapter_MY;

	private boolean[] IsScrap_MY;

	private boolean mBlockLayoutRequests = false;

	
	//reflect 
	private Method method_offsetChildrenTopAndBottom;

	private Field field_OldItemCount;

	private Method method_setNextSelectedPositionInt;

	private Field field_SelectedTop;

	private Field field_SelectedPosition;

//	private Method method_setSelectedPositionInt;

	private Field field_FirstPosition;

	private Method method_reconcileSelectedPosition;

	private Method method_obtainView;

	private Field field_TouchMode;

	private Field field_MotionPosition;

	private Field field_NeedSync;

	private Field field_SpecificTop;

	private Field field_SyncPosition;

	private Field field_DataChanged;

	private Field field_InLayout;

	private Field field_SyncRowId;

	private Field field_CachingStarted;

	private Field field_WidthMeasureSpec;

	private Method method_shouldShowSelector;

	private Method method_rememberSyncState;

	private Method method_getDistance;

	private Method method_hideSelector;

	private Field field_ResurrectToPosition;

	private Method method_hasOpaqueScrollbars;

	private Method method_invokeOnItemScrollListener;

	private Method method_sendToTextFilter;

	private Method method_updateScrollIndicators;

	private Field field_SelectorRect;

	private Class<?> class_RecycleBin;

	private Field field_Recycler;

	private Method method_fillActiveViews;

	private Method method_scrapActiveViews;

	private Method method_getActiveView;

	private Method method_addScrapView;

	private Field field_viewType;

	private Method method_checkSelectionChanged;

	private Method method_checkFocus;

	private Method method_setViewTypeCount;

	private Method method_positionSelector;

	private Field field_SelectedRowId;

	private Field field_adapter;

	private Field field_ItemCount;

	private Field field_IsScrap;

	private Method method_getScrapView;

    public ListView(Context context) {
        this(context, null);
		System.out.println("resp1onse ListView: public ListView(Context context) { start constructor ");
		System.out.println("resp1onse ListView: public ListView(Context context) { end constructor ");
    }

    public ListView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.listViewStyle);
		System.out.println("resp1onse ListView: public ListView(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse ListView: public ListView(Context context, AttributeSet attrs) { end constructor ");
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		System.out.println("resp1onse ListView: public ListView(Context context, AttributeSet attrs, int defStyle) { start constructor ");

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ListView, defStyle, 0);

        
        final Drawable d = a.getDrawable(R.styleable.ListView_divider);
        if (d != null) {
            // If a divider is specified use its intrinsic height for divider height
            setDivider(d);
        }
        
        final Drawable osHeader = a.getDrawable(
                R.styleable.ListView_overScrollHeader);
        if (osHeader != null) {
            setOverscrollHeader(osHeader);
        }

        final Drawable osFooter = a.getDrawable(
                R.styleable.ListView_overScrollFooter);
        if (osFooter != null) {
            setOverscrollFooter(osFooter);
        }

        // Use the height specified, zero being the default
        final int dividerHeight = a.getDimensionPixelSize(
                R.styleable.ListView_dividerHeight, 0);
        if (dividerHeight != 0) {
            setDividerHeight(dividerHeight);
        }

        setChoiceMode(a.getInt(R.styleable.ListView_choiceMode, CHOICE_MODE_NONE));
        
        mHeaderDividersEnabled = a.getBoolean(R.styleable.ListView_headerDividersEnabled, true);
        mFooterDividersEnabled = a.getBoolean(R.styleable.ListView_footerDividersEnabled, true);

        setIsScrap();
        
        initRecycleBinMethod();
        
        a.recycle();
		System.out.println("resp1onse ListView: public ListView(Context context, AttributeSet attrs, int defStyle) { end constructor ");
    }


	/**
     * @return The maximum amount a list view will scroll in response to
     *   an arrow event.
     */
    public int getMaxScrollAmount() {
		System.out.println("resp1onse ListView: public int getMaxScrollAmount() { start return ");
		System.out.println("resp1onse ListView: public int getMaxScrollAmount() { end return ");
        return (int) (MAX_SCROLL_FACTOR * (getBottom() - getTop()));
    }

    /**
     * Make sure views are touching the top or bottom edge, as appropriate for
     * our gravity
     */
    private void adjustViewsUpOrDown() {
		System.out.println("resp1onse ListView: private void adjustViewsUpOrDown() { start void ");
        final int childCount = getChildCount();
        int delta;

        if (childCount > 0) {
            View child;

            if (!isStackFromBottom()) {
                // Uh-oh -- we came up short. Slide all views up to make them
                // align with the top
                child = getChildAt(0);
                delta = child.getTop() - getListPaddingTop();
                if (getFirstVisiblePosition() != 0) {
                    // It's OK to have some space above the first item if it is
                    // part of the vertical spacing
                    delta -= mDividerHeight;
                }
                if (delta < 0) {
                    // We only are looking to see if we are too low, not too high
                    delta = 0;
                }
            } else {
                // we are too high, slide all views down to align with bottom
                child = getChildAt(childCount - 1);
                delta = child.getBottom() - (getHeight() - getListPaddingBottom());

                if (getFirstVisiblePosition() + childCount < getCount()) {
                    // It's OK to have some space below the last item if it is
                    // part of the vertical spacing
                    delta += mDividerHeight;
                }

                if (delta > 0) {
                    delta = 0;
                }
            }

            if (delta != 0) {
                offsetChildrenTopAndBottom(-delta);
            }
        }
		System.out.println("resp1onse ListView: private void adjustViewsUpOrDown() { end void ");
    }

    

	/**
     * Add a fixed view to appear at the top of the list. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p>
     * NOTE: Call this before calling setAdapter. This is so ListView can wrap
     * the supplied cursor with one that will also account for header and footer
     * views.
     *
     * @param v The view to add.
     * @param data Data to associate with this view
     * @param isSelectable whether the item is selectable
     */
    public void addHeaderView(View v, Object data, boolean isSelectable) {
		System.out.println("resp1onse ListView: public void addHeaderView(View v, Object data, boolean isSelectable) { start void ");

//        if (mAdapter != null) {
//            throw new IllegalStateException(
//                    "Cannot add header view to list -- setAdapter has already been called.");
//        }
//
//        FixedViewInfo info = new FixedViewInfo();
//        info.view = v;
//        info.data = data;
//        info.isSelectable = isSelectable;
//        mHeaderViewInfos.add(info);
		System.out.println("resp1onse ListView: public void addHeaderView(View v, Object data, boolean isSelectable) { end void ");
    }

    /**
     * Add a fixed view to appear at the top of the list. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p>
     * NOTE: Call this before calling setAdapter. This is so ListView can wrap
     * the supplied cursor with one that will also account for header and footer
     * views.
     *
     * @param v The view to add.
     */
    public void addHeaderView(View v) {
		System.out.println("resp1onse ListView: public void addHeaderView(View v) { start void ");
        addHeaderView(v, null, true);
		System.out.println("resp1onse ListView: public void addHeaderView(View v) { end void ");
    }

//    @Override
    public int getHeaderViewsCount() {
//		System.out.println("resp1onse ListView: public int getHeaderViewsCount() { start return ");
//		System.out.println("resp1onse ListView: public int getHeaderViewsCount() { end return ");
        return mHeaderViewInfos.size();
    }

    /**
     * Removes a previously-added header view.
     *
     * @param v The view to remove
     * @return true if the view was removed, false if the view was not a header
     *         view
     */
    public boolean removeHeaderView(View v) {
        
		System.out.println("resp1onse ListView: public boolean removeHeaderView(View v) { start return ");
		System.out.println("resp1onse ListView: public boolean removeHeaderView(View v) { end return ");
        return false;
    }

    

    /**
     * Add a fixed view to appear at the bottom of the list. If addFooterView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p>
     * NOTE: Call this before calling setAdapter. This is so ListView can wrap
     * the supplied cursor with one that will also account for header and footer
     * views.
     *
     * @param v The view to add.
     * @param data Data to associate with this view
     * @param isSelectable true if the footer view can be selected
     */
    public void addFooterView(View v, Object data, boolean isSelectable) {
		System.out.println("resp1onse ListView: public void addFooterView(View v, Object data, boolean isSelectable) { start void ");
       
		System.out.println("resp1onse ListView: public void addFooterView(View v, Object data, boolean isSelectable) { end void ");
    }

    /**
     * Add a fixed view to appear at the bottom of the list. If addFooterView is called more
     * than once, the views will appear in the order they were added. Views added using
     * this call can take focus if they want.
     * <p>NOTE: Call this before calling setAdapter. This is so ListView can wrap the supplied
     * cursor with one that will also account for header and footer views.
     *
     *
     * @param v The view to add.
     */
    public void addFooterView(View v) {
		System.out.println("resp1onse ListView: public void addFooterView(View v) { start void ");
        addFooterView(v, null, true);
		System.out.println("resp1onse ListView: public void addFooterView(View v) { end void ");
    }

    //@Override
    public int getFooterViewsCount() {
//		System.out.println("resp1onse ListView: public int getFooterViewsCount() { start return ");
//		System.out.println("resp1onse ListView: public int getFooterViewsCount() { end return ");
        return mFooterViewInfos.size();
    }

    /**
     * Removes a previously-added footer view.
     *
     * @param v The view to remove
     * @return
     * true if the view was removed, false if the view was not a footer view
     */
    public boolean removeFooterView(View v) {
        
		System.out.println("resp1onse ListView: public boolean removeFooterView(View v) { start return ");
		System.out.println("resp1onse ListView: public boolean removeFooterView(View v) { end return ");
        return false;
    }

    /**
     * Returns the adapter currently in use in this ListView. The returned adapter
     * might not be the same adapter passed to {@link #setAdapter(ListAdapter)} but
     * might be a {@link WrapperListAdapter}.
     *
     * @return The adapter currently used to display data in this ListView.
     *
     * @see #setAdapter(ListAdapter)
     */
    @Override
    public ListAdapter getAdapter() {
		System.out.println("resp1onse ListView: public ListAdapter getAdapter() { start return ");
		System.out.println("resp1onse ListView: public ListAdapter getAdapter() { end return ");
        return mAdapter_MY;
    }

    /**
     * Sets the data behind this ListView.
     *
     * The adapter passed to this method may be wrapped by a {@link WrapperListAdapter},
     * depending on the ListView features currently in use. For instance, adding
     * headers and/or footers will cause the adapter to be wrapped.
     *
     * @param adapter The ListAdapter which is responsible for maintaining the
     *        data backing this list and for producing a view to represent an
     *        item in that data set.
     *
     * @see #getAdapter() 
     */
    @Override
    public void setAdapter(ListAdapter adapter) {
		System.out.println("resp1onse ListView: public void setAdapter(ListAdapter adapter) { start void ");
//        if (null != mAdapter) {
//            mAdapter.unregisterDataSetObserver(mDataSetObserver);
//        }

        resetList();
//        mRecycler.clear();

        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter_MY = new HeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter_MY = adapter;
            setAdapterSuper(adapter);
        }
        
        setItemCount(adapter.getCount());

//        mOldSelectedPosition = INVALID_POSITION;
//        mOldSelectedRowId = INVALID_ROW_ID;
        if (mAdapter_MY != null) {
            mAreAllItemsSelectable = mAdapter_MY.areAllItemsEnabled();
//            mOldItemCount = getCount();
            setOldItemCount(getCount());
//            mItemCount = mAdapter.getCount();
            checkFocus();

           DataSetObserver mDataSetObserver = getAdapterDataSetObserver();
            mAdapter_MY.registerDataSetObserver(mDataSetObserver);

            recycler_setViewTypeCount(mAdapter_MY.getViewTypeCount());

            int position;
            if (isStackFromBottom()) {
                position = lookForSelectablePosition(getCount() - 1, false);
            } else {
                position = lookForSelectablePosition(0, true);
            }
            setSelectedPositionInt(position);
            setNextSelectedPositionInt(position);

            if (getCount() == 0) {
                // Nothing selected
                checkSelectionChanged();
            }

            if (mChoiceMode != CHOICE_MODE_NONE &&
                    mAdapter_MY.hasStableIds() &&
                    mCheckedIdStates == null) {
                mCheckedIdStates = new LongSparseArray<Boolean>();
            }

        } else {
            mAreAllItemsSelectable = true;
            checkFocus();
            // Nothing selected
            checkSelectionChanged();
        }

        if (mCheckStates != null) {
            mCheckStates.clear();
        }
        
        if (mCheckedIdStates != null) {
            mCheckedIdStates.clear();
        }

        requestLayout();
		System.out.println("resp1onse ListView: public void setAdapter(ListAdapter adapter) { end void ");
    }

	private void setItemCount(int count) {
		System.out.println("resp1onse ListView: private void setItemCount(int count) { start void ");
		try {
			if (field_ItemCount == null) {
				field_ItemCount = AdapterView.class.getDeclaredField("mItemCount");
				field_ItemCount.setAccessible(true);
			}
			field_ItemCount.set(this, count);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setItemCount(int count) { end void ");
	}

	private void setAdapterSuper(ListAdapter adapter) {
		System.out.println("resp1onse ListView: private void setAdapterSuper(ListAdapter adapter) { start void ");
		try {
			if (field_adapter == null) {
				field_adapter = AbsListView.class.getDeclaredField("mAdapter");
				field_adapter.setAccessible(true);
			}
			field_adapter.set(this, adapter);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setAdapterSuper(ListAdapter adapter) { end void ");
	}

	/**
     * The list is empty. Clear everything out.
     */
@SuppressLint("NewApi")
//    @ Ove rride
    void resetList() {
		System.out.println("resp1onse ListView: void resetList() { start void ");
        // The parent's resetList() will remove all views from the layout so we need to
        // cleanup the state of our footers and headers
        clearRecycledState(mHeaderViewInfos);
        clearRecycledState(mFooterViewInfos);

//        super.resetList();
//        mLayoutMode = LAYOUT_NORMAL;
        setLayoutMode(LAYOUT_NORMAL);
		System.out.println("resp1onse ListView: void resetList() { end void ");
    }

    private void clearRecycledState(ArrayList<FixedViewInfo> infos) {
		System.out.println("resp1onse ListView: private void clearRecycledState(ArrayList<FixedViewInfo> infos) { start void ");
        if (infos != null) {
            final int count = infos.size();

            for (int i = 0; i < count; i++) {
                final View child = infos.get(i).view;
                final LayoutParams p = (LayoutParams) child.getLayoutParams();
                if (p != null) {
//                    p.recycledHeaderFooter = false;
                }
            }
        }
		System.out.println("resp1onse ListView: private void clearRecycledState(ArrayList<FixedViewInfo> infos) { end void ");
    }

    /**
     * @return Whether the list needs to show the top fading edge
     */
    private boolean showingTopFadingEdge() {
		System.out.println("resp1onse ListView: private boolean showingTopFadingEdge() { start return ");
        final int listTop = getScrollY() + getListPaddingTop();
		System.out.println("resp1onse ListView: private boolean showingTopFadingEdge() { end return  =1");
        return (getFirstVisiblePosition() > 0) || (getChildAt(0).getTop() > listTop);
    }

    /**
     * @return Whether the list needs to show the bottom fading edge
     */
    private boolean showingBottomFadingEdge() {
		System.out.println("resp1onse ListView: private boolean showingBottomFadingEdge() { start return ");
        final int childCount = getChildCount();
        final int bottomOfBottomChild = getChildAt(childCount - 1).getBottom();
        final int lastVisiblePosition = getFirstVisiblePosition() + childCount - 1;

        final int listBottom = getScrollY() + getHeight() - getListPaddingBottom();

		System.out.println("resp1onse ListView: private boolean showingBottomFadingEdge() { end return  =1");
        return (lastVisiblePosition < getCount() - 1)
                         || (bottomOfBottomChild < listBottom);
    }


    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {

		System.out.println("resp1onse ListView: public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) { start return ");
        int rectTopWithinChild = rect.top;

        // offset so rect is in coordinates of the this view
        rect.offset(child.getLeft(), child.getTop());
        rect.offset(-child.getScrollX(), -child.getScrollY());

        final int height = getHeight();
        int listUnfadedTop = getScrollY();
        int listUnfadedBottom = listUnfadedTop + height;
        final int fadingEdge = getVerticalFadingEdgeLength();

        if (showingTopFadingEdge()) {
            // leave room for top fading edge as long as rect isn't at very top
        	
            if ((getSelectedPosition() > 0) || (rectTopWithinChild > fadingEdge)) {
                listUnfadedTop += fadingEdge;
            }
        }

        int childCount = getChildCount();
        int bottomOfBottomChild = getChildAt(childCount - 1).getBottom();

        if (showingBottomFadingEdge()) {
            // leave room for bottom fading edge as long as rect isn't at very bottom
            if ((getSelectedPosition() < getCount() - 1)
                    || (rect.bottom < (bottomOfBottomChild - fadingEdge))) {
                listUnfadedBottom -= fadingEdge;
            }
        }

        int scrollYDelta = 0;

        if (rect.bottom > listUnfadedBottom && rect.top > listUnfadedTop) {
            // need to MOVE DOWN to get it in view: move down just enough so
            // that the entire rectangle is in view (or at least the first
            // screen size chunk).

            if (rect.height() > height) {
                // just enough to get screen size chunk on
                scrollYDelta += (rect.top - listUnfadedTop);
            } else {
                // get entire rect at bottom of screen
                scrollYDelta += (rect.bottom - listUnfadedBottom);
            }

            // make sure we aren't scrolling beyond the end of our children
            int distanceToBottom = bottomOfBottomChild - listUnfadedBottom;
            scrollYDelta = Math.min(scrollYDelta, distanceToBottom);
        } else if (rect.top < listUnfadedTop && rect.bottom < listUnfadedBottom) {
            // need to MOVE UP to get it in view: move up just enough so that
            // entire rectangle is in view (or at least the first screen
            // size chunk of it).

            if (rect.height() > height) {
                // screen size chunk
                scrollYDelta -= (listUnfadedBottom - rect.bottom);
            } else {
                // entire rect at top
                scrollYDelta -= (listUnfadedTop - rect.top);
            }

            // make sure we aren't scrolling any further than the top our children
            int top = getChildAt(0).getTop();
            int deltaToTop = top - listUnfadedTop;
            scrollYDelta = Math.max(scrollYDelta, deltaToTop);
        }

        final boolean scroll = scrollYDelta != 0;
        if (scroll) {
            scrollListItemsBy(-scrollYDelta);
            positionSelector(INVALID_POSITION, child);
            setSelectedTop(child.getTop());
            
            invalidate();
        }
		System.out.println("resp1onse ListView: public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) { end return  =1");
        return scroll;
    }

	/**
     * {@inheritDoc}
     */
//    @Override
    public void fillGap(boolean down) {
		System.out.println("resp1onse ListView: public void fillGap(boolean down) { start void ");
        final int count = getChildCount();
        if (down) {
            final int startOffset = count > 0 ? getChildAt(count - 1).getBottom() + mDividerHeight :
                    getListPaddingTop();
            fillDown(getFirstVisiblePosition() + count, startOffset);
            correctTooHigh(getChildCount());
        } else {
            final int startOffset = count > 0 ? getChildAt(0).getTop() - mDividerHeight :
                    getHeight() - getListPaddingBottom();
            fillUp(getFirstVisiblePosition() - 1, startOffset);
            correctTooLow(getChildCount());
        }
		System.out.println("resp1onse ListView: public void fillGap(boolean down) { end void ");
    }

    /**
     * Fills the list from pos down to the end of the list view.
     *
     * @param pos The first position to put in the list
     *
     * @param nextTop The location where the top of the item associated with pos
     *        should be drawn
     *
     * @return The view that is currently selected, if it happens to be in the
     *         range that we draw.
     */
    private View fillDown(int pos, int nextTop) {
		System.out.println("resp1onse ListView: private View fillDown(int pos, int nextTop) { start return ");
        View selectedView = null;

        int end = (getBottom() - getTop()) - getListPaddingBottom();

        while (nextTop < end && pos < getCount()) {
            // is this the selected item?
            boolean selected = pos == getSelectedPosition();
            View child = makeAndAddView(pos, nextTop, true, getListPaddingLeft(), selected);

            nextTop = child.getBottom() + mDividerHeight;
            if (selected) {
                selectedView = child;
            }
            pos++;
        }

		System.out.println("resp1onse ListView: private View fillDown(int pos, int nextTop) { end return  =1");
        return selectedView;
    }

    /**
     * Fills the list from pos up to the top of the list view.
     *
     * @param pos The first position to put in the list
     *
     * @param nextBottom The location where the bottom of the item associated
     *        with pos should be drawn
     *
     * @return The view that is currently selected
     */
    private View fillUp(int pos, int nextBottom) {
		System.out.println("resp1onse ListView: private View fillUp(int pos, int nextBottom) { start return ");
        View selectedView = null;

        int end = getListPaddingTop();

        while (nextBottom > end && pos >= 0) {
            // is this the selected item?
            boolean selected = pos == getSelectedPosition();
            View child = makeAndAddView(pos, nextBottom, false, getListPaddingLeft(), selected);
            nextBottom = child.getTop() - mDividerHeight;
            if (selected) {
                selectedView = child;
            }
            pos--;
        }

        setFirstPosition(pos + 1);

		System.out.println("resp1onse ListView: private View fillUp(int pos, int nextBottom) { end return  =1");
        return selectedView;
    }

	

    /**
     * Fills the list from top to bottom, starting with mFirstPosition
     *
     * @param nextTop The location where the top of the first item should be
     *        drawn
     *
     * @return The view that is currently selected
     */
    private View fillFromTop(int nextTop) {
		System.out.println("resp1onse ListView: private View fillFromTop(int nextTop) { start return ");
    	int mFirstPosition = getFirstVisiblePosition();
        mFirstPosition = Math.min(mFirstPosition, getSelectedPosition());
        mFirstPosition = Math.min(mFirstPosition, getCount() - 1);
        if (mFirstPosition < 0) {
            mFirstPosition = 0;
        }
        setFirstPosition(mFirstPosition);
		System.out.println("resp1onse ListView: private View fillFromTop(int nextTop) { end return  =1");
        return fillDown(mFirstPosition, nextTop);
    }


    /**
     * Put getSelectedPosition() in the middle of the screen and then build up and
     * down from there. This method forces getSelectedPosition() to the center.
     *
     * @param childrenTop Top of the area in which children can be drawn, as
     *        measured in pixels
     * @param childrenBottom Bottom of the area in which children can be drawn,
     *        as measured in pixels
     * @return Currently selected view
     */
    private View fillFromMiddle(int childrenTop, int childrenBottom) {
		System.out.println("resp1onse ListView: private View fillFromMiddle(int childrenTop, int childrenBottom) { start return ");
        int height = childrenBottom - childrenTop;

        int position = reconcileSelectedPosition();

        View sel = makeAndAddView(position, childrenTop, true,
                getListPaddingLeft(), true);
//        mFirstPosition = position;

        setFirstPosition(position);
        int selHeight = sel.getMeasuredHeight();
        if (selHeight <= height) {
            sel.offsetTopAndBottom((height - selHeight) / 2);
        }

        fillAboveAndBelow(sel, position);

        if (!isStackFromBottom()) {
            correctTooHigh(getChildCount());
        } else {
            correctTooLow(getChildCount());
        }

		System.out.println("resp1onse ListView: private View fillFromMiddle(int childrenTop, int childrenBottom) { end return  =1");
        return sel;
    }


	/**
     * Once the selected view as been placed, fill up the visible area above and
     * below it.
     *
     * @param sel The selected view
     * @param position The position corresponding to sel
     */
    private void fillAboveAndBelow(View sel, int position) {
		System.out.println("resp1onse ListView: private void fillAboveAndBelow(View sel, int position) { start void ");
        final int dividerHeight = mDividerHeight;
        if (!isStackFromBottom()) {
            fillUp(position - 1, sel.getTop() - dividerHeight);
            adjustViewsUpOrDown();
            fillDown(position + 1, sel.getBottom() + dividerHeight);
        } else {
            fillDown(position + 1, sel.getBottom() + dividerHeight);
            adjustViewsUpOrDown();
            fillUp(position - 1, sel.getTop() - dividerHeight);
        }
		System.out.println("resp1onse ListView: private void fillAboveAndBelow(View sel, int position) { end void ");
    }


    /**
     * Fills the grid based on positioning the new selection at a specific
     * location. The selection may be moved so that it does not intersect the
     * faded edges. The grid is then filled upwards and downwards from there.
     *
     * @param selectedTop Where the selected item should be
     * @param childrenTop Where to start drawing children
     * @param childrenBottom Last pixel where children can be drawn
     * @return The view that currently has selection
     */
    private View fillFromSelection(int selectedTop, int childrenTop, int childrenBottom) {
		System.out.println("resp1onse ListView: private View fillFromSelection(int selectedTop, int childrenTop, int childrenBottom) { start return ");
        int fadingEdgeLength = getVerticalFadingEdgeLength();
        final int selectedPosition = getSelectedPosition();

        View sel;

        final int topSelectionPixel = getTopSelectionPixel(childrenTop, fadingEdgeLength,
                selectedPosition);
        final int bottomSelectionPixel = getBottomSelectionPixel(childrenBottom, fadingEdgeLength,
                selectedPosition);

        sel = makeAndAddView(selectedPosition, selectedTop, true, getListPaddingLeft(), true);


        // Some of the newly selected item extends below the bottom of the list
        if (sel.getBottom() > bottomSelectionPixel) {
            // Find space available above the selection into which we can scroll
            // upwards
            final int spaceAbove = sel.getTop() - topSelectionPixel;

            // Find space required to bring the bottom of the selected item
            // fully into view
            final int spaceBelow = sel.getBottom() - bottomSelectionPixel;
            final int offset = Math.min(spaceAbove, spaceBelow);

            // Now offset the selected item to get it into view
            sel.offsetTopAndBottom(-offset);
        } else if (sel.getTop() < topSelectionPixel) {
            // Find space required to bring the top of the selected item fully
            // into view
            final int spaceAbove = topSelectionPixel - sel.getTop();

            // Find space available below the selection into which we can scroll
            // downwards
            final int spaceBelow = bottomSelectionPixel - sel.getBottom();
            final int offset = Math.min(spaceAbove, spaceBelow);

            // Offset the selected item to get it into view
            sel.offsetTopAndBottom(offset);
        }

        // Fill in views above and below
        fillAboveAndBelow(sel, selectedPosition);

        if (!isStackFromBottom()) {
            correctTooHigh(getChildCount());
        } else {
            correctTooLow(getChildCount());
        }

		System.out.println("resp1onse ListView: private View fillFromSelection(int selectedTop, int childrenTop, int childrenBottom) { end return  =1");
        return sel;
    }

    /**
     * Calculate the bottom-most pixel we can draw the selection into
     *
     * @param childrenBottom Bottom pixel were children can be drawn
     * @param fadingEdgeLength Length of the fading edge in pixels, if present
     * @param selectedPosition The position that will be selected
     * @return The bottom-most pixel we can draw the selection into
     */
    private int getBottomSelectionPixel(int childrenBottom, int fadingEdgeLength,
            int selectedPosition) {
        int bottomSelectionPixel = childrenBottom;
        if (selectedPosition != getCount() - 1) {
            bottomSelectionPixel -= fadingEdgeLength;
        }
        return bottomSelectionPixel;
    }

    /**
     * Calculate the top-most pixel we can draw the selection into
     *
     * @param childrenTop Top pixel were children can be drawn
     * @param fadingEdgeLength Length of the fading edge in pixels, if present
     * @param selectedPosition The position that will be selected
     * @return The top-most pixel we can draw the selection into
     */
    private int getTopSelectionPixel(int childrenTop, int fadingEdgeLength, int selectedPosition) {
        // first pixel we can draw the selection into
		System.out.println("resp1onse ListView: private int getTopSelectionPixel(int childrenTop, int fadingEdgeLength, int selectedPosition) { start return ");
        int topSelectionPixel = childrenTop;
        if (selectedPosition > 0) {
            topSelectionPixel += fadingEdgeLength;
        }
		System.out.println("resp1onse ListView: private int getTopSelectionPixel(int childrenTop, int fadingEdgeLength, int selectedPosition) { end return  =1");
        return topSelectionPixel;
    }


    /**
     * Fills the list based on positioning the new selection relative to the old
     * selection. The new selection will be placed at, above, or below the
     * location of the new selection depending on how the selection is moving.
     * The selection will then be pinned to the visible part of the screen,
     * excluding the edges that are faded. The list is then filled upwards and
     * downwards from there.
     *
     * @param oldSel The old selected view. Useful for trying to put the new
     *        selection in the same place
     * @param newSel The view that is to become selected. Useful for trying to
     *        put the new selection in the same place
     * @param delta Which way we are moving
     * @param childrenTop Where to start drawing children
     * @param childrenBottom Last pixel where children can be drawn
     * @return The view that currently has selection
     */
    private View moveSelection(View oldSel, View newSel, int delta, int childrenTop,
            int childrenBottom) {
        int fadingEdgeLength = getVerticalFadingEdgeLength();
        final int selectedPosition = getSelectedPosition();

        View sel;

        final int topSelectionPixel = getTopSelectionPixel(childrenTop, fadingEdgeLength,
                selectedPosition);
        final int bottomSelectionPixel = getBottomSelectionPixel(childrenTop, fadingEdgeLength,
                selectedPosition);

        if (delta > 0) {
            /*
             * Case 1: Scrolling down.
             */

            /*
             *     Before           After
             *    |       |        |       |
             *    +-------+        +-------+
             *    |   A   |        |   A   |
             *    |   1   |   =>   +-------+
             *    +-------+        |   B   |
             *    |   B   |        |   2   |
             *    +-------+        +-------+
             *    |       |        |       |
             *
             *    Try to keep the top of the previously selected item where it was.
             *    oldSel = A
             *    sel = B
             */

            // Put oldSel (A) where it belongs
            oldSel = makeAndAddView(selectedPosition - 1, oldSel.getTop(), true,
                    getListPaddingLeft(), false);

            final int dividerHeight = mDividerHeight;

            // Now put the new selection (B) below that
            sel = makeAndAddView(selectedPosition, oldSel.getBottom() + dividerHeight, true,
                    getListPaddingLeft(), true);

            // Some of the newly selected item extends below the bottom of the list
            if (sel.getBottom() > bottomSelectionPixel) {

                // Find space available above the selection into which we can scroll upwards
                int spaceAbove = sel.getTop() - topSelectionPixel;

                // Find space required to bring the bottom of the selected item fully into view
                int spaceBelow = sel.getBottom() - bottomSelectionPixel;

                // Don't scroll more than half the height of the list
                int halfVerticalSpace = (childrenBottom - childrenTop) / 2;
                int offset = Math.min(spaceAbove, spaceBelow);
                offset = Math.min(offset, halfVerticalSpace);

                // We placed oldSel, so offset that item
                oldSel.offsetTopAndBottom(-offset);
                // Now offset the selected item to get it into view
                sel.offsetTopAndBottom(-offset);
            }

            // Fill in views above and below
            if (!isStackFromBottom()) {
                fillUp(getSelectedPosition() - 2, sel.getTop() - dividerHeight);
                adjustViewsUpOrDown();
                fillDown(getSelectedPosition() + 1, sel.getBottom() + dividerHeight);
            } else {
                fillDown(getSelectedPosition() + 1, sel.getBottom() + dividerHeight);
                adjustViewsUpOrDown();
                fillUp(getSelectedPosition() - 2, sel.getTop() - dividerHeight);
            }
        } else if (delta < 0) {
            /*
             * Case 2: Scrolling up.
             */

            /*
             *     Before           After
             *    |       |        |       |
             *    +-------+        +-------+
             *    |   A   |        |   A   |
             *    +-------+   =>   |   1   |
             *    |   B   |        +-------+
             *    |   2   |        |   B   |
             *    +-------+        +-------+
             *    |       |        |       |
             *
             *    Try to keep the top of the item about to become selected where it was.
             *    newSel = A
             *    olSel = B
             */

            if (newSel != null) {
                // Try to position the top of newSel (A) where it was before it was selected
                sel = makeAndAddView(selectedPosition, newSel.getTop(), true, getListPaddingLeft(),
                        true);
            } else {
                // If (A) was not on screen and so did not have a view, position
                // it above the oldSel (B)
                sel = makeAndAddView(selectedPosition, oldSel.getTop(), false, getListPaddingLeft(),
                        true);
            }

            // Some of the newly selected item extends above the top of the list
            if (sel.getTop() < topSelectionPixel) {
                // Find space required to bring the top of the selected item fully into view
                int spaceAbove = topSelectionPixel - sel.getTop();

               // Find space available below the selection into which we can scroll downwards
                int spaceBelow = bottomSelectionPixel - sel.getBottom();

                // Don't scroll more than half the height of the list
                int halfVerticalSpace = (childrenBottom - childrenTop) / 2;
                int offset = Math.min(spaceAbove, spaceBelow);
                offset = Math.min(offset, halfVerticalSpace);

                // Offset the selected item to get it into view
                sel.offsetTopAndBottom(offset);
            }

            // Fill in views above and below
            fillAboveAndBelow(sel, selectedPosition);
        } else {

            int oldTop = oldSel.getTop();

            /*
             * Case 3: Staying still
             */
            sel = makeAndAddView(selectedPosition, oldTop, true, getListPaddingLeft(), true);

            // We're staying still...
            if (oldTop < childrenTop) {
                // ... but the top of the old selection was off screen.
                // (This can happen if the data changes size out from under us)
                int newBottom = sel.getBottom();
                if (newBottom < childrenTop + 20) {
                    // Not enough visible -- bring it onscreen
                    sel.offsetTopAndBottom(childrenTop - sel.getTop());
                }
            }

            // Fill in views above and below
            fillAboveAndBelow(sel, selectedPosition);
        }

        return sel;
    }

    private class FocusSelector implements Runnable {
        private int mPosition;
        private int mPositionTop;
        
        public FocusSelector setup(int position, int top) {
		System.out.println("resp1onse ListView: public FocusSelector setup(int position, int top) { start return ");
            mPosition = position;
            mPositionTop = top;
		System.out.println("resp1onse ListView: public FocusSelector setup(int position, int top) { end return  =1");
            return this;
        }
        
        @SuppressLint("NewApi")
		public void run() {
		System.out.println("resp1onse ListView: public void run() { start void ");
            setSelectionFromTop(mPosition, mPositionTop);
		System.out.println("resp1onse ListView: public void run() { end void ");
        }
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		System.out.println("resp1onse ListView: protected void onSizeChanged(int w, int h, int oldw, int oldh) { start void ");
        if (getChildCount() > 0) {
            View focusedChild = getFocusedChild();
            if (focusedChild != null) {
                final int childPosition = getFirstVisiblePosition() + indexOfChild(focusedChild);
                final int childBottom = focusedChild.getBottom();
                final int offset = Math.max(0, childBottom - (h - getPaddingTop()));
                final int top = focusedChild.getTop() - offset;
                if (mFocusSelector == null) {
                    mFocusSelector = new FocusSelector();
                }
                post(mFocusSelector.setup(childPosition, top));
            }
        }
        super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("resp1onse ListView: protected void onSizeChanged(int w, int h, int oldw, int oldh) { end void ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse ListView: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void ");
        // Sets up mListPadding
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childWidth = 0;
        int childHeight = 0;

//        mItemCount = mAdapter == null ? 0 : mAdapter.getCount();
        if (getCount() > 0 && (widthMode == MeasureSpec.UNSPECIFIED ||
                heightMode == MeasureSpec.UNSPECIFIED)) {
            final View child = obtainView2(0, IsScrap_MY);

            measureScrapChild(child, 0, widthMeasureSpec);

            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();

            if (recycleOnMeasure() && recycler_shouldRecycleViewType((LayoutParams) child.getLayoutParams())) {
                recycler_addScrapView(child, 0);
            }
        }

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            widthSize = getListPaddingLeft() + getListPaddingRight() + childWidth +
                    getVerticalScrollbarWidth();
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = getListPaddingTop() + getListPaddingBottom() + childHeight +
                    getVerticalFadingEdgeLength() * 2;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            // TODO: after first layout we should maybe start at the first visible position, not 0
            heightSize = measureHeightOfChildren(widthMeasureSpec, 0, NO_POSITION, heightSize, -1);
        }

        setMeasuredDimension(widthSize, heightSize);
//        mWidthMeasureSpec = widthMeasureSpec;  
        setWidthMeasureSpec(widthMeasureSpec);
		System.out.println("resp1onse ListView: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { end void ");
    }


	private void measureScrapChild(View child, int position, int widthMeasureSpec) {
		System.out.println("resp1onse ListView: private void measureScrapChild(View child, int position, int widthMeasureSpec) { start void ");
        LayoutParams p = (LayoutParams) child.getLayoutParams();
        if (p == null) {
            p = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0);
            child.setLayoutParams(p);
        }
//        p.viewType = mAdapter.getItemViewType(position);
//        p.forceAdd = true;

        int childWidthSpec = ViewGroup.getChildMeasureSpec(widthMeasureSpec,
                getListPaddingLeft() + getListPaddingRight(), p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
		System.out.println("resp1onse ListView: private void measureScrapChild(View child, int position, int widthMeasureSpec) { end void ");
    }

    /**
     * @return True to recycle the views used to measure this ListView in
     *         UNSPECIFIED/AT_MOST modes, false otherwise.
     * @hide
     */
    @ViewDebug.ExportedProperty(category = "list")
    protected boolean recycleOnMeasure() {
		System.out.println("resp1onse ListView: protected boolean recycleOnMeasure() { start return ");
		System.out.println("resp1onse ListView: protected boolean recycleOnMeasure() { end return ");
        return true;
    }

    /**
     * Measures the height of the given range of children (inclusive) and
     * returns the height with this ListView's padding and divider heights
     * included. If maxHeight is provided, the measuring will stop when the
     * current height reaches maxHeight.
     *
     * @param widthMeasureSpec The width measure spec to be given to a child's
     *            {@link View#measure(int, int)}.
     * @param startPosition The position of the first child to be shown.
     * @param endPosition The (inclusive) position of the last child to be
     *            shown. Specify {@link #NO_POSITION} if the last child should be
     *            the last available child from the adapter.
     * @param maxHeight The maximum height that will be returned (if all the
     *            children don't fit in this value, this value will be
     *            returned).
     * @param disallowPartialChildPosition In general, whether the returned
     *            height should only contain entire children. This is more
     *            powerful--it is the first inclusive position at which partial
     *            children will not be allowed. Example: it looks nice to have
     *            at least 3 completely visible children, and in portrait this
     *            will most likely fit; but in landscape there could be times
     *            when even 2 children can not be completely shown, so a value
     *            of 2 (remember, inclusive) would be good (assuming
     *            startPosition is 0).
     * @return The height of this ListView with the given children.
     */
    final int measureHeightOfChildren(int widthMeasureSpec, int startPosition, int endPosition,
            final int maxHeight, int disallowPartialChildPosition) {

        final ListAdapter adapter = mAdapter_MY;
        if (adapter == null) {
            return getListPaddingTop() + getListPaddingBottom();
        }

        // Include the padding of the list
        int returnedHeight = getListPaddingTop() + getListPaddingBottom();
        final int dividerHeight = ((mDividerHeight > 0) && mDivider != null) ? mDividerHeight : 0;
        // The previous height value that was less than maxHeight and contained
        // no partial children
        int prevHeightWithoutPartialChild = 0;
        int i;
        View child;

        // mItemCount - 1 since endPosition parameter is inclusive
        endPosition = (endPosition == NO_POSITION) ? adapter.getCount() - 1 : endPosition;
//        final AbsListView.RecycleBin recycleBin = mRecycler;
        final Object recycleBin = null;
        final boolean recyle = recycleOnMeasure();
        final boolean[] isScrap = IsScrap_MY;

        for (i = startPosition; i <= endPosition; ++i) {
            child = obtainView2(i, isScrap);

            measureScrapChild(child, i, widthMeasureSpec);

            if (i > 0) {
                // Count the divider for all but one child
                returnedHeight += dividerHeight;
            }

            // Recycle the view before we possibly return from the method
            if (recyle && recycler_shouldRecycleViewType((LayoutParams) child.getLayoutParams())) {
//                recycleBin.addScrapView(child);
            	recycler_addScrapView(child, -1);
                
            }

            returnedHeight += child.getMeasuredHeight();

            if (returnedHeight >= maxHeight) {
                // We went over, figure out which height to return.  If returnedHeight > maxHeight,
                // then the i'th position did not fit completely.
                return (disallowPartialChildPosition >= 0) // Disallowing is enabled (> -1)
                            && (i > disallowPartialChildPosition) // We've past the min pos
                            && (prevHeightWithoutPartialChild > 0) // We have a prev height
                            && (returnedHeight != maxHeight) // i'th child did not fit completely
                        ? prevHeightWithoutPartialChild
                        : maxHeight;
            }

            if ((disallowPartialChildPosition >= 0) && (i >= disallowPartialChildPosition)) {
                prevHeightWithoutPartialChild = returnedHeight;
            }
        }

        // At this point, we went through the range of children, and they each
        // completely fit, so return the returnedHeight
        return returnedHeight;
    }
    

//	@Override
    public int findMotionRow(int y) {
		System.out.println("resp1onse ListView: public int findMotionRow(int y) { start return ");
        int childCount = getChildCount();
        if (childCount > 0) {
            if (!isStackFromBottom()) {
                for (int i = 0; i < childCount; i++) {
                    View v = getChildAt(i);
                    if (y <= v.getBottom()) {
                        return getFirstVisiblePosition() + i;
                    }
                }
            } else {
                for (int i = childCount - 1; i >= 0; i--) {
                    View v = getChildAt(i);
                    if (y >= v.getTop()) {
                        return getFirstVisiblePosition() + i;
                    }
                }
            }
        }
		System.out.println("resp1onse ListView: public int findMotionRow(int y) { end return  =1");
        return INVALID_POSITION;
    }

    /**
     * Put a specific item at a specific location on the screen and then build
     * up and down from there.
     *
     * @param position The reference view to use as the starting point
     * @param top Pixel offset from the top of this view to the top of the
     *        reference view.
     *
     * @return The selected view, or null if the selected view is outside the
     *         visible area.
     */
    private View fillSpecific(int position, int top) {
		System.out.println("resp1onse ListView: private View fillSpecific(int position, int top) { start return ");
        boolean tempIsSelected = position == getSelectedPosition();
        View temp = makeAndAddView(position, top, true, getListPaddingLeft(), tempIsSelected);
        // Possibly changed again in fillUp if we add rows above this one.
//        mFirstPosition = position;

        setFirstPosition(position);
        View above;
        View below;

        final int dividerHeight = mDividerHeight;
        if (!isStackFromBottom()) {
            above = fillUp(position - 1, temp.getTop() - dividerHeight);
            // This will correct for the top of the first view not touching the top of the list
            adjustViewsUpOrDown();
            below = fillDown(position + 1, temp.getBottom() + dividerHeight);
            int childCount = getChildCount();
            if (childCount > 0) {
                correctTooHigh(childCount);
            }
        } else {
            below = fillDown(position + 1, temp.getBottom() + dividerHeight);
            // This will correct for the bottom of the last view not touching the bottom of the list
            adjustViewsUpOrDown();
            above = fillUp(position - 1, temp.getTop() - dividerHeight);
            int childCount = getChildCount();
            if (childCount > 0) {
                 correctTooLow(childCount);
            }
        }

        if (tempIsSelected) {
            return temp;
        } else if (above != null) {
            return above;
        } else {
            return below;
        }
    }

    /**
     * Check if we have dragged the bottom of the list too high (we have pushed the
     * top element off the top of the screen when we did not need to). Correct by sliding
     * everything back down.
     *
     * @param childCount Number of children
     */
    private void correctTooHigh(int childCount) {
		System.out.println("resp1onse ListView: private void correctTooHigh(int childCount) { start void ");
        // First see if the last item is visible. If it is not, it is OK for the
        // top of the list to be pushed up.
        int lastPosition = getFirstVisiblePosition() + childCount - 1;
        if (lastPosition == getCount() - 1 && childCount > 0) {

            // Get the last child ...
            final View lastChild = getChildAt(childCount - 1);

            // ... and its bottom edge
            final int lastBottom = lastChild.getBottom();

            // This is bottom of our drawable area
            final int end = (getBottom() - getTop()) - getListPaddingBottom();

            // This is how far the bottom edge of the last view is from the bottom of the
            // drawable area
            int bottomOffset = end - lastBottom;
            View firstChild = getChildAt(0);
            final int firstTop = firstChild.getTop();

            // Make sure we are 1) Too high, and 2) Either there are more rows above the
            // first row or the first row is scrolled off the top of the drawable area
            if (bottomOffset > 0 && (getFirstVisiblePosition() > 0 || firstTop < getListPaddingTop()))  {
                if (getFirstVisiblePosition() == 0) {
                    // Don't pull the top too far down
                    bottomOffset = Math.min(bottomOffset, getListPaddingTop() - firstTop);
                }
                // Move everything down
                offsetChildrenTopAndBottom(bottomOffset);
                if (getFirstVisiblePosition() > 0) {
                    // Fill the gap that was opened above mFirstPosition with more rows, if
                    // possible
                    fillUp(getFirstVisiblePosition() - 1, firstChild.getTop() - mDividerHeight);
                    // Close up the remaining gap
                    adjustViewsUpOrDown();
                }

            }
        }
		System.out.println("resp1onse ListView: private void correctTooHigh(int childCount) { end void ");
    }

    /**
     * Check if we have dragged the bottom of the list too low (we have pushed the
     * bottom element off the bottom of the screen when we did not need to). Correct by sliding
     * everything back up.
     *
     * @param childCount Number of children
     */
    private void correctTooLow(int childCount) {
		System.out.println("resp1onse ListView: private void correctTooLow(int childCount) { start void ");
        // First see if the first item is visible. If it is not, it is OK for the
        // bottom of the list to be pushed down.
        if (getFirstVisiblePosition() == 0 && childCount > 0) {

            // Get the first child ...
            final View firstChild = getChildAt(0);

            // ... and its top edge
            final int firstTop = firstChild.getTop();

            // This is top of our drawable area
            final int start = getListPaddingTop();

            // This is bottom of our drawable area
            final int end = (getBottom() - getTop()) - getListPaddingBottom();

            // This is how far the top edge of the first view is from the top of the
            // drawable area
            int topOffset = firstTop - start;
            View lastChild = getChildAt(childCount - 1);
            final int lastBottom = lastChild.getBottom();
            int lastPosition = getFirstVisiblePosition() + childCount - 1;

            // Make sure we are 1) Too low, and 2) Either there are more rows below the
            // last row or the last row is scrolled off the bottom of the drawable area
            if (topOffset > 0) {
                if (lastPosition < getCount() - 1 || lastBottom > end)  {
                    if (lastPosition == getCount() - 1) {
                        // Don't pull the bottom too far up
                        topOffset = Math.min(topOffset, lastBottom - end);
                    }
                    // Move everything up
                    offsetChildrenTopAndBottom(-topOffset);
                    if (lastPosition < getCount() - 1) {
                        // Fill the gap that was opened below the last position with more rows, if
                        // possible
                        fillDown(lastPosition + 1, lastChild.getBottom() + mDividerHeight);
                        // Close up the remaining gap
                        adjustViewsUpOrDown();
                    }
                } else if (lastPosition == getCount() - 1) {
                    adjustViewsUpOrDown();                    
                }
            }
        }
		System.out.println("resp1onse ListView: private void correctTooLow(int childCount) { end void ");
    }

    @Override
    protected void layoutChildren() {
		System.out.println("resp1onse ListView: protected void layoutChildren() { start void ");
        final boolean blockLayoutRequests = mBlockLayoutRequests ;
        if (!blockLayoutRequests) {
            mBlockLayoutRequests = true;
        } else {
            return;
        }

        try {
            super.layoutChildren();

            invalidate();

            if (mAdapter_MY == null) {
                resetList();
                invokeOnItemScrollListener();
		System.out.println("resp1onse ListView: protected void layoutChildren() { end return if ");
                return;
            }

            int childrenTop = getListPaddingTop();
            int childrenBottom = getBottom() - getTop() - getListPaddingBottom();

            int childCount = getChildCount();
            int index = 0;
            int delta = 0;

            View sel;
            View oldSel = null;
            View oldFirst = null;
            View newSel = null;

            View focusLayoutRestoreView = null;

            // Remember stuff we will need down below
            switch (getLayoutMode()) {
            case LAYOUT_SET_SELECTION:
                index = getSelectedItemPosition() - getFirstVisiblePosition();
                if (index >= 0 && index < childCount) {
                    newSel = getChildAt(index);
                }
                break;
            case LAYOUT_FORCE_TOP:
            case LAYOUT_FORCE_BOTTOM:
            case LAYOUT_SPECIFIC:
            case LAYOUT_SYNC:
                break;
            case LAYOUT_MOVE_SELECTION:
            default:
                // Remember the previously selected view
                index = getSelectedPosition() - getFirstVisiblePosition();
                if (index >= 0 && index < childCount) {
                    oldSel = getChildAt(index);
                }

                // Remember the previous first child
                oldFirst = getChildAt(0);

                if (getSelectedItemPosition() >= 0) {
                    delta = getSelectedItemPosition() - getSelectedPosition();
                }

                // Caution: newSel might be null
                newSel = getChildAt(index + delta);
            }


            boolean dataChanged = isDataChanged();
            if (dataChanged) {
                handleDataChanged();
            }

            // Handle the empty set by removing all views that are visible
            // and calling it a day
            if (getCount() == 0) {
                resetList();
                invokeOnItemScrollListener();
		System.out.println("resp1onse ListView: protected void layoutChildren() { end return if ");
                return;
            } else if (getCount() != mAdapter_MY.getCount()) {
                throw new IllegalStateException("The content of the adapter has changed but "
                        + "ListView did not receive a notification. Make sure the content of "
                        + "your adapter is not modified from a background thread, but only "
                        + "from the UI thread. [in ListView(" + getId() + ", " + getClass() 
                        + ") with Adapter(" + mAdapter_MY.getClass() + ")]");
            }

            setSelectedPositionInt(getSelectedItemPosition());

            // Pull all children into the RecycleBin.
            // These views will be reused if possible
            final int firstPosition = getFirstVisiblePosition();
//            final RecycleBin recycleBin = mRecycler;
            final Object recycleBin = getRecycler();

            // reset the focus restoration
            View focusLayoutRestoreDirectChild = null;


            // Don't put header or footer views into the Recycler. Those are
            // already cached in mHeaderViews;
            if (dataChanged) {
                for (int i = 0; i < childCount; i++) {
//                    recycleBin.addScrapView(getChildAt(i));
                    recycler_addScrapView(getChildAt(i), getFirstVisiblePosition() + i);
                    if (ViewDebug.TRACE_RECYCLER) {
                        ViewDebug.trace(getChildAt(i),
                                ViewDebug.RecyclerTraceType.MOVE_TO_SCRAP_HEAP, index, i);
                    }
                }
            } else {
//                recycleBin.fillActiveViews(childCount, firstPosition);
                recycler_fillActiveViews(childCount, firstPosition);
            }

            // take focus back to us temporarily to avoid the eventual
            // call to clear focus when removing the focused child below
            // from messing things up when ViewRoot assigns focus back
            // to someone else
            final View focusedChild = getFocusedChild();
            if (focusedChild != null) {
                // TODO: in some cases focusedChild.getParent() == null

                // we can remember the focused view to restore after relayout if the
                // data hasn't changed, or if the focused position is a header or footer
                if (!dataChanged || isDirectChildHeaderOrFooter(focusedChild)) {
                    focusLayoutRestoreDirectChild = focusedChild;
                    // remember the specific view that had focus
                    focusLayoutRestoreView = findFocus();
                    if (focusLayoutRestoreView != null) {
                        // tell it we are going to mess with it
                        focusLayoutRestoreView.onStartTemporaryDetach();
                    }
                }
                requestFocus();
            }

            // Clear out old views
            detachAllViewsFromParent();

            switch (getLayoutMode()) {
            case LAYOUT_SET_SELECTION:
                if (newSel != null) {
                    sel = fillFromSelection(newSel.getTop(), childrenTop, childrenBottom);
                } else {
                    sel = fillFromMiddle(childrenTop, childrenBottom);
                }
                break;
            case LAYOUT_SYNC:
                sel = fillSpecific(getSyncPosition(), getSpecificTop());
                break;
            case LAYOUT_FORCE_BOTTOM:
                sel = fillUp(getCount() - 1, childrenBottom);
                adjustViewsUpOrDown();
                break;
            case LAYOUT_FORCE_TOP:
//                mFirstPosition = 0;
                setFirstPosition(0);
                sel = fillFromTop(childrenTop);
                adjustViewsUpOrDown();
                break;
            case LAYOUT_SPECIFIC:
                sel = fillSpecific(reconcileSelectedPosition(), getSpecificTop());
                break;
            case LAYOUT_MOVE_SELECTION:
                sel = moveSelection(oldSel, newSel, delta, childrenTop, childrenBottom);
                break;
            default:
                if (childCount == 0) {
                    if (!isStackFromBottom()) {
                        final int position = lookForSelectablePosition(0, true);
                        setSelectedPositionInt(position);
                        sel = fillFromTop(childrenTop);
                    } else {
                        final int position = lookForSelectablePosition(getCount() - 1, false);
                        setSelectedPositionInt(position);
                        sel = fillUp(getCount() - 1, childrenBottom);
                    }
                } else {
                    if (getSelectedPosition() >= 0 && getSelectedPosition() < getCount()) {
                        sel = fillSpecific(getSelectedPosition(),
                                oldSel == null ? childrenTop : oldSel.getTop());
                    } else if (getFirstVisiblePosition() < getCount()) {
                        sel = fillSpecific(getFirstVisiblePosition(),
                                oldFirst == null ? childrenTop : oldFirst.getTop());
                    } else {
                        sel = fillSpecific(0, childrenTop);
                    }
                }
                break;
            }

            // Flush any cached views that did not get reused above
//            recycleBin.scrapActiveViews();
            recycler_scrapActiveViews();

            if (sel != null) {
                // the current selected item should get focus if items
                // are focusable
                if (mItemsCanFocus && hasFocus() && !sel.hasFocus()) {
                    final boolean focusWasTaken = (sel == focusLayoutRestoreDirectChild &&
                            focusLayoutRestoreView.requestFocus()) || sel.requestFocus();
                    if (!focusWasTaken) {
                        // selected item didn't take focus, fine, but still want
                        // to make sure something else outside of the selected view
                        // has focus
                        final View focused = getFocusedChild();
                        if (focused != null) {
                            focused.clearFocus();
                        }
                        positionSelector(INVALID_POSITION, sel);
                    } else {
                        sel.setSelected(false);
                        setSelectorRectEmpty();
                    }
                } else {
                    positionSelector(INVALID_POSITION, sel);
                }
//                mSelectedTop = sel.getTop();
                setSelectedTop(sel.getTop());
            } else {
                if (getTouchMode() > TOUCH_MODE_DOWN && getTouchMode() < TOUCH_MODE_SCROLL) {
                    View child = getChildAt(getMotionPosition() - getFirstVisiblePosition());
                    if (child != null) positionSelector(getMotionPosition(), child);
                } else {
//                    mSelectedTop = 0;
                	setSelectedTop(0);
                	setSelectorRectEmpty();
                }

                // even if there is not selected position, we may need to restore
                // focus (i.e. something focusable in touch mode)
                if (hasFocus() && focusLayoutRestoreView != null) {
                    focusLayoutRestoreView.requestFocus();
                }
            }

            // tell focus view we are done mucking with it, if it is still in
            // our view hierarchy.
            if (focusLayoutRestoreView != null
                    && focusLayoutRestoreView.getWindowToken() != null) {
                focusLayoutRestoreView.onFinishTemporaryDetach();
            }
            
//            mLayoutMode = LAYOUT_NORMAL;
            setLayoutMode(LAYOUT_NORMAL);
//            mDataChanged = false;
            setDataChanged(false);
//            mNeedSync = false;
            setNeedSync(false);
            setNextSelectedPositionInt(getSelectedPosition());

            updateScrollIndicators();

            if (getCount() > 0) {
                checkSelectionChanged();
            }

            invokeOnItemScrollListener();
        } finally {
            if (!blockLayoutRequests) {
                mBlockLayoutRequests = false;
            }
        }
		System.out.println("resp1onse ListView: protected void layoutChildren() { end void ");
    }

	/**
     * @param child a direct child of this list.
     * @return Whether child is a header or footer view.
     */
    private boolean isDirectChildHeaderOrFooter(View child) {

		System.out.println("resp1onse ListView: private boolean isDirectChildHeaderOrFooter(View child) { start return ");
        final ArrayList<FixedViewInfo> headers = mHeaderViewInfos;
        final int numHeaders = headers.size();
        for (int i = 0; i < numHeaders; i++) {
            if (child == headers.get(i).view) {
                return true;
            }
        }
        final ArrayList<FixedViewInfo> footers = mFooterViewInfos;
        final int numFooters = footers.size();
        for (int i = 0; i < numFooters; i++) {
            if (child == footers.get(i).view) {
                return true;
            }
        }
		System.out.println("resp1onse ListView: private boolean isDirectChildHeaderOrFooter(View child) { end return  =1");
        return false;
    }

    /**
     * Obtain the view and add it to our list of children. The view can be made
     * fresh, converted from an unused view, or used as is if it was in the
     * recycle bin.
     *
     * @param position Logical position in the list
     * @param y Top or bottom edge of the view to add
     * @param flow If flow is true, align top edge to y. If false, align bottom
     *        edge to y.
     * @param childrenLeft Left edge where children should be positioned
     * @param selected Is this position selected?
     * @return View that was added
     */
    private View makeAndAddView(int position, int y, boolean flow, int childrenLeft,
            boolean selected) {
        View child;


        if (!isDataChanged()) {
            // Try to use an exsiting view for this position
//            child = mRecycler.getActiveView(position);
        	child = recycler_getActiveView(position);
            if (child != null) {
                if (ViewDebug.TRACE_RECYCLER) {
                    ViewDebug.trace(child, ViewDebug.RecyclerTraceType.RECYCLE_FROM_ACTIVE_HEAP,
                            position, getChildCount());
                }

                // Found it -- we're using an existing child
                // This just needs to be positioned
                setupChild(child, position, y, flow, childrenLeft, selected, true);

                return child;
            }
        }

        // Make a new view for this position, or convert an unused view if possible
        child = obtainView2(position, IsScrap_MY);

        // This needs to be positioned and measured
        setupChild(child, position, y, flow, childrenLeft, selected, IsScrap_MY[0]);

        return child;
    }
    
	/**
     * Add a view as a child and make sure it is measured (if necessary) and
     * positioned properly.
     *
     * @param child The view to add
     * @param position The position of this child
     * @param y The y position relative to which this view will be positioned
     * @param flowDown If true, align top edge to y. If false, align bottom
     *        edge to y.
     * @param childrenLeft Left edge where children should be positioned
     * @param selected Is this position selected?
     * @param recycled Has this view been pulled from the recycle bin? If so it
     *        does not need to be remeasured.
     */
    private void setupChild(View child, int position, int y, boolean flowDown, int childrenLeft,
            boolean selected, boolean recycled) {
    	
    	if (recycled) {
			
//    		final View scrapView1 = mRecycler.getScrapView(position);
    		final View scrapView1 = getScrapView(position);
    		final View child1 = mAdapter_MY.getView(position, scrapView1, this);
    		System.out.println("resp1onse : recycled : scrapView1 == null " + (scrapView1 == null));
    		 if (scrapView1 != null) {
	            if (child1 != scrapView1) {
	            	System.out.println("resp1onse : recycled : child != scrapView1 " );
	            } else {
	            	System.out.println("resp1onse : recycled : child == scrapView1 " );
	            }
	        }
    		
		}
    	
    	System.out.println("resp1onse : recycled = " + recycled +" position = " + position);
        final boolean isSelected = selected && shouldShowSelector();
        final boolean updateChildSelected = isSelected != child.isSelected();
        final int mode = getTouchMode();
        final boolean isPressed = mode > TOUCH_MODE_DOWN && mode < TOUCH_MODE_SCROLL &&
                getMotionPosition() == position;
        final boolean updateChildPressed = isPressed != child.isPressed();
        final boolean needToMeasure = !recycled || updateChildSelected || child.isLayoutRequested();

        // Respect layout params that are already in the view. Otherwise make some up...
        // noinspection unchecked
        AbsListView.LayoutParams p = (AbsListView.LayoutParams) child.getLayoutParams();
        if (p == null) {
            p = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        }
//        p.viewType = mAdapter_MY.getItemViewType(position);
//        if ((recycled && !p.forceAdd) || (p.recycledHeaderFooter &&
//                p.viewType == AdapterView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER)) {
//            attachViewToParent(child, flowDown ? -1 : 0, p);
//        } else {
//            p.forceAdd = false;
//            if (p.viewType == AdapterView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER) {
//                p.recycledHeaderFooter = true;
//            }
//            addViewInLayout(child, flowDown ? -1 : 0, p, true);
//        }
        
        if (recycled) {
        	attachViewToParent(child, flowDown ? -1 : 0, p);
		}else {
			addViewInLayout(child, flowDown ? -1 : 0, p, true);
		}
        
//        addViewInLayout(child, flowDown ? -1 : 0, p, true);//TODO

        if (updateChildSelected) {
            child.setSelected(isSelected);
        }

        if (updateChildPressed) {
            child.setPressed(isPressed);
        }

        if (mChoiceMode != CHOICE_MODE_NONE && mCheckStates != null) {
            if (child instanceof Checkable) {
                ((Checkable) child).setChecked(mCheckStates.get(position));
            }
        }

        if (needToMeasure) {
            int childWidthSpec = ViewGroup.getChildMeasureSpec(getWidthMeasureSpec(),
                    getListPaddingLeft() + getListPaddingRight(), p.width);
            int lpHeight = p.height;
            int childHeightSpec;
            if (lpHeight > 0) {
                childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
            } else {
                childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            }
            child.measure(childWidthSpec, childHeightSpec);
        } else {
            cleanupLayoutState(child);
        }

        final int w = child.getMeasuredWidth();
        final int h = child.getMeasuredHeight();
        final int childTop = flowDown ? y : y - h;

        if (needToMeasure) {
            final int childRight = childrenLeft + w;
            final int childBottom = childTop + h;
            child.layout(childrenLeft, childTop, childRight, childBottom);
        } else {
            child.offsetLeftAndRight(childrenLeft - child.getLeft());
            child.offsetTopAndBottom(childTop - child.getTop());
        }

        if (getCachingStarted() && !child.isDrawingCacheEnabled()) {
            child.setDrawingCacheEnabled(true);
        }
    }

	@Override
    protected boolean canAnimate() {
		System.out.println("resp1onse ListView: protected boolean canAnimate() { start return ");
		System.out.println("resp1onse ListView: protected boolean canAnimate() { end return ");
        return super.canAnimate() && getCount() > 0;
    }

    /**
     * Sets the currently selected item. If in touch mode, the item will not be selected
     * but it will still be positioned appropriately. If the specified selection position
     * is less than 0, then the item at position 0 will be selected.
     *
     * @param position Index (starting at 0) of the data item to be selected.
     */
    @Override
    public void setSelection(int position) {
		System.out.println("resp1onse ListView: public void setSelection(int position) { start void ");
        setSelectionFromTop(position, 0);
		System.out.println("resp1onse ListView: public void setSelection(int position) { end void ");
    }

    /**
     * Sets the selected item and positions the selection y pixels from the top edge
     * of the ListView. (If in touch mode, the item will not be selected but it will
     * still be positioned appropriately.)
     *
     * @param position Index (starting at 0) of the data item to be selected.
     * @param y The distance from the top edge of the ListView (plus padding) that the
     *        item will be positioned.
     */
    @SuppressLint("Override")
	public void setSelectionFromTop(int position, int y) {
		System.out.println("resp1onse ListView: public void setSelectionFromTop(int position, int y) { start void ");
        if (mAdapter_MY == null) {
            return;
        }

        if (!isInTouchMode()) {
            position = lookForSelectablePosition(position, true);
            if (position >= 0) {
                setNextSelectedPositionInt(position);
            }
        } else {
//            mResurrectToPosition = position;
            setResurrectToPosition(position);
        }

        if (position >= 0) {
//            mLayoutMode = LAYOUT_SPECIFIC;
            setLayoutMode(LAYOUT_SPECIFIC);
//            mSpecificTop = getListPaddingTop() + y;
            setSpecificTop(getListPaddingTop() + y);

            if (isNeedSync()) {
//                mSyncPosition = position;
                setSyncPosition(position);
//                mSyncRowId = mAdapter.getItemId(position);
                setSyncRowId(mAdapter_MY.getItemId(position));
            }

            requestLayout();
        }
		System.out.println("resp1onse ListView: public void setSelectionFromTop(int position, int y) { end void ");
    }

	/**
     * Makes the item at the supplied position selected.
     * 
     * @param position the position of the item to select
     */
//    @ Over ride
    public void setSelectionInt(int position) {
		System.out.println("resp1onse ListView: public void setSelectionInt(int position) { start void ");
        setNextSelectedPositionInt(position);
        boolean awakeScrollbars = false;

        final int selectedPosition = getSelectedPosition();

        if (selectedPosition >= 0) {
            if (position == selectedPosition - 1) {
                awakeScrollbars = true;
            } else if (position == selectedPosition + 1) {
                awakeScrollbars = true;
            }
        }

        layoutChildren();

        if (awakeScrollbars) {
            awakenScrollBars();
        }
		System.out.println("resp1onse ListView: public void setSelectionInt(int position) { end void ");
    }

    /**
     * Find a position that can be selected (i.e., is not a separator).
     *
     * @param position The starting position to look at.
     * @param lookDown Whether to look down for other positions.
     * @return The next selectable position starting at position and then searching either up or
     *         down. Returns {@link #INVALID_POSITION} if nothing can be found.
     */
//    @ Ove rride
    public int lookForSelectablePosition(int position, boolean lookDown) {
		System.out.println("resp1onse ListView: public int lookForSelectablePosition(int position, boolean lookDown) { start return ");
        final ListAdapter adapter = mAdapter_MY;
        if (adapter == null || isInTouchMode()) {
            return INVALID_POSITION;
        }

        final int count = adapter.getCount();
        if (!mAreAllItemsSelectable) {
            if (lookDown) {
                position = Math.max(0, position);
                while (position < count && !adapter.isEnabled(position)) {
                    position++;
                }
            } else {
                position = Math.min(position, count - 1);
                while (position >= 0 && !adapter.isEnabled(position)) {
                    position--;
                }
            }

            if (position < 0 || position >= count) {
                return INVALID_POSITION;
            }
		System.out.println("resp1onse ListView: public int lookForSelectablePosition(int position, boolean lookDown) { end return if ");
            return position;
        } else {
            if (position < 0 || position >= count) {
                return INVALID_POSITION;
            }
		System.out.println("resp1onse ListView: public int lookForSelectablePosition(int position, boolean lookDown) { end return if ");
            return position;
        }
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("resp1onse ListView: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { start return ");
        boolean populated = super.dispatchPopulateAccessibilityEvent(event);

        // If the item count is less than 15 then subtract disabled items from the count and
        // position. Otherwise ignore disabled items.
        if (!populated) {
            int itemCount = 0;
            int currentItemIndex = getSelectedItemPosition();

            ListAdapter adapter = getAdapter();
            if (adapter != null) {
                final int count = adapter.getCount();
                if (count < 15) {
                    for (int i = 0; i < count; i++) {
                        if (adapter.isEnabled(i)) {
                            itemCount++;
                        } else if (i <= currentItemIndex) {
                            currentItemIndex--;
                        }
                    }
                } else {
                    itemCount = count;
                }
            }

            event.setItemCount(itemCount);
            event.setCurrentItemIndex(currentItemIndex);
        }

		System.out.println("resp1onse ListView: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { end return  =1");
        return populated;
    }

    /**
     * setSelectionAfterHeaderView set the selection to be the first list item
     * after the header views.
     */
    public void setSelectionAfterHeaderView() {
		System.out.println("resp1onse ListView: public void setSelectionAfterHeaderView() { start void ");
//        final int count = mHeaderViewInfos.size();
//        if (count > 0) {
//            mNextSelectedPosition = 0;
//            return;
//        }
//
//        if (mAdapter != null) {
//            setSelection(count);
//        } else {
//            mNextSelectedPosition = count;
//            mLayoutMode = LAYOUT_SET_SELECTION;
//        }

		System.out.println("resp1onse ListView: public void setSelectionAfterHeaderView() { end void ");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Dispatch in the normal way
		System.out.println("resp1onse ListView: public boolean dispatchKeyEvent(KeyEvent event) { start return ");
        boolean handled = super.dispatchKeyEvent(event);
        if (!handled) {
            // If we didn't handle it...
            View focused = getFocusedChild();
            if (focused != null && event.getAction() == KeyEvent.ACTION_DOWN) {
                // ... and our focused child didn't handle it
                // ... give it to ourselves so we can scroll if necessary
                handled = onKeyDown(event.getKeyCode(), event);
            }
        }
		System.out.println("resp1onse ListView: public boolean dispatchKeyEvent(KeyEvent event) { end return  =1");
        return handled;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("resp1onse ListView: public boolean onKeyDown(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ListView: public boolean onKeyDown(int keyCode, KeyEvent event) { end return ");
        return commonKey(keyCode, 1, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		System.out.println("resp1onse ListView: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { start return ");
		System.out.println("resp1onse ListView: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { end return ");
        return commonKey(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
		System.out.println("resp1onse ListView: public boolean onKeyUp(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ListView: public boolean onKeyUp(int keyCode, KeyEvent event) { end return ");
        return commonKey(keyCode, 1, event);
    }

    private boolean commonKey(int keyCode, int count, KeyEvent event) {
		System.out.println("resp1onse ListView: private boolean commonKey(int keyCode, int count, KeyEvent event) { start return ");
        if (mAdapter_MY == null) {
            return false;
        }

        if (isDataChanged()) {
            layoutChildren();
        }

        boolean handled = false;
        int action = event.getAction();

        if (action != KeyEvent.ACTION_UP) {
//            if (getSelectedPosition() < 0) {
//                switch (keyCode) {
//                case KeyEvent.KEYCODE_DPAD_UP:
//                case KeyEvent.KEYCODE_DPAD_DOWN:
//                case KeyEvent.KEYCODE_DPAD_CENTER:
//                case KeyEvent.KEYCODE_ENTER:
//                case KeyEvent.KEYCODE_SPACE:
//                    if (resurrectSelection()) {
//                        return true;
//                    }
//                }
//            }
            switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (!event.isAltPressed()) {
                    while (count > 0) {
                        handled = arrowScroll(FOCUS_UP);
                        count--;
                    }
                } else {
                    handled = fullScroll(FOCUS_UP);
                }
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (!event.isAltPressed()) {
                    while (count > 0) {
                        handled = arrowScroll(FOCUS_DOWN);
                        count--;
                    }
                } else {
                    handled = fullScroll(FOCUS_DOWN);
                }
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                handled = handleHorizontalFocusWithinListItem(View.FOCUS_LEFT);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                handled = handleHorizontalFocusWithinListItem(View.FOCUS_RIGHT);
                break;

            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
//                if (getCount() > 0 && event.getRepeatCount() == 0) {
//                    keyPressed();
//                }
                handled = true;
                break;

            case KeyEvent.KEYCODE_SPACE:
//                if (mPopup == null || !mPopup.isShowing()) {
//                    if (!event.isShiftPressed()) {
//                        pageScroll(FOCUS_DOWN);
//                    } else {
//                        pageScroll(FOCUS_UP);
//                    }
//                    handled = true;
//                }
                break;
            }
        }

        if (!handled) {
            handled = sendToTextFilter(keyCode, count, event);
        }

        if (handled) {
            return true;
        } else {
            switch (action) {
                case KeyEvent.ACTION_DOWN:
		System.out.println("resp1onse ListView: private boolean commonKey(int keyCode, int count, KeyEvent event) { end return if ");
                    return super.onKeyDown(keyCode, event);

                case KeyEvent.ACTION_UP:
		System.out.println("resp1onse ListView: private boolean commonKey(int keyCode, int count, KeyEvent event) { end return if ");
                    return super.onKeyUp(keyCode, event);

                case KeyEvent.ACTION_MULTIPLE:
		System.out.println("resp1onse ListView: private boolean commonKey(int keyCode, int count, KeyEvent event) { end return if ");
                    return super.onKeyMultiple(keyCode, count, event);

                default: // shouldn't happen
		System.out.println("resp1onse ListView: private boolean commonKey(int keyCode, int count, KeyEvent event) { end return if ");
                    return false;
            }
        }
    }


	/**
     * Scrolls up or down by the number of items currently present on screen.
     *
     * @param direction either {@link View#FOCUS_UP} or {@link View#FOCUS_DOWN}
     * @return whether selection was moved
     */
    boolean pageScroll(int direction) {
        int nextPage = -1;
        boolean down = false;

        if (direction == FOCUS_UP) {
            nextPage = Math.max(0, getSelectedPosition() - getChildCount() - 1);
        } else if (direction == FOCUS_DOWN) {
            nextPage = Math.min(getCount() - 1, getSelectedPosition() + getChildCount() - 1);
            down = true;
        }

        if (nextPage >= 0) {
            int position = lookForSelectablePosition(nextPage, down);
            if (position >= 0) {
//                mLayoutMode = LAYOUT_SPECIFIC;
                setLayoutMode(LAYOUT_SPECIFIC);
//                mSpecificTop = getPaddingTop() + getVerticalFadingEdgeLength();
                setSpecificTop(getPaddingTop() + getVerticalFadingEdgeLength());

                if (down && position > getCount() - getChildCount()) {
//                    mLayoutMode = LAYOUT_FORCE_BOTTOM;
                    setLayoutMode(LAYOUT_FORCE_BOTTOM);
                }

                if (!down && position < getChildCount()) {
//                    mLayoutMode = LAYOUT_FORCE_TOP;
                	setLayoutMode(LAYOUT_FORCE_TOP);
                }

                setSelectionInt(position);
                invokeOnItemScrollListener();
                if (!awakenScrollBars()) {
                    invalidate();
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Go to the last or first item if possible (not worrying about panning across or navigating
     * within the internal focus of the currently selected item.)
     *
     * @param direction either {@link View#FOCUS_UP} or {@link View#FOCUS_DOWN}
     *
     * @return whether selection was moved
     */
	boolean fullScroll(int direction) {
        boolean moved = false;
        if (direction == FOCUS_UP) {
            if (getSelectedPosition() != 0) {
                int position = lookForSelectablePosition(0, true);
                if (position >= 0) {
//                    mLayoutMode = LAYOUT_FORCE_TOP;
                    setLayoutMode(LAYOUT_FORCE_TOP);
                    setSelectionInt(position);
                    invokeOnItemScrollListener();
                }
                moved = true;
            }
        } else if (direction == FOCUS_DOWN) {
            if (getSelectedPosition() < getCount() - 1) {
                int position = lookForSelectablePosition(getCount() - 1, true);
                if (position >= 0) {
//                    mLayoutMode = LAYOUT_FORCE_BOTTOM;
                	setLayoutMode(LAYOUT_FORCE_BOTTOM);
                    setSelectionInt(position);
                    invokeOnItemScrollListener();
                }
                moved = true;
            }
        }

        if (moved && !awakenScrollBars()) {
            awakenScrollBars();
            invalidate();
        }

        return moved;
    }

	/**
     * To avoid horizontal focus searches changing the selected item, we
     * manually focus search within the selected item (as applicable), and
     * prevent focus from jumping to something within another item.
     * @param direction one of {View.FOCUS_LEFT, View.FOCUS_RIGHT}
     * @return Whether this consumes the key event.
     */
    private boolean handleHorizontalFocusWithinListItem(int direction) {
		System.out.println("resp1onse ListView: private boolean handleHorizontalFocusWithinListItem(int direction) { start return ");
        if (direction != View.FOCUS_LEFT && direction != View.FOCUS_RIGHT)  {
            throw new IllegalArgumentException("direction must be one of"
                    + " {View.FOCUS_LEFT, View.FOCUS_RIGHT}");
        }

        final int numChildren = getChildCount();
        if (mItemsCanFocus && numChildren > 0 && getSelectedPosition() != INVALID_POSITION) {
            final View selectedView = getSelectedView();
            if (selectedView != null && selectedView.hasFocus() &&
                    selectedView instanceof ViewGroup) {

                final View currentFocus = selectedView.findFocus();
                final View nextFocus = FocusFinder.getInstance().findNextFocus(
                        (ViewGroup) selectedView, currentFocus, direction);
                if (nextFocus != null) {
                    // do the math to get interesting rect in next focus' coordinates
                    currentFocus.getFocusedRect(mTempRect);
                    offsetDescendantRectToMyCoords(currentFocus, mTempRect);
                    offsetRectIntoDescendantCoords(nextFocus, mTempRect);
                    if (nextFocus.requestFocus(direction, mTempRect)) {
                        return true;
                    }
                }
                // we are blocking the key from being handled (by returning true)
                // if the global result is going to be some other view within this
                // list.  this is to acheive the overall goal of having
                // horizontal d-pad navigation remain in the current item.
                final View globalNextFocus = FocusFinder.getInstance().findNextFocus(
                        (ViewGroup) getRootView(), currentFocus, direction);
                if (globalNextFocus != null) {
                    return isViewAncestorOf(globalNextFocus, this);
                }
            }
        }
		System.out.println("resp1onse ListView: private boolean handleHorizontalFocusWithinListItem(int direction) { end return  =1");
        return false;
    }

    /**
     * Scrolls to the next or previous item if possible.
     *
     * @param direction either {@link View#FOCUS_UP} or {@link View#FOCUS_DOWN}
     *
     * @return whether selection was moved
     */
    boolean arrowScroll(int direction) {
        try {
//            mInLayout = true;
            setInLayout(true);
            final boolean handled = arrowScrollImpl(direction);
            if (handled) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
            }
            return handled;
        } finally {
//            mInLayout = false;
        	 setInLayout(false);
        }
    }

	/**
     * Handle an arrow scroll going up or down.  Take into account whether items are selectable,
     * whether there are focusable items etc.
     *
     * @param direction Either {@link android.view.View#FOCUS_UP} or {@link android.view.View#FOCUS_DOWN}.
     * @return Whether any scrolling, selection or focus change occured.
     */
    private boolean arrowScrollImpl(int direction) {
		System.out.println("resp1onse ListView: private boolean arrowScrollImpl(int direction) { start return ");
        if (getChildCount() <= 0) {
            return false;
        }

        View selectedView = getSelectedView();

        int nextSelectedPosition = lookForSelectablePositionOnScreen(direction);
        int amountToScroll = amountToScroll(direction, nextSelectedPosition);

        // if we are moving focus, we may OVERRIDE the default behavior
        final ArrowScrollFocusResult focusResult = mItemsCanFocus ? arrowScrollFocused(direction) : null;
        if (focusResult != null) {
            nextSelectedPosition = focusResult.getSelectedPosition();
            amountToScroll = focusResult.getAmountToScroll();
        }

        boolean needToRedraw = focusResult != null;
        if (nextSelectedPosition != INVALID_POSITION) {
            handleNewSelectionChange(selectedView, direction, nextSelectedPosition, focusResult != null);
            setSelectedPositionInt(nextSelectedPosition);
            setNextSelectedPositionInt(nextSelectedPosition);
            selectedView = getSelectedView();
            if (mItemsCanFocus && focusResult == null) {
                // there was no new view found to take focus, make sure we
                // don't leave focus with the old selection
                final View focused = getFocusedChild();
                if (focused != null) {
                    focused.clearFocus();
                }
            }
            needToRedraw = true;
            checkSelectionChanged();
        }

        if (amountToScroll > 0) {
            scrollListItemsBy((direction == View.FOCUS_UP) ? amountToScroll : -amountToScroll);
            needToRedraw = true;
        }

        // if we didn't find a new focusable, make sure any existing focused
        // item that was panned off screen gives up focus.
        if (mItemsCanFocus && (focusResult == null)
                && selectedView != null && selectedView.hasFocus()) {
            final View focused = selectedView.findFocus();
            if (distanceToView(focused) > 0) {
                focused.clearFocus();
            }
        }

        // if  the current selection is panned off, we need to remove the selection
        if (nextSelectedPosition == INVALID_POSITION && selectedView != null
                && !isViewAncestorOf(selectedView, this)) {
            selectedView = null;
            hideSelector();

            // but we don't want to set the ressurect position (that would make subsequent
            // unhandled key events bring back the item we just scrolled off!)
//            mResurrectToPosition = INVALID_POSITION;
            setResurrectToPosition(INVALID_POSITION);
        }

        if (needToRedraw) {
            if (selectedView != null) {
                positionSelector(nextSelectedPosition, selectedView);
//                mSelectedTop = selectedView.getTop();
                setSelectedTop(selectedView.getTop());
            }
            if (!awakenScrollBars()) {
                invalidate();
            }
            invokeOnItemScrollListener();
		System.out.println("resp1onse ListView: private boolean arrowScrollImpl(int direction) { end return if ");
            return true;
        }

		System.out.println("resp1onse ListView: private boolean arrowScrollImpl(int direction) { end return  =1");
        return false;
    }

	/**
     * When selection changes, it is possible that the previously selected or the
     * next selected item will change its size.  If so, we need to offset some folks,
     * and re-layout the items as appropriate.
     *
     * @param selectedView The currently selected view (before changing selection).
     *   should be <code>null</code> if there was no previous selection.
     * @param direction Either {@link android.view.View#FOCUS_UP} or
     *        {@link android.view.View#FOCUS_DOWN}.
     * @param newSelectedPosition The position of the next selection.
     * @param newFocusAssigned whether new focus was assigned.  This matters because
     *        when something has focus, we don't want to show selection (ugh).
     */
    private void handleNewSelectionChange(View selectedView, int direction, int newSelectedPosition,
            boolean newFocusAssigned) {
        if (newSelectedPosition == INVALID_POSITION) {
            throw new IllegalArgumentException("newSelectedPosition needs to be valid");
        }

        // whether or not we are moving down or up, we want to preserve the
        // top of whatever view is on top:
        // - moving down: the view that had selection
        // - moving up: the view that is getting selection
        View topView;
        View bottomView;
        int topViewIndex, bottomViewIndex;
        boolean topSelected = false;
        final int selectedIndex = getSelectedPosition() - getFirstVisiblePosition();
        final int nextSelectedIndex = newSelectedPosition - getFirstVisiblePosition();
        if (direction == View.FOCUS_UP) {
            topViewIndex = nextSelectedIndex;
            bottomViewIndex = selectedIndex;
            topView = getChildAt(topViewIndex);
            bottomView = selectedView;
            topSelected = true;
        } else {
            topViewIndex = selectedIndex;
            bottomViewIndex = nextSelectedIndex;
            topView = selectedView;
            bottomView = getChildAt(bottomViewIndex);
        }

        final int numChildren = getChildCount();

        // start with top view: is it changing size?
        if (topView != null) {
            topView.setSelected(!newFocusAssigned && topSelected);
            measureAndAdjustDown(topView, topViewIndex, numChildren);
        }

        // is the bottom view changing size?
        if (bottomView != null) {
            bottomView.setSelected(!newFocusAssigned && !topSelected);
            measureAndAdjustDown(bottomView, bottomViewIndex, numChildren);
        }
    }

    /**
     * Re-measure a child, and if its height changes, lay it out preserving its
     * top, and adjust the children below it appropriately.
     * @param child The child
     * @param childIndex The view group index of the child.
     * @param numChildren The number of children in the view group.
     */
    private void measureAndAdjustDown(View child, int childIndex, int numChildren) {
		System.out.println("resp1onse ListView: private void measureAndAdjustDown(View child, int childIndex, int numChildren) { start void ");
        int oldHeight = child.getHeight();
        measureItem(child);
        if (child.getMeasuredHeight() != oldHeight) {
            // lay out the view, preserving its top
            relayoutMeasuredItem(child);

            // adjust views below appropriately
            final int heightDelta = child.getMeasuredHeight() - oldHeight;
            for (int i = childIndex + 1; i < numChildren; i++) {
                getChildAt(i).offsetTopAndBottom(heightDelta);
            }
        }
		System.out.println("resp1onse ListView: private void measureAndAdjustDown(View child, int childIndex, int numChildren) { end void ");
    }

    /**
     * Measure a particular list child.
     * TODO: unify with setUpChild.
     * @param child The child.
     */
    private void measureItem(View child) {
		System.out.println("resp1onse ListView: private void measureItem(View child) { start void ");
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(getWidthMeasureSpec(),
                getListPaddingLeft() + getListPaddingRight(), p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
		System.out.println("resp1onse ListView: private void measureItem(View child) { end void ");
    }

    /**
     * Layout a child that has been measured, preserving its top position.
     * TODO: unify with setUpChild.
     * @param child The child.
     */
    private void relayoutMeasuredItem(View child) {
		System.out.println("resp1onse ListView: private void relayoutMeasuredItem(View child) { start void ");
        final int w = child.getMeasuredWidth();
        final int h = child.getMeasuredHeight();
        final int childLeft = getListPaddingLeft();
        final int childRight = childLeft + w;
        final int childTop = child.getTop();
        final int childBottom = childTop + h;
        child.layout(childLeft, childTop, childRight, childBottom);
		System.out.println("resp1onse ListView: private void relayoutMeasuredItem(View child) { end void ");
    }

    /**
     * @return The amount to preview next items when arrow srolling.
     */
    private int getArrowScrollPreviewLength() {
		System.out.println("resp1onse ListView: private int getArrowScrollPreviewLength() { start return ");
		System.out.println("resp1onse ListView: private int getArrowScrollPreviewLength() { end return ");
        return Math.max(MIN_SCROLL_PREVIEW_PIXELS, getVerticalFadingEdgeLength());
    }

    /**
     * Determine how much we need to scroll in order to get the next selected view
     * visible, with a fading edge showing below as applicable.  The amount is
     * capped at {@link #getMaxScrollAmount()} .
     *
     * @param direction either {@link android.view.View#FOCUS_UP} or
     *        {@link android.view.View#FOCUS_DOWN}.
     * @param nextSelectedPosition The position of the next selection, or
     *        {@link #INVALID_POSITION} if there is no next selectable position
     * @return The amount to scroll. Note: this is always positive!  Direction
     *         needs to be taken into account when actually scrolling.
     */
    private int amountToScroll(int direction, int nextSelectedPosition) {
		System.out.println("resp1onse ListView: private int amountToScroll(int direction, int nextSelectedPosition) { start return ");
        final int listBottom = getHeight() - getListPaddingBottom();
        final int listTop = getListPaddingTop();

        final int numChildren = getChildCount();

        if (direction == View.FOCUS_DOWN) {
            int indexToMakeVisible = numChildren - 1;
            if (nextSelectedPosition != INVALID_POSITION) {
                indexToMakeVisible = nextSelectedPosition - getFirstVisiblePosition();
            }

            final int positionToMakeVisible = getFirstVisiblePosition() + indexToMakeVisible;
            final View viewToMakeVisible = getChildAt(indexToMakeVisible);

            int goalBottom = listBottom;
            if (positionToMakeVisible < getCount() - 1) {
                goalBottom -= getArrowScrollPreviewLength();
            }

            if (viewToMakeVisible.getBottom() <= goalBottom) {
                // item is fully visible.
                return 0;
            }

            if (nextSelectedPosition != INVALID_POSITION
                    && (goalBottom - viewToMakeVisible.getTop()) >= getMaxScrollAmount()) {
                // item already has enough of it visible, changing selection is good enough
                return 0;
            }

            int amountToScroll = (viewToMakeVisible.getBottom() - goalBottom);

            if ((getFirstVisiblePosition() + numChildren) == getCount()) {
                // last is last in list -> make sure we don't scroll past it
                final int max = getChildAt(numChildren - 1).getBottom() - listBottom;
                amountToScroll = Math.min(amountToScroll, max);
            }

		System.out.println("resp1onse ListView: private int amountToScroll(int direction, int nextSelectedPosition) { end return if ");
            return Math.min(amountToScroll, getMaxScrollAmount());
        } else {
            int indexToMakeVisible = 0;
            if (nextSelectedPosition != INVALID_POSITION) {
                indexToMakeVisible = nextSelectedPosition - getFirstVisiblePosition();
            }
            final int positionToMakeVisible = getFirstVisiblePosition() + indexToMakeVisible;
            final View viewToMakeVisible = getChildAt(indexToMakeVisible);
            int goalTop = listTop;
            if (positionToMakeVisible > 0) {
                goalTop += getArrowScrollPreviewLength();
            }
            if (viewToMakeVisible.getTop() >= goalTop) {
                // item is fully visible.
                return 0;
            }

            if (nextSelectedPosition != INVALID_POSITION &&
                    (viewToMakeVisible.getBottom() - goalTop) >= getMaxScrollAmount()) {
                // item already has enough of it visible, changing selection is good enough
                return 0;
            }

            int amountToScroll = (goalTop - viewToMakeVisible.getTop());
            if (getFirstVisiblePosition() == 0) {
                // first is first in list -> make sure we don't scroll past it
                final int max = listTop - getChildAt(0).getTop();
                amountToScroll = Math.min(amountToScroll,  max);
            }
		System.out.println("resp1onse ListView: private int amountToScroll(int direction, int nextSelectedPosition) { end return if ");
            return Math.min(amountToScroll, getMaxScrollAmount());
        }
    }

    /**
     * Holds results of focus aware arrow scrolling.
     */
    static private class ArrowScrollFocusResult {
        private int mSelectedPosition;
        private int mAmountToScroll;

        /**
         * How {@link android.widget.ListView#arrowScrollFocused} returns its values.
         */
        void populate(int selectedPosition, int amountToScroll) {
		System.out.println("resp1onse ListView: void populate(int selectedPosition, int amountToScroll) { start void ");
            mSelectedPosition = selectedPosition;
            mAmountToScroll = amountToScroll;
		System.out.println("resp1onse ListView: void populate(int selectedPosition, int amountToScroll) { end void ");
        }

        public int getSelectedPosition() {
		System.out.println("resp1onse ListView: public int getSelectedPosition() { start return ");
		System.out.println("resp1onse ListView: public int getSelectedPosition() { end return ");
            return mSelectedPosition;
        }

        public int getAmountToScroll() {
		System.out.println("resp1onse ListView: public int getAmountToScroll() { start return ");
		System.out.println("resp1onse ListView: public int getAmountToScroll() { end return ");
            return mAmountToScroll;
        }
    }

    /**
     * @param direction either {@link android.view.View#FOCUS_UP} or
     *        {@link android.view.View#FOCUS_DOWN}.
     * @return The position of the next selectable position of the views that
     *         are currently visible, taking into account the fact that there might
     *         be no selection.  Returns {@link #INVALID_POSITION} if there is no
     *         selectable view on screen in the given direction.
     */
    private int lookForSelectablePositionOnScreen(int direction) {
		System.out.println("resp1onse ListView: private int lookForSelectablePositionOnScreen(int direction) { start return ");
        final int firstPosition = getFirstVisiblePosition();
        if (direction == View.FOCUS_DOWN) {
            int startPos = (getSelectedPosition() != INVALID_POSITION) ?
                    getSelectedPosition() + 1 :
                    firstPosition;
            if (startPos >= mAdapter_MY.getCount()) {
                return INVALID_POSITION;
            }
            if (startPos < firstPosition) {
                startPos = firstPosition;
            }
            
            final int lastVisiblePos = getLastVisiblePosition();
            final ListAdapter adapter = getAdapter();
            for (int pos = startPos; pos <= lastVisiblePos; pos++) {
                if (adapter.isEnabled(pos)
                        && getChildAt(pos - firstPosition).getVisibility() == View.VISIBLE) {
                    return pos;
                }
            }
        } else {
            int last = firstPosition + getChildCount() - 1;
            int startPos = (getSelectedPosition() != INVALID_POSITION) ?
                    getSelectedPosition() - 1 :
                    firstPosition + getChildCount() - 1;
            if (startPos < 0) {
                return INVALID_POSITION;
            }
            if (startPos > last) {
                startPos = last;
            }

            final ListAdapter adapter = getAdapter();
            for (int pos = startPos; pos >= firstPosition; pos--) {
                if (adapter.isEnabled(pos)
                        && getChildAt(pos - firstPosition).getVisibility() == View.VISIBLE) {
                    return pos;
                }
            }
        }
		System.out.println("resp1onse ListView: private int lookForSelectablePositionOnScreen(int direction) { end return  =1");
        return INVALID_POSITION;
    }

    /**
     * Do an arrow scroll based on focus searching.  If a new view is
     * given focus, return the selection delta and amount to scroll via
     * an {@link ArrowScrollFocusResult}, otherwise, return null.
     *
     * @param direction either {@link android.view.View#FOCUS_UP} or
     *        {@link android.view.View#FOCUS_DOWN}.
     * @return The result if focus has changed, or <code>null</code>.
     */
    private ArrowScrollFocusResult arrowScrollFocused(final int direction) {
		System.out.println("resp1onse ListView: private ArrowScrollFocusResult arrowScrollFocused(final int direction) { start return ");
        final View selectedView = getSelectedView();
        View newFocus;
        if (selectedView != null && selectedView.hasFocus()) {
            View oldFocus = selectedView.findFocus();
            newFocus = FocusFinder.getInstance().findNextFocus(this, oldFocus, direction);
        } else {
            if (direction == View.FOCUS_DOWN) {
                final boolean topFadingEdgeShowing = (getFirstVisiblePosition() > 0);
                final int listTop = getListPaddingTop() +
                        (topFadingEdgeShowing ? getArrowScrollPreviewLength() : 0);
                final int ySearchPoint =
                        (selectedView != null && selectedView.getTop() > listTop) ?
                                selectedView.getTop() :
                                listTop;
                mTempRect.set(0, ySearchPoint, 0, ySearchPoint);
            } else {
                final boolean bottomFadingEdgeShowing =
                        (getFirstVisiblePosition() + getChildCount() - 1) < getCount();
                final int listBottom = getHeight() - getListPaddingBottom() -
                        (bottomFadingEdgeShowing ? getArrowScrollPreviewLength() : 0);
                final int ySearchPoint =
                        (selectedView != null && selectedView.getBottom() < listBottom) ?
                                selectedView.getBottom() :
                                listBottom;
                mTempRect.set(0, ySearchPoint, 0, ySearchPoint);
            }
            newFocus = FocusFinder.getInstance().findNextFocusFromRect(this, mTempRect, direction);
        }

        if (newFocus != null) {
            final int positionOfNewFocus = positionOfNewFocus(newFocus);

            // if the focus change is in a different new position, make sure
            // we aren't jumping over another selectable position
            if (getSelectedPosition() != INVALID_POSITION && positionOfNewFocus != getSelectedPosition()) {
                final int selectablePosition = lookForSelectablePositionOnScreen(direction);
                if (selectablePosition != INVALID_POSITION &&
                        ((direction == View.FOCUS_DOWN && selectablePosition < positionOfNewFocus) ||
                        (direction == View.FOCUS_UP && selectablePosition > positionOfNewFocus))) {
                    return null;
                }
            }

            int focusScroll = amountToScrollToNewFocus(direction, newFocus, positionOfNewFocus);

            final int maxScrollAmount = getMaxScrollAmount();
            if (focusScroll < maxScrollAmount) {
                // not moving too far, safe to give next view focus
                newFocus.requestFocus(direction);
                mArrowScrollFocusResult.populate(positionOfNewFocus, focusScroll);
		System.out.println("resp1onse ListView: private ArrowScrollFocusResult arrowScrollFocused(final int direction) { end return if ");
                return mArrowScrollFocusResult;
            } else if (distanceToView(newFocus) < maxScrollAmount){
                // Case to consider:
                // too far to get entire next focusable on screen, but by going
                // max scroll amount, we are getting it at least partially in view,
                // so give it focus and scroll the max ammount.
                newFocus.requestFocus(direction);
                mArrowScrollFocusResult.populate(positionOfNewFocus, maxScrollAmount);
		System.out.println("resp1onse ListView: private ArrowScrollFocusResult arrowScrollFocused(final int direction) { end return if ");
                return mArrowScrollFocusResult;
            }
        }
		System.out.println("resp1onse ListView: private ArrowScrollFocusResult arrowScrollFocused(final int direction) { end return  =1");
        return null;
    }

    /**
     * @param newFocus The view that would have focus.
     * @return the position that contains newFocus
     */
    private int positionOfNewFocus(View newFocus) {
		System.out.println("resp1onse ListView: private int positionOfNewFocus(View newFocus) { start return ");
        final int numChildren = getChildCount();
        for (int i = 0; i < numChildren; i++) {
            final View child = getChildAt(i);
            if (isViewAncestorOf(newFocus, child)) {
                return getFirstVisiblePosition() + i;
            }
        }
		System.out.println("resp1onse ListView: private int positionOfNewFocus(View newFocus) { end return  =1");
        throw new IllegalArgumentException("newFocus is not a child of any of the"
                + " children of the list!");
    }

    /**
     * Return true if child is an ancestor of parent, (or equal to the parent).
     */
    private boolean isViewAncestorOf(View child, View parent) {
		System.out.println("resp1onse ListView: private boolean isViewAncestorOf(View child, View parent) { start return ");
        if (child == parent) {
            return true;
        }

        final ViewParent theParent = child.getParent();
		System.out.println("resp1onse ListView: private boolean isViewAncestorOf(View child, View parent) { end return  =1");
        return (theParent instanceof ViewGroup) && isViewAncestorOf((View) theParent, parent);
    }

    /**
     * Determine how much we need to scroll in order to get newFocus in view.
     * @param direction either {@link android.view.View#FOCUS_UP} or
     *        {@link android.view.View#FOCUS_DOWN}.
     * @param newFocus The view that would take focus.
     * @param positionOfNewFocus The position of the list item containing newFocus
     * @return The amount to scroll.  Note: this is always positive!  Direction
     *   needs to be taken into account when actually scrolling.
     */
    private int amountToScrollToNewFocus(int direction, View newFocus, int positionOfNewFocus) {
		System.out.println("resp1onse ListView: private int amountToScrollToNewFocus(int direction, View newFocus, int positionOfNewFocus) { start return ");
        int amountToScroll = 0;
        newFocus.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(newFocus, mTempRect);
        if (direction == View.FOCUS_UP) {
            if (mTempRect.top < getListPaddingTop()) {
                amountToScroll = getListPaddingTop() - mTempRect.top;
                if (positionOfNewFocus > 0) {
                    amountToScroll += getArrowScrollPreviewLength();
                }
            }
        } else {
            final int listBottom = getHeight() - getListPaddingBottom();
            if (mTempRect.bottom > listBottom) {
                amountToScroll = mTempRect.bottom - listBottom;
                if (positionOfNewFocus < getCount() - 1) {
                    amountToScroll += getArrowScrollPreviewLength();
                }
            }
        }
		System.out.println("resp1onse ListView: private int amountToScrollToNewFocus(int direction, View newFocus, int positionOfNewFocus) { end return  =1");
        return amountToScroll;
    }

    /**
     * Determine the distance to the nearest edge of a view in a particular
     * direction.
     * 
     * @param descendant A descendant of this list.
     * @return The distance, or 0 if the nearest edge is already on screen.
     */
    private int distanceToView(View descendant) {
		System.out.println("resp1onse ListView: private int distanceToView(View descendant) { start return ");
        int distance = 0;
        descendant.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(descendant, mTempRect);
        final int listBottom = getBottom() - getTop() - getListPaddingBottom();
        if (mTempRect.bottom < getListPaddingTop()) {
            distance = getListPaddingTop() - mTempRect.bottom;
        } else if (mTempRect.top > listBottom) {
            distance = mTempRect.top - listBottom;
        }
		System.out.println("resp1onse ListView: private int distanceToView(View descendant) { end return  =1");
        return distance;
    }


    /**
     * Scroll the children by amount, adding a view at the end and removing
     * views that fall off as necessary.
     *
     * @param amount The amount (positive or negative) to scroll.
     */
    private void scrollListItemsBy(int amount) {
		System.out.println("resp1onse ListView: private void scrollListItemsBy(int amount) { start void ");
        offsetChildrenTopAndBottom(amount);

        final int listBottom = getHeight() - getListPaddingBottom();
        final int listTop = getListPaddingTop();
//        final AbsListView.RecycleBin recycleBin = mRecycler;
        final Object recycleBin = getRecycler();

        if (amount < 0) {
            // shifted items up

            // may need to pan views into the bottom space
            int numChildren = getChildCount();
            View last = getChildAt(numChildren - 1);
            while (last.getBottom() < listBottom) {
                final int lastVisiblePosition = getFirstVisiblePosition() + numChildren - 1;
                if (lastVisiblePosition < getCount() - 1) {
                    last = addViewBelow(last, lastVisiblePosition);
                    numChildren++;
                } else {
                    break;
                }
            }

            // may have brought in the last child of the list that is skinnier
            // than the fading edge, thereby leaving space at the end.  need
            // to shift back
            if (last.getBottom() < listBottom) {
                offsetChildrenTopAndBottom(listBottom - last.getBottom());
            }

            // top views may be panned off screen
            View first = getChildAt(0);
            while (first.getBottom() < listTop) {
                AbsListView.LayoutParams layoutParams = (LayoutParams) first.getLayoutParams();
                if (recycler_shouldRecycleViewType(layoutParams)) {
                    detachViewFromParent(first);
//                    recycleBin.addScrapView(first);
                    recycler_addScrapView(first, getFirstVisiblePosition());
                } else {
                    removeViewInLayout(first);
                }
                first = getChildAt(0);
//                mFirstPosition++;
                setFirstPosition(getFirstVisiblePosition() + 1);
            }
        } else {
            // shifted items down
            View first = getChildAt(0);

            // may need to pan views into top
            while ((first.getTop() > listTop) && (getFirstVisiblePosition() > 0)) {
                first = addViewAbove(first, getFirstVisiblePosition());
//                mFirstPosition--;
                setFirstPosition(getFirstVisiblePosition() - 1);
            }

            // may have brought the very first child of the list in too far and
            // need to shift it back
            if (first.getTop() > listTop) {
                offsetChildrenTopAndBottom(listTop - first.getTop());
            }

            int lastIndex = getChildCount() - 1;
            View last = getChildAt(lastIndex);

            // bottom view may be panned off screen
            while (last.getTop() > listBottom) {
                AbsListView.LayoutParams layoutParams = (LayoutParams) last.getLayoutParams();
                if (recycler_shouldRecycleViewType(layoutParams)) {
                    detachViewFromParent(last);
//                    recycleBin.addScrapView(last);
                    recycler_addScrapView(last, getFirstVisiblePosition() + lastIndex);
                    
                } else {
                    removeViewInLayout(last);
                }
                last = getChildAt(--lastIndex);
            }
        }
		System.out.println("resp1onse ListView: private void scrollListItemsBy(int amount) { end void ");
    }

    private View addViewAbove(View theView, int position) {
		System.out.println("resp1onse ListView: private View addViewAbove(View theView, int position) { start return ");
        int abovePosition = position - 1;
        View view = obtainView2(abovePosition, IsScrap_MY);
        int edgeOfNewChild = theView.getTop() - mDividerHeight;
        setupChild(view, abovePosition, edgeOfNewChild, false, getListPaddingLeft(),
                false, IsScrap_MY[0]);
		System.out.println("resp1onse ListView: private View addViewAbove(View theView, int position) { end return  =1");
        return view;
    }

    private View addViewBelow(View theView, int position) {
		System.out.println("resp1onse ListView: private View addViewBelow(View theView, int position) { start return ");
        int belowPosition = position + 1;
        View view = obtainView2(belowPosition, IsScrap_MY);
        int edgeOfNewChild = theView.getBottom() + mDividerHeight;
        setupChild(view, belowPosition, edgeOfNewChild, true, getListPaddingLeft(),
                false, IsScrap_MY[0]);
		System.out.println("resp1onse ListView: private View addViewBelow(View theView, int position) { end return  =1");
        return view;
    }

    /**
     * Indicates that the views created by the ListAdapter can contain focusable
     * items.
     *
     * @param itemsCanFocus true if items can get focus, false otherwise
     */
    public void setItemsCanFocus(boolean itemsCanFocus) {
		System.out.println("resp1onse ListView: public void setItemsCanFocus(boolean itemsCanFocus) { start void ");
        mItemsCanFocus = itemsCanFocus;
        if (!itemsCanFocus) {
            setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        }
		System.out.println("resp1onse ListView: public void setItemsCanFocus(boolean itemsCanFocus) { end void ");
    }

    /**
     * @return Whether the views created by the ListAdapter can contain focusable
     * items.
     */
    public boolean getItemsCanFocus() {
		System.out.println("resp1onse ListView: public boolean getItemsCanFocus() { start return ");
		System.out.println("resp1onse ListView: public boolean getItemsCanFocus() { end return ");
        return mItemsCanFocus;
    }

    /**
     * @hide Pending API council approval.
     */
    @Override
    public boolean isOpaque() {
//		System.out.println("resp1onse ListView: public boolean isOpaque() { start return ");
//		System.out.println("resp1onse ListView: public boolean isOpaque() { end return ");
        return (getCachingStarted() && mIsCacheColorOpaque && mDividerIsOpaque &&
                hasOpaqueScrollbars()) || super.isOpaque();
    }


	@Override
    public void setCacheColorHint(int color) {
		System.out.println("resp1onse ListView: public void setCacheColorHint(int color) { start void ");
        final boolean opaque = (color >>> 24) == 0xFF;
        mIsCacheColorOpaque = opaque;
        if (opaque) {
            if (mDividerPaint == null) {
                mDividerPaint = new Paint();
            }
            mDividerPaint.setColor(color);
        }
        super.setCacheColorHint(color);
		System.out.println("resp1onse ListView: public void setCacheColorHint(int color) { end void ");
    }

    void drawOverscrollHeader(Canvas canvas, Drawable drawable, Rect bounds) {
		System.out.println("resp1onse ListView: void drawOverscrollHeader(Canvas canvas, Drawable drawable, Rect bounds) { start void ");
        final int height = drawable.getMinimumHeight();

        canvas.save();
        canvas.clipRect(bounds);

        final int span = bounds.bottom - bounds.top;
        if (span < height) {
            bounds.top = bounds.bottom - height;
        }

        drawable.setBounds(bounds);
        drawable.draw(canvas);

        canvas.restore();
		System.out.println("resp1onse ListView: void drawOverscrollHeader(Canvas canvas, Drawable drawable, Rect bounds) { end void ");
    }

    void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect bounds) {
		System.out.println("resp1onse ListView: void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect bounds) { start void ");
        final int height = drawable.getMinimumHeight();

        canvas.save();
        canvas.clipRect(bounds);

        final int span = bounds.bottom - bounds.top;
        if (span < height) {
            bounds.bottom = bounds.top + height;
        }

        drawable.setBounds(bounds);
        drawable.draw(canvas);

        canvas.restore();
		System.out.println("resp1onse ListView: void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect bounds) { end void ");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
		System.out.println("resp1onse ListView: protected void dispatchDraw(Canvas canvas) { start void ");
        // Draw the dividers
        final int dividerHeight = mDividerHeight;
        final Drawable overscrollHeader = mOverScrollHeader;
        final Drawable overscrollFooter = mOverScrollFooter;
        final boolean drawOverscrollHeader = overscrollHeader != null;
        final boolean drawOverscrollFooter = overscrollFooter != null;
        final boolean drawDividers = dividerHeight > 0 && mDivider != null;

        if (drawDividers || drawOverscrollHeader || drawOverscrollFooter) {
        	System.out.println("resp1onse ListView: protected void dispatchDraw(Canvas canvas)    drawDividers ");
            // Only modify the top and bottom in the loop, we set the left and right here
            final Rect bounds = mTempRect;
            bounds.left = getPaddingLeft();
            bounds.right = getRight() - getLeft() - getPaddingRight();

            final int count = getChildCount();
            final int headerCount = mHeaderViewInfos.size();
            final int itemCount = getCount();
            final int footerLimit = itemCount - mFooterViewInfos.size() - 1;
            final boolean headerDividers = mHeaderDividersEnabled;
            final boolean footerDividers = mFooterDividersEnabled;
            final int first = getFirstVisiblePosition();
            final boolean areAllItemsSelectable = mAreAllItemsSelectable;
            final ListAdapter adapter = mAdapter_MY;
            // If the list is opaque *and* the background is not, we want to
            // fill a rect where the dividers would be for non-selectable items
            // If the list is opaque and the background is also opaque, we don't
            // need to draw anything since the background will do it for us
            final boolean fillForMissingDividers = drawDividers && isOpaque() && !super.isOpaque();

            if (fillForMissingDividers && mDividerPaint == null && mIsCacheColorOpaque) {
                mDividerPaint = new Paint();
                mDividerPaint.setColor(getCacheColorHint());
            }
            final Paint paint = mDividerPaint;

            final int listBottom = getBottom() - getTop() - getListPaddingBottom() + getScrollY();
            if (!isStackFromBottom()) {
                int bottom = 0;
                
                // Draw top divider or header for overscroll
                final int scrollY = getScrollY();
                if (count > 0 && scrollY < 0) {
                    if (drawOverscrollHeader) {
                        bounds.bottom = 0;
                        bounds.top = scrollY;
                        drawOverscrollHeader(canvas, overscrollHeader, bounds);
                    } else if (drawDividers) {
                        bounds.bottom = 0;
                        bounds.top = -dividerHeight;
                        drawDivider(canvas, bounds, -1);
                    }
                }

                for (int i = 0; i < count; i++) {
                    if ((headerDividers || first + i >= headerCount) &&
                            (footerDividers || first + i < footerLimit)) {
                        View child = getChildAt(i);
                        bottom = child.getBottom();
                        // Don't draw dividers next to items that are not enabled
                        if (drawDividers &&
                                (bottom < listBottom && !(drawOverscrollFooter && i == count - 1))) {
                            if ((areAllItemsSelectable ||
                                    (adapter.isEnabled(first + i) && (i == count - 1 ||
                                            adapter.isEnabled(first + i + 1))))) {
                                bounds.top = bottom;
                                bounds.bottom = bottom + dividerHeight;
                                drawDivider(canvas, bounds, i);
                            } else if (fillForMissingDividers) {
                                bounds.top = bottom;
                                bounds.bottom = bottom + dividerHeight;
                                canvas.drawRect(bounds, paint);
                            }
                        }
                    }
                }

                final int overFooterBottom = getBottom() + getScrollY();
                if (drawOverscrollFooter && first + count == itemCount &&
                        overFooterBottom > bottom) {
                    bounds.top = bottom;
                    bounds.bottom = overFooterBottom;
                    drawOverscrollFooter(canvas, overscrollFooter, bounds);
                }
            } else {
                int top;
                int listTop = getListPaddingTop();

                final int scrollY = getScrollY();

                if (count > 0 && drawOverscrollHeader) {
                    bounds.top = scrollY;
                    bounds.bottom = getChildAt(0).getTop();
                    drawOverscrollHeader(canvas, overscrollHeader, bounds);
                }

                final int start = drawOverscrollHeader ? 1 : 0;
                for (int i = start; i < count; i++) {
                    if ((headerDividers || first + i >= headerCount) &&
                            (footerDividers || first + i < footerLimit)) {
                        View child = getChildAt(i);
                        top = child.getTop();
                        // Don't draw dividers next to items that are not enabled
                        if (drawDividers && top > listTop) {
                            if ((areAllItemsSelectable ||
                                    (adapter.isEnabled(first + i) && (i == count - 1 ||
                                            adapter.isEnabled(first + i + 1))))) {
                                bounds.top = top - dividerHeight;
                                bounds.bottom = top;
                                // Give the method the child ABOVE the divider, so we
                                // subtract one from our child
                                // position. Give -1 when there is no child above the
                                // divider.
                                drawDivider(canvas, bounds, i - 1);
                            } else if (fillForMissingDividers) {
                                bounds.top = top - dividerHeight;
                                bounds.bottom = top;
                                canvas.drawRect(bounds, paint);
                            }
                        }
                    }
                }
                
                if (count > 0 && scrollY > 0) {
                    if (drawOverscrollFooter) {
                        final int absListBottom = getBottom();
                        bounds.top = absListBottom;
                        bounds.bottom = absListBottom + scrollY;
                        drawOverscrollFooter(canvas, overscrollFooter, bounds);
                    } else if (drawDividers) {
                        bounds.top = listBottom;
                        bounds.bottom = listBottom + dividerHeight;
                        drawDivider(canvas, bounds, -1);
                    }
                }
            }
        }

        // Draw the indicators (these should be drawn above the dividers) and children
        super.dispatchDraw(canvas);
		System.out.println("resp1onse ListView: protected void dispatchDraw(Canvas canvas) { end void ");
    }

    /**
     * Draws a divider for the given child in the given bounds.
     *
     * @param canvas The canvas to draw to.
     * @param bounds The bounds of the divider.
     * @param childIndex The index of child (of the View) above the divider.
     *            This will be -1 if there is no child above the divider to be
     *            drawn.
     */
    void drawDivider(Canvas canvas, Rect bounds, int childIndex) {
		System.out.println("resp1onse ListView: void drawDivider(Canvas canvas, Rect bounds, int childIndex) { start void ");
        // This widget draws the same divider for all children
        final Drawable divider = mDivider;
        final boolean clipDivider = mClipDivider;

        if (!clipDivider) {
            divider.setBounds(bounds);
        } else {
            canvas.save();
            canvas.clipRect(bounds);
        }

        divider.draw(canvas);

        if (clipDivider) {
            canvas.restore();
        }
		System.out.println("resp1onse ListView: void drawDivider(Canvas canvas, Rect bounds, int childIndex) { end void ");
    }

    /**
     * Returns the drawable that will be drawn between each item in the list.
     *
     * @return the current drawable drawn between list elements
     */
    public Drawable getDivider() {
		System.out.println("resp1onse ListView: public Drawable getDivider() { start return ");
		System.out.println("resp1onse ListView: public Drawable getDivider() { end return ");
        return mDivider;
    }

    /**
     * Sets the drawable that will be drawn between each item in the list. If the drawable does
     * not have an intrinsic height, you should also call {@link #setDividerHeight(int)}
     *
     * @param divider The drawable to use.
     */
    public void setDivider(Drawable divider) {
		System.out.println("resp1onse ListView: public void setDivider(Drawable divider) { start void ");
        if (divider != null) {
            mDividerHeight = divider.getIntrinsicHeight();
            mClipDivider = divider instanceof ColorDrawable;
        } else {
            mDividerHeight = 0;
            mClipDivider = false;
        }
        mDivider = divider;
        mDividerIsOpaque = divider == null || divider.getOpacity() == PixelFormat.OPAQUE;
        requestLayoutIfNecessary();
		System.out.println("resp1onse ListView: public void setDivider(Drawable divider) { end void ");
    }

    /**
     * @return Returns the height of the divider that will be drawn between each item in the list.
     */
    public int getDividerHeight() {
		System.out.println("resp1onse ListView: public int getDividerHeight() { start return ");
		System.out.println("resp1onse ListView: public int getDividerHeight() { end return ");
        return mDividerHeight;
    }
    
    /**
     * Sets the height of the divider that will be drawn between each item in the list. Calling
     * this will override the intrinsic height as set by {@link #setDivider(Drawable)}
     *
     * @param height The new height of the divider in pixels.
     */
    public void setDividerHeight(int height) {
		System.out.println("resp1onse ListView: public void setDividerHeight(int height) { start void ");
        mDividerHeight = height;
        requestLayoutIfNecessary();
		System.out.println("resp1onse ListView: public void setDividerHeight(int height) { end void ");
    }

	/**
     * Enables or disables the drawing of the divider for header views.
     *
     * @param headerDividersEnabled True to draw the headers, false otherwise.
     *
     * @see #setFooterDividersEnabled(boolean)
     * @see #addHeaderView(android.view.View)
     */
    public void setHeaderDividersEnabled(boolean headerDividersEnabled) {
		System.out.println("resp1onse ListView: public void setHeaderDividersEnabled(boolean headerDividersEnabled) { start void ");
        mHeaderDividersEnabled = headerDividersEnabled;
        invalidate();
		System.out.println("resp1onse ListView: public void setHeaderDividersEnabled(boolean headerDividersEnabled) { end void ");
    }

    /**
     * Enables or disables the drawing of the divider for footer views.
     *
     * @param footerDividersEnabled True to draw the footers, false otherwise.
     *
     * @see #setHeaderDividersEnabled(boolean)
     * @see #addFooterView(android.view.View)
     */
    public void setFooterDividersEnabled(boolean footerDividersEnabled) {
		System.out.println("resp1onse ListView: public void setFooterDividersEnabled(boolean footerDividersEnabled) { start void ");
        mFooterDividersEnabled = footerDividersEnabled;
        invalidate();
		System.out.println("resp1onse ListView: public void setFooterDividersEnabled(boolean footerDividersEnabled) { end void ");
    }
    
    /**
     * Sets the drawable that will be drawn above all other list content.
     * This area can become visible when the user overscrolls the list.
     *
     * @param header The drawable to use
     */
    public void setOverscrollHeader(Drawable header) {
		System.out.println("resp1onse ListView: public void setOverscrollHeader(Drawable header) { start void ");
        mOverScrollHeader = header;
        if (getScrollY() < 0) {
            invalidate();
        }
		System.out.println("resp1onse ListView: public void setOverscrollHeader(Drawable header) { end void ");
    }

    /**
     * @return The drawable that will be drawn above all other list content
     */
    public Drawable getOverscrollHeader() {
		System.out.println("resp1onse ListView: public Drawable getOverscrollHeader() { start return ");
		System.out.println("resp1onse ListView: public Drawable getOverscrollHeader() { end return ");
        return mOverScrollHeader;
    }

    /**
     * Sets the drawable that will be drawn below all other list content.
     * This area can become visible when the user overscrolls the list,
     * or when the list's content does not fully fill the container area.
     *
     * @param footer The drawable to use
     */
    public void setOverscrollFooter(Drawable footer) {
		System.out.println("resp1onse ListView: public void setOverscrollFooter(Drawable footer) { start void ");
        mOverScrollFooter = footer;
        invalidate();
		System.out.println("resp1onse ListView: public void setOverscrollFooter(Drawable footer) { end void ");
    }

    /**
     * @return The drawable that will be drawn below all other list content
     */
    public Drawable getOverscrollFooter() {
		System.out.println("resp1onse ListView: public Drawable getOverscrollFooter() { start return ");
		System.out.println("resp1onse ListView: public Drawable getOverscrollFooter() { end return ");
        return mOverScrollFooter;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		System.out.println("resp1onse ListView: protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) { start void ");
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);

        int closetChildIndex = -1;
        if (gainFocus && previouslyFocusedRect != null) {
            previouslyFocusedRect.offset(getScrollX(), getScrollY());

            final ListAdapter adapter = mAdapter_MY;
            // Don't cache the result of getChildCount or mFirstPosition here,
            // it could change in layoutChildren.
            if (adapter.getCount() < getChildCount() + getFirstVisiblePosition()) {
//                mLayoutMode = LAYOUT_NORMAL;
                setLayoutMode(LAYOUT_NORMAL);
                layoutChildren();
            }

            // figure out which item should be selected based on previously
            // focused rect
            Rect otherRect = mTempRect;
            int minDistance = Integer.MAX_VALUE;
            final int childCount = getChildCount();
            final int firstPosition = getFirstVisiblePosition();

            for (int i = 0; i < childCount; i++) {
                // only consider selectable views
                if (!adapter.isEnabled(firstPosition + i)) {
                    continue;
                }

                View other = getChildAt(i);
                other.getDrawingRect(otherRect);
                offsetDescendantRectToMyCoords(other, otherRect);
                int distance = getDistance(previouslyFocusedRect, otherRect, direction);

                if (distance < minDistance) {
                    minDistance = distance;
                    closetChildIndex = i;
                }
            }
        }

        if (closetChildIndex >= 0) {
            setSelection(closetChildIndex + getFirstVisiblePosition());
        } else {
            requestLayout();
        }
		System.out.println("resp1onse ListView: protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) { end void ");
    }

	/*
     * (non-Javadoc)
     *
     * Children specified in XML are assumed to be header views. After we have
     * parsed them move them out of the children list and into mHeaderViews.
     */
    @Override
    protected void onFinishInflate() {
		System.out.println("resp1onse ListView: protected void onFinishInflate() { start void ");
        super.onFinishInflate();

        int count = getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; ++i) {
                addHeaderView(getChildAt(i));
            }
            removeAllViews();
        }
		System.out.println("resp1onse ListView: protected void onFinishInflate() { end void ");
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//		System.out.println("resp1onse ListView: public boolean onTouchEvent(MotionEvent ev) { start return ");
//        if (mItemsCanFocus && ev.getAction() == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) {
//            // Don't handle edge touches immediately -- they may actually belong to one of our
//            // descendants.
//            return false;
//        }
//		System.out.println("resp1onse ListView: public boolean onTouchEvent(MotionEvent ev) { end return  =1");
//        return super.onTouchEvent(ev);
//    }

    /**
     * @see #setChoiceMode(int)
     *
     * @return The current choice mode
     */
    public int getChoiceMode() {
		System.out.println("resp1onse ListView: public int getChoiceMode() { start return ");
		System.out.println("resp1onse ListView: public int getChoiceMode() { end return ");
        return mChoiceMode;
    }

    /**
     * Defines the choice behavior for the List. By default, Lists do not have any choice behavior
     * ({@link #CHOICE_MODE_NONE}). By setting the choiceMode to {@link #CHOICE_MODE_SINGLE}, the
     * List allows up to one item to  be in a chosen state. By setting the choiceMode to
     * {@link #CHOICE_MODE_MULTIPLE}, the list allows any number of items to be chosen.
     *
     * @param choiceMode One of {@link #CHOICE_MODE_NONE}, {@link #CHOICE_MODE_SINGLE}, or
     * {@link #CHOICE_MODE_MULTIPLE}
     */
    public void setChoiceMode(int choiceMode) {
		System.out.println("resp1onse ListView: public void setChoiceMode(int choiceMode) { start void choiceMode = " + choiceMode);
        mChoiceMode = choiceMode;
        super.setChoiceMode(choiceMode);
        
        if (mChoiceMode != CHOICE_MODE_NONE) {
            if (mCheckStates == null) {
                mCheckStates = new SparseBooleanArray();
            }
            if (mCheckedIdStates == null && mAdapter_MY != null && mAdapter_MY.hasStableIds()) {
                mCheckedIdStates = new LongSparseArray<Boolean>();
            }
        }
		System.out.println("resp1onse ListView: public void setChoiceMode(int choiceMode) { end void ");
    }

    @Override
    public boolean performItemClick(View view, int position, long id) {
		System.out.println("resp1onse ListView: public boolean performItemClick(View view, int position, long id) { start return ");
        boolean handled = false;

        if (mChoiceMode != CHOICE_MODE_NONE) {
            handled = true;

            if (mChoiceMode == CHOICE_MODE_MULTIPLE) {
                boolean newValue = !mCheckStates.get(position, false);
                mCheckStates.put(position, newValue);
                if (mCheckedIdStates != null && mAdapter_MY.hasStableIds()) {
                    if (newValue) {
                        mCheckedIdStates.put(mAdapter_MY.getItemId(position), Boolean.TRUE);
                    } else {
                        mCheckedIdStates.delete(mAdapter_MY.getItemId(position));
                    }
                }
            } else {
                boolean newValue = !mCheckStates.get(position, false);
                if (newValue) {
                    mCheckStates.clear();
                    mCheckStates.put(position, true);
                    if (mCheckedIdStates != null && mAdapter_MY.hasStableIds()) {
                        mCheckedIdStates.clear();
                        mCheckedIdStates.put(mAdapter_MY.getItemId(position), Boolean.TRUE);
                    }
                } 
            }

//            mDataChanged = true;
            setDataChanged(true);
            rememberSyncState();
            requestLayout();
        }

        handled |= super.performItemClick(view, position, id);

		System.out.println("resp1onse ListView: public boolean performItemClick(View view, int position, long id) { end return  =1");
        return handled;
    }


	/**
     * Sets the checked state of the specified position. The is only valid if
     * the choice mode has been set to {@link #CHOICE_MODE_SINGLE} or
     * {@link #CHOICE_MODE_MULTIPLE}.
     * 
     * @param position The item whose checked state is to be checked
     * @param value The new checked state for the item
     */
    public void setItemChecked(int position, boolean value) {
		System.out.println("resp1onse ListView: public void setItemChecked(int position, boolean value) { start void ");
        if (mChoiceMode == CHOICE_MODE_NONE) {
            return;
        }

        if (mChoiceMode == CHOICE_MODE_MULTIPLE) {
            mCheckStates.put(position, value);
            if (mCheckedIdStates != null && mAdapter_MY.hasStableIds()) {
                if (value) {
                    mCheckedIdStates.put(mAdapter_MY.getItemId(position), Boolean.TRUE);
                } else {
                    mCheckedIdStates.delete(mAdapter_MY.getItemId(position));
                }
            }
        } else {
            boolean updateIds = mCheckedIdStates != null && mAdapter_MY.hasStableIds();
            // Clear all values if we're checking something, or unchecking the currently
            // selected item
            if (value || isItemChecked(position)) {
                mCheckStates.clear();
                if (updateIds) {
                    mCheckedIdStates.clear();
                }
            }
            // this may end up selecting the value we just cleared but this way
            // we ensure length of mCheckStates is 1, a fact getCheckedItemPosition relies on
            if (value) {
                mCheckStates.put(position, true);
                if (updateIds) {
                    mCheckedIdStates.put(mAdapter_MY.getItemId(position), Boolean.TRUE);
                }
            }
        }

        // Do not generate a data change while we are in the layout phase
        if (!getInLayout() && !mBlockLayoutRequests) {
//            mDataChanged = true;
        	setDataChanged(true);
            rememberSyncState();
            requestLayout();
        }
		System.out.println("resp1onse ListView: public void setItemChecked(int position, boolean value) { end void ");
    }


	/**
     * Returns the checked state of the specified position. The result is only
     * valid if the choice mode has been set to {@link #CHOICE_MODE_SINGLE}
     * or {@link #CHOICE_MODE_MULTIPLE}.
     *
     * @param position The item whose checked state to return
     * @return The item's checked state or <code>false</code> if choice mode
     *         is invalid
     *
     * @see #setChoiceMode(int)
     */
    public boolean isItemChecked(int position) {
		System.out.println("resp1onse ListView: public boolean isItemChecked(int position) { start return ");
        if (mChoiceMode != CHOICE_MODE_NONE && mCheckStates != null) {
            return mCheckStates.get(position);
        }

		System.out.println("resp1onse ListView: public boolean isItemChecked(int position) { end return  =1");
        return false;
    }

    /**
     * Returns the currently checked item. The result is only valid if the choice
     * mode has been set to {@link #CHOICE_MODE_SINGLE}.
     *
     * @return The position of the currently checked item or
     *         {@link #INVALID_POSITION} if nothing is selected
     *
     * @see #setChoiceMode(int)
     */
    public int getCheckedItemPosition() {
		System.out.println("resp1onse ListView: public int getCheckedItemPosition() { start return ");
        if (mChoiceMode == CHOICE_MODE_SINGLE && mCheckStates != null && mCheckStates.size() == 1) {
            return mCheckStates.keyAt(0);
        }

		System.out.println("resp1onse ListView: public int getCheckedItemPosition() { end return  =1");
        return INVALID_POSITION;
    }

    /**
     * Returns the set of checked items in the list. The result is only valid if
     * the choice mode has not been set to {@link #CHOICE_MODE_NONE}.
     *
     * @return  A SparseBooleanArray which will return true for each call to
     *          get(int position) where position is a position in the list,
     *          or <code>null</code> if the choice mode is set to
     *          {@link #CHOICE_MODE_NONE}.
     */
    public SparseBooleanArray getCheckedItemPositions() {
		System.out.println("resp1onse ListView: public SparseBooleanArray getCheckedItemPositions() { start return ");
        if (mChoiceMode != CHOICE_MODE_NONE) {
            return mCheckStates;
        }
		System.out.println("resp1onse ListView: public SparseBooleanArray getCheckedItemPositions() { end return  =1");
        return null;
    }

    /**
     * Returns the set of checked items ids. The result is only valid if the
     * choice mode has not been set to {@link #CHOICE_MODE_NONE}.
     * 
     * @return A new array which contains the id of each checked item in the
     *         list.
     *         
     * @deprecated Use {@link #getCheckedItemIds()} instead.
     */
    @Deprecated
    public long[] getCheckItemIds() {
        // Use new behavior that correctly handles stable ID mapping.
		System.out.println("resp1onse ListView: public long[] getCheckItemIds() { start return ");
        if (mAdapter_MY != null && mAdapter_MY.hasStableIds()) {
            return getCheckedItemIds();
        }

        // Old behavior was buggy, but would sort of work for adapters without stable IDs.
        // Fall back to it to support legacy apps.
        if (mChoiceMode != CHOICE_MODE_NONE && mCheckStates != null && mAdapter_MY != null) {
            final SparseBooleanArray states = mCheckStates;
            final int count = states.size();
            final long[] ids = new long[count];
            final ListAdapter adapter = mAdapter_MY;

            int checkedCount = 0;
            for (int i = 0; i < count; i++) {
                if (states.valueAt(i)) {
                    ids[checkedCount++] = adapter.getItemId(states.keyAt(i));
                }
            }

            // Trim array if needed. mCheckStates may contain false values
            // resulting in checkedCount being smaller than count.
            if (checkedCount == count) {
                return ids;
            } else {
                final long[] result = new long[checkedCount];
                System.arraycopy(ids, 0, result, 0, checkedCount);

		System.out.println("resp1onse ListView: public long[] getCheckItemIds() { end return if ");
                return result;
            }
        }
		System.out.println("resp1onse ListView: public long[] getCheckItemIds() { end return  =1");
        return new long[0];
    }
    
    /**
     * Returns the set of checked items ids. The result is only valid if the
     * choice mode has not been set to {@link #CHOICE_MODE_NONE} and the adapter
     * has stable IDs. ({@link ListAdapter#hasStableIds()} == {@code true})
     * 
     * @return A new array which contains the id of each checked item in the
     *         list.
     */
    public long[] getCheckedItemIds() {
		System.out.println("resp1onse ListView: public long[] getCheckedItemIds() { start return ");
        if (mChoiceMode == CHOICE_MODE_NONE || mCheckedIdStates == null || mAdapter_MY == null) {
            return new long[0];
        }
        
        final LongSparseArray<Boolean> idStates = mCheckedIdStates;
        final int count = idStates.size();
        final long[] ids = new long[count];
        
        for (int i = 0; i < count; i++) {
            ids[i] = idStates.keyAt(i);
        }
        
		System.out.println("resp1onse ListView: public long[] getCheckedItemIds() { end return  =1");
        return ids;
    }

    /**
     * Clear any choices previously set
     */
    public void clearChoices() {
		System.out.println("resp1onse ListView: public void clearChoices() { start void ");
        if (mCheckStates != null) {
            mCheckStates.clear();
        }
        if (mCheckedIdStates != null) {
            mCheckedIdStates.clear();
        }
		System.out.println("resp1onse ListView: public void clearChoices() { end void ");
    }

    
    private void offsetChildrenTopAndBottom(int offset) {
		System.out.println("resp1onse ListView: private void offsetChildrenTopAndBottom(int offset) { start void ");
    	
		try {
			
			
			if (method_offsetChildrenTopAndBottom == null) {
				
				Method[] declaredMethods = ViewGroup.class.getDeclaredMethods();
				for (Method m : declaredMethods) {
					
					if (m.getName().equals("offsetChildrenTopAndBottom")) {
						method_offsetChildrenTopAndBottom = m;  
						method_offsetChildrenTopAndBottom.setAccessible(true);  
					}
				}
				
//				method_offsetChildrenTopAndBottom = ViewGroup.class.getDeclaredMethod("offsetChildrenTopAndBottom");
//				method_offsetChildrenTopAndBottom.setAccessible(true);  
			}
			
			method_offsetChildrenTopAndBottom.invoke(this, offset); 
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		
		System.out.println("resp1onse ListView: private void offsetChildrenTopAndBottom(int offset) { end void ");
	}
    
    public void positionSelector(int position, View sel) {
		System.out.println("resp1onse ListView: public void positionSelector(int position, View sel) { start void ");
//        final Rect selectorRect = mSelectorRect;
//        selectorRect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
//        positionSelector(selectorRect.left, selectorRect.top, selectorRect.right,
//                selectorRect.bottom);
//
//        final boolean isChildViewEnabled = mIsChildViewEnabled;
//        if (sel.isEnabled() != isChildViewEnabled) {
//            mIsChildViewEnabled = !isChildViewEnabled;
//            refreshDrawableState();
//        }
    	try {
			if (method_positionSelector == null) {
				method_positionSelector = AbsListView.class.getDeclaredMethod("positionSelector");
				method_positionSelector.setAccessible(true);  
			}
			
			method_positionSelector.invoke(this, position, sel); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		System.out.println("resp1onse ListView: public void positionSelector(int position, View sel) { end void ");
    }

	private void setSelectedTop(int top) {
		System.out.println("resp1onse ListView: private void setSelectedTop(int top) { start void ");
//		mSelectedTop = top;
		try {
			if (field_SelectedTop == null) {
				field_SelectedTop = AbsListView.class.getDeclaredField("mSelectedTop");
				field_SelectedTop.setAccessible(true);
			}
			field_SelectedTop.setInt(this, top);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setSelectedTop(int top) { end void ");
	}

    private int getSelectedPosition() {
		System.out.println("resp1onse ListView: private int getSelectedPosition() { start return ");
    	int selectedPosition = 0;
    	try {
			if (field_SelectedPosition == null) {
				field_SelectedPosition = AdapterView.class.getDeclaredField("mSelectedPosition");
				field_SelectedPosition.setAccessible(true);
			}
			selectedPosition = field_SelectedPosition.getInt(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getSelectedPosition() { end return  =1");
		return selectedPosition;
	}
    
   

	private DataSetObserver getAdapterDataSetObserver() {
		
		System.out.println("resp1onse ListView: private DataSetObserver getAdapterDataSetObserver() { start return ");
		Object obj = null;
		try {
			Class classType = Class.forName("android.widget.AbsListView$AdapterDataSetObserver");
			Constructor[] constructors = classType.getDeclaredConstructors();
//			System.out.println("resp1one : " + Arrays.toString(constructors));
//			obj = classType.newInstance();
			constructors[0].setAccessible(true);
//			System.out.println("resp1one : constructors[0] = " + constructors[0].getModifiers());
			obj = constructors[0].newInstance(ListView.this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private DataSetObserver getAdapterDataSetObserver() { end return  =1");
		return (DataSetObserver) obj;
	}


    private void setOldItemCount(int count) {
		System.out.println("resp1onse ListView: private void setOldItemCount(int count) { start void ");
    	try {
			if (field_OldItemCount == null) {
				field_OldItemCount = AdapterView.class.getDeclaredField("mOldItemCount");
				field_OldItemCount.setAccessible(true);
			}
			field_OldItemCount.setInt(this, count);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setOldItemCount(int count) { end void ");
	}

	private void checkFocus() {
		System.out.println("resp1onse ListView: private void checkFocus() { start void ");
		try {
			if (method_checkFocus == null) {
				method_checkFocus = AdapterView.class.getDeclaredMethod("checkFocus");
				method_checkFocus.setAccessible(true);  
			}
			
			method_checkFocus.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void checkFocus() { end void ");
	}

	private void checkSelectionChanged() {
		System.out.println("resp1onse ListView: private void checkSelectionChanged() { start void ");
		try {
			if (method_checkSelectionChanged == null) {
				method_checkSelectionChanged = AdapterView.class.getDeclaredMethod("checkSelectionChanged");
				method_checkSelectionChanged.setAccessible(true);  
			}
			
			method_checkSelectionChanged.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void checkSelectionChanged() { end void ");
	}

	private void setNextSelectedPositionInt(int position) {
		System.out.println("resp1onse ListView: private void setNextSelectedPositionInt(int position) { start void ");
//		try {
//		if (method_setSelectedPositionInt == null) {
////			method_setSelectedPositionInt = AdapterView.class.getDeclaredMethod("setSelectedPositionInt");
//			Method[] declaredMethods = AdapterView.class.getDeclaredMethods();
//			System.out.println("resp1one : " + Arrays.toString(declaredMethods));
////			method_setViewTypeCount = recycleBinClass.getDeclaredMethod("setViewTypeCount");
//			for (Method m : declaredMethods) {
//				if (m.getName().equals("setSelectedPositionInt")) {
//					method_setSelectedPositionInt = m;
//					break;
//				}
//			}
//			method_setSelectedPositionInt.setAccessible(true);  
//		}
//		
//		method_setSelectedPositionInt.invoke(this, position); 
//	} catch (IllegalAccessException e) {
//		e.printStackTrace();
//	} catch (IllegalArgumentException e) {
//		e.printStackTrace();
//	} catch (InvocationTargetException e) {
//		e.printStackTrace();
//	}  
		try {
			if (method_setNextSelectedPositionInt == null) {
//				method_setNextSelectedPositionInt = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt");
				Method[] declaredMethods = AdapterView.class.getDeclaredMethods();
//				System.out.println("resp1one : " + Arrays.toString(declaredMethods));
				for (Method m : declaredMethods) {
					if (m.getName().equals("setNextSelectedPositionInt")) {
						method_setNextSelectedPositionInt = m;
						break;
					}
				}
				
				method_setNextSelectedPositionInt.setAccessible(true);  
			}
			
			method_setNextSelectedPositionInt.invoke(this, position); 
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		
		System.out.println("resp1onse ListView: private void setNextSelectedPositionInt(int position) { end void ");
	}

	public void setSelectedPositionInt(int position) {
		System.out.println("resp1onse ListView: public void setSelectedPositionInt(int position) { start void ");
//		try {
//			if (method_setSelectedPositionInt == null) {
////				method_setSelectedPositionInt = AdapterView.class.getDeclaredMethod("setSelectedPositionInt");
//				Method[] declaredMethods = AdapterView.class.getDeclaredMethods();
//				System.out.println("resp1one : " + Arrays.toString(declaredMethods));
////				method_setViewTypeCount = recycleBinClass.getDeclaredMethod("setViewTypeCount");
//				for (Method m : declaredMethods) {
//					if (m.getName().equals("setSelectedPositionInt")) {
//						method_setSelectedPositionInt = m;
//						break;
//					}
//				}
//				method_setSelectedPositionInt.setAccessible(true);  
//			}
//			
//			method_setSelectedPositionInt.invoke(this, position); 
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}  
		
		try {
			if (field_SelectedPosition == null) {
				field_SelectedPosition = AdapterView.class.getDeclaredField("mSelectedPosition");
				field_SelectedPosition.setAccessible(true);
			}
			field_SelectedPosition.setInt(this, position);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		setSelectedRowId(getItemIdAtPosition(position));
		System.out.println("resp1onse ListView: public void setSelectedPositionInt(int position) { end void ");
	}

    private void setSelectedRowId(long position) {
		System.out.println("resp1onse ListView: private void setSelectedRowId(long position) { start void ");
    	try {
			if (field_SelectedRowId == null) {
				field_SelectedRowId = AdapterView.class.getDeclaredField("mSelectedRowId");
				field_SelectedRowId.setAccessible(true);
			}
			field_SelectedRowId.setLong(this, position);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setSelectedRowId(long position) { end void ");
	}

	private void setFirstPosition(int position) {
		System.out.println("resp1onse ListView: private void setFirstPosition(int position) { start void ");
//		mFirstPosition = pos + 1;
    	try {
			if (field_FirstPosition == null) {
				field_FirstPosition = AdapterView.class.getDeclaredField("mFirstPosition");
				field_FirstPosition.setAccessible(true);
			}
			field_FirstPosition.setInt(this, position);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setFirstPosition(int position) { end void ");
	}
    
    private int reconcileSelectedPosition() {
		System.out.println("resp1onse ListView: private int reconcileSelectedPosition() { start return ");
    	int position = 0;
    	try {
			if (method_reconcileSelectedPosition == null) {
				method_reconcileSelectedPosition = AdapterView.class.getDeclaredMethod("reconcileSelectedPosition");
				method_reconcileSelectedPosition.setAccessible(true);  
			}
			
			position = (Integer) method_reconcileSelectedPosition.invoke(this, position); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private int reconcileSelectedPosition() { end return  =1");
		return position;
	}
    
    private void recycler_setViewTypeCount(int viewTypeCount) {
		System.out.println("resp1onse ListView: private void recycler_setViewTypeCount(int viewTypeCount) { start void ");
//		mRecycler.setViewTypeCount(mAdapter.getViewTypeCount());
		
		try {
//			if (method_setViewTypeCount == null) {
//				Class<?> recycleBinClass = getRecycleBinClass();
//				Method[] declaredMethods = recycleBinClass.getDeclaredMethods();
////				System.out.println("resp1one : " + Arrays.toString(declaredMethods));
////				method_setViewTypeCount = recycleBinClass.getDeclaredMethod("setViewTypeCount");
//				for (Method m : declaredMethods) {
//					if (m.getName().equals("setViewTypeCount")) {
//						method_setViewTypeCount = m;
//						break;
//					}
//				}
//				
//				method_setViewTypeCount.setAccessible(true);  
//			}
			Object obj = getRecycler(); 
			method_setViewTypeCount.invoke(obj, viewTypeCount); 
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		System.out.println("resp1onse ListView: private void recycler_setViewTypeCount(int viewTypeCount) { end void ");
	}
    
	private boolean recycler_shouldRecycleViewType(final AbsListView.LayoutParams layoutParams) {
//		mRecycler.shouldRecycleViewType(
//		        ((LayoutParams) child.getLayoutParams()).viewType);
		
		System.out.println("resp1onse ListView: private boolean recycler_shouldRecycleViewType(final AbsListView.LayoutParams layoutParams) { start return ");
		int viewType = 0;
		try {
			if (field_viewType == null) {
				field_viewType = LayoutParams.class.getDeclaredField("viewType");
				field_viewType.setAccessible(true);
			}
			viewType = field_viewType.getInt(layoutParams);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private boolean recycler_shouldRecycleViewType(final AbsListView.LayoutParams layoutParams) { end return  =1");
		return viewType  >= 0;
	}

	private void recycler_addScrapView(final View child, int position) {
		System.out.println("resp1onse ListView: private void recycler_addScrapView(final View child, int position) { start void ");
//		mRecycler.addScrapView(child);
    	Object obj = getRecycler(); 
		
		try {
//			if (method_addScrapView == null) {
//				method_addScrapView = getRecycleBinClass().getDeclaredMethod("addScrapView");
//				method_addScrapView.setAccessible(true);  
//			}
			
			method_addScrapView.invoke(obj, child, position); 
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		System.out.println("resp1onse ListView: private void recycler_addScrapView(final View child, int position) { end void ");
	}

    public View obtainView2(int i, boolean[] isScrap) {
		System.out.println("resp1onse ListView: public View obtainView2(int i, boolean[] isScrap) { start return ");
    	View view = null;
    	try {
			if (method_obtainView == null) {
//				method_obtainView = AbsListView.class.getDeclaredMethod("obtainView");
				Method[] declaredMethods = AbsListView.class.getDeclaredMethods();
//				System.out.println("resp1one : " + Arrays.toString(declaredMethods));
//				method_setViewTypeCount = recycleBinClass.getDeclaredMethod("setViewTypeCount");
				for (Method m : declaredMethods) {
					if (m.getName().equals("obtainView")) {
						method_obtainView = m;
						break;
					}
				}
				
				method_obtainView.setAccessible(true);  
			}
			
			view = (View) method_obtainView.invoke(this, i, isScrap); 
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: public View obtainView2(int i, boolean[] isScrap) { end return  =1");
		return view;
	}
    

    private View recycler_getActiveView(int position) {
		System.out.println("resp1onse ListView: private View recycler_getActiveView(int position) { start return ");
    	View v = null;
    	Object obj = getRecycler();
		
		try {
//			if (method_getActiveView == null) {
//				method_getActiveView = getRecycleBinClass().getDeclaredMethod("getActiveView");
//				method_getActiveView.setAccessible(true);  
//			}
			
			v = (View) method_getActiveView.invoke(obj, position); 
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		System.out.println("resp1onse ListView: private View recycler_getActiveView(int position) { end return  =1");
		return v;
	}


	private void recycler_scrapActiveViews() {
		System.out.println("resp1onse ListView: private void recycler_scrapActiveViews() { start void ");
		Object obj = getRecycler();
		
		try {
//			if (method_scrapActiveViews == null) {
//				method_scrapActiveViews = getRecycleBinClass().getDeclaredMethod("scrapActiveViews");
//				method_scrapActiveViews.setAccessible(true);  
//			}
			
			method_scrapActiveViews.invoke(obj); 
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		System.out.println("resp1onse ListView: private void recycler_scrapActiveViews() { end void ");
	}

	private void recycler_fillActiveViews(int childCount, int firstPosition) {
		System.out.println("resp1onse ListView: private void recycler_fillActiveViews(int childCount, int firstPosition) { start void ");
		Object obj = getRecycler();
		
		try {
//			if (method_fillActiveViews == null) {
//				method_fillActiveViews = getRecycleBinClass().getDeclaredMethod("fillActiveViews");
//				method_fillActiveViews.setAccessible(true);  
//			}
			
			method_fillActiveViews.invoke(obj, childCount, firstPosition); 
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		System.out.println("resp1onse ListView: private void recycler_fillActiveViews(int childCount, int firstPosition) { end void ");
	}
	
	Class<?> getRecycleBinClass(){
		try {
			if (class_RecycleBin == null) {
				class_RecycleBin = Class.forName("android.widget.AbsListView$RecycleBin");  
			}
			return class_RecycleBin;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getRecycler() {
		
		System.out.println("resp1onse ListView: private Object getRecycler() { start return ");
		Object obj = null;
		try {
			if (field_Recycler == null) {
				field_Recycler = AbsListView.class.getDeclaredField("mRecycler");
				field_Recycler.setAccessible(true);
			}
			obj = field_Recycler.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private Object getRecycler() { end return  =1");
		return obj;
	}

	private int getMotionPosition() {
		System.out.println("resp1onse ListView: private int getMotionPosition() { start return ");
		int position = 0;
		try {
			if (field_MotionPosition == null) {
				field_MotionPosition = AbsListView.class.getDeclaredField("mMotionPosition");
				field_MotionPosition.setAccessible(true);
			}
			position = (Integer) field_MotionPosition.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getMotionPosition() { end return  =1");
		return position;
	}

	private int getTouchMode() {
		System.out.println("resp1onse ListView: private int getTouchMode() { start return ");
		int position = 0;
		try {
			if (field_TouchMode == null) {
				field_TouchMode = AbsListView.class.getDeclaredField("mTouchMode");
				field_TouchMode.setAccessible(true);
			}
			position = (Integer) field_TouchMode.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getTouchMode() { end return  =1");
		return position;
	}

	private void setSelectorRectEmpty() {
		System.out.println("resp1onse ListView: private void setSelectorRectEmpty() { start void ");
//		mSelectorRect.setEmpty();
		Rect mSelectorRect = null;
		try {
			
			if (field_SelectorRect == null) {
				field_SelectorRect = AbsListView.class.getDeclaredField("mSelectorRect");
				field_SelectorRect.setAccessible(true);
			}
			mSelectorRect = (Rect) field_SelectorRect.get(this);
			mSelectorRect.setEmpty();
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		System.out.println("resp1onse ListView: private void setSelectorRectEmpty() { end void ");
	}

    private void updateScrollIndicators() {
		System.out.println("resp1onse ListView: private void updateScrollIndicators() { start void ");
    	try {
			if (method_updateScrollIndicators == null) {
				method_updateScrollIndicators = AbsListView.class.getDeclaredMethod("updateScrollIndicators");
				method_updateScrollIndicators.setAccessible(true);  
			}
			
			method_updateScrollIndicators.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		System.out.println("resp1onse ListView: private void updateScrollIndicators() { end void ");
	}

	private void setNeedSync(boolean b) {
		System.out.println("resp1onse ListView: private void setNeedSync(boolean b) { start void ");
		try {
			if (field_NeedSync == null) {
				field_NeedSync = AdapterView.class.getDeclaredField("mNeedSync");
				field_NeedSync.setAccessible(true);
			}
			field_NeedSync.setBoolean(this, b);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setNeedSync(boolean b) { end void ");
	}

	private int getSpecificTop() {
		System.out.println("resp1onse ListView: private int getSpecificTop() { start return ");
		int position = 0;
		try {
			if (field_SpecificTop == null) {
				field_SpecificTop = AdapterView.class.getDeclaredField("mSpecificTop");
				field_SpecificTop.setAccessible(true);
			}
			position = (Integer) field_SpecificTop.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getSpecificTop() { end return  =1");
		return position;
	}

	private int getSyncPosition() {
		System.out.println("resp1onse ListView: private int getSyncPosition() { start return ");
		int position = 0;
		try {
			if (field_SyncPosition == null) {
				field_SyncPosition = AdapterView.class.getDeclaredField("mSyncPosition");
				field_SyncPosition.setAccessible(true);
			}
			position = (Integer) field_SyncPosition.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getSyncPosition() { end return  =1");
		return position;
	}

	private boolean isDataChanged() {
		System.out.println("resp1onse ListView: private boolean isDataChanged() { start return ");
		boolean b = false;
		try {
			if (field_DataChanged == null) {
				field_DataChanged = AdapterView.class.getDeclaredField("mDataChanged");
				field_DataChanged.setAccessible(true);
			}
			b = (Boolean) field_DataChanged.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private boolean isDataChanged() { end return  =1");
		return b;
	}


    private boolean getCachingStarted() {
//		System.out.println("resp1onse ListView: private boolean getCachingStarted() { start return ");
    	boolean b = false;
		try {
			if (field_CachingStarted == null) {
				field_CachingStarted = AbsListView.class.getDeclaredField("mCachingStarted");
				field_CachingStarted.setAccessible(true);
			}
			b = (Boolean) field_CachingStarted.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
//		System.out.println("resp1onse ListView: private boolean getCachingStarted() { end return  =1");
		return b;
	}

	private int getWidthMeasureSpec() {
		System.out.println("resp1onse ListView: private int getWidthMeasureSpec() { start return ");
		int position = 0;
		try {
			if (field_WidthMeasureSpec == null) {
				field_WidthMeasureSpec = AbsListView.class.getDeclaredField("mWidthMeasureSpec");
				field_WidthMeasureSpec.setAccessible(true);
			}
			position = (Integer) field_WidthMeasureSpec.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private int getWidthMeasureSpec() { end return  =1");
		return position;
	}

	private boolean shouldShowSelector() {
		System.out.println("resp1onse ListView: private boolean shouldShowSelector() { start return ");
		boolean b = false;
    	try {
			if (method_shouldShowSelector == null) {
				method_shouldShowSelector = AdapterView.class.getDeclaredMethod("shouldShowSelector");
				method_shouldShowSelector.setAccessible(true);  
			}
			
			b = (Boolean) method_shouldShowSelector.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private boolean shouldShowSelector() { end return  =1");
		return b;
	}
	

    private void rememberSyncState() {
		System.out.println("resp1onse ListView: private void rememberSyncState() { start void ");
    	try {
			if (method_rememberSyncState == null) {
				method_rememberSyncState = AdapterView.class.getDeclaredMethod("rememberSyncState");
				method_rememberSyncState.setAccessible(true);  
			}
			
			method_rememberSyncState.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private void rememberSyncState() { end void ");
	}

	private void setDataChanged(boolean b) {
		System.out.println("resp1onse ListView: private void setDataChanged(boolean b) { start void ");
		try {
			if (field_DataChanged == null) {
				field_DataChanged = AdapterView.class.getDeclaredField("mDataChanged");
				field_DataChanged.setAccessible(true);
			}
			field_DataChanged.set(this, b);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setDataChanged(boolean b) { end void ");
	}


    private int getDistance(Rect previouslyFocusedRect, Rect otherRect, int direction) {
		System.out.println("resp1onse ListView: private int getDistance(Rect previouslyFocusedRect, Rect otherRect, int direction) { start return ");
    	int position = 0;
    	try {
			if (method_getDistance == null) {
				method_getDistance = AbsListView.class.getDeclaredMethod("getDistance");
				method_getDistance.setAccessible(true);  
			}
			
			position = (Integer) method_getDistance.invoke(this, previouslyFocusedRect, otherRect, direction); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private int getDistance(Rect previouslyFocusedRect, Rect otherRect, int direction) { end return  =1");
		return position;
	}
    

    private void requestLayoutIfNecessary() {
		System.out.println("resp1onse ListView: private void requestLayoutIfNecessary() { start void ");
    	if (getChildCount() > 0) {
            resetList();
            requestLayout();
            invalidate();
        }
		System.out.println("resp1onse ListView: private void requestLayoutIfNecessary() { end void ");
	}

    private void hideSelector() {
		System.out.println("resp1onse ListView: private void hideSelector() { start void ");
    	try {
			if (method_hideSelector == null) {
				method_hideSelector = AbsListView.class.getDeclaredMethod("hideSelector");
				method_hideSelector.setAccessible(true);  
			}
			
			method_hideSelector.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private void hideSelector() { end void ");
	}

	private void setResurrectToPosition(int invalidPosition) {
		System.out.println("resp1onse ListView: private void setResurrectToPosition(int invalidPosition) { start void ");
		try {
			if (field_ResurrectToPosition == null) {
				field_ResurrectToPosition = AbsListView.class.getDeclaredField("mResurrectToPosition");
				field_ResurrectToPosition.setAccessible(true);
			}
			field_ResurrectToPosition.set(this, invalidPosition);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setResurrectToPosition(int invalidPosition) { end void ");
	}

    private void setWidthMeasureSpec(int widthMeasureSpec) {
		System.out.println("resp1onse ListView: private void setWidthMeasureSpec(int widthMeasureSpec) { start void ");
//      mWidthMeasureSpec = widthMeasureSpec; 
    	try {
			if (field_WidthMeasureSpec == null) {
				field_WidthMeasureSpec = AbsListView.class.getDeclaredField("mWidthMeasureSpec");
				field_WidthMeasureSpec.setAccessible(true);
			}
			field_WidthMeasureSpec.set(this, widthMeasureSpec);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private void setWidthMeasureSpec(int widthMeasureSpec) { end void ");
	}
    

    private void setSyncRowId(long itemId) {
		System.out.println("resp1onse ListView: private void setSyncRowId(long itemId) { start void ");
    	try {
			if (field_SyncRowId == null) {
				field_SyncRowId = AdapterView.class.getDeclaredField("mSyncRowId");
				field_SyncRowId.setAccessible(true);
			}
			field_SyncRowId.set(this, itemId);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setSyncRowId(long itemId) { end void ");
	}

	private boolean isNeedSync() {
		System.out.println("resp1onse ListView: private boolean isNeedSync() { start return ");
		boolean b = false;
		try {
			if (field_NeedSync == null) {
				field_NeedSync = AdapterView.class.getDeclaredField("mNeedSync");
				field_NeedSync.setAccessible(true);
			}
			b = (Boolean) field_NeedSync.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private boolean isNeedSync() { end return  =1");
		return b;
	}

	private void setSpecificTop(int i) {
		System.out.println("resp1onse ListView: private void setSpecificTop(int i) { start void ");
		try {
			if (field_SpecificTop == null) {
				field_SpecificTop = AdapterView.class.getDeclaredField("mSpecificTop");
				field_SpecificTop.setAccessible(true);
			}
			field_SpecificTop.set(this, i);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setSpecificTop(int i) { end void ");
	}

	private void setSyncPosition(int position) {
		System.out.println("resp1onse ListView: private void setSyncPosition(int position) { start void ");
		try {
			if (field_SyncPosition == null) {
				field_SyncPosition = AdapterView.class.getDeclaredField("mSyncPosition");
				field_SyncPosition.setAccessible(true);
			}
			field_SyncPosition.set(this, position);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setSyncPosition(int position) { end void ");
	}

    private boolean hasOpaqueScrollbars() {
		System.out.println("resp1onse ListView: private boolean hasOpaqueScrollbars() { start return ");
    	boolean b = false;
		
		try {
			if (method_hasOpaqueScrollbars == null) {
				method_hasOpaqueScrollbars = View.class.getDeclaredMethod("mhasOpaqueScrollbars");
				method_hasOpaqueScrollbars.setAccessible(true);
			}
			b = (Boolean) method_hasOpaqueScrollbars.invoke(this);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	
		System.out.println("resp1onse ListView: private boolean hasOpaqueScrollbars() { end return  =1");
		return b;
	}

    private void setInLayout(boolean b) {
		System.out.println("resp1onse ListView: private void setInLayout(boolean b) { start void ");
    	try {
			if (field_InLayout == null) {
				field_InLayout = AdapterView.class.getDeclaredField("mInLayout");
				field_InLayout.setAccessible(true);
			}
			field_InLayout.set(this, b);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setInLayout(boolean b) { end void ");
	}

    private void invokeOnItemScrollListener() {
		System.out.println("resp1onse ListView: private void invokeOnItemScrollListener() { start void ");
    	try {
			if (method_invokeOnItemScrollListener == null) {
				method_invokeOnItemScrollListener = AbsListView.class.getDeclaredMethod("invokeOnItemScrollListener");
				method_invokeOnItemScrollListener.setAccessible(true);  
			}
			
			method_invokeOnItemScrollListener.invoke(this); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void invokeOnItemScrollListener() { end void ");
	}

    private boolean sendToTextFilter(int keyCode, int count, KeyEvent event) {
		System.out.println("resp1onse ListView: private boolean sendToTextFilter(int keyCode, int count, KeyEvent event) { start return ");
    	boolean b = false;
    	try {
			if (method_sendToTextFilter == null) {
				method_sendToTextFilter = AbsListView.class.getDeclaredMethod("msendToTextFilter");
				method_sendToTextFilter.setAccessible(true);  
			}
			
			b = (Boolean) method_sendToTextFilter.invoke(this, keyCode, count, event); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private boolean sendToTextFilter(int keyCode, int count, KeyEvent event) { end return  =1");
		return b;
	}

    private boolean getInLayout() {
		System.out.println("resp1onse ListView: private boolean getInLayout() { start return ");
    	boolean b = false;
		try {
			if (field_InLayout == null) {
				field_InLayout = AdapterView.class.getDeclaredField("mInLayout");
				field_InLayout.setAccessible(true);
			}
			b = (Boolean) field_InLayout.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("resp1onse ListView: private boolean getInLayout() { end return  =1");
		return b;
	}
    
    private void setIsScrap() {
		System.out.println("resp1onse ListView: private void setIsScrap() { start void ");
    	
		try {
			if (field_IsScrap == null) {
				field_IsScrap = AbsListView.class.getDeclaredField("mIsScrap");
				field_IsScrap.setAccessible(true);
			}
			IsScrap_MY = (boolean[]) field_IsScrap.get(this);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("resp1onse ListView: private void setIsScrap() { end void ");
	}

    
    

   	private void initRecycleBinMethod() {
		System.out.println("resp1onse ListView: private void initRecycleBinMethod() { start void ");
		Class<?> recycleBinClass = getRecycleBinClass();
		Method[] declaredMethods = recycleBinClass.getDeclaredMethods();
		for (Method m : declaredMethods) {
			
			if (m.getName().equals("getActiveView")) {
				method_getActiveView = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("scrapActiveViews")) {
				method_scrapActiveViews = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("setViewTypeCount")) {
				method_setViewTypeCount = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("addScrapView")) {
				method_addScrapView = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("fillActiveViews")) {
				method_fillActiveViews = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("getScrapView")) {
				method_getScrapView = m;
				m.setAccessible(true);  
			}
		}
   		
		System.out.println("resp1onse ListView: private void initRecycleBinMethod() { end void ");
   	}
   	
   	View getScrapView(int position){
   		View v = null;
    	try {
			v = (View) method_getScrapView.invoke(getRecycler(), position); 
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		System.out.println("resp1onse ListView: private boolean sendToTextFilter(int keyCode, int count, KeyEvent event) { end return  =1");
		return v;
   		
   	}

    
}
