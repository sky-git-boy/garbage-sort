package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.LoginInfo;
import com.sky.vo.DataVo;

import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @create 2021-05-24 20:42
 */
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {
    // 获取本周每天的登录次数
    List<DataVo> getLoginWeekData();
}