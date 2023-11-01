package com.example.bottledwater.service.impl;

import com.example.bottledwater.entity.Delivery;
import com.example.bottledwater.mapper.DeliveryMapper;
import com.example.bottledwater.service.DeliveryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryImpl implements DeliveryInterface {
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public boolean insert(Delivery record) {
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
    public boolean selectIdnumber(Delivery delivery) {
        if (deliveryMapper.selectIdnumber(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectPhone(Delivery delivery) {
        if (deliveryMapper.selectPhone(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean selectUsername(Delivery delivery) {
        if (deliveryMapper.selectUsername(delivery) == null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Delivery> selectAll() {
        return deliveryMapper.selectAll();
    }

    @Override
    public List<Delivery> selectLike(String sel) {
        return deliveryMapper.selectLike(sel);
    }

    @Override
    public boolean updateByPrimaryKey(Delivery record) {
        if (deliveryMapper.updateByPrimaryKey(record) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Delivery selectByPrimaryKey(Integer id) {
        return deliveryMapper.selectByPrimaryKey(id);
    }
}
