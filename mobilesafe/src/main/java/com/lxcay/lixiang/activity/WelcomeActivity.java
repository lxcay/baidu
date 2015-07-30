package com.lxcay.lixiang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.Window;

/**
 * Created by lixiang on 2015/4/21.
 */
public class WelcomeActivity extends FragmentActivity {

    private Fragment loginSetting, loginPage;
    private GestureDetector mGesture = null;

    private boolean onLogin = true;

    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

    }
}