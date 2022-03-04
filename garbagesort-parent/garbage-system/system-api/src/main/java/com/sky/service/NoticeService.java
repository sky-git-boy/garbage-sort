package com.sky.service;

import com.sky.domain.Notice;
import com.sky.dto.NoticeDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-05-24 21:55
 */
public interface NoticeService {

    /**
     * 分页查询
     */
    DataGridView listNoticePage(NoticeDto noticeDto);

    /**
     * 根据ID查询
     */
    Notice getOne(Long noticeId);

    /**
     * 添加
     */
    int addNotice(NoticeDto noticeDto);

    /**
     * 修改
     */
    int updateNotice(NoticeDto noticeDto);

    /**
     * 根据ID删除
     */
    int deleteNoticeByIds(Long[] noticeIds);
}
