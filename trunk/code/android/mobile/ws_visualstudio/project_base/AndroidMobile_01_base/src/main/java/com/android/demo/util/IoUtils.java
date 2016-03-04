package com.android.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/9/11.
 */
public class IoUtils {

    // 辅助方法，用于把流转换为字符串
    public static String convertStreamToString(InputStream is)
            throws IOException {
        return convertStreamToString(is, "UTF-8");
    }

    // 辅助方法，用于把流转换为字符串
    public static String convertStreamToString(InputStream is, String encoding) {
        ByteArrayOutputStream bao = null;
        String result = "";
        try {
            bao = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                bao.write(buf, 0, len);
            }
            result = new String(bao.toByteArray(), encoding);
        } catch (Exception e) {
        }
        return result;
    }

}
