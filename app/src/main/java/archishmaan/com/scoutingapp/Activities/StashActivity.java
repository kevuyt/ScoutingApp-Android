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
import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;
import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class StashActivity extends Fragment implements View.OnClickListener {
    ScrollView scrollView;
    LinearLayout linearLayout;
    int dropPoints, markerPoints, autoParkPoints, samplePoints, hangPoints, endPartialParkPoints, endFullParkPoints, autoPoints, teleopPoints, endGamePoints, totalPoints;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stash_activity, container, false);
        scrollView = new ScrollView(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        for (ScoutingModel match : matches) {
            Button button = new Button(getContext());
            /*
            if (match.isAutoDrop()) {dropPoints = 30;}
            else {dropPoints = 0;}
            if (match.isMarker()) {markerPoints = 15;}
            else {markerPoints = 0;}
            if (match.isAutoPark()) {autoParkPoints = 10;}
            else {autoParkPoints = 0;}
            if (match.isSample()) {samplePoints = 25;}
            else {samplePoints = 0;}
            if (match.isEndHang()) {hangPoints = 50;}
            else {hangPoints = 0;}
            if (match.isEndPartial()) {endPartialParkPoints = 15;}
            else {endPartialParkPoints = 0;}
            if (match.isFullPark()) {endFullParkPoints = 25;}
            else {endFullParkPoints = 0;}
            autoPoints = dropPoints + markerPoints + autoParkPoints + samplePoints;
            teleopPoints = (match.getDepot() * 2) + (match.getLander() * 5);
            endGamePoints = hangPoints + endPartialParkPoints + endFullParkPoints;
            totalPoints = autoPoints + teleopPoints + endGamePoints;
            button.setText("Match #: " + match.getMatchNumber() + ", Team #: "
                    + match.getTeamNumber() + ", Auto Points: " + autoPoints
                    + match.getTeamNumber() + ", Auto Points: " + autoPoints
                    + ", Tele-op Points: " + teleopPoints + ", End Game Points: "
                    + endGamePoints + ", Total: " + totalPoints);
                    match.setTextSize(13);
                    */
            button.setText ("Match #: " + match.getMatchNumber() + ", Team #: " + match.getTeamNumber());
            button.setId(match.getMatchNumber());
            button.setTextSize(15);
            button.setWidth(1000);
            button.setHeight(100);
            button.getBackground().setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.DARKEN);
            linearLayout.addView(button);
            button.setOnClickListener(this);
        }

        return scrollView;
    }

    @Override
    public void onClick(View view) {
    
    }

}
