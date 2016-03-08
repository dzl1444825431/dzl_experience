package com.dzl.groovy.net

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

/**实现网络下载文件到本地*/
def http = new HTTPBuilder()
def v = ""


//try {
def url = 'http://dd.myapp.com/16891/1DCD91416C3C6EF3130D69CC0874E748.apk'
http.request( url, GET, BINARY ) {    req ->
	println "thisObject = " + thisObject
	println "http = " + http
	println "delegate = " + delegate
	println "delegate.response = " + delegate.response
	println "req = " + req

	delegate.response.success = {    resp, reader ->
		println ''
		//		assert resp.statusLine.statusCode == 200
		//		println "Got response: ${resp.statusLine}"
		//		println "Content-Type: ${resp.headers.'Content-Type'}"
		//		println reader.text
		//		println this
		//		println thisObject

		//		reader.eachLine{
		//			println it
		//		}

		//		new FileOutputStream(name)
		//
		//		int c = 0;
		//		while((c = reader.read())!= -1){
		//			   println ((char)c)
		//		}

		println "reader = " + reader

		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("d:\\d\\c.apk"))

		int len = 2048
		byte[] buf = new byte[len]
		while ((len = reader.read(buf)) != -1) {
			os.write(buf, 0, len)
		}
		os.flush();

	}
	delegate.response.'404' = {    println 'Not found'    }
	delegate.response.failure = {    println "it" + it    }
}
//} catch (Exception e) {
//	println 1111
////	e.printStackTrace()
//}

println 'end'

def downloadFile(String remoteFilePath, String localFilePath)
{
	//Java代码 实现下载
	URL urlfile = null;
	HttpURLConnection httpUrl = null;
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;
	File f = new File(localFilePath);
	try
	{
		println 'aaaaaa'
		urlfile = new URL(remoteFilePath);
		httpUrl = (HttpURLConnection)urlfile.openConnection();
		httpUrl.connect();
		bis = new BufferedInputStream(httpUrl.getInputStream());
		bos = new BufferedOutputStream(new FileOutputStream(f));
		int len = 2048;
		byte[] b = new byte[len];
		while ((len = bis.read(b)) != -1)
		{
			bos.write(b, 0, len);
		}
		bos.flush();
		bis.close();
		httpUrl.disconnect();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			bis.close();
			bos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

//downloadFile("http://dd.myapp.com/16891/73B1AA464BC8E2B23B4180076E4C4058.apk","d:\\\\D\\\\a.apk")
//downloadFile("http://dd.myapp.com/16891/1DCD91416C3C6EF3130D69CC0874E748.apk","d:\\\\D\\\\a.apk")
