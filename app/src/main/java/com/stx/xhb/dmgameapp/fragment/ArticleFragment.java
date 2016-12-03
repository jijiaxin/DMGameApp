package com.stx.xhb.dmgameapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.SettingActivity;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;
import com.stx.xhb.dmgameapp.entity.Channel;
import com.stx.xhb.dmgameapp.fragment.innerFragments.CommondFragment;
import com.stx.xhb.dmgameapp.fragment.innerFragments.NewsFragment;
import com.stx.xhb.dmgameapp.utils.ChannelUtils;
import com.stx.xhb.dmgameapp.view.PagerSlidingTabStrip;
import com.stx.xhb.dmgameapp.view.TipsToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章的Fragment
 */
public class ArticleFragment extends Fragment {
    //标题
    private String[] TITLE = {"头条"};
    private View view;
    private ViewPager article_viewpager;
    private PagerSlidingTabStrip indicator;
    private TabPageIndicatorAdapter adapter;
    //fragment的集合
    private List<Fragment> fragments = new ArrayList<>();
    private TextView tv_title;
    private ImageButton main_action_menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article, container, false);
        initData();
        initView();
        setAdapter();
        setListener();
        return view;
    }

    //获取控件
    private void initView() {
        //隐藏toolbar menu控件
        main_action_menu = (ImageButton) view.findViewById(R.id.main_action_menu);
        //获取到标题栏控件
        tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("文章");
        article_viewpager = (ViewPager) view.findViewById(R.id.article_viewpager);
        //实例化TabPageIndicator然后设置ViewPager与之关联
        indicator = (PagerSlidingTabStrip) view.findViewById(R.id.article_indicator);

    }

    //初始化数据
    private void initData() {

        NewsFragment newsFragment = new NewsFragment();//新闻
        fragments.add(newsFragment);
        ChannelUtils channelUtils = new ChannelUtils(getActivity());
        List<Channel> channels = channelUtils.getChannelInfoCache();
        if (channels != null && channels.size() > 0) {

        } else {
            TipsToast.makeText(getActivity(), "数据错误，请重启APP", TipsToast.LENGTH_LONG).show();
            getActivity().finish();
            return;
        }
        TITLE = new String[channels.size()+1];
        TITLE[0] = "头条";
        for (int i = 0; i < channels.size(); i++) {
            TITLE[i+1] = channels.get(i).getName();
            CommondFragment fragment = new CommondFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("typeid", channels.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, TITLE);
        //设置适配器
        article_viewpager.setAdapter(adapter);
        article_viewpager.setCurrentItem(0);
        indicator.setViewPager(article_viewpager);
    }

    //设置监听
    private void setListener() {
        //indicator的滑动监听事件
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //动态改变标题栏文字
                tv_title.setText(TITLE[position]);
            }
        });
        //main_action_menu的点击事件
        main_action_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到个人设置界面
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }


}
