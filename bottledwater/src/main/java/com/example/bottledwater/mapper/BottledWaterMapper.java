package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.BottledWater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BottledWaterMapper {
    int insert(BottledWater record);//插入商品
    List<BottledWater> selectAll();//商品展示
    int deleteByPrimaryKey(Integer id);//根据id删除商品
    List<BottledWater> selProduct(String sel);//模糊查询商品
    //id查询
    BottledWater selectByPrimaryKey(Integer id);
//商品查询
    Integer selectIdBottledWater(BottledWater bottledWater);
    int updateByPrimaryKey(BottledWater record);//更新商品信息

}