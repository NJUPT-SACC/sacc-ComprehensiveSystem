package com.sacc.comprehensivesystem.common.utils;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 把不是RestResult的返回结果包装成RestResult
 * @author goufaan
 */
@ControllerAdvice
@Component
public class RestResultConverter extends MappingJackson2HttpMessageConverter implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //参照 StringHttpMessageConverter 修复了 String 为参数时，read发生错误的问题
        if(type == String.class){
            return StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        }
        else{
            return super.read(type, contextClass, inputMessage);
        }
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (!(object instanceof RestResult)){
            RestResult<Object> restResult = new RestResult<>();
            restResult.setStatus(RestResult.STATUS_SUCCESS);
            restResult.setMessage("success");
            restResult.setData(object);
            object = restResult;
        }
        super.writeInternal(object, type, outputMessage);
    }
}
