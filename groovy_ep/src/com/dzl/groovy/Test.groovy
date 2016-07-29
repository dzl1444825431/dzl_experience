package com.dzl.groovy

import com.dzl.groovy.android.DzlLibUtil

def pathname = '\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\text'

//System.out.print( new File(pathname).text)

println pathname.size()
int i = pathname.toString().lastIndexOf('\\')
println i

i = pathname.toString().lastIndexOf('\\', i - 1)
println i
i = pathname.toString().lastIndexOf('\\', i - 1)
println i

println pathname.substring(i + 1).replaceAll('\\\\','.')

getIndex(pathname,'baiduYun', -1)

println DzlLibUtil.getLastIndex(pathname, '\\', 3)
println pathname.toString().substring(DzlLibUtil.getLastIndex(pathname, '\\', 3) + 1).replaceAll('\\\\','.')

def getIndex(String str, String sub, count){

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

	println index;
}

def getLastIndex(String str, String sub, count){
	
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
	
	println index;
}