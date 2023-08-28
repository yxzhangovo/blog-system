package pers.blog.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: Web配置
 */

public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域路径
                .allowedOriginPatterns("*") // 允许跨域请求的域名
                .allowCredentials(true) // 允许Cookie
                .allowedMethods("GET","POST","DELETE","PUT")    // 允许的请求方式
                .allowedHeaders("*")    // 允许Header属性
                .maxAge(3600);  // 跨域允许时间
    }
}
