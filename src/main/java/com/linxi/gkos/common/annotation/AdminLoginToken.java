package com.linxi.gkos.common.annotation;

import java.lang.annotation.*;

//需要登录才能进行操作的注解MemberLoginToken
@Target({ElementType.METHOD, ElementType.TYPE})//Target注解用途
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminLoginToken {
    //该参数是否必须，如果true的话，当请求中没有传递该参数就报错。
    // 就是请求的url路径上如果需要参数的话是否必须传参(跟@RequestParam中的required是否需要参数一样)
    boolean required() default true;
}
