package com.dzl.groovy

/**
 * 1.生成mvp 三个文件 view model presenter 但不嵌入activity | fragment
 * 2.print activity | fragment所可能需要代码
 *
 * packagePath ： 工程路径
 * src ：原mvp主题名
 * target ：目标mvp主题名
 *
 *
 */
class MvpFile {
	
	
	public MvpFile(Object packagePath, Object src, Object target, Object mvp_logic_path) {
		super();
		this.packagePath = packagePath;
		this.src = src;
		this.target = target;
		this.mvp_logic_path = mvp_logic_path;
	}

	//android 工程路径
	def packagePath

	def src
	def target

	//逻辑代码路径
	def mvp_logic_path

	def conver(){
		//android 工程包名
		def javaPackage = packagePath.substring(packagePath.lastIndexOf("\\", packagePath.lastIndexOf("\\", packagePath.lastIndexOf("\\") - 1) - 1) + 1).replaceAll("\\\\",".")
		def mvp_view_src
		def mvp_view_target

		/**1. android mvp File Copy mvp中的view presenter*/
		mvp_view_src = packagePath + "\\presenter\\view\\I" + src + "View.java"
		mvp_view_target = packagePath + "\\presenter\\view\\I" + target + "View.java"
		new File(mvp_view_target).write(new File(mvp_view_src).getText().replaceAll(src, target))


		mvp_view_src = packagePath + "\\presenter\\" + src + "Presenter.java"
		mvp_view_target = packagePath + "\\presenter\\" + target + "Presenter.java"
		new File(mvp_view_target).write(new File(mvp_view_src).getText().replaceAll(src, target))


		/**2. Print recycleView swipeLayout load logic逻辑*/
		println new File(mvp_logic_path).getText()


		def model = """
package ${javaPackage}.model;

import ${javaPackage}.BaseEntity;

/**
		 * @author dzl 2016年01月09日
		 */
public class ${target} extends BaseEntity {

	/**
			 *
			 */
	private static final long serialVersionUID = 1L;

}


"""
		mvp_view_target = packagePath + "\\model\\" + target + ".java"
		new File(mvp_view_target).write(model)
	}




}