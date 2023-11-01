package com.example.bottledwater.service;

import com.example.bottledwater.entity.BottledWater;

import java.util.List;

public interface BottledWaterInterface {
    boolean insert(BottledWater record);

    BottledWater selectByPrimaryKey(Integer id);

    List<BottledWater> getAllBottledWater(); //商品展示

    boolean updateByPrimaryKey(BottledWater record); //更新商品信息

    List<BottledWater> selProduct(String sel); //模糊查询商品
}
