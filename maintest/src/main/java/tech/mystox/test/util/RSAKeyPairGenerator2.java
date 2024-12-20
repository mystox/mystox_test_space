package tech.mystox.test.util;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class RSAKeyPairGenerator2 {
    public static void main(String[] args) throws Exception {
        // 生成 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512); // 设置密钥长度
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        BigInteger modulus1 = aPublic.getModulus();
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        BigInteger modulus2 = aPrivate.getModulus();
        BigInteger publicExponent = aPublic.getPublicExponent();
        System.out.println(publicExponent);
        BigInteger privateExponent = aPrivate.getPrivateExponent();
        System.out.println(privateExponent);
        System.out.println(modulus1);
        System.out.println(modulus2);
//        String mod = "93047301809631402599910794060017464983349688364761399395782751728309137178381945435566919985857389289889244630186740701862074946684760815260454275695771888834476450131391951278128691898478276981237635569505254740444050443218344339199139533371394214604476794361673827840530368224527172722137201278130179811479";
//        BigInteger modulus = new BigInteger(mod);
//        BigInteger exponent = new BigInteger("65537");
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus1, privateExponent);
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus2, publicExponent);


        //转rsaprivatecrtkey


//        privateKeySpec = new RSAPrivateKeySpec(
//                new BigInteger("23579130019487386168225989536902910265782243250446612949949167426492405799306128166838021898863941598321037739792944494221317108995718532945622924998377762584241507938988322774740590001984000283347524747016694769849945455248944173413893001446588713025069214060798585509182355279521955750662306285574666420573635833234999683633374898417081389607282807186074392224763587855185942549279461773974435705463544679989337521804290807116046273625002422730366285869319966555197311921109204135261867074855177383612818853056056651287708316733768952397214679354027545591710387496934767212044895323922759596100379526116058124851231"),
//                new BigInteger("16316540664262515069582325274090725597196858471565746736853146954527321900671868082064652503705977011847618635844634721721027087142603890742892492357011000060392398592261767034762965915131550068664931180889484176991087116300637307299593535081011409194804674105791791681446038028618951359511211260159859267885635548965953723585367621781659946232596754985809611157059509757789656567709268885725624328635144032874626683468693430829094323854654723039113359392902772112319580864708930241594571639986257291785204585815823637553638803925982960946479948702251007949538207079566853436536271732822980017852414762728462808407569"));
//        publicKeySpec = new RSAPublicKeySpec(
//                new BigInteger("23579130019487386168225989536902910265782243250446612949949167426492405799306128166838021898863941598321037739792944494221317108995718532945622924998377762584241507938988322774740590001984000283347524747016694769849945455248944173413893001446588713025069214060798585509182355279521955750662306285574666420573635833234999683633374898417081389607282807186074392224763587855185942549279461773974435705463544679989337521804290807116046273625002422730366285869319966555197311921109204135261867074855177383612818853056056651287708316733768952397214679354027545591710387496934767212044895323922759596100379526116058124851231"),
//                new BigInteger("65537"));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 获取公钥和私钥
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        RSAPrivateCrtKey rsaPrivateCrtKey = createCrtKey((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        System.out.println("RSA 密钥对生成成功");
        System.out.println("公钥:\n"+Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("私钥:\n"+Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("私钥CRT:\n"+Base64.getEncoder().encodeToString(rsaPrivateCrtKey.getEncoded()));
        System.out.println(privateKey.getAlgorithm());
        System.out.println(privateKey.getFormat());
        System.out.println(privateKey.getClass());

//        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey publicKey1 = keyFactory.generatePublic(publicKeySpec);
        // 使用公钥加密密码
        byte[] encryptedBytes = encryptRSA("123456".getBytes(StandardCharsets.UTF_8), publicKey);

        // 将加密结果转换为 Base64 编码的字符串
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("加密后的密码：\n" + encryptedPassword);
    }


    // 使用公钥加密数据
    public static byte[] encryptRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }


    /**
     * Find a factor of n by following the algorithm outlined in Handbook of Applied Cryptography, section
     * 8.2.2(i). See https://cacr.uwaterloo.ca/hac/about/chap8.pdf.
     *
     */

    private static BigInteger findFactor(BigInteger e, BigInteger d, BigInteger n) {
        BigInteger edMinus1 = e.multiply(d).subtract(BigInteger.ONE);
        int s = edMinus1.getLowestSetBit();
        BigInteger t = edMinus1.shiftRight(s);

        for (int aInt = 2; true; aInt++) {
            BigInteger aPow = BigInteger.valueOf(aInt).modPow(t, n);
            for (int i = 1; i <= s; i++) {
                if (aPow.equals(BigInteger.ONE)) {
                    break;
                }
                if (aPow.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
                BigInteger aPowSquared = aPow.multiply(aPow).mod(n);
                if (aPowSquared.equals(BigInteger.ONE)) {
                    return aPow.subtract(BigInteger.ONE).gcd(n);
                }
                aPow = aPowSquared;
            }
        }

    }

    public static RSAPrivateCrtKey createCrtKey(RSAPublicKey rsaPub, RSAPrivateKey rsaPriv) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeySpecException {

        BigInteger e = rsaPub.getPublicExponent();
        BigInteger d = rsaPriv.getPrivateExponent();
        BigInteger n = rsaPub.getModulus();
        BigInteger p = findFactor(e, d, n);
        BigInteger q = n.divide(p);
        if (p.compareTo(q) > 0) {
            BigInteger t = p;
            p = q;
            q = t;
        }
        BigInteger exp1 = d.mod(p.subtract(BigInteger.ONE));
        BigInteger exp2 = d.mod(q.subtract(BigInteger.ONE));
        BigInteger coeff = q.modInverse(p);
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(n, e, d, p, q, exp1, exp2, coeff);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateCrtKey) kf.generatePrivate(keySpec);

    }
}
