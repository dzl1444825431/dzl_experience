package com.dzl.test.matrix2;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class MatrixActivity extends BaseActivity {
	
	private static final String LOG_TAG = "MainActivity";
	private Button mBtnRotate = null;
	private Button mBtnSkew = null;
	private Button mBtnImage = null;
	private Button mBtnReset = null;
	private ImageView mImgArrowLeft = null;
	private float mDegreeCount = 0F;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Drawable drawable;
		
		RectF rectF;
		Rect rect;

		setContentView(R.layout.activity_matrix);
		
		Matrix matrix = new Matrix();
		System.out.println("resp1onse : " + matrix);
		
		matrix.setRotate(90f);
		System.out.println("resp1onse : " + matrix);

		mImgArrowLeft = (ImageView) findViewById(R.id.img);

		mBtnRotate = (Button) findViewById(R.id.rotate);
		mBtnRotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int pivot_x = mImgArrowLeft.getWidth() / 2;
				int pivot_y = mImgArrowLeft.getHeight() / 2;
				Matrix matrix = new Matrix();
				matrix.setTranslate(-pivot_x, -pivot_x);
				mDegreeCount += 30F;
				matrix.postRotate(mDegreeCount);
				matrix.postTranslate(pivot_x, pivot_y);
				Log.i(LOG_TAG, matrix.toString());
				mImgArrowLeft.setImageMatrix(matrix);
			}
		});

		mBtnSkew = (Button) findViewById(R.id.skew);
		mBtnSkew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Matrix matrix = new Matrix();
				matrix.setSkew(0.5F, 0.5F);
				Log.i(LOG_TAG, matrix.toString());
				mImgArrowLeft.setImageMatrix(matrix);
			}
		});

		mBtnImage = (Button) findViewById(R.id.image);
		mBtnImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Matrix matrix = new Matrix();
				float center_y = mImgArrowLeft.getHeight() / 2;
				matrix.setTranslate(0, -center_y);
				matrix.postScale(1.0F, -1.0F);
				matrix.postTranslate(0, center_y);
				Log.i(LOG_TAG, matrix.toString());
				mImgArrowLeft.setImageMatrix(matrix);
			}
		});

		mBtnReset = (Button) findViewById(R.id.reset);
		mBtnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mImgArrowLeft.setImageMatrix(null);
				mDegreeCount = 0F;
			}
		});
	}

}
