package com.qf.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.qf.adapter.ListViewAdapter;
import com.qf.adapter.ViewpagerAdapter;
import com.qf.autoscrollviewpager.AutoScrollViewPager;
import com.qf.custom.ImgNavView;
import com.qf.jingzhiwardrobe.R;
import com.qf.model.ListData;
import com.qf.model.ViewPagerData;
import com.qf.utils.Constants;
import com.qf.utils.InitDataUtils;
import com.qf.utils.JSONUtil;
import com.qf.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class HomeFragment2 extends Fragment implements ViewPager.OnPageChangeListener,
        VolleyUtil.OnRequest, View.OnClickListener, AbsListView.OnScrollListener, View.OnTouchListener {

    private static final String TAG = "print";
    private AutoScrollViewPager autoScrollViewPager;
    private ViewpagerAdapter adapter;
    private ListView myListView;
    private ListViewAdapter listAdapter;
    private NetworkImageView netImageView;
    private ImgNavView inv;//自定义导航栏控件
    private int imageIds[] = new int[]{R.mipmap.ad_1, R.mipmap.ad_2, R.mipmap.ad_3};
    private PopupWindow popupWindow;
    private ImageView iv_more;
    private ImageView iv_tab_more;
    private ImageView iv_more_up;
    private ImageView iv_more_tab_up;
    private LinearLayout moreLayout;
    private LinearLayout tabLayout;
    private LinearLayout moreTabLayout;
    private RelativeLayout hideLayout;
    private RelativeLayout hideTabLayout;
    private TextView[] horizontaltv;
    private TextView[] tabhorizontaltv;
    private TextView[] popupwindowtv;
    private HorizontalScrollView horizontal;
    private HorizontalScrollView tabhorizontal;
    private ImageButton imgTop;
    private LinearLayout head_viewpager;
    private LinearLayout head2;
    private LinearLayout head3;
    private LinearLayout head4;
    private String strcategory = "";
    private int pageindex = 1;
    private String[] category = new String[]{
            "", "1", "2", "3", "5", "10", "7", "6", "8", "4"
    };
    private String url = String.format(Constants.URL.LIST_VIEW_DATA,pageindex, strcategory);
    private List<ListData> datas;
    private static List<ViewPagerData> vpdatas;
    private ViewPager vp;
    private String vpurl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, null);
        initView(view);
        getData();
        getVPData();
        return view;
    }

    private void initView(View view) {
        //listview的几个头部的布局
        head_viewpager = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.head_viewpage_layout, null);
        head2 = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.head_2_layout, null);
        head3 = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.head_3_layout, null);
        head4 = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.head_4_layout, null);
        vp = (ViewPager) head3.findViewById(R.id.vp);
        tabLayout = (LinearLayout) view.findViewById(R.id.top_visible_layout);
        LinearLayout blank = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.blank_layout,null);
        //布局中的horizontalview
        horizontal = (HorizontalScrollView) head4.findViewById(R.id.horizontal_layout);
        horizontal.setSmoothScrollingEnabled(false);
        horizontal.setHorizontalScrollBarEnabled(false);
        //头部可以移动移动的horizontalview
        tabhorizontal = (HorizontalScrollView) tabLayout.findViewById(R.id.horizontal_tab_layout);
        tabhorizontal.setSmoothScrollingEnabled(false);
        tabhorizontal.setHorizontalScrollBarEnabled(false);

        adapter = new ViewpagerAdapter(getActivity(), imageIds);
        //导航点
        inv = (ImgNavView) head_viewpager.findViewById(R.id.image_nav_view);
        inv.setCount(imageIds.length);
        //自动无限循环的viewpager
        autoScrollViewPager = (AutoScrollViewPager) head_viewpager.findViewById(R.id.auto_view_pager);
        autoScrollViewPager.setInterval(2000);//设置循环的时间间隔
        autoScrollViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);
        autoScrollViewPager.setAdapter(adapter);
        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.addOnPageChangeListener(this);
        //返回顶部的控件
        imgTop = (ImageButton) view.findViewById(R.id.iv_bt_top);
        imgTop.setOnClickListener(this);

        //ListView的操作
        myListView = (ListView) view.findViewById(R.id.lv_id);
        myListView.addHeaderView(head_viewpager);
        myListView.addHeaderView(blank);
        myListView.addHeaderView(head2);
        myListView.addHeaderView(blank);
        myListView.addHeaderView(head3);
        myListView.addHeaderView(head4);
        myListView.setOnScrollListener(this);//设置ListView的滚动监听
        myListView.setOnTouchListener(this);


        listAdapter = new ListViewAdapter(getActivity());
        //horizontalview 的更多和返回
        iv_more = (ImageView) head4.findViewById(R.id.iv_more);
        iv_tab_more = (ImageView) tabLayout.findViewById(R.id.iv_tab_more);
        iv_more_up = (ImageView) head4.findViewById(R.id.more_up);
        iv_more_tab_up = (ImageView) tabLayout.findViewById(R.id.more_tab_up);

        iv_more.setOnClickListener(this);
        iv_more_up.setOnClickListener(this);
        iv_tab_more.setOnClickListener(this);
        iv_more_tab_up.setOnClickListener(this);

        moreLayout = (LinearLayout) head4.findViewById(R.id.more_layout);
        moreTabLayout = (LinearLayout) tabLayout.findViewById(R.id.more_tab_layout);
        hideLayout = (RelativeLayout) head4.findViewById(R.id.horizontal_include_hide);
        hideTabLayout = (RelativeLayout) tabLayout.findViewById(R.id.horizontal_tab_hide);


        popupWindow = new PopupWindow(getResources().getDisplayMetrics().widthPixels, 165);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_layout, null);
        popupWindow.setContentView(v);

        //textview的集合
        horizontaltv = InitDataUtils.getHorizontalData(view);
        popupwindowtv = InitDataUtils.getPopupWindowData(v);
        tabhorizontaltv = InitDataUtils.getTabHorizontalData(tabLayout);

        for (int i = 0; i < horizontaltv.length; i++) {
            horizontaltv[i].setOnClickListener(this);
        }
        for (int i = 0; i < tabhorizontaltv.length; i++) {
            tabhorizontaltv[i].setOnClickListener(this);
        }
        for (int i = 0; i < popupwindowtv.length; i++) {
            popupwindowtv[i].setOnClickListener(this);
        }
    }

    private void getData() {

        VolleyUtil.requestString(url, this);
    }

    private void getVPData() {

        vpurl = Constants.URL.VIEWPAGER_DATA;
        Log.e("print", "vpurl------------->>>" + vpurl);
        VolleyUtil.requestString(vpurl, this);
    }

    public static List<ViewPagerData> getData2VP(){
        return vpdatas;
    }


    //下载数据的监听
    @Override
    public void response(String urls, String response) {

        if (response != null) {
            if (urls.equals(url)) {
                Log.e(TAG,"response================>>>"+response);
                datas = JSONUtil.parseJsonData(response);
                Log.e("print", "------------->>>" + datas.size());
                if (datas.size() == 0) return;
                listAdapter.setDatas(datas);
                myListView.setAdapter(listAdapter);
                //Log.e(TAG,"stop_position================>>>"+stop_position);
                myListView.setSelection(stop_position);
            }
            if (urls.equals(vpurl)){
                vpdatas = JSONUtil.parseVPJsonData(response);
                vp.setAdapter(new VPAdapter(getFragmentManager(),vpdatas));
            }

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

        if (v != null) {
            switch (v.getId()) {
                //栏目向下更多
                case R.id.iv_more:

                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAsDropDown(moreLayout);
                        if (hideLayout.getVisibility() == View.GONE) {
                            hideLayout.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                //栏目向上收起
                case R.id.more_up:
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        if (hideLayout.getVisibility() == View.VISIBLE) {
                            hideLayout.setVisibility(View.GONE);
                        }
                    }
                    break;
                //移动栏目向下更多
                case R.id.iv_tab_more:

                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAsDropDown(moreTabLayout);
                        if (hideTabLayout.getVisibility() == View.GONE) {
                            hideTabLayout.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                //移动栏目向上收起
                case R.id.more_tab_up:
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        if (hideTabLayout.getVisibility() == View.VISIBLE) {
                            hideTabLayout.setVisibility(View.GONE);
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

                //tabhorizontal  item  的监听
                case R.id.tv_tab_1:
                    tabhorizontalGetData(R.id.tv_tab_1);
                    break;
                case R.id.tv_tab_2:
                    tabhorizontalGetData(R.id.tv_tab_2);
                    break;
                case R.id.tv_tab_3:
                    tabhorizontalGetData(R.id.tv_tab_3);
                    break;
                case R.id.tv_tab_4:
                    tabhorizontalGetData(R.id.tv_tab_4);
                    break;
                case R.id.tv_tab_5:
                    tabhorizontalGetData(R.id.tv_tab_5);
                    break;
                case R.id.tv_tab_6:
                    tabhorizontalGetData(R.id.tv_tab_5);
                    break;
                case R.id.tv_tab_7:
                    tabhorizontalGetData(R.id.tv_tab_7);
                    break;
                case R.id.tv_tab_8:
                    tabhorizontalGetData(R.id.tv_tab_8);
                    break;
                case R.id.tv_tab_9:
                    tabhorizontalGetData(R.id.tv_tab_9);
                    break;
                case R.id.tv_tab_10:
                    tabhorizontalGetData(R.id.tv_tab_10);
                    break;
                //返回顶部操作
                case R.id.iv_bt_top:
                    myListView.setSelection(5);
                    if (imgTop.getVisibility() == View.VISIBLE){
                        imgTop.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    }

    public void horizontalGetData(int resid) {
        for (int i = 0; i < horizontaltv.length; i++) {
            if (horizontaltv[i].getId() == resid) {
                horizontaltv[i].setTextColor(Color.RED);
                tabhorizontaltv[i].setTextColor(Color.RED);
                popupwindowtv[i].setTextColor(Color.RED);
                strcategory = category[i];
                Log.e(TAG,"horizontalGetData---------------->>>"+strcategory);
                //如果再次点击时，数据的URL和点击之前的URL一致时，就不重新下载数据
                if (url.equals(String.format(Constants.URL.LIST_VIEW_DATA, pageindex, strcategory))) {
                    return;
                }
                url = String.format(Constants.URL.LIST_VIEW_DATA, pageindex,strcategory);
                getData();
            } else {
                horizontaltv[i].setTextColor(Color.BLACK);
                tabhorizontaltv[i].setTextColor(Color.BLACK);
                popupwindowtv[i].setTextColor(Color.BLACK);
            }
        }
    }

    private Handler handler = new Handler();
    public void tabhorizontalGetData(int resid) {
        for (int i = 0; i < tabhorizontaltv.length; i++) {
            if (tabhorizontaltv[i].getId() == resid) {
                tabhorizontaltv[i].setTextColor(Color.RED);
                horizontaltv[i].setTextColor(Color.RED);
                popupwindowtv[i].setTextColor(Color.RED);
                horizontal.smoothScrollTo((horizontal.getMaxScrollAmount() / 5) * i, 0);
                strcategory = category[i];
                Log.e(TAG, "tabhorizontalGetData---------------->>>" + strcategory);
                //如果再次点击时，数据的URL和点击之前的URL一致时，就不重新下载数据
                if (url.equals(String.format(Constants.URL.LIST_VIEW_DATA, pageindex,strcategory))) {
                    return;
                }
                tabLayout.setVisibility(View.GONE);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myListView.setSelection(5);
                    }
                });

                url = String.format(Constants.URL.LIST_VIEW_DATA,pageindex, strcategory);
                getData();

            } else {
                tabhorizontaltv[i].setTextColor(Color.BLACK);
                horizontaltv[i].setTextColor(Color.BLACK);
                popupwindowtv[i].setTextColor(Color.BLACK);
            }
        }
    }


    public void popToHorizontalGetData(int resid) {
        for (int i = 0; i < popupwindowtv.length; i++) {
            if (popupwindowtv[i].getId() == resid) {
                popupwindowtv[i].setTextColor(Color.RED);
                horizontaltv[i].setTextColor(Color.RED);
                tabhorizontaltv[i].setTextColor(Color.RED);
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    hideLayout.setVisibility(View.GONE);
                    hideTabLayout.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);
                    horizontal.smoothScrollTo((horizontal.getMaxScrollAmount() / 5) * i, 0);
                    strcategory = category[i];
                    Log.e(TAG, "popToHorizontalGetData---------------->>>" + strcategory);
                    url = String.format(Constants.URL.LIST_VIEW_DATA,pageindex, strcategory);
                    getData();
                }
            } else {
                horizontaltv[i].setTextColor(Color.BLACK);
                tabhorizontaltv[i].setTextColor(Color.BLACK);
                popupwindowtv[i].setTextColor(Color.BLACK);
            }
        }
    }

    //ListView 的滚动监听事件----->实现记录滚动停止时的记录，当点击加载新的数据，从记录的位置开始显示的操作
    private  int stop_position;//记录滚动停止时的可见位置的记录
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //判断是否停止滚动
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
            stop_position = myListView.getFirstVisiblePosition();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if(firstVisibleItem > 6){
            if (imgTop.getVisibility() == View.GONE){
                imgTop.setVisibility(View.VISIBLE);
            }
        }else {
            imgTop.setVisibility(View.GONE);
        }

        /*//判断是否是最后一条item
        if (firstVisibleItem+visibleItemCount == totalItemCount){
            //是最后一条，执行上拉加载下一页数据的操作
            pageindex++;
            url = String.format(Constants.URL.LIST_VIEW_DATA, pageindex , strcategory);
            Log.e(TAG,"pageindex------>"+pageindex+"strcategory------>"+strcategory);
            getData();
            //myListView.setSelection(totalItemCount);
        }*/
    }

    //设置listview的触摸事件，用来判断是向上滑动还是向下滑动
    private float moveY;
    private float stopY;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                stopY = event.getY();
                if (stopY < moveY){
                    //向下滑动了
                    if (myListView.getFirstVisiblePosition() > 6) {
                        tabLayout.setVisibility(View.VISIBLE);
                    }else {
                        tabLayout.setVisibility(View.GONE);
                    }
                }
                if (stopY > moveY){
                    //向上滑动
                    tabLayout.setVisibility(View.GONE);
                }
                break;

        }
        return false;
    }

    //viewpager  的适配器
    class VPAdapter extends FragmentStatePagerAdapter{

        private List<ViewPagerData> vpDatas;

        public VPAdapter(FragmentManager fm, List<ViewPagerData> vpDatas) {
            super(fm);
            this.vpDatas = vpDatas;
        }

        @Override
        public Fragment getItem(int position) {
            ViewPagerFragment vpf = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            vpf.setArguments(bundle);
            return vpf;
        }

        @Override
        public int getCount() {
            return vpDatas.size();
        }
    }
}
