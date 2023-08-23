package com.linxi.gkos.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtil {
    public static  String getToken(String phone,String password){
        //创建token
        //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        //withAudience()存入需要保存在token的信息，这里把user存入token中     sign：签名
        String token = JWT.create().withAudience(phone).sign(Algorithm.HMAC256(password));
        return token;
    }
}


