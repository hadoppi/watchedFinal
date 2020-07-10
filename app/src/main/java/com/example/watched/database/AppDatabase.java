package com.example.watched.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

import com.example.watched.database.dao.ListDao;
import com.example.watched.database.dao.TvShowDao;
import com.example.watched.database.dao.ClientDao;
import com.example.watched.database.dao.EpisodeDao;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.database.repository.ListRepository;

@Database(entities = {TvShowEntity.class, ClientEntity.class, EpisodeEntity.class, ListEntity.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";
    private static final String DATABASE_NAME = "oppi_Database";
    private static AppDatabase instance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDatabase database = AppDatabase.getInstance(appContext);
                            initializeDemoData(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                .build();
    }

    public static void initializeDemoData(final AppDatabase database) {
        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                Log.i(TAG, "Wipe database.");
                database.clientDao().deleteAll();
                database.accountDao().deleteAll();
                database.episodeDao().deleteAll();
                database.listDao().deleteAll();


                DatabaseInitializer.populateDatabase(database);
            });
        });
    }

    public abstract TvShowDao accountDao();

    public abstract ClientDao clientDao();

    public abstract EpisodeDao episodeDao();

    public abstract ListDao listDao();

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }}


