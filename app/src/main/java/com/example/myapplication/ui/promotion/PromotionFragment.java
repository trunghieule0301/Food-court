package com.example.myapplication.ui.promotion;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class PromotionFragment extends Fragment {

    private PromotionViewModel promotionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        promotionViewModel =
                ViewModelProviders.of(this).get(PromotionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_promotion, container, false);
        final TextView textView = root.findViewById(R.id.text_promotion);
        promotionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
