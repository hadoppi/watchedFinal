package com.example.watched.database.async.account;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.AccountEntity;
import com.example.watched.util.OnAsyncEventListener;

public class DeleteAccount extends AsyncTask<AccountEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteAccount(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(AccountEntity... params) {
        try {
            for (AccountEntity account : params)
                ((BaseApp) application).getDatabase().accountDao()
                        .delete(account);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}