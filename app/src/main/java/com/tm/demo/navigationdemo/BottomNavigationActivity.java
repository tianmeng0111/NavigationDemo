package com.tm.demo.navigationdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tm.demo.navigationdemo.fragment.FixFragmentNavigator;
import com.tm.demo.navigationdemo.fragment.MainFragment;
import com.tm.demo.navigationdemo.fragment.MessageFragment;
import com.tm.demo.navigationdemo.fragment.MyFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.NavigationUI;

/**
 * BottomNavigationView 配合Navigation切换fragment
 */
public class BottomNavigationActivity extends AppCompatActivity {

    private static final String TAG = "BottomNavigationActivity";

    private static final String [] TITLE = {
            "Main",
            "Message",
            "My"
    };

    TabLayout tabLayout;

    FragmentManager fragmentManager;
    Fragment fragment;

    NavController navController;
    NavGraph navGraph;

    //用于tablayout切换fragment
    NavOptions options;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        // 获取作为Navigation的fragment
        fragment = fragmentManager.findFragmentById(R.id.fragment_nav_main_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.inflateMenu(R.menu.menu_fragments);

        //获取导航控制器 多种获得controller方式
//        navController = NavHostFragment.findNavController(fragment);//本质上同下
        navController = Navigation.findNavController(fragment.getView());

//        navController = Navigation.findNavController(this, R.id.fragment_nav_main_activity);

        /**
         * 创建自定义的Fragment导航器
         * 这里一定要手动添加，否则会重新实例化fragment
         */
        FixFragmentNavigator fragmentNavigator =
                new FixFragmentNavigator(this, fragment.getChildFragmentManager(), fragment.getId());
        //获取导航器提供者
        NavigatorProvider provider = navController.getNavigatorProvider();
        //把自定义的Fragment导航器添加进去
        provider.addNavigator(fragmentNavigator);
        //手动创建导航图
        navGraph = initNavGraph(provider, fragmentNavigator);
        //设置导航图
        navController.setGraph(navGraph);

        //'com.google.android.material:material'下的bottomNavigationView 才能联动
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim);
        options = builder.build();

        /**
         * tabLayout
         */
        tabLayout = findViewById(R.id.tab_layout_main_activity);
        tabLayout.addTab(tabLayout.newTab(), true);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        for(int i = 0; i < TITLE.length; i++){
            tabLayout.getTabAt(i).setText(TITLE[i]);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if (navController.getCurrentDestination().getId() == R.id.fragment_main) {
                            return;
                        }
                        try {
                            navController.navigate(R.id.fragment_main, null, options);
                        } catch (IllegalArgumentException e) {
                            Log.e(TAG, "onTabSelected: " + e.getMessage());
                        }
                        break;
                    case 1:
                        if (navController.getCurrentDestination().getId() == R.id.fragment_message) {
                            return;
                        }
                        try {
                            navController.navigate(R.id.fragment_message, null, options);
                        } catch (IllegalArgumentException e) {
                            Log.e(TAG, "onTabSelected: " + e.getMessage());
                        }
                        break;
                    case 2:
                        if (navController.getCurrentDestination().getId() == R.id.fragment_my) {
                            return;
                        }
                        try {
                            navController.navigate(R.id.fragment_my, null, options);
                        } catch (IllegalArgumentException e) {
                            Log.e(TAG, "onTabSelected: " + e.getMessage());
                        }
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.e(TAG, "onNavigationItemSelected: " + menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case R.id.fragment_main:
                        tabLayout.getTabAt(0).select();
                        break;
                    case R.id.fragment_message:
                        tabLayout.getTabAt(1).select();
                        break;
                    case R.id.fragment_my:
                        tabLayout.getTabAt(2).select();
                        break;
                }
                return false;
            }
        });
    }

    //手动创建导航图，把3个目的地添加进来
    private static NavGraph initNavGraph(NavigatorProvider provider, FixFragmentNavigator fragmentNavigator) {
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        //用自定义的导航器来创建目的地
        FragmentNavigator.Destination destination1 = fragmentNavigator.createDestination();
        destination1.setId(R.id.fragment_main);
        destination1.setClassName(MainFragment.class.getCanonicalName());
        destination1.setLabel(MainFragment.class.getSimpleName());
        navGraph.addDestination(destination1);

        FragmentNavigator.Destination destination2 = fragmentNavigator.createDestination();
        destination2.setId(R.id.fragment_message);
        destination2.setClassName(MessageFragment.class.getCanonicalName());
        destination2.setLabel(MessageFragment.class.getSimpleName());
        navGraph.addDestination(destination2);

        FragmentNavigator.Destination destination3 = fragmentNavigator.createDestination();
        destination3.setId(R.id.fragment_my);
        destination3.setClassName(MyFragment.class.getCanonicalName());
        destination3.setLabel(MyFragment.class.getSimpleName());
        navGraph.addDestination(destination3);

        navGraph.setStartDestination(R.id.fragment_main);

        return navGraph;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_go_drawer_navigation_activity:
                startActivity(new Intent(this, NavigationViewActivity.class));
                break;
            case R.id.menu_go_jump_activity:
                startActivity(new Intent(this, NavigationJumpActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
