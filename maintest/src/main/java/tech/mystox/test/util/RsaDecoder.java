package tech.mystox.test.util;

import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.Cipher;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

public class RsaDecoder {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 由n和d获取私钥
		PrivateKey privateKey = getPrivateKey("7583258983483452668112391773172243489183543497642239118519141922566439397957255284407933122331301458471073983703598057157251593378269392969200805359548143",
				"123");

		// 由n和d获取私钥
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) getPrivateKey("7583258983483452668112391773172243489183543497642239118519141922566439397957255284407933122331301458471073983703598057157251593378269392969200805359548143",
				"123");

		// 创建 PEMWriter
		PemObject pemObject = new PemObject("PRIVATE KEY", privateKey.getEncoded());
		PemWriter pemWriter = new PemWriter(new FileWriter("private_key.pem"));
		// 写入 PEM 格式的私钥
		pemWriter.writeObject(pemObject);

		// 关闭 PEMWriter
		pemWriter.close();


		String codenew =Base64.getEncoder().encodeToString(privateKey.getEncoded());


		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());

		PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(
				new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE),
				PrivateKeyInfo.getInstance(pkcs8EncodedKeySpec.getEncoded())
		);

		PemWriter pemWriter2 = new PemWriter(new FileWriter("private_key2.pem"));
		// 写入 PEM 格式的私钥
//		pemWriter2.writeObject(privateKey1);
//		pemWriter2.close();


		// 创建 PEMWriter

		KeyFactory keyFactory1 = getKeyFactory("7583258983483452668112391773172243489183543497642239118519141922566439397957255284407933122331301458471073983703598057157251593378269392969200805359548143",
				"741582721594602257502362312499212788534374937461115255666099769317001237491904307964396026764847946075682953812841533482094929709216906587946468022125209");
		// 将私钥转换为 PKCS#1 格式的 PEM 文件
//		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//		JcaPKCS8PrivateKeyInfoBuilder pkcs8PrivateKeyInfoBuilder = new JcaPKCS8PrivateKeyInfoBuilder(privateKey);
//		PKCS8PrivateKeyInfo pkcs8PrivateKeyInfo = pkcs8PrivateKeyInfoBuilder.build();
//		PrivateKeyInfo pkcs1PrivateKeyInfo = pkcs8PrivateKeyInfo.parsePrivateKeyInfo();
//
//		FileWriter fileWriter = new FileWriter("private_key_pkcs1.pem");
//		PemWriter pemWriter = new PemWriter(fileWriter);
//		pemWriter.writeObject(pkcs1PrivateKeyInfo);
//		pemWriter.close();


		// 将私钥转换为 RSAPrivateCrtKeySpec
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		// 构建 PKCS#1 格式的私钥



		StringBuilder pkcs1PrivateKey = new StringBuilder();
		pkcs1PrivateKey.append("-----BEGIN RSA PRIVATE KEY-----\n");
		pkcs1PrivateKey.append(base64Encode(removeLeadingZeros(privateKeySpec.getModulus().toByteArray()))).append('\n');
		pkcs1PrivateKey.append(base64Encode(privateKeySpec.getPrivateExponent().toByteArray())).append('\n');
		pkcs1PrivateKey.append("-----END RSA PRIVATE KEY-----");


		System.out.println(pkcs1PrivateKey);




		byte[] encoded = privateKey.getEncoded();
		System.out.println(Base64.getEncoder().encodeToString(encoded));
		System.out.println(privateKey.getAlgorithm());
		System.out.println(privateKey.getFormat());
		System.out.println("密文base64： "+Base64.getEncoder().encodeToString(hexString2Bytes("71FAE7A08E6CA4914F2474E3F629C01785A7FB734CBA6C2BAE99DB32159D994F58AA0AAE6F48D5C172A2C129E938E98F7EDAD76BFA75C411FE04168E978C4A3A")));
		;
		System.out.println(decode("71FAE7A08E6CA4914F2474E3F629C01785A7FB734CBA6C2BAE99DB32159D994F58AA0AAE6F48D5C172A2C129E938E98F7EDAD76BFA75C411FE04168E978C4A3A"));
	}


	private static byte[] removeLeadingZeros(byte[] data) {
		int leadingZeros = 0;
		while (leadingZeros < data.length - 1 && data[leadingZeros] == 0) {
			leadingZeros++;
		}
		byte[] result = new byte[data.length - leadingZeros];
		System.arraycopy(data, leadingZeros, result, 0, result.length);
		return result;
	}

	private static String base64Encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	// 将base64编码后的私钥字符串转成PrivateKey实例
	public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception {
		BigInteger modulus = new BigInteger(modulusStr);
		BigInteger exponent = new BigInteger(exponentStr);
		RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(privateKeySpec);
	}

	public static KeyFactory getKeyFactory(String modulusStr, String exponentStr) throws Exception {
		BigInteger modulus = new BigInteger(modulusStr);
		BigInteger exponent = new BigInteger(exponentStr);
		RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory;
	}

	/**
	 *  私钥解密，并转换成十六进制字符串
	 * @param content 密文
	 * @return 成功返回解密后字符串,失败返回密文本身
	 */
	public static String decode(String content) {
		String decrypted = null;
		try {
			// 由n和d获取私钥
			PrivateKey privateKey = getPrivateKey("7583258983483452668112391773172243489183543497642239118519141922566439397957255284407933122331301458471073983703598057157251593378269392969200805359548143", "741582721594602257502362312499212788534374937461115255666099769317001237491904307964396026764847946075682953812841533482094929709216906587946468022125209");

			// 私钥解密
			decrypted = decrypt(content,privateKey);
		}catch(Exception e) {
			e.printStackTrace();
			decrypted = content;
		}finally {
			if(null == decrypted) {
				decrypted = content;
			}
		}
			return decrypted;

	}
	/**
	 *  私钥解密，并转换成十六进制字符串
	 * @param content 密文
	 * @return 成功返回解密后字符串,失败返回null
	 */
	public static String decodeOrReturnNull(String content) {
		String decrypted = null;
		try {
			// 由n和d获取私钥
			PrivateKey privateKey = getPrivateKey("7583258983483452668112391773172243489183543497642239118519141922566439397957255284407933122331301458471073983703598057157251593378269392969200805359548143", "741582721594602257502362312499212788534374937461115255666099769317001237491904307964396026764847946075682953812841533482094929709216906587946468022125209");

			// 私钥解密
			decrypted = decrypt(content,privateKey);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		}
		return decrypted;

	}

	/**
	 *  私钥解密，并转换成十六进制字符串
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		int splitLength = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;
		byte[] contentBytes = hexString2Bytes(content);
		byte[][] arrays = splitBytes(contentBytes, splitLength);
		StringBuffer sb = new StringBuffer();
		for (byte[] array : arrays) {
			sb.append(new String(cipher.doFinal(array),"UTF-8"));
		}
		return sb.toString();
	}

	// 拆分byte数组
	public static byte[][] splitBytes(byte[] bytes, int splitLength) {
		int x; // 商，数据拆分的组数，余数不为0时+1
		int y; // 余数
		y = bytes.length % splitLength;
		if (y != 0) {
			x = bytes.length / splitLength + 1;
		} else {
			x = bytes.length / splitLength;
		}
		byte[][] arrays = new byte[x][];
		byte[] array;
		for (int i = 0; i < x; i++) {

			if (i == x - 1 && bytes.length % splitLength != 0) {
				array = new byte[bytes.length % splitLength];
				System.arraycopy(bytes, i * splitLength, array, 0, bytes.length % splitLength);
			} else {
				array = new byte[splitLength];
				System.arraycopy(bytes, i * splitLength, array, 0, splitLength);
			}
			arrays[i] = array;
		}
		return arrays;
	}

	// byte数组转十六进制字符串
	public static String bytesToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	// 十六进制字符串转byte数组
	public static byte[] hexString2Bytes(String hex) {
		int len = (hex.length() / 2);
		hex = hex.toUpperCase();
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

}
