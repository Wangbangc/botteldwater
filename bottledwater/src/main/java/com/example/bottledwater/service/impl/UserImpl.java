package com.example.bottledwater.service.impl;

import com.example.bottledwater.entity.UserWater;
import com.example.bottledwater.mapper.UserWaterMapper;
import com.example.bottledwater.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserInterface {

    @Autowired
    private UserWaterMapper userWaterMapper;

    @Override
    public UserWater selectIdUserwater(int id) {
        return userWaterMapper.selectIdUserwater(id);
    }

    @Override
    public boolean insert(UserWater record) {
        if (userWaterMapper.insert(record) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectusername(UserWater userwater) {
        if (userWaterMapper.selectUsername(userwater) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectPhone(UserWater userwater) {
        if (userWaterMapper.selectPhone(userwater) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectEmail(UserWater userwater) {
        if (userWaterMapper.selectEmail(userwater) != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserWater> selectAll() {
        return userWaterMapper.selectAll();
    }

    @Override
    public boolean updateuser(UserWater userwater) {
        if (userWaterMapper.updateuser(userwater) > 0) {

            return true;
        }
        return false;
    }

    @Override
    public boolean deluser(int id) {
        if (userWaterMapper.deluser(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserWater> selectUser(String sel) {
        return userWaterMapper.selectUser(sel);
    }

}
