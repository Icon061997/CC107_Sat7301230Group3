package com.example.ovs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class UpdateActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){
        HomeActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        HomeActivity.closeDrawer(drawerLayout);
    }
    public void ClickElection(View view){
        HomeActivity.redirectActivity(this, HomeActivity.class);
    }
    public void ClickHistory(View view){
        HomeActivity.redirectActivity(this, HistoryActivity.class);
    }
    public void ClickUser(View view) {
        HomeActivity.redirectActivity(this, UserActivity.class);
    }
    public void ClickUpdate(View view){
        recreate();
    }
    public void ClickLogout(View view){
        HomeActivity.logout(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }
}