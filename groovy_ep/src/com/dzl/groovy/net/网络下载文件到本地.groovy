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

/**实现网络下载文件到本地*/
//def http = new HTTPBuilder()
//
//def url = 'http://www.baidu.com'
//url = 'http://sj.qq.com/myapp/union.htm?orgame=1&page=3'
//http.request( url, GET, TEXT ) {req ->
//
//	delegate.response.success = {    resp, reader ->
//		String text = reader.text
////		println "\nreader.text = " + text
//		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream('D:\\D\\e.txt'))
//		os.write(text.getBytes())
//	}
//	delegate.response.failure = {    println "it" + it    }
//}
//

String text = 'reader.text = <!DOCTYPE html><!--STATUS OK--><html><head><meta http-equiv="content-type" content="text/html;charset=utf-8"><link rel="dns-prefetch" href="//s1.bdstatic.com"/><link rel="dns-prefetch" href="//t1.baidu.com"/><link rel="dns-prefetch" href="//t2.baidu.com"/><link rel="dns-prefetch" href="//t3.baidu.com"/><link rel="dns-prefetch" href="//t10.baidu.com"/><link rel="dns-prefetch" href="//t11.baidu.com"/><link rel="dns-prefetch" href="//t12.baidu.com"/><link rel="dns-prefetch" href="//b1.bdstatic.com"/><title>百度一下，你就知道</title><style >html,body{height:100%}html{overflow-y:auto}#wrapper{position:relative;_position:;min-height:100%}#content{padding-bottom:100px;text-align:center}#ftCon{height:100px;position:absolute;bottom:44px;text-align:center;width:100%;margin:0 auto;z-index:0;overflow:hidden}#ftConw{width:720px;margin:0 auto}body{font:12px arial;text-align:;background:#fff}body,p,form,ul,li{margin:0;padding:0;list-style:none}body,form,#fm{position:relative}td{text-align:left}img{border:0}a{color:#00c}a:active{color:#f60}.bg{background-image:url(http://s1.bdstatic.com/r/www/cache/static/global/img/icons_a7f1d98c.png);background-repeat:no-repeat;_background-image:url(http://s1.bdstatic.com/r/www/cache/static/global/img/icons_2df5fe5e.gif)}.bg_tuiguang_browser{width:16px;height:16px;background-position:-600px 0;display:inline-block;vertical-align:text-bottom;font-style:normal;overflow:hidden;margin-right:5px}.bg_tuiguang_browser_big{width:56px;height:56px;position:absolute;left:10px;top:10px;background-position:-600px -24px}.bg_tuiguang_weishi{width:56px;height:56px;position:absolute;left:10px;top:10px;background-position:-672px -24px}.c-icon{display:inline-block;width:14px;height:14px;vertical-align:text-bottom;font-style normal;overflow:hidden;background:url(http://s1.bdstatic.com/r/www/cache/static/global/img/icons_a7f1d98c.png) no-repeat 0 0;_background-image:url(http://s1.bdstatic.com/r/www/cache/static/global/img/icons_2df5fe5e.gif)}.c-icon-triangle-down-blue{background-position:-480px -168px}.c-icon-chevron-unfold2{background-position:-504px -168px}#u{color:#999;padding:4px 10px 5px 0;text-align:right}#u a{margin:0 5px}#u .reg{margin:0}#m{width:720px;margin:0 auto}#nv a,#nv b,.btn,#lk{font-size:14px}#fm{padding-left:110px;text-align:left;z-index:1}input{border:0;padding:0}#nv{height:19px;font-size:16px;margin:0 0 4px;text-align:left;text-indent:137px}.bdsug{position:absolute;width:418px;background:#fff;display:none;border:1px solid #817f82}.bdsug li{width:402px;color:#000;font:14pxarial;line-height:25px;padding:0 8px;position:relative;cursor:default}.bdsug li.bdsug-s{background:#ebebeb}#fm .bdsug{top:31px}.bdsug-store span,.bdsug-store b{color:#7a77c8}.bdsug-store-del{font-size:12px;color:#666;text-decoration:underline;position:absolute;right:8px;top:0;cursor:pointer;display:none}.bdsug-s .bdsug-store-del{display:inline-block}.bdsug-ala{display:inline-block;border-bottom:1px solid #e6e6e6}.bdsug-ala h3{line-height:14px;background:url(//www.baidu.com/img/sug_bd.png) no-repeat left center;margin:6px 0 4px 0;font-size:12px;font-weight:normal;color:#7b7b7b;padding-left:20px}.bdsug-ala p{font-size:14px;font-weight:bold;padding-left:20px}.bdsug .bdsug-direct{width:auto;padding:0;border-bottom:1px solid #f1f1f1}.bdsug .bdsug-direct p{color:#00c;font-weight:bold;line-height:34px;padding:0 8px;cursor:pointer;white-space:nowrap;overflow:hidden}.bdsug .bdsug-direct p img{width:16px;height:16px;margin:7px 6px 9px 0;vertical-align:middle}.bdsug .bdsug-direct p span{margin-left:8px}.bdsug .bdsug-direct p i{font-size:12px;line-height:100%;font-style:normal;font-weight:normal;color:#fff;background-color:#2b99ff;display:inline;text-align:center;padding:1px 5px;*padding:2px 5px 0 5px;margin-left:8px;overflow:hidden}.s_ipt_wr{width:418px;height:30px;display:inline-block;margin-right:5px;background-position:0 -96px;border:1px solid #b6b6b6;border-color:#9a9a9a #cdcdcd #cdcdcd #9a9a9a;vertical-align:top}.s_ipt{width:405px;height:22px;font-size:16px;line-height:22px;font-family:arial;margin:5px 0 0 7px;background:#fff;outline:0;-webkit-appearance:none}.s_btn{width:95px;height:32px;padding-top:2px;font-size:14px;background-color:#ddd;background-position:0 -48px;cursor:pointer}.s_btn_h{background-position:-240px -48px}.s_btn_wr{width:97px;height:34px;display:inline-block;background-position:-120px -48px;*position:relative;z-index:0;vertical-align:top}#lg img{vertical-align:top;margin-bottom:3px}#lk{margin:33px 0}#lk span{font:14px "宋体"}#lm{height:60px;line-height:15px}#lh{margin:16px 0 5px;word-spacing:3px}.tools{position:absolute;top:-4px;*top:10px;right:7px}#mHolder{width:62px;position:relative;z-index:296;display:none}#mCon{height:18px;line-height:18px;position:absolute;cursor:pointer}#mCon span{color:#00c;cursor:default;display:block;width:24px}#mCon .hw{text-decoration:underline;cursor:pointer;display:inline-block}#mCon .pinyin{display:inline-block}#mCon .c-icon-chevron-unfold2{margin-left:5px}#mMenu a{width:100%;height:100%;display:block;line-height:22px;text-indent:6px;text-decoration:none;filter:none}#mMenu,#user ul{box-shadow:1px 1px 2px #ccc;-moz-box-shadow:1px 1px 2px #ccc;-webkit-box-shadow:1px 1px 2px #ccc;filter:progid:DXImageTransform.Microsoft.Shadow(Strength=2,Direction=135,Color="#cccccc")}#mMenu{width:56px;border:1px solid #9b9b9b;list-style:none;position:absolute;right:27px;top:28px;display:none;background:#fff}#mMenu a:hover{background:#ebebeb}#mMenu .ln{height:1px;background:#ebebeb;overflow:hidden;font-size:1px;line-height:1px;margin-top:-1px}#cp,#cp a{color:#666}#seth{display:none;behavior:url(#default#homepage)}#setf{display:none}#sekj{margin-left:14px}#shouji{margin-right:14px}#user,#user_center{z-index:2}</style><script >function h(obj){obj.style.behavior=\'url(#default#homepage)\';var a = obj.setHomePage(\'//www.baidu.com/\');}</script></head><body><div id="wrapper" style="display:none"><script>!function(){function a(){location.hash&&location.hash.match(/[^a-zA-Z0-9](wd|word)=/)&&location.replace("//www.baidu.com/s?"+location.href.match(/#(.*)$/)[1])}location.hash.match(/[^a-zA-Z0-9](wd|word)=/)?location.replace("//www.baidu.com/s?"+location.href.match(/#(.*)$/)[1]):(document.getElementById("wrapper").style.display="block","onhashchange"in window?window.onhashchange=a:setInterval(a,200))}();</script><div id="content"><div id="u1"><a href="//www.baidu.com/gaoji/preferences.html" name="tj_setting">搜索设置</a>|<a href="https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F" name="tj_login" id="lb" onclick="return false;">登录</a><a href="https://passport.baidu.com/v2/?reg&regType=1&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F" target="_blank" name="tj_reg" class="reg">注册</a></div><div id="m"><div id="lg"><img src="http://www.baidu.com/img/bdlogo.gif" width="270" height="129"></div><p id="nv"><a href="http://news.baidu.com">新&nbsp;闻</a>　<b>网&nbsp;页</b>　<a href="http://tieba.baidu.com">贴&nbsp;吧</a>　<a href="http://zhidao.baidu.com">知&nbsp;道</a>　<a href="http://music.baidu.com">音&nbsp;乐</a>　<a href="http://image.baidu.com">图&nbsp;片</a>　<a href="http://v.baidu.com">视&nbsp;频</a>　<a href="http://map.baidu.com">地&nbsp;图</a></p><div id="fm"><form name="f" action="/s"><span class="bg s_ipt_wr"><input type="text" name="wd" id="kw" maxlength="100" class="s_ipt"></span><input type="hidden" name="rsv_bp" value="0"><input type=hidden name=ch value=""><input type=hidden name=tn value="baidu"><input type=hidden name=bar value=""><input type="hidden" name="rsv_spt" value="3"><input type="hidden" name="ie" value="utf-8"><span class="bg s_btn_wr"><input type="submit" value="百度一下" id="su" class="bg s_btn" onmousedown="this.className=\'bg s_btn s_btn_h\'" onmouseout="this.className=\'bg s_btn\'"></span></form><span class="tools"><span id="mHolder"><div id="mCon"><span>输入法</span></div></span></span><ul id="mMenu"><li><a href="#" name="ime_hw">手写</a></li><li><a href="#" name="ime_py">拼音</a></li><li class="ln"></li><li><a href="#" name="ime_cl">关闭</a></li></ul></div><p id="lk"><a href="http://baike.baidu.com">百科</a>　<a href="http://wenku.baidu.com">文库</a>　<a href="http://www.hao123.com">hao123</a><span>&nbsp;|&nbsp;<a href="//www.baidu.com/more/">更多&gt;&gt;</a></span></p><p id="lm"></p></div></div><div id="ftCon"><div id="ftConw"><p id="st" ><a id="seth" onClick="h(this)" href="/" onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'homepage\',\'pos\':0})">把百度设为主页</a><a id="setf" href="http://www.baidu.com/cache/sethelp/index.html" onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'favorites\',\'pos\':0})" target="_blank">把百度设为主页</a></p><p id="lh"><a href="http://e.baidu.com/?refer=888" onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'btlink\',\'pos\':2})">加入百度推广</a>&nbsp;|&nbsp;<a onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'tj_bang\'})" href="http://top.baidu.com">搜索风云榜</a>&nbsp;|&nbsp;<a onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'tj_about\'})" href="http://home.baidu.com">关于百度</a>&nbsp;|&nbsp;<a onmousedown="return ns_c({\'fm\':\'behs\',\'tab\':\'tj_about_en\'})" href="http://ir.baidu.com">About Baidu</a></p><p id="cp">&copy;2016&nbsp;Baidu&nbsp;<a href="/duty/" name="tj_duty">使用百度前必读</a>&nbsp;京ICP证030173号&nbsp;<img src="//www.baidu.com/cache/global/img/gs-2.0.gif"></p></div></div></div><script>var bds={se:{},comm : {ishome : 1,sid : "18881_19139_1433_18241_17943_18205_18559_17000_17072_15020_12145",user : "",username : "",userid : "0",sugHost : "http://suggestion.baidu.com/su",loginAction : [],encTn:\'415eWTKEqJBIHIMjObuoqprTISvkWh0XD6QsiylKQRz++ZN8KndGLlPA/KA\'}}</script><script type="text/javascript" src="http://s1.bdstatic.com/r/www/cache/static/jquery/jquery-1.10.2.min_f2fb5194.js" charset="utf-8"></script><script type="text/javascript" src="http://s1.bdstatic.com/r/www/cache/static/global/js/home_0096cc55.js" charset="utf-8"></script><script>var bdUser = null;var w=window,d=document,n=navigator,k=d.f.wd,a=d.getElementById("nv").getElementsByTagName("a"),isIE=n.userAgent.indexOf("MSIE")!=-1&&!window.opera;(function(){if(/q=([^&]+)/.test(location.search)){k.value=decodeURIComponent(RegExp["x241"])}})();if(n.cookieEnabled){bds.se.sug({\'form\':$(\'#fm form\')[0],\'ipt\':$(\'#kw\')[0]});};function addEV(o, e, f){if(w.attachEvent){o.attachEvent("on" + e, f);}else if(w.addEventListener){ o.addEventListener(e, f, false);}}function G(id){return d.getElementById(id);}function ns_c(q){var p = encodeURIComponent(window.document.location.href), sQ = \'\', sV = \'\', mu=\'\', img = window["BD_PS_C" + (new Date()).getTime()] = new Image();for (v in q) {sV = q[v];sQ += v + "=" + sV + "&";} mu= "&mu=" + p ;img.src = "http://nsclick.baidu.com/v.gif?pid=201&pj=www&rsv_sid=18881_19139_1433_18241_17943_18205_18559_17000_17072_15020_12145&" + sQ + "path="+p+"&t="+new Date().getTime();return true;}if(/\bbdime=[12]/.test(d.cookie)){document.write(\'<script src="\' + "http://s1.bdstatic.com/r/www/cache/static/ime/js/openime_bc856b14.js" + \'" charset="utf-8"></script>\');}(function(){var u = G("u").getElementsByTagName("a"), nv = G("nv").getElementsByTagName("a"), lk = G("lk").getElementsByTagName("a"), un = "";var tj_nv = ["news","tieba","zhidao","mp3","img","video","map"];var tj_lk = ["baike","wenku","hao123","more"];un = bds.comm.user == "" ? "" : bds.comm.user;function _addTJ(obj){addEV(obj, "mousedown", function(e){var e = e || window.event;var target = e.target || e.srcElement;if(target.name){ns_c({\'fm\':\'behs\',\'tab\':target.name,\'un\':encodeURIComponent(un)});}});}for(var i = 0; i < u.length; i++){_addTJ(u[i]);}for(var i = 0; i < nv.length; i++){nv[i].name = \'tj_\' + tj_nv[i];}for(var i = 0; i < lk.length; i++){lk[i].name = \'tj_\' + tj_lk[i];}})();(function() {var links = {\'tj_news\': [\'word\', \'http://news.baidu.com/ns?tn=news&cl=2&rn=20&ct=1&ie=utf-8\'],\'tj_tieba\': [\'kw\', \'http://tieba.baidu.com/f?ie=utf-8\'],\'tj_zhidao\': [\'word\', \'http://zhidao.baidu.com/search?pn=0&rn=10&lm=0\'],\'tj_mp3\': [\'key\', \'http://music.baidu.com/search?fr=ps&ie=utf-8\'],\'tj_img\': [\'word\', \'http://image.baidu.com/i?ct=201326592&cl=2&nc=1&lm=-1&st=-1&tn=baiduimage&istype=2&fm=&pv=&z=0&ie=utf-8\'],\'tj_video\': [\'word\', \'http://video.baidu.com/v?ct=301989888&s=25&ie=utf-8\'],\'tj_map\': [\'wd\', \'http://map.baidu.com/?newmap=1&ie=utf-8&s=s\'],\'tj_baike\': [\'word\', \'http://baike.baidu.com/search/word?pic=1&sug=1&enc=utf8\'],\'tj_wenku\': [\'word\', \'http://wenku.baidu.com/search?ie=utf-8\']};var domArr = [G(\'nv\'), G(\'lk\'),G(\'cp\')],kw = G(\'kw\');for (var i = 0, l = domArr.length; i < l; i++) {domArr[i].onmousedown = function(e) {e = e || window.event;var target = e.target || e.srcElement,name = target.getAttribute(\'name\'),items = links[name],reg = new RegExp(\'^\\s+|\\s+x24\'),key = kw.value.replace(reg, \'\');if (items) {if (key.length > 0) {var wd = items[0], url = items[1],url = url + ( name === \'tj_map\' ? encodeURIComponent(\'&\' + wd + \'=\' + key) : ( ( url.indexOf(\'?\') > 0 ? \'&\' : \'?\' ) + wd + \'=\' + encodeURIComponent(key) ) );target.href = url;} else {target.href = target.href.match(new RegExp(\'^http://.+.baidu.com\'))[0];}}name && ns_c({\'fm\': \'behs\',\'tab\': name,\'query\': encodeURIComponent(key),\'un\': encodeURIComponent(bds.comm.user || \'\') });};}})();addEV(w,"load",function(){k.focus()});w.onunload=function(){};</script><script type="text/javascript" src="http://s1.bdstatic.com/r/www/cache/static/user/js/u_cff953eb.js" charset="utf-8"></script><script>try{document.cookie="WWW_ST=;expires=Sat, 01 Jan 2000 00:00:00 GMT";$(document.forms[0]).on("submit",function(){var _t=new Date().getTime();document.cookie = "WWW_ST=" + _t +";expires=" + new Date(_t + 10000).toGMTString()})}catch(e){}</script></body></html>'
//text = 'meta'
text = new File('D:\\D\\a.txt').text
Pattern p = ~/ex_url="http[^"]*"/
Matcher matcher = p.matcher(text)
println matcher.find()
//
println matcher.size()
int i = 1
matcher.each {
	String url = it.toString().substring(8, it.size() - 1)
	String path = 'D:\\D\\' + url.substring(url.lastIndexOf('?') + 1).replace('fsname=','').replace('&asr=8eff','')
	println i + '\t' + url + '     \t' + path
	i++
	downloadFile(url, path, true)
	
}




