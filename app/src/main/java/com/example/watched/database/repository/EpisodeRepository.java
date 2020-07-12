package com.example.watched.database.repository;

import android.app.Application;
import android.util.Pair;

import androidx.lifecycle.LiveData;

import com.example.watched.BaseApp;
import com.example.watched.database.async.Episode.CreateEpisode;
import com.example.watched.database.async.Episode.DeleteEpisode;
import com.example.watched.database.async.Episode.UpdateEpisode;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.util.OnAsyncEventListener;

import java.util.List;

public class EpisodeRepository {

    private static EpisodeRepository instance;

    private EpisodeRepository() {

    }

    public static EpisodeRepository getInstance() {
        if (instance == null) {
            synchronized (EpisodeRepository.class) {
                if (instance == null) {
                    instance = new EpisodeRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<EpisodeEntity>> getAllSeen (Application application){
        return ((BaseApp) application).getDatabase().episodeDao().getAllSeen(true);
    }
    public LiveData<List<EpisodeEntity>> getAllNotSeen (Application application){
        return ((BaseApp) application).getDatabase().episodeDao().getAllNotSeen(true);
    }
    public LiveData<EpisodeEntity> getEpisode(final Long EpisodeId, Application application) {
        return ((BaseApp) application).getDatabase().episodeDao().getById(EpisodeId);
    }

    public LiveData<List<EpisodeEntity>> getEpisodes(Application application) {
        return ((BaseApp) application).getDatabase().episodeDao().getAll();
    }

    public LiveData<List<EpisodeEntity>> getByTvShow(final String tvShow, Application application) {
        return ((BaseApp) application).getDatabase().episodeDao().getAllEpisodeFromShow(tvShow);
    }

    public void insert(final EpisodeEntity Episode, OnAsyncEventListener callback,
                       Application application) {
        new CreateEpisode(application, callback).execute(Episode);
    }

    public void update(final EpisodeEntity Episode, OnAsyncEventListener callback,
                       Application application) {
        new UpdateEpisode(application, callback).execute(Episode);
    }

    public void delete(final EpisodeEntity Episode, OnAsyncEventListener callback,
                       Application application) {
        new DeleteEpisode(application, callback).execute(Episode);
    }


}
