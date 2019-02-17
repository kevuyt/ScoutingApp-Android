package archishmaan.com.scoutingappv2.LocalDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Matches.class}, version = 1)
public abstract class MatchesDatabase extends RoomDatabase {

    public abstract MatchesDao matchesDao();

}
