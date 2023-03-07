package com.xhy.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhy.common.constant.CryptoConst;
import com.xhy.common.mapper.CmAdminMapper;
import com.xhy.common.pojo.entity.CmAdminEntity;
import com.xhy.common.pojo.params.CmAdminChangePassParams;
import com.xhy.common.pojo.params.CmAdminLoginParams;
import com.xhy.common.service.CmAdminService;
import com.xhy.common.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuehy
 * @since 2023/2/15
 */
@Service
public class CmAdminServiceImpl extends ServiceImpl<CmAdminMapper, CmAdminEntity> implements CmAdminService {

    @Value("${project.is-product}")
    private boolean isProduct;

    @Resource
    private TokenAdminHelper tokenAdminHelper;

    @Override
    public Result login(CmAdminLoginParams params, HttpServletRequest request) {
        List<CmAdminEntity> adminList = this.baseMapper.selectList(
                Wrappers.<CmAdminEntity>lambdaQuery()
                        .eq(CmAdminEntity::getAccount, params.getAccount())
        );
        if (adminList.isEmpty()) {
            return Result.errParam("账号或密码错误");
        }
        CmAdminEntity adminEntity = adminList.get(0);
        String adminId = adminEntity.getId();
        try {
            String password = CryptoConst.SHA256.digestHex(adminId + RSAParamsUtil.decrypt(params.getPassword()));
            if (!adminEntity.getPassword().equals(password)) {
                return Result.errParam("账号或密码错误");
            }
        } catch (Exception e) {
            if (isProduct) {
                return Result.errParam("账号或密码错误");
            } else {
                //若不是生产环境,则尝试不解密的密码
                String password = PasswordUtil.encode(adminId, params.getPassword());
                if (!adminEntity.getPassword().equals(password)) {
                    return Result.errParam("账号或密码错误");
                }
            }
        }
        if (CmAdminEntity.DISABLED.equals(adminEntity.getEnabled())) {
            return Result.errParam("此账号被禁用");
        }
        Map<String, String> resMap = new HashMap<>();
        if (tokenAdminHelper.checkAlreadyLogin(adminId)) {
            resMap.put("token", tokenAdminHelper.getAdminToken(adminId));
        } else {
            resMap.put("token", tokenAdminHelper.generateToken(adminEntity, request.getSession().getId()));
        }
        resMap.put("adminId", adminEntity.getId());
        resMap.put("isSuperAdmin", adminEntity.getIsSuperAdmin());
        //设置最后登录信息
        adminEntity.setLastLoginIp(IPUtil.getIpAddr(request));
        adminEntity.setLastLoginTime(new Date());
        this.baseMapper.updateById(adminEntity);
        return Result.success(resMap);
    }

    @Override
    public Result changePass(CmAdminChangePassParams params, HttpServletRequest request) {
        String adminId = tokenAdminHelper.request2AdminId(request);
        if (StrUtil.isBlank(adminId)) {
            return Result.errParam("账号验证失败");
        }
        String newPassword = "", oldPassword = "";
        try {
            newPassword = RSAParamsUtil.decrypt(params.getNewAdminPass());
            oldPassword = RSAParamsUtil.decrypt(params.getOldAdminPass());
            oldPassword = PasswordUtil.encode(adminId, oldPassword);
        } catch (Exception e) {
            return Result.errParam("参数验证失败");
        }
        if (!PasswordUtil.checkPassword(newPassword)) {
            return Result.errParam("新密码不符合要求");
        }
        CmAdminEntity adminEntity = this.baseMapper.selectById(adminId);
        if (!adminEntity.getPassword().equals(oldPassword)) {
            return Result.errParam("账号或密码错误");
        }
        newPassword = PasswordUtil.encode(adminId, newPassword);
        adminEntity.setPassword(newPassword);
        this.baseMapper.updateById(adminEntity);
        return Result.success();
    }

}
