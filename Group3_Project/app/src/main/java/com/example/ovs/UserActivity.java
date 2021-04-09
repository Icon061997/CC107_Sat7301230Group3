package com.example.ovs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class UserActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view) {
        HomeActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeActivity.closeDrawer(drawerLayout);
    }

    public void ClickElection(View view) {
        HomeActivity.redirectActivity(this, HomeActivity.class);
    }

    public void ClickHistory(View view) {
        HomeActivity.redirectActivity(this, HistoryActivity.class);
    }

    public void ClickUser(View view) {
        recreate();
    }

    public void ClickUpdate(View view) {
        HomeActivity.redirectActivity(this, UpdateActivity.class);
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