package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.CampusCallRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by summer_h on 2019/4/29 21:35
 */
public class SchoolCallActivity extends AppCompatActivity {
    private List<String> mListName, mListNum;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_call);
    }

    private void initData() {
        String[] array = getResources().getStringArray(R.array.campus_call_name);
        String[] array1 = getResources().getStringArray(R.array.campus_call_num);
        mListName = Arrays.asList(array);
        mListNum = Arrays.asList(array1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CampusCallRecyclerAdapter(this,mListName, mListNum ));
    }
}
