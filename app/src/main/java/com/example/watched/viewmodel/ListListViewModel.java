package com.example.watched.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.database.repository.ListRepository;
import com.example.watched.util.OnAsyncEventListener;

import java.util.List;

public class ListListViewModel extends AndroidViewModel {

    private Context applicationContext;
    private Application application;
    private ListRepository repository;
    private final MediatorLiveData<List<ListEntity>> observableList;
    private final MediatorLiveData<List<ListEntity>> observableListId;

    public ListListViewModel(@NonNull Application application, final String owner,
                             ListRepository listRepository) {
        super(application);

        applicationContext = application.getApplicationContext();
        this.application = application;
        repository = listRepository;
        observableList = new MediatorLiveData<>();
        observableListId = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableList.setValue(null);
        observableListId.setValue(null);

        LiveData<List<ListEntity>> lists = repository.getByOwner(owner, this.application);

        // observe the changes of the entities from the database and forward them
        observableList.addSource(lists, observableListId::setValue);

    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final ListRepository listRepository;
        private final String owner;

        public Factory(@NonNull Application application, String owner, String owner1){
            this.application = application;
            this.owner = owner;
            listRepository = ListRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ListListViewModel(application, owner, listRepository);
        }
    }


    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<ListEntity>> getAccounts() {
        return observableList;
    }

    public void deleteAccount(ListEntity list, OnAsyncEventListener callback) {
        repository.delete(list, callback, application);
    }


}
