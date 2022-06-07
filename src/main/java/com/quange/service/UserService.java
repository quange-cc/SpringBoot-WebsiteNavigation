package com.quange.service;

public interface UserService {

    boolean checkUser(String username, String password);

    boolean updatePasswd(String s);
}
