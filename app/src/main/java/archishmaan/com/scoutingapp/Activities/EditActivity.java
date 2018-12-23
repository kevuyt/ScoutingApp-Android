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
    EditText matchNumEdit, teamNumEdit, depotEdit, landerEdit;
    CheckBox autoDropEdit, markerEdit, autoParkEdit, sampleEdit, endHangEdit, endPartParkEdit, endFullParkEdit;
    int matchIndex;
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
        update.setOnClickListener(this);
        matchIndex = updateMatch.get(0).getMatchNumber() - 1;
        matchNumEdit.setText(matches.get(matchIndex).getMatchNumber());
        teamNumEdit.setText(matches.get(matchIndex).getTeamNumber());
        autoDropEdit.setChecked(matches.get(matchIndex).isAutoDrop());
        autoParkEdit.setChecked(matches.get(matchIndex).isAutoPark());
        markerEdit.setChecked(matches.get(matchIndex).isMarker());
        sampleEdit.setChecked(matches.get(matchIndex).isSample());
        depotEdit.setText(matches.get(matchIndex).getDepot());
        landerEdit.setText(matches.get(matchIndex).getLander());
        endHangEdit.setChecked(matches.get(matchIndex).isEndHang());
        endFullParkEdit.setChecked(matches.get(matchIndex).isFullPark());
        endPartParkEdit.setChecked(matches.get(matchIndex).isEndPartial());

        updateMatch.remove(0);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (!matchNumEdit.getText().toString().equals("")) {
            if (!teamNumEdit.getText().toString().equals("")) {
                if (!depotEdit.getText().toString().equals("")) {
                    if (!landerEdit.getText().toString().equals("")) {
                        matches.set(matchIndex,
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
