package com.sacc.comprehensivesystem.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSON相关的工具类，用于JavaBean和json字符串的相互转换
 * @author yu.jinbiao
 */
public class JSONUtils {
    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将一个JavaBean转换为Json字符串
     *
     * @param obj 要转换的Java对象
     * @return 转换后的Json字符串
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error While Transform JavaBean to Json: {}", e.getMessage());
        }
        return null;
    }


    /**
     * 将Json字符串转换为Java对象
     *
     * @param jsonStr 要转换的Json串
     * @param cls     目标对象的Java类
     * @param <T>     要转换的Java类型
     * @return 转化后的Java对象
     */
    public static <T> T readValue(String jsonStr, Class<T> cls) {
        try {
            return objectMapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            logger.error("Error While Transform JsonString to JavaBean: {}\n {}", e.getMessage(),
                    e.getStackTrace());
        }
        return null;
    }

    /**
     * 将Json字符串转换为Java对象
     *
     * @param jsonStr 要转换的Json串
     * @param valueTypeRef     类型说明
     * @param <T>     要转换的Java类型
     * @return 转化后的Java对象
     */
    public static <T> T readValue(String jsonStr, TypeReference valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (IOException e) {
            logger.error("Error While Transform JsonString to JavaBean: {}\n {}", e.getMessage(),
                    e.getStackTrace());
        }
        return null;
    }

//    public static <T> T read( jsonObject) {
////        ObjectMapper objectMapper = new ObjectMapper();
////
////    }
}
