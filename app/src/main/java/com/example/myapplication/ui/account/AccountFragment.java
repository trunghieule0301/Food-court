package com.example.myapplication.ui.account;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.myapplication.Customer;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.ViewModel.AccountViewModel;

import java.util.ArrayList;

public class AccountFragment extends Fragment {
    private AccountViewModel accountViewModel;

    Button buttonLogin, buttonRegister;
    EditText editTextAccount, editTextPassword;
    MainActivity mainActivityData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        buttonLogin = (Button) root.findViewById(R.id.btnLogin);

        buttonRegister = (Button) root.findViewById(R.id.btnRegister);

        editTextAccount = (EditText) root.findViewById(R.id.edtAccount);

        editTextPassword = (EditText) root.findViewById(R.id.edtPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
           
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    public boolean CheckAccount(String account, String password){
        ArrayList<Customer> arrayListCusCheck = new ArrayList<>();
        mainActivityData.GetCustomerData(mainActivityData.urlGetCusData, arrayListCusCheck);
        for (int i = 0; i < arrayListCusCheck.size(); i++){
            if (arrayListCusCheck.get(i).getAccount().equals(account) && arrayListCusCheck.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


    }




}
