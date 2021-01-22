package com.tm.demo.navigationdemo.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

/**
 * 重写NavHostFragment，让fragment 不重写实例化
 */
public class FixNavHostFragment extends NavHostFragment {

    @NonNull
    @Override
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        return new FixFragmentNavigator(requireContext(), getChildFragmentManager(), getId());
    }
}
