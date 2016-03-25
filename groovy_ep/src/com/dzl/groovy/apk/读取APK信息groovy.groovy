package com.dzl.groovy.apk


def file_path = 'D:\\D\\16_03_22'
def file_path_log = 'D:\\aaa4.txt'

def f = new File(file_path)
File f_log = new File(file_path_log)

readApkAndWrite(f, f_log)

//println f_log.getText

private readApkAndWrite(File f, File f_log) {
	def file_filter = new FileFilter(){
				boolean accept(File arg0) {
					return arg0.getName().endsWith('.apk')
				}
			}

	def list = f.listFiles(file_filter)
	def file_lists = []
	list.each {
		file_lists.add(it.getAbsolutePath())
	}

	println file_lists
	String aapt = 'aapt d badging '
	if (!f_log.exists()) {
		f_log.createNewFile()
	}

	Runtime runtime = Runtime.getRuntime()
	file_lists.each {
		try {
			String cmd = aapt + it
			def progress = runtime.exec(cmd)
			f_log.append('\r\n')
			f_log.append(progress.getInputStream())

		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}

f_log.eachLine {
	if (it.contains('application-label:')) {
		println it
	}
}
