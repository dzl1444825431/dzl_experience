package com.dzl.groovy.net

def path = 'D:\\D\\0627\\a'
def file = new File(path)
def list = file.list()
println 'adb shell pm set-install-location 2 '
list.each {
	if (it.endsWith('.apk')) {
		println "adb install ${path}\\${it}"
	}
}
