package com.sacc.comprehensivesystem.common.utils;

import com.sacc.comprehensivesystem.admin.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64 {
    // BASE64加密
    static Logger logger = LoggerFactory.getLogger(Base64.class);


    public static String Base64Decoder(String unDecoderData) {
        try {
            java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

            byte[] data = encoder.encode(unDecoderData.getBytes());
            return new String(data);
        }catch (Exception e)
        {
            logger.debug("加密失败");
            return "";
        }
    }
    //BASE64解密
    public static String Base64UnDecoder(String DecodedData) {
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            byte[] bytes = decoder.decode(DecodedData);
            return new String(bytes);
        }catch (Exception e)
        {
            logger.debug("加密失败");
            return "";
        }
    }

}
