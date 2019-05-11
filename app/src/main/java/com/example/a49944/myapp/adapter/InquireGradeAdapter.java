package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.net.hhnet.bean.GradeBean;

import java.util.List;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-5-5
 * Description： 显示查询成绩数据的适配器
 */
public class InquireGradeAdapter extends RecyclerView.Adapter<InquireGradeAdapter.MViewHolder> {

    private final Context mContext;
    private final List<GradeBean.RowsBean> rows;
    private OnQuireGradeListener mOnQuireGradeListener;

    public InquireGradeAdapter(Context context, List<GradeBean.RowsBean> rows) {
        this.mContext = context;
        this.rows = rows;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_grade_rc_layout, parent, false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, final int position) {
        holder.ClassName.setText(rows.get(position).getKcmc());
        holder.gpa.setText(rows.get(position).getCjjd());
        holder.grade.setText(rows.get(position).getZcj());

        /**
         * 点击展开具体的信息
         */
        holder.ClassName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnQuireGradeListener.onClickWhenQuireGrade(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        private CheckBox gradeId;
        private TextView ClassName;
        private TextView gpa;
        private TextView grade;

        public MViewHolder(View itemView) {
            super(itemView);
            gradeId = itemView.findViewById(R.id.grade_id);
            ClassName = itemView.findViewById(R.id.class_name);
            gpa = itemView.findViewById(R.id.gpa);
            grade = itemView.findViewById(R.id.grade);
        }
    }


    /**
     * 监控每条成绩 item 的点击事件的接口
     */
    public interface OnQuireGradeListener {
        void onClickWhenQuireGrade(View view, int position);
    }

    /**
     * 给接口提供实例
     *
     * @param onQuireGradeListener
     */
    public void setOnQuireGradeListener(OnQuireGradeListener onQuireGradeListener) {
        InquireGradeAdapter.this.mOnQuireGradeListener = onQuireGradeListener;
    }

}
