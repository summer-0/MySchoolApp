package com.example.a49944.myapp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.net.hhnet.Configuration;
import com.example.a49944.myapp.net.hhnet.NetClient;
import com.example.a49944.myapp.net.hhnet.UserManagement;
import com.example.a49944.myapp.net.hhnet.bean.ScheduleBean;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.ui.fragment.MScheduleTermFragment;
import com.example.a49944.myapp.ui.fragment.MScheduleWeekFragment;
import com.example.a49944.myapp.utils.LogUtils;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by summer_h on 2019/5/3 21:17
 */
public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ScheduleActivity.class.getSimpleName();
    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFabTerm, mFabWeek;
    private RecyclerView mRecyclerView;
    private ConfigManager mConfigManager;
    private TextView mTvDate, mTvWeek, mTvTerm;
    private List<ScheduleBean.RowsBean> mSingleClass;
    private TextView mondayOne, mondayTwo, mondayThree, mondayFour, mondayFive, mondaySix;
    private TextView tuesdayOne, tuesdayTwo, tuesdayThree, tuesdayFour, tuesdayFive, tuesdaySix;
    private TextView wednesdayOne, wednesdayTwo, wednesdayThree, wednesdayFour, wednesdayFive, wednesdaySix;
    private TextView thursdayOne, thursdayTwo, thursdayThree, thursdayFour, thursdayFive, thursdaySix;
    private TextView fridayOne, fridayTwo, fridayThree, fridayFour, fridayFive, fridaySix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initView();
        initData();
    }

    private void initView() {
        mFabMenu = findViewById(R.id.fab_menu);
        mFabTerm = findViewById(R.id.fab_term);
        mFabWeek = findViewById(R.id.fab_week);
        mTvDate = findViewById(R.id.tv_date);
        mTvWeek = findViewById(R.id.tv_week);
        mTvTerm = findViewById(R.id.tv_term);
        initTextView();
    }

    private void initTextView() {
        mondayOne = findViewById(R.id.monday_one);
        mondayTwo = findViewById(R.id.monday_two);
        mondayThree = findViewById(R.id.monday_three);
        mondayFour = findViewById(R.id.monday_four);
        mondayFive = findViewById(R.id.monday_five);
        mondaySix = findViewById(R.id.monday_six);
        tuesdayOne = findViewById(R.id.tuesday_one);
        tuesdayTwo = findViewById(R.id.tuesday_two);
        tuesdayThree = findViewById(R.id.tuesday_three);
        tuesdayFour = findViewById(R.id.tuesday_four);
        tuesdayFive = findViewById(R.id.tuesday_five);
        tuesdaySix = findViewById(R.id.tuesday_six);
        wednesdayOne = findViewById(R.id.wednesday_one);
        wednesdayTwo = findViewById(R.id.wednesday_two);
        wednesdayThree = findViewById(R.id.wednesday_three);
        wednesdayFour = findViewById(R.id.wednesday_four);
        wednesdayFive = findViewById(R.id.wednesday_five);
        wednesdaySix = findViewById(R.id.wednesday_six);
        thursdayOne = findViewById(R.id.thursday_one);
        thursdayTwo = findViewById(R.id.thursday_two);
        thursdayThree = findViewById(R.id.thursday_three);
        thursdayFour = findViewById(R.id.thursday_four);
        thursdayFive = findViewById(R.id.thursday_five);
        thursdaySix = findViewById(R.id.thursday_six);
        fridayOne = findViewById(R.id.friday_one);
        fridayTwo = findViewById(R.id.friday_two);
        fridayThree = findViewById(R.id.friday_three);
        fridayFour = findViewById(R.id.friday_four);
        fridayFive = findViewById(R.id.friday_five);
        fridaySix = findViewById(R.id.friday_six);

    }

    private void initData() {
        mSingleClass = new ArrayList<>();
        mConfigManager = ConfigManager.getInstance();
        mFabWeek.setOnClickListener(this);
        mFabTerm.setOnClickListener(this);
        initAllDate();
        initSchedule();
    }

    private void initSchedule() {
        String scheduleTerm = mConfigManager.getScheduleTerm();
        String scheduleWeek = mConfigManager.getScheduleWeek();
        if (scheduleTerm != null && !TextUtils.isEmpty(scheduleTerm)
                && scheduleWeek != null && !TextUtils.isEmpty(scheduleWeek)) {
            List<ScheduleBean.RowsBean> scheduleList = mConfigManager.getScheduleList(scheduleTerm);
            if (scheduleList != null && scheduleList.size() > 0) {
                mSingleClass.clear();
                mSingleClass = scheduleList;
                setWeekSchedule(scheduleWeek, scheduleTerm);
            }
        }

    }

    /**
     * 显示日期
     */
    private void initAllDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        mTvDate.setText(format.substring(5));
        String scheduleWeek = mConfigManager.getScheduleWeek();
        String scheduleTerm = mConfigManager.getScheduleTerm();
        if (scheduleWeek != null && !TextUtils.isEmpty(scheduleWeek)) {
            mTvWeek.setText("第" + scheduleWeek + "周");
        }
        if (scheduleTerm != null && !TextUtils.isEmpty(scheduleTerm)) {
            mTvTerm.setText(scheduleTerm);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_term:
                //选择学期
                confirmFireMissiles();
                mFabMenu.close(true);
                break;
            case R.id.fab_week:
                //选择周数
                confirmFireMissilesWeek();
                mFabMenu.close(true);
                break;
            default:
                break;
        }
    }

    /**
     * 选择周数
     */
    private void confirmFireMissilesWeek() {
        MScheduleWeekFragment mScheduleWeekFragment = new MScheduleWeekFragment();
        mScheduleWeekFragment.setOnClickQueryScheduleListenser(new MScheduleWeekFragment.OnClickQueryScheduleWeekListenser() {
            @Override
            public void onClick() {
                //查询课表（通过周数查询）
                String scheduleWeek = mConfigManager.getScheduleWeek();
                String scheduleTerm = mConfigManager.getScheduleTerm();
                List<ScheduleBean.RowsBean> scheduleList = mConfigManager.getScheduleList(scheduleTerm);
                if (scheduleTerm != null && !TextUtils.isEmpty(scheduleTerm) && scheduleWeek != null && !TextUtils.isEmpty(scheduleWeek)) {
                    if (scheduleList != null && scheduleList.size() > 0) {
                        setWeekSchedule(scheduleWeek, scheduleTerm);
                    } else if (UserManagement.isIsLogin()) {
                        String[] strSplits = scheduleTerm.split("-");
                        String yearMonths = strSplits[0] + "0" + strSplits[2];  //201701
                        inquireSchedule(yearMonths, scheduleWeek, scheduleTerm);
                    }

                }

            }
        });
        mScheduleWeekFragment.show(getSupportFragmentManager(), "missiles");
    }

    private void confirmFireMissiles() {
        MScheduleTermFragment mScheduleTermFragment = new MScheduleTermFragment();
        //设置“查询按钮点击监听”
        mScheduleTermFragment.setOnClickQueryScheduleListenser(new MScheduleTermFragment.OnClickQueryScheduleListenser() {
            @Override
            public void onClick() {
                //查询课表
                String scheduleTerm = mConfigManager.getScheduleTerm();  // 2017-2018-1
                List<ScheduleBean.RowsBean> scheduleList = mConfigManager.getScheduleList(scheduleTerm);
                if (scheduleTerm != null && !TextUtils.isEmpty(scheduleTerm)) {
                    // TODO: 2019/5/11 后续需要根据不同的学号获取缓存的课表
                    if (scheduleList != null && scheduleList.size() > 0) {
                        mSingleClass = scheduleList;
                        setWeekSchedule("1", scheduleTerm);
                    } else if (UserManagement.isIsLogin()) { //这里请求的判断需要严谨
                        String[] strSplits = scheduleTerm.split("-");
                        String yearMonths = strSplits[0] + "0" + strSplits[2];  //201701
                        //查询本学期的所有课表
                        inquireSchedule(yearMonths, "", scheduleTerm);
                        mConfigManager.setScheduleWeek("1");
                    }
                }

            }
        });
        mScheduleTermFragment.show(getSupportFragmentManager(), "missiles");
    }


    /**
     * 查询课表
     *
     * @param yearMonth
     * @param week
     * @param scheduleTerm
     */
    private void inquireSchedule(String yearMonth, String week, String scheduleTerm) {
        Call<ScheduleBean> scheduleBeanCall = NetClient.getApiService().inquireSchedule(yearMonth, week);
        scheduleBeanCall.enqueue(new Callback<ScheduleBean>() {
            @Override
            public void onResponse(Call<ScheduleBean> call, Response<ScheduleBean> response) {
                if (response != null && response.code() == Configuration.OK_CODE) {
                    LogUtils.i(TAG, "课表查询成功！");
                    LogUtils.i(TAG, " 总数： " + response.body().getTotal());
                    if (response.body().getTotal() > 0) {
                        mSingleClass.clear();
                        mSingleClass = response.body().getRows();
                        setWeekSchedule("1", scheduleTerm);
                        mConfigManager.setScheduleList(mSingleClass, scheduleTerm);
                    }
                }
            }

            @Override
            public void onFailure(Call<ScheduleBean> call, Throwable t) {
                LogUtils.i(TAG, "课表查询失败！");
            }
        });

    }

    /**
     * 设置课表
     *
     * @param week         顶部的星期显示
     * @param scheduleTerm 顶部的学期显示
     */
    private void setWeekSchedule(String week, String scheduleTerm) { //201701
        mTvWeek.setText("第" + week + "周");
        mTvTerm.setText(scheduleTerm);
        clearTVBackGrandAndText();
        for (int i = 0; i < mSingleClass.size(); i++) {
            if (mSingleClass.get(i).getZc().equals(week)) {
                switch (mSingleClass.get(i).getXq()) {
                    case "1":
                        switch (mSingleClass.get(i).getJcdm()) {
                            case "0102":
                                mondayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayOne.setBackgroundColor(getResources().getColor(R.color.c_light));
                                break;
                            case "0304":
                                mondayTwo.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayTwo.setBackgroundColor(getResources().getColor(R.color.c_grap));
                                break;
                            case "01020304":
                                mondayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayOne.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                mondayTwo.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            case "0506":
                                mondayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayThree.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "0708":
                                mondayFour.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayFour.setBackgroundColor(getResources().getColor(R.color.c_red));
                                break;
                            case "05060708":
                                mondayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayThree.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                mondayFour.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            case "0910":
                                mondayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayFive.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "1112":
                                mondaySix.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondaySix.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                break;
                            case "09101112":
                                mondayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                mondayFive.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                mondaySix.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "2":
                        switch (mSingleClass.get(i).getJcdm()) {
                            case "0102":
                                tuesdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayOne.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "0304":
                                tuesdayTwo.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayTwo.setBackgroundColor(getResources().getColor(R.color.orange));
                                break;
                            case "01020304":
                                tuesdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayOne.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                tuesdayTwo.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "0506":
                                tuesdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayThree.setBackgroundColor(getResources().getColor(R.color.c_light));
                                break;
                            case "0708":
                                tuesdayFour.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayFour.setBackgroundColor(getResources().getColor(R.color.c_red));
                                break;
                            case "05060708":
                                tuesdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayThree.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                tuesdayFour.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "0910":
                                tuesdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayFive.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            case "1112":
                                tuesdaySix.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdaySix.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                break;
                            case "09101112":
                                tuesdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                tuesdayFive.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                tuesdaySix.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "3":
                        switch (mSingleClass.get(i).getJcdm()) {
                            case "0102":
                                wednesdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayOne.setBackgroundColor(getResources().getColor(R.color.progress_end_color));
                                break;
                            case "0304":
                                wednesdayTwo.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayTwo.setBackgroundColor(getResources().getColor(R.color._939da4));
                                break;
                            case "01020304":
                                wednesdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayOne.setBackgroundColor(getResources().getColor(R.color.c_light));
                                wednesdayTwo.setBackgroundColor(getResources().getColor(R.color.c_light));
                                break;
                            case "0506":
                                wednesdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayThree.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "0708":
                                wednesdayFour.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayFour.setBackgroundColor(getResources().getColor(R.color.loading_web_bg_color));
                                break;
                            case "05060708":
                                wednesdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayThree.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                wednesdayFour.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "0910":
                                wednesdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayFive.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                break;
                            case "1112":
                                wednesdaySix.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdaySix.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                break;
                            case "09101112":
                                wednesdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                wednesdayFive.setBackgroundColor(getResources().getColor(R.color.c_grap));
                                wednesdaySix.setBackgroundColor(getResources().getColor(R.color.c_grap));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "4":
                        switch (mSingleClass.get(i).getJcdm()) {
                            case "0102":
                                thursdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayOne.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "0304":
                                thursdayTwo.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayTwo.setBackgroundColor(getResources().getColor(R.color.c_red));
                                break;
                            case "01020304":
                                thursdayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayOne.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                thursdayTwo.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "0506":
                                thursdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayThree.setBackgroundColor(getResources().getColor(R.color.c_grap));
                                break;
                            case "0708":
                                thursdayFour.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayFour.setBackgroundColor(getResources().getColor(R.color.c_light));
                                break;
                            case "05060708":
                                thursdayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayThree.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                thursdayFour.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            case "0910":
                                thursdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayFive.setBackgroundColor(getResources().getColor(R.color.progress_start_color));
                                break;
                            case "1112":
                                thursdaySix.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdaySix.setBackgroundColor(getResources().getColor(R.color.orange));
                                break;
                            case "09101112":
                                thursdayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                thursdayFive.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                thursdaySix.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "5":
                        switch (mSingleClass.get(i).getJcdm()) {
                            case "0102":
                                fridayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayOne.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "0304":
                                fridayTwo.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayTwo.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "01020304":
                                fridayOne.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayOne.setBackgroundColor(getResources().getColor(R.color.tabSelectorTextColorChecked));
                                fridayTwo.setBackgroundColor(getResources().getColor(R.color.tabSelectorTextColorChecked));
                                break;
                            case "0506":
                                fridayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayThree.setBackgroundColor(getResources().getColor(R.color.inbox_purple));
                                break;
                            case "0708":
                                fridayFour.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayFour.setBackgroundColor(getResources().getColor(R.color.inbox_primary));
                                break;
                            case "05060708":
                                fridayThree.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayThree.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                fridayFour.setBackgroundColor(getResources().getColor(R.color.c_pink));
                                break;
                            case "0910":
                                fridayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayFive.setBackgroundColor(getResources().getColor(R.color.C_origin));
                                break;
                            case "1112":
                                fridaySix.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridaySix.setBackgroundColor(getResources().getColor(R.color.c_light));
                                break;
                            case "09101112":
                                fridayFive.setText(mSingleClass.get(i).getKcmc() + " " + mSingleClass.get(i).getJxcdmc());
                                fridayFive.setBackgroundColor(getResources().getColor(R.color.custom_gray));
                                fridaySix.setBackgroundColor(getResources().getColor(R.color.custom_gray));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * 清除背景色和文字
     */
    private void clearTVBackGrandAndText() {
        mondayOne.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondayTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondayThree.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondayFour.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondayFive.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondaySix.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdayOne.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdayTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdayThree.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdayFour.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdayFive.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tuesdaySix.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdayOne.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdayTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdayThree.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdayFour.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdayFive.setBackgroundColor(Color.argb(0, 0, 0, 0));
        wednesdaySix.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdayOne.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdayTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdayThree.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdayFour.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdayFive.setBackgroundColor(Color.argb(0, 0, 0, 0));
        thursdaySix.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridayOne.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridayTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridayThree.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridayFour.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridayFive.setBackgroundColor(Color.argb(0, 0, 0, 0));
        fridaySix.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mondayOne.setText("");
        mondayTwo.setText("");
        mondayThree.setText("");
        mondayFour.setText("");
        mondayFive.setText("");
        mondaySix.setText("");
        tuesdayOne.setText("");
        tuesdayTwo.setText("");
        tuesdayThree.setText("");
        tuesdayFour.setText("");
        tuesdayFive.setText("");
        tuesdaySix.setText("");
        wednesdayOne.setText("");
        wednesdayTwo.setText("");
        wednesdayThree.setText("");
        wednesdayFour.setText("");
        wednesdayFive.setText("");
        wednesdaySix.setText("");
        thursdayOne.setText("");
        thursdayTwo.setText("");
        thursdayThree.setText("");
        thursdayFour.setText("");
        thursdayFive.setText("");
        thursdaySix.setText("");
        fridayOne.setText("");
        fridayTwo.setText("");
        fridayThree.setText("");
        fridayFour.setText("");
        fridayFive.setText("");
        fridaySix.setText("");
    }
}
