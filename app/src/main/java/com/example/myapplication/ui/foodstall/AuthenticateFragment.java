package com.example.myapplication.ui.foodstall;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.ViewModel.AuthenticateViewModel;

public class AuthenticateFragment extends Fragment {

    TextView txtQty1;
    TextView txtQty2;
    TextView txtQty3;
    TextView txtQty4;
    TextView txtQty5;
    TextView txtPrice1;
    TextView txtPrice2;
    TextView txtPrice3;
    TextView txtPrice4;
    TextView txtPrice5;
    TextView txtMon1;
    TextView txtMon2;
    TextView txtMon3;
    TextView txtMon4;
    TextView txtMon5;
    TextView txtTotalPrice;
    Bundle bundle;
    int[] tong = new int[6];
    private AuthenticateViewModel authenticateViewModel;

    public static AuthenticateFragment newInstance() {
        return new AuthenticateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        if(bundle != null) {
            tong[1] = bundle.getInt("key1", 0);
            tong[2] = bundle.getInt("key2", 0);
            tong[3] = bundle.getInt("key3", 0);
            tong[4] = bundle.getInt("key4", 0);
            tong[5] = bundle.getInt("key5", 0);
        }
        return inflater.inflate(R.layout.fragment_authenticate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticateViewModel = ViewModelProviders.of(this).get(AuthenticateViewModel.class);
        // TODO: Use the ViewModel
        txtQty1 = (TextView) view.findViewById(R.id.txtQty1);
        txtQty2 = (TextView) view.findViewById(R.id.txtQty2);
        txtQty3 = (TextView) view.findViewById(R.id.txtQty3);
        txtQty4 = (TextView) view.findViewById(R.id.txtQty4);
        txtQty5 = (TextView) view.findViewById(R.id.txtQty5);
        txtPrice1 = (TextView) view.findViewById(R.id.txtPrice1);
        txtPrice2 = (TextView) view.findViewById(R.id.txtPrice2);
        txtPrice3 = (TextView) view.findViewById(R.id.txtPrice3);
        txtPrice4 = (TextView) view.findViewById(R.id.txtPrice4);
        txtPrice5 = (TextView) view.findViewById(R.id.txtPrice5);
        txtMon1 = (TextView) view.findViewById(R.id.txtMon1);
        txtMon2 = (TextView) view.findViewById(R.id.txtMon2);
        txtMon3 = (TextView) view.findViewById(R.id.txtMon3);
        txtMon4 = (TextView) view.findViewById(R.id.txtMon4);
        txtMon5 = (TextView) view.findViewById(R.id.txtMon5);
        txtTotalPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
        int[] price = new int[6];
        int[] stack = new int[6];
        int[] stackQuantity = new int[6];
        price[1] = tong[1]*150000;
        price[2] = tong[2]*150000;
        price[3] = tong[3]*90000;
        price[4] = tong[4]*175000;
        price[5] = tong[5]*95000;
        int Phai_tra;
        int total = 0;
        Phai_tra = price[1] + price[2] + price[3] + price[4] + price[5];
        txtTotalPrice.setText(String.valueOf(Phai_tra));
        for(int i = 1; i < 6; i++){
            if(price[i] > 0){
                total = total + 1;
            }
        }

        // Arrange price column

        for(int i = 1; i < 6 ; i++){
            for(int k = 1; k <= total; k++) {
                if(price[i] > 0 && stack[k] == 0) {
                    stack[k] = price[i];
                    break;
                }
            }
        }

        if(total == 1){
            txtPrice1.setText(String.valueOf(stack[1]));
        }

        if(total == 2){
            txtPrice1.setText(String.valueOf(stack[1]));
            txtPrice2.setText(String.valueOf(stack[2]));
        }

        if(total == 3){
            txtPrice1.setText(String.valueOf(stack[1]));
            txtPrice2.setText(String.valueOf(stack[2]));
            txtPrice3.setText(String.valueOf(stack[3]));
        }

        if(total == 4){
            txtPrice1.setText(String.valueOf(stack[1]));
            txtPrice2.setText(String.valueOf(stack[2]));
            txtPrice3.setText(String.valueOf(stack[3]));
            txtPrice4.setText(String.valueOf(stack[4]));
        }

        if(total == 5){
            txtPrice1.setText(String.valueOf(stack[1]));
            txtPrice2.setText(String.valueOf(stack[2]));
            txtPrice3.setText(String.valueOf(stack[3]));
            txtPrice4.setText(String.valueOf(stack[4]));
            txtPrice5.setText(String.valueOf(stack[5]));
        }

        // Arrange quantity column

        for(int i = 1; i < 6 ; i++){
            for(int k = 1; k <= total; k++) {
                if(tong[i] > 0 && stackQuantity[k] == 0) {
                    stackQuantity[k] = tong[i];
                    break;
                }
            }
        }

        if(total == 1){
            txtQty1.setText(String.valueOf(stackQuantity[1]));
        }

        if(total == 2){
            txtQty1.setText(String.valueOf(stackQuantity[1]));
            txtQty2.setText(String.valueOf(stackQuantity[2]));
        }

        if(total == 3){
            txtQty1.setText(String.valueOf(stackQuantity[1]));
            txtQty2.setText(String.valueOf(stackQuantity[2]));
            txtQty3.setText(String.valueOf(stackQuantity[3]));
        }

        if(total == 4){
            txtQty1.setText(String.valueOf(stackQuantity[1]));
            txtQty2.setText(String.valueOf(stackQuantity[2]));
            txtQty3.setText(String.valueOf(stackQuantity[3]));
            txtQty4.setText(String.valueOf(stackQuantity[4]));
        }

        if(total == 5){
            txtQty1.setText(String.valueOf(stackQuantity[1]));
            txtQty2.setText(String.valueOf(stackQuantity[2]));
            txtQty3.setText(String.valueOf(stackQuantity[3]));
            txtQty4.setText(String.valueOf(stackQuantity[4]));
            txtQty5.setText(String.valueOf(stackQuantity[5]));
        }

        // Arrange Food item
        String[] nameOfFood = new String[]{"", "Mon 1" , "Mon 2" ,"Mon 3" ,"Mon 4" ,"Mon 5"};
        String[] stackNameOfFood = new String[]{"","","","","",""};
        for(int i = 1; i < 6 ; i++){
            for(int k = 1; k <= total; k++) {
                if(tong[i] > 0 && stackNameOfFood[k] == "") {
                    if(i == 1){
                        stackNameOfFood[k] = nameOfFood[1];
                    }
                    if(i == 2){
                        stackNameOfFood[k] = nameOfFood[2];
                    }
                    if(i == 3){
                        stackNameOfFood[k] = nameOfFood[3];
                    }
                    if(i == 4){
                        stackNameOfFood[k] = nameOfFood[4];
                    }
                    if(i == 5){
                        stackNameOfFood[k] = nameOfFood[5];
                    }
                    break;
                }
            }
        }

        if(total == 1){
            txtMon1.setText(stackNameOfFood[1]);
        }

        if(total == 2){
            txtMon1.setText(stackNameOfFood[1]);
            txtMon2.setText(stackNameOfFood[2]);
        }

        if(total == 3){
            txtMon1.setText(stackNameOfFood[1]);
            txtMon2.setText(stackNameOfFood[2]);
            txtMon3.setText(stackNameOfFood[3]);
        }

        if(total == 4){
            txtMon1.setText(stackNameOfFood[1]);
            txtMon2.setText(stackNameOfFood[2]);
            txtMon3.setText(stackNameOfFood[3]);
            txtMon4.setText(stackNameOfFood[4]);
        }

        if(total == 5){
            txtMon1.setText(stackNameOfFood[1]);
            txtMon2.setText(stackNameOfFood[2]);
            txtMon3.setText(stackNameOfFood[3]);
            txtMon4.setText(stackNameOfFood[4]);
            txtMon5.setText(stackNameOfFood[5]);
        }


    }
}