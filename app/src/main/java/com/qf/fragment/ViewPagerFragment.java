package com.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.qf.jingzhiwardrobe.R;
import com.qf.model.ViewPagerData;
import com.qf.utils.Constants;
import com.qf.utils.VolleyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 15-11-26.
 */
public class ViewPagerFragment extends Fragment {

    private static final String TAG = "print";
    private NetworkImageView iv;
    private TextView tv_vp_1, tv_vp_2;
    private ViewPager view;
    private List<ViewPagerData> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_fragment_layout, null, false);
        initView(view);
        setData();
        return view;
    }

    private void initView(View view) {

        iv = (NetworkImageView) view.findViewById(R.id.iv_vp);
        tv_vp_1 = (TextView) view.findViewById(R.id.tv_vp_1);
        tv_vp_2 = (TextView) view.findViewById(R.id.tv_vp_2);
    }

    private void setData() {

        datas = HomeFragment2.getData2VP();
        int position = getArguments().getInt("position");
        Log.e("------>", "datas-----》》》" + datas.size());
            ViewPagerData data = datas.get(position);
            String url = Constants.URL.HTTP + data.getsImg();
            VolleyImageLoader.getInstance(getActivity()).loadImage(iv, url);
            tv_vp_1.setText(data.getsItemName());
            tv_vp_2.setText(data.getDwActMinPrice());

    }

}
