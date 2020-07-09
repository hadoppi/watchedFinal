package com.example.watched.database.async.Episode;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.util.OnAsyncEventListener;

public class CreateEpisode extends AsyncTask<EpisodeEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateEpisode(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(EpisodeEntity... params) {
        try {
            for (EpisodeEntity Episode : params)
                ((BaseApp) application).getDatabase().episodeDao()
                        .insert(Episode);
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
