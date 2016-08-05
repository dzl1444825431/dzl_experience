package com.dzl.groovy.android


import com.dzl.groovy.file.FileUitl

class A{

	def static readFile(String path, List<File> uixs, List<File> pngs){

		new File(path).listFiles().each {

			if (it.isDirectory()) {
				
				if (!it.getName().contains('uiautomatorviewer')) {
					return
				}
				
				it.listFiles().each { f ->

					String name = f.getName()
					if (name.endsWith('.uix')) {
						uixs.add(f)
					} else if (name.endsWith('.png')) {
						pngs.add(f)
					}
				}
			}
		}
	}

	def static copy(File src, String path){
		def target
		if (src.getName().endsWith('.png') || src.getName().endsWith('.uix')){
			target = path + src.getName()
		}
		def exists
		if (target) {
			exists = FileUitl.copy(src, target)
		}
		return (exists == false ? null : target)
	}

	def static listDirection(File f){
		def uixs = new ArrayList<File>()
		f.listFiles().each {

			if (it.isDirectory()) {
				list(it,uixs)
			}
		}
		return uixs;
	}

	def static list(File f, uixs){
		def ui = false
		f.listFiles().each {
			if (it.getName().endsWith('.uix')) {
				uixs.add(it)
				ui = true
			}
			if (ui && it.getName().endsWith('.png')) {
				uixs.add(it)
				ui = true
			}
		}
	}
}
