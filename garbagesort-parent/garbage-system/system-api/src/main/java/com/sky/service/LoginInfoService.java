package com.sky.service;

import com.sky.domain.LoginInfo;
import com.sky.dto.LoginInfoDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-05-24 20:42
 */
public interface LoginInfoService {

    /**
     * 添加
     */
    int insertLoginInfo(LoginInfo loginInfo);

    /**
     * 分页查询
     */
    DataGridView listForPage(LoginInfoDto loginInfoDto);

    /**
     * 删除登陆日志
     */
    int deleteLoginInfoByIds(Long[] infoIds);

    /**
     * 清空登陆日志
     */
    int clearLoginInfo();

}
