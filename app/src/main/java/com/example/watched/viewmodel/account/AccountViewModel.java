package com.example.watched.viewmodel.account;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.watched.BaseApp;
import com.example.watched.database.entity.AccountEntity;
import com.example.watched.database.repository.AccountRepository;
import com.example.watched.util.OnAsyncEventListener;

public class AccountViewModel  extends AndroidViewModel {

    private Application application;

    private AccountRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<AccountEntity> observableAccount;

    public AccountViewModel(@NonNull Application application,
                                   final String name, AccountRepository accountRepository) {
        super(application);

        this.application = application;

        repository = accountRepository;

        observableAccount = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableAccount.setValue(null);

        LiveData<AccountEntity> account = repository.getAccount(name, application);

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

        private final AccountRepository repository;

        public Factory(@NonNull Application application, String name) {
            this.application = application;
            this.name = name;
            repository = ((BaseApp) application).getAccountRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AccountViewModel(application, name, repository);
        }
    }

    /**
     * Expose the LiveData AccountEntity query so the UI can observe it.
     */
    public LiveData<AccountEntity> getAccount() {
        return observableAccount;
    }

    public void createAccount(AccountEntity account, OnAsyncEventListener callback) {
        repository.insert(account, callback, application);
    }

    public void updateAccount(AccountEntity account, OnAsyncEventListener callback) {
        repository.update(account, callback, application);
    }
}