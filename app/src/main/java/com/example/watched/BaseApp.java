package com.example.watched;

import android.app.Application;

import com.example.watched.database.AppDatabase;
import com.example.watched.database.repository.AccountRepository;
import com.example.watched.database.repository.ClientRepository;
import com.example.watched.database.repository.EpisodeRepository;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public AccountRepository getAccountRepository() {

        return AccountRepository.getInstance();
    }

    public ClientRepository getClientRepository() {
        return ClientRepository.getInstance();
    }
    public EpisodeRepository getEpisodeRepository() {
        return EpisodeRepository.getInstance();
    }
}