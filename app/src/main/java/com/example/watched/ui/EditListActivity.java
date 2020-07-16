package com.example.watched.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.watched.R;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.viewmodel.ListListViewModel;

public class EditListActivity extends BaseActivity {

    private static final String TAG = "EditListActivity";

    private ListEntity list;
    private boolean isEditMode;
    private Toast toast;
    private EditText listName;
    private EditText showName;

    private ListListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_edit_list, frameLayout);

        navigationView.setCheckedItem(position);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);

        listName = findViewById(R.id.listName);
        listName.requestFocus();
        showName = findViewById(R.id.showName);
        showName.requestFocus();
        Button saveBtn = findViewById(R.id.createAccountButton);
        saveBtn.setOnClickListener(view -> {
            saveChanges(listName.getText().toString(), showName.getText().toString());
            onBackPressed();
            toast.show();
        });

        String name = getIntent().getStringExtra("name");
        if (name == null) {
            setTitle("create liste");
            toast = Toast.makeText(this, "Liste created", Toast.LENGTH_LONG);
            isEditMode = false;
        } else {
            setTitle(getString(R.string.title_activity_edit_account));
            saveBtn.setText(R.string.action_update);
            toast = Toast.makeText(this, getString(R.string.account_edited), Toast.LENGTH_LONG);
            isEditMode = true;
        }

        ListListViewModel.Factory factory = new ListListViewModel.Factory(
                getApplication(), name);
        viewModel = ViewModelProviders.of(this, factory).get(ListListViewModel.class);
        if (isEditMode) {
            viewModel.getListByname().observe(this, listEntity -> {
                if (listEntity != null) {
                    list = listEntity;
                    listName.setText(list.getName());

                }
            });
        }
    }

    private void saveChanges( String listname ,String showName) {
        if (isEditMode) {
            if(!"".equals(listname)) {
                list.setName(listname);
                list.setFavoriteShows(showName);
                viewModel.update(list, new OnAsyncEventListener() {
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
            ListEntity newShow = new ListEntity();
            newShow.setFavoriteShows(showName);
            newShow.setName(listname);
            ListListViewModel.Factory factory = new ListListViewModel.Factory(
                    getApplication(), listname);
            viewModel = ViewModelProviders.of(this, factory).get(ListListViewModel.class);
            viewModel.createList(newShow, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "createList: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "createShow: failure", e);
                }
            });
        }
    }
}
