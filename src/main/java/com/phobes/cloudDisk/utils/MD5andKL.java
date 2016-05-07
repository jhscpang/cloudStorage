/**
 * 
 */
package com.phobes.cloudDisk.utils;

import java.security.MessageDigest;

/**
 * @author pangchao E-mail: pangchao620@163.com
 * @date : 2015�?12�?5�? 下午1:00:03
 * @Description : MD5和KL可�?�加密算法工具类
 * @version 1.0
 */
public class MD5andKL {

	// MD5加码�?32�?
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可�?�的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解�?
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	//使用MD5和KL双重加密
	public static String MD5KL(String inStr) {
		return KL(MD5(inStr));
	}
	
	// 测试主函�?
	public static void main(String args[]) {
		String s = new String("a");
		System.out.println("原始�?" + s);
		System.out.println("MD5后：" + MD5(s)); 
		System.out.println("MD5后再加密�?" +MD5KL(s)); 
		System.out.println("解密为MD5后的�?" + JM(KL(MD5(s))));	 
	}
}
