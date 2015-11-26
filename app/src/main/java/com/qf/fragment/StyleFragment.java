package com.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

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
public class StyleFragment extends Fragment implements VolleyUtil.OnRequest, View.OnClickListener {
    private StaggeredGridView gd;
    private MyGridViewAdapter madapter;
    private LinearLayout layout1, layout2;
    private MainAdapter mainAdapter;
    private TextView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    private ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11;
    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13,bt14;
    private View view;
    private ScrollView sv;
    private View view3;
    private PopupWindow window;

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
        tv.setOnClickListener(this);tv2.setOnClickListener(this);tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);tv5.setOnClickListener(this);tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);tv8.setOnClickListener(this);tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);iv1.setOnClickListener(this);iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);iv4.setOnClickListener(this);iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);iv7.setOnClickListener(this);iv8.setOnClickListener(this);
        iv9.setOnClickListener(this);iv10.setOnClickListener(this);iv11.setOnClickListener(this);
        bt1.setOnClickListener(this); bt2.setOnClickListener(this); bt3.setOnClickListener(this);
        bt4.setOnClickListener(this); bt5.setOnClickListener(this); bt6.setOnClickListener(this);
        bt7.setOnClickListener(this); bt8.setOnClickListener(this); bt9.setOnClickListener(this);
        bt10.setOnClickListener(this); bt11.setOnClickListener(this); bt12.setOnClickListener(this);
        bt13.setOnClickListener(this); bt14.setOnClickListener(this);


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
        view3 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout,null);
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
        iv1 = (ImageView) view3.findViewById(R.id.iv_id);
        iv2 = (ImageView) view3.findViewById(R.id.imageView2);
        iv3 = (ImageView) view3.findViewById(R.id.imageView3);
        iv4 = (ImageView) view3.findViewById(R.id.imageView4);
        iv5 = (ImageView) view3.findViewById(R.id.imageView5);
        iv6 = (ImageView) view3.findViewById(R.id.imageView7);
        iv7 = (ImageView) view3.findViewById(R.id.imageView8);
        iv8 = (ImageView) view3.findViewById(R.id.imageView9);
        iv9 = (ImageView) view3.findViewById(R.id.imageView12);
        iv10 = (ImageView) view3.findViewById(R.id.imageView13);
        iv11 = (ImageView) view3.findViewById(R.id.imageView14);
        bt1= (Button) view3.findViewById(R.id.bt_id);
        bt2= (Button) view3.findViewById(R.id.bt_id2);
        bt3= (Button) view3.findViewById(R.id.bt_id3);
        bt4= (Button) view3.findViewById(R.id.bt_id4);
        bt5= (Button) view3.findViewById(R.id.bt_id5);
        bt6= (Button) view3.findViewById(R.id.bt_id6);
        bt7= (Button) view3.findViewById(R.id.bt_id7);
        bt8= (Button) view3.findViewById(R.id.bt_id8);
        bt9= (Button) view3.findViewById(R.id.bt_id9);
        bt10= (Button) view3.findViewById(R.id.bt_id10);
        bt11= (Button) view3.findViewById(R.id.bt_id11);
        bt12= (Button) view3.findViewById(R.id.bt_id12);
        bt13= (Button) view3.findViewById(R.id.bt_id13);
        bt14= (Button) view3.findViewById(R.id.bt_id14);
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
                showPopupwin(view);
                break;
            case R.id.iv_id:
                window.dismiss();
                break;
            case R.id.imageView2:
                loadData(String.format(Constants.URL.POPUPDATA,"12203",1), madapter);
                window.dismiss();
                tv9.setText("卫衣");
                break;
            case R.id.imageView3:
                loadData(String.format(Constants.URL.POPUPDATA,"11087",1), madapter);
                window.dismiss();
                tv9.setText("针织");
                break;
            case R.id.imageView4:
                loadData(String.format(Constants.URL.POPUPDATA,"12532",1), madapter);
                window.dismiss();
                tv9.setText("风衣");
                break;
            case R.id.imageView5:
                loadData(String.format(Constants.URL.POPUPDATA,"13732",1), madapter);
                window.dismiss();
                tv9.setText("大衣");
                break;
            case R.id.imageView7:
                loadData(String.format(Constants.URL.POPUPDATA,"8998",1), madapter);
                window.dismiss();
                tv9.setText("OL");
                break;
            case R.id.imageView8:
                loadData(String.format(Constants.URL.POPUPDATA,"9005",1), madapter);
                window.dismiss();
                tv9.setText("英伦");
                break;
            case R.id.imageView9:
                loadData(String.format(Constants.URL.POPUPDATA,"8847",1), madapter);
                window.dismiss();
                tv9.setText("民族");
                break;

            case R.id.imageView12:
                loadData(String.format(Constants.URL.POPUPDATA,"9007",1), madapter);
                window.dismiss();
                tv9.setText("约会");
                break;
            case R.id.imageView13:
                loadData(String.format(Constants.URL.POPUPDATA,"8979",1), madapter);
                window.dismiss();
                tv9.setText("宴会");
                break;
            case R.id.imageView14:
                loadData(String.format(Constants.URL.POPUPDATA,"9033",1), madapter);
                window.dismiss();
                tv9.setText("出游");
                break;
            case R.id.bt_id:
                loadData(String.format(Constants.URL.POPUPDATA,"8977",1), madapter);
                window.dismiss();
                tv9.setText(bt1.getText());
                break;
            case R.id.bt_id2:
                loadData(String.format(Constants.URL.POPUPDATA,"8969",1), madapter);
                window.dismiss();
                tv9.setText(bt2.getText());
                break;
            case R.id.bt_id3:
                loadData(String.format(Constants.URL.POPUPDATA, "8855", 1), madapter);
                window.dismiss();
                tv9.setText(bt3.getText());
                break;
            case R.id.bt_id4:
                loadData(String.format(Constants.URL.POPUPDATA,"8862",1), madapter);
                window.dismiss();
                tv9.setText(bt4.getText());
                break;
            case R.id.bt_id5:
                loadData(String.format(Constants.URL.POPUPDATA,"8995",1), madapter);
                window.dismiss();
                tv9.setText(bt5.getText());
                break;
            case R.id.bt_id6:
                loadData(String.format(Constants.URL.POPUPDATA,"8999",1), madapter);
                window.dismiss();
                tv9.setText(bt6.getText());
                break;
            case R.id.bt_id7:
                loadData(String.format(Constants.URL.POPUPDATA,"9031",1), madapter);
                window.dismiss();
                tv9.setText(bt7.getText());
                break;
            case R.id.bt_id8:
                loadData(String.format(Constants.URL.POPUPDATA,"9000",1), madapter);
                window.dismiss();
                tv9.setText(bt8.getText());
                break;
            case R.id.bt_id9:
                loadData(String.format(Constants.URL.POPUPDATA,"9875",1), madapter);
                window.dismiss();
                tv9.setText(bt9.getText());
                break;
            case R.id.bt_id10:
                loadData(String.format(Constants.URL.POPUPDATA,"8848",1), madapter);
                window.dismiss();
                tv9.setText(bt10.getText());
                break;
            case R.id.bt_id11:
                loadData(String.format(Constants.URL.POPUPDATA, "9356", 1), madapter);
                window.dismiss();
                tv9.setText(bt11.getText());
                break;
            case R.id.bt_id12:
                loadData(String.format(Constants.URL.POPUPDATA,"8980",1), madapter);
                window.dismiss();
                tv9.setText(bt12.getText());
                break;
            case R.id.bt_id13:
                loadData(String.format(Constants.URL.POPUPDATA,"9842",1), madapter);
                window.dismiss();
                tv9.setText(bt13.getText());
                break;
            case R.id.bt_id14:
                loadData(String.format(Constants.URL.POPUPDATA,"8863",1), madapter);
                window.dismiss();
                tv9.setText("运动");
                break;
        }

    }
    public void showPopupwin(View view){
        window = new PopupWindow(getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels *2/3);
        View rootView = view.findViewById(R.id.layot_main);
        window.setContentView(view3);
        setBackgroundAlpha(1f);
        window.setAnimationStyle(R.style.popuwindowanim);
        window.setFocusable(true);
        window.showAtLocation(rootView, Gravity.BOTTOM | Gravity.LEFT, 0, 120);

    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);

    }
}
