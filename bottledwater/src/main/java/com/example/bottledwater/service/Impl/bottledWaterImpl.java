package com.example.bottledwater.service.Impl;

import com.example.bottledwater.entity.bottledWater;
import com.example.bottledwater.mapper.bottledWaterMapper;
import com.example.bottledwater.service.bottledWaterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bottledWaterImpl implements bottledWaterInterface {
    @Autowired
    private bottledWaterMapper bottledWaterMapper;

    @Override
    public boolean insert(bottledWater record) {
        try {
            int result = bottledWaterMapper.insert(record);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public bottledWater selectByPrimaryKey(Integer id) {
        return bottledWaterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<bottledWater> getAllBottledWater() {
        return bottledWaterMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(bottledWater record) {
       if (bottledWaterMapper.updateByPrimaryKey(record)>0) return true;
       else return false;
    }
}
