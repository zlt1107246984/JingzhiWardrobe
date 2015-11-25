package com.qf.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.qf.adapter.ListViewAdapter;
import com.qf.adapter.ViewpagerAdapter;
import com.qf.autoscrollviewpager.AutoScrollViewPager;
import com.qf.custom.ImgNavView;
import com.qf.custom.MyListView;
import com.qf.jingzhiwardrobe.R;
import com.qf.model.ListData;
import com.qf.utils.Constants;
import com.qf.utils.InitDataUtils;
import com.qf.utils.JSONUtil;
import com.qf.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, VolleyUtil.OnRequest, View.OnClickListener {

    private static final String TAG = "print";
    private AutoScrollViewPager autoScrollViewPager;
    private ViewpagerAdapter adapter;
    private MyListView myListView;
    private ListViewAdapter listAdapter;
    private NetworkImageView netImageView;
    private ImgNavView inv;//自定义导航栏控件
    private int imageIds[] = new int[]{R.mipmap.ad_1,R.mipmap.ad_2,R.mipmap.ad_3};
    private PopupWindow popupWindow;
    private ImageView iv_more;
    private ImageView iv_more_up;
    private LinearLayout moreLayout;
    private RelativeLayout hideLayout;
    private TextView [] horizontaltv;
    private TextView [] popupwindowtv;
    private String [] category = new String[]{
            "","1","2","3","5","10","7","6","8","4"
    };
    private String url = String.format(Constants.URL.LIST_VIEW_DATA,category[0]);
    private HorizontalScrollView horizontal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,null);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        horizontal = (HorizontalScrollView) view.findViewById(R.id.horizontal_layout);
        horizontal.setSmoothScrollingEnabled(false);
        horizontal.setHorizontalScrollBarEnabled(false);
        adapter = new ViewpagerAdapter(getActivity(),imageIds);
        if (view != null){

            inv = (ImgNavView) view.findViewById(R.id.image_nav_view);
            inv.setCount(imageIds.length);
            autoScrollViewPager = (AutoScrollViewPager) view.findViewById(R.id.auto_view_pager);
            autoScrollViewPager.setInterval(2000);//设置循环的时间间隔
            autoScrollViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);
            autoScrollViewPager.setAdapter(adapter);

            autoScrollViewPager.startAutoScroll();

            autoScrollViewPager.addOnPageChangeListener(this);

            myListView = (MyListView) view.findViewById(R.id.lv_id);

            listAdapter = new ListViewAdapter(getActivity());

            iv_more = (ImageView) view.findViewById(R.id.iv_more);
            iv_more_up = (ImageView) view.findViewById(R.id.more_up);

            iv_more.setOnClickListener(this);
            iv_more_up.setOnClickListener(this);

            moreLayout = (LinearLayout) view.findViewById(R.id.more_layout);
            hideLayout = (RelativeLayout) view.findViewById(R.id.horizontal_include_hide);

            popupWindow = new PopupWindow(getResources().getDisplayMetrics().widthPixels,165);
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_layout,null);
            popupWindow.setContentView(v);


            horizontaltv = InitDataUtils.getHorizontalData(view);
            popupwindowtv = InitDataUtils.getPopupWindowData(v);

            for (int i = 0; i < horizontaltv.length; i++) {
                horizontaltv[i].setOnClickListener(this);
            }
            for (int i = 0; i < popupwindowtv.length; i++) {
                popupwindowtv[i].setOnClickListener(this);
            }
        }

    }

    private void getData() {

        VolleyUtil.requestString(url, this);
    }


    //下载数据的监听
    @Override
    public void response(String url, String response) {

        if (response != null){
            List<ListData> datas = JSONUtil.parseJsonData(response);
            Log.e("print","------------->>>"+datas.size());
            listAdapter.setDatas(datas);
            myListView.setAdapter(listAdapter);

        }

    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    //viewpager的监听事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        inv.selectIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //点击事件的监听
    @Override
    public void onClick(View v) {

        if (v != null){
            switch (v.getId()){
                //栏目向下更多
                case R.id.iv_more:

                    if (popupWindow.isShowing()){
                        popupWindow.dismiss();
                    }else {
                        popupWindow.showAsDropDown(moreLayout);
                        if (hideLayout.getVisibility() == View.GONE){
                            hideLayout.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                //栏目向上收起
                case R.id.more_up:
                    if (popupWindow.isShowing()){
                        popupWindow.dismiss();
                        if (hideLayout.getVisibility() == View.VISIBLE){
                            hideLayout.setVisibility(View.GONE);
                        }
                    }
                    break;
                //horizontal  item  的监听
                case R.id.tv_1:
                    horizontalGetData(R.id.tv_1);
                    break;
                case R.id.tv_2:
                    horizontalGetData(R.id.tv_2);
                    break;
                case R.id.tv_3:
                    horizontalGetData(R.id.tv_3);
                    break;
                case R.id.tv_4:
                    horizontalGetData(R.id.tv_4);
                    break;
                case R.id.tv_5:
                    horizontalGetData(R.id.tv_5);
                    break;
                case R.id.tv_6:
                    horizontalGetData(R.id.tv_6);
                    break;
                case R.id.tv_7:
                    horizontalGetData(R.id.tv_7);
                    break;
                case R.id.tv_8:
                    horizontalGetData(R.id.tv_8);
                    break;
                case R.id.tv_9:
                    horizontalGetData(R.id.tv_9);
                    break;
                case R.id.tv_10:
                    horizontalGetData(R.id.tv_10);
                    break;

                //popupwindow  item  的监听
                case R.id.tv_pop_1:
                    popToHorizontalGetData(R.id.tv_pop_1);
                    break;
                case R.id.tv_pop_2:
                    popToHorizontalGetData(R.id.tv_pop_2);
                    break;
                case R.id.tv_pop_3:
                    popToHorizontalGetData(R.id.tv_pop_3);
                    break;
                case R.id.tv_pop_4:
                    popToHorizontalGetData(R.id.tv_pop_4);
                    break;
                case R.id.tv_pop_5:
                    popToHorizontalGetData(R.id.tv_pop_5);
                    break;
                case R.id.tv_pop_6:
                    popToHorizontalGetData(R.id.tv_pop_6);
                    break;
                case R.id.tv_pop_7:
                    popToHorizontalGetData(R.id.tv_pop_7);
                    break;
                case R.id.tv_pop_8:
                    popToHorizontalGetData(R.id.tv_pop_8);
                    break;
                case R.id.tv_pop_9:
                    popToHorizontalGetData(R.id.tv_pop_9);
                    break;
                case R.id.tv_pop_10:
                    popToHorizontalGetData(R.id.tv_pop_10);
                    break;



            }
        }
    }

    public void horizontalGetData(int resid){
        for (int i = 0; i < horizontaltv.length; i++) {
            if (horizontaltv[i].getId() == resid){
                horizontaltv[i].setTextColor(Color.RED);
                popupwindowtv[i].setTextColor(Color.RED);
                //如果再次点击时，数据的URL和点击之前的URL一致时，就不重新下载数据
                if (url.equals(String.format(Constants.URL.LIST_VIEW_DATA,category[i]))){
                    return;
                }
                url = String.format(Constants.URL.LIST_VIEW_DATA,category[i]);
                getData();
            }else {
                horizontaltv[i].setTextColor(Color.BLACK);
                popupwindowtv[i].setTextColor(Color.BLACK);
            }
        }
    }


    public void popToHorizontalGetData(int resid){
        for (int i = 0; i < popupwindowtv.length; i++) {
            if (popupwindowtv[i].getId() == resid){
                popupwindowtv[i].setTextColor(Color.RED);
                horizontaltv[i].setTextColor(Color.RED);
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                    hideLayout.setVisibility(View.GONE);
                    horizontal.smoothScrollTo((horizontal.getMaxScrollAmount()/5)*i,0);
                    url = String.format(Constants.URL.LIST_VIEW_DATA,category[i]);
                    getData();
                }
            }else {
                horizontaltv[i].setTextColor(Color.BLACK);
                popupwindowtv[i].setTextColor(Color.BLACK);
            }
        }
    }
}
