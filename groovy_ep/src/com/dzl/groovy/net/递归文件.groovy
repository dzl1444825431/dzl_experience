package com.dzl.groovy.net

def path = 'D:\\D\\16_03_22'
def file = new File(path)
def list = file.list()
list.each {
	if (it.endsWith('.apk')) {
		println "adb install ${path}\\${it}"
	}
}
