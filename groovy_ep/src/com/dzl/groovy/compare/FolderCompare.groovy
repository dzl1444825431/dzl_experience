package com.dzl.groovy.compare

import java.lang.annotation.Target;

def p1 = "D:\\work\\whwl_sql\\whwl"
def p2 = "D:\\work\\whwl2014-11-26\\whwl\\WebRoot"

def src = p2
def target = p1
File src_file = new File(src)


println 1

private com(File src, String p1, String p2){
//	println src.getAbsolutePath()
//	println src.exists()
	
	src.listFiles().each {
		if (it.isDirectory()) {
			com(it, p1, p2)
		}else{
			def replace = it.getAbsolutePath().replace(p1, p2)
			def f = new File(replace)
			if (!f.exists()) {
				if (it.getName().endsWith('.wav')) {
//					it.delete()
//					println(" delete " + it.getAbsolutePath())
				}
				println("no exists " + replace)
			}else{
//				if (it.getName().endsWith('.jsp') || it.getName().endsWith('.xml') ) {
//					
//					if (!it.text.equals(f.text)) {
//						println(" text no equals " + replace)
//					}
//				}
			}
		}
		
	}
	
}

com(src_file, src, target)