package com.example.myapplication.ui.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ourData;
import com.example.myapplication.ui.foodstall.FoodFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackOrderRecyclerViewAdapter extends RecyclerView.Adapter<TrackOrderRecyclerViewAdapter.MyViewHolder> {

    private static ClickListener clickListener;

    @NonNull
    @Override
    public TrackOrderRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.item_track_order,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackOrderRecyclerViewAdapter.MyViewHolder holder, int position) {
        // view item on screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                holder.textViewNameTitle.setText(ourData.orderArrayList.get(position).getID());
            }
        }, 1000);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textViewNameTitle)
        TextView textViewNameTitle;

        @BindView(R.id.textViewName)
        TextView textViewName;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TrackOrderRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
