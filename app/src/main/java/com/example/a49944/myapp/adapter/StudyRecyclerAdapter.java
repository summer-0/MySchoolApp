package com.example.a49944.myapp.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.example.a49944.myapp.R;

import java.util.List;
import java.util.Random;

public class StudyRecyclerAdapter extends RecyclerView.Adapter<StudyRecyclerAdapter.MyViewHolder> {
    private List<String> mList;
    public StudyRecyclerAdapter(List<String> list){
        mList = list;
    }
    private OnRecyclerItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_study_recyclerview2, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mList.get(i));
        Random random = new Random();
        int red =random.nextInt(150);//0-190        ,如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int green =random.nextInt(150);//0-190
        int blue =random.nextInt(150);//0-190
        myViewHolder.textView.setTextColor(Color.rgb(red, green, blue));
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick();
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_study_view);
        }
    }
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        mOnItemClickListener = listener;
    }
   public interface OnRecyclerItemClickListener{
        void onItemClick();
  }
}
