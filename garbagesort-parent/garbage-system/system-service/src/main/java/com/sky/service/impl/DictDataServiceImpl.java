package com.sky.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.constants.Constants;
import com.sky.domain.DictData;
import com.sky.dto.DictDataDto;
import com.sky.mapper.DictDataMapper;
import com.sky.service.DictDataService;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author sky
 * @create 2021-05-23 21:04
 */
@Service
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public DataGridView listPage(DictDataDto dictDataDto) {
        QueryWrapper<DictData> qw = new QueryWrapper<>();
        Page<DictData> page = new Page<>(dictDataDto.getPageNum(), dictDataDto.getPageSize());
        qw.eq(StringUtils.isNotBlank(dictDataDto.getDictType()), DictData.COL_DICT_TYPE,dictDataDto.getDictType());
        qw.like(StringUtils.isNotBlank(dictDataDto.getDictLabel()), DictData.COL_DICT_LABEL,dictDataDto.getDictLabel());
        qw.eq(StringUtils.isNotBlank(dictDataDto.getStatus()), DictData.COL_STATUS,dictDataDto.getStatus());
        this.dictDataMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int insert(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDto, dictData);
        // 设置创建者，创建时间
        dictData.setCreateBy(dictDataDto.getSimpleUser().getUserName());
        dictData.setCreateTime(DateUtil.date());
        return this.dictDataMapper.insert(dictData);
    }

    @Override
    public DictData selectDictDataById(Long dictCode) {
        return this.dictDataMapper.selectById(dictCode);
    }

    @Override
    public int update(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDto, dictData);
        // 设置修改人，修改时间
        dictData.setUpdateBy(dictDataDto.getSimpleUser().getUserName());
        return this.dictDataMapper.updateById(dictData);
    }

    @Override
    public int deleteDictDataByIds(Long[] dictCodeIds) {
        List<Long> ids = Arrays.asList(dictCodeIds);
        if(ids.size() > 0) {
            return this.dictDataMapper.deleteBatchIds(ids);
        } else {
            return -1;
        }
    }

    /**
     * 之前是从数据库中查询
     * 现在是从redis中查询
     */
    @Override
    public List<DictData> selectDictDataByDictType(String dictType) {
        // 从 redis 中查询
        String key = Constants.DICT_REDIS_PROFIX + dictType;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String json = ops.get(key);
        return JSON.parseArray(json, DictData.class);

//        QueryWrapper<DictData> qw = new QueryWrapper<>();
//        qw.eq(DictData.COL_DICT_TYPE,dictType);
//        qw.eq(DictData.COL_STATUS, Constants.STATUS_TRUE); // 可用的
//        return this.dictDataMapper.selectList(qw);
    }
}
