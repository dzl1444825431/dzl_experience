package com.dzl.groovy.android

class DzlLibUtil {

	def static createFileDirectory(path, files, split){
		files.split(split).each {

			def replaceAll = "${path}\\${it}"
			replaceAll = replaceAll.replaceAll("\\.", "\\\\")
			File f = new File(replaceAll)

			if (!f.exists()) {
				f.mkdirs()
			}
		}
	}

	def static getIndex(String str, String sub, count){

		if (count < 0) {
			count = 0;
		}

		int index = -1;

		for (;;) {
			if (count-- < 0) {
				break;
			}

			index = str.indexOf(sub, index + 1)

			if (index == -1) {
				break;
			}
		}

		return index;
	}

	def static getLastIndex(String str, String sub, count){

		if (count <= 0) {
			return -1;
		}

		int index = str.size();

		for (;;) {
			if (count-- <= 0) {
				break;
			}

			index = str.lastIndexOf(sub, index - 1)

			if (index == -1) {
				break;
			}
		}

		return index;
	}
}
