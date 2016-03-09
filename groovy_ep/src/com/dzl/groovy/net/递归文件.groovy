package com.dzl.groovy.net


def file = new File('D:\\D')
def list = file.list()
list.each {
	if (it.endsWith('.apk')) {
		println 'adb install D:\\D\\' + it
	}
}
