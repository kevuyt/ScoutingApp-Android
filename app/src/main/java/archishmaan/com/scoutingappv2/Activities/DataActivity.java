package archishmaan.com.scoutingappv2.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import archishmaan.com.scoutingappv2.Models.ScoutingModel;
import archishmaan.com.scoutingappv2.R;

import static archishmaan.com.scoutingappv2.Activities.MainActivity.fragment;
import static archishmaan.com.scoutingappv2.Activities.MainActivity.matchesDatabase;
import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;
import static archishmaan.com.scoutingappv2.R.string.export_button;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */

public class DataActivity extends Fragment implements View.OnClickListener {
    FileOutputStream outputStream;
    ScrollView scrollView;
    int score;
    String drop, marker, sample, autoPark, hang, endPark;
    int requestValue = 0;
    LinearLayout linearLayout;
    static List<ScoutingModel> updateMatch = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_activity, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onClick(View view) {}

    public void createExportButton(Button export) {
        export.setText(getString(export_button));
        export.setTextSize(25);
        export.setWidth(1000);
        export.setHeight(125);
        export.getBackground().setColorFilter(Color.parseColor("#DAA620"), PorterDuff.Mode.DARKEN);
        linearLayout.addView(export);
        export.setOnClickListener(v -> createCSV());
    }


    @SuppressLint("SetTextI18n")
    public void createButton(Button button, ScoutingModel match){
        button.setText ("Match #: " + match.getMatchNumber() + ", Team #: " + match.getTeamNumber() + ", Score: " + score);
        button.setId(match.getMatchNumber());
        button.setTextSize(15);
        button.setWidth(1000);
        button.setHeight(100);
        button.getBackground().setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.DARKEN);
        linearLayout.addView(button);
        final ScoutingModel updateMatchEntry = match;
        button.setOnClickListener(v -> {
            updateMatch.add(updateMatchEntry);
            fragment = new EditActivity();
            loadFragment(new EditActivity());
        });
    }

    public void updateScore(ScoutingModel match) {
        if (match.isAutoDrop()) {score +=30;}
        if (match.isSample()) {score+=25;}
        if (match.isDoubleSample()) {score+=50;}
        if (match.isMarker()) {score+=15;}
        if (match.isAutoPark()) {score+=10;}
        if (match.getDepot()>0) {score+=match.getDepot()*2;}
        if (match.getLander()>0) {score+=match.getLander()*5;}
        if (match.isEndHang()) {score+=50;}
        if (match.isEndPartial()) {score+=15;}
        if (match.isFullPark()) {score+=25;}
    }

    public void createCSV() {

        if (matches.size()>0) {
            String filename = "Scouting Data " + matches.get(0).getTournament() + String.valueOf(matches.get(matches.size() - 1).getMatchNumber()) + ".csv";

            File directoryDocument = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(directoryDocument, filename);
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestValue);
                requestValue += 1;
            }
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestValue);
                requestValue += 1;
            }
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(("Tournament Name,Match Number,Team Number,Auto Drop,Marker,Auto Park,Sample,Depot,Lander,End Hang,End Park" + System.lineSeparator()).getBytes());
                for (ScoutingModel match : matches) createRows(match);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(getActivity(), "No matches to export", Toast.LENGTH_SHORT).show();
        }
    }

    public void createRows(ScoutingModel match){
        if (match.isAutoDrop()) {drop = "y";}
        else {drop = "n";}
        if (match.isSample()) {sample = String.valueOf(25);}
        else if (match.isDoubleSample()) {sample = String.valueOf(50);}
        else {sample = "n";}
        if (match.isMarker()) {marker = "y";}
        else {marker = "n";}
        if (match.isAutoPark()) {autoPark = "y";}
        else {autoPark = "n";}
        if (match.isEndHang()) {hang = "y";}
        else {hang = "n";}
        if (match.isEndPartial()) {endPark = String.valueOf(15);}
        else if (match.isFullPark()) {endPark = String.valueOf(25);}
        else {endPark = "n";}
        try {
            outputStream.write((match.getTournament() + ",").getBytes());
            outputStream.write((match.getMatchNumber() + ",").getBytes());
            outputStream.write((match.getTeamNumber() + ",").getBytes());
            outputStream.write((drop + ",").getBytes());
            outputStream.write((marker + ",").getBytes());
            outputStream.write((autoPark + ",").getBytes());
            outputStream.write((sample + ",").getBytes());
            outputStream.write((match.getDepot() + ",").getBytes());
            outputStream.write((match.getLander() + ",").getBytes());
            outputStream.write((hang + ",").getBytes());
            outputStream.write((endPark + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initView (View view) {
        view.setBackgroundColor(getResources().getColor(android.R.color.black));
        scrollView = view.findViewById(R.id.scrollView);
        linearLayout = view.findViewById(R.id.linearLayout);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        for (ScoutingModel match : matches) {
            score = 0;
            Button button = new Button(getContext());
            updateScore(match);
            createButton(button, match);
        }

        Button export = new Button(getContext());
        createExportButton(export);
    }

    public void loadFragment (Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.app_bar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {

        case R.id.action_delete:
            if (matches.size()>0) {
            AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            builder.setTitle("Clear all matches").setMessage("Are you sure you want to clear all matches? This action cannot be undone.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        matches.clear();
                        int matchesCount = matchesDatabase.matchesDao().getAll().size();
                        for (int i = 0; i<matchesCount;i++) {
                            matchesDatabase.matchesDao().deleteMatch(matchesDatabase.matchesDao().getMatch(i+1).get(0));
                        }
                        assert getFragmentManager()!= null;
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new DataActivity())
                                .commit();
                        Toast.makeText(getActivity(), "Matches Cleared", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {})
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();}
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
