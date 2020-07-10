package com.example.watched.ui.tvShow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.example.watched.R;
import com.example.watched.ui.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class EpisodeDetailActivity extends BaseActivity {

    private static final String TAG = "Episode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_detail_episode, frameLayout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_activity_episode_detail));
        navigationView.setCheckedItem(position);


        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(BaseActivity.PREFS_USER, null);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(EpisodeDetailActivity.this, EditAccountActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    startActivity(intent);
                }
        );

//        AccountListViewModel.Factory factory = new AccountListViewModel.Factory(
//                getApplication());
//        viewModel = ViewModelProviders.of(this, factory).get(AccountListViewModel.class);
//        viewModel.getOwnAccounts().observe(this, accountEntities -> {
//            if (accountEntities != null) {
//                tvShows = accountEntities;
//                adapter.setData(tvShows);
//            }
//        });

        String synopsis = getIntent().getStringExtra("synopsis");
        TextView textView = findViewById(R.id.synopsis);
        textView.setText(synopsis);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    //    private void createDeleteDialog(final int position) {
//        final TvShowEntity account = tvShows.get(position);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        final View view = inflater.inflate(R.layout.row_delete_item, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
//        alertDialog.setCancelable(false);
//
//        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
//        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), account.getName()));
//
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
//            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
//            viewModel.deleteAccount(account, new OnAsyncEventListener() {
//                @Override
//                public void onSuccess() {
//                    Log.d(TAG, "deleteAccount: success");
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    Log.d(TAG, "deleteAccount: failure", e);
//                }
//            });
//            toast.show();
//        });
//
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
//        alertDialog.setView(view);
//        alertDialog.show();
//    }

}
