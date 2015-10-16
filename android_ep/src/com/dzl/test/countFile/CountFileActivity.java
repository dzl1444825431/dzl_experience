package com.dzl.test.countFile;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Files.FileColumns;
import android.widget.TextView;

public class CountFileActivity extends Activity {

	TextView tv;
	String volumeName = "external";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		// query database
		Uri uri = Files.getContentUri(volumeName);
        refreshMediaCategory(FileCategory.Apk, uri);
        setContentView(tv);
        
        Cursor cursor = query();
        if (cursor != null) {
			while (cursor.moveToNext()) {
				System.out.println("resp1onse : " + cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
			}
		}
        
	}
	
	private boolean refreshMediaCategory(FileCategory fc, Uri uri) {
        String[] columns = new String[] {
                "COUNT(*)", "SUM(_size)"
        };
        tv.setText("无");
        Cursor c = getContentResolver().query(uri, columns, buildSelectionByCategory(fc), null, null);
        if (c == null) {
        	System.out.println("resp1onse : fail to query uri:" + uri);
            return false;
        }

        if (c.moveToNext()) {
        	tv.setText("count:" + c.getLong(0) + " size:" + c.getLong(1));
        	tv.setTextSize(36f);
            System.out.println("resp1onse : Retrieved " + fc.name() + " info >>> count:" + c.getLong(0) + " size:" + c.getLong(1));
            c.close();
            return true;
        }

        return false;
    }
	
	private String buildSelectionByCategory(FileCategory cat) {
        String selection = null;
        switch (cat) {
            case Theme:
                selection = FileColumns.DATA + " LIKE '%.mtz'";
                break;
//            case Doc:
//                selection = buildDocSelection();
//                break;
//            case Zip:
//                selection = "(" + FileColumns.MIME_TYPE + " == '" + Util.sZipFileMimeType + "')";
//                break;
            case Apk:
                selection = FileColumns.DATA + " LIKE '%.apk'";
                break;
            default:
                selection = null;
        }
        return selection;
    }
	
	
	
	public enum FileCategory {
        All, Music, Video, Picture, Theme, Doc, Zip, Apk, Custom, Other, Favorite
    }
	
	public Cursor query(/*FileCategory fc, SortMethod sort*/) {
		System.out.println("resp1onse FileCategoryHelper: public Cursor query(FileCategory fc, SortMethod sort) { start return ");
//        Uri uri = getContentUriByCategory(fc);
//        String selection = buildSelectionByCategory(fc);
//        String sortOrder = buildSortOrder(sort);
		
        Uri uri = Files.getContentUri(volumeName);
        String selection = "_data LIKE '%.apk'";
        String sortOrder = "title asc";

        if (uri == null) {
        	System.out.println("resp1onse : invalid uri, category:  APK" );
        	System.out.println("resp1onse FileCategoryHelper: public Cursor query(FileCategory fc, SortMethod sort) { end return if ");
            return null;
        }

        String[] columns = new String[] {
                FileColumns._ID, FileColumns.DATA, FileColumns.SIZE, FileColumns.DATE_MODIFIED
        };

		System.out.println("resp1onse FileCategoryHelper: public Cursor query(FileCategory fc, SortMethod sort) { end return  =1");
        return this.getContentResolver().query(uri, columns, selection, null, sortOrder);
    }

}
