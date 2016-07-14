package com.dzl.groovy

def path = 'D:\\apk\\Download3'
def target_path = 'D:\\apk\\Download3\\'

def file = new File(path)
int i = 1;
file.listFiles().each {
//	it.renameTo(target_path + (i++) + '.apk')
	println "adb install ${it.getAbsolutePath()}"
}
