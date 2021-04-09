package com.example.ovs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class Authenticate extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);


        TextView msg_txt = findViewById(R.id.txt_msg);
        Button vote_button = findViewById(R.id.btn_vote);



        final androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);

        switch (biometricManager.canAuthenticate()){

            case androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this,"You can use your fingerprint to login",Toast.LENGTH_LONG).show();
                break;
            case androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this,"No fingerprint sensor",Toast.LENGTH_LONG).show();
                vote_button.setVisibility(View.INVISIBLE);
                break;
            case androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this,"Biometric sensor is not available",Toast.LENGTH_LONG).show();
                vote_button.setVisibility(View.INVISIBLE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this,"Your device don't have any fingerprint, check your security setting",Toast.LENGTH_LONG).show();
                vote_button.setVisibility(View.INVISIBLE);
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);


        final BiometricPrompt biometricPrompt = new BiometricPrompt(Authenticate.this,executor,new BiometricPrompt.AuthenticationCallback(){

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(Authenticate.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        final BiometricPrompt.PromptInfo  promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("User fingerprint to login")
                .setNegativeButtonText("cancel")
                .build();

        vote_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                biometricPrompt.authenticate(promptInfo);


            }
        });


    }
}
