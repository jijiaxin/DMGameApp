package com.stx.xhb.dmgameapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.LoginActivity;
import com.stx.xhb.dmgameapp.entity.LoginEntity;
import com.stx.xhb.dmgameapp.entity.UserEntity;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.UserUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;

import static com.stx.xhb.dmgameapp.R.id.iv_portrait_unlogin_cover;

/**
 * Created by hasty on 16/11/3.
 */
public class MyFragment extends Fragment{
    private View view;
    private ImageView iv_login;
    private TextView nameTipTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initView();
        setListener();
        setAdapter();
        //setSwipeRefreshInfo();
        return view;

    }

    //初始化控件
    private void initView() {
        //隐藏toolbar menu控件
        ImageButton main_action_menu = (ImageButton) view.findViewById(R.id.main_action_menu);
        main_action_menu.setVisibility(View.GONE);
        nameTipTv = (TextView) view.findViewById(R.id.name_tip);
        TextView tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.tab_my));
        iv_login = (ImageView) view.findViewById(iv_portrait_unlogin_cover);
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, GAME_NAME);
        //gridViewAdapter = new GridViewAdapter(getActivity(), gameListItems);
        //设置适配器
        //sp.setAdapter(adapter);
        //game_grid.setAdapter(gridViewAdapter);

    }

    //设置事件监听
    private void setListener() {

        iv_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("myfragment onresume");

        getUserInfo();
    }

    /**
     * 更新用户信息
     */
    private void getUserInfo(){
        LogUtil.e("getuserinfo");
        UserEntity userEntity= UserUtils.getUserInfo(getActivity());
        // 有用户信息
        if (userEntity != null && userEntity.getData() != null) {
            nameTipTv.setText(userEntity.getData().getUsername());
            // 用户小头像地址
            UserEntity.DataEntity.AvatarEntity avatarEntity = userEntity.getData().getAvatar();
            String url = avatarEntity.getPrefix()+avatarEntity.getDir()+
                    avatarEntity.getName()+avatarEntity.getNamePostfix()+
                    "small."+avatarEntity.getExt();
            LogUtil.e("getuserinfo+url  " + url);

            x.image().bind(iv_login,url);
        }else{
            LoginEntity loginEntity = UserUtils.getLoginInfo(getActivity());
            // 没有用户信息，但是有登录信息
            if (loginEntity != null && loginEntity.getData() != null) {
                String url = String.format(HttpAdress.USER_URL, loginEntity.getData().getUid(), loginEntity.getData().getToken());
                x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        LogUtil.e("getuserinfo: " + result);
                        String json = new String(result);
                        UserEntity userEntity = new Gson().fromJson(json, UserEntity.class);
                        if (userEntity.getSignal() == 1){
                            UserUtils.saveUserInfo(getActivity(), json);

                            nameTipTv.setText(userEntity.getData().getUsername());
                            // 用户小头像地址
                            UserEntity.DataEntity.AvatarEntity avatarEntity = userEntity.getData().getAvatar();
                            String url = avatarEntity.getPrefix()+avatarEntity.getDir()+
                                    avatarEntity.getName()+avatarEntity.getNamePostfix()+
                                    "small."+avatarEntity.getExt();
                            LogUtil.e("getuserinfo+url  " + url);

                            x.image().bind(iv_login,url);
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }else{
                // 没有用户信息和登录信息，默认状态，显示灰色头像和未登录提示

            }
        }

    }
}
