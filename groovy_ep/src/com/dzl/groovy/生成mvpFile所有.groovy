package com.dzl.groovy

/**
 * 
 * 批量生成 activity adapter model view presenter文件
 * 
 * packagePath ： 工程路径
 * src ：原mvp主题名
 * target ：目标mvp主题名
 * 
 */

//android 工程路径
//def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def packagePath = "D:\\baiduYun\\dzl_github\\yizego\\yizego\\app\\src\\main\\java\\com\\yizego\\areagou"
packagePath = "D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\java\\com\\dzl1\\nongye"

def src = "OrderDetail"
def target = "OrderCommit"
def relpaceFile = true
def array = []

def i = 0
//array[i++] = "${packagePath}\\fragment\\${src}Fragment.java"
array[i++] = "${packagePath}\\activity\\${src}Activity.java"
array[i++] = "${packagePath}\\adapter\\${src}Adapter.java"
//array[i++] = "${packagePath}\\model\\${src}.java"
array[i++] = "${packagePath}\\presenter\\${src}Presenter.java"
array[i++] = "${packagePath}\\presenter\\view\\I${src}View.java"

array.each {
	copyFile(it, src, target, relpaceFile)
}

private copyFile(pathname, src, target, relpace = false) {

	def file = new File(pathname)
	if (!file.exists()) {
		return
	}

	def src_lower = src[0].toLowerCase() + (src.size() > 0 ? src.substring(1) : '')
	def target_lower = target[0].toLowerCase() + (target.size() > 0 ? target.substring(1) : '')

	def mvp_view_target = pathname.replaceAll(src, target)

	def target_file = new File(mvp_view_target)

	try {
		target_file.write(file.getText().replaceAll(src, target).replaceAll(src_lower, target_lower))
	} catch (Exception e) {
		e.printStackTrace()
	}
	println mvp_view_target


}




