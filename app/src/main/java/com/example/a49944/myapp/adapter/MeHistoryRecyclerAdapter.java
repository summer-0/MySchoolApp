package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.mine.HistoryData;
import com.example.a49944.myapp.ui.activity.WebViewActivity;

import java.util.List;

/**
 * Created by summer_h on 2019/5/4 0:00
 */
public class MeHistoryRecyclerAdapter extends RecyclerView.Adapter<MeHistoryRecyclerAdapter.MyHistoryViewHolder> {
    private List<HistoryData> mList;
    private Context mContext;
    public MeHistoryRecyclerAdapter(Context context, List<HistoryData> list){
        mContext = context;
        mList = list;
    }
    public void refreshAdapter(List<HistoryData> list){
        mList = list;
    }
    @NonNull
    @Override
    public MyHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_me_history, null);
        return new MyHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHistoryViewHolder myHistoryViewHolder, final int i) {
        //绑定数据
        myHistoryViewHolder.tvHisTitle.setText(mList.get(i).getTitle());
        myHistoryViewHolder.tvAuthor.setText(mList.get(i).getAuthor_name());
        myHistoryViewHolder.tvDate.setText(mList.get(i).getDate());
        myHistoryViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mList.get(i).getUrl();
                String category = mList.get(i).getCategory();
                if (url!=null && !url.isEmpty()){
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url", url);
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

    class MyHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvHisTitle, tvAuthor, tvDate;
        CardView cardView;
        MyHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHisTitle = itemView.findViewById(R.id.tv_his_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDate = itemView.findViewById(R.id.tv_date);
            cardView = itemView.findViewById(R.id.cardview_me);
        }
    }
}
