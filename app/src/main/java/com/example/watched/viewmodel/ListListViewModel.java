package com.example.watched.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import java.util.List;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.repository.ListRepository;
import com.example.watched.util.OnAsyncEventListener;

public class ListListViewModel extends AndroidViewModel {

    private Context applicationContext;
    private Application application;
    private ListRepository repository;
    private MediatorLiveData<List<ListEntity>> observableList;
    private MediatorLiveData<ListEntity> observableListByName;
    private LiveData<List<ListEntity>> lists;
    private LiveData<ListEntity> listsByName;


    public ListListViewModel(@NonNull Application application, String name,
                             ListRepository listRepository) {
        super(application);

        applicationContext = application.getApplicationContext();
        this.application = application;
        repository = listRepository;

        observableList = new MediatorLiveData<>();
        observableList.setValue(null);
        lists = repository.getAll(application);
        observableList.addSource(lists, observableList::setValue);

        observableListByName = new MediatorLiveData<>();
        observableListByName.setValue(null);
        listsByName = repository.getByName(name, application);
        observableListByName.addSource(listsByName, observableListByName::setValue);

    }


    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final ListRepository listRepository;
        private String name;

        public Factory(@NonNull Application application) {
            this.application = application;
            listRepository = ((BaseApp) application).getListRepository();
        }

        public Factory(@NonNull Application application, String name) {
            this.application = application;
            this.name = name;
            listRepository = ((BaseApp) application).getListRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ListListViewModel(application, name, listRepository);
        }

    }


    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<ListEntity>> getList() {
        return observableList;
    }

    public LiveData<ListEntity> getListByname() {
        return observableListByName;
    }

    public void deleteList(ListEntity list, OnAsyncEventListener callback) {
        repository.deleteList(list, callback, application);
    }
    public void deleteShowFromList(ListEntity list, OnAsyncEventListener callback) {
        repository.deleteShowFromList(list, callback, application);
    }
    public void createList(ListEntity list, OnAsyncEventListener callback) {
        repository.insert(list, callback, application);
    }
    public void update(ListEntity list, OnAsyncEventListener callback) {
        repository.update(list, callback, application);
    }
}



