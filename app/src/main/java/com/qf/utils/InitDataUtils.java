package com.qf.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.qf.jingzhiwardrobe.R;

/**
 * Created by Administrator on 15-11-25.
 */
public class InitDataUtils {

    private static TextView[] horizontaltv;
    private static TextView[] popupwindowtv;

    public static TextView[] getHorizontalData(View view){

        if (view != null) {

            TextView tv = (TextView) view.findViewById(R.id.tv_1);
            tv.setTextColor(Color.RED);

            horizontaltv = new TextView[]{
                    tv,
                    (TextView) view.findViewById(R.id.tv_2),
                    (TextView) view.findViewById(R.id.tv_3),
                    (TextView) view.findViewById(R.id.tv_4),
                    (TextView) view.findViewById(R.id.tv_5),
                    (TextView) view.findViewById(R.id.tv_6),
                    (TextView) view.findViewById(R.id.tv_7),
                    (TextView) view.findViewById(R.id.tv_8),
                    (TextView) view.findViewById(R.id.tv_9),
                    (TextView) view.findViewById(R.id.tv_10),

            };
        }


        return horizontaltv;

    }

    public static TextView [] getPopupWindowData(View view){
        if (view != null) {
            popupwindowtv = new TextView[]{
                    (TextView) view.findViewById(R.id.tv_pop_1),
                    (TextView) view.findViewById(R.id.tv_pop_2),
                    (TextView) view.findViewById(R.id.tv_pop_3),
                    (TextView) view.findViewById(R.id.tv_pop_4),
                    (TextView) view.findViewById(R.id.tv_pop_5),
                    (TextView) view.findViewById(R.id.tv_pop_6),
                    (TextView) view.findViewById(R.id.tv_pop_7),
                    (TextView) view.findViewById(R.id.tv_pop_8),
                    (TextView) view.findViewById(R.id.tv_pop_9),
                    (TextView) view.findViewById(R.id.tv_pop_10),
            };
        }
        return popupwindowtv;
    }


}
