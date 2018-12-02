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
import android.widget.ListView;

import java.util.List;
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

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
    private View view;
    static List<ScoutingModel> matches;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scouting_activity, container, false);
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

        stash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matches.add(new ScoutingModel(
                        Integer.parseInt(matchNum.getText().toString()),
                        Integer.parseInt(teamNum.getText().toString()),
                        Integer.parseInt(depot.getText().toString()),
                        Integer.parseInt(lander.getText().toString()),
                        Boolean.parseBoolean(autoDrop.getText().toString()),
                        Boolean.parseBoolean(marker.getText().toString()),
                        Boolean.parseBoolean(autoPark.getText().toString()),
                        Boolean.parseBoolean(sample.getText().toString()),
                        Boolean.parseBoolean(endHang.getText().toString()),
                        Boolean.parseBoolean(endPartPark.getText().toString()),
                        Boolean.parseBoolean(endFullPark.getText().toString())));
                try {
                    String filepath = "c://stash_activity.xml"
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(filepath);

                    Node contraintLayout = doc.getElementsByTagName("android.support.constraint.ConstraintLayout").item(0);
                    Node listView = doc.getElementsByTagName("ListView").item(0);

                }

                 Button setText = view.findViewById(R.id.scouting_data_button + match);
                 setText.setText(Integer.parseInt(teamNum.getText().toString()) + " - " + Integer.parseInt(matchNum.getText().toString()));
            }
        });
        return view;
    }

    public static List<ScoutingModel> getMatches () {return matches;}

}