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

import com.dzl.test.util.ReflectUtilView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Adapter;

/**
 * An AdapterView is a view whose children are determined by an {@link Adapter}.
 *
 * <p>
 * See {@link ListView}, {@link GridView}, {@link Spinner2} and
 *      {@link Gallery} for commonly used subclasses of AdapterView.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using AdapterView, read the
 * <a href="{@docRoot}guide/topics/ui/binding.html">Binding to Data with AdapterView</a>
 * developer guide.</p></div>
 */
@SuppressLint("NewApi")
public abstract class AdapterView2<T extends Adapter> extends ViewGroup {

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the adapter does not want the item's view recycled.
     */
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the item is a header or footer.
     */
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;

    /**
     * The position of the first child displayed
     */
    @ViewDebug.ExportedProperty(category = "scrolling")
    int mFirstPosition = 0;

    /**
     * The offset in pixels from the top of the AdapterView to the top
     * of the view to select during the next layout.
     */
    int mSpecificTop;

    /**
     * Position from which to start looking for mSyncRowId
     */
    int mSyncPosition;

    /**
     * Row id to look for when data has changed
     */
    long mSyncRowId = INVALID_ROW_ID;

    /**
     * Height of the view when mSyncPosition and mSyncRowId where set
     */
    long mSyncHeight;

    /**
     * True if we need to sync to mSyncRowId
     */
    boolean mNeedSync = false;

    /**
     * Indicates whether to sync based on the selection or position. Possible
     * values are {@link #SYNC_SELECTED_POSITION} or
     * {@link #SYNC_FIRST_POSITION}.
     */
    int mSyncMode;

    /**
     * Our height after the last layout
     */
    private int mLayoutHeight;

    /**
     * Sync based on the selected child
     */
    static final int SYNC_SELECTED_POSITION = 0;

    /**
     * Sync based on the first child displayed
     */
    static final int SYNC_FIRST_POSITION = 1;

    /**
     * Maximum amount of time to spend in {@link #findSyncPosition()}
     */
    static final int SYNC_MAX_DURATION_MILLIS = 100;

    /**
     * Indicates that this view is currently being laid out.
     */
    boolean mInLayout = false;

    /**
     * The listener that receives notifications when an item is selected.
     */
    OnItemSelectedListener mOnItemSelectedListener;

    /**
     * The listener that receives notifications when an item is clicked.
     */
    OnItemClickListener mOnItemClickListener;

    /**
     * The listener that receives notifications when an item is long clicked.
     */
    OnItemLongClickListener mOnItemLongClickListener;

    /**
     * True if the data has changed since the last layout
     */
    boolean mDataChanged;

    /**
     * The position within the adapter's data set of the item to select
     * during the next layout.
     */
    @ViewDebug.ExportedProperty(category = "list")
    int mNextSelectedPosition = INVALID_POSITION;

    /**
     * The item id of the item to select during the next layout.
     */
    long mNextSelectedRowId = INVALID_ROW_ID;

    /**
     * The position within the adapter's data set of the currently selected item.
     */
    @ViewDebug.ExportedProperty(category = "list")
    int mSelectedPosition = INVALID_POSITION;

    /**
     * The item id of the currently selected item.
     */
    long mSelectedRowId = INVALID_ROW_ID;

    /**
     * View to show if there are no items to show.
     */
    private View mEmptyView;

    /**
     * The number of items in the current adapter.
     */
    @ViewDebug.ExportedProperty(category = "list")
    int mItemCount;

    /**
     * The number of items in the adapter before a data changed event occurred.
     */
    int mOldItemCount;

    /**
     * Represents an invalid position. All valid positions are in the range 0 to 1 less than the
     * number of items in the current adapter.
     */
    public static final int INVALID_POSITION = -1;

    /**
     * Represents an empty or invalid row id
     */
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;

    /**
     * The last selected position we used when notifying
     */
    int mOldSelectedPosition = INVALID_POSITION;
    
    /**
     * The id of the last selected position we used when notifying
     */
    long mOldSelectedRowId = INVALID_ROW_ID;

    /**
     * Indicates what focusable state is requested when calling setFocusable().
     * In addition to this, this view has other criteria for actually
     * determining the focusable state (such as whether its empty or the text
     * filter is shown).
     *
     * @see #setFocusable(boolean)
     * @see #checkFocus()
     */
    private boolean mDesiredFocusableState;
    private boolean mDesiredFocusableInTouchModeState;

    private SelectionNotifier mSelectionNotifier;
    /**
     * When set to true, calls to requestLayout() will not propagate up the parent hierarchy.
     * This is used to layout the children during a layout pass.
     */
    boolean mBlockLayoutRequests = false;

	Context mContext;

    public AdapterView2(Context context) {
        this(context, null);
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context) { start constructor ");
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context) { end constructor ");
    }

    public AdapterView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context, AttributeSet attrs) { start constructor ");
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context, AttributeSet attrs) { end constructor ");
    }

    public AdapterView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context, AttributeSet attrs, int defStyleAttr) { start constructor ");
        mContext = context;
//    }
//
//    public AdapterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);

        // If not explicitly specified this view is important for accessibility.
        if (getImportantForAccessibility() == IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
            setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
        }
		System.out.println("resp1onse AdapterView2: public AdapterView2(Context context, AttributeSet attrs, int defStyleAttr) { end constructor ");
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * AdapterView has been clicked.
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param parent The AdapterView where the click happened.
         * @param view The view within the AdapterView that was clicked (this
         *            will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         * @param id The row id of the item that was clicked.
         */
        void onItemClick(AdapterView2<?> parent, View view, int position, long id);
    }

    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been clicked.
     *
     * @param listener The callback that will be invoked.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
		System.out.println("resp1onse AdapterView2: public void setOnItemClickListener(OnItemClickListener listener) { start void ");
        mOnItemClickListener = listener;
		System.out.println("resp1onse AdapterView2: public void setOnItemClickListener(OnItemClickListener listener) { end void ");
    }

    /**
     * @return The callback to be invoked with an item in this AdapterView has
     *         been clicked, or null id no callback has been set.
     */
    public final OnItemClickListener getOnItemClickListener() {
		System.out.println("resp1onse AdapterView2: public final OnItemClickListener getOnItemClickListener() { start return ");
		System.out.println("resp1onse AdapterView2: public final OnItemClickListener getOnItemClickListener() { end return ");
        return mOnItemClickListener;
    }

    /**
     * Call the OnItemClickListener, if it is defined. Performs all normal
     * actions associated with clicking: reporting accessibility event, playing
     * a sound, etc.
     *
     * @param view The view within the AdapterView that was clicked.
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     * @return True if there was an assigned OnItemClickListener that was
     *         called, false otherwise is returned.
     */
    public boolean performItemClick(View view, int position, long id) {
		System.out.println("resp1onse AdapterView2: public boolean performItemClick(View view, int position, long id) { start return ");
        if (mOnItemClickListener != null) {
            playSoundEffect(SoundEffectConstants.CLICK);
            mOnItemClickListener.onItemClick(this, view, position, id);
            if (view != null) {
                view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
            }
		System.out.println("resp1onse AdapterView2: public boolean performItemClick(View view, int position, long id) { end return if ");
            return true;
        }

		System.out.println("resp1onse AdapterView2: public boolean performItemClick(View view, int position, long id) { end return  =1");
        return false;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * view has been clicked and held.
     */
    public interface OnItemLongClickListener {
        /**
         * Callback method to be invoked when an item in this view has been
         * clicked and held.
         *
         * Implementers can call getItemAtPosition(position) if they need to access
         * the data associated with the selected item.
         *
         * @param parent The AbsListView where the click happened
         * @param view The view within the AbsListView that was clicked
         * @param position The position of the view in the list
         * @param id The row id of the item that was clicked
         *
         * @return true if the callback consumed the long click, false otherwise
         */
        boolean onItemLongClick(AdapterView2<?> parent, View view, int position, long id);
    }


    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been clicked and held
     *
     * @param listener The callback that will run
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		System.out.println("resp1onse AdapterView2: public void setOnItemLongClickListener(OnItemLongClickListener listener) { start void ");
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        mOnItemLongClickListener = listener;
		System.out.println("resp1onse AdapterView2: public void setOnItemLongClickListener(OnItemLongClickListener listener) { end void ");
    }

    /**
     * @return The callback to be invoked with an item in this AdapterView has
     *         been clicked and held, or null id no callback as been set.
     */
    public final OnItemLongClickListener getOnItemLongClickListener() {
		System.out.println("resp1onse AdapterView2: public final OnItemLongClickListener getOnItemLongClickListener() { start return ");
		System.out.println("resp1onse AdapterView2: public final OnItemLongClickListener getOnItemLongClickListener() { end return ");
        return mOnItemLongClickListener;
    }

    /**
     * Interface definition for a callback to be invoked when
     * an item in this view has been selected.
     */
    public interface OnItemSelectedListener {
        /**
         * <p>Callback method to be invoked when an item in this view has been
         * selected. This callback is invoked only when the newly selected
         * position is different from the previously selected position or if
         * there was no selected item.</p>
         *
         * Impelmenters can call getItemAtPosition(position) if they need to access the
         * data associated with the selected item.
         *
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        void onItemSelected(AdapterView2<?> parent, View view, int position, long id);

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is activated
         * or when the adapter becomes empty.
         *
         * @param parent The AdapterView that now contains no selected item.
         */
        void onNothingSelected(AdapterView2<?> parent);
    }


    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been selected.
     *
     * @param listener The callback that will run
     */
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		System.out.println("resp1onse AdapterView2: public void setOnItemSelectedListener(OnItemSelectedListener listener) { start void ");
        mOnItemSelectedListener = listener;
		System.out.println("resp1onse AdapterView2: public void setOnItemSelectedListener(OnItemSelectedListener listener) { end void ");
    }

    public final OnItemSelectedListener getOnItemSelectedListener() {
		System.out.println("resp1onse AdapterView2: public final OnItemSelectedListener getOnItemSelectedListener() { start return ");
		System.out.println("resp1onse AdapterView2: public final OnItemSelectedListener getOnItemSelectedListener() { end return ");
        return mOnItemSelectedListener;
    }

    /**
     * Extra menu information provided to the
     * {@link android.view.View.OnCreateContextMenuListener#onCreateContextMenu(ContextMenu, View, ContextMenuInfo) }
     * callback when a context menu is brought up for this AdapterView.
     *
     */
    public static class AdapterContextMenuInfo implements ContextMenu.ContextMenuInfo {

        public AdapterContextMenuInfo(View targetView, int position, long id) {
            this.targetView = targetView;
		System.out.println("resp1onse AdapterView2: public AdapterContextMenuInfo(View targetView, int position, long id) { start constructor ");
            this.position = position;
            this.id = id;
		System.out.println("resp1onse AdapterView2: public AdapterContextMenuInfo(View targetView, int position, long id) { end constructor ");
        }

        /**
         * The child view for which the context menu is being displayed. This
         * will be one of the children of this AdapterView.
         */
        public View targetView;

        /**
         * The position in the adapter for which the context menu is being
         * displayed.
         */
        public int position;

        /**
         * The row id of the item for which the context menu is being displayed.
         */
        public long id;
    }

    /**
     * Returns the adapter currently associated with this widget.
     *
     * @return The adapter used to provide this view's content.
     */
    public abstract T getAdapter();

    /**
     * Sets the adapter that provides the data and the views to represent the data
     * in this widget.
     *
     * @param adapter The adapter to use to create this view's content.
     */
    public abstract void setAdapter(T adapter);

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param child Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void addView(View child) {
		System.out.println("resp1onse AdapterView2: public void addView(View child) { start void ");
		System.out.println("resp1onse AdapterView2: public void addView(View child) { end void ");
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param child Ignored.
     * @param index Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void addView(View child, int index) {
		System.out.println("resp1onse AdapterView2: public void addView(View child, int index) { start void ");
		System.out.println("resp1onse AdapterView2: public void addView(View child, int index) { end void ");
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param child Ignored.
     * @param params Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void addView(View child, LayoutParams params) {
		System.out.println("resp1onse AdapterView2: public void addView(View child, LayoutParams params) { start void ");
		System.out.println("resp1onse AdapterView2: public void addView(View child, LayoutParams params) { end void ");
        throw new UnsupportedOperationException("addView(View, LayoutParams) "
                + "is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param child Ignored.
     * @param index Ignored.
     * @param params Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void addView(View child, int index, LayoutParams params) {
		System.out.println("resp1onse AdapterView2: public void addView(View child, int index, LayoutParams params) { start void ");
		System.out.println("resp1onse AdapterView2: public void addView(View child, int index, LayoutParams params) { end void ");
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) "
                + "is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param child Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void removeView(View child) {
		System.out.println("resp1onse AdapterView2: public void removeView(View child) { start void ");
		System.out.println("resp1onse AdapterView2: public void removeView(View child) { end void ");
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @param index Ignored.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void removeViewAt(int index) {
		System.out.println("resp1onse AdapterView2: public void removeViewAt(int index) { start void ");
		System.out.println("resp1onse AdapterView2: public void removeViewAt(int index) { end void ");
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException when called.
     *
     * @throws UnsupportedOperationException Every time this method is invoked.
     */
    @Override
    public void removeAllViews() {
		System.out.println("resp1onse AdapterView2: public void removeAllViews() { start void ");
		System.out.println("resp1onse AdapterView2: public void removeAllViews() { end void ");
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//		System.out.println("resp1onse AdapterView2: protected void onLayout(boolean changed, int left, int top, int right, int bottom) { start void ");
        mLayoutHeight = getHeight();
//		System.out.println("resp1onse AdapterView2: protected void onLayout(boolean changed, int left, int top, int right, int bottom) { end void ");
    }

    /**
     * Return the position of the currently selected item within the adapter's data set
     *
     * @return int Position (starting at 0), or {@link #INVALID_POSITION} if there is nothing selected.
     */
    @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
		System.out.println("resp1onse AdapterView2: public int getSelectedItemPosition() { start return ");
		System.out.println("resp1onse AdapterView2: public int getSelectedItemPosition() { end return ");
        return mNextSelectedPosition;
    }

    /**
     * @return The id corresponding to the currently selected item, or {@link #INVALID_ROW_ID}
     * if nothing is selected.
     */
    @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
		System.out.println("resp1onse AdapterView2: public long getSelectedItemId() { start return ");
		System.out.println("resp1onse AdapterView2: public long getSelectedItemId() { end return ");
        return mNextSelectedRowId;
    }

    /**
     * @return The view corresponding to the currently selected item, or null
     * if nothing is selected
     */
    public abstract View getSelectedView();

    /**
     * @return The data corresponding to the currently selected item, or
     * null if there is nothing selected.
     */
    public Object getSelectedItem() {
		System.out.println("resp1onse AdapterView2: public Object getSelectedItem() { start return ");
        T adapter = getAdapter();
        int selection = getSelectedItemPosition();
        if (adapter != null && adapter.getCount() > 0 && selection >= 0) {
            return adapter.getItem(selection);
        } else {
            return null;
        }
    }

    /**
     * @return The number of items owned by the Adapter associated with this
     *         AdapterView. (This is the number of data items, which may be
     *         larger than the number of visible views.)
     */
    @ViewDebug.CapturedViewProperty
    public int getCount() {
		System.out.println("resp1onse AdapterView2: public int getCount() { start return ");
		System.out.println("resp1onse AdapterView2: public int getCount() { end return ");
        return mItemCount;
    }

    /**
     * Get the position within the adapter's data set for the view, where view is a an adapter item
     * or a descendant of an adapter item.
     *
     * @param view an adapter item, or a descendant of an adapter item. This must be visible in this
     *        AdapterView at the time of the call.
     * @return the position within the adapter's data set of the view, or {@link #INVALID_POSITION}
     *         if the view does not correspond to a list item (or it is not currently visible).
     */
    public int getPositionForView(View view) {
		System.out.println("resp1onse AdapterView2: public int getPositionForView(View view) { start return ");
        View listItem = view;
        try {
            View v;
            while (!(v = (View) listItem.getParent()).equals(this)) {
                listItem = v;
            }
        } catch (ClassCastException e) {
            // We made it up to the window without find this list view
            return INVALID_POSITION;
        }

        // Search the children for the list item
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).equals(listItem)) {
                return mFirstPosition + i;
            }
        }

        // Child not found!
		System.out.println("resp1onse AdapterView2: public int getPositionForView(View view) { end return  =1");
        return INVALID_POSITION;
    }

    /**
     * Returns the position within the adapter's data set for the first item
     * displayed on screen.
     *
     * @return The position within the adapter's data set
     */
    public int getFirstVisiblePosition() {
		System.out.println("resp1onse AdapterView2: public int getFirstVisiblePosition() { start return ");
		System.out.println("resp1onse AdapterView2: public int getFirstVisiblePosition() { end return ");
        return mFirstPosition;
    }

    /**
     * Returns the position within the adapter's data set for the last item
     * displayed on screen.
     *
     * @return The position within the adapter's data set
     */
    public int getLastVisiblePosition() {
		System.out.println("resp1onse AdapterView2: public int getLastVisiblePosition() { start return ");
		System.out.println("resp1onse AdapterView2: public int getLastVisiblePosition() { end return ");
        return mFirstPosition + getChildCount() - 1;
    }

    /**
     * Sets the currently selected item. To support accessibility subclasses that
     * override this method must invoke the overriden super method first.
     *
     * @param position Index (starting at 0) of the data item to be selected.
     */
    public abstract void setSelection(int position);

    /**
     * Sets the view to show if the adapter is empty
     */
//    @android.view.RemotableViewMethod
    public void setEmptyView(View emptyView) {
		System.out.println("resp1onse AdapterView2: public void setEmptyView(View emptyView) { start void ");
        mEmptyView = emptyView;

        // If not explicitly specified this view is important for accessibility.
        if (emptyView != null
                && emptyView.getImportantForAccessibility() == IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
            emptyView.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
        }

        final T adapter = getAdapter();
        final boolean empty = ((adapter == null) || adapter.isEmpty());
        updateEmptyStatus(empty);
		System.out.println("resp1onse AdapterView2: public void setEmptyView(View emptyView) { end void ");
    }

    /**
     * When the current adapter is empty, the AdapterView can display a special view
     * called the empty view. The empty view is used to provide feedback to the user
     * that no data is available in this AdapterView.
     *
     * @return The view to show if the adapter is empty.
     */
    public View getEmptyView() {
		System.out.println("resp1onse AdapterView2: public View getEmptyView() { start return ");
		System.out.println("resp1onse AdapterView2: public View getEmptyView() { end return ");
        return mEmptyView;
    }

    /**
     * Indicates whether this view is in filter mode. Filter mode can for instance
     * be enabled by a user when typing on the keyboard.
     *
     * @return True if the view is in filter mode, false otherwise.
     */
    boolean isInFilterMode() {
        return false;
    }

    @Override
    public void setFocusable(boolean focusable) {
		System.out.println("resp1onse AdapterView2: public void setFocusable(boolean focusable) { start void ");
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;

        mDesiredFocusableState = focusable;
        if (!focusable) {
            mDesiredFocusableInTouchModeState = false;
        }

        super.setFocusable(focusable && (!empty || isInFilterMode()));
		System.out.println("resp1onse AdapterView2: public void setFocusable(boolean focusable) { end void ");
    }

    @Override
    public void setFocusableInTouchMode(boolean focusable) {
		System.out.println("resp1onse AdapterView2: public void setFocusableInTouchMode(boolean focusable) { start void ");
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;

        mDesiredFocusableInTouchModeState = focusable;
        if (focusable) {
            mDesiredFocusableState = true;
        }

        super.setFocusableInTouchMode(focusable && (!empty || isInFilterMode()));
		System.out.println("resp1onse AdapterView2: public void setFocusableInTouchMode(boolean focusable) { end void ");
    }

    void checkFocus() {
		System.out.println("resp1onse AdapterView2: void checkFocus() { start void ");
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;
        final boolean focusable = !empty || isInFilterMode();
        // The order in which we set focusable in touch mode/focusable may matter
        // for the client, see View.setFocusableInTouchMode() comments for more
        // details
        super.setFocusableInTouchMode(focusable && mDesiredFocusableInTouchModeState);
        super.setFocusable(focusable && mDesiredFocusableState);
        if (mEmptyView != null) {
            updateEmptyStatus((adapter == null) || adapter.isEmpty());
        }
		System.out.println("resp1onse AdapterView2: void checkFocus() { end void ");
    }

    /**
     * Update the status of the list based on the empty parameter.  If empty is true and
     * we have an empty view, display it.  In all the other cases, make sure that the listview
     * is VISIBLE and that the empty view is GONE (if it's not null).
     */
    @SuppressLint("WrongCall")
	private void updateEmptyStatus(boolean empty) {
		System.out.println("resp1onse AdapterView2: private void updateEmptyStatus(boolean empty) { start void ");
        if (isInFilterMode()) {
            empty = false;
        }

        if (empty) {
            if (mEmptyView != null) {
                mEmptyView.setVisibility(View.VISIBLE);
                setVisibility(View.GONE);
            } else {
                // If the caller just removed our empty view, make sure the list view is visible
                setVisibility(View.VISIBLE);
            }

            // We are now GONE, so pending layouts will not be dispatched.
            // Force one here to make sure that the state of the list matches
            // the state of the adapter.
            if (mDataChanged) {           
                this.onLayout(false, getLeft(), getTop(), getRight(), getBottom()); 
            }
        } else {
            if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);
            setVisibility(View.VISIBLE);
        }
		System.out.println("resp1onse AdapterView2: private void updateEmptyStatus(boolean empty) { end void ");
    }

    /**
     * Gets the data associated with the specified position in the list.
     *
     * @param position Which data to get
     * @return The data associated with the specified position in the list
     */
    public Object getItemAtPosition(int position) {
		System.out.println("resp1onse AdapterView2: public Object getItemAtPosition(int position) { start return ");
        T adapter = getAdapter();
		System.out.println("resp1onse AdapterView2: public Object getItemAtPosition(int position) { end return  =1");
        return (adapter == null || position < 0) ? null : adapter.getItem(position);
    }

    public long getItemIdAtPosition(int position) {
		System.out.println("resp1onse AdapterView2: public long getItemIdAtPosition(int position) { start return ");
        T adapter = getAdapter();
		System.out.println("resp1onse AdapterView2: public long getItemIdAtPosition(int position) { end return  =1");
        return (adapter == null || position < 0) ? INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
		System.out.println("resp1onse AdapterView2: public void setOnClickListener(OnClickListener l) { start void ");
		System.out.println("resp1onse AdapterView2: public void setOnClickListener(OnClickListener l) { end void ");
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. "
                + "You probably want setOnItemClickListener instead");
    }

    /**
     * Override to prevent freezing of any views created by the adapter.
     */
    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
		System.out.println("resp1onse AdapterView2: protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) { start void ");
        dispatchFreezeSelfOnly(container);
		System.out.println("resp1onse AdapterView2: protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) { end void ");
    }

    /**
     * Override to prevent thawing of any views created by the adapter.
     */
    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		System.out.println("resp1onse AdapterView2: protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) { start void ");
        dispatchThawSelfOnly(container);
		System.out.println("resp1onse AdapterView2: protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) { end void ");
    }

    class AdapterDataSetObserver extends DataSetObserver {

        private Parcelable mInstanceState = null;

        @Override
        public void onChanged() {
		System.out.println("resp1onse AdapterView2: public void onChanged() { start void ");
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = getAdapter().getCount();

            // Detect the case where a cursor that was previously invalidated has
            // been repopulated with new data.
            if (AdapterView2.this.getAdapter().hasStableIds() && mInstanceState != null
                    && mOldItemCount == 0 && mItemCount > 0) {
                AdapterView2.this.onRestoreInstanceState(mInstanceState);
                mInstanceState = null;
            } else {
                rememberSyncState();
            }
            checkFocus();
            requestLayout();
		System.out.println("resp1onse AdapterView2: public void onChanged() { end void ");
        }

        @Override
        public void onInvalidated() {
		System.out.println("resp1onse AdapterView2: public void onInvalidated() { start void ");
            mDataChanged = true;

            if (AdapterView2.this.getAdapter().hasStableIds()) {
                // Remember the current state for the case where our hosting activity is being
                // stopped and later restarted
                mInstanceState = AdapterView2.this.onSaveInstanceState();
            }

            // Data is invalid so we should reset our state
            mOldItemCount = mItemCount;
            mItemCount = 0;
            mSelectedPosition = INVALID_POSITION;
            mSelectedRowId = INVALID_ROW_ID;
            mNextSelectedPosition = INVALID_POSITION;
            mNextSelectedRowId = INVALID_ROW_ID;
            mNeedSync = false;

            checkFocus();
            requestLayout();
		System.out.println("resp1onse AdapterView2: public void onInvalidated() { end void ");
        }

        public void clearSavedState() {
		System.out.println("resp1onse AdapterView2: public void clearSavedState() { start void ");
            mInstanceState = null;
		System.out.println("resp1onse AdapterView2: public void clearSavedState() { end void ");
        }
    }

    @Override
    protected void onDetachedFromWindow() {
		System.out.println("resp1onse AdapterView2: protected void onDetachedFromWindow() { start void ");
        super.onDetachedFromWindow();
        removeCallbacks(mSelectionNotifier);
		System.out.println("resp1onse AdapterView2: protected void onDetachedFromWindow() { end void ");
    }

    private class SelectionNotifier implements Runnable {
        public void run() {
		System.out.println("resp1onse AdapterView2: public void run() { start void ");
            if (mDataChanged) {
                // Data has changed between when this SelectionNotifier
                // was posted and now. We need to wait until the AdapterView
                // has been synched to the new data.
                if (getAdapter() != null) {
                    post(this);
                }
            } else {
                fireOnSelected();
                performAccessibilityActionsOnSelected();
            }
		System.out.println("resp1onse AdapterView2: public void run() { end void ");
        }
    }

    void selectionChanged() {
		System.out.println("resp1onse AdapterView2: void selectionChanged() { start void ");
        if (mOnItemSelectedListener != null
                /*|| AccessibilityManager.getInstance(mContext).isEnabled()*/) {
            if (mInLayout || mBlockLayoutRequests) {
                // If we are in a layout traversal, defer notification
                // by posting. This ensures that the view tree is
                // in a consistent state and is able to accomodate
                // new layout or invalidate requests.
                if (mSelectionNotifier == null) {
                    mSelectionNotifier = new SelectionNotifier();
                }
                post(mSelectionNotifier);
            } else {
                fireOnSelected();
                performAccessibilityActionsOnSelected();
            }
		System.out.println("resp1onse AdapterView2: void selectionChanged() { end void ");
        }
    }

    private void fireOnSelected() {
		System.out.println("resp1onse AdapterView2: private void fireOnSelected() { start void ");
        if (mOnItemSelectedListener == null) {
            return;
        }
        final int selection = getSelectedItemPosition();
        if (selection >= 0) {
            View v = getSelectedView();
            mOnItemSelectedListener.onItemSelected(this, v, selection,
                    getAdapter().getItemId(selection));
        } else {
            mOnItemSelectedListener.onNothingSelected(this);
        }
		System.out.println("resp1onse AdapterView2: private void fireOnSelected() { end void ");
    }

    private void performAccessibilityActionsOnSelected() {
		System.out.println("resp1onse AdapterView2: private void performAccessibilityActionsOnSelected() { start void ");
//        if (!AccessibilityManager.getInstance(mContext).isEnabled()) {
//            return;
//        }
        final int position = getSelectedItemPosition();
        if (position >= 0) {
            // we fire selection events here not in View
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
        }
		System.out.println("resp1onse AdapterView2: private void performAccessibilityActionsOnSelected() { end void ");
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("resp1onse AdapterView2: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { start return ");
        View selectedView = getSelectedView();
        if (selectedView != null && selectedView.getVisibility() == VISIBLE
                && selectedView.dispatchPopulateAccessibilityEvent(event)) {
            return true;
        }
		System.out.println("resp1onse AdapterView2: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { end return  =1");
        return false;
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) {
		System.out.println("resp1onse AdapterView2: public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) { start return ");
        if (super.onRequestSendAccessibilityEvent(child, event)) {
            // Add a record for ourselves as well.
            AccessibilityEvent record = AccessibilityEvent.obtain();
            onInitializeAccessibilityEvent(record);
            // Populate with the text of the requesting child.
            child.dispatchPopulateAccessibilityEvent(record);
            event.appendRecord(record);
		System.out.println("resp1onse AdapterView2: public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) { end return if ");
            return true;
        }
		System.out.println("resp1onse AdapterView2: public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) { end return  =1");
        return false;
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
		System.out.println("resp1onse AdapterView2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { start void ");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(AdapterView2.class.getName());
        info.setScrollable(isScrollableForAccessibility());
        View selectedView = getSelectedView();
        if (selectedView != null) {
            info.setEnabled(selectedView.isEnabled());
        }
		System.out.println("resp1onse AdapterView2: public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) { end void ");
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("resp1onse AdapterView2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { start void ");
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(AdapterView2.class.getName());
        event.setScrollable(isScrollableForAccessibility());
        View selectedView = getSelectedView();
        if (selectedView != null) {
            event.setEnabled(selectedView.isEnabled());
        }
        event.setCurrentItemIndex(getSelectedItemPosition());
        event.setFromIndex(getFirstVisiblePosition());
        event.setToIndex(getLastVisiblePosition());
        event.setItemCount(getCount());
		System.out.println("resp1onse AdapterView2: public void onInitializeAccessibilityEvent(AccessibilityEvent event) { end void ");
    }

    private boolean isScrollableForAccessibility() {
		System.out.println("resp1onse AdapterView2: private boolean isScrollableForAccessibility() { start return ");
        T adapter = getAdapter();
        if (adapter != null) {
            final int itemCount = adapter.getCount();
		System.out.println("resp1onse AdapterView2: private boolean isScrollableForAccessibility() { end return if ");
            return itemCount > 0
                && (getFirstVisiblePosition() > 0 || getLastVisiblePosition() < itemCount - 1);
        }
		System.out.println("resp1onse AdapterView2: private boolean isScrollableForAccessibility() { end return  =1");
        return false;
    }

    @Override
    protected boolean canAnimate() {
		System.out.println("resp1onse AdapterView2: protected boolean canAnimate() { start return ");
		System.out.println("resp1onse AdapterView2: protected boolean canAnimate() { end return ");
        return super.canAnimate() && mItemCount > 0;
    }

    void handleDataChanged() {
		System.out.println("resp1onse AdapterView2: void handleDataChanged() { start void ");
        final int count = mItemCount;
        boolean found = false;

        if (count > 0) {

            int newPos;

            // Find the row we are supposed to sync to
            if (mNeedSync) {
                // Update this first, since setNextSelectedPositionInt inspects
                // it
                mNeedSync = false;

                // See if we can find a position in the new data with the same
                // id as the old selection
                newPos = findSyncPosition();
                if (newPos >= 0) {
                    // Verify that new selection is selectable
                    int selectablePos = lookForSelectablePosition(newPos, true);
                    if (selectablePos == newPos) {
                        // Same row id is selected
                        setNextSelectedPositionInt(newPos);
                        found = true;
                    }
                }
            }
            if (!found) {
                // Try to use the same position if we can't find matching data
                newPos = getSelectedItemPosition();

                // Pin position to the available range
                if (newPos >= count) {
                    newPos = count - 1;
                }
                if (newPos < 0) {
                    newPos = 0;
                }

                // Make sure we select something selectable -- first look down
                int selectablePos = lookForSelectablePosition(newPos, true);
                if (selectablePos < 0) {
                    // Looking down didn't work -- try looking up
                    selectablePos = lookForSelectablePosition(newPos, false);
                }
                if (selectablePos >= 0) {
                    setNextSelectedPositionInt(selectablePos);
                    checkSelectionChanged();
                    found = true;
                }
            }
        }
        if (!found) {
            // Nothing is selected
            mSelectedPosition = INVALID_POSITION;
            mSelectedRowId = INVALID_ROW_ID;
            mNextSelectedPosition = INVALID_POSITION;
            mNextSelectedRowId = INVALID_ROW_ID;
            mNeedSync = false;
            checkSelectionChanged();
        }

//        notifySubtreeAccessibilityStateChangedIfNeeded();
        ReflectUtilView.methodInvoke_View_notifySubtreeAccessibilityStateChangedIfNeeded(this);
		System.out.println("resp1onse AdapterView2: void handleDataChanged() { end void ");
    }

    void checkSelectionChanged() {
		System.out.println("resp1onse AdapterView2: void checkSelectionChanged() { start void ");
        if ((mSelectedPosition != mOldSelectedPosition) || (mSelectedRowId != mOldSelectedRowId)) {
            selectionChanged();
            mOldSelectedPosition = mSelectedPosition;
            mOldSelectedRowId = mSelectedRowId;
        }
		System.out.println("resp1onse AdapterView2: void checkSelectionChanged() { end void ");
    }

    /**
     * Searches the adapter for a position matching mSyncRowId. The search starts at mSyncPosition
     * and then alternates between moving up and moving down until 1) we find the right position, or
     * 2) we run out of time, or 3) we have looked at every position
     *
     * @return Position of the row that matches mSyncRowId, or {@link #INVALID_POSITION} if it can't
     *         be found
     */
    int findSyncPosition() {
        int count = mItemCount;

        if (count == 0) {
            return INVALID_POSITION;
        }

        long idToMatch = mSyncRowId;
        int seed = mSyncPosition;

        // If there isn't a selection don't hunt for it
        if (idToMatch == INVALID_ROW_ID) {
            return INVALID_POSITION;
        }

        // Pin seed to reasonable values
        seed = Math.max(0, seed);
        seed = Math.min(count - 1, seed);

        long endTime = SystemClock.uptimeMillis() + SYNC_MAX_DURATION_MILLIS;

        long rowId;

        // first position scanned so far
        int first = seed;

        // last position scanned so far
        int last = seed;

        // True if we should move down on the next iteration
        boolean next = false;

        // True when we have looked at the first item in the data
        boolean hitFirst;

        // True when we have looked at the last item in the data
        boolean hitLast;

        // Get the item ID locally (instead of getItemIdAtPosition), so
        // we need the adapter
        T adapter = getAdapter();
        if (adapter == null) {
            return INVALID_POSITION;
        }

        while (SystemClock.uptimeMillis() <= endTime) {
            rowId = adapter.getItemId(seed);
            if (rowId == idToMatch) {
                // Found it!
                return seed;
            }

            hitLast = last == count - 1;
            hitFirst = first == 0;

            if (hitLast && hitFirst) {
                // Looked at everything
                break;
            }

            if (hitFirst || (next && !hitLast)) {
                // Either we hit the top, or we are trying to move down
                last++;
                seed = last;
                // Try going up next time
                next = false;
            } else if (hitLast || (!next && !hitFirst)) {
                // Either we hit the bottom, or we are trying to move up
                first--;
                seed = first;
                // Try going down next time
                next = true;
            }

        }

        return INVALID_POSITION;
    }

    /**
     * Find a position that can be selected (i.e., is not a separator).
     *
     * @param position The starting position to look at.
     * @param lookDown Whether to look down for other positions.
     * @return The next selectable position starting at position and then searching either up or
     *         down. Returns {@link #INVALID_POSITION} if nothing can be found.
     */
    int lookForSelectablePosition(int position, boolean lookDown) {
        return position;
    }

    /**
     * Utility to keep mSelectedPosition and mSelectedRowId in sync
     * @param position Our current position
     */
    void setSelectedPositionInt(int position) {
		System.out.println("resp1onse AdapterView2: void setSelectedPositionInt(int position) { start void ");
        mSelectedPosition = position;
        mSelectedRowId = getItemIdAtPosition(position);
		System.out.println("resp1onse AdapterView2: void setSelectedPositionInt(int position) { end void ");
    }

    /**
     * Utility to keep mNextSelectedPosition and mNextSelectedRowId in sync
     * @param position Intended value for mSelectedPosition the next time we go
     * through layout
     */
    void setNextSelectedPositionInt(int position) {
		System.out.println("resp1onse AdapterView2: void setNextSelectedPositionInt(int position) { start void ");
        mNextSelectedPosition = position;
        mNextSelectedRowId = getItemIdAtPosition(position);
        // If we are trying to sync to the selection, update that too
        if (mNeedSync && mSyncMode == SYNC_SELECTED_POSITION && position >= 0) {
            mSyncPosition = position;
            mSyncRowId = mNextSelectedRowId;
        }
		System.out.println("resp1onse AdapterView2: void setNextSelectedPositionInt(int position) { end void ");
    }

    /**
     * Remember enough information to restore the screen state when the data has
     * changed.
     *
     */
    void rememberSyncState() {
		System.out.println("resp1onse AdapterView2: void rememberSyncState() { start void ");
        if (getChildCount() > 0) {
            mNeedSync = true;
            mSyncHeight = mLayoutHeight;
            if (mSelectedPosition >= 0) {
                // Sync the selection state
                View v = getChildAt(mSelectedPosition - mFirstPosition);
                mSyncRowId = mNextSelectedRowId;
                mSyncPosition = mNextSelectedPosition;
                if (v != null) {
                    mSpecificTop = v.getTop();
                }
                mSyncMode = SYNC_SELECTED_POSITION;
            } else {
                // Sync the based on the offset of the first view
                View v = getChildAt(0);
                T adapter = getAdapter();
                if (mFirstPosition >= 0 && mFirstPosition < adapter.getCount()) {
                    mSyncRowId = adapter.getItemId(mFirstPosition);
                } else {
                    mSyncRowId = NO_ID;
                }
                mSyncPosition = mFirstPosition;
                if (v != null) {
                    mSpecificTop = v.getTop();
                }
                mSyncMode = SYNC_FIRST_POSITION;
            }
        }
		System.out.println("resp1onse AdapterView2: void rememberSyncState() { end void ");
    }
}
