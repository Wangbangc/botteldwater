package com.example.bottledwater.service;

import com.example.bottledwater.entity.bottledWater;

import java.util.List;

public interface bottledWaterInterface {
    boolean insert(bottledWater record);
    bottledWater selectByPrimaryKey(Integer id);
    List<bottledWater> getAllBottledWater();//商品展示
   boolean updateByPrimaryKey(bottledWater record);//更新商品信息
    List<bottledWater> selProduct(String sel);//模糊查询商品
}
