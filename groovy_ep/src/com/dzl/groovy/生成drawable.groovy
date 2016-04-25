package com.dzl.groovy

import com.dzl.groovy.drawable.Resources

/**
 * 
 * 批量生成 drawable
 * 
 */

//android 工程路径
//def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def packagePath = "D:\\baiduYun\\dzl_github\\yizego\\yizegou\\app\\src\\main\\res\\drawable"

def path = packagePath + "\\" + "bg_click_white_gray" + ".xml"
def select = ""
def normal = ""
def corners = "10"
def solid = ""

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableCorners(path, solid)
//resource.generateDrawableImage(path, select, normal)

def solid_pressed = "#d9d9d9"
def solid_normal = "#ffffff"
resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

