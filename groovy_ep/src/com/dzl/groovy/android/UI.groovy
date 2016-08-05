package com.dzl.groovy.android

import java.util.List;

import groovy.util.Node;

class UI {

	def path
	def is_contentView = false
	def printNode = false		//添加log信息开关
	def printBounds = false		//输入bounds信息开关
	def view_index
	def tree_level = 0
	def tree_name
	def tree_name_child_index = -1

	def assign_level = -1
	def bounds

	def desityPer = 1.5f

	List<String> id_list = new ArrayList<String>()
	List<String> variable_list = new ArrayList<String>()
	List<String> text_list = new ArrayList<String>()
	List<String> text_list_level = new ArrayList<String>()
	List<String> printlist = new ArrayList<String>()
	List<String> bounds_list = new ArrayList<String>()

	public UI(String path){
		this.path = path
	}

	def start() {
		Node node = new XmlParser().parse(path)
		parse(node,'', '', 0 , 0, 0, 0, 0, printNode, '')

		Collections.sort(id_list)
		Collections.sort(variable_list)

		println()
		for (id in id_list) {
			println "protected int ${id};"
		}

		println()
		for (var in variable_list) {
			println "public ${var};"
		}

		println()
		for (id in id_list) {
			println "${id} = generateViewId();"
		}

		println()
		for (var in printlist) {
			println(var)
		}

		println()
		for (var in variable_list) {
			if (var.toString().contains("image")) {
				println "setImageViewScaleType(${var.split(' ')[1]}, ImageView.ScaleType.CENTER);"
			}
		}

		println()
		boolean logo = false
		for (var in variable_list) {
			if (var.toString().contains("image")) {
				logo = true
				println "setImageViewIconDrawable(context, ${var.split(' ')[1]}, IconSnakeFlow.e000, 0xff000000);"
			}
		}

		if (logo == true) {
			println()
			println "//loadImageViewGlide(ui.imageview, url);"
		}

		println()
		for (var in variable_list) {
			if (var.toString().contains("text")) {
				println "//setText(ui.${var}, \"\");"
			}
		}

		print("\n//")
		for (var in text_list) {
			print "${var},"
		}

		print("\n//")
		for (var in text_list_level) {
			print "${var},"
		}

		if (printBounds) {

			println()
			for (var in bounds_list) {
				println var
			}
		}

	}

	def parse(Node node,String parentNodeName, String parentVariableName,
			level, p_left, p_right, p_top, p_bottom,printNode,String nodeVaribleNameOld = ''){

		if (!node) {
			return
		}

		def attr = node.attributes()

		String bounds = attr.get('bounds').toString()
		String nodeName = attr.get('class').toString()
		String rid = attr.get('resource-id').toString()
		String text = attr.get('text').toString()

		int point = nodeName.toString().lastIndexOf('.')
		if (point >= 0) {
			nodeName = nodeName.toString().substring(point + 1)
		}

		String nodeVariableName

		def left = 0, right = 0, top = 0, bottom = 0

		if (is_contentView) {

			def m = bounds =~ /\[(.*),(.*)\]\[(.*),(.*)\]/
			def width
			def height

			def m_left, m_right, m_top, m_bottom //margin
			if(m.find()){

				left = Integer.parseInt(m.group(1))
				right = Integer.parseInt(m.group(3))
				top = Integer.parseInt(m.group(2))
				bottom = Integer.parseInt(m.group(4))

				if (this.bounds && this.bounds == bounds) {
					printNode = true;
				}

				//添加log关口
				if (level++ >= tree_level && printNode) {


					view_index++
					if (nodeName == 'ImageView') {
						def a = 1
					}
					width = (right - left)
					height = (bottom - top)

					m_left = left - p_left
					m_right = p_right - right
					m_top = top - p_top
					m_bottom = p_bottom - bottom

					if (m_left == 0 && m_right == 0) {
						width = "MATCH_PARENT"
					}else if (width > 600) {
						width = "MATCH_PARENT"
					} else {
						def w = translate(width)
						width = "translate(context, ${w})"
						if (w == 1) {
							width = 1
						}
					}

					if (m_top == 0 && m_bottom == 0) {
						height = "MATCH_PARENT"
					}else if (height > 700) {
						height = "MATCH_PARENT"
					} else {
						def h = translate(height)
						height = "translate(context, ${h})"
						if (h == 1) {
							height = 1
						}
					}


					if (nodeVaribleNameOld) {
						nodeVariableName = nodeName.toLowerCase() + '_' + nodeVaribleNameOld
					}else{
						nodeVariableName= nodeName.toLowerCase() + "${view_index}"
					}

					def paramName = "params${view_index}"

					printlist.add("")

					variable_list.add("${nodeName} ${nodeVariableName}")
					id_list.add("id_${nodeVariableName}")

					printlist.add("${nodeVariableName} = new ${nodeName}(context); // level = ${level}")
					printlist.add("${nodeVariableName}.setId(id_${nodeVariableName});")
					if (width == 1 || height == 1) {
						printlist.add("setBackgroundColor(${nodeVariableName}, ColorConfig.COLOR_LINE2);")
					}
					if ((nodeName == 'TextView' || nodeName == 'Button' || nodeName.contains('EditText'))) {
						printlist.add("${parentNodeName}.LayoutParams ${paramName} = new ${parentNodeName}.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);///*${width}*/ ${height}*/")
					} else {
						printlist.add("${parentNodeName}.LayoutParams ${paramName} = new ${parentNodeName}.LayoutParams(${width}, ${height});")
					}


					int center = 0;
					if (parentNodeName == 'RelativeLayout') {

						if (nodeName == 'ImageView') {
							def a = 1
						}
						if (text == 'V') {
							def a = 1
						}

						if ((m_left - m_right == 0 || m_left - m_right == 1) && width != 'MATCH_PARENT') {
							center = 2
						}
						if ((m_bottom - m_top == 0 || m_bottom - m_top == 1) && height != 'MATCH_PARENT') {
							center += 3;
						}

						if (center == 5) {
							m_left = m_right = m_top = m_bottom = 0
							printlist.add("${paramName}.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);")
						} else if (center == 2) {
							m_left = m_right = 0
							printlist.add("${paramName}.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);")
						} else if (center == 3) {
							m_top = m_bottom = 0
							printlist.add("${paramName}.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);")
						}

						if (m_right > 0 && m_left > 0 && m_right < m_left && m_right < 200) {
							printlist.add("${paramName}.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);")
						}

						if (m_bottom > 0 && m_top > 0 && m_bottom < m_top && m_bottom < 200) {
							printlist.add("${paramName}.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);")
						} else if (height == 1 && bottom == p_bottom) {
							printlist.add("${paramName}.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);")
						}

						printlist.add("${paramName}.addRule(RelativeLayout.BELOW, id_${nodeVariableName});")


					} else if (parentNodeName == 'LinearLayout') {

						if (m_left - m_right == 0 && width != 'MATCH_PARENT') {
							center = 2
						}
						if (m_bottom - m_top == 0 && height != 'MATCH_PARENT') {
							center += 3;
						}

						if (center == 5) {
							m_left = m_right = m_top = m_bottom = 0
							printlist.add("${paramName}.gravity = Gravity.CENTER;")
						} else if (center == 2) {
							m_left = m_right = 0
							printlist.add("${paramName}.gravity = Gravity.CENTER_HORIZONTAL;")
						} else if (center == 3) {
							m_top = m_bottom = 0
							printlist.add("${paramName}.gravity = Gravity.CENTER_VERTICAL;")
						}

					}

					if (m_left > 0 && m_left < (p_right - p_left) / 2) {
						printlist.add("${paramName}.leftMargin = translate(context, ${translate(m_left)});")
					}
					if (m_right > 0 && m_right < (p_right - p_left) / 2) {
						printlist.add("${paramName}.rightMargin = translate(context, ${translate(m_right)});")
					}
					if (m_top > 0 && m_top < (p_bottom - p_top) / 2) {
						printlist.add("${paramName}.topMargin = translate(context, ${translate(m_top)});")
					}
					if (m_bottom > 0 && m_bottom < (p_bottom - p_top) / 2) {
						printlist.add("${paramName}.bottomMargin = translate(context, ${translate(m_bottom)});")
					}


					printlist.add("${nodeVariableName}.setLayoutParams(${paramName});")
					printlist.add("")

					if (text && (nodeName == 'TextView' || nodeName == 'CheckBox' || nodeName == 'Button' || nodeName.contains('EditText'))) {
						printlist.add("${nodeVariableName}.setGravity(Gravity.CENTER);")
						printlist.add("${nodeVariableName}.setSingleLine(true);")
						printlist.add("${nodeVariableName}.setEllipsize(TextUtils.TruncateAt.END);")
						printlist.add("${nodeVariableName}.setTextSize(translateToTextSize(context, 30));")
						printlist.add("${nodeVariableName}.setTextColor(0xffffffff);")
						printlist.add("${nodeVariableName}.setText(\"${text}\");")
						text_list.add(text)
						text_list_level.add("${text} level = ${level}")
					} else if (nodeName == 'LinearLayout') {
						printlist.add("${nodeVariableName}.setOrientation(LinearLayout.VERTICAL);")
					}

					printlist.add((parentVariableName ? "${parentVariableName}." : "") + "addView(${nodeVariableName});")
				}

			}



		}

		if (!is_contentView && rid == 'android:id/content') {
			is_contentView = true
			view_index = 0;

			def m = bounds =~ /\[(.*),(.*)\]\[(.*),(.*)\]/
			def width
			def height

			if(m.find()){

				left = Integer.parseInt(m.group(1))
				right = Integer.parseInt(m.group(3))
				top = Integer.parseInt(m.group(2))
				bottom = Integer.parseInt(m.group(4))
			}
		}

		String name_lower = nodeName.toLowerCase()
		if (name_lower.contains('viewpager') || name_lower.contains('recyclerview') || name_lower.contains('listview')) {
			println"//node ${nodeName} level = ${level}"
		}

		if (this.bounds && this.bounds == bounds) {
			printNode = true

			if (node.children().size() > 0) {
				bounds_list.add(nodeName + " " + bounds)
			}

			node.children().each {
				parse(it, nodeName, nodeVariableName, level, left, right, top, bottom, printNode)
			}
			//			printNode = false
		} else {
			if (tree_name && nodeName == tree_name && tree_name_child_index >= 0) {
				def children = node.children()
				println"//${tree_name} level = ${level}"
				if (children.size() >= tree_name_child_index) {
					if (node.children().size() > 0) {
						bounds_list.add(nodeName + " " + bounds)
					}
					def child = children.get(tree_name_child_index)
					parse(child, nodeName, nodeVariableName,
							level, left, right, top, bottom, printNode)
				}
			}else{

				if (node.children().size() > 0) {
					bounds_list.add(nodeName + " " + bounds)
				}
				node.children().each {
					parse(it, nodeName, nodeVariableName, level, left, right, top, bottom, printNode)
				}
			}
		}





	}

	def translate(length){
		if (length - 1 == 0) {
			return 1;
		}
		return (int)(length / desityPer);
	}


}