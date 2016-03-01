package com.dzl.groovy


def xmlFilePath = "D:\\baiduYun\\dzl_github\\app1\\yiqiaoquancheng\\res\\layout\\item_footer.xml"
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

int j = 1

//1.读取和设置属性  2.设置布局参数  3.添加到父view中

def each3
each3 = {node, level, parentname ->

	if (!node) {
		return
	}

	println ""
	
	level++
	def nodeName = node.name()
	def nodeVariableName = nodeName.toLowerCase() + "_" + level + "_" + j++
	//	println level + " name = " + nodeName

	println nodeName + " " + nodeVariableName + " = new " + nodeName + "(context);"
	node.attributes().each {
		def attr = it.key.toString().replace("{http://schemas.android.com/apk/res/android}","")
		
		if (attr == "layout_width") {
			attr = attr[0].toUpperCase() + attr.substring(1)
			println "${parentname}.LayoutParams params = new ${parentname}.LayoutParams(, heigth);"
		} else {
		
			attr = attr[0].toUpperCase() + attr.substring(1)
					println nodeVariableName + ".set" + attr + "(" + it.value + ");"
		}
		
	}
	
//	RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, heigth);
//	footer_status_view.setLayoutParams(params);
//	rcl_product.addFooterView(footer_status_view);

	node.children().each { child ->
		each3(child, level, nodeName)
	}

}

each3(xml, 0, "")




