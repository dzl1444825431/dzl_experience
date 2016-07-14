package com.dzl.groovy.net
@Grapes([
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' ),
@GrabConfig(systemClassLoader=false)
	])


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

/** 实现网络下载文件到本地 */
/** 下载到本地 */
String text = '下载到本地'
text = new File('D:\\D\\a.txt').text
println 1


def downloadFile(String url, String file_path, boolean reload){
	if (reload) {
		File file = new File(file_path);
		if (file.exists()) {
			return
		}
	}
	try {
		Thread.sleep(10 * 1000)
	} catch (Exception e) {
		e.printStackTrace()
	}
	try {
		HTTPBuilder http = new HTTPBuilder()
		http.request(url, GET, BINARY){
			delegate.response.success = {resp, reader ->
			writeFile(file_path, reader)
			}
		}
	} catch (Exception e) {
		e.printStackTrace()
	}
}

def writeFile(String file_path, InputStream is){
	BufferedOutputStream bs = null
	try {
		bs = new BufferedOutputStream(new FileOutputStream(file_path))

		int len = 2048
		byte[] buf = new byte[len]
		while ((len = is.read(buf)) != -1) {
			bs.write(buf, 0, len)
		}
		bs.flush()
	} catch (Exception e) {
		e.printStackTrace()
	}finally{
		if (bs != null) {
			try {
				bs.close()
			} catch (Exception e) {
			}
		}
	}
}


Pattern p = ~/ex_url="http[^"]*"/
Matcher matcher = p.matcher(text)
println matcher.find()
//
println matcher.size()
int i = 1
matcher.each {
	String url = it.toString().substring(8, it.size() - 1)
	String path = 'D:\\D\\0627\\' + url.substring(url.lastIndexOf('?') + 1).replace('fsname=','').replace('&asr=8eff','')
	println i + '\t' + url + '     \t' + path
	i++
	downloadFile(url, path, true)
	
}




