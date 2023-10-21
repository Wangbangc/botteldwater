package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.bottledWater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface bottledWaterMapper {
    int insert(bottledWater record);//插入商品
    List<bottledWater> selectAll();//商品展示
    int deleteByPrimaryKey(Integer id);//根据id删除商品

    //id查询
    bottledWater selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(bottledWater record);//更新商品信息

}