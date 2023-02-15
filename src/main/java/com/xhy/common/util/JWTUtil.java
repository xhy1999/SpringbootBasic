package com.xhy.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT工具类
 * @author xuehy
 * @since 2022/12/8
 */
@Component
public class JWTUtil {

    private static final String JWT_ID = "Basic";
    private static final String JWT_KEY = "8RP3MzHy2Ivb0y1p";

    public String createJWT(String userId, String sessionId) {
        JwtBuilder builder = Jwts.builder().setId(JWT_ID)
                .setIssuedAt(new Date())
                .claim("userId", userId)
                .claim("sessionId", sessionId)
                .signWith(SignatureAlgorithm.HS256, JWT_KEY);
        return builder.compact();
    }

    //解析JWT
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(jwtStr).getBody();
    }

    //获取用户编号
    public String getUserId(String jwtStr) {
        return parseJWT(jwtStr).get("userId").toString();
    }

}
