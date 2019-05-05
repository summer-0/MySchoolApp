package com.example.a49944.myapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.a49944.myapp.ui.activity.*;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.utils.LogUtils;

public class CampusFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = CampusFragment.class.getName();
    private ImageView mKNTrace,mCall, mInform, mGrade, mSchedule, mExam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_campus, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        // mBtnTest = view.findViewById(R.id.btn_test);
        mKNTrace = view.findViewById(R.id.iv_kntrace);
        mCall = view.findViewById(R.id.iv_call);
        mInform = view.findViewById(R.id.iv_inform);
        mGrade = view.findViewById(R.id.iv_grade);
        mSchedule = view.findViewById(R.id.iv_schedule);
        mExam = view.findViewById(R.id.iv_exam);
    }

    private void initData() {
        mKNTrace.setOnClickListener(this);
        mCall.setOnClickListener(this);
        mInform.setOnClickListener(this);
        mGrade.setOnClickListener(this);
        mSchedule.setOnClickListener(this);
        mExam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_kntrace:
                Intent intent = new Intent(getContext(), KNTraceActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_call:
                Intent intentCall = new Intent(getContext(), SchoolCallActivity.class);
                startActivity(intentCall);
                break;
            case R.id.iv_inform:
                Intent intentInform = new Intent(getContext(), InformActivity.class);
                startActivity(intentInform);
                break;
            case R.id.iv_grade:
                //成绩查询
                Intent intentGrade = new Intent(getContext(), InquireGradeActivity.class);
                startActivity(intentGrade);
                break;
            case R.id.iv_schedule:
                //课表查询
                Intent intentSchedule = new Intent(getContext(), ScheduleActivity.class);
                startActivity(intentSchedule);
                break;
            case R.id.iv_exam:
                //课表查询
                Intent intentExam = new Intent(getContext(), ExamActivity.class);
                startActivity(intentExam);
                break;
            default:
                break;
        }
    }
}
