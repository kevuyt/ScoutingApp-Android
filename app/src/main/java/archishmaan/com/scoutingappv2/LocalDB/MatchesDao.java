package archishmaan.com.scoutingappv2.LocalDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMatch(Matches matches);



    @Query("SELECT * FROM Matches")
    List<Matches> getAll();

    @Query("Select * FROM Matches WHERE :matchNumber = matchNumber")
    List<Matches> getMatch(int matchNumber);

    @Update
    void updateMatch(Matches matches);

    @Delete
    void deleteMatch(Matches matches);

}
