package com.example.bottledwater.service.Impl;

import com.example.bottledwater.entity.delivery;
import com.example.bottledwater.mapper.deliveryMapper;
import com.example.bottledwater.service.deliveryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class deliveryImpl implements deliveryInterface {
    @Autowired
    private deliveryMapper deliveryMapper;

    @Override
    public boolean insert(delivery record) {
        if (deliveryMapper.insert(record) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        if (deliveryMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectIdnumber(delivery delivery) {
        if (deliveryMapper.selectIdnumber(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectPhone(delivery delivery) {
        if (deliveryMapper.selectPhone(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectUsername(delivery delivery) {
        if (deliveryMapper.selectUsername(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public List<delivery> selectAll() {
        return deliveryMapper.selectAll();
    }

    @Override
    public List<delivery> selectLike(String sel) {
        return deliveryMapper.selectLike(sel);
    }

    @Override
    public boolean updateByPrimaryKey(delivery record) {
        if (deliveryMapper.updateByPrimaryKey(record) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public delivery selectByPrimaryKey(Integer id) {
        return deliveryMapper.selectByPrimaryKey(id);
    }
}
