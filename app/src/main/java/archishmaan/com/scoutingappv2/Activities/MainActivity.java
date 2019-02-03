package archishmaan.com.scoutingappv2.Activities;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;
import archishmaan.com.scoutingappv2.SQL.Local.LocalDB;
import archishmaan.com.scoutingappv2.SQL.Local.MatchesDatabase;

import static archishmaan.com.scoutingappv2.Activities.DataActivity.updateMatch;
import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    int matchesCount, i;
    LocalDB



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchesCount = db.getMatchesDao().getCount();
        i = 0;
        matches.clear();
        while (i < matchesCount) {
            i++;
            matches.add(new ScoutingModel(
                    db.getMatchesDao().findMatch(i).get(0).getTournament(),
                    db.getMatchesDao().findMatch(i).get(0).getMatchNumber(),
                    db.getMatchesDao().findMatch(i).get(0).getTeamNumber(),
                    db.getMatchesDao().findMatch(i).get(0).getDepot(),
                    db.getMatchesDao().findMatch(i).get(0).getLander(),
                    db.getMatchesDao().findMatch(i).get(0).isAutoDrop(),
                    db.getMatchesDao().findMatch(i).get(0).isMarker(),
                    db.getMatchesDao().findMatch(i).get(0).isSample(),
                    db.getMatchesDao().findMatch(i).get(0).isDoubleSample(),
                    db.getMatchesDao().findMatch(i).get(0).isAutoPark(),
                    db.getMatchesDao().findMatch(i).get(0).isEndHang(),
                    db.getMatchesDao().findMatch(i).get(0).isEndPartial(),
                    db.getMatchesDao().findMatch(i).get(0).isFullPark()));
        }

        loadFragment(new ScoutingActivity());
        BottomNavigationView navigation = findViewById(R.id.nav);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.scout:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new ScoutingActivity();
                break;
            case R.id.data:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new DataActivity();
                break;
            case R.id.analyze:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new AnalyzeActivity();
                break;
            default:
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
            return false;
    }
    public MatchesDatabase getDb() {
        return db;
    }
}