package com.android.demo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.demo.jni.JNISecurity;
import com.android.demo.security.des.DES;
import com.android.demo.security.rsa.RSATest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {


    private final static String TAG = AndroidTest.class.getSimpleName();

    public ApplicationTest() {
        super(Application.class);
    }


    public void testdeleteDirectorySync() {
        // getBytesEncrypt(Keys.DEFAULT_PARTNER);
        // getBytesEncrypt(Keys.DEFAULT_SELLER);
        // getBytesEncrypt(Keys.PRIVATE);
        // getBytesEncrypt(Keys.PUBLIC);
    }


    public void testGetDefualt() throws Exception {
        Log.d(TAG, "直接从JNI获取的 :" + new String(JNISecurity.getDefaultPartner(), "UTF-8").toString());
        Log.d(TAG, "直接从JNI获取的 :" + new String(JNISecurity.getDefaultSeller(), "UTF-8").toString());
        Log.d(TAG, "直接从JNI获取的 :" + new String(JNISecurity.getPublicKey(), "UTF-8").toString());
        Log.d(TAG, "直接从JNI获取的 :" + new String(JNISecurity.getPrivateKey(), "UTF-8").toString());
    }

    private void getBytesEncrypt(String src) {
        String charset = "UTF-8";
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        int BLOCK_LENGTH = 16;
        byte[] bytesEncrypt = null;
        byte[] srcbytes = null;
        String strEncrypt = null;
        byte[] bytesDecrypt = null;
        String strDecrypt = null;
        int targetLength = 0;
        int OutLength = 0;
        try {
            // 执行加密过程
            srcbytes = src.getBytes(charset);
            targetLength = srcbytes.length;
            OutLength = (targetLength % BLOCK_LENGTH == 0) ? targetLength : ((1 + (targetLength / BLOCK_LENGTH)) * BLOCK_LENGTH);
            bytesEncrypt = new byte[OutLength];
            int length = JNISecurity.Encrypt(srcbytes, key, bytesEncrypt, targetLength);
            // 执行解密过程
            int OutLength2 = (OutLength % BLOCK_LENGTH == 0) ? OutLength : ((1 + (OutLength / BLOCK_LENGTH)) * BLOCK_LENGTH);
            bytesDecrypt = new byte[targetLength];
            JNISecurity.Decrypt(bytesEncrypt, key, bytesDecrypt, OutLength);
            strDecrypt = new String(bytesDecrypt, charset);

            Log.d(TAG, "执行加密前的字符串:" + src);
            Log.d(TAG, "执行加密前的字节序列:" + Arrays.toString(srcbytes));
            Log.d(TAG, "执行加密前的字节序列的长度:" + srcbytes.length);
            Log.d(TAG, "执行加密后的字节序列:" + Arrays.toString(bytesEncrypt));
            Log.d(TAG, "执行解密后的字节序列:" + Arrays.toString(bytesDecrypt));
            Log.d(TAG, "执行解密后的字符串:" + strDecrypt);
            Log.d(TAG, "执行加密前的字符串==解密后的字符串???:" + src.equals(strDecrypt));

            // 直接从JNI获取btye[]
            // byte[] directGetFromJniBtyes = Security.getDefaultPartner();
            // byte[] directGetFromJniBtyes = Security.getDefaultSeller();
            // byte[] directGetFromJniBtyes = Security.getPrivateKey();
            byte[] directGetFromJniBtyes = JNISecurity.getPublicKey();

            String jniString = new String(directGetFromJniBtyes, charset);
            Log.d(TAG, "直接从JNI获取的解密后的字符序列:" + Arrays.toString(directGetFromJniBtyes));
            Log.d(TAG, "直接从JNI获取的解密后的字符序列转换成字符串 :" + jniString);
            Log.d(TAG, "执行加密前的字符串==直接从JNI获取的解密后的字符序列转换成字符串???:" + src.equals(jniString));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception {

        //------Test for DES|START|-------
        final byte[] keyBytes = "ABCDEFGHIJKLMNOPQRSTUVWX".getBytes();
        DES encrypt = DES.getInstance();
        Log.d(TAG, "密钥是：" + new String(keyBytes));
        String szSrc = "AFE0AFE6-8DBA-E6CB-3240-103AC6F94766";
        szSrc = "test1";
        //szSrc =encrypt.readFile("e:\\HttpUtil2.java");
        Log.d(TAG, "加密前的字符串:" + szSrc);
        String encode = encrypt.encrypt(szSrc);
        Log.d(TAG, "加密后的字符串:" + encode);

        // String urlEncodeStr = URLEncoder.encode(encodedString, "UTF-8");
        // System.out.println("UTF-8编码的字符串:" + urlEncodeStr);


        //写入文件
//        encrypt.writeFile(encode);

        //解密
        String decode = encrypt.decrypt(encode);
        Log.d(TAG, "解密后的字符串:" + decode);


        //------Test for RSA|START|-------
        RSATest.test();
        RSATest.testSign();

        //------Test for DES|START|-------
    }
}