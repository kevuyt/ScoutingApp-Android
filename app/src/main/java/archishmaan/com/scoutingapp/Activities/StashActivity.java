package archishmaan.com.scoutingapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;

import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class StashActivity extends Fragment implements View.OnClickListener {
    Button button;
    ScrollView scrollView;
    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stash_activity, null);
        scrollView = new ScrollView();
        LinearLayout linearLayout = new LinearLayout();
        linearLayout.setOrientation(linearLayout.setOrientation(LinearLayout.VERTICAL););
        scrollView.addView(linearLayout);

        for (ScoutingModel match : matches){

            LinearLayout linear1 = new LinearLayout();
            linear1.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(linear1);
            button = new Button();
            button.setText("Match #: " + match.getMatchNumber() + ", Team #: " + match.getTeamNumber());
            button.setId(match.getMatchNumber());
            button.setTextSize(15);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linear1.addView(button);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
