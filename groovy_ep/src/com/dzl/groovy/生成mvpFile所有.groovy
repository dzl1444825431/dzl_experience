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
def packagePath = "D:\\baiduYun\\dzl_github\\yizego\\yizegou\\app\\src\\main\\java\\com\\motor\\yizegou"

def src = "FavoriteShop"
def target = "TestMvp"
def relpaceFile = false
def array = []

array[0] = "${packagePath}\\activity\\${src}Activity.java"
array[1] = "${packagePath}\\adapter\\${src}Adapter.java"
array[2] = "${packagePath}\\model\\${src}.java"
array[3] = "${packagePath}\\presenter\\${src}Presenter.java"
array[4] = "${packagePath}\\presenter\\view\\I${src}View.java"

array.each {
	copyFile(it, src, target)
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

	if (!target_file.exists() && !relpace) {
		try {
			target_file.write(file.getText().replaceAll(src, target).replaceAll(src_lower, target_lower))
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	println mvp_view_target


}




