package com.example.watched.ui.tvShow;

import androidx.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.watched.R;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.ui.BaseActivity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.viewmodel.tvShow.TvShowViewModel;

public class EditShowActivity extends BaseActivity {

    private static final String TAG = "EditSchowActivity";

    private TvShowEntity show;
    private String owner;
    private boolean isEditMode;
    private Toast toast;
    private EditText etAccountName;

    private TvShowViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_edit_account, frameLayout);

        navigationView.setCheckedItem(position);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        owner = settings.getString(BaseActivity.PREFS_USER, null);

        etAccountName = findViewById(R.id.accountName);
        etAccountName.requestFocus();
        Button saveBtn = findViewById(R.id.createAccountButton);
        saveBtn.setOnClickListener(view -> {
            saveChanges(etAccountName.getText().toString());
            onBackPressed();
            toast.show();
        });

        String name = getIntent().getStringExtra("name");
        if (name == null) {
            setTitle(getString(R.string.title_activity_create_account));
            toast = Toast.makeText(this, getString(R.string.account_created), Toast.LENGTH_LONG);
            isEditMode = false;
        } else {
            setTitle(getString(R.string.title_activity_edit_account));
            saveBtn.setText(R.string.action_update);
            toast = Toast.makeText(this, getString(R.string.account_edited), Toast.LENGTH_LONG);
            isEditMode = true;
        }

        TvShowViewModel.Factory factory = new TvShowViewModel.Factory(
                getApplication(), name);
        viewModel = ViewModelProviders.of(this, factory).get(TvShowViewModel.class);
        if (isEditMode) {
            viewModel.getTvShow().observe(this, accountEntity -> {
                if (accountEntity != null) {
                    show = accountEntity;
                    etAccountName.setText(show.getName());
                }
            });
        }
    }

    private void saveChanges(String showName) {
        if (isEditMode) {
            if(!"".equals(showName)) {
                show.setName(showName);
                viewModel.updateTvShow(show, new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "updateAccount: success");
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "updateAccount: failure", e);
                    }
                });
            }
        } else {
            TvShowEntity newShow = new TvShowEntity();

            newShow.setName(showName);
            viewModel.createTvShow(newShow, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "createShow: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "createShow: failure", e);
                }
            });
        }
    }
}
