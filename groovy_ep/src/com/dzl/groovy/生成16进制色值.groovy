package com.dzl.groovy
/**
 * 转化10进制色值为16进制
 */

def array = []
array = ['76 163 234', '240 76 110','254 107 9','255 178 2','156 156 156','255 62 62'
	,'25 167 63'
	,'51 153 255'
	,'9 108 150'
	,'255 197 132'
	]



def split = ' '
parseColor(array, split)

def parseColor(array, split){
	array.each {
		println("${it} \t=\t0xff${parseColorToHex(it, split)}\t #ff${parseColorToHex(it, split)}")
	}
}

/**
 * 转化10进制色值为16进制
 */
def parseColorToHex(src, split){
	
	def result = ''
	
	try {
		src.split(split).each {
			def parseInt = Integer.parseInt(it)
					def toHexString = Integer.toHexString(parseInt)
					if (toHexString.length() == 1) {
						toHexString = '0' + toHexString;
					}
					result += toHexString
		}
	} catch (Exception e) {
//		e.printStackTrace()
	}
	
	return result;
}