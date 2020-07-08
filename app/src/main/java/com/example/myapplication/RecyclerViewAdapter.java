package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.foodstall.FoodFragment;
import com.example.myapplication.ui.foodstall.foodDetail;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Fragment myFragment;

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.food_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        ((MyViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return ourData.title1.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView itemFood;
        TextView textView;
        TextView textView2;
        ImageView img_food_thumbnail;
        CardView cardView ;
        ImageButton buttonAdd;
        ImageButton buttonMul;
        TextView textResult;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemFood = (CardView) itemView.findViewById(R.id.cardview_id_1);
            textView = (TextView) itemView.findViewById(R.id.name_of_food);
            textView2 = (TextView) itemView.findViewById(R.id.description_of_food);
            img_food_thumbnail = (ImageView) itemView.findViewById(R.id.food_icon);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            textResult = (TextView) itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), foodDetail.class);
                    i.putExtra("title", ourData.title1[getAdapterPosition()]);
                    i.putExtra("title1", ourData.title2[getAdapterPosition()]);
                    i.putExtra("icon", ourData.picturePath[getAdapterPosition()]);
                    view.getContext().startActivity(i);
                }
            });

        }

        public void bindView(int position){
            img_food_thumbnail.setImageResource(ourData.picturePath[position]);
            textView.setText(ourData.title1[position]);
            textView2.setText(ourData.title2[position]);
        }

        public void onClick(View view) {
        }


    }


}
