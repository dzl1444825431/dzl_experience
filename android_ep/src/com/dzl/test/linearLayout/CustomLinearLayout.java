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

package com.dzl.test.linearLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.RemoteViews.RemoteView;

import com.dzl.test.R;

/**
 * A Layout that arranges its children in a single column or a single row. The
 * direction of the row can be set by calling {@link #setOrientation(int)
 * setOrientation()}. You can also specify gravity, which specifies the
 * alignment of all the child elements by calling {@link #setGravity(int)
 * setGravity()} or specify that specific children grow to fill up any remaining
 * space in the layout by setting the <em>weight</em> member of
 * {@link android.widget.CustomLinearLayout.LayoutParams
 * CustomLinearLayout.LayoutParams}. The default orientation is horizontal.
 *
 * <p>
 * See the <a href="{@docRoot}
 * resources/tutorials/views/hello-linearlayout.html">Linear Layout
 * tutorial</a>.
 * </p>
 *
 * <p>
 * Also see {@link CustomLinearLayout.LayoutParams
 * android.widget.CustomLinearLayout.LayoutParams} for layout attributes
 * </p>
 */
@RemoteView
public class CustomLinearLayout extends ViewGroup {
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;

	/**
	 * Whether the children of this layout are baseline aligned. Only applicable
	 * if {@link #mOrientation} is horizontal.
	 */
	@ViewDebug.ExportedProperty(category = "layout")
	private boolean mBaselineAligned = true;

	/**
	 * If this layout is part of another layout that is baseline aligned, use
	 * the child at this index as the baseline.
	 *
	 * Note: this is orthogonal to {@link #mBaselineAligned}, which is concerned
	 * with whether the children of this layout are baseline aligned.
	 */
	@ViewDebug.ExportedProperty(category = "layout")
	private int mBaselineAlignedChildIndex = -1;

	/**
	 * The additional offset to the child's baseline. We'll calculate the
	 * baseline of this layout as we measure vertically; for horizontal linear
	 * layouts, the offset of 0 is appropriate.
	 */
	@ViewDebug.ExportedProperty(category = "measurement")
	private int mBaselineChildTop = 0;

	@ViewDebug.ExportedProperty(category = "measurement")
	private int mOrientation;

	@ViewDebug.ExportedProperty(category = "measurement", mapping = { @ViewDebug.IntToString(from = -1, to = "NONE"),
			@ViewDebug.IntToString(from = Gravity.NO_GRAVITY, to = "NONE"), @ViewDebug.IntToString(from = Gravity.TOP, to = "TOP"),
			@ViewDebug.IntToString(from = Gravity.BOTTOM, to = "BOTTOM"), @ViewDebug.IntToString(from = Gravity.LEFT, to = "LEFT"),
			@ViewDebug.IntToString(from = Gravity.RIGHT, to = "RIGHT"), @ViewDebug.IntToString(from = Gravity.CENTER_VERTICAL, to = "CENTER_VERTICAL"),
			@ViewDebug.IntToString(from = Gravity.FILL_VERTICAL, to = "FILL_VERTICAL"),
			@ViewDebug.IntToString(from = Gravity.CENTER_HORIZONTAL, to = "CENTER_HORIZONTAL"),
			@ViewDebug.IntToString(from = Gravity.FILL_HORIZONTAL, to = "FILL_HORIZONTAL"), @ViewDebug.IntToString(from = Gravity.CENTER, to = "CENTER"),
			@ViewDebug.IntToString(from = Gravity.FILL, to = "FILL") })
	private int mGravity = Gravity.LEFT | Gravity.TOP;

	@ViewDebug.ExportedProperty(category = "measurement")
	private int mTotalLength;

	@ViewDebug.ExportedProperty(category = "layout")
	private float mWeightSum;

	@ViewDebug.ExportedProperty(category = "layout")
	private boolean mUseLargestChild;

	private int[] mMaxAscent;
	private int[] mMaxDescent;

	private static final int VERTICAL_GRAVITY_COUNT = 4;

	private static final int INDEX_CENTER_VERTICAL = 0;
	private static final int INDEX_TOP = 1;
	private static final int INDEX_BOTTOM = 2;
	private static final int INDEX_FILL = 3;

	public CustomLinearLayout(Context context) {
		super(context);
		System.out.println("resp1onse CustomLinearLayout: public CustomLinearLayout(Context context) { start constructor ");
		System.out.println("resp1onse CustomLinearLayout: public CustomLinearLayout(Context context) { end constructor ");
	}

	public CustomLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		System.out.println("resp1onse CustomLinearLayout: public CustomLinearLayout(Context context, AttributeSet attrs) { start constructor ");

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);

		int index = a.getInt(R.styleable.CustomLinearLayout_orientation, -1);
		if (index >= 0) {
			setOrientation(index);
		}

		index = a.getInt(R.styleable.CustomLinearLayout_gravity, -1);
		if (index >= 0) {
			setGravity(index);
		}

		boolean baselineAligned = a.getBoolean(R.styleable.CustomLinearLayout_baselineAligned, true);
		if (!baselineAligned) {
			setBaselineAligned(baselineAligned);
		}

		mWeightSum = a.getFloat(R.styleable.CustomLinearLayout_weightSum, -1.0f);

		mBaselineAlignedChildIndex = a.getInt(R.styleable.CustomLinearLayout_baselineAlignedChildIndex, -1);

		// TO DO: Better name, add Java APIs, make it public
		mUseLargestChild = a.getBoolean(R.styleable.CustomLinearLayout_useLargestChild, false);

		a.recycle();
		System.out.println("resp1onse CustomLinearLayout: public CustomLinearLayout(Context context, AttributeSet attrs) { end constructor ");
	}

	/**
	 * <p>
	 * Indicates whether widgets contained within this layout are aligned on
	 * their baseline or not.
	 * </p>
	 *
	 * @return true when widgets are baseline-aligned, false otherwise
	 */
	public boolean isBaselineAligned() {
		System.out.println("resp1onse CustomLinearLayout: public boolean isBaselineAligned() { start return ");
		System.out.println("resp1onse CustomLinearLayout: public boolean isBaselineAligned() { end return ");
		return mBaselineAligned;
	}

	/**
	 * <p>
	 * Defines whether widgets contained in this layout are baseline-aligned or
	 * not.
	 * </p>
	 *
	 * @param baselineAligned
	 *            true to align widgets on their baseline, false otherwise
	 *
	 * @attr ref android.R.styleable#LinearLayout_baselineAligned
	 */
	// @android.view.RemotableViewMethod
	public void setBaselineAligned(boolean baselineAligned) {
		System.out.println("resp1onse CustomLinearLayout: public void setBaselineAligned(boolean baselineAligned) { start void ");
		mBaselineAligned = baselineAligned;
		System.out.println("resp1onse CustomLinearLayout: public void setBaselineAligned(boolean baselineAligned) { end void ");
	}

	@Override
	public int getBaseline() {
		System.out.println("resp1onse CustomLinearLayout: public int getBaseline() { start return ");
		if (mBaselineAlignedChildIndex < 0) {
			return super.getBaseline();
		}

		if (getChildCount() <= mBaselineAlignedChildIndex) {
			throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout " + "set to an index that is out of bounds.");
		}

		final View child = getChildAt(mBaselineAlignedChildIndex);
		final int childBaseline = child.getBaseline();

		if (childBaseline == -1) {
			if (mBaselineAlignedChildIndex == 0) {
				// this is just the default case, safe to return -1
				return -1;
			}
			// the user picked an index that points to something that doesn't
			// know how to calculate its baseline.
		System.out.println("resp1onse CustomLinearLayout: public int getBaseline() { end return if ");
			throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout " + "points to a View that doesn't know how to get its baseline.");
		}

		// TO DO: This should try to take into account the virtual offsets
		// (See getNextLocationOffset and getLocationOffset)
		// We should add to childTop:
		// sum([getNextLocationOffset(getChildAt(i)) / i <
		// mBaselineAlignedChildIndex])
		// and also add:
		// getLocationOffset(child)
		int childTop = mBaselineChildTop;

		if (mOrientation == VERTICAL) {
			final int majorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
			if (majorGravity != Gravity.TOP) {
				switch (majorGravity) {
				case Gravity.BOTTOM:
					childTop = getBottom() - getTop() - getPaddingBottom() - mTotalLength;
					break;

				case Gravity.CENTER_VERTICAL:
					childTop += ((getBottom() - getTop() - getPaddingTop() - getPaddingBottom()) - mTotalLength) / 2;
					break;
				}
			}
		}

		CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();
		System.out.println("resp1onse CustomLinearLayout: public int getBaseline() { end return  =1");
		return childTop + lp.topMargin + childBaseline;
	}

	/**
	 * @return The index of the child that will be used if this layout is part
	 *         of a larger layout that is baseline aligned, or -1 if none has
	 *         been set.
	 */
	public int getBaselineAlignedChildIndex() {
		System.out.println("resp1onse CustomLinearLayout: public int getBaselineAlignedChildIndex() { start return ");
		System.out.println("resp1onse CustomLinearLayout: public int getBaselineAlignedChildIndex() { end return ");
		return mBaselineAlignedChildIndex;
	}

	/**
	 * @param i
	 *            The index of the child that will be used if this layout is
	 *            part of a larger layout that is baseline aligned.
	 * 
	 * @attr ref android.R.styleable#LinearLayout_baselineAlignedChildIndex
	 */
	// @android.view.RemotableViewMethod
	public void setBaselineAlignedChildIndex(int i) {
		System.out.println("resp1onse CustomLinearLayout: public void setBaselineAlignedChildIndex(int i) { start void ");
		if ((i < 0) || (i >= getChildCount())) {
			throw new IllegalArgumentException("base aligned child index out " + "of range (0, " + getChildCount() + ")");
		}
		mBaselineAlignedChildIndex = i;
		System.out.println("resp1onse CustomLinearLayout: public void setBaselineAlignedChildIndex(int i) { end void ");
	}

	/**
	 * <p>
	 * Returns the view at the specified index. This method can be overriden to
	 * take into account virtual children. Refer to
	 * {@link android.widget.TableLayout} and {@link android.widget.TableRow}
	 * for an example.
	 * </p>
	 *
	 * @param index
	 *            the child's index
	 * @return the child at the specified index
	 */
	View getVirtualChildAt(int index) {
		return getChildAt(index);
	}

	/**
	 * <p>
	 * Returns the virtual number of children. This number might be different
	 * than the actual number of children if the layout can hold virtual
	 * children. Refer to {@link android.widget.TableLayout} and
	 * {@link android.widget.TableRow} for an example.
	 * </p>
	 *
	 * @return the virtual number of children
	 */
	int getVirtualChildCount() {
		return getChildCount();
	}

	/**
	 * Returns the desired weights sum.
	 *
	 * @return A number greater than 0.0f if the weight sum is defined, or a
	 *         number lower than or equals to 0.0f if not weight sum is to be
	 *         used.
	 */
	public float getWeightSum() {
		System.out.println("resp1onse CustomLinearLayout: public float getWeightSum() { start return ");
		System.out.println("resp1onse CustomLinearLayout: public float getWeightSum() { end return ");
		return mWeightSum;
	}

	/**
	 * Defines the desired weights sum. If unspecified the weights sum is
	 * computed at layout time by adding the layout_weight of each child.
	 *
	 * This can be used for instance to give a single child 50% of the total
	 * available space by giving it a layout_weight of 0.5 and setting the
	 * weightSum to 1.0.
	 *
	 * @param weightSum
	 *            a number greater than 0.0f, or a number lower than or equals
	 *            to 0.0f if the weight sum should be computed from the
	 *            children's layout_weight
	 */
	// @android.view.RemotableViewMethod
	public void setWeightSum(float weightSum) {
		System.out.println("resp1onse CustomLinearLayout: public void setWeightSum(float weightSum) { start void ");
		mWeightSum = Math.max(0.0f, weightSum);
		System.out.println("resp1onse CustomLinearLayout: public void setWeightSum(float weightSum) { end void ");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { start void ");
		if (mOrientation == VERTICAL) {
			measureVertical(widthMeasureSpec, heightMeasureSpec);
		} else {
			measureHorizontal(widthMeasureSpec, heightMeasureSpec);
		}
		System.out.println("resp1onse CustomLinearLayout: protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	/**
	 * Measures the children when the orientation of this LinearLayout is set to
	 * {@link #VERTICAL}.
	 *
	 * @param widthMeasureSpec
	 *            Horizontal space requirements as imposed by the parent.
	 * @param heightMeasureSpec
	 *            Vertical space requirements as imposed by the parent.
	 *
	 * @see #getOrientation()
	 * @see #setOrientation(int)
	 * @see #onMeasure(int, int)
	 */
	void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout: void measureVertical(int widthMeasureSpec, int heightMeasureSpec) { start void ");
		mTotalLength = 0;//子view的高度和
		int maxWidth = 0;
		int alternativeMaxWidth = 0;//子视图的最大宽度(【不】包含layout_weight>0的子view)
		int weightedMaxWidth = 0;	//子视图的最大宽度(【仅】包含layout_weight>0的子view)
		boolean allFillParent = true;//子视图的宽度是否全是fillParent的，用于后续判断是否需要重新计算
		float totalWeight = 0;		//所有子view的weight之和  

		final int count = getVirtualChildCount();//子view的个数(仅包含直接子view）

		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		boolean matchWidth = false;

		//以LinearLayout中第几个子view的baseLine作为LinearLayout的基准线 
		final int baselineChildIndex = mBaselineAlignedChildIndex;
		final boolean useLargestChild = mUseLargestChild;

		int largestChildHeight = Integer.MIN_VALUE;

		// See how tall everyone is. Also remember max width.
		for (int i = 0; i < count; ++i) {
			final View child = getVirtualChildAt(i);

			if (child == null) {
				mTotalLength += measureNullChild(i);
				continue;
			}

			if (child.getVisibility() == View.GONE) {
				i += getChildrenSkipCount(child, i);
				continue;
			}

			CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

			totalWeight += lp.weight;

			if (heightMode == MeasureSpec.EXACTLY && lp.height == 0 && lp.weight > 0) {
				// Optimization: don't bother measuring children who are going
				// to use
				// leftover space. These views will get measured again down
				// below if
				// there is any leftover space.
				/*
                如果父View的mode是EXACTLY，并且height==0 并且lp.weight>0
                那么就先不measure这个child，该view的具体高度等会还要计算，
                直接把topMargin和bottoMargin等属性加到totaoLength中 
              */ 
				final int totalLength = mTotalLength;
				mTotalLength = Math.max(totalLength, totalLength + lp.topMargin + lp.bottomMargin);
			} else {
				int oldHeight = Integer.MIN_VALUE;

				if (lp.height == 0 && lp.weight > 0) {
					// heightMode is either UNSPECIFIED or AT_MOST, and this
					// child wanted to stretch to fill available space.
					// Translate that to WRAP_CONTENT so that it does not end up
					// with a height of 0
					
					//如果父View不是EXACLTY,那么将子View的height变为WRAP_CONTENT  
					oldHeight = 0;
					lp.height = LayoutParams.WRAP_CONTENT;
				}

				// Determine how big this child would like to be. If this or
				// previous children have given a weight, then we allow it to
				// use all available space (and we will shrink things later
				// if needed).
				measureChildBeforeLayout(child, i, widthMeasureSpec, 0, heightMeasureSpec, totalWeight == 0 ? mTotalLength : 0);

				if (oldHeight != Integer.MIN_VALUE) {
					lp.height = oldHeight;
				}

				final int childHeight = child.getMeasuredHeight();
				final int totalLength = mTotalLength;
				mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));

				if (useLargestChild) {
					largestChildHeight = Math.max(childHeight, largestChildHeight);
				}
			}

			/**
			 * If applicable, compute the additional offset to the child's
			 * baseline we'll need later when asked {@link #getBaseline}.
			 */
			if ((baselineChildIndex >= 0) && (baselineChildIndex == i + 1)) {
				mBaselineChildTop = mTotalLength;
			}

			// if we are trying to use a child index for our baseline, the above
			// book keeping only works if there are no children above it with
			// weight. fail fast to aid the developer.
			if (i < baselineChildIndex && lp.weight > 0) {
				//为什么i < baselineChildIndex && lp.weight > 0不行。  
                //假如行的话，如果LinearLayout与其他view视图对其的话，  
                //由于weight>0的作用，会影响其他所有的view位置  
                //应该是由于效率的原因才不允许这样。
				throw new RuntimeException("A child of LinearLayout with index " + "less than mBaselineAlignedChildIndex has weight > 0, which "
						+ "won't work.  Either remove the weight, or don't set " + "mBaselineAlignedChildIndex.");
			}

			boolean matchWidthLocally = false;
			if (widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT) {
				// The width of the linear layout will scale, and at least one
				// child said it wanted to match our width. Set a flag
				// indicating that we need to remeasure at least that view when
				// we know our width.
				
				//如果LinearLayout宽度不是已确定的，如是wrap_content,而子view是FILL_PARENT，  
                //则做标记matchWidth=true； matchWidthLocally = true;  
				matchWidth = true;
				matchWidthLocally = true;
			}

			final int margin = lp.leftMargin + lp.rightMargin;
			final int measuredWidth = child.getMeasuredWidth() + margin;
			maxWidth = Math.max(maxWidth, measuredWidth);

			allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;
			if (lp.weight > 0) {
				/*
				 * Widths of weighted Views are bogus if we end up remeasuring,
				 * so keep them separate.
				 */
				 //如父width是wrap_content，子是fill_parent，则子的宽度需要在父确定后才能确定。这里并不是真实的宽度  
				weightedMaxWidth = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
			} else {
				alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
			}

			i += getChildrenSkipCount(child, i);
		}

		if (useLargestChild && heightMode == MeasureSpec.AT_MOST) {
			mTotalLength = 0;

			for (int i = 0; i < count; ++i) {
				final View child = getVirtualChildAt(i);

				if (child == null) {
					mTotalLength += measureNullChild(i);
					continue;
				}

				if (child.getVisibility() == GONE) {
					i += getChildrenSkipCount(child, i);
					continue;
				}

				final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();
				// Account for negative margins
				final int totalLength = mTotalLength;
				mTotalLength = Math.max(totalLength, totalLength + largestChildHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
			}
		}

		// Add in our padding // 给总高度加上自身的padding
		mTotalLength += getPaddingTop() + getPaddingBottom();

		int heightSize = mTotalLength;//将所有View的高度赋值给heightSize

		// Check against our minimum height // 最小的高度就是背景的高度mBGDrawable.getMinimumHeight()
		heightSize = Math.max(heightSize, getSuggestedMinimumHeight());

		// Reconcile our calculated size with the heightMeasureSpec
		// 如果heightMeasureSpec的size是精确的，那么这个heightsize值==heightMeasureSpec的size
		heightSize = resolveSize(heightSize, heightMeasureSpec);

		// Either expand children with weight to take up available space or
		// shrink them if they extend beyond our current bounds
		//屏幕的高度还剩下delta
		int delta = heightSize - mTotalLength;
		if (delta != 0 && totalWeight > 0.0f) {
			//如果设置了weightsum属性，这weightSum等于weightsum的属性，否则等于totalWeight
			float weightSum = mWeightSum > 0.0f ? mWeightSum : totalWeight;

			mTotalLength = 0;

			for (int i = 0; i < count; ++i) {
				final View child = getVirtualChildAt(i);

				if (child.getVisibility() == View.GONE) {
					continue;
				}

				CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

				float childExtra = lp.weight;
				if (childExtra > 0) {
					// Child said it could absorb extra space -- give him his
					// share
					// 计算方式:子view的weight属性值 * 剩余高度 / weight总和
					int share = (int) (childExtra * delta / weightSum);
					weightSum -= childExtra;
					delta -= share;

					final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin
							+ lp.rightMargin, lp.width);

					// TO DO: Use a field like lp.isMeasured to figure out if
					// this
					// child has been previously measured
					if ((lp.height != 0) || (heightMode != MeasureSpec.EXACTLY)) {
						// child was measured once already above...
						// base new measurement on stored values
						int childHeight = child.getMeasuredHeight() + share;
						// 重新设置子view的高度
						if (childHeight < 0) {
							childHeight = 0;
						}

						child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
					} else {
						// child was skipped in the loop above.
						// Measure for this first time here
						child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, MeasureSpec.EXACTLY));
					}
				}

				final int margin = lp.leftMargin + lp.rightMargin;
				final int measuredWidth = child.getMeasuredWidth() + margin;
				maxWidth = Math.max(maxWidth, measuredWidth);

				boolean matchWidthLocally = widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT;

				alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);

				allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;

				final int totalLength = mTotalLength;
				mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
			}

			// Add in our padding
			mTotalLength += getPaddingTop() + getPaddingBottom();
			// TO DO: Should we recompute the heightSpec based on the new total
			// length?
		} else {
			alternativeMaxWidth = Math.max(alternativeMaxWidth, weightedMaxWidth);
		}

		if (!allFillParent && widthMode != MeasureSpec.EXACTLY) {
			maxWidth = alternativeMaxWidth;
		}

		maxWidth += getPaddingLeft() + getPaddingRight();

		// Check against our minimum width
		maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

		setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), heightSize);

		if (matchWidth) {
			forceUniformWidth(count, heightMeasureSpec);
		}
		System.out.println("resp1onse CustomLinearLayout: void measureVertical(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	private void forceUniformWidth(int count, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout: private void forceUniformWidth(int count, int heightMeasureSpec) { start void ");
		// Pretend that the linear layout has an exact size.
		int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
		for (int i = 0; i < count; ++i) {
			final View child = getVirtualChildAt(i);
			if (child.getVisibility() != GONE) {
				CustomLinearLayout.LayoutParams lp = ((CustomLinearLayout.LayoutParams) child.getLayoutParams());

				if (lp.width == LayoutParams.MATCH_PARENT) {
					// Temporarily force children to reuse their old measured
					// height
					// FIX ME: this may not be right for something like wrapping
					// text?
					int oldHeight = lp.height;
					lp.height = child.getMeasuredHeight();

					// Remeasue with new dimensions
					measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
					lp.height = oldHeight;
				}
			}
		}
		System.out.println("resp1onse CustomLinearLayout: private void forceUniformWidth(int count, int heightMeasureSpec) { end void ");
	}

	/**
	 * Measures the children when the orientation of this LinearLayout is set to
	 * {@link #HORIZONTAL}.
	 *
	 * @param widthMeasureSpec
	 *            Horizontal space requirements as imposed by the parent.
	 * @param heightMeasureSpec
	 *            Vertical space requirements as imposed by the parent.
	 *
	 * @see #getOrientation()
	 * @see #setOrientation(int)
	 * @see #onMeasure(int, int)
	 */
	void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout: void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) { start void ");
		mTotalLength = 0;
		int maxHeight = 0;
		int alternativeMaxHeight = 0;
		int weightedMaxHeight = 0;
		boolean allFillParent = true;
		float totalWeight = 0;

		final int count = getVirtualChildCount();

		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		boolean matchHeight = false;

		if (mMaxAscent == null || mMaxDescent == null) {
			mMaxAscent = new int[VERTICAL_GRAVITY_COUNT];
			mMaxDescent = new int[VERTICAL_GRAVITY_COUNT];
		}

		final int[] maxAscent = mMaxAscent;
		final int[] maxDescent = mMaxDescent;

		maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
		maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;

		final boolean baselineAligned = mBaselineAligned;
		final boolean useLargestChild = mUseLargestChild;

		final boolean isExactly = widthMode == MeasureSpec.EXACTLY;

		int largestChildWidth = Integer.MIN_VALUE;

		// See how wide everyone is. Also remember max height.
		for (int i = 0; i < count; ++i) {
			final View child = getVirtualChildAt(i);

			if (child == null) {
				mTotalLength += measureNullChild(i);
				continue;
			}

			if (child.getVisibility() == GONE) {
				i += getChildrenSkipCount(child, i);
				continue;
			}

			final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

			totalWeight += lp.weight;

			if (widthMode == MeasureSpec.EXACTLY && lp.width == 0 && lp.weight > 0) {
				// Optimization: don't bother measuring children who are going
				// to use
				// leftover space. These views will get measured again down
				// below if
				// there is any leftover space.
				if (isExactly) {
					mTotalLength += lp.leftMargin + lp.rightMargin;
				} else {
					final int totalLength = mTotalLength;
					mTotalLength = Math.max(totalLength, totalLength + lp.leftMargin + lp.rightMargin);
				}

				// Baseline alignment requires to measure widgets to obtain the
				// baseline offset (in particular for TextViews). The following
				// defeats the optimization mentioned above. Allow the child to
				// use as much space as it wants because we can shrink things
				// later (and re-measure).
				if (baselineAligned) {
					final int freeSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
					child.measure(freeSpec, freeSpec);
				}
			} else {
				int oldWidth = Integer.MIN_VALUE;

				if (lp.width == 0 && lp.weight > 0) {
					// widthMode is either UNSPECIFIED or AT_MOST, and this
					// child
					// wanted to stretch to fill available space. Translate that
					// to
					// WRAP_CONTENT so that it does not end up with a width of 0
					oldWidth = 0;
					lp.width = LayoutParams.WRAP_CONTENT;
				}

				// Determine how big this child would like to be. If this or
				// previous children have given a weight, then we allow it to
				// use all available space (and we will shrink things later
				// if needed).
				measureChildBeforeLayout(child, i, widthMeasureSpec, totalWeight == 0 ? mTotalLength : 0, heightMeasureSpec, 0);

				if (oldWidth != Integer.MIN_VALUE) {
					lp.width = oldWidth;
				}

				final int childWidth = child.getMeasuredWidth();
				if (isExactly) {
					mTotalLength += childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
				} else {
					final int totalLength = mTotalLength;
					mTotalLength = Math.max(totalLength, totalLength + childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child));
				}

				if (useLargestChild) {
					largestChildWidth = Math.max(childWidth, largestChildWidth);
				}
			}

			boolean matchHeightLocally = false;
			if (heightMode != MeasureSpec.EXACTLY && lp.height == LayoutParams.MATCH_PARENT) {
				// The height of the linear layout will scale, and at least one
				// child said it wanted to match our height. Set a flag
				// indicating that
				// we need to remeasure at least that view when we know our
				// height.
				matchHeight = true;
				matchHeightLocally = true;
			}

			final int margin = lp.topMargin + lp.bottomMargin;
			final int childHeight = child.getMeasuredHeight() + margin;

			if (baselineAligned) {
				final int childBaseline = child.getBaseline();
				if (childBaseline != -1) {
					// Translates the child's vertical gravity into an index
					// in the range 0..VERTICAL_GRAVITY_COUNT
					final int gravity = (lp.gravity < 0 ? mGravity : lp.gravity) & Gravity.VERTICAL_GRAVITY_MASK;
					final int index = ((gravity >> Gravity.AXIS_Y_SHIFT) & ~Gravity.AXIS_SPECIFIED) >> 1;

					maxAscent[index] = Math.max(maxAscent[index], childBaseline);
					maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
				}
			}

			maxHeight = Math.max(maxHeight, childHeight);

			allFillParent = allFillParent && lp.height == LayoutParams.MATCH_PARENT;
			if (lp.weight > 0) {
				/*
				 * Heights of weighted Views are bogus if we end up remeasuring,
				 * so keep them separate.
				 */
				weightedMaxHeight = Math.max(weightedMaxHeight, matchHeightLocally ? margin : childHeight);
			} else {
				alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? margin : childHeight);
			}

			i += getChildrenSkipCount(child, i);
		}

		// Check mMaxAscent[INDEX_TOP] first because it maps to Gravity.TOP,
		// the most common case
		if (maxAscent[INDEX_TOP] != -1 || maxAscent[INDEX_CENTER_VERTICAL] != -1 || maxAscent[INDEX_BOTTOM] != -1 || maxAscent[INDEX_FILL] != -1) {
			final int ascent = Math.max(maxAscent[INDEX_FILL],
					Math.max(maxAscent[INDEX_CENTER_VERTICAL], Math.max(maxAscent[INDEX_TOP], maxAscent[INDEX_BOTTOM])));
			final int descent = Math.max(maxDescent[INDEX_FILL],
					Math.max(maxDescent[INDEX_CENTER_VERTICAL], Math.max(maxDescent[INDEX_TOP], maxDescent[INDEX_BOTTOM])));
			maxHeight = Math.max(maxHeight, ascent + descent);
		}

		if (useLargestChild && widthMode == MeasureSpec.AT_MOST) {
			mTotalLength = 0;

			for (int i = 0; i < count; ++i) {
				final View child = getVirtualChildAt(i);

				if (child == null) {
					mTotalLength += measureNullChild(i);
					continue;
				}

				if (child.getVisibility() == GONE) {
					i += getChildrenSkipCount(child, i);
					continue;
				}

				final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();
				if (isExactly) {
					mTotalLength += largestChildWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
				} else {
					final int totalLength = mTotalLength;
					mTotalLength = Math.max(totalLength, totalLength + largestChildWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child));
				}
			}
		}

		// Add in our padding
		mTotalLength += getPaddingLeft() + getPaddingRight();

		int widthSize = mTotalLength;

		// Check against our minimum width
		widthSize = Math.max(widthSize, getSuggestedMinimumWidth());

		// Reconcile our calculated size with the widthMeasureSpec
		widthSize = resolveSize(widthSize, widthMeasureSpec);

		// Either expand children with weight to take up available space or
		// shrink them if they extend beyond our current bounds
		int delta = widthSize - mTotalLength;
		if (delta != 0 && totalWeight > 0.0f) {
			float weightSum = mWeightSum > 0.0f ? mWeightSum : totalWeight;

			maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
			maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;
			maxHeight = -1;

			mTotalLength = 0;

			for (int i = 0; i < count; ++i) {
				final View child = getVirtualChildAt(i);

				if (child == null || child.getVisibility() == View.GONE) {
					continue;
				}

				final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

				float childExtra = lp.weight;
				if (childExtra > 0) {
					// Child said it could absorb extra space -- give him his
					// share
					int share = (int) (childExtra * delta / weightSum);
					weightSum -= childExtra;
					delta -= share;

					final int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin
							+ lp.bottomMargin, lp.height);

					// TO DO: Use a field like lp.isMeasured to figure out if
					// this
					// child has been previously measured
					if ((lp.width != 0) || (widthMode != MeasureSpec.EXACTLY)) {
						// child was measured once already above ... base new
						// measurement
						// on stored values
						int childWidth = child.getMeasuredWidth() + share;
						if (childWidth < 0) {
							childWidth = 0;
						}

						child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), childHeightMeasureSpec);
					} else {
						// child was skipped in the loop above. Measure for this
						// first time here
						child.measure(MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, MeasureSpec.EXACTLY), childHeightMeasureSpec);
					}
				}

				if (isExactly) {
					mTotalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
				} else {
					final int totalLength = mTotalLength;
					mTotalLength = Math
							.max(totalLength, totalLength + child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child));
				}

				boolean matchHeightLocally = heightMode != MeasureSpec.EXACTLY && lp.height == LayoutParams.MATCH_PARENT;

				final int margin = lp.topMargin + lp.bottomMargin;
				int childHeight = child.getMeasuredHeight() + margin;
				maxHeight = Math.max(maxHeight, childHeight);
				alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? margin : childHeight);

				allFillParent = allFillParent && lp.height == LayoutParams.MATCH_PARENT;

				if (baselineAligned) {
					final int childBaseline = child.getBaseline();
					if (childBaseline != -1) {
						// Translates the child's vertical gravity into an index
						// in the range 0..2
						final int gravity = (lp.gravity < 0 ? mGravity : lp.gravity) & Gravity.VERTICAL_GRAVITY_MASK;
						final int index = ((gravity >> Gravity.AXIS_Y_SHIFT) & ~Gravity.AXIS_SPECIFIED) >> 1;

						maxAscent[index] = Math.max(maxAscent[index], childBaseline);
						maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
					}
				}
			}

			// Add in our padding
			mTotalLength += getPaddingLeft() + getPaddingRight();
			// TO DO: Should we update widthSize with the new total length?

			// Check mMaxAscent[INDEX_TOP] first because it maps to Gravity.TOP,
			// the most common case
			if (maxAscent[INDEX_TOP] != -1 || maxAscent[INDEX_CENTER_VERTICAL] != -1 || maxAscent[INDEX_BOTTOM] != -1 || maxAscent[INDEX_FILL] != -1) {
				final int ascent = Math.max(maxAscent[INDEX_FILL],
						Math.max(maxAscent[INDEX_CENTER_VERTICAL], Math.max(maxAscent[INDEX_TOP], maxAscent[INDEX_BOTTOM])));
				final int descent = Math.max(maxDescent[INDEX_FILL],
						Math.max(maxDescent[INDEX_CENTER_VERTICAL], Math.max(maxDescent[INDEX_TOP], maxDescent[INDEX_BOTTOM])));
				maxHeight = Math.max(maxHeight, ascent + descent);
			}
		} else {
			alternativeMaxHeight = Math.max(alternativeMaxHeight, weightedMaxHeight);
		}

		if (!allFillParent && heightMode != MeasureSpec.EXACTLY) {
			maxHeight = alternativeMaxHeight;
		}

		maxHeight += getPaddingTop() + getPaddingBottom();

		// Check against our minimum height
		maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());

		setMeasuredDimension(widthSize, resolveSize(maxHeight, heightMeasureSpec));

		if (matchHeight) {
			forceUniformHeight(count, widthMeasureSpec);
		}
		System.out.println("resp1onse CustomLinearLayout: void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) { end void ");
	}

	private void forceUniformHeight(int count, int widthMeasureSpec) {
		System.out.println("resp1onse CustomLinearLayout: private void forceUniformHeight(int count, int widthMeasureSpec) { start void ");
		// Pretend that the linear layout has an exact size. This is the
		// measured height of
		// ourselves. The measured height should be the max height of the
		// children, changed
		// to accomodate the heightMesureSpec from the parent
		int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
		for (int i = 0; i < count; ++i) {
			final View child = getVirtualChildAt(i);
			if (child.getVisibility() != GONE) {
				CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

				if (lp.height == LayoutParams.MATCH_PARENT) {
					// Temporarily force children to reuse their old measured
					// width
					// FIX ME: this may not be right for something like wrapping
					// text?
					int oldWidth = lp.width;
					lp.width = child.getMeasuredWidth();

					// Remeasure with new dimensions
					measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
					lp.width = oldWidth;
				}
			}
		}
		System.out.println("resp1onse CustomLinearLayout: private void forceUniformHeight(int count, int widthMeasureSpec) { end void ");
	}

	/**
	 * <p>
	 * Returns the number of children to skip after measuring/laying out the
	 * specified child.
	 * </p>
	 *
	 * @param child
	 *            the child after which we want to skip children
	 * @param index
	 *            the index of the child after which we want to skip children
	 * @return the number of children to skip, 0 by default
	 */
	int getChildrenSkipCount(View child, int index) {
		return 0;
	}

	/**
	 * <p>
	 * Returns the size (width or height) that should be occupied by a null
	 * child.
	 * </p>
	 *
	 * @param childIndex
	 *            the index of the null child
	 * @return the width or height of the child depending on the orientation
	 */
	int measureNullChild(int childIndex) {
		return 0;
	}

	/**
	 * <p>
	 * Measure the child according to the parent's measure specs. This method
	 * should be overriden by subclasses to force the sizing of children. This
	 * method is called by {@link #measureVertical(int, int)} and
	 * {@link #measureHorizontal(int, int)}.
	 * </p>
	 *
	 * @param child
	 *            the child to measure
	 * @param childIndex
	 *            the index of the child in this view
	 * @param widthMeasureSpec
	 *            horizontal space requirements as imposed by the parent
	 * @param totalWidth
	 *            extra space that has been used up by the parent horizontally
	 * @param heightMeasureSpec
	 *            vertical space requirements as imposed by the parent
	 * @param totalHeight
	 *            extra space that has been used up by the parent vertically
	 */
	void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
		System.out.println("resp1onse CustomLinearLayout: void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) { start void ");
		measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
		System.out.println("resp1onse CustomLinearLayout: void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) { end void ");
	}

	/**
	 * <p>
	 * Return the location offset of the specified child. This can be used by
	 * subclasses to change the location of a given widget.
	 * </p>
	 *
	 * @param child
	 *            the child for which to obtain the location offset
	 * @return the location offset in pixels
	 */
	int getLocationOffset(View child) {
		return 0;
	}

	/**
	 * <p>
	 * Return the size offset of the next sibling of the specified child. This
	 * can be used by subclasses to change the location of the widget following
	 * <code>child</code>.
	 * </p>
	 *
	 * @param child
	 *            the child whose next sibling will be moved
	 * @return the location offset of the next child in pixels
	 */
	int getNextLocationOffset(View child) {
		return 0;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		System.out.println("resp1onse CustomLinearLayout: protected void onLayout(boolean changed, int l, int t, int r, int b) { start void ");
		if (mOrientation == VERTICAL) {
			layoutVertical();
		} else {
			layoutHorizontal();
		}
		System.out.println("resp1onse CustomLinearLayout: protected void onLayout(boolean changed, int l, int t, int r, int b) { end void ");
	}

	/**
	 * Position the children during a layout pass if the orientation of this
	 * LinearLayout is set to {@link #VERTICAL}.
	 *
	 * @see #getOrientation()
	 * @see #setOrientation(int)
	 * @see #onLayout(boolean, int, int, int, int)
	 */
	void layoutVertical() {
		System.out.println("resp1onse CustomLinearLayout: void layoutVertical() { start void ");
		final int paddingLeft = getPaddingLeft();

		int childTop = getPaddingTop();
		int childLeft;

		// Where right end of child should go
		final int width = getRight() - getLeft();
		int childRight = width - getPaddingRight();

		// Space available for child
		int childSpace = width - paddingLeft - getPaddingRight();

		final int count = getVirtualChildCount();

		final int majorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
		final int minorGravity = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;

		if (majorGravity != Gravity.TOP) {
			switch (majorGravity) {
			case Gravity.BOTTOM:
				// mTotalLength contains the padding already, we add the top
				// padding to compensate
				childTop = getBottom() - getTop() + getPaddingTop() - mTotalLength;
				break;

			case Gravity.CENTER_VERTICAL:
				childTop += ((getBottom() - getTop()) - mTotalLength) / 2;
				break;
			}

		}

		for (int i = 0; i < count; i++) {
			final View child = getVirtualChildAt(i);
			if (child == null) {
				childTop += measureNullChild(i);
			} else if (child.getVisibility() != GONE) {
				final int childWidth = child.getMeasuredWidth();
				final int childHeight = child.getMeasuredHeight();

				final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

				int gravity = lp.gravity;
				if (gravity < 0) {
					gravity = minorGravity;
				}

				switch (gravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
				case Gravity.LEFT:
					childLeft = paddingLeft + lp.leftMargin;
					break;

				case Gravity.CENTER_HORIZONTAL:
					childLeft = paddingLeft + ((childSpace - childWidth) / 2) + lp.leftMargin - lp.rightMargin;
					break;

				case Gravity.RIGHT:
					childLeft = childRight - childWidth - lp.rightMargin;
					break;
				default:
					childLeft = paddingLeft;
					break;
				}

				childTop += lp.topMargin;
				setChildFrame(child, childLeft, childTop + getLocationOffset(child), childWidth, childHeight);
				childTop += childHeight + lp.bottomMargin + getNextLocationOffset(child);

				i += getChildrenSkipCount(child, i);
			}
		}
		System.out.println("resp1onse CustomLinearLayout: void layoutVertical() { end void ");
	}

	/**
	 * Position the children during a layout pass if the orientation of this
	 * LinearLayout is set to {@link #HORIZONTAL}.
	 *
	 * @see #getOrientation()
	 * @see #setOrientation(int)
	 * @see #onLayout(boolean, int, int, int, int)
	 */
	void layoutHorizontal() {
		System.out.println("resp1onse CustomLinearLayout: void layoutHorizontal() { start void ");
		final int paddingTop = getPaddingTop();

		int childTop;
		int childLeft = getPaddingLeft();

		// Where bottom of child should go
		final int height = getBottom() - getTop();
		int childBottom = height - getPaddingBottom();

		// Space available for child
		int childSpace = height - paddingTop - getPaddingBottom();

		final int count = getVirtualChildCount();

		final int majorGravity = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
		final int minorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;

		final boolean baselineAligned = mBaselineAligned;

		final int[] maxAscent = mMaxAscent;
		final int[] maxDescent = mMaxDescent;

		if (majorGravity != Gravity.LEFT) {
			switch (majorGravity) {
			case Gravity.RIGHT:
				// mTotalLength contains the padding already, we add the left
				// padding to compensate
				childLeft = getRight() - getLeft() + getPaddingLeft() - mTotalLength;
				break;

			case Gravity.CENTER_HORIZONTAL:
				childLeft += ((getRight() - getLeft()) - mTotalLength) / 2;
				break;
			}
		}

		for (int i = 0; i < count; i++) {
			final View child = getVirtualChildAt(i);

			if (child == null) {
				childLeft += measureNullChild(i);
			} else if (child.getVisibility() != GONE) {
				final int childWidth = child.getMeasuredWidth();
				final int childHeight = child.getMeasuredHeight();
				int childBaseline = -1;

				final CustomLinearLayout.LayoutParams lp = (CustomLinearLayout.LayoutParams) child.getLayoutParams();

				if (baselineAligned && lp.height != LayoutParams.MATCH_PARENT) {
					childBaseline = child.getBaseline();
				}

				int gravity = lp.gravity;
				if (gravity < 0) {
					gravity = minorGravity;
				}

				switch (gravity & Gravity.VERTICAL_GRAVITY_MASK) {
				case Gravity.TOP:
					childTop = paddingTop + lp.topMargin;
					if (childBaseline != -1) {
						childTop += maxAscent[INDEX_TOP] - childBaseline;
					}
					break;

				case Gravity.CENTER_VERTICAL:
					// Removed support for baseline alignment when
					// layout_gravity or
					// gravity == center_vertical. See bug #1038483.
					// Keep the code around if we need to re-enable this feature
					// if (childBaseline != -1) {
					// // Align baselines vertically only if the child is
					// smaller than us
					// if (childSpace - childHeight > 0) {
					// childTop = paddingTop + (childSpace / 2) - childBaseline;
					// } else {
					// childTop = paddingTop + (childSpace - childHeight) / 2;
					// }
					// } else {
					childTop = paddingTop + ((childSpace - childHeight) / 2) + lp.topMargin - lp.bottomMargin;
					break;

				case Gravity.BOTTOM:
					childTop = childBottom - childHeight - lp.bottomMargin;
					if (childBaseline != -1) {
						int descent = child.getMeasuredHeight() - childBaseline;
						childTop -= (maxDescent[INDEX_BOTTOM] - descent);
					}
					break;
				default:
					childTop = paddingTop;
					break;
				}

				childLeft += lp.leftMargin;
				setChildFrame(child, childLeft + getLocationOffset(child), childTop, childWidth, childHeight);
				childLeft += childWidth + lp.rightMargin + getNextLocationOffset(child);

				i += getChildrenSkipCount(child, i);
			}
		}
		System.out.println("resp1onse CustomLinearLayout: void layoutHorizontal() { end void ");
	}

	private void setChildFrame(View child, int left, int top, int width, int height) {
		System.out.println("resp1onse CustomLinearLayout: private void setChildFrame(View child, int left, int top, int width, int height) { start void ");
		child.layout(left, top, left + width, top + height);
		System.out.println("resp1onse CustomLinearLayout: private void setChildFrame(View child, int left, int top, int width, int height) { end void ");
	}

	/**
	 * Should the layout be a column or a row.
	 * 
	 * @param orientation
	 *            Pass HORIZONTAL or VERTICAL. Default value is HORIZONTAL.
	 * 
	 * @attr ref android.R.styleable#LinearLayout_orientation
	 */
	public void setOrientation(int orientation) {
		System.out.println("resp1onse CustomLinearLayout: public void setOrientation(int orientation) { start void ");
		if (mOrientation != orientation) {
			mOrientation = orientation;
			requestLayout();
		}
		System.out.println("resp1onse CustomLinearLayout: public void setOrientation(int orientation) { end void ");
	}

	/**
	 * Returns the current orientation.
	 * 
	 * @return either {@link #HORIZONTAL} or {@link #VERTICAL}
	 */
	public int getOrientation() {
		System.out.println("resp1onse CustomLinearLayout: public int getOrientation() { start return ");
		System.out.println("resp1onse CustomLinearLayout: public int getOrientation() { end return ");
		return mOrientation;
	}

	/**
	 * Describes how the child views are positioned. Defaults to GRAVITY_TOP. If
	 * this layout has a VERTICAL orientation, this controls where all the child
	 * views are placed if there is extra vertical space. If this layout has a
	 * HORIZONTAL orientation, this controls the alignment of the children.
	 * 
	 * @param gravity
	 *            See {@link android.view.Gravity}
	 * 
	 * @attr ref android.R.styleable#LinearLayout_gravity
	 */
	// @android.view.RemotableViewMethod
	public void setGravity(int gravity) {
		System.out.println("resp1onse CustomLinearLayout: public void setGravity(int gravity) { start void ");
		if (mGravity != gravity) {
			if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) {
				gravity |= Gravity.LEFT;
			}

			if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
				gravity |= Gravity.TOP;
			}

			mGravity = gravity;
			requestLayout();
		}
		System.out.println("resp1onse CustomLinearLayout: public void setGravity(int gravity) { end void ");
	}

	// @android.view.RemotableViewMethod
	public void setHorizontalGravity(int horizontalGravity) {
		System.out.println("resp1onse CustomLinearLayout: public void setHorizontalGravity(int horizontalGravity) { start void ");
		final int gravity = horizontalGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
		if ((mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) != gravity) {
			mGravity = (mGravity & ~Gravity.HORIZONTAL_GRAVITY_MASK) | gravity;
			requestLayout();
		}
		System.out.println("resp1onse CustomLinearLayout: public void setHorizontalGravity(int horizontalGravity) { end void ");
	}

	// @android.view.RemotableViewMethod
	public void setVerticalGravity(int verticalGravity) {
		System.out.println("resp1onse CustomLinearLayout: public void setVerticalGravity(int verticalGravity) { start void ");
		final int gravity = verticalGravity & Gravity.VERTICAL_GRAVITY_MASK;
		if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != gravity) {
			mGravity = (mGravity & ~Gravity.VERTICAL_GRAVITY_MASK) | gravity;
			requestLayout();
		}
		System.out.println("resp1onse CustomLinearLayout: public void setVerticalGravity(int verticalGravity) { end void ");
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams generateLayoutParams(AttributeSet attrs) { start return ");
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams generateLayoutParams(AttributeSet attrs) { end return ");
		return new CustomLinearLayout.LayoutParams(getContext(), attrs);
	}

	/**
	 * Returns a set of layout parameters with a width of
	 * {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT} and a height of
	 * {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT} when the
	 * layout's orientation is {@link #VERTICAL}. When the orientation is
	 * {@link #HORIZONTAL}, the width is set to
	 * {@link LayoutParams#WRAP_CONTENT} and the height to
	 * {@link LayoutParams#WRAP_CONTENT}.
	 */
	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		System.out.println("resp1onse CustomLinearLayout: protected LayoutParams generateDefaultLayoutParams() { start return ");
		if (mOrientation == HORIZONTAL) {
			return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		} else if (mOrientation == VERTICAL) {
			return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
		System.out.println("resp1onse CustomLinearLayout: protected LayoutParams generateDefaultLayoutParams() { end return  =1");
		return null;
	}

	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		System.out.println("resp1onse CustomLinearLayout: protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomLinearLayout: protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { end return ");
		return new LayoutParams(p);
	}

	// Override to allow type-checking of LayoutParams.
	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		System.out.println("resp1onse CustomLinearLayout: protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { start return ");
		System.out.println("resp1onse CustomLinearLayout: protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { end return ");
		return p instanceof CustomLinearLayout.LayoutParams;
	}

	/**
	 * Per-child layout information associated with ViewLinearLayout.
	 * 
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_weight
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_gravity
	 */
	public static class LayoutParams extends ViewGroup.MarginLayoutParams {
		/**
		 * Indicates how much of the extra space in the LinearLayout will be
		 * allocated to the view associated with these LayoutParams. Specify 0
		 * if the view should not be stretched. Otherwise the extra pixels will
		 * be pro-rated among all views whose weight is greater than 0.
		 */
		@ViewDebug.ExportedProperty(category = "layout")
		public float weight;

		/**
		 * Gravity for the view associated with these LayoutParams.
		 *
		 * @see android.view.Gravity
		 */
		@ViewDebug.ExportedProperty(category = "layout", mapping = { @ViewDebug.IntToString(from = -1, to = "NONE"),
				@ViewDebug.IntToString(from = Gravity.NO_GRAVITY, to = "NONE"), @ViewDebug.IntToString(from = Gravity.TOP, to = "TOP"),
				@ViewDebug.IntToString(from = Gravity.BOTTOM, to = "BOTTOM"), @ViewDebug.IntToString(from = Gravity.LEFT, to = "LEFT"),
				@ViewDebug.IntToString(from = Gravity.RIGHT, to = "RIGHT"), @ViewDebug.IntToString(from = Gravity.CENTER_VERTICAL, to = "CENTER_VERTICAL"),
				@ViewDebug.IntToString(from = Gravity.FILL_VERTICAL, to = "FILL_VERTICAL"),
				@ViewDebug.IntToString(from = Gravity.CENTER_HORIZONTAL, to = "CENTER_HORIZONTAL"),
				@ViewDebug.IntToString(from = Gravity.FILL_HORIZONTAL, to = "FILL_HORIZONTAL"), @ViewDebug.IntToString(from = Gravity.CENTER, to = "CENTER"),
				@ViewDebug.IntToString(from = Gravity.FILL, to = "FILL") })
		public int gravity = -1;

		/**
		 * {@inheritDoc}
		 */
		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(Context c, AttributeSet attrs) { start constructor ");
			TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout_Layout);

			weight = a.getFloat(R.styleable.CustomLinearLayout_Layout_layout_weight, 0);
			gravity = a.getInt(R.styleable.CustomLinearLayout_Layout_layout_gravity, -1);

			a.recycle();
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(Context c, AttributeSet attrs) { end constructor ");
		}

		/**
		 * {@inheritDoc}
		 */
		public LayoutParams(int width, int height) {
			super(width, height);
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(int width, int height) { start constructor ");
			weight = 0;
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(int width, int height) { end constructor ");
		}

		/**
		 * Creates a new set of layout parameters with the specified width,
		 * height and weight.
		 *
		 * @param width
		 *            the width, either {@link #MATCH_PARENT},
		 *            {@link #WRAP_CONTENT} or a fixed size in pixels
		 * @param height
		 *            the height, either {@link #MATCH_PARENT},
		 *            {@link #WRAP_CONTENT} or a fixed size in pixels
		 * @param weight
		 *            the weight
		 */
		public LayoutParams(int width, int height, float weight) {
			super(width, height);
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(int width, int height, float weight) { start constructor ");
			this.weight = weight;
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(int width, int height, float weight) { end constructor ");
		}

		/**
		 * {@inheritDoc}
		 */
		public LayoutParams(ViewGroup.LayoutParams p) {
			super(p);
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(ViewGroup.LayoutParams p) { start constructor ");
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(ViewGroup.LayoutParams p) { end constructor ");
		}

		/**
		 * {@inheritDoc}
		 */
		public LayoutParams(MarginLayoutParams source) {
			super(source);
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(MarginLayoutParams source) { start constructor ");
		System.out.println("resp1onse CustomLinearLayout: public LayoutParams(MarginLayoutParams source) { end constructor ");
		}

		public String debug(String output) {
		System.out.println("resp1onse CustomLinearLayout: public String debug(String output) { start return ");
		System.out.println("resp1onse CustomLinearLayout: public String debug(String output) { end return ");
			return output + "CustomLinearLayout.LayoutParams={width=" + sizeToString(width) + ", height=" + sizeToString(height) + " weight=" + weight + "}";
		}

		protected static String sizeToString(int size) {
		System.out.println("resp1onse CustomLinearLayout: protected static String sizeToString(int size) { start return ");
			if (size == WRAP_CONTENT) {
				return "wrap-content";
			}
			if (size == MATCH_PARENT) {
				return "match-parent";
			}
		System.out.println("resp1onse CustomLinearLayout: protected static String sizeToString(int size) { end return  =1");
			return String.valueOf(size);
		}
	}

}
