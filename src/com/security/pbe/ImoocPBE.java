package com.security.pbe;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 * 对称加密算法——PBE
 * @author 
 * PBE基于口令加密
 * 对已有的算法的包装
 * JDK、BC
 * 盐
 * PBEWithMD5AndDES
 */
public class ImoocPBE {
	private static String src = "imooc security pbe";
	
	public static void main(String[] args) {
		jdkPBE();
	}
	private static void jdkPBE() {
		try {
			//初始化盐(加密的随机数、字符串)
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);
			//口令与密钥
			String password = "imooc";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(pbeKeySpec);
			//加密
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);//100迭代次数
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk pbe encrypt: "+Base64.encodeBase64String(result));
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key,pbeParameterSpec);
			result = cipher.doFinal(result);
			System.out.println("jdk pbe decrypt: "+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
