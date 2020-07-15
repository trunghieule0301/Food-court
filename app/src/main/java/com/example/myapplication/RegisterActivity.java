package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ui.account.AccountFragment;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button buttonCancelRegister, buttonRegisterReal;
    EditText editTextAccountRegister, editTextUsernameRegister, editTextEmailRegister, editTextPasswordRegister, editTextAgeRegister, editTextSexRegister, editTextAddressRegister;
    String urlInsertCustomer = "http://foodcourt2020.medianewsonline.com/insertCustomer.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Anh xa
        buttonRegisterReal = (Button) findViewById(R.id.btnRegisterReal);
        buttonCancelRegister = (Button) findViewById(R.id.btnCancelRegister);
        editTextAccountRegister = (EditText) findViewById(R.id.edtAccountRegister);
        editTextUsernameRegister = (EditText) findViewById(R.id.edtUserName);
        editTextEmailRegister = (EditText) findViewById(R.id.edtEmailRegister);
        editTextPasswordRegister = (EditText) findViewById(R.id.edtPasswordRegister);
        editTextAgeRegister = (EditText) findViewById(R.id.edtAgeRegister);
        editTextSexRegister = (EditText) findViewById(R.id.edtSexRegister);
        editTextAddressRegister = (EditText) findViewById(R.id.edtAddressRegister);

        // proccess button

        buttonRegisterReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                if ((editTextAccountRegister.getText().toString().equals("") ||
                        editTextUsernameRegister.getText().toString().equals("") ||
                        editTextEmailRegister.getText().toString().equals("") ||
                        editTextPasswordRegister.getText().toString().equals("") ||
                        editTextAgeRegister.getText().toString().equals("") ||
                        editTextSexRegister.getText().toString().equals("") ||
                        editTextAddressRegister.getText().toString().equals(""))){
                    Toast.makeText(RegisterActivity.this, "Please fill all information", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    AddCustomer(urlInsertCustomer);
                }
            }
        });

        buttonCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AddCustomer(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")){
                            Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Connect fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserAccount", editTextAccountRegister.getText().toString().trim());
                params.put("UserName", editTextUsernameRegister.getText().toString().trim());
                params.put("UserEmail", editTextEmailRegister.getText().toString().trim());
                params.put("UserPass", editTextPasswordRegister.getText().toString().trim());
                params.put("UserAge", editTextAgeRegister.getText().toString().trim());
                params.put("UserSex", editTextSexRegister.getText().toString().trim());
                params.put("Address", editTextAddressRegister.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}