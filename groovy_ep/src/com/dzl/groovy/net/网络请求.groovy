package com.dzl.groovy.net

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )


import groovyx.net.http.HTTPBuilder


import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator




import static groovyx.net.http.ContentType.*


import static groovyx.net.http.Method.*


def http = new HTTPBuilder()
def v = ""

http.request( 'http://www.baidu.com', GET, TEXT ) {    req ->
	println "1" + thisObject
	delegate.response.success = {    resp, reader ->
		assert resp.statusLine.statusCode == 200
		println "Got response: ${resp.statusLine}"
		println "Content-Type: ${resp.headers.'Content-Type'}"
//		println reader.text
		println this
		println thisObject
	}
	delegate.response.'404' = {   println 'Not found'   }
}
