package com.qf.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
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
public class StyleFragment extends Fragment implements VolleyUtil.OnRequest,View.OnClickListener {
    private StaggeredGridView gd;
    private MyGridViewAdapter madapter;
    private LinearLayout layout1,layout2;
    private MainAdapter mainAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.style_layout,null);
        initView(view);
        loadData(Constants.URL.mainUrl,mainAdapter);
        return view;
    }

    private void loadData(String url,BaseAdapter adapter) {
        VolleyUtil.requestString(url, this);
        gd.setAdapter(adapter);
    }

    @Override
    public void response(String url, String response) {
        if(Constants.URL.mainUrl.equals(url )){
            List<mainEntity> datas = JSONUtil.jsonMainParser(response);
            mainAdapter.setDatas(datas);
        }else{
            List<DapeiEntity> datas = JSONUtil.JsonParser(response);
            madapter.setDatas(datas);
        }

    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }
    private void initView(View view) {
        gd = (StaggeredGridView) view.findViewById(R.id.gd_id);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.head,null);
        gd .addHeaderView(view2);//设置header
        madapter = new MyGridViewAdapter(getActivity());
        mainAdapter = new MainAdapter(getActivity());
        layout1 = (LinearLayout) view2.findViewById(R.id.layout_lab);
        layout2 = (LinearLayout) view2.findViewById(R.id.layout_lab2);
        initClickEvent();
    }
    private void initClickEvent() {
        for(int i = 0; i < 10;i++){
            if(i<5){
                layout1.getChildAt(i).setOnClickListener(this);

            }else{
                layout2.getChildAt(i-5).setOnClickListener(this);
            }
        }
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.tv_id:
                loadData(Constants.URL.mainUrl,mainAdapter);
                 break;
            case R.id.tv_id2:
                loadData(Constants.URL.ouMeiUrl,madapter);
                break;
            case R.id.tv_id3:
                loadData(Constants.URL.sweetUrl,madapter);
                break;
            case R.id.tv_id4:
                loadData(Constants.URL.slimUrl,madapter);
                break;
            case R.id.tv_id5:
                loadData(Constants.URL.boyUrl,madapter);
                break;
            case R.id.tv_id6:
                loadData(Constants.URL.hanUrl,madapter);
                break;
            case R.id.tv_id7:
                loadData(Constants.URL.girlUrl,madapter);
                break;
            case R.id.tv_id8:
                loadData(Constants.URL.schoolUrl,madapter);
                break;
            case R.id.tv_id9:
                loadData(Constants.URL.packageUrl,madapter);
                break;
            case R.id.tv_id10:
                break;
        }
    }
}
