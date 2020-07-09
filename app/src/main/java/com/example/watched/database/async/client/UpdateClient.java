package com.example.watched.database.async.client;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.util.OnAsyncEventListener;

public class UpdateClient extends AsyncTask<ClientEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener calback;
    private Exception exception;

    public UpdateClient(Application application, OnAsyncEventListener callback) {
        this.application = application;
        calback = callback;
    }

    @Override
    protected Void doInBackground(ClientEntity... params) {
        try {
            for (ClientEntity client : params)
                ((BaseApp) application).getDatabase().clientDao()
                        .update(client);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (calback != null) {
            if (exception == null) {
                calback.onSuccess();
            } else {
                calback.onFailure(exception);
            }
        }
    }
}