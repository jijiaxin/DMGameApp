package com.stx.xhb.dmgameapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.dmgameapp.entity.Channel;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teewon on 2016/11/30.
 */

public class ChannelUtils {

    private final static String SP_NAME_CHANNEL = "channel";
    Context context;

    public ChannelUtils(Context context) {
        this.context = context;
    }

    public void getChannelInfoNet() {
        x.http().get(new RequestParams(HttpAdress.CHANNEL_URL), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.e(result);
                try{
                    saveChannelInfo(context, result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled");

            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished");

            }
        });

    }

    private void saveChannelInfo(Context context, String json){
        SharedPreferences.Editor editor = context.getSharedPreferences(SP_NAME_CHANNEL, 1).edit();
        editor.putString("channel", json);
        editor.apply();
    }

    public ArrayList<Channel> getChannelInfoCache(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME_CHANNEL, 1);
        String result = sharedPreferences.getString("channel", null);
        if (result != null){
            try {
                JSONArray jsonArray = new JSONArray(result);
                ArrayList<Channel> channels = new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<Channel>>(){}.getType());
                return channels;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
