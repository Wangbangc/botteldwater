package com.example.bottledwater.service.impl;

import com.example.bottledwater.entity.BottledWater;
import com.example.bottledwater.mapper.BottledWaterMapper;
import com.example.bottledwater.service.BottledWaterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottledWaterImpl implements BottledWaterInterface {
    @Autowired
    private BottledWaterMapper bottledWaterMapper;

    @Override
    public boolean insert(BottledWater record) {
        try {
            int result = bottledWaterMapper.insert(record);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BottledWater selectByPrimaryKey(Integer id) {
        return bottledWaterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BottledWater> getAllBottledWater() {
        return bottledWaterMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(BottledWater record) {
        return bottledWaterMapper.updateByPrimaryKey(record) > 0;
    }

    @Override
    public List<BottledWater> selProduct(String sel) {
        return bottledWaterMapper.selProduct(sel);
    }
}
