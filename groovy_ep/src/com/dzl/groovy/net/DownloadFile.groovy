package com.dzl.groovy.net
@Grapes([
//	@GrabConfig(systemClassLoader = true, initContextClassLoader=true),
	@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )
])

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class DownloadFile{

	def downloadFile(String url, String file_path){
		HTTPBuilder http = new HTTPBuilder()
		http.request(url, GET, BINARY){
			delegate.response.success = {resp, reader ->
				writeFile(file_path, reader)
			}
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
}
