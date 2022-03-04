package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.mapper.LoginInfoMapper;
import com.sky.mapper.SysSmsLogMapper;
import com.sky.service.DataService;
import com.sky.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sky
 * @create 2021-09-20 20:49
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Autowired
    private SysSmsLogMapper smsLogMapper;

    @Override
    public List<int[]> getWeekDayData() {
        List<int[]> res = new ArrayList<>();
        res.add(dealData(this.loginInfoMapper.getLoginWeekData()));
        res.add(dealData(this.smsLogMapper.getSmsWeekData()));
        return res;
    }

    @Override
    public List<Integer> getCount() {
        List<Integer> res = new ArrayList<>();
        Integer loginCount = loginInfoMapper.selectCount(new QueryWrapper<>());
        Integer smsCount = smsLogMapper.selectCount(new QueryWrapper<>());
        res.add(loginCount);
        res.add(smsCount);
        return res;
    }

    private int[] dealData(List<DataVo> data) {
        int[] res = new int[]{0, 0, 0, 0, 0, 0, 0};
        for (DataVo dataVo : data) {
            res[dataVo.getWeekDay()] = dataVo.getNum();
        }
        return res;
    }
}
