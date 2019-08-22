package com.sacc.comprehensivesystem.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtils {
    private static Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    /**
     * 将原始密码加盐（salt）后进行hash，生成密码的Hash值
     * @param password 原始密码
     * @return Hash后的结果
     */
    public static String generateHash(String password) {
        String randomStr = RandomUtils.getRandomStr(16, 2);
        return randomStr + EncryptionUtils.md5Hex(randomStr + password);
    }

    /**
     * 判断密码是否正确
     * @param password 原始密码
     * @param hashCode 密码加盐（salt）后的hash值
     * @return 密码是否正确
     */
    public static boolean checkHash(String password, String hashCode) {
        if (hashCode.length() < 48) {
            return false;
        }
        String randomStr = hashCode.substring(0, 16);
        return hashCode.substring(16).equals(EncryptionUtils.md5Hex(randomStr + password));
    }
}
