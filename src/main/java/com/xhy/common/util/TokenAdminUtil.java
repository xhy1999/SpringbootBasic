package com.xhy.common.util;

import cn.hutool.core.util.StrUtil;
import com.xhy.common.constant.CmRedisConst;
import com.xhy.common.interceptor.JwtInterceptor;
import com.xhy.common.pojo.entity.CmAdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 管理员Token工具类
 * @author xuehy
 * @since 2023/1/4
 */
@Slf4j
@Component
public class TokenAdminUtil {

    @Resource
    private JWTUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 检查用户是否已经登陆
     * @param adminId 管理员编号
     * @return 是否已经登录
     */
    public boolean checkAlreadyLogin(String adminId) {
        if (StrUtil.isBlank(adminId)) {
            return false;
        }
        return StrUtil.isNotBlank(redisUtil.getHashMapValue(CmRedisConst.ADMIN_KEY_PREFIX + adminId, CmRedisConst.TOKEN));
    }

    /**
     * 根据用户信息生成Token
     * @param admin {@link CmAdminEntity}
     * @param sessionId sessionId
     * @return token
     */
    public String generateToken(CmAdminEntity admin, String sessionId) {
        String token = jwtUtil.createAdminJwt(admin.getId(), admin.getAccount(), sessionId, admin.getIsSuperAdmin());
        String key = CmRedisConst.ADMIN_KEY_PREFIX + admin.getId();
        HashMap<String, String> map = new HashMap<>();
        map.put(CmRedisConst.TOKEN, token);
        map.put(CmRedisConst.IS_SUPER_ADMIN, admin.getIsSuperAdmin());
        redisUtil.addHashMap(key, map);
        redisUtil.setExpireTime(key);
        return token;
    }

    /**
     * 用请求头解出adminId
     * @param request {@link HttpServletRequest}
     * @return adminId
     */
    public String request2AdminId(HttpServletRequest request) {
        return request == null ? null : token2AdminId(request.getHeader(JwtInterceptor.TOKEN_HEAD));
    }

    /**
     * 用token解出adminId
     * @param adminToken token
     * @return adminId
     */
    public String token2AdminId(String adminToken) {
        try {
            if (StrUtil.isNotBlank(adminToken)) {
                if (adminToken.startsWith("Token=")) {
                    adminToken = adminToken.substring(6);
                }
                return jwtUtil.getAdminId(adminToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据adminId获取Redis中的用户token
     * @param adminId 管理员编号
     * @return token
     */
    public String getAdminToken(String adminId) {
        if (StrUtil.isBlank(adminId)) {
            return null;
        }
        try {
            return redisUtil.getHashMapValue(CmRedisConst.ADMIN_KEY_PREFIX + adminId, CmRedisConst.TOKEN);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 刷新Token过期时间
     * @param adminId 管理员编号
     */
    public void refreshTokenExpire(String adminId) {
        redisUtil.setExpireTime(CmRedisConst.ADMIN_KEY_PREFIX + adminId);
    }

}
