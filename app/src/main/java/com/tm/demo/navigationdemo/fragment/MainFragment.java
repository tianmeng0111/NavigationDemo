package com.tm.demo.navigationdemo.fragment;

import android.os.Bundle;
import android.view.View;

import com.tm.demo.navigationdemo.NavigationJumpActivity;
import com.tm.demo.navigationdemo.R;

import androidx.navigation.Navigation;

public class MainFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initEvent(View view) {
       if (getActivity() instanceof NavigationJumpActivity) {
           View btnNextFragment = view.findViewById(R.id.btn_next_fragment);
           btnNextFragment.setVisibility(View.VISIBLE);
           btnNextFragment.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   //bundle传参方式
                   Bundle bundle = new Bundle();
                   bundle.putString("name1", "通过bundle传递参数");
                   Navigation.findNavController(v).navigate(R.id.action_one_to_two, bundle);
               }
           });
       }
    }
}
