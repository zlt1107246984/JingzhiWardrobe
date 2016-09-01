package com.qf.custom;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ListView的下拉刷新封装
 */
public class GridViewPullToFresh extends LinearLayout implements View.OnTouchListener {

    private View headView;//头部控件
    private int headViewHeight;//头部控件的高度

    private StaggeredGridView sg = new StaggeredGridView(getContext());;//显示的内容

    private static final int NONE = 0;//普通状态
    private static final int PULL = 1;//正在下拉
    private static final int PULL_REFRESH = 2;//松手刷新
    private static final int REFRESHING = 3;//刷新中
    private int state = NONE;//控件当前的状态

    //回调接口对象
    private OnRefreshListener onRefresh;
    public GridViewPullToFresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化当前控件
     */
    private void init() {
        this.setOrientation(VERTICAL);//设为纵向排列
    }

    /**
     * 设置头部的方法
     * @param view
     */
    public void setHeadView(View view){
        headView = view;
        initView();
    }

    public void setHeadView(int resId){
        View view = LayoutInflater.from(getContext()).inflate(resId, null);
        setHeadView(view);
    }

    /**
     * 初始化内部控件
     */
    private void initView() {
        //处理下拉的头部
        headView.measure(0, 0);//测量组件的宽高
        headViewHeight = headView.getMeasuredHeight();//获得测量后的宽高
        headView.setPadding(0, -headViewHeight, 0, 0);
        this.addView(headView);


        //处理ListView

        Log.e("Tag2",sg+"");
        sg.setOnTouchListener(this);
        this.addView(sg);
    }


    /**
     * ListView onTouch监听 -- 该方法先与ListView OnTouchEvent方法被调用，如果返回false，则调用OnTouchEvent,反之则不调用
     * @param v
     * @param event
     * @return
     */
    private int by = -1;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean ispull = isPull(event);
        //计算手势在Y轴上的位移
        int y = (int) event.getRawY();
        if(by == -1){
            by = y;
        }
        int movey = y - by;
        by = y;
        if(ispull){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:

                    if(movey < 0 && headView.getPaddingTop() <= -headViewHeight && state == NONE){
                        return false;
                    }

                    headView.setPadding(headView.getPaddingLeft(), headView.getPaddingTop() + movey / 2, headView.getPaddingRight(), headView.getPaddingBottom());
                    //通过偏移量改变头部的图片
                    if(state != REFRESHING) {
                        if(onRefresh != null) {
                            boolean flag = onRefresh.pullMoveY(headView.getPaddingTop() + headViewHeight, headView);//调用接口回调中的方法
                            if(flag){
                                onRefresh.pullRefresh(headView.getPaddingTop() + headViewHeight, headView);
                                state = PULL_REFRESH;//当前状态为松手刷新
                            } else {
                                state = PULL;
                            }
                        }

                        if(headView.getPaddingTop() > -headViewHeight && state == NONE){
                            state = PULL;//已经开拉
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:
                default:
                    //松手执行方法
                    reset();
                    break;
            }

            return true;
        }
        return false;
    }

    /**
     * 重置控件
     */
    int backHeight = -headViewHeight;
    private Handler handler = new Handler();
    private void reset() {
        if(state != NONE){
            by = -1;

            if(state == PULL || state == REFRESHING){//普通下拉
                backHeight = -headViewHeight;
                state = NONE;
            } else if(state == PULL_REFRESH){//松手刷新
                backHeight = 0;
                state = REFRESHING;//状态变为正在刷新
                if(onRefresh != null){
                    //回调刷新方法
                    onRefresh.refreshing(headView);
                }
            }

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(headView.getPaddingTop() > backHeight) {
                                headView.setPadding(headView.getPaddingLeft(), headView.getPaddingTop() - 5,
                                        headView.getPaddingRight(), headView.getPaddingBottom());
                            } else {
                                headView.setPadding(headView.getPaddingLeft(), backHeight,
                                        headView.getPaddingRight(), headView.getPaddingBottom());
                                timer.cancel();
                            }
                        }
                    });
                }
            }, 0, 5);
        }
    }

    /**
     * 判断是否可以下拉
     */
    private int y = -1;
    private boolean isPull(MotionEvent event) {
        if(state == REFRESHING){
            return false;
        }

        if(sg.getChildCount() > 0){
            View view = sg.getChildAt(0);
            if(view.getTop() == 0){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        y = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(y == (int)event.getRawY()){
                            y = -1;
                            return false;
                        }
                        break;
                }

                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 刷新完成
     */
    public void refreshCompelet(){
        reset();
        if (onRefresh != null)
            onRefresh.refreshCompelet(headView);
    }

    /**
     * 内部ListView的操作封装
     * @param adapter
     */
    public void setAdapter(ListAdapter adapter){
        sg.setAdapter(adapter);
    }

    public void addHeader(View view){
        Log.e("Tag",sg+"");
        sg.addHeaderView(view);
    }
    public void addFooter(View view){
        sg.addFooterView(view);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        sg.setOnItemClickListener(onItemClickListener);

    }
    public void setScrollListener(AbsListView.OnScrollListener onScrollListener){
        sg.setOnScrollListener(onScrollListener);
    }
    public StaggeredGridView getLv() {
        return sg;
    }
    public void backTop(int position){

        sg.setSelection(position);
    }

    /**
     * 回调的接口
     */

    public interface OnRefreshListener{
        //下拉偏移量回调方法
        boolean pullMoveY(int movey, View headView);

        //松手刷新时回调方法
        void pullRefresh(int movey, View headView);

        //正在刷新
        void refreshing(View headView);

        //刷新完成
        void refreshCompelet(View headView);
    }

    public void setOnRefresh(OnRefreshListener onRefresh) {
        this.onRefresh = onRefresh;
    }

}
