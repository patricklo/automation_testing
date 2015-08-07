package com.ibm.testing.mockapplication;

/**
 * Created by patrick on 8/6/2015.
 */
public class Users {

    public static User createValidUser(){
        User user =new User();
        user.withUserName("username").withPassword("password");
        return user;
    }

    public static User createInvalidUser(){
        User user = new User();
        user.withUserName("").withPassword("");
        return user;
    }
}
