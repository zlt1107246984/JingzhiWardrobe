package com.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.qf.adapter.MyGridViewAdapter;
import com.qf.model.DapeiEntity;
import com.qf.utils.Constants;
import com.qf.utils.JSONUtil;
import com.qf.utils.VolleyUtil;
import com.qf.jingzhiwardrobe.R;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class StyleFragment extends Fragment implements VolleyUtil.OnRequest {
    private GridView gd;
    private MyGridViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.style_layout,null);

        initView(view);
        loadData();
        return view;
    }

    private void loadData() {
        VolleyUtil.requestString(Constants.URL.ouMeiUrl,this);
        gd.setAdapter(adapter);
    }

    @Override
    public void response(String url, String response) {
        List<DapeiEntity> datas = JSONUtil.JsonParser(response);
        adapter.setDatas(datas);
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    private void initView(View view) {
        gd = (GridView) view.findViewById(R.id.gd_id);
        adapter = new MyGridViewAdapter(getActivity());
    }


}
