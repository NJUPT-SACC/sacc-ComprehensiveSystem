package com.sacc.comprehensivesystem.common;

import com.sacc.comprehensivesystem.common.utils.RestResultConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * web配置
 * @author goufaan
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RestResultConverter restResultConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Todo
        converters.add(restResultConverter);
        super.configureMessageConverters(converters);
    }
}
