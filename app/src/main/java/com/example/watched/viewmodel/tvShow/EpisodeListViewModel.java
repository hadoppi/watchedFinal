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
import com.example.watched.database.repository.EpisodeRepository;
import com.example.watched.util.OnAsyncEventListener;

import java.util.List;

public class EpisodeListViewModel extends AndroidViewModel {

    private Application application;

    private EpisodeRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<EpisodeEntity>> observableOwnEpisodes;

    public EpisodeListViewModel(@NonNull Application application,
                                final String name,
                                EpisodeRepository EpisodeRepository) {
        super(application);

        this.application = application;

        repository = EpisodeRepository;

        observableOwnEpisodes = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableOwnEpisodes.setValue(null);


        LiveData<List<EpisodeEntity>> ownEpisodes = repository.getByTvShow(name, application);

        // observe the changes of the entities from the database and forward them
        observableOwnEpisodes.addSource(ownEpisodes, observableOwnEpisodes::setValue);
    }

    /**
     * A creator is used to inject the Episode id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String nameTvShow;

        private final EpisodeRepository EpisodeRepository;


        public Factory(@NonNull Application application, String nameTvShow) {
            this.application = application;
            this.nameTvShow = nameTvShow;
            EpisodeRepository = ((BaseApp) application).getEpisodeRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new EpisodeListViewModel(application, nameTvShow, EpisodeRepository);
        }
    }



    /**
     * Expose the LiveData EpisodeEntities query so the UI can observe it.
     */
    public LiveData<List<EpisodeEntity>> getOwnEpisodes() {
        return observableOwnEpisodes;
    }

    public void deleteEpisode(EpisodeEntity Episode, OnAsyncEventListener callback) {
        repository.delete(Episode, callback, application);
    }


}
