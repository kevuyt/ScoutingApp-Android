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
            button.setText("Match #: " + match.getMatchNumber() + ", Team #: " + match.getTeamNumber());
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
