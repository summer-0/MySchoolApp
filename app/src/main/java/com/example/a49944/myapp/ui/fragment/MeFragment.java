package com.example.a49944.myapp.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.a49944.myapp.MainActivity;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.MediaAdapter;
import com.example.a49944.myapp.net.hhnet.UserManagement;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.sdk.DataCleanManager;
import com.example.a49944.myapp.ui.activity.*;
import com.example.a49944.myapp.utils.LogUtils;
import com.guoxiaoxing.phoenix.core.PhoenixOption;
import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.guoxiaoxing.phoenix.core.model.MimeType;
import com.guoxiaoxing.phoenix.picker.Phoenix;
import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    //登录正确码
    private static final int LOGIN_REQUEST = 4;


    //相机拍摄的头像文件(本次演示存放在SD卡根目录下)
    private static final File USER_ICON = new File(Environment.getExternalStorageDirectory(), "user_icon.jpg");

    private SwipeRefreshLayout mSwipeRefresh;
    private Button mBtnLogin, mBtnPicture, mBtnCamera, mBtnCancel;
    private CircleImageView mCircleImg;
    private PopupWindow mPopupWindow;
    private TextView mTvAbout, mTvSuggest, mTvcheck, mTvCacheTotal, mTvLogOut, mTvMessage, mTvHistory;
    private LinearLayout mLlCleanCache;
    private View rootView;
    private MediaAdapter mMediaAdapter;
    private ConfigManager mConfigManager;
    public static final int REQUEST_CODE = 0x000111;
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
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main_me, null);
        } else {
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
        mTvcheck = view.findViewById(R.id.tv_check);
        mLlCleanCache = view.findViewById(R.id.ll_clean_cache);
        mTvCacheTotal = view.findViewById(R.id.tv_cache_total);
        mTvLogOut = view.findViewById(R.id.tv_logout);
        mTvMessage = view.findViewById(R.id.tv_message);
        mTvHistory = view.findViewById(R.id.tv_history);
    }

    private void initData() {
//        mMediaAdapter = new MediaAdapter(mContext);
        mConfigManager = ConfigManager.getInstance();
        String photoPath = mConfigManager.getPhotoPath();
        if (photoPath != null && !TextUtils.isEmpty(photoPath)){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.color.color_f6)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext)
                    .load(photoPath)
                    .apply(options)
                    .into(mCircleImg);
        }
        if (UserManagement.isIsLogin()) {
            mBtnLogin.setText("150806 刘新华");
        } else {
            mBtnLogin.setText("立即登录");
        }
        mSwipeRefresh.setOnRefreshListener(this);
        mBtnLogin.setOnClickListener(this);
        mCircleImg.setOnClickListener(this);
        mTvAbout.setOnClickListener(this);
        mTvSuggest.setOnClickListener(this);
        mTvcheck.setOnClickListener(this);
        mLlCleanCache.setOnClickListener(this);
        mTvLogOut.setOnClickListener(this);
        mTvMessage.setOnClickListener(this);
        mTvHistory.setOnClickListener(this);
        //计算缓存
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(mContext);
            mTvCacheTotal.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                if (!UserManagement.isIsLogin()){ //如果未登录，则跳转登录页面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST);
                }

                //startActivity(intent);
                break;
            case R.id.iv_user:
                // initPermission();
                //initPhoenix();
                showPopWindow();
                //initPhoenix();
                break;
            case R.id.tv_about:
                Intent intentAbout = new Intent(getActivity(), AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.tv_suggest:
                Intent intentSuggest = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intentSuggest);
                break;
            case R.id.tv_check:
                Toast.makeText(mContext, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_clean_cache:
                //清除缓存
                DataCleanManager.clearAllCache(mContext);
                try {
                    mTvCacheTotal.setText(DataCleanManager.getTotalCacheSize(mContext));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_message:
                Intent intentMessage = new Intent(getActivity(), MessageActivity.class);
                startActivity(intentMessage);
                break;
            case R.id.tv_history:
                Intent intentHistory = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.tv_logout:
                if (UserManagement.isIsLogin()) {
                    UserManagement.setIsLogin(false);
                    Intent intentLogout = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intentLogout);
                } else {
                    Toast.makeText(mContext, "未登录", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void initPhoenix() {
        Phoenix.with()
                .theme(PhoenixOption.THEME_DEFAULT) //主题
                .fileType(MimeType.ofAll()) //显示的文件类型图片、视频、图片和视频
                .maxPickNumber(1)  //最大选择数量
                .minPickNumber(0)   //最小选择数量
                .spanCount(4)   //每行显示个数
                .enablePreview(true)    //是否开启预览
                .enableCamera(true) //是否开启拍照
                .enableAnimation(true)  //选择界面图片点击效果
                .enableCompress(true)   //是否开启压缩
                .compressPictureFilterSize(1024)    //多少kb以下的图片不压缩
                .compressVideoFilterSize(2048)  //多少kb以下的视频不压缩
                .thumbnailHeight(160)   //选择界面图片高度
                .thumbnailWidth(160)    //选择界面图片宽度
                .enableClickSound(false)    //是否开启点击声音
                //      .pickedMediaList(mMediaAdapter.getData()) //已选图片数据
                .videoFilterTime(0) //显示多少秒以内的视频
                .mediaFilterSize(10000) //显示多少kb以下的图片/视频， 默认为0，表示不限制
                .start(MeFragment.this, PhoenixOption.TYPE_PICK_MEDIA, REQUEST_CODE);
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
        // mBtnPicture = popview.findViewById(R.id.btn_picture);
        mBtnCamera = popview.findViewById(R.id.btn_camera);
        mBtnCancel = popview.findViewById(R.id.btn_cancel);

//        mBtnPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mPopupWindow.isShowing()) {
//                    mPopupWindow.dismiss();
//                }
//                getPicFromAlbm();
//            }
//        });
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                //getPicFromCamera();
                initPhoenix();
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
        mTempFile = new File(mContext.getExternalCacheDir() + "/photo.png");
        try {
            if (mTempFile.exists()) {
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
        if (requestCode == REQUEST_CODE ) {
            //返回的数据
            List<MediaEntity> result = Phoenix.result(data);
            MediaEntity mediaEntity = result.get(0);
            String path = mediaEntity.getFinalPath();
            mConfigManager.setPhotoPath(path);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.color.color_f6)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext)
                    .load(path)
                    .apply(options)
                    .into(mCircleImg);
            // mMediaAdapter.setData(result);
        }
        if (requestCode == LOGIN_REQUEST){
            if (UserManagement.isIsLogin()) {
                mBtnLogin.setText("150806 刘新华");
            } else {
                mBtnLogin.setText("立即登录");
            }
        }
        /*switch (requestCode) {
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
        }*/
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
