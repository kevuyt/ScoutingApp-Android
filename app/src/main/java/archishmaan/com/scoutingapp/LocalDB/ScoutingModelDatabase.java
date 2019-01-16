package archishmaan.com.scoutingapp.LocalDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import archishmaan.com.scoutingapp.Models.ScoutingModelDB;

@Database(entities = {ScoutingModelDB.class}, version = 1, exportSchema = false)
public abstract class ScoutingModelDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}