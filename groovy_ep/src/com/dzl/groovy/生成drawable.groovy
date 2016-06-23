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

def path = packagePath + "\\" + "bg_menu_detail_back" + ".xml"
def select = ""
def normal = ""
def corners = "96"
def solid = "#ffffff"

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableImage(path, select, normal)

//resource.generateDrawableCorners(path, solid, corners)

def solid_pressed = "#99ffffff"
def solid_normal = "#16ffffff"
resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

