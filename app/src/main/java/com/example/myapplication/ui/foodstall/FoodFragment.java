package com.example.myapplication.ui.foodstall;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;

public class FoodFragment extends Fragment {

    ImageButton button14;
    ImageButton button15;
    ImageButton button12;
    ImageButton button13;
    ImageButton button10;
    ImageButton button11;
    ImageButton button6;
    ImageButton button9;
    ImageButton button16;
    ImageButton button17;
    Button button18;
    TextView txtKetQua;
    TextView txtKetQua1;
    TextView txtKetQua2;
    TextView txtKetQua3;
    TextView txtKetQua4;
    TextView txtKetQua5;

    int tong = 0;
    int tong1 = 0;
    int tong2 = 0;
    int tong3 = 0;
    int tong4 = 0;
    int tong5 = 0;
    int[] tong_hang = new int[6];

    public static final String KEY1 = "Tong mon da dat";

    private FoodViewModel foodViewModel;

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        foodViewModel =
                ViewModelProviders.of(this).get(FoodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_food, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_id_in_food_frag);
        RecyclerViewAdapter listAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        button14 = (ImageButton) view.findViewById((R.id.button14));
        button15 = (ImageButton) view.findViewById((R.id.button15));
        button12 = (ImageButton) view.findViewById((R.id.button12));
        button13 = (ImageButton) view.findViewById((R.id.button13));
        button10 = (ImageButton) view.findViewById((R.id.button10));
        button11 = (ImageButton) view.findViewById((R.id.button11));
        button6 = (ImageButton) view.findViewById((R.id.button6));
        button9 = (ImageButton) view.findViewById((R.id.button9));
        button16 = (ImageButton) view.findViewById((R.id.button16));
        button17 = (ImageButton) view.findViewById((R.id.button17));
        button18 = (Button) view.findViewById((R.id.button_purchase));
        txtKetQua = (TextView) view.findViewById((R.id.textView));
        txtKetQua1 = (TextView) view.findViewById((R.id.textView5));
        txtKetQua2 = (TextView) view.findViewById((R.id.textView4));
        txtKetQua3 = (TextView) view.findViewById((R.id.textView3));
        txtKetQua4 = (TextView) view.findViewById((R.id.textView2));
        txtKetQua5 = (TextView) view.findViewById((R.id.textView1));


        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong4++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua1.setText(String.valueOf(tong4));
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong4 >= 1) {
                    tong--;
                    tong4--;
                } else {
                    tong4 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua4.setText(String.valueOf(tong4));
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong3++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua2.setText(String.valueOf(tong3));
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong3 >= 1) {
                    tong--;
                    tong3--;
                } else {
                    tong3 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua2.setText(String.valueOf(tong3));
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong2++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua3.setText(String.valueOf(tong2));
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong2 >= 1) {
                    tong--;
                    tong2--;
                } else {
                    tong2 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua3.setText(String.valueOf(tong2));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong1++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua4.setText(String.valueOf(tong1));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong1 >= 1) {
                    tong--;
                    tong1--;
                } else {
                    tong1 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua4.setText(String.valueOf(tong1));
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong5++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua5.setText(String.valueOf(tong5));
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong5 >= 1) {
                    tong--;
                    tong5--;
                } else {
                    tong5 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua5.setText(String.valueOf(tong5));
            }
        });

        tong_hang[0]=tong;
        tong_hang[1]=tong1;
        tong_hang[2]=tong2;
        tong_hang[3]=tong3;
        tong_hang[4]=tong4;
        tong_hang[5]=tong5;

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong > 0) {
                    Fragment myFragment = new AuthenticateFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putInt("key1", tong1);
                    bundle.putInt("key2", tong2);
                    bundle.putInt("key3", tong3);
                    bundle.putInt("key4", tong4);
                    bundle.putInt("key5", tong5);
                    myFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameFood, myFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}