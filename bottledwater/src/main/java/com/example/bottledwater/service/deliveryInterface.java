package com.example.bottledwater.service;

import com.example.bottledwater.entity.delivery;

import java.util.List;


public interface deliveryInterface {
    boolean insert(delivery record);

    boolean deleteByPrimaryKey(Integer id);

    boolean selectIdnumber(delivery delivery);

    boolean selectPhone(delivery delivery);

    boolean selectUsername(delivery delivery);

    List<delivery> selectAll();

    List<delivery> selectLike(String sel);

    boolean updateByPrimaryKey(delivery record);

    delivery selectByPrimaryKey(Integer id);
}
