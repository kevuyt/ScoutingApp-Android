package archishmaan.com.scoutingapp.Activities;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Objects;

import archishmaan.com.scoutingapp.LocalDB.ScoutingModelDatabase;
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;
import static archishmaan.com.scoutingapp.Activities.DataActivity.updateMatch;
import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static final String DATABASE_NAME = "matches_db";
    public static ScoutingModelDatabase scoutingModelDatabase;
    int entrys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoutingModelDatabase = Room.databaseBuilder(Objects.requireNonNull(getApplicationContext()), ScoutingModelDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        new Thread(() -> entrys = scoutingModelDatabase.daoAccess().fetchCountScoutingModelDB());
        int f = 1;
        for (ScoutingModel match : matches) {matches.remove(match);}
        while (f < entrys) {
            matches.add(new ScoutingModel(
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).getTournament(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).getMatchNumber(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).getTeamNumber(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).getDepot(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).getLander(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isAutoDrop(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isMarker(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isAutoPark(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isSample(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isDoubleSample(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isEndHang(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isEndPartial(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyMatchNumber(f).isFullPark()
                    )
            );
            f +=1;
        }
        //loading the default fragment
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