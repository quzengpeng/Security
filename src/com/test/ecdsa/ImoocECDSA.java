package com.test.ecdsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * 数字签名算法——ECDSA
 */
public class ImoocECDSA {
	private static String src = "imooc security ecdsa";
	public static void main(String[] args) {
		jdkECDSA();
	}
	public static void jdkECDSA() {
		try {
			//1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
			keyPairGenerator.initialize(256);
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			ECPublicKey ecPublicKey = (ECPublicKey)keyPair.getPublic();
			ECPrivateKey ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();
			//2.执行签名(使用私钥对数据签名)
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withECDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] sign = signature.sign();
			System.out.println("jdk ecdsa sign: "+Hex.encodeHexString(sign));
			//3.验证签名(使用公钥对数据校验)
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean b = signature.verify(sign);
			System.out.println("jdk ecdsa verify: "+b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
