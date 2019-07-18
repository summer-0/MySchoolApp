package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.ExamRecyclerAdapter;
import com.example.a49944.myapp.bean.exam.ExamBean;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.net.hhnet.Configuration;
import com.example.a49944.myapp.net.hhnet.NetClient;
import com.example.a49944.myapp.net.hhnet.UserManagement;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.ui.fragment.MDialogFragment;
import com.example.a49944.myapp.ui.fragment.MExamTermFragment;
import com.example.a49944.myapp.ui.fragment.MScheduleTermFragment;
import com.example.a49944.myapp.utils.LogUtils;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer_h on 2019/5/3 21:17
 */
public class ExamActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ExamActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFabTerm;
    private ConfigManager mConfigManager;
    private List<ExamBean.RowsBean> mExamList;
    private ExamRecyclerAdapter mAdapter;

    //固定数据
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_exam);
        mFabMenu = findViewById(R.id.fab_menu);
        mFabTerm = findViewById(R.id.fab_term);
    }

    private void initData() {
        mExamList = new ArrayList<>();
        mList = new ArrayList<>();
        mConfigManager = ConfigManager.getInstance();
        mFabTerm.setOnClickListener(this);
        mAdapter = new ExamRecyclerAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_term:
                //选择学期
                confirmFireMissiles();
                mFabMenu.close(true);
                break;
            default:
                break;
        }
    }

    /**
     * 选择学期
     */
    private void confirmFireMissiles() {
        MExamTermFragment mExamTermFragment = new MExamTermFragment();
        mExamTermFragment.setOnClickQueryExamListenser(new MExamTermFragment.OnClickQueryExamListenser() {
            @Override
            public void onClick() {
                //查询考试安排
                String scheduleTerm = mConfigManager.getExamTerm();
                if (scheduleTerm != null && !TextUtils.isEmpty(scheduleTerm)) {
                    if (UserManagement.isIsLogin()) { //这里请求的判断需要严谨
                        String[] strSplits = scheduleTerm.split("-");
                        String yearMonths = strSplits[0] + "0" + strSplits[2];  //201701
                        //查询本学期的所有考试安排
                        // TODO: 2019/5/12  参数
                        //inquireExam(yearMonths);
                        inquireExamTest(yearMonths);
                    }
                }
            }
        });

        mExamTermFragment.show(getSupportFragmentManager(), "missiles");
    }

    private void inquireExamTest(String yearMonths) {
        switch (yearMonths){
            case "201701":
                String str1 = "数据库原理 2017-11-26 14:30--16:30 综合实验大楼308";
                String str2 = "通信原理 2018-01-02 08:00--09:30 北主楼305";
                String str3 = "毛泽东思想和中国特色社会主义理论体系概论(1) 2018-01-09 16:40--18:10 北主楼203";
                String str4 = "计算机网络原理 2018-01-15 08:00--10:00 北主楼301";
                String str5 = "操作系统 2018-01-18 14:30--16:30 北主楼204";
                mList.clear();
                mList.add(str1);
                mList.add(str2);
                mList.add(str3);
                mList.add(str4);
                mList.add(str5);
                mAdapter.refreshAdapter(mList);
                mAdapter.notifyDataSetChanged();
                break;
            case "201702":
                //2017-2018-2
                String str6 = "毛泽东思想和中国特色社会主义理论体系概论(2) 2018-06-10 09:00--10:30 北主楼201";
                String str7 = "电子商务 2018-07-10 10:10--11:40 北主楼1102";
                String str8 = "微机系统与接口技术 2018-07-12 08:30--10:00 北主楼905";
                mList.clear();
                mList.add(str6);
                mList.add(str7);
                mList.add(str8);
                mAdapter.refreshAdapter(mList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 根据学年来查考试安排
     *
     * @param yearMonth 201701
     */
    private void inquireExam(String yearMonth) {
        Call<ExamBean> examCall = NetClient.getApiService().inquireExam(yearMonth);
        examCall.enqueue(new Callback<ExamBean>() {
            @Override
            public void onResponse(Call<ExamBean> call, Response<ExamBean> response) {
                if (response.code() == Configuration.OK_CODE) {
                    LogUtils.i(TAG, "考试安排请求成功！");
                    mExamList.clear();
                    mExamList = response.body().getRows();
                    //mAdapter.refreshAdapter(mExamList);
                   // mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ExamBean> call, Throwable t) {
                LogUtils.i(TAG, "考试安排请求失败！");
            }
        });
    }



    private void testData() {
        /*Call<ResponseBody> examBeanCall = NetClient.getApiService().inquireExam("201701", "", "1", "20", "zc%2Cxq%2Cjcdm2", "asc");
        URL url = examBeanCall.request().url().url();
        LogUtils.i(TAG, "url = "+ url.toString());
        examBeanCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == Configuration.OK_CODE) {
                    LogUtils.i(TAG, "请求成功！");
                    //LogUtils.i(TAG, response.body().getRows().get(0).getJkteaxms());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.i(TAG, "请求失败！");
            }
        });*/
    }
}
