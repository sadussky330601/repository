/**
 *
 */
package com.android.demo.security.des;

import com.android.demo.security.base64.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author KAILEN 2013-1-5下午12:47:06 TODO
 */
public class DES {

    private static final String Algorithm = "DESede";
    public static final byte[] keyBytes = "ABCDEFGHIJKLMNOPQRSTUVWX".getBytes();
    private static DES encrypt = new DES();

    public static DES getInstance() {
        return encrypt;
    }

    //加密
    public byte[] encryptMode(byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    //解密
    public byte[] decryptMode(byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    //解码及解密
    public String decrypt(String src) throws Exception {
        //String srcString = new String(src, "UTF-8");
        byte[] srcBytes = decryptMode(decode(src));
        return new String(srcBytes, "UTF-8");
    }

    //加密及编码
    public String encrypt(String src) throws Exception {
        String srcString = encode(encryptMode(src.getBytes("UTF-8")));
        // return srcString.getBytes("UTF-8");
        return srcString;
    }


    private void writeFile(String src) throws Exception {
        File file = new File("D://encrypt.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.close();
    }

    private String readFile(String path) {
        File file = new File(path);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        char[] cbuf = new char[10240];
        try {
            fr.read(cbuf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String res = new String(cbuf);
        return res;
    }

    //编码
    public String encode(byte[] bstr) {
        // return new sun.misc.BASE64Encoder().encode(bstr);
        // return new String(Base64.encodeBase64(bstr));
        return Base64.encodeToString(bstr, Base64.DEFAULT);
    }

    //解码
    public byte[] decode(String str) {
        byte[] bt = null;
        // try {
        bt = Base64.decode(str, Base64.DEFAULT);
        // bt = Base64.decodeBase64(str.getBytes("UTF-8"));
        // sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        // bt = decoder.decodeBuffer(str);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        return bt;
    }

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {

            DES encrypt = DES.getInstance();
            System.out.println("密钥是：" + new String(keyBytes));
            String szSrc = "AFE0AFE6-8DBA-E6CB-3240-103AC6F94766";
            szSrc = "test1";
            //szSrc =encrypt.readFile("e:\\HttpUtil2.java");
            System.out.println("加密前的字符串:" + szSrc);
            String encode = encrypt.encrypt(szSrc);
            System.out.println("加密后的字符串:" + encode);

            // String urlEncodeStr = URLEncoder.encode(encodedString, "UTF-8");
            // System.out.println("UTF-8编码的字符串:" + urlEncodeStr);


            //写入文件
            encrypt.writeFile(encode);

            //解密
            String decode = encrypt.decrypt(encode);
            System.out.println("解密后的字符串:" + decode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
