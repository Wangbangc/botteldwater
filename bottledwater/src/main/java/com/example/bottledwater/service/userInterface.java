package com.example.bottledwater.service;

import com.example.bottledwater.entity.userwater;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userInterface {
    userwater selectIdUserwater(int id);
    boolean insert(userwater record);
    boolean selectusername(userwater userwater);
    boolean selectPhone(userwater userwater);
    boolean selectEmail(userwater userwater);
    List<userwater> selectAll();
    boolean updateuser(userwater userwater);
    boolean deluser(int id);
    List<userwater> selectUser(String sel);
}
