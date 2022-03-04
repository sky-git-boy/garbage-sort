package com.sky.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * MD5工具类
 * @author sky
 * @create 2021-05-18 18:57
 */
public class AppMd5Utils {
    /**
     * 生成盐
     */
    public static String createSalt(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 生成加密字符串
     */
    public static String md5(String source,String salt,Integer hashIterations){
        return new Md5Hash(source,salt,hashIterations).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("001001","9ED13A792A6B438D9AC3FC1C90F2F06A",2));
    }
}