package archishmaan.com.scoutingapp.LocalDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.Models.ScoutingModelDB;

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySingleScoutingModelDB (ScoutingModelDB scoutingModel);
    @Insert
    void insertMultipleScoutingModelDBs (List<ScoutingModelDB> matches);
    @Query("SELECT * FROM ScoutingModelDB WHERE KeyID = :keyID")
    ScoutingModelDB fetchOneScoutingModelDBbyKeyID(int keyID);
    @Query("SELECT COUNT(*) FROM ScoutingModelDB")
    Integer fetchCountScoutingModelDB();
    @Update
    void updateScoutingModelDB (ScoutingModelDB scoutingModeDB);
    @Delete
    void deleteScoutingModelDB (ScoutingModelDB scoutingModeDB);
}
