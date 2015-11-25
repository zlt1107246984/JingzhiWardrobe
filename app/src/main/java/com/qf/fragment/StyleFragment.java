package com.qf.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.etsy.android.grid.ExtendableListView;
import com.etsy.android.grid.StaggeredGridView;
import com.qf.adapter.MainAdapter;
import com.qf.adapter.MyGridViewAdapter;
import com.qf.model.DapeiEntity;
import com.qf.model.mainEntity;
import com.qf.utils.Constants;
import com.qf.utils.JSONUtil;
import com.qf.utils.VolleyUtil;
import com.qf.jingzhiwardrobe.R;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class StyleFragment extends Fragment implements VolleyUtil.OnRequest, View.OnClickListener {
    private StaggeredGridView gd;
    private MyGridViewAdapter madapter;
    private LinearLayout layout1, layout2;
    private MainAdapter mainAdapter;
    private TextView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    private View view;
    private ScrollView sv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.style_layout, null);
        initView(view);
        loadData(Constants.URL.mainUrl, mainAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);
    }

    private void loadData(String url, BaseAdapter adapter) {
        VolleyUtil.requestString(url, this);
        gd.setAdapter(adapter);
    }

    @Override
    public void response(String url, String response) {
        if (Constants.URL.mainUrl.equals(url)) {
            List<mainEntity> datas = JSONUtil.jsonMainParser(response);
            mainAdapter.setDatas(datas);
        } else {
            List<DapeiEntity> datas = JSONUtil.JsonParser(response);
            madapter.setDatas(datas);
        }

    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    private void initView(View view) {
        gd = (StaggeredGridView) view.findViewById(R.id.gd_id);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.head, null);
        gd.addHeaderView(view2);//设置header
        madapter = new MyGridViewAdapter(getActivity());
        mainAdapter = new MainAdapter(getActivity());
        layout1 = (LinearLayout) view2.findViewById(R.id.layout_lab);
        layout2 = (LinearLayout) view2.findViewById(R.id.layout_lab2);
        tv = (TextView) view2.findViewById(R.id.tv_id);
        tv2 = (TextView) view2.findViewById(R.id.tv_id2);
        tv3 = (TextView) view2.findViewById(R.id.tv_id3);
        tv4 = (TextView) view2.findViewById(R.id.tv_id4);
        tv5 = (TextView) view2.findViewById(R.id.tv_id5);
        tv6 = (TextView) view2.findViewById(R.id.tv_id6);
        tv7 = (TextView) view2.findViewById(R.id.tv_id7);
        tv8 = (TextView) view2.findViewById(R.id.tv_id8);
        tv9 = (TextView) view2.findViewById(R.id.tv_id9);
        tv10 = (TextView) view2.findViewById(R.id.tv_id10);
        sv = (ScrollView) view2.findViewById(R.id.sv_id);

        initClickEvent();
    }

    private void initClickEvent() {
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                layout1.getChildAt(i).setOnClickListener(this);

            } else {
                layout2.getChildAt(i - 5).setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_id:
                loadData(Constants.URL.mainUrl, mainAdapter);
                break;
            case R.id.tv_id2:
                loadData(Constants.URL.ouMeiUrl, madapter);
                break;
            case R.id.tv_id3:
                loadData(Constants.URL.sweetUrl, madapter);
                break;
            case R.id.tv_id4:
                loadData(Constants.URL.slimUrl, madapter);
                break;
            case R.id.tv_id5:
                loadData(Constants.URL.boyUrl, madapter);
                break;
            case R.id.tv_id6:
                loadData(Constants.URL.hanUrl, madapter);
                break;
            case R.id.tv_id7:
                loadData(Constants.URL.girlUrl, madapter);
                break;
            case R.id.tv_id8:
                loadData(Constants.URL.schoolUrl, madapter);
                break;
            case R.id.tv_id9:
                loadData(Constants.URL.packageUrl, madapter);
                break;
            case R.id.tv_id10:
                PopupWindow window = new PopupWindow(getResources().getDisplayMetrics().widthPixels,
                        getResources().getDisplayMetrics().heightPixels *2/3);
                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout,null);
                View rootView = view.findViewById(R.id.layot_main);
                window.setContentView(view2);
                setBackgroundAlpha(1f);
                window.setAnimationStyle(R.style.popuwindowanim);
                window.showAtLocation(rootView, Gravity.BOTTOM| Gravity.LEFT, 0, 120);
                window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });
                break;
        }

    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);

    }
}
