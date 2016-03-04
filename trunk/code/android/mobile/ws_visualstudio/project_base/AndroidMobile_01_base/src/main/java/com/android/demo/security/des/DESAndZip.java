package com.android.demo.security.des;

import android.util.Log;

import com.android.demo.util.GZipUtil;

public class DESAndZip {

    public static final String ENCODING = "UTF-8";

    /**
     * 加密并压缩
     */
    public static byte[] encryptAndGzip(String source) {
        try {
            byte[] encoded = DES.getInstance().encryptMode(
                    source.getBytes(ENCODING));
            String encodedString = DES.getInstance().encode(
                    encoded);

            byte[] zip_data = GZipUtil.gzip(encodedString.getBytes(ENCODING));

            return zip_data;
        } catch (Exception ex) {
            Log.e("mytag", "加密并压缩失败：" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 解压缩并解密
     */
    public static String unzipAndDecrypt(byte[] source) {
        try {
            byte[] unzip_data = GZipUtil.unzip(source);

            byte[] srcBytes = DES.getInstance().decryptMode(
                    DES.getInstance().decode(
                            new String(unzip_data, ENCODING)));
            return new String(srcBytes, ENCODING);
        } catch (Exception ex) {
            Log.e("mytag", "解压缩并解密失败：" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String source) {
        try {
            byte[] encoded = DES.getInstance().encryptMode(
                    source.getBytes(ENCODING));
            return DES.getInstance().encode(encoded);
        } catch (Exception ex) {
            Log.e("mytag", "加密失败：" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
