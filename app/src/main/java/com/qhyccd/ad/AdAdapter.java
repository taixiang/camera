package com.qhyccd.ad;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhyccd.R;

import java.util.List;

/**
 * @author tx
 * @date 2018/12/11
 */
public class AdAdapter extends RecyclerView.Adapter<AdAdapter.Holder> {

    private Context context;
    private List<String> data;

    public AdAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_adapter_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(position>0 && position % 8 == 0){
            holder.linearLayout.setVisibility(View.GONE);
        }else {
            holder.linearLayout.setVisibility(View.VISIBLE);
        }
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView tv;
        private LinearLayout linearLayout;

        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

    }

}
