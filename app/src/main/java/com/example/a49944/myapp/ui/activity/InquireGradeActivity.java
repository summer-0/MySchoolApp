package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.InquireGradeAdapter;
import com.example.a49944.myapp.net.grade.GradeBeanEvent;
import com.example.a49944.myapp.net.grade.InquireGradeYearEvent;
import com.example.a49944.myapp.net.hhnet.ApiService;
import com.example.a49944.myapp.net.hhnet.Configuration;
import com.example.a49944.myapp.net.hhnet.NetClient;
import com.example.a49944.myapp.net.hhnet.bean.GradeBean;
import com.example.a49944.myapp.ui.fragment.MDialogFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer_h on 2019/5/3 21:17
 */
public class InquireGradeActivity extends AppCompatActivity implements View.OnClickListener, InquireGradeAdapter.OnQuireGradeListener {
    private RecyclerView mGradeRecyclerView;
    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFabCalc, mFabInquire;
    private List<GradeBean.RowsBean> rows;
    private InquireGradeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        initView();
        initData();
        initRecyclerView();
    }

    private void initView() {
        mFabMenu = findViewById(R.id.fab_menu);
//        mFabCalc = findViewById(R.id.fab_calc);
        mFabInquire = findViewById(R.id.fab_inquire);
        mGradeRecyclerView = findViewById(R.id.recycler_grade);
    }

    private void initData() {
        rows = new ArrayList<>();
//        mFabCalc.setOnClickListener(this);
        mFabInquire.setOnClickListener(this);
        //添加一个按钮
//        ContextThemeWrapper context = new ContextThemeWrapper(this, R.style.MenuButtonsStyle);
//        FloatingActionButton programFab2 = new FloatingActionButton(context);
//        programFab2.setLabelText("Programmatically added button");
//        programFab2.setImageResource(R.mipmap.ic_edit);
//        mFabMenu.addMenuButton(programFab2);
        //菜单开关监听
//        mFabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
//            @Override
//            public void onMenuToggle(boolean opened) {
//                String text;
//                if (opened) {
//                    text = "Menu opened";
//                } else {
//                    text = "Menu closed";
//                }
//                Toast.makeText(InquireGradeActivity.this, text, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_inquire:
//                Toast.makeText(this, "成绩查询", Toast.LENGTH_SHORT).show();
                confirmFireMissiles();
                mFabMenu.close(true);
                break;
//            case R.id.fab_calc:
//                Toast.makeText(this, "绩点计算", Toast.LENGTH_SHORT).show();
//                mFabMenu.close(true);
//                break;
            default:
                break;
        }
    }

    private void initRecyclerView() {
        mAdapter = new InquireGradeAdapter(InquireGradeActivity.this, rows);
        mGradeRecyclerView.setLayoutManager(new LinearLayoutManager(InquireGradeActivity.this));
        mGradeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnQuireGradeListener(InquireGradeActivity.this);
        mGradeRecyclerView.setAdapter(mAdapter);

    }


    /**
     * 请求成绩
     */
    private void inQuireGrade() {
        String date = InquireGradeYearEvent.getDate();
        String[] splitYear = date.split("-");
        String dataYears = splitYear[0] + "0" + splitYear[2];
        ApiService apiService = NetClient.getApiService();
        Call<GradeBean> gradeBeanCall = apiService.inquireGrade(dataYears);
        gradeBeanCall.enqueue(new Callback<GradeBean>() {
            @Override
            public void onResponse(Call<GradeBean> call, Response<GradeBean> response) {
                if (response.body().getTotal() != Configuration.TOTAL_NULL) {
                    rows.clear();
                    rows.addAll(response.body().getRows());
                    mAdapter.notifyDataSetChanged();

                } else if (response.body().getTotal() == Configuration.TOTAL_NULL) {
                    Toast.makeText(InquireGradeActivity.this, "暂时没有查询到该学期成绩哦～", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GradeBean> call, Throwable t) {
                Toast.makeText(InquireGradeActivity.this, "查询错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void confirmFireMissiles() {
        MDialogFragment mDialogFragment = new MDialogFragment();
        mDialogFragment.setOnClickQueryGradeListenser(new MDialogFragment.OnClickQueryGradeListenser() {
            @Override
            public void onClick() {
                inQuireGrade();
            }
        });
        mDialogFragment.show(getSupportFragmentManager(), "missiles");

    }

    @Override
    public void onBackPressed() {
        if (mFabMenu.isOpened()) {
            mFabMenu.close(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClickWhenQuireGrade(View view, int position) {
        GradeBeanEvent gradeBeanEvent = new GradeBeanEvent();
        gradeBeanEvent.setGradeBean(rows.get(position));
        // TODO: 2019/5/10 查看成绩详情
        // Intent intent = new Intent(InquireGradeActivity.this, SelectClassNameActivity.class);
        // startActivity(intent);
    }
}
