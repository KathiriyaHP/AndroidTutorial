package com.shifter.Share;

import android.content.Context;
import android.content.SharedPreferences;

//SharedPreferences manager class
public class SharedPrefs {

    //SharedPreferences file name
    private static String SHARED_PREFS_FILE_NAME = "shift_shared_prefs";

    //here you can centralize all your shared prefs keys
    public static final String DEVICE_ID="device_id";
    public static final String KEY_IS_LOGIN = "is_login";
    public static final String USER_TYPE ="user_type";
    public static final String PASSWORD = "pwd";
    public static final String USER_ID = "user_id";
    public static final String FULL_NAME = "full_name";
    public static final String USER_NAME = "user_name";
    public static final String EMAIL = "email";
    public static final String MOBLIE_NO = "mobile_no";
    public static final String DOB="dob";
    public static final String IMAGE = "image";
    public static final String BALANCE = "balance";
    public static final String DEVICE_TOKEN="device_token";
    public static final String VERSION = "version";
    public static final String API_KEY="api_key";
    public static final String PAYPAL_ID="paypal_id";


    //WorldPay
    public static final String ADDRESS="address";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String POSTAL_CODE="postalcode";
    public static final String COUNTRY="contry";


    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }
    public static boolean contain(Context context, String key) {
        return getPrefs(context).contains(key);
    }
    public static void clearPrefs(Context context) {
        String device_id = getString(context,DEVICE_ID);
        getPrefs(context).edit().clear().commit();
        save(context,DEVICE_ID,device_id);
    }

    //Save Booleans
    public static void savePref(Context context, String key, boolean value) {
        getPrefs(context).edit().putBoolean(key, value).commit();
    }

    //Get Booleans
    public static boolean getBoolean(Context context, String key) {
        return getPrefs(context).getBoolean(key, false);
    }

    //Get Booleans if not found return a predefined default value
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getPrefs(context).getBoolean(key, defaultValue);
    }

    //Strings
    public static void save(Context context, String key, String value) {
        getPrefs(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getPrefs(context).getString(key, defaultValue);
    }

    //Integers
    public static void save(Context context, String key, int value) {
        getPrefs(context).edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key) {
        return getPrefs(context).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getPrefs(context).getInt(key, defaultValue);
    }

    //Floats
    public static void save(Context context, String key, float value) {
        getPrefs(context).edit().putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String key) {
        return getPrefs(context).getFloat(key, 0);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getPrefs(context).getFloat(key, defaultValue);
    }

    //Longs
    public static void save(Context context, String key, long value) {
        getPrefs(context).edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String key) {
        return getPrefs(context).getLong(key, 0);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getPrefs(context).getLong(key, defaultValue);
    }
}