package com.example.watched.ui.client;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.watched.R;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.ui.BaseActivity;
import com.example.watched.ui.MainActivity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.viewmodel.client.ClientViewModel;

public class ClientActivity extends BaseActivity {

    private static final int EDIT_CLIENT = 1;
    private static final int DELETE_CLIENT = 2;

    private Toast toast;

    private boolean isEditable;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPwd1;
    private EditText etPwd2;

    private ClientViewModel viewModel;

    private ClientEntity client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_client);
        navigationView.setCheckedItem(position);

        getLayoutInflater().inflate(R.layout.activity_client, frameLayout);

        initiateView();

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(PREFS_USER, null);

        ClientViewModel.Factory factory = new ClientViewModel.Factory(getApplication(), user);
        viewModel = ViewModelProviders.of(this, factory).get(ClientViewModel.class);
        viewModel.getClient().observe(this, accountEntity -> {
            if (accountEntity != null) {
                client = accountEntity;
                updateContent();
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, EDIT_CLIENT, Menu.NONE, getString(R.string.action_edit))
                .setIcon(R.drawable.ic_edit_white_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, DELETE_CLIENT, Menu.NONE, getString(R.string.action_delete))
                .setIcon(R.drawable.ic_delete_white_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == EDIT_CLIENT) {
            if (isEditable) {
                item.setIcon(R.drawable.ic_edit_white_24dp);
                switchEditableMode();
            } else {
                item.setIcon(R.drawable.ic_done_white_24dp);
                switchEditableMode();
            }
        }
        if (item.getItemId() == DELETE_CLIENT) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.action_delete));
            alertDialog.setCancelable(false);
            alertDialog.setMessage(getString(R.string.delete_msg));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_delete), (dialog, which) -> {
                viewModel.deleteClient(client, new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {
                        logout();
                    }

                    @Override
                    public void onFailure(Exception e) {}
                });
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initiateView() {
        isEditable = false;
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etEmail = findViewById(R.id.email);
        etPwd1 = findViewById(R.id.password);
        etPwd2 = findViewById(R.id.passwordRep);
    }

    private void switchEditableMode() {
        if (!isEditable) {
            LinearLayout linearLayout = findViewById(R.id.clientPasswordLayout);
            linearLayout.setVisibility(View.VISIBLE);
            etFirstName.setFocusable(true);
            etFirstName.setEnabled(true);
            etFirstName.setFocusableInTouchMode(true);

            etLastName.setFocusable(true);
            etLastName.setEnabled(true);
            etLastName.setFocusableInTouchMode(true);

            etEmail.setFocusable(true);
            etEmail.setEnabled(true);
            etEmail.setFocusableInTouchMode(true);
            etEmail.requestFocus();
        } else {
            saveChanges(
                    etFirstName.getText().toString(),
                    etLastName.getText().toString(),
                    etEmail.getText().toString(),
                    etPwd1.getText().toString(),
                    etPwd2.getText().toString()
            );
            LinearLayout linearLayout = findViewById(R.id.clientPasswordLayout);
            linearLayout.setVisibility(View.GONE);
            etFirstName.setFocusable(false);
            etFirstName.setEnabled(false);
            etLastName.setFocusable(false);
            etLastName.setEnabled(false);
            etEmail.setFocusable(false);
            etEmail.setEnabled(false);
        }
        isEditable = !isEditable;
    }

    private void updateContent() {
        if (client != null) {
            etFirstName.setText(client.getFirstName());
            etLastName.setText(client.getLastName());
            etEmail.setText(client.getEmail());
        }
    }

    private void saveChanges(String firstName, String lastName, String email, String pwd, String pwd2) {
        if (!pwd.equals(pwd2) || pwd.length() < 5) {
            toast = Toast.makeText(this, getString(R.string.error_edit_invalid_password), Toast.LENGTH_LONG);
            toast.show();
            etPwd1.setText("");
            etPwd2.setText("");
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_invalid_email));
            etEmail.requestFocus();
            return;
        }
        client.setEmail(email);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPassword(pwd);

        viewModel.updateClient(client, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                setResponse(true);
            }

            @Override
            public void onFailure(Exception e) {
                setResponse(false);
            }
        });
    }

    private void setResponse(Boolean response) {
        if (response) {
            updateContent();
            toast = Toast.makeText(this, getString(R.string.client_edited), Toast.LENGTH_LONG);
            toast.show();
        } else {
            etEmail.setError(getString(R.string.error_used_email));
            etEmail.requestFocus();
        }
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
