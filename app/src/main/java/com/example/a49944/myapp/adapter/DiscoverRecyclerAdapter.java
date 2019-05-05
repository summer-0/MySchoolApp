package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.mine.HistoryData;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.ui.activity.WebViewActivity;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.utils.LogUtils;
import io.realm.Realm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer_h on 2019/4/18 8:59
 */
public class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.DisViewHolder> {
    private static final String TAG = DiscoverRecyclerAdapter.class.getSimpleName();
    private List<JuHeBean.ResultBean.DataBean> mList;
//    private List<JuHeBean.ResultBean.DataBean> mCacheList;
    private Context mContext;
//    private ConfigManager mConfigManager;
    private Realm mRealm;
    public DiscoverRecyclerAdapter(List<JuHeBean.ResultBean.DataBean> list, Context context){
        mList = list;
        mContext = context;
//        mCacheList = new ArrayList<>();
//        mConfigManager = ConfigManager.getInstance();
        //实例化 realm
        mRealm = Realm.getDefaultInstance();
    }

    public void refreshDate(List<JuHeBean.ResultBean.DataBean> list){
        this.mList = list;
        //this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DisViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover_recyclerview, viewGroup, false);
        return new DisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisViewHolder disViewHolder, final int i) {
        //绑定数据
        ImageView imageView1, imageView2,imageView3;
        disViewHolder.tvTitle.setText(mList.get(i).getTitle());
        disViewHolder.tvAuthor.setText(mList.get(i).getAuthor_name());
        disViewHolder.tvDate.setText(mList.get(i).getDate());
        imageView1 = disViewHolder.imageView1;
        imageView2 = disViewHolder.imageView2;
        imageView3 = disViewHolder.imageView3;
        disViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存数据
                //JuHeBean.ResultBean.DataBean dataBean = mList.get(i);
               // mCacheList.add(mList.get(i));
               // mConfigManager.setMeHistoryList(mCacheList, ConfigConstant.ME_HISTORY);

                final HistoryData historyData = new HistoryData();
                String url = mList.get(i).getUrl();
                String title = mList.get(i).getTitle();
                String category = mList.get(i).getCategory();
                String author_name = mList.get(i).getAuthor_name();
                String date = mList.get(i).getDate();

                historyData.setTitle(title);
                historyData.setUrl(url);
                historyData.setDate(date);
                historyData.setAuthor_name(author_name);

                //使用事务块
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(historyData);
                    }
                });
                if (mRealm != null){
                    mRealm.close();
                }

                if (url!=null && !url.isEmpty()){
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("category", category);
                    mContext.startActivity(intent);
                }
            }
        });
        String thumbnail_pic_s = mList.get(i).getThumbnail_pic_s();
        String thumbnail_pic_s02 = mList.get(i).getThumbnail_pic_s02();
        String thumbnail_pic_s03 = mList.get(i).getThumbnail_pic_s03();
        if (thumbnail_pic_s != null && !thumbnail_pic_s.isEmpty()){
            Glide.with(mContext).load(thumbnail_pic_s).placeholder(R.mipmap.loading_img).centerCrop().into(imageView1);
        }else {
            imageView1.setVisibility(View.GONE);
        }
        if (thumbnail_pic_s02!= null && !thumbnail_pic_s02.isEmpty()){
            Glide.with(mContext).load(thumbnail_pic_s02).placeholder(R.mipmap.loading_img).centerCrop().into(imageView2);
        }else {
            imageView2.setVisibility(View.GONE);
        }
        if (thumbnail_pic_s03 != null && !thumbnail_pic_s03.isEmpty()){
            Glide.with(mContext).load(thumbnail_pic_s03).placeholder(R.mipmap.loading_img).centerCrop().into(imageView3);
        }else {
            imageView3.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DisViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView1, imageView2, imageView3;
        TextView tvTitle, tvAuthor,tvDate;
        CardView cardView;
        DisViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_dis);
            imageView1 = itemView.findViewById(R.id.image1);
            imageView2 = itemView.findViewById(R.id.image2);
            imageView3 = itemView.findViewById(R.id.image3);
            tvTitle =itemView.findViewById(R.id.tv_dis_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
