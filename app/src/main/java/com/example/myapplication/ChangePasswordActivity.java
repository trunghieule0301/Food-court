package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText editTextOldPass, editTextNewPass, editTextRetypePass;
    Button buttonConfirmChangePass, buttonCancelChangePass;
    String urlCusData = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    String urlUpdatePass = "http://foodcourt2020.medianewsonline.com/updateCustomer.php";
    int index_cus;
    ArrayList<Customer> arrayListCustomer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        AnhXa();

        index_cus = getIntent().getIntExtra("index_cus",0);
        Toast.makeText(this, index_cus + "", Toast.LENGTH_SHORT).show();
        
        buttonConfirmChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNewPass.getText().toString().equals("") || editTextOldPass.getText().toString().equals("") || editTextRetypePass.getText().toString().equals("")){
                    Toast.makeText(ChangePasswordActivity.this, "Please fill all plain", Toast.LENGTH_SHORT).show(); 
                }
                else{
                    if (editTextOldPass.getText().toString().equals(arrayListCustomer.get(index_cus).getPassword())){
                        if (editTextNewPass.getText().toString().equals(editTextRetypePass.getText().toString())){
                            UpdatePassword(urlUpdatePass, arrayListCustomer.get(index_cus).getID() + "", editTextNewPass.getText().toString());
                            finish();
                        }
                        else{
                            Toast.makeText(ChangePasswordActivity.this, "Retype password doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ChangePasswordActivity.this, "Wrong old password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        
        buttonCancelChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                UpdatePassword(urlUpdatePass, "4", "1");
            }
        });
    }

    protected void AnhXa(){
        editTextOldPass = (EditText) findViewById(R.id.edtOldPass);
        editTextNewPass = (EditText) findViewById(R.id.edtNewPass);
        editTextRetypePass = (EditText) findViewById(R.id.edtRetypePass);
        buttonConfirmChangePass = (Button) findViewById(R.id.btnConfirmChangePassword);
        buttonCancelChangePass = (Button) findViewById(R.id.btnCancelChangePassword);
        GetCustomerData(urlCusData, arrayListCustomer);
    }

    public void GetCustomerData(String url, ArrayList<Customer> arrayListCustomer){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(ChangePasswordActivity.this, "Load Customer Data success", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ChangePasswordActivity.this, "Load data fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void UpdatePassword(String url, String id, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")){
                            Toast.makeText(ChangePasswordActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ChangePasswordActivity.this, "Update pass fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChangePasswordActivity.this, "Connect data fail due to" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID", id);
                params.put("Password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}