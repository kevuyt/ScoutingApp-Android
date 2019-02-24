package archishmaan.com.scoutingappv2.Activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import archishmaan.com.scoutingappv2.LocalDB.Matches;
import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;

import static archishmaan.com.scoutingappv2.Activities.MainActivity.primaryKeys;

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
    public Button stash;
    public boolean duplicate = false;
    public static List<ScoutingModel> matches = new ArrayList<>();
    InputMethodManager inputMethodManager;
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity, container, false);

        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        viewGroup = container;

        initView(view);
        stash.setOnClickListener(this);
        checkDuplicates();
        return view;
    }
    @Override
    public void onClick(View v) {
        duplicate = false;
        if (depot.getText().toString().equals("")) createDialog("Empty Depot", "Did this team score in the depot?", depot);
        if (lander.getText().toString().equals("")) createDialog("Empty Lander", "Did this team score in the lander?", lander);
        if (isNotClear()) {
            if (!duplicate) {
                createMatch();
                clear();
            }
        }
        else if (teamNum.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Don't forget to fill in the Team Number", Toast.LENGTH_SHORT).show();
        }
        else if (tournament.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Don't forget to fill in the Tournament Name", Toast.LENGTH_SHORT).show();
        }
        else if (matchNum.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Don't forget to fill in the Match Number", Toast.LENGTH_SHORT).show();
        }

    }
    public void createDialog(String title, String message, EditText scoringArea) {
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle(title).setMessage(message)
                .setPositiveButton("No", (dialog, which) -> {
                    scoringArea.setText(String.valueOf(0));
                    if (isNotClear()) {
                        duplicate = true;
                        createMatch();
                        clear();
                    }
                })
                .setNegativeButton("Yes", (dialog, which) -> scoringArea.setText(""))
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
        try {
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

        Matches matches = new Matches();
        matches.setId(primaryKeys.size());
        matches.setTournament(tournament.getText().toString());
        matches.setMatchNumber(Integer.parseInt(matchNum.getText().toString()));
        matches.setTeamNumber(Integer.parseInt(teamNum.getText().toString()));
        matches.setDepot(Integer.parseInt(depot.getText().toString()));
        matches.setLander(Integer.parseInt(lander.getText().toString()));
        matches.setAutoDrop(autoDrop.isChecked());
        matches.setMarker(marker.isChecked());
        matches.setAutoPark(autoPark.isChecked());
        matches.setSample(sample.isChecked());
        matches.setDoubleSample(doubleSample.isChecked());
        matches.setEndHang(endHang.isChecked());
        matches.setEndPartial(endPartPark.isChecked());
        matches.setFullPark(endFullPark.isChecked());

        MainActivity.matchesDatabase.matchesDao().addMatch(matches);
        primaryKeys.add(primaryKeys.size());
        Toast.makeText(getActivity(), "Stashed successfully", Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Cannot stash numbers larger than 2 million", Toast.LENGTH_SHORT).show();
        }
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

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);

        inputMethodManager.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);

        stash = view.findViewById(R.id.stash);
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

        if (matches.size()>0) {
            matchNum.setText(String.valueOf(matches.get(matches.size() - 1).getMatchNumber() + 1));
            tournament.setText(matches.get(matches.size()-1).getTournament());
        }

        else {
        matchNum.setText(String.valueOf(1));
        }
    }

}