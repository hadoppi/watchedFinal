package com.example.watched.database.async.tvShow;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.util.OnAsyncEventListener;

public class DeleteTvShow extends AsyncTask<TvShowEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteTvShow(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TvShowEntity... params) {
        try {
            for (TvShowEntity account : params)
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