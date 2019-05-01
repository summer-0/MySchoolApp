package com.example.a49944.myapp.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.sdk.ConfigManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatEditText mStuNumber, mStuPassword;
    private ImageView mImgYzm;
    private EditText mInputYzm;
    private TextView mLoginButton;
    private AppCompatCheckBox mIsCheck;
    private ConfigManager mConfigManager;
    private String mStrStuNumber;
    private String mStrStuPassword;
    private String mStrInputYzm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        mStuNumber = findViewById(R.id.stuNumber);
        mStuPassword = findViewById(R.id.stuPassword);
        mImgYzm = findViewById(R.id.img_yzm);
        mImgYzm.setOnClickListener(this);
        mInputYzm = findViewById(R.id.input_yzm);
        mLoginButton = findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(this);
        mIsCheck = findViewById(R.id.isCheck);
        mIsCheck.setOnClickListener(this);
    }

    private void initData() {
        mConfigManager = ConfigManager.getInstance();
        //判断是否设置了保存登录状态
        if (mConfigManager.getUserInfoSP().getBoolean(ConfigConstant.USER_ISSELECT, false)) {
            mStuNumber.setText(mConfigManager.getUserInfoSP().getString(ConfigConstant.USER_STUNUMBER, ""));
            mStuPassword.setText(mConfigManager.getUserInfoSP().getString(ConfigConstant.USER_STUPASSWORD, ""));
        }else {
            mIsCheck.setChecked(false);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //登录
                mStrStuNumber = mStuNumber.getText().toString().trim(); // 学号
                mStrStuPassword = mStuPassword.getText().toString().trim(); // 密码
                mStrInputYzm = mInputYzm.getText().toString().trim(); //验证码
                login(mStrStuNumber, mStrStuPassword, mStrInputYzm);
                finish();
                break;
            case R.id.img_yzm:
                //更换验证码
                break;
            default:
                break;
        }
    }

    /**
     * 保存登录状态
     *
     * @param strStuNumber
     * @param strStuPassword
     * @param checked
     */
    private void saveLoginStatus(String strStuNumber, String strStuPassword, boolean checked) {
        if (checked) {  //  true  保存密码
            mConfigManager.setSaveLoginState(strStuNumber, strStuPassword, checked);
        }else {     //不保存
            SharedPreferences.Editor edit = mConfigManager.getUserInfoSP().edit();
            edit.putBoolean(ConfigConstant.USER_ISSELECT, checked);
            edit.apply();
        }
    }

    /**
     * 登录
     *
     * @param mStrStuNumber
     * @param mStrStuPassword
     * @param mStrInputYzm
     */
    private void login(String mStrStuNumber, String mStrStuPassword, String mStrInputYzm) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveLoginStatus(mStrStuNumber, mStrStuPassword, mIsCheck.isChecked());
    }
}
