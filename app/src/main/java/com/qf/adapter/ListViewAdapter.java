package com.qf.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.qf.jingzhiwardrobe.R;
import com.qf.model.ListData;
import com.qf.utils.Constants;
import com.qf.utils.VolleyImageLoader;

/**
 * Created by Administrator on 15-11-24.
 */
public class ListViewAdapter extends AbsBaseAdapter<ListData> {

    private Context context;

    public ListViewAdapter(Context context) {
        super(context, R.layout.list_data_layout);
        this.context = context;
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, ListData data) {
        NetworkImageView iv = (NetworkImageView) viewHolder.getView(R.id.iv_image);
        NetworkImageView siv = (NetworkImageView) viewHolder.getView(R.id.iv_small_image);
        TextView tv_long = (TextView) viewHolder.getView(R.id.tv_long);
        TextView tv_small = (TextView) viewHolder.getView(R.id.tv_small);
        String imgurl = Constants.URL.HTTP+data.getImageUrl();
        String simgurl = Constants.URL.HTTP+data.getSmallImageUrl();
        VolleyImageLoader.getInstance(context).loadImage(iv,imgurl);
        VolleyImageLoader.getInstance(context).loadImage(siv,simgurl);
        tv_long.setText(data.getDataMsg());
        tv_small.setText(data.getCutMsg());
    }
}
