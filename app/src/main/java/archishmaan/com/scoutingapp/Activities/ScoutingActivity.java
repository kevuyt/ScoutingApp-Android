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
import java.util.ArrayList;
import java.util.List;
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class ScoutingActivity extends Fragment implements View.OnClickListener {
    public EditText matchNum;
    public EditText teamNum;
    public CheckBox autoDrop;
    public CheckBox marker;
    public CheckBox autoPark;
    public CheckBox sample;
    public EditText depot;
    public EditText lander;
    public CheckBox endHang;
    public CheckBox endPartPark;
    public CheckBox endFullPark;
    static List<ScoutingModel> matches = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity, container, false);
        Button stash = view.findViewById(R.id.stash);
        initView(view);
        stash.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (!isClear()) {
            matches.add(new ScoutingModel(
                    Integer.parseInt(matchNum.getText().toString()),
                    Integer.parseInt(teamNum.getText().toString()),
                    Integer.parseInt(depot.getText().toString()),
                    Integer.parseInt(lander.getText().toString()),
                    autoDrop.isChecked(),
                    marker.isChecked(),
                    autoPark.isChecked(),
                    sample.isChecked(),
                    endHang.isChecked(),
                    endPartPark.isChecked(),
                    endFullPark.isChecked())
            );
            clear();
        }
    }
    public boolean isClear() {
        return !(!matchNum.getText().toString().equals("") &&
                !teamNum.getText().toString().equals("") &&
                !depot.getText().toString().equals("") &&
                !lander.getText().toString().equals(""));
    }
    public void clear() {
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
    public void initView(View view){
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
    }

}