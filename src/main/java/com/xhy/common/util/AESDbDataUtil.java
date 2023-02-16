package com.xhy.common.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据库敏感数据加密工具类
 * @author xuehy
 * @since 2023/2/16
 */
@Slf4j
public class AESDbDataUtil {

    private static final String KEY;
    private static final String IV;
    private static final AES AES;

    static {
        //TODO 这里的密钥对要重新生成 key:16个英文字符 iv:无长度要求
        KEY = "h0k%_m,eoir$sn6n";
        IV = "mxC1M!rW/zDY4@9j";
        AES = new AES(Mode.CBC, Padding.PKCS5Padding, KEY.getBytes(), IV.getBytes());
    }

    /**
     * 加密
     * @param msg 需要加密的明文
     * @return 加密后的密文
     */
    public static String encrypt(String msg) {
        return AES.encryptHex(msg);
    }

    /**
     * 解密
     * @param encrypt 需要解密的密文
     * @return 解密后的明文
     */
    public static String decrypt(String encrypt) {
        return AES.decryptStr(encrypt);
    }

    public static void main(String[] args) {
        String msg = "Donald Trump 123321 哈哈";
        String encrypt = encrypt(msg);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt));
    }

}
