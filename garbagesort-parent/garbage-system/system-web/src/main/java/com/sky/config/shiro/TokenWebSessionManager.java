package com.sky.config.shiro;

import com.sky.constants.Constants;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * 如果没有 token 生成一个返回到前台
 * 如果有就从请求头里面取出来
 * @author sky
 * @create 2021-05-19 20:45
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 从头里面得到请求 TOKEN 如果不存在就生成一个
        String token = WebUtils.toHttp(request).getHeader(Constants.TOKEN);
        if(StringUtils.hasLength(token)) {
            return token;
        }
        return UUID.randomUUID().toString() ;
    }
}
