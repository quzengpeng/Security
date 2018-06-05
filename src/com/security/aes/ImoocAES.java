package com.security.aes;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 对称加密算法——AES
 * @author dapeng
 * AES通常用于移动通信系统加密以及基于SSH协议的软件
 */
public class ImoocAES {
	private static String src = "imooc security aes";
	
	public static void main(String[] args) {
		jdkAES();
	}
	public static void jdkAES() {
		try {
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(new SecureRandom());//128
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			//转换KEY
			Key key = new SecretKeySpec(keyBytes, "AES");
			//加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(src.getBytes());
			System.out.println("jdk aes encrypt: "+Base64.encodeBase64String(doFinal));
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			doFinal = cipher.doFinal(doFinal);
			System.out.println("jdk aes decrypt: "+ new String(doFinal));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
