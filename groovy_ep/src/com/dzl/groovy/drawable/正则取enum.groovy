package com.dzl.groovy.drawable

import java.util.regex.Matcher
import java.util.regex.Pattern;;

def pathname = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\drawable\\ttt"
def file = new File(pathname)


Pattern pattern = Pattern.compile('<span class="mls">(.*)</span>')

def matcher = pattern.matcher(file.text)


def list = new ArrayList<String>()
def list2 = new ArrayList<String>()
while (matcher.find()) {
	def name = matcher.group(1).trim().replace('-','_')
	//	println name
	list.add(name)
}



Pattern pattern2 = Pattern.compile('<input type="text" readonly value="(.*)" class="unit size1of2" />')

def matcher2 = pattern2.matcher(file.text)

while (matcher2.find()) {
	def name = matcher2.group(1).trim().replace('-','_')
	//	println name
	list2.add(name)
}
//ic_glass('\uE92D'),
println list.size +' '+ list2.size
def length = list2.size
def i = 0
for(i= 0;i<length ;i++){
	
	println list2.get(i) +'(\'\\u' + list2.get(i)+'\'),'
	
}

