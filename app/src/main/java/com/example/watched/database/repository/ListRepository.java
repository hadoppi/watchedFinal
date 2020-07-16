package com.example.watched.database.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.watched.BaseApp;
import com.example.watched.database.async.list.CreateList;
import com.example.watched.database.async.list.DeleteList;
import com.example.watched.database.async.list.UpdateList;
import com.example.watched.database.async.tvShow.CreateTvShow;
import com.example.watched.database.async.tvShow.DeleteTvShow;
import com.example.watched.database.async.tvShow.UpdateTvShow;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.util.OnAsyncEventListener;

import java.util.List;

public class ListRepository {

    private static ListRepository instance;

    private ListRepository() {

    }

    public static ListRepository getInstance() {
        if (instance == null) {
            synchronized (ListRepository.class) {
                if (instance == null) {
                    instance = new ListRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<ListEntity> getByName(final String name, Context application) {
        return ((BaseApp) application).getDatabase().listDao().getByName(name);
    }
    public LiveData<List<String>> getAllName( Context application) {
        return ((BaseApp) application).getDatabase().listDao().getAllName();
    }
    public LiveData<List<ListEntity>> getByOwner(final String owner, Context application) {
        return ((BaseApp) application).getDatabase().listDao().getByOwner(owner);
    }
    public LiveData<List<ListEntity>> getAll(Application application) {
        return ((BaseApp) application).getDatabase().listDao().getAll();
    }

    public void insert(final ListEntity list, OnAsyncEventListener callback,
                       Application application) {
        new CreateList(application, callback).execute(list);
    }

    public void update(final ListEntity list, OnAsyncEventListener callback,
                       Application application) {
        new UpdateList(application, callback).execute(list);
    }

    public void deleteList(final ListEntity list, OnAsyncEventListener callback,
                       Application application) {
        new DeleteList(list.getName(), application, callback).execute(list);
    }


    public void deleteShowFromList(ListEntity list, OnAsyncEventListener callback, Application application) {
        new DeleteList(list.getName(), list.getFavoriteShows(), application, callback).execute(list);
    }
}
