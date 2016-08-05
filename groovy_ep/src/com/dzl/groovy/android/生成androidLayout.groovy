package com.dzl.groovy.android

import java.util.regex.Matcher
import java.util.regex.Pattern

import javax.swing.text.html.CSS.LengthUnit

import com.dzl.groovy.file.FileUitl;;;;

def path = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\android\\b.xml"
path = "D:/baiduYun/dzl_github/uix/dump_2435332018442501441.uix"

def ui = new UI(path)
ui.tree_level = 1
ui.printBounds = 0
ui.printNode = 1
//ui.assign_level = 14
//ui.tree_name = 'ListView'
//ui.tree_name_child_index = 0
//ui.bounds = '[0,644][1080,959]'
def copyFile = 1

if (copyFile) {
	ui.start()

} else {
	def path_uix = 'C:/Users/Administrator/AppData/Local/Temp'
	def target = 'D:/baiduYun/dzl_github/uix/'

	def uixs = new ArrayList<File>()
	def pngs = new ArrayList<File>()
	A.readFile(path_uix, uixs, pngs)

	def size = uixs.size()
	for(int i = 0; i < size; i++){
		String name = uixs.get(i).getName()
		def exists = A.copy(uixs.get(i), target)
		if (exists) {
			println exists
			FileUitl.copy(pngs.get(i), target + name.replaceAll('uix', 'png'))
		}
		
	}

	println uixs.size()
}










