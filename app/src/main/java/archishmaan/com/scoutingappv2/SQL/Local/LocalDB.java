package archishmaan.com.scoutingappv2.SQL.Local;

import android.arch.persistence.room.Room;
import android.content.Context;

public class LocalDB {
    private MatchesDatabase matchesDatabase;
    private MatchesDao matchesDao;
    private static LocalDB instance;
    private static Context context;
    private LocalDB () {
        matchesDatabase = Room.databaseBuilder(context, MatchesDatabase.class, "matches").build();
        matchesDao = matchesDatabase.getMatchesDao();
        instance = this;
    }

    public static LocalDB getInstance() {
        return instance;
    }

    public static void setContext(Context context1) {
        context = context1;
    }

}
