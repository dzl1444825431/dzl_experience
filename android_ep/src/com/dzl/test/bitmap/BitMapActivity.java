package com.dzl.test.bitmap;

import java.io.InputStream;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class BitMapActivity extends BaseActivity {

	private ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.bitmap_activity);
		
		imgView = (ImageView) findViewById(R.id.imageView2);

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory
				.decodeResource(getResources(), R.raw.ic_launcher333, options);
		int imageHeight = options.outHeight;
		int imageWidth = options.outWidth;
		String imageType = options.outMimeType;

		System.out.println("respone : " + imageWidth + " " + imageHeight + " "
				+ imageType);
//		calculateInSampleSize(options, 50, 50);
		
		
		
		Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), R.raw.ic_launcher333, 50 , 50);
		
		imgView.setImageBitmap(bitmap);
		
		final int memClass = ((ActivityManager) getSystemService(
	            Context.ACTIVITY_SERVICE)).getMemoryClass();

		
	    // Use 1/8th of the available memory for this memory cache.
	    final int cacheSize = 1024 * 1024 * memClass / 8;
	    System.out.println("respone : " + memClass + " " + cacheSize);
		
	    getBitmap();
	}
	
	/**
	 * bitmap 创建方式一 ： 使用 BitmapDrawable
	 * bitmap 创建方式二 ： 使用 BitmapFactory
	 * bitmap 创建方式三 ： 使用 源Bitmap对象（旧建新）
	 */
	void getBitmap(){
		InputStream is=getResources().openRawResource(R.drawable.ic_launcher2);  // 读取资源文件获取输入流 
		BitmapDrawable bmpDraw=new BitmapDrawable(is);   
		Bitmap bmp=bmpDraw.getBitmap(); 
		
		System.out.println("respone : " + bmp.getWidth() + " " + bmp.getHeight());
//		或者： 
		BitmapDrawable bmpDraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.ic_launcher);   
		Bitmap bmp2=bmpDraw2.getBitmap(); 
		System.out.println("respone : " + bmp2.getWidth() + " " + bmp2.getHeight());
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
//	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
	    options.inSampleSize = -2;
	    
	    System.out.println("respone : options = " + options.outHeight + " " + options.outWidth + " "
				+ options.outMimeType + " " + options.inSampleSize);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}

}
