/**
 * 
 */
package com.android.demo.security.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright 2014 @Kevin, All Right Reserved.
 * 
 * @date 2015年3月24日下午4:18:06
 * @version 1.0.0
 */
public class SHA1 {

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String encryptBySHA1(String srcStr) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = srcStr.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest());
			return strDes;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
	}

}
