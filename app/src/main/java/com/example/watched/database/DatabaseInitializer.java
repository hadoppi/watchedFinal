package com.example.watched.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.entity.TvShowEntity;
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
        TvShowEntity account = new TvShowEntity(name);
        db.accountDao().insert(account);
    }

    private static void addEpisode(final AppDatabase db, final String name, final String tvShow, final int numberEpisode,
                                   final int time, final String synopsis) {
        EpisodeEntity episode = new EpisodeEntity(tvShow, name, numberEpisode, time, synopsis);
        db.episodeDao().insert(episode);
    }
    private static void addList(final AppDatabase db, final String name, final String owner, final String fav
    ) {
        ListEntity list = new ListEntity(name, owner, fav);
        db.listDao().insert(list);
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
        addAccount(db, "	Beavis and Butt-Head	");
        addAccount(db, "	The Good Wife	");
        addAccount(db, "	Avatar: The Last Airbender	");
        addAccount(db, "	Dr. Katz, Professional Therapist	");
        addAccount(db, "	Happy Days	");
        addAccount(db, "	Will &amp; Grace	");
        addAccount(db, "	Justified	");
        addAccount(db, "	Golden Girls	");
        addAccount(db, "	Frasier	");
        addAccount(db, "	Good Times	");
        addAccount(db, "	Soap	");
        addAccount(db, "	Rome	");
        addAccount(db, "	Boardwalk Empire	");
        addAccount(db, "	The Real World	");
        addAccount(db, "	Oz	");
        addAccount(db, "	Rick and Morty	");
        addAccount(db, "	Alias	");
        addAccount(db, "	Downton Abbey	");
        addAccount(db, "	The Americans	");
        addAccount(db, "	Hannibal	");
        addAccount(db, "	ER	");
        addAccount(db, "	I, Claudius	");
        addAccount(db, "	The Wonder Years	");
        addAccount(db, "	Survivor	");
        addAccount(db, "	House of Cards (US)	");
        addAccount(db, "	The Mary Tyler Moore Show	");
        addAccount(db, "	The Shield	");
        addAccount(db, "	Hill Street Blues	");
        addAccount(db, "	The Andy Griffith Show	");
        addAccount(db, "	The Honeymooners	");
        addAccount(db, "	Sex and the City	");
        addAccount(db, "	Law &amp; Order	");
        addAccount(db, "	The Muppet Show	");
        addAccount(db, "	Friday Night Lights	");
        addAccount(db, "	Mr. Show with Bob and David	");
        addAccount(db, "	Doctor Who (963)	");
        addAccount(db, "	The Office (US)	");
        addAccount(db, "	It's Always Sunny in Philadelphia	");
        addAccount(db, "	Roseanne	");
        addAccount(db, "	Cowboy Bebop	");
        addAccount(db, "	Louie	");
        addAccount(db, "	Freaks and Geeks	");
        addAccount(db, "	Alfred Hitchcock Presents	");
        addAccount(db, "	Curb Your Enthusiasm	");
        addAccount(db, "	Fargo	");
        addAccount(db, "	Better Call Saul	");
        addAccount(db, "	Doctor Who (2005)	");
        addAccount(db, "	Veep	");
        addAccount(db, "	Community	");
        addAccount(db, "	The Office (UK)	");
        addAccount(db, "	Star Trek: Deep Space Nine	");
        addAccount(db, "	Sanford and Son	");
        addAccount(db, "	M*A*S*H	");
        addAccount(db, "	Mystery Science Theater 3000	");
        addAccount(db, "	In Living Color	");
        addAccount(db, "	Late Show with David Letterman	");
        addAccount(db, "	The Prisoner	");
        addAccount(db, "	Batman: The Animated Series	");
        addAccount(db, "	The Leftovers	");
        addAccount(db, "	Mister Rogers' Neighborhood	");
        addAccount(db, "	Six Feet Under	");
        addAccount(db, "	Monty Python's Flying Circus	");
        addAccount(db, "	The X-Files	");
        addAccount(db, "	Star Trek: The Next Generation	");
        addAccount(db, "	Roots	");
        addAccount(db, "	Twin Peaks	");
        addAccount(db, "	Futurama	");
        addAccount(db, "	Friends	");
        addAccount(db, "	30 Rock	");
        addAccount(db, "	Buffy the Vampire Slayer	");
        addAccount(db, "	NYPD Blue	");
        addAccount(db, "	Cheers	");
        addAccount(db, "	Deadwood	");
        addAccount(db, "	Band of Brothers	");
        addAccount(db, "	The Daily Show with Jon Stewart	");
        addAccount(db, "	The Tonight Show with Johnny Carson	");
        addAccount(db, "	The West Wing	");
        addAccount(db, "	Fawlty Towers	");
        addAccount(db, "	The Larry Sanders Show	");
        addAccount(db, "	Sesame Street	");
        addAccount(db, "	Chapelle's Show	");
        addAccount(db, "	Battlestar Galactica	");
        addAccount(db, "	The Cosby Show	");
        addAccount(db, "	South Park	");
        addAccount(db, "	Parks and Recreation	");
        addAccount(db, "	Arrested Development	");
        addAccount(db, "	All in the Family	");
        addAccount(db, "	Saturday Night Live	");
        addAccount(db, "	Game of Thrones	");
        addAccount(db, "	Seinfeld	");
        addAccount(db, "	The Simpsons	");
        addAccount(db, "	Mad Men	");
        addAccount(db, "	I Love Lucy	");
        addAccount(db, "	Star Trek: The Original Series	");
        addAccount(db, "	Breaking Bad	");
        addAccount(db, "	The Twilight Zone	");
        addAccount(db, "	The Sopranos	");
        addAccount(db, "The Wire");

        try {
            // Let's ensure that the tvshows are already stored in the database before we continue.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addEpisode(db, "le commencement", "Lost", 1, 45, "Au début c'était chouette mais finalement ils ont dû tous partir");
        addEpisode(db, "le retour", "Lost", 2, 42, "mais ils sont revenus tkt ");
        addEpisode(db, "la vie d'arthur", "Lost", 3, 41, "arthur raconte son épopée");
        addEpisode(db, "la mère du fils", "Lost", 4, 43, "totologie par excellence, ");
        addEpisode(db, "la fille du père", "Lost", 5, 39, "elle n'est pas celle qu'on croit");


        try {
            // Let's ensure that the tvshows are already stored in the database before we continue.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addList(db, "action","m.p@fifa.com",  "Lost");
        addList(db, "action","m.p@fifa.com", "The Wire" );
        addList(db, "romantique","m.p@fifa.com", "The Twilight Zone");
        addList(db, "romantique","m.p@fifa.com", "I Love Lucy" );

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
