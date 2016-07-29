package com.dzl.groovy

import com.dzl.groovy.drawable.Resources

/**
 * 
 * 批量生成 drawable
 * 
 */

//android 工程路径
def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
packagePath = "D:\\baiduYun\\dzl_github\\yizego\\yizego\\app\\src\\main\\res\\drawable"
//packagePath = "D:\\work\\haoyangde\\haoyangde\\res\\drawable"
packagePath = "D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\res\\drawable"
packagePath = "D:\\baiduYun\\dzl_github\\yizego\\print\\app\\src\\main\\res\\drawable"

def path = packagePath + "\\" + "bg_circle_gray_24" + ".xml"
def select = ""
def normal = ""
def corners = "60"
def solid = "#ff36801c"

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableImage(path, select, normal)

//resource.generateDrawableCorners(path, solid, corners)

def solid_pressed = "#ffd9d9d9"
def solid_normal = "#ffffffff"
resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

