package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import com.example.a49944.myapp.R;

/**
 * Created by summer_h on 2019/5/3 21:17
 */
public class ScheduleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_grade);
    }

    private void initData() {

    }


}
