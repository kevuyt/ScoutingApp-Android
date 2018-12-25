package archishmaan.com.scoutingapp.Activities;

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

import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;
import static archishmaan.com.scoutingapp.Activities.StashActivity.updateMatch;
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;


public class EditActivity extends Fragment implements View.OnClickListener {
    Button update;
    int matchesIndex;
    EditText matchNumEdit, teamNumEdit, depotEdit, landerEdit;
    CheckBox autoDropEdit, markerEdit, autoParkEdit, sampleEdit, endHangEdit, endPartParkEdit, endFullParkEdit;
     public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_activity, container, false);
        update = view.findViewById((R.id.update));
        matchNumEdit = view.findViewById(R.id.match_number);
        teamNumEdit = view.findViewById(R.id.team_number);
        autoDropEdit = view.findViewById((R.id.auto_drop));
        markerEdit = view.findViewById((R.id.auto_marker));
        autoParkEdit = view.findViewById((R.id.auto_park));
        sampleEdit = view.findViewById((R.id.auto_sample));
        depotEdit = view.findViewById((R.id.depot_minerals));
        landerEdit = view.findViewById((R.id.lander_minerals));
        endHangEdit = view.findViewById((R.id.end_hang));
        endPartParkEdit = view.findViewById((R.id.end_partial_park));
        endFullParkEdit = view.findViewById((R.id.end_full_park));
        matchNumEdit.setText(matches.get(updateMatch.get(0)).getMatchNumber());
        teamNumEdit.setText(matches.get(updateMatch.get(0)).getTeamNumber());
        autoDropEdit.setChecked(matches.get(updateMatch.get(0)).isAutoDrop());
        autoParkEdit.setChecked(matches.get(updateMatch.get(0)).isAutoPark());
        markerEdit.setChecked(matches.get(updateMatch.get(0)).isMarker());
        sampleEdit.setChecked(matches.get(updateMatch.get(0)).isSample());
        depotEdit.setText(matches.get(updateMatch.get(0)).getDepot());
        landerEdit.setText(matches.get(updateMatch.get(0)).getLander());
        endHangEdit.setChecked(matches.get(updateMatch.get(0)).isEndHang());
        endFullParkEdit.setChecked(matches.get(updateMatch.get(0)).isFullPark());
        endPartParkEdit.setChecked(matches.get(updateMatch.get(0)).isEndPartial());
        matchesIndex = matches.get(updateMatch.get(0)).getMatchNumber() - 1;
        updateMatch.remove(0);
        update.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (!matchNumEdit.getText().toString().equals("")) {
            if (!teamNumEdit.getText().toString().equals("")) {
                if (!depotEdit.getText().toString().equals("")) {
                    if (!landerEdit.getText().toString().equals("")) {
                        matches.set(matchesIndex,
                                new ScoutingModel(
                                        Integer.parseInt(matchNumEdit.getText().toString()),
                                        Integer.parseInt(teamNumEdit.getText().toString()),
                                        Integer.parseInt(depotEdit.getText().toString()),
                                        Integer.parseInt(landerEdit.getText().toString()),
                                        autoDropEdit.isChecked(),
                                        markerEdit.isChecked(),
                                        autoParkEdit.isChecked(),
                                        sampleEdit.isChecked(),
                                        endHangEdit.isChecked(),
                                        endPartParkEdit.isChecked(),
                                        endFullParkEdit.isChecked()
                                )
                        );
                        matchNumEdit.setText("");
                        teamNumEdit.setText("");
                        autoDropEdit.setChecked(false);
                        autoParkEdit.setChecked(false);
                        markerEdit.setChecked(false);
                        sampleEdit.setChecked(false);
                        depotEdit.setText("");
                        landerEdit.setText("");
                        endHangEdit.setChecked(false);
                        endFullParkEdit.setChecked(false);
                        endPartParkEdit.setChecked(false);
                        assert getFragmentManager() != null;
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new StashActivity())
                                .commit();
                    }
                }
            }
        }
     }
}
