package com.优化;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CopyFile {
	static Set<String> set = new HashSet<String>();

	public static void main(String[] args) {

		 
		 
		set.add("bar_menu");
		 
		set.add("abs__action_bar_home");
		 
		set.add("refresh_head");
		set.add("load_more");
		 
		set.add("main_menu");
		 
		set.add("fragment_listview");
		 
		set.add("fragment_listview");
		 
		set.add("main_menu");
		 
		set.add("fragment_category_bar");
		 
		set.add("activitybar");
		 
		set.add("fragment_actionbar");
		 
		set.add("fragment_category_bar");
		 
		set.add("fragment_actionbar");
		 
		set.add("fragment_listview");
		 
		set.add("fragment_listview");
		 
		set.add("refresh_head");
		set.add("load_more");
		 
		set.add("refresh_head");
		set.add("load_more");
		 
		set.add("activitybar");
		 
		 
		set.add("fragment_actionbar");
		 
		 

		 
		set.add("activity_about");
		 
		set.add("activity_app_comment");
		 
		set.add("fragment_appdetail_picture");
		 
		set.add("activity_app_details");
		set.add("pager_app_details1");
		set.add("pager_app_details2");
		set.add("pager_app_details3");
		 
		set.add("activity_app_search");
		set.add("activity_app_search_item");
		 
		set.add("activity_categorydetailed");
		set.add("activity_details_text");
		 
		set.add("activity_downloadlist");
		 
		set.add("activity_feedback"); 
		 
		set.add("activity_home_app_special_category");
		set.add("listviewfootter, null)");
		 
		set.add("activity_home_app_special_detailed");
		set.add("listviewfootter");
		set.add("activity_home_app_special_detailed_item");
		 
		set.add("activity_login");
		 
		set.add("activity_main");
		 
		set.add("activity_register"); 
		 
		set.add("activity_setting");
		 
		 
		 
		set.add("activity_start");
		 
		 
		set.add("activity_subsidyguide");
		 
		set.add("activity_uc_msg");
		set.add("msg_listview");
		set.add("msg_listview");
		 
		set.add("activity_updatepassword");
		 
		set.add("activity_user_agreement");
		 
		 
		set.add("pager_app_details2_item");
		 
		set.add("pager_app_details3_item");
		 
		set.add("pager_app_details3_item");
		 
		set.add("activity_categorydetailed_listview_item");
		 
		set.add("fragment_category_griview_item");
		set.add("activity_category_text");
		set.add("activity_details_text");
		 
		set.add("activity_downloadlist_downloadingtab");
		set.add("activity_downloadlist_downloadfinishtab");
		set.add("activity_download_item");
		set.add("dialog_download_delete");
		set.add("dialog_download_delete");
		 
		set.add("fragment_folder_item");
		 
		set.add("fragment_home_list_common");
		 
		set.add("activity_home_app_special_category_item");
		 
		set.add("fragment_home_list_common");
		 
		set.add("fragment_task_list_banner_item");
		 
		set.add("fragment_home_gridview_item");
		 
		set.add("fragment_home_listview_item");
		 
		set.add("fragment_home_head_viewpager_item");
		 
		set.add("fragment_home_list_item1");
		 
		set.add("fragment_home_list_item1");
		 
		set.add("fragment_home_list_item1");
		 
		set.add("fragment_home_list_common"); 
		 
		set.add("activity_uc_msg_item");
		 
		set.add("activity_app_search_result_item");
		 
		set.add("activity_setting_item");
		 
		set.add("popupwindow_log_item");
		 
		 
		set.add("notification_item");
		 
		 
		set.add("pager_app_details1");
		set.add("activity_app_details_item");
		 
		set.add("pager_app_details2");
		 
		set.add("pager_app_details3");
		 
		set.add("fragment_home_app_subsidy"); 
		 
		set.add("fragment_category"); 
		 
		set.add("fragment_folder");
		 
		set.add("fragment_home_app");
		set.add("listviewfootter, null)");
		set.add("fragment_home_app_titl");
		 
		set.add("activity_home_viewpagertab");
		set.add("fragment_home_tab_indicator");
		 
		set.add("fragment_home_listview,container");
		set.add("fragment_home_head_viewpager");
		 
		set.add("fragment_home");
		 
		set.add("fragment_listview");
		 
		set.add("fragment_home_app_rankinglist_header");
		 
		set.add("fragment_home_app_subsidy_header");
		 
		set.add("fragment_my_home"); 
		 
		 
		set.add("progress_dialog_layout");
		set.add("actionbar");

//		String e = "comm_background_fill_gray";

		//set.add(e);
//		for (String str : set) {
//			copy(str);
//			
//		}
		
//		compareNoFile();
		//resp1onse : [fragment_home_listview,container, abs__action_bar_home, listviewfootter, null), activitybar]
	}

	static void compareNoFile() {
		File file = new File("D:\\work\\android\\sources\\app\\res\\layout");
		File[] files = file.listFiles();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			for (File file2 : files) {
				if (next.equals(file2.getName().replace(".xml", ""))) {
					iterator.remove();
					break;
				}
			}
			
		}
		System.out.println("resp1onse : " + set);
	}

	static String path = "D:\\work\\android\\sources\\app\\res\\drawable\\";
	static String path2 = "D:\\work\\drawable\\";

	static void copy(String str) {
		File aTargetFile = new File(path + str + ".xml");
		if (aTargetFile.exists()) {
			System.out.println("resp1onse :  have file = "
					+ aTargetFile.getName());
			return;
		}

		File aSourceFile = new File(path2 + str + ".xml");
		
		if (!aSourceFile.exists()) {
			System.out.println("resp1onse :  no file = "
					+ aSourceFile.getName());
			return;
		}

		copyWithFileStream(aSourceFile, aTargetFile);
	}

	static void copyWithFileStream(File aSourceFile, File aTargetFile) {

		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(aSourceFile);
			fos = new FileOutputStream(aTargetFile);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);

			byte[] buffer = new byte[1024 * 1024 * 8];
			int len = 0;
			while ((len = bis.read(buffer)) != -1) {
				System.out.println("resp1onse : " + len);
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIO(bos);
			closeIO(bis);
			closeIO(fos);
			closeIO(fis);
		}
	}

	static void closeIO(Closeable closeable) {

		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			closeable = null;

		}
	}

}
