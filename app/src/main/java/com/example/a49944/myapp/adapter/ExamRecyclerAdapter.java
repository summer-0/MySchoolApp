package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.exam.ExamBean;

import java.util.List;

/**
 * Created by summer_h on 2019/4/27 16:04
 */
public class ExamRecyclerAdapter extends RecyclerView.Adapter<ExamRecyclerAdapter.ExamViewHoler> {
    private Context mContext;
    //private List<ExamBean.RowsBean> mExamList;
    private List<String> mStrList;

    public ExamRecyclerAdapter(Context context, List<String> strList) {
        mContext = context;
        mStrList = strList;
    }

    public void refreshAdapter(List<String> strList) {
        mStrList = strList;
    }

    @NonNull
    @Override
    public ExamViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_exam, null);
        return new ExamViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHoler examViewHoler, int i) {
        //绑定数据
//        examViewHoler.examCourse.setText(mExamList.get(i).getKcmc());
//        examViewHoler.examDate.setText(mExamList.get(i).getKsrq());
//        examViewHoler.examTime.setText(mExamList.get(i).getKssj());
//        examViewHoler.examPlace.setText(mExamList.get(i).getKscdmc());
//        examViewHoler.examType.setText("闭卷");
        if (mStrList.size() > 0){
            String str = mStrList.get(i);
            String[] strings = str.split(" ");
            examViewHoler.examCourse.setText(strings[0]);
            examViewHoler.examDate.setText(strings[1]);
            examViewHoler.examTime.setText(strings[2]);
            examViewHoler.examType.setText("闭卷");
            examViewHoler.examPlace.setText(strings[3]);
        }

    }

    @Override
    public int getItemCount() {
        return mStrList.size();
    }

    class ExamViewHoler extends RecyclerView.ViewHolder {
        TextView examCourse, examDate, examTime, examType, examPlace;

        ExamViewHoler(@NonNull View itemView) {
            super(itemView);
            examCourse = itemView.findViewById(R.id.tv_exam_course);
            examDate = itemView.findViewById(R.id.tv_exam_date);
            examTime = itemView.findViewById(R.id.tv_exam_time);
            examPlace = itemView.findViewById(R.id.tv_exam_place);
            examType = itemView.findViewById(R.id.tv_exam_type);
        }
    }
}
