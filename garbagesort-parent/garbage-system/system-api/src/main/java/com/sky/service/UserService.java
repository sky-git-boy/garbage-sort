package com.sky.service;

import com.sky.domain.User;
import com.sky.dto.UserDto;
import com.sky.vo.DataGridView;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-19 19:56
 */
public interface UserService {
    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     */
    User queryUserByPhone(String phone);

    /**
     * 根据用户 ID 查询用户
     *
     * @param userId 用户ID
     */
    User getOne(Long userId);

    /**
     * 分页查询用户信息
     */
    DataGridView listUserForPage(UserDto userDto);

    /**
     * 添加用户
     */
    int addUser(UserDto userDto);

    /**
     * 修改用户信息
     */
    int updateUser(UserDto userDto);

    /**
     * 根据 ID 删除用户信息
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 重置用户密码
     */
    int resetPwd(Long[] userIds);

    /**
     * 保存用户和角色之间的关系
     */
    int saveUserRole(Long userId, Long[] roleIds);

    /**
     * 查询所有可用用户信息
     */
    List<User> selectAllUser();

    /**
     * 查询需要排班的医生信息
     */
    List<User> getUsersNeedScheduling(Long deptId);
}
