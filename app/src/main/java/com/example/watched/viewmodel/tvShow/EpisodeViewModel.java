package com.example.watched.viewmodel.tvShow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.database.repository.EpisodeRepository;
import com.example.watched.database.repository.TvShowRepository;
import com.example.watched.util.OnAsyncEventListener;

public class EpisodeViewModel extends AndroidViewModel {

    private Application application;

    private EpisodeRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<EpisodeEntity> observableAccount;

    public EpisodeViewModel(@NonNull Application application,
                            final Long id, EpisodeRepository episodeRepository) {
        super(application);

        this.application = application;

        repository = episodeRepository;

        observableAccount = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableAccount.setValue(null);

        LiveData<EpisodeEntity> episode = repository.getEpisode(id, application);

        // observe the changes of the account entity from the database and forward them
        observableAccount.addSource(episode, observableAccount::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final Long id;

        private final EpisodeRepository repository;

        public Factory(@NonNull Application application, Long id) {
            this.application = application;
            this.id = id;
            repository = ((BaseApp) application).getEpisodeRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new EpisodeViewModel(application, id, repository);
        }
    }

    /**
     * Expose the LiveData AccountEntity query so the UI can observe it.
     */
    public LiveData<EpisodeEntity> getEpisode() {
        return observableAccount;
    }

    public void createEpisode(EpisodeEntity episode, OnAsyncEventListener callback) {
        repository.insert(episode, callback, application);
    }

    public void updateEpisode(EpisodeEntity account, OnAsyncEventListener callback) {
        repository.update(account, callback, application);
    }
}