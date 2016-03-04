package com.android.demo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.demo.MainActivity;
import com.android.demo.R;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 * 1. 系统各种Manager对象，静态引用Manager对象
 * 2. 网络相关：判断网络连接，网络类型
 * 3. GPS相关：判断GPS是否可用。
 * 4. 获取应用程序包相关的信息。1,包相关信息，2 应用程序签名SIGN,公钥
 * 5. 进程判断，Service判断，
 * 6. intent相关调用：拨打电话，
 * 7. ContentProvider 相关操作。
 * 8. 资源文件相关操作。
 * 9.
 */
public class Comutils {


    private final static String TAG = "Comutils";
    private PackageInfo packageInfo;
    private String packageName;
    private String packageCodePath;
    private String packageResourcePath;

    private static PackageManager packageManager;
    private static WindowManager windowManager;
    // 电话管理器
    private static TelephonyManager telephonyManager;
    // 短消息服务  1）发送和接受 2）监控SMS消息 3）管理SMS文件夹
    private static SmsManager smsManager;
    // asset资源管理
    private static AssetManager assetManager;
    // 资源管理
    private static Resources resources;
    public static final String AUDIO_SERVICE = "audio"; // 定义音频服务的ID
    public static final String WINDOW_SERVICE = "window";//   定义窗口服务的ID
    public static final String NOTIFICATION_SERVICE = "notification";


    private Context mContext;
    private static Comutils sIntance;

    private Comutils(Context context) {
        this.mContext = context;
        this.init(context);
    }

    public static Comutils getInstance(Context context) {
        if (null == sIntance) {
            synchronized (Comutils.class) {
                if (null == sIntance) {
                    sIntance = new Comutils(context);
                }
            }
        }
        return sIntance;
    }


    public void init(Context context) {
        packageManager = context.getPackageManager();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        smsManager = SmsManager.getDefault();
        assetManager = context.getAssets();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        resources = context.getResources();

        packageName = context.getPackageName();
        packageCodePath = context.getPackageCodePath();
        packageResourcePath = context.getPackageResourcePath();
        Logger.i(TAG, "\npackageName:" + packageName);
        Logger.i(TAG, "\npackageCodePath:" + packageCodePath);
        Logger.i(TAG, "\npackageResourcePath:" + packageResourcePath);

        try {
            packageInfo = packageManager.getPackageInfo(packageName,
                    PackageManager.GET_ACTIVITIES | PackageManager.GET_RECEIVERS
                            | PackageManager.GET_SERVICES | PackageManager.GET_PROVIDERS
                            | PackageManager.GET_INSTRUMENTATION | PackageManager.GET_INTENT_FILTERS
                            | PackageManager.GET_SIGNATURES | PackageManager.GET_RESOLVED_FILTER
                            | PackageManager.GET_META_DATA | PackageManager.GET_GIDS
                            | PackageManager.GET_DISABLED_COMPONENTS | PackageManager.GET_SHARED_LIBRARY_FILES
                            | PackageManager.GET_URI_PERMISSION_PATTERNS | PackageManager.GET_PERMISSIONS
                            | PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_CONFIGURATIONS
                            | PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS | PackageManager.GET_PERMISSIONS);
            Parcel parcle = Parcel.obtain();
            packageInfo.writeToParcel(parcle, 0);
            Logger.i(TAG, "\npackageInfo:" + packageName
                            + "\npackageName:" + packageInfo.packageName
                            + "\nversionCode:" + packageInfo.versionCode
                            + "\nversionName:" + packageInfo.versionName
                            + "\nsharedUserId:" + packageInfo.sharedUserId
                            + "\nsharedUserLabel:" + packageInfo.sharedUserLabel
                            + "\nfirstInstallTime:" + packageInfo.firstInstallTime
                            + "\nlastUpdateTime:" + packageInfo.lastUpdateTime
                            + "\ngids:" + packageInfo.gids
                            + "\nactivities:" + packageInfo.activities
                            + "\nactivities:" + packageInfo.activities
                            + "\nreceivers:" + packageInfo.receivers
                            + "\nservices:" + packageInfo.services
                            + "\nproviders:" + packageInfo.providers
                            + "\ninstrumentation:" + packageInfo.instrumentation
                            + "\npermissions:" + packageInfo.permissions
                            + "\nrequestedPermissions:" + packageInfo.requestedPermissions
                            + "\nsignatures:" + packageInfo.signatures
                            + "\nconfigPreferences:" + packageInfo.configPreferences
                            + "\nreqFeatures:" + packageInfo.reqFeatures
//                    + "\ninstallLocation:" + packageInfo.installLocation
//                    + "\nrequiredForAllUsers:" + (packageInfo.requiredForAllUsers ? 1 : 0)
//                    + "\nrestrictedAccountType:" + packageInfo.restrictedAccountType
//                    + "\nrequiredAccountType:" + packageInfo.requiredAccountType
            );

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Logger.i(TAG, isGpsEnabled(context) ? "GPS已经打开!" : "GPS未打开!");
        Logger.i(TAG, isNetworkConn(context) ? "网络已经连接!" : "网络未连接!");
        Logger.i(TAG, "网络类型: " + getNetWorkType(context));
        Logger.i(TAG, "网络所属G?: " + getNetworkClassName(telMgr.getNetworkType()));


        /**
         * <code>
         * // 颜色资源获取
         int color = resources.getColor(R.color.test_color_1);
         String appname = resources.getString(R.string.app_name);
         // 获取尺寸
         float dimen = resources.getDimension(R.dimen.test_dimen_1);
         // drawable
         Drawable drawable = resources.getDrawable(R.drawable.icon);
         // ColorDrawable
         ColorDrawable colorDrawable = (ColorDrawable) resources.getDrawable(R.drawable.icon);
         // XML资源
         XmlResourceParser xml = resources.getXml(R.xml.test);
         // 原始资源，在 res/raw文件夹下面
         InputStream in = resources.openRawResource(R.raw.test);
         // 使用资产，在assets文件夹下面
         try {
         InputStream inputStream = assetManager.open("/test/test.txt");
         } catch (IOException e) {
         e.printStackTrace();
         }
         * </code>
         */
    }


    /**
     * @param context
     */
    public static void getScree(Context context) {
//        if(context instanceof Activity){
//            // 获取设备宽和高
//            if (android.os.Build.VERSION.SDK_INT >= 13) {
//                Point point = new Point();
//                Display display =  ((Activity)context).getWindow().getWindowManager().getDefaultDisplay();
//                display.
//
//                .getSize(display);
//                windowWidth = display.x;
//                windowHeight = display.y;
//            } else {
//                Display display = this.getWindowManager().getDefaultDisplay();
//                windowWidth = display.getWidth();
//                windowHeight = display.getHeight();
//            }
//        }
//


    }


    /**
     * @return bool
     * @Description: 判断该程序是否在前台运行，为判断消息通知
     */
    public static boolean isTopActivity(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationInfo().packageName;
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    //get name of current progress
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * juge the service is running or not
     *
     * @param context
     * @param serviceClassName
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceClassName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClassName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void toastShort(Context context, String tip) {
        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }

    public static void toastShort(Context context, int msgResId) {
        Toast.makeText(context, msgResId, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void toastLong(Context context, int msgResId) {
        Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
    }


    //-------Network |START|--------

    public static String getNetWorkType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        String netWorkTypeStr = "UNKNOW";
        if (networkInfo == null) {
            return netWorkTypeStr;
        }
        int connType = networkInfo.getType();
        if (connType == ConnectivityManager.TYPE_MOBILE) {
            netWorkTypeStr = getNetworkTypeName(telMgr.getNetworkType());
        } else if (connType == ConnectivityManager.TYPE_WIFI) {
            netWorkTypeStr = "WIFI";
        } else if (connType == ConnectivityManager.TYPE_BLUETOOTH) {
            netWorkTypeStr = "BLUETOOTH";
        }
        return netWorkTypeStr;
    }

    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;
    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;
    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;
    /**
     * Current network is CDMA: Either IS95A or IS95B
     */
    public static final int NETWORK_TYPE_CDMA = 4;
    /**
     * Current network is EVDO revision 0
     */
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    /**
     * Current network is EVDO revision A
     */
    public static final int NETWORK_TYPE_EVDO_A = 6;
    /**
     * Current network is 1xRTT
     */
    public static final int NETWORK_TYPE_1xRTT = 7;
    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;
    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;
    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;
    /**
     * Current network is iDen
     */
    public static final int NETWORK_TYPE_IDEN = 11;
    /**
     * Current network is EVDO revision B
     */
    public static final int NETWORK_TYPE_EVDO_B = 12;
    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;
    /**
     * Current network is eHRPD
     */
    public static final int NETWORK_TYPE_EHRPD = 14;
    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;

    /**
     * Unknown network class. {@hide}
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * Class of broadly defined "2G" networks. {@hide}
     */
    public static final int NETWORK_CLASS_2_G = 1;
    /**
     * Class of broadly defined "3G" networks. {@hide}
     */
    public static final int NETWORK_CLASS_3_G = 2;
    /**
     * Class of broadly defined "4G" networks. {@hide}
     */
    public static final int NETWORK_CLASS_4_G = 3;

    /**
     * Return general class of network type, such as "3G" or "4G". In cases
     * where classification is contentious, this method is conservative.
     *
     * @hide
     */
    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS: //为移动和联通2G
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA: //为电信2G
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS: // 联通3G
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A://电信3G
            case NETWORK_TYPE_HSDPA: //联通3G
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B://为电信3G
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP: //联通3G
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }


    public static String getNetworkClassName(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS: //为移动和联通2G
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA: //为电信2G
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return "2G网络";
            case NETWORK_TYPE_UMTS: // 联通3G
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A://电信3G
            case NETWORK_TYPE_HSDPA: //联通3G
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B://为电信3G
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP: //联通3G
                return "3G网络";
            case NETWORK_TYPE_LTE:
                return "4G网络";
            default:
                return "UNKOWN";
        }
    }

    /**
     * {@hide}
     */
    public static String getNetworkTypeName(int type) {
        switch (type) {
            case NETWORK_TYPE_GPRS:
                return "GPRS";
            case NETWORK_TYPE_EDGE:
                return "EDGE";
            case NETWORK_TYPE_UMTS:
                return "UMTS";
            case NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case NETWORK_TYPE_HSPA:
                return "HSPA";
            case NETWORK_TYPE_CDMA:
                return "CDMA";
            case NETWORK_TYPE_EVDO_0:
                return "CDMA - EvDo rev. 0";
            case NETWORK_TYPE_EVDO_A:
                return "CDMA - EvDo rev. A";
            case NETWORK_TYPE_EVDO_B:
                return "CDMA - EvDo rev. B";
            case NETWORK_TYPE_1xRTT:
                return "CDMA - 1xRTT";
            case NETWORK_TYPE_LTE:
                return "LTE";
            case NETWORK_TYPE_EHRPD:
                return "CDMA - eHRPD";
            case NETWORK_TYPE_IDEN:
                return "iDEN";
            case NETWORK_TYPE_HSPAP:
                return "HSPA+";
            default:
                return "UNKNOWN";
        }
    }


    //is Network connect ?
    public static boolean isNetworkConn(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    //is Network Available
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Logger.i(TAG, "Network Unavailabel");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Logger.i(TAG, "Network Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //is GPS Enabled
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE));
        // List<String> accessibleProviders =
        // locationManager.getProviders(true);
        // return accessibleProviders != null && accessibleProviders.size() > 0;
        return locationManager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    //-------Network |END|--------


    //------INTENT|START|---------
    // intent作用：
    // 1） 从一个应用程序调用其他应用程序
    // 2） 可以使用它从应用程序内部调用内部或外部组件
    // 3） 可以使用它触发事件，这样其他用户就可以通过发布--订阅模型类似的方式进行响应
    // 4)拨打电话 // Intent callMe = new Intent( Intent.ACTION_CALL,
    // Uri.parse("tel:"+whichTel.tel));
    // context.startActivity(callMe);
    // 5) 广播消息
    // 6) 启动service
    // 7) 启动activity
    // 7) 显示网页和一组联系人
    // 当一个应用程序启动时，Android系统默认启动
    // Action为android.intent.action.MAIN、
    // Category为android.intent.category.LAUNCHER的Activity
    // Action与Data组合，去启动一些系统的Activity。
    // Action代表了动作，Data代表了数据——用Action去操作指定数据。
    // 通过这种隐式Intent，可以非常轻松地用自己开发的Activity去代替系统的Activity。

    /**
     * <font size='2' color='red'> <strong> intent遵循的规则</strong></font><br/>
     * 1)在算法顶层，只有一个组件名字，如果设置了此名称，那么intent的所有其他 方面都被忽略<br/>
     * 2)看intent的操作(action)属性，如果intent指定了一项操作，那么目标活动必须在其intent过滤器中
     * 列出该操作，如果没有指定其他特性，那么android将调用此活动，如果有多个活动， android将提供活动选择器<br/>
     * 3)第三步查看intent数据(data)部分，如果Intent指定了数据URI，并且Intent未提供数据类型，
     * 那么android就会通过ContentProvider.getType()从此URI来获取数据类型，目标活动必须通过 intent过滤器来表明他
     * 可以处理此类数据。如果数据URI不是内容URI，或者未指定数据类型，那么就会考虑 URI方案。目标活动应该指明它可以处理具有此类方案的RUI.<br/>
     * 4)Android然后查看类别(category)，Android只会挑选与该类型匹配的活动，因此，如果指定了intent类别，
     * 那么目标活动将在其过滤器中声明此类别。<br/>
     */

    //------INTENT|END|---------


    //------ContentProvider数据包装器|START|---------
    // contentProvider 是数据包装器，Android支持通过名为ContentProvider的类似REST
    // 的抽象来公开数据源或者（数据提供程序）,只有希望与外部或者在应用程序之间共享数据时，
    // 才需要使用ContentProvider抽象。

    // MIME类型规范，text/html 类型/子类型
    // 每个主类型都包含子类型。如果供应商有专用的数据格式，那么子类型名称将以vnd开头，例如
    // 微软的 excel表格使用子类型 vnd.ms-excel标示。
    // 一些子类型以 x- 开头，这些子类型是不必注册的非标准子类型，它们被认为是2个协作机构共同定义的
    // 私有值。 比如 application/x-tar ; audio/x-aiff
    /**
     * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
     */
    public static final String TEST_TYPE = "vnd.android.cursor.dir/vnd.kailen.test";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single note.
     */
    public static final String TEST_ITEM_TYPE = "vnd.android.cursor.item/vnd.kailen.test";

    // 授权
    public static final String AUTHORITY = "com.kailen.base.provider.Test";
    // android:authorities="com.kailen.base.provider.Test"

    public static final Uri TEST_URI = Uri.parse("content://" + AUTHORITY + "/notes");
    // public static final Uri SINGEL_TEST_URI =Uri.withAppendedPath(TEST_URI,
    // "11") ;
    public static final Uri SINGEL_TEST_URI = ContentUris.withAppendedId(TEST_URI, 11);

    public void ttt3(Context context) {
        Cursor cursor = ((Activity) context).managedQuery(SINGEL_TEST_URI, null, null, null, null);

    }

    // 获取联系人信息
    public void ttt4(Context context) {
        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        Uri myContactUri = ContentUris.withAppendedId(contactUri, 11);
        Cursor cursor = ((Activity) context).managedQuery(myContactUri, null, null, null, null);
        String[] projection = new String[]{Contacts.People._ID};
    }

    // 查询的几种不同方式
    public void ttt5(Context context) {
        // 方式1
        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        Uri myContactUri = ContentUris.withAppendedId(contactUri, 11);
        Cursor cursor = ((Activity) context).managedQuery(myContactUri, null, null, null, null);

        // 方式2
        final Uri uri = Uri.parse("content://com.android.contacts/contacts/11");
        String noteId = uri.getPathSegments().get(1);
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables("notes");
        builder.appendWhere("_id = " + noteId);
        SQLiteDatabase db = null;
        builder.query(db, null, null, null, null, null, null);
        // 方式3
        db.rawQuery("select * from notes where _id=? ", new String[]{"1"});
        // 方式4
        // db.query(table, columns, selection, selectionArgs, groupBy, having,
        // orderBy)
    }

    // ContentProvider实例查询
    public void testNote(Context context) {
        final String AUTHORITY = "com.google.provider.NotePad";
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes");
        final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";
        final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(CONTENT_URI, null, null, null, null);
        Logger.d(TAG, "查询完成！");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Log.d(TAG,
                    "\tNOTE.TITLE= " + cursor.getString(cursor.getColumnIndex("title"))
                            + "\tNOTE.NOTE = " + cursor.getString(cursor.getColumnIndex("note"))
                            + "\tNOTE.created = " + cursor.getLong(cursor.getColumnIndex("created"))
                            + "\tNOTE.modified = "
                            + cursor.getLong(cursor.getColumnIndex("modified")));
        }
    }

    // ContentProvider实例查询
    // 更新及删除与此类似
    public void testNoteInsert(Context context) {
        final String AUTHORITY = "com.google.provider.NotePad";
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("title", "this is a new title !");
        values.put("note", "this is a new note ! !");
        resolver.insert(CONTENT_URI, values);
    }
    //------ContentProvider数据包装器|END|---------

    //------Android 获取签名、公钥方法|START|---------
    private byte[] getSign(Context context, String targetPackage) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();
        while (iter.hasNext()) {
            PackageInfo info = iter.next();
            String packageName = info.packageName;
            // 按包名 取签名
            if (packageName.equals(targetPackage)) {
                return info.signatures[0].toByteArray();
            }
        }
        return null;
    }

    public static String getPublicKey(byte[] signature) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));
            String publickey = cert.getPublicKey().toString();
            publickey = publickey.substring(publickey.indexOf("modulus: ") + 9, publickey.indexOf("\n", publickey.indexOf("modulus:")));
            Logger.d("TRACK", publickey);
            return publickey;
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //------Android 获取签名、公钥方法|END|---------


    //------ intent 调用  |START|---------
    public static void invokeCall(Context context, String tell) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tell));
        context.startActivity(intent);
    }


    //------ SHOW POPWINDOWS |START|---------

    public static PopupWindow showPopWin(final Activity context, View achor, int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutResId, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        PopupWindow pop = new PopupWindow(contentView, w, h);
        // 设置SelectPicPopupWindow的View
        pop.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        // this.setWidth(w / 2 + 50);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        pop.setSoftInputMode(WindowManager.LayoutParams.ANIMATION_CHANGED);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        // 刷新状态
        pop.update();
        // // 实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(0000000000);
        // // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        // this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.Popup_PopLcResult);

        pop.showAtLocation(achor, Gravity.BOTTOM, 0, 0);
        return pop;
    }


    //------ 资源尺寸之间的相互转换 |START|---------
    public static int pixl2DIP(float pixl, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                pixl, context.getResources().getDisplayMetrics());
    }


    public static void notification(String ticker, String title, String content, Context context) {
        // 先设定RemoteViews
        RemoteViews view_custom = new RemoteViews(context.getPackageName(), R.layout.notification_view_custom);
        // 设置对应IMAGEVIEW的ID的资源图片
//        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.ic_launcher);
        // view_custom.setInt(R.id.custom_icon,"setBackgroundResource",R.drawable.icon);
        view_custom.setTextViewText(R.id.tv_custom_title, title);
        view_custom.setTextViewText(R.id.tv_custom_content, content);
        view_custom.setTextViewText(R.id.tv_custom_time, DateUtils.format_4(new Date()));
        // 设置显示
        view_custom.setViewVisibility(R.id.tv_custom_time, View.VISIBLE);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        // 对Builder进行配置
        mBuilder.setContentTitle(title).setContentText(content) // 设置通知栏显示内容
                .setContentIntent(getDefalutIntent(context, Notification.FLAG_AUTO_CANCEL)) // 设置通知栏点击意图
                        // .setNumber(number) //设置通知集合的数量
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker(ticker) // 通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) // 设置该通知优先级
                        // .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)// 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                        // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
                        // requires VIBRATE permission
//                .setSmallIcon(R.drawable.ic_launcher)// 设置通知小ICON
                .setContent(view_custom)
                .setVibrate(new long[]{0, 300, 500, 700}); // 功能：设置震动方式。
        mNotificationManager.notify(0x1111, mBuilder.build());
        /**
         * <code>
         * 提醒标志符成员：
         Notification.FLAG_SHOW_LIGHTS       //三色灯提醒，在使用三色灯提醒时候必须加该标志符
         Notification.FLAG_ONGOING_EVENT     //发起正在运行事件（活动中）
         Notification.FLAG_INSISTENT   		//让声音、振动无限循环，直到用户响应 （取消或者打开）
         Notification.FLAG_ONLY_ALERT_ONCE   //发起Notification后，铃声和震动均只执行一次
         Notification.FLAG_AUTO_CANCEL       //用户单击通知后自动消失
         Notification.FLAG_NO_CLEAR          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
         Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务
         *
         </code>
         */
    }

    public static PendingIntent getDefalutIntent(Context context, int flags) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, flags);
        return pendingIntent;
    }
}
