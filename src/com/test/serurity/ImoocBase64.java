package com.test.serurity;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;

public class ImoocBase64 {
	private static String src = "imooc security base64";
	
	public static void main(String[] args) {
//		commonCodesBase64();
		bouncyCastleBase64();
	}
	
	public static void jdkBase64() {
		Base64Encoder encoder = new Base64Encoder();
	}
	
	public static void commonCodesBase64() {
		byte[] encodeBase64 = Base64.encodeBase64(src.getBytes());
		System.out.println("encode: "+ new String(encodeBase64));
		byte[] decodeBase64 = Base64.decodeBase64(encodeBase64);
		System.out.println("decode: " + new String(decodeBase64));
	}
	
	public static void bouncyCastleBase64() {
		byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		System.out.println("encode: " + new String(encode));
		byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
		System.out.println("decode: "+ new String(decode));
	}
}
