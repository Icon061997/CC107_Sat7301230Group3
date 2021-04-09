package com.example.ovs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
    private String URL = "https://cc107project.000webhostapp.com/OVS/login.php";
    private EditText inputPhoneLog, inputPasswordLog;
    Button buttonLogin;
    CheckBox showpass, rmbrme;
    private String phone, password;
    TextView textCreateAccount, forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textCreateAccount = findViewById(R.id.create);
        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        inputPhoneLog = findViewById(R.id.txtLoginPhone);
        inputPasswordLog = findViewById(R.id.txtLoginPwd);
        forget = findViewById(R.id.forget);
        rmbrme = findViewById(R.id.rmberme);
        showpass = findViewById(R.id.showpass);

        SharedPreferences preferences = getSharedPreferences("Checked", MODE_PRIVATE);
        String checkbox = preferences.getString("Remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "Please Sign in", Toast.LENGTH_SHORT).show();
        }

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));

            }
        });

        buttonLogin = findViewById(R.id.btnLoginLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputPasswordLog.setTransformationMethod(null);
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();

                } else {
                    inputPasswordLog.setTransformationMethod(new PasswordTransformationMethod());
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });

        rmbrme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                } else if (!compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void login() {
        phone = inputPhoneLog.getText().toString().trim();
        password = inputPasswordLog.getText().toString().trim();
        if (!phone.equals("") && !password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(LoginActivity.this, Authenticate.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Email and Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("phone", phone);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(LoginActivity.this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
