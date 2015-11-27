package com.qf.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.qf.jingzhiwardrobe.R;
import com.qf.model.mainEntity;
import com.qf.utils.VolleyImageLoader;
import com.qf.utils.VolleyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 15-11-24.
 */
public class MainAdapter extends AbsBaseAdapter2<mainEntity> {
    private Context context;
    public MainAdapter(Context context, int... resid) {
        super(context, R.layout.grideview_title,R.layout.gridview_detial);
        this.context = context;
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, mainEntity data) {
        TextView tv = (TextView) viewHolder.getView(R.id.tv_id);
        NetworkImageView iv = (NetworkImageView) viewHolder.getView(R.id.iv_id);
        if(data != null){
            if(data.getType() == 0){
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EEE");
                String time =dateFormat.format(date);
                tv.setText(time);
            }else {
                tv.setText(data.getTitle());
                VolleyImageLoader.getInstance(context).loadImage(iv, "http://img14.360buyimg.com/dapei/" + data.getMainlogo());
            }
        }
    }

}
