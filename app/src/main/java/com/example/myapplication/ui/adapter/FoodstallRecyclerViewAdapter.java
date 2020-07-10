package com.example.myapplication.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Categories;
import com.example.myapplication.ui.foodstall.FoodFragment;
import com.example.myapplication.ui.foodstall.FoodstallFragment;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodstallRecyclerViewAdapter extends RecyclerView.Adapter<FoodstallRecyclerViewAdapter.MyViewHolder> {

    private List<Categories.Category> categories;
    private FoodstallFragment context;
    private static ClickListener clickListener;

    public FoodstallRecyclerViewAdapter(List<Categories.Category> categories, FoodstallFragment context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodstallRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.foodstall_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodstallRecyclerViewAdapter.MyViewHolder holder, final int position) {
        String strCategoryThum = categories.get(position).getStrCategoryThumb();
        Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_circle).into(holder.categoryThumb);
        String strCategoryName = categories.get(position).getStrCategory();
        holder.categoryName.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.foodstall_img_id)
        ImageView categoryThumb;
        @BindView(R.id.foodstall_title_id)
        TextView categoryName;

        public MyViewHolder(@NonNull View itemView) {
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
        FoodstallRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

}
