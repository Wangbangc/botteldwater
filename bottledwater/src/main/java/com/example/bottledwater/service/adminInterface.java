package com.example.bottledwater.service;

import com.example.bottledwater.entity.admin;
import org.springframework.stereotype.Service;


public interface adminInterface {
   boolean token(String username);
    admin login(admin rec);
    boolean loginname(admin rec);
    boolean register(admin rec);
    boolean updateAdmin(admin rec,String username);
}
