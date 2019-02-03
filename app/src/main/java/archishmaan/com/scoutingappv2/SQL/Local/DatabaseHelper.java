package archishmaan.com.scoutingappv2.SQL.Local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Archishmaan Peyyety on 1/19/19.
 * Project: ScoutingAppV2
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "scouting_table";
    private static final String ID = "ID";
    private static final String MATCH_NUMBER = "match_num";
    private static final String TEAM_NUMBER = "team_num";
    private static final String DEPOT = "depot";
    private static final String LANDER = "lander";
    private static final String AUTO_DROP = "auto_drop";
    private static final String AUTO_MARKER = "auto_marker";
    private static final String AUTO_SAMPLE = "auto_sample";
    private static final String AUTO_DOUBLE_SAMPLE = "auto_double_sample";
    private static final String ENDD_HANG = "end_hang";
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MATCH_NUMBER + " INTEGER, " +"";

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
