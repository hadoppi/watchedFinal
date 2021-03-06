package com.example.watched.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AlertDialog;

import com.example.watched.R;
import com.example.watched.ui.tvShow.TvShowsActivity;

public class MainActivity extends BaseActivity {
    Button buttonDiscover;
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

        buttonDiscover = findViewById(R.id.circle_button2);
        buttonDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();
            }
        });
        buttonConfiguration = findViewById(R.id.circle_button5);
        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfiguration();
            }
        });

        buttonMyList = findViewById(R.id.circle_button);
        buttonMyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyList();
            }
        });


    }
    public void openDiscover(){
        Intent intent = new Intent(this, TvShowsActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void openMyList(){
        Intent intent = new Intent(this, MyList.class);
        startActivity(intent);
        this.finish();
    }
    public void openConfiguration(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        this.finish();
    }



    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.app_name));
        navigationView.setCheckedItem(R.id.nav_none);
    }
    @Override
    protected void onStop() {
        super.onStop();

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
