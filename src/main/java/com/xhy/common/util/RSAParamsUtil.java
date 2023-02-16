package com.xhy.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.util.Base64;

/**
 * RSA参数工具类 只负责解密
 * @author xuehy
 * @since 2022/11/23
 */
public class RSAParamsUtil {

    //Base64编码后的私钥 与RSAResultUtil用不同密钥对
    private static final String PRIVATE_KEY;
    private static final RSA RSA;
    private static final AsymmetricAlgorithm DEFAULT_ALGORITHM_RSA = AsymmetricAlgorithm.RSA_ECB_PKCS1;

    static {
        //TODO 这里的密钥对要重新生成并提供给前端
        PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANfF5kHNUZOumUVApH8YXewzuglexSoXB1NvMZkvz/1Yc9aHiogjVrbFRTA8sloY8A/LiheEw0FUl9jsSFwGzHPKWac4REAQ1y5h8G2L3bQaZBHbaIL4hdjdooTLCE4a+ciEunezW0CylcdLME1i45otsbW8hq5R8BM5TCEx7Cr1AgMBAAECgYAicMd0pVhqDCpmxWSm7LVr0u3U5Q2aJEbaaHCFlP4hNWciokvYYwZKR2k9w9Jr/c2uQmduTZX6QuMMkC+/O06SudHsutmGLEPYWA+/r7aHYQ1KdYB9uB/Khux3y8/qkcxyVeYt9zq9MqivGt8e4dXUuEtecYx/inaJrt7E8nQjGQJBAO2GX9k9skkTIuoEbTkB1y31lmNqe9l810DggiFqxna+rfGnan6IhMkmqPOMk3RH1wchv9BbRfjFhKmqHfn/96sCQQDojmY8mDTJZZkidhDQqMD0FVDmSqmau1jMiWVQIld0KHf8eTx8UKCIlzGz8d2CGH5trXkOmp4AXH5oRRHryUffAkEA6Py/NhvjrvRbcgtni9y3UrisjNYdqCdiCZxvaRIQnNfLTdg3iOimv5zxav0AgrYZQUCko190kmJFEBvzjY+8QwJBAMdGbnjx/Bmvw57yCMqkMGN3eJE93UPH0fnCqlwWcVYJ73ca9Cead1VUKbUGRkdaccvN4HQRLd8lYvQ6a3beTYUCQQCkDH7Nr+m9FJ3rNa4uDKss0V5Td+q78GOP6c+dV1fXZ2E5UXYpYZ/Yj8HF+7+/PUGdM8jrp7RH0Uy6EXXp7/a/";
        RSA = new RSA(DEFAULT_ALGORITHM_RSA.getValue());
        RSA.setPrivateKey(KeyUtil.generatePrivateKey(DEFAULT_ALGORITHM_RSA.getValue(), Base64.getDecoder().decode(PRIVATE_KEY)));
    }

    /**
     * RSA解密
     * @param base64EncryptData 先加密再Base64编码的字符串
     * @return 先用Base64解码,再用私钥解密后的字符串
     */
    public static String decrypt(String base64EncryptData) {
        byte[] decode = Base64.getDecoder().decode(base64EncryptData);
        byte[] decrypt = RSA.decrypt(decode, KeyType.PrivateKey);
        return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println("私钥:" + rsa.getPrivateKeyBase64());
        System.out.println("公钥:" + rsa.getPublicKeyBase64());
        String encryptData = RSAResultUtil.encrypt("Donald Trump 123321 哈哈");
        System.out.println("加密后:" + encryptData);
        System.out.println("解密后:" + decrypt(encryptData));
    }

}
