package com.example.bottledwater.service;

import com.example.bottledwater.entity.Delivery;

import java.util.List;


public interface DeliveryInterface {
    boolean insert(Delivery record);

    boolean deleteByPrimaryKey(Integer id);

    boolean selectIdnumber(Delivery delivery);

    boolean selectPhone(Delivery delivery);

    boolean selectUsername(Delivery delivery);

    List<Delivery> selectAll();

    List<Delivery> selectLike(String sel);

    boolean updateByPrimaryKey(Delivery record);

    Delivery selectByPrimaryKey(Integer id);
}
