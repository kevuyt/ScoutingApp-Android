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
        for (ScoutingModel match : matches) {matches.remove(match);}
        for (int f = 1; f <= entrys; f++) {
            matches.add(new ScoutingModel(
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).getTournament(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).getMatchNumber(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).getTeamNumber(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).getDepot(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).getLander(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isAutoDrop(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isMarker(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isAutoPark(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isSample(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isDoubleSample(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isEndHang(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isEndPartial(),
                    scoutingModelDatabase.daoAccess().fetchOneScoutingModelDBbyKeyID(f).isFullPark()
                    )
            );
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