package com.sacc.comprehensivesystem.common.utils;

import java.util.UUID;

/**
 * 生成唯一主键
 * @author yu.jinbiao
 */
public class UUIDUtils {
    public static String getUUID() {
        /**
         * 新创建一个UUID
         * @return UUID
         */
        return UUID.randomUUID().toString().replace("-","");
    }
}
