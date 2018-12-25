package archishmaan.com.scoutingapp.Activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    static List<Integer> updateMatch = new ArrayList<>();
    int buttonIndex;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scrollView = new ScrollView(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        for (int i = 0; i<matches.size(); i++) {
            Button button = new Button(getContext());
            button.setText ("Match #: " + matches.get(i).getMatchNumber() + ", Team #: " + matches.get(i).getTeamNumber());
            button.setId(matches.get(i).getMatchNumber());
            button.setTextSize(15);
            button.setWidth(1000);
            button.setHeight(100);
            button.getBackground().setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.DARKEN);
            linearLayout.addView(button);
            final int j = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMatch.add(j);
                    assert getFragmentManager() != null;
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new EditActivity())
                            .commit();
                }
            });



        }

        return scrollView;
    }

    @Override
    public void onClick(View view) {


    }

}
