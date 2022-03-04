package com.sky.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.constants.Constants;
import com.sky.domain.User;
import com.sky.dto.UserDto;
import com.sky.mapper.RoleMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.UserService;
import com.sky.utils.AppMd5Utils;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author sky
 * @create 2021-05-19 19:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User queryUserByPhone(String phone) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(User.COL_PHONE, phone);
        return this.userMapper.selectOne(qw);
    }

    @Override
    public User getOne(Long userId) {
        return this.userMapper.selectById(userId);
    }

    @Override
    public DataGridView listUserForPage(UserDto userDto) {
        Page<User> page = new Page<>(userDto.getPageNum(), userDto.getPageSize());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(userDto.getUserName()), User.COL_USER_NAME, userDto.getUserName());
        qw.like(StringUtils.isNotBlank(userDto.getPhone()), User.COL_PHONE, userDto.getPhone());
        qw.eq(StringUtils.isNotBlank(userDto.getStatus()), User.COL_STATUS, userDto.getStatus());
        qw.ge(null!= userDto.getBeginTime(), User.COL_CREATE_TIME, userDto.getBeginTime());
        qw.le(null!= userDto.getEndTime(), User.COL_CREATE_TIME, userDto.getEndTime());
        qw.orderByAsc(User.COL_USER_ID); this.userMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int addUser(UserDto userDto) {
        User temp = queryUserByPhone(userDto.getPhone());
        if (temp != null) {
            return 0;
        }
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        user.setUserType(Constants.USER_NORMAL);
        // 设置密码（默认手机号后5位）
        String defaultPwd = user.getPhone().substring(5);
        user.setCreateBy(userDto.getSimpleUser().getUserName());
        user.setCreateTime(DateUtil.date());
        user.setSalt(AppMd5Utils.createSalt());
        user.setPassword(AppMd5Utils.md5(defaultPwd, user.getSalt(),2));
        return this.userMapper.insert(user);
    }

    @Override
    public int updateUser(UserDto userDto) {
        User user = this.userMapper.selectById(userDto.getUserId());
        if(null == user)
            return 0;
        BeanUtil.copyProperties(userDto,user);
        user.setUpdateBy(userDto.getSimpleUser().getUserName());
        return this.userMapper.updateById(user);
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        //根据用户 IDS 删除 sys_role_user 里面的数据
        this.roleMapper.deleteRoleUserByUserIds(ids);
        return this.userMapper.deleteBatchIds(ids);
    }

    @Override
    public int resetPwd(Long[] userIds) {
        int res = 0;

        for(Long userId : userIds) {
            User user = this.userMapper.selectById(userId);
            String defaultPwd;
            if(user.getUserType().equals(Constants.USER_ADMIN)) {
                defaultPwd = "123456";
            } else {
              defaultPwd = user.getPhone().substring(5);
            }
            user.setSalt(AppMd5Utils.createSalt());
            user.setPassword(AppMd5Utils.md5(defaultPwd, user.getSalt(), 2));
            res += this.userMapper.updateById(user);
        }

        return res == userIds.length ? 1 : 0;
    }

    @Override
    public int saveUserRole(Long userId, Long[] roleIds) {
        return 0;
    }

    @Override
    public List<User> selectAllUser() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(User.COL_STATUS, Constants.STATUS_TRUE);
        qw.eq(User.COL_USER_TYPE, Constants.USER_NORMAL);
        qw.orderByAsc(User.COL_USER_ID);
        return this.userMapper.selectList(qw);
    }

    @Override
    public List<User> getUsersNeedScheduling(Long deptId) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(User.COL_DEPT_ID, deptId);
        qw.eq(User.COL_USER_TYPE, Constants.USER_NORMAL);
        qw.eq(User.COL_SCHEDULING_FLAG, Constants.SCHEDULING_FLAG_TRUE);
        qw.orderByAsc(User.COL_USER_ID);
        return this.userMapper.selectList(qw);
    }
}
