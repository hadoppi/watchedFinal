package com.example.watched.database.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.watched.BaseApp;
import com.example.watched.database.async.client.CreateClient;
import com.example.watched.database.async.client.DeleteClient;
import com.example.watched.database.async.client.UpdateClient;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.util.OnAsyncEventListener;

public class ClientRepository {

    private static ClientRepository instance;

    private ClientRepository() {
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            synchronized (AccountRepository.class) {
                if (instance == null) {
                    instance = new ClientRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<ClientEntity> getClient(final String clientId, Application application) {
        return ((BaseApp) application).getDatabase().clientDao().getById(clientId);
    }


    public void insert(final ClientEntity client, OnAsyncEventListener callback,
                       Application application) {
        new CreateClient(application, callback).execute(client);
    }

    public void update(final ClientEntity client, OnAsyncEventListener callback,
                       Application application) {
        new UpdateClient(application, callback).execute(client);
    }

    public void delete(final ClientEntity client, OnAsyncEventListener callback,
                       Application application) {
        new DeleteClient(application, callback).execute(client);
    }
}
