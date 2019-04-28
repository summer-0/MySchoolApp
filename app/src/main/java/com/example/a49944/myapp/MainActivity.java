package com.example.a49944.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a49944.myapp.ui.fragment.FragmentManagerWrapper;
import com.example.a49944.myapp.ui.fragment.LoginFragment;
import com.example.a49944.myapp.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_content, FragmentManagerWrapper.getInstance().createFragment(LoginFragment.class)).commitAllowingStateLoss();
    }
    /**
     * 从登录界面切换至主界面
     */
    public void changeToHomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, FragmentManagerWrapper.getInstance().createFragment(MainFragment.class)).commitAllowingStateLoss();
    }

}
