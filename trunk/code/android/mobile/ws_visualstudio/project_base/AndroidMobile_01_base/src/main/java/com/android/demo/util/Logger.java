package com.android.demo.util;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.android.demo.cons.Cons;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */
public class Logger {

    public static boolean D = true;
    public static boolean I = true;
    public static boolean E = true;
    public static boolean W = true;

    /**
     * 打印普通消息 （常用） added by aihua.yan 2012-04-09
     *
     * @param clazz
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (D)
            Log.i(tag, msg);
    }

    /**
     * 打印普通消息 （常用） added by aihua.yan 2012-04-09
     *
     * @param clazz
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (D)
            Log.d(tag, msg);
    }

    /***
     * @param tag
     * @param desc
     * @param ex
     */
    public static void d(String tag, String desc, Throwable ex) {
        if (D)
            Log.d(tag, desc, ex);
    }

    /**
     * 打印普通消息 （常用） added by aihua.yan 2012-04-09
     *
     * @param clazz
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (D)
            Log.v(tag, msg);
    }

    /**
     * 打印普通消息 （常用） added by aihua.yan 2012-04-09
     *
     * @param clazz
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (D)
            Log.w(tag, msg);
    }

    /**
     * 打印普通消息 （常用） added by aihua.yan 2012-04-09
     *
     * @param clazz
     * @param msg
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * 打印普通消息 （常用）
     *
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, String msg) {
        Log.i(clazz.getSimpleName(), msg);
    }

    /**
     * 打印普通消息 （常用）
     *
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, String msg, Throwable tr) {
        Log.i(clazz.getSimpleName(), msg, tr);
    }

    /**
     * 打印普通消息 （常用）
     *
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, Exception e) {
        Log.i(clazz.getSimpleName(), e.getLocalizedMessage());
    }

    /**
     * 打印调试信息
     *
     * @param clazz
     * @param msg
     */
    public static void d(Class<?> clazz, String msg) {
        Log.d(clazz.getSimpleName(), msg);
    }

    /**
     * 打印调试信息
     *
     * @param clazz
     * @param msg
     */
    public static void d(Class<?> clazz, String msg, Throwable tr) {
        Log.d(clazz.getSimpleName(), msg, tr);
    }

    /**
     * 打印调试信息
     *
     * @param clazz
     * @param msg
     */
    public static void d(Class<?> clazz, Exception e) {
        Log.d(clazz.getSimpleName(), e.getLocalizedMessage());
    }

    /**
     * 打印系统错误信息
     *
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, String msg) {
        Log.e(clazz.getSimpleName(), msg);
    }

    /**
     * 打印系统错误信息
     *
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, String msg, Throwable tr) {
        Log.e(clazz.getSimpleName(), msg, tr);
    }

    /**
     * 打印系统错误信息
     *
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, Exception e) {
        Log.e(clazz.getSimpleName(), e.getLocalizedMessage());
    }

    /**
     * 打印警告信息
     *
     * @param clazz
     * @param msg
     */
    public static void w(Class<?> clazz, String msg) {
        Log.w(clazz.getSimpleName(), msg);
    }

    /**
     * 打印警告信息
     *
     * @param clazz
     * @param msg
     */
    public static void w(Class<?> clazz, String msg, Throwable tr) {
        Log.w(clazz.getSimpleName(), msg, tr);
    }

    /**
     * 打印警告信息
     *
     * @param clazz
     * @param msg
     */
    public static void w(Class<?> clazz, Exception e) {
        Log.w(clazz.getSimpleName(), e.getLocalizedMessage());
    }

    /**
     * 输出完整的错误堆栈
     *
     * @param e
     * @return
     */
    public static String getStackTrace(Throwable e) {

        StringBuffer stack = new StringBuffer();
        stack.append(e);
        stack.append("\r\n");
        stack.append(e.getMessage());
        stack.append("\r\n");

        Throwable rootCause = e.getCause();

        while (rootCause != null) {
            stack.append("Root Cause:\r\n");
            stack.append(rootCause);
            stack.append("\r\n");
            stack.append(rootCause.getMessage());
            stack.append("\r\n");
            stack.append("StackTrace:\r\n");
            stack.append(rootCause);
            stack.append("\r\n");
            rootCause = rootCause.getCause();
        }

        for (int i = 0; i < e.getStackTrace().length; i++) {
            stack.append(e.getStackTrace()[i].toString());
            stack.append("\r\n");
        }

        return stack.toString();
    }

    private static final String LOG_FORMAT = "%1$s\n%2$s";

    public static void d(String tag, String message, Object... args) {
        log(Log.DEBUG, tag, null, message, args);
    }

    public static void i(String tag, String message, Object... args) {
        log(Log.INFO, tag, null, message, args);
    }

    public static void w(String tag, String message, Object... args) {
        log(Log.WARN, tag, null, message, args);
    }

    public static void e(String tag, Throwable ex) {
        log(Log.ERROR, tag, ex, null);
    }

    public static void e(String tag, String message, Object... args) {
        log(Log.ERROR, tag, null, message, args);
    }

    public static void e(String tag, Throwable ex, String message, Object... args) {
        log(Log.ERROR, tag, ex, message, args);
    }

    private static void log(int priority, String tag, Throwable ex, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }

        String log;
        if (ex == null) {
            log = message;
        } else {
            String logMessage = message == null ? ex.getMessage() : message;
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT, logMessage, logBody);
        }
        Log.println(priority, tag, log);
    }


    //---------record the log to file |START|-----------------

    private final static int MAX_LOG_FILE_LENTH = 20 * 1024 * 1024;


    public static void eFile(Throwable ex, String tag, String message, Object... args) {
        logFile(Log.ERROR, null, ex, tag, message, args);
    }


    private static void eFile(String filePath, Throwable ex, String tag, String message, Object... args) {
        logFile(Log.ERROR, filePath, ex, tag, message, args);
    }

    private static void logFile(int priority, String filePath, Throwable ex, String tag, String message, Object... args) {
        File file = null;
        FileWriter writer = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedWriter bufferedWriter = null;
        String sdCardRoot = FileUtils.getSdcardPath();
        try {

            StringBuffer buffer = new StringBuffer();
            buffer.append("-------------------------|START|-------------------------");
            buffer.append("\n" + DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date()).toString() + "  ");
            if (priority == Log.VERBOSE) {
                buffer.append("V/" + tag + ":");
            } else if (priority == Log.DEBUG) {
                buffer.append("D/" + tag + ":");
            } else if (priority == Log.INFO) {
                buffer.append("I/" + tag + ":");
            } else if (priority == Log.WARN) {
                buffer.append("W/" + tag + ":");
            } else if (priority == Log.ERROR) {
                buffer.append("E/" + tag + ":");
            } else if (priority == Log.ASSERT) {
                buffer.append("A/" + tag + ":");
            }

            if (args.length > 0) {
                message = String.format(message, args);
            }
            String log;
            if (ex == null) {
                log = message;
            } else {
                String logMessage = message == null ? ex.getMessage() : message;
                String logBody = Log.getStackTraceString(ex);
                log = String.format(LOG_FORMAT, logMessage, logBody);
            }
            Log.println(priority, tag, log);
            String fileLocation = TextUtils.isEmpty(filePath) ? Cons.PATH_CACHE_LOGS : filePath;
            file = FileUtils.getExpectFileOnSDCard("log.txt", fileLocation);
            if (file.exists() && file.length() > MAX_LOG_FILE_LENTH) file.delete();
            if (!file.exists()) file.createNewFile();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            bufferedWriter.append("\n" + buffer.toString() + log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        }
    }
    //---------record the log to file |END|-----------------

}
