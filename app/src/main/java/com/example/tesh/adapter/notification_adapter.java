package com.example.tesh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesh.R;
import com.example.tesh.model.notification_model;

import java.util.List;

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.notification_viewholder> {
    private List<notification_model> mList;
    private Context mcontext;

    public notification_adapter(Context mcontext) {
        this.mcontext = mcontext;
    }


    public void setData(List<notification_model> list){
        this.mList= list;
        notifyDataSetChanged();
    }

//    public List<notification_model> getmList() {
//        this.
//        return mList;
//    }

    @NonNull
    @Override
    public notification_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifiction, parent, false);

        return new notification_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notification_viewholder holder, int position) {
        notification_model noti = mList.get(position);
        if (noti == null){
            return;
        }
        holder.image.setImageResource(noti.getImage());
        holder.tv_title.setText(noti.getTitle());
        holder.tv_content.setText(noti.getContent());
        holder.tv_date.setText(noti.getDate());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class notification_viewholder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView tv_title, tv_content, tv_date;

        public notification_viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgLogo);
            tv_title = itemView.findViewById(R.id.tvTitle);
            tv_content = itemView.findViewById(R.id.tvContent);
            tv_date = itemView.findViewById(R.id.tvDate);
        }
    }
}
