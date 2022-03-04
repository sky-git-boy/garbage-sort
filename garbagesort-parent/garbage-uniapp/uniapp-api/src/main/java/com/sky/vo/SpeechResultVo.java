package com.sky.vo;

import lombok.Data;

/**
 * 语音识别结果类
 */
@Data
public class SpeechResultVo {
    private String corpus_no;
    private String err_msg;
    private String err_no;
    private String[] result;
    private String sn;
}
