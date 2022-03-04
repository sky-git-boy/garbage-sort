package com.sky.controller.system;

import cn.hutool.core.util.RandomUtil;
import com.sky.controller.BaseController;
import com.sky.domain.User;
import com.sky.service.UserService;
import com.sky.utils.TsmsUtil;
import com.sky.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: lwx
 * @Date: 2021/9/8 16:08
 */
@RestController
@RequestMapping("system/resetPassWord")
@Slf4j
public class ResetPasswordController extends BaseController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TsmsUtil tsmsUtil;

    @PostMapping("/sendMail")
    public AjaxResult sendMail(@RequestParam String mobil) {
        System.out.println("发送邮件");
        String context = RandomUtil.randomNumbers(4);
        redisTemplate.opsForValue().set(mobil, context);
        tsmsUtil.sendSMS(mobil, context);
        return AjaxResult.success("发生邮件成功");

    }

    @PostMapping("/reset")
    public AjaxResult resetPassword(@RequestParam String mobil, @RequestParam String code) {

        if (redisTemplate.opsForValue().get(mobil).equals(code)) {
            User user = userService.queryUserByPhone(mobil);
            Long[] userId = new Long[1];
            userId[0] = user.getUserId();
            userService.resetPwd(userId);
            return AjaxResult.success("重置密码成功");
        } else {
            return AjaxResult.fail();
        }

    }

    @PostMapping("/selectUserByMobil")
    public AjaxResult ajaxResult(@RequestParam String mobil) {
        User user = userService.queryUserByPhone(mobil);
        if (user == null) {
            return AjaxResult.fail("查无此用户");
        } else {
            return AjaxResult.success("操作成功");
        }
    }
}

