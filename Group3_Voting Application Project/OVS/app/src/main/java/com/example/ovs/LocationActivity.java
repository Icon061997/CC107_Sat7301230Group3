package com.example.ovs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    EditText inputBrgy, inputCity, inputRegion, inputCode, inputAddress;
    Button buttonNext, buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

      /*  inputBrgy = findViewById(R.id.txtBrgy);
        inputCity = findViewById(R.id.txtCity);
        inputRegion = findViewById(R.id.txtRegion);
        inputCode = findViewById(R.id.txtCode);
        inputAddress = findViewById(R.id.txtAddress);


        buttonBack = findViewById(R.id.btnLoginWelcome);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        buttonNext = findViewById(R.id.btnLoginWelcome);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (inputBrgy.getText().toString().equals("")) {
                    Toast.makeText(LocationActivity.this, "Enter barangay", Toast.LENGTH_SHORT).show();
                } else if (inputCity.getText().toString().equals("")) {
                    Toast.makeText(LocationActivity.this, "Enter city", Toast.LENGTH_SHORT).show();
                } else if (inputRegion.getText().toString().equals("")) {
                    Toast.makeText(LocationActivity.this, "Enter region", Toast.LENGTH_SHORT).show();
                } else if (inputCode.getText().toString().equals("")) {
                    Toast.makeText(LocationActivity.this, "Re-type code", Toast.LENGTH_SHORT).show();
                } else if (inputAddress.getText().toString().equals("")) {
                    Toast.makeText(LocationActivity.this, "Enter address", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, String> params = new HashMap<>();
                    params.put("brgy", inputBrgy.getText().toString());
                    params.put("city", inputCity.toString());
                    params.put("region", inputRegion.getText().toString());
                    params.put("code", inputCode.getText().toString());
                    params.put("address", inputAddress.getText().toString());
                    register(params);
                }

            }

        });

    }

    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(LocationActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<RegistrationResponseModel> registerCall = networkService.register(params);
        registerCall.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<RegistrationResponseModel> call, @NonNull Response<RegistrationResponseModel> response) {
                RegistrationResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(LocationActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LocationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LocationActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<RegistrationResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });

       */
    }
}