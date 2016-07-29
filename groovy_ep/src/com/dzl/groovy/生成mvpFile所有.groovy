package com.dzl.groovy

import com.dzl.groovy.android.DzlLibUtil;

/**
 * 
 * 批量生成 activity adapter model view presenter文件
 * 
 * srcPath ： 工程路径
 * src ：原mvp主题名
 * target ：目标mvp主题名
 * 
 */

//android 工程路径
//def srcPath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def srcPath = "D:\\baiduYun\\dzl_github\\yizego\\yizego\\app\\src\\main\\java\\com\\yizego\\areagou"
def targetPath = "D:\\baiduYun\\dzl_github\\yizego\\print\\app\\src\\main\\java\\com\\dzl\\print"

srcPath = "D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\java\\com\\dzl1\\nongye"
srcPath = "D:\\baiduYun\\dzl_github\\yizego\\print\\app\\src\\main\\java\\com\\dzl\\print"

def src = "AdBanner"
def target = "AdBanner"

def array = []
targetPath = null

def i = 0
//array[i++] = "${srcPath}\\fragment\\${src}Fragment.java"
//array[i++] = "${srcPath}\\activity\\${src}Activity.java"
array[i++] = "${srcPath}\\adapter\\${src}Adapter.java"
//array[i++] = "${srcPath}\\model\\${src}.java"
array[i++] = "${srcPath}\\presenter\\${src}Presenter.java"
array[i++] = "${srcPath}\\presenter\\view\\I${src}View.java"

array.each {
	copyFile(it, src, target, srcPath, targetPath)
}

private copyFile(srcPath, src, target, srcPath2 = null, targetPath = null) {

	def file = new File(srcPath)
	if (!file.exists()) {
		return
	}

	def src_lower = src[0].toLowerCase() + (src.size() > 0 ? src.substring(1) : '')
	def target_lower = target[0].toLowerCase() + (target.size() > 0 ? target.substring(1) : '')

	def mvp_view_target = srcPath.replaceAll(src, target)
	
	if (targetPath && srcPath2) {
		mvp_view_target = mvp_view_target.replace(srcPath2, targetPath)
	}

	File target_file = new File(mvp_view_target)

	try {
		
		if (!target_file.getParentFile().exists()) {
			target_file.getParentFile().mkdirs()
		}
		
		if (targetPath && srcPath2) {
			
			String p1 = srcPath2.toString().substring(DzlLibUtil.getLastIndex(srcPath2, '\\', 3) + 1).replaceAll('\\\\','.')
			String p2 = targetPath.toString().substring(DzlLibUtil.getLastIndex(targetPath, '\\', 3) + 1).replaceAll('\\\\','.')
			println ("${p1}  ${p2}")
			
			target_file.write(file.getText().replaceAll(src, target).replaceAll(src_lower, target_lower).replaceAll(p1, p2))
		} else {
			target_file.write(file.getText().replaceAll(src, target).replaceAll(src_lower, target_lower))
		}
		
	} catch (Exception e) {
		e.printStackTrace()
	}
	println mvp_view_target


}






