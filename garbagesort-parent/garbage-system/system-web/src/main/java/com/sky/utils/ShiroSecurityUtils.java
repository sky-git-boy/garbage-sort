package com.sky.utils;

import com.sky.constants.Constants;
import com.sky.domain.SimpleUser;
import com.sky.domain.User;
import com.sky.vo.ActiverUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-23 17:13
 */
public class ShiroSecurityUtils {

    // 得到当前登录的用户对象 ActiveUser
    public static ActiverUser getCurrentActiveUser() {
        Subject subject = SecurityUtils.getSubject();
        return (ActiverUser) subject.getPrincipal();
    }

    // 得到当前登陆的用户对象 User
    public static User getCurrentUser() { return getCurrentActiveUser().getUser(); }

    // 得到当前登陆的用户对象 SimpleUser
    public static SimpleUser getCurrentSimpleUser() {
        User user = getCurrentActiveUser().getUser();
        return new SimpleUser(user.getUserId(),user.getUserName());
    }

    // 得到当前登陆的用户名称
    public static String getCurrentUserName() {
        return getCurrentActiveUser().getUser().getUserName();
    }

    // 得到当前登陆对象的角色编码
    public static List<String> getCurrentUserRoles() { return getCurrentActiveUser().getRoles(); }

    // 得到当前登陆对象的权限编码
    public static List<String> getCurrentUserPermissions() { return getCurrentActiveUser().getPermissions(); }

    // 判断当前用户是否是超级管理员
    public static boolean isAdmin() { return getCurrentUser().getUserType().equals(Constants.USER_ADMIN); }
}
