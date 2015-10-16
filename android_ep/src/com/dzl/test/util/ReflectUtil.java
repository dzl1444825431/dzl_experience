package com.dzl.test.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.animation.LayoutTransition;
import android.animation.LayoutTransition.TransitionListener;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.inputmethodservice.InputMethodService.Insets;
import android.os.Parcelable;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import android.widget.AbsSpinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SpinnerAdapter;

public class ReflectUtil {
	
	private static final byte LAYOUT_DIRECTION_MASK = 0x00000003 << 2;
	public static Method method_AbsSpinner_RecycleBin_put;
	public static Method method_AbsSpinner_RecycleBin_get;
	public static Method method_AbsSpinner_RecycleBin_clear;

	public static Method method_AbsSpinner_generateDefaultLayoutParams;
	public static Method method_AbsSpinner_getAdapter;
	public static Method method_AbsSpinner_getChildHeight;
	public static Method method_AbsSpinner_getChildWidth;
	public static Method method_AbsSpinner_getCount;
	public static Method method_AbsSpinner_getSelectedView;
	public static Method method_AbsSpinner_onInitializeAccessibilityEvent;
	public static Method method_AbsSpinner_onInitializeAccessibilityNodeInfo;
	public static Method method_AbsSpinner_onMeasure;
	public static Method method_AbsSpinner_onRestoreInstanceState;
	public static Method method_AbsSpinner_onSaveInstanceState;
	public static Method method_AbsSpinner_pointToPosition;
	public static Method method_AbsSpinner_recycleAllViews;
	public static Method method_AbsSpinner_requestLayout;
	public static Method method_AbsSpinner_resetList;
	public static Method method_AbsSpinner_setAdapter;
	public static Method method_AbsSpinner_setSelection;
	public static Method method_AbsSpinner_setSelectionInt;
	public static Method method_AbsSpinner_access$000;
	public static Method method_AbsSpinner_initAbsSpinner;

	static {
		initField_AbsSpinner(AbsSpinner.class);
		initMethod_AbsSpinner(AbsSpinner.class);
		initField_AdapterView(AdapterView.class);
		initMethod_AdapterView(AdapterView.class);
		initField_ViewGroup(ViewGroup.class);
		initMethod_ViewGroup(ViewGroup.class);
		initRecycleBinMethod();
	}

	private static void initRecycleBinMethod() {
		Class<?> recycleBinClass = class_AbsSpinner_RecycleBin();
		Method[] declaredMethods = recycleBinClass.getDeclaredMethods();
		for (Method m : declaredMethods) {
			
			if (m.getName().equals("put")) {
				method_AbsSpinner_RecycleBin_put = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("get")) {
				method_AbsSpinner_RecycleBin_get = m;
				m.setAccessible(true);  
			}else if (m.getName().equals("clear")) {
				method_AbsSpinner_RecycleBin_clear = m;
				m.setAccessible(true);  
			}
		}
   		
   	}
	
	public static Class<?> class_AbsSpinner_RecycleBin;
	
	public static void methodInvoke_AbsSpinner_RecycleBin_put(Object obj, int i, View child) {
		try {
			Object bin = getField_AbsSpinner_mRecycler(obj);
			method_AbsSpinner_RecycleBin_put.invoke(bin, i, child);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static View methodInvoke_AbsSpinner_RecycleBin_get(Object obj, int position) {
		View v = null;
		try {
			Object bin = getField_AbsSpinner_mRecycler(obj);
			v = (View) method_AbsSpinner_RecycleBin_get.invoke(bin, position);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	public static void methodInvoke_AbsSpinner_RecycleBin_clear(Object obj) {
		try {
			Object bin = getField_AbsSpinner_mRecycler(obj);
			method_AbsSpinner_RecycleBin_clear.invoke(bin);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static Class<?> class_AbsSpinner_RecycleBin(){
		try {
			if (class_AbsSpinner_RecycleBin == null) {
				class_AbsSpinner_RecycleBin = Class.forName("android.widget.AbsSpinner$RecycleBin");  
			}
			return class_AbsSpinner_RecycleBin;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void initMethod_AbsSpinner(Class<?> clz) {
		Method[] methods = clz.getDeclaredMethods();
		for (Method m : methods) {

			if (m.getName().equals("generateDefaultLayoutParams")) {
				method_AbsSpinner_generateDefaultLayoutParams = m;
				method_AbsSpinner_generateDefaultLayoutParams.setAccessible(true);

			} else if (m.getName().equals("getAdapter")) {
				method_AbsSpinner_getAdapter = m;
				method_AbsSpinner_getAdapter.setAccessible(true);

			} else if (m.getName().equals("getChildHeight")) {
				method_AbsSpinner_getChildHeight = m;
				method_AbsSpinner_getChildHeight.setAccessible(true);

			} else if (m.getName().equals("getChildWidth")) {
				method_AbsSpinner_getChildWidth = m;
				method_AbsSpinner_getChildWidth.setAccessible(true);

			} else if (m.getName().equals("getCount")) {
				method_AbsSpinner_getCount = m;
				method_AbsSpinner_getCount.setAccessible(true);

			} else if (m.getName().equals("getSelectedView")) {
				method_AbsSpinner_getSelectedView = m;
				method_AbsSpinner_getSelectedView.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityEvent")) {
				method_AbsSpinner_onInitializeAccessibilityEvent = m;
				method_AbsSpinner_onInitializeAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityNodeInfo")) {
				method_AbsSpinner_onInitializeAccessibilityNodeInfo = m;
				method_AbsSpinner_onInitializeAccessibilityNodeInfo.setAccessible(true);

			} else if (m.getName().equals("onMeasure")) {
				method_AbsSpinner_onMeasure = m;
				method_AbsSpinner_onMeasure.setAccessible(true);

			} else if (m.getName().equals("onRestoreInstanceState")) {
				method_AbsSpinner_onRestoreInstanceState = m;
				method_AbsSpinner_onRestoreInstanceState.setAccessible(true);

			} else if (m.getName().equals("onSaveInstanceState")) {
				method_AbsSpinner_onSaveInstanceState = m;
				method_AbsSpinner_onSaveInstanceState.setAccessible(true);

			} else if (m.getName().equals("pointToPosition")) {
				method_AbsSpinner_pointToPosition = m;
				method_AbsSpinner_pointToPosition.setAccessible(true);

			} else if (m.getName().equals("recycleAllViews")) {
				method_AbsSpinner_recycleAllViews = m;
				method_AbsSpinner_recycleAllViews.setAccessible(true);

			} else if (m.getName().equals("requestLayout")) {
				method_AbsSpinner_requestLayout = m;
				method_AbsSpinner_requestLayout.setAccessible(true);

			} else if (m.getName().equals("resetList")) {
				method_AbsSpinner_resetList = m;
				method_AbsSpinner_resetList.setAccessible(true);

			} else if (m.getName().equals("setAdapter")) {
				method_AbsSpinner_setAdapter = m;
				method_AbsSpinner_setAdapter.setAccessible(true);

			} else if (m.getName().equals("setSelection")) {
				method_AbsSpinner_setSelection = m;
				method_AbsSpinner_setSelection.setAccessible(true);

			} else if (m.getName().equals("setSelectionInt")) {
				method_AbsSpinner_setSelectionInt = m;
				method_AbsSpinner_setSelectionInt.setAccessible(true);

			} else if (m.getName().equals("access$000")) {
				method_AbsSpinner_access$000 = m;
				method_AbsSpinner_access$000.setAccessible(true);

			} else if (m.getName().equals("initAbsSpinner")) {
				method_AbsSpinner_initAbsSpinner = m;
				method_AbsSpinner_initAbsSpinner.setAccessible(true);

			}
		}
	}

	public static LayoutParams methodInvoke_AbsSpinner_generateDefaultLayoutParams(Object obj) {
		LayoutParams o = null;
		try {
			o = (LayoutParams) method_AbsSpinner_generateDefaultLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Adapter methodInvoke_AbsSpinner_getAdapter(Object obj) {
		Adapter o = null;
		try {
			o = (Adapter) method_AbsSpinner_getAdapter.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AbsSpinner_getChildHeight(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AbsSpinner_getChildHeight.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AbsSpinner_getChildWidth(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AbsSpinner_getChildWidth.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AbsSpinner_getCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AbsSpinner_getCount.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_AbsSpinner_getSelectedView(Object obj) {
		View o = null;
		try {
			o = (View) method_AbsSpinner_getSelectedView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AbsSpinner_onInitializeAccessibilityEvent(Object obj) {
		try {
			method_AbsSpinner_onInitializeAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_onInitializeAccessibilityNodeInfo(Object obj) {
		try {
			method_AbsSpinner_onInitializeAccessibilityNodeInfo.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_onMeasure(Object obj) {
		try {
			method_AbsSpinner_onMeasure.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_onRestoreInstanceState(Object obj) {
		try {
			method_AbsSpinner_onRestoreInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Parcelable methodInvoke_AbsSpinner_onSaveInstanceState(Object obj) {
		Parcelable o = null;
		try {
			o = (Parcelable) method_AbsSpinner_onSaveInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AbsSpinner_pointToPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AbsSpinner_pointToPosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AbsSpinner_recycleAllViews(Object obj) {
		try {
			method_AbsSpinner_recycleAllViews.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_requestLayout(Object obj) {
		try {
			method_AbsSpinner_requestLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_resetList(Object obj) {
		try {
			method_AbsSpinner_resetList.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_setAdapter(Object obj) {
		try {
			method_AbsSpinner_setAdapter.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_setSelection(Object obj) {
		try {
			method_AbsSpinner_setSelection.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_setSelectionInt(Object obj) {
		try {
			method_AbsSpinner_setSelectionInt.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_access$000(Object obj) {
		try {
			method_AbsSpinner_access$000.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AbsSpinner_initAbsSpinner(Object obj) {
		try {
			method_AbsSpinner_initAbsSpinner.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Field field_AbsSpinner_mAdapter;
	public static Field field_AbsSpinner_mDataSetObserver;
	public static Field field_AbsSpinner_mTouchFrame;
	public static Field field_AbsSpinner_mSelectionLeftPadding;
	public static Field field_AbsSpinner_mSelectionRightPadding;
	public static Field field_AbsSpinner_mSelectionTopPadding;
	public static Field field_AbsSpinner_mSelectionBottomPadding;
	public static Field field_AbsSpinner_mHeightMeasureSpec;
	public static Field field_AbsSpinner_mWidthMeasureSpec;
	public static Field field_AbsSpinner_mSpinnerPadding;
	public static Field field_AbsSpinner_mRecycler;

	private static void initField_AbsSpinner(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field f : fields) {

			if (f.getName().equals("mAdapter")) {
				field_AbsSpinner_mAdapter = f;
				field_AbsSpinner_mAdapter.setAccessible(true);

			} else if (f.getName().equals("mDataSetObserver")) {
				field_AbsSpinner_mDataSetObserver = f;
				field_AbsSpinner_mDataSetObserver.setAccessible(true);

			} else if (f.getName().equals("mTouchFrame")) {
				field_AbsSpinner_mTouchFrame = f;
				field_AbsSpinner_mTouchFrame.setAccessible(true);

			} else if (f.getName().equals("mSelectionLeftPadding")) {
				field_AbsSpinner_mSelectionLeftPadding = f;
				field_AbsSpinner_mSelectionLeftPadding.setAccessible(true);

			} else if (f.getName().equals("mSelectionRightPadding")) {
				field_AbsSpinner_mSelectionRightPadding = f;
				field_AbsSpinner_mSelectionRightPadding.setAccessible(true);

			} else if (f.getName().equals("mSelectionTopPadding")) {
				field_AbsSpinner_mSelectionTopPadding = f;
				field_AbsSpinner_mSelectionTopPadding.setAccessible(true);

			} else if (f.getName().equals("mSelectionBottomPadding")) {
				field_AbsSpinner_mSelectionBottomPadding = f;
				field_AbsSpinner_mSelectionBottomPadding.setAccessible(true);

			} else if (f.getName().equals("mHeightMeasureSpec")) {
				field_AbsSpinner_mHeightMeasureSpec = f;
				field_AbsSpinner_mHeightMeasureSpec.setAccessible(true);

			} else if (f.getName().equals("mWidthMeasureSpec")) {
				field_AbsSpinner_mWidthMeasureSpec = f;
				field_AbsSpinner_mWidthMeasureSpec.setAccessible(true);
			} else if (f.getName().equals("mSpinnerPadding")) {
				field_AbsSpinner_mSpinnerPadding = f;
				field_AbsSpinner_mSpinnerPadding.setAccessible(true);
			} else if (f.getName().equals("mRecycler")) {
				field_AbsSpinner_mRecycler = f;
				field_AbsSpinner_mRecycler.setAccessible(true);
			}
		}
	}

	public static SpinnerAdapter getField_AbsSpinner_mAdapter(Object obj) {
		SpinnerAdapter o = null;
		try {
			o = (SpinnerAdapter) field_AbsSpinner_mAdapter.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mAdapter(Object obj, Object value) {
		try {
			field_AbsSpinner_mAdapter.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataSetObserver getField_AbsSpinner_mDataSetObserver(Object obj) {
		DataSetObserver o = null;
		try {
			o = (DataSetObserver) field_AbsSpinner_mDataSetObserver.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mDataSetObserver(Object obj, Object value) {
		try {
			field_AbsSpinner_mDataSetObserver.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Rect getField_AbsSpinner_mTouchFrame(Object obj) {
		Rect o = null;
		try {
			o = (Rect) field_AbsSpinner_mTouchFrame.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mTouchFrame(Object obj, Object value) {
		try {
			field_AbsSpinner_mTouchFrame.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mSelectionLeftPadding(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mSelectionLeftPadding.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mSelectionLeftPadding(Object obj, Object value) {
		try {
			field_AbsSpinner_mSelectionLeftPadding.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mSelectionRightPadding(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mSelectionRightPadding.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mSelectionRightPadding(Object obj, Object value) {
		try {
			field_AbsSpinner_mSelectionRightPadding.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mSelectionTopPadding(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mSelectionTopPadding.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mSelectionTopPadding(Object obj, Object value) {
		try {
			field_AbsSpinner_mSelectionTopPadding.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mSelectionBottomPadding(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mSelectionBottomPadding.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mSelectionBottomPadding(Object obj, Object value) {
		try {
			field_AbsSpinner_mSelectionBottomPadding.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mHeightMeasureSpec(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mHeightMeasureSpec.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mHeightMeasureSpec(Object obj, Object value) {
		try {
			field_AbsSpinner_mHeightMeasureSpec.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AbsSpinner_mWidthMeasureSpec(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AbsSpinner_mWidthMeasureSpec.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AbsSpinner_mWidthMeasureSpec(Object obj, Object value) {
		try {
			field_AbsSpinner_mWidthMeasureSpec.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Rect getField_AbsSpinner_mSpinnerPadding(Object obj) {
		Rect o = null;
		try {
			o = (Rect) field_AbsSpinner_mSpinnerPadding.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static Object getField_AbsSpinner_mRecycler(Object obj) {
		Object o = null;
		try {
			o = field_AbsSpinner_mRecycler.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static void setField_AbsSpinner_mSpinnerPadding(Object obj, Object value) {
		try {
			field_AbsSpinner_mSpinnerPadding.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Method method_AdapterView_addView;
	public static Method method_AdapterView_canAnimate;
	public static Method method_AdapterView_checkFocus;
	public static Method method_AdapterView_checkSelectionChanged;
	public static Method method_AdapterView_dispatchPopulateAccessibilityEvent;
	public static Method method_AdapterView_dispatchRestoreInstanceState;
	public static Method method_AdapterView_dispatchSaveInstanceState;
	public static Method method_AdapterView_findSyncPosition;
	public static Method method_AdapterView_getCount;
	public static Method method_AdapterView_getEmptyView;
	public static Method method_AdapterView_getFirstVisiblePosition;
	public static Method method_AdapterView_getItemAtPosition;
	public static Method method_AdapterView_getItemIdAtPosition;
	public static Method method_AdapterView_getLastVisiblePosition;
	public static Method method_AdapterView_getPositionForView;
	public static Method method_AdapterView_getSelectedItem;
	public static Method method_AdapterView_getSelectedItemId;
	public static Method method_AdapterView_getSelectedItemPosition;
	public static Method method_AdapterView_handleDataChanged;
	public static Method method_AdapterView_isInFilterMode;
	public static Method method_AdapterView_lookForSelectablePosition;
	public static Method method_AdapterView_onDetachedFromWindow;
	public static Method method_AdapterView_onInitializeAccessibilityEvent;
	public static Method method_AdapterView_onInitializeAccessibilityNodeInfo;
	public static Method method_AdapterView_onLayout;
	public static Method method_AdapterView_onRequestSendAccessibilityEvent;
	public static Method method_AdapterView_performItemClick;
	public static Method method_AdapterView_rememberSyncState;
	public static Method method_AdapterView_removeAllViews;
	public static Method method_AdapterView_removeView;
	public static Method method_AdapterView_removeViewAt;
	public static Method method_AdapterView_selectionChanged;
	public static Method method_AdapterView_setEmptyView;
	public static Method method_AdapterView_setFocusable;
	public static Method method_AdapterView_setFocusableInTouchMode;
	public static Method method_AdapterView_setNextSelectedPositionInt;
	public static Method method_AdapterView_setOnClickListener;
	public static Method method_AdapterView_setOnItemClickListener;
	public static Method method_AdapterView_setOnItemLongClickListener;
	public static Method method_AdapterView_setOnItemSelectedListener;
	public static Method method_AdapterView_setSelectedPositionInt;
	public static Method method_AdapterView_access$000;
	public static Method method_AdapterView_access$100;
	public static Method method_AdapterView_access$200;
	public static Method method_AdapterView_access$300;
	public static Method method_AdapterView_fireOnSelected;
	public static Method method_AdapterView_isScrollableForAccessibility;
	public static Method method_AdapterView_performAccessibilityActionsOnSelected;
	public static Method method_AdapterView_updateEmptyStatus;

	private static void initMethod_AdapterView(Class<?> clz) {
		Method[] methods = clz.getDeclaredMethods();
		for (Method m : methods) {

			if (m.getName().equals("addView")) {
				method_AdapterView_addView = m;
				method_AdapterView_addView.setAccessible(true);

			} else if (m.getName().equals("canAnimate")) {
				method_AdapterView_canAnimate = m;
				method_AdapterView_canAnimate.setAccessible(true);

			} else if (m.getName().equals("checkFocus")) {
				method_AdapterView_checkFocus = m;
				method_AdapterView_checkFocus.setAccessible(true);

			} else if (m.getName().equals("checkSelectionChanged")) {
				method_AdapterView_checkSelectionChanged = m;
				method_AdapterView_checkSelectionChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchPopulateAccessibilityEvent")) {
				method_AdapterView_dispatchPopulateAccessibilityEvent = m;
				method_AdapterView_dispatchPopulateAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchRestoreInstanceState")) {
				method_AdapterView_dispatchRestoreInstanceState = m;
				method_AdapterView_dispatchRestoreInstanceState.setAccessible(true);

			} else if (m.getName().equals("dispatchSaveInstanceState")) {
				method_AdapterView_dispatchSaveInstanceState = m;
				method_AdapterView_dispatchSaveInstanceState.setAccessible(true);

			} else if (m.getName().equals("findSyncPosition")) {
				method_AdapterView_findSyncPosition = m;
				method_AdapterView_findSyncPosition.setAccessible(true);

			} else if (m.getName().equals("getCount")) {
				method_AdapterView_getCount = m;
				method_AdapterView_getCount.setAccessible(true);

			} else if (m.getName().equals("getEmptyView")) {
				method_AdapterView_getEmptyView = m;
				method_AdapterView_getEmptyView.setAccessible(true);

			} else if (m.getName().equals("getFirstVisiblePosition")) {
				method_AdapterView_getFirstVisiblePosition = m;
				method_AdapterView_getFirstVisiblePosition.setAccessible(true);

			} else if (m.getName().equals("getItemAtPosition")) {
				method_AdapterView_getItemAtPosition = m;
				method_AdapterView_getItemAtPosition.setAccessible(true);

			} else if (m.getName().equals("getItemIdAtPosition")) {
				method_AdapterView_getItemIdAtPosition = m;
				method_AdapterView_getItemIdAtPosition.setAccessible(true);

			} else if (m.getName().equals("getLastVisiblePosition")) {
				method_AdapterView_getLastVisiblePosition = m;
				method_AdapterView_getLastVisiblePosition.setAccessible(true);

			} else if (m.getName().equals("getPositionForView")) {
				method_AdapterView_getPositionForView = m;
				method_AdapterView_getPositionForView.setAccessible(true);

			} else if (m.getName().equals("getSelectedItem")) {
				method_AdapterView_getSelectedItem = m;
				method_AdapterView_getSelectedItem.setAccessible(true);

			} else if (m.getName().equals("getSelectedItemId")) {
				method_AdapterView_getSelectedItemId = m;
				method_AdapterView_getSelectedItemId.setAccessible(true);

			} else if (m.getName().equals("getSelectedItemPosition")) {
				method_AdapterView_getSelectedItemPosition = m;
				method_AdapterView_getSelectedItemPosition.setAccessible(true);

			} else if (m.getName().equals("handleDataChanged")) {
				method_AdapterView_handleDataChanged = m;
				method_AdapterView_handleDataChanged.setAccessible(true);

			} else if (m.getName().equals("isInFilterMode")) {
				method_AdapterView_isInFilterMode = m;
				method_AdapterView_isInFilterMode.setAccessible(true);

			} else if (m.getName().equals("lookForSelectablePosition")) {
				method_AdapterView_lookForSelectablePosition = m;
				method_AdapterView_lookForSelectablePosition.setAccessible(true);

			} else if (m.getName().equals("onDetachedFromWindow")) {
				method_AdapterView_onDetachedFromWindow = m;
				method_AdapterView_onDetachedFromWindow.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityEvent")) {
				method_AdapterView_onInitializeAccessibilityEvent = m;
				method_AdapterView_onInitializeAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityNodeInfo")) {
				method_AdapterView_onInitializeAccessibilityNodeInfo = m;
				method_AdapterView_onInitializeAccessibilityNodeInfo.setAccessible(true);

			} else if (m.getName().equals("onLayout")) {
				method_AdapterView_onLayout = m;
				method_AdapterView_onLayout.setAccessible(true);

			} else if (m.getName().equals("onRequestSendAccessibilityEvent")) {
				method_AdapterView_onRequestSendAccessibilityEvent = m;
				method_AdapterView_onRequestSendAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("performItemClick")) {
				method_AdapterView_performItemClick = m;
				method_AdapterView_performItemClick.setAccessible(true);

			} else if (m.getName().equals("rememberSyncState")) {
				method_AdapterView_rememberSyncState = m;
				method_AdapterView_rememberSyncState.setAccessible(true);

			} else if (m.getName().equals("removeAllViews")) {
				method_AdapterView_removeAllViews = m;
				method_AdapterView_removeAllViews.setAccessible(true);

			} else if (m.getName().equals("removeView")) {
				method_AdapterView_removeView = m;
				method_AdapterView_removeView.setAccessible(true);

			} else if (m.getName().equals("removeViewAt")) {
				method_AdapterView_removeViewAt = m;
				method_AdapterView_removeViewAt.setAccessible(true);

			} else if (m.getName().equals("selectionChanged")) {
				method_AdapterView_selectionChanged = m;
				method_AdapterView_selectionChanged.setAccessible(true);

			} else if (m.getName().equals("setEmptyView")) {
				method_AdapterView_setEmptyView = m;
				method_AdapterView_setEmptyView.setAccessible(true);

			} else if (m.getName().equals("setFocusable")) {
				method_AdapterView_setFocusable = m;
				method_AdapterView_setFocusable.setAccessible(true);

			} else if (m.getName().equals("setFocusableInTouchMode")) {
				method_AdapterView_setFocusableInTouchMode = m;
				method_AdapterView_setFocusableInTouchMode.setAccessible(true);

			} else if (m.getName().equals("setNextSelectedPositionInt")) {
				method_AdapterView_setNextSelectedPositionInt = m;
				method_AdapterView_setNextSelectedPositionInt.setAccessible(true);

			} else if (m.getName().equals("setOnClickListener")) {
				method_AdapterView_setOnClickListener = m;
				method_AdapterView_setOnClickListener.setAccessible(true);

			} else if (m.getName().equals("setOnItemClickListener")) {
				method_AdapterView_setOnItemClickListener = m;
				method_AdapterView_setOnItemClickListener.setAccessible(true);

			} else if (m.getName().equals("setOnItemLongClickListener")) {
				method_AdapterView_setOnItemLongClickListener = m;
				method_AdapterView_setOnItemLongClickListener.setAccessible(true);

			} else if (m.getName().equals("setOnItemSelectedListener")) {
				method_AdapterView_setOnItemSelectedListener = m;
				method_AdapterView_setOnItemSelectedListener.setAccessible(true);

			} else if (m.getName().equals("setSelectedPositionInt")) {
				method_AdapterView_setSelectedPositionInt = m;
				method_AdapterView_setSelectedPositionInt.setAccessible(true);

			} else if (m.getName().equals("access$000")) {
				method_AdapterView_access$000 = m;
				method_AdapterView_access$000.setAccessible(true);

			} else if (m.getName().equals("access$100")) {
				method_AdapterView_access$100 = m;
				method_AdapterView_access$100.setAccessible(true);

			} else if (m.getName().equals("access$200")) {
				method_AdapterView_access$200 = m;
				method_AdapterView_access$200.setAccessible(true);

			} else if (m.getName().equals("access$300")) {
				method_AdapterView_access$300 = m;
				method_AdapterView_access$300.setAccessible(true);

			} else if (m.getName().equals("fireOnSelected")) {
				method_AdapterView_fireOnSelected = m;
				method_AdapterView_fireOnSelected.setAccessible(true);

			} else if (m.getName().equals("isScrollableForAccessibility")) {
				method_AdapterView_isScrollableForAccessibility = m;
				method_AdapterView_isScrollableForAccessibility.setAccessible(true);

			} else if (m.getName().equals("performAccessibilityActionsOnSelected")) {
				method_AdapterView_performAccessibilityActionsOnSelected = m;
				method_AdapterView_performAccessibilityActionsOnSelected.setAccessible(true);

			} else if (m.getName().equals("updateEmptyStatus")) {
				method_AdapterView_updateEmptyStatus = m;
				method_AdapterView_updateEmptyStatus.setAccessible(true);

			}
		}
	}

	public static void methodInvoke_AdapterView_addView(Object obj) {
		try {
			method_AdapterView_addView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_AdapterView_canAnimate(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_canAnimate.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_checkFocus(Object obj) {
		try {
			method_AdapterView_checkFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_checkSelectionChanged(Object obj) {
		try {
			method_AdapterView_checkSelectionChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_AdapterView_dispatchPopulateAccessibilityEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_dispatchPopulateAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_dispatchRestoreInstanceState(Object obj) {
		try {
			method_AdapterView_dispatchRestoreInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_dispatchSaveInstanceState(Object obj) {
		try {
			method_AdapterView_dispatchSaveInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int methodInvoke_AdapterView_findSyncPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_findSyncPosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_getCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_getCount.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_AdapterView_getEmptyView(Object obj) {
		View o = null;
		try {
			o = (View) method_AdapterView_getEmptyView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_getFirstVisiblePosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_getFirstVisiblePosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Object methodInvoke_AdapterView_getItemAtPosition(Object obj) {
		Object o = null;
		try {
			o = (Object) method_AdapterView_getItemAtPosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static long methodInvoke_AdapterView_getItemIdAtPosition(Object obj) {
		long o = -1;
		try {
			o = (Long) method_AdapterView_getItemIdAtPosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_getLastVisiblePosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_getLastVisiblePosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_getPositionForView(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_getPositionForView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Object methodInvoke_AdapterView_getSelectedItem(Object obj) {
		Object o = null;
		try {
			o = (Object) method_AdapterView_getSelectedItem.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static long methodInvoke_AdapterView_getSelectedItemId(Object obj) {
		long o = -1;
		try {
			o = (Long) method_AdapterView_getSelectedItemId.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_getSelectedItemPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_getSelectedItemPosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_handleDataChanged(Object obj) {
		try {
			method_AdapterView_handleDataChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_AdapterView_isInFilterMode(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_isInFilterMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_AdapterView_lookForSelectablePosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_AdapterView_lookForSelectablePosition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_onDetachedFromWindow(Object obj) {
		try {
			method_AdapterView_onDetachedFromWindow.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_onInitializeAccessibilityEvent(Object obj) {
		try {
			method_AdapterView_onInitializeAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_onInitializeAccessibilityNodeInfo(Object obj) {
		try {
			method_AdapterView_onInitializeAccessibilityNodeInfo.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_onLayout(Object obj) {
		try {
			method_AdapterView_onLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_AdapterView_onRequestSendAccessibilityEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_onRequestSendAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_AdapterView_performItemClick(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_performItemClick.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_rememberSyncState(Object obj) {
		try {
			method_AdapterView_rememberSyncState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_removeAllViews(Object obj) {
		try {
			method_AdapterView_removeAllViews.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_removeView(Object obj) {
		try {
			method_AdapterView_removeView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_removeViewAt(Object obj) {
		try {
			method_AdapterView_removeViewAt.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_selectionChanged(Object obj) {
		try {
			method_AdapterView_selectionChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setEmptyView(Object obj) {
		try {
			method_AdapterView_setEmptyView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setFocusable(Object obj) {
		try {
			method_AdapterView_setFocusable.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setFocusableInTouchMode(Object obj) {
		try {
			method_AdapterView_setFocusableInTouchMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setNextSelectedPositionInt(Object obj, Object v) {
		try {
			method_AdapterView_setNextSelectedPositionInt.invoke(obj, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setOnClickListener(Object obj) {
		try {
			method_AdapterView_setOnClickListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setOnItemClickListener(Object obj) {
		try {
			method_AdapterView_setOnItemClickListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setOnItemLongClickListener(Object obj) {
		try {
			method_AdapterView_setOnItemLongClickListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setOnItemSelectedListener(Object obj) {
		try {
			method_AdapterView_setOnItemSelectedListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_setSelectedPositionInt(Object obj, Object v) {
//		try {
//			method_AdapterView_setSelectedPositionInt.invoke(obj, v);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			field_AdapterView_mSelectedPosition.set(obj, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void methodInvoke_AdapterView_setSelectedPositionInt2(Object obj, Object v2) {
		try {
			field_AdapterView_mSelectedRowId.set(obj, v2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void methodInvoke_AdapterView_access$000(Object obj) {
		try {
			method_AdapterView_access$000.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Parcelable methodInvoke_AdapterView_access$100(Object obj) {
		Parcelable o = null;
		try {
			o = (Parcelable) method_AdapterView_access$100.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_access$200(Object obj) {
		try {
			method_AdapterView_access$200.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_access$300(Object obj) {
		try {
			method_AdapterView_access$300.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_fireOnSelected(Object obj) {
		try {
			method_AdapterView_fireOnSelected.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_AdapterView_isScrollableForAccessibility(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_AdapterView_isScrollableForAccessibility.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_AdapterView_performAccessibilityActionsOnSelected(Object obj) {
		try {
			method_AdapterView_performAccessibilityActionsOnSelected.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_AdapterView_updateEmptyStatus(Object obj) {
		try {
			method_AdapterView_updateEmptyStatus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Field field_AdapterView_mSelectionNotifier;
	public static Field field_AdapterView_mOnItemSelectedListener;
	public static Field field_AdapterView_mOnItemLongClickListener;
	public static Field field_AdapterView_mOnItemClickListener;
	public static Field field_AdapterView_mEmptyView;
	public static Field field_AdapterView_mFirstPosition;
	public static Field field_AdapterView_mSyncRowId;
	public static Field field_AdapterView_mSyncHeight;
	public static Field field_AdapterView_mSelectedRowId;
	public static Field field_AdapterView_mOldSelectedRowId;
	public static Field field_AdapterView_mNextSelectedRowId;
	public static Field field_AdapterView_mNextSelectedPosition;
	public static Field field_AdapterView_mOldItemCount;
	public static Field field_AdapterView_mOldSelectedPosition;
	public static Field field_AdapterView_mNeedSync;
	public static Field field_AdapterView_mDesiredFocusableState;
	public static Field field_AdapterView_mDesiredFocusableInTouchModeState;
	public static Field field_AdapterView_mDataChanged;
	public static Field field_AdapterView_mSelectedPosition;
	public static Field field_AdapterView_mLayoutHeight;
	public static Field field_AdapterView_mBlockLayoutRequests;
	public static Field field_AdapterView_mSpecificTop;
	public static Field field_AdapterView_mItemCount;
	public static Field field_AdapterView_mSyncMode;
	public static Field field_AdapterView_mSyncPosition;
	public static Field field_AdapterView_mInLayout;

	private static void initField_AdapterView(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field f : fields) {

			if (f.getName().equals("mSelectionNotifier")) {
				field_AdapterView_mSelectionNotifier = f;
				field_AdapterView_mSelectionNotifier.setAccessible(true);

			} else if (f.getName().equals("mOnItemSelectedListener")) {
				field_AdapterView_mOnItemSelectedListener = f;
				field_AdapterView_mOnItemSelectedListener.setAccessible(true);

			} else if (f.getName().equals("mOnItemLongClickListener")) {
				field_AdapterView_mOnItemLongClickListener = f;
				field_AdapterView_mOnItemLongClickListener.setAccessible(true);

			} else if (f.getName().equals("mOnItemClickListener")) {
				field_AdapterView_mOnItemClickListener = f;
				field_AdapterView_mOnItemClickListener.setAccessible(true);

			} else if (f.getName().equals("mEmptyView")) {
				field_AdapterView_mEmptyView = f;
				field_AdapterView_mEmptyView.setAccessible(true);

			} else if (f.getName().equals("mFirstPosition")) {
				field_AdapterView_mFirstPosition = f;
				field_AdapterView_mFirstPosition.setAccessible(true);

			} else if (f.getName().equals("mSyncRowId")) {
				field_AdapterView_mSyncRowId = f;
				field_AdapterView_mSyncRowId.setAccessible(true);

			} else if (f.getName().equals("mSyncHeight")) {
				field_AdapterView_mSyncHeight = f;
				field_AdapterView_mSyncHeight.setAccessible(true);

			} else if (f.getName().equals("mSelectedRowId")) {
				field_AdapterView_mSelectedRowId = f;
				field_AdapterView_mSelectedRowId.setAccessible(true);

			} else if (f.getName().equals("mOldSelectedRowId")) {
				field_AdapterView_mOldSelectedRowId = f;
				field_AdapterView_mOldSelectedRowId.setAccessible(true);

			} else if (f.getName().equals("mNextSelectedRowId")) {
				field_AdapterView_mNextSelectedRowId = f;
				field_AdapterView_mNextSelectedRowId.setAccessible(true);

			} else if (f.getName().equals("mNextSelectedPosition")) {
				field_AdapterView_mNextSelectedPosition = f;
				field_AdapterView_mNextSelectedPosition.setAccessible(true);

			} else if (f.getName().equals("mOldItemCount")) {
				field_AdapterView_mOldItemCount = f;
				field_AdapterView_mOldItemCount.setAccessible(true);

			} else if (f.getName().equals("mOldSelectedPosition")) {
				field_AdapterView_mOldSelectedPosition = f;
				field_AdapterView_mOldSelectedPosition.setAccessible(true);

			} else if (f.getName().equals("mNeedSync")) {
				field_AdapterView_mNeedSync = f;
				field_AdapterView_mNeedSync.setAccessible(true);

			} else if (f.getName().equals("mDesiredFocusableState")) {
				field_AdapterView_mDesiredFocusableState = f;
				field_AdapterView_mDesiredFocusableState.setAccessible(true);

			} else if (f.getName().equals("mDesiredFocusableInTouchModeState")) {
				field_AdapterView_mDesiredFocusableInTouchModeState = f;
				field_AdapterView_mDesiredFocusableInTouchModeState.setAccessible(true);

			} else if (f.getName().equals("mDataChanged")) {
				field_AdapterView_mDataChanged = f;
				field_AdapterView_mDataChanged.setAccessible(true);

			} else if (f.getName().equals("mSelectedPosition")) {
				field_AdapterView_mSelectedPosition = f;
				field_AdapterView_mSelectedPosition.setAccessible(true);

			} else if (f.getName().equals("mLayoutHeight")) {
				field_AdapterView_mLayoutHeight = f;
				field_AdapterView_mLayoutHeight.setAccessible(true);

			} else if (f.getName().equals("mBlockLayoutRequests")) {
				field_AdapterView_mBlockLayoutRequests = f;
				field_AdapterView_mBlockLayoutRequests.setAccessible(true);

			} else if (f.getName().equals("mSpecificTop")) {
				field_AdapterView_mSpecificTop = f;
				field_AdapterView_mSpecificTop.setAccessible(true);

			} else if (f.getName().equals("mItemCount")) {
				field_AdapterView_mItemCount = f;
				field_AdapterView_mItemCount.setAccessible(true);

			} else if (f.getName().equals("mSyncMode")) {
				field_AdapterView_mSyncMode = f;
				field_AdapterView_mSyncMode.setAccessible(true);

			} else if (f.getName().equals("mSyncPosition")) {
				field_AdapterView_mSyncPosition = f;
				field_AdapterView_mSyncPosition.setAccessible(true);

			} else if (f.getName().equals("mInLayout")) {
				field_AdapterView_mInLayout = f;
				field_AdapterView_mInLayout.setAccessible(true);
			}
		}
	}

	public static void setField_AdapterView_mSelectionNotifier(Object obj, Object value) {
		try {
			field_AdapterView_mSelectionNotifier.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OnItemSelectedListener getField_AdapterView_mOnItemSelectedListener(Object obj) {
		OnItemSelectedListener o = null;
		try {
			o = (OnItemSelectedListener) field_AdapterView_mOnItemSelectedListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOnItemSelectedListener(Object obj, Object value) {
		try {
			field_AdapterView_mOnItemSelectedListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OnItemLongClickListener getField_AdapterView_mOnItemLongClickListener(Object obj) {
		OnItemLongClickListener o = null;
		try {
			o = (OnItemLongClickListener) field_AdapterView_mOnItemLongClickListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOnItemLongClickListener(Object obj, Object value) {
		try {
			field_AdapterView_mOnItemLongClickListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OnItemClickListener getField_AdapterView_mOnItemClickListener(Object obj) {
		OnItemClickListener o = null;
		try {
			o = (OnItemClickListener) field_AdapterView_mOnItemClickListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOnItemClickListener(Object obj, Object value) {
		try {
			field_AdapterView_mOnItemClickListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static View getField_AdapterView_mEmptyView(Object obj) {
		View o = null;
		try {
			o = (View) field_AdapterView_mEmptyView.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mEmptyView(Object obj, Object value) {
		try {
			field_AdapterView_mEmptyView.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mFirstPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mFirstPosition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mFirstPosition(Object obj, Object value) {
		try {
			field_AdapterView_mFirstPosition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_AdapterView_mSyncRowId(Object obj) {
		long o = -1;
		try {
			o = (Long) field_AdapterView_mSyncRowId.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSyncRowId(Object obj, Object value) {
		try {
			field_AdapterView_mSyncRowId.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_AdapterView_mSyncHeight(Object obj) {
		long o = -1;
		try {
			o = (Long) field_AdapterView_mSyncHeight.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSyncHeight(Object obj, Object value) {
		try {
			field_AdapterView_mSyncHeight.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_AdapterView_mSelectedRowId(Object obj) {
		long o = -1;
		try {
			o = (Long) field_AdapterView_mSelectedRowId.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSelectedRowId(Object obj, Object value) {
		try {
			field_AdapterView_mSelectedRowId.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_AdapterView_mOldSelectedRowId(Object obj) {
		long o = -1;
		try {
			o = (Long) field_AdapterView_mOldSelectedRowId.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOldSelectedRowId(Object obj, Object value) {
		try {
			field_AdapterView_mOldSelectedRowId.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_AdapterView_mNextSelectedRowId(Object obj) {
		long o = -1;
		try {
			o = (Long) field_AdapterView_mNextSelectedRowId.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mNextSelectedRowId(Object obj, Object value) {
		try {
			field_AdapterView_mNextSelectedRowId.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mNextSelectedPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mNextSelectedPosition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mNextSelectedPosition(Object obj, Object value) {
		try {
			field_AdapterView_mNextSelectedPosition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mOldItemCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mOldItemCount.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOldItemCount(Object obj, Object value) {
		try {
			field_AdapterView_mOldItemCount.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mOldSelectedPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mOldSelectedPosition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mOldSelectedPosition(Object obj, Object value) {
		try {
			field_AdapterView_mOldSelectedPosition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mNeedSync(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mNeedSync.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mNeedSync(Object obj, Object value) {
		try {
			field_AdapterView_mNeedSync.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mDesiredFocusableState(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mDesiredFocusableState.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mDesiredFocusableState(Object obj, Object value) {
		try {
			field_AdapterView_mDesiredFocusableState.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mDesiredFocusableInTouchModeState(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mDesiredFocusableInTouchModeState.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mDesiredFocusableInTouchModeState(Object obj, Object value) {
		try {
			field_AdapterView_mDesiredFocusableInTouchModeState.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mDataChanged(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mDataChanged.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mDataChanged(Object obj, Object value) {
		try {
			field_AdapterView_mDataChanged.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mSelectedPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mSelectedPosition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSelectedPosition(Object obj, Object value) {
		try {
			field_AdapterView_mSelectedPosition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mLayoutHeight(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mLayoutHeight.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mLayoutHeight(Object obj, Object value) {
		try {
			field_AdapterView_mLayoutHeight.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mBlockLayoutRequests(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mBlockLayoutRequests.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mBlockLayoutRequests(Object obj, Object value) {
		try {
			field_AdapterView_mBlockLayoutRequests.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mSpecificTop(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mSpecificTop.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSpecificTop(Object obj, Object value) {
		try {
			field_AdapterView_mSpecificTop.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mItemCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mItemCount.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mItemCount(Object obj, Object value) {
		try {
			field_AdapterView_mItemCount.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mSyncMode(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mSyncMode.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSyncMode(Object obj, Object value) {
		try {
			field_AdapterView_mSyncMode.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_AdapterView_mSyncPosition(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_AdapterView_mSyncPosition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mSyncPosition(Object obj, Object value) {
		try {
			field_AdapterView_mSyncPosition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_AdapterView_mInLayout(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_AdapterView_mInLayout.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_AdapterView_mInLayout(Object obj, Object value) {
		try {
			field_AdapterView_mInLayout.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Method method_ViewGroup_addChildrenForAccessibility;
	public static Method method_ViewGroup_addFocusables;
	public static Method method_ViewGroup_addStatesFromChildren;
	public static Method method_ViewGroup_addTouchables;
	public static Method method_ViewGroup_addView;
	public static Method method_ViewGroup_addViewInLayout;
	public static Method method_ViewGroup_attachLayoutAnimationParameters;
	public static Method method_ViewGroup_attachViewToParent;
	public static Method method_ViewGroup_bringChildToFront;
	public static Method method_ViewGroup_canAnimate;
	public static Method method_ViewGroup_checkLayoutParams;
	public static Method method_ViewGroup_childDrawableStateChanged;
	public static Method method_ViewGroup_childHasTransientStateChanged;
	public static Method method_ViewGroup_cleanupLayoutState;
	public static Method method_ViewGroup_clearChildFocus;
	public static Method method_ViewGroup_clearDisappearingChildren;
	public static Method method_ViewGroup_clearFocus;
	public static Method method_ViewGroup_computeOpticalInsets;
	public static Method method_ViewGroup_createSnapshot;
	public static Method method_ViewGroup_debug;
	public static Method method_ViewGroup_detachAllViewsFromParent;
	public static Method method_ViewGroup_detachViewFromParent;
	public static Method method_ViewGroup_detachViewsFromParent;
	public static Method method_ViewGroup_dispatchAttachedToWindow;
	public static Method method_ViewGroup_dispatchCancelPendingInputEvents;
	public static Method method_ViewGroup_dispatchCollectViewAttributes;
	public static Method method_ViewGroup_dispatchConfigurationChanged;
	public static Method method_ViewGroup_dispatchDetachedFromWindow;
	public static Method method_ViewGroup_dispatchDisplayHint;
	public static Method method_ViewGroup_dispatchDragEvent;
	public static Method method_ViewGroup_dispatchDraw;
	public static Method method_ViewGroup_dispatchFinishTemporaryDetach;
	public static Method method_ViewGroup_dispatchFreezeSelfOnly;
	public static Method method_ViewGroup_dispatchGenericFocusedEvent;
	public static Method method_ViewGroup_dispatchGenericPointerEvent;
	public static Method method_ViewGroup_dispatchGetDisplayList;
	public static Method method_ViewGroup_dispatchHoverEvent;
	public static Method method_ViewGroup_dispatchKeyEvent;
	public static Method method_ViewGroup_dispatchKeyEventPreIme;
	public static Method method_ViewGroup_dispatchKeyShortcutEvent;
	public static Method method_ViewGroup_dispatchPopulateAccessibilityEventInternal;
	public static Method method_ViewGroup_dispatchRestoreInstanceState;
	public static Method method_ViewGroup_dispatchSaveInstanceState;
	public static Method method_ViewGroup_dispatchScreenStateChanged;
	public static Method method_ViewGroup_dispatchSetActivated;
	public static Method method_ViewGroup_dispatchSetPressed;
	public static Method method_ViewGroup_dispatchSetSelected;
	public static Method method_ViewGroup_dispatchStartTemporaryDetach;
	public static Method method_ViewGroup_dispatchSystemUiVisibilityChanged;
	public static Method method_ViewGroup_dispatchThawSelfOnly;
	public static Method method_ViewGroup_dispatchTouchEvent;
	public static Method method_ViewGroup_dispatchTrackballEvent;
	public static Method method_ViewGroup_dispatchUnhandledMove;
	public static Method method_ViewGroup_dispatchVisibilityChanged;
	public static Method method_ViewGroup_dispatchWindowFocusChanged;
	public static Method method_ViewGroup_dispatchWindowSystemUiVisiblityChanged;
	public static Method method_ViewGroup_dispatchWindowVisibilityChanged;
	public static Method method_ViewGroup_drawChild;
	public static Method method_ViewGroup_drawableStateChanged;
	public static Method method_ViewGroup_endViewTransition;
	public static Method method_ViewGroup_findFocus;
	public static Method method_ViewGroup_findFrontmostDroppableChildAt;
	public static Method method_ViewGroup_findViewByAccessibilityIdTraversal;
	public static Method method_ViewGroup_findViewByPredicateTraversal;
	public static Method method_ViewGroup_findViewTraversal;
	public static Method method_ViewGroup_findViewWithTagTraversal;
	public static Method method_ViewGroup_findViewsWithText;
	public static Method method_ViewGroup_finishAnimatingView;
	public static Method method_ViewGroup_fitSystemWindows;
	public static Method method_ViewGroup_focusSearch;
	public static Method method_ViewGroup_focusableViewAvailable;
	public static Method method_ViewGroup_gatherTransparentRegion;
	public static Method method_ViewGroup_generateDefaultLayoutParams;
	public static Method method_ViewGroup_generateLayoutParams;
	public static Method method_ViewGroup_getChildAt;
	public static Method method_ViewGroup_getChildCount;
	public static Method method_ViewGroup_getChildDrawingOrder;
	public static Method method_ViewGroup_getChildStaticTransformation;
	public static Method method_ViewGroup_getChildTransformation;
	public static Method method_ViewGroup_getChildVisibleRect;
	public static Method method_ViewGroup_getClipChildren;
	public static Method method_ViewGroup_getDescendantFocusability;
	public static Method method_ViewGroup_getFocusedChild;
	public static Method method_ViewGroup_getLayoutAnimation;
	public static Method method_ViewGroup_getLayoutAnimationListener;
	public static Method method_ViewGroup_getLayoutMode;
	public static Method method_ViewGroup_getLayoutTransition;
	public static Method method_ViewGroup_getOverlay;
	public static Method method_ViewGroup_getPersistentDrawingCache;
	public static Method method_ViewGroup_handleFocusGainInternal;
	public static Method method_ViewGroup_hasFocus;
	public static Method method_ViewGroup_hasFocusable;
	public static Method method_ViewGroup_hasHoveredChild;
	public static Method method_ViewGroup_hasTransientState;
	public static Method method_ViewGroup_indexOfChild;
	public static Method method_ViewGroup_internalSetPadding;
	public static Method method_ViewGroup_invalidateChildFast;
	public static Method method_ViewGroup_invalidateChildInParent;
	public static Method method_ViewGroup_invalidateChildInParentFast;
	public static Method method_ViewGroup_invalidateInheritedLayoutMode;
	public static Method method_ViewGroup_isAlwaysDrawnWithCacheEnabled;
	public static Method method_ViewGroup_isAnimationCacheEnabled;
	public static Method method_ViewGroup_isChildrenDrawingOrderEnabled;
	public static Method method_ViewGroup_isChildrenDrawnWithCacheEnabled;
	public static Method method_ViewGroup_isLayoutModeOptical;
	public static Method method_ViewGroup_isLayoutSuppressed;
	public static Method method_ViewGroup_isMotionEventSplittingEnabled;
	public static Method method_ViewGroup_isTransformedTouchPointInView;
	public static Method method_ViewGroup_isViewTransitioning;
	public static Method method_ViewGroup_jumpDrawablesToCurrentState;
	public static Method method_ViewGroup_makeOptionalFitsSystemWindows;
	public static Method method_ViewGroup_measureChild;
	public static Method method_ViewGroup_measureChildWithMargins;
	public static Method method_ViewGroup_measureChildren;
	public static Method method_ViewGroup_notifyChildOfDrag;
	public static Method method_ViewGroup_notifySubtreeAccessibilityStateChanged;
	public static Method method_ViewGroup_offsetChildrenTopAndBottom;
	public static Method method_ViewGroup_offsetRectBetweenParentAndChild;
	public static Method method_ViewGroup_onAnimationEnd;
	public static Method method_ViewGroup_onAnimationStart;
	public static Method method_ViewGroup_onAttachedToWindow;
	public static Method method_ViewGroup_onChildVisibilityChanged;
	public static Method method_ViewGroup_onCreateDrawableState;
	public static Method method_ViewGroup_onDebugDraw;
	public static Method method_ViewGroup_onDebugDrawMargins;
	public static Method method_ViewGroup_onDetachedFromWindow;
	public static Method method_ViewGroup_onInitializeAccessibilityEventInternal;
	public static Method method_ViewGroup_onInitializeAccessibilityNodeInfoInternal;
	public static Method method_ViewGroup_onInterceptHoverEvent;
	public static Method method_ViewGroup_onInterceptTouchEvent;
	public static Method method_ViewGroup_onRequestFocusInDescendants;
	public static Method method_ViewGroup_onRequestSendAccessibilityEvent;
	public static Method method_ViewGroup_onRequestSendAccessibilityEventInternal;
	public static Method method_ViewGroup_onSetLayoutParams;
	public static Method method_ViewGroup_onViewAdded;
	public static Method method_ViewGroup_onViewRemoved;
	public static Method method_ViewGroup_recomputeViewAttributes;
	public static Method method_ViewGroup_removeAllViews;
	public static Method method_ViewGroup_removeAllViewsInLayout;
	public static Method method_ViewGroup_removeDetachedView;
	public static Method method_ViewGroup_removeView;
	public static Method method_ViewGroup_removeViewAt;
	public static Method method_ViewGroup_removeViewInLayout;
	public static Method method_ViewGroup_removeViews;
	public static Method method_ViewGroup_removeViewsInLayout;
	public static Method method_ViewGroup_requestChildFocus;
	public static Method method_ViewGroup_requestChildRectangleOnScreen;
	public static Method method_ViewGroup_requestDisallowInterceptTouchEvent;
	public static Method method_ViewGroup_requestFocus;
	public static Method method_ViewGroup_requestSendAccessibilityEvent;
	public static Method method_ViewGroup_requestTransitionStart;
	public static Method method_ViewGroup_requestTransparentRegion;
	public static Method method_ViewGroup_resetResolvedDrawables;
	public static Method method_ViewGroup_resetResolvedLayoutDirection;
	public static Method method_ViewGroup_resetResolvedPadding;
	public static Method method_ViewGroup_resetResolvedTextAlignment;
	public static Method method_ViewGroup_resetResolvedTextDirection;
	public static Method method_ViewGroup_resetSubtreeAccessibilityStateChanged;
	public static Method method_ViewGroup_resolveDrawables;
	public static Method method_ViewGroup_resolveLayoutDirection;
	public static Method method_ViewGroup_resolveLayoutParams;
	public static Method method_ViewGroup_resolvePadding;
	public static Method method_ViewGroup_resolveRtlPropertiesIfNeeded;
	public static Method method_ViewGroup_resolveTextAlignment;
	public static Method method_ViewGroup_resolveTextDirection;
	public static Method method_ViewGroup_scheduleLayoutAnimation;
	public static Method method_ViewGroup_setAddStatesFromChildren;
	public static Method method_ViewGroup_setAlwaysDrawnWithCacheEnabled;
	public static Method method_ViewGroup_setAnimationCacheEnabled;
	public static Method method_ViewGroup_setChildrenDrawingCacheEnabled;
	public static Method method_ViewGroup_setChildrenDrawingOrderEnabled;
	public static Method method_ViewGroup_setChildrenDrawnWithCacheEnabled;
	public static Method method_ViewGroup_setClipChildren;
	public static Method method_ViewGroup_setClipToPadding;
	public static Method method_ViewGroup_setDescendantFocusability;
	public static Method method_ViewGroup_setLayoutAnimation;
	public static Method method_ViewGroup_setLayoutAnimationListener;
	public static Method method_ViewGroup_setLayoutMode;
	public static Method method_ViewGroup_setLayoutTransition;
	public static Method method_ViewGroup_setMotionEventSplittingEnabled;
	public static Method method_ViewGroup_setOnHierarchyChangeListener;
	public static Method method_ViewGroup_setPersistentDrawingCache;
	public static Method method_ViewGroup_setStaticTransformationsEnabled;
	public static Method method_ViewGroup_shouldDelayChildPressedState;
	public static Method method_ViewGroup_showContextMenuForChild;
	public static Method method_ViewGroup_startActionModeForChild;
	public static Method method_ViewGroup_startLayoutAnimation;
	public static Method method_ViewGroup_startViewTransition;
	public static Method method_ViewGroup_suppressLayout;
	public static Method method_ViewGroup_unFocus;
	public static Method method_ViewGroup_updateLocalSystemUiVisibility;
	public static Method method_ViewGroup_updateViewLayout;
	public static Method method_ViewGroup_access$000;
	public static Method method_ViewGroup_access$100;
	public static Method method_ViewGroup_access$200;
	public static Method method_ViewGroup_access$300;
	public static Method method_ViewGroup_access$302;
	public static Method method_ViewGroup_access$400;
	public static Method method_ViewGroup_access$500;
	public static Method method_ViewGroup_addDisappearingView;
	public static Method method_ViewGroup_addInArray;
	public static Method method_ViewGroup_addTouchTarget;
	public static Method method_ViewGroup_addViewInner;
	public static Method method_ViewGroup_bindLayoutAnimation;
	public static Method method_ViewGroup_canViewReceivePointerEvents;
	public static Method method_ViewGroup_cancelAndClearTouchTargets;
	public static Method method_ViewGroup_cancelHoverTarget;
	public static Method method_ViewGroup_cancelTouchTarget;
	public static Method method_ViewGroup_clearCachedLayoutMode;
	public static Method method_ViewGroup_clearTouchTargets;
	public static Method method_ViewGroup_debugDraw;
	public static Method method_ViewGroup_dipsToPixels;
	public static Method method_ViewGroup_dispatchTransformedGenericPointerEvent;
	public static Method method_ViewGroup_dispatchTransformedTouchEvent;
	public static Method method_ViewGroup_drawCorner;
	public static Method method_ViewGroup_drawRect;
	public static Method method_ViewGroup_drawRectCorners;
	public static Method method_ViewGroup_exitHoverTargets;
	public static Method method_ViewGroup_fillDifference;
	public static Method method_ViewGroup_fillRect;
	public static Method method_ViewGroup_getChildMeasureSpec;
	public static Method method_ViewGroup_getDebugPaint;
	public static Method method_ViewGroup_getLocalPoint;
	public static Method method_ViewGroup_getTouchTarget;
	public static Method method_ViewGroup_hasBooleanFlag;
	public static Method method_ViewGroup_initFromAttributes;
	public static Method method_ViewGroup_initViewGroup;
	public static Method method_ViewGroup_notifyAnimationListener;
	public static Method method_ViewGroup_obtainMotionEventNoHistoryOrSelf;
	public static Method method_ViewGroup_removeFromArray;
	public static Method method_ViewGroup_removePointersFromTouchTargets;
	public static Method method_ViewGroup_removeViewInternal;
	public static Method method_ViewGroup_removeViewsInternal;
	public static Method method_ViewGroup_resetCancelNextUpFlag;
	public static Method method_ViewGroup_resetTouchState;
	public static Method method_ViewGroup_setBooleanFlag;
	public static Method method_ViewGroup_sign;
	public static Method method_ViewGroup_isLayoutRtl;

	private static void initMethod_ViewGroup(Class<?> clz) {
		Method[] methods = clz.getDeclaredMethods();
		for (Method m : methods) {

			if (m.getName().equals("addChildrenForAccessibility")) {
				method_ViewGroup_addChildrenForAccessibility = m;
				method_ViewGroup_addChildrenForAccessibility.setAccessible(true);

			} else if (m.getName().equals("addFocusables")) {
				method_ViewGroup_addFocusables = m;
				method_ViewGroup_addFocusables.setAccessible(true);

			} else if (m.getName().equals("addStatesFromChildren")) {
				method_ViewGroup_addStatesFromChildren = m;
				method_ViewGroup_addStatesFromChildren.setAccessible(true);

			} else if (m.getName().equals("addTouchables")) {
				method_ViewGroup_addTouchables = m;
				method_ViewGroup_addTouchables.setAccessible(true);

			} else if (m.getName().equals("addView")) {
				method_ViewGroup_addView = m;
				method_ViewGroup_addView.setAccessible(true);

			} else if (m.getName().equals("addViewInLayout")) {
				method_ViewGroup_addViewInLayout = m;
				method_ViewGroup_addViewInLayout.setAccessible(true);

			} else if (m.getName().equals("attachLayoutAnimationParameters")) {
				method_ViewGroup_attachLayoutAnimationParameters = m;
				method_ViewGroup_attachLayoutAnimationParameters.setAccessible(true);

			} else if (m.getName().equals("attachViewToParent")) {
				method_ViewGroup_attachViewToParent = m;
				method_ViewGroup_attachViewToParent.setAccessible(true);

			} else if (m.getName().equals("bringChildToFront")) {
				method_ViewGroup_bringChildToFront = m;
				method_ViewGroup_bringChildToFront.setAccessible(true);

			} else if (m.getName().equals("canAnimate")) {
				method_ViewGroup_canAnimate = m;
				method_ViewGroup_canAnimate.setAccessible(true);

			} else if (m.getName().equals("checkLayoutParams")) {
				method_ViewGroup_checkLayoutParams = m;
				method_ViewGroup_checkLayoutParams.setAccessible(true);

			} else if (m.getName().equals("childDrawableStateChanged")) {
				method_ViewGroup_childDrawableStateChanged = m;
				method_ViewGroup_childDrawableStateChanged.setAccessible(true);

			} else if (m.getName().equals("childHasTransientStateChanged")) {
				method_ViewGroup_childHasTransientStateChanged = m;
				method_ViewGroup_childHasTransientStateChanged.setAccessible(true);

			} else if (m.getName().equals("cleanupLayoutState")) {
				method_ViewGroup_cleanupLayoutState = m;
				method_ViewGroup_cleanupLayoutState.setAccessible(true);

			} else if (m.getName().equals("clearChildFocus")) {
				method_ViewGroup_clearChildFocus = m;
				method_ViewGroup_clearChildFocus.setAccessible(true);

			} else if (m.getName().equals("clearDisappearingChildren")) {
				method_ViewGroup_clearDisappearingChildren = m;
				method_ViewGroup_clearDisappearingChildren.setAccessible(true);

			} else if (m.getName().equals("clearFocus")) {
				method_ViewGroup_clearFocus = m;
				method_ViewGroup_clearFocus.setAccessible(true);

			} else if (m.getName().equals("computeOpticalInsets")) {
				method_ViewGroup_computeOpticalInsets = m;
				method_ViewGroup_computeOpticalInsets.setAccessible(true);

			} else if (m.getName().equals("createSnapshot")) {
				method_ViewGroup_createSnapshot = m;
				method_ViewGroup_createSnapshot.setAccessible(true);

			} else if (m.getName().equals("debug")) {
				method_ViewGroup_debug = m;
				method_ViewGroup_debug.setAccessible(true);

			} else if (m.getName().equals("detachAllViewsFromParent")) {
				method_ViewGroup_detachAllViewsFromParent = m;
				method_ViewGroup_detachAllViewsFromParent.setAccessible(true);

			} else if (m.getName().equals("detachViewFromParent")) {
				method_ViewGroup_detachViewFromParent = m;
				method_ViewGroup_detachViewFromParent.setAccessible(true);

			} else if (m.getName().equals("detachViewsFromParent")) {
				method_ViewGroup_detachViewsFromParent = m;
				method_ViewGroup_detachViewsFromParent.setAccessible(true);

			} else if (m.getName().equals("dispatchAttachedToWindow")) {
				method_ViewGroup_dispatchAttachedToWindow = m;
				method_ViewGroup_dispatchAttachedToWindow.setAccessible(true);

			} else if (m.getName().equals("dispatchCancelPendingInputEvents")) {
				method_ViewGroup_dispatchCancelPendingInputEvents = m;
				method_ViewGroup_dispatchCancelPendingInputEvents.setAccessible(true);

			} else if (m.getName().equals("dispatchCollectViewAttributes")) {
				method_ViewGroup_dispatchCollectViewAttributes = m;
				method_ViewGroup_dispatchCollectViewAttributes.setAccessible(true);

			} else if (m.getName().equals("dispatchConfigurationChanged")) {
				method_ViewGroup_dispatchConfigurationChanged = m;
				method_ViewGroup_dispatchConfigurationChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchDetachedFromWindow")) {
				method_ViewGroup_dispatchDetachedFromWindow = m;
				method_ViewGroup_dispatchDetachedFromWindow.setAccessible(true);

			} else if (m.getName().equals("dispatchDisplayHint")) {
				method_ViewGroup_dispatchDisplayHint = m;
				method_ViewGroup_dispatchDisplayHint.setAccessible(true);

			} else if (m.getName().equals("dispatchDragEvent")) {
				method_ViewGroup_dispatchDragEvent = m;
				method_ViewGroup_dispatchDragEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchDraw")) {
				method_ViewGroup_dispatchDraw = m;
				method_ViewGroup_dispatchDraw.setAccessible(true);

			} else if (m.getName().equals("dispatchFinishTemporaryDetach")) {
				method_ViewGroup_dispatchFinishTemporaryDetach = m;
				method_ViewGroup_dispatchFinishTemporaryDetach.setAccessible(true);

			} else if (m.getName().equals("dispatchFreezeSelfOnly")) {
				method_ViewGroup_dispatchFreezeSelfOnly = m;
				method_ViewGroup_dispatchFreezeSelfOnly.setAccessible(true);

			} else if (m.getName().equals("dispatchGenericFocusedEvent")) {
				method_ViewGroup_dispatchGenericFocusedEvent = m;
				method_ViewGroup_dispatchGenericFocusedEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchGenericPointerEvent")) {
				method_ViewGroup_dispatchGenericPointerEvent = m;
				method_ViewGroup_dispatchGenericPointerEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchGetDisplayList")) {
				method_ViewGroup_dispatchGetDisplayList = m;
				method_ViewGroup_dispatchGetDisplayList.setAccessible(true);

			} else if (m.getName().equals("dispatchHoverEvent")) {
				method_ViewGroup_dispatchHoverEvent = m;
				method_ViewGroup_dispatchHoverEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchKeyEvent")) {
				method_ViewGroup_dispatchKeyEvent = m;
				method_ViewGroup_dispatchKeyEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchKeyEventPreIme")) {
				method_ViewGroup_dispatchKeyEventPreIme = m;
				method_ViewGroup_dispatchKeyEventPreIme.setAccessible(true);

			} else if (m.getName().equals("dispatchKeyShortcutEvent")) {
				method_ViewGroup_dispatchKeyShortcutEvent = m;
				method_ViewGroup_dispatchKeyShortcutEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchPopulateAccessibilityEventInternal")) {
				method_ViewGroup_dispatchPopulateAccessibilityEventInternal = m;
				method_ViewGroup_dispatchPopulateAccessibilityEventInternal.setAccessible(true);

			} else if (m.getName().equals("dispatchRestoreInstanceState")) {
				method_ViewGroup_dispatchRestoreInstanceState = m;
				method_ViewGroup_dispatchRestoreInstanceState.setAccessible(true);

			} else if (m.getName().equals("dispatchSaveInstanceState")) {
				method_ViewGroup_dispatchSaveInstanceState = m;
				method_ViewGroup_dispatchSaveInstanceState.setAccessible(true);

			} else if (m.getName().equals("dispatchScreenStateChanged")) {
				method_ViewGroup_dispatchScreenStateChanged = m;
				method_ViewGroup_dispatchScreenStateChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchSetActivated")) {
				method_ViewGroup_dispatchSetActivated = m;
				method_ViewGroup_dispatchSetActivated.setAccessible(true);

			} else if (m.getName().equals("dispatchSetPressed")) {
				method_ViewGroup_dispatchSetPressed = m;
				method_ViewGroup_dispatchSetPressed.setAccessible(true);

			} else if (m.getName().equals("dispatchSetSelected")) {
				method_ViewGroup_dispatchSetSelected = m;
				method_ViewGroup_dispatchSetSelected.setAccessible(true);

			} else if (m.getName().equals("dispatchStartTemporaryDetach")) {
				method_ViewGroup_dispatchStartTemporaryDetach = m;
				method_ViewGroup_dispatchStartTemporaryDetach.setAccessible(true);

			} else if (m.getName().equals("dispatchSystemUiVisibilityChanged")) {
				method_ViewGroup_dispatchSystemUiVisibilityChanged = m;
				method_ViewGroup_dispatchSystemUiVisibilityChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchThawSelfOnly")) {
				method_ViewGroup_dispatchThawSelfOnly = m;
				method_ViewGroup_dispatchThawSelfOnly.setAccessible(true);

			} else if (m.getName().equals("dispatchTouchEvent")) {
				method_ViewGroup_dispatchTouchEvent = m;
				method_ViewGroup_dispatchTouchEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchTrackballEvent")) {
				method_ViewGroup_dispatchTrackballEvent = m;
				method_ViewGroup_dispatchTrackballEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchUnhandledMove")) {
				method_ViewGroup_dispatchUnhandledMove = m;
				method_ViewGroup_dispatchUnhandledMove.setAccessible(true);

			} else if (m.getName().equals("dispatchVisibilityChanged")) {
				method_ViewGroup_dispatchVisibilityChanged = m;
				method_ViewGroup_dispatchVisibilityChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchWindowFocusChanged")) {
				method_ViewGroup_dispatchWindowFocusChanged = m;
				method_ViewGroup_dispatchWindowFocusChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchWindowSystemUiVisiblityChanged")) {
				method_ViewGroup_dispatchWindowSystemUiVisiblityChanged = m;
				method_ViewGroup_dispatchWindowSystemUiVisiblityChanged.setAccessible(true);

			} else if (m.getName().equals("dispatchWindowVisibilityChanged")) {
				method_ViewGroup_dispatchWindowVisibilityChanged = m;
				method_ViewGroup_dispatchWindowVisibilityChanged.setAccessible(true);

			} else if (m.getName().equals("drawChild")) {
				method_ViewGroup_drawChild = m;
				method_ViewGroup_drawChild.setAccessible(true);

			} else if (m.getName().equals("drawableStateChanged")) {
				method_ViewGroup_drawableStateChanged = m;
				method_ViewGroup_drawableStateChanged.setAccessible(true);

			} else if (m.getName().equals("endViewTransition")) {
				method_ViewGroup_endViewTransition = m;
				method_ViewGroup_endViewTransition.setAccessible(true);

			} else if (m.getName().equals("findFocus")) {
				method_ViewGroup_findFocus = m;
				method_ViewGroup_findFocus.setAccessible(true);

			} else if (m.getName().equals("findFrontmostDroppableChildAt")) {
				method_ViewGroup_findFrontmostDroppableChildAt = m;
				method_ViewGroup_findFrontmostDroppableChildAt.setAccessible(true);

			} else if (m.getName().equals("findViewByAccessibilityIdTraversal")) {
				method_ViewGroup_findViewByAccessibilityIdTraversal = m;
				method_ViewGroup_findViewByAccessibilityIdTraversal.setAccessible(true);

			} else if (m.getName().equals("findViewByPredicateTraversal")) {
				method_ViewGroup_findViewByPredicateTraversal = m;
				method_ViewGroup_findViewByPredicateTraversal.setAccessible(true);

			} else if (m.getName().equals("findViewTraversal")) {
				method_ViewGroup_findViewTraversal = m;
				method_ViewGroup_findViewTraversal.setAccessible(true);

			} else if (m.getName().equals("findViewWithTagTraversal")) {
				method_ViewGroup_findViewWithTagTraversal = m;
				method_ViewGroup_findViewWithTagTraversal.setAccessible(true);

			} else if (m.getName().equals("findViewsWithText")) {
				method_ViewGroup_findViewsWithText = m;
				method_ViewGroup_findViewsWithText.setAccessible(true);

			} else if (m.getName().equals("finishAnimatingView")) {
				method_ViewGroup_finishAnimatingView = m;
				method_ViewGroup_finishAnimatingView.setAccessible(true);

			} else if (m.getName().equals("fitSystemWindows")) {
				method_ViewGroup_fitSystemWindows = m;
				method_ViewGroup_fitSystemWindows.setAccessible(true);

			} else if (m.getName().equals("focusSearch")) {
				method_ViewGroup_focusSearch = m;
				method_ViewGroup_focusSearch.setAccessible(true);

			} else if (m.getName().equals("focusableViewAvailable")) {
				method_ViewGroup_focusableViewAvailable = m;
				method_ViewGroup_focusableViewAvailable.setAccessible(true);

			} else if (m.getName().equals("gatherTransparentRegion")) {
				method_ViewGroup_gatherTransparentRegion = m;
				method_ViewGroup_gatherTransparentRegion.setAccessible(true);

			} else if (m.getName().equals("generateDefaultLayoutParams")) {
				method_ViewGroup_generateDefaultLayoutParams = m;
				method_ViewGroup_generateDefaultLayoutParams.setAccessible(true);

			} else if (m.getName().equals("generateLayoutParams")) {
				method_ViewGroup_generateLayoutParams = m;
				method_ViewGroup_generateLayoutParams.setAccessible(true);

			} else if (m.getName().equals("getChildAt")) {
				method_ViewGroup_getChildAt = m;
				method_ViewGroup_getChildAt.setAccessible(true);

			} else if (m.getName().equals("getChildCount")) {
				method_ViewGroup_getChildCount = m;
				method_ViewGroup_getChildCount.setAccessible(true);

			} else if (m.getName().equals("getChildDrawingOrder")) {
				method_ViewGroup_getChildDrawingOrder = m;
				method_ViewGroup_getChildDrawingOrder.setAccessible(true);

			} else if (m.getName().equals("getChildStaticTransformation")) {
				method_ViewGroup_getChildStaticTransformation = m;
				method_ViewGroup_getChildStaticTransformation.setAccessible(true);

			} else if (m.getName().equals("getChildTransformation")) {
				method_ViewGroup_getChildTransformation = m;
				method_ViewGroup_getChildTransformation.setAccessible(true);

			} else if (m.getName().equals("getChildVisibleRect")) {
				method_ViewGroup_getChildVisibleRect = m;
				method_ViewGroup_getChildVisibleRect.setAccessible(true);

			} else if (m.getName().equals("getClipChildren")) {
				method_ViewGroup_getClipChildren = m;
				method_ViewGroup_getClipChildren.setAccessible(true);

			} else if (m.getName().equals("getDescendantFocusability")) {
				method_ViewGroup_getDescendantFocusability = m;
				method_ViewGroup_getDescendantFocusability.setAccessible(true);

			} else if (m.getName().equals("getFocusedChild")) {
				method_ViewGroup_getFocusedChild = m;
				method_ViewGroup_getFocusedChild.setAccessible(true);

			} else if (m.getName().equals("getLayoutAnimation")) {
				method_ViewGroup_getLayoutAnimation = m;
				method_ViewGroup_getLayoutAnimation.setAccessible(true);

			} else if (m.getName().equals("getLayoutAnimationListener")) {
				method_ViewGroup_getLayoutAnimationListener = m;
				method_ViewGroup_getLayoutAnimationListener.setAccessible(true);

			} else if (m.getName().equals("getLayoutMode")) {
				method_ViewGroup_getLayoutMode = m;
				method_ViewGroup_getLayoutMode.setAccessible(true);

			} else if (m.getName().equals("getLayoutTransition")) {
				method_ViewGroup_getLayoutTransition = m;
				method_ViewGroup_getLayoutTransition.setAccessible(true);

			} else if (m.getName().equals("getOverlay")) {
				method_ViewGroup_getOverlay = m;
				method_ViewGroup_getOverlay.setAccessible(true);

			} else if (m.getName().equals("getPersistentDrawingCache")) {
				method_ViewGroup_getPersistentDrawingCache = m;
				method_ViewGroup_getPersistentDrawingCache.setAccessible(true);

			} else if (m.getName().equals("handleFocusGainInternal")) {
				method_ViewGroup_handleFocusGainInternal = m;
				method_ViewGroup_handleFocusGainInternal.setAccessible(true);

			} else if (m.getName().equals("hasFocus")) {
				method_ViewGroup_hasFocus = m;
				method_ViewGroup_hasFocus.setAccessible(true);

			} else if (m.getName().equals("hasFocusable")) {
				method_ViewGroup_hasFocusable = m;
				method_ViewGroup_hasFocusable.setAccessible(true);

			} else if (m.getName().equals("hasHoveredChild")) {
				method_ViewGroup_hasHoveredChild = m;
				method_ViewGroup_hasHoveredChild.setAccessible(true);

			} else if (m.getName().equals("hasTransientState")) {
				method_ViewGroup_hasTransientState = m;
				method_ViewGroup_hasTransientState.setAccessible(true);

			} else if (m.getName().equals("indexOfChild")) {
				method_ViewGroup_indexOfChild = m;
				method_ViewGroup_indexOfChild.setAccessible(true);

			} else if (m.getName().equals("internalSetPadding")) {
				method_ViewGroup_internalSetPadding = m;
				method_ViewGroup_internalSetPadding.setAccessible(true);

			} else if (m.getName().equals("invalidateChildFast")) {
				method_ViewGroup_invalidateChildFast = m;
				method_ViewGroup_invalidateChildFast.setAccessible(true);

			} else if (m.getName().equals("invalidateChildInParent")) {
				method_ViewGroup_invalidateChildInParent = m;
				method_ViewGroup_invalidateChildInParent.setAccessible(true);

			} else if (m.getName().equals("invalidateChildInParentFast")) {
				method_ViewGroup_invalidateChildInParentFast = m;
				method_ViewGroup_invalidateChildInParentFast.setAccessible(true);

			} else if (m.getName().equals("invalidateInheritedLayoutMode")) {
				method_ViewGroup_invalidateInheritedLayoutMode = m;
				method_ViewGroup_invalidateInheritedLayoutMode.setAccessible(true);

			} else if (m.getName().equals("isAlwaysDrawnWithCacheEnabled")) {
				method_ViewGroup_isAlwaysDrawnWithCacheEnabled = m;
				method_ViewGroup_isAlwaysDrawnWithCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("isAnimationCacheEnabled")) {
				method_ViewGroup_isAnimationCacheEnabled = m;
				method_ViewGroup_isAnimationCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("isChildrenDrawingOrderEnabled")) {
				method_ViewGroup_isChildrenDrawingOrderEnabled = m;
				method_ViewGroup_isChildrenDrawingOrderEnabled.setAccessible(true);

			} else if (m.getName().equals("isChildrenDrawnWithCacheEnabled")) {
				method_ViewGroup_isChildrenDrawnWithCacheEnabled = m;
				method_ViewGroup_isChildrenDrawnWithCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("isLayoutModeOptical")) {
				method_ViewGroup_isLayoutModeOptical = m;
				method_ViewGroup_isLayoutModeOptical.setAccessible(true);

			} else if (m.getName().equals("isLayoutSuppressed")) {
				method_ViewGroup_isLayoutSuppressed = m;
				method_ViewGroup_isLayoutSuppressed.setAccessible(true);

			} else if (m.getName().equals("isMotionEventSplittingEnabled")) {
				method_ViewGroup_isMotionEventSplittingEnabled = m;
				method_ViewGroup_isMotionEventSplittingEnabled.setAccessible(true);

			} else if (m.getName().equals("isTransformedTouchPointInView")) {
				method_ViewGroup_isTransformedTouchPointInView = m;
				method_ViewGroup_isTransformedTouchPointInView.setAccessible(true);

			} else if (m.getName().equals("isViewTransitioning")) {
				method_ViewGroup_isViewTransitioning = m;
				method_ViewGroup_isViewTransitioning.setAccessible(true);

			} else if (m.getName().equals("jumpDrawablesToCurrentState")) {
				method_ViewGroup_jumpDrawablesToCurrentState = m;
				method_ViewGroup_jumpDrawablesToCurrentState.setAccessible(true);

			} else if (m.getName().equals("makeOptionalFitsSystemWindows")) {
				method_ViewGroup_makeOptionalFitsSystemWindows = m;
				method_ViewGroup_makeOptionalFitsSystemWindows.setAccessible(true);

			} else if (m.getName().equals("measureChild")) {
				method_ViewGroup_measureChild = m;
				method_ViewGroup_measureChild.setAccessible(true);

			} else if (m.getName().equals("measureChildWithMargins")) {
				method_ViewGroup_measureChildWithMargins = m;
				method_ViewGroup_measureChildWithMargins.setAccessible(true);

			} else if (m.getName().equals("measureChildren")) {
				method_ViewGroup_measureChildren = m;
				method_ViewGroup_measureChildren.setAccessible(true);

			} else if (m.getName().equals("notifyChildOfDrag")) {
				method_ViewGroup_notifyChildOfDrag = m;
				method_ViewGroup_notifyChildOfDrag.setAccessible(true);

			} else if (m.getName().equals("notifySubtreeAccessibilityStateChanged")) {
				method_ViewGroup_notifySubtreeAccessibilityStateChanged = m;
				method_ViewGroup_notifySubtreeAccessibilityStateChanged.setAccessible(true);

			} else if (m.getName().equals("offsetChildrenTopAndBottom")) {
				method_ViewGroup_offsetChildrenTopAndBottom = m;
				method_ViewGroup_offsetChildrenTopAndBottom.setAccessible(true);

			} else if (m.getName().equals("offsetRectBetweenParentAndChild")) {
				method_ViewGroup_offsetRectBetweenParentAndChild = m;
				method_ViewGroup_offsetRectBetweenParentAndChild.setAccessible(true);

			} else if (m.getName().equals("onAnimationEnd")) {
				method_ViewGroup_onAnimationEnd = m;
				method_ViewGroup_onAnimationEnd.setAccessible(true);

			} else if (m.getName().equals("onAnimationStart")) {
				method_ViewGroup_onAnimationStart = m;
				method_ViewGroup_onAnimationStart.setAccessible(true);

			} else if (m.getName().equals("onAttachedToWindow")) {
				method_ViewGroup_onAttachedToWindow = m;
				method_ViewGroup_onAttachedToWindow.setAccessible(true);

			} else if (m.getName().equals("onChildVisibilityChanged")) {
				method_ViewGroup_onChildVisibilityChanged = m;
				method_ViewGroup_onChildVisibilityChanged.setAccessible(true);

			} else if (m.getName().equals("onCreateDrawableState")) {
				method_ViewGroup_onCreateDrawableState = m;
				method_ViewGroup_onCreateDrawableState.setAccessible(true);

			} else if (m.getName().equals("onDebugDraw")) {
				method_ViewGroup_onDebugDraw = m;
				method_ViewGroup_onDebugDraw.setAccessible(true);

			} else if (m.getName().equals("onDebugDrawMargins")) {
				method_ViewGroup_onDebugDrawMargins = m;
				method_ViewGroup_onDebugDrawMargins.setAccessible(true);

			} else if (m.getName().equals("onDetachedFromWindow")) {
				method_ViewGroup_onDetachedFromWindow = m;
				method_ViewGroup_onDetachedFromWindow.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityEventInternal")) {
				method_ViewGroup_onInitializeAccessibilityEventInternal = m;
				method_ViewGroup_onInitializeAccessibilityEventInternal.setAccessible(true);

			} else if (m.getName().equals("onInitializeAccessibilityNodeInfoInternal")) {
				method_ViewGroup_onInitializeAccessibilityNodeInfoInternal = m;
				method_ViewGroup_onInitializeAccessibilityNodeInfoInternal.setAccessible(true);

			} else if (m.getName().equals("onInterceptHoverEvent")) {
				method_ViewGroup_onInterceptHoverEvent = m;
				method_ViewGroup_onInterceptHoverEvent.setAccessible(true);

			} else if (m.getName().equals("onInterceptTouchEvent")) {
				method_ViewGroup_onInterceptTouchEvent = m;
				method_ViewGroup_onInterceptTouchEvent.setAccessible(true);

			} else if (m.getName().equals("onRequestFocusInDescendants")) {
				method_ViewGroup_onRequestFocusInDescendants = m;
				method_ViewGroup_onRequestFocusInDescendants.setAccessible(true);

			} else if (m.getName().equals("onRequestSendAccessibilityEvent")) {
				method_ViewGroup_onRequestSendAccessibilityEvent = m;
				method_ViewGroup_onRequestSendAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("onRequestSendAccessibilityEventInternal")) {
				method_ViewGroup_onRequestSendAccessibilityEventInternal = m;
				method_ViewGroup_onRequestSendAccessibilityEventInternal.setAccessible(true);

			} else if (m.getName().equals("onSetLayoutParams")) {
				method_ViewGroup_onSetLayoutParams = m;
				method_ViewGroup_onSetLayoutParams.setAccessible(true);

			} else if (m.getName().equals("onViewAdded")) {
				method_ViewGroup_onViewAdded = m;
				method_ViewGroup_onViewAdded.setAccessible(true);

			} else if (m.getName().equals("onViewRemoved")) {
				method_ViewGroup_onViewRemoved = m;
				method_ViewGroup_onViewRemoved.setAccessible(true);

			} else if (m.getName().equals("recomputeViewAttributes")) {
				method_ViewGroup_recomputeViewAttributes = m;
				method_ViewGroup_recomputeViewAttributes.setAccessible(true);

			} else if (m.getName().equals("removeAllViews")) {
				method_ViewGroup_removeAllViews = m;
				method_ViewGroup_removeAllViews.setAccessible(true);

			} else if (m.getName().equals("removeAllViewsInLayout")) {
				method_ViewGroup_removeAllViewsInLayout = m;
				method_ViewGroup_removeAllViewsInLayout.setAccessible(true);

			} else if (m.getName().equals("removeDetachedView")) {
				method_ViewGroup_removeDetachedView = m;
				method_ViewGroup_removeDetachedView.setAccessible(true);

			} else if (m.getName().equals("removeView")) {
				method_ViewGroup_removeView = m;
				method_ViewGroup_removeView.setAccessible(true);

			} else if (m.getName().equals("removeViewAt")) {
				method_ViewGroup_removeViewAt = m;
				method_ViewGroup_removeViewAt.setAccessible(true);

			} else if (m.getName().equals("removeViewInLayout")) {
				method_ViewGroup_removeViewInLayout = m;
				method_ViewGroup_removeViewInLayout.setAccessible(true);

			} else if (m.getName().equals("removeViews")) {
				method_ViewGroup_removeViews = m;
				method_ViewGroup_removeViews.setAccessible(true);

			} else if (m.getName().equals("removeViewsInLayout")) {
				method_ViewGroup_removeViewsInLayout = m;
				method_ViewGroup_removeViewsInLayout.setAccessible(true);

			} else if (m.getName().equals("requestChildFocus")) {
				method_ViewGroup_requestChildFocus = m;
				method_ViewGroup_requestChildFocus.setAccessible(true);

			} else if (m.getName().equals("requestChildRectangleOnScreen")) {
				method_ViewGroup_requestChildRectangleOnScreen = m;
				method_ViewGroup_requestChildRectangleOnScreen.setAccessible(true);

			} else if (m.getName().equals("requestDisallowInterceptTouchEvent")) {
				method_ViewGroup_requestDisallowInterceptTouchEvent = m;
				method_ViewGroup_requestDisallowInterceptTouchEvent.setAccessible(true);

			} else if (m.getName().equals("requestFocus")) {
				method_ViewGroup_requestFocus = m;
				method_ViewGroup_requestFocus.setAccessible(true);

			} else if (m.getName().equals("requestSendAccessibilityEvent")) {
				method_ViewGroup_requestSendAccessibilityEvent = m;
				method_ViewGroup_requestSendAccessibilityEvent.setAccessible(true);

			} else if (m.getName().equals("requestTransitionStart")) {
				method_ViewGroup_requestTransitionStart = m;
				method_ViewGroup_requestTransitionStart.setAccessible(true);

			} else if (m.getName().equals("requestTransparentRegion")) {
				method_ViewGroup_requestTransparentRegion = m;
				method_ViewGroup_requestTransparentRegion.setAccessible(true);

			} else if (m.getName().equals("resetResolvedDrawables")) {
				method_ViewGroup_resetResolvedDrawables = m;
				method_ViewGroup_resetResolvedDrawables.setAccessible(true);

			} else if (m.getName().equals("resetResolvedLayoutDirection")) {
				method_ViewGroup_resetResolvedLayoutDirection = m;
				method_ViewGroup_resetResolvedLayoutDirection.setAccessible(true);

			} else if (m.getName().equals("resetResolvedPadding")) {
				method_ViewGroup_resetResolvedPadding = m;
				method_ViewGroup_resetResolvedPadding.setAccessible(true);

			} else if (m.getName().equals("resetResolvedTextAlignment")) {
				method_ViewGroup_resetResolvedTextAlignment = m;
				method_ViewGroup_resetResolvedTextAlignment.setAccessible(true);

			} else if (m.getName().equals("resetResolvedTextDirection")) {
				method_ViewGroup_resetResolvedTextDirection = m;
				method_ViewGroup_resetResolvedTextDirection.setAccessible(true);

			} else if (m.getName().equals("resetSubtreeAccessibilityStateChanged")) {
				method_ViewGroup_resetSubtreeAccessibilityStateChanged = m;
				method_ViewGroup_resetSubtreeAccessibilityStateChanged.setAccessible(true);

			} else if (m.getName().equals("resolveDrawables")) {
				method_ViewGroup_resolveDrawables = m;
				method_ViewGroup_resolveDrawables.setAccessible(true);

			} else if (m.getName().equals("resolveLayoutDirection")) {
				method_ViewGroup_resolveLayoutDirection = m;
				method_ViewGroup_resolveLayoutDirection.setAccessible(true);

			} else if (m.getName().equals("resolveLayoutParams")) {
				method_ViewGroup_resolveLayoutParams = m;
				method_ViewGroup_resolveLayoutParams.setAccessible(true);

			} else if (m.getName().equals("resolvePadding")) {
				method_ViewGroup_resolvePadding = m;
				method_ViewGroup_resolvePadding.setAccessible(true);

			} else if (m.getName().equals("resolveRtlPropertiesIfNeeded")) {
				method_ViewGroup_resolveRtlPropertiesIfNeeded = m;
				method_ViewGroup_resolveRtlPropertiesIfNeeded.setAccessible(true);

			} else if (m.getName().equals("resolveTextAlignment")) {
				method_ViewGroup_resolveTextAlignment = m;
				method_ViewGroup_resolveTextAlignment.setAccessible(true);

			} else if (m.getName().equals("resolveTextDirection")) {
				method_ViewGroup_resolveTextDirection = m;
				method_ViewGroup_resolveTextDirection.setAccessible(true);

			} else if (m.getName().equals("scheduleLayoutAnimation")) {
				method_ViewGroup_scheduleLayoutAnimation = m;
				method_ViewGroup_scheduleLayoutAnimation.setAccessible(true);

			} else if (m.getName().equals("setAddStatesFromChildren")) {
				method_ViewGroup_setAddStatesFromChildren = m;
				method_ViewGroup_setAddStatesFromChildren.setAccessible(true);

			} else if (m.getName().equals("setAlwaysDrawnWithCacheEnabled")) {
				method_ViewGroup_setAlwaysDrawnWithCacheEnabled = m;
				method_ViewGroup_setAlwaysDrawnWithCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("setAnimationCacheEnabled")) {
				method_ViewGroup_setAnimationCacheEnabled = m;
				method_ViewGroup_setAnimationCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("setChildrenDrawingCacheEnabled")) {
				method_ViewGroup_setChildrenDrawingCacheEnabled = m;
				method_ViewGroup_setChildrenDrawingCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("setChildrenDrawingOrderEnabled")) {
				method_ViewGroup_setChildrenDrawingOrderEnabled = m;
				method_ViewGroup_setChildrenDrawingOrderEnabled.setAccessible(true);

			} else if (m.getName().equals("setChildrenDrawnWithCacheEnabled")) {
				method_ViewGroup_setChildrenDrawnWithCacheEnabled = m;
				method_ViewGroup_setChildrenDrawnWithCacheEnabled.setAccessible(true);

			} else if (m.getName().equals("setClipChildren")) {
				method_ViewGroup_setClipChildren = m;
				method_ViewGroup_setClipChildren.setAccessible(true);

			} else if (m.getName().equals("setClipToPadding")) {
				method_ViewGroup_setClipToPadding = m;
				method_ViewGroup_setClipToPadding.setAccessible(true);

			} else if (m.getName().equals("setDescendantFocusability")) {
				method_ViewGroup_setDescendantFocusability = m;
				method_ViewGroup_setDescendantFocusability.setAccessible(true);

			} else if (m.getName().equals("setLayoutAnimation")) {
				method_ViewGroup_setLayoutAnimation = m;
				method_ViewGroup_setLayoutAnimation.setAccessible(true);

			} else if (m.getName().equals("setLayoutAnimationListener")) {
				method_ViewGroup_setLayoutAnimationListener = m;
				method_ViewGroup_setLayoutAnimationListener.setAccessible(true);

			} else if (m.getName().equals("setLayoutMode")) {
				method_ViewGroup_setLayoutMode = m;
				method_ViewGroup_setLayoutMode.setAccessible(true);

			} else if (m.getName().equals("setLayoutTransition")) {
				method_ViewGroup_setLayoutTransition = m;
				method_ViewGroup_setLayoutTransition.setAccessible(true);

			} else if (m.getName().equals("setMotionEventSplittingEnabled")) {
				method_ViewGroup_setMotionEventSplittingEnabled = m;
				method_ViewGroup_setMotionEventSplittingEnabled.setAccessible(true);

			} else if (m.getName().equals("setOnHierarchyChangeListener")) {
				method_ViewGroup_setOnHierarchyChangeListener = m;
				method_ViewGroup_setOnHierarchyChangeListener.setAccessible(true);

			} else if (m.getName().equals("setPersistentDrawingCache")) {
				method_ViewGroup_setPersistentDrawingCache = m;
				method_ViewGroup_setPersistentDrawingCache.setAccessible(true);

			} else if (m.getName().equals("setStaticTransformationsEnabled")) {
				method_ViewGroup_setStaticTransformationsEnabled = m;
				method_ViewGroup_setStaticTransformationsEnabled.setAccessible(true);

			} else if (m.getName().equals("shouldDelayChildPressedState")) {
				method_ViewGroup_shouldDelayChildPressedState = m;
				method_ViewGroup_shouldDelayChildPressedState.setAccessible(true);

			} else if (m.getName().equals("showContextMenuForChild")) {
				method_ViewGroup_showContextMenuForChild = m;
				method_ViewGroup_showContextMenuForChild.setAccessible(true);

			} else if (m.getName().equals("startActionModeForChild")) {
				method_ViewGroup_startActionModeForChild = m;
				method_ViewGroup_startActionModeForChild.setAccessible(true);

			} else if (m.getName().equals("startLayoutAnimation")) {
				method_ViewGroup_startLayoutAnimation = m;
				method_ViewGroup_startLayoutAnimation.setAccessible(true);

			} else if (m.getName().equals("startViewTransition")) {
				method_ViewGroup_startViewTransition = m;
				method_ViewGroup_startViewTransition.setAccessible(true);

			} else if (m.getName().equals("suppressLayout")) {
				method_ViewGroup_suppressLayout = m;
				method_ViewGroup_suppressLayout.setAccessible(true);

			} else if (m.getName().equals("unFocus")) {
				method_ViewGroup_unFocus = m;
				method_ViewGroup_unFocus.setAccessible(true);

			} else if (m.getName().equals("updateLocalSystemUiVisibility")) {
				method_ViewGroup_updateLocalSystemUiVisibility = m;
				method_ViewGroup_updateLocalSystemUiVisibility.setAccessible(true);

			} else if (m.getName().equals("updateViewLayout")) {
				method_ViewGroup_updateViewLayout = m;
				method_ViewGroup_updateViewLayout.setAccessible(true);

			} else if (m.getName().equals("access$000")) {
				method_ViewGroup_access$000 = m;
				method_ViewGroup_access$000.setAccessible(true);

			} else if (m.getName().equals("access$100")) {
				method_ViewGroup_access$100 = m;
				method_ViewGroup_access$100.setAccessible(true);

			} else if (m.getName().equals("access$200")) {
				method_ViewGroup_access$200 = m;
				method_ViewGroup_access$200.setAccessible(true);

			} else if (m.getName().equals("access$300")) {
				method_ViewGroup_access$300 = m;
				method_ViewGroup_access$300.setAccessible(true);

			} else if (m.getName().equals("access$302")) {
				method_ViewGroup_access$302 = m;
				method_ViewGroup_access$302.setAccessible(true);

			} else if (m.getName().equals("access$400")) {
				method_ViewGroup_access$400 = m;
				method_ViewGroup_access$400.setAccessible(true);

			} else if (m.getName().equals("access$500")) {
				method_ViewGroup_access$500 = m;
				method_ViewGroup_access$500.setAccessible(true);

			} else if (m.getName().equals("addDisappearingView")) {
				method_ViewGroup_addDisappearingView = m;
				method_ViewGroup_addDisappearingView.setAccessible(true);

			} else if (m.getName().equals("addInArray")) {
				method_ViewGroup_addInArray = m;
				method_ViewGroup_addInArray.setAccessible(true);

			} else if (m.getName().equals("addTouchTarget")) {
				method_ViewGroup_addTouchTarget = m;
				method_ViewGroup_addTouchTarget.setAccessible(true);

			} else if (m.getName().equals("addViewInner")) {
				method_ViewGroup_addViewInner = m;
				method_ViewGroup_addViewInner.setAccessible(true);

			} else if (m.getName().equals("bindLayoutAnimation")) {
				method_ViewGroup_bindLayoutAnimation = m;
				method_ViewGroup_bindLayoutAnimation.setAccessible(true);

			} else if (m.getName().equals("canViewReceivePointerEvents")) {
				method_ViewGroup_canViewReceivePointerEvents = m;
				method_ViewGroup_canViewReceivePointerEvents.setAccessible(true);

			} else if (m.getName().equals("cancelAndClearTouchTargets")) {
				method_ViewGroup_cancelAndClearTouchTargets = m;
				method_ViewGroup_cancelAndClearTouchTargets.setAccessible(true);

			} else if (m.getName().equals("cancelHoverTarget")) {
				method_ViewGroup_cancelHoverTarget = m;
				method_ViewGroup_cancelHoverTarget.setAccessible(true);

			} else if (m.getName().equals("cancelTouchTarget")) {
				method_ViewGroup_cancelTouchTarget = m;
				method_ViewGroup_cancelTouchTarget.setAccessible(true);

			} else if (m.getName().equals("clearCachedLayoutMode")) {
				method_ViewGroup_clearCachedLayoutMode = m;
				method_ViewGroup_clearCachedLayoutMode.setAccessible(true);

			} else if (m.getName().equals("clearTouchTargets")) {
				method_ViewGroup_clearTouchTargets = m;
				method_ViewGroup_clearTouchTargets.setAccessible(true);

			} else if (m.getName().equals("debugDraw")) {
				method_ViewGroup_debugDraw = m;
				method_ViewGroup_debugDraw.setAccessible(true);

			} else if (m.getName().equals("dipsToPixels")) {
				method_ViewGroup_dipsToPixels = m;
				method_ViewGroup_dipsToPixels.setAccessible(true);

			} else if (m.getName().equals("dispatchTransformedGenericPointerEvent")) {
				method_ViewGroup_dispatchTransformedGenericPointerEvent = m;
				method_ViewGroup_dispatchTransformedGenericPointerEvent.setAccessible(true);

			} else if (m.getName().equals("dispatchTransformedTouchEvent")) {
				method_ViewGroup_dispatchTransformedTouchEvent = m;
				method_ViewGroup_dispatchTransformedTouchEvent.setAccessible(true);

			} else if (m.getName().equals("drawCorner")) {
				method_ViewGroup_drawCorner = m;
				method_ViewGroup_drawCorner.setAccessible(true);

			} else if (m.getName().equals("drawRect")) {
				method_ViewGroup_drawRect = m;
				method_ViewGroup_drawRect.setAccessible(true);

			} else if (m.getName().equals("drawRectCorners")) {
				method_ViewGroup_drawRectCorners = m;
				method_ViewGroup_drawRectCorners.setAccessible(true);

			} else if (m.getName().equals("exitHoverTargets")) {
				method_ViewGroup_exitHoverTargets = m;
				method_ViewGroup_exitHoverTargets.setAccessible(true);

			} else if (m.getName().equals("fillDifference")) {
				method_ViewGroup_fillDifference = m;
				method_ViewGroup_fillDifference.setAccessible(true);

			} else if (m.getName().equals("fillRect")) {
				method_ViewGroup_fillRect = m;
				method_ViewGroup_fillRect.setAccessible(true);

			} else if (m.getName().equals("getChildMeasureSpec")) {
				method_ViewGroup_getChildMeasureSpec = m;
				method_ViewGroup_getChildMeasureSpec.setAccessible(true);

			} else if (m.getName().equals("getDebugPaint")) {
				method_ViewGroup_getDebugPaint = m;
				method_ViewGroup_getDebugPaint.setAccessible(true);

			} else if (m.getName().equals("getLocalPoint")) {
				method_ViewGroup_getLocalPoint = m;
				method_ViewGroup_getLocalPoint.setAccessible(true);

			} else if (m.getName().equals("getTouchTarget")) {
				method_ViewGroup_getTouchTarget = m;
				method_ViewGroup_getTouchTarget.setAccessible(true);

			} else if (m.getName().equals("hasBooleanFlag")) {
				method_ViewGroup_hasBooleanFlag = m;
				method_ViewGroup_hasBooleanFlag.setAccessible(true);

			} else if (m.getName().equals("initFromAttributes")) {
				method_ViewGroup_initFromAttributes = m;
				method_ViewGroup_initFromAttributes.setAccessible(true);

			} else if (m.getName().equals("initViewGroup")) {
				method_ViewGroup_initViewGroup = m;
				method_ViewGroup_initViewGroup.setAccessible(true);

			} else if (m.getName().equals("notifyAnimationListener")) {
				method_ViewGroup_notifyAnimationListener = m;
				method_ViewGroup_notifyAnimationListener.setAccessible(true);

			} else if (m.getName().equals("obtainMotionEventNoHistoryOrSelf")) {
				method_ViewGroup_obtainMotionEventNoHistoryOrSelf = m;
				method_ViewGroup_obtainMotionEventNoHistoryOrSelf.setAccessible(true);

			} else if (m.getName().equals("removeFromArray")) {
				method_ViewGroup_removeFromArray = m;
				method_ViewGroup_removeFromArray.setAccessible(true);

			} else if (m.getName().equals("removePointersFromTouchTargets")) {
				method_ViewGroup_removePointersFromTouchTargets = m;
				method_ViewGroup_removePointersFromTouchTargets.setAccessible(true);

			} else if (m.getName().equals("removeViewInternal")) {
				method_ViewGroup_removeViewInternal = m;
				method_ViewGroup_removeViewInternal.setAccessible(true);

			} else if (m.getName().equals("removeViewsInternal")) {
				method_ViewGroup_removeViewsInternal = m;
				method_ViewGroup_removeViewsInternal.setAccessible(true);

			} else if (m.getName().equals("resetCancelNextUpFlag")) {
				method_ViewGroup_resetCancelNextUpFlag = m;
				method_ViewGroup_resetCancelNextUpFlag.setAccessible(true);

			} else if (m.getName().equals("resetTouchState")) {
				method_ViewGroup_resetTouchState = m;
				method_ViewGroup_resetTouchState.setAccessible(true);

			} else if (m.getName().equals("setBooleanFlag")) {
				method_ViewGroup_setBooleanFlag = m;
				method_ViewGroup_setBooleanFlag.setAccessible(true);

			} else if (m.getName().equals("sign")) {
				method_ViewGroup_sign = m;
				method_ViewGroup_sign.setAccessible(true);
			} else if (m.getName().equals("isLayoutRtl")) {
				method_ViewGroup_isLayoutRtl = m;
				method_ViewGroup_isLayoutRtl.setAccessible(true);

			}
		}
	}

	public static void methodInvoke_ViewGroup_addChildrenForAccessibility(Object obj) {
		try {
			method_ViewGroup_addChildrenForAccessibility.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_addFocusables(Object obj) {
		try {
			method_ViewGroup_addFocusables.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_addStatesFromChildren(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_addStatesFromChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_addTouchables(Object obj) {
		try {
			method_ViewGroup_addTouchables.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_addView(Object obj) {
		try {
			method_ViewGroup_addView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_addViewInLayout(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_addViewInLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_attachLayoutAnimationParameters(Object obj) {
		try {
			method_ViewGroup_attachLayoutAnimationParameters.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_attachViewToParent(Object obj) {
		try {
			method_ViewGroup_attachViewToParent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_bringChildToFront(Object obj) {
		try {
			method_ViewGroup_bringChildToFront.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_canAnimate(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_canAnimate.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_checkLayoutParams(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_checkLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_childDrawableStateChanged(Object obj) {
		try {
			method_ViewGroup_childDrawableStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_childHasTransientStateChanged(Object obj) {
		try {
			method_ViewGroup_childHasTransientStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_cleanupLayoutState(Object obj) {
		try {
			method_ViewGroup_cleanupLayoutState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_clearChildFocus(Object obj) {
		try {
			method_ViewGroup_clearChildFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_clearDisappearingChildren(Object obj) {
		try {
			method_ViewGroup_clearDisappearingChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_clearFocus(Object obj) {
		try {
			method_ViewGroup_clearFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Insets methodInvoke_ViewGroup_computeOpticalInsets(Object obj) {
		Insets o = null;
		try {
			o = (Insets) method_ViewGroup_computeOpticalInsets.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Bitmap methodInvoke_ViewGroup_createSnapshot(Object obj) {
		Bitmap o = null;
		try {
			o = (Bitmap) method_ViewGroup_createSnapshot.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_debug(Object obj) {
		try {
			method_ViewGroup_debug.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_detachAllViewsFromParent(Object obj) {
		try {
			method_ViewGroup_detachAllViewsFromParent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_detachViewFromParent(Object obj) {
		try {
			method_ViewGroup_detachViewFromParent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_detachViewsFromParent(Object obj) {
		try {
			method_ViewGroup_detachViewsFromParent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchAttachedToWindow(Object obj) {
		try {
			method_ViewGroup_dispatchAttachedToWindow.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchCancelPendingInputEvents(Object obj) {
		try {
			method_ViewGroup_dispatchCancelPendingInputEvents.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchCollectViewAttributes(Object obj) {
		try {
			method_ViewGroup_dispatchCollectViewAttributes.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchConfigurationChanged(Object obj) {
		try {
			method_ViewGroup_dispatchConfigurationChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchDetachedFromWindow(Object obj) {
		try {
			method_ViewGroup_dispatchDetachedFromWindow.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchDisplayHint(Object obj) {
		try {
			method_ViewGroup_dispatchDisplayHint.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_dispatchDragEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchDragEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_dispatchDraw(Object obj) {
		try {
			method_ViewGroup_dispatchDraw.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchFinishTemporaryDetach(Object obj) {
		try {
			method_ViewGroup_dispatchFinishTemporaryDetach.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchFreezeSelfOnly(Object obj) {
		try {
			method_ViewGroup_dispatchFreezeSelfOnly.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_dispatchGenericFocusedEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchGenericFocusedEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchGenericPointerEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchGenericPointerEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_dispatchGetDisplayList(Object obj) {
		try {
			method_ViewGroup_dispatchGetDisplayList.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_dispatchHoverEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchHoverEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchKeyEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchKeyEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchKeyEventPreIme(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchKeyEventPreIme.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchKeyShortcutEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchKeyShortcutEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchPopulateAccessibilityEventInternal(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchPopulateAccessibilityEventInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_dispatchRestoreInstanceState(Object obj) {
		try {
			method_ViewGroup_dispatchRestoreInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchSaveInstanceState(Object obj) {
		try {
			method_ViewGroup_dispatchSaveInstanceState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchScreenStateChanged(Object obj) {
		try {
			method_ViewGroup_dispatchScreenStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchSetActivated(Object obj) {
		try {
			method_ViewGroup_dispatchSetActivated.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchSetPressed(Object obj) {
		try {
			method_ViewGroup_dispatchSetPressed.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchSetSelected(Object obj) {
		try {
			method_ViewGroup_dispatchSetSelected.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchStartTemporaryDetach(Object obj) {
		try {
			method_ViewGroup_dispatchStartTemporaryDetach.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchSystemUiVisibilityChanged(Object obj) {
		try {
			method_ViewGroup_dispatchSystemUiVisibilityChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchThawSelfOnly(Object obj) {
		try {
			method_ViewGroup_dispatchThawSelfOnly.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_dispatchTouchEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchTouchEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchTrackballEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchTrackballEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchUnhandledMove(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchUnhandledMove.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_dispatchVisibilityChanged(Object obj) {
		try {
			method_ViewGroup_dispatchVisibilityChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchWindowFocusChanged(Object obj) {
		try {
			method_ViewGroup_dispatchWindowFocusChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchWindowSystemUiVisiblityChanged(Object obj) {
		try {
			method_ViewGroup_dispatchWindowSystemUiVisiblityChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_dispatchWindowVisibilityChanged(Object obj) {
		try {
			method_ViewGroup_dispatchWindowVisibilityChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_drawChild(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_drawChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_drawableStateChanged(Object obj) {
		try {
			method_ViewGroup_drawableStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_endViewTransition(Object obj) {
		try {
			method_ViewGroup_endViewTransition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static View methodInvoke_ViewGroup_findFocus(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_findFrontmostDroppableChildAt(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findFrontmostDroppableChildAt.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_findViewByAccessibilityIdTraversal(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findViewByAccessibilityIdTraversal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_findViewByPredicateTraversal(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findViewByPredicateTraversal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_findViewTraversal(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findViewTraversal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_findViewWithTagTraversal(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_findViewWithTagTraversal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_findViewsWithText(Object obj) {
		try {
			method_ViewGroup_findViewsWithText.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_finishAnimatingView(Object obj) {
		try {
			method_ViewGroup_finishAnimatingView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_fitSystemWindows(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_fitSystemWindows.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_focusSearch(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_focusSearch.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_focusableViewAvailable(Object obj) {
		try {
			method_ViewGroup_focusableViewAvailable.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_gatherTransparentRegion(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_gatherTransparentRegion.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static LayoutParams methodInvoke_ViewGroup_generateDefaultLayoutParams(Object obj) {
		LayoutParams o = null;
		try {
			o = (LayoutParams) method_ViewGroup_generateDefaultLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static LayoutParams methodInvoke_ViewGroup_generateLayoutParams(Object obj) {
		LayoutParams o = null;
		try {
			o = (LayoutParams) method_ViewGroup_generateLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_getChildAt(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_getChildAt.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_getChildCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getChildCount.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_getChildDrawingOrder(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getChildDrawingOrder.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_getChildStaticTransformation(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_getChildStaticTransformation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Transformation methodInvoke_ViewGroup_getChildTransformation(Object obj) {
		Transformation o = null;
		try {
			o = (Transformation) method_ViewGroup_getChildTransformation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_getChildVisibleRect(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_getChildVisibleRect.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_getClipChildren(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_getClipChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_getDescendantFocusability(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getDescendantFocusability.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static View methodInvoke_ViewGroup_getFocusedChild(Object obj) {
		View o = null;
		try {
			o = (View) method_ViewGroup_getFocusedChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static LayoutAnimationController methodInvoke_ViewGroup_getLayoutAnimation(Object obj) {
		LayoutAnimationController o = null;
		try {
			o = (LayoutAnimationController) method_ViewGroup_getLayoutAnimation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static AnimationListener methodInvoke_ViewGroup_getLayoutAnimationListener(Object obj) {
		AnimationListener o = null;
		try {
			o = (AnimationListener) method_ViewGroup_getLayoutAnimationListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_getLayoutMode(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getLayoutMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static LayoutTransition methodInvoke_ViewGroup_getLayoutTransition(Object obj) {
		LayoutTransition o = null;
		try {
			o = (LayoutTransition) method_ViewGroup_getLayoutTransition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static ViewGroupOverlay methodInvoke_ViewGroup_getOverlay(Object obj) {
		ViewGroupOverlay o = null;
		try {
			o = (ViewGroupOverlay) method_ViewGroup_getOverlay.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_getPersistentDrawingCache(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getPersistentDrawingCache.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_handleFocusGainInternal(Object obj) {
		try {
			method_ViewGroup_handleFocusGainInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_hasFocus(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_hasFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_hasFocusable(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_hasFocusable.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_hasHoveredChild(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_hasHoveredChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_hasTransientState(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_hasTransientState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_indexOfChild(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_indexOfChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_internalSetPadding(Object obj) {
		try {
			method_ViewGroup_internalSetPadding.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_invalidateChildFast(Object obj) {
		try {
			method_ViewGroup_invalidateChildFast.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ViewParent methodInvoke_ViewGroup_invalidateChildInParent(Object obj) {
		ViewParent o = null;
		try {
			o = (ViewParent) method_ViewGroup_invalidateChildInParent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static ViewParent methodInvoke_ViewGroup_invalidateChildInParentFast(Object obj) {
		ViewParent o = null;
		try {
			o = (ViewParent) method_ViewGroup_invalidateChildInParentFast.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_invalidateInheritedLayoutMode(Object obj) {
		try {
			method_ViewGroup_invalidateInheritedLayoutMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_isAlwaysDrawnWithCacheEnabled(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isAlwaysDrawnWithCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isAnimationCacheEnabled(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isAnimationCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isChildrenDrawingOrderEnabled(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isChildrenDrawingOrderEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isChildrenDrawnWithCacheEnabled(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isChildrenDrawnWithCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isLayoutModeOptical(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isLayoutModeOptical.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isLayoutSuppressed(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isLayoutSuppressed.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isMotionEventSplittingEnabled(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isMotionEventSplittingEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isTransformedTouchPointInView(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isTransformedTouchPointInView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_isViewTransitioning(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_isViewTransitioning.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_jumpDrawablesToCurrentState(Object obj) {
		try {
			method_ViewGroup_jumpDrawablesToCurrentState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_makeOptionalFitsSystemWindows(Object obj) {
		try {
			method_ViewGroup_makeOptionalFitsSystemWindows.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_measureChild(Object obj) {
		try {
			method_ViewGroup_measureChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_measureChildWithMargins(Object obj) {
		try {
			method_ViewGroup_measureChildWithMargins.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_measureChildren(Object obj) {
		try {
			method_ViewGroup_measureChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_notifyChildOfDrag(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_notifyChildOfDrag.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_notifySubtreeAccessibilityStateChanged(Object obj) {
		try {
			method_ViewGroup_notifySubtreeAccessibilityStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_offsetChildrenTopAndBottom(Object obj) {
		try {
			method_ViewGroup_offsetChildrenTopAndBottom.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_offsetRectBetweenParentAndChild(Object obj) {
		try {
			method_ViewGroup_offsetRectBetweenParentAndChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onAnimationEnd(Object obj) {
		try {
			method_ViewGroup_onAnimationEnd.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onAnimationStart(Object obj) {
		try {
			method_ViewGroup_onAnimationStart.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onAttachedToWindow(Object obj) {
		try {
			method_ViewGroup_onAttachedToWindow.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onChildVisibilityChanged(Object obj) {
		try {
			method_ViewGroup_onChildVisibilityChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int[] methodInvoke_ViewGroup_onCreateDrawableState(Object obj) {
		int[] o = null;
		try {
			o = (int[]) method_ViewGroup_onCreateDrawableState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_onDebugDraw(Object obj) {
		try {
			method_ViewGroup_onDebugDraw.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onDebugDrawMargins(Object obj) {
		try {
			method_ViewGroup_onDebugDrawMargins.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onDetachedFromWindow(Object obj) {
		try {
			method_ViewGroup_onDetachedFromWindow.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onInitializeAccessibilityEventInternal(Object obj) {
		try {
			method_ViewGroup_onInitializeAccessibilityEventInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onInitializeAccessibilityNodeInfoInternal(Object obj) {
		try {
			method_ViewGroup_onInitializeAccessibilityNodeInfoInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_onInterceptHoverEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_onInterceptHoverEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_onInterceptTouchEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_onInterceptTouchEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_onRequestFocusInDescendants(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_onRequestFocusInDescendants.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_onRequestSendAccessibilityEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_onRequestSendAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_onRequestSendAccessibilityEventInternal(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_onRequestSendAccessibilityEventInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_onSetLayoutParams(Object obj) {
		try {
			method_ViewGroup_onSetLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onViewAdded(Object obj) {
		try {
			method_ViewGroup_onViewAdded.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_onViewRemoved(Object obj) {
		try {
			method_ViewGroup_onViewRemoved.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_recomputeViewAttributes(Object obj) {
		try {
			method_ViewGroup_recomputeViewAttributes.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeAllViews(Object obj) {
		try {
			method_ViewGroup_removeAllViews.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeAllViewsInLayout(Object obj) {
		try {
			method_ViewGroup_removeAllViewsInLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeDetachedView(Object obj) {
		try {
			method_ViewGroup_removeDetachedView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeView(Object obj) {
		try {
			method_ViewGroup_removeView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViewAt(Object obj) {
		try {
			method_ViewGroup_removeViewAt.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViewInLayout(Object obj) {
		try {
			method_ViewGroup_removeViewInLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViews(Object obj) {
		try {
			method_ViewGroup_removeViews.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViewsInLayout(Object obj) {
		try {
			method_ViewGroup_removeViewsInLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_requestChildFocus(Object obj) {
		try {
			method_ViewGroup_requestChildFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_requestChildRectangleOnScreen(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_requestChildRectangleOnScreen.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_requestDisallowInterceptTouchEvent(Object obj) {
		try {
			method_ViewGroup_requestDisallowInterceptTouchEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_requestFocus(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_requestFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_requestSendAccessibilityEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_requestSendAccessibilityEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_requestTransitionStart(Object obj) {
		try {
			method_ViewGroup_requestTransitionStart.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_requestTransparentRegion(Object obj) {
		try {
			method_ViewGroup_requestTransparentRegion.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetResolvedDrawables(Object obj) {
		try {
			method_ViewGroup_resetResolvedDrawables.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetResolvedLayoutDirection(Object obj) {
		try {
			method_ViewGroup_resetResolvedLayoutDirection.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetResolvedPadding(Object obj) {
		try {
			method_ViewGroup_resetResolvedPadding.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetResolvedTextAlignment(Object obj) {
		try {
			method_ViewGroup_resetResolvedTextAlignment.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetResolvedTextDirection(Object obj) {
		try {
			method_ViewGroup_resetResolvedTextDirection.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resetSubtreeAccessibilityStateChanged(Object obj) {
		try {
			method_ViewGroup_resetSubtreeAccessibilityStateChanged.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resolveDrawables(Object obj) {
		try {
			method_ViewGroup_resolveDrawables.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_resolveLayoutDirection(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_resolveLayoutDirection.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_resolveLayoutParams(Object obj) {
		try {
			method_ViewGroup_resolveLayoutParams.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_resolvePadding(Object obj) {
		try {
			method_ViewGroup_resolvePadding.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_resolveRtlPropertiesIfNeeded(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_resolveRtlPropertiesIfNeeded.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_resolveTextAlignment(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_resolveTextAlignment.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_resolveTextDirection(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_resolveTextDirection.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_scheduleLayoutAnimation(Object obj) {
		try {
			method_ViewGroup_scheduleLayoutAnimation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setAddStatesFromChildren(Object obj) {
		try {
			method_ViewGroup_setAddStatesFromChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setAlwaysDrawnWithCacheEnabled(Object obj) {
		try {
			method_ViewGroup_setAlwaysDrawnWithCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setAnimationCacheEnabled(Object obj) {
		try {
			method_ViewGroup_setAnimationCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setChildrenDrawingCacheEnabled(Object obj) {
		try {
			method_ViewGroup_setChildrenDrawingCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setChildrenDrawingOrderEnabled(Object obj) {
		try {
			method_ViewGroup_setChildrenDrawingOrderEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setChildrenDrawnWithCacheEnabled(Object obj) {
		try {
			method_ViewGroup_setChildrenDrawnWithCacheEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setClipChildren(Object obj) {
		try {
			method_ViewGroup_setClipChildren.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setClipToPadding(Object obj) {
		try {
			method_ViewGroup_setClipToPadding.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setDescendantFocusability(Object obj) {
		try {
			method_ViewGroup_setDescendantFocusability.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setLayoutAnimation(Object obj) {
		try {
			method_ViewGroup_setLayoutAnimation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setLayoutAnimationListener(Object obj) {
		try {
			method_ViewGroup_setLayoutAnimationListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setLayoutMode(Object obj) {
		try {
			method_ViewGroup_setLayoutMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setLayoutTransition(Object obj) {
		try {
			method_ViewGroup_setLayoutTransition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setMotionEventSplittingEnabled(Object obj) {
		try {
			method_ViewGroup_setMotionEventSplittingEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setOnHierarchyChangeListener(Object obj) {
		try {
			method_ViewGroup_setOnHierarchyChangeListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setPersistentDrawingCache(Object obj) {
		try {
			method_ViewGroup_setPersistentDrawingCache.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setStaticTransformationsEnabled(Object obj) {
		try {
			method_ViewGroup_setStaticTransformationsEnabled.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_shouldDelayChildPressedState(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_shouldDelayChildPressedState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_showContextMenuForChild(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_showContextMenuForChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static ActionMode methodInvoke_ViewGroup_startActionModeForChild(Object obj) {
		ActionMode o = null;
		try {
			o = (ActionMode) method_ViewGroup_startActionModeForChild.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_startLayoutAnimation(Object obj) {
		try {
			method_ViewGroup_startLayoutAnimation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_startViewTransition(Object obj) {
		try {
			method_ViewGroup_startViewTransition.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_suppressLayout(Object obj) {
		try {
			method_ViewGroup_suppressLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_unFocus(Object obj) {
		try {
			method_ViewGroup_unFocus.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_updateLocalSystemUiVisibility(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_updateLocalSystemUiVisibility.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_updateViewLayout(Object obj) {
		try {
			method_ViewGroup_updateViewLayout.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_access$000(Object obj) {
		try {
			method_ViewGroup_access$000.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LayoutAnimationController methodInvoke_ViewGroup_access$100(Object obj) {
		LayoutAnimationController o = null;
		try {
			o = (LayoutAnimationController) method_ViewGroup_access$100.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static AnimationListener methodInvoke_ViewGroup_access$200(Object obj) {
		AnimationListener o = null;
		try {
			o = (AnimationListener) method_ViewGroup_access$200.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_access$300(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_access$300.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_access$302(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_access$302.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_access$500(Object obj) {
		try {
			method_ViewGroup_access$500.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_addDisappearingView(Object obj) {
		try {
			method_ViewGroup_addDisappearingView.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_addInArray(Object obj) {
		try {
			method_ViewGroup_addInArray.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_addViewInner(Object obj) {
		try {
			method_ViewGroup_addViewInner.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_bindLayoutAnimation(Object obj) {
		try {
			method_ViewGroup_bindLayoutAnimation.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_canViewReceivePointerEvents(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_canViewReceivePointerEvents.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_cancelAndClearTouchTargets(Object obj) {
		try {
			method_ViewGroup_cancelAndClearTouchTargets.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_cancelHoverTarget(Object obj) {
		try {
			method_ViewGroup_cancelHoverTarget.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_cancelTouchTarget(Object obj) {
		try {
			method_ViewGroup_cancelTouchTarget.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_clearCachedLayoutMode(Object obj) {
		try {
			method_ViewGroup_clearCachedLayoutMode.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_clearTouchTargets(Object obj) {
		try {
			method_ViewGroup_clearTouchTargets.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_debugDraw(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_debugDraw.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static int methodInvoke_ViewGroup_dipsToPixels(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_dipsToPixels.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchTransformedGenericPointerEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchTransformedGenericPointerEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_dispatchTransformedTouchEvent(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_dispatchTransformedTouchEvent.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_drawCorner(Object obj) {
		try {
			method_ViewGroup_drawCorner.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_drawRect(Object obj) {
		try {
			method_ViewGroup_drawRect.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_drawRectCorners(Object obj) {
		try {
			method_ViewGroup_drawRectCorners.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_exitHoverTargets(Object obj) {
		try {
			method_ViewGroup_exitHoverTargets.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_fillDifference(Object obj) {
		try {
			method_ViewGroup_fillDifference.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_fillRect(Object obj) {
		try {
			method_ViewGroup_fillRect.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int methodInvoke_ViewGroup_getChildMeasureSpec(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_getChildMeasureSpec.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Paint methodInvoke_ViewGroup_getDebugPaint(Object obj) {
		Paint o = null;
		try {
			o = (Paint) method_ViewGroup_getDebugPaint.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static PointF methodInvoke_ViewGroup_getLocalPoint(Object obj) {
		PointF o = null;
		try {
			o = (PointF) method_ViewGroup_getLocalPoint.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean methodInvoke_ViewGroup_hasBooleanFlag(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_hasBooleanFlag.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_initFromAttributes(Object obj) {
		try {
			method_ViewGroup_initFromAttributes.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_initViewGroup(Object obj) {
		try {
			method_ViewGroup_initViewGroup.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_notifyAnimationListener(Object obj) {
		try {
			method_ViewGroup_notifyAnimationListener.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MotionEvent methodInvoke_ViewGroup_obtainMotionEventNoHistoryOrSelf(Object obj) {
		MotionEvent o = null;
		try {
			o = (MotionEvent) method_ViewGroup_obtainMotionEventNoHistoryOrSelf.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_removeFromArray(Object obj) {
		try {
			method_ViewGroup_removeFromArray.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removePointersFromTouchTargets(Object obj) {
		try {
			method_ViewGroup_removePointersFromTouchTargets.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViewInternal(Object obj) {
		try {
			method_ViewGroup_removeViewInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_removeViewsInternal(Object obj) {
		try {
			method_ViewGroup_removeViewsInternal.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean methodInvoke_ViewGroup_resetCancelNextUpFlag(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) method_ViewGroup_resetCancelNextUpFlag.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void methodInvoke_ViewGroup_resetTouchState(Object obj) {
		try {
			method_ViewGroup_resetTouchState.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void methodInvoke_ViewGroup_setBooleanFlag(Object obj) {
		try {
			method_ViewGroup_setBooleanFlag.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int methodInvoke_ViewGroup_sign(Object obj) {
		int o = -1;
		try {
			o = (Integer) method_ViewGroup_sign.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static boolean methodInvoke_ViewGroup_isLayoutRtl(Object obj) {
		boolean o = false;
		byte mMarginFlags = getField_ViewGroup_mMarginFlags(obj);
		//		try {
//			o = (Boolean) method_ViewGroup_isLayoutRtl.invoke(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		 o = ((mMarginFlags & LAYOUT_DIRECTION_MASK) == 1);
		return o;
	}

	public static Field field_ViewGroup_DEBUG_DRAW;
	public static Field field_ViewGroup_LAYOUT_MODE_DEFAULT;
	public static Field field_ViewGroup_sDebugLines;
	public static Field field_ViewGroup_sDebugPaint;
	public static Field field_ViewGroup_mAnimationListener;
	public static Field field_ViewGroup_mCachePaint;
	public static Field field_ViewGroup_mVisibilityChangingChildren;
	public static Field field_ViewGroup_mTransitioningViews;
	public static Field field_ViewGroup_mChildTransformation;
	public static Field field_ViewGroup_mChildren;
	public static Field field_ViewGroup_mTransition;
	public static Field field_ViewGroup_mCurrentDrag;
	public static Field field_ViewGroup_mCurrentDragView;
	public static Field field_ViewGroup_mDisappearingChildren;
	public static Field field_ViewGroup_mDragNotifiedChildren;
	public static Field field_ViewGroup_mFirstHoverTarget;
	public static Field field_ViewGroup_mFirstTouchTarget;
	public static Field field_ViewGroup_mFocused;
	public static Field field_ViewGroup_mOnHierarchyChangeListener;
	public static Field field_ViewGroup_mLocalPoint;
	public static Field field_ViewGroup_mInvalidateRegion;
	public static Field field_ViewGroup_mInvalidationTransformation;
	public static Field field_ViewGroup_mLayoutTransitionListener;
	public static Field field_ViewGroup_mLayoutAnimationController;
	public static Field field_ViewGroup_mLastTouchDownTime;
	public static Field field_ViewGroup_mLastTouchDownY;
	public static Field field_ViewGroup_mLastTouchDownX;
	public static Field field_ViewGroup_mLayoutCalledWhileSuppressed;
	public static Field field_ViewGroup_mLayoutMode;
	public static Field field_ViewGroup_mLastTouchDownIndex;
	public static Field field_ViewGroup_mHoveredSelf;
	public static Field field_ViewGroup_mGroupFlags;
	public static Field field_ViewGroup_mPersistentDrawingCache;
	public static Field field_ViewGroup_mSuppressLayout;
	public static Field field_ViewGroup_mChildrenCount;
	public static Field field_ViewGroup_mChildCountWithTransientState;
	public static Field field_ViewGroup_mChildAcceptsDrag;
	public static Field field_ViewGroup_mMarginFlags;

	private static void initField_ViewGroup(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
//		Field field = null;
//		try {
//			field = clz.getField("mMarginFlags");
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Field declaredField;
//		try {
//			declaredField = clz.getDeclaredField("mMarginFlags");
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for (Field f : fields) {

			if (f.getName().equals("DEBUG_DRAW")) {
				field_ViewGroup_DEBUG_DRAW = f;
				field_ViewGroup_DEBUG_DRAW.setAccessible(true);

			} else if (f.getName().equals("LAYOUT_MODE_DEFAULT")) {
				field_ViewGroup_LAYOUT_MODE_DEFAULT = f;
				field_ViewGroup_LAYOUT_MODE_DEFAULT.setAccessible(true);

			} else if (f.getName().equals("sDebugLines")) {
				field_ViewGroup_sDebugLines = f;
				field_ViewGroup_sDebugLines.setAccessible(true);

			} else if (f.getName().equals("sDebugPaint")) {
				field_ViewGroup_sDebugPaint = f;
				field_ViewGroup_sDebugPaint.setAccessible(true);

			} else if (f.getName().equals("mAnimationListener")) {
				field_ViewGroup_mAnimationListener = f;
				field_ViewGroup_mAnimationListener.setAccessible(true);

			} else if (f.getName().equals("mCachePaint")) {
				field_ViewGroup_mCachePaint = f;
				field_ViewGroup_mCachePaint.setAccessible(true);

			} else if (f.getName().equals("mVisibilityChangingChildren")) {
				field_ViewGroup_mVisibilityChangingChildren = f;
				field_ViewGroup_mVisibilityChangingChildren.setAccessible(true);

			} else if (f.getName().equals("mTransitioningViews")) {
				field_ViewGroup_mTransitioningViews = f;
				field_ViewGroup_mTransitioningViews.setAccessible(true);

			} else if (f.getName().equals("mChildTransformation")) {
				field_ViewGroup_mChildTransformation = f;
				field_ViewGroup_mChildTransformation.setAccessible(true);

			} else if (f.getName().equals("mChildren")) {
				field_ViewGroup_mChildren = f;
				field_ViewGroup_mChildren.setAccessible(true);

			} else if (f.getName().equals("mTransition")) {
				field_ViewGroup_mTransition = f;
				field_ViewGroup_mTransition.setAccessible(true);

			} else if (f.getName().equals("mCurrentDrag")) {
				field_ViewGroup_mCurrentDrag = f;
				field_ViewGroup_mCurrentDrag.setAccessible(true);

			} else if (f.getName().equals("mCurrentDragView")) {
				field_ViewGroup_mCurrentDragView = f;
				field_ViewGroup_mCurrentDragView.setAccessible(true);

			} else if (f.getName().equals("mDisappearingChildren")) {
				field_ViewGroup_mDisappearingChildren = f;
				field_ViewGroup_mDisappearingChildren.setAccessible(true);

			} else if (f.getName().equals("mDragNotifiedChildren")) {
				field_ViewGroup_mDragNotifiedChildren = f;
				field_ViewGroup_mDragNotifiedChildren.setAccessible(true);

			} else if (f.getName().equals("mFirstHoverTarget")) {
				field_ViewGroup_mFirstHoverTarget = f;
				field_ViewGroup_mFirstHoverTarget.setAccessible(true);

			} else if (f.getName().equals("mFirstTouchTarget")) {
				field_ViewGroup_mFirstTouchTarget = f;
				field_ViewGroup_mFirstTouchTarget.setAccessible(true);

			} else if (f.getName().equals("mFocused")) {
				field_ViewGroup_mFocused = f;
				field_ViewGroup_mFocused.setAccessible(true);

			} else if (f.getName().equals("mOnHierarchyChangeListener")) {
				field_ViewGroup_mOnHierarchyChangeListener = f;
				field_ViewGroup_mOnHierarchyChangeListener.setAccessible(true);

			} else if (f.getName().equals("mLocalPoint")) {
				field_ViewGroup_mLocalPoint = f;
				field_ViewGroup_mLocalPoint.setAccessible(true);

			} else if (f.getName().equals("mInvalidateRegion")) {
				field_ViewGroup_mInvalidateRegion = f;
				field_ViewGroup_mInvalidateRegion.setAccessible(true);

			} else if (f.getName().equals("mInvalidationTransformation")) {
				field_ViewGroup_mInvalidationTransformation = f;
				field_ViewGroup_mInvalidationTransformation.setAccessible(true);

			} else if (f.getName().equals("mLayoutTransitionListener")) {
				field_ViewGroup_mLayoutTransitionListener = f;
				field_ViewGroup_mLayoutTransitionListener.setAccessible(true);

			} else if (f.getName().equals("mLayoutAnimationController")) {
				field_ViewGroup_mLayoutAnimationController = f;
				field_ViewGroup_mLayoutAnimationController.setAccessible(true);

			} else if (f.getName().equals("mLastTouchDownTime")) {
				field_ViewGroup_mLastTouchDownTime = f;
				field_ViewGroup_mLastTouchDownTime.setAccessible(true);

			} else if (f.getName().equals("mLastTouchDownY")) {
				field_ViewGroup_mLastTouchDownY = f;
				field_ViewGroup_mLastTouchDownY.setAccessible(true);

			} else if (f.getName().equals("mLastTouchDownX")) {
				field_ViewGroup_mLastTouchDownX = f;
				field_ViewGroup_mLastTouchDownX.setAccessible(true);

			} else if (f.getName().equals("mLayoutCalledWhileSuppressed")) {
				field_ViewGroup_mLayoutCalledWhileSuppressed = f;
				field_ViewGroup_mLayoutCalledWhileSuppressed.setAccessible(true);

			} else if (f.getName().equals("mLayoutMode")) {
				field_ViewGroup_mLayoutMode = f;
				field_ViewGroup_mLayoutMode.setAccessible(true);

			} else if (f.getName().equals("mLastTouchDownIndex")) {
				field_ViewGroup_mLastTouchDownIndex = f;
				field_ViewGroup_mLastTouchDownIndex.setAccessible(true);

			} else if (f.getName().equals("mHoveredSelf")) {
				field_ViewGroup_mHoveredSelf = f;
				field_ViewGroup_mHoveredSelf.setAccessible(true);

			} else if (f.getName().equals("mGroupFlags")) {
				field_ViewGroup_mGroupFlags = f;
				field_ViewGroup_mGroupFlags.setAccessible(true);

			} else if (f.getName().equals("mPersistentDrawingCache")) {
				field_ViewGroup_mPersistentDrawingCache = f;
				field_ViewGroup_mPersistentDrawingCache.setAccessible(true);

			} else if (f.getName().equals("mSuppressLayout")) {
				field_ViewGroup_mSuppressLayout = f;
				field_ViewGroup_mSuppressLayout.setAccessible(true);

			} else if (f.getName().equals("mChildrenCount")) {
				field_ViewGroup_mChildrenCount = f;
				field_ViewGroup_mChildrenCount.setAccessible(true);

			} else if (f.getName().equals("mChildCountWithTransientState")) {
				field_ViewGroup_mChildCountWithTransientState = f;
				field_ViewGroup_mChildCountWithTransientState.setAccessible(true);

			} else if (f.getName().equals("mChildAcceptsDrag")) {
				field_ViewGroup_mChildAcceptsDrag = f;
				field_ViewGroup_mChildAcceptsDrag.setAccessible(true);
			} else if (f.getName().equals("mMarginFlags")) {
				field_ViewGroup_mMarginFlags = f;
				field_ViewGroup_mMarginFlags.setAccessible(true);
			}
		}
	}

	public static byte getField_ViewGroup_mMarginFlags(Object obj) {
		byte o = 0;
		try {
			o = (Byte) field_ViewGroup_mMarginFlags.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	public static boolean getField_ViewGroup_DEBUG_DRAW(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_ViewGroup_DEBUG_DRAW.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_DEBUG_DRAW(Object obj, Object value) {
		try {
			field_ViewGroup_DEBUG_DRAW.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_LAYOUT_MODE_DEFAULT(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_LAYOUT_MODE_DEFAULT.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_LAYOUT_MODE_DEFAULT(Object obj, Object value) {
		try {
			field_ViewGroup_LAYOUT_MODE_DEFAULT.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float[] getField_ViewGroup_sDebugLines(Object obj) {
		float[] o = null;
		try {
			o = (float[]) field_ViewGroup_sDebugLines.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_sDebugLines(Object obj, Object value) {
		try {
			field_ViewGroup_sDebugLines.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Paint getField_ViewGroup_sDebugPaint(Object obj) {
		Paint o = null;
		try {
			o = (Paint) field_ViewGroup_sDebugPaint.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_sDebugPaint(Object obj, Object value) {
		try {
			field_ViewGroup_sDebugPaint.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AnimationListener getField_ViewGroup_mAnimationListener(Object obj) {
		AnimationListener o = null;
		try {
			o = (AnimationListener) field_ViewGroup_mAnimationListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mAnimationListener(Object obj, Object value) {
		try {
			field_ViewGroup_mAnimationListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Paint getField_ViewGroup_mCachePaint(Object obj) {
		Paint o = null;
		try {
			o = (Paint) field_ViewGroup_mCachePaint.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mCachePaint(Object obj, Object value) {
		try {
			field_ViewGroup_mCachePaint.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mVisibilityChangingChildren(Object obj, Object value) {
		try {
			field_ViewGroup_mVisibilityChangingChildren.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mTransitioningViews(Object obj, Object value) {
		try {
			field_ViewGroup_mTransitioningViews.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Transformation getField_ViewGroup_mChildTransformation(Object obj) {
		Transformation o = null;
		try {
			o = (Transformation) field_ViewGroup_mChildTransformation.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mChildTransformation(Object obj, Object value) {
		try {
			field_ViewGroup_mChildTransformation.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static View[] getField_ViewGroup_mChildren(Object obj) {
		View[] o = null;
		try {
			o = (View[]) field_ViewGroup_mChildren.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mChildren(Object obj, Object value) {
		try {
			field_ViewGroup_mChildren.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LayoutTransition getField_ViewGroup_mTransition(Object obj) {
		LayoutTransition o = null;
		try {
			o = (LayoutTransition) field_ViewGroup_mTransition.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mTransition(Object obj, Object value) {
		try {
			field_ViewGroup_mTransition.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DragEvent getField_ViewGroup_mCurrentDrag(Object obj) {
		DragEvent o = null;
		try {
			o = (DragEvent) field_ViewGroup_mCurrentDrag.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mCurrentDrag(Object obj, Object value) {
		try {
			field_ViewGroup_mCurrentDrag.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static View getField_ViewGroup_mCurrentDragView(Object obj) {
		View o = null;
		try {
			o = (View) field_ViewGroup_mCurrentDragView.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mCurrentDragView(Object obj, Object value) {
		try {
			field_ViewGroup_mCurrentDragView.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mDisappearingChildren(Object obj, Object value) {
		try {
			field_ViewGroup_mDisappearingChildren.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mDragNotifiedChildren(Object obj, Object value) {
		try {
			field_ViewGroup_mDragNotifiedChildren.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mFirstHoverTarget(Object obj, Object value) {
		try {
			field_ViewGroup_mFirstHoverTarget.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setField_ViewGroup_mFirstTouchTarget(Object obj, Object value) {
		try {
			field_ViewGroup_mFirstTouchTarget.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static View getField_ViewGroup_mFocused(Object obj) {
		View o = null;
		try {
			o = (View) field_ViewGroup_mFocused.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mFocused(Object obj, Object value) {
		try {
			field_ViewGroup_mFocused.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OnHierarchyChangeListener getField_ViewGroup_mOnHierarchyChangeListener(Object obj) {
		OnHierarchyChangeListener o = null;
		try {
			o = (OnHierarchyChangeListener) field_ViewGroup_mOnHierarchyChangeListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mOnHierarchyChangeListener(Object obj, Object value) {
		try {
			field_ViewGroup_mOnHierarchyChangeListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PointF getField_ViewGroup_mLocalPoint(Object obj) {
		PointF o = null;
		try {
			o = (PointF) field_ViewGroup_mLocalPoint.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLocalPoint(Object obj, Object value) {
		try {
			field_ViewGroup_mLocalPoint.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static RectF getField_ViewGroup_mInvalidateRegion(Object obj) {
		RectF o = null;
		try {
			o = (RectF) field_ViewGroup_mInvalidateRegion.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mInvalidateRegion(Object obj, Object value) {
		try {
			field_ViewGroup_mInvalidateRegion.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Transformation getField_ViewGroup_mInvalidationTransformation(Object obj) {
		Transformation o = null;
		try {
			o = (Transformation) field_ViewGroup_mInvalidationTransformation.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mInvalidationTransformation(Object obj, Object value) {
		try {
			field_ViewGroup_mInvalidationTransformation.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TransitionListener getField_ViewGroup_mLayoutTransitionListener(Object obj) {
		TransitionListener o = null;
		try {
			o = (TransitionListener) field_ViewGroup_mLayoutTransitionListener.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLayoutTransitionListener(Object obj, Object value) {
		try {
			field_ViewGroup_mLayoutTransitionListener.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LayoutAnimationController getField_ViewGroup_mLayoutAnimationController(Object obj) {
		LayoutAnimationController o = null;
		try {
			o = (LayoutAnimationController) field_ViewGroup_mLayoutAnimationController.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLayoutAnimationController(Object obj, Object value) {
		try {
			field_ViewGroup_mLayoutAnimationController.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getField_ViewGroup_mLastTouchDownTime(Object obj) {
		long o = -1;
		try {
			o = (Long) field_ViewGroup_mLastTouchDownTime.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLastTouchDownTime(Object obj, Object value) {
		try {
			field_ViewGroup_mLastTouchDownTime.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float getField_ViewGroup_mLastTouchDownY(Object obj) {
		float o = -1f;
		try {
			o = (Float) field_ViewGroup_mLastTouchDownY.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLastTouchDownY(Object obj, Object value) {
		try {
			field_ViewGroup_mLastTouchDownY.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float getField_ViewGroup_mLastTouchDownX(Object obj) {
		float o = -1f;
		try {
			o = (Float) field_ViewGroup_mLastTouchDownX.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLastTouchDownX(Object obj, Object value) {
		try {
			field_ViewGroup_mLastTouchDownX.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_ViewGroup_mLayoutCalledWhileSuppressed(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_ViewGroup_mLayoutCalledWhileSuppressed.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLayoutCalledWhileSuppressed(Object obj, Object value) {
		try {
			field_ViewGroup_mLayoutCalledWhileSuppressed.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mLayoutMode(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mLayoutMode.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLayoutMode(Object obj, Object value) {
		try {
			field_ViewGroup_mLayoutMode.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mLastTouchDownIndex(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mLastTouchDownIndex.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mLastTouchDownIndex(Object obj, Object value) {
		try {
			field_ViewGroup_mLastTouchDownIndex.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_ViewGroup_mHoveredSelf(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_ViewGroup_mHoveredSelf.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mHoveredSelf(Object obj, Object value) {
		try {
			field_ViewGroup_mHoveredSelf.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mGroupFlags(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mGroupFlags.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mGroupFlags(Object obj, Object value) {
		try {
			field_ViewGroup_mGroupFlags.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mPersistentDrawingCache(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mPersistentDrawingCache.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mPersistentDrawingCache(Object obj, Object value) {
		try {
			field_ViewGroup_mPersistentDrawingCache.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_ViewGroup_mSuppressLayout(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_ViewGroup_mSuppressLayout.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mSuppressLayout(Object obj, Object value) {
		try {
			field_ViewGroup_mSuppressLayout.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mChildrenCount(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mChildrenCount.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mChildrenCount(Object obj, Object value) {
		try {
			field_ViewGroup_mChildrenCount.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getField_ViewGroup_mChildCountWithTransientState(Object obj) {
		int o = -1;
		try {
			o = (Integer) field_ViewGroup_mChildCountWithTransientState.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mChildCountWithTransientState(Object obj, Object value) {
		try {
			field_ViewGroup_mChildCountWithTransientState.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getField_ViewGroup_mChildAcceptsDrag(Object obj) {
		boolean o = false;
		try {
			o = (Boolean) field_ViewGroup_mChildAcceptsDrag.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void setField_ViewGroup_mChildAcceptsDrag(Object obj, Object value) {
		try {
			field_ViewGroup_mChildAcceptsDrag.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
