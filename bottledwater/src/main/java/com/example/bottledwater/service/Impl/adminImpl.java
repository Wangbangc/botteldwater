package com.example.bottledwater.service.Impl;

import com.example.bottledwater.entity.admin;
import com.example.bottledwater.mapper.adminMapper;
import com.example.bottledwater.service.adminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminImpl implements adminInterface {

    @Autowired
    private adminMapper adminMapper;

    @Override
    public boolean token(String username) {
        if (adminMapper.token(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public admin login(admin rec) {
     return adminMapper.login(rec);
    }

    @Override
    public boolean loginname(admin rec) {
        if (adminMapper.loginname(rec)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(admin rec) {

        if (adminMapper.register(rec)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(admin rec,String usernanme) {
        if (adminMapper.updateAdmin(rec,usernanme)==1){
            return true;
        }
        return false;
    }

}
