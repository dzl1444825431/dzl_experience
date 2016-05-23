package com.dzl.groovy

import com.dzl.groovy.drawable.Resources

/**
 * 
 * 批量生成 drawable
 * 
 */

//android 工程路径
//def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def packagePath = "D:\\work\\haoyangde\\haoyangde\\res\\drawable"

def path = packagePath + "\\" + "bg_button_cancel_order" + ".xml"
def select = ""
def normal = ""
def corners = "40"
def solid = "#ffffff"

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
//resource.generateDrawableColor(path, select, normal)
//resource.generateDrawableColorShape(path,select, normal)
//resource.generateDrawableImage(path, select, normal)

//resource.generateDrawableCorners(path, solid, corners)

def solid_pressed = "#6a6a6a"
def solid_normal = "#929292"
resource.generateDrawableColorUsShape(path,solid_pressed, solid_normal, corners)

