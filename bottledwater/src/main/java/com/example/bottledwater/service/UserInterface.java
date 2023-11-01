package com.example.bottledwater.service;

import com.example.bottledwater.entity.UserWater;

import java.util.List;


public interface UserInterface {
    UserWater selectIdUserwater(int id);

    boolean insert(UserWater record);

    boolean selectusername(UserWater userwater);

    boolean selectPhone(UserWater userwater);

    boolean selectEmail(UserWater userwater);

    List<UserWater> selectAll();

    boolean updateuser(UserWater userwater);

    boolean deluser(int id);

    List<UserWater> selectUser(String sel);
}
