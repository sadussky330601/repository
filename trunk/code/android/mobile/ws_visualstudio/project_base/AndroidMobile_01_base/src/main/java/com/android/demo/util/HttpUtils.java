package com.android.demo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linwb
 */
public class HttpUtils {

    private static String TAG = "NetWorkUtil";
    private static String SECRET_HEADER_KEY = "zbwApiKey";
    //	private static String SECRET_HEADER_VALUE = "abc";
    private static String SECRET_HEADER_VALUE = "4e7yke4aqsbaa3ddc3gwutjj5mp3d284";

    public static String doGet(String urlString) {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
        HttpConnectionParams.setSoTimeout(httpParams, 20000);
        HttpClient client = new DefaultHttpClient(httpParams);
        HttpGet get = new HttpGet(urlString);
        get.setHeader(new BasicHeader(SECRET_HEADER_KEY, SECRET_HEADER_VALUE));
        String result = "";
        try {
            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                result = EntityUtils.toString(resEntityGet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO
    public static String doGet(String urlString, Map<String, String> paras) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(SECRET_HEADER_KEY, SECRET_HEADER_VALUE);
        return doGet(urlString, paras, header);
    }

    // TODO
    public static String doGet(String urlString, Map<String, String> paras, Map<String, String> header) {
        String localUrl = urlString;
        String result = "";
        try {
            if (paras != null && !paras.isEmpty()) {
                boolean first = true;
                for (String key : paras.keySet()) {
                    if (first) {
                        localUrl += "?" + key + "=" + paras.get(key);
                        first = false;
                    } else {
                        localUrl += "&" + key + "=" + paras.get(key);
                    }
                }
            }

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
            HttpConnectionParams.setSoTimeout(httpParams, 20000);
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpGet get = new HttpGet(localUrl);
            if (header != null && !paras.isEmpty()) {
                for (String key : header.keySet()) {
                    get.setHeader(key, header.get(key));
                }
            }
            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                result = EntityUtils.toString(resEntityGet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doPost(String url, Map<String, String> headerParam) {
        HttpParams httpParams = new BasicHttpParams();
        // HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
        // HttpConnectionParams.setSoTimeout(httpParams, 20000);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(url);
        post.setHeader(SECRET_HEADER_KEY, SECRET_HEADER_VALUE);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : headerParam.keySet()) {
            params.add(new BasicNameValuePair(key, headerParam.get(key)));
        }
        String result = "";
        try {
            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
        }
        return result;
    }

    ;

    public static HttpURLConnection createURLConnection(String url) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) new URL(url).openConnection();
            urlCon.setConnectTimeout(10000);
            urlCon.setReadTimeout(30000);
        } catch (Exception e) {
            // Log.e(TAG, "createURLConnection:" + e.getMessage());
        }
        return urlCon;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public static void showWeb(WebView webView, String urlStr) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(urlStr);
    }

    public static String doPost(String url, String jsonString, Map<String, String> header) {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
        HttpConnectionParams.setSoTimeout(httpParams, 20000);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(url);
        if (header != null && !header.isEmpty()) {
            for (String key : header.keySet()) {
                post.setHeader(key, header.get(key));
            }
        }
        String result = null;
        try {
            post.setEntity(new StringEntity(jsonString, "utf-8"));
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            } else {

            }
        } catch (IOException e) {

        }
        return result;
    }

    ;

    public static String doPost(String url, String jsonString) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(SECRET_HEADER_KEY, SECRET_HEADER_VALUE);
        header.put("Content-Type", "application/json");
        return doPost(url, jsonString, header);
    }

    ;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailabel");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
