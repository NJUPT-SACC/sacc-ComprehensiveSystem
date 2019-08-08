package com.sacc.comprehensivesystem.common.utils;

import java.util.Random;

public class RandomUtils {
    public static String NUMBER_CHAR = "1234567890";
    public static String LOWER_CHAR = "qwertyuiopasdfghjklzxcvbnm";
    public static String UPPER_CHAR = "QWERTYUIOPASDFGHJKLZXCVBNM";
    public static String SYMBOL_CHAR = "~!@#$%^&*()";
    public static String ALL_CHAR = NUMBER_CHAR + LOWER_CHAR + UPPER_CHAR + SYMBOL_CHAR;
    public static Random RANDOM = new Random();
    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @param type   类型：<br/>
     *               1: 仅数字
     *               2: 数字+字母
     *               3: 数字+大小写字母
     *               4：数字+大小写字母+特殊字符
     * @return 随机数字
     */
    public static String getRandomStr(int length, int type) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(1000000);
            switch (type){
                case 1:
                    sb.append(ALL_CHAR.charAt(randomInt % (NUMBER_CHAR.length())));
                    break;
                case 2:
                    sb.append(ALL_CHAR.charAt(randomInt % (NUMBER_CHAR.length() + LOWER_CHAR.length())));
                    break;
                case 3:
                    sb.append(ALL_CHAR.charAt(randomInt % (NUMBER_CHAR.length() + LOWER_CHAR.length()*2)));
                    break;
                case 4:
                    sb.append(ALL_CHAR.charAt(randomInt % (NUMBER_CHAR.length() + LOWER_CHAR.length()*2 + SYMBOL_CHAR.length())));
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 随机生成0-max之间整数
     * @param max
     * @return
     */
    public static int getRandomInt(int max){
        return RANDOM.nextInt(max);
    }

    /**
     * 随机生成min-max之间整数
     * @param max
     * @param min
     * @return
     */
    public static int getRandomInt(int max, int min){
        return (int)(Math.random()*(max - min + 1) + min);
    }
}
