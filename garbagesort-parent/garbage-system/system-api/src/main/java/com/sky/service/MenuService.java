package com.sky.service;

import com.sky.domain.Menu;
import com.sky.domain.SimpleUser;
import com.sky.dto.MenuDto;

import java.util.List;

/**
@author sky
@create 2021-05-19 20:07
*/
public interface MenuService {

    /**
     * 查询菜单信息
     * 如果是管理员，那么d直接查询所有菜单和权限
     * 如果登录用户为普通用户，那么根据用户ID关联角色和权限
     * @param isAdmin   是否是管理员
     * @param simpleUser    如果isAdmin为true，simpleUser 可以为空
     */
    List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);

    /**
     * 根据条件查询所有菜单及权限信息
     */
    List<Menu> listAllMenus(MenuDto menuDto);

    /**
     * 根据 ID 查询菜单和权限
     */
    Menu getOne(Long menuId);

    /**
     * 修改菜单和权限信息
     */
    int updateMenu(MenuDto menuDto);

    /**
     * 添加菜单和权限信息
     */
    int addMenu(MenuDto menuDto);

    /**
     * 根据ID删除菜单和权限信息
     */
    int deleteMenuById(Long menuId);

    /**
     * 判断是否有子菜单
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 根据 角色Id 获取菜单
     */
    List<Long> getMenuIdsByRoleId(Long roleId);
}
