package com.dzl.test.gridview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * 注意设置 一定大小的padding 否则边缘线不起作用，显示不能正确显示
 */
public class CustomGridView extends GridView {
	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomGridView(Context context) {
		super(context);
	}

	public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	/**
	 * 注意设置 一定大小的padding 否则边缘线不起作用，显示不能正确显示
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		View child = getChildAt(0);
		int column = getWidth() / child.getWidth();// 计算出一共有多少列，假设有3列
		int childCount = getChildCount();// 子view的总数
		// System.out.println("子view的总数childCount==" + childCount);
		Paint paint;// 画笔
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.parseColor("#ffd3dde6"));// 设置画笔的颜色

		for (int i = 0; i < childCount; i++) {// 遍历子view
			View cellView = getChildAt(i);// 获取子view
			if (i < column) {// 第一行  顶部画横线
				System.out.println("resp1onse : 1 = " + i);
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), paint);
			}
			if (i % column == 0) {// 第一列  画左则竖线
				System.out.println("resp1onse : 2 = " + i);
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), paint);
			}
			if ((i + 1) % column == 0) {// 最后一列  画底线、右竖线
				System.out.println("resp1onse : 3 = " + i);
				// 画子view底部横线
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
						paint);
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(),
						paint);
			} else if ((i + 1) > (childCount - (childCount % column))) {// 如果view是最后一行
				System.out.println("resp1onse : 4 = " + i);
				// 画子view的右边竖线
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(),
						paint);
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
						paint);
			} else {// 如果view不是最后一行
				System.out.println("resp1onse : 5 = " + i);
				// 画子view的右边竖线
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(),
						paint);
				// 画子view的底部横线
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
						paint);
			}
		}
	}

}
