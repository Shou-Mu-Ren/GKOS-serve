package com.linxi.gkos.common.resolver;

import com.auth0.jwt.JWT;
import com.linxi.gkos.common.annotation.LoginResult;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.mapper.ResultMapper;
import com.linxi.gkos.pojo.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.linxi.gkos.pojo.result.CodeMsg.TOKEN_TO_DTO_WRONG;

@Service("resultDtoResolver")
public class ResultDtoResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private ResultMapper mapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginResult.class)
                && methodParameter.getParameterType().isAssignableFrom(ResultDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 获取request
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // request为空，抛出异常
        if(request == null ){
            JsonVos.raise(TOKEN_TO_DTO_WRONG);
        }
        // 从request获取鉴权信息
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ","");
        // 获取鉴权信息对应的登录用户dto
        String phone  = JWT.decode(token).getAudience().get(0);
        ResultDto resultDto = mapper.findResultByPhone(phone);
        if (resultDto == null){
            JsonVos.raise(TOKEN_TO_DTO_WRONG);
        }
        // 返回
        return resultDto;
    }
}
