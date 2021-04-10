package com.example.helpinghand.helpers;



public class UserLoginCache {
    private static String user;


    public static void login(String username) {
       user = username;
    }

    public static String getLoggedUser() {
        return user;
    }
}
