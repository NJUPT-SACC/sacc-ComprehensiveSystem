package com.sacc.comprehensivesystem.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密相关的工具类
 * @author yu.jinbiao
 */
public class EncryptionUtils {
    static Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);

    /**
     * 计算字符串的MD5值
     * @param str 原始数据
     * @return MD5 hash值
     */
    public static String md5Hex(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * 计算字符串的SHA1 Hash值
     * @param str 原始数据
     * @return SHA1 hash值
     */
    public static String sha1Hex(String str){
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 计算字符串的SHA256 Hash值
     * @param str 原始数据
     * @return SHA256 hash值
     */
    public static String sha256Hex(String str){
        return DigestUtils.sha256Hex(str);
    }


    /**
     * 计算字符串的Base64 值
     * @param str 原始数据
     * @return Base64加密值
     */
    public static  String base64Hex(String str){
        return new String(Base64.encodeBase64(str.getBytes()));
    }
}
