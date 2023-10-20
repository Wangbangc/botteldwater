package com.example.bottledwater.service.Impl;

import com.example.bottledwater.entity.userwater;
import com.example.bottledwater.mapper.userwaterMapper;
import com.example.bottledwater.service.userInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userImpl implements userInterface {
    @Autowired
    private userwaterMapper userwaterMapper;
    @Override
    public boolean insert(userwater record) {
        if (userwaterMapper.insert(record)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean selectusername(userwater userwater) {
        if (userwaterMapper.selectUsername(userwater)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean selectPhone(userwater userwater) {
        if (userwaterMapper.selectPhone(userwater)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean selectEmail(userwater userwater) {
        if (userwaterMapper.selectEmail(userwater)!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<userwater> selectAll() {
        return userwaterMapper.selectAll();
    }

    @Override
    public boolean updateuser(userwater userwater) {
        if (userwaterMapper.updateuser(userwater)>0){

            return true;
        }return false;
    }

    @Override
    public boolean deluser(int id) {
        if (userwaterMapper.deluser(id)>0){
            return true;
        }return false;
    }

    @Override
    public List<userwater> selectUser(String sel) {
        return userwaterMapper.selectUser(sel);
    }

}
