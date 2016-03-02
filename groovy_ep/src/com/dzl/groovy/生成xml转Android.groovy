package com.dzl.groovy


def xmlFilePath = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\item_footer.xml"
def xml = new XmlParser().parse(xmlFilePath)

//println xml

//def attrs = xml.attributes()
//println attrs
//

//xml.attributes().each {
//	println it.key.toString().replace("{http://schemas.android.com/apk/res/android}","") + "=" + it.value
//}

//println xml.value()
//println xml.value().class.getClass().getName()

int i = 0

//def nodeEach
//nodeEach = {node ->
//
//	def map = node.value()
//	println map.class.getClass().getName()
//	++i;
//	println i
//	if (!map) {
//		return
//	}
//
//	++i;
//	println i
//	map.each{
//		println "1 level " + it.class.getClass().getName()			//groovy.util.NodeList
//		println "1 level it.attributes = " + it.attributes()
//		println "1 level it.value = " + it.value()
//		println ""
//		it.each{n ->
//			println "2 level " + n.class.getClass().getName() + "  "+ n
////			println "value = " + n.name
//
//			n.each{n1 ->
//				println "3 level " + n1.class.getClass().getName() //+ n
//	//			println "value = " + n.name
//			}
//
//		}
//		println ""
//
//	}
//
//}
//
//nodeEach xml
//
//
//
//
//
//
//
//
//
//def nodeEachDub
//nodeEachDub = {node ->
//
//	def map = node.value()
//	println "map = " + map.class.getClass().getName()
//	++i;
//	println i
//	if (!map) {
//		return
//	}
//
//	++i;
//	println i
////	println map instanceof Node
//	map.each{
////		println it.get(0)
//		println "1 level " + it//.class.getClass().getName()			//groovy.util.NodeList
//		println "1 level it.attributes = " + it.attributes()
//		println "1 level it.value = " + it.value()
//		println "1 level it.text = " + it.text()
//		println ""
//
//		println ""
//
//	}
//
//}
//nodeEachDub xml
//println xml.name()
//xml.children().each {
//	println it instanceof Node
//}

int index = 1

//1.读取和设置属性  2.设置布局参数  3.添加到父view中

//注意引用, 背景 padding

def viewMethod = [
	'id':'setId',//(int)
	'tag':'setTag',//(Object)
//	'background':'setBackground',//(Drawable)
//	'background':'setBackgroundColor',//(int)
//	'background':'setBackgroundDrawable',//(Drawable)
//	'background':'setBackgroundResource',//(int)
	
//	'padding':'setPadding',//(int, int, int, int)
//	'paddingLeft':'setPadding',//(int, int, int, int)
//	'paddingTop':'setPadding',//(int, int, int, int)
//	'paddingRight':'setPadding',//(int, int, int, int)
//	'paddingBottom':'setPadding',//(int, int, int, int)
	
	'focusable':'setFocusable',//(boolean)
	'visibility':'setVisibility',//(int)
	'clickable':'setClickable',//(boolean)
	'alpha':'setAlpha',//(float)
	'minHeight':'setMinimumHeight',//(int)
	'minWidth':'setMinimumWidth',//(int)
	'enabled':'setEnabled',//(boolean)
	'selected':'setSelected',//(boolean)
	
	]


def each3
each3 = {node, level, parentNodeName, parentVariableName ->

	if (!node) {
		return
	}

	println ""
	
	level++
	index++
	
	def nodeName = node.name()
	def nodeVariableName = nodeName.toLowerCase() + "_${level}_${index}"
	//	println level + " name = " + nodeName

	println "${nodeName} ${nodeVariableName} = new ${nodeName}(context);"
	
	def attrs_layout = [:];
	def attrs_self = [:];
	
	node.attributes().each {
		def attr = it.key.toString().substring(it.key.toString().indexOf("}") + 1)
		//.replace("{http://schemas.android.com/apk/res/android}","")
		
		def val = it.value.toString()
		if(val.startsWith("@")){
			//处理引用value
			def ind = val.indexOf("/") + 1
			if (val.startsWith("@anim")) {
				val = "res.getAnimation(R.anim." + val.substring(ind) + ")"
			}else if (val.startsWith("@color")) {
				val = "res.getColor(R.color." + val.substring(ind) + ")"
			}else if (val.startsWith("@dimen")) {
				val = "res.getDimension(R.dimen." + val.substring(ind) + ")"
			}else if (val.startsWith("@drawable")) {
				val = "res.getDrawable(R.drawable." + val.substring(ind) + ")"
			}else if (val.startsWith("@layout")) {
				val = "res.getLayout(R.layout." + val.substring(ind) + ")"
			}else if (val.startsWith("@string")) {
				val = "res.getString(R.string." + val.substring(ind) + ")"
			}else{
				val = "R.id." + val.substring(ind)
			}
		}else if(val.startsWith("#")){
			//处理颜色
			if(val.size() == 9){
				val = val.replace("#","0x")
			}else if(val.size() == 7){
				val = val.replace("#","0xff")
			}else if(val.size() == 4){
				val = "0xff" + val[1] + val[1] + val[2] + val[2] + val[3] + val[3]
			}
		}else if (val == "visible" || val == "gone") {
			val = val[0].toUpperCase()
		}
		
		//处理布局属性
		if (attr.startsWith("layout_")) {
			attrs_layout.put(attr, val)
		} else {
			attrs_self.put(attr, val)
		}
		
	}
	def paramName = "params_${level}_${index}"
	println "${parentNodeName}.LayoutParams ${paramName} = new ${parentNodeName}.generateDefaultLayoutParams();"
	
	//解析布局属性
	attrs_layout.each {key, value->
		if (value == "match_parent" || value == "wrap_content") {
			value = value.toUpperCase()
		}
		println "${paramName}.${key} = ${value};"
	}
	println "${nodeVariableName}.setLayoutParams(${paramName});"
	println ""
	
	def padding = [:]
	
	//开始解析self自身属性
	def iterator = attrs_self.entrySet().iterator()
	while (iterator.hasNext()) {
		def entry = iterator.next()
		
		if (entry.key == 'background'){
			if (entry.value.contains('R.drawable')) {
				println "${nodeVariableName}.setBackground(${entry.value});"
			}else{
				println "${nodeVariableName}.setBackgroundColor(${entry.value});"
			}
		}else if(entry.key.startsWith('padding')){
			//解析padding
			padding.put(entry.key, entry.value)
		}else {
		
			viewMethod.each {
				
				if (it.key == entry.key) {
					
					iterator.remove()
					println "${nodeVariableName}.${it.value}(${entry.value});"
					
					return;
				}
			}
		}
		
	}
	
	def paddingLeft = 0
	def paddingTop = 0
	def paddingRight = 0
	def paddingBottom = 0
	//解析生成padding参数
	padding.each {
		if (it.key == 'padding') {
			paddingLeft = paddingTop = paddingRight = paddingBottom = it.value
			return
		}else if (it.key == 'paddingLeft') {
			paddingLeft = it.value
		}else if (it.key == 'paddingTop') {
			paddingTop = it.value
		}else if (it.key == 'paddingRight') {
			paddingRight = it.value
		}else if (it.key == 'paddingBottom') {
			paddingBottom = it.value
		}
	}
	
	if (padding) {
		println "${nodeVariableName}.setPadding(${paddingLeft},${paddingTop},${paddingRight},${paddingBottom});"
	}
	
//	//解析self属性
//	attrs_self.each {
//		
//		println attrs_self.size()
//		println "11111111${nodeVariableName}.setAttr(${it.key},${it.value});"
//		it.remove()
//	}
	
	println ((parentVariableName ? "${parentVariableName}." : "") + "addView(${nodeVariableName});")
	
//	RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, heigth);
//	footer_status_view.setLayoutParams(params);
//	rcl_product.addFooterView(footer_status_view);

	node.children().each { child ->
		each3(child, level, nodeName, nodeVariableName)
	}

}

each3(xml, 0, "ViewGroup", "")




