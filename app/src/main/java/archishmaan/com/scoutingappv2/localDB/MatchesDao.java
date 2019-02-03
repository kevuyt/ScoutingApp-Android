package archishmaan.com.scoutingappv2.localDB;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface MatchesDao {
    @Query("SELECT COUNT(*) FROM matches")
    public int getCount();
    @Query("SELECT * FROM matches")
    public List<Matches> getAll();
    @Query("SELECT * FROM matches WHERE match_Number LIKE :matchNumber")
    public List<Matches> findMatch(int matchNumber);
    @Insert
    public void InsertMatch(Matches matches);
    @Delete
    public void DeleteMatch(Matches matches);

}
