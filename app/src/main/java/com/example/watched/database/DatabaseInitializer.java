package com.example.watched.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.watched.database.entity.AccountEntity;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.database.entity.EpisodeEntity;

/**
 * Generates dummy data
 */
public class DatabaseInitializer {

    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addClient(final AppDatabase db, final String email, final String firstName,
                                  final String lastName, final String password) {
        ClientEntity client = new ClientEntity(email, firstName, lastName, password);
        db.clientDao().insert(client);
    }

    private static void addAccount(final AppDatabase db, final String name
    ) {
        AccountEntity account = new AccountEntity(name);
        db.accountDao().insert(account);
    }

    private static void addEpisode(final AppDatabase db, final String name, final String tvShow, final int numberEpisode,
                                   final int time) {
        EpisodeEntity episode = new EpisodeEntity(tvShow, name, numberEpisode, time);
        db.episodeDao().insert(episode);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.clientDao().deleteAll();

        addClient(db,
                "m.p@fifa.com", "Michel", "Platini", "michel1"
        );
        addClient(db,
                "s.b@fifa.com", "Sepp", "Blatter", "sepp1"
        );
        addClient(db,
                "e.s@fifa.com", "Ebbe", "Schwartz", "ebbe1"
        );
        addClient(db,
                "a.c@fifa.com", "Aleksander", "Ceferin", "aleksander1"
        );

        try {
            // Let's ensure that the clients are already stored in the database before we continue.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addAccount(db,
                "Lost"
        );
        addAccount(db,
                "Young Sheldon"
        );

        try {
            // Let's ensure that the tvshows are already stored in the database before we continue.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addEpisode(db, "le commencement", "Lost", 1, 45);
        addEpisode(db, "le retour", "Lost", 2, 42);
        addEpisode(db, "la vie d'arthur", "Lost", 3, 41);
        addEpisode(db, "la mère du fils", "Lost", 4, 43);
        addEpisode(db, "la fille du père", "Lost", 5, 39);

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }

    }
}
