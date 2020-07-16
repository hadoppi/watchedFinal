package com.example.watched.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watched.R;
import com.example.watched.adapter.RecyclerAdapter;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.util.RecyclerViewItemClickListener;
import com.example.watched.viewmodel.ListListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyList extends BaseActivity {

    private static final String TAG = "MyList";

    private List<ListEntity> lists;
    private RecyclerAdapter<ListEntity> adapter;
    private ListListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_list, frameLayout);

        setTitle(getString(R.string.title_activity_list));
        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String owner = settings.getString(BaseActivity.PREFS_USER, null);

        lists = new ArrayList<>();
        adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + lists.get(position));

                Intent intent = new Intent(MyList.this, DetailList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("listName", lists.get(position).getName());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + lists.get(position));

                createDeleteDialog(position);
            }
        });

        FloatingActionButton fab;
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(MyList.this, EditListActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    startActivity(intent);
                }
        );

        ListListViewModel.Factory factory = new ListListViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(ListListViewModel.class);
        viewModel.getList().observe(this, lists -> {

            if (lists != null) {
                this.lists = lists;
                List<ListEntity> nouvelle = new ArrayList<>();
                for (int i = 0; i < lists.size(); i++) {
                    nouvelle.add(lists.remove(i));
                }
                ArrayList<String> result = new ArrayList<>();
                HashSet<String> set = new HashSet<>();
                for (int i = 0; i < lists.size(); i++) {
                    if (!set.contains(nouvelle.get(i).getName())) {
                        result.add(nouvelle.get(i).getName());
                        set.add(nouvelle.get(i).getName());
                    } else {
                        lists.remove(i);
                    }
                }
                adapter.setData(this.lists);
            }
        });
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        /*
        The activity has to be finished manually in order to guarantee the navigation hierarchy working.
        */
        finish();
        return super.onNavigationItemSelected(item);
    }

    private void createDeleteDialog(final int position) {
        final ListEntity list = lists.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
        alertDialog.setCancelable(false);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), list));

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
            viewModel.deleteList(list, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "deleteList: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "deleteList: failure", e);
                }
            });
            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}

