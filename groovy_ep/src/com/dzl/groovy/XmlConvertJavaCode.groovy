package com.dzl.groovy


/**
 * step	 1.读取和设置属性  2.设置布局参数  3.添加到父view中
 * @author dzl 2016年3月3日
 *
 */
class XmlConvertJavaCode {

	def xml_file_path
	def view_index
	
	def convert = {xmlFilePath, parentNodeName = 'ViewGroup', parentNodeValiable = '', level = 0, index = 0, nodeVariableName = ''->
		xml_file_path = xmlFilePath
		view_index = index
		def xml = new XmlParser().parse(xmlFilePath)
		recursion(xml, parentNodeName, parentNodeValiable, level, nodeVariableName)
	}

	def gravityStrAppend = {str ->
		return 'Gravity.' + str.toString().toUpperCase().replaceAll('\\|','|Gravity.')
	}

	def viewMethod = [
		'id':'setId',//(int)
		'tag':'setTag',//(Object)
		'focusable':'setFocusable',//(boolean)
		'visibility':'setVisibility',//(int)
		'clickable':'setClickable',//(boolean)
		'alpha':'setAlpha',//(float)
		'minHeight':'setMinimumHeight',//(int)
		'minWidth':'setMinimumWidth',//(int)
		'enabled':'setEnabled',//(boolean)
		'selected':'setSelected',//(boolean)

	]
	def textViewMethod = [
		'gravity':'setGravity',
		'hint':'setHint',
		'textColorHint':'setHintTextColor',
		'singleLine':'setSingleLine',
		'text':'setText',
		'textColor':'setTextColor',
		'textSize':'setTextSize'
	]
	def imageViewMethod = [
		'src':'setImageDrawable',
		'scaleType':'setScaleType'
	]
	def progressBarMethod = [
		'indeterminate':'setIndeterminate'
	]
	def linearLayoutMethod = [
		'gravity':'setGravity',
		'orientation':'setOrientation'
	]
	def relativeLayoutMethod = [
		'gravity':'setGravity',
		'ignoreGravity':'setIgnoreGravity'
	]
	def viewGroupParams = [
		//	'layout_width':'width',
		//	'layout_height':'height',
		'layout_marginBottom':'bottomMargin',
		'layout_marginLeft':'leftMargin',
		'layout_marginRight':'rightMargin',
		'layout_marginTop':'topMargin',
	]

	def linearLayoutParams = [
		'layout_weight':'weight',
		'layout_gravity':'gravity'
	]

	def frameLayoutParams = [
		'layout_gravity':'gravity'
	]

	//2 对应addRule的(int,int)两参数方法
	def relativeLayoutParams = [
		'layout_toLeftOf':'2LEFT_OF',
		'layout_toRightOf':'2RIGHT_OF',
		'layout_above':'2ABOVE',
		'layout_below':'2BELOW',
		'layout_alignBaseline':'2ALIGN_BASELINE',
		'layout_alignLeft':'2ALIGN_LEFT',
		'layout_alignTop':'2ALIGN_TOP',
		'layout_alignRight':'2ALIGN_RIGHT',
		'layout_alignBottom':'2ALIGN_BOTTOM',

		'layout_alignParentLeft':'ALIGN_PARENT_LEFT',
		'layout_alignParentTop':'ALIGN_PARENT_TOP',
		'layout_alignParentRight':'ALIGN_PARENT_RIGHT',
		'layout_alignParentBottom':'ALIGN_PARENT_BOTTOM',
		'layout_centerInParent':'CENTER_IN_PARENT',
		'layout_centerHorizontal':'CENTER_HORIZONTAL',
		'layout_centerVertical':'CENTER_VERTICAL',
	]
	def recursion = {node, parentNodeName, parentVariableName, level, nodeVaribleNameOld = '' ->

		if (!node) {
			return
		}

		println ""

		def nodeName = node.name()
		
		level++
		view_index++
		
		def nodeVariableName 
		
		if (nodeVaribleNameOld) {
			nodeVariableName = nodeName.toLowerCase() + '_' + nodeVaribleNameOld
		}else{
			nodeVariableName= nodeName.toLowerCase() + "_${level}_${view_index}"
		}
		
		def attrs_layout = [:]
		def attrs_self = [:]

		def width = 0
		def height = 0

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
					val = "(int)res.getDimension(R.dimen." + val.substring(ind) + ")"
				}else if (val.startsWith("@drawable")) {
					val = "res.getDrawable(R.drawable." + val.substring(ind) + ")"
				}else if (val.startsWith("@layout")) {
					val = val.substring(ind)
				}else if (val.startsWith("@string")) {
					val = "res.getString(R.string." + val.substring(ind) + ")"
				}else if (val.startsWith("@null")) {
					val = "null"
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

			}else if (val.endsWith('dp')){
				val = "dip2px(${val.replace('dp','')})"
			}else if (val.endsWith('sp')){
				val = "sp2px(${val.replace('sp','')})"
			}else if (val.endsWith('px')){
				val = val.replace('px','')
			}

			//处理布局属性
			if (attr.startsWith("layout_")) {
				if (attr == 'layout_width') {
					if (val == "match_parent" || val == "wrap_content") {
						val = val.toUpperCase()
					}
					width = val
				}else if (attr == 'layout_height') {
					if (val == "match_parent" || val == "wrap_content") {
						val = val.toUpperCase()
					}
					height = val
				}else{
					attrs_layout.put(attr, val)
				}

			} else {
				attrs_self.put(attr, val)
			}

		}
		
		println "${nodeName} ${nodeVariableName} = new ${nodeName}(context);"
		def paramName = "params_${level}_${view_index}"
		
		println "${parentNodeName}.LayoutParams ${paramName} = new ${parentNodeName}.LayoutParams(${width}, ${height});"

		//解析布局属性   分层次 先viewgroup margin  再到三大layout
		//父节点relativeLayout 进行单独处理

		if (attrs_layout.containsKey('layout_margin')) {

			def margin = attrs_layout.get('layout_margin');
			attrs_layout.put('layout_marginLeft', margin)
			attrs_layout.put('layout_marginTop', margin)
			attrs_layout.put('layout_marginRight', margin)
			attrs_layout.put('layout_marginBottom', margin)

		}

		Iterator attrs_iterator = attrs_layout.iterator()
		while (attrs_iterator.hasNext()) {
			def it = attrs_iterator.next()

			viewGroupParams.each {key, value ->
				if (it.key == key) {
					attrs_iterator.remove()
					def val = it.value
					if (val == "match_parent" || val == "wrap_content") {
						val = val.toUpperCase()
					}
					println "${paramName}.${value} = ${val};"
					return
				}

			}


		}

		//处理三大layout
		if (parentNodeName == 'LinearLayout') {
			attrs_iterator = attrs_layout.iterator()
			while (attrs_iterator.hasNext()) {
				def it = attrs_iterator.next()

				linearLayoutParams.each {key, value ->
					if (it.key == key) {
						attrs_iterator.remove()
						def val = it.value

						if (it.key == 'layout_gravity') {
							val = gravityStrAppend(val)
						}else if(it.key == 'weight'){
							val = "(float)${val}"
						}
						println "${paramName}.${value} = ${val};	//LinearLayout"
						return
					}

				}

			}
		}else if (parentNodeName == 'FrameLayout') {
			attrs_iterator = attrs_layout.iterator()
			while (attrs_iterator.hasNext()) {
				def it = attrs_iterator.next()

				frameLayoutParams.each {key, value ->
					if (it.key == key) {
						attrs_iterator.remove()
						def val = it.value

						if (it.key == 'layout_gravity') {
							val = gravityStrAppend(val)
						}
						println "${paramName}.${value} = ${val};	//FrameLayout"
						return
					}

				}

			}
		}else if (parentNodeName == 'RelativeLayout') {
			attrs_iterator = attrs_layout.iterator()
			while (attrs_iterator.hasNext()) {
				def it = attrs_iterator.next()

				relativeLayoutParams.each {key, value ->
					if (it.key == key) {
						attrs_iterator.remove()
						def val = it.value
						if (value[0] == '2') {
							value = value.substring(1)
						}else{
							val = (val == 'true' ? 'RelativeLayout.TRUE' : 0)
						}
						println "${paramName}.addRule(RelativeLayout.${value}, ${val});	//RelativeLayout"
						return
					}

				}

			}
		}

		println "${nodeVariableName}.setLayoutParams(${paramName});"
		println ""

		def padding = [:]

		//开始解析self自身属性
		def iterator = attrs_self.entrySet().iterator()
		while (iterator.hasNext()) {
			def entry = iterator.next()

			if (entry.key == 'background'){
				iterator.remove()
				if (entry.value.toString().startsWith('0x') || entry.value.toString().startsWith('res.getColor')) {
					println "${nodeVariableName}.setBackgroundColor(${entry.value});"
				}else{
					println "setBackground(${nodeVariableName}, ${entry.value});"
				}
			}else if(entry.key.startsWith('padding')){
				iterator.remove()
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

		if (nodeName == 'TextView' || nodeName == 'CheckBox' || nodeName == 'Button' || nodeName == 'EditText') {
			attrs_self.each {attrubite ->

				textViewMethod.each {
					if (attrubite.key == it.key) {

						def val = attrubite.value
						if (it.key == 'gravity') {
							val = gravityStrAppend(attrubite.value)
						}else if(it.key == 'text' || it.key == 'hint') {
							if (!val.startsWith('res')) {
								val = "\"${val}\""
							}
						}else if(it.key == 'textSize') {
							if(val.contains('res.getDimension')){
								val = "px2dip(${val})"
							}
						}

						println "${nodeVariableName}.${it.value}(${val});"
						return;
					}
				}
			}
		}else if (nodeName == 'ImageView' || nodeName == 'ImageButton') {
			attrs_self.each {attrubite ->

				imageViewMethod.each {
					if (attrubite.key == it.key) {

						def val = attrubite.value
						if (it.key == 'scaleType') {
							val = 'ImageView.ScaleType.' + attrubite.value.toString().replaceAll('\\|','|ImageView.ScaleType.')
						}

						println "${nodeVariableName}.${it.value}(${val});"
						return;
					}
				}
			}
		}else if (nodeName == 'ProgressBar') {
			attrs_self.each {attrubite ->
				progressBarMethod.each {
					if (attrubite.key == it.key) {
						def val = attrubite.value
						println "${nodeVariableName}.${it.value}(${val});"
						return;
					}
				}
			}
		}else if (nodeName == 'LinearLayout') {
			attrs_self.each {attrubite ->
				linearLayoutMethod.each {
					if (attrubite.key == it.key) {

						def val = attrubite.value
						if (it.key == 'gravity') {
							val = gravityStrAppend(attrubite.value)
						}else if (it.key == 'orientation') {
							val = 'LinearLayout.' + val.toUpperCase()
						}

						println "${nodeVariableName}.${it.value}(${val});"
						return;
					}
				}
			}
		}else if (nodeName == 'RelativeLayout') {
			attrs_self.each {attrubite ->
				relativeLayoutMethod.each {
					if (attrubite.key == it.key) {

						def val = attrubite.value
						if (it.key == 'gravity') {
							val = gravityStrAppend(attrubite.value)
						}

						println "${nodeVariableName}.${it.value}(${val});"
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
			println "${nodeVariableName}.setPadding(${paddingLeft}, ${paddingTop}, ${paddingRight}, ${paddingBottom});"
		}



		if (nodeName == 'include') {
			def layout_file_path = attrs_self.get('layout')
			
			layout_file_path = xml_file_path.substring(0, xml_file_path.lastIndexOf('\\') + 1) + layout_file_path + '.xml'
			println "//include start ==================="
			convert(layout_file_path,parentNodeName, parentVariableName, level - 1, view_index, nodeVariableName)
			println '//include end ==================='
		}else {
			println ((parentVariableName ? "${parentVariableName}." : "") + "addView(${nodeVariableName});")
		}
		
		node.children().each { child ->
			recursion(child, nodeName, nodeVariableName, level)
		}
		

	}

}
