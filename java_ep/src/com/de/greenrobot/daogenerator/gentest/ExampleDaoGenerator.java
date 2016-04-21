/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.de.greenrobot.daogenerator.gentest;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

	public static void main(String[] args) throws Exception {
		// generator1();
		// generator2();
		// generator3();
		generator4();
	}

	static void generator1() throws Exception, IOException {
		Schema schema = new Schema(1005, "com.dzl.zyyidianyun.entity");

		addNote(schema);
		addCustomerOrder(schema);

		new DaoGenerator().generateAll(schema, "../yidianyun_dzl/src-gen");
	}

	static void generator2() throws Exception, IOException {
		Schema schema = new Schema(1005, "com.dzl.zyyidianyun.entity");

		Entity note = schema.addEntity("BaseEntity");

		note.addLongProperty("aid").primaryKey();
		note.addStringProperty("title");
		note.addStringProperty("logo_url");
		note.addStringProperty("jump_url");
		note.addStringProperty("desc");
		note.addIntProperty("category_id");
		note.addBooleanProperty("favorite");
		note.addBooleanProperty("is_scan");
		note.addStringProperty("category_name");
		note.addBooleanProperty("is_collect_yun");
		note.addIntProperty("fid");

		new DaoGenerator().generateAll(schema, "../yidianyun_dzl/src-gen");
	}

	static void generator3() throws Exception, IOException {
		Schema schema = new Schema(1001, "com.zhs.haoyangde.entity");

		Entity note = schema.addEntity("Area");
		// private String contact_province;
		// private String contact_city;
		// private String contact_district;
		// private String distance;
		//
		// private String area_id;
		// private String store_id;
		//
		// private String store_name;
		// private String area_name;
		//
		// private String state;
		note.addIntProperty("area_id_db").primaryKey();
		note.addIntProperty("area_id");
		note.addStringProperty("area_name");
		note.addIntProperty("area_parent_id");

		// note.addStringProperty("goods_name");
		// note.addStringProperty("pic");
		// note.addDoubleProperty("price");
		// note.addDoubleProperty("old_price");

		new DaoGenerator().generateAll(schema, "./src-gen");
	}

	private static void generator4() throws Exception, IOException {
		Schema schema = new Schema(1001, "com.zhs.haoyangde.entity");

		Entity note = schema.addEntity("Location");
//		private String contact_province;
//		private String contact_city;
//		private String contact_district;
//		private String distance;
//
//		private Integer area_id;
//		private Integer store_id;
//
//		private String store_name;
//		private String area_name;
//
//		private String state;
//		private Integer area_id_db;
		// "store_id": "13",
		// "area_id": "5025",
		// "area_name": "高新小区2",
		// "store_name": "好样的旗舰店",
		// "state": "2",
		// "contact_province": "广西",
		// "contact_city": "宿迁市",
		// "contact_district": "宿豫区",
		// "distance": "38"
		note.addIntProperty("area_id_db").primaryKey();
		note.addIntProperty("area_id");
		note.addIntProperty("store_id");

		note.addStringProperty("contact_province");
		note.addStringProperty("contact_city");
		note.addStringProperty("contact_district");
		note.addStringProperty("distance");
		note.addStringProperty("area_name");
		note.addStringProperty("store_name");
		note.addIntProperty("state");

		new DaoGenerator().generateAll(schema, "./src-gen");
	}

	// private Long aid;
	// private String title;
	// private String logo_url;
	// private String jump_url;
	// private String desc;
	// private int category_id;
	// private boolean favorite;
	// private boolean is_scan;
	// private String category_name;
	// private boolean is_collect_yun;
	// private Integer fid;

	// private long id;
	//
	// private String name;
	//
	// private String logoUrl;
	//
	// private String jumpUrl;
	//
	// private String desc;
	//
	// private int type;
	//
	// private int categoryID;
	// private int yun;
	// private int scan;
	private static void addNote(Schema schema) {
		Entity note = schema.addEntity("BaseEntity");
		note.addIdProperty();
		note.addStringProperty("name");
		note.addStringProperty("logoUrl");
		note.addStringProperty("jumpUrl");
		note.addStringProperty("desc");
		note.addIntProperty("type");
		note.addIntProperty("categoryID");
		note.addIntProperty("yun");
		note.addIntProperty("scan");
		note.addDateProperty("date");
	}

	private static void addCustomerOrder(Schema schema) {
		Entity customer = schema.addEntity("Customer");
		customer.addIdProperty();
		customer.addStringProperty("name").notNull();

		Entity order = schema.addEntity("Order");
		order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
		order.addIdProperty();
		Property orderDate = order.addDateProperty("date").getProperty();
		Property customerId = order.addLongProperty("customerId").notNull().getProperty();
		order.addToOne(customer, customerId);

		ToMany customerToOrders = customer.addToMany(order, customerId);
		customerToOrders.setName("orders");
		customerToOrders.orderAsc(orderDate);

	}

}
