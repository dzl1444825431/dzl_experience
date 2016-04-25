package com.dzl.groovy

import com.dzl.groovy.drawable.Resources

/**
 * 
 * 批量生成 drawable
 * 
 */

//android 工程路径
//def packagePath = "D:\\work\\haoyangde\\haoyangde\\src\\com\\zhs\\haoyangde"
def packagePath = "D:\\baiduYun\\dzl_github\\yizego\\yizegou\\app\\src\\main\\java\\com\\motor\\yizegou"

def path = ""
def select = ""
def normal = ""
def corners = ""
def solid = ""

def resource = new Resources()

//android:textColor="@drawable/bg_tab_frag_home_tx"
resource.generateDrawableColor(path, select, normal)
resource.generateDrawableColorShape(path,select, normal)
resource.generateDrawableCorners(path, solid)
resource.generateDrawableImage(path, select, normal)


