package com.ibm.autotest.mockapplication;

public class Users {
	public static User createValidUser(){
		User user = new User();
		user.withUserName("username").withPassword("password");
		return user;
	}
	
	public static User createInvalidUser(){
		User user = new User();
		user.withUserName("").withPassword("");
		return user;
	}
}
