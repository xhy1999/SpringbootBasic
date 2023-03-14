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
public class JWTHelper {

    //TODO 这里的id和key都要修改
    private static final String JWT_ID = "Basic";
    private static final String JWT_KEY = "8RP3MzHy2Ivb0y1p";

    public static final String NAME_ACCOUNT = "account";
    public static final String NAME_ADMIN_ID = "adminId";
    public static final String NAME_SESSION_ID = "sessionId";
    public static final String NAME_ROLE = "roleId";

    //创建管理员JWT
    public String createAdminJwt(String adminId, String account, String sessionId, Integer roleId) {
        JwtBuilder builder = Jwts.builder().setId(JWT_ID)
                .setIssuedAt(new Date())
                .claim(NAME_ACCOUNT, account)
                .claim(NAME_ADMIN_ID, adminId)
                .claim(NAME_SESSION_ID, sessionId)
                .claim(NAME_ROLE, roleId)
                .signWith(SignatureAlgorithm.HS256, JWT_KEY);
        return builder.compact();
    }

    //解析JWT
    public Claims parseJwt(String jwtStr) {
        return Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(jwtStr).getBody();
    }

    //******************************** 获取信息

    public String getAccount(String jwtStr) {
        return (String) parseJwt(jwtStr).get(NAME_ACCOUNT);
    }

    public String getAdminId(String jwtStr) {
        return (String) parseJwt(jwtStr).get(NAME_ADMIN_ID);
    }

    public Integer getRoleId(String jwtStr) {
        return (Integer) parseJwt(jwtStr).get(NAME_ROLE);
    }

}
