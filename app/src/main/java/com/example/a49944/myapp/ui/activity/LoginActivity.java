package com.example.a49944.myapp.ui.activity;

import android.content.Intent;
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
import com.example.a49944.myapp.MainActivity;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.constant.AppConstant;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.img.ImageFractory;
import com.example.a49944.myapp.net.hhnet.Configuration;
import com.example.a49944.myapp.net.hhnet.LoginSuccessStatusmessage;
import com.example.a49944.myapp.net.hhnet.NetClient;
import com.example.a49944.myapp.net.hhnet.UserManagement;
import com.example.a49944.myapp.net.hhnet.bean.LoginBean;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.utils.LogUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private AppCompatEditText mStuNumber, mStuPassword;
    private ImageView mImgYzm;
    private EditText mInputYzm;
    private TextView mLoginButton;
    private AppCompatCheckBox mIsCheck;
    private ConfigManager mConfigManager;
    private String mStrStuNumber;
    private String mStrStuPassword;
    private String mStrInputYzm;
    private String cookie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //初始化cookie
        initCookie();
    }

    /**
     * 初始化cookie
     */
    private void initCookie() {
        Call<String> initCookie = NetClient.getApiServiceForScalas().initCookie();
        initCookie.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == Configuration.OK_CODE) {
                    LogUtils.i(TAG, "成功");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
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
        } else {
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
                //finish();
                break;
            case R.id.img_yzm:
                //更换验证码
                getVerifyCode();
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码图片
     */
    private void getVerifyCode() {
        //获取验证码的url拼接
        cookie = UserManagement.getCookie();
        String url = AppConstant.BASE_URL + "yzm?d=" + System.currentTimeMillis();
        ImageFractory.MGlideYZM(this, url, "JSESSIONID=" + cookie, mImgYzm, R.mipmap.yzm);
        // LogUtils.i(TAG, cookie);
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
        } else {     //不保存
            SharedPreferences.Editor edit = mConfigManager.getUserInfoSP().edit();
            edit.putBoolean(ConfigConstant.USER_ISSELECT, checked);
            edit.apply();
        }
    }

    /**
     * 登录
     *
     * @param strStuNumber
     * @param strStuPasswords
     * @param strInputYzm
     */
    private void login(String strStuNumber, String strStuPasswords, String strInputYzm) {
        Call<LoginBean> loginBeanCall = NetClient.getApiService().login(strStuNumber, strStuPasswords, strInputYzm);
        loginBeanCall.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.body().getCode() == Configuration.LOGIN_SUCCESS) {
                    //登录成功
                    UserManagement.setIsLogin(true);
                    LoginSuccessStatusmessage statusmessage = new LoginSuccessStatusmessage();
                    statusmessage.setLogin(true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveLoginStatus(mStrStuNumber, mStrStuPassword, mIsCheck.isChecked());
    }
}
