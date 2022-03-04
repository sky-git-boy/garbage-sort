package com.sky.utils;

import java.io.IOException;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sky.domain.SysSmsLog;
import com.sky.service.SysSmsLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lwx
 * @Date: 2021/9/19 11:48
 */
@Slf4j
@Component
public class TsmsUtil {

    @Value("${tencent.appid}")
    private int appid;

    @Value("${tencent.appkey}")
    private String appkey;

    @Value("${tencent.templateId}")
    private int templateId;

    @Autowired
    private SysSmsLogService sysSmsLogService;

    public String sendSMS(String phoneNumber, String code) {
        String reStr = "wu"; // 定义返回值
        String smsSign = "垃圾分类sky";
        System.out.println("2222");
        try {
            // 参数，一定要对应短信模板中的参数顺序和个数，
            String[] params = {code};
            // 创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            // 发送
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");

            // 构造短信发送记录对象
            SysSmsLog sysSmsLog = new SysSmsLog();
            sysSmsLog.setId(IdGeneratorSnowflake.snowflakeId());
            sysSmsLog.setMobile(phoneNumber);
            sysSmsLog.setCode(code);
            sysSmsLog.setType("0");
            sysSmsLog.setCreateTime(DateUtil.date());

            // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result.toString());

            if (result.result == 0) {
                reStr = "success";
                sysSmsLog.setStatus("0");
            } else {
                reStr = "error";
                sysSmsLog.setStatus("1");
                sysSmsLog.setErrorInfo("error");
            }

            this.sysSmsLogService.save(sysSmsLog);

        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        System.out.println("3333");
        return reStr;
    }


}

