package com.stx.xhb.dmgameapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pkxutao on 2016/11/26.
 */

public class RecordUtils {
    private static String NAME_FILE = "record";
    private static String FIRST_IN = "FIRST_IN";

    /**
     * 保存登陆返回信息
     * @param context
     */
    public static void saveHasIn(Context context, boolean isFirstIn){
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_FILE, 1).edit();
        editor.putBoolean(FIRST_IN, isFirstIn);
        editor.apply();
    }

    /**
     * 获取登录返回信息
     * @param context
     * @return
     */
    public static boolean getHasIn(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME_FILE, 1);
        boolean info = sp.getBoolean(FIRST_IN, false);
        return  info;
    }

}
