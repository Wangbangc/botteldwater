package com.example.bottledwater.service.impl;

import com.example.bottledwater.entity.Admin;
import com.example.bottledwater.mapper.AdminMapper;
import com.example.bottledwater.service.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminImpl implements AdminInterface {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean token(String username) {
        if (adminMapper.token(username) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Admin login(Admin rec) {
        return adminMapper.login(rec);
    }

    @Override
    public boolean loginname(Admin rec) {
        if (adminMapper.loginname(rec) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean register(Admin rec) {

        if (adminMapper.register(rec) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin rec, String usernanme) {
        if (adminMapper.updateAdmin(rec, usernanme) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Admin selectUsernameAdmin(String username) {
        return adminMapper.selectUsernameAdmin(username);
    }

}
