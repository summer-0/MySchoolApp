package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.a49944.myapp.R;

public class FeedBackActivity extends AppCompatActivity  {
    private Toolbar mToolbar;
    private Button mBtnSubmit;
    private LinearLayout mLlProblem;
    private TextView mTextSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar_web);
        mBtnSubmit = findViewById(R.id.btn_submit);
        mLlProblem = findViewById(R.id.ll_pro_suggest);
        mTextSubmit = findViewById(R.id.tv_submit_success);
    }

    private void initData() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlProblem.setVisibility(View.GONE);
                mTextSubmit.setVisibility(View.VISIBLE);
            }
        });
    }



}
