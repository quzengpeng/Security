package com.security.aes;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * �ԳƼ����㷨����AES
 * @author dapeng
 * AESͨ�������ƶ�ͨ��ϵͳ�����Լ�����SSHЭ������
 */
public class ImoocAES {
	private static String src = "imooc security aes";
	
	public static void main(String[] args) {
		jdkAES();
	}
	public static void jdkAES() {
		try {
			//����KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(new SecureRandom());//128
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			//ת��KEY
			Key key = new SecretKeySpec(keyBytes, "AES");
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(src.getBytes());
			System.out.println("jdk aes encrypt: "+Base64.encodeBase64String(doFinal));
			//����
			cipher.init(Cipher.DECRYPT_MODE, key);
			doFinal = cipher.doFinal(doFinal);
			System.out.println("jdk aes decrypt: "+ new String(doFinal));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
