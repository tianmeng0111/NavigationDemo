package com.tm.demo.navigationdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tm.demo.navigationdemo.NavigationJumpActivity;
import com.tm.demo.navigationdemo.R;

import androidx.navigation.Navigation;

public class MessageFragment extends BaseFragment {

    private String name;
    private String name1;

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        name1 = bundle.getString("name1");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(MessageFragment.class.getSimpleName());
        EditText et = view.findViewById(R.id.edit_content);
        et.setText(name1);
    }

    @Override
    protected void initEvent(View view) {
        if (getActivity() instanceof NavigationJumpActivity) {
            View btnNextFragment = view.findViewById(R.id.btn_next_fragment);
            btnNextFragment.setVisibility(View.VISIBLE);
            btnNextFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_two_to_three);
                }
            });
        }
    }
}
