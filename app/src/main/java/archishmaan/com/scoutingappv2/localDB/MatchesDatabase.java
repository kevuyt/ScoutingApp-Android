package archishmaan.com.scoutingappv2.localDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1, entities = {Matches.class})
public abstract class MatchesDatabase extends RoomDatabase {
    abstract public MatchesDao matchesDao();
}
