package com.example.watched.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AlertDialog;

import com.example.watched.R;
import com.example.watched.ui.account.AccountsActivity;

public class MainActivity<Textview> extends BaseActivity {
    Button buttonDiscover;
    Button buttonMessages;
    Button buttonFriends;
    Button buttonTimer;
    Button buttonMyList;
    Button buttonConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        ActionBar actionBAr = getSupportActionBar();
        actionBAr.setTitle(" WATCHED by Oppikofer ");

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(BaseActivity.PREFS_USER, null);


        buttonDiscover = (Button) findViewById(R.id.circle_button2);
        buttonDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();
            }
        });
        buttonConfiguration = (Button) findViewById(R.id.circle_button5);
        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfiguration();
            }
        });
        buttonMessages = (Button) findViewById(R.id.circle_button6);
        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessages();
            }
        });
        buttonMyList = (Button) findViewById(R.id.circle_button);
        buttonMyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyList();
            }
        });
        buttonFriends = (Button) findViewById(R.id.circle_button7);
        buttonFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFriends();
            }
        });
        buttonTimer = (Button) findViewById(R.id.circle_button4);
        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimer();
            }
        });

    }
    public void openDiscover(){
        Intent intent = new Intent(this, AccountsActivity.class);
        startActivity(intent);
    }
    public void openMessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
    }
    public void openFriends(){
        Intent intent = new Intent(this, Friends.class);
        startActivity(intent);
    }
    public void openTimer(){
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
    public void openMyList(){
        Intent intent = new Intent(this, MyList.class);
        startActivity(intent);
    }
    public void openConfiguration(){
        Intent intent = new Intent(this, Configuration.class);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.app_name));
        navigationView.setCheckedItem(R.id.nav_none);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.action_logout));
        alertDialog.setCancelable(false);
        alertDialog.setMessage(getString(R.string.logout_msg));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_logout), (dialog, which) -> logout());
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }
}
