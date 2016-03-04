/**
 *
 */
package com.android.demo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.text.TextUtils;

import com.android.demo.cons.Cons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 * 1. 所有文件操作。SD操作功能集合
 */
public class FileUtils {

    protected final static String TAG = FileUtils.class.getSimpleName();
    protected final static String PKG = "pkg";
    protected final static String ROOT = "/";
    protected final static int BUFFER_SIZE = 8 * 1024; // 8 KB
    protected static String sPackageName;
    protected static String sSDCardRoot;
    protected static FileUtils sInstance;
    protected Context mContext;
    protected static String EXTERNAL_PATH_DOWNLOAD = "Android/data/pkg/files/download";
    protected static String EXTERNAL_PATH_CACHE = "Android/data/pkg/files/cache";
    protected static String EXTERNAL_PATH_CACHE_DEFAULT = "Android/data/pkg/caches";
    protected static String EXTERNAL_PATH_FILES_DEFAULT = "Android/data/pkg/files";
    protected static String INTERNAL_PATH_CACHE = "data/data/pkg/files/cache";

    static {
        sSDCardRoot = getSdcardPath();
    }

    protected FileUtils(Context context) {
        this.mContext = context;
        sPackageName = context.getApplicationInfo().packageName;
    }

    public static FileUtils getInstance(Context context) {
        if (null == sInstance) {
            synchronized (FileUtils.class) {
                if (null == sInstance) {
                    sInstance = new FileUtils(context);
                }
            }
        }
        return sInstance;
    }

    // 判断sdcard是否存在,true为存在，false为不存在
    public static boolean isSDCardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    // 获取sdcard路径
    public static String getSdcardPath() {
        if (isSDCardExist()) {
            return Environment.getExternalStorageDirectory().toString();// 获取跟目录
        }
        return "";
    }

    // 检查文件存在性
    public static boolean isFileExist(String fileName) {
        if (TextUtils.isEmpty(fileName))
            return false;
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 多线程同步删除目录或者文件，删除目录下所有文件，包括目录
     *
     * @param directory  需要删除指定目录下的所有文件和文件夹directory
     * @param deleteRoot 如果是true，则删除传入的根目录，false则不删除根目录自身
     * @return
     */
    public synchronized static boolean deleteDirectorySync(String directory, boolean deleteRoot) {
        if (TextUtils.isEmpty(directory))
            return false;
        File rootFile = new File(directory);
        if (rootFile.exists()) {
            if (rootFile.isDirectory()) {
                File[] subFiles = rootFile.listFiles();
                for (int i = 0; i < subFiles.length; i++) {
                    deleteDirectorySync(subFiles[i].getAbsolutePath(), true);
                }
                if (deleteRoot)
                    rootFile.delete();
            } else {
                rootFile.delete();
            }
            return true;
        }
        return false;
    }

    /**
     * 判断sdcard的状态，并告知用户
     *
     * @return sdcard的状态，并告知用户
     */
    public static String checkAndReturnSDCardStatus() {
        String status = Environment.getExternalStorageState();
        if (status != null) {
            if (status.equals(Environment.MEDIA_MOUNTED)) {
                return "SD卡已挂载！";
            } else if (status.equals(Environment.MEDIA_REMOVED)) {
                return "SD卡已经移除或不存在";

            } else if (status.equals(Environment.MEDIA_SHARED)) {
                return "SD卡正在使用中";

            } else if (status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                return "SD卡只能读，不能写";
            } else {
                return "SD卡不能使用或不存在";
            }
        } else {
            return "SD卡不能使用或不存在";
        }
    }

    /**
     * 在/创建任意目录 推荐使用这种方法在/上创建任意文件目录<br>
     * 其中path必须是"a/b/c","c",这样的字符串,否则会错误!<br>
     *
     * @param path
     * @return
     */
    public static File getExpectDirectory(String... paths) {
        if (!isSDCardExist() || paths == null || paths.length == 0)
            return null;
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].contains(PKG))
                paths[i] = paths[i].replaceAll(PKG, sPackageName);
        }
        String local = paths[0];
        for (int i = 1; i < paths.length; i++) {
            local += "/" + paths[i];
        }
        File targetFile = new File(ROOT, local);
        if (targetFile.exists()) {
            return targetFile;
        } else {
            if (!targetFile.mkdirs()) {
                Logger.w(TAG, "Unable to create  file directory on Root /:" + local);
                return null;
            } else {
                Logger.d(TAG, "Success create file on Root / " + local);
            }
            try {
                new File(targetFile, ".nomedia").createNewFile();
            } catch (IOException e) {
                Logger.i(TAG, "Can't create \".nomedia\" file in application   file directory on Root /:" + local);
            }
        }

//        String[] targetPahts = local.split("/");
//        for (int i = 0; i < targetPahts.length; i++) {
//            if (i == 0) {
//                targetFile = new File(new File(ROOT), targetPahts[i]);
//            } else {
//                targetFile = new File(targetFile, targetPahts[i]);
//            }
//        }
//        if (!targetFile.exists()) {
//            if (!targetFile.mkdirs()) {
//                Logger.w(TAG, "Unable to create  file directory on Root /:" + local);
//                return null;
//            } else {
//                Logger.d(TAG, "Success create file on Root / " + local);
//            }
//            try {
//                new File(targetFile, ".nomedia").createNewFile();
//            } catch (IOException e) {
//                Logger.i(TAG, "Can't create \".nomedia\" file in application   file directory on Root /:" + local);
//            }
//        }
        return targetFile;
    }

    /**
     * 在SDCARD创建任意目录5 推荐使用这种方法在SDCARD上创建任意文件目录<br>
     * 其中path必须是"a/b/c","c",这样的字符串,否则会错误!<br>
     *
     * @param path
     * @return
     */
    public static File getExpectDirectoryOnSDCard(String... paths) {
        if (!isSDCardExist() || paths == null || paths.length == 0)
            return null;
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].contains(PKG))
                paths[i] = paths[i].replaceAll(PKG, sPackageName);
        }
        String local = paths[0];
        for (int i = 1; i < paths.length; i++) {
            local += "/" + paths[i];
        }
        File targetFile = new File(ROOT, local);
        if (targetFile.exists()) {
            return targetFile;
        }
        String[] targetPahts = local.split("/");
        for (int i = 0; i < targetPahts.length; i++) {
            if (i == 0) {
                targetFile = new File(new File(sSDCardRoot), targetPahts[i]);
            } else {
                targetFile = new File(targetFile, targetPahts[i]);
            }
        }
        if (!targetFile.exists()) {
            if (!targetFile.mkdirs()) {
                Logger.w(TAG, "Unable to create external  file directory");
                return null;
            } else {
                Logger.d(TAG, "Success create file on SDCard " + targetFile.getAbsolutePath());
            }
            try {
                new File(targetFile, ".nomedia").createNewFile();
            } catch (IOException e) {
                Logger.i(TAG, "Can't create \".nomedia\" file in application external file directory");
            }
        }
        return targetFile;
    }

    /**
     * 在SDCard指定的目录下获取指定的文件
     *
     * @param fileName 指定的文件名
     * @param paths
     * @return 指定的目录下
     */
    public static File getExpectFileOnSDCard(String fileName, String... paths) {
        File directory = getExpectDirectoryOnSDCard(paths);
        File expectFile = new File(directory, fileName);
        if (!expectFile.exists()) {
            try {
                expectFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return expectFile;
    }

    /**
     * 在SDCard根据文件的绝对路径返回文件，如果文件不存在，则创建空文件并返回
     *
     * @param fileAbsolutePath 指定文件的绝对路径
     * @return
     */
    public static File getExpectFileOnSDCard(String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            String parent = file.getParent();
            getExpectDirectoryOnSDCard(parent);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    // 获取指定的缓存目录
    public static File getCacheDirectory(Context context, String... paths) {
        File appCacheDir = null;
        if (isSDCardExist()) {
            appCacheDir = getExpectDirectoryOnSDCard(paths);
        } else {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    // 获取默认的缓存目录
    public static File getDefaultCacheDirectory(Context context) {
        File appCacheDir = context.getExternalCacheDir();
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    public void showDirectoryStr() {

        Logger.d(TAG, "SDCardRoot :%1$s", getSdcardPath());
        /**
         * Context对象的openFileOutput()和openFileInput()来进行数据持久化存储的这种方式，
         * 你的数据文件将存储在内部存储空间的/data/data/你的应用程序的包名/files/目录下，
         * 无法指定更深一级的目录，而且默认是Context.MODE_PRIVATE模式，即别的应用程序不能访问它。 MODE_APPEND
         * 表示写文件时是追加模式，即从文件末开始写数据 MODE_PRIVATE MODE_WORLD_READABLE
         * MODE_WORLD_WRITEABLE 你可以使用openFileOutput()的int
         * mode参数来让别的应用程序也能访问你的文件。 <font color='red' >
         * 注意：保存在/data/data/你的应用程序的包名目录中文件，会在卸载你的应用程序时被删除掉。</font>
         */
        Logger.d(TAG, "openFileOutput() :%1$s", "/data/data/" + sPackageName + "/files/");
        /**
         * 用File返回数据文件的根目录，返回的文件的路径为“/data”。该目录下的文件是只读。 应用程序无法对该目录下的文件进行写操作。
         */
        Logger.d(TAG, "Environment.getDataDirectory():%1$s", Environment.getDataDirectory());
        /**
         * 用File返回缓存文件的根目录，返回的文件的路径为“/cache”。对于第三方应用程序。
         * 该目录下的文件是只读。第三方应用程序无法对该目录下的文件进行写操作。
         */
        Logger.d(TAG, "Environment.getDownloadCacheDirectory():%1$s", Environment.getDownloadCacheDirectory());
        /**
         * 用File返回Android系统文件的根目录，返回的文件的路径为“/system”。该目录下的文件是只读。
         * 应用程序无法对该目录下的文件进行写操作。
         */
        Logger.d(TAG, "Environment.getRootDirectory():%1$s", Environment.getRootDirectory());
        /**
         * 对于私有文件只能被创建该文件的应用访问，如果希望文件能被其他应用读和写，可以在创建文件时，指定Context.
         * MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE权限。
         * Activity还提供了getCacheDir()和getFilesDir()方法：
         * getCacheDir()方法用于获取/data/data/<package name>/cache目录
         * getFilesDir()方法用于获取/data/data/<package name>/files目录
         */
        Logger.d(TAG, "Context.getFilesDir().getPath() :%1$s", mContext.getFilesDir().getPath());
        /**
         * 该目录主要用于存放缓存文件，当系统的内存存储空间紧张时，该目录下的文件会被删除掉。关于这些文件究竟会在存储空间剩余多少的情况，
         * 没有严格的标准保障
         * 。注意：你不应该依赖系统来清理这些缓存文件，你应该对这些缓存文件占用的最大存储空间设定个最大值,比如是1M，当实际占用空间超过这个值时
         * ，你应该对这些缓存文件做相应的清理工作（prune）。
         */
        // Logger.d(TAG, "Context.getCacheDir():%1$s",
        // mContext.getCacheDir().getAbsolutePath());
        // Logger.d(TAG, "getExpectDirectory(INTERNAL_PATH_CACHE):%1$s",
        // getExpectDirectory(INTERNAL_PATH_CACHE));
        // Logger.d(TAG, "getExpectDirectoryOnSDCard(EXTERNAL_PATH_CACHE):%1$s",
        // getExpectDirectoryOnSDCard(EXTERNAL_PATH_CACHE));
        // Logger.d(TAG,
        // "getExpectDirectoryOnSDCard(EXTERNAL_PATH_DOWNLOAD):%1$s",
        // getExpectDirectoryOnSDCard(EXTERNAL_PATH_DOWNLOAD));

        // 获取SDCARD上面的目录
        // Logger.d(TAG, "Context.getExternalCacheDir():%1$s",
        // mContext.getExternalCacheDir().getAbsolutePath());
        // Logger.d(TAG, "Context.getExternalFilesDir():%1$s",
        // mContext.getExternalFilesDir(null).getAbsolutePath());

        // SDCARD上创建缓存图片位置
        // /storage/sdcard0/Android/data/com.android.mobile.base/cache/iamges
        // SDCARD上创建缓存文件位置
        // /storage/sdcard0/Android/data/com.android.mobile.base/cache/files
        // SDCARD上创建非缓存的下载文件位置
        // /storage/sdcard0/Android/files/com.android.mobile.base/files/download
        // SDCARD上创建非缓存的图片文件位置
        // /storage/sdcard0/Android/files/com.android.mobile.base/files/images
        // SDCARD上创建非缓存的apk文件位置
        // /storage/sdcard0/Android/files/com.android.mobile.base/files/apk
        // getExpectDirectoryOnSDCard("Android/data/pkg/cache/images");
        // getExpectDirectoryOnSDCard("Android/data/pkg/cache/files");
        // getExpectDirectoryOnSDCard("Android/data/pkg/cache/log");
        // getExpectDirectoryOnSDCard("Android/kevin/files/download");
        // getExpectDirectoryOnSDCard("Android/kevin/files/images");
        // getExpectDirectoryOnSDCard("Android/kevin/files/apk");

        getExpectDirectoryOnSDCard(Cons.PATH_CACHE_IAMGES);
        getExpectDirectoryOnSDCard(Cons.PATH_CACHE_FILES);
        getExpectDirectoryOnSDCard(Cons.PATH_CACHE_LOGS);
        getExpectDirectoryOnSDCard(Cons.PATH_FILE_IMAGES);
        getExpectDirectoryOnSDCard(Cons.PATH_FILE_DOWNLOAD);
        getExpectDirectoryOnSDCard(Cons.PATH_FILE_APKS);

        /**
         *
         * <code>
         * 	02-12 12:55:55.298: D/FnFile(28144): SDCardRoot :/storage/sdcard0
         02-12 12:55:55.298: D/FnFile(28144): openFileOutput() :/data/data/com.android.mobile.base/files/
         02-12 12:55:55.298: D/FnFile(28144): Environment.getDataDirectory():/data
         02-12 12:55:55.298: D/FnFile(28144): Environment.getDownloadCacheDirectory():/cache
         02-12 12:55:55.298: D/FnFile(28144): Environment.getRootDirectory():/system
         02-12 12:55:55.298: D/FnFile(28144): Context.getFilesDir().getPath() :/data/data/com.android.mobile.base/files
         02-12 12:55:55.298: D/FnFile(28144): Context.getCacheDir():/data/data/com.android.mobile.base/cache
         02-12 12:55:55.300: D/FnFile(28144): getExpectDirectory(INTERNAL_PATH_CACHE):/data/data/com.android.mobile.base/files/cache
         02-12 12:55:55.301: W/FnFile(28144): Unable to create  file directory on Root /:android/data/com.android.mobile.base/files/cache
         02-12 12:55:55.301: D/FnFile(28144): getExpectDirectory(EXTERNAL_PATH_CACHE):null
         02-12 12:55:55.302: W/FnFile(28144): Unable to create  file directory on Root /:android/data/com.android.mobile.base/files/download
         02-12 12:55:55.302: D/FnFile(28144): getExpectDirectory(EXTERNAL_PATH_DOWNLOAD):null
         02-12 13:25:32.977: D/FnFile(30661): Context.getExternalCacheDir():/storage/sdcard0/Android/data/com.android.mobile.base/cache
         02-12 13:25:32.977: D/FnFile(30661): Context.getExternalFilesDir():/storage/sdcard0/Android/data/com.android.mobile.base/files
         </code>
         *
         */
    }

    /**
     * 复制流
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copyStream(InputStream is, OutputStream os) throws IOException {
        try {
            int read;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            os.flush();
            os.close();
            is.close();
        }
    }

    /**
     * 保存Bitmap到指定的文件位置
     *
     * @param bitmap
     * @param filePathName
     * @param iconFormat
     * @return
     */
    public static boolean saveBitmapToSDFile(final Bitmap bitmap, final String fileAbsolutePath, CompressFormat iconFormat) {
        boolean result = false;
        try {
            getExpectFileOnSDCard(fileAbsolutePath);
            OutputStream outputStream = new FileOutputStream(fileAbsolutePath);
            result = bitmap.compress(iconFormat, 100, outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private void copyDBToSDcrad() {
        String DATABASE_NAME = "数据库文件名称";
        String oldPath = "data/data/com.packagename/databases/" + DATABASE_NAME;
        String newPath = Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME;
        copyFile(oldPath, newPath);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
