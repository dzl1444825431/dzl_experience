package com.dzl.fastJson;

public class User extends User2{
	private String name1;
	private int age;

	public String getName1() {
		return name1;
	}

	public void setName1(String name) {
		this.name1 = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name1 + ", age=" + age + "] superName = " + super.getName();
	}
}