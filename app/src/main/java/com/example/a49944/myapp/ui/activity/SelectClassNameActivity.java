package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a49944.myapp.R;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-5-4
 * Description：展示每一门课程具体的课程信息
 */
public class SelectClassNameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tearmYear;
    private TextView stuNum;
    private TextView classNum;
    private TextView className;
    private TextView countGrade;
    private TextView gpa;
    private TextView countTime;
    private TextView countLearnGrade;
    private TextView classDiv;
    private TextView classWhitch;
    private TextView classWay;
    private TextView classRoot;
    private TextView classRank;
    private ImageView btnCancle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class_name_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    public void initView() {
        btnCancle = findViewById(R.id.btn_cancel_2);
        btnCancle.setOnClickListener(SelectClassNameActivity.this);
        tearmYear = findViewById(R.id.tearm_year);
        stuNum = findViewById(R.id.stu_num);
        classNum = findViewById(R.id.class_num_);
        className = findViewById(R.id.class_called);
        countGrade = findViewById(R.id.count_grade);
        gpa = findViewById(R.id._gpa);
        countTime = findViewById(R.id.count_time);
        countLearnGrade = findViewById(R.id.count_learn_grade);
        classDiv = findViewById(R.id.class_div);
        classWhitch = findViewById(R.id.class_whitch);
        classWay = findViewById(R.id.class_way);
        classRoot = findViewById(R.id.class_root);
        classRank = findViewById(R.id.class_rank);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_2:
                finish();
                break;
            default:
                break;
        }
    }
}
