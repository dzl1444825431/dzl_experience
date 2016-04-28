package com.dzl.groovy

import com.dzl.groovy.drawable.Resources

/**
 * 
 * 批量生成 drawable
 * 
 */

//android 工程路径
//def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def packagePath = "D:\\baiduYun\\dzl_github\\jindouyun\\jindouyun\\app\\src\\main\\res\\drawable"

def path = packagePath + "\\" + "bg_edittext_search" + ".xml"
def select = ""
def normal = ""
def corners = "10"
def solid = "#ffffff"

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableImage(path, select, normal)

resource.generateDrawableCorners(path, solid, corners)

def solid_pressed = "#848484"
def solid_normal = "#ffffff"
//resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

