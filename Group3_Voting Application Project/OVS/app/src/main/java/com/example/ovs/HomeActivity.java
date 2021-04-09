package com.example.ovs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    Button vote;
    private String URL = "https://cc107project.000webhostapp.com/OVS/voting.php";
    private EditText inputPres, inputVP, inputPL, inputSen1, inputSen2, inputSen3, inputSen4, inputSen5,
            inputSen6, inputSen7, inputSen8, inputSen9, inputSen10, inputSen11, inputSen12;

    private String pres, vpres, plist, sen1, sen2, sen3, sen4, sen5, sen6, sen7,
            sen8, sen9, sen10, sen11, sen12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        inputPres = findViewById(R.id.txtPresident);
        inputVP = findViewById(R.id.txtVP);
        inputPL = findViewById(R.id.txtParty);
        inputSen1 = findViewById(R.id.txtSenator0);
        inputSen2 = findViewById(R.id.txtSenator1);
        inputSen3 = findViewById(R.id.txtSenator2);
        inputSen4 = findViewById(R.id.txtSenator3);
        inputSen5 = findViewById(R.id.txtSenator5);
        inputSen6 = findViewById(R.id.txtSenator6);
        inputSen7 = findViewById(R.id.txtSenator7);
        inputSen8 = findViewById(R.id.txtSenator8);
        inputSen9 = findViewById(R.id.txtSenator9);
        inputSen10 = findViewById(R.id.txtSenator10);
        inputSen11 = findViewById(R.id.txtSenator11);
        inputSen12 = findViewById(R.id.txtSenator12);

        vote = findViewById(R.id.btnVote);
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voting();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    public void voting() {
        pres = inputPres.getText().toString().trim();
        vpres = inputVP.getText().toString().trim();
        plist = inputPL.getText().toString().trim();
        sen1 = inputSen1.getText().toString().trim();
        sen2 = inputSen2.getText().toString().trim();
        sen3 = inputSen3.getText().toString().trim();
        sen4 = inputSen4.getText().toString().trim();
        sen5 = inputSen5.getText().toString().trim();
        sen6 = inputSen6.getText().toString().trim();
        sen7 = inputSen7.getText().toString().trim();
        sen8 = inputSen8.getText().toString().trim();
        sen9 = inputSen9.getText().toString().trim();
        sen10 = inputSen10.getText().toString().trim();
        sen11 = inputSen11.getText().toString().trim();
        sen12 = inputSen12.getText().toString().trim();

        if (!pres.equals("") && !vpres.equals("") && !plist.equals("")) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(HomeActivity.this, "Successfully Voted", Toast.LENGTH_SHORT).show();


                } else if (response.equals("failure")) {
                    Toast.makeText(HomeActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("pres", pres);
                data.put("vpres", vpres);
                data.put("plist", plist);
                data.put("sen1", sen1);
                data.put("sen2", sen2);
                data.put("sen3", sen3);
                data.put("sen4", sen4);
                data.put("sen5", sen5);
                data.put("sen6", sen6);
                data.put("sen7", sen7);
                data.put("sen8", sen8);
                data.put("sen9", sen9);
                data.put("sen10", sen10);
                data.put("sen11", sen11);
                data.put("sen12", sen12);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        } else {
            Toast.makeText(HomeActivity.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
    }


    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer((GravityCompat.START));
        }
    }

    public void ClickElection(View view) {
        recreate();
    }

    public void ClickHistory(View view) {
        redirectActivity(this, HistoryActivity.class);
    }

    public void ClickUser(View view) {
        redirectActivity(this, UserActivity.class);
    }

    public void ClickUpdate(View view) {
        redirectActivity(this, UpdateActivity.class);
    }

    public void ClickLogout(View view) {
        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}