package com.example.ovs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgetActivity extends AppCompatActivity {

    private String URL = "https://cc107project.000webhostapp.com/OVS/forget.php";
    Button btnSubmit;
    private EditText inputForgetPhone, inputForgetName;
    private String phone, name;
    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        btnSubmit = findViewById(R.id.btnForgetSubmit);

        inputForgetPhone = findViewById(R.id.txtForgetPhone);
        inputForgetName = findViewById(R.id.txtForgetName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword();
            }
        });

    }

    public void forgetPassword() {
        phone = inputForgetPhone.getText().toString().trim();
        name = inputForgetName.getText().toString().trim();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("SUCCESS")) {
                        Toast.makeText(ForgetActivity.this, "Email successfully sent please check your mail inbox", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ForgetActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ForgetActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("phone", phone);
                    data.put("name", name);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

