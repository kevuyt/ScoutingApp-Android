package archishmaan.com.scoutingappv2.Activities;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import archishmaan.com.scoutingappv2.LocalDB.Matches;
import archishmaan.com.scoutingappv2.LocalDB.MatchesDatabase;
import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;

import static archishmaan.com.scoutingappv2.Activities.DataActivity.updateMatch;
import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static MatchesDatabase matchesDatabase;
    public static List<Integer> primaryKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryKeys.add(0);

        matchesDatabase = Room.databaseBuilder(getApplicationContext(),
                MatchesDatabase.class, "matchesdb").allowMainThreadQueries()
                .build();

        int matchesCount = matchesDatabase.matchesDao().getAll().size();

        for (int i = 0; i < matchesCount; i++) {

                try {
                    Matches match = matchesDatabase.matchesDao().getMatch(i + 1).get(0);
                    matches.add(
                            new ScoutingModel(
                                    match.getTournament(),
                                    match.getMatchNumber(),
                                    match.getTeamNumber(),
                                    match.getDepot(),
                                    match.getLander(),
                                    match.isAutoDrop(),
                                    match.isMarker(),
                                    match.isAutoPark(),
                                    match.isSample(),
                                    match.isDoubleSample(),
                                    match.isEndHang(),
                                    match.isEndPartial(),
                                    match.isFullPark()));
                    primaryKeys.add(primaryKeys.size());
                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "No entrys in database", Toast.LENGTH_SHORT).show();
                }

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

}