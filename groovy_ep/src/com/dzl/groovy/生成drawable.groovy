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

def path = packagePath + "\\" + "bg_button_yellow" + ".xml"
def select = ""
def normal = ""
def corners = "80"
def solid = "#ff36801c"

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableImage(path, select, normal)

//resource.generateDrawableCorners(path, solid, corners)

def solid_pressed = "#ffd9d9d9"
def solid_normal = "#fffd8d00"
resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

