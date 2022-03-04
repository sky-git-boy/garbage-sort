package com.sky.config.shiro;

import com.sky.domain.User;
import com.sky.service.UserService;
import com.sky.vo.ActiverUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * 自定义 realm 去匹配用户名和密码
 * @author sky
 * @create 2021-05-19 21:07
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;


    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 做认证  --就是登陆
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户登陆名
        String phone=token.getPrincipal().toString();
        //根据电话查询用户是否存在
        User user = userService.queryUserByPhone(phone);
        if(null != user) {//说明用户存在，但是密码可能不正确
            //组装存放到reids里面的对象
            ActiverUser activerUser=new ActiverUser();
            activerUser.setUser(user);
            //匹配密码
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
                    activerUser,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName()
            );
            System.out.println(info);
            return info;
        }else{
            return null;//代表用户不存在
        }
    }

    /**
     * 做授权  --登陆成功之后判断用户是否有某个菜单或按钮的权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();//身份得到的就是上一个方法的返回值的值 第一个参数activerUser
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        return info;
    }
}
