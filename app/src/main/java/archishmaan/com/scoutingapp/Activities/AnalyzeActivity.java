package archishmaan.com.scoutingapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;

import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class AnalyzeActivity extends Fragment {
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.analysis_activity, container, false);

      return view;
    }


}