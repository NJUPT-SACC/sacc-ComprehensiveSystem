package com.sacc.ComprehensiveSystem.admin.shrio.tools;

public class Base64 {
    // BASE64加密
    public String base64decoder(String unDecoderData) {

        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

        byte[] data = encoder.encode(unDecoderData.getBytes());
        return new String(data);
    }
    //BASE64解密
    public String Base64Decoder(String DecodedData) {
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        byte[] bytes = decoder.decode(DecodedData);
        return new String(bytes);
    }

}
