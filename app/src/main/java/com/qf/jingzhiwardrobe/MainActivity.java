package com.qf.jingzhiwardrobe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.qf.fragment.CircleFragment;
import com.qf.fragment.HomeFragment;
import com.qf.fragment.MineFragment;
import com.qf.fragment.StyleFragment;
import com.qf.fragment.WardrobeFragment;

public class MainActivity extends FragmentActivity {
    private HomeFragment fragment = null;
    private FragmentManager manager;
    private FragmentTransaction action;
    private RadioGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RadioGroup) findViewById(R.id.rg_group);
        loadData();
    }
    /*
    *通过隐藏fragment的方法来进行对个fragment的切换
    *
    *
    */
    private void loadData() {
        fragment = new HomeFragment();
        manager = getSupportFragmentManager();
        layout.getChildAt(0).performClick();
        action = manager.beginTransaction();
        action.add(R.id.fragment1, fragment, "fragment1");
        action.commit();
        layout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            Fragment lastFragment = fragment;
            RadioButton lastView = (RadioButton) findViewById(R.id.rb_button1);
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton current = (RadioButton) findViewById(checkedId);
                lastView = current;
                manager.beginTransaction().hide(lastFragment).commit();
                switch (checkedId) {
                    case R.id.rb_button1:
                        Fragment fragment = manager.findFragmentByTag("fragment1");
                        manager.beginTransaction().show(fragment).commit();
                        lastFragment = fragment;
                        break;
                    case R.id.rb_button2:
                        Fragment fragment2 = manager.findFragmentByTag("fragment2");
                        if (fragment2 == null) {
                            fragment2 = new StyleFragment();
                            manager.beginTransaction().add(R.id.fragment1, fragment2, "fragment2").commit();
                        } else {
                            manager.beginTransaction().show(fragment2).commit();
                        }
                        lastFragment = fragment2;
                        break;
                    case R.id.rb_button3:
                        Fragment fragment3 = manager.findFragmentByTag("fragment3");
                        if (fragment3 == null) {
                            fragment3 = new CircleFragment();
                            manager.beginTransaction().add(R.id.fragment1, fragment3, "fragment3").commit();
                        } else {
                            manager.beginTransaction().show(fragment3).commit();
                        }
                        lastFragment = fragment3;
                        break;
                    case R.id.rb_button4:
                        Fragment fragment4 = manager.findFragmentByTag("fragment4");
                        if (fragment4 == null) {
                            fragment4 = new WardrobeFragment();
                            manager.beginTransaction().add(R.id.fragment1, fragment4, "fragment3").commit();
                        } else {
                            manager.beginTransaction().show(fragment4).commit();
                        }
                        lastFragment = fragment4;
                        break;
                    case R.id.rb_button5:
                        Fragment fragment5 = manager.findFragmentByTag("fragment5");
                        if (fragment5 == null) {
                            fragment5 = new MineFragment();
                            manager.beginTransaction().add(R.id.fragment1, fragment5, "fragment3").commit();
                        } else {
                            manager.beginTransaction().show(fragment5).commit();
                        }
                        lastFragment = fragment5;
                        break;

                }
            }
        });
    }
}