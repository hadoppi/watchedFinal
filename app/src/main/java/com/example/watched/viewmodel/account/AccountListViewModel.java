package com.example.watched.viewmodel.account;

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
import com.example.watched.database.entity.AccountEntity;
import com.example.watched.database.pojo.TvShowWithEpisodes;
import com.example.watched.database.repository.AccountRepository;
import com.example.watched.database.repository.ClientRepository;
import com.example.watched.util.OnAsyncEventListener;

public class AccountListViewModel extends AndroidViewModel {

    private Context applicationContext;
    private Application application;
    private AccountRepository repository;
    private final MediatorLiveData<List<AccountEntity>> observableShow;
    private final MediatorLiveData<List<AccountEntity>> observableShowId;

    public AccountListViewModel(@NonNull Application application,
                                AccountRepository accountRepository) {
        super(application);

        applicationContext = application.getApplicationContext();
        this.application = application;
        repository = accountRepository;
        observableShow = new MediatorLiveData<>();
        observableShowId = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableShow.setValue(null);
        observableShowId.setValue(null);

        LiveData<List<AccountEntity>> tvShows = repository.getAccounts(applicationContext);
//        LiveData<List<TvShowWithEpisodes>> tvshowsIDId = repository.getAccounts(applicationContext);

        // observe the changes of the entities from the database and forward them
        observableShow.addSource(tvShows, observableShow::setValue);
//        observableShowId.addSource(tvShowsID, observableShowId::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final AccountRepository accountRepository;

        public Factory(@NonNull Application application ){
            this.application = application;
            accountRepository = AccountRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AccountListViewModel(application, accountRepository);
        }
    }


    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<AccountEntity>> getAccounts() {
        return observableShow;
    }

    public void deleteAccount(AccountEntity account, OnAsyncEventListener callback) {
        repository.delete(account, callback, application);
    }


}
