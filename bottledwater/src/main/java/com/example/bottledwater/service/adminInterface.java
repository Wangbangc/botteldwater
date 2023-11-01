package com.example.bottledwater.service;

import com.example.bottledwater.entity.admin;


public interface adminInterface {
   boolean token(String username);
    admin login(admin rec);
    boolean loginname(admin rec);
    boolean register(admin rec);
    boolean updateAdmin(admin rec,String username);
    admin selectUsernameAdmin(String username);

}
