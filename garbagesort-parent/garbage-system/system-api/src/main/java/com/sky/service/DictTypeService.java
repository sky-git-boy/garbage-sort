package com.sky.service;

import com.sky.domain.DictType;
import com.sky.dto.DictTypeDto;
import com.sky.vo.DataGridView;

/**
@author sky
@create 2021-05-20 15:56
*/
public interface DictTypeService {
    /**
     * 分页查询字典类型
     * （含条件查询）
     */
    DataGridView listPage(DictTypeDto dictTypeDto);

    /**
     * 查询所有字典类型（可用）
     */
    DataGridView list();

    /**
     * 检查字典类型是否存在
     */
    Boolean checkDictTypeUnique(Long dictId, String dictType);

    /**
     * 添加字典类型
     */
    int insert(DictTypeDto dictTypeDto);

    /**
     * 更新字典类型
     */
    int update(DictTypeDto dictTypeDto);

    /**
     * 根据 ID 批量删除字典类型
     */
    int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 根据 ID 查询一个字典类型
     */
    DictType selectDictTypeById(Long dictId);

    /**
     * 同步缓存
     */
    void dictCacheAsync();
}

