package com.example.myapplication.ui.account;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Customer;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountFragment extends Fragment {

    Button buttonLogin, buttonRegister;
    EditText editTextAccount, editTextPassword;
//    MainActivity mainActivityData;
    ArrayList<Customer> arrayListCustomer;
    private String url = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        buttonLogin = (Button) root.findViewById(R.id.btnLogin);

        buttonRegister = (Button) root.findViewById(R.id.btnRegister);

        editTextAccount = (EditText) root.findViewById(R.id.edtAccount);

        editTextPassword = (EditText) root.findViewById(R.id.edtPassword);
        return root;
    }
    public boolean CheckAccount(String account, String password){
        for (int i = 0; i < arrayListCustomer.size(); i++){
            if (arrayListCustomer.get(i).getAccount().equals(account) && arrayListCustomer.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public void GetCustomerData(String url, ArrayList<Customer> arrayListCustomer){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Load Customer Data success", Toast.LENGTH_SHORT).show();
                        for (int i = 0;i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListCustomer.add(new Customer(
                                        object.getInt("ID"),
                                        object.getString("Account"),
                                        object.getString("Name"),
                                        object.getString("Email"),
                                        object.getString("PassWord"),
                                        object.getInt("Age"),
                                        object.getString("Sex"),
                                        object.getString("Address")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Load data fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        arrayListCustomer = new ArrayList<>();
        GetCustomerData(url, arrayListCustomer);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), editTextAccount.getText().toString(), Toast.LENGTH_SHORT).show();
                if (editTextAccount.getText().toString().equals("") || editTextPassword.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please fill account and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (CheckAccount(editTextAccount.getText().toString(),editTextPassword.getText().toString())){
                        Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Login fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
