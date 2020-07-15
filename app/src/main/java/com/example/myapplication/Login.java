package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button buttonLogin, buttonRegister;
    EditText editTextAccount, editTextPassword;
    ArrayList<Customer> arrayListCustomer;
    private String url = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Anh xa
        buttonLogin = (Button) findViewById(R.id.btnLogin);

        buttonRegister = (Button) findViewById(R.id.btnRegister);

        editTextAccount = (EditText) findViewById(R.id.edtAccount);

        editTextPassword = (EditText) findViewById(R.id.edtPassword);
        loadLoginActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLoginActivity();
    }

    private void loadLoginActivity(){
        arrayListCustomer = new ArrayList<>();
        GetCustomerData(url, arrayListCustomer);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), editTextAccount.getText().toString(), Toast.LENGTH_SHORT).show();
                if (editTextAccount.getText().toString().equals("") || editTextPassword.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Please fill account and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (CheckAccount(editTextAccount.getText().toString(),editTextPassword.getText().toString())){
                        Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Login fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(Login.this, "Load Customer Data success", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Login.this, "Load data fail due to " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}