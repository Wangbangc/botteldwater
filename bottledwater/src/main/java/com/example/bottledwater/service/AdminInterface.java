package com.example.bottledwater.service;

import com.example.bottledwater.entity.Admin;


public interface AdminInterface {
   boolean token(String username);
    Admin login(Admin rec);
    boolean loginname(Admin rec);
    boolean register(Admin rec);
    boolean updateAdmin(Admin rec, String username);
    Admin selectUsernameAdmin(String username);

}
