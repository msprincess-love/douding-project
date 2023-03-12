package com.douding.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpingMvcConfig implements WebMvcConfigurer {
    @Value("${file.path}")
    private String FILE_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //所有带/f/ 都认为是静态资源文件
        registry.addResourceHandler("/f/**").addResourceLocations("file:"+FILE_PATH);
    //例如: http://127.0.0.1:9003/file/f/teachers/fCY6376B-1.jpeg
        //浏览器输入 就可以看到图片了
    }
}
