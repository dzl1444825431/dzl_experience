package com.dzl.groovy


def xmlFilePath = "D:\\baiduYun\\dzl_github\\dzl_experience\\groovy_ep\\src\\com\\dzl\\groovy\\item_footer.xml"

xmlFilePath = 'D:\\work\\workspace_eclipse\\dzl_xml_android\\res\\layout\\activity_about.xml'


def parser = new XmlConvertJavaCode()
parser.convert(xmlFilePath, 0, "ViewGroup", "")




