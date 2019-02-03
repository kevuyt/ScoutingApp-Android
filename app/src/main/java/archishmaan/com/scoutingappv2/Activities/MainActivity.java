package archishmaan.com.scoutingappv2.Activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import archishmaan.com.scoutingappv2.R;

import static archishmaan.com.scoutingappv2.Activities.DataActivity.updateMatch;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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