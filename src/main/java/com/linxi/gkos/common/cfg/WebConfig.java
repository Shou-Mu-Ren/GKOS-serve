package com.linxi.gkos.common.cfg;


import com.linxi.gkos.common.interceptor.AuthenticationInterceptor;
import com.linxi.gkos.common.resolver.UserDtoResolver;
import com.linxi.gkos.pojo.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private UserDtoResolver userDtoResolver;

//    跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        **是路径全局跨域
        registry.addMapping("/**")
//                开发那些ip、端口、域名
                .allowedOrigins("*")
//                是否跨域发cookie
                .allowCredentials(true)
//                允许那些方法
                .allowedMethods("GET","POST")
//                可以有那些头信息
                .allowedHeaders("*");
    }

//    登录拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截规则     addInterceptor():需要传入一个拦截器，可以是自定义拦截规则
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authenticationInterceptor());
        //addPathPatterns("/**")所有的请求都拦截
        InterceptorRegistration register = interceptorRegistration.addPathPatterns("/**");
        //上一步拦截所有然后得到的也是一个InterceptorRegistration，调用excludePathPatterns方法指定可以放行的路径
//        register.excludePathPatterns("");
    }

    @Bean//AuthenticationInterceptor自定义的认证拦截器，自动配置并注入spring管理
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userDtoResolver);
    }

}
