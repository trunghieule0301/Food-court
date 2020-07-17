package com.example.myapplication.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ourData;
import com.example.myapplication.ui.foodstall.FoodDetailFragment;
import com.example.myapplication.ui.foodstall.FoodFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailRecyclerViewAdapter extends RecyclerView.Adapter<FoodDetailRecyclerViewAdapter.MyViewHolder> {

    private static List<Meals.Meal> meals;
    private FoodDetailFragment context;
    private static ClickListener clickListener;

    @NonNull
    @Override
    public FoodDetailRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.bill_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.name_of_food_in_bill.setText(ourData.data[position]);
    }

    @Override
    public int getItemCount() {
        return FoodFragment.count1;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name_of_food_in_bill)
        TextView name_of_food_in_bill;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        FoodDetailRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

}
