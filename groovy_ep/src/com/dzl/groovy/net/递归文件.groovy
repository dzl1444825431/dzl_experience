package com.dzl.groovy.net

def path = 'D:\\D\\16_03_23'
def file = new File(path)
def list = file.list()
println 'adb shell pm set-install-location 2 '
list.each {
	if (it.endsWith('.apk')) {
		println "adb install -s ${path}\\${it}"
	}
}
