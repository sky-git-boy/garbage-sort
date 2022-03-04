package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-25 9:49
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色 IDS 删除 sys_role_menu 中间表的数据
     */
    void deleteRoleMenuByRoleIds(@Param("ids") List<Long> rids);

    /**
     * 根据角色 IDS 删除 sys_role_user 中间表的数据
     */
    void deleteRoleUserByRoleIds(@Param("ids") List<Long> rids);

    /**
     * 保存角色和菜单权限关系
     */
    void saveRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    /**
     * 根据用户 ID 删除 sys_role_user 中间表的数据
     */
    void deleteRoleUserByUserIds(@Param("ids") List<Long> ids);

    /**
     * 根据菜单权限 ID 删除 sys_role_menu
     */
    void deleteRoleMenuByMenuIds(@Param("ids") List<Long> ids);

    /**
     * 根据用户 ID 查询角色
     */
    List<Long> selectRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 保存角色和用户的关系
     */
    void saveRoleUser(@Param("userId") Long userId, @Param("roleId") Long roleId);
}