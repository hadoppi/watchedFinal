package com.example.watched.viewmodel.tvShow;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.database.repository.TvShowRepository;
import com.example.watched.util.OnAsyncEventListener;

public class TvShowViewModel extends AndroidViewModel {

    private Application application;

    private TvShowRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<TvShowEntity> observableAccount;

    public TvShowViewModel(@NonNull Application application,
                           final String name, TvShowRepository tvShowRepository) {
        super(application);

        this.application = application;

        repository = tvShowRepository;

        observableAccount = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableAccount.setValue(null);

        LiveData<TvShowEntity> account = repository.getAccount(name, application);

        // observe the changes of the account entity from the database and forward them
        observableAccount.addSource(account, observableAccount::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String name;

        private final TvShowRepository repository;

        public Factory(@NonNull Application application, String name) {
            this.application = application;
            this.name = name;
            repository = ((BaseApp) application).getTvShowRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TvShowViewModel(application, name, repository);
        }
    }

    /**
     * Expose the LiveData AccountEntity query so the UI can observe it.
     */
    public LiveData<TvShowEntity> getTvShow() {
        return observableAccount;
    }

    public void createTvShow(TvShowEntity tvShow, OnAsyncEventListener callback) {
        repository.insert(tvShow, callback, application);
    }

    public void updateTvShow(TvShowEntity tvShow, OnAsyncEventListener callback) {
        repository.update(tvShow, callback, application);
    }
}