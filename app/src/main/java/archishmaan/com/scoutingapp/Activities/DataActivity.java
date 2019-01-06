package archishmaan.com.scoutingapp.Activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;
import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class DataActivity extends Fragment implements View.OnClickListener {
    ScrollView scrollView;
    int score;
    LinearLayout linearLayout;
    static List<ScoutingModel> updateMatch = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scrollView = new ScrollView(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        for (int i = 0; i< matches.size(); i++) {
            score = 0;
            Button button = new Button(getContext());
            if (matches.get(i).isAutoDrop()) {score +=30;}
            if (matches.get(i).isSample()) {score+=25;}
            if (matches.get(i).isDoubleSample()) {score+=50;}
            if (matches.get(i).isMarker()) {score+=15;}
            if (matches.get(i).isAutoPark()) {score+=10;}
            if (matches.get(i).getDepot()>0) {score+=matches.get(i).getDepot()*2;}
            if (matches.get(i).getLander()>0) {score+=matches.get(i).getLander()*5;}
            if (matches.get(i).isEndHang()) {score+=50;}
            if (matches.get(i).isEndPartial()) {score+=15;}
            if (matches.get(i).isFullPark()) {score+=25;}
            button.setText ("Match #: " + matches.get(i).getMatchNumber() + ", Team #: " + matches.get(i).getTeamNumber() + ", Score: " + score++);
            button.setId(matches.get(i).getMatchNumber());
            button.setTextSize(15);
            button.setWidth(1000);
            button.setHeight(100);
            button.getBackground().setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.DARKEN);
            linearLayout.addView(button);
            final ScoutingModel updateMatchEntry = matches.get(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMatch.add(updateMatchEntry);
                    assert getFragmentManager() != null;
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new EditActivity())
                            .commit();
                }
            });
        }
        Button export = new Button(getContext());
        export.setText("Export");
        export.setTextSize(25);
        export.setWidth(1000);
        export.setHeight(125);
        export.getBackground().setColorFilter(Color.parseColor("#DAA620"), PorterDuff.Mode.DARKEN);
        linearLayout.addView(export);
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String csvName = "ScoutingData.csv";
                File csvFile = new File(csvName);
                PrintWriter csvWriter = null;
                try {
                    csvWriter = new PrintWriter(new FileWriter(csvFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    for(ScoutingModel match : matches){
                        assert csvWriter != null;
                        csvWriter.println(match);
                    }*/
            }
        });

        return scrollView;
    }

    @Override
    public void onClick(View view) {


    }

}
