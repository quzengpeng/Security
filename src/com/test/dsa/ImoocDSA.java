package com.test.dsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;
//����ǩ���㷨����DSA
public class ImoocDSA {
	private static String src = "imooc security dsa";
	public static void main(String[] args) {
		jdkDSA();
	}
	
	public static void jdkDSA() {
		try {
			//1.��ʼ����Կ
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			DSAPublicKey dsaPublicKey = (DSAPublicKey)keyPair.getPublic();
			DSAPrivateKey dsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate();
			//2.ִ��ǩ��(ʹ��˽Կ������ǩ��)
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] sign = signature.sign();
			System.out.println("jdk dsa sign: "+Hex.encodeHexString(sign));
			//3.��֤ǩ��(ʹ�ù�Կ������У��)
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("DSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean b = signature.verify(sign);
			System.out.println("jdk dsa verify:" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
