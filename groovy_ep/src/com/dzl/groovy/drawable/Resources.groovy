package com.dzl.groovy.drawable

class Resources {

	def private writeFile(path, text){
		new File(path).write(text)
		println path
	}

	/**
	 * android:background="@drawable/bg"
	 * @return
	 */
	def generateDrawableCorners(path, solid, corners = 5){

		def text = """<?xml version="1.0" encoding="utf-8"?>
				<shape xmlns:android="http://schemas.android.com/apk/res/android" >
				<corners android:radius="@dimen/_${corners}px"/>
				<solid android:color="@color/${solid}"/>
				</shape>
				"""

		writeFile(path, text)
	}

	/**
	 * android:background="@drawable/bg"
	 * @return
	 */
	def generateDrawableImage(path, select, normal){

		def text = """<?xml version="1.0" encoding="utf-8"?>
				<selector xmlns:android="http://schemas.android.com/apk/res/android" >
				
				<item android:state_selected="true" android:drawable="@drawable/${select}" />
				<item android:state_pressed="true" android:drawable="@drawable/${select}" />
				<item android:drawable="@drawable/${normal}" />
				
				</selector>
				"""
		writeFile(path, text)
	}

	/**
	 * TextView
	 * android:textColor="@drawable/bg_tab_frag_home_tx"
	 */
	def generateDrawableColorUsShape(path, solid_pressed, solid_normal, corners = 5){

		def text = """<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_pressed="true">
        <shape>
            <corners android:radius="@dimen/_${corners}px" />
            <solid android:color="${solid_pressed}" />
        </shape>
    </item>
    <item>
        <shape>
            <corners android:radius="@dimen/_${corners}px" />
            <solid android:color="${solid_normal}" />
        </shape>
    </item>

</selector>
				"""
		writeFile(path, text)
	}
	
	/**
	 * TextView
	 * android:textColor="@drawable/bg_tab_frag_home_tx"
	 */
	def generateDrawableColor(path, select, normal){
		
		def text = """<?xml version="1.0" encoding="utf-8"?>
				<selector xmlns:android="http://schemas.android.com/apk/res/android" >
				
				<item android:state_selected="true" android:drawable="@color/${select}" />
				<item android:state_pressed="true" android:drawable="@color/${select}" />
				<item android:drawable="@color/${normal}" />
				
				</selector>
				"""
				writeFile(path, text)
	}

	/**
	 * TextView
	 * android:background="@drawable/bg"
	 */
	def generateDrawableColorShape(path, select, normal){

		def text = """<?xml version="1.0" encoding="utf-8"?>
				<selector xmlns:android="http://schemas.android.com/apk/res/android" >
				
				<item android:state_selected="true" android:drawable="@drawable/${select}" />
				<item android:state_pressed="true" android:drawable="@drawable/${select}" />
				<item android:drawable="@drawable/${normal}" />
				
				</selector>
				"""
		writeFile(path, text)
	}
}
