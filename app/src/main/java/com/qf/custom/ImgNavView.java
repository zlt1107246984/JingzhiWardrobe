package com.qf.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.jingzhiwardrobe.R;


/**
 * 头部导航图标的封装类 ...
 */
public class ImgNavView extends LinearLayout{

    private int count;
    private int checkedimg, uncheckedimg;
    private LayoutParams layoutParams;

    public ImgNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parestAttr(attrs);
        init();
    }

    private void parestAttr(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.navproperties);
        count = typedArray.getInteger(R.styleable.navproperties_count, 0);
        checkedimg = typedArray.getResourceId(R.styleable.navproperties_checkedimg, 0);
        uncheckedimg = typedArray.getResourceId(R.styleable.navproperties_uncheckedimg, 0);
    }

    private void init() {
        this.removeAllViews();
        if(count > 0){
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 2;
            layoutParams.rightMargin = 2;
            for(int i = 0; i < count; i++){
                ImageView iv = new ImageView(getContext());
                if(i == 0){
                    iv.setImageResource(checkedimg);
                    iv.setTag("checked");
                } else {
                    iv.setImageResource(uncheckedimg);
                }
                iv.setLayoutParams(layoutParams);
                this.addView(iv);
            }
        }
    }


    public void setCount(int count){
        this.count = count;
        init();
    }

    public void selectIndex(int index){
        ImageView iv = (ImageView) this.findViewWithTag("checked");
        iv.setImageResource(uncheckedimg);
        iv.setTag(null);

        iv = (ImageView) this.getChildAt(index);
        iv.setImageResource(checkedimg);
        iv.setTag("checked");
    }
}
