package io.sozin.droidlubster.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

/**
 * Created by tes1oner on 24/11/17.
 */

public class AppUtils {
    public static int getVersionCode(Context context){
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo.versionCode;
    }
    public static String getVersion(Context context){
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo.versionName;
    }
    public static PackageInfo getPackageInfo(Context context){
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
