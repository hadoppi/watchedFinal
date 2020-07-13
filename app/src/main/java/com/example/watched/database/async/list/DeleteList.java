package com.example.watched.database.async.list;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.util.OnAsyncEventListener;

public class DeleteList extends AsyncTask<ListEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;
    private String name;
    private String favShow;

    public DeleteList(String name, Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
        this.name = name;
        this.favShow = "deleteAllList";
    }

    public DeleteList(String name, String favShow, Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
        this.name = name;
        this.favShow = favShow;
    }

    @Override
    protected Void doInBackground(ListEntity... params) {
        try {
            for (ListEntity list : params)
                if (this.favShow.equals("deleteAllList"))
                    ((BaseApp) application).getDatabase().listDao()
                            .deleteAllList(name);
                else ((BaseApp) application).getDatabase().listDao()
                        .delete(name, favShow);

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