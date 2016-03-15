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

//android 工程路径
def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"

def src = "StoreCategory"
def target = "StoreProduct"

//逻辑代码路径
def mvp_logic_path = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\ActivityRecyclerViewAndLoadStatus.groovy"

def mvp = new MvpFile(packagePath, src, target, mvp_logic_path)
mvp.conver()
mvp.logFileLogic()
//mvp.generateMode()
