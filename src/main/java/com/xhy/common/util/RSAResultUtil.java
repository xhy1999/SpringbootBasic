package com.xhy.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.util.Base64;

/**
 * RSA返回值工具类 只负责加密
 * @author xuehy
 * @since 2022/11/23
 */
public class RSAResultUtil {

    //Base64编码后的公钥 与RSAParamsUtil用不同密钥对
    private static final String PUBLIC_KEY;
    private static final RSA RSA;
    private static final AsymmetricAlgorithm DEFAULT_ALGORITHM_RSA = AsymmetricAlgorithm.RSA_ECB_PKCS1;

    static {
        //TODO 这里的密钥对要重新生成并提供给前端
        PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSqrijwi351RqMZ0as7ehRsGD4nQ/HTfeljQHJxOjvgdUbYYOJ4Z8bTWkZ97gCGKqLJw7nXVHfol0k9GasK73KlpF00h9UEkvCt1Hxj2sFC1dvDtj5VxOWwWQxaGtvOo+lM/X4Y5uMg4CkR/otr1MR7A2bAPTpEgSgKJLJqA0c3wIDAQAB";
        RSA = new RSA(DEFAULT_ALGORITHM_RSA.getValue());
        RSA.setPublicKey(KeyUtil.generatePublicKey(DEFAULT_ALGORITHM_RSA.getValue(), Base64.getDecoder().decode(PUBLIC_KEY)));
    }

    /**
     * RSA加密
     * @param data  待加密数据
     * @return  先加密,再Base64编码的字符串
     */
    public static String encrypt(String data) {
        byte[] encrypt  = RSA.encrypt(StrUtil.bytes(data, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println("私钥:" + rsa.getPrivateKeyBase64());
        System.out.println("公钥:" + rsa.getPublicKeyBase64());
        String encryptData = encrypt("Donald Trump 123321");
        System.out.println("加密后:" + encryptData);
        System.out.println("解密后:" + RSAParamsUtil.decrypt(encryptData));
    }

}
