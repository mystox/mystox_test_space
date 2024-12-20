package tech.mystox.test.util;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAKeyPairGenerator {
    public static void main(String[] args) throws Exception {
        // 生成 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA 密钥对生成成功");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey publicKey1 = keyFactory.generatePublic(publicKeySpec);
        // 使用公钥加密密码
        byte[] encryptedBytes = encryptRSA("123456".getBytes(StandardCharsets.UTF_8), publicKey);

        // 将加密结果转换为 Base64 编码的字符串
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("加密后的密码：" + encryptedPassword);
    }


    // 使用公钥加密数据
    public static byte[] encryptRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
}
