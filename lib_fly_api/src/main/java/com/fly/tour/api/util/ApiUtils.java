package com.fly.tour.api.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fly.tour.api.BuildConfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 工具类
 *
 * @author Administrator
 */
public class ApiUtils {

//    public final static String cacheDir = Environment
//            .getExternalStorageDirectory().getPath()
//            + "/android/data/"
//            + ShowSelfApp.getInstance().getPackageName();
    public static Dialog UpdateDialog;
    public static long lasttime;// 上次通知时间
//    private static MyDialog myDialog;
    private static long lastClickTime;
    private static long lastClickRoomTime;
    private static ProgressDialog progressDialog;
    private static AlertDialog alertDialog;

//    static {
//        System.loadLibrary("aes");
//    }

    public ApiUtils() {
    }

//    private native static String getKey();

//    public static String getAesKey() {
//        if (BuildConfig.SERVER_TYPE.equals("local")) {
//            return "12345678";
//        } else {
//            return getKey();
//        }
//    }

//    public static void clearMediaCache() {
//        File[] files = new File(cacheDir + "/media/").listFiles();
//        if (files != null) {
//            for (File f : files) {
//                f.delete();
//            }
//        }
//    }

//    public static String parseCityLocation(String location) {
//        if (!TextUtils.isEmpty(location)) {
//
//            ProvinceInfo pInfo = City.queryOnePro(Integer.parseInt(location));
//            if (TextUtils.isEmpty(pInfo.getProName())) {
//                return "红人直播星球";
//            }
//            return pInfo.getProName();
//        }
//        return null;
//    }
//
//    public static void showLog(String content) {
//        DLog.i("showself", content);
//    }
//
//    public static void showToast(Context context, String content) {
//        if (!TextUtils.isEmpty(content)) {
//            Toast.makeText(ShowSelfApp.getContext(), content, Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
//
//    /**
//     * 显示吐司内容
//     *
//     * @param content 吐司内容
//     */
//    public static void showToast(String content) {
//        showToast(ShowSelfApp.getContext(), content);
//    }
//
//    public static void showToast(int strId) {
//        showToast(ShowSelfApp.getContext(), ShowSelfApp.getContext().getString(strId));
//    }
//
//    public static void showToastShort(Context context, String content) {
//        if (!TextUtils.isEmpty(content)) {
//            Toast.makeText(ShowSelfApp.getContext(), content, Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
//
//    public static void showToast(Context context, int resId) {
//        Toast.makeText(ShowSelfApp.getContext(), resId, Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    public static void showToastShort(Context context, int resId) {
//        Toast.makeText(ShowSelfApp.getContext(), resId, Toast.LENGTH_SHORT)
//                .show();
//    }

    public static boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            if (netInfo != null
                    && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    public static final Bitmap roundTopAndScale(Bitmap resultBitmap, float roundPx) {
        if (resultBitmap == null || roundPx < 0)
            return resultBitmap;
        int width = resultBitmap.getWidth();
        int height = resultBitmap.getHeight();
        Rect srcRect = new Rect(0, 0, width, height);
        Rect desRect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(desRect);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Config config = Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas roundCanvas = new Canvas(bitmap);
        roundCanvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        roundCanvas.drawRect(new RectF(0, height - roundPx * 2, width, height), paint);
        Mode mode = Mode.SRC_IN;
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(mode);
        paint.setXfermode(porterDuffXfermode);
        roundCanvas.drawBitmap(resultBitmap, srcRect, desRect, paint);
        //  resultBitmap.recycle();
        return bitmap;
    }

    /**
     * 检测网络是否正常
     *
     * @param context
     * @return
     */
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 防止多次点击
     *
     * @return
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        if (timeD < 400) {
            return true;
        }
        return false;
    }

    /**
     * 防止免费花多次点击
     *
     * @return
     */
    public static boolean isEnterRoomFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickRoomTime;
        lastClickRoomTime = time;
        if (timeD < 1000) {
            return true;
        }
        return false;

    }

    public static boolean isShardFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        if (timeD < 1000) {
            return true;
        }
        return false;
    }

    /**
     * 检测字符串可否转为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 根据日期计算年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) {
        int age = 0;
        if (birthDay != null) {
            Calendar cal = Calendar.getInstance();

            if (cal.before(birthDay)) {
                return -1;
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            Calendar call = Calendar.getInstance();
            call.setTime(birthDay);
            int yearBirth = call.get(Calendar.YEAR);
            int monthBirth = call.get(Calendar.MONTH);
            int dayOfMonthBirth = call.get(Calendar.DAY_OF_MONTH);
            age = yearNow - yearBirth;
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    } else {
                        // do nothing
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            } else {
                // monthNow<monthBirth
                // donothing
            }
        }

        return age;
    }

//    /**
//     * 计算星座
//     *
//     * @param birthday
//     * @return
//     */
//    public static String getConstellation(Date birthday) {
//        String[] constellationArray = ShowSelfApp.getInstance().getResources()
//                .getStringArray(R.array.constellation);
//
//        if (birthday == null) {
//            return constellationArray[1];
//        }
//        int index = 0;
//
//        int[] edgeArray = {0, 19, 18, 20, 19, 20, 21, 22, 22, 22, 23, 22, 21};
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(birthday);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        if (day <= edgeArray[month]) {
//            index = month;
//        } else {
//            index = month + 1;
//        }
//
//        if (index > 12) {
//            index = 1;
//        }
//        return constellationArray[index];
//    }

    public static String getDescriptionOfDate2(long dateline) {

        try {
            String ret = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long create = dateline;
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600
                    + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数
            long ms_now = now.getTimeInMillis();
            if (ms_now - create < ms) {
                ret = new SimpleDateFormat("HH:mm").format(new Date(create));
            } else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
                ret = "昨天";

            } else {
                ret = new SimpleDateFormat("MM-dd").format(new Date(create));
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

//    /**
//     * 计算dateline是多长时间以前
//     *
//     * @param dateline
//     * @return
//     */
//    public static String getDescriptionOfDate(Date dateline) {
//        if (dateline == null) {
//            return "";
//        }
//        int minute = 60;
//        int hour = minute * 60;
//        int day = hour * 24;
//        int month = day * 30;
//        int year = month * 12;
//        long millions = System.currentTimeMillis() - dateline.getTime();
//        int second = (int) (millions / 1000);
//
//        if (second < 1 * minute) {
//            if (second <= 15) {
//                return ShowSelfApp.getInstance().getString(
//                        R.string.second_front);
//            } else {
//                return String.format(
//                        ShowSelfApp.getInstance().getString(
//                                R.string.second_front_d), second);
//            }
//        }
//
//        if (second < 2 * minute) {
//
//            return ShowSelfApp.getInstance().getString(
//                    R.string.minute_one_front);
//        }
//
//        if (second < 45 * minute) {
//            int minutes = second / minute;
//
//            return String.format(
//                    ShowSelfApp.getInstance().getString(
//                            R.string.minute_one_front_d), minutes);
//        }
//
//        if (second < 90 * minute) {
//
//            return ShowSelfApp.getInstance().getString(R.string.hour_one_front);
//        }
//
//        if (second < 24 * hour) {
//            int hours = second / hour;
//
//            return String.format(
//                    ShowSelfApp.getInstance().getString(
//                            R.string.hour_one_front_d), hours);
//        }
//
//        if (second < 48 * hour) {
//
//            return ShowSelfApp.getInstance().getString(R.string.yesterday);
//        }
//
//        if (second < 30 * day) {
//            int days = second / day;
//
//            return String.format(
//                    ShowSelfApp.getInstance().getString(R.string.front_day_d),
//                    days);
//        }
//
//        if (second < 12 * month) {
//            int months = second / month;
//            if (months <= 1) {
//
//                return ShowSelfApp.getInstance()
//                        .getString(R.string.front_month);
//            } else {
//
//                return String.format(
//                        ShowSelfApp.getInstance().getString(
//                                R.string.front_month_d), months);
//            }
//        } else {
//            int years = second / year;
//            if (year <= 1) {
//
//                return ShowSelfApp.getInstance().getString(R.string.front_year);
//            } else {
//
//                return String.format(
//                        ShowSelfApp.getInstance().getString(
//                                R.string.front_year_d), years);
//            }
//        }
//    }
//
//    /**
//     * 计算剩余时间
//     *
//     * @param dateline
//     * @return
//     */
//    public static String getLeftTime(long dateline) {
//        if (dateline < 60) {
//
//            return ShowSelfApp.getInstance().getString(R.string.one_minute_min);
//        } else {
//            if (dateline % 60 == 0) {
//
//                return ShowSelfApp.getInstance().getString(R.string.under)
//                        + dateline / 60
//                        + ShowSelfApp.getInstance().getString(R.string.minute);
//            } else {
//                return ShowSelfApp.getInstance().getString(R.string.under)
//                        + (dateline / 60 + 1)
//                        + ShowSelfApp.getInstance().getString(R.string.minute);
//            }
//        }
//    }

    /**
     * 半角转全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("!", "！").replaceAll(":", "：");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 替换http
     *
     * @param str
     * @return
     */
    public static String httpFilter(String str) {
        return str;
    }

    /**
     * 检测注册时的用户名是否合法
     */
    public static boolean checkUsername(String username) {
        return username.matches("[a-zA-Z_0-9]{4,20}");
    }

    /**
     * 检测注册时密码是否合法
     */
    public static boolean checkPassword(String password) {
        String pattern = "[a-zA-Z0-9]{6,20}";
        return password.matches(pattern);
    }

//    /**
//     * 检查新版本
//     *
//     * @param activity
//     * @return
//     */
//    public static Task checkVersion() {
//        HashMap<Object, Object> param = new HashMap<Object, Object>();
//        param.put("type", 2);
//        param.put("note", "");
//        Task task = new Task(Task.TASK_FEEDBACK, param);
//        return task;
//    }
//
//    public static Task checkVersionSetting() {
//        HashMap<Object, Object> param = new HashMap<Object, Object>();
//        param.put("type", 3);
//        param.put("note", "");
//        Task task = new Task(Task.TASK_CHECKVERSION, param);
//        return task;
//    }
//
//    /**
//     * 是否弹出更新对话框
//     *
//     * @param acti
//     * @param is_new_android is_new_android
//     * @param toastFlag      当前是最新版本时 是否提示用户
//     */
//    public static void upload(final Activity acti, String is_new_android,
//                              boolean toastFlag) {
//        try {
//            if (!TextUtils.isEmpty(is_new_android)) {
//                if (UpdateDialog == null || !UpdateDialog.isShowing()) {
//                    SharePrenceUtil.saveIsNeedUpdata(true, acti);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(acti);
//                    builder.setTitle(R.string.versions_update);
//                    builder.setMessage(is_new_android);
//                    builder.setPositiveButton(R.string.immediately_update,
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog,
//                                                    int which) {
//
//                                    Intent intent = new Intent();
//                                    intent.setAction("android.intent.action.VIEW");
//                                    Uri content_url = Uri
//                                            .parse(PersonalDataUtils.APPSTORE_URL);
//                                    intent.setData(content_url);
//                                    acti.startActivity(intent);
//                                    UpdateDialog.dismiss();
//                                    UpdateDialog = null;
//                                }
//                            });
//                    builder.setNegativeButton(R.string.not_update,
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog,
//                                                    int which) {
//                                    UpdateDialog.dismiss();
//                                    UpdateDialog = null;
//                                }
//                            });
//                    UpdateDialog = builder.create();
//                    UpdateDialog.show();
//                }
//            } else {
//                SharePrenceUtil.saveIsNeedUpdata(false, acti);
//                if (toastFlag) {
//                    ApiUtils.showToast(
//                            acti,
//                            ShowSelfApp.getInstance().getString(
//                                    R.string.already_newest_versions));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 显示"正在加载..."进度框
     *
     * @param context
     */
//    public static void showProgress(Context context) {
//        showCustomProgress(context, true, true);
//    }
//
//    public static void showProgress(Context context, String text, boolean cancelable, boolean outCancelable) {
//        showCustomProgress(context, cancelable, outCancelable);
//    }

//    public static void showCustomProgress(Context context, boolean cancelable, boolean outCancelable) {
//        showCustomProgress(context, cancelable, outCancelable, null);
//    }

//    public static void showCustomProgress(Context context, boolean cancelable, boolean outCancelable, DialogInterface.OnKeyListener onKeyListener) {
//        try {
//            dismissCustomProgress();
//            myDialog = new MyDialog();
//            myDialog.setCancelable(cancelable);
//            myDialog.setCanceledOnTouchOutside(outCancelable);
//            View view = LayoutInflater.from(context).inflate(R.layout.custom_only_progress_bar_dialog, null);
//            myDialog.showCustomDialog(context, view, 1, Gravity.CENTER,
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
//                    R.style.dialog_transparent, onKeyListener);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void dismissCustomProgress() {
//        try {
//            if (myDialog != null && myDialog.isShowing()) {
//                myDialog.dismiss();
//            }
//            myDialog = null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 关闭进度框
//     *
//     * @param context
//     */
//    public static void diessProgerss(Context context) {
//        dismissCustomProgress();
//    }
//
//    /**
//     * 显示无提示内容的进度框
//     *
//     * @param cancelable 是否能取消
//     * @param context
//     */
//    public static void showProgress(Context context, boolean cancelable) {
//        showCustomProgress(context, cancelable, cancelable);
//    }

//    public static void showVipDialog(final Activity activity, String content,
//                                     String btnok, String btncancle) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle(R.string.prompt);
//        builder.setMessage(content);
//
//        if (btnok != null) {
//            builder.setPositiveButton(btnok,
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            activity.startActivity(new Intent(activity,
//                                    VIPActivity.class));
//                            if (activity.getClass().getSimpleName()
//                                    .equals("PhotoScrollActivity")) {
//                                activity.finish();
//                            }
//                        }
//                    });
//        }
//
//        if (btncancle != null) {
//            builder.setNegativeButton(btncancle,
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (activity.getClass().getSimpleName()
//                                    .equals("PhotoScrollActivity")) {
//                                activity.finish();
//                            }
//                        }
//                    });
//        }
//        builder.create().show();
//    }

//    public static String parseCity(String lat, String lng) {
//        String result = "";
//        String url = "http://api.map.baidu.com/geocoder?output=json&location=lat,%20lng&key=37492c0ee6f924cb5e934fa08c6b1676";
//        url = url.replace("lat,%20lng", lat + ",%20" + lng);
//        String respJson = UploadParam.getHttp(url);
//        if (respJson != null && !"".equals(respJson)) {
//            try {
//                JSONObject jobj = new JSONObject(respJson).getJSONObject(
//                        "result").getJSONObject("addressComponent");
//                result = jobj.optString("province");
//                result += jobj.optString("city");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//        if ("".equals(result)) {
//            result = ShowSelfApp.getInstance().getString(R.string.spark);
//        }
//        return result;
//    }

    /**
     * 裁剪图片
     *
     * @param uri 图片uri aspectX aspectY 是宽高的比例 outputX outputY 是裁剪图片宽高
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int aspectX,
                                      int aspectY, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        /*
         * intent.putExtra("outputX", outputX); intent.putExtra("outputY",
         * outputY);
         */
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);// 系统的裁剪图片默认对图片进行人脸识别，当识别到有人脸时，会按aspectX和aspectY为1来处理，如果想设置成自定义的裁剪比例，需要设置noFaceDetection为true
        File mFile = new File(Environment.getExternalStorageDirectory()
                + "/myPhoto");
        if (!mFile.exists())
            mFile.mkdirs();
        File imFile = new File(Environment.getExternalStorageDirectory()
                + "/myPhoto/croptemp");
        Uri imageUri = Uri.fromFile(imFile);
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, 111);
    }

    /**
     * 裁剪图片
     *
     * @param uri 图片uri aspectX aspectY 是宽高的比例 outputX outputY 是裁剪图片宽高
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int aspectX,
                                      int aspectY, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        /*
         * intent.putExtra("outputX", outputX); intent.putExtra("outputY",
         * outputY);
         */
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);// 系统的裁剪图片默认对图片进行人脸识别，当识别到有人脸时，会按aspectX和aspectY为1来处理，如果想设置成自定义的裁剪比例，需要设置noFaceDetection为true
        File mFile = new File(Environment.getExternalStorageDirectory()
                + "/myPhoto");
        if (!mFile.exists())
            mFile.mkdirs();
        File imFile = new File(Environment.getExternalStorageDirectory()
                + "/myPhoto/croptemp");
        Uri imageUri = Uri.fromFile(imFile);
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, requestCode);
    }

    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
//        DLog.i("nowvideo", "rotation =" + rotation);
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    public static int getFistPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
//        DLog.i("nowvideo", "rotation =" + rotation);
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    public static int getPictureDegree(int rotation) {
        // 获得手机的方向
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            default:
                degree = 0;
                break;
        }
        return degree;
    }

//    /**
//     * 发送通知
//     *
//     * @param context
//     * @param message 通知内容
//     * @param flage   当前是否在秀色应用里
//     */
//    public static void showNotification(Context context, String message,
//                                        String url, boolean flage, int noteid) {
//        lasttime = System.currentTimeMillis();
//        PreferenceOfLoginInfo ofLoginInfo = PreferenceOfLoginInfo.getInstance();
//        Boolean soundFlag = ShowSelfApp.getSoundFlag();
//        if (soundFlag == null) {
//            soundFlag = ofLoginInfo.getSoundFlag();
//            ShowSelfApp.setSoundFlag(soundFlag);
//        }
//        PendingIntent pendingIntent = null;
//
//        // ----------------
//        Intent intent = new Intent();
//        int loginType = PreferenceOfLoginInfo.getInstance().getLoginType();
//        if (loginType == -1) {// 注销登录
//            intent.setClass(context, LoadingActivity.class);
//        } else {
//            try {
//                ActivityManager am = (ActivityManager) context
//                        .getSystemService(Context.ACTIVITY_SERVICE);
//                List<RunningTaskInfo> rs = am.getRunningTasks(100);
//                boolean b = false;// 秀色有没有在后台运行
//                for (RunningTaskInfo r : rs) {
//                    if ("com.showself.ui".equals(r.baseActivity
//                            .getPackageName())) {
//                        b = true;
//                        break;
//                    }
//                }
//                if (b) {
//                    Intent i = CustomUrlUtil.parseUrl(url, context);
//                    if (i != null) {
//                        intent = i;
//                    }
//                } else {
//                    intent.setClass(context, HomeActivity.class);
//                }
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        }
//
//        pendingIntent = PendingIntent.getActivity(context, noteid, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        final NotificationManager nm = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification n = new Notification();
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
//                R.layout.notify_layout);
//        remoteViews.setTextViewText(R.id.tv_title, ShowSelfApp.getInstance()
//                .getString(R.string.app_name));
//        remoteViews.setTextViewText(R.id.tv_content, message);
//        n.contentView = remoteViews;
//        n.contentIntent = pendingIntent;
//        n.when = System.currentTimeMillis();
//        n.icon = R.drawable.showself;
//        n.tickerText = message;
//        if (soundFlag) {
//            n.defaults = Notification.DEFAULT_SOUND;
//        }
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
//        nm.notify(noteid, n);
//    }
//
//    public static Intent getNotificationIntent(Context context, String url) {
//        Intent intent = new Intent();
//        int loginType = PreferenceOfLoginInfo.getInstance().getLoginType();
//        if (loginType == -1) {// 注销登录
//            intent.setClass(context, LoadingActivity.class);
//            if (url != null && !TextUtils.isEmpty(url)) {
//                intent.putExtra("custom_url", url);
//            }
//        } else {
//            try {
//                ActivityManager am = (ActivityManager) context
//                        .getSystemService(Context.ACTIVITY_SERVICE);
//                List<RunningTaskInfo> rs = am.getRunningTasks(100);
//                boolean b = false;// 秀色有没有在后台运行
//                for (RunningTaskInfo r : rs) {
//                    if ("com.showself.ui".equals(r.baseActivity
//                            .getPackageName())) {
//                        b = true;
//                        break;
//                    }
//                }
//
//                if (b) {
//                    Intent i = CustomUrlUtil.parseUrl(url, context);
//                    if (i != null) {
//                        intent = i;
//                    }
//                } else {
//                    intent.setClass(context, LoadingActivity.class);
//                    if (url != null && !TextUtils.isEmpty(url)) {
//                        intent.putExtra("custom_url", url);
//                    }
//                }
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        }
//
//        return intent;
//    }
//
//    public static void dissNotification() {
//        Context context = ShowSelfApp.getInstance().getApplicationContext();
//        NotificationManager nm = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.cancelAll();
//    }

    /**
     * 圆角
     *
     * @param resultBitmap
     * @param roundPx
     * @return
     */
    public static final Bitmap roundAndScale(Bitmap resultBitmap, float roundPx) {
        if (resultBitmap == null || roundPx < 0)
            return resultBitmap;
        if (roundPx == 0) {
            roundPx = resultBitmap.getWidth() / 2;
        }
        int width = resultBitmap.getWidth();
        int height = resultBitmap.getHeight();
        Rect srcRect = new Rect(0, 0, width, height);
        Rect desRect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(desRect);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Config config = Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas roundCanvas = new Canvas(bitmap);
        roundCanvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        Mode mode = Mode.SRC_IN;
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(mode);
        paint.setXfermode(porterDuffXfermode);
        roundCanvas.drawBitmap(resultBitmap, srcRect, desRect, paint);
//		resultBitmap.recycle();
//		resultBitmap = null;
//		System.gc();
        return bitmap;
    }

    /**
     * 图片圆角半径始终为roundPx，与ImageView尺寸、图片尺寸无关
     */
    public static final Bitmap roundAndScale(Bitmap resultBitmap, int viewWidth, float roundPx) {
        if (resultBitmap == null || roundPx < 0)
            return resultBitmap;
        if (roundPx == 0) {
            roundPx = resultBitmap.getWidth() / 2;
        } else {
            roundPx *= 1.0 * resultBitmap.getWidth() / viewWidth;
        }
        int width = resultBitmap.getWidth();
        int height = resultBitmap.getHeight();
        Rect srcRect = new Rect(0, 0, width, height);
        Rect desRect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(desRect);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Config config = Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas roundCanvas = new Canvas(bitmap);
        roundCanvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        Mode mode = Mode.SRC_IN;
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(mode);
        paint.setXfermode(porterDuffXfermode);
        roundCanvas.drawBitmap(resultBitmap, srcRect, desRect, paint);
        return bitmap;
    }

    /**
     * 矩形转圆形
     */
    public static Bitmap makeRoundCorner(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int left = 0, top = 0, right = width, bottom = height;
        float roundPx = height / 2;
        if (width > height) {
            left = (width - height) / 2;
            top = 0;
            right = left + height;
            bottom = height;
        } else if (height > width) {
            left = 0;
            top = (height - width) / 2;
            right = width;
            bottom = top + width;
            roundPx = width / 2;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    /**
     * 对字符串进行base64解码
     *
     * @param basestr
     * @return
     */
    public static String base64ToUrl(String basestr) {
        if (basestr == null || "".equals(basestr))
            return "";
        try {
            return new String(Base64.decode(basestr, Base64.URL_SAFE), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static String parseLocation(String location) {
//        if (!TextUtils.isEmpty(location)) {
//            String[] local = location.split("-");
//            if (local.length < 2) {
//                return null;
//            }
//
//            ProvinceInfo pInfo = City.queryOnePro(Integer.parseInt(local[0]));
//            if (TextUtils.isEmpty(pInfo.getProName())) {
//                return null;
//            }
//            CityInfo cInfo = City.queryOneCity(Integer.parseInt(local[1]));
//            if (TextUtils.isEmpty(cInfo.getCityName())) {
//                return null;
//            }
//            if (local.length == 3) {
//                ZoneInfo zInfo = City.queryOneZone(Integer.parseInt(local[2]));
//                if (TextUtils.isEmpty(zInfo.getZoneName())) {
//                    return null;
//                }
//            }
//            if (pInfo.getProName().equals(cInfo.getCityName())) {
//                return pInfo.getProName();
//            } else {
//                return pInfo.getProName() + " " + cInfo.getCityName();
//            }
//        }
//        return null;
//    }
//
//    public static StateListDrawable createSelector(int selected, int unselected) {
//        Context context = ShowSelfApp.getInstance().getApplicationContext();
//        StateListDrawable states = new StateListDrawable();
//
//        states.addState(new int[]{android.R.attr.state_pressed}, context
//                .getResources().getDrawable(selected));
//        states.addState(new int[]{android.R.attr.state_focused}, context
//                .getResources().getDrawable(selected));
//        states.addState(new int[]{android.R.attr.state_selected}, context
//                .getResources().getDrawable(selected));
//        states.addState(new int[]{},
//                context.getResources().getDrawable(unselected));
//        return states;
//    }
//
//    public static boolean isNetworkConnected(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//        return ni != null && ni.isConnectedOrConnecting();
//    }
//
//    public static String getOrMakeIMEI() {
//        try {
//            ContentResolver resolver = ShowSelfApp.getInstance()
//                    .getApplicationContext().getContentResolver();
//            String idfa = Settings.System.getString(resolver, "com.sf.im");
//            if (!TextUtils.isEmpty(idfa)) {
//                return idfa;
//            } else {
//                idfa = SharePrenceUtil.getIDFA();
//                if (!TextUtils.isEmpty(idfa)) {
//                    Settings.System.putString(resolver, "com.sf.im", idfa);
//                    return idfa;
//                } else {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("s");
//                    Random random = new Random();
//                    for (int i = 0; i < 14; i++) {
//                        sb.append(random.nextInt(9));
//                    }
//                    idfa = sb.toString();
//                    Settings.System.putString(resolver, "com.sf.im", idfa);
//                    SharePrenceUtil.saveIDFA(idfa);
//                    return idfa;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static String getPubIdfa() {
//        try {
//            ContentResolver resolver = ShowSelfApp.getInstance()
//                    .getApplicationContext().getContentResolver();
//            String idfa = Settings.System.getString(resolver, "com.test.im");
//            if (!TextUtils.isEmpty(idfa)) {
//                return idfa;
//            } else {
//                idfa = SharePrenceUtil.getPUBIDFA();
//                if (!TextUtils.isEmpty(idfa)) {
//                    Settings.System.putString(resolver, "com.test.im", idfa);
//                    return idfa;
//                } else {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("s");
//                    Random random = new Random();
//                    for (int i = 0; i < 14; i++) {
//                        sb.append(random.nextInt(9));
//                    }
//                    idfa = sb.toString();
//                    Settings.System.putString(resolver, "com.test.im", idfa);
//                    SharePrenceUtil.savePUBIDFA(idfa);
//                    return idfa;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static boolean isRunningOnEmulator() {
//        TelephonyManager tm = (TelephonyManager) ShowSelfApp
//                .getInstance()
//                .getApplicationContext()
//                .getSystemService(
//                        ShowSelfApp.getInstance().getApplicationContext().TELEPHONY_SERVICE);
//        boolean flag = "000000000000000".equals(tm.getDeviceId());
//        return flag || Build.FINGERPRINT.toLowerCase().startsWith("generic")
//                || Build.BRAND.toLowerCase().contains("generic")
//                || Build.MODEL.toLowerCase().contains("sdk")
//                || Build.PRODUCT.toLowerCase().contains("sdk");
//    }

    /**
     * 方法描述：判断某一应用是否正在运行
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

    public static String convertCount(String countStr) {
        String result = "";
        Integer count = 0;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return countStr;
        }
        if (count <= 9999) {
            result = countStr;
        } else if (count >= 10000 && count <= 99999) {
            countStr = countStr.substring(0, 2);
            StringBuilder sb = new StringBuilder();
            sb.append(countStr.charAt(0));
            sb.append(".");
            sb.append(countStr.charAt(1));
            sb.append("万");
            result = sb.toString();
        } else {
            countStr = countStr.substring(0, countStr.length() - 4);
            result = countStr + "万";
        }
        return result;
    }

    public static boolean isTopActivity(Context context) {
        String packageName = context.getPackageName();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {

                return true;
            }
        }
        return false;
    }

//    /*
//     * 显示一个只有一个按钮的提示对话框
//     */
//    public static void showOneBtnDialog(Context context, int msgId) {
//
//        new AlertDialog.Builder(context).setTitle(R.string.dialog_title_notice).setMessage(msgId)
//                .setPositiveButton(R.string.dialog_button_confirm, null
//                ).setCancelable(false).create().show();
//    }
//
//    public static boolean isVisitor() {
//        int loginType = PreferenceOfLoginInfo.getInstance().getLoginType();
//        if (loginType == PreferenceOfLoginInfo.VISITOR_LOGIN_TYPE) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 根据图片url从网络或本地缓存获取Bitmap
     *
     * @param cacheDir
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static Bitmap getBitmapByUrl(File cacheDir, String url)
            throws MalformedURLException, IOException {
        int firstHalfLength = url.length() / 2;
        String localFilename = String.valueOf(url.substring(0, firstHalfLength)
                .hashCode());
        localFilename += String.valueOf(url.substring(firstHalfLength)
                .hashCode());
        File cacheFile = new File(cacheDir, localFilename);
        if (!cacheFile.exists() || cacheFile.length() <= 0) {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-type",
                    "application/x-java-serialized-object");
            int imageTotalSize = conn.getContentLength();
            int writeTotalSize = 0;
            if (conn.getResponseCode() == 200 || conn.getResponseCode() == 206) {
                BufferedInputStream is = new BufferedInputStream(
                        conn.getInputStream(), 8192);
                FileOutputStream os = new FileOutputStream(cacheFile);
                try {
                    byte[] bytes = new byte[1024];
                    int len = -1;
                    while ((len = is.read(bytes)) != -1) {
                        os.write(bytes, 0, len);
                        writeTotalSize += len;
                    }
                    if (writeTotalSize != imageTotalSize) {
                        cacheFile.delete();
                        return null;
                    }

                } finally {
                    try {
                        os.close();
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return null;
            }
        }
        Bitmap bmp = null;
        Options options = new Options();
        InputStream is = cacheFile.toURL().openStream();
        try {
            bmp = BitmapFactory.decodeStream(is, null, options);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bmp;
    }

//    public static void SaveLocalMd5(Context context) {
//        String md5 = null;
//
//        byte[] signInfo = getSign(context);
//        if (signInfo != null) {
//            md5 = Md5Encrypt.md5(signInfo);
//        }
//
//        SharePrenceUtil.SaveLocalMd5(context, md5);
//    }

    public static byte[] getSign(Context context) {
        Signature[] signs = getRawSignature(context, context.getPackageName());
        if ((signs == null) || (signs.length == 0)) {
            return null;
        } else {
            Signature sign = signs[0];
            // String signMd5 = MD5.getMessageDigest(sign.toByteArray());
            if (sign != null) {
                return sign.toByteArray();
            }
        }
        return null;
    }

    public static Signature[] getRawSignature(Context context,
                                              String packageName) {
        if ((packageName == null) || (packageName.length() == 0)) {
            return null;
        }
        PackageManager pkgMgr = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pkgMgr.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
        } catch (NameNotFoundException e) {
            return null;
        }
        if (info == null) {
            return null;
        }
        return info.signatures;
    }

//    public static void MD5FailedDialog(final Context context, String msg,
//                                       final String jumpUrl) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                .setTitle(R.string.dialog_title_notice)
//                .setPositiveButton(R.string.dialog_button_confirm,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                Intent intent;
//                                try {
//                                    intent = CustomUrlUtil.parseUrl(
//                                            jumpUrl, context);
//                                    context.startActivity(intent);
//                                } catch (Exception e) {
//
//                                    e.printStackTrace();
//                                }
//                                dialog.dismiss();
//                            }
//                        })
//                .setNegativeButton(R.string.dialog_button_cancel,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                dialog.dismiss();
//                            }
//                        }).setCancelable(false);
//        if (msg != null) {
//            builder.setMessage(msg);
//        } else {
//            builder.setMessage(R.string.dialog_text_md5_failed_default);
//        }
//        if (alertDialog != null && alertDialog.isShowing()) {
//
//        } else {
//            alertDialog = builder.create();
//            alertDialog.show();
//        }
//    }
//
//    /**
//     * 根据md5的校验结果判断是否弹出盗版提示
//     */
//    public static void handleMd5Result(Context context) {
//
//        Md5Info md5Info = SharePrenceUtil.getMd5Info(context);
//        if (!DLog.printLog) {
//            if (md5Info != null) {
//                if (!md5Info.getCheckResult()) {
//                    ApiUtils.MD5FailedDialog(context, md5Info.getMsg(), md5Info.getCustomUrl());
//                }
//            }
//        }
//    }
//
//    public static void ShowGameRechargeNotice(Context context) {
//        showToast(context, R.string.golden_egg_money_not_enough);
//    }
//
//    // 请求发送礼物时弹出金钱不足
//    public static void dialogForAsk(final Context context, String message) {
//        new AlertDialog.Builder(context)
//                .setTitle(R.string.dialog_title_notice)
//                .setMessage(message)
//                .setPositiveButton(R.string.get_money_free,
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int whichButton) {
//                                if (ApiUtils.isFastClick())
//                                    return;
//                                RouteManager.enterProductsActivity(context, 0);
//                            }
//                        }).setNegativeButton(R.string.negative, null).show();
//    }

    /**
     * 获取圆角位图的方法
     *
     * @param bitmap 需要转化成圆角的位图
     * @param pixels 圆角的度数，数值越大，圆角越大
     * @param heigth
     * @param width
     * @return 处理后的圆角位图
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels, int width, int heigth) {
        Bitmap output = Bitmap.createBitmap(width,
                heigth, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, heigth);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static String getChannel(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/em")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            return ret.substring(split[0].length() + 5);

        } else {
            return "";
        }
    }

//    public static void armyAssembleDialog(Context context, String fromName, String toName, int roomid) {
//        MyDialog dialog = new MyDialog();
//        ArmyAssembleDialogView view = new ArmyAssembleDialogView(context, dialog, roomid, fromName, toName);
//        dialog.showCustomDialog(context, view.getView(), 1, Gravity.CENTER,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, R.style.anim_sclae_inout_style);
//
//    }
//
//    public static void armyAssembleSucceedDialog(Context context, String armyName, String toName, int roomid) {
//        MyDialog dialog = new MyDialog();
//        ArmyAssembleSucceedDialogView view = new ArmyAssembleSucceedDialogView(context, dialog, roomid, armyName, toName);
//        dialog.showCustomDialog(context, view.getView(), 1, Gravity.CENTER,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, R.style.anim_sclae_inout_style);
//    }
//
//    public static CustomNoticeDialog showDialogWithTwoButton(Context context, String title, String note, String leftBtnText,
//                                                             int leftBtnTextColor, String rightBtnText, int rightBtnTextColor, CustomDialogActionCallBack callBack) {
//        CustomNoticeDialog dialog = new CustomNoticeDialog(context, callBack);
//        dialog.showDialogWithTwoButton(title, note, "", leftBtnText, leftBtnTextColor, rightBtnText, rightBtnTextColor);
//        return dialog;
//    }
//
//    public static MyDialog showDialogWithSingleButton(Context context, String title, String note, String note2,
//                                                      String content, int contentColor,
//                                                      CustomDialogActionCallBack callBack, boolean ifCancle) {
//        CustomNoticeDialog dialog = new CustomNoticeDialog(context, callBack, ifCancle);
//        dialog.showDialogWithSingleButton(title, note, note2, content, contentColor);
//        return dialog.getmDialog();
//    }
//
//    private native static String getKey();
//
//    public static String getAesKey() {
//        if (BuildConfig.SERVER_TYPE.equals("local")) {
//            return "12345678";
//        } else {
//            return getKey();
//        }
//    }
//
//    public static String getTwoRadomChar() {
//        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(chars.charAt((int) (Math.random() * 52)));
//        builder.append(chars.charAt((int) (Math.random() * 52)));
//        return builder.toString();
//    }
//
//    public static String getWebViewUserInfo(Context context) {
//        String infoString = null;
//        LoginResultInfo loginInfo = SharePrenceUtil.getLonginInfo(context);
//        try {
//            infoString = CipherUtil.encryptData(loginInfo.getUserId() + "_" + loginInfo.getSessionId(), getAesKey());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return infoString;
//    }

//    public static String getWebViewUserInfoEncrypt(Context context, String url) {
//        if (TextUtils.isEmpty(url)) {
//            return "";
//        }
//        // webview 增加用户信息参数
//        String userInfo = "?activity=";
//        if (url.contains("?")) {
//            userInfo = "&activity=";
//        }
//        url = url + userInfo + ApiUtils.getWebViewUserInfo(context);
//        return url;
//    }

    /**
     * 获取mac地址
     */
    public static String getMac(Context context) {

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    /**
     * 获取手机的MAC地址
     *
     * @return
     */
    public static String getMacAddress() {
        String str = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return macSerial;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

//    public static List<CountryAreaInfo> getCountArea() {
//        List<CountryAreaInfo> countryAreaInfos = new ArrayList<CountryAreaInfo>();
//        CountryAreaInfo countryAreaInfo = new CountryAreaInfo();
//        countryAreaInfo.setArea(244);
//        countryAreaInfo.setAreaName("安哥拉");
//        countryAreaInfos.add(countryAreaInfo);
//        CountryAreaInfo countryAreaInfo1 = new CountryAreaInfo();
//        countryAreaInfo1.setArea(93);
//        countryAreaInfo1.setAreaName("阿富汗");
//        countryAreaInfos.add(countryAreaInfo1);
//        CountryAreaInfo countryAreaInfo2 = new CountryAreaInfo();
//        countryAreaInfo2.setArea(355);
//        countryAreaInfo2.setAreaName("阿尔巴尼亚");
//        countryAreaInfos.add(countryAreaInfo2);
//        CountryAreaInfo countryAreaInfo3 = new CountryAreaInfo();
//        countryAreaInfo3.setArea(213);
//        countryAreaInfo3.setAreaName("阿尔及利亚");
//        countryAreaInfos.add(countryAreaInfo3);
//        CountryAreaInfo countryAreaInfo4 = new CountryAreaInfo();
//        countryAreaInfo4.setArea(376);
//        countryAreaInfo4.setAreaName("安道尔共和国");
//        countryAreaInfos.add(countryAreaInfo4);
//        CountryAreaInfo countryAreaInfo5 = new CountryAreaInfo();
//        countryAreaInfo5.setArea(1264);
//        countryAreaInfo5.setAreaName("安圭拉岛");
//        countryAreaInfos.add(countryAreaInfo5);
//
//        CountryAreaInfo countryAreaInfo6 = new CountryAreaInfo();
//        countryAreaInfo6.setArea(1268);
//        countryAreaInfo6.setAreaName("安提瓜和巴布达");
//        countryAreaInfos.add(countryAreaInfo6);
//        CountryAreaInfo countryAreaInfo7 = new CountryAreaInfo();
//        countryAreaInfo7.setArea(54);
//        countryAreaInfo7.setAreaName("阿根廷");
//        countryAreaInfos.add(countryAreaInfo7);
//        CountryAreaInfo countryAreaInfo8 = new CountryAreaInfo();
//        countryAreaInfo8.setArea(374);
//        countryAreaInfo8.setAreaName("亚美尼亚");
//        countryAreaInfos.add(countryAreaInfo8);
//        CountryAreaInfo countryAreaInfo9 = new CountryAreaInfo();
//        countryAreaInfo9.setArea(247);
//        countryAreaInfo9.setAreaName("阿森松");
//        countryAreaInfos.add(countryAreaInfo9);
//        CountryAreaInfo countryAreaInfo10 = new CountryAreaInfo();
//        countryAreaInfo10.setArea(61);
//        countryAreaInfo10.setAreaName("澳大利亚");
//        countryAreaInfos.add(countryAreaInfo10);
//        CountryAreaInfo countryAreaInfo11 = new CountryAreaInfo();
//        countryAreaInfo11.setArea(43);
//        countryAreaInfo11.setAreaName("奥地利");
//        countryAreaInfos.add(countryAreaInfo11);
//        CountryAreaInfo countryAreaInfo12 = new CountryAreaInfo();
//        countryAreaInfo12.setArea(994);
//        countryAreaInfo12.setAreaName("阿塞拜疆");
//        countryAreaInfos.add(countryAreaInfo12);
//        CountryAreaInfo countryAreaInfo13 = new CountryAreaInfo();
//        countryAreaInfo13.setArea(1242);
//        countryAreaInfo13.setAreaName("巴哈马");
//        countryAreaInfos.add(countryAreaInfo13);
//
//        CountryAreaInfo countryAreaInfo14 = new CountryAreaInfo();
//        countryAreaInfo14.setArea(973);
//        countryAreaInfo14.setAreaName("巴林");
//        countryAreaInfos.add(countryAreaInfo14);
//        CountryAreaInfo countryAreaInfo15 = new CountryAreaInfo();
//        countryAreaInfo15.setArea(880);
//        countryAreaInfo15.setAreaName("孟加拉国");
//        countryAreaInfos.add(countryAreaInfo15);
//        CountryAreaInfo countryAreaInfo16 = new CountryAreaInfo();
//        countryAreaInfo16.setArea(1246);
//        countryAreaInfo16.setAreaName("巴巴多斯");
//        countryAreaInfos.add(countryAreaInfo16);
//        CountryAreaInfo countryAreaInfo17 = new CountryAreaInfo();
//        countryAreaInfo17.setArea(375);
//        countryAreaInfo17.setAreaName("白俄罗斯");
//        countryAreaInfos.add(countryAreaInfo17);
//        CountryAreaInfo countryAreaInfo18 = new CountryAreaInfo();
//        countryAreaInfo18.setArea(32);
//        countryAreaInfo18.setAreaName("比利时");
//        countryAreaInfos.add(countryAreaInfo18);
//        CountryAreaInfo countryAreaInfo19 = new CountryAreaInfo();
//        countryAreaInfo19.setArea(501);
//        countryAreaInfo19.setAreaName("伯利兹");
//        countryAreaInfos.add(countryAreaInfo19);
//
//
//        CountryAreaInfo countryAreaInfo20 = new CountryAreaInfo();
//        countryAreaInfo20.setArea(229);
//        countryAreaInfo20.setAreaName("贝宁");
//        countryAreaInfos.add(countryAreaInfo20);
//        CountryAreaInfo countryAreaInfo21 = new CountryAreaInfo();
//        countryAreaInfo21.setArea(1441);
//        countryAreaInfo21.setAreaName("百慕大群岛");
//        countryAreaInfos.add(countryAreaInfo21);
//        CountryAreaInfo countryAreaInfo22 = new CountryAreaInfo();
//        countryAreaInfo22.setArea(591);
//        countryAreaInfo22.setAreaName("玻利维亚");
//        countryAreaInfos.add(countryAreaInfo22);
//        CountryAreaInfo countryAreaInfo23 = new CountryAreaInfo();
//        countryAreaInfo23.setArea(267);
//        countryAreaInfo23.setAreaName("博茨瓦纳");
//        countryAreaInfos.add(countryAreaInfo23);
//        CountryAreaInfo countryAreaInfo24 = new CountryAreaInfo();
//        countryAreaInfo24.setArea(55);
//        countryAreaInfo24.setAreaName("巴西");
//        countryAreaInfos.add(countryAreaInfo24);
//        CountryAreaInfo countryAreaInfo25 = new CountryAreaInfo();
//        countryAreaInfo25.setArea(673);
//        countryAreaInfo25.setAreaName("文莱");
//        countryAreaInfos.add(countryAreaInfo25);
//
//
//        CountryAreaInfo countryAreaInfo26 = new CountryAreaInfo();
//        countryAreaInfo26.setArea(359);
//        countryAreaInfo26.setAreaName("保加利亚");
//        countryAreaInfos.add(countryAreaInfo26);
//        CountryAreaInfo countryAreaInfo27 = new CountryAreaInfo();
//        countryAreaInfo27.setArea(226);
//        countryAreaInfo27.setAreaName("布基纳法索");
//        countryAreaInfos.add(countryAreaInfo27);
//        CountryAreaInfo countryAreaInfo28 = new CountryAreaInfo();
//        countryAreaInfo28.setArea(95);
//        countryAreaInfo28.setAreaName("缅甸");
//        countryAreaInfos.add(countryAreaInfo28);
//        CountryAreaInfo countryAreaInfo29 = new CountryAreaInfo();
//        countryAreaInfo29.setArea(257);
//        countryAreaInfo29.setAreaName("布隆迪");
//        countryAreaInfos.add(countryAreaInfo29);
//        CountryAreaInfo countryAreaInfo30 = new CountryAreaInfo();
//        countryAreaInfo30.setArea(237);
//        countryAreaInfo30.setAreaName("喀麦隆");
//        countryAreaInfos.add(countryAreaInfo30);
//
//
//        CountryAreaInfo countryAreaInfo31 = new CountryAreaInfo();
//        countryAreaInfo31.setArea(1);
//        countryAreaInfo31.setAreaName("加拿大");
//        countryAreaInfos.add(countryAreaInfo31);
//        CountryAreaInfo countryAreaInfo32 = new CountryAreaInfo();
//        countryAreaInfo32.setArea(1345);
//        countryAreaInfo32.setAreaName("开曼群岛");
//        countryAreaInfos.add(countryAreaInfo32);
//        CountryAreaInfo countryAreaInfo33 = new CountryAreaInfo();
//        countryAreaInfo33.setArea(236);
//        countryAreaInfo33.setAreaName("中非共和国");
//        countryAreaInfos.add(countryAreaInfo33);
//        CountryAreaInfo countryAreaInfo34 = new CountryAreaInfo();
//        countryAreaInfo34.setArea(235);
//        countryAreaInfo34.setAreaName("乍得");
//        countryAreaInfos.add(countryAreaInfo34);
//        CountryAreaInfo countryAreaInfo35 = new CountryAreaInfo();
//        countryAreaInfo35.setArea(56);
//        countryAreaInfo35.setAreaName("智利");
//        countryAreaInfos.add(countryAreaInfo35);
//        CountryAreaInfo countryAreaInfo36 = new CountryAreaInfo();
//        countryAreaInfo36.setArea(86);
//        countryAreaInfo36.setAreaName("中国");
//        countryAreaInfos.add(countryAreaInfo36);
//        CountryAreaInfo countryAreaInfo37 = new CountryAreaInfo();
//        countryAreaInfo37.setArea(57);
//        countryAreaInfo37.setAreaName("哥伦比亚");
//        countryAreaInfos.add(countryAreaInfo37);
//        CountryAreaInfo countryAreaInfo38 = new CountryAreaInfo();
//        countryAreaInfo38.setArea(242);
//        countryAreaInfo38.setAreaName("刚果");
//        countryAreaInfos.add(countryAreaInfo38);
//        CountryAreaInfo countryAreaInfo39 = new CountryAreaInfo();
//        countryAreaInfo39.setArea(682);
//        countryAreaInfo39.setAreaName("库克群岛");
//        countryAreaInfos.add(countryAreaInfo39);
//        CountryAreaInfo countryAreaInfo40 = new CountryAreaInfo();
//        countryAreaInfo40.setArea(506);
//        countryAreaInfo40.setAreaName("哥斯达黎加");
//        countryAreaInfos.add(countryAreaInfo40);
//        CountryAreaInfo countryAreaInfo41 = new CountryAreaInfo();
//        countryAreaInfo41.setArea(53);
//        countryAreaInfo41.setAreaName("古巴");
//        countryAreaInfos.add(countryAreaInfo41);
//        CountryAreaInfo countryAreaInfo42 = new CountryAreaInfo();
//        countryAreaInfo42.setArea(357);
//        countryAreaInfo42.setAreaName("塞浦路斯");
//        countryAreaInfos.add(countryAreaInfo42);
//        CountryAreaInfo countryAreaInfo43 = new CountryAreaInfo();
//        countryAreaInfo43.setArea(420);
//        countryAreaInfo43.setAreaName("捷克");
//        countryAreaInfos.add(countryAreaInfo43);
//
//        CountryAreaInfo countryAreaInfo44 = new CountryAreaInfo();
//        countryAreaInfo44.setArea(45);
//        countryAreaInfo44.setAreaName("丹麦");
//        countryAreaInfos.add(countryAreaInfo44);
//
//        CountryAreaInfo countryAreaInfo45 = new CountryAreaInfo();
//        countryAreaInfo45.setArea(253);
//        countryAreaInfo45.setAreaName("吉布提");
//        countryAreaInfos.add(countryAreaInfo45);
//
//        CountryAreaInfo countryAreaInfo46 = new CountryAreaInfo();
//        countryAreaInfo46.setArea(1890);
//        countryAreaInfo46.setAreaName("多米尼加共和国");
//        countryAreaInfos.add(countryAreaInfo46);
//
//        CountryAreaInfo countryAreaInfo47 = new CountryAreaInfo();
//        countryAreaInfo47.setArea(593);
//        countryAreaInfo47.setAreaName("厄瓜多尔");
//        countryAreaInfos.add(countryAreaInfo47);
//
//        CountryAreaInfo countryAreaInfo48 = new CountryAreaInfo();
//        countryAreaInfo48.setArea(20);
//        countryAreaInfo48.setAreaName("埃及");
//        countryAreaInfos.add(countryAreaInfo48);
//
//        CountryAreaInfo countryAreaInfo49 = new CountryAreaInfo();
//        countryAreaInfo49.setArea(503);
//        countryAreaInfo49.setAreaName("萨尔瓦多");
//        countryAreaInfos.add(countryAreaInfo49);
//
//        CountryAreaInfo countryAreaInfo50 = new CountryAreaInfo();
//        countryAreaInfo50.setArea(372);
//        countryAreaInfo50.setAreaName("爱沙尼亚");
//        countryAreaInfos.add(countryAreaInfo50);
//
//        CountryAreaInfo countryAreaInfo51 = new CountryAreaInfo();
//        countryAreaInfo51.setArea(251);
//        countryAreaInfo51.setAreaName("埃塞俄比亚");
//        countryAreaInfos.add(countryAreaInfo51);
//
//        CountryAreaInfo countryAreaInfo52 = new CountryAreaInfo();
//        countryAreaInfo52.setArea(679);
//        countryAreaInfo52.setAreaName("斐济");
//        countryAreaInfos.add(countryAreaInfo52);
//
//        CountryAreaInfo countryAreaInfo53 = new CountryAreaInfo();
//        countryAreaInfo53.setArea(358);
//        countryAreaInfo53.setAreaName("芬兰");
//        countryAreaInfos.add(countryAreaInfo53);
//
//        CountryAreaInfo countryAreaInfo54 = new CountryAreaInfo();
//        countryAreaInfo54.setArea(33);
//        countryAreaInfo54.setAreaName("法国");
//        countryAreaInfos.add(countryAreaInfo54);
//
//        CountryAreaInfo countryAreaInfo55 = new CountryAreaInfo();
//        countryAreaInfo55.setArea(594);
//        countryAreaInfo55.setAreaName("法属圭亚那");
//        countryAreaInfos.add(countryAreaInfo55);
//
//        CountryAreaInfo countryAreaInfo56 = new CountryAreaInfo();
//        countryAreaInfo56.setArea(241);
//        countryAreaInfo56.setAreaName("加蓬");
//        countryAreaInfos.add(countryAreaInfo56);
//
//        CountryAreaInfo countryAreaInfo57 = new CountryAreaInfo();
//        countryAreaInfo57.setArea(220);
//        countryAreaInfo57.setAreaName("冈比亚");
//        countryAreaInfos.add(countryAreaInfo57);
//
//        CountryAreaInfo countryAreaInfo58 = new CountryAreaInfo();
//        countryAreaInfo58.setArea(995);
//        countryAreaInfo58.setAreaName("格鲁吉亚");
//        countryAreaInfos.add(countryAreaInfo58);
//
//        CountryAreaInfo countryAreaInfo59 = new CountryAreaInfo();
//        countryAreaInfo59.setArea(49);
//        countryAreaInfo59.setAreaName("德国");
//        countryAreaInfos.add(countryAreaInfo59);
//
//        CountryAreaInfo countryAreaInfo60 = new CountryAreaInfo();
//        countryAreaInfo60.setArea(233);
//        countryAreaInfo60.setAreaName("加纳");
//        countryAreaInfos.add(countryAreaInfo60);
//
//        CountryAreaInfo countryAreaInfo61 = new CountryAreaInfo();
//        countryAreaInfo61.setArea(350);
//        countryAreaInfo61.setAreaName("直布罗陀");
//        countryAreaInfos.add(countryAreaInfo61);
//
//        CountryAreaInfo countryAreaInfo62 = new CountryAreaInfo();
//        countryAreaInfo62.setArea(30);
//        countryAreaInfo62.setAreaName("希腊");
//        countryAreaInfos.add(countryAreaInfo62);
//
//        CountryAreaInfo countryAreaInfo63 = new CountryAreaInfo();
//        countryAreaInfo63.setArea(1809);
//        countryAreaInfo63.setAreaName("格林纳达");
//        countryAreaInfos.add(countryAreaInfo63);
//
//        CountryAreaInfo countryAreaInfo64 = new CountryAreaInfo();
//        countryAreaInfo64.setArea(1671);
//        countryAreaInfo64.setAreaName("关岛");
//        countryAreaInfos.add(countryAreaInfo64);
//
//        CountryAreaInfo countryAreaInfo65 = new CountryAreaInfo();
//        countryAreaInfo65.setArea(502);
//        countryAreaInfo65.setAreaName("危地马拉");
//        countryAreaInfos.add(countryAreaInfo65);
//
//        CountryAreaInfo countryAreaInfo66 = new CountryAreaInfo();
//        countryAreaInfo66.setArea(224);
//        countryAreaInfo66.setAreaName("几内亚");
//        countryAreaInfos.add(countryAreaInfo66);
//
//        CountryAreaInfo countryAreaInfo67 = new CountryAreaInfo();
//        countryAreaInfo67.setArea(592);
//        countryAreaInfo67.setAreaName("圭亚那");
//        countryAreaInfos.add(countryAreaInfo67);
//
//        CountryAreaInfo countryAreaInfo68 = new CountryAreaInfo();
//        countryAreaInfo68.setArea(509);
//        countryAreaInfo68.setAreaName("海地");
//        countryAreaInfos.add(countryAreaInfo68);
//
//        CountryAreaInfo countryAreaInfo69 = new CountryAreaInfo();
//        countryAreaInfo69.setArea(504);
//        countryAreaInfo69.setAreaName("洪都拉斯");
//        countryAreaInfos.add(countryAreaInfo69);
//        CountryAreaInfo countryAreaInfo70 = new CountryAreaInfo();
//        countryAreaInfo70.setArea(852);
//        countryAreaInfo70.setAreaName("香港");
//        countryAreaInfos.add(countryAreaInfo70);
//
//        CountryAreaInfo countryAreaInfo71 = new CountryAreaInfo();
//        countryAreaInfo71.setArea(36);
//        countryAreaInfo71.setAreaName("匈牙利");
//        countryAreaInfos.add(countryAreaInfo71);
//        CountryAreaInfo countryAreaInfo72 = new CountryAreaInfo();
//        countryAreaInfo72.setArea(354);
//        countryAreaInfo72.setAreaName("冰岛");
//        countryAreaInfos.add(countryAreaInfo72);
//
//        CountryAreaInfo countryAreaInfo73 = new CountryAreaInfo();
//        countryAreaInfo73.setArea(91);
//        countryAreaInfo73.setAreaName("印度");
//        countryAreaInfos.add(countryAreaInfo73);
//
//        CountryAreaInfo countryAreaInfo74 = new CountryAreaInfo();
//        countryAreaInfo74.setArea(62);
//        countryAreaInfo74.setAreaName("印度尼西亚");
//        countryAreaInfos.add(countryAreaInfo74);
//
//        CountryAreaInfo countryAreaInfo75 = new CountryAreaInfo();
//        countryAreaInfo75.setArea(98);
//        countryAreaInfo75.setAreaName("伊朗");
//        countryAreaInfos.add(countryAreaInfo75);
//
//        CountryAreaInfo countryAreaInfo76 = new CountryAreaInfo();
//        countryAreaInfo76.setArea(964);
//        countryAreaInfo76.setAreaName("伊拉克");
//        countryAreaInfos.add(countryAreaInfo76);
//
//        CountryAreaInfo countryAreaInfo77 = new CountryAreaInfo();
//        countryAreaInfo77.setArea(353);
//        countryAreaInfo77.setAreaName("爱尔兰");
//        countryAreaInfos.add(countryAreaInfo77);
//
//        CountryAreaInfo countryAreaInfo78 = new CountryAreaInfo();
//        countryAreaInfo78.setArea(972);
//        countryAreaInfo78.setAreaName("以色列");
//        countryAreaInfos.add(countryAreaInfo78);
//
//        CountryAreaInfo countryAreaInfo79 = new CountryAreaInfo();
//        countryAreaInfo79.setArea(39);
//        countryAreaInfo79.setAreaName("意大利");
//        countryAreaInfos.add(countryAreaInfo79);
//
//        CountryAreaInfo countryAreaInfo80 = new CountryAreaInfo();
//        countryAreaInfo80.setArea(225);
//        countryAreaInfo80.setAreaName("科特迪瓦");
//        countryAreaInfos.add(countryAreaInfo80);
//
//        CountryAreaInfo countryAreaInfo81 = new CountryAreaInfo();
//        countryAreaInfo81.setArea(1876);
//        countryAreaInfo81.setAreaName("牙买加");
//        countryAreaInfos.add(countryAreaInfo81);
//
//        CountryAreaInfo countryAreaInfo82 = new CountryAreaInfo();
//        countryAreaInfo82.setArea(81);
//        countryAreaInfo82.setAreaName("日本");
//        countryAreaInfos.add(countryAreaInfo82);
//
//        CountryAreaInfo countryAreaInfo83 = new CountryAreaInfo();
//        countryAreaInfo83.setArea(962);
//        countryAreaInfo83.setAreaName("约旦");
//        countryAreaInfos.add(countryAreaInfo83);
//
//        CountryAreaInfo countryAreaInfo84 = new CountryAreaInfo();
//        countryAreaInfo84.setArea(855);
//        countryAreaInfo84.setAreaName("柬埔寨");
//        countryAreaInfos.add(countryAreaInfo84);
//
//        CountryAreaInfo countryAreaInfo85 = new CountryAreaInfo();
//        countryAreaInfo85.setArea(327);
//        countryAreaInfo85.setAreaName("哈萨克斯坦");
//        countryAreaInfos.add(countryAreaInfo85);
//
//        CountryAreaInfo countryAreaInfo86 = new CountryAreaInfo();
//        countryAreaInfo86.setArea(254);
//        countryAreaInfo86.setAreaName("肯尼亚");
//        countryAreaInfos.add(countryAreaInfo86);
//
//        CountryAreaInfo countryAreaInfo87 = new CountryAreaInfo();
//        countryAreaInfo87.setArea(82);
//        countryAreaInfo87.setAreaName("韩国");
//        countryAreaInfos.add(countryAreaInfo87);
//
//        CountryAreaInfo countryAreaInfo88 = new CountryAreaInfo();
//        countryAreaInfo88.setArea(965);
//        countryAreaInfo88.setAreaName("科威特");
//        countryAreaInfos.add(countryAreaInfo88);
//
//        CountryAreaInfo countryAreaInfo89 = new CountryAreaInfo();
//        countryAreaInfo89.setArea(331);
//        countryAreaInfo89.setAreaName("吉尔吉斯坦");
//        countryAreaInfos.add(countryAreaInfo89);
//
//        CountryAreaInfo countryAreaInfo90 = new CountryAreaInfo();
//        countryAreaInfo90.setArea(856);
//        countryAreaInfo90.setAreaName("老挝");
//        countryAreaInfos.add(countryAreaInfo90);
//
//        CountryAreaInfo countryAreaInfo91 = new CountryAreaInfo();
//        countryAreaInfo91.setArea(371);
//        countryAreaInfo91.setAreaName("拉脱维亚");
//        countryAreaInfos.add(countryAreaInfo91);
//
//        CountryAreaInfo countryAreaInfo92 = new CountryAreaInfo();
//        countryAreaInfo92.setArea(961);
//        countryAreaInfo92.setAreaName("黎巴嫩");
//        countryAreaInfos.add(countryAreaInfo92);
//
//        CountryAreaInfo countryAreaInfo93 = new CountryAreaInfo();
//        countryAreaInfo93.setArea(266);
//        countryAreaInfo93.setAreaName("莱索托");
//        countryAreaInfos.add(countryAreaInfo93);
//
//        CountryAreaInfo countryAreaInfo94 = new CountryAreaInfo();
//        countryAreaInfo94.setArea(231);
//        countryAreaInfo94.setAreaName("利比里亚");
//        countryAreaInfos.add(countryAreaInfo94);
//
//        CountryAreaInfo countryAreaInfo95 = new CountryAreaInfo();
//        countryAreaInfo95.setArea(218);
//        countryAreaInfo95.setAreaName("利比亚");
//        countryAreaInfos.add(countryAreaInfo95);
//
//        CountryAreaInfo countryAreaInfo96 = new CountryAreaInfo();
//        countryAreaInfo96.setArea(423);
//        countryAreaInfo96.setAreaName("列支敦士登");
//        countryAreaInfos.add(countryAreaInfo96);
//
//        CountryAreaInfo countryAreaInfo97 = new CountryAreaInfo();
//        countryAreaInfo97.setArea(370);
//        countryAreaInfo97.setAreaName("立陶宛");
//        countryAreaInfos.add(countryAreaInfo97);
//
//        CountryAreaInfo countryAreaInfo98 = new CountryAreaInfo();
//        countryAreaInfo98.setArea(352);
//        countryAreaInfo98.setAreaName("卢森堡");
//        countryAreaInfos.add(countryAreaInfo98);
//
//        CountryAreaInfo countryAreaInfo99 = new CountryAreaInfo();
//        countryAreaInfo99.setArea(853);
//        countryAreaInfo99.setAreaName("澳门");
//        countryAreaInfos.add(countryAreaInfo99);
//
//        CountryAreaInfo countryAreaInfo100 = new CountryAreaInfo();
//        countryAreaInfo100.setArea(261);
//        countryAreaInfo100.setAreaName("马达加斯加");
//        countryAreaInfos.add(countryAreaInfo100);
//
//        CountryAreaInfo countryAreaInfo101 = new CountryAreaInfo();
//        countryAreaInfo101.setArea(265);
//        countryAreaInfo101.setAreaName("马拉维");
//        countryAreaInfos.add(countryAreaInfo101);
//
//        CountryAreaInfo countryAreaInfo102 = new CountryAreaInfo();
//        countryAreaInfo102.setArea(60);
//        countryAreaInfo102.setAreaName("马来西亚");
//        countryAreaInfos.add(countryAreaInfo102);
//
//        CountryAreaInfo countryAreaInfo103 = new CountryAreaInfo();
//        countryAreaInfo103.setArea(960);
//        countryAreaInfo103.setAreaName("马尔代夫");
//        countryAreaInfos.add(countryAreaInfo103);
//
//        CountryAreaInfo countryAreaInfo104 = new CountryAreaInfo();
//        countryAreaInfo104.setArea(223);
//        countryAreaInfo104.setAreaName("马里");
//        countryAreaInfos.add(countryAreaInfo104);
//
//        CountryAreaInfo countryAreaInfo105 = new CountryAreaInfo();
//        countryAreaInfo105.setArea(356);
//        countryAreaInfo105.setAreaName("马耳他");
//        countryAreaInfos.add(countryAreaInfo105);
//
//        CountryAreaInfo countryAreaInfo106 = new CountryAreaInfo();
//        countryAreaInfo106.setArea(1670);
//        countryAreaInfo106.setAreaName("马里亚那群岛");
//        countryAreaInfos.add(countryAreaInfo106);
//
//        CountryAreaInfo countryAreaInfo107 = new CountryAreaInfo();
//        countryAreaInfo107.setArea(596);
//        countryAreaInfo107.setAreaName("马提尼克");
//        countryAreaInfos.add(countryAreaInfo107);
//
//        CountryAreaInfo countryAreaInfo108 = new CountryAreaInfo();
//        countryAreaInfo108.setArea(230);
//        countryAreaInfo108.setAreaName("毛里求斯");
//        countryAreaInfos.add(countryAreaInfo108);
//        CountryAreaInfo countryAreaInfo109 = new CountryAreaInfo();
//        countryAreaInfo109.setArea(52);
//        countryAreaInfo109.setAreaName("墨西哥");
//        countryAreaInfos.add(countryAreaInfo109);
//        CountryAreaInfo countryAreaInfo110 = new CountryAreaInfo();
//        countryAreaInfo110.setArea(373);
//        countryAreaInfo110.setAreaName("摩尔多瓦");
//        countryAreaInfos.add(countryAreaInfo110);
//        CountryAreaInfo countryAreaInfo111 = new CountryAreaInfo();
//        countryAreaInfo111.setArea(377);
//        countryAreaInfo111.setAreaName("摩纳哥");
//        countryAreaInfos.add(countryAreaInfo111);
//        CountryAreaInfo countryAreaInfo112 = new CountryAreaInfo();
//        countryAreaInfo112.setArea(976);
//        countryAreaInfo112.setAreaName("蒙古");
//        countryAreaInfos.add(countryAreaInfo112);
//        CountryAreaInfo countryAreaInfo113 = new CountryAreaInfo();
//        countryAreaInfo113.setArea(1664);
//        countryAreaInfo113.setAreaName("蒙特塞拉特岛");
//        countryAreaInfos.add(countryAreaInfo113);
//        CountryAreaInfo countryAreaInfo114 = new CountryAreaInfo();
//        countryAreaInfo114.setArea(212);
//        countryAreaInfo114.setAreaName("摩洛哥");
//        countryAreaInfos.add(countryAreaInfo114);
//        CountryAreaInfo countryAreaInfo115 = new CountryAreaInfo();
//        countryAreaInfo115.setArea(258);
//        countryAreaInfo115.setAreaName("莫桑比克");
//        countryAreaInfos.add(countryAreaInfo115);
//        CountryAreaInfo countryAreaInfo116 = new CountryAreaInfo();
//        countryAreaInfo116.setArea(264);
//        countryAreaInfo116.setAreaName("纳米比亚");
//        countryAreaInfos.add(countryAreaInfo116);
//        CountryAreaInfo countryAreaInfo117 = new CountryAreaInfo();
//        countryAreaInfo117.setArea(674);
//        countryAreaInfo117.setAreaName("瑙鲁");
//        countryAreaInfos.add(countryAreaInfo117);
//        CountryAreaInfo countryAreaInfo118 = new CountryAreaInfo();
//        countryAreaInfo118.setArea(977);
//        countryAreaInfo118.setAreaName("尼泊尔");
//        countryAreaInfos.add(countryAreaInfo118);
//        CountryAreaInfo countryAreaInfo119 = new CountryAreaInfo();
//        countryAreaInfo119.setArea(599);
//        countryAreaInfo119.setAreaName("荷属安的列斯");
//        countryAreaInfos.add(countryAreaInfo119);
//        CountryAreaInfo countryAreaInfo120 = new CountryAreaInfo();
//        countryAreaInfo120.setArea(31);
//        countryAreaInfo120.setAreaName("荷兰");
//        countryAreaInfos.add(countryAreaInfo120);
//        CountryAreaInfo countryAreaInfo121 = new CountryAreaInfo();
//        countryAreaInfo121.setArea(64);
//        countryAreaInfo121.setAreaName("新西兰");
//        countryAreaInfos.add(countryAreaInfo121);
//        CountryAreaInfo countryAreaInfo122 = new CountryAreaInfo();
//        countryAreaInfo122.setArea(505);
//        countryAreaInfo122.setAreaName("尼加拉瓜");
//        countryAreaInfos.add(countryAreaInfo122);
//        CountryAreaInfo countryAreaInfo123 = new CountryAreaInfo();
//        countryAreaInfo123.setArea(227);
//        countryAreaInfo123.setAreaName("尼日尔");
//        countryAreaInfos.add(countryAreaInfo123);
//        CountryAreaInfo countryAreaInfo124 = new CountryAreaInfo();
//        countryAreaInfo124.setArea(234);
//        countryAreaInfo124.setAreaName("尼日利亚");
//        countryAreaInfos.add(countryAreaInfo124);
//        CountryAreaInfo countryAreaInfo125 = new CountryAreaInfo();
//        countryAreaInfo125.setArea(850);
//        countryAreaInfo125.setAreaName("朝鲜");
//        countryAreaInfos.add(countryAreaInfo125);
//        CountryAreaInfo countryAreaInfo126 = new CountryAreaInfo();
//        countryAreaInfo126.setArea(47);
//        countryAreaInfo126.setAreaName("挪威");
//        countryAreaInfos.add(countryAreaInfo126);
//        CountryAreaInfo countryAreaInfo127 = new CountryAreaInfo();
//        countryAreaInfo127.setArea(968);
//        countryAreaInfo127.setAreaName("阿曼");
//        countryAreaInfos.add(countryAreaInfo127);
//        CountryAreaInfo countryAreaInfo128 = new CountryAreaInfo();
//        countryAreaInfo128.setArea(92);
//        countryAreaInfo128.setAreaName("巴基斯坦");
//        countryAreaInfos.add(countryAreaInfo128);
//        CountryAreaInfo countryAreaInfo129 = new CountryAreaInfo();
//        countryAreaInfo129.setArea(507);
//        countryAreaInfo129.setAreaName("巴拿马");
//        countryAreaInfos.add(countryAreaInfo129);
//        CountryAreaInfo countryAreaInfo130 = new CountryAreaInfo();
//        countryAreaInfo130.setArea(675);
//        countryAreaInfo130.setAreaName("巴布亚新几内亚");
//        countryAreaInfos.add(countryAreaInfo130);
//        CountryAreaInfo countryAreaInfo131 = new CountryAreaInfo();
//        countryAreaInfo131.setArea(595);
//        countryAreaInfo131.setAreaName("巴拉圭");
//        countryAreaInfos.add(countryAreaInfo131);
//        CountryAreaInfo countryAreaInfo132 = new CountryAreaInfo();
//        countryAreaInfo132.setArea(51);
//        countryAreaInfo132.setAreaName("秘鲁");
//        countryAreaInfos.add(countryAreaInfo132);
//        CountryAreaInfo countryAreaInfo133 = new CountryAreaInfo();
//        countryAreaInfo133.setArea(63);
//        countryAreaInfo133.setAreaName("菲律宾");
//        countryAreaInfos.add(countryAreaInfo133);
//        CountryAreaInfo countryAreaInfo134 = new CountryAreaInfo();
//        countryAreaInfo134.setArea(48);
//        countryAreaInfo134.setAreaName("波兰");
//        countryAreaInfos.add(countryAreaInfo134);
//        CountryAreaInfo countryAreaInfo135 = new CountryAreaInfo();
//        countryAreaInfo135.setArea(689);
//        countryAreaInfo135.setAreaName("法属玻利尼西亚");
//        countryAreaInfos.add(countryAreaInfo135);
//        CountryAreaInfo countryAreaInfo136 = new CountryAreaInfo();
//        countryAreaInfo136.setArea(351);
//        countryAreaInfo136.setAreaName("葡萄牙");
//        countryAreaInfos.add(countryAreaInfo136);
//        CountryAreaInfo countryAreaInfo137 = new CountryAreaInfo();
//        countryAreaInfo137.setArea(1787);
//        countryAreaInfo137.setAreaName("波多黎各");
//        countryAreaInfos.add(countryAreaInfo137);
//        CountryAreaInfo countryAreaInfo138 = new CountryAreaInfo();
//        countryAreaInfo138.setArea(974);
//        countryAreaInfo138.setAreaName("卡塔尔");
//        countryAreaInfos.add(countryAreaInfo138);
//        CountryAreaInfo countryAreaInfo139 = new CountryAreaInfo();
//        countryAreaInfo139.setArea(262);
//        countryAreaInfo139.setAreaName("留尼旺");
//        countryAreaInfos.add(countryAreaInfo139);
//        CountryAreaInfo countryAreaInfo140 = new CountryAreaInfo();
//        countryAreaInfo140.setArea(40);
//        countryAreaInfo140.setAreaName("罗马尼亚");
//        countryAreaInfos.add(countryAreaInfo140);
//        CountryAreaInfo countryAreaInfo141 = new CountryAreaInfo();
//        countryAreaInfo141.setArea(7);
//        countryAreaInfo141.setAreaName("俄罗斯");
//        countryAreaInfos.add(countryAreaInfo141);
//        CountryAreaInfo countryAreaInfo142 = new CountryAreaInfo();
//        countryAreaInfo142.setArea(1758);
//        countryAreaInfo142.setAreaName("圣卢西亚");
//        countryAreaInfos.add(countryAreaInfo142);
//        CountryAreaInfo countryAreaInfo143 = new CountryAreaInfo();
//        countryAreaInfo143.setArea(1784);
//        countryAreaInfo143.setAreaName("圣文森特岛");
//        countryAreaInfos.add(countryAreaInfo143);
//        CountryAreaInfo countryAreaInfo144 = new CountryAreaInfo();
//        countryAreaInfo144.setArea(684);
//        countryAreaInfo144.setAreaName("东萨摩亚(美)");
//        countryAreaInfos.add(countryAreaInfo144);
//        CountryAreaInfo countryAreaInfo145 = new CountryAreaInfo();
//        countryAreaInfo145.setArea(685);
//        countryAreaInfo145.setAreaName("西萨摩亚");
//        countryAreaInfos.add(countryAreaInfo145);
//        CountryAreaInfo countryAreaInfo146 = new CountryAreaInfo();
//        countryAreaInfo146.setArea(378);
//        countryAreaInfo146.setAreaName("圣马力诺");
//        countryAreaInfos.add(countryAreaInfo146);
//        CountryAreaInfo countryAreaInfo147 = new CountryAreaInfo();
//        countryAreaInfo147.setArea(239);
//        countryAreaInfo147.setAreaName("圣多美和普林西比");
//        countryAreaInfos.add(countryAreaInfo147);
//        CountryAreaInfo countryAreaInfo148 = new CountryAreaInfo();
//        countryAreaInfo148.setArea(966);
//        countryAreaInfo148.setAreaName("沙特阿拉伯");
//        countryAreaInfos.add(countryAreaInfo148);
//        CountryAreaInfo countryAreaInfo149 = new CountryAreaInfo();
//        countryAreaInfo149.setArea(221);
//        countryAreaInfo149.setAreaName("塞内加尔");
//        countryAreaInfos.add(countryAreaInfo149);
//        CountryAreaInfo countryAreaInfo150 = new CountryAreaInfo();
//        countryAreaInfo150.setArea(248);
//        countryAreaInfo150.setAreaName("塞舌尔");
//        countryAreaInfos.add(countryAreaInfo150);
//        CountryAreaInfo countryAreaInfo151 = new CountryAreaInfo();
//        countryAreaInfo151.setArea(232);
//        countryAreaInfo151.setAreaName("塞拉利昂");
//        countryAreaInfos.add(countryAreaInfo151);
//        CountryAreaInfo countryAreaInfo152 = new CountryAreaInfo();
//        countryAreaInfo152.setArea(65);
//        countryAreaInfo152.setAreaName("新加坡");
//        countryAreaInfos.add(countryAreaInfo152);
//        CountryAreaInfo countryAreaInfo153 = new CountryAreaInfo();
//        countryAreaInfo153.setArea(421);
//        countryAreaInfo153.setAreaName("斯洛伐克");
//        countryAreaInfos.add(countryAreaInfo153);
//        CountryAreaInfo countryAreaInfo154 = new CountryAreaInfo();
//        countryAreaInfo154.setArea(386);
//        countryAreaInfo154.setAreaName("斯洛文尼亚");
//        countryAreaInfos.add(countryAreaInfo154);
//        CountryAreaInfo countryAreaInfo155 = new CountryAreaInfo();
//        countryAreaInfo155.setArea(677);
//        countryAreaInfo155.setAreaName("所罗门群岛");
//        countryAreaInfos.add(countryAreaInfo155);
//        CountryAreaInfo countryAreaInfo156 = new CountryAreaInfo();
//        countryAreaInfo156.setArea(252);
//        countryAreaInfo156.setAreaName("索马里");
//        countryAreaInfos.add(countryAreaInfo156);
//        CountryAreaInfo countryAreaInfo157 = new CountryAreaInfo();
//        countryAreaInfo157.setArea(27);
//        countryAreaInfo157.setAreaName("南非");
//        countryAreaInfos.add(countryAreaInfo157);
//        CountryAreaInfo countryAreaInfo158 = new CountryAreaInfo();
//        countryAreaInfo158.setArea(34);
//        countryAreaInfo158.setAreaName("西班牙");
//        countryAreaInfos.add(countryAreaInfo158);
//        CountryAreaInfo countryAreaInfo159 = new CountryAreaInfo();
//        countryAreaInfo159.setArea(94);
//        countryAreaInfo159.setAreaName("斯里兰卡");
//        countryAreaInfos.add(countryAreaInfo159);
//        CountryAreaInfo countryAreaInfo160 = new CountryAreaInfo();
//        countryAreaInfo160.setArea(1758);
//        countryAreaInfo160.setAreaName("圣卢西亚");
//        countryAreaInfos.add(countryAreaInfo160);
//        CountryAreaInfo countryAreaInfo161 = new CountryAreaInfo();
//        countryAreaInfo161.setArea(1784);
//        countryAreaInfo161.setAreaName("圣文森特");
//        countryAreaInfos.add(countryAreaInfo161);
//        CountryAreaInfo countryAreaInfo162 = new CountryAreaInfo();
//        countryAreaInfo162.setArea(249);
//        countryAreaInfo162.setAreaName("苏丹");
//        countryAreaInfos.add(countryAreaInfo162);
//        CountryAreaInfo countryAreaInfo163 = new CountryAreaInfo();
//        countryAreaInfo163.setArea(597);
//        countryAreaInfo163.setAreaName("苏里南");
//        countryAreaInfos.add(countryAreaInfo163);
//        CountryAreaInfo countryAreaInfo164 = new CountryAreaInfo();
//        countryAreaInfo164.setArea(268);
//        countryAreaInfo164.setAreaName("斯威士兰");
//        countryAreaInfos.add(countryAreaInfo164);
//        CountryAreaInfo countryAreaInfo165 = new CountryAreaInfo();
//        countryAreaInfo165.setArea(46);
//        countryAreaInfo165.setAreaName("瑞典");
//        countryAreaInfos.add(countryAreaInfo165);
//        CountryAreaInfo countryAreaInfo166 = new CountryAreaInfo();
//        countryAreaInfo166.setArea(41);
//        countryAreaInfo166.setAreaName("瑞士");
//        countryAreaInfos.add(countryAreaInfo166);
//        CountryAreaInfo countryAreaInfo167 = new CountryAreaInfo();
//        countryAreaInfo167.setArea(963);
//        countryAreaInfo167.setAreaName("叙利亚");
//        countryAreaInfos.add(countryAreaInfo167);
//        CountryAreaInfo countryAreaInfo168 = new CountryAreaInfo();
//        countryAreaInfo168.setArea(886);
//        countryAreaInfo168.setAreaName("台湾省");
//        countryAreaInfos.add(countryAreaInfo168);
//        CountryAreaInfo countryAreaInfo169 = new CountryAreaInfo();
//        countryAreaInfo169.setArea(992);
//        countryAreaInfo169.setAreaName("塔吉克斯坦");
//        countryAreaInfos.add(countryAreaInfo169);
//        CountryAreaInfo countryAreaInfo170 = new CountryAreaInfo();
//        countryAreaInfo170.setArea(255);
//        countryAreaInfo170.setAreaName("坦桑尼亚");
//        countryAreaInfos.add(countryAreaInfo170);
//        CountryAreaInfo countryAreaInfo171 = new CountryAreaInfo();
//        countryAreaInfo171.setArea(66);
//        countryAreaInfo171.setAreaName("泰国");
//        countryAreaInfos.add(countryAreaInfo171);
//        CountryAreaInfo countryAreaInfo172 = new CountryAreaInfo();
//        countryAreaInfo172.setArea(228);
//        countryAreaInfo172.setAreaName("多哥");
//        countryAreaInfos.add(countryAreaInfo172);
//        CountryAreaInfo countryAreaInfo173 = new CountryAreaInfo();
//        countryAreaInfo173.setArea(676);
//        countryAreaInfo173.setAreaName("汤加");
//        countryAreaInfos.add(countryAreaInfo173);
//        CountryAreaInfo countryAreaInfo174 = new CountryAreaInfo();
//        countryAreaInfo174.setArea(1809);
//        countryAreaInfo174.setAreaName("特立尼达和多巴哥");
//        countryAreaInfos.add(countryAreaInfo174);
//        CountryAreaInfo countryAreaInfo175 = new CountryAreaInfo();
//        countryAreaInfo175.setArea(216);
//        countryAreaInfo175.setAreaName("突尼斯");
//        countryAreaInfos.add(countryAreaInfo175);
//        CountryAreaInfo countryAreaInfo176 = new CountryAreaInfo();
//        countryAreaInfo176.setArea(90);
//        countryAreaInfo176.setAreaName("土耳其");
//        countryAreaInfos.add(countryAreaInfo176);
//        CountryAreaInfo countryAreaInfo177 = new CountryAreaInfo();
//        countryAreaInfo177.setArea(993);
//        countryAreaInfo177.setAreaName("土库曼斯坦");
//        countryAreaInfos.add(countryAreaInfo177);
//        CountryAreaInfo countryAreaInfo178 = new CountryAreaInfo();
//        countryAreaInfo178.setArea(256);
//        countryAreaInfo178.setAreaName("乌干达");
//        countryAreaInfos.add(countryAreaInfo178);
//        CountryAreaInfo countryAreaInfo179 = new CountryAreaInfo();
//        countryAreaInfo179.setArea(380);
//        countryAreaInfo179.setAreaName("乌克兰");
//        countryAreaInfos.add(countryAreaInfo179);
//        CountryAreaInfo countryAreaInfo180 = new CountryAreaInfo();
//        countryAreaInfo180.setArea(971);
//        countryAreaInfo180.setAreaName("阿拉伯联合酋长国");
//        countryAreaInfos.add(countryAreaInfo180);
//        CountryAreaInfo countryAreaInfo181 = new CountryAreaInfo();
//        countryAreaInfo181.setArea(44);
//        countryAreaInfo181.setAreaName("英国");
//        countryAreaInfos.add(countryAreaInfo181);
//        CountryAreaInfo countryAreaInfo182 = new CountryAreaInfo();
//        countryAreaInfo182.setArea(1);
//        countryAreaInfo182.setAreaName("美国");
//        countryAreaInfos.add(countryAreaInfo182);
//        CountryAreaInfo countryAreaInfo183 = new CountryAreaInfo();
//        countryAreaInfo183.setArea(598);
//        countryAreaInfo183.setAreaName("乌拉圭");
//        countryAreaInfos.add(countryAreaInfo183);
//        CountryAreaInfo countryAreaInfo184 = new CountryAreaInfo();
//        countryAreaInfo184.setArea(233);
//        countryAreaInfo184.setAreaName("乌兹别克斯坦");
//        countryAreaInfos.add(countryAreaInfo184);
//        CountryAreaInfo countryAreaInfo185 = new CountryAreaInfo();
//        countryAreaInfo185.setArea(58);
//        countryAreaInfo185.setAreaName("委内瑞拉");
//        countryAreaInfos.add(countryAreaInfo185);
//        CountryAreaInfo countryAreaInfo186 = new CountryAreaInfo();
//        countryAreaInfo186.setArea(84);
//        countryAreaInfo186.setAreaName("越南");
//        countryAreaInfos.add(countryAreaInfo186);
//        CountryAreaInfo countryAreaInfo187 = new CountryAreaInfo();
//        countryAreaInfo187.setArea(967);
//        countryAreaInfo187.setAreaName("也门");
//        countryAreaInfos.add(countryAreaInfo187);
//        CountryAreaInfo countryAreaInfo188 = new CountryAreaInfo();
//        countryAreaInfo188.setArea(381);
//        countryAreaInfo188.setAreaName("南斯拉夫");
//        countryAreaInfos.add(countryAreaInfo188);
//        CountryAreaInfo countryAreaInfo189 = new CountryAreaInfo();
//        countryAreaInfo189.setArea(263);
//        countryAreaInfo189.setAreaName("津巴布韦");
//        countryAreaInfos.add(countryAreaInfo189);
//        CountryAreaInfo countryAreaInfo190 = new CountryAreaInfo();
//        countryAreaInfo190.setArea(243);
//        countryAreaInfo190.setAreaName("扎伊尔");
//        countryAreaInfos.add(countryAreaInfo190);
//        CountryAreaInfo countryAreaInfo191 = new CountryAreaInfo();
//        countryAreaInfo191.setArea(260);
//        countryAreaInfo191.setAreaName("赞比亚");
//        countryAreaInfos.add(countryAreaInfo191);
//        return countryAreaInfos;
//
//    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

//    public static void initShumeng(Context context) {
//        try {
//            if (BuildConfig.IS_SHUMENG) {
//                String idfaString = SystemInfo.getShareSystem().getIdfa();
//
//                if (!TextUtils.isEmpty(idfaString)) {
//                    Main.go(context, NetworkConstant.getChannelId(context),
//                            idfaString);
//                }
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//    }

//    /**
//     * 获取状态栏高度
//     *
//     * @return
//     */
//    public static int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = ShowSelfApp.getInstance().getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = ShowSelfApp.getInstance().getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }

    /**
     * 解决ScrollView嵌套ListView时只显示一行的问题
     */
    public static void setListViewHeightInCardFragment(ListView listView, Context context) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        //最多显示屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        totalHeight += listView.getDividerHeight() * (listAdapter.getCount() - 1);
        if (totalHeight > screenHeight) {
            totalHeight = screenHeight;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static String getStringDate(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // hide nav bar
            decorView.setSystemUiVisibility(uiOptions);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static int getStateHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

//    public static int getWindowWidth() {
//        //屏幕尺寸
//        WindowManager wm = (WindowManager) ShowSelfApp.getInstance().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        return dm.widthPixels;
//    }
//
//    public static int getWindowHeight() {
//        //屏幕尺寸
//        WindowManager wm = (WindowManager) ShowSelfApp.getInstance().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        return dm.heightPixels;
//    }

//    public static int getDefaultPlayerHeight() {
//        return 3 * getWindowWidth() / 4;
//    }

    /**
     * 带圆角的单色GradientDrawable
     */
    public static GradientDrawable getGradientDrawable(int color, int cornerRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(cornerRadius);
        return drawable;
    }

//    /**
//     * 从资源获取int类型数据
//     */
//    public static int getResourceInt(String list, String grade, int i, String name) {
//        if (i < 0) return -1;
//        try {
//            DefaultResourceProvider defaultResourceProvider = ResourceManager.getProvider();
//            Object obj = defaultResourceProvider.getResource("{\"res\":\"" + list + "\", \"cons\":{\"" + grade + "\":\"" + i + "\"},\"property\":\"" + name + "\"}");
//            if (obj != null) {
//                Integer giftUrl = (Integer) obj;
//                return giftUrl;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//
//    }
//
//    /**
//     * 从资源获取String类型数据
//     */
//    public static String getResourceString(String list, String grade, int i, String name) {
//        if (i < 0) return "";
//        String str = "";
//        try {
//            DefaultResourceProvider defaultResourceProvider = ResourceManager.getProvider();
//            Object string = defaultResourceProvider.getResource("{\"res\":\"" + list + "\", \"cons\":{\"" + grade + "\":\"" + i + "\"},\"property\":\"" + name + "\"}");
//
//            if (null != string) {
//                str = (String) string;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return str;
//    }
//
//    /**
//     * 从资源获取String类型数据
//     */
//    public static String getResourceString(String list, String grade, String i, String name) {
//        if (TextUtils.isEmpty(i)) {
//            return "";
//        }
//        String str = "";
//        try {
//            DefaultResourceProvider defaultResourceProvider = ResourceManager.getProvider();
//            Object string = defaultResourceProvider.getResource("{\"res\":\"" + list + "\", \"cons\":{\"" + grade + "\":\"" + i + "\"},\"property\":\"" + name + "\"}");
//
//            if (null != string) {
//                str = (String) string;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return str;
//    }

//    /**
//     * 从资源获取当前明星等级段score(当前等级明星值的左区间)
//     */
//    public static int getScore(int i) {
//        return getResourceInt("shallUserGrades", "grade", i, "score");
//    }
//
//    /**
//     * 从资源获取当前明星等级段scoreUp(当前等级明星值的右区间)
//     */
//    public static int getScoreUp(int i) {
//        return getResourceInt("shallUserGrades", "grade", i, "scoreUp");
//    }
//
//    /**
//     * 从资源获取明星等级url
//     */
//    public static String getStarUrl(int i) {
//        return getResourceString("shallUserGrades", "grade", i, "ext1Image");
//    }
//
//    public static String getGiftUrl(int i) {
//        return getResourceString("gifts", "giftid", i, "url");
//    }
//
//    public static String getPKAnimUrl(String key) {
//        return getResourceString("constants", "key", key, "value");
//    }
//
//    /**
//     * PK连胜
//     */
//    public static String getWinUrl(int streak) {
//        if (streak > 100) {
//            streak = 100;
//        }
//        String baseUrl = getResourceString("constants", "key", "pk.winTimes.url", "value");
//        if (!TextUtils.isEmpty(baseUrl)) {
//            return baseUrl.replace("{winTimes}", streak + "");
//        }
//        return baseUrl;
//    }
//
//    /**
//     * 战力值
//     */
//    public static String getPKScoreUrl(int grade) {
//        return getResourceString("constants", "key", "pk.grade.icon." + grade, "value");
//    }
//
//    public static String getGlobalMsgUrl(String url) {
//        String baseUrl = getResourceString("constants", "key", url, "value");
//        return baseUrl;
//    }
//
//    /**
//     * 从资源获取财富等级url
//     */
//    public static String getWealthUrl(int i) {
//        if (i <= 0) {
//            i = 1;
//        }
//        return getResourceString("userGrades", "grade", i, "highlightImage");
//    }
//
//    /**
//     * 军团角色
//     */
//    public static String getArmyRole(int level) {
//        return getResourceString("constants", "key", "army.role." + level, "value");
//    }
//
//    /**
//     * 军团头衔
//     */
//    public static String getArmyTitle(int level) {
//        return getResourceString("constants", "key", "army.title.level.url." + level, "value");
//    }

//    public static Bitmap getCarPlate(String url, String text, int width, int height, String font_color) {
//        Bitmap plate_bit = BitmapFactory.decodeFile(url);
//        if (plate_bit == null) {
//            return null;
//        }
//        try {
//            int paddingLeft = DensityUtil.dip2px(ShowSelfApp.getInstance(), 3);
//            //换算图的高度
//            int backgroundHeight = DensityUtil.dip2px(ShowSelfApp.getInstance(), height);
////        int backgroundWidth = plate_bit.getWidth()*backgroundHeight/plate_bit.getHeight();
//            int backgroundWidth = DensityUtil.dip2px(ShowSelfApp.getInstance(), width);
//            //根据图的高度计算字体大小
//            Paint paint = new Paint();
//            paint.setTextSize(backgroundHeight);
//            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
//            int textWithPaddingHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
//            for (int i = backgroundHeight; i > 0 && textWithPaddingHeight >= backgroundHeight; ) {
//                i--;
//                paint.setTextSize(i);
//                fontMetricsInt = paint.getFontMetricsInt();
//                textWithPaddingHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
//            }
//            float text_width = paint.measureText(text) + paddingLeft * 2;
//            if (text_width > backgroundWidth) {
//                backgroundWidth = (int) text_width + 1;
//                backgroundHeight = plate_bit.getHeight() * backgroundWidth / plate_bit.getWidth();
//            }
//            Bitmap bg = Bitmap.createBitmap(backgroundWidth, backgroundHeight, Config.ARGB_4444);
//            Canvas canvas = new Canvas(bg);
//            plate_bit = Bitmap.createScaledBitmap(plate_bit, backgroundWidth, backgroundHeight, true);
//            canvas.drawBitmap(plate_bit, 0, 0, paint);
//
//            paint.setColor(Color.WHITE);
//            int textBaseline = backgroundHeight - (backgroundHeight - textWithPaddingHeight) / 2 - fontMetricsInt.descent;
//            paint.setColor(Color.parseColor(font_color));
//            canvas.drawText(text, paddingLeft, textBaseline, paint);
//            return bg;
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//    public static Bitmap getArmyBit(Context context, String armyName, String badgUrl, String armyTitleUrl, int armyLevel, int height) {
//        int backgroundHeight = DensityUtil.dip2px(context, height);
//        int army_level_width = backgroundHeight * 15 / 20;//护卫队等级宽度
//
//        int army_name_height = backgroundHeight * 16 / 20;//护卫队名字宽度
//        int army_name_width = army_name_height * 34 / 16;//护卫队名字高度
//        int backgroundWidth = army_level_width + army_name_width;
//        Bitmap background_bit = Bitmap.createBitmap(backgroundWidth, backgroundHeight, Config.ARGB_4444);
//        //根据图的高度计算字体大小
//        Paint paint = new Paint();
//        paint.setTextSize(army_name_height);
//        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
//        int textWithPaddingHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
//        for (int i = army_name_height; i > 0 && textWithPaddingHeight >= army_name_height - DensityUtil.dip2px(ShowSelfApp.getInstance(), 2); ) {
//            i--;
//            paint.setTextSize(i);
//            fontMetricsInt = paint.getFontMetricsInt();
//            textWithPaddingHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
//        }
//        ApplicationInfo appInfo = context.getApplicationInfo();
//        int army_name_resID = context.getResources().getIdentifier(badgUrl, "drawable", appInfo.packageName);
//        int army_level_resID = context.getResources().getIdentifier(armyTitleUrl, "drawable", appInfo.packageName);
//        if (army_name_resID == 0) {
//            army_name_resID = R.drawable.armybackground0_19;
//        }
//        if (army_level_resID == 0) {
//            army_level_resID = R.drawable.armyshen;
//        }
//        //army_no_badge_bg
//        Canvas canvas = new Canvas(background_bit);
//        Bitmap army_name_bit = BitmapFactory.decodeResource(context.getResources(), army_name_resID);
//        army_name_bit = Bitmap.createScaledBitmap(army_name_bit, army_name_width, army_name_height, true);
//        canvas.drawBitmap(army_name_bit, 0, 0, paint);
//        //
//        Bitmap level_bitmap = BitmapFactory.decodeResource(context.getResources(), army_level_resID);
//        level_bitmap = Bitmap.createScaledBitmap(level_bitmap, army_level_width, backgroundHeight, true);
//        canvas.drawBitmap(level_bitmap, army_name_width, 0, paint);
//        //
//        // 数字0-9的图片资源id
//        int[] mImageId = {R.drawable.showself_team_teamlevel_num_0,
//                R.drawable.showself_team_teamlevel_num_1,
//                R.drawable.showself_team_teamlevel_num_2,
//                R.drawable.showself_team_teamlevel_num_3,
//                R.drawable.showself_team_teamlevel_num_4,
//                R.drawable.showself_team_teamlevel_num_5,
//                R.drawable.showself_team_teamlevel_num_6,
//                R.drawable.showself_team_teamlevel_num_7,
//                R.drawable.showself_team_teamlevel_num_8,
//                R.drawable.showself_team_teamlevel_num_9};
//        int num_bit_height = backgroundHeight * 7 / 20;
//        int num_bit_width = num_bit_height * 5 / 7;
//        if (armyLevel < 10) {
//            Bitmap army_num_bitmap1 = BitmapFactory.decodeResource(context.getResources(), mImageId[armyLevel]);
//            army_num_bitmap1 = Bitmap.createScaledBitmap(army_num_bitmap1, num_bit_width, num_bit_height, true);
//            canvas.drawBitmap(army_num_bitmap1, army_name_width * 26 / 34, backgroundHeight / 2, paint);
//        } else {
//            int num1 = armyLevel / 10;
//            int num2 = armyLevel % 10;
//            Bitmap army_num_bitmap1 = BitmapFactory.decodeResource(context.getResources(), mImageId[num1]);
//            army_num_bitmap1 = Bitmap.createScaledBitmap(army_num_bitmap1, num_bit_width, num_bit_height, true);
//            canvas.drawBitmap(army_num_bitmap1, army_name_width * 26 / 34, backgroundHeight / 2, paint);
//
//            Bitmap army_num_bitmap2 = BitmapFactory.decodeResource(context.getResources(), mImageId[num2]);
//            army_num_bitmap2 = Bitmap.createScaledBitmap(army_num_bitmap2, num_bit_width, num_bit_height, true);
//            canvas.drawBitmap(army_num_bitmap2, army_name_width * 26 / 34 + num_bit_width, backgroundHeight / 2, paint);
//        }
//        paint.setColor(Color.WHITE);
//        int textBaseline = army_name_height - (army_name_height - textWithPaddingHeight) / 2 - fontMetricsInt.descent;
//        float marger_left = army_name_width - paint.measureText(armyName);
//        canvas.drawText(armyName, marger_left / 2, textBaseline, paint);
//        return background_bit;
//    }

//    public static Bitmap getRedCircleBit() {
//        int width = DensityUtil.dip2px(ShowSelfApp.getInstance(), 6);
//        Bitmap bitmap = Bitmap.createBitmap(width, width, Config.ARGB_4444);
//        Paint paint = new Paint();
//        paint.setColor(Color.parseColor("#feabbf"));
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
//        return bitmap;
//    }

    public static String getNewUrl(String credit_url) {
        if (TextUtils.isEmpty(credit_url)) {
            return credit_url;
        }
        String[] urls = credit_url.split("\\.");
        for (int i = 0; i < urls.length; i++) {
            if (i == 0) {
                credit_url = urls[0];
            } else {
                if (i != urls.length - 1) {
                    credit_url += "." + urls[i];
                } else {
                    credit_url += "_big." + urls[i];
                }
            }
        }
        return credit_url;
    }

//    /**
//     * @param key
//     * @return获取公聊区背景图片,房间用户列表官，控，售，控图片,周星，星星,
//     */
//    public static String getPublicMsgBgUrl(String key) {
//        String baseUrl = getResourceString("constants", "key", key, "value");
//        return baseUrl;
//    }
//
//    /**
//     * 从资源获取财富等级名称
//     */
//    public static String getWealthName(int i) {
//        return getResourceString("userGrades", "grade", i, "note");
//    }
//
//    /**
//     * @param vip
//     * @return
//     */
//    public static String getVipUrlFromRes(int vip) {
//        return getResourceString("constants", "key", "chat.vip." + vip, "value");
//    }

//    public static boolean checkIsSupportLogin(Context context) {
//        boolean isSupport = false;
//        try {
//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//            TelephonyManager iPhoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (iPhoneManager == null) return false;
//            if (iPhoneManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT) return false;
//            String str;
//            str = iPhoneManager.getSimOperator();
//            if (str.startsWith("46000") || str.startsWith("46002") || str.startsWith("46004") || str.startsWith("46007")) {
//                str = "中国移动";
//                isSupport = true;
//            } else if (str.startsWith("46001") || str.startsWith("46006") || str.startsWith("46009")) {
//                str = "中国联通";
//                isSupport = false;
//            } else if (str.startsWith("46003") || str.startsWith("46005") || str.startsWith("46011")) {
//                str = "中国电信";
//                isSupport = true;
//            }
//
//        } catch (Exception localException) {
//            localException.printStackTrace();
//        }
//        return isSupport;
//    }
//
//    public void upload_Phtot_error(int id, Context context) {
//        HashMap<Object, Object> taskparam = new HashMap<Object, Object>();
//        taskparam.put("fuid", id);
//        Task task = new Task(Task.TASK_GET_PROFILE, taskparam);
//        if (!ApiUtils.isConnectingToInternet(context)) {
//            // ApiUtils.showToast(
//            // this,
//            // this.getResources().getString(R.string.no_connectivity_internet));
//        }
//        task.getTaskparam().put("activityName",
//                context.getClass().getSimpleName());
//        DLog.i("activityName----->", context.getClass().getSimpleName());
//        TaskService.addTask(task, context);
//    }
//
//    public static void unZip(File zipFile) {
//        try {
//            ZipFile file = new ZipFile(zipFile);
//            Enumeration<ZipEntry> zipEnum = (Enumeration<ZipEntry>) file.entries();
//            String dirName = zipFile.getAbsolutePath().substring(0, zipFile.getAbsolutePath().lastIndexOf("."));
//            File dir = new File(dirName);
//            dir.mkdirs();
//            while (zipEnum.hasMoreElements()) {
//                ZipEntry entry = zipEnum.nextElement();
//                if (entry.isDirectory()) {
//                    String dirstr = dirName + File.separator + entry.getName();
//                    File f = new File(dirstr);
//                    f.mkdir();
//                    continue;
//                }
//                InputStream is = file.getInputStream(entry);
//                File itemFile = new File(dir, entry.getName());
//                if (itemFile.exists()) {
//                    itemFile.delete();
//                }
//                StreamUtil.saveStreamToFile(is, itemFile);
//            }
//            file.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        zipFile.delete();
//
//    }

    /**
     * 关闭软键盘
     *
     * @param activity 当前activity
     */
    public static void hintKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
