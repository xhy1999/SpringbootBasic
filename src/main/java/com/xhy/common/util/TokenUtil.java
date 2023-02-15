package com.xhy.common.util;

import cn.hutool.core.util.StrUtil;
import com.xhy.common.constant.CmRedisConst;
import com.xhy.common.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Token工具类
 * @author xuehy
 * @since 2022/12/8
 */
@Slf4j
@Component
public class TokenUtil {

    @Resource
    private JWTUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 检查用户是否已经登陆
     * @param userId 用户编号
     * @return 是否已经登录
     */
    public boolean checkAlreadyLogin(String userId) {
        if (StrUtil.isBlank(userId)) {
            return false;
        }
        return StrUtil.isNotBlank(redisUtil.getHashMapValue(CmRedisConst.USER_KEY_PREFIX + userId, CmRedisConst.TOKEN));
    }

//    /**
//     * 根据用户信息生成Token
//     * @param user {@link CmUserEntity}
//     * @param sessionId sessionId
//     * @return token
//     */
//    public String generateToken(CmUserEntity user, String sessionId) {
//        String token = jwtUtil.createJWT(String.valueOf(user.getUserId()), sessionId);
//        String key = CmRedisConst.USER_KEY_PREFIX + user.getUserId();
//        HashMap<String, String> map = new HashMap<>();
//        map.put(CmRedisConst.TOKEN, token);
//        redisUtil.addHashMap(key, map);
//        redisUtil.setExpireTime(key);
//        return token;
//    }

    /**
     * 用请求头解出userId
     * @param request {@link HttpServletRequest}
     * @return userId
     */
    public String request2UserId(HttpServletRequest request) {
        return token2UserId(request.getHeader(JwtInterceptor.TOKEN_HEAD));
    }

    /**
     * 用token解出userId
     * @param userToken token
     * @return userId
     */
    public String token2UserId(String userToken) {
        try {
            if (StrUtil.isNotBlank(userToken)) {
                return jwtUtil.getUserId(userToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户id获取Redis中的用户token
     * @param userId 用户编号
     * @return token
     */
    public String getUserToken(String userId) {
        if (StrUtil.isBlank(userId)) {
            return null;
        }
        try {
            return redisUtil.getHashMapValue(CmRedisConst.USER_KEY_PREFIX + userId, CmRedisConst.TOKEN);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 刷新Token过期时间
     * @param userId 用户编号
     */
    public void refreshTokenExpire(String userId) {
        redisUtil.setExpireTime(CmRedisConst.USER_KEY_PREFIX + userId);
    }

}
