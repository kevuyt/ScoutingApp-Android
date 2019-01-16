package archishmaan.com.scoutingapp.Activities;

import android.arch.persistence.room.Room;
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
import java.util.Objects;

import archishmaan.com.scoutingapp.LocalDB.ScoutingModelDatabase;
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.Models.ScoutingModelDB;
import archishmaan.com.scoutingapp.R;

import static archishmaan.com.scoutingapp.Activities.MainActivity.scoutingModelDatabase;
import static archishmaan.com.scoutingapp.SQL.SqlApi.createRow;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */

public class ScoutingActivity extends Fragment implements View.OnClickListener{
    //Snackbar stashMessage;
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
    public static List<ScoutingModel> matches = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity, container, false);

        Button stash = view.findViewById(R.id.stash);
        initView(view);
        stash.setOnClickListener(this);
        //ScrollView scrollView = view.findViewById(R.id.scrollView);
        //stashMessage = Snackbar.make(scrollView, "Stashed", Snackbar.LENGTH_SHORT);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (!isClear()) {
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
                    endFullPark.isChecked())
            );
            int teamNumInt = Integer.parseInt(teamNum.getText().toString());
            int depotInt = Integer.parseInt(depot.getText().toString());
            int landerInt = Integer.parseInt(lander.getText().toString());
            new Thread(() -> {
              ScoutingModelDB scoutingModelDB = new ScoutingModelDB(
                      tournament.getText().toString(),
                      Integer.parseInt(matchNum.getText().toString()),
                      teamNumInt,
                      depotInt,
                      landerInt,
                      autoDrop.isChecked(),
                      marker.isChecked(),
                      autoPark.isChecked(),
                      sample.isChecked(),
                      doubleSample.isChecked(),
                      endHang.isChecked(),
                      endPartPark.isChecked(),
                      endFullPark.isChecked());
              scoutingModelDatabase.daoAccess().insertOnlySingleScoutingModelDB(scoutingModelDB);
            }).start();
            createRow(
                    new ScoutingModel(
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
                            endFullPark.isChecked()
                    )
            );
            //stashMessage.setAction("Undo", new UndoListener());
            //stashMessage.show();

            clear();
        }
    }
    public boolean isClear() {
        return !(!tournament.getText().toString().equals("") &&
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