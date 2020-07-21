package com.example.myapplication.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.name_of_food_in_bill.setText(ourData.food[position]);
        holder.price.setText(ourData.price[position] + " VND");
    }

    @Override
    public int getItemCount() {
        return FoodFragment.count1;
    }

    public static int check;

    public static int SUM;

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public int sum = 1;

        public int sub_sum = 1;

        @BindView(R.id.name_of_food_in_bill)
        TextView name_of_food_in_bill;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.buttonMul)
        Button buttonMul;

        @BindView(R.id.buttonAdd)
        Button buttonAdd;

        @BindView(R.id.sum_of_each_food)
        TextView sum_of_each_food;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            buttonAdd.setOnClickListener(this);
            buttonMul.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonAdd:
                    sum++;
                    if (sub_sum < 0){
                        sub_sum = 1;
                    }else {
                        sub_sum++;
                    }
                    sum_of_each_food.setText(String.valueOf(sum));
                    check = 1;
                    break;
                case R.id.buttonMul:
                    if(sum > 0) {
                        sum--;
                    }
                    sub_sum--;
                    sum_of_each_food.setText(String.valueOf(sum));
                    check = 0;
                    break;
            }
            SUM = sub_sum;
//            Toast.makeText(v.getContext(), "test click " + String.valueOf(sum), Toast.LENGTH_SHORT).show();
            clickListener.onClick(v, getAdapterPosition(), check, SUM);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        FoodDetailRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position, int check, int sum);
    }
}
