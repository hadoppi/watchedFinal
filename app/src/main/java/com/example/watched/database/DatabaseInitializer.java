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
        addAccount(db,"BeavisandButt-Head");
        addAccount(db,"TheGoodWife");
        addAccount(db,"Avatar:TheLastAirbender");
        addAccount(db,"Dr.Katz,ProfessionalTherapist");
        addAccount(db,"HappyDays");
        addAccount(db,"Will&amp;Grace");
        addAccount(db,"Justified");
        addAccount(db,"GoldenGirls");
        addAccount(db,"Frasier");
        addAccount(db,"GoodTimes");
        addAccount(db,"Soap");
        addAccount(db,"Rome");
        addAccount(db,"BoardwalkEmpire");
        addAccount(db,"TheRealWorld");
        addAccount(db,"Oz");
        addAccount(db,"RickandMorty");
        addAccount(db,"Alias");
        addAccount(db,"DowntonAbbey");
        addAccount(db,"TheAmericans");
        addAccount(db,"Hannibal");
        addAccount(db,"ER");
        addAccount(db,"I,Claudius");
        addAccount(db,"TheWonderYears");
        addAccount(db,"Survivor");
        addAccount(db,"HouseofCards(US)");
        addAccount(db,"TheMaryTylerMooreShow");
        addAccount(db,"TheShield");
        addAccount(db,"HillStreetBlues");
        addAccount(db,"TheAndyGriffithShow");
        addAccount(db,"TheHoneymooners");
        addAccount(db,"SexandtheCity");
        addAccount(db,"Law&amp;Order");
        addAccount(db,"TheMuppetShow");
        addAccount(db,"FridayNightLights");
        addAccount(db,"Mr.ShowwithBobandDavid");
        addAccount(db,"DoctorWho(963)");
        addAccount(db,"TheOffice(US)");
        addAccount(db,"It'sAlwaysSunnyinPhiladelphia");
        addAccount(db,"Roseanne");
        addAccount(db,"CowboyBebop");
        addAccount(db,"Louie");
        addAccount(db,"FreaksandGeeks");
        addAccount(db,"AlfredHitchcockPresents");
        addAccount(db,"CurbYourEnthusiasm");
        addAccount(db,"Fargo");
        addAccount(db,"BetterCallSaul");
        addAccount(db,"DoctorWho(2005)");
        addAccount(db,"Veep");
        addAccount(db,"Community");
        addAccount(db,"TheOffice(UK)");
        addAccount(db,"StarTrek:DeepSpaceNine");
        addAccount(db,"SanfordandSon");
        addAccount(db,"M*A*S*H");
        addAccount(db,"MysteryScienceTheater3000");
        addAccount(db,"InLivingColor");
        addAccount(db,"LateShowwithDavidLetterman");
        addAccount(db,"ThePrisoner");
        addAccount(db,"Batman:TheAnimatedSeries");
        addAccount(db,"TheLeftovers");
        addAccount(db,"MisterRogers'Neighborhood");
        addAccount(db,"SixFeetUnder");
        addAccount(db,"MontyPython'sFlyingCircus");
        addAccount(db,"TheX-Files");
        addAccount(db,"StarTrek:TheNextGeneration");
        addAccount(db,"Roots");
        addAccount(db,"TwinPeaks");
        addAccount(db,"Futurama");
        addAccount(db,"Friends");
        addAccount(db,"30Rock");
        addAccount(db,"BuffytheVampireSlayer");
        addAccount(db,"NYPDBlue");
        addAccount(db,"Cheers");
        addAccount(db,"Deadwood");
        addAccount(db,"BandofBrothers");
        addAccount(db,"TheDailyShowwithJonStewart");
        addAccount(db,"TheTonightShowwithJohnnyCarson");
        addAccount(db,"TheWestWing");
        addAccount(db,"FawltyTowers");
        addAccount(db,"TheLarrySandersShow");
        addAccount(db,"SesameStreet");
        addAccount(db,"Chapelle'sShow");
        addAccount(db,"BattlestarGalactica");
        addAccount(db,"TheCosbyShow");
        addAccount(db,"SouthPark");
        addAccount(db,"ParksandRecreation");
        addAccount(db,"ArrestedDevelopment");
        addAccount(db,"AllintheFamily");
        addAccount(db,"SaturdayNightLive");
        addAccount(db,"GameofThrones");
        addAccount(db,"Seinfeld");
        addAccount(db,"TheSimpsons");
        addAccount(db,"MadMen");
        addAccount(db,"ILoveLucy");
        addAccount(db,"StarTrek:TheOriginalSeries");
        addAccount(db,"BreakingBad");
        addAccount(db,"TheTwilightZone");
        addAccount(db,"TheSopranos");
        addAccount(db,"TheWire");
        addAccount(db,"TheGoodPlace");


        try {
            // Let's ensure that the tvshows are already stored in the database before we continue.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addEpisode(db, "Episode 1 : Le Réveil","Lost", 1, 45,"Résumé : Un avion, en provenance de Sydney et faisant route vers Los Angeles, s'écrase sur la plage d'une île tropicale, tuant ainsi la plupart de ses passagers mais laissant cependant de nombreux survivants. Jack, un jeune médecin, vient au secours des rescapés, tous aussi terrifiés les uns que les autres. Il fera très vite connaissance avec Kate, celle-ci l'ayant aidé à soigner sa blessure. Alors que les survivants restent sans aucun signe des secours et que la nuit tombe, des bruits mystérieux se font entendre dans la forêt. Le lendemain, Jack accompagné de Kate et de Charlie, bassiste dans un groupe de rock, partent à la recherche du cockpit de l'avion, tombé dans la jungle, espérant ainsi appeler à l'aide. Ils y trouvent le pilote qui les informe que l'avion avait dévié sa trajectoire et sans radio : les secours ne les cherchent donc pas au bon endroit. Ils récupèrent alors le transpondeur mais un bruit effrayant se manifeste à l'extérieur. Jack, Kate et Charlie s'enfuient après que le pilote se soit fait emporter par une créature mystérieuse.") ;
        addEpisode(db, "Episode 2 : Le Nouveau depart ", "Lost", 2, 42, "Résumé : Jack découvre que c’est Kate la prisonnière. Le 4e jour, le groupe sur les hauteurs rejoint la plage après la nuit mais garde le secret sur le SOS ; et ils ont confié l’arme à Kate. Les survivants commencent à s’organiser en vue d’un long séjour. Le marshal est toujours en condition critique, Kate vient le voir et il l’attaque. Le soir, tous sont convaincus que le marshal ne survivra pas. Sawyer tente de le tuer pour abréger ses souffrances mais il lui perfore un poumon ; le marshal survit. Le 5e jour, John trouve Vincent, le chien de Walt.");
        addEpisode(db, "Episode 3 : Les pieds sur Terre", "Lost", 3, 41, "Résumé : Dans la nuit, le camp est secoué par des sangliers sauvages qui dévoraient les cadavres dans la carlingue. Les survivants décident de brûler les corps le lendemain. 6e jour. Sayid veut trianguler le SOS pour en trouver la source. Pour cela, il faut disperser des récepteurs dans l'île. John organise la chasse au sanglier. La chasse tourne mal, Michael est blessé et Kate casse un récepteur. L'incinération des corps est l'occasion d'une cérémonie en souvenir des personnes décédées.");
        addEpisode(db, "Episode 4 :  A la recherche du père ", "Lost", 4, 43, "Résumé : Le 7e jour, Joanna se noie le matin, les survivants ne sont plus que 46. Jack a des visions d’un homme qui s’avère être une hallucination de son père. Ils sont à court d'eau, et quelqu'un vole les quelques bouteilles restantes. Jack poursuit l’hallucination dans la jungle et John part à la recherche d'eau. Jack trouve le cercueil vide dans lequel était son père juste à côté de grottes et d'une source d'eau");
        addEpisode(db, "Episode 5 : Regard vers l’ouest ", "Lost", 5, 39, "Résumé : Le 8e jour, Jin, le mari de Sun attaque Michael sans raison apparente. Jack, Kate, Locke et Charlie partent chercher de l'eau près des grottes où Jack en a trouvé dans l'épisode précédent ; ils se font attaquer par des guêpes, et découvrent là les corps d'un homme et d'une femme qui reposent depuis, selon Jack, 40 ou 50 ans. Jack propose de déménager le camp vers les grottes, mais Sayid et d’autres s’y opposent car ils sont plus visibles sur la plage. On découvre que Sun parle anglais, mais qu'elle le cache de son mari ; on apprend pourquoi Jin a attaqué Michael");
        addEpisode(db, "Episode 1 : La cible ", "TheWire", 1, 39, "Résumé : Le détective James McNulty est contacté par un juge après que le jeune D'Angelo Barksdale est sorti libre du tribunal. Il lui révèle que l'affaire concernant D'Angelo pourrait être liée à une série d'assassinats restés impunis en raison des mesures d'intimidation ou d'élimination des témoins pratiquées par Avon Barksdale et son gang. Barksdale, à la tête d'un immense réseau de trafic de drogue, est toujours parvenu à échapper à la police. Quelque temps plus tard, Cedric Daniels reçoit l'ordre d'ouvrir une enquête. McNulty est, quant à lui, chargé de mener une filature discrète.");
        addEpisode(db, "Episode 2 : Le détachement ", "TheWire",2 , 39, "Résumé : L'homme qui a témoigné contre D'Angelo Barksdale est assassiné ; selon McNulty, ce meurtre a probablement été commandité par Avon Barksdale. McNulty et Moreland coincent D'Angelo et le convainquent d'écrire une lettre d'excuse à la famille de la victime. Il est sur le point de commencer quand son avocat arrive et le libère. Pendant ce temps, une nouvelle recrue arrive...");
        addEpisode(db, "Episode 3 : La poudre aux yeux ", "TheWire",3 , 39, "Résumé : Des investigations matinales menées par trois agents tournent à l'émeute dans une cité du quartier ouest. Une fois de plus, cela fait une très mauvaise publicité à la police. Le lieutenant Daniels est mis sous pression par ses supérieurs, qui lui demandent d'obtenir des résultats au plus vite. Pour satisfaire sa hiérarchie, il procède à des arrestations sans tarder, même s'il ne s'agit que de petits délinquants. Par ailleurs, McNulty et Greggs rendent visite à un agent du FBI afin d'obtenir des moyens de surveillance.");
        addEpisode(db, "Episode 4 : Histoire ancienne ", "TheWire", 4, 39, "Résumé : Au procès, Greggs et McNulty tentent de remonter la filière criminelle en tentant de soutirer aux prévenus des informations sur Avon Barksdale et Stringer Bell. Pendant ce temps, Bodie parvient à tromper les agents de sécurité du centre pour jeunes délinquants et à s'enfuir. Furieux d'avoir été trompé, Avon Barksdale demande à Stinkum, Wee-Bey et Stringer d'annoncer qu'il double la prime pour celui qui éliminera Omar et sa bande.");
        addEpisode(db, "Episode 5 : Le code ", "TheWire", 5, 39, "Résumé : Avon Barksdale est de plus en plus suspicieux; il finit par demander qu'on change toutes les lignes téléphoniques de son appartement. Il propose aussi une promotion à l'un de ses hommes en lui offrant un nouveau territoire. De son côté, Stringer apprend à D'Angelo à gérer son équipe de manière plus rigoureuse, en ne montrant aucune pitié. L'agent McNulty, qui a trouvé un moyen d'intercepter les messages du clan Barksdale, est dérouté par le code qu'emploient les trafiquants. Les inspecteurs se mobilisent pour trouver la clé du code. Quant à Carver et Hauk, ils sont toujours sur les traces de Bodie.");
        addEpisode(db, "Episode 1 : celui qui déménage ", "Friends", 1, 39, "Résumé  Cinq amis new-yorkais se retrouvent dans leur bar favori, le Central Perk. Il y a Ross et Monica, Chandler, Joey et Phoebe. Soudain surgit Rachel, une amie d’enfance de Monica. Elle vient de quitter son fiancé Barry, le jour même de leur mariage. Tous tentent de lui remonter le moral. Ross déprime car son ex-femme est en réalité lesbienne mais l’arrivée de Rachel le remet en course car il était amoureux d’elle lorsqu'il était adolescent. Il commence à chercher des solutions pour la conquérir. Monica sort avec Paul, le sommelier du restaurant où elle travaille. Ce dernier lui avoue qu’il n’a pas fait l’amour depuis plusieurs années, ce qui attendrit Monica qui ne s’attend pas à tomber dans un piège.");
        addEpisode(db, "Episode 2 : Celui qui est perdu ", "Friends", 2, 39, "Résumé Ross apprend que son ex-femme est enceinte. Les parents de Ross et Monica viennent dîner. Comme toujours, les reproches à l’encontre de Monica fusent. Ross leur annonce que son ex-femme est homosexuelle et enceinte de lui. Rachel rend sa bague de fiançailles à Barry. Elle se rend compte qu’il a supporté la rupture bien mieux qu’elle.");
        addEpisode(db, "Episode 3 : Celui qui a un rôle ", "Friends", 3, 39, "Résumé Après avoir aidé Joey pour une audition, Chandler se remet à fumer. Pour la première fois, le nouvel ami de Monica, Alan, est adopté par les autres, à tel point que cela finit même par l’inquiéter. Phoebe voit son compte en banque crédité par erreur de mille dollars, qu’elle offre à Lizzy, une clocharde. Pour la remercier, celle-ci lui offre une canette de soda, où Phoebe trouve un pouce… ce qui lui fait gagner sept mille dollars supplémentaires en dédommagement ! Chandler est tellement déprimé par la rupture d’Alan et Monica qu’il se remet à fumer. Phoebe lui propose les sept mille dollars qu’elle a gagnés s’il arrête. Il accepte avec empressement.");
        addEpisode(db, "Episode 4 : Celui avec George ", "Friends", 4, 39, "Résumé : Monica et Phoebe décident d’organiser une soirée pyjama. Lors de la soirée, Rachel se joint à elles et les fait déprimer car elles se rendent compte qu’elles n’ont pas de plan pour leurs vies futures. Plus tard, elles s’aperçoivent que leur voisin n’est autre que George Stephanopoulos, l’ancien conseiller de Bill Clinton. Ross est très triste car c’est l’anniversaire de sa première fois avec son ex-femme. Les garçons décident de l’emmener voir un match de hockey mais Ross, finit à l’hôpital car il reçoit le palet dans le nez.");
        addEpisode(db, "Episode 5 Celui qui lave plus blanc ", "Friends", 5, 39, "Résumé : Phoebe et Chandler décident de rompre ensemble avec leurs relations respectives. Si la tâche s’avère aisée pour Phoebe, Chandler a néanmoins plus de mal à rompre avec Janice. Ross et Rachel vont faire leur lessive ensemble à la laverie automatique. Une dispute avec une femme envahissante va donner l’occasion à Rachel de s’affirmer. Joey et Monica, après quelques malentendus, décident de faire rompre un couple lors d’un dîner. En effet, Joey veut récupérer son ex-copine maintenant recasée et fait croire à Monica qu’elle est venue au dîner avec son frère pour que cette dernière séduise son nouveau petit ami.");
        addEpisode(db, "Episode 1 : tout va bien ", "TheGoodPlace",1 , 39, "Résumé Quand Eleanor meurt et se retrouve au bon endroit dans un au-delà utopique réservé aux personnes probes, elle comprend vite qu'il y a une erreur sur la personne.");
        addEpisode(db, "Episode 2 : je voulais voler ", "TheGoodPlace", 2, 39, "Résumé Eleanor demande à Chidi de lui montrer comment devenir meilleure pour gagner sa place au Bon Endroit, alors il insiste pour qu'elle participe au nettoyage du quartier au lieu d'apprendre à voler.");
        addEpisode(db, "Episode 3 : Tahani Al Jamil ", "TheGoodPlace", 3, 39, "Résumé : Eleanor croit que Tahani a signé le message qu'elle a reçu et connaît son secret. Michael propose un nouveau passe-temps à Chidi afin d'élargir ses possibilités au-delà de la philosophie et de l'éthique. Michael essaie également d'ajuster la personnalité de Janet mais sans résultats probants.");
        addEpisode(db, "Episode 4 Jason Mendoza ", "TheGoodPlace", 4, 39, "Résumé Eleanor découvre que Jianyu s'appelle Jason Mendoza et qu'il mérite encore moins qu'elle d'être au Bon Endroit. Tahani organise un gala pour l'ouverture d'un nouveau restaurant.");
        addEpisode(db, "Episode 5 : Alerte catastrophe apocalyptique de niveau 55", "TheGoodPlace", 5, 39, "Résumé Chidi ne supporte plus de passer ses journées à donner des cours à Eleanor. Michael met le quartier en quarantaine le temps de réparer le mystérieux gouffre qui menace le Bon Endroit. Enfermée chez elle avec de nombreux voisins, Tahani apprend qu'elle n'est pas aussi appréciée qu'elle le pensait et fait tout pour remonter dans l'estime des gens.");
//        addEpisode(db, "", "", , 39, "");

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
