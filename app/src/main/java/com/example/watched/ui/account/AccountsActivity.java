package com.example.watched.ui.account;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.watched.ui.MainActivity;
import com.example.watched.ui.Timer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.watched.R;
import com.example.watched.adapter.RecyclerAdapter;
import com.example.watched.database.entity.AccountEntity;
import com.example.watched.ui.BaseActivity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.util.RecyclerViewItemClickListener;
import com.example.watched.viewmodel.account.AccountListViewModel;

public class AccountsActivity extends BaseActivity {

    private static final String TAG = "TVShowsActivity";

    private List<AccountEntity> tvShows;
    private RecyclerAdapter<AccountEntity> adapter;
    private AccountListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_accounts, frameLayout);

        setTitle(getString(R.string.title_activity_tvShow));
        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.accountsRecyclerView);


        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(BaseActivity.PREFS_USER, null);

        tvShows = new ArrayList<>();
        adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + tvShows.get(position).getName());

                Intent intent = new Intent(AccountsActivity.this, EpisodesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("tvShow", tvShows.get(position).getName());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + tvShows.get(position).getName());

                createDeleteDialog(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(AccountsActivity.this, EditAccountActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    startActivity(intent);
                }
        );

        AccountListViewModel.Factory factory = new AccountListViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(AccountListViewModel.class);
        viewModel.getAccounts().observe(this, tvShows -> {

            if (tvShows != null) {
                this.tvShows = tvShows;
                adapter.setData(this.tvShows);
            }
            ;
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
        final AccountEntity account = tvShows.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
        alertDialog.setCancelable(false);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), account.getName()));

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
            viewModel.deleteAccount(account, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "deleteAccount: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "deleteAccount: failure", e);
                }
            });
            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();
    }
}
