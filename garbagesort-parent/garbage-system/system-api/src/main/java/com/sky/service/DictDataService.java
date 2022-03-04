package com.sky.service;

import com.sky.domain.DictData;
import com.sky.dto.DictDataDto;
import com.sky.vo.DataGridView;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-23 21:04
 */
public interface DictDataService {

    // 分页查询字典数据
    DataGridView listPage(DictDataDto dictDataDto);

    // 添加字典数据
    int insert(DictDataDto dictDataDto);

    // 根据字典数据ID查询字典数据
    DictData selectDictDataById(Long dictCode);

    // 更新字典数据
    int update(DictDataDto dictDataDto);

    // 根据字典数据IDs删除字典信息
    int deleteDictDataByIds(Long[] dictCodeIds);

    // 根据字典类型查询字典数据【公共的】
    List<DictData> selectDictDataByDictType(String dictType);

}
