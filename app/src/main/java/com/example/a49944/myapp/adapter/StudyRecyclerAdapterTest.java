package com.example.a49944.myapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import com.example.a49944.myapp.ui.activity.WebViewActivity;
import com.example.a49944.myapp.utils.LogUtils;

import java.util.List;
import java.util.Random;

public class StudyRecyclerAdapterTest extends RecyclerView.Adapter<StudyRecyclerAdapterTest.MyViewHolder> {
    private static final String TAG = StudyRecyclerAdapterTest.class.getSimpleName();
    private List<WanAndroidBean.DataBean.ArticlesBean> mList;
    private Context mContext;
    public StudyRecyclerAdapterTest(List<WanAndroidBean.DataBean.ArticlesBean> list, Context context){
        mList = list;
        mContext = context;
    }

    public void refreshDate(List<WanAndroidBean.DataBean.ArticlesBean> list){
        this.mList = list;
       // this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_study_recyclerview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        myViewHolder.textView.setText(mList.get(i).getTitle());
        final Random random = new Random();
        int red =random.nextInt(150);//0-190        ,如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int green =random.nextInt(150);//0-190
        int blue =random.nextInt(150);//0-190
        myViewHolder.textView.setTextColor(Color.rgb(red, green, blue));
     /*   myViewHolder.ll_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = mList.get(i).getLink();
                String category = mList.get(i).getTitle();
                LogUtils.i(TAG, link);
                if (link!=null && !link.isEmpty()){
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("study_url", link);
                    intent.putExtra("category", category);
                    mContext.startActivity(intent);
                }
            }
        });*/
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkLink = mList.get(i).getLink();
                String category = mList.get(i).getTitle();
                LogUtils.i(TAG, apkLink);
                if (apkLink!=null && !apkLink.isEmpty()){
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("study_url", apkLink);
                    intent.putExtra("category", category);
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout ll_study;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_study_view);
            ll_study = itemView.findViewById(R.id.ll_study);
        }
    }



}
