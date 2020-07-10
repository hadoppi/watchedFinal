package com.example.watched.database.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.watched.BaseApp;
import com.example.watched.database.async.tvShow.CreateTvShow;
import com.example.watched.database.async.tvShow.DeleteTvShow;
import com.example.watched.database.async.tvShow.UpdateTvShow;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.util.OnAsyncEventListener;

public class TvShowRepository {

    private static TvShowRepository instance;

    private TvShowRepository() {

    }

    public static TvShowRepository getInstance() {
        if (instance == null) {
            synchronized (TvShowRepository.class) {
                if (instance == null) {
                    instance = new TvShowRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<TvShowEntity> getAccount(final String name, Application application) {
        return ((BaseApp) application).getDatabase().accountDao().getByName(name);
    }

    public LiveData<List<TvShowEntity>> getAccounts(Context application) {
        return ((BaseApp) application).getDatabase().accountDao().getAll();
    }




    public void insert(final TvShowEntity account, OnAsyncEventListener callback,
                       Application application) {
        new CreateTvShow(application, callback).execute(account);
    }

    public void update(final TvShowEntity account, OnAsyncEventListener callback,
                       Application application) {
        new UpdateTvShow(application, callback).execute(account);
    }

    public void delete(final TvShowEntity account, OnAsyncEventListener callback,
                       Application application) {
        new DeleteTvShow(application, callback).execute(account);
    }


}
