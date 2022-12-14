//package com.wanfeng.dingyuehao.config;
//
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
//@Component
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //接收XML请求数据
//        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
//        builder.indentOutput(true);
//        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
//    }
//
//}
