package com.sky.service;


import com.sky.domain.Role;
import com.sky.dto.RoleDto;
import com.sky.vo.DataGridView;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-25 9:49
 */
public interface RoleService {

    /**
     * 分页查询角色
     */
    DataGridView listRolePage(RoleDto roleDto);

    /**
     * 查询所有可用角色
     */
    List<Role> listAllRoles();

    /**
     * 根据ID查询角色
     */
    Role getOne(Long roleId);

    /**
     * 添加一个角色
     */
    int addRole(RoleDto roleDto);

    /**
     * 修改角色
     */
    int updateRole(RoleDto roleDto);

    /**
     * 根据角色ID删除角色
     */
    int deleteRoleByIds(Long[] roleIds);

    /**
     * 保存角色和菜单之间的关系
     */
    void saveRoleMenu(Long roleId, Long[] menuIds);

    /**
     *根据用户ID查询用户拥有的角色IDS
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 保存角色和用户之间的关系
     */
    void saveRoleUser(Long userId, Long[] roleIds);

}
