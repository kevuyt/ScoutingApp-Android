package archishmaan.com.scoutingapp.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class ScoutingActivity extends Fragment implements View.OnClickListener {
    private EditText matchNum;
    private EditText teamNum;
    private CheckBox autoDrop;
    private CheckBox marker;
    private CheckBox autoPark;
    private CheckBox sample;
    private EditText depot;
    private EditText lander;
    private CheckBox endHang;
    private CheckBox endPartPark;
    private CheckBox endFullPark;
    static List<ScoutingModel> matches = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity, container, false);
        Button stash = view.findViewById(R.id.stash);
        matchNum = view.findViewById(R.id.match_number);
        teamNum = view.findViewById(R.id.team_number);
        autoDrop = view.findViewById((R.id.auto_drop));
        marker = view.findViewById((R.id.auto_marker));
        autoPark = view.findViewById((R.id.auto_park));
        sample = view.findViewById((R.id.auto_sample));
        depot = view.findViewById((R.id.depot_minerals));
        lander = view.findViewById((R.id.lander_minerals));
        endHang = view.findViewById((R.id.end_hang));
        endPartPark = view.findViewById((R.id.end_partial_park));
        endFullPark = view.findViewById((R.id.end_full_park));

        stash.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
    if (String.valueOf(matchNum) != "") {
        if (String.valueOf(teamNum) != "") {
            if (String.valueOf(depot) != "") {
                if (String.valueOf(lander) != "") {
                    matches.add(
                            new ScoutingModel(
                                    Integer.parseInt(String.valueOf(matchNum)),
                                    Integer.parseInt(String.valueOf(teamNum)),
                                    Integer.parseInt(String.valueOf(depot)),
                                    Integer.parseInt(String.valueOf(lander)),
                                    Boolean.parseBoolean(String.valueOf(autoDrop)),
                                    Boolean.parseBoolean(String.valueOf(marker)),
                                    Boolean.parseBoolean(String.valueOf(autoPark)),
                                    Boolean.parseBoolean(String.valueOf(sample)),
                                    Boolean.parseBoolean(String.valueOf(endHang)),
                                    Boolean.parseBoolean(String.valueOf(endPartPark)),
                                    Boolean.parseBoolean(String.valueOf(endFullPark))
                            )
                    );
                }
            }
        }
    }
    else

        matchNum.setText("");
        teamNum.setText("");
        autoDrop.setChecked(false);
        autoPark.setChecked(false);
        marker.setChecked(false);
        sample.setChecked(false);
        depot.setText("");
        lander.setText("");
        endHang.setChecked(false);
        endFullPark.setChecked(false);
        endPartPark.setChecked(false);

    }
    public static List<ScoutingModel> getMatches () {return matches;}

}