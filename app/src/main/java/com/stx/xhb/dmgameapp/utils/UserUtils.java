package com.stx.xhb.dmgameapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.entity.LoginEntity;
import com.stx.xhb.dmgameapp.entity.UserEntity;

/**
 * Created by pkxutao on 2016/11/26.
 */

public class UserUtils {
    private static String NAME_USER = "user";
    private static String CODE_LOGIN = "LOGIN_INFO";
    private static String CODE_USER  = "USER_INFO";

    /**
     * 保存登陆返回信息
     * @param context
     * @param loginInfo
     */
    public static void saveLoginInfo(Context context, String loginInfo){
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_USER, 1).edit();
        editor.putString(CODE_LOGIN, loginInfo);
        editor.apply();
    }

    /**
     * 获取登录返回信息
     * @param context
     * @return
     */
    public static LoginEntity getLoginInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME_USER, 1);
        String info = sp.getString(CODE_LOGIN, "");
        return  new Gson().fromJson(info, LoginEntity.class);
    }

    /**
     * 保存用户信息
     * @param context
     * @param json
     */
    public static void saveUserInfo(Context context, String json){
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_USER, 1).edit();
        editor.putString(CODE_USER, json);
        editor.apply();
    }

    /**
     * 获取用户信息
     * @param context
     * @return
     */
    public static UserEntity getUserInfo(Context context){
        try{
            SharedPreferences sp = context.getSharedPreferences(NAME_USER, 1);
            String info = sp.getString(CODE_USER, "");
            return  new Gson().fromJson(info, UserEntity.class);

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    /**
     * 清除本地缓存用户信息
     * @param context
     */
    public static void clearUserInfo(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_USER, 1).edit();
        editor.putString(CODE_USER, "");
        editor.apply();
    }

}
