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
import archishmaan.com.scoutingappv2.localDB.MatchesDatabase;

import static archishmaan.com.scoutingappv2.Activities.DataActivity.updateMatch;
import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    MatchesDatabase matchesDatabase = Room.databaseBuilder(getApplicationContext(), MatchesDatabase.class, "matches").build();
    int dbCount, f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbCount = matchesDatabase.matchesDao().getCount();
        f = 0;
        while (f < dbCount) {
            f++;
            matches.add(new ScoutingModel(matchesDatabase.matchesDao().findMatch(f).get(0).getTournament(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).getMatchNumber(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).getTeamNumber(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).getDepot(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).getLander(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isAutoDrop(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isMarker(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isSample(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isDoubleSample(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isAutoPark(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isEndHang(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isEndPartial(),
                    matchesDatabase.matchesDao().findMatch(f).get(0).isFullPark()));
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
                while (updateMatch.size() != 0) {
                    updateMatch.remove(0);
                }
                fragment = new ScoutingActivity();

                break;

            case R.id.data:
                while (updateMatch.size() != 0) {
                    updateMatch.remove(0);
                }
                fragment = new DataActivity();

                break;

            case R.id.analyze:
                while (updateMatch.size() != 0) {
                    updateMatch.remove(0);
                }
                fragment = new AnalyzeActivity();

                break;

            default:
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
            return false;
    }
}