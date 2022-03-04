package com.sky.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author sky
 * @create 2021-05-19 20:07
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据菜单 ID 查询它的子节点个数
     */
    Long queryChildCountByMenuId(Long menuId);

    /**
     * 根据角色 ID 查询菜单
     */
    List<Long> queryMenuIdsByRoleId(Long roleId);

    /**
     * 根据用户Id查询用户拥有的菜单信息
     */
    List<Menu> selectMenuListByUserId(@Param("userId") Serializable userId);
}