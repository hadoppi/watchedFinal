//package com.example.watched.ui;
//
//import android.accounts.Account;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.watched.R;
//import com.example.watched.adapter.RecyclerAdapter;
//import com.example.watched.database.entity.AccountEntity;
//import com.example.watched.ui.account.AccountDetailActivity;
//import com.example.watched.util.RecyclerViewItemClickListener;
//import com.example.watched.viewmodel.account.AccountListViewModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Discover extends AppCompatActivity {
//    private static final String TAG = "discoverActivity";
//
//    private List<AccountEntity> movies;
//    private RecyclerAdapter recyclerAdapter;
//    private AccountListViewModel viewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_discover);
//
//        RecyclerView recyclerView = findViewById(R.id.main_listview);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        movies = new ArrayList<>();
//        recyclerAdapter = new RecyclerAdapter(new RecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Log.d(TAG, "clicked position:" + position);
//                Log.d(TAG, "clicked on: " + movies.get(position).toString());
//
//                Intent intent = new Intent(Discover.this, AccountDetailActivity.class);
//                intent.setFlags(
//                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
//                                Intent.FLAG_ACTIVITY_NO_HISTORY
//                );
//                intent.putExtra("Titre", movies.get(position).toString());
//                startActivity(intent);
//            }
//
//            @Override
//            public void onItemLongClick(View v, int position) {
//                Log.d(TAG, "longClicked position:" + position);
//                Log.d(TAG, "longClicked on: " + movies.get(position).toString());
//            }
//        });
//
//        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(view -> {
//                    Intent intent = new Intent(Discover.this, AccountDetailActivity.class);
//                    intent.setFlags(
//                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
//                                    Intent.FLAG_ACTIVITY_NO_HISTORY
//                    );
//                    startActivity(intent);
//                }
//        );
////        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
////        String user = settings.getString(BaseActivity.PREFS_USER, null);
////
////        AccountListViewModel.Factory factory = new AccountListViewModel.Factory(
////                getApplication());
////        viewModel = ViewModelProviders.of(this, factory).get(AccountListViewModel.class);
////        viewModel.getOwnAccounts().observe(this, movieEntities -> {
////            if (movieEntities != null) {
////                movies = movieEntities;
////                recyclerAdapter.setData(movies);
////            }
////        });
////
////        recyclerView.setAdapter(recyclerAdapter);
////    }
////
////
//
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        ActionBar actionBAr = getSupportActionBar();
//        actionBAr.setTitle(" Discover ");
//
//        return true;
//
//    }
//
//
//}