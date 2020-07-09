package com.example.watched.database.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.watched.BaseApp;
import com.example.watched.database.async.account.CreateAccount;
import com.example.watched.database.async.account.DeleteAccount;
import com.example.watched.database.async.account.UpdateAccount;
import com.example.watched.database.entity.AccountEntity;
import com.example.watched.util.OnAsyncEventListener;

public class AccountRepository {

    private static AccountRepository instance;

    private AccountRepository() {

    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            synchronized (AccountRepository.class) {
                if (instance == null) {
                    instance = new AccountRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<AccountEntity> getAccount(final String name, Application application) {
        return ((BaseApp) application).getDatabase().accountDao().getByName(name);
    }

    public LiveData<List<AccountEntity>> getAccounts(Context application) {
        return ((BaseApp) application).getDatabase().accountDao().getAll();
    }




    public void insert(final AccountEntity account, OnAsyncEventListener callback,
                       Application application) {
        new CreateAccount(application, callback).execute(account);
    }

    public void update(final AccountEntity account, OnAsyncEventListener callback,
                       Application application) {
        new UpdateAccount(application, callback).execute(account);
    }

    public void delete(final AccountEntity account, OnAsyncEventListener callback,
                       Application application) {
        new DeleteAccount(application, callback).execute(account);
    }


}
