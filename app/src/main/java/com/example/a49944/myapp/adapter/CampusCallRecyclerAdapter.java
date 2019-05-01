package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a49944.myapp.R;

import java.util.List;

/**
 * Created by summer_h on 2019/4/29 21:58
 */
public class CampusCallRecyclerAdapter extends RecyclerView.Adapter<CampusCallRecyclerAdapter.CallViewHoler> {
    private Context mContext;
    private List<String> mListName, mListNum;
    public CampusCallRecyclerAdapter(Context context, List<String> listName, List<String > listNum){
        mContext = context;
        mListName = listName;
        mListNum = listNum;
    }
    @NonNull
    @Override
    public CallViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_call_recyclerview, null);
        return new CallViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CallViewHoler callViewHoler, int i) {
        callViewHoler.mTextName.setText(mListName.get(i));
        callViewHoler.mTextNum.setText(mListNum.get(i));
        callViewHoler.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = callViewHoler.mTextNum.getText();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + text);
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListName.size();
    }

    class CallViewHoler extends RecyclerView.ViewHolder{
        TextView mTextName, mTextNum;
        CardView mCardView;
         CallViewHoler(@NonNull View itemView) {
            super(itemView);
             mTextName = itemView.findViewById(R.id.tv_call_name);
             mTextNum = itemView.findViewById(R.id.tv_call_num);
             mCardView = itemView.findViewById(R.id.cardview_call);
        }
    }
}
