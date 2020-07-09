package com.example.watched.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watched.R;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ActionBar actionBAr = getSupportActionBar();
        actionBAr.setTitle(" Mes listes ");

        return true;

    }
}
