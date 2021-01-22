package com.tm.demo.navigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class NavigationJumpActivity extends AppCompatActivity {

    private static final String TAG = "NavigationJumpActivity";

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_jump);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_nav_jump);
        navController = Navigation.findNavController(fragment.getView());
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.e(TAG, "onSupportNavigateUp: ----------------");
        return navController.navigateUp();
    }
}
