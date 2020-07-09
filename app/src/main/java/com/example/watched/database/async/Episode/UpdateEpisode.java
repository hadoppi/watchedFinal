package com.example.watched.database.async.Episode;

import android.app.Application;
import android.os.AsyncTask;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.util.OnAsyncEventListener;

public class UpdateEpisode extends AsyncTask<EpisodeEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener calback;
    private Exception exception;

    public UpdateEpisode(Application application, OnAsyncEventListener callback) {
        this.application = application;
        calback = callback;
    }

    @Override
    protected Void doInBackground(EpisodeEntity... params) {
        try {
            for (EpisodeEntity Episode : params)
                ((BaseApp) application).getDatabase().episodeDao()
                        .update(Episode);
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