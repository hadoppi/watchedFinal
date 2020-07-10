package com.example.watched.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watched.R;

public class Friends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        final String [] friends = getResources().getStringArray(R.array.friends);
        ListView list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_layout, friends){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listview_layout, parent, false);
                } else {
                    view = convertView;
                }
                TextView textView1 = view.findViewById(R.id.listview_show);
                textView1.setText(friends[position]);

                return view;
            }
        };
        list = findViewById(R.id.main_listview);
        list.setAdapter(adapter);

        //ListeView handler
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(Friends.this, "You have selected: " + friends[position], Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ActionBar actionBAr = getSupportActionBar();
        actionBAr.setTitle(" Amis ");

        return true;

    }
}