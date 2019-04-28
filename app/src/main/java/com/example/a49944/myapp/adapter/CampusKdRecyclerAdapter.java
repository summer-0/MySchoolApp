package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.campus.KdTrace;

import java.util.List;

/**
 * Created by summer_h on 2019/4/27 16:04
 */
public class CampusKdRecyclerAdapter extends RecyclerView.Adapter<CampusKdRecyclerAdapter.KdViewHoler> {
    private Context mContext;
    private List<KdTrace.TracesBean> mList;
    public CampusKdRecyclerAdapter(Context context, List<KdTrace.TracesBean> list){
        mContext = context;
        mList = list;
    }
    public void refreshDate(List<KdTrace.TracesBean> list){
        this.mList = list;
    }
    @NonNull
    @Override
    public KdViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_campus_kd,null);
        return new KdViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KdViewHoler kdViewHoler, int i) {
        //绑定数据
        //kdViewHoler.tvExpressTime.setText(mList.get(i).getAcceptTime());
        ///kdViewHoler.tvExpressText.setText(mList.get(i).getAcceptStation());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class KdViewHoler extends RecyclerView.ViewHolder{
        View viewTopLine, viewButtomLine;
        ImageView ivExpressSpot;
        TextView tvExpressText, tvExpressTime;
        public KdViewHoler(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.iv_expres_spot);
            itemView.findViewById(R.id.view_top_line);
            itemView.findViewById(R.id.view_buttom_line);
            itemView.findViewById(R.id.tv_express_text);
            itemView.findViewById(R.id.tv_express_time);
        }
    }
}
