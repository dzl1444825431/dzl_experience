package com.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dzl.fastJson.User;

/**
 * 获取getString 无异常 
 * 属性可赋值到父类
 * @author dzl
 *
 */
public class TestJson {

	static class UserGroup {
		private String name;
		private List<User> users = new ArrayList<User>();

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		@Override
		public String toString() {
			return "UserGroup [name=" + name + ", users=" + users + "]";
		}
	}

	public static void main(String[] args) {
		// 构建用户geust
		User guestUser = new User();
		guestUser.setName("guest");
		guestUser.setAge(28);
		// 构建用户root
		User rootUser = new User();
		rootUser.setName("root");
		guestUser.setAge(35);
		// 构建用户组对象
		UserGroup group = new UserGroup();
		group.setName("admin");
		group.getUsers().add(guestUser);
		group.getUsers().add(rootUser);
		// 用户组对象转JSON串
		String jsonString = JSON.toJSONString(group);
//		System.out.println("jsonString:" + jsonString);
		
		
		
		
		
		
		jsonString = "{\"name11\":\"admin\",\"users11\":[{\"age\":35,\"name\":\"guest\"},{\"age\":0,\"name\":\"root\"}]}";
		
		JSONObject object = JSONObject.parseObject(jsonString);
		
		jsonString = JSON.toJSONString(group);
		object = JSONObject.parseObject(jsonString);
		String string = object.getString("name1111");
		System.out.println(string);
		
		// JSON串转用户组对象
		UserGroup group2 = JSON.parseObject(jsonString, UserGroup.class);
		System.out.println("group2:" + group2);

		// 构建用户对象数组
		User[] users = new User[2];
		users[0] = guestUser;
		users[1] = rootUser;
		// 用户对象数组转JSON串
		String jsonString2 = JSON.toJSONString(users);
		System.out.println("jsonString2:" + jsonString2);
		// JSON串转用户对象列表
		List<User> users2 = JSON.parseArray(jsonString2, User.class);
		System.out.println("users2:" + users2);
		
	}
}
