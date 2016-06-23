package com.dzl.groovy

/**
 * 
 * 批量生成 jfinal controller view model文件
 * 
 * jfinal_project_path ： 工程路径
 * src ：原mvc主题名
 * target ：目标mvc主题名
 * 
 */

//jfinal 工程路径
def jfinal_project_path = "D:\\baiduYun\\dzl_github\\yi\\yi"

def src = "Department"
def target = "Announcement"
def src_title = "部门"
def target_title = "公告"
def relpaceFile = false
def array = []

def i = 0
def src_lower = src[0].toLowerCase() + src[1..-1]

array[i++] = "${jfinal_project_path}\\src\\com\\yi\\controller\\${src}Controller.java"
array[i++] = "${jfinal_project_path}\\src\\com\\yi\\model\\${src}.java"
array[i++] = "${jfinal_project_path}\\WebContent\\${src_lower}\\${src_lower}.html"
array[i++] = "${jfinal_project_path}\\WebContent\\${src_lower}\\edit.html"

array.each {
	copyFile(it, src, target, src_title, target_title)
}

private copyFile(pathname, src, target, src_title, target_title, relpace = false) {

	def file = new File(pathname)
	if (!file.exists()) {
		return
	}

	def src_lower = src[0].toLowerCase() + (src.size() > 0 ? src.substring(1) : '')
	def target_lower = target[0].toLowerCase() + (target.size() > 0 ? target.substring(1) : '')

	def mvp_view_target = pathname.replaceAll(src, target).replace(src_lower, target_lower)

	def target_file = new File(mvp_view_target)

	if (!target_file.getParentFile().exists()) {
		target_file.getParentFile().mkdirs();
	}

	println ((target_file.exists() ? "exists\t" : "no_exists\t") + mvp_view_target)

	try {
		target_file.write(file.getText().replaceAll(src, target).replaceAll(src_lower, target_lower).replace(src_title, target_title))
	} catch (Exception e) {
		e.printStackTrace()
	}



}




