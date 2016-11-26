package com.stx.xhb.dmgameapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.classic.common.MultipleStatusView;
import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.GameDetailActivity;
import com.stx.xhb.dmgameapp.activities.LoginActivity;
import com.stx.xhb.dmgameapp.activities.SettingActivity;
import com.stx.xhb.dmgameapp.adapter.GridViewAdapter;
import com.stx.xhb.dmgameapp.entity.GameListItem;
import com.stx.xhb.dmgameapp.entity.LoginEntity;
import com.stx.xhb.dmgameapp.entity.UserEntity;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.NetConnectedUtils;
import com.stx.xhb.dmgameapp.utils.UserUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.loadingviewfinal.GridViewFinal;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;

/**
 * Created by hasty on 16/11/3.
 */
public class MyFragment extends Fragment{
    private View view;
    private Spinner sp;
    private View view_myreg;
    private ImageView iv_login;
    //游戏类型集合
    private static final String[] GAME_NAME = new String[]{
            "游戏", "动作", "射击", "角色扮演", "养成", "益智", "即时策略", "策略", "体育", "模拟经营", "赛车", "冒险"
    };
    //游戏typeid
    private static final int[] GAME_TYPE_ID = new int[]{
            179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initView();
        setLstener();
        setAdapter();
        //setSwipeRefreshInfo();
        return view;

    }

    //初始化控件
    private void initView() {
        //隐藏toolbar menu控件
        ImageButton main_action_menu = (ImageButton) view.findViewById(R.id.main_action_menu);
        main_action_menu.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.tab_my));
        iv_login = (ImageView) view.findViewById(R.id.iv_portrait_unlogin_cover);
        sp = (Spinner) view.findViewById(R.id.game_spinner);
        view_myreg = view.findViewById(R.id.linear_myreg);
        //game_grid = (GridViewFinal) view.findViewById(R.id.content_view);
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, GAME_NAME);
        //gridViewAdapter = new GridViewAdapter(getActivity(), gameListItems);
        //设置适配器
        //sp.setAdapter(adapter);
        //game_grid.setAdapter(gridViewAdapter);

    }

    //设置事件监听
    private void setLstener() {
        //spinner的点击事件
        //sp.setOnItemSelectedListener(this);
        //Gridview的点击事件
        //game_grid.setOnItemClickListener(this);
        //点击重试
        /*multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData(1, typeid);
            }
        });*/

        iv_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i = 0;
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        view_myreg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i = 0;
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
        LoginEntity loginEntity = UserUtils.getLoginInfo(getActivity());
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
        }
    }
}
