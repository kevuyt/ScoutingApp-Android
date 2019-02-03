package archishmaan.com.scoutingappv2.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;

import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;
import static archishmaan.com.scoutingappv2.Activities.DataActivity.updateMatch;

public class EditActivity extends Fragment implements View.OnClickListener {
    Button update, delete;
    int matchesIndex;
    EditText tournamentEdit, matchNumEdit, teamNumEdit, depotEdit, landerEdit;
    CheckBox autoDropEdit, markerEdit, autoParkEdit, sampleEdit, doubleSampleEdit, endHangEdit, endPartParkEdit, endFullParkEdit;
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_activity, container, false);
        initView(view);
        matchesIndex = updateMatch.get(0).getMatchNumber() - 1;
        update.setOnClickListener(v -> {
            if (!isClear()) {
                matches.set(matches.indexOf(updateMatch.get(0)),
                        new ScoutingModel(
                                tournamentEdit.getText().toString(),
                                Integer.parseInt(matchNumEdit.getText().toString()),
                                Integer.parseInt(teamNumEdit.getText().toString()),
                                Integer.parseInt(depotEdit.getText().toString()),
                                Integer.parseInt(landerEdit.getText().toString()),
                                autoDropEdit.isChecked(),
                                markerEdit.isChecked(),
                                autoParkEdit.isChecked(),
                                sampleEdit.isChecked(),
                                doubleSampleEdit.isChecked(),
                                endHangEdit.isChecked(),
                                endPartParkEdit.isChecked(),
                                endFullParkEdit.isChecked())
                );
                updateMatch.remove(0);
                clear();
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new DataActivity())
                        .commit();
            }
        });
        delete.setOnClickListener(v -> {
            matches.remove(updateMatch.get(0));
            updateMatch.remove(0);
            clear();
            assert getFragmentManager()!= null;
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DataActivity())
                    .commit();
        });

        return view;
    }
    @Override
    public void onClick(View v) {

    }
    public boolean isClear() {
        return (tournamentEdit.getText().toString().equals("")  ||
                matchNumEdit.getText().toString().equals("") ||
                teamNumEdit.getText().toString().equals("") ||
                depotEdit.getText().toString().equals("") ||
                landerEdit.getText().toString().equals(""));
    }
    public  void initView(View view) {
        update = view.findViewById((R.id.update));
        delete = view.findViewById(R.id.delete);
        tournamentEdit = view.findViewById(R.id.tournament);
        matchNumEdit = view.findViewById(R.id.match_number);
        teamNumEdit = view.findViewById(R.id.team_number);
        autoDropEdit = view.findViewById(R.id.auto_drop);
        markerEdit = view.findViewById(R.id.auto_marker);
        autoParkEdit = view.findViewById(R.id.auto_park);
        sampleEdit = view.findViewById(R.id.auto_sample);
        doubleSampleEdit = view.findViewById(R.id.auto_double_sample);
        depotEdit = view.findViewById(R.id.depot_minerals);
        landerEdit = view.findViewById(R.id.lander_minerals);
        endHangEdit = view.findViewById(R.id.end_hang);
        endPartParkEdit = view.findViewById(R.id.end_partial_park);
        endFullParkEdit = view.findViewById(R.id.end_full_park);
        clear();
        tournamentEdit.setText(String.valueOf(updateMatch.get(0).getTournament()));
        matchNumEdit.setText(String.valueOf(updateMatch.get(0).getMatchNumber()));
        teamNumEdit.setText(String.valueOf(updateMatch.get(0).getTeamNumber()));
        autoDropEdit.setChecked(updateMatch.get(0).isAutoDrop());
        autoParkEdit.setChecked(updateMatch.get(0).isAutoPark());
        markerEdit.setChecked(updateMatch.get(0).isMarker());
        sampleEdit.setChecked(updateMatch.get(0).isSample());
        doubleSampleEdit.setChecked(updateMatch.get(0).isDoubleSample());
        depotEdit.setText(String.valueOf(updateMatch.get(0).getDepot()));
        landerEdit.setText(String.valueOf(updateMatch.get(0).getLander()));
        endHangEdit.setChecked(updateMatch.get(0).isEndHang());
        endFullParkEdit.setChecked(updateMatch.get(0).isFullPark());
        endPartParkEdit.setChecked(updateMatch.get(0).isEndPartial());
    }
    public void clear() {
        matchNumEdit.setText("");
        teamNumEdit.setText("");
        autoDropEdit.setChecked(false);
        autoParkEdit.setChecked(false);
        markerEdit.setChecked(false);
        sampleEdit.setChecked(false);
        doubleSampleEdit.setChecked(false);
        depotEdit.setText("");
        landerEdit.setText("");
        endHangEdit.setChecked(false);
        endFullParkEdit.setChecked(false);
        endPartParkEdit.setChecked(false);
    }
}
