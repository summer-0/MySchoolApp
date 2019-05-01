package com.example.a49944.myapp.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.CampusKdRecyclerAdapter;
import com.example.a49944.myapp.bean.campus.KdTrace;
import com.example.a49944.myapp.net.kdniao.KdniaoTrackQueryAPI;
import com.example.a49944.myapp.utils.LogUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by summer_h on 2019/4/25 17:10
 */
public class KNTraceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = KNTraceActivity.class.getSimpleName();
    private static String EXPCODE = "YD";
    private TextView mTvData;
    private RecyclerView mRecyclerView;
    private AppCompatEditText mEtNumber;
    private LinearLayout mSearch;
    private RelativeLayout mSelectName;
    private TextView mTvKdName;
    private View mPopView;
    private PopupWindow mPopWindow;
    private static final int SERARCH_YES = 0;
    private static final int SERARCH_NO = 1;
    private List<KdTrace.TracesBean> mList;
    private CampusKdRecyclerAdapter mAdapter;
    private Button mBtnZto, mBtnYto, mBtnYd, mBtnYzpy, mBtnEms, mBtnJd, mBtnUc;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SERARCH_YES:
                    mTvData.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mAdapter.refreshDate(mList);
                    mAdapter.notifyDataSetChanged();
                    mList = null;
                    break;
                case SERARCH_NO:
                    mTvData.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kntrace);
        initView();
        initData();
    }

    private void initView() {
        mTvData = findViewById(R.id.tv_no_data);
        mTvKdName = findViewById(R.id.tv_kd_name);
        mRecyclerView = findViewById(R.id.recycler_kn_trace);
        mEtNumber = findViewById(R.id.et_number);
        mSearch = findViewById(R.id.ll_search);
        mSelectName = findViewById(R.id.rl_select_name);
    }

    private void initData() {
        mList = new ArrayList<>();
        mSelectName.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mAdapter = new CampusKdRecyclerAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_select_name:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                } else {
                    showPopWindow();
                }
                break;
            case R.id.ll_search:
                //查询
                if (mList == null) {
                    mList = new ArrayList<>();
                }
                final String kdNumber = mEtNumber.getText().toString();
                new Thread() {
                    @Override
                    public void run() {
                        KdniaoTrackQueryAPI trackQueryAPI = new KdniaoTrackQueryAPI();

                        try {
                            //网络请求
                            String result = trackQueryAPI.getOrderTracesByJson(EXPCODE, kdNumber);
                            LogUtils.i(TAG, result);
                            //解析json
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray trances = (JSONArray) jsonObject.get("Traces");
                            for (int i = 0; i < trances.length(); i++) {
                                KdTrace.TracesBean bean = new KdTrace.TracesBean();
                                JSONObject o = (JSONObject) trances.get(i);
                                bean.setAcceptStation((String) o.get("AcceptStation"));
                                bean.setAcceptTime((String) o.get("AcceptTime"));
                                mList.add(bean);
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (mList.size() > 0) {
                            Collections.reverse(mList); //反转
                            mHandler.sendEmptyMessage(SERARCH_YES);
                        } else {
                            mHandler.sendEmptyMessage(SERARCH_NO);
                        }
                    }
                }.start();

                break;
            default:
                break;
        }

    }

    private void showPopWindow() {
        //选择快递名字
        mPopView = LayoutInflater.from(this).inflate(R.layout.popwindow_kd_select, null, false);
        mBtnZto = mPopView.findViewById(R.id.btn_zto);
        mBtnYto = mPopView.findViewById(R.id.btn_yto);
        mBtnYd = mPopView.findViewById(R.id.btn_yd);
        mBtnYzpy = mPopView.findViewById(R.id.btn_yzpy);
        mBtnEms = mPopView.findViewById(R.id.btn_ems);
        mBtnJd = mPopView.findViewById(R.id.btn_jd);
        mBtnUc = mPopView.findViewById(R.id.btn_uc);
        mBtnZto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnZto.getText());
                EXPCODE = "ZTO";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }

            }
        });
        mBtnYto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnYto.getText());
                EXPCODE = "YTO";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
            }
        });
        mBtnYd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnYd.getText());
                EXPCODE = "YD";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
            }
        });
        mBtnEms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnEms.getText());
                EXPCODE = "EMS";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
            }
        });
        mBtnJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnJd.getText());
                EXPCODE = "JD";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }

            }
        });
        mBtnUc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnUc.getText());
                EXPCODE = "UC";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }

            }
        });
        mBtnYzpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvKdName.setText(mBtnYzpy.getText());
                EXPCODE = "YZPY";
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }

            }
        });
        mPopWindow = new PopupWindow(mPopView, 500, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.showAsDropDown(mSelectName);
    }

    @Override
    public void onBackPressed() {
        if (mPopWindow!= null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }else {
            super.onBackPressed();
        }
    }
}
