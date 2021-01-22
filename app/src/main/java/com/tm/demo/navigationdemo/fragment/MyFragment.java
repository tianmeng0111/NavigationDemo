package com.tm.demo.navigationdemo.fragment;

import android.view.View;
import android.widget.TextView;

import com.tm.demo.navigationdemo.R;

public class MyFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(MyFragment.class.getSimpleName());
    }

    @Override
    protected void initEvent(View view) {

    }
}
