package com.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.adapter.ViewpagerAdapter;
import com.qf.autoscrollviewpager.AutoScrollViewPager;
import com.qf.custom.ImgNavView;
import com.qf.jingzhiwardrobe.R;

/**
 * Created by Administrator on 15-11-21.
 */
public class HomeFragment extends Fragment {

    private AutoScrollViewPager autoScrollViewPager;
    private ViewpagerAdapter adapter;
    private ImgNavView inv;
    private int imageIds[] = new int[]{R.mipmap.ad_1,R.mipmap.ad_2,R.mipmap.ad_3};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        adapter = new ViewpagerAdapter(getActivity(),imageIds);
        if (view != null){
            inv = (ImgNavView) view.findViewById(R.id.image_nav_view);
            autoScrollViewPager = (AutoScrollViewPager) view.findViewById(R.id.auto_view_pager);
            autoScrollViewPager.setInterval(2000);//设置循环的时间间隔
            autoScrollViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);
            autoScrollViewPager.setAdapter(adapter);

            autoScrollViewPager.startAutoScroll();
        }

    }
}
