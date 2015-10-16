//package com.dzl.test.typeArray;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.widget.Button;
//
//import com.dzl.test.R;
//
//public class TypeArrayView extends Button {
//
//	/**
//	 * 
//	 */
//
//	public TypeArrayView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//
//		TypedArray typedArray = context.obtainStyledAttributes(attrs,
//				R.styleable.custon_button);
//		float dimension = typedArray.getDimension(
//				R.styleable.custon_button_textSize2,1);
//		setTextSize(dimension);
//		typedArray.recycle();
//		
//	}
//
//}