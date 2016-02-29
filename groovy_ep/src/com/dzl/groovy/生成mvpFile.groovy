package com.dzl.groovy


//android 工程路径
def packagePath = "D:\\baiduYun\\dzl_github\\app1\\yiqiaoquancheng\\src\\com\\yiqiao\\yiqiaoquancheng"

//逻辑代码路径
def mvp_logic_path = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\ActivityRecyclerViewAndLoadStatus.groovy"

//android 工程包名
def javaPackage = packagePath.substring(packagePath.lastIndexOf("\\", packagePath.lastIndexOf("\\", packagePath.lastIndexOf("\\") - 1) - 1) + 1).replaceAll("\\\\",".")

def src = "Seller"
def target = "SellerProduct"



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
