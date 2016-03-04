/**
 *
 */
package com.android.demo.cons;

import com.android.demo.jni.JNISecurity;

/**
 * Copyright 2014 @Kevin, All Right Reserved.
 *
 * @version 1.0.0
 * @date 2015-2-10下午4:06:54
 */
public class Cons {

    // Keys
    public static String DEFAULT_PARTNER = "";
    public static String DEFAULT_SELLER = "";
    public static String PRIVATE = "";
    public static String PUBLIC = "";


    // 缓存图片位置
    public final static String PATH_CACHE_IAMGES = "Android/data/pkg/cache/images";
    // 缓存文件位置
    public final static String PATH_CACHE_FILES = "Android/data/pkg/cache/files";
    // 缓存日志文件位置
    public final static String PATH_CACHE_LOGS = "Android/data/pkg/cache/logs";
    // 非缓存图片位置
    public final static String PATH_FILE_DOWNLOAD = "Android/kevin/files/download";
    // 非缓存下载文件位置
    public final static String PATH_FILE_IMAGES = "Android/kevin/files/images";
    // 非缓存APK文件位置
    public final static String PATH_FILE_APKS = "Android/kevin/files/apks";


    //.so library postion
    public final static String LIB_SO_SECURITY = "security";
    public final static String TAG = "BASE";


    static {
        try {
            DEFAULT_PARTNER = new String(JNISecurity.getDefaultPartner(), "UTF-8");
            DEFAULT_SELLER = new String(JNISecurity.getDefaultSeller(), "UTF-8");
            PRIVATE = new String(JNISecurity.getPrivateKey(), "UTF-8");
            PUBLIC = new String(JNISecurity.getPublicKey(), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
