package com.ziggs.ziggs_backend.config;

import com.ziggs.ziggs_backend.security.FirebaseAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirebaseAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/users/create-user",
                        "/devices",
                        "/**"
                );
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
