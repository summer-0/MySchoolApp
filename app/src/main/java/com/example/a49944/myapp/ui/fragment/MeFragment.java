package com.example.a49944.myapp.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.ui.activity.AboutActivity;
import com.example.a49944.myapp.ui.activity.FeedBackActivity;
import com.example.a49944.myapp.ui.activity.LoginActivity;
import com.example.a49944.myapp.utils.LogUtils;
import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class MeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    public static final String TAG = MeFragment.class.getSimpleName();
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_SMALL_PICTURE = 3;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;


    //相机拍摄的头像文件(本次演示存放在SD卡根目录下)
    private static final File USER_ICON = new File(Environment.getExternalStorageDirectory(), "user_icon.jpg");

    private SwipeRefreshLayout mSwipeRefresh;
    private Button mBtnLogin, mBtnPicture, mBtnCamera, mBtnCancel;
    private CircleImageView mCircleImg;
    private PopupWindow mPopupWindow;
    private TextView mTvAbout, mTvSuggest;
    private View rootView;

    //调用照相机返回图片文件
    private File mTempFile;
    //最后显示的图片文件
    private String mFile;
    private Context mContext;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private final Runnable mRefreshDone = new Runnable() {
        @Override
        public void run() {
            //结束刷新
            mSwipeRefresh.setRefreshing(false);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_main_me, null);
        }else {
            return rootView;
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mSwipeRefresh = view.findViewById(R.id.swipeRefresh_layout);
        mBtnLogin = view.findViewById(R.id.btn_login);
        mCircleImg = view.findViewById(R.id.iv_user);
        mTvAbout = view.findViewById(R.id.tv_about);
        mTvSuggest = view.findViewById(R.id.tv_suggest);
    }

    private void initData() {
        mSwipeRefresh.setOnRefreshListener(this);
        mBtnLogin.setOnClickListener(this);
        mCircleImg.setOnClickListener(this);
        mTvAbout.setOnClickListener(this);
        mTvSuggest.setOnClickListener(this);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_user:
                initPermission();
                break;
            case R.id.tv_about:
                Intent intentAbout = new Intent(getActivity(), AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.tv_suggest:
                Intent intentSuggest = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intentSuggest);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化相机权限
     */
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {
            //有权限直接调用系统相机
            if (mPopupWindow == null || !mPopupWindow.isShowing()) {
                showPopWindow();
            }

        }
    }

    private void showPopWindow() {
        View popview = getLayoutInflater().inflate(R.layout.popwindow_photo_select, null);
        mPopupWindow = new PopupWindow(popview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mBtnPicture = popview.findViewById(R.id.btn_picture);
        mBtnCamera = popview.findViewById(R.id.btn_camera);
        mBtnCancel = popview.findViewById(R.id.btn_cancel);

        mBtnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                getPicFromAlbm();
            }
        });
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                getPicFromCamera();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });

        mPopupWindow.showAtLocation(mSwipeRefresh, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        long ms = System.currentTimeMillis() / 1000;

       // mTempFile = new File(Environment.getExternalStorageDirectory().getPath(), ms + ".png");
        mTempFile = new File( mContext.getExternalCacheDir() + "/photo.png");
        try {
            if (mTempFile.exists()){
                mTempFile.delete();
            }
            mTempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //api >= 24  （Android 7.0）
            //如果在Android7.0以上，使用fileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".provider", mTempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            LogUtils.i(TAG, "getPicFromCamera ： " + contentUri.toString());
        } else {
            //低于Android7.0的
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 从相册中获取图片
     */
    private void getPicFromAlbm() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去用剪裁也需要对uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".provider", mTempFile);
                        //开始对图片进行裁剪
                        startPhotoZoom(contentUri);
                    } else {
                        //开始对图片进行裁剪
                        startPhotoZoom(Uri.fromFile(mTempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后的返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    //开始对图片进行裁剪
                    startPhotoZoom(uri);
                }
                break;
            case CROP_SMALL_PICTURE:    //调用裁剪后返回
                if (data != null) {
                    //让刚才裁剪的图片显示到界面上
                    Bitmap photo = BitmapFactory.decodeFile(mFile);
                    mCircleImg.setImageBitmap(photo);
                } else {
                    LogUtils.i(TAG, "onActivityResult : data为空");
                }
                break;
        }
    }

    /**
     * 对图片进行裁剪
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            LogUtils.i(TAG, "startPhotoZoom : uri为空");
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //outputX outputY 是裁剪图片的宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        File file = new File(getPath());
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CROP_SMALL_PICTURE);

    }

    /**
     * 裁剪后的地址
     *
     * @return
     */
    private String getPath() {
        if (mFile == null) {
            mFile = mContext.getExternalCacheDir() + "/photo.png";
//            long ms = System.currentTimeMillis() / 1000;
//            mFile =  Environment.getExternalStorageDirectory() + "/" +"wode/"+ "outtemp.png";
        }
        LogUtils.i(TAG, "getPath = " + mFile);
        return mFile;
    }
}
