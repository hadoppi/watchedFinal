//package com.example.watched.ui.tvShow;
//
//import androidx.lifecycle.ViewModelProviders;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.appcompat.app.AlertDialog;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.text.NumberFormat;
//
//import com.example.watched.R;
//import com.example.watched.database.entity.TvShowEntity;
//import com.example.watched.ui.BaseActivity;
//import com.example.watched.util.OnAsyncEventListener;
//import com.example.watched.viewmodel.account.AccountViewModel;
//
//public class AccountDetailActivity extends BaseActivity {
//
//    private static final String TAG = "AccountDetailActivity";
//
//    private static final int EDIT_ACCOUNT = 1;
//
//    private TvShowEntity account;
//
//
//    private AccountViewModel viewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_account, frameLayout);
//
//        navigationView.setCheckedItem(position);
//
//        String name = getIntent().getStringExtra("name");
//
//        initiateView();
//
//        AccountViewModel.Factory factory = new AccountViewModel.Factory(
//                getApplication(), name);
//        viewModel = ViewModelProviders.of(this, factory).get(AccountViewModel.class);
//        viewModel.getAccount().observe(this, accountEntity -> {
//            if (accountEntity != null) {
//                account = accountEntity;
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(0, EDIT_ACCOUNT, Menu.NONE, getString(R.string.title_activity_edit_account))
//                .setIcon(R.drawable.ic_config)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == EDIT_ACCOUNT) {
//            Intent intent = new Intent(this, EditAccountActivity.class);
//            intent.putExtra("TvShow", account.getName());
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void initiateView() {
//        tvBalance = findViewById(R.id.accBalance);
//        defaultFormat = NumberFormat.getCurrencyInstance();
//
//        Button depositBtn = findViewById(R.id.depositButton);
//        depositBtn.setOnClickListener(view -> generateDialog(R.string.action_deposit));
//
//        Button withdrawBtn = findViewById(R.id.withdrawButton);
//        withdrawBtn.setOnClickListener(view -> generateDialog(R.string.action_withdraw));
//    }
//
//
//    private void generateDialog(final int action) {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        final View view = inflater.inflate(R.layout.account_actions, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(getString(action));
//        alertDialog.setCancelable(false);
//
//
//        final EditText accountMovement = view.findViewById(R.id.account_movement);
//
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Double amount = Double.parseDouble(accountMovement.getText().toString());
//                Toast toast = Toast.makeText(AccountDetailActivity.this, getString(R.string.error_withdraw), Toast.LENGTH_LONG);
//
//
//
//                viewModel.updateAccount(account, new OnAsyncEventListener() {
//                    @Override
//                    public void onSuccess() {
//                        Log.d(TAG, "updateAccount: success");
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        Log.d(TAG, "updateAccount: failure", e);
//                    }
//                });
//            }
//        });
//
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel),
//                (dialog, which) -> alertDialog.dismiss());
//        alertDialog.setView(view);
//        alertDialog.show();
//    }
//}
