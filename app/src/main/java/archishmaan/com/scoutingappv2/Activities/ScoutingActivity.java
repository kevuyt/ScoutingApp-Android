package archishmaan.com.scoutingappv2.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;


/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */

public class ScoutingActivity extends Fragment implements View.OnClickListener{
    public EditText tournament;
    public EditText matchNum;
    public EditText teamNum;
    public CheckBox autoDrop;
    public CheckBox marker;
    public CheckBox autoPark;
    public CheckBox sample;
    public CheckBox doubleSample;
    public EditText depot;
    public EditText lander;
    public CheckBox endHang;
    public CheckBox endPartPark;
    public CheckBox endFullPark;
    public boolean duplicate = false;
    public static List<ScoutingModel> matches = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity, container, false);

        Button stash = view.findViewById(R.id.stash);
        initView(view);
        stash.setOnClickListener(this);
        checkDuplicates();
        return view;
    }
    @Override
    public void onClick(View v) {
        if (depot.getText().toString().equals("")) createDialog("Empty Depot", "Did this team score in the depot?");
        else if (lander.getText().toString().equals("")) createDialog("Empty Lander", "Did this team score in the lander?");
        if (isNotClear() && !duplicate) {
            createMatch();
            clear();
        }
    }
    public void createDialog(String title, String message) {
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message)
                .setPositiveButton("No", (dialog, which) -> {
                    lander.setText(String.valueOf(0));
                    if (isNotClear()) {
                        duplicate = true;
                        createMatch();
                        clear();
                    }
                })
                .setNegativeButton("Yes", (dialog, which) -> lander.setText(""))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void checkDuplicates(){
        sample.setOnClickListener(view1 -> {
            if (doubleSample.isChecked()) doubleSample.setChecked(false);
        });
        doubleSample.setOnClickListener(view12 -> {
            if (sample.isChecked()) sample.setChecked(false);
        });
        endPartPark.setOnClickListener(view13 -> {
            if (endFullPark.isChecked()) endFullPark.setChecked(false);
            if (endHang.isChecked()) endHang.setChecked(false);
        });
        endFullPark.setOnClickListener(view14 -> {
            if (endPartPark.isChecked()) endPartPark.setChecked(false);
            if (endHang.isChecked()) endHang.setChecked(false);
        });
        endHang.setOnClickListener(v -> {
            if (endPartPark.isChecked()) endPartPark.setChecked(false);
            if (endFullPark.isChecked()) endFullPark.setChecked(false);
        });
    }
    public void createMatch() {
        matches.add(new ScoutingModel(
                tournament.getText().toString(),
                Integer.parseInt(matchNum.getText().toString()),
                Integer.parseInt(teamNum.getText().toString()),
                Integer.parseInt(depot.getText().toString()),
                Integer.parseInt(lander.getText().toString()),
                autoDrop.isChecked(),
                marker.isChecked(),
                autoPark.isChecked(),
                sample.isChecked(),
                doubleSample.isChecked(),
                endHang.isChecked(),
                endPartPark.isChecked(),
                endFullPark.isChecked()));
    }
    public boolean isNotClear() {

        return (!tournament.getText().toString().equals("") &&
                !matchNum.getText().toString().equals("") &&
                !teamNum.getText().toString().equals("") &&
                !depot.getText().toString().equals("") &&
                !lander.getText().toString().equals(""));
    }
    public void clear() {
        matchNum.setText(String.valueOf(Integer.parseInt(matchNum.getText().toString()) + 1));
        teamNum.setText("");
        autoDrop.setChecked(false);
        autoPark.setChecked(false);
        marker.setChecked(false);
        sample.setChecked(false);
        doubleSample.setChecked(false);
        depot.setText("");
        lander.setText("");
        endHang.setChecked(false);
        endFullPark.setChecked(false);
        endPartPark.setChecked(false);
    }
    public void initView(View view){
        tournament = view.findViewById(R.id.tournament);
        matchNum = view.findViewById(R.id.match_number);
        teamNum = view.findViewById(R.id.team_number);
        autoDrop = view.findViewById(R.id.auto_drop);
        marker = view.findViewById(R.id.auto_marker);
        autoPark = view.findViewById(R.id.auto_park);
        sample = view.findViewById(R.id.auto_sample);
        doubleSample = view.findViewById(R.id.auto_double_sample);
        depot = view.findViewById(R.id.depot_minerals);
        lander = view.findViewById(R.id.lander_minerals);
        endHang = view.findViewById(R.id.end_hang);
        endPartPark = view.findViewById(R.id.end_partial_park);
        endFullPark = view.findViewById(R.id.end_full_park);
        matchNum.setText(String.valueOf(matches.size() + 1));
        if (matches.size() > 0) {
        tournament.setText(matches.get(matches.size()-1).getTournament());
        }
    }

}