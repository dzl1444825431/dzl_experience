package com.dzl.groovy


def xmlFilePath = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\item_footer.xml"

xmlFilePath = 'D:\\work\\workspace_eclipse\\dzl_xml_android\\res\\layout\\frag_myself.xml'
xmlFilePath = 'D:\\baiduYun\\dzl_github\\app1\\yiqiaoquancheng\\res\\layout\\item_footer.xml'
xmlFilePath = 'D:\\baiduYun\\dzl_github\\app1\\yiqiaoquancheng\\res\\layout\\layout_loading.xml'
xmlFilePath = 'D:\\work\\workspace_eclipse\\auto\\res\\layout\\dialog_progress_msg.xml'
xmlFilePath = 'D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\res\\layout\\layout_actionbar.xml'
xmlFilePath = 'D:\\baiduYun\\dzl_github\\yizego\\nongye\\app\\src\\main\\res\\layout\\activity_category.xml'


def parser = new XmlConvertJavaCode()
parser.convert(xmlFilePath)




