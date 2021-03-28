package com.example.kbcomic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cbs.imagesPath}")
    private String imagesPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("图片文件路径:"+imagesPath);
        registry.addResourceHandler("/comics/**").addResourceLocations(imagesPath);
    }
}
