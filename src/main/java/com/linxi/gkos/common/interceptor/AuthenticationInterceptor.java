package com.linxi.gkos.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.annotation.ResultLoginToken;
import com.linxi.gkos.mapper.ResultMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.linxi.gkos.pojo.result.CodeMsg.*;


/**
 * 新增AuthenticationInterceptor拦截器，在controller访问需要进行token验证的路径则会进行拦截处理，如访问/user则会先拦截判断
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从http请求头中取出token
        String token = request.getHeader("Authorization");
        //如果不是映射到方法直接通过(不是映射方法指的是没有说明请求路径的方法，如controller中的普通方法)
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();//获取请求的映射方法
        //检查映射方法是否有passToken注解，有则跳过认证，判断该请求映射方法上是否有passToken注解
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }



        //检查映射方法是否有@UserLoginToken注解，有则进行token认证对比
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()) {
                //执行认证
                if (token == null) {
                    response.setContentType("application/json;charset=UTF-8");
                    //token为空或无token
                    response.getWriter().println(EMPTY_ACCESS_TOKEN);
                    return false;
                }

                token = token.replace("Bearer ","");
                //获取token中的phone
                String phone = "";

                try {
                    phone  = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().println(TOKEN_FORMAT_ERROR);
                    return false;
                }

                UserDto userDto = userMapper.findUserByPhone(phone);
                if(userDto != null){
                    //验证token    verifier:校验机    Algorithm：算法
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userDto.getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().println(TOKEN_WRONG_PASSWORD);
                        return false;
                    }
                    return true;
                }
            }
        }

        //检查映射方法是否有@ResultLoginToken注解，有则进行token认证对比
        if (method.isAnnotationPresent(ResultLoginToken.class)) {
            ResultLoginToken resultLoginToken = method.getAnnotation(ResultLoginToken.class);

            if (resultLoginToken.required()) {
                //执行认证
                if (token == null) {
                    response.setContentType("application/json;charset=UTF-8");
                    //token为空或无token
                    response.getWriter().println(EMPTY_ACCESS_TOKEN);
                    return false;
                }

                token = token.replace("Bearer ","");
                //获取token中的phone
                String phone = "";

                try {
                    phone  = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().println(TOKEN_FORMAT_ERROR);
                    return false;
                }
                ResultDto resultDto = resultMapper.findResultByPhone(phone);
                if(resultDto != null){
                    //验证token    verifier:校验机    Algorithm：算法
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(resultDto.getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().println(TOKEN_WRONG_PASSWORD);
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

}

