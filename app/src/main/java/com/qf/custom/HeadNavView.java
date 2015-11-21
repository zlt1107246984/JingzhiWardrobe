package com.qf.custom;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.android.volley.VolleyError;
import com.qf.utils.Constants;
import com.qf.utils.JSONUtil;
import com.qf.utils.VolleyUtil;


/**
 * 组合自定义头部控件
 */
public class HeadNavView extends FrameLayout implements VolleyUtil.OnRequest, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    //private ViewPagerAdapter viewPagerAdapter;
    private FragmentManager fm;
    private ImgNavView inv;


    public HeadNavView(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
        init();
    }

    private void init() {
       /* LayoutInflater.from(getContext()).inflate(R.layout.custem_navview, this, true);
        viewPager = (ViewPager) this.findViewById(R.id.vp_head);
        viewPager.addOnPageChangeListener(this);

        inv = (ImgNavView) findViewById(R.id.inv_id);*/
    }

    /**
     * 根据cityID更新头部数据
     * @param cityid
     */
    public void setCityId(int cityid){
        if(cityid > 0){
           // String url = String.format(Constants.URL.HOME_HEAD_URL, cityid);
          //  VolleyUtil.requestString(url, this);
        }
    }


    /**
     * 头部数据下载成功
     * @param url
     * @param response
     */
    @Override
    public void response(String url, String response) {
        /*List<HeadNavEntity> datas = JSONUtil.getHeadNavByJSON(response);
        inv.setCount(datas.size());
        viewPagerAdapter = new ViewPagerAdapter(fm, datas);
        viewPager.setAdapter(viewPagerAdapter);*/
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    /**
     * FragmentStatePagerAdapter
     * FragmentPagerAdapter
     */
   /* class ViewPagerAdapter extends FragmentStatePagerAdapter{

        private List<HeadNavEntity> datas;

        public ViewPagerAdapter(FragmentManager fm, List<HeadNavEntity> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            HeadNavFragment headNavFragment = HeadNavFragment.getInstance(datas.get(position));
            return headNavFragment;
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }*/



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
}
