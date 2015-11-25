package com.qf.adapter;

import android.content.Context;

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
        String imgurl = Constants.URL.HTTP+data.getImageUrl();
        VolleyImageLoader.getInstance(context).loadImage(iv,imgurl);
    }
}
